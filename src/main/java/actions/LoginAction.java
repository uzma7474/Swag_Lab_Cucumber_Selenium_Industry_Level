package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;

/**
 * Business actions for SauceDemo Login functionality.
 */
public class LoginAction {

	private static final Logger log = LoggerFactory.getLogger(LoginAction.class);

	private final LoginPage loginPage;

	public LoginAction(LoginPage loginPage) {

		if (loginPage == null) {
			throw new IllegalArgumentException("LoginPage must not be null");
		}

		this.loginPage = loginPage;
	}

	public void openLoginPage() {

		log.info("Opening SauceDemo login page");

		loginPage.open();
	}

	public void enterUsername(String username) {

		log.info("Entering username");

		loginPage.enterUsername(username);
	}

	public void enterPassword(String password) {

		/*
		 * Never log password values.
		 */
		log.info("Entering password");

		loginPage.enterPassword(password);
	}

	public void clickLogin() {

		log.info("Clicking Login button");

		loginPage.clickLogin();
	}

	public void login(String username, String password) {

		log.info("Performing login");

		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

	public void closeErrorMessage() {

		log.info("Closing login error message");

		loginPage.closeErrorMessage();
	}
}