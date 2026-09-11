package pages;

import base.BasePage;
import config.EnvironmentManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

	@FindBy(id = "user-name")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	@FindBy(css = "[data-test='error']")
	private WebElement errorMessage;

	@FindBy(css = "[data-test='error-button']")
	private WebElement errorCloseButton;

	public LoginPage() {

		super();

		PageFactory.initElements(driver, this);

		log.debug("LoginPage initialized");
	}

	public void open() {

		log.info("Navigating to SauceDemo");

		navigateTo(EnvironmentManager.getBaseUrl());
	}

	public void enterUsername(String username) {

		if (username == null) {
			throw new IllegalArgumentException("Username must not be null");
		}

		log.info("Entering username");

		type(usernameInput, username);
	}

	public void enterPassword(String password) {

		if (password == null) {
			throw new IllegalArgumentException("Password must not be null");
		}

		/*
		 * Do NOT log password.
		 */

		log.info("Entering password");

		type(passwordInput, password);
	}

	public void clickLogin() {

		log.info("Clicking Login button");

		click(loginButton);
	}

	public boolean isLoginPageDisplayed() {

		String currentUrl = getCurrentUrl();

		boolean displayed = currentUrl.contains("/");

		log.debug("Login page URL validation: {}", currentUrl);

		return displayed && isDisplayed(usernameInput) && isDisplayed(passwordInput) && isDisplayed(loginButton);
	}

	public boolean isErrorMessageDisplayed() {

		return isDisplayed(errorMessage);
	}

	public String getErrorMessage() {

		return getText(errorMessage);
	}

	public boolean isPasswordMasked() {

		String type = getAttribute(passwordInput, "type");

		return "password".equalsIgnoreCase(type);
	}

	public boolean isUsernameDisplayed() {

		return isDisplayed(usernameInput);
	}

	public boolean isPasswordDisplayed() {

		return isDisplayed(passwordInput);
	}

	public boolean isLoginButtonDisplayed() {

		return isDisplayed(loginButton);
	}

	public boolean isLoginButtonEnabled() {

		return isEnabled(loginButton);
	}

	public void closeErrorMessage() {

		log.info("Closing login error message");

		if (isDisplayed(errorCloseButton)) {

			click(errorCloseButton);

		} else {

			log.warn("Login error close button is not displayed");
		}
	}

	public void refresh() {

		log.info("Refreshing login page");

		driver.navigate().refresh();
	}
}