package utils;

import config.ConfigManager;
import constants.AppConstants;
import driver.DriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public final class WaitUtils {

	private static final Logger log = LoggerFactory.getLogger(WaitUtils.class);

	private static final int DEFAULT_WAIT = ConfigManager.getExplicitWait();

	private WaitUtils() {
		// Prevent object creation
	}

	private static WebDriver getDriver() {

		WebDriver driver = DriverManager.getDriver();

		if (driver == null) {

			log.error("WebDriver is not initialized");

			throw new IllegalStateException("WebDriver is not initialized");
		}

		return driver;
	}

	private static WebDriverWait getWait() {

		return new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_WAIT));
	}

	public static WebElement waitForVisibility(By locator) {

		log.debug("Waiting for visibility: {}", locator);

		WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

		log.debug("Element visible: {}", locator);

		return element;
	}

	public static WebElement waitForClickable(By locator) {

		log.debug("Waiting for clickable element: {}", locator);

		WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));

		log.debug("Element clickable: {}", locator);

		return element;
	}

	public static WebElement waitForClickable(WebElement locator) {

		log.debug("Waiting for clickable element: {}", locator);

		WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));

		log.debug("Element clickable: {}", locator);

		return element;
	}

	public static boolean waitForUrlContains(String expectedUrl) {

		log.debug("Waiting for URL to contain: {}", expectedUrl);

		boolean result = getWait().until(ExpectedConditions.urlContains(expectedUrl));

		log.debug("URL condition satisfied: {}", expectedUrl);

		return result;
	}

	// =========================================================
	// GENERIC CONDITION
	// =========================================================
	/**
	 * * Waits until the supplied ExpectedCondition is satisfied. * * Example: * *
	 * boolean result = WaitUtils.waitForCondition( * driver -> checkoutStepOnePage
	 * * .isErrorMessageDisplayed()); * * @param condition ExpectedCondition to wait
	 * for * @return condition result
	 */
	public static boolean waitForCondition(ExpectedCondition<Boolean> condition) {
		if (condition == null) {
			log.error("Wait condition must not be null");
			throw new IllegalArgumentException("Wait condition must not be null");

		}
		log.debug("Waiting for custom condition");
		Boolean result = getWait().until(condition);
		log.debug("Custom wait condition satisfied: {}", result);
		return Boolean.TRUE.equals(result);

	}

	// =========================================================
	// GENERIC CONDITION WITH CUSTOM TIMEOUT
	// =========================================================
	/**
	 * * Waits until the supplied ExpectedCondition is satisfied * using a custom
	 * timeout. * * @param condition ExpectedCondition to wait for * @param
	 * timeoutInSeconds timeout in seconds * @return condition result
	 */
	public static boolean waitForCondition(ExpectedCondition<Boolean> condition, int timeoutInSeconds) {
		if (condition == null) {
			log.error("Wait condition must not be null");
			throw new IllegalArgumentException("Wait condition must not be null");

		}
		if (timeoutInSeconds <= 0) {
			log.error("Timeout must be greater than zero: {}", timeoutInSeconds);
			throw new IllegalArgumentException("Timeout must be greater than zero");

		}
		log.debug("Waiting for custom condition. Timeout: {} seconds", timeoutInSeconds);
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
		Boolean result = wait.until(condition);
		log.debug("Custom wait condition satisfied: {}", result);
		return Boolean.TRUE.equals(result);

	}

	public static boolean waitForElementVisible(WebElement element, int timeoutInSeconds) {

		WebDriver driver = DriverManager.getDriver();

		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized");
		}

		if (element == null) {
			throw new IllegalArgumentException("WebElement must not be null");
		}

		try {

			log.info("Waiting {} seconds for element to be visible", timeoutInSeconds);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

			wait.until(ExpectedConditions.visibilityOf(element));

			log.info("Element is visible");

			return true;

		} catch (Exception e) {

			log.error("Element was not visible within {} seconds", timeoutInSeconds, e);

			return false;
		}
	}

	/**
	 * Waits for an element to become visible using a String locator.
	 *
	 * @param locator CSS selector or XPath
	 * @return true if element becomes visible, otherwise false
	 */
	public static boolean waitForElementVisible_(String locator) {

		WebDriver driver = DriverManager.getDriver();

		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized");
		}

		if (locator == null || locator.trim().isEmpty()) {
			throw new IllegalArgumentException("Locator must not be null or empty");
		}

		try {

			log.info("Waiting for element to be visible: {}", locator);

			By by = createLocator(locator);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.DEFAULT_TIMEOUT));

			wait.until(ExpectedConditions.visibilityOfElementLocated(by));

			log.info("Element is visible: {}", locator);

			return true;

		} catch (TimeoutException e) {

			log.error("Element was not visible within {} seconds: {}", AppConstants.DEFAULT_TIMEOUT, locator);

			return false;
		}
	}

	/**
	 * Creates Selenium By locator. Supports CSS selector and XPath.
	 */
	private static By createLocator(String locator) {

		String trimmedLocator = locator.trim();

		if (trimmedLocator.startsWith("/") || trimmedLocator.startsWith("(")) {

			return By.xpath(trimmedLocator);

		}

		return By.cssSelector(trimmedLocator);
	}

	public static boolean waitForElementVisible(String locator) {

		WebDriver driver = DriverManager.getDriver();

		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized");
		}

		if (locator == null || locator.trim().isEmpty()) {
			throw new IllegalArgumentException("Locator must not be null or empty");
		}

		try {

			log.info("Waiting for element to be visible: {}", locator);

			By by;

			if (locator.startsWith("//") || locator.startsWith("(//")) {

				by = By.xpath(locator);

			} else {

				by = By.cssSelector(locator);
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.DEFAULT_TIMEOUT));

			wait.until(ExpectedConditions.visibilityOfElementLocated(by));

			log.info("Element is visible: {}", locator);

			return true;

		} catch (TimeoutException e) {

			log.error("Element was not visible within {} seconds: {}", AppConstants.DEFAULT_TIMEOUT, locator);

			return false;
		}
	}

	/**
	 * Waits until the product list is loaded and contains at least one product.
	 */
//	public void waitForProductList() {
//
//		log.info("Waiting for product list to be displayed");
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//
//		wait.until(d -> !productItems.isEmpty() && productItems.stream().anyMatch(WebElement::isDisplayed));
//
//		log.info("Product list is displayed successfully. Product count: {}", productItems.size());
//	}

}