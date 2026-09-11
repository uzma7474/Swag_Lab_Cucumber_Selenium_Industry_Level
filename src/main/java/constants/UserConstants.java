package constants;

/**
 * Central repository for SauceDemo test-user constants.
 *
 * Responsibilities: - Store predefined SauceDemo usernames - Store test
 * password - Store user types - Store expected authentication messages
 *
 * This class contains test-data constants only.
 */
public final class UserConstants {

	/**
	 * Private constructor prevents object creation.
	 */
	private UserConstants() {
		throw new UnsupportedOperationException("UserConstants class cannot be instantiated");
	}

	// ============================================================
	// COMMON PASSWORD
	// ============================================================

	/**
	 * Common password used by SauceDemo demo users.
	 *
	 * IMPORTANT: Never log this value.
	 */
	public static final String DEFAULT_PASSWORD = "secret_sauce";

	// ============================================================
	// STANDARD USER
	// ============================================================

	public static final String STANDARD_USER = "standard_user";

	// ============================================================
	// LOCKED OUT USER
	// ============================================================

	public static final String LOCKED_OUT_USER = "locked_out_user";

	// ============================================================
	// PROBLEM USER
	// ============================================================

	public static final String PROBLEM_USER = "problem_user";

	// ============================================================
	// PERFORMANCE GLITCH USER
	// ============================================================

	public static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";

	// ============================================================
	// OTHER SAUCEDEMO USERS
	// ============================================================

	public static final String ERROR_USER = "error_user";

	public static final String VISUAL_USER = "visual_user";

	// ============================================================
	// USER TYPES
	// ============================================================

	public static final String STANDARD_USER_TYPE = "standard";

	public static final String LOCKED_USER_TYPE = "locked";

	public static final String PROBLEM_USER_TYPE = "problem";

	public static final String PERFORMANCE_GLITCH_USER_TYPE = "performance_glitch";

	public static final String ERROR_USER_TYPE = "error";

	public static final String VISUAL_USER_TYPE = "visual";

	// ============================================================
	// INVALID TEST DATA
	// ============================================================

	public static final String INVALID_USERNAME = "invalid_user";

	public static final String INVALID_PASSWORD = "wrong_password";

	public static final String EMPTY_USERNAME = "";

	public static final String EMPTY_PASSWORD = "";

	// ============================================================
	// LOGIN ERROR MESSAGES
	// ============================================================

	public static final String USERNAME_REQUIRED_MESSAGE = "Epic sadface: Username is required";

	public static final String PASSWORD_REQUIRED_MESSAGE = "Epic sadface: Password is required";

	public static final String INVALID_CREDENTIALS_MESSAGE = "Epic sadface: Username and password do not match any user in this service";

	public static final String LOCKED_USER_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";

	// ============================================================
	// LOGIN SUCCESS
	// ============================================================

	public static final String LOGIN_SUCCESS_URL = "/inventory.html";

	public static final String LOGIN_SUCCESS_PAGE = "inventory.html";
}