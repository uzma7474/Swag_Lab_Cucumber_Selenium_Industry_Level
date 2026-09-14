package utils;

import config.ConfigManager;
import driver.DriverManager;

import org.openqa.selenium.By;
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

	public static boolean waitForUrlContains(String expectedUrl) {

		log.debug("Waiting for URL to contain: {}", expectedUrl);

		boolean result = getWait().until(ExpectedConditions.urlContains(expectedUrl));

		log.debug("URL condition satisfied: {}", expectedUrl);

		return result;
	}
	
	
	
	
	
}