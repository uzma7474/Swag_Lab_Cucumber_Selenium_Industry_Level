package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import driver.DriverManager;
import page_object_manager.PageObjectManager;
import pages.CartPage;
import pages.Checkout_Step_One_Page;
import pages.InventoryPage;
import utils.WaitUtils;

/**
 * Business actions for SauceDemo Checkout Step One functionality.
 *
 * Page:
 * checkout-step-one.html
 *
 * Responsibilities:
 * - Open Checkout Step One page
 * - Enter customer checkout information
 * - Continue to Checkout Step Two
 * - Cancel checkout and return to Cart
 * - Handle checkout validation errors
 */
public class Checkout_Step_One_Action {

	private static final Logger log = LoggerFactory.getLogger(Checkout_Step_One_Action.class);

	private final Checkout_Step_One_Page checkoutStepOnePage;

	private final PageObjectManager pageObjectManager;

	private final CartPage cartPage;

	private final InventoryPage inventoryPage;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	/**
	 * Creates Checkout_Step_One_Action using a new PageObjectManager.
	 *
	 * @param checkoutStepOnePage Checkout Step One page object
	 */
	public Checkout_Step_One_Action(Checkout_Step_One_Page checkoutStepOnePage) {

		if (checkoutStepOnePage == null) {
			throw new IllegalArgumentException(
					"Checkout_Step_One_Page must not be null");
		}

		this.checkoutStepOnePage = checkoutStepOnePage;

		this.pageObjectManager = new PageObjectManager();

		this.cartPage = this.pageObjectManager.getCartPage();

		this.inventoryPage = this.pageObjectManager.getInventoryPage();

		log.debug("Checkout_Step_One_Action initialized");
	}

	/**
	 * Creates Checkout_Step_One_Action using the supplied PageObjectManager.
	 *
	 * @param checkoutStepOnePage Checkout Step One page object
	 * @param pageObjectManager Page Object Manager
	 */
	public Checkout_Step_One_Action(
			Checkout_Step_One_Page checkoutStepOnePage,
			PageObjectManager pageObjectManager) {

		if (checkoutStepOnePage == null) {
			throw new IllegalArgumentException(
					"Checkout_Step_One_Page must not be null");
		}

		this.checkoutStepOnePage = checkoutStepOnePage;

		/*
		 * Use the PageObjectManager supplied by the caller.
		 * If null is supplied, create a new PageObjectManager.
		 */
		this.pageObjectManager = pageObjectManager != null
				? pageObjectManager
				: new PageObjectManager();

		this.cartPage = this.pageObjectManager.getCartPage();

		this.inventoryPage = this.pageObjectManager.getInventoryPage();

		log.debug("Checkout_Step_One_Action initialized with PageObjectManager");
	}

	// =========================================================
	// NAVIGATION
	// =========================================================

	/**
	 * Opens SauceDemo Checkout Step One page directly.
	 */
	public void openCheckoutStepOnePage() {

		log.info("Opening SauceDemo Checkout Step One page");

		checkoutStepOnePage.open();
	}

	
	public boolean isValidPostalCode(String postalCode) {

	    if (postalCode == null || postalCode.trim().isEmpty()) {
	        log.warn("Postal code is empty");
	        return false;
	    }

	    boolean valid = postalCode.matches("^[1-9][0-9]{5}$");

	    log.info("Postal code '{}' valid: {}", postalCode, valid);

	    return valid;
	}
	
	
	/**
	 * Opens Checkout Step One page from the Cart page.
	 *
	 * Expected flow:
	 *
	 * Cart -> Checkout -> Checkout Step One
	 */
	public void proceedToCheckoutFromCart() {

		log.info("Proceeding from Cart to Checkout Step One page");

		cartPage.clickCheckout();
	}

	// =========================================================
	// CUSTOMER INFORMATION
	// =========================================================

