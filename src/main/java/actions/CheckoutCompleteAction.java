
package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page_object_manager.PageObjectManager;
import pages.CheckoutCompletePage;

public class CheckoutCompleteAction {

	private static final Logger log = LoggerFactory.getLogger(CheckoutCompleteAction.class);

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
	public CheckoutCompleteAction(CheckoutCompletePage checkoutCompletePage) {

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
	 * Navigates back to the previous browser page.
	 */
	public void navigateBack() {

		log.info("Navigating back from Checkout Complete page");

		checkoutCompletePage.navigateBack();

		log.info("Successfully navigated back from Checkout Complete page");
	}

	/**
	 * Navigates forward to the next browser page.
	 */
	public void navigateForward() {

		log.info("Navigating forward from Checkout Complete page");

		checkoutCompletePage.navigateForward();

		log.info("Successfully navigated forward");
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
	 *
	 * After a successful order, SauceDemo provides the Back Home button to return
	 * the user to the Products page.
	 */
	public void clickBackHome() {

		log.info("Clicking Back Home button");

		checkoutCompletePage.clickBackHome();

		log.info("Back Home button clicked successfully");
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
