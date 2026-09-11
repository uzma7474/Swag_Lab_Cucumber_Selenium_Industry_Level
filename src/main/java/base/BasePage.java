package base;

import driver.DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

	protected final WebDriver driver;

	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected BasePage() {

		this.driver = DriverManager.getDriver();

		if (this.driver == null) {

			log.error("WebDriver is not initialized for {}", getClass().getSimpleName());

			throw new IllegalStateException("WebDriver is not initialized");
		}

		log.debug("Initialized page object: {}", getClass().getSimpleName());
	}

	// ============================================================
	// Navigation
	// ============================================================

	protected void navigateTo(String url) {

		log.info("Navigating to URL: {}", url);

		try {

			driver.get(url);

			log.info("Navigation completed. Current URL: {}", driver.getCurrentUrl());

		} catch (Exception e) {

			log.error("Failed to navigate to URL: {}", url, e);

			throw e;
		}
	}

	protected String getCurrentUrl() {

		String currentUrl = driver.getCurrentUrl();

		log.debug("Current URL: {}", currentUrl);

		return currentUrl;
	}

	protected String getPageTitle() {

		String title = driver.getTitle();

		log.debug("Current page title: {}", title);

		return title;
	}

	// ============================================================
	// Element Actions
	// ============================================================

	/**
	 * Types text into an input field.
	 */
	protected void type(WebElement element, String text) {

		if (element == null) {

			log.error("Cannot type because WebElement is null");

			throw new IllegalArgumentException("WebElement cannot be null");
		}

		try {

			log.debug("Entering text into element: {}", elementDescription(element));

			element.clear();
			element.sendKeys(text);

			log.debug("Text entered successfully");

		} catch (Exception e) {

			log.error("Failed to enter text into element: {}", elementDescription(element), e);

			throw e;
		}
	}

	/**
	 * Clicks a WebElement.
	 */
	protected void click(WebElement element) {

		if (element == null) {

			log.error("Cannot click because WebElement is null");

			throw new IllegalArgumentException("WebElement cannot be null");
		}

		try {

			log.debug("Clicking element: {}", elementDescription(element));

			element.click();

			log.debug("Element clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click element: {}", elementDescription(element), e);

			throw e;
		}
	}

	/**
	 * Gets visible text from a WebElement.
	 */
	protected String getText(WebElement element) {

		if (element == null) {

			log.error("Cannot retrieve text because WebElement is null");

			throw new IllegalArgumentException("WebElement cannot be null");
		}

		try {

			String text = element.getText();

			log.debug("Retrieved text from element: {}", text);

			return text;

		} catch (Exception e) {

			log.error("Failed to retrieve text from element: {}", elementDescription(element), e);

			throw e;
		}
	}

	/**
	 * Checks whether an element is displayed.
	 */
	protected boolean isDisplayed(WebElement element) {

		if (element == null) {

			log.warn("Cannot check visibility because WebElement is null");

			return false;
		}

		try {

			boolean displayed = element.isDisplayed();

			log.debug("Element displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.debug("Element is not displayed or is unavailable");

			return false;
		}
	}

	/**
	 * Checks whether an element is enabled.
	 */
	protected boolean isEnabled(WebElement element) {

		if (element == null) {

			log.warn("Cannot check enabled state because WebElement is null");

			return false;
		}

		try {

			boolean enabled = element.isEnabled();

			log.debug("Element enabled: {}", enabled);

			return enabled;

		} catch (Exception e) {

			log.debug("Unable to determine element enabled state", e);

			return false;
		}
	}

	/**
	 * Gets an element attribute.
	 */
	protected String getAttribute(WebElement element, String attributeName) {

		if (element == null) {

			log.error("Cannot retrieve attribute because WebElement is null");

			throw new IllegalArgumentException("WebElement cannot be null");
		}

		try {

			String value = element.getAttribute(attributeName);

			log.debug("Element attribute '{}' value: {}", attributeName, value);

			return value;

		} catch (Exception e) {

			log.error("Failed to retrieve attribute '{}' from element", attributeName, e);

			throw e;
		}
	}

	/**
	 * Returns a safe description of the element for logging.
	 */
	private String elementDescription(WebElement element) {

		try {

			String id = element.getAttribute("id");

			if (id != null && !id.isBlank()) {
				return "id=" + id;
			}

			String dataTest = element.getAttribute("data-test");

			if (dataTest != null && !dataTest.isBlank()) {
				return "data-test=" + dataTest;
			}

			return element.getTagName();

		} catch (Exception e) {

			return "unknown-element";
		}
	}
}