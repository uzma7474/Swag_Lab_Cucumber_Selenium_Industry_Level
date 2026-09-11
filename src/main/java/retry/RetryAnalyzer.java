package retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * RetryAnalyzer automatically retries failed TestNG test methods.
 *
 * Maximum retries can be configured using: -Dretry.count=2
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	private static final Logger log = LoggerFactory.getLogger(RetryAnalyzer.class);

	private static final int DEFAULT_RETRY_COUNT = 2;

	private int retryCount = 0;

	private final int maxRetryCount;

	public RetryAnalyzer() {
		this.maxRetryCount = getConfiguredRetryCount();
	}

	@Override
	public boolean retry(ITestResult result) {

		if (retryCount < maxRetryCount) {

			retryCount++;

			log.warn("Retrying failed test: {} | Attempt {}/{}", result.getName(), retryCount + 1, maxRetryCount + 1);

			return true;
		}

		log.error("Test failed after {} attempts: {}", maxRetryCount + 1, result.getName());

		return false;
	}

	private int getConfiguredRetryCount() {

		String configuredValue = System.getProperty("retry.count");

		if (configuredValue == null || configuredValue.isBlank()) {

			return DEFAULT_RETRY_COUNT;
		}

		try {

			int value = Integer.parseInt(configuredValue);

			if (value < 0) {
				log.warn("Invalid retry.count '{}'. Using default: {}", value, DEFAULT_RETRY_COUNT);

				return DEFAULT_RETRY_COUNT;
			}

			return value;

		} catch (NumberFormatException e) {

			log.warn("Invalid retry.count '{}'. Using default: {}", configuredValue, DEFAULT_RETRY_COUNT);

			return DEFAULT_RETRY_COUNT;
		}
	}
}