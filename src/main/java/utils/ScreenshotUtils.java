package utils;

import driver.DriverManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for taking and storing Selenium screenshots.
 */
public final class ScreenshotUtils {

	private static final String SCREENSHOT_DIRECTORY = "reports/screenshots";

	private ScreenshotUtils() {
		// Prevent object creation
	}

	/**
	 * Captures screenshot and saves it to reports/screenshots.
	 *
	 * @param screenshotName screenshot name
	 * @return screenshot path
	 */
	public static String takeScreenshot(String screenshotName) {

		WebDriver driver = DriverManager.getDriver();

		return takeScreenshot(driver, screenshotName);
	}

	/**
	 * Captures screenshot using supplied WebDriver.
	 *
	 * @param driver         WebDriver
	 * @param screenshotName screenshot name
	 * @return screenshot path
	 */
	public static String takeScreenshot(WebDriver driver, String screenshotName) {

		if (!(driver instanceof TakesScreenshot)) {

			throw new IllegalStateException("Current WebDriver does not support screenshots.");
		}

		try {

			Path directory = Paths.get(SCREENSHOT_DIRECTORY);

			Files.createDirectories(directory);

			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

			String safeName = sanitizeFileName(screenshotName);

			String fileName = safeName + "_" + timestamp + ".png";

			Path destination = directory.resolve(fileName);

			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

			return destination.toAbsolutePath().toString();

		} catch (IOException e) {

			throw new RuntimeException("Unable to save screenshot: " + screenshotName, e);
		}
	}

	/**
	 * Captures screenshot as byte array.
	 *
	 * Useful for attaching screenshots directly to Cucumber reports.
	 *
	 * @return screenshot bytes
	 */
	public static byte[] getScreenshotAsBytes() {

		WebDriver driver = DriverManager.getDriver();

		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	/**
	 * Captures screenshot as Base64.
	 *
	 * @return Base64 screenshot
	 */
	public static String getScreenshotAsBase64() {

		WebDriver driver = DriverManager.getDriver();

		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	/**
	 * Removes characters that are not suitable for file names.
	 *
	 * @param fileName original name
	 * @return safe file name
	 */
	private static String sanitizeFileName(String fileName) {

		if (fileName == null || fileName.isBlank()) {

			return "screenshot";
		}

		return fileName.trim().replaceAll("[\\\\/:*?\"<>|\\s]+", "_");
	}
}