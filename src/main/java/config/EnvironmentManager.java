package config;

import constants.AppConstants;

/**
 * Handles environment-specific configuration.
 */
public final class EnvironmentManager {

	private EnvironmentManager() {
	}

	/**
	 * Returns the current environment.
	 *
	 * @return qa or prod
	 */
	public static String getEnvironment() {

		return ConfigManager.getEnvironment();
	}

	/**
	 * Checks whether QA environment is active.
	 *
	 * @return true if QA
	 */
	public static boolean isQA() {

		return AppConstants.QA_ENVIRONMENT.equalsIgnoreCase(getEnvironment());
	}

	/**
	 * Checks whether production environment is active.
	 *
	 * @return true if production
	 */
	public static boolean isProd() {

		return AppConstants.PROD_ENVIRONMENT.equalsIgnoreCase(getEnvironment());
	}

	/**
	 * Returns the application URL for the currently selected environment.
	 *
	 * @return application URL
	 */
	public static String getBaseUrl() {

		return ConfigManager.getApplicationUrl();
	}

	/**
	 * Returns configured browser.
	 *
	 * @return browser name
	 */
	public static String getBrowser() {

		return ConfigManager.getBrowser();
	}

	/**
	 * Returns whether browser should run headless.
	 *
	 * @return true if headless
	 */
	public static boolean isHeadless() {

		return ConfigManager.isHeadless();
	}

	/**
	 * Returns whether browser should maximize.
	 *
	 * @return true if maximize is enabled
	 */
	public static boolean shouldMaximize() {

		return ConfigManager.shouldMaximize();
	}

	/**
	 * Prints environment information.
	 */
	public static void printEnvironmentInfo() {

		System.out.println("========================================");

		System.out.println("Environment : " + getEnvironment());

		System.out.println("Browser     : " + getBrowser());

		System.out.println("Headless    : " + isHeadless());

		System.out.println("Base URL    : " + getBaseUrl());

		System.out.println("========================================");
	}
}
