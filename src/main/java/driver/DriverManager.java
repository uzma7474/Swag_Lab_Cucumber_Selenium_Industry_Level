package driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages WebDriver instances for the current execution thread.
 *
 * ThreadLocal is used so that each parallel test thread gets its own
 * independent WebDriver instance.
 *
 * Logging: - Uses SLF4J - Logs driver creation, retrieval, validation and
 * cleanup
 */
public final class DriverManager {

	/**
	 * SLF4J logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(DriverManager.class);

	/**
	 * ThreadLocal WebDriver instance.
	 *
	 * Each execution thread gets its own WebDriver.
	 */
	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

	/**
	 * Private constructor to prevent object creation.
	 */
	private DriverManager() {
	}

	/**
	 * Stores WebDriver for the current thread.
	 *
	 * @param driver WebDriver instance
	 */
	public static void setDriver(WebDriver driver) {

		if (driver == null) {

			log.error("Attempted to initialize ThreadLocal with null WebDriver");

			throw new IllegalArgumentException("WebDriver cannot be null.");
		}

		DRIVER.set(driver);

		log.info("WebDriver initialized successfully. " + "Thread: {}, Driver: {}", Thread.currentThread().getName(),
				driver.getClass().getSimpleName());
	}

	/**
	 * Returns WebDriver associated with the current thread.
	 *
	 * @return current WebDriver
	 */
	public static WebDriver getDriver() {

		WebDriver driver = DRIVER.get();

		if (driver == null) {

			log.error("WebDriver is not initialized for current thread. " + "Thread: {}",
					Thread.currentThread().getName());

			throw new IllegalStateException("WebDriver is not initialized for the current thread. "
					+ "Please initialize the driver before accessing it.");
		}

		log.debug("Returning WebDriver for current thread. " + "Thread: {}, Driver: {}",
				Thread.currentThread().getName(), driver.getClass().getSimpleName());

		return driver;
	}

	/**
	 * Checks whether a WebDriver has been initialized for the current execution
	 * thread.
	 *
	 * @return true if driver exists
	 */
	public static boolean isDriverInitialized() {

		boolean initialized = DRIVER.get() != null;

		log.debug("Driver initialized status: {}. Thread: {}", initialized, Thread.currentThread().getName());

		return initialized;
	}

	/**
	 * Quits the current WebDriver and removes it from ThreadLocal.
	 */
	public static void quitDriver() {

		WebDriver driver = DRIVER.get();

		if (driver == null) {

			log.debug("No WebDriver found to quit. Thread: {}", Thread.currentThread().getName());

			return;
		}

		log.info("Quitting WebDriver. Thread: {}, Driver: {}", Thread.currentThread().getName(),
				driver.getClass().getSimpleName());

		try {

			driver.quit();

			log.info("WebDriver quit successfully. Thread: {}", Thread.currentThread().getName());

		} catch (Exception e) {

			log.error("Exception occurred while quitting WebDriver. " + "Thread: {}", Thread.currentThread().getName(),
					e);

			throw e;

		} finally {

			DRIVER.remove();

			log.debug("WebDriver removed from ThreadLocal. Thread: {}", Thread.currentThread().getName());
		}
	}

	/**
	 * Removes the WebDriver reference without calling quit().
	 *
	 * Normally quitDriver() should be used instead.
	 */
	public static void removeDriver() {

		WebDriver driver = DRIVER.get();

		if (driver != null) {

			log.warn("Removing WebDriver from ThreadLocal without " + "calling quit(). Thread: {}, Driver: {}",
					Thread.currentThread().getName(), driver.getClass().getSimpleName());

		} else {

			log.debug("No WebDriver reference found in ThreadLocal. " + "Thread: {}", Thread.currentThread().getName());
		}

		DRIVER.remove();

		log.debug("WebDriver reference removed from ThreadLocal. " + "Thread: {}", Thread.currentThread().getName());
	}
}