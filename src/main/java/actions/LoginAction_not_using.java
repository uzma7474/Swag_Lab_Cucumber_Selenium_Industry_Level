package actions;

import pages.LoginPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Action class for SauceDemo Login functionality.
 *
 * Responsibilities: - Perform login page actions - Enter username - Enter
 * password - Click Login button - Open Login page - Close login error message
 *
 * This class does not contain assertions. Assertions are handled by
 * LoginAssertion.
 */
public class LoginAction_not_using {

	private static final Logger log = LoggerFactory.getLogger(LoginAction_not_using.class);

	private final LoginPage loginPage;

	/**
	 * Constructor.
	 *
	 * @param loginPage LoginPage instance
	 */
	public LoginAction_not_using(LoginPage loginPage) {

		if (loginPage == null) {

			log.error("LoginPage cannot be null");

			throw new IllegalArgumentException("LoginPage cannot be null");
		}

		this.loginPage = loginPage;

		log.debug("LoginAction initialized successfully");
	}

	// ============================================================
	// Navigation
	// ============================================================

	/**
	 * Opens the SauceDemo login page.
	 */
	public void openLoginPage() {

		log.info("Action: Opening SauceDemo login page");

		try {

			loginPage.open();

			log.info("Action completed: Login page opened successfully");

		} catch (Exception e) {

			log.error("Action failed: Unable to open Login page", e);

			throw e;
		}
	}

	// ============================================================
	// Username
	// ============================================================

	/**
	 * Enters username into the Login page.
	 *
	 * @param username username to enter
	 */
	public void enterUsername(String username) {

		log.info("Action: Entering username: {}", username);

		try {

			loginPage.enterUsername(username);

			log.debug("Action completed: Username entered successfully");

		} catch (Exception e) {

			log.error("Action failed: Unable to enter username: {}", username, e);

			throw e;
		}
	}

	// ============================================================
	// Password
	// ============================================================

	/**
	 * Enters password into the Login page.
	 *
	 * IMPORTANT: The actual password is never logged.
	 *
	 * @param password password to enter
	 */
	public void enterPassword(String password) {

		log.info("Action: Entering password");

		try {

			loginPage.enterPassword(password);

			log.debug("Action completed: Password entered successfully");

		} catch (Exception e) {

			log.error("Action failed: Unable to enter password", e);

			throw e;
		}
	}

	// ============================================================
	// Login
	// ============================================================

	/**
	 * Clicks the Login button.
	 */
	public void clickLogin() {

		log.info("Action: Clicking Login button");

		try {

			loginPage.clickLogin();

			log.debug("Action completed: Login button clicked successfully");

		} catch (Exception e) {

			log.error("Action failed: Unable to click Login button", e);

			throw e;
		}
	}

	/**
	 * Performs complete login operation.
	 *
	 * @param username username
	 * @param password password
	 */
	public void login(String username, String password) {

		log.info("Action: Performing login for username: {}", username);

		try {

			enterUsername(username);

			enterPassword(password);

			clickLogin();

			log.info("Action completed: Login submitted successfully");

		} catch (Exception e) {

			log.error("Action failed: Login operation failed for username: {}", username, e);

			throw e;
		}
	}

	// ============================================================
	// Error Message
	// ============================================================

	/**
	 * Closes the login error message.
	 *
	 * Note: This requires the close-error functionality to be present in LoginPage.
	 */
//	public void closeErrorMessage() {
//
//		log.info("Action: Closing login error message");
//
//		try {
//
//			loginPage.closeErrorMessage();
//
//			log.debug("Action completed: Login error message closed");
//
//		} catch (Exception e) {
//
//			log.error("Action failed: Unable to close login error message", e);
//
//			throw e;
//		}
//	}
	
	
	
	
}