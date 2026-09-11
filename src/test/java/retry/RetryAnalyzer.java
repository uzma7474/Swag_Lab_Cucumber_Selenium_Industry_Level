package retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private static final Logger log = LoggerFactory.getLogger(RetryAnalyzer.class);

	private static final int MAX_RETRY_COUNT = 2;

	private int retryCount = 0;

	@Override
	public boolean retry(ITestResult result) {

		if (retryCount < MAX_RETRY_COUNT) {

			retryCount++;

			log.warn("Retrying failed test: {} | Attempt: {} of {}", result.getName(), retryCount, MAX_RETRY_COUNT);

			return true;
		}

		log.error("Test failed after {} retry attempts: {}", MAX_RETRY_COUNT, result.getName());

		return false;
	}
}