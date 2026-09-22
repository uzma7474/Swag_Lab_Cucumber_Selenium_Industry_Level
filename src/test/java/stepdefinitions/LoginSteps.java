
package stepdefinitions;

import actions.LoginAction;
import assertions.InventoryAssertions;
import assertions.LoginAssertions;
import constants.UserConstants;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page_object_manager.PageObjectManager;
import pages.InventoryPage;
import pages.LoginPage;

/**
 * Step definitions for SauceDemo Login feature.
 *
 * Architecture:
 *
 * Feature ↓ LoginSteps ↓ LoginAction ↓ LoginPage ↓ BasePage ↓ DriverManager ↓
 * WebDriver
 *
 * Assertions:
 *
 * LoginSteps ↓ LoginAssertions / InventoryAssertions ↓ Page Objects
 *
 * Page Object Management:
 *
 * LoginSteps ↓ ScenarioContext ↓ PageObjectManager ↓ Page Objects
 *
 * IMPORTANT: Page Objects are initialized lazily because WebDriver is created
 * by the Cucumber @Before hook before the first step executes.
 */
public class LoginSteps {

	private static final Logger log = LoggerFactory.getLogger(LoginSteps.class);

	private final PageObjectManager pageObjectManager;

	private LoginPage loginPage;
	private InventoryPage inventoryPage;

	private LoginAction loginAction;

	private LoginAssertions loginAssertions;
	private InventoryAssertions inventoryAssertions;

