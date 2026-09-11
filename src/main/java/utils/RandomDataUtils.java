package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * Utility class for generating random test data.
 *
 * Useful for data-driven and negative/positive test scenarios.
 */
public final class RandomDataUtils {

	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz"
			+ "0123456789";

	private static final String DIGITS = "0123456789";

	private static final String[] FIRST_NAMES = { "John", "David", "Robert", "Michael", "James", "William", "Daniel",
			"Thomas", "Matthew", "Chris", "Emma", "Olivia", "Sophia", "Ava", "Mia", "Emily" };

	private static final String[] LAST_NAMES = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis",
			"Wilson", "Taylor", "Anderson", "Thomas", "Jackson" };

	private RandomDataUtils() {
		// Prevent object creation
	}

	// ========================================================
	// Names
	// ========================================================

	/**
	 * Generates random first name.
	 *
	 * @return first name
	 */
	public static String randomFirstName() {

		return FIRST_NAMES[ThreadLocalRandom.current().nextInt(FIRST_NAMES.length)];
	}

	/**
	 * Generates random last name.
	 *
	 * @return last name
	 */
	public static String randomLastName() {

		return LAST_NAMES[ThreadLocalRandom.current().nextInt(LAST_NAMES.length)];
	}

	/**
	 * Generates random full name.
	 *
	 * @return full name
	 */
	public static String randomFullName() {

		return randomFirstName() + " " + randomLastName();
	}

	// ========================================================
	// Email
	// ========================================================

	/**
	 * Generates random email address.
	 *
	 * @return email
	 */
	public static String randomEmail() {

		return "test_" + randomAlphanumeric(8) + "@example.com";
	}

	/**
	 * Generates random Gmail-style email.
	 *
	 * @return email
	 */
	public static String randomGmail() {

		return "test" + randomDigits(8) + "@gmail.com";
	}

	// ========================================================
	// Phone
	// ========================================================

	/**
	 * Generates a 10-digit phone number.
	 *
	 * @return phone number
	 */
	public static String randomPhoneNumber() {

		int firstDigit = ThreadLocalRandom.current().nextInt(6, 10);

		return firstDigit + randomDigits(9);
	}

	// ========================================================
	// Strings
	// ========================================================

	/**
	 * Generates random alphabetic string.
	 *
	 * @param length length
	 * @return random string
	 */
	public static String randomAlphabetic(int length) {

		validateLength(length);

		return generateRandomString(ALPHABET, length);
	}

	/**
	 * Generates random alphanumeric string.
	 *
	 * @param length length
	 * @return random string
	 */
	public static String randomAlphanumeric(int length) {

		validateLength(length);

		return generateRandomString(ALPHANUMERIC, length);
	}

	/**
	 * Generates random numeric string.
	 *
	 * @param length length
	 * @return numeric string
	 */
	public static String randomDigits(int length) {

		validateLength(length);

		return generateRandomString(DIGITS, length);
	}

	/**
	 * Generates random string using supplied characters.
	 *
	 * @param characters allowed characters
	 * @param length     length
	 * @return random string
	 */
	private static String generateRandomString(String characters, int length) {

		StringBuilder result = new StringBuilder(length);

		ThreadLocalRandom random = ThreadLocalRandom.current();

		for (int index = 0; index < length; index++) {

			int position = random.nextInt(characters.length());

			result.append(characters.charAt(position));
		}

		return result.toString();
	}

	// ========================================================
	// Numbers
	// ========================================================

	/**
	 * Generates random integer between min and max.
	 *
	 * Both values are inclusive.
	 *
	 * @param min minimum
	 * @param max maximum
	 * @return random integer
	 */
	public static int randomInt(int min, int max) {

		if (min > max) {

			throw new IllegalArgumentException("Minimum cannot be greater than maximum.");
		}

		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	/**
	 * Generates random long between min and max.
	 *
	 * @param min minimum
	 * @param max maximum
	 * @return random long
	 */
	public static long randomLong(long min, long max) {

		if (min > max) {

			throw new IllegalArgumentException("Minimum cannot be greater than maximum.");
		}

		return ThreadLocalRandom.current().nextLong(min, max + 1);
	}

	// ========================================================
	// Boolean
	// ========================================================

	/**
	 * Generates random boolean.
	 *
	 * @return true or false
	 */
	public static boolean randomBoolean() {

		return ThreadLocalRandom.current().nextBoolean();
	}

	// ========================================================
	// UUID
	// ========================================================

	/**
	 * Generates UUID.
	 *
	 * @return UUID string
	 */
	public static String randomUUID() {

		return java.util.UUID.randomUUID().toString();
	}

	// ========================================================
	// Date
	// ========================================================

	/**
	 * Generates random date between two dates.
	 *
	 * @param startDate start date
	 * @param endDate   end date
	 * @return random LocalDate
	 */
	public static LocalDate randomDate(LocalDate startDate, LocalDate endDate) {

		if (startDate.isAfter(endDate)) {

			throw new IllegalArgumentException("Start date cannot be after end date.");
		}

		long startEpochDay = startDate.toEpochDay();

		long endEpochDay = endDate.toEpochDay();

		long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

		return LocalDate.ofEpochDay(randomDay);
	}

	/**
	 * Generates date using format.
	 *
	 * @param startDate start date
	 * @param endDate   end date
	 * @param pattern   date format
	 * @return formatted random date
	 */
	public static String randomDate(LocalDate startDate, LocalDate endDate, String pattern) {

		LocalDate date = randomDate(startDate, endDate);

		return date.format(DateTimeFormatter.ofPattern(pattern));
	}

	// ========================================================
	// SauceDemo Test Data Helpers
	// ========================================================

	/**
	 * Generates random checkout first name.
	 *
	 * Useful for SauceDemo checkout information.
	 *
	 * @return first name
	 */
	public static String randomCheckoutFirstName() {

		return randomFirstName();
	}

	/**
	 * Generates random checkout last name.
	 *
	 * Useful for SauceDemo checkout information.
	 *
	 * @return last name
	 */
	public static String randomCheckoutLastName() {

		return randomLastName();
	}

	/**
	 * Generates random postal code.
	 *
	 * @return postal code
	 */
	public static String randomPostalCode() {

		return randomDigits(5);
	}

	/**
	 * Generates random checkout data.
	 *
	 * @return array containing first name, last name and postal code
	 */
	public static String[] randomCheckoutData() {

		return new String[] { randomCheckoutFirstName(), randomCheckoutLastName(), randomPostalCode() };
	}

	// ========================================================
	// Validation
	// ========================================================

	/**
	 * Validates random string length.
	 *
	 * @param length length
	 */
	private static void validateLength(int length) {

		if (length < 0) {

			throw new IllegalArgumentException("Length cannot be negative.");
		}
	}
}