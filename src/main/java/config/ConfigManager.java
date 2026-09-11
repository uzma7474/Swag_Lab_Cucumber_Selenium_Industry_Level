package config;

import constants.AppConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Manages framework and environment configuration.
 *
 * Configuration hierarchy:
 *
 * config.properties ↓ qa.properties / prod.properties
 *
 * Environment-specific properties override common properties when the same key
 * exists.
 */
public final class ConfigManager {

	private static final Properties COMMON_PROPERTIES = new Properties();

	private static final Properties ENVIRONMENT_PROPERTIES = new Properties();

	static {

		loadCommonProperties();

		loadEnvironmentProperties();
	}

	/**
	 * Private constructor to prevent object creation.
	 */
	private ConfigManager() {
	}

	/**
	 * Loads common configuration from config.properties.
	 */
	private static void loadCommonProperties() {

		loadProperties(AppConstants.CONFIG_FILE, COMMON_PROPERTIES);
	}

	/**
	 * Loads environment-specific configuration.
	 */
	private static void loadEnvironmentProperties() {

		String environment = COMMON_PROPERTIES.getProperty(AppConstants.ENVIRONMENT_KEY,
				AppConstants.DEFAULT_ENVIRONMENT);

		String environmentFile;

		switch (environment.toLowerCase()) {

		case AppConstants.QA_ENVIRONMENT:

			environmentFile = AppConstants.QA_CONFIG_FILE;

			break;

		case AppConstants.PROD_ENVIRONMENT:

			environmentFile = AppConstants.PROD_CONFIG_FILE;

			break;

		default:

			throw new IllegalArgumentException("Unsupported environment: " + environment);
		}

		loadProperties(environmentFile, ENVIRONMENT_PROPERTIES);
	}

	/**
	 * Loads a properties file.
	 *
	 * @param fileName   properties file path
	 * @param properties Properties object
	 */
	private static void loadProperties(String fileName, Properties properties) {

		try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(fileName)) {

			if (inputStream == null) {

				throw new RuntimeException("Configuration file not found: " + fileName);
			}

			properties.load(inputStream);

		} catch (IOException e) {

			throw new RuntimeException("Unable to load configuration file: " + fileName, e);
		}
	}

	/**
	 * Gets a configuration value.
	 *
	 * Environment-specific configuration takes precedence over common
	 * configuration.
	 *
	 * @param key property key
	 * @return property value or null
	 */
	public static String get(String key) {

		String value = ENVIRONMENT_PROPERTIES.getProperty(key);

		if (value != null) {
			return value.trim();
		}

		value = COMMON_PROPERTIES.getProperty(key);

		if (value != null) {
			return value.trim();
		}

		return null;
	}

	/**
	 * Gets a configuration value with a default value.
	 *
	 * @param key          property key
	 * @param defaultValue default value
	 * @return property value or default value
	 */
	public static String get(String key, String defaultValue) {

		String value = get(key);

		return value != null ? value : defaultValue;
	}

	/**
	 * Gets an integer configuration value.
	 *
	 * @param key          property key
	 * @param defaultValue default integer
	 * @return integer value
	 */
	public static int getInt(String key, int defaultValue) {

		String value = get(key);

		if (value == null || value.isBlank()) {
			return defaultValue;
		}

		try {

			return Integer.parseInt(value.trim());

		} catch (NumberFormatException e) {

			throw new IllegalArgumentException("Invalid integer value for property '" + key + "': " + value, e);
		}
	}

	/**
	 * Gets a boolean configuration value.
	 *
	 * @param key          property key
	 * @param defaultValue default boolean
	 * @return boolean value
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {

		String value = get(key);

		if (value == null || value.isBlank()) {
			return defaultValue;
		}

		return Boolean.parseBoolean(value.trim());
	}

	/**
	 * Gets the currently configured environment.
	 *
	 * @return environment name
	 */
	public static String getEnvironment() {

		return get(AppConstants.ENVIRONMENT_KEY, AppConstants.DEFAULT_ENVIRONMENT);
	}

	/**
	 * Gets the application URL.
	 *
	 * @return application URL
	 */
	public static String getApplicationUrl() {

		return get(AppConstants.APPLICATION_URL_KEY, AppConstants.DEFAULT_APPLICATION_URL);
	}

	/**
	 * Gets the configured browser.
	 *
	 * @return browser name
	 */
	public static String getBrowser() {

		return get(AppConstants.BROWSER_KEY, AppConstants.DEFAULT_BROWSER);
	}

	/**
	 * Gets explicit wait timeout.
	 *
	 * @return explicit wait in seconds
	 */
	public static int getExplicitWait() {

		return getInt(AppConstants.EXPLICIT_WAIT_KEY, AppConstants.DEFAULT_EXPLICIT_WAIT);
	}

	/**
	 * Gets implicit wait timeout.
	 *
	 * @return implicit wait in seconds
	 */
	public static int getImplicitWait() {

		return getInt(AppConstants.IMPLICIT_WAIT_KEY, AppConstants.DEFAULT_IMPLICIT_WAIT);
	}

	/**
	 * Gets page load timeout.
	 *
	 * @return page load timeout in seconds
	 */
	public static int getPageLoadTimeout() {

		return getInt(AppConstants.PAGE_LOAD_TIMEOUT_KEY, AppConstants.DEFAULT_PAGE_LOAD_TIMEOUT);
	}

	/**
	 * Gets script timeout.
	 *
	 * @return script timeout in seconds
	 */
	public static int getScriptTimeout() {

		return getInt(AppConstants.SCRIPT_TIMEOUT_KEY, AppConstants.DEFAULT_SCRIPT_TIMEOUT);
	}

	/**
	 * Gets headless browser setting.
	 *
	 * @return true if headless
	 */
	public static boolean isHeadless() {

		return getBoolean(AppConstants.HEADLESS_KEY, false);
	}

	/**
	 * Gets browser maximize setting.
	 *
	 * @return true if browser should maximize
	 */
	public static boolean shouldMaximize() {

		return getBoolean(AppConstants.MAXIMIZE_KEY, true);
	}

	/**
	 * Gets screenshot-on-failure setting.
	 *
	 * @return true if screenshot should be captured
	 */
	public static boolean isScreenshotOnFailureEnabled() {

		return getBoolean(AppConstants.SCREENSHOT_ON_FAILURE_KEY, true);
	}

	/**
	 * Gets screenshot-after-step setting.
	 *
	 * @return true if screenshot should be captured
	 */
	public static boolean isScreenshotAfterStepEnabled() {

		return getBoolean(AppConstants.SCREENSHOT_AFTER_STEP_KEY, false);
	}

	/**
	 * Gets log level.
	 *
	 * @return configured log level
	 */
	public static String getLogLevel() {

		return get(AppConstants.LOG_LEVEL_KEY, AppConstants.DEFAULT_LOG_LEVEL);
	}
}
