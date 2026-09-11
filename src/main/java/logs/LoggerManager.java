package logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggerManager
 *
 * Centralized SLF4J logger factory for the automation framework.
 *
 * Usage:
 *
 * private static final Logger log = LoggerManager.getLogger(MyClass.class);
 */
public final class LoggerManager {

	private LoggerManager() {
		// Prevent object creation
	}

	/**
	 * Returns SLF4J logger for the supplied class.
	 *
	 * @param clazz class requesting logger
	 * @return SLF4J Logger
	 */
	public static Logger getLogger(Class<?> clazz) {

		return LoggerFactory.getLogger(clazz);
	}

	/**
	 * Returns logger using logger name.
	 *
	 * @param loggerName logger name
	 * @return SLF4J Logger
	 */
	public static Logger getLogger(String loggerName) {

		return LoggerFactory.getLogger(loggerName);
	}
}