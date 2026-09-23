package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BasePage;
import config.EnvironmentManager;

public class CheckoutInformationPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(CheckoutInformationPage.class);

	// =========================================================
	// PAGE LOCATORS
	// =========================================================

	@FindBy(css = ".title")
	private WebElement pageTitle;

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

	@FindBy(css = ".checkout_info")
	private WebElement checkoutInfoContainer;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public CheckoutInformationPage() {

		super();

		PageFactory.initElements(driver, this);

		log.debug("CheckoutInformationPage initialized");
	}

	// =========================================================
	// NAVIGATION
	// =========================================================

	/**
	 * Opens Checkout Information page directly.
	 *
	 * Normally this page should be reached through:
	 *
	 * Login -> Inventory -> Cart -> Checkout
	 *
	 * Direct navigation may redirect if the required checkout state
	 * has not been established.
	 */
	public void open() {

		log.info("Navigating to SauceDemo Checkout Information page");

		navigateTo(EnvironmentManager.getBaseUrl() + "/checkout-step-one.html");
	}

	// =========================================================
	// PAGE VERIFICATION
	// =========================================================

	/**
	 * Returns the Checkout Information page title.
	 */
	public String getPageTitle() {

		log.debug("Getting Checkout Information page title");

		return getText(pageTitle);
	}

	/**
	 * Checks whether Checkout Information page is displayed.
	 */
	public boolean isPageDisplayed() {

		log.debug("Checking whether Checkout Information page is displayed");

		return isDisplayed(pageTitle);
	}

	/**
	 * Checks whether checkout information form is displayed.
	 */
	public boolean isCheckoutInfoFormDisplayed() {

		log.debug("Checking whether checkout information form is displayed");

		return isDisplayed(checkoutInfoContainer);
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
	 * Checks whether first name field is displayed.
	 */
	public boolean isFirstNameDisplayed() {

		log.debug("Checking whether first name field is displayed");

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
	 * Checks whether last name field is displayed.
	 */
	public boolean isLastNameDisplayed() {

		log.debug("Checking whether last name field is displayed");

		return isDisplayed(lastNameInput);
	}

	// =========================================================
	// POSTAL CODE
	// =========================================================

	/**
	 * Enters postal / ZIP code.
	 */
	public void enterPostalCode(String postalCode) {

		log.debug("Entering postal code");

		type(postalCodeInput, postalCode);
	}

	/**
	 * Clears postal code field.
	 */
	public void clearPostalCode() {

		log.debug("Clearing postal code field");

		postalCodeInput.clear();
	}

	/**
	 * Checks whether postal code field is displayed.
	 */
	public boolean isPostalCodeDisplayed() {

		log.debug("Checking whether postal code field is displayed");

		return isDisplayed(postalCodeInput);
	}

	// =========================================================
	// CHECKOUT ACTIONS
	// =========================================================

	/**
	 * Clicks Continue button.
	 */
	public void clickContinue() {

		log.info("Clicking Continue button on Checkout Information page");

		click(continueButton);
	}

	/**
	 * Clicks Cancel button.
	 */
	public void clickCancel() {

		log.info("Clicking Cancel button on Checkout Information page");

		click(cancelButton);
	}

	// =========================================================
	// BUTTON VERIFICATION
	// =========================================================

	/**
	 * Checks whether Continue button is displayed.
	 */
	public boolean isContinueButtonDisplayed() {

		log.debug("Checking whether Continue button is displayed");

		return isDisplayed(continueButton);
	}

	/**
	 * Checks whether Cancel button is displayed.
	 */
	public boolean isCancelButtonDisplayed() {

		log.debug("Checking whether Cancel button is displayed");

		return isDisplayed(cancelButton);
	}

	/**
	 * Checks whether Continue button is enabled.
	 */
	public boolean isContinueButtonEnabled() {

		log.debug("Checking whether Continue button is enabled");

		return continueButton.isEnabled();
	}

	/**
	 * Checks whether Cancel button is enabled.
	 */
	public boolean isCancelButtonEnabled() {

		log.debug("Checking whether Cancel button is enabled");

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
	 * Checks whether checkout validation error is displayed.
	 */
	public boolean isErrorMessageDisplayed() {

		log.debug("Checking whether checkout validation error is displayed");

		return isDisplayed(errorMessage);
	}

	// =========================================================
	// FORM OPERATIONS
	// =========================================================

	/**
	 * Enters complete checkout information.
	 *
	 * @param firstName  customer's first name
	 * @param lastName   customer's last name
	 * @param postalCode customer's postal / ZIP code
	 */
	public void enterCheckoutInformation(
			String firstName,
			String lastName,
			String postalCode) {

		log.info("Entering checkout information");

		enterFirstName(firstName);
		enterLastName(lastName);
		enterPostalCode(postalCode);
	}

	/**
	 * Enters checkout information and continues.
	 *
	 * @param firstName  customer's first name
	 * @param lastName   customer's last name
	 * @param postalCode customer's postal / ZIP code
	 */
	public void completeCheckoutInformation(
			String firstName,
			String lastName,
			String postalCode) {

		log.info("Completing Checkout Information form");

		enterCheckoutInformation(firstName, lastName, postalCode);
		clickContinue();
	}
	
	
	public boolean isFirstNameFieldDisplayed() {

	    try {
	        return firstNameInput.isDisplayed();
	    } catch (NoSuchElementException | StaleElementReferenceException e) {
	        log.info("First Name field is not displayed");
	        return false;
	    }
	}

	public boolean isLastNameFieldDisplayed() {

	    try {
	        return lastNameInput.isDisplayed();
	    } catch (NoSuchElementException | StaleElementReferenceException e) {
	        log.info("Last Name field is not displayed");
	        return false;
	    }
	}

	public boolean isPostalCodeFieldDisplayed() {

	    try {
	        return postalCodeInput.isDisplayed();
	    } catch (NoSuchElementException | StaleElementReferenceException e) {
	        log.info("Postal Code field is not displayed");
	        return false;
	    }
	}
	
	
	
}