
package stepdefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import actions.Checkout_Step_One_Action;
import assertions.Checkout_Step_One_Assertions;
import context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for SauceDemo Checkout Step One.
 *
 * Responsibilities: - Perform Checkout Step One actions - Validate checkout
 * information - Validate required-field errors - Validate checkout navigation
 */
public class Checkout_Step_One_Steps {

	private static final Logger log = LoggerFactory.getLogger(Checkout_Step_One_Steps.class);

	private final Checkout_Step_One_Action checkoutStepOneAction;

	private final Checkout_Step_One_Assertions checkoutStepOneAssertions;

	/**
	 * Constructor injection using ScenarioContext.
	 *
	 * @param context ScenarioContext containing PageObjectManager
	 */
	public Checkout_Step_One_Steps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.checkoutStepOneAction = new Checkout_Step_One_Action(
				context.getPageObjectManager().getCheckout_Step_One_Page());

		this.checkoutStepOneAssertions = new Checkout_Step_One_Assertions(
				context.getPageObjectManager().getCheckout_Step_One_Page());

		log.debug("Checkout_Step_One_Steps initialized");
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

	// @Given("the Checkout Step One page should be displayed")
	@Then("the Checkout Step One page should be displayed")
	public void theCheckoutStepOnePageShouldBeDisplayed() {

		log.info("Verifying Checkout Step One page is displayed");

		checkoutStepOneAssertions.verifyCheckoutStepOnePageDisplayed();
	}

	@Then("the page title should be {string}")
	public void thePageTitleShouldBe(String expectedTitle) {

		log.info("Verifying Checkout Step One page title");

		checkoutStepOneAssertions.verifyPageTitle(expectedTitle);
	}

	@Then("the checkout information form should be displayed")
	public void theCheckoutInformationFormShouldBeDisplayed() {

		log.info("Verifying checkout information form");

		checkoutStepOneAssertions.verifyCheckoutInformationFormDisplayed();
	}

	@Then("the Checkout Step Two page should be displayed")
	public void the_checkout_step_two_page_should_be_displayed() {

		log.info("Verifying Checkout Step Two page is displayed");

		checkoutStepOneAssertions.verifyCheckoutOverviewPageDisplayed();

		log.info("Checkout Step Two page is displayed successfully");
	}

	@Given("the user is on the Checkout Step One page")
	public void theUserIsOnTheCheckoutStepOnePage() {

		log.info("Ensuring user is on Checkout Step One page");

		// checkoutStepOneAction.openCheckoutStepOnePage();

		checkoutStepOneAssertions.verifyCheckoutStepOnePageDisplayed();

		log.info("User is on Checkout Step One page successfully");
	}

	// =========================================================
	// FIRST NAME
	// =========================================================

	@When("the user enters first name {string}")
	public void theUserEntersFirstName(String firstName) {

		log.info("Entering first name");

		checkoutStepOneAction.enterFirstName(firstName);
	}

	@And("the user clears the First Name field")
	public void theUserClearsFirstNameField() {

		log.info("Clearing First Name field");

		checkoutStepOneAction.clearFirstName();
	}

	@Then("the First Name field should contain {string}")
	public void theFirstNameFieldShouldContain(String expectedFirstName) {

		log.info("Verifying First Name field value");

		checkoutStepOneAssertions.verifyFirstNameValue(expectedFirstName);
	}

	@Then("the First Name field should be displayed")
	public void theFirstNameFieldShouldBeDisplayed() {

		log.info("Verifying First Name field is displayed");

		checkoutStepOneAssertions.verifyFirstNameFieldDisplayed();
	}

	@Then("the First Name field should be empty")
	public void theFirstNameFieldShouldBeEmpty() {

		log.info("Verifying First Name field is empty");

		checkoutStepOneAssertions.verifyFirstNameValue("");
	}

	// =========================================================
	// LAST NAME
	// =========================================================

	@When("the user enters last name {string}")
	public void theUserEntersLastName(String lastName) {

		log.info("Entering last name");

		checkoutStepOneAction.enterLastName(lastName);
	}

	@And("the user clears the Last Name field")
	public void theUserClearsLastNameField() {

		log.info("Clearing Last Name field");

		checkoutStepOneAction.clearLastName();
	}

	@Then("the Last Name field should contain {string}")
	public void theLastNameFieldShouldContain(String expectedLastName) {

		log.info("Verifying Last Name field value");

		checkoutStepOneAssertions.verifyLastNameValue(expectedLastName);
	}

	@Then("the Last Name field should be displayed")
	public void theLastNameFieldShouldBeDisplayed() {

		log.info("Verifying Last Name field is displayed");

		checkoutStepOneAssertions.verifyLastNameFieldDisplayed();
	}

	@Then("the Last Name field should be empty")
	public void theLastNameFieldShouldBeEmpty() {

		log.info("Verifying Last Name field is empty");

		checkoutStepOneAssertions.verifyLastNameValue("");
	}

