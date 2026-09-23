package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import config.ConfigManager;
import page_object_manager.PageObjectManager;
import pages.LoginPage;
import utils.WaitUtils;

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
	
    public LoginAction(PageObjectManager pageObjectManager) {

        if (pageObjectManager == null) {
            throw new IllegalArgumentException(
                    "PageObjectManager must not be null"
            );
        }

        this.pageObjectManager = new PageObjectManager();
        this.loginPage = pageObjectManager.getLoginPage();

        log.info("LoginAction initialized successfully");
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

	public void navigateToLoginPage() {

		log.info("Navigating to SauceDemo Login page");

		String baseUrl = ConfigManager.getBaseUrl();

		if (baseUrl == null || baseUrl.isBlank()) {
			log.error("Base URL is null or empty");
			throw new IllegalStateException("Cannot navigate to Login page because Base URL is not configured");
		}

		loginPage.navigateTo(baseUrl);

		WaitUtils.waitForPageLoad();

		log.info("Successfully navigated to Login page: {}", baseUrl);
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
	
	
	 /**
     * Verifies that the Login page is displayed.
     */
    public void verifyLoginPageDisplayed() {

        log.info("Verifying Login page is displayed");

        boolean isDisplayed = loginPage.isLoginPageDisplayed();

        Assert.assertTrue(
                isDisplayed,
                "Login page should be displayed but it was not"
        );

        log.info("Login page is displayed successfully");
    }
	
	
	
}