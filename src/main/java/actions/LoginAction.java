package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page_object_manager.PageObjectManager;
import pages.LoginPage;

/**
 * Business actions for SauceDemo Login functionality.
 */
public class LoginAction {

	private static final Logger log = LoggerFactory.getLogger(LoginAction.class);

	private final LoginPage loginPage;
	
	private final PageObjectManager pageObjectManager;
	
	public LoginAction(LoginPage loginPage) {

		if (loginPage == null) {
			throw new IllegalArgumentException("LoginPage must not be null");
		}

		this.loginPage = loginPage;
		this.pageObjectManager = new PageObjectManager();
	}

	public LoginAction(LoginPage loginPage, PageObjectManager pageObjectManager) {

		if (loginPage == null) {
			throw new IllegalArgumentException("LoginPage must not be null");
		}

		this.loginPage = loginPage;
		this.pageObjectManager = new PageObjectManager();
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

	public void login_(String username, String password) {

		log.info("Performing login");

		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}
	
    public void login(String username, String password) {

        log.info("Performing login");

        // IMPORTANT
        loginPage.open();

        log.info("Entering username");
        loginPage.enterUsername(username);

        log.info("Entering password");
        loginPage.enterPassword(password);

        log.info("Clicking login button");
        loginPage.clickLogin();

        log.info("Login completed");
    }

	public void closeErrorMessage() {

		log.info("Closing login error message");

		loginPage.closeErrorMessage();
	}
}