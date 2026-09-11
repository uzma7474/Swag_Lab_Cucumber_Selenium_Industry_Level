package base;

import config.EnvironmentManager;
import driver.DriverFactory;
import driver.DriverManager;

import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base class for traditional TestNG tests.
 *
 * Cucumber scenarios should normally use Hooks.java for WebDriver lifecycle
 * management.
 *
 * This class is useful if the framework contains additional direct TestNG
 * tests.
 */
public abstract class BaseTest {

	private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

	/**
	 * Initializes WebDriver before each TestNG test.
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		log.info("==================================================");
		log.info("Starting TestNG test setup");
		log.info("Test class: {}", getClass().getSimpleName());

		try {

			// --------------------------------------------------------
			// Create WebDriver
			// --------------------------------------------------------

			log.info("Creating WebDriver");

			WebDriver driver = DriverFactory.createDriver();

			log.info("WebDriver created successfully: {}", driver.getClass().getSimpleName());

			// --------------------------------------------------------
			// Register WebDriver in DriverManager
			// --------------------------------------------------------

			DriverManager.setDriver(driver);

			log.info("WebDriver registered in DriverManager");

			// --------------------------------------------------------
			// Print environment information
			// --------------------------------------------------------

			log.info("Loading test environment information");

			EnvironmentManager.printEnvironmentInfo();

			// --------------------------------------------------------
			// Navigate to application
			// --------------------------------------------------------

			String baseUrl = EnvironmentManager.getBaseUrl();

			log.info("Navigating to application base URL: {}", baseUrl);

			driver.get(baseUrl);

			log.info("Application loaded successfully");

			log.info("Current URL: {}", driver.getCurrentUrl());

			log.info("Page title: {}", driver.getTitle());

		} catch (Exception e) {

			log.error("Failed during TestNG test setup", e);

			/*
			 * If driver initialization partially succeeded, make sure the WebDriver is
			 * cleaned up.
			 */
			try {

				if (DriverManager.isDriverInitialized()) {

					log.warn("Cleaning up WebDriver after setup failure");

					DriverManager.quitDriver();
				}

			} catch (Exception cleanupException) {

				log.error("Failed to clean up WebDriver after setup failure", cleanupException);
			}

			throw e;
		}

		log.info("TestNG test setup completed successfully");
		log.info("==================================================");
	}

	/**
	 * Returns the current WebDriver.
	 *
	 * @return WebDriver
	 */
	protected WebDriver getDriver() {

		log.debug("Retrieving WebDriver for test class: {}", getClass().getSimpleName());

		WebDriver driver = DriverManager.getDriver();

		log.debug("WebDriver retrieved successfully: {}", driver.getClass().getSimpleName());

		return driver;
	}

	/**
	 * Closes WebDriver after each TestNG test.
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		log.info("==================================================");
		log.info("Starting TestNG test teardown");
		log.info("Test class: {}", getClass().getSimpleName());

		try {

			if (DriverManager.isDriverInitialized()) {

				log.info("WebDriver is initialized. Closing browser");

				DriverManager.quitDriver();

				log.info("WebDriver closed and ThreadLocal cleaned successfully");

			} else {

				log.debug("No WebDriver initialized for current thread. " + "Browser cleanup not required.");
			}

		} catch (Exception e) {

			log.error("Failed during TestNG WebDriver teardown", e);

			/*
			 * Do not rethrow here.
			 *
			 * @AfterMethod(alwaysRun = true) should complete cleanup without hiding the
			 * original test failure.
			 */
		}

		log.info("TestNG test teardown completed");
		log.info("==================================================");
	}
}