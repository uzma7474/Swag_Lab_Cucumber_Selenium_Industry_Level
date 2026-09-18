package hooks;

import driver.DriverFactory;
import driver.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import context.ScenarioContext;
import reports.ExtentManager;
import reports.ExtentTestManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Hooks {

	private static final Logger log = LoggerFactory.getLogger(Hooks.class);

	private static final String SCREENSHOT_DIRECTORY = "src/test/resources/screenshots";
	
	private final ScenarioContext scenarioContext;

	public Hooks(ScenarioContext scenarioContext) {
	    this.scenarioContext = scenarioContext;
	}	

	@Before(order = 0)
	public void beforeScenario(Scenario scenario) {

		String scenarioName = scenario.getName();

		log.info("==================================================");
		log.info("Starting Cucumber Scenario: {}", scenarioName);
		log.info("Scenario Tags: {}", scenario.getSourceTagNames());

		try {

			// =====================================================
			// 1. Create WebDriver
			// =====================================================

			log.info("Creating WebDriver");

			WebDriver driver = DriverFactory.createDriver();

			DriverManager.setDriver(driver);

			log.info("WebDriver initialized successfully. Thread: {}", Thread.currentThread().getName());

			// =====================================================
			// 2. Initialize Extent Report
			// =====================================================

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
	 * Runs after every Cucumber scenario.
	 *
	 * If the scenario fails: 1. Capture screenshot 2. Save PNG file 3. Attach
	 * screenshot to Cucumber report 4. Attach screenshot to Extent Report
	 */
	@After(order = 0)
	public void afterScenario(Scenario scenario) {

		String scenarioName = scenario.getName();

		log.info("Ending Cucumber Scenario: {}", scenarioName);

		try {

			if (scenario.isFailed()) {

				log.error("Scenario FAILED: {}", scenarioName);

				captureFailureScreenshot(scenario, "Scenario Failed");

				ExtentTestManager.fail("Scenario Failed: " + scenarioName);

			} else {

				log.info("Scenario PASSED: {}", scenarioName);

				ExtentTestManager.pass("Scenario Passed: " + scenarioName);
			}

		} catch (Exception e) {

			log.error("Error while processing scenario: {}", scenarioName, e);

			try {

				ExtentTestManager.warning("Error while processing scenario: " + e.getMessage());

			} catch (Exception extentException) {

				log.error("Unable to update Extent Report", extentException);
			}

		} finally {

			// =====================================================
			// 1. Flush Extent Report
			// =====================================================

			try {

				log.debug("Flushing ExtentReports");

				ExtentManager.flush();

				log.debug("ExtentReports flushed successfully");

			} catch (Exception e) {

				log.error("Failed to flush ExtentReports", e);
			}

			// =====================================================
			// 2. Remove ExtentTest from ThreadLocal
			// =====================================================

			try {

				log.debug("Removing ExtentTest from ThreadLocal");

				ExtentTestManager.removeTest();

				log.debug("ExtentTest removed successfully");

			} catch (Exception e) {

				log.error("Failed to remove ExtentTest", e);
			}

			// =====================================================
			// 3. Close WebDriver
			// =====================================================

			quitDriver();

			log.info("Finished Cucumber Scenario: {}", scenarioName);

			log.info("==================================================");
		}
		
		  scenarioContext.clear();

	}

	/**
	 * Captures screenshot when scenario fails.
	 *
	 * Screenshot is: 1. Saved as PNG file 2. Attached to Cucumber report 3.
	 * Attached to Extent Report
	 */
	private void captureFailureScreenshot(Scenario scenario, String message) {

		log.info("Attempting to capture failure screenshot: {}", message);

		try {

			// =====================================================
			// 1. Check WebDriver
			// =====================================================

			if (!DriverManager.isDriverInitialized()) {

				log.warn("Cannot capture screenshot. " + "WebDriver is not initialized.");

				ExtentTestManager.warning("WebDriver is not initialized. " + "Screenshot could not be captured.");

				return;
			}

			WebDriver driver = DriverManager.getDriver();

			// =====================================================
			// 2. Check Screenshot Support
			// =====================================================

			if (!(driver instanceof TakesScreenshot)) {

				log.warn("Current WebDriver does not support screenshots.");

				ExtentTestManager.warning("WebDriver does not support screenshots.");

				return;
			}

			// =====================================================
			// 3. Capture Screenshot
			// =====================================================

			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

			byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);

			log.info("Screenshot captured successfully. Size: {} bytes", screenshot.length);

			// =====================================================
			// 4. Create Screenshot Directory
			// =====================================================

			Path screenshotDirectory = Paths.get(SCREENSHOT_DIRECTORY);

			if (!Files.exists(screenshotDirectory)) {

				Files.createDirectories(screenshotDirectory);

				log.info("Screenshot directory created: {}", screenshotDirectory.toAbsolutePath());
			}

			// =====================================================
			// 5. Create Unique Screenshot File Name
			// =====================================================

			String scenarioName = scenario.getName();

			String safeScenarioName = sanitizeFileName(scenarioName);

			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

			String fileName = safeScenarioName + "_FAILED_" + timestamp + ".png";

			Path screenshotPath = screenshotDirectory.resolve(fileName);

			// =====================================================
			// 6. Save Screenshot to Physical File
			// =====================================================

			Files.write(screenshotPath, screenshot);

			log.info("Failure screenshot saved to: {}", screenshotPath.toAbsolutePath());

			// =====================================================
			// 7. Attach Screenshot to Cucumber Report
			// =====================================================

			scenario.attach(screenshot, "image/png", message);

			log.info("Screenshot attached to Cucumber report.");

			// =====================================================
			// 8. Attach Screenshot to Extent Report
			// =====================================================

			if (ExtentTestManager.getTest() != null) {

				String base64Screenshot = java.util.Base64.getEncoder().encodeToString(screenshot);

				ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot, message);

				ExtentTestManager.info("Failure screenshot saved: " + screenshotPath.toAbsolutePath());

				log.info("Screenshot attached to Extent Report.");

			} else {

				log.warn("ExtentTest is not available. " + "Screenshot cannot be attached to Extent Report.");
			}

		} catch (Exception e) {

			log.error("Unable to capture failure screenshot: {}", message, e);

			try {

				ExtentTestManager.warning("Unable to capture screenshot: " + e.getMessage());

			} catch (Exception extentException) {

				log.error("Unable to log screenshot failure " + "to Extent Report", extentException);
			}
		}
	}

	/**
	 * Removes characters that are not valid in Windows file names.
	 */
	private String sanitizeFileName(String fileName) {

		if (fileName == null || fileName.isBlank()) {

			return "Unknown_Scenario";
		}

		return fileName.replaceAll("[\\\\/:*?\"<>|]", "_").replaceAll("\\s+", "_").trim();
	}

	/**
	 * Extract Cucumber scenario tags.
	 */
	private String[] extractTags(Scenario scenario) {

		String[] tags = scenario.getSourceTagNames().toArray(new String[0]);

		log.debug("Extracted scenario tags: {}", Arrays.toString(tags));

		return tags;
	}

	/**
	 * Safely closes WebDriver and removes it from ThreadLocal.
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
