package retry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer {

	private static final Logger log = LoggerFactory.getLogger(RetryListener.class);

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		Class<? extends IRetryAnalyzer> retryAnalyzer = annotation.getRetryAnalyzerClass();

		if (retryAnalyzer == null) {

			annotation.setRetryAnalyzer(RetryAnalyzer.class);

			log.debug("RetryAnalyzer applied to test method: {}",
					testMethod != null ? testMethod.getName() : "unknown");
		}
	}
}

//public class RetryListener implements IAnnotationTransformer {
//
//	private static final Logger log = LoggerFactory.getLogger(RetryListener.class);
//
//	@Override
//	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
//
//		if (annotation.getRetryAnalyzerClass() == null) {
//
//			//annotation.setRetryAnalyzer(RetryAnalyzer.class);
//			annotation.setRetryAnalyzer(RetryAnalyzer.class);
//
//			log.debug("RetryAnalyzer applied to test method: {}",
//					testMethod != null ? testMethod.getName() : "unknown");
//		}
//	}
//}