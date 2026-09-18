package actions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import page_object_manager.PageObjectManager;
import pages.Checkout_Step_Two_Page;

public class Checkout_Step_Two_Action {

	private static final Logger log = LoggerFactory.getLogger(Checkout_Step_Two_Action.class);

	private final Checkout_Step_Two_Page checkoutStepTwoPage;

	private final PageObjectManager pageObjectManager;
	
	

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	/**
	 * Creates Checkout_Step_Two_Action using a new PageObjectManager.
	 *
	 * @param checkoutStepTwoPage Checkout Step Two page object
	 */
	public Checkout_Step_Two_Action(Checkout_Step_Two_Page checkoutStepTwoPage) {

		if (checkoutStepTwoPage == null) {

			throw new IllegalArgumentException("Checkout_Step_Two_Page must not be null");
		}

		this.checkoutStepTwoPage = checkoutStepTwoPage;

		this.pageObjectManager = new PageObjectManager();

		log.debug("Checkout_Step_Two_Action initialized");
	}
	
	
	/**
	 * Completes the checkout process by clicking the Finish button.
	 */
	public void completeCheckout() {

	    log.info("Completing checkout from Checkout Step Two page");

	    checkoutStepTwoPage.clickFinish();

	    log.info("Finish button clicked successfully. Checkout completion initiated");
	}
	

	/**
	 * Creates Checkout_Step_Two_Action using supplied PageObjectManager.
	 */
	public Checkout_Step_Two_Action(Checkout_Step_Two_Page checkoutStepTwoPage, PageObjectManager pageObjectManager) {

		if (checkoutStepTwoPage == null) {

			throw new IllegalArgumentException("Checkout_Step_Two_Page must not be null");
		}

		this.checkoutStepTwoPage = checkoutStepTwoPage;

		this.pageObjectManager = pageObjectManager != null ? pageObjectManager : new PageObjectManager();

		log.debug("Checkout_Step_Two_Action initialized with PageObjectManager");
	}

	// =========================================================
	// NAVIGATION
	// =========================================================

	/**
	 * Opens Checkout Step Two page directly.
	 */
	public void openCheckoutStepTwoPage() {

		log.info("Opening SauceDemo Checkout Step Two page");

		checkoutStepTwoPage.open();
	}
	
	public void refreshPage() {

	    log.info("Refreshing Checkout Step Two page");

	    checkoutStepTwoPage.refreshPage();

	    log.info("Checkout Step Two page refreshed successfully");
	}

	public void navigateBack() {

	    log.info("Navigating back from Checkout Step Two page");

	    checkoutStepTwoPage.navigateBack();

	    log.info("Successfully navigated back from Checkout Step Two page");
	}

	// =========================================================
	// PAGE OPERATIONS
	// =========================================================

	/**
	 * Gets Checkout Step Two page title.
	 */
	public String getPageTitle() {

		log.debug("Getting Checkout Step Two page title");

		return checkoutStepTwoPage.getPageTitle();
	}

	public void navigateForward() {

	    log.info("Navigating forward from Checkout Step Two page");

	    checkoutStepTwoPage.navigateForward();

	    log.info("Successfully navigated forward");
	}
	
	
	
	
	/**
	 * Checks whether Checkout Step Two page is displayed.
	 */
	public boolean isCheckoutStepTwoPageDisplayed() {

		log.debug("Checking Checkout Step Two page");

		return checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed();
	}

	/**
	 * Checks whether Checkout Overview is displayed.
	 */
	public boolean isCheckoutOverviewDisplayed() {

		log.debug("Checking Checkout Overview");

		return checkoutStepTwoPage.isCheckoutOverviewDisplayed();
	}

	// =========================================================
	// PRODUCT OPERATIONS
	// =========================================================

	/**
	 * Returns number of products in Checkout Overview.
	 */
	public int getCartItemCount() {

		log.info("Getting Checkout Overview item count");

		return checkoutStepTwoPage.getCartItemCount();
	}

	/**
	 * Returns all product names.
	 */
	public List<String> getProductNames() {

		log.debug("Getting product names from Checkout Overview");

		return checkoutStepTwoPage.getProductNames();
	}

	/**
	 * Returns all product descriptions.
	 */
	public List<String> getProductDescriptions() {

		log.debug("Getting product descriptions");

		return checkoutStepTwoPage.getProductDescriptions();
	}

