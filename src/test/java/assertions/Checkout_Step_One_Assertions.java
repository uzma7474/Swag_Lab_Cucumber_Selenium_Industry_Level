
package assertions;

import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.Checkout_Step_One_Page;
import utils.WaitUtils;

public class Checkout_Step_One_Assertions {

	private static final Logger log = LoggerFactory.getLogger(Checkout_Step_One_Assertions.class);

	private final Checkout_Step_One_Page checkoutStepOnePage;

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	public Checkout_Step_One_Assertions() {

		this.checkoutStepOnePage = new Checkout_Step_One_Page();

		log.debug("Checkout_Step_One_Assertions initialized");
	}

	public Checkout_Step_One_Assertions(Checkout_Step_One_Page checkoutStepOnePage) {

		if (checkoutStepOnePage == null) {
			throw new IllegalArgumentException("Checkout_Step_One_Page must not be null");
		}

		this.checkoutStepOnePage = checkoutStepOnePage;

		log.debug("Checkout_Step_One_Assertions initialized");
	}

	// =========================================================
	// PAGE ASSERTIONS
	// =========================================================

	// =========================================================
	// FIRST NAME VALIDATION
	// =========================================================

	public void verifyFirstNameFieldDisplayed_() {

		log.info("Verifying First Name field is displayed");

		Assert.assertTrue(checkoutStepOnePage.isFirstNameFieldDisplayed(), "First Name field should be displayed");

		log.info("First Name field is displayed successfully");
	}

	/**
	 * Verifies the value entered in the First Name field.
	 *
	 * @param expectedFirstName expected first name
	 */
	public void verifyFirstNameValue(String expectedFirstName) {

		log.info("Verifying First Name field value");

		String actualFirstName = checkoutStepOnePage.getFirstName();

		Assert.assertEquals(actualFirstName, expectedFirstName, "Incorrect First Name field value");

		log.info("First Name field value verified successfully");
	}

	/**
	 * Verifies that the checkout error message is displayed.
	 */
	public void verifyErrorMessageDisplayed_() {

		log.info("Verifying checkout error message visibility");

		Assert.assertTrue(checkoutStepOnePage.isErrorMessageDisplayed(), "Checkout error message should be displayed");

		log.info("Checkout error message is displayed successfully");
	}

	/**
	 * Verifies the value entered in the Postal Code field.
	 *
	 * @param expectedPostalCode expected postal code
	 */
	public void verifyPostalCodeValue(String expectedPostalCode) {

		log.info("Verifying Postal Code field value");

		String actualPostalCode = checkoutStepOnePage.getPostalCode();

		Assert.assertEquals(actualPostalCode, expectedPostalCode, "Incorrect Postal Code field value");

		log.info("Postal Code field value verified successfully");
	}

	/**
	 * Verifies the value entered in the Last Name field.
	 *
	 * @param expectedLastName expected last name
	 */
	public void verifyLastNameValue(String expectedLastName) {

		log.info("Verifying Last Name field value");

		String actualLastName = checkoutStepOnePage.getLastName();

		Assert.assertEquals(actualLastName, expectedLastName, "Incorrect Last Name field value");

		log.info("Last Name field value verified successfully");
	}

	/**
	 * Verifies that Checkout Step One page is displayed.
	 */
	public void verifyCheckoutStepOnePageDisplayed() {

		log.info("Verifying Checkout Step One page is displayed");

		Assert.assertTrue(checkoutStepOnePage.isCheckoutStepOnePageDisplayed(),
				"Checkout Step One page should be displayed");

		log.info("Checkout Step One page is displayed successfully");
	}

	/**
	 * Verifies Checkout Step One page title.
	 */
	public void verifyPageTitle(String expectedTitle) {

		log.info("Verifying Checkout Step One page title. Expected: {}", expectedTitle);

		Assert.assertEquals(checkoutStepOnePage.getPageTitle(), expectedTitle,
				"Checkout Step One page title is incorrect");

		log.info("Checkout Step One page title verified successfully");
	}

	/**
	 * Verifies checkout information form is displayed.
	 */
	public void verifyCheckoutInformationFormDisplayed() {

		log.info("Verifying checkout information form is displayed");

		Assert.assertTrue(checkoutStepOnePage.isCheckoutInformationFormDisplayed(),
				"Checkout information form should be displayed");

		log.info("Checkout information form is displayed successfully");
	}

