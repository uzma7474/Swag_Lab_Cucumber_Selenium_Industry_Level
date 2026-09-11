package hooks;

import driver.DriverFactory;
import driver.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reports.ExtentManager;
import reports.ExtentTestManager;

import java.util.Arrays;

public class Hooks {

	private static final Logger log = LoggerFactory.getLogger(Hooks.class);

	/**
	 * Runs before every Cucumber scenario.
	 */

	@Before(order = 0)
	public void beforeScenario(Scenario scenario) {

		String scenarioName = scenario.getName();

		log.info("==================================================");
		log.info("Starting Cucumber Scenario: {}", scenarioName);
		log.info("Scenario Tags: {}", scenario.getSourceTagNames());

		try {

			// =========================================================
			// 1. Create WebDriver
			// =========================================================

			log.info("Creating WebDriver");

			WebDriver driver = DriverFactory.createDriver();

			// Store driver in ThreadLocal
			DriverManager.setDriver(driver);

			log.info("WebDriver initialized successfully. Thread: {}", Thread.currentThread().getName());

			// =========================================================
			// 2. Initialize Extent Report
			// =========================================================

			ExtentTestManager.startTest(scenarioName);

			ExtentTestManager.assignCategories(extractTags(scenario));

			String browser = System.getProperty("browser", "chrome");

			ExtentTestManager.assignDevice(browser);

			ExtentTestManager.info("Starting scenario: " + scenarioName);

			log.info("Extent test initialized successfully for scenario: {}", scenarioName);

			log.info("Browser: {}", browser);

		} catch (Exception e) {

			log.error("Failed to initialize scenario: {}", scenarioName, e);

			DriverManager.quitDriver();

			throw e;
		}
	}

	/**
	 * Runs after every Cucumber step.
	 */
	@AfterStep
	public void afterStep(Scenario scenario) {

		String scenarioName = scenario.getName();

		try {

			if (scenario.isFailed()) {

				log.error("Cucumber step failed in scenario: {}", scenarioName);

				attachScreenshot("Step Failed");

				ExtentTestManager.fail("Step execution failed");

			} else {

				log.debug("Cucumber step executed successfully in scenario: {}", scenarioName);

				ExtentTestManager.pass("Step executed successfully");
			}

		} catch (Exception e) {

			log.error("Error while processing @AfterStep for scenario: {}", scenarioName, e);

			ExtentTestManager.warning("Error while processing after step: " + e.getMessage());
		}
	}

	/**
	 * Runs after every Cucumber scenario.
	 */
	@After(order = 0)
	public void afterScenario(Scenario scenario) {

		String scenarioName = scenario.getName();

		log.info("Ending Cucumber Scenario: {}", scenarioName);

		try {

			if (scenario.isFailed()) {

				log.error("Scenario FAILED: {}", scenarioName);

				attachScreenshot("Scenario Failed");

				ExtentTestManager.fail("Scenario Failed: " + scenarioName);

			} else {

				log.info("Scenario PASSED: {}", scenarioName);

				ExtentTestManager.pass("Scenario Passed: " + scenarioName);
			}

		} catch (Exception e) {

			log.error("Error while updating Extent report for scenario: {}", scenarioName, e);

		} finally {

			/*
			 * --------------------------------------------------------- 1. Flush
			 * ExtentReports ---------------------------------------------------------
			 */
			try {

				log.debug("Flushing ExtentReports");

				ExtentManager.flush();

				log.debug("ExtentReports flushed successfully");

			} catch (Exception e) {

				log.error("Failed to flush ExtentReports", e);
			}

			/*
			 * --------------------------------------------------------- 2. Remove
			 * ExtentTest from ThreadLocal
			 * ---------------------------------------------------------
			 */
			try {

				log.debug("Removing ExtentTest from ThreadLocal");

				ExtentTestManager.removeTest();

				log.debug("ExtentTest removed successfully");

			} catch (Exception e) {

				log.error("Failed to remove ExtentTest", e);
			}

			/*
			 * --------------------------------------------------------- 3. Close WebDriver
			 * ---------------------------------------------------------
			 */
			quitDriver();

			log.info("Finished Cucumber Scenario: {}", scenarioName);

			log.info("==================================================");
		}
	}

	/**
	 * Captures and attaches screenshot to ExtentReports.
	 */
	private void attachScreenshot(String message) {

		log.debug("Attempting to capture screenshot: {}", message);

		try {

			if (!DriverManager.isDriverInitialized()) {

				log.warn("Cannot capture screenshot because WebDriver " + "is not initialized");

				ExtentTestManager.warning("WebDriver is not initialized. " + "Screenshot could not be captured.");

				return;
			}

			WebDriver driver = DriverManager.getDriver();

			if (!(driver instanceof TakesScreenshot)) {

				log.warn("Current WebDriver does not support screenshots");

				ExtentTestManager.warning("WebDriver does not support screenshots.");

				return;
			}

			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

			String base64Screenshot = java.util.Base64.getEncoder().encodeToString(screenshot);

			if (ExtentTestManager.getTest() != null) {

				ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot, message);

				log.info("Screenshot successfully attached to " + "Extent report: {}", message);

			} else {

				log.warn("ExtentTest is not available. " + "Screenshot cannot be attached.");
			}

		} catch (Exception e) {

			log.error("Unable to capture screenshot: {}", message, e);

			try {

				ExtentTestManager.warning("Unable to capture screenshot: " + e.getMessage());

			} catch (Exception extentException) {

				log.error("Unable to log screenshot failure " + "to ExtentReports", extentException);
			}
		}
	}

	/**
	 * Extracts Cucumber scenario tags.
	 */
	private String[] extractTags(Scenario scenario) {

		String[] tags = scenario.getSourceTagNames().toArray(new String[0]);

		log.debug("Extracted scenario tags: {}", Arrays.toString(tags));

		return tags;
	}

	/**
	 * Safely quits browser and removes WebDriver from ThreadLocal.
	 */
	private void quitDriver() {

		try {

			if (!DriverManager.isDriverInitialized()) {

				log.debug("No WebDriver found for current thread. " + "Browser cleanup not required.");

				return;
			}

			log.info("Closing browser for current thread");

			DriverManager.quitDriver();

			log.info("Browser closed and WebDriver ThreadLocal " + "cleaned successfully");

		} catch (Exception e) {

			log.error("Unable to close browser", e);

			try {

				ExtentTestManager.warning("Unable to close browser: " + e.getMessage());

			} catch (Exception extentException) {

				log.error("Unable to log browser close failure", extentException);
			}
		}
	}
}