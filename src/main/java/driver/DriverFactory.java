package driver;

import config.ConfigManager;
import config.EnvironmentManager;
import constants.AppConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Factory responsible for creating WebDriver instances.
 *
 * Supported browsers: - Chrome - Firefox - Edge
 *
 * Logging: - Uses SLF4J - Log implementation is configured through Log4j2
 */
public final class DriverFactory {

	/**
	 * SLF4J logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

	/**
	 * Private constructor to prevent object creation.
	 */
	private DriverFactory() {
	}

	/**
	 * Creates a WebDriver based on configured browser.
	 *
	 * @return WebDriver instance
	 */
	public static WebDriver createDriver() {

		String browser = EnvironmentManager.getBrowser().trim().toLowerCase();

		log.info("Starting WebDriver creation. Browser: {}", browser);

		log.debug("Headless mode: {}", EnvironmentManager.isHeadless());

		WebDriver driver;

		switch (browser) {

		case AppConstants.CHROME:

			log.info("Creating Chrome WebDriver");

			driver = createChromeDriver();

			break;

		case AppConstants.FIREFOX:

			log.info("Creating Firefox WebDriver");

			driver = createFirefoxDriver();

			break;

		case AppConstants.EDGE:

			log.info("Creating Edge WebDriver");

			driver = createEdgeDriver();

			break;

		default:

			log.error("Unsupported browser requested: {}", browser);

			throw new IllegalArgumentException("Unsupported browser: " + browser + ". Supported browsers are: "
					+ AppConstants.CHROME + ", " + AppConstants.FIREFOX + ", " + AppConstants.EDGE);
		}

		log.debug("Browser driver created successfully: {}", driver.getClass().getSimpleName());

		configureDriver(driver);

		log.info("WebDriver configuration completed successfully for: {}", browser);

		return driver;
	}

	/**
	 * Creates Chrome WebDriver.
	 *
	 * @return Chrome WebDriver
	 */
	private static WebDriver createChromeDriver() {

		log.debug("Setting up ChromeDriver using WebDriverManager");

		try {

			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();

			configureChromeOptions(options);

			log.debug("Chrome options configured. Creating ChromeDriver");

			WebDriver driver = new ChromeDriver(options);

			log.info("ChromeDriver created successfully");

			return driver;

		} catch (Exception e) {

			log.error("Failed to create ChromeDriver", e);

			throw e;
		}
	}

	/**
	 * Creates Firefox WebDriver.
	 *
	 * @return Firefox WebDriver
	 */
	private static WebDriver createFirefoxDriver() {

		log.debug("Setting up FirefoxDriver using WebDriverManager");

		try {

			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions options = new FirefoxOptions();

			configureFirefoxOptions(options);

			log.debug("Firefox options configured. Creating FirefoxDriver");

			WebDriver driver = new FirefoxDriver(options);

			log.info("FirefoxDriver created successfully");

			return driver;

		} catch (Exception e) {

			log.error("Failed to create FirefoxDriver", e);

			throw e;
		}
	}

	/**
	 * Creates Edge WebDriver.
	 *
	 * @return Edge WebDriver
	 */
	private static WebDriver createEdgeDriver() {

		log.debug("Setting up EdgeDriver using WebDriverManager");

		try {

			WebDriverManager.edgedriver().setup();

			EdgeOptions options = new EdgeOptions();

			configureEdgeOptions(options);

			log.debug("Edge options configured. Creating EdgeDriver");

			WebDriver driver = new EdgeDriver(options);

			log.info("EdgeDriver created successfully");

			return driver;

		} catch (Exception e) {

			log.error("Failed to create EdgeDriver", e);

			throw e;
		}
	}

	/**
	 * Configures Chrome-specific options.
	 *
	 * @param options Chrome options
	 */
	private static void configureChromeOptions(ChromeOptions options) {

		log.debug("Configuring Chrome options");

		options.addArguments("--disable-notifications");

		options.addArguments("--disable-popup-blocking");

		options.addArguments("--disable-infobars");

		options.addArguments("--remote-allow-origins=*");

		if (EnvironmentManager.isHeadless()) {

			log.info("Chrome running in headless mode");

			options.addArguments("--headless=new");

			options.addArguments("--window-size=1920,1080");
		}
	}

	/**
	 * Configures Firefox-specific options.
	 *
	 * @param options Firefox options
	 */
	private static void configureFirefoxOptions(FirefoxOptions options) {

		log.debug("Configuring Firefox options");

		options.addPreference("dom.webnotifications.enabled", false);

		if (EnvironmentManager.isHeadless()) {

			log.info("Firefox running in headless mode");

			options.addArguments("--headless");

			options.addArguments("--width=1920");

			options.addArguments("--height=1080");
		}
	}

	/**
	 * Configures Edge-specific options.
	 *
	 * @param options Edge options
	 */
	private static void configureEdgeOptions(EdgeOptions options) {

		log.debug("Configuring Edge options");

		options.addArguments("--disable-notifications");

		options.addArguments("--disable-popup-blocking");

		options.addArguments("--remote-allow-origins=*");

		if (EnvironmentManager.isHeadless()) {

			log.info("Edge running in headless mode");

			options.addArguments("--headless=new");

			options.addArguments("--window-size=1920,1080");
		}
	}

	/**
	 * Configures common WebDriver settings.
	 *
	 * @param driver WebDriver instance
	 */
	private static void configureDriver(WebDriver driver) {

		log.debug("Configuring common WebDriver settings");

		configureTimeouts(driver);

		configureWindow(driver);

		log.debug("Common WebDriver configuration completed");
	}

	/**
	 * Configures Selenium timeout settings.
	 *
	 * @param driver WebDriver instance
	 */
	private static void configureTimeouts(WebDriver driver) {

		long implicitWait = ConfigManager.getImplicitWait();

		long pageLoadTimeout = ConfigManager.getPageLoadTimeout();

		long scriptTimeout = ConfigManager.getScriptTimeout();

		log.info("Configuring Selenium timeouts - " + "Implicit: {}s, Page Load: {}s, Script: {}s", implicitWait,
				pageLoadTimeout, scriptTimeout);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));

		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(scriptTimeout));

		log.debug("Selenium timeout configuration completed");
	}

	/**
	 * Configures browser window.
	 *
	 * @param driver WebDriver instance
	 */
	private static void configureWindow(WebDriver driver) {

		if (EnvironmentManager.isHeadless()) {

			log.debug("Headless mode enabled. Skipping window maximize");

			return;
		}

		if (EnvironmentManager.shouldMaximize()) {

			log.info("Maximizing browser window");

			driver.manage().window().maximize();

			log.debug("Browser window maximized successfully");

		} else {

			log.debug("Browser maximize disabled by configuration");
		}
	}
}