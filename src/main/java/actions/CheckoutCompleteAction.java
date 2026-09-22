
package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page_object_manager.PageObjectManager;
import pages.CheckoutCompletePage;
import pages.Checkout_Step_Two_Page;
import utils.WaitUtils;

public class CheckoutCompleteAction {

	private static final Logger log = LoggerFactory.getLogger(CheckoutCompleteAction.class);

	private final CheckoutCompletePage checkoutCompletePage;

	private final Checkout_Step_Two_Page checkoutStepTwoPage;

	private final PageObjectManager pageObjectManager;

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	/**
	 * Creates CheckoutCompleteAction using a new PageObjectManager.
	 *
	 * @param checkoutCompletePage Checkout Complete page object
	 */
	public CheckoutCompleteAction(CheckoutCompletePage checkoutCompletePage, PageObjectManager pageObjectManager) {

		if (checkoutCompletePage == null) {
			log.error("CheckoutCompletePage is null");
			throw new IllegalArgumentException("CheckoutCompletePage must not be null");
		}

		if (pageObjectManager == null) {
			log.error("PageObjectManager is null");
			throw new IllegalArgumentException("PageObjectManager must not be null");
		}

		this.checkoutCompletePage = checkoutCompletePage;
		this.pageObjectManager = pageObjectManager;

		this.checkoutStepTwoPage = pageObjectManager.getCheckoutStepTwoPage();

		if (this.checkoutStepTwoPage == null) {
			log.error("Checkout_Step_Two_Page is null");
			throw new IllegalStateException("Checkout_Step_Two_Page is not initialized");
		}

		log.info("CheckoutCompleteAction initialized successfully");
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
	
	
	public void navigateTo(String url) {
		checkoutCompletePage.navigateTo(url);
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
	public void clickBackHome_() {

		log.info("Clicking Back Home button");

		checkoutCompletePage.clickBackHome();

		log.info("Back Home button clicked successfully");
	}

	/**
	 * Clicks the Back Home button.
	 */
	public void clickBackHome() {

		try {

			log.info("Clicking Back Home button");

			checkoutCompletePage.clickBackHome();

			log.info("Back Home button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click Back Home button", e);

			throw e;
		}
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

	// ============================================================
	// COMPLETE CHECKOUT
	// ============================================================

	/**
	 * Clicks Finish on Checkout Step Two and waits for the Checkout Complete page
	 * to be displayed.
	 */
	public void completeCheckout() {

		log.info("Starting final checkout completion from Checkout Step Two");

		try {

			// Verify that we are on Checkout Step Two
			String currentUrl = checkoutStepTwoPage.getCurrentUrl();

			log.info("Current URL before clicking Finish: {}", currentUrl);

			if (!currentUrl.contains("/checkout-step-two.html")) {

				log.error("User is not on Checkout Step Two. Current URL: {}", currentUrl);

				throw new IllegalStateException("Cannot complete checkout because user is not "
						+ "on Checkout Step Two. Current URL: " + currentUrl);
			}

			// Click Finish
			log.info("Clicking Finish button");

			checkoutStepTwoPage.clickFinish();

			// Wait for Checkout Complete page
			WaitUtils.waitForPageLoad();

			log.info("Finish button clicked. Waiting for Checkout Complete page");

			// Verify URL
			String completeUrl = checkoutCompletePage.getCurrentUrl();

			log.info("URL after clicking Finish: {}", completeUrl);

			if (!completeUrl.contains("/checkout-complete.html")) {

				log.error("Checkout Complete URL was not reached. " + "Current URL: {}", completeUrl);

				throw new IllegalStateException(
						"Checkout did not navigate to " + "/checkout-complete.html. Current URL: " + completeUrl);
			}

			// Verify page is ready
			checkoutCompletePage.verifyCheckoutCompletePageReady();

			log.info("Order completed successfully. " + "Checkout Complete page is displayed.");

		} catch (Exception e) {

			log.error("Failed to complete checkout successfully", e);

			throw new RuntimeException("Failed to complete checkout", e);
		}
	}

	// ============================================================
	// BACK HOME
	// ============================================================

	/**
	 * Clicks Back Home from Checkout Complete page.
	 */

	// ============================================================
	// PAGE VERIFICATION
	// ============================================================

	/**
	 * Verifies that Checkout Complete page is displayed.
	 */
	public boolean isCheckoutCompletePageDisplayed() {

		try {

			boolean displayed = checkoutCompletePage.isCheckoutCompletePageReady();

			log.info("Checkout Complete page displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.error("Unable to determine Checkout Complete page state", e);

			return false;
		}
	}

}
