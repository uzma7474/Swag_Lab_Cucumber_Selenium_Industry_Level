package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ExtentManager
 *
 * Responsible for creating and maintaining the single ExtentReports instance
 * for the entire test execution.
 *
 * Design: - Singleton pattern - Thread-safe initialization - Spark HTML report
 * - Centralized report configuration
 */
public final class ExtentManager {

	private static ExtentReports extentReports;

	private static final String DEFAULT_REPORT_PATH = "reports/extent-report.html";

	private static final String REPORT_TITLE = "SauceDemo Automation Execution Report";

	private static final String DOCUMENT_TITLE = "SauceDemo Selenium Automation Report";

	private static final String REPORT_NAME = "SauceDemo Test Execution";

	private ExtentManager() {
		// Prevent object creation
	}

	/**
	 * Returns the ExtentReports instance.
	 *
	 * @return ExtentReports instance
	 */
	public static synchronized ExtentReports getExtentReports() {

		if (extentReports == null) {
			extentReports = createExtentReports();
		}

		return extentReports;
	}

	/**
	 * Creates and configures ExtentReports.
	 *
	 * @return configured ExtentReports instance
	 */
	private static ExtentReports createExtentReports() {

		String reportPath = getReportPath();

		createReportDirectory(reportPath);

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

		configureSparkReporter(sparkReporter);

		ExtentReports reports = new ExtentReports();

		reports.attachReporter(sparkReporter);

		addSystemInformation(reports);

		return reports;
	}

	/**
	 * Returns report path.
	 *
	 * System property can override the default path:
	 *
	 * -Dextent.report.path=reports/my-report.html
	 *
	 * @return report file path
	 */
	private static String getReportPath() {

		String configuredPath = System.getProperty("extent.report.path");

		if (configuredPath != null && !configuredPath.isBlank()) {

			return configuredPath;
		}

		return DEFAULT_REPORT_PATH;
	}

	/**
	 * Configures Extent Spark reporter.
	 *
	 * @param sparkReporter Extent Spark reporter
	 */
	private static void configureSparkReporter(ExtentSparkReporter sparkReporter) {

		sparkReporter.config().setDocumentTitle(DOCUMENT_TITLE);

		sparkReporter.config().setReportName(REPORT_NAME);

		sparkReporter.config().setTheme(Theme.STANDARD);

		sparkReporter.config().setEncoding("UTF-8");

		sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Adds execution/environment information to the Extent report.
	 *
	 * @param reports ExtentReports instance
	 */
	private static void addSystemInformation(ExtentReports reports) {

		reports.setSystemInfo("Application", "SauceDemo");

		reports.setSystemInfo("Application URL", "https://www.saucedemo.com/");

		reports.setSystemInfo("Automation", "Selenium WebDriver");

		reports.setSystemInfo("Framework", "Cucumber + TestNG");

		reports.setSystemInfo("Language", "Java");

		reports.setSystemInfo("Browser", System.getProperty("browser", "chrome"));

		reports.setSystemInfo("Environment", System.getProperty("environment", "qa"));

		reports.setSystemInfo("OS", System.getProperty("os.name", "Unknown"));

		reports.setSystemInfo("OS Version", System.getProperty("os.version", "Unknown"));

		reports.setSystemInfo("Java Version", System.getProperty("java.version", "Unknown"));

		reports.setSystemInfo("User", System.getProperty("user.name", "Unknown"));
	}

	/**
	 * Creates the report directory if it does not exist.
	 *
	 * Example:
	 *
	 * reports/ ├── extent-report.html └── screenshots/
	 *
	 * @param reportPath report file path
	 */
	private static void createReportDirectory(String reportPath) {

		try {

			Path reportFile = Paths.get(reportPath);

			Path parentDirectory = reportFile.getParent();

			if (parentDirectory != null) {

				Files.createDirectories(parentDirectory);
			}

		} catch (IOException e) {

			throw new RuntimeException("Unable to create Extent report directory: " + reportPath, e);
		}
	}

	/**
	 * Flushes all Extent report data to disk.
	 */
	public static synchronized void flush() {

		if (extentReports != null) {

			extentReports.flush();
		}
	}

	/**
	 * Closes the report.
	 *
	 * Normally flush() is sufficient for ExtentReports. This method is provided for
	 * explicit cleanup.
	 */
	public static synchronized void close() {

		if (extentReports != null) {

			extentReports.flush();

			extentReports = null;
		}
	}
}
