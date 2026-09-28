package utils;

import java.util.List;

import models.LoginTestData;
import models.LoginUser;

/**
 * Utility class for reading SauceDemo login test data from users.json.
 *
 * JSON structure:
 *
 * users.json ├── users ├── invalidCredentials └── emptyCredentials
 *
 * The reader provides: 1. Category-specific lookup 2. Generic lookup across all
 * categories 3. Clear validation and error messages
 */
public final class LoginDataReader {

	private static final String USERS_JSON = "testdata/users.json";

	private static final LoginTestData TEST_DATA = JsonUtils.readResource(USERS_JSON, LoginTestData.class);

	private LoginDataReader() {
		// Utility class
	}

	// ============================================================
	// GENERIC USER LOOKUP
	// ============================================================

	/**
	 * Searches for a user type across all JSON categories.
	 *
	 * Examples:
	 *
	 * standard locked invalid_password invalid_username empty empty_username
	 * empty_password
	 *
	 * @param userType user type to search
	 * @return matching LoginUser
	 */
	public static LoginUser getUserOrCategory(String userTypeOrCategory) {

		validateUserType(userTypeOrCategory);

		// First try normal userType lookup
		LoginUser user = findUser(TEST_DATA.getUsers(), userTypeOrCategory);

		if (user != null) {
			return user;
		}

		// Try invalid credential category
		if ("invalidCredentials".equalsIgnoreCase(userTypeOrCategory)) {

			if (TEST_DATA.getInvalidCredentials() != null && !TEST_DATA.getInvalidCredentials().isEmpty()) {

				return TEST_DATA.getInvalidCredentials().get(0);
			}
		}

		// Try empty credential category
		if ("emptyCredentials".equalsIgnoreCase(userTypeOrCategory)) {

			if (TEST_DATA.getEmptyCredentials() != null && !TEST_DATA.getEmptyCredentials().isEmpty()) {

				return TEST_DATA.getEmptyCredentials().get(0);
			}
		}

		// Try userType inside invalidCredentials
		user = findUser(TEST_DATA.getInvalidCredentials(), userTypeOrCategory);

		if (user != null) {
			return user;
		}

		// Try userType inside emptyCredentials
		user = findUser(TEST_DATA.getEmptyCredentials(), userTypeOrCategory);

		if (user != null) {
			return user;
		}

		throw new IllegalArgumentException(
				"User type or category '" + userTypeOrCategory + "' not found in users.json.");
	}

	public static LoginUser getUser_not_using(String userType) {

		validateUserType(userType);

		LoginUser user;

		// Search normal users
		user = findUser(TEST_DATA.getUsers(), userType);

		if (user != null) {
			return user;
		}

		// Search invalid credentials
		user = findUser(TEST_DATA.getInvalidCredentials(), userType);

		if (user != null) {
			return user;
		}

		// Search empty credentials
		user = findUser(TEST_DATA.getEmptyCredentials(), userType);

		if (user != null) {
			return user;
		}

		throw new IllegalArgumentException("User type '" + userType + "' not found in users.json.");
	}

	// ============================================================
	// CATEGORY-SPECIFIC LOOKUPS
	// ============================================================

	/**
	 * Returns a user from the "users" category.
	 */
	public static LoginUser getNormalUser(String userType) {

		validateUserType(userType);

		return findUserOrThrow(TEST_DATA.getUsers(), userType, "users");
	}

	/**
	 * Returns a user from the "invalidCredentials" category.
	 */
	public static LoginUser getInvalidCredential(String userType) {

		validateUserType(userType);

		return findUserOrThrow(TEST_DATA.getInvalidCredentials(), userType, "invalidCredentials");
	}

	/**
	 * Returns a user from the "emptyCredentials" category.
	 */
	public static LoginUser getEmptyCredential(String userType) {

		validateUserType(userType);

		return findUserOrThrow(TEST_DATA.getEmptyCredentials(), userType, "emptyCredentials");
	}

	// ============================================================
	// FIND BY CATEGORY
	// ============================================================

	/**
	 * Finds a LoginUser based on its category.
	 *
	 * Example:
	 *
	 * getUserByCategory("empty_username", "emptyCredentials")
	 */
	public static LoginUser getUserByCategory(String userType, String category) {

		validateUserType(userType);

		if (category == null || category.isBlank()) {
			throw new IllegalArgumentException("Category must not be null or empty.");
		}

		switch (category.toLowerCase()) {

		case "users":
			return getNormalUser(userType);

		case "invalidcredentials":
			return getInvalidCredential(userType);

		case "emptycredentials":
			return getEmptyCredential(userType);

		default:
			throw new IllegalArgumentException("Unknown login data category: " + category + ". Supported categories: "
					+ "users, invalidCredentials, emptyCredentials");
		}
	}

	// ============================================================
	// PRIVATE SEARCH METHODS
	// ============================================================

	/**
	 * Searches a list and returns null when no match is found.
	 */
	private static LoginUser findUser(List<LoginUser> users, String userType) {

		if (users == null || users.isEmpty()) {
			return null;
		}

		return users.stream().filter(user -> user != null)
				.filter(user -> user.getUserType() != null && user.getUserType().equalsIgnoreCase(userType)).findFirst()
				.orElse(null);
	}

	/**
	 * Searches a specific category and throws a meaningful exception when the
	 * requested user type does not exist.
	 */
	private static LoginUser findUserOrThrow(List<LoginUser> users, String userType, String category) {

		LoginUser user = findUser(users, userType);

		if (user == null) {
			throw new IllegalArgumentException(
					"User type '" + userType + "' not found in JSON category '" + category + "'.");
		}

		return user;
	}

	// ============================================================
	// VALIDATION
	// ============================================================

	private static void validateUserType(String userType) {

		if (userType == null || userType.isBlank()) {
			throw new IllegalArgumentException("User type must not be null or empty.");
		}
	}
}