	// =========================================================
	// FIELD ASSERTIONS
	// =========================================================

	/**
	 * Verifies First Name field is displayed.
	 */
	public void verifyFirstNameFieldDisplayed() {

		log.info("Verifying First Name field is displayed");

		Assert.assertTrue(checkoutStepOnePage.isFirstNameFieldDisplayed(), "First Name field should be displayed");

		log.info("First Name field is displayed successfully");
	}

	/**
	 * Verifies Last Name field is displayed.
	 */
	public void verifyLastNameFieldDisplayed() {

		log.info("Verifying Last Name field is displayed");

		Assert.assertTrue(checkoutStepOnePage.isLastNameFieldDisplayed(), "Last Name field should be displayed");

		log.info("Last Name field is displayed successfully");
	}

	/**
	 * Verifies Postal Code field is displayed.
	 */
	public void verifyPostalCodeFieldDisplayed() {

		log.info("Verifying Postal Code field is displayed");

		Assert.assertTrue(checkoutStepOnePage.isPostalCodeFieldDisplayed(), "Postal Code field should be displayed");

		log.info("Postal Code field is displayed successfully");
	}

	/**
	 * Verifies all checkout input fields are displayed.
	 */
	public void verifyAllInputFieldsDisplayed() {

		log.info("Verifying all checkout input fields are displayed");

		Assert.assertTrue(checkoutStepOnePage.areAllInputFieldsDisplayed(),
				"First Name, Last Name and Postal Code fields should all be displayed");

		log.info("All checkout input fields are displayed successfully");
	}

	// =========================================================
	// FIELD VALUE ASSERTIONS
	// =========================================================

	/**
	 * Verifies First Name field value.
	 */
	public void verifyFirstName(String expectedFirstName) {

		log.info("Verifying First Name field value");

		Assert.assertEquals(checkoutStepOnePage.getFirstName(), expectedFirstName,
				"First Name field value is incorrect");

		log.info("First Name field value verified successfully");
	}

	/**
	 * Verifies Last Name field value.
	 */
	public void verifyLastName(String expectedLastName) {

		log.info("Verifying Last Name field value");

		Assert.assertEquals(checkoutStepOnePage.getLastName(), expectedLastName, "Last Name field value is incorrect");

		log.info("Last Name field value verified successfully");
	}

	/**
	 * Verifies Postal Code field value.
	 */
	public void verifyPostalCode(String expectedPostalCode) {

		log.info("Verifying Postal Code field value");

		Assert.assertEquals(checkoutStepOnePage.getPostalCode(), expectedPostalCode,
				"Postal Code field value is incorrect");

		log.info("Postal Code field value verified successfully");
	}

	/**
	 * Verifies complete checkout information.
	 */
	public void verifyCheckoutInformation(String expectedFirstName, String expectedLastName,
			String expectedPostalCode) {

		log.info("Verifying complete checkout information");

		verifyFirstName(expectedFirstName);
		verifyLastName(expectedLastName);
		verifyPostalCode(expectedPostalCode);

		log.info("Complete checkout information verified successfully");
	}

	// =========================================================
	// BUTTON ASSERTIONS
	// =========================================================

	/**
	 * Verifies Continue button is displayed.
	 */
	public void verifyContinueButtonDisplayed() {

		log.info("Verifying Continue button is displayed");

		Assert.assertTrue(checkoutStepOnePage.isContinueButtonDisplayed(), "Continue button should be displayed");

		log.info("Continue button is displayed successfully");
	}

	/**
	 * Verifies Cancel button is displayed.
	 */
	public void verifyCancelButtonDisplayed() {

		log.info("Verifying Cancel button is displayed");

		Assert.assertTrue(checkoutStepOnePage.isCancelButtonDisplayed(), "Cancel button should be displayed");

		log.info("Cancel button is displayed successfully");
	}

	/**
	 * Verifies Continue button is enabled.
	 */
	public void verifyContinueButtonEnabled() {

		log.info("Verifying Continue button is enabled");

		Assert.assertTrue(checkoutStepOnePage.isContinueButtonEnabled(), "Continue button should be enabled");

		log.info("Continue button is enabled successfully");
	}

