
package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page_object_manager.PageObjectManager;
import pages.CheckoutCompletePage;

public class CheckoutCompleteAction_Not_Using {

	private static final Logger log = LoggerFactory.getLogger(CheckoutCompleteAction_Not_Using.class);

	private final CheckoutCompletePage checkoutCompletePage;

	private final PageObjectManager pageObjectManager;

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	/**
	 * Creates CheckoutCompleteAction using a new PageObjectManager.
	 *
	 * @param checkoutCompletePage Checkout Complete page object
	 */
	public CheckoutCompleteAction_Not_Using(CheckoutCompletePage checkoutCompletePage) {

		if (checkoutCompletePage == null) {
			throw new IllegalArgumentException("CheckoutCompletePage must not be null");
		}

		this.checkoutCompletePage = checkoutCompletePage;

		this.pageObjectManager = new PageObjectManager();

		log.debug("CheckoutCompleteAction initialized");
	}

	// =========================================================
	// PAGE NAVIGATION ACTIONS
	// =========================================================

	/**
	 * Navigates back to the previous page.
	 */
	public void navigateBack() {

		log.info("Navigating back from Checkout Complete page");

		checkoutCompletePage.navigateBack();

		log.info("Successfully navigated back from Checkout Complete page");
	}

	/**
	 * Navigates forward to Checkout Complete page.
	 */
	public void navigateForward() {

		log.info("Navigating forward to Checkout Complete page");

		checkoutCompletePage.navigateForward();

		log.info("Successfully navigated forward to Checkout Complete page");
	}

	/**
	 * Refreshes the Checkout Complete page.
	 */
	public void refreshPage() {

		log.info("Refreshing Checkout Complete page");

		checkoutCompletePage.refreshPage();

		log.info("Checkout Complete page refreshed successfully");
	}

	// =========================================================
	// BACK HOME ACTION
	// =========================================================

	/**
	 * Clicks the Back Home button.
	 */
	public void clickBackHome() {

		log.info("Clicking Back Home button");

		checkoutCompletePage.clickBackHome();

		log.info("Back Home button clicked successfully");
	}

	// =========================================================
	// ORDER COMPLETION ACTION
	// =========================================================

	/**
	 * Completes and validates the order completion flow.
	 *
	 * This method verifies that the Checkout Complete page is displayed and that
	 * the order was successfully completed.
	 */
	public void completeOrder() {

		log.info("Verifying successful order completion");

		checkoutCompletePage.verifyOrderCompletedSuccessfully();

		log.info("Order completion verified successfully");
	}

	// =========================================================
	// PAGE INITIALIZATION
	// =========================================================

	/**
	 * Returns the Checkout Complete page object.
	 *
	 * @return CheckoutCompletePage
	 */
	public CheckoutCompletePage getCheckoutCompletePage() {

		log.debug("Returning CheckoutCompletePage");

		return checkoutCompletePage;
	}

	/**
	 * Returns the PageObjectManager.
	 *
	 * @return PageObjectManager
	 */
	public PageObjectManager getPageObjectManager() {

		log.debug("Returning PageObjectManager");

		return pageObjectManager;
	}
}