	/**
	 * Constructor injection through PicoContainer.
	 *
	 * We only store PageObjectManager here.
	 *
	 * We DO NOT create Page Objects here because the WebDriver is created by the
	 * Cucumber Before hook.
	 */
	public LoginSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext cannot be null.");
		}

		this.pageObjectManager = context.getPageObjectManager();

		log.debug("LoginSteps initialized");
	}

	// ============================================================
	// LAZY INITIALIZATION
	// ============================================================

	/**
	 * Initializes Page Objects, Actions and Assertions.
	 *
	 * This method is called before every step that requires Login or Inventory
	 * functionality.
	 *
	 * Page Objects are obtained from PageObjectManager.
	 *
	 * This guarantees that:
	 *
	 * 1. WebDriver has already been created by Hooks. 2. Page Objects are managed
	 * centrally. 3. LoginAction is not null. 4. LoginAssertions is not null. 5.
	 * InventoryAssertions is not null.
	 */
	private void initialize() {

		if (loginPage != null) {
			return;
		}

		log.debug("Initializing Login page objects");

		// --------------------------------------------------------
		// PAGE OBJECTS
		// --------------------------------------------------------

		loginPage = pageObjectManager.getLoginPage();

		inventoryPage = pageObjectManager.getInventoryPage();

		// --------------------------------------------------------
		// ACTIONS
		// --------------------------------------------------------

		loginAction = new LoginAction(loginPage);

		// --------------------------------------------------------
		// ASSERTIONS
		// --------------------------------------------------------

		loginAssertions = new LoginAssertions(loginPage);

		inventoryAssertions = new InventoryAssertions(inventoryPage);

		log.debug("Login page objects, actions and assertions initialized");
	}

	// ============================================================
	// BACKGROUND
	// ============================================================

	@Given("the user is on the SauceDemo login page")
	public void theUserIsOnTheSauceDemoLoginPage() {

		initialize();

		log.info("Opening SauceDemo login page");

		loginAction.openLoginPage();

		loginAssertions.verifyLoginPageDisplayed();

		log.info("SauceDemo login page is displayed");
	}

	@Given("the user is logged in to SauceDemo")
	public void theUserIsLoggedInToSauceDemo() {

		initialize();

		log.info("Logging in to SauceDemo");

		loginAction.login(UserConstants.STANDARD_USER, UserConstants.DEFAULT_PASSWORD);

		log.info("SauceDemo login completed");
	}

	// ============================================================
	// USERNAME
	// ============================================================

	@When("the user enters username {string}")
	public void theUserEntersUsername(String username) {

		initialize();

		log.info("Entering username: {}", username);

		loginAction.enterUsername(username);
	}

	// ============================================================
	// PASSWORD
	// ============================================================

	@When("the user enters password {string}")
	public void theUserEntersPassword(String password) {

		initialize();

		/*
		 * IMPORTANT:
		 *
		 * Never log the actual password.
		 */

		log.info("Entering password");

		loginAction.enterPassword(password);
	}

	@Given("the user is logged in as {string}")
	public void the_user_is_logged_in_as(String username) {

	    log.info("Logging in as user: {}", username);

	    loginAction.login(username, UserConstants.DEFAULT_PASSWORD);

	    log.info("User logged in successfully");
	}
	
	
	// ============================================================
	// LOGIN BUTTON
	// ============================================================

	@When("the user clicks the Login button")
	public void theUserClicksTheLoginButton() {

		initialize();

		log.info("Clicking Login button");

		loginAction.clickLogin();
	}

	// ============================================================
	// SUCCESSFUL LOGIN
	// ============================================================

	@Then("the user should be successfully logged in")
	public void theUserShouldBeSuccessfullyLoggedIn() {

		initialize();

		log.info("Verifying successful login");

		inventoryAssertions.verifyInventoryPageDisplayed();

		log.info("User successfully logged in");
	}

	// ============================================================
	// INVENTORY PAGE
	// ============================================================

	@Then("the inventory page should be displayed")
	public void theInventoryPageShouldBeDisplayed() {

		initialize();

		log.info("Verifying inventory page is displayed");

		inventoryAssertions.verifyInventoryPageDisplayed();

		log.info("Inventory page is displayed");
	}

	// ============================================================
	// ERROR MESSAGE
	// ============================================================

	@Then("the login error message should be displayed")
	public void theLoginErrorMessageShouldBeDisplayed() {

		initialize();

		log.info("Verifying login error message is displayed");

		loginAssertions.verifyErrorMessageDisplayed();
	}

	// ============================================================
	// ERROR MESSAGE CONTENT
	// ============================================================

	@Then("the login error message should contain {string}")
	public void theLoginErrorMessageShouldContain(String expectedMessage) {

		initialize();

		log.info("Verifying login error message contains expected text: {}", expectedMessage);

		loginAssertions.verifyErrorMessageContains(expectedMessage);
	}

	// ============================================================
	// PASSWORD MASKING
	// ============================================================

	@Then("the password field should be masked")
	public void thePasswordFieldShouldBeMasked() {

		initialize();

		log.info("Verifying password field is masked");

		loginAssertions.verifyPasswordIsMasked();
	}

	// ============================================================
	// USERNAME FIELD
	// ============================================================

	@Then("the username field should be displayed")
	public void theUsernameFieldShouldBeDisplayed() {

		initialize();

		log.info("Verifying username field is displayed");

		loginAssertions.verifyUsernameFieldDisplayed();
	}

	// ============================================================
	// PASSWORD FIELD
	// ============================================================

	@Then("the password field should be displayed")
	public void thePasswordFieldShouldBeDisplayed() {

		initialize();

		log.info("Verifying password field is displayed");

		loginAssertions.verifyPasswordFieldDisplayed();
	}

	// ============================================================
	// LOGIN BUTTON DISPLAY
	// ============================================================

	@Then("the Login button should be displayed")
	public void theLoginButtonShouldBeDisplayed() {

		initialize();

		log.info("Verifying Login button is displayed");

		loginAssertions.verifyLoginButtonDisplayed();
	}

	// ============================================================
	// CLOSE ERROR MESSAGE
	// ============================================================

	@When("the user closes the login error message")
	public void theUserClosesTheLoginErrorMessage() {

		initialize();

		log.info("Closing login error message");

		loginAction.closeErrorMessage();
	}

	// ============================================================
	// ERROR MESSAGE NOT DISPLAYED
	// ============================================================

	@Then("the login error message should not be displayed")
	public void theLoginErrorMessageShouldNotBeDisplayed() {

		initialize();

		log.info("Verifying login error message is not displayed");

		loginAssertions.verifyErrorMessageNotDisplayed();
	}

	// ============================================================
	// REMAIN ON LOGIN PAGE
	// ============================================================

	@Then("the user should remain on the login page")
	public void theUserShouldRemainOnTheLoginPage() {

		initialize();

		log.info("Verifying user remains on login page");

		loginAssertions.verifyLoginPageDisplayed();
	}

	// ============================================================
	// REFRESH PAGE
	// ============================================================

	@When("the user refreshes the page")
	public void theUserRefreshesThePage() {

		initialize();

		log.info("Refreshing current page");

		loginPage.refresh();
	}

	// ============================================================
	// LOGIN PAGE AFTER REFRESH
	// ============================================================

	@Then("the SauceDemo login page should be displayed")
	public void theSauceDemoLoginPageShouldBeDisplayed() {

		initialize();

		log.info("Verifying SauceDemo login page");

		loginAssertions.verifyLoginPageDisplayed();
	}
}
