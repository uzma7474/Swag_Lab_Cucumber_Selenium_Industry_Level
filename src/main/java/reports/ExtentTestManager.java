package reports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.util.Arrays;

/**
 * ExtentTestManager
 *
 * Manages the currently executing ExtentTest instance.
 *
 * ThreadLocal is used so that each parallel scenario receives its own
 * ExtentTest instance.
 */
public final class ExtentTestManager {

	private static final ThreadLocal<ExtentTest> EXTENT_TEST = new ThreadLocal<>();

	private ExtentTestManager() {
		// Prevent object creation
	}

	/**
	 * Starts a new Extent test.
	 *
	 * @param testName scenario/test name
	 * @return ExtentTest instance
	 */
	public static ExtentTest startTest(String testName) {

		ExtentTest test = ExtentManager.getExtentReports().createTest(testName);

		EXTENT_TEST.set(test);

		return test;
	}

	/**
	 * Starts a new Extent test with description.
	 *
	 * @param testName    test/scenario name
	 * @param description test description
	 * @return ExtentTest instance
	 */
	public static ExtentTest startTest(String testName, String description) {

		ExtentTest test = ExtentManager.getExtentReports().createTest(testName, description);

		EXTENT_TEST.set(test);

		return test;
	}

	/**
	 * Returns the current ExtentTest.
	 *
	 * @return current test
	 */
	public static ExtentTest getTest() {

		return EXTENT_TEST.get();
	}

	/**
	 * Logs an informational message.
	 *
	 * @param message information message
	 */
	public static void info(String message) {

		if (getTest() != null) {

			getTest().info(message);
		}
	}

	/**
	 * Logs a pass message.
	 *
	 * @param message pass message
	 */
	public static void pass(String message) {

		if (getTest() != null) {

			getTest().pass(message);
		}
	}

	/**
	 * Logs a warning message.
	 *
	 * @param message warning message
	 */
	public static void warning(String message) {

		if (getTest() != null) {

			getTest().warning(message);
		}
	}

	/**
	 * Logs a skip message.
	 *
	 * @param message skip message
	 */
	public static void skip(String message) {

		if (getTest() != null) {

			getTest().skip(message);
		}
	}

	/**
	 * Logs a failure message.
	 *
	 * @param message failure message
	 */
	public static void fail(String message) {

		if (getTest() != null) {

			getTest().fail(message);
		}
	}

	/**
	 * Logs a failure with exception.
	 *
	 * @param throwable exception
	 */
	public static void fail(Throwable throwable) {

		if (getTest() != null) {

			getTest().fail(throwable);
		}
	}

	/**
	 * Logs an exception with failure status.
	 *
	 * @param message   failure message
	 * @param throwable exception
	 */
	public static void fail(String message, Throwable throwable) {

		if (getTest() != null) {

			getTest().log(Status.FAIL, throwable);
		}
	}

	/**
	 * Adds a screenshot to the current test.
	 *
	 * @param screenshotPath screenshot file path
	 */
	public static void addScreenshot(String screenshotPath) {

		if (getTest() != null) {

			try {

				getTest().addScreenCaptureFromPath(screenshotPath);

			} catch (Exception e) {

				getTest().warning("Unable to attach screenshot: " + e.getMessage());
			}
		}
	}

	/**
	 * Adds a Base64 screenshot to the report.
	 *
	 * @param base64Screenshot Base64 encoded screenshot
	 */
	public static void addBase64Screenshot(String base64Screenshot) {

		if (getTest() != null && base64Screenshot != null && !base64Screenshot.isBlank()) {

			getTest().addScreenCaptureFromBase64String(base64Screenshot);
		}
	}

	/**
	 * Assigns a category/tag to the current test.
	 *
	 * Example:
	 *
	 * @positive
	 * @login
	 * @LGN001
	 *
	 * @param category category name
	 */
	public static void assignCategory(String category) {

		if (getTest() != null && category != null && !category.isBlank()) {

			getTest().assignCategory(category);
		}
	}

	/**
	 * Assigns multiple categories/tags.
	 *
	 * @param categories test categories
	 */
	public static void assignCategories(String... categories) {

		if (getTest() != null && categories != null) {

			Arrays.stream(categories).filter(category -> category != null && !category.isBlank())
					.forEach(category -> getTest().assignCategory(category));
		}
	}

	/**
	 * Adds test author information.
	 *
	 * @param author author name
	 */
	public static void assignAuthor(String author) {

		if (getTest() != null && author != null && !author.isBlank()) {

			getTest().assignAuthor(author);
		}
	}

	/**
	 * Adds test device/browser information.
	 *
	 * @param device device/browser name
	 */
	public static void assignDevice(String device) {

		if (getTest() != null && device != null && !device.isBlank()) {

			getTest().assignDevice(device);
		}
	}

	/**
	 * Adds test description.
	 *
	 * @param description test description
	 */
	public static void assignDescription(String description) {

		if (getTest() != null && description != null && !description.isBlank()) {

			getTest().getModel().setDescription(description);
		}
	}

	/**
	 * Marks the current test as passed.
	 *
	 * @param message pass message
	 */
	public static void markPassed(String message) {

		if (getTest() != null) {

			getTest().log(Status.PASS, message);
		}
	}

	/**
	 * Marks the current test as failed.
	 *
	 * @param message failure message
	 */
	public static void markFailed(String message) {

		if (getTest() != null) {

			getTest().log(Status.FAIL, message);
		}
	}

	/**
	 * Removes the current test from ThreadLocal.
	 *
	 * This should be called after each Cucumber scenario.
	 */
	public static void removeTest() {

		EXTENT_TEST.remove();
	}
}
