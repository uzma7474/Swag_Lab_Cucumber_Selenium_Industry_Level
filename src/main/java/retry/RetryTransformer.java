package retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Automatically applies RetryAnalyzer to TestNG tests.
 */
public class RetryTransformer implements IAnnotationTransformer {

	private static final Logger log = LoggerFactory.getLogger(RetryTransformer.class);

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		if (annotation.getRetryAnalyzerClass() == null) {

			annotation.setRetryAnalyzer(RetryAnalyzer.class);

			log.debug("RetryAnalyzer applied to test method: {}",
					testMethod != null ? testMethod.getName() : "unknown");
		}
	}
}