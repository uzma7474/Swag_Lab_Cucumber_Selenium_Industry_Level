package stepdefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import actions.LoginAction;
import assertions.InventoryAssertions;
import assertions.LoginAssertions;
import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.LoginUser;
import page_object_manager.PageObjectManager;
import pages.InventoryPage;
import pages.LoginPage;
import utils.LoginDataReader;

/**
 * Step definitions for data-driven SauceDemo Login scenarios.
 *
 * Architecture:
 *
 * Feature ↓ LoginJsonDataSteps ↓ LoginDataReader ↓ JsonUtils ↓ users.json
 *
 * LoginJsonDataSteps ↓ LoginAction ↓ LoginPage ↓ BasePage ↓ DriverManager ↓
 * WebDriver
 *
 * Assertions:
 *
 * LoginJsonDataSteps ↓ LoginAssertions / InventoryAssertions ↓ Page Objects
 *
 * IMPORTANT: WebDriver is initialized by the Cucumber @Before hook before the
 * login steps execute.
 */
public class LoginJsonDataSteps {

	private static final Logger log = LoggerFactory.getLogger(LoginJsonDataSteps.class);

	private final PageObjectManager pageObjectManager;

	private final LoginPage loginPage;
	private final InventoryPage inventoryPage;

	private final LoginAction loginAction;

	private final LoginAssertions loginAssertions;
	private final InventoryAssertions inventoryAssertions;

	/**
	 * Stores the currently loaded login test data.
	 */
	private LoginUser currentUser;

