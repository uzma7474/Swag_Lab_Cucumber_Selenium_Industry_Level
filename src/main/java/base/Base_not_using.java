package base;


import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Base_not_using {

	protected final WebDriver driver;

	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected Base_not_using() {

		this.driver = DriverManager.getDriver();

		if (this.driver == null) {

			log.error("WebDriver is not initialized for {}", getClass().getSimpleName());

			throw new IllegalStateException("WebDriver is not initialized");
		}

		log.debug("Initialized page object: {}", getClass().getSimpleName());
	}

	protected void navigateTo(String url) {

		log.info("Navigating to URL: {}", url);

		driver.get(url);

		log.info("Navigation completed. Current URL: {}", driver.getCurrentUrl());
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
}