	/**
	 * Enters customer's first name.
	 *
	 * @param firstName customer first name
	 */
	public void enterFirstName(String firstName) {

		log.info("Entering first name during checkout");

		checkoutStepOnePage.enterFirstName(firstName);
	}

	
//	public String getFirstName() {
//		return checkoutStepOnePage.getFirstNameValue();
//	}
//	
//	public String getLastName() {
//		return checkoutStepOnePage.getLastName();
//	}
	
	public  boolean isValidFirstName() {
		
		String firstName = checkoutStepOnePage.getFirstName();
				
				
	    if (firstName == null || firstName.isEmpty()) {
	        return false;
	    }

	    return firstName.matches("^[A-Za-z]+$");
	}
	
	/**
	 * Enters customer's last name.
	 *
	 * @param lastName customer last name
	 */
	public void enterLastName(String lastName) {

		log.info("Entering last name during checkout");

		checkoutStepOnePage.enterLastName(lastName);
	}

//	public String getPostalCode() {
//		return checkoutStepOnePage.getPostalCode();
//	}
//	
	
	
	/**
	 * Enters customer's ZIP / Postal Code.
	 *
	 * @param postalCode ZIP / Postal Code
	 */
	public void enterPostalCode(String postalCode) {

		log.info("Entering postal code during checkout");

		checkoutStepOnePage.enterPostalCode(postalCode);
	}

	/**
	 * Enters complete checkout information.
	 *
	 * @param firstName first name
	 * @param lastName last name
	 * @param postalCode ZIP / Postal Code
	 */
	public void enterCheckoutInformation(
			String firstName,
			String lastName,
			String postalCode) {

		log.info("Entering complete checkout information");

		checkoutStepOnePage.enterFirstName(firstName);
		checkoutStepOnePage.enterLastName(lastName);
		checkoutStepOnePage.enterPostalCode(postalCode);
	}

	// =========================================================
	// CHECKOUT ACTIONS
	// =========================================================

	/**
	 * Clicks Continue button.
	 *
	 * Expected navigation:
	 *
	 * checkout-step-one.html
	 *          ->
	 * checkout-step-two.html
	 */
	public void clickContinue() {

		log.info("Clicking Continue on Checkout Step One page");

		checkoutStepOnePage.clickContinue();
	}

	/**
	 * Completes checkout information and continues to
	 * Checkout Step Two.
	 *
	 * @param firstName first name
	 * @param lastName last name
	 * @param postalCode ZIP / Postal Code
	 */
	public void continueWithCheckoutInformation(
			String firstName,
			String lastName,
			String postalCode) {

		log.info("Entering checkout information and continuing");

		enterCheckoutInformation(firstName, lastName, postalCode);

		clickContinue();
	}

	/**
	 * Clicks Cancel button.
	 *
	 * Expected navigation:
	 *
	 * checkout-step-one.html
	 *          ->
	 * cart.html
	 */
	public void clickCancel() {

		log.info("Clicking Cancel on Checkout Step One page");

		checkoutStepOnePage.clickCancel();
	}

	/**
	 * Cancels checkout and returns to Cart page.
	 */
	public void cancelCheckout() {

		log.info("Cancelling checkout and returning to Cart");

		checkoutStepOnePage.clickCancel();
	}

	// =========================================================
	// FIELD OPERATIONS
	// =========================================================

	/**
	 * Clears First Name field.
	 */
	public void clearFirstName() {

		log.debug("Clearing First Name field");

		checkoutStepOnePage.clearFirstName();
	}

	/**
	 * Clears Last Name field.
	 */
	public void clearLastName() {

		log.debug("Clearing Last Name field");

		checkoutStepOnePage.clearLastName();
	}

	/**
	 * Clears Postal Code field.
	 */
	public void clearPostalCode() {

		log.debug("Clearing Postal Code field");

		checkoutStepOnePage.clearPostalCode();
	}

	/**
	 * Clears all checkout information fields.
	 */
	public void clearCheckoutInformation() {

		log.debug("Clearing all checkout information fields");

		checkoutStepOnePage.clearFirstName();
		checkoutStepOnePage.clearLastName();
		checkoutStepOnePage.clearPostalCode();
	}