	/**
	 * Constructor.
	 */
	public LoginJsonDataSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext cannot be null.");
		}

		this.pageObjectManager = context.getPageObjectManager();

		this.loginPage = pageObjectManager.getLoginPage();

		this.inventoryPage = pageObjectManager.getInventoryPage();

		this.loginAction = new LoginAction(pageObjectManager);

		this.loginAssertions = new LoginAssertions(loginPage);

		this.inventoryAssertions = new InventoryAssertions(inventoryPage);

		log.debug("LoginJsonDataSteps initialized");
	}

	// ============================================================
	// GENERIC JSON LOGIN
	// ============================================================

	/**
	 * Loads login credentials from users.json.
	 *
	 * LoginDataReader searches:
	 *
	 * 1. users 2. invalidCredentials 3. emptyCredentials
	 *
	 * Examples:
	 *
	 * standard locked problem invalid invalid_password invalid_username empty
	 * empty_username empty_password
	 */

	@When("the user logs in using {string} user from users.json")
	public void the_user_logs_in_using_user_from_users_json(String userType) {

		log.info("Loading login credentials from users.json for user type: {}", userType);

		Assert.assertNotNull(userType, "User type must not be null.");

		Assert.assertFalse(userType.isBlank(), "User type must not be empty.");

		// Read login user data from users.json
		currentUser = LoginDataReader.getUserOrCategory(userType);

		Assert.assertNotNull(currentUser, "No login data found for user type: " + userType);

		Assert.assertNotNull(currentUser.getUsername(), "Username must not be null for user type: " + userType);

		Assert.assertNotNull(currentUser.getPassword(), "Password must not be null for user type: " + userType);

		Assert.assertNotNull(currentUser.getUserType(), "User type in JSON must not be null.");

		Assert.assertNotNull(currentUser.getCategory(), "Login data category must not be null.");

		log.info("Login test data loaded | type: {} | category: {} | expectedSuccess: {}", currentUser.getUserType(),
				currentUser.getCategory(), currentUser.isExpectedLoginSuccess());

		// Enter username
		log.info("Entering username for user type: {}", currentUser.getUserType());

		loginAction.enterUsername(currentUser.getUsername());

		// Enter password
		// Never log the actual password.
		log.info("Entering password for user type: {}", currentUser.getUserType());

		loginAction.enterPassword(currentUser.getPassword());

		// Click Login
		log.info("Clicking Login button");

		loginAction.clickLogin();

		log.info("Login action completed for user type: {}", currentUser.getUserType());
	}

	@When("the user logs in using {string} credentials from users.json")
	public void the_user_logs_in_using_credentials_from_users_json(String userType) {

		log.info("Loading login credentials from users.json for user type: {}", userType);

		Assert.assertNotNull(userType, "User type must not be null.");

		Assert.assertFalse(userType.isBlank(), "User type must not be empty.");

		/*
		 * LoginDataReader is responsible for searching all JSON categories.
		 */
		currentUser = LoginDataReader.getUserOrCategory(userType);

		validateUserData(currentUser);

		log.info("Login test data loaded | type: {} | category: {} | expectedSuccess: {}", currentUser.getUserType(),
				currentUser.getCategory(), currentUser.isExpectedLoginSuccess());

		performLogin(currentUser);

		log.info("Login action completed for user type: {}", currentUser.getUserType());
	}

	// ============================================================
	// LOGIN SUCCESS
	// ============================================================

	/**
	 * Verifies successful login according to users.json.
	 */
	@Then("the data driven login should be successful")
	public void the_data_driven_login_should_be_successful() {

		log.info("Verifying successful data-driven login");

		validateCurrentUser();

		Assert.assertTrue(currentUser.isExpectedLoginSuccess(),
				"Login was expected to be successful for user type: " + currentUser.getUserType());

		inventoryAssertions.verifyInventoryPageDisplayed();

		log.info("Data-driven login successful for user type: {}", currentUser.getUserType());
	}

	// ============================================================
	// LOGIN FAILURE
	// ============================================================

	/**
	 * Verifies failed login according to users.json.
	 */
	@Then("the login should not be successful")
	public void the_login_should_not_be_successful() {

		log.info("Verifying that data-driven login was not successful");

		validateCurrentUser();

		Assert.assertFalse(currentUser.isExpectedLoginSuccess(),
				"Login was expected to fail for user type: " + currentUser.getUserType());

		loginAssertions.verifyLoginPageDisplayed();

		log.info("Login failure verified for user type: {}", currentUser.getUserType());
	}

	// ============================================================
	// DATA-DRIVEN LOGIN FAILURE
	// ============================================================

	/**
	 * Alternative failure assertion.
	 *
	 * Can be used when your feature contains:
	 *
	 * Then the data driven login should fail
	 */
	@Then("the data driven login should fail")
	public void the_data_driven_login_should_fail() {

		log.info("Verifying data-driven login failure");

		validateCurrentUser();

		Assert.assertFalse(currentUser.isExpectedLoginSuccess(),
				"Login was expected to fail for user type: " + currentUser.getUserType());

		loginAssertions.verifyLoginPageDisplayed();

		log.info("Data-driven login failure verified");
	}

	// ============================================================
	// LOGIN PAGE
	// ============================================================

	@Then("the login page should be displayed")
	public void the_login_page_should_be_displayed() {

		log.info("Verifying Login page is displayed");

		loginAssertions.verifyLoginPageDisplayed();

		log.info("Login page displayed successfully");
	}

	// ============================================================
	// LOGIN BUTTON
	// ============================================================

	@Then("the Login button should be enabled")
	public void the_login_button_should_be_enabled() {

		log.info("Verifying Login button is enabled");

		loginAssertions.verifyLoginButtonEnabled();

		log.info("Login button is enabled successfully");
	}

	// ============================================================
	// REFRESH
	// ============================================================

	@When("the user refreshes the login page")
	public void the_user_refreshes_the_login_page() {

		log.info("Refreshing Login page");

		loginAction.refreshLoginPage();

		log.info("Login page refresh completed");
	}

	// ============================================================
	// PRIVATE METHODS
	// ============================================================

	/**
	 * Performs login using LoginAction.
	 */
	private void performLogin(LoginUser user) {

		log.info("Entering username for user type: {}", user.getUserType());

		loginAction.enterUsername(user.getUsername());

		/*
		 * Never log the actual password.
		 */
		log.info("Entering password for user type: {}", user.getUserType());

		loginAction.enterPassword(user.getPassword());

		log.info("Clicking Login button");

		loginAction.clickLogin();
	}

	/**
	 * Validates LoginUser data loaded from users.json.
	 */
	private void validateUserData(LoginUser user) {

		Assert.assertNotNull(user, "Login test data must not be null.");

		Assert.assertNotNull(user.getUsername(), "Username must not be null.");

		Assert.assertNotNull(user.getPassword(), "Password must not be null.");

		Assert.assertNotNull(user.getUserType(), "User type must not be null.");

		Assert.assertNotNull(user.getCategory(), "Login data category must not be null.");

		log.debug("Login test data validation passed | type: {} | category: {}", user.getUserType(),
				user.getCategory());
	}

	/**
	 * Ensures that a login data record was loaded before executing an assertion.
	 */
	private void validateCurrentUser() {

		Assert.assertNotNull(currentUser,
				"Current login test data must not be null. " + "Make sure the login step was executed first.");
	}
}