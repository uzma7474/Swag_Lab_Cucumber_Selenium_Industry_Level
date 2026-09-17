package pages;

import base.BasePage;
import config.EnvironmentManager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Checkout_Step_One_Page extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(Checkout_Step_One_Page.class);

	// =========================================================
	// PAGE LOCATORS
	// =========================================================

	@FindBy(css = ".title")
	private WebElement pageTitle;

	@FindBy(css = ".checkout_info")
	private WebElement checkoutInformationForm;

	@FindBy(id = "first-name")
	private WebElement firstNameInput;

	@FindBy(id = "last-name")
	private WebElement lastNameInput;

	@FindBy(id = "postal-code")
	private WebElement postalCodeInput;

	@FindBy(id = "cancel")
	private WebElement cancelButton;

	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(css = "[data-test='error']")
	private WebElement errorMessage;

	@FindBy(css = ".error-message-container")
	private WebElement errorMessageContainer;

	@FindBy(css = ".error-button")
	private WebElement errorCloseButton;
	
	private static final String ERROR_MESSAGE_LOCATOR =
	        "[data-test='error']";

	@FindBy(css = ERROR_MESSAGE_LOCATOR)
	private WebElement errorMsg;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public Checkout_Step_One_Page() {

		super();

		PageFactory.initElements(driver, this);

		log.debug("Checkout_Step_One_Page initialized");
	}

	public String getErrorMessageLocator() {
	    return ERROR_MESSAGE_LOCATOR;
	}
	
	
	
	public String getErrorMessageText() {

	    log.info("Getting checkout error message text");

	    return errorMessage.getText();
	}
	
	
	// =========================================================
	// NAVIGATION
	// =========================================================

	/**
	 * Opens Checkout Step One page directly.
	 *
	 * Normally this page should be reached through:
	 *
	 * Login -> Inventory -> Cart -> Checkout
	 *
	 * Direct navigation can be used for page-level testing.
	 */
	public void open() {

		log.info("Navigating to SauceDemo Checkout Step One page");

		navigateTo(EnvironmentManager.getBaseUrl() + "/checkout-step-one.html");
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

	/**
	 * Returns the checkout page title.
	 */
	public String getPageTitle() {

		log.debug("Getting Checkout Step One page title");

		return getText(pageTitle);
	}

	/**
	 * Checks whether Checkout Step One page is displayed.
	 */
	public boolean isCheckoutStepOnePageDisplayed() {

		log.debug("Checking whether Checkout Step One page is displayed");

		return isDisplayed(pageTitle)
				&& "Checkout: Your Information".equalsIgnoreCase(getText(pageTitle));
	}
	
	
	public boolean isCheckoutOverviewPageDisplayed() {

	    try {
	        return driver.getCurrentUrl().contains("checkout-step-two.html")
	                && getPageTitle().equals("Checkout: Overview");
	    } catch (Exception e) {
	        log.error("Unable to verify Checkout Step Two page", e);
	        return false;
	    }
	}




	/**
	 * Checks whether checkout information form is displayed.
	 */
	public boolean isCheckoutInformationFormDisplayed() {

		log.debug("Checking checkout information form visibility");

		return isDisplayed(checkoutInformationForm);
	}

	// =========================================================
	// FIRST NAME
	// =========================================================

	/**
	 * Enters first name.
	 */
	public void enterFirstName(String firstName) {

		log.debug("Entering first name");

		type(firstNameInput, firstName);
	}

	/**
	 * Clears first name field.
	 */
	public void clearFirstName() {

		log.debug("Clearing first name field");

		firstNameInput.clear();
	}

	/**
	 * Returns first name field value.
	 */
	public String getFirstName() {

		log.debug("Getting first name field value");

		return firstNameInput.getAttribute("value");
	}

	/**
	 * Checks whether first name field is displayed.
	 */
	public boolean isFirstNameFieldDisplayed() {

		log.debug("Checking first name field visibility");

		return isDisplayed(firstNameInput);
	}

	// =========================================================
	// LAST NAME
	// =========================================================

	/**
	 * Enters last name.
	 */
	public void enterLastName(String lastName) {

		log.debug("Entering last name");

		type(lastNameInput, lastName);
	}

	/**
	 * Clears last name field.
	 */
	public void clearLastName() {

		log.debug("Clearing last name field");

		lastNameInput.clear();
	}

	/**
	 * Returns last name field value.
	 */
	public String getLastName() {

		log.debug("Getting last name field value");

		return lastNameInput.getAttribute("value");
	}

	/**
	 * Checks whether last name field is displayed.
	 */
	public boolean isLastNameFieldDisplayed() {

		log.debug("Checking last name field visibility");

		return isDisplayed(lastNameInput);
	}

	// =========================================================
	// POSTAL CODE
	// =========================================================

	/**
	 * Enters ZIP / Postal Code.
	 */
	public void enterPostalCode(String postalCode) {

		log.debug("Entering postal code");

		type(postalCodeInput, postalCode);
	}

	/**
	 * Clears ZIP / Postal Code field.
	 */
	public void clearPostalCode() {

		log.debug("Clearing postal code field");

		postalCodeInput.clear();
	}

	/**
	 * Returns ZIP / Postal Code field value.
	 */
	public String getPostalCode() {

		log.debug("Getting postal code field value");

		return postalCodeInput.getAttribute("value");
	}

	/**
	 * Checks whether postal code field is displayed.
	 */
	public boolean isPostalCodeFieldDisplayed() {

		log.debug("Checking postal code field visibility");

		return isDisplayed(postalCodeInput);
	}

	// =========================================================
	// CHECKOUT ACTIONS
	// =========================================================

	/**
	 * Clicks Continue button.
	 *
	 * Expected successful navigation:
	 *
	 * checkout-step-one.html
	 *             ->
	 * checkout-step-two.html
	 */
	public void clickContinue() {

		log.info("Clicking Continue button on Checkout Step One page");

		click(continueButton);
	}

	/**
	 * Clicks Cancel button.
	 *
	 * Expected navigation:
	 *
	 * checkout-step-one.html
	 *             ->
	 * cart.html
	 */
	public void clickCancel() {

		log.info("Clicking Cancel button on Checkout Step One page");

		click(cancelButton);
	}

	/**
	 * Checks whether Continue button is displayed.
	 */
	public boolean isContinueButtonDisplayed() {

		log.debug("Checking Continue button visibility");

		return isDisplayed(continueButton);
	}

	/**
	 * Checks whether Cancel button is displayed.
	 */
	public boolean isCancelButtonDisplayed() {

		log.debug("Checking Cancel button visibility");

		return isDisplayed(cancelButton);
	}

	/**
	 * Checks whether Continue button is enabled.
	 */
	public boolean isContinueButtonEnabled() {

		log.debug("Checking Continue button enabled state");

		return continueButton.isEnabled();
	}

	/**
	 * Checks whether Cancel button is enabled.
	 */
	public boolean isCancelButtonEnabled() {

		log.debug("Checking Cancel button enabled state");

		return cancelButton.isEnabled();
	}

	// =========================================================
	// ERROR MESSAGE
	// =========================================================

	/**
	 * Returns checkout validation error message.
	 */
	public String getErrorMessage() {

		log.debug("Getting checkout validation error message");

		return getText(errorMessage);
	}

	/**
	 * Checks whether error message is displayed.
	 */
	public boolean isErrorMessageDisplayed() {

		log.debug("Checking checkout error message visibility");

		return isDisplayed(errorMessage);
	}

	/**
	 * Closes checkout validation error message.
	 */
	public void closeErrorMessage() {

		log.info("Closing checkout validation error message");

		if (isDisplayed(errorCloseButton)) {
			click(errorCloseButton);
		}
	}

	// =========================================================
	// FIELD VALIDATION
	// =========================================================

	/**
	 * Checks whether all checkout input fields are displayed.
	 */
	public boolean areAllInputFieldsDisplayed() {

		log.debug("Checking all checkout input fields");

		return isFirstNameFieldDisplayed()
				&& isLastNameFieldDisplayed()
				&& isPostalCodeFieldDisplayed();
	}

	/**
	 * Checks whether checkout page is ready for user input.
	 */
	public boolean isPageReadyForCheckout() {

		log.debug("Checking Checkout Step One page readiness");

		return isCheckoutStepOnePageDisplayed()
				&& areAllInputFieldsDisplayed()
				&& isContinueButtonDisplayed()
				&& isCancelButtonDisplayed();
	}
	
	
	public static boolean isValidFirstName(String firstName) {

	    if (firstName == null || firstName.isEmpty()) {
	        return false;
	    }

	    return firstName.matches("^[A-Za-z]+$");
	}
	
	public String getFirstNameValue() {

		String value = firstNameInput.getAttribute("value");

		log.info("Retrieved First Name value: {}", value);

		return value;
	}

	
	
	public static boolean isValidLastName(String lastName) {

	    if (lastName == null || lastName.isEmpty()) {
	        return false;
	    }

	    return lastName.matches("^[A-Za-z]+$");
	}
	
	public static boolean isValidIndianPostalCode(String postalCode) {

	    if (postalCode == null || postalCode.isEmpty()) {
	        return false;
	    }

	    return postalCode.matches("^[1-9][0-9]{5}$");
	}
	
	
	
	
	
}