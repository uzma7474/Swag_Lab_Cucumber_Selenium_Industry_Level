package utils;

import config.ConfigManager;
import constants.AppConstants;
import driver.DriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public final class WaitUtils {

	private static final Logger log = LoggerFactory.getLogger(WaitUtils.class);

	private static final int DEFAULT_WAIT = ConfigManager.getExplicitWait();

	private static final int DEFAULT_TIMEOUT = 15;

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

	public static WebElement waitForVisibility(WebDriver driver, WebElement element) {

		return new WebDriverWait(driver, Duration.ofSeconds(AppConstants.DEFAULT_TIMEOUT))
				.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForCondition(Function<WebDriver, Boolean> condition) {

		WebDriver driver = DriverManager.getDriver();

		new WebDriverWait(driver, Duration.ofSeconds(15)).until(condition);
	}

	public static WebElement waitForVisibility(By locator) {

		log.debug("Waiting for visibility: {}", locator);

		WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

		log.debug("Element visible: {}", locator);

		return element;
	}

	// =========================================================
	// WAIT FOR ELEMENT CLICKABLE
	// =========================================================
	/**
	 * * Waits until the supplied WebElement is visible and enabled * so that it can
	 * be clicked. * Example: * * WaitUtils.waitForElementClickable(openMenuButton);
	 */
	public static WebElement waitForElementClickable(WebElement element) {
		if (element == null) {
			throw new IllegalArgumentException("Element cannot be null");

		}
		try {
			log.debug("Waiting for element to be clickable: {}", element);
			WebElement clickableElement = getWait().until(ExpectedConditions.elementToBeClickable(element));
			log.debug("Element is clickable: {}", element);
			return clickableElement;

		} catch (TimeoutException e) {
			log.error("Element was not clickable within {} seconds: {}", DEFAULT_TIMEOUT, element, e);
			throw e;

		}

	}

	// =========================================================
	// WAIT FOR ELEMENT CLICKABLE - CUSTOM TIMEOUT
	// =========================================================
	/**
	 * * Waits until a WebElement is clickable using a custom timeout.
	 */
	public static WebElement waitForElementClickable(WebElement element, long timeoutInSeconds) {
		if (element == null) {
			throw new IllegalArgumentException("Element cannot be null");

		}

		try {
			log.debug("Waiting {} seconds for element to be clickable", timeoutInSeconds);
			return getWait().until(ExpectedConditions.elementToBeClickable(element));

		} catch (TimeoutException e) {
			log.error("Element was not clickable within {} seconds", timeoutInSeconds, e);
			throw e;

		}

	}

	// =========================================================
	// WAIT FOR PRESENCE
	// =========================================================
	/** * Waits until an element is present in DOM. */
	public static WebElement waitForPresence(By locator) {
		if (locator == null) {
			throw new IllegalArgumentException("Locator cannot be null");

		}

		try {
			return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));

		} catch (TimeoutException e) {
			log.error("Element was not present within {} seconds: {}", DEFAULT_TIMEOUT, locator, e);
			throw e;

		}

	}

	// =========================================================
	// WAIT FOR LOCATOR TO BE CLICKABLE
	// =========================================================
	/** * Waits until an element identified by By locator * is clickable. */
	public static WebElement waitForElementClickable(By locator) {
		if (locator == null) {
			throw new IllegalArgumentException("Locator cannot be null");

		}

		try {
			log.debug("Waiting for locator to be clickable: {}", locator);
			return getWait().until(ExpectedConditions.elementToBeClickable(locator));

		} catch (TimeoutException e) {
			log.error("Locator was not clickable within {} seconds: {}", DEFAULT_TIMEOUT, locator, e);
			throw e;

		}

	}

	public static String waitForText(By locator) {

		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));

		return wait.until(driver -> {

			try {

				String text = driver.findElement(locator).getText();

				if (text != null && !text.trim().isEmpty()) {
					return text.trim();
				}

			} catch (StaleElementReferenceException e) {

				// DOM changed.
				// Find the element again on the next polling cycle.
			}

			return null;
		});
	}

	public static boolean waitForTextToChange(By locator, String previousText) {

		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));

		return wait.until(driver -> {

			try {

				String currentText = driver.findElement(locator).getText();

				return currentText != null && !currentText.trim().isEmpty() && !currentText.trim().equals(previousText);

			} catch (Exception e) {

				return false;
			}
		});
	}

	/**
	 * Pauses execution for the specified number of seconds.
	 *
	 * @param seconds number of seconds to wait
	 */
	public static void waitForSeconds(int seconds) {

		if (seconds < 0) {
			throw new IllegalArgumentException("Wait time cannot be negative: " + seconds);
		}

		try {

			log.debug("Waiting for {} second(s)", seconds);

			Thread.sleep(seconds * 1000L);

		} catch (InterruptedException e) {

			Thread.currentThread().interrupt();

			log.warn("Thread was interrupted while waiting for {} second(s)", seconds);

			throw new RuntimeException("Thread interrupted during wait", e);
		}
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

	public static WebElement waitForClickability(WebDriver driver, WebElement element) {

		return new WebDriverWait(driver, Duration.ofSeconds(AppConstants.DEFAULT_TIMEOUT))
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static List<WebElement> waitForProductCards(WebDriver driver, List<WebElement> productCards) {

		return new WebDriverWait(driver, Duration.ofSeconds(AppConstants.DEFAULT_TIMEOUT)).until(d -> {
			if (productCards != null && !productCards.isEmpty()) {

				boolean allDisplayed = productCards.stream().allMatch(WebElement::isDisplayed);

				return allDisplayed ? productCards : null;
			}

			return null;
		});
	}

	public static void waitForUrlContains(WebDriver driver, String urlFragment) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.urlContains(urlFragment));
	}

	public static void waitForVisibility(WebDriver driver, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Waits until the specified WebElement is visible and clickable.
	 *
	 * @param driver  WebDriver instance
	 * @param element WebElement to wait for
	 * @return clickable WebElement
	 */
	public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {

		if (driver == null) {
			throw new IllegalArgumentException("WebDriver must not be null");
		}

		if (element == null) {
			throw new IllegalArgumentException("WebElement must not be null");
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));

		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Waits until the current page has completely loaded.
	 */
	public static void waitForPageLoad() {

		WebDriver driver = DriverManager.getDriver();

		if (driver == null) {

			log.error("WebDriver is not initialized for waitForPageLoad()");

			throw new IllegalStateException("WebDriver is not initialized for current thread");
		}

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));

			wait.until(webDriver -> {

				try {

					String readyState = ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
							.toString();

					log.debug("Current document.readyState: {}", readyState);

					return "complete".equals(readyState);

				} catch (Exception e) {

					log.debug("Unable to determine document.readyState", e);

					return false;
				}
			});

			log.info("Page loaded successfully");

		} catch (Exception e) {

			log.error("Page did not load completely within {} seconds", DEFAULT_TIMEOUT, e);

			throw new RuntimeException("Page did not load completely", e);
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