	// =========================================================
	// POSTAL CODE
	// =========================================================

	@When("the user enters postal code {string}")
	public void theUserEntersPostalCode(String postalCode) {

		log.info("Entering postal code");

		checkoutStepOneAction.enterPostalCode(postalCode);
	}

	@And("the user clears the Postal Code field")
	public void theUserClearsPostalCodeField() {

		log.info("Clearing Postal Code field");

		checkoutStepOneAction.clearPostalCode();
	}

	@Then("the Postal Code field should contain {string}")
	public void thePostalCodeFieldShouldContain(String expectedPostalCode) {

		log.info("Verifying Postal Code field value");

		checkoutStepOneAssertions.verifyPostalCodeValue(expectedPostalCode);
	}

	@Then("the Postal Code field should be displayed")
	public void thePostalCodeFieldShouldBeDisplayed() {

		log.info("Verifying Postal Code field is displayed");

		checkoutStepOneAssertions.verifyPostalCodeFieldDisplayed();
	}

	@Then("the Postal Code field should be empty")
	public void thePostalCodeFieldShouldBeEmpty() {

		log.info("Verifying Postal Code field is empty");

		checkoutStepOneAssertions.verifyPostalCodeValue("");
	}

	// =========================================================
	// CHECKOUT ACTIONS
	// =========================================================

	@When("the user clicks the Continue button")
	public void theUserClicksTheContinueButton() {

		log.info("Clicking Continue button");

		checkoutStepOneAction.clickContinue();
	}

	@When("the user clicks the Cancel button")
	public void theUserClicksTheCancelButton() {

		log.info("Clicking Cancel button");

		checkoutStepOneAction.clickCancel();
	}

	// =========================================================
	// CHECKOUT INFORMATION
	// =========================================================

	@When("the user enters checkout information with first name {string}, last name {string}, and postal code {string}")
	public void theUserEntersCheckoutInformation(String firstName, String lastName, String postalCode) {

		log.info("Entering complete checkout information");

		checkoutStepOneAction.enterCheckoutInformation(firstName, lastName, postalCode);
	}

	@When("the user enters checkout information and continues with first name {string}, last name {string}, and postal code {string}")
	public void theUserEntersCheckoutInformationAndContinues(String firstName, String lastName, String postalCode) {

		log.info("Entering checkout information and continuing");

		checkoutStepOneAction.continueWithCheckoutInformation(firstName, lastName, postalCode);
	}

	// =========================================================
	// ERROR VALIDATION
	// =========================================================

	@Then("the checkout error message should be displayed")
	public void theCheckoutErrorMessageShouldBeDisplayed() {

		log.info("Verifying checkout error message is displayed");

		checkoutStepOneAssertions.verifyErrorMessageDisplayed();
	}

	@Then("the checkout error message should be {string}")
	public void theCheckoutErrorMessageShouldBe(String expectedMessage) {

		log.info("Verifying checkout error message");

		checkoutStepOneAssertions.verifyErrorMessage(expectedMessage);
	}

	@Then("the Checkout Step One page should remain displayed")
	public void the_checkout_step_one_page_should_remain_displayed() {

		log.info("Verifying Checkout Step One page remains displayed");

		checkoutStepOneAssertions.verifyCheckoutStepOnePageDisplayed();

		log.info("Checkout Step One page remains displayed successfully");
	}

	@Then("the system should handle the postal code according to its validation rules")
	public void the_system_should_handle_the_postal_code_according_to_its_validation_rules() {

		log.info("Verifying postal code is handled according to validation rules");

		checkoutStepOneAssertions.verifyPostalCodeValidation();

		log.info("Postal code validation rules handled successfully");
	}

	@Then("no postal code validation error should be displayed")
	public void no_postal_code_validation_error_displayed() {
		log.info("Verifying postal code is handled according to validation rules");

		checkoutStepOneAssertions.verifyPostalCodeValidation(false);

		log.info("Postal code validation rules handled successfully");
	}

	@When("the user closes the checkout error message")
	public void theUserClosesTheCheckoutErrorMessage() {

		log.info("Closing checkout error message");

		checkoutStepOneAction.closeErrorMessage();
	}

	@Then("the checkout error message should not be displayed")
	public void theCheckoutErrorMessageShouldNotBeDisplayed() {

		log.info("Verifying checkout error message is not displayed");

		checkoutStepOneAssertions.verifyErrorMessageNotDisplayed();

		log.info("Verified checkout error message is not displayed");
	}

	// =========================================================
	// WAIT VALIDATION
	// =========================================================

	@Then("the Checkout Step One page should be displayed after waiting")
	public void theCheckoutStepOnePageShouldBeDisplayedAfterWaiting() {

		log.info("Waiting for Checkout Step One page");

		checkoutStepOneAssertions.waitAndVerifyCheckoutStepOnePageDisplayed();
	}