	/**
	 * Returns all product prices.
	 */
	public List<String> getProductPrices() {

		log.debug("Getting product prices");

		return checkoutStepTwoPage.getProductPrices();
	}

	/**
	 * Gets product name by index.
	 */
	public String getProductName(int index) {

		log.debug("Getting product name at index: {}", index);

		return checkoutStepTwoPage.getProductName(index);
	}

	/**
	 * Gets product price by index.
	 */
	public String getProductPrice(int index) {

		log.debug("Getting product price at index: {}", index);

		return checkoutStepTwoPage.getProductPrice(index);
	}

	/**
	 * Checks whether product is displayed.
	 */
	public boolean isProductDisplayed(String productName) {

		log.info("Checking whether product is displayed: {}", productName);

		return checkoutStepTwoPage.isProductDisplayed(productName);
	}

	
	
	
	
	// =========================================================
	// PAYMENT / SHIPPING
	// =========================================================

	/**
	 * Checks whether Payment Information is displayed.
	 */
	public boolean isPaymentInformationDisplayed() {

		log.debug("Checking Payment Information");

		return checkoutStepTwoPage.isPaymentInformationDisplayed();
	}

	/**
	 * Gets Payment Information.
	 */
	public String getPaymentInformation() {

		log.debug("Getting Payment Information");

		return checkoutStepTwoPage.getPaymentInformation();
	}

	/**
	 * Checks whether Shipping Information is displayed.
	 */
	public boolean isShippingInformationDisplayed() {

		log.debug("Checking Shipping Information");

		return checkoutStepTwoPage.isShippingInformationDisplayed();
	}

	/**
	 * Gets Shipping Information.
	 */
	public String getShippingInformation() {

		log.debug("Getting Shipping Information");

		return checkoutStepTwoPage.getShippingInformation();
	}

	// =========================================================
	// PRICE OPERATIONS
	// =========================================================

	/**
	 * Gets subtotal text.
	 */
	public String getSubtotal() {

		log.debug("Getting subtotal");

		return checkoutStepTwoPage.getSubtotal();
	}

	/**
	 * Gets tax text.
	 */
	public String getTax() {

		log.debug("Getting tax");

		return checkoutStepTwoPage.getTax();
	}

	/**
	 * Gets total text.
	 */
	public String getTotal() {

		log.debug("Getting total");

		return checkoutStepTwoPage.getTotal();
	}

	// =========================================================
	// CHECKOUT COMPLETION
	// =========================================================

	/**
	 * Clicks Finish button.
	 */
	public void clickFinish() {

		log.info("Clicking Finish on Checkout Step Two");

		checkoutStepTwoPage.clickFinish();
	}

	/**
	 * Completes checkout.
	 */
	public void finishCheckout() {

		log.info("Completing SauceDemo checkout");

		checkoutStepTwoPage.clickFinish();
	}

	/**
	 * Clicks Cancel button.
	 */
	public void clickCancel() {

		log.info("Clicking Cancel on Checkout Step Two");

		checkoutStepTwoPage.clickCancel();
	}

	/**
	 * Cancels checkout.
	 */
	public void cancelCheckout() {

		log.info("Cancelling Checkout Step Two");

		checkoutStepTwoPage.clickCancel();
	}

	// =========================================================
	// BUTTON OPERATIONS
	// =========================================================

	public boolean isFinishButtonDisplayed() {

		return checkoutStepTwoPage.isFinishButtonDisplayed();
	}

	public boolean isCancelButtonDisplayed() {

		return checkoutStepTwoPage.isCancelButtonDisplayed();
	}

	public boolean isFinishButtonEnabled() {

		return checkoutStepTwoPage.isFinishButtonEnabled();
	}

	public boolean isCancelButtonEnabled() {

		return checkoutStepTwoPage.isCancelButtonEnabled();
	}

	// =========================================================
	// PAGE READINESS
	// =========================================================

	/**
	 * Checks whether Checkout Step Two is completely ready.
	 */
	public boolean isPageReadyForCheckoutCompletion() {

		log.debug("Checking Checkout Step Two page readiness");

		return checkoutStepTwoPage.isPageReadyForCheckoutCompletion();
	}
}