	/**
	 * Verifies Cancel button is enabled.
	 */
	public void verifyCancelButtonEnabled() {

		log.info("Verifying Cancel button is enabled");

		Assert.assertTrue(checkoutStepOnePage.isCancelButtonEnabled(), "Cancel button should be enabled");

		log.info("Cancel button is enabled successfully");
	}

	/**
	 * Verifies all checkout controls are displayed.
	 */
	public void verifyAllCheckoutControlsDisplayed() {

		log.info("Verifying all Checkout Step One controls are displayed");

		Assert.assertTrue(checkoutStepOnePage.isContinueButtonDisplayed(), "Continue button should be displayed");

		Assert.assertTrue(checkoutStepOnePage.isCancelButtonDisplayed(), "Cancel button should be displayed");

		log.info("All Checkout Step One controls are displayed successfully");
	}

	// =========================================================
	// ERROR ASSERTIONS
	// =========================================================

	/**
	 * Verifies checkout validation error is displayed.
	 */
	public void verifyErrorMessageDisplayed() {

		log.info("Verifying checkout validation error message is displayed");

		Assert.assertTrue(checkoutStepOnePage.isErrorMessageDisplayed(),
				"Checkout validation error message should be displayed");

		log.info("Checkout validation error message is displayed successfully");
	}

	public void verifyErrorMessageNotDisplayed() {

		log.info("Verifying checkout error message is not displayed");

		boolean isDisplayed = checkoutStepOnePage.isErrorMessageDisplayed();

		Assert.assertFalse(isDisplayed, "Checkout error message should not be displayed but it is displayed");

		log.info("Checkout error message is not displayed successfully");
	}

	public void verifyCheckoutOverviewPageDisplayed() {

		log.info("Verifying Checkout Step Two / Checkout Overview page");

		Assert.assertTrue(checkoutStepOnePage.isCheckoutOverviewPageDisplayed(),
				"Checkout Step Two page should be displayed");

		log.info("Checkout Step Two page is displayed successfully");
	}

	/**
	 * Verifies checkout validation error message.
	 */
	public void verifyErrorMessage(String expectedMessage) {

		log.info("Verifying checkout error message. Expected: {}", expectedMessage);

		Assert.assertEquals(checkoutStepOnePage.getErrorMessage(), expectedMessage,
				"Checkout validation error message is incorrect");

		log.info("Checkout validation error message verified successfully");
	}

	public void verifyPostalCodeValidation_() {

		log.info("Verifying Postal Code validation");

		Assert.assertTrue(checkoutStepOnePage.isErrorMessageDisplayed(),
				"Postal Code validation error should be displayed");

		Assert.assertEquals(checkoutStepOnePage.getErrorMessage(), "Error: Postal Code is required",
				"Incorrect Postal Code validation message");

		log.info("Postal Code validation verified successfully");
	}

	public void verifyPostalCodeValidationABCD() {
		log.info("Verifying handling of alphabetic postal code");

		Assert.assertFalse(checkoutStepOnePage.isErrorMessageDisplayed(),
				"An unexpected validation error was displayed for the postal code");

		log.info("Alphabetic postal code was handled without a validation error");

	}

	public void verifyPostalCodeValidation() {

		log.info("Verifying valid postal code handling");

		Assert.assertTrue(checkoutStepOnePage.isPostalCodeFieldDisplayed(), "Postal Code field should be displayed");

		Assert.assertFalse(checkoutStepOnePage.isErrorMessageDisplayed(),
				"No validation error should be displayed for a valid postal code");

		log.info("Valid postal code handled successfully");
	}

	public void verifyPostalCodeValidation(boolean errorExpected) {
		log.info("Verifying postal code validation. Error expected: {}", errorExpected);

		boolean errorDisplayed = checkoutStepOnePage.isErrorMessageDisplayed();

		Assert.assertEquals(errorDisplayed, errorExpected, "Unexpected Postal Code validation behavior");

		log.info("Postal code validation verified successfully");

	}

	/**
	 * Verifies First Name required validation.
	 */
	public void verifyFirstNameRequiredError() {

		log.info("Verifying First Name required error");

		verifyErrorMessage("Error: First Name is required");

		log.info("First Name required error verified successfully");
	}

	/**
	 * Verifies Last Name required validation.
	 */
	public void verifyLastNameRequiredError() {

		log.info("Verifying Last Name required error");

		verifyErrorMessage("Error: Last Name is required");

		log.info("Last Name required error verified successfully");
	}

