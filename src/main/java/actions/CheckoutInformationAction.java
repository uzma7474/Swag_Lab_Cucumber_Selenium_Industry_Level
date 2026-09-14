package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page_object_manager.PageObjectManager;
import pages.CartPage;
import pages.CheckoutInformationPage;

public class CheckoutInformationAction {

	private static final Logger log = LoggerFactory.getLogger(CheckoutInformationAction.class);

	private final CheckoutInformationPage checkoutInformationPage;

	private final PageObjectManager pageObjectManager;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================
	

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public CheckoutInformationAction(CheckoutInformationPage checkoutInformationPage) {

		if (checkoutInformationPage == null) {
			throw new IllegalArgumentException("CartPage must not be null");
		}

		this.checkoutInformationPage = checkoutInformationPage;
		this.pageObjectManager = new PageObjectManager();
	}

	public CheckoutInformationAction(CheckoutInformationPage checkoutInformationPage, PageObjectManager pageObjectManager) {

		if (checkoutInformationPage == null) {
			throw new IllegalArgumentException("CartPage must not be null");
		}

		this.checkoutInformationPage = checkoutInformationPage;

		/*
		 * Use the PageObjectManager supplied by the caller.
		 */
		this.pageObjectManager = pageObjectManager != null ? pageObjectManager : new PageObjectManager();
	}

	public CheckoutInformationAction(PageObjectManager pageObjectManager) {

		if (pageObjectManager == null) {
			throw new IllegalArgumentException(
					"PageObjectManager must not be null");
		}

		this.pageObjectManager = pageObjectManager;

		this.checkoutInformationPage =
				pageObjectManager.getCheckoutInformationPage();

		log.debug("CheckoutInformationActions initialized");
	}

	// =========================================================
	// NAVIGATION
	// =========================================================

	public void openCheckoutInformationPage() {

		log.info("Opening Checkout Information page");

		checkoutInformationPage.open();
	}

	// =========================================================
	// CUSTOMER INFORMATION ACTIONS
	// =========================================================

	/**
	 * Enters first name.
	 */
	public void enterFirstName(String firstName) {

		log.info("Entering first name");

		checkoutInformationPage.enterFirstName(firstName);
	}

	/**
	 * Enters last name.
	 */
	public void enterLastName(String lastName) {

		log.info("Entering last name");

		checkoutInformationPage.enterLastName(lastName);
	}

	/**
	 * Enters postal code.
	 */
	public void enterPostalCode(String postalCode) {

		log.info("Entering postal code");

		checkoutInformationPage.enterPostalCode(postalCode);
	}

	/**
	 * Enters complete checkout information.
	 */
	public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {

		log.info("Entering checkout information");

		checkoutInformationPage.enterCheckoutInformation(firstName, lastName, postalCode);
	}

	// =========================================================
	// CHECKOUT ACTIONS
	// =========================================================

	/**
	 * Clicks Continue button.
	 */
	public void clickContinue() {

		log.info("Clicking Continue button");

		checkoutInformationPage.clickContinue();
	}

	/**
	 * Clicks Cancel button.
	 */
	public void clickCancel() {

		log.info("Clicking Cancel button");

		checkoutInformationPage.clickCancel();
	}

	/**
	 * Enters checkout information and clicks Continue.
	 */
	public void completeCheckoutInformation(String firstName, String lastName, String postalCode) {

		log.info("Completing Checkout Information");

		checkoutInformationPage.completeCheckoutInformation(firstName, lastName, postalCode);
	}

	// =========================================================
	// FORM ACTIONS
	// =========================================================

	/**
	 * Clears first name field.
	 */
	public void clearFirstName() {

		log.debug("Clearing first name field");

		checkoutInformationPage.clearFirstName();
	}

	/**
	 * Clears last name field.
	 */
	public void clearLastName() {

		log.debug("Clearing last name field");

		checkoutInformationPage.clearLastName();
	}

	/**
	 * Clears postal code field.
	 */
	public void clearPostalCode() {

		log.debug("Clearing postal code field");

		checkoutInformationPage.clearPostalCode();
	}

	// =========================================================
	// PAGE VALIDATION ACTIONS
	// =========================================================

	/**
	 * Checks whether Checkout Information page is displayed.
	 */
	public boolean isCheckoutInformationPageDisplayed() {

		log.debug("Checking Checkout Information page visibility");

		return checkoutInformationPage.isPageDisplayed();
	}

	/**
	 * Checks whether checkout information form is displayed.
	 */
	public boolean isCheckoutInformationFormDisplayed() {

		log.debug("Checking checkout information form visibility");

		return checkoutInformationPage.isCheckoutInfoFormDisplayed();
	}

	/**
	 * Checks whether Continue button is displayed.
	 */
	public boolean isContinueButtonDisplayed() {

		log.debug("Checking Continue button visibility");

		return checkoutInformationPage.isContinueButtonDisplayed();
	}

	/**
	 * Checks whether Cancel button is displayed.
	 */
	public boolean isCancelButtonDisplayed() {

		log.debug("Checking Cancel button visibility");

		return checkoutInformationPage.isCancelButtonDisplayed();
	}

	/**
	 * Checks whether Continue button is enabled.
	 */
	public boolean isContinueButtonEnabled() {

		log.debug("Checking Continue button enabled state");

		return checkoutInformationPage.isContinueButtonEnabled();
	}

	// =========================================================
	// ERROR HANDLING
	// =========================================================

	/**
	 * Checks whether checkout validation error is displayed.
	 */
	public boolean isErrorMessageDisplayed() {

		log.debug("Checking checkout error message visibility");

		return checkoutInformationPage.isErrorMessageDisplayed();
	}

	/**
	 * Returns checkout validation error message.
	 */
	public String getErrorMessage() {

		log.debug("Retrieving checkout error message");

		return checkoutInformationPage.getErrorMessage();
	}
}