	@Then("the checkout error message should be displayed after waiting")
	public void theCheckoutErrorMessageShouldBeDisplayedAfterWaiting() {

		log.info("Waiting for checkout error message");

		checkoutStepOneAssertions.waitAndVerifyErrorMessage("Error: First Name is required");
	}

	// =========================================================
	// CHECKOUT STEP ONE - BUTTON VALIDATION
	// =========================================================

	@Then("the Continue button should be displayed")
	public void theContinueButtonShouldBeDisplayed() {

		log.info("Verifying Continue button is displayed");

		checkoutStepOneAssertions.verifyContinueButtonDisplayed();

		log.info("Continue button is displayed successfully");
	}

	@Then("the Cancel button should be displayed")
	public void theCancelButtonShouldBeDisplayed() {

		log.info("Verifying Cancel button is displayed");

		checkoutStepOneAssertions.verifyCancelButtonDisplayed();

		log.info("Cancel button is displayed successfully");
	}

	@Then("the system should handle the first name {string} according to its validation rules")
	public void theSystemShouldHandleTheFirstNameAccordingToItsValidationRules(String firstname) {
		log.info("Validating First Name according to validation rules");

		checkoutStepOneAssertions.firstnameValidation(firstname);

	}

	@Then("the system should handle the first name according to its validation rules")
	public void theSystemShouldHandleTheFirstNameAccordingToItsValidationRules() {

		log.info("Validating First Name according to validation rules");
		checkoutStepOneAssertions.verifyFirstNameCorrect("John");
	}

	@Then("the system should handle the last name {string} according to its validation rules")
	public void theSystemShouldHandleTheLastnameAccordingToItsValidationRules(String lastName) {
		log.info("Validating Last Name according to validation rules");

		checkoutStepOneAssertions.verifyLastNameValidity(lastName);
	}

	@Then("the system should accept the first name {string} according to its validation rules")
	public void theSystemShouldAcceptFirstNameAccordingToItsValidationRules(String firstName) {

		checkoutStepOneAssertions.verifyValidFirstName(firstName);
	}

	@Then("the system should reject the first name {string} according to its validation rules")
	public void theSystemShouldRejectFirstNameAccordingToItsValidationRules(String firstName) {

		checkoutStepOneAssertions.verifyInvalidFirstName(firstName);
	}

	@Then("the system should accept the last name {string} according to its validation rules")
	public void theSystemShouldAcceptTheLastNameAccordingToItsValidationRules(String lastName) {

		checkoutStepOneAssertions.verifyValidLastName(lastName);
	}

	@Then("the system should reject the last name {string} according to its validation rules")
	public void theSystemShouldRejectTheLastNameAccordingToItsValidationRules(String lastName) {

		checkoutStepOneAssertions.verifyInvalidLastName(lastName);
	}

	@Then("the system should accept the postal code {string} according to its validation rules")
	public void theSystemShouldAcceptPostalCodeAccordingToItsValidationRules(String postalCode) {

		checkoutStepOneAssertions.verifyValidPostalCode(postalCode);
	}

	@Then("the system should reject the postal code {string} according to its validation rules")
	public void theSystemShouldRejectPostalCodeAccordingToItsValidationRules(String postalCode) {

		checkoutStepOneAssertions.verifyInvalidPostalCode(postalCode);
	}

	@When("the checkout error message is displayed")
	public void the_checkout_error_message_is_displayed() {

	    log.info("Verifying checkout error message is displayed");

	    checkoutStepOneAssertions.verifyCheckoutErrorMessageDisplayed();

	    log.info("Checkout error message is displayed successfully");
	}
	
	
	@When("the user reaches Checkout Step Two")
	public void the_user_reaches_checkout_step_two() {

	    log.info("Attempting to reach Checkout Step Two");

	    checkoutStepOneAction.enterFirstName("John");
	    checkoutStepOneAction.enterLastName("Doe");
	    checkoutStepOneAction.enterPostalCode("411042");

	    checkoutStepOneAction.clickContinue();

	     log.info("User attempted to proceed to Checkout Step Two");
	}
	  
	@When("the user proceeds to Checkout Step Two") 
	public void the_user_proceeds_to_checkout_step_two() { 
		log.info( "STEP: User proceeds to Checkout Step Two" ); 
		
		checkoutStepOneAction.proceedToCheckoutStepTwo(); 
		
		log.info( "STEP: User successfully proceeded to Checkout Step Two" ); 
			
	} 
		

		
	@When("the user enters valid checkout information") 
	public void the_user_enters_valid_checkout_information() { 
		log.info("User enters valid checkout information"); 
		
		checkoutStepOneAction.enterValidCheckoutInformation(); 
			
		log.info("Valid checkout information entered successfully"); 
			
	}
		
	@When("the user continues to Checkout Step Two") 
	public void the_user_continues_to_checkout_step_two() { 
		log.info("User continues to Checkout Step Two"); 
			
		checkoutStepOneAction.clickContinue(); 
			
		log.info("Checkout Step Two page opened"); 
			
	}
	

	
}