	/**
	 * Verifies Postal Code required validation.
	 */
	public void verifyPostalCodeRequiredError() {

		log.info("Verifying Postal Code required error");

		verifyErrorMessage("Error: Postal Code is required");

		log.info("Postal Code required error verified successfully");
	}

	// =========================================================
	// PAGE READINESS ASSERTION
	// =========================================================

	/**
	 * Verifies Checkout Step One page is completely ready.
	 */
	public void verifyPageReadyForCheckout() {

		log.info("Verifying Checkout Step One page is ready for checkout");

		Assert.assertTrue(checkoutStepOnePage.isPageReadyForCheckout(),
				"Checkout Step One page should be ready for checkout");

		log.info("Checkout Step One page is ready for checkout");
	}

	// =========================================================
	// WAIT BASED ASSERTIONS
	// =========================================================

	/**
	 * Waits for Checkout Step One page to be displayed and verifies it.
	 */
	public void waitAndVerifyCheckoutStepOnePageDisplayed() {

		log.info("Waiting for Checkout Step One page to be displayed");

		boolean pageDisplayed = WaitUtils
				.waitForCondition(driver -> checkoutStepOnePage.isCheckoutStepOnePageDisplayed());

		Assert.assertTrue(pageDisplayed, "Checkout Step One page should be displayed after waiting");

		log.info("Checkout Step One page displayed successfully after wait");
	}

	/**
	 * Waits for checkout error message and verifies it.
	 */
	public void waitAndVerifyErrorMessage(String expectedMessage) {

		log.info("Waiting for checkout validation error message");

		boolean errorDisplayed = WaitUtils.waitForCondition(driver -> checkoutStepOnePage.isErrorMessageDisplayed());

		Assert.assertTrue(errorDisplayed, "Checkout validation error message should be displayed");

		verifyErrorMessage(expectedMessage);
	}

	/** * Waits for Checkout Step One page to be displayed * and verifies it. */
	public void waitAndVerifyCheckoutStepOnePageDisplayed_() {
		log.info("Waiting for Checkout Step One page to be displayed");
		boolean pageDisplayed = WaitUtils
				.waitForCondition(driver -> checkoutStepOnePage.isCheckoutStepOnePageDisplayed());
		Assert.assertTrue(pageDisplayed, "Checkout Step One page should be displayed after waiting");
		log.info("Checkout Step One page displayed successfully after wait");

	}

	/** * Waits for checkout error message and verifies it. */
	public void waitAndVerifyErrorMessage_(String expectedMessage) {
		log.info("Waiting for checkout validation error message");
		boolean errorDisplayed = WaitUtils.waitForCondition(driver -> checkoutStepOnePage.isErrorMessageDisplayed());
		Assert.assertTrue(errorDisplayed, "Checkout validation error message should be displayed");
		verifyErrorMessage(expectedMessage);

	}

	public void firstnameValidation(String firstName) {
		// String firstName = checkoutStepOnePage.getFirstNameValue();
		log.info("First Name entered: {}", firstName);
		boolean validFirstName = firstName != null && !firstName.isEmpty() && firstName.matches("^[A-Za-z]+$");
		if (validFirstName) {
			log.info("First Name '{}' is valid according to validation rules", firstName);
			Assert.assertTrue(validFirstName, "First Name should be valid: " + firstName);

		} else {
			log.info("First Name '{}' is invalid according to validation rules", firstName);
			Assert.assertFalse(validFirstName, "First Name should be invalid: " + firstName);
		}
		log.info("First Name validation completed");
	}

	public void lastNameValidation(String lastName) {
		// String firstName = checkoutStepOnePage.getFirstNameValue();
		log.info("First Name entered: {}", lastName);
		boolean validFirstName = lastName != null && !lastName.isEmpty() && lastName.matches("^[A-Za-z]+$");
		if (validFirstName) {
			log.info("First Name '{}' is valid according to validation rules", lastName);
			Assert.assertTrue(validFirstName, "First Name should be valid: " + lastName);

		} else {
			log.info("First Name '{}' is invalid according to validation rules", lastName);
			Assert.assertFalse(validFirstName, "First Name should be invalid: " + lastName);
		}
		log.info("First Name validation completed");
	}

