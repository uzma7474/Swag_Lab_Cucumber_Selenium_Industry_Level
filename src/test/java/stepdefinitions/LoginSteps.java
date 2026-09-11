package stepdefinitions;

import assertions.InventoryAssertions;
import assertions.LoginAssertions;
import actions.LoginAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.InventoryPage;
import pages.LoginPage;

/**
 * Step definitions for SauceDemo Login feature.
 *
 * Architecture:
 *
 * Feature ↓ LoginSteps ↓ LoginAction ↓ LoginPage ↓ BasePage ↓ WebDriver
 *
 * Assertions are delegated to:
 *
 * LoginSteps ↓ LoginAssertion ↓ LoginPage
 */
public class LoginSteps {

	private static final Logger log = LoggerFactory.getLogger(LoginSteps.class);

	private LoginPage loginPage;
	private LoginAction loginAction;
	private LoginAssertions loginAssertion;

	private InventoryPage inventoryPage;
	private InventoryAssertions inventoryAssertions;

	/**
	 * Initializes page objects, actions and assertions.
	 *
	 * This method is intentionally lazy because WebDriver is created by Cucumber
	 * Hooks before the scenario execution.
	 */
	private void initialize() {

		if (loginPage == null) {

			log.debug("Initializing Login page objects");

			loginPage = new LoginPage();
			loginAction = new LoginAction(loginPage);
			loginAssertion = new LoginAssertions(loginPage);

			inventoryPage = new InventoryPage();
			inventoryAssertions = new InventoryAssertions(inventoryPage);

			log.debug("Login page objects initialized");
		}
	}

	// ============================================================
	// BACKGROUND
	// ============================================================

	@Given("the user is on the SauceDemo login page")
	public void theUserIsOnTheSauceDemoLoginPage() {

		initialize();

		log.info("Opening SauceDemo login page");

		loginAction.openLoginPage();

		loginAssertion.verifyLoginPageDisplayed();

		log.info("SauceDemo login page is displayed");
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
		 * Do NOT log the password.
		 */
		log.info("Entering password");

		loginAction.enterPassword(password);
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

		loginAssertion.verifyErrorMessageDisplayed();
	}

	// ============================================================
	// ERROR MESSAGE CONTENT
	// ============================================================

	@Then("the login error message should contain {string}")
	public void theLoginErrorMessageShouldContain(String expectedMessage) {

		initialize();

		log.info("Verifying login error message contains expected text: {}", expectedMessage);

		loginAssertion.verifyErrorMessageContains(expectedMessage);
	}

	// ============================================================
	// PASSWORD MASKING
	// ============================================================

	@Then("the password field should be masked")
	public void thePasswordFieldShouldBeMasked() {

		initialize();

		log.info("Verifying password field is masked");

		loginAssertion.verifyPasswordIsMasked();
	}

	// ============================================================
	// USERNAME FIELD
	// ============================================================

	@Then("the username field should be displayed")
	public void theUsernameFieldShouldBeDisplayed() {

		initialize();

		log.info("Verifying username field is displayed");

		loginAssertion.verifyUsernameFieldDisplayed();
	}

	// ============================================================
	// PASSWORD FIELD
	// ============================================================

	@Then("the password field should be displayed")
	public void thePasswordFieldShouldBeDisplayed() {

		initialize();

		log.info("Verifying password field is displayed");

		loginAssertion.verifyPasswordFieldDisplayed();
	}

	// ============================================================
	// LOGIN BUTTON DISPLAY
	// ============================================================

	@Then("the Login button should be displayed")
	public void theLoginButtonShouldBeDisplayed() {

		initialize();

		log.info("Verifying Login button is displayed");

		loginAssertion.verifyLoginButtonDisplayed();
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

		loginAssertion.verifyErrorMessageNotDisplayed();
	}

	// ============================================================
	// REMAIN ON LOGIN PAGE
	// ============================================================

	@Then("the user should remain on the login page")
	public void theUserShouldRemainOnTheLoginPage() {

		initialize();

		log.info("Verifying user remains on login page");

		loginAssertion.verifyLoginPageDisplayed();
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

		loginAssertion.verifyLoginPageDisplayed();
	}
}