	// =========================================================
	// ERROR HANDLING
	// =========================================================

	/**
	 * Closes checkout validation error message.
	 */
	public void closeErrorMessage() {

		log.info("Closing checkout validation error message");

		checkoutStepOnePage.closeErrorMessage();
	}

	// =========================================================
	// COMBINED BUSINESS ACTIONS
	// =========================================================

	/**
	 * Attempts to continue checkout without entering
	 * any customer information.
	 *
	 * This is useful for negative BDD scenarios.
	 */
	public void continueWithoutCheckoutInformation() {

		log.info("Attempting to continue checkout without information");

		checkoutStepOnePage.clickContinue();
	}

	/**
	 * Attempts checkout with only first name.
	 *
	 * @param firstName first name
	 */
	public void continueWithOnlyFirstName(String firstName) {

		log.info("Attempting checkout with only first name");

		checkoutStepOnePage.enterFirstName(firstName);
		checkoutStepOnePage.clickContinue();
	}

	/**
	 * Attempts checkout with only last name.
	 *
	 * @param lastName last name
	 */
	public void continueWithOnlyLastName(String lastName) {

		log.info("Attempting checkout with only last name");

		checkoutStepOnePage.enterLastName(lastName);
		checkoutStepOnePage.clickContinue();
	}

	/**
	 * Attempts checkout with only postal code.
	 *
	 * @param postalCode ZIP / Postal Code
	 */
	public void continueWithOnlyPostalCode(String postalCode) {

		log.info("Attempting checkout with only postal code");

		checkoutStepOnePage.enterPostalCode(postalCode);
		checkoutStepOnePage.clickContinue();
	}

	/**
	 * Attempts checkout with first name and last name
	 * but without postal code.
	 *
	 * @param firstName first name
	 * @param lastName last name
	 */
	public void continueWithoutPostalCode(
			String firstName,
			String lastName) {

		log.info("Attempting checkout without postal code");

		checkoutStepOnePage.enterFirstName(firstName);
		checkoutStepOnePage.enterLastName(lastName);
		checkoutStepOnePage.clickContinue();
	}

	/**
	 * Attempts checkout with first name and postal code
	 * but without last name.
	 *
	 * @param firstName first name
	 * @param postalCode ZIP / Postal Code
	 */
	public void continueWithoutLastName(
			String firstName,
			String postalCode) {

		log.info("Attempting checkout without last name");

		checkoutStepOnePage.enterFirstName(firstName);
		checkoutStepOnePage.enterPostalCode(postalCode);
		checkoutStepOnePage.clickContinue();
	}

	/**
	 * Attempts checkout with last name and postal code
	 * but without first name.
	 *
	 * @param lastName last name
	 * @param postalCode ZIP / Postal Code
	 */
	public void continueWithoutFirstName(
			String lastName,
			String postalCode) {

		log.info("Attempting checkout without first name");

		checkoutStepOnePage.enterLastName(lastName);
		checkoutStepOnePage.enterPostalCode(postalCode);
		checkoutStepOnePage.clickContinue();
	}
	
	/** * Proceeds from Checkout Step One to Checkout Step Two. 
	 * */ 
	public void proceedToCheckoutStepTwo() { 
		log.info("Proceeding to Checkout Step Two"); 
		try { 
			// Click Continue on Checkout Step One 
			checkoutStepOnePage.clickContinue(); 
			
			// Wait until Checkout Step Two URL is loaded 
			WaitUtils.waitForUrlContains( DriverManager.getDriver(), "checkout-step-two.html" ); 
			log.info( "Successfully navigated to Checkout Step Two. Current URL: {}", 
					DriverManager.getDriver().getCurrentUrl() ); 
		} catch (Exception e) { 
				log.error( "Failed to proceed to Checkout Step Two", e ); 
				throw e; 
				
		} 
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