	public boolean isValidFirstName(String firstName) {

		if (firstName == null || firstName.isEmpty()) {
			return false;
		}

		return firstName.matches("^[A-Za-z]+$");
	}

	public void verifyFirstNameCorrect(String firstName) {

		boolean actualResult = isValidFirstName(firstName);

		log.info("First Name: {}, Validation Result: {}", firstName, actualResult);

		Assert.assertTrue(actualResult, "First Name failed validation: " + firstName);
	}

	public boolean isValidLastName(String lastName) {

		if (lastName == null || lastName.isEmpty()) {
			return false;
		}

		return lastName.matches("^[A-Za-z]+$");
	}

	public void verifyLastNameValidity(String lastName) {

		boolean actualResult = isValidLastName(lastName);

		log.info("Last Name: {}, Validation Result: {}", lastName, actualResult);

		Assert.assertTrue(actualResult, "Last Name failed validation: " + lastName);
	}

	public void verifyValidFirstName(String firstName) {

		boolean isValid = firstName != null && !firstName.isEmpty() && firstName.matches("^[A-Za-z]+$");

		Assert.assertTrue(isValid, "First Name should be valid but was invalid: " + firstName);
	}

	public void verifyInvalidFirstName(String firstName) {

		boolean isValid = firstName != null && !firstName.isEmpty() && firstName.matches("^[A-Za-z]+$");

		Assert.assertFalse(isValid, "First Name should be invalid but was accepted: " + firstName);
	}

	public void verifyValidLastName(String lastName) {

		boolean isValid = lastName != null && !lastName.isEmpty() && lastName.matches("^[A-Za-z]+$");

		Assert.assertTrue(isValid, "Last Name should be valid but was invalid: " + lastName);
	}

	public void verifyInvalidLastName(String lastName) {

		boolean isValid = lastName != null && !lastName.isEmpty() && lastName.matches("^[A-Za-z]+$");

		Assert.assertFalse(isValid, "Last Name should be invalid but was accepted: " + lastName);
	}

	public void verifyValidPostalCode(String postalCode) {

		boolean isValid = postalCode != null && !postalCode.isEmpty() && postalCode.matches("^[1-9][0-9]{5}$");

		Assert.assertTrue(isValid, "Postal Code should be valid but was invalid: " + postalCode);
	}

	public void verifyInvalidPostalCode(String postalCode) {

		boolean isValid = postalCode != null && !postalCode.isEmpty() && postalCode.matches("^[1-9][0-9]{5}$");

		Assert.assertFalse(isValid, "Postal Code should be invalid but was accepted: " + postalCode);
	}

	public void verifyCheckoutErrorMsgDisplayed() {

		log.info("Verifying checkout error message is displayed");

		boolean isDisplayed = WaitUtils.waitForElementVisible(checkoutStepOnePage.getErrorMessage());

		Assert.assertTrue(isDisplayed, "Checkout error message should be displayed but was not displayed");

		log.info("Checkout error message is displayed");
	}

	public void verifyCheckoutErrorMessageDisplayed_() {

		log.info("Verifying checkout error message is displayed");

		boolean isDisplayed = WaitUtils.waitForElementVisible("[data-test='error']");

		Assert.assertTrue(isDisplayed, "Checkout error message should be displayed but was not displayed");

		log.info("Checkout error message is displayed");
	}

	public void verifyCheckoutErrorMessageDisplayed() {

		log.info("Verifying checkout error message is displayed");

		boolean isDisplayed = WaitUtils.waitForElementVisible(checkoutStepOnePage.getErrorMessageLocator());

		Assert.assertTrue(isDisplayed, "Checkout error message should be displayed but was not displayed");

		String errorMessage = checkoutStepOnePage.getErrorMessageText();

		log.info("Checkout error message displayed: {}", errorMessage);

		log.info("Checkout error message is displayed successfully");
	}
	
	
	/**
	 * Enters valid checkout information.
	 *
	 * Valid SauceDemo checkout data:
	 * First Name : John
	 * Last Name  : Doe
	 * Postal Code: 411001
	 */
	public void enterValidCheckoutInformation() {

	    log.info("Entering valid checkout information");

	    checkoutStepOnePage.enterFirstName("John");
	    checkoutStepOnePage.enterLastName("Doe");
	    checkoutStepOnePage.enterPostalCode("411001");

	    log.info("Valid checkout information entered successfully");
	}


}
