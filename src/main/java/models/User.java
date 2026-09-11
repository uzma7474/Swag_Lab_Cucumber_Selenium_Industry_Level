package models;

/**
 * Model representing a SauceDemo application user.
 *
 * Responsibilities: - Store user/test credential data. - Provide getters and
 * setters.
 *
 * This class must not contain: - Selenium locators - WebDriver code -
 * Assertions - Business actions
 *
 * Password is intentionally not exposed through toString() to avoid accidental
 * credential leakage in logs/reports.
 */
public class User {

	private String username;
	private String password;
	private String userType;
	private boolean expectedLoginSuccess;

	/**
	 * Default constructor.
	 */
	public User() {
	}

	/**
	 * Constructor with username and password.
	 *
	 * @param username SauceDemo username
	 * @param password SauceDemo password
	 */
	public User(String username, String password) {

		this.username = username;
		this.password = password;
	}

	/**
	 * Full constructor.
	 *
	 * @param username             username
	 * @param password             password
	 * @param userType             type of SauceDemo user
	 * @param expectedLoginSuccess expected login result
	 */
	public User(String username, String password, String userType, boolean expectedLoginSuccess) {

		this.username = username;
		this.password = password;
		this.userType = userType;
		this.expectedLoginSuccess = expectedLoginSuccess;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isExpectedLoginSuccess() {
		return expectedLoginSuccess;
	}

	public void setExpectedLoginSuccess(boolean expectedLoginSuccess) {

		this.expectedLoginSuccess = expectedLoginSuccess;
	}

	/**
	 * Returns a safe representation of the user.
	 *
	 * IMPORTANT: Password is never included.
	 */
	@Override
	public String toString() {

		return "User{" + "username='" + username + '\'' + ", userType='" + userType + '\'' + ", expectedLoginSuccess="
				+ expectedLoginSuccess + '}';
	}
}
