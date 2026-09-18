
package assertions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import context.ScenarioContext;
import pages.CheckoutCompletePage;

public class CheckoutCompleteAssertions {

	private static final Logger log = LoggerFactory.getLogger(CheckoutCompleteAssertions.class);

	private final CheckoutCompletePage checkoutCompletePage;

	private final ScenarioContext scenarioContext;

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	/**
	 * Creates CheckoutCompleteAssertions using the supplied CheckoutCompletePage
	 * and ScenarioContext.
	 *
	 * @param checkoutCompletePage Checkout Complete page object
	 * @param scenarioContext      Scenario context
	 */
	public CheckoutCompleteAssertions(CheckoutCompletePage checkoutCompletePage, ScenarioContext scenarioContext) {

		if (checkoutCompletePage == null) {
			throw new IllegalArgumentException("CheckoutCompletePage must not be null");
		}

		if (scenarioContext == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.checkoutCompletePage = checkoutCompletePage;
		this.scenarioContext = scenarioContext;

		log.debug("CheckoutCompleteAssertions initialized");
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

	/**
	 * Verifies that Checkout Complete page is displayed.
	 */
	public void verifyCheckoutCompletePageReady() {

		log.info("Verifying Checkout Complete page is ready");

		Assert.assertTrue(checkoutCompletePage.isCheckoutCompletePageReady(),
				"Checkout Complete page should be displayed");

		log.info("Checkout Complete page is displayed successfully");
	}

	/**
	 * Verifies that Checkout Complete page is NOT displayed.
	 */
	public void verifyCheckoutCompletePageNotDisplayed() {

		log.info("Verifying Checkout Complete page is not displayed");

		Assert.assertFalse(checkoutCompletePage.isCheckoutCompletePageReady(),
				"Checkout Complete page should not be displayed");

		log.info("Checkout Complete page is not displayed");
	}

	// =========================================================
	// URL VALIDATION
	// =========================================================

	/**
	 * Verifies current URL contains expected value.
	 *
	 * @param expectedUrlPart expected URL text
	 */
	public void verifyCurrentUrlContains(String expectedUrlPart) {

		log.info("Verifying current URL contains: {}", expectedUrlPart);

		Assert.assertNotNull(expectedUrlPart, "Expected URL part must not be null");

		Assert.assertFalse(expectedUrlPart.trim().isEmpty(), "Expected URL part must not be empty");

		String currentUrl = getCurrentUrl();

		Assert.assertTrue(currentUrl.contains(expectedUrlPart),
				"Expected URL to contain '" + expectedUrlPart + "' but actual URL was '" + currentUrl + "'");

		log.info("Current URL validation passed");
	}

	/**
	 * Returns current browser URL.
	 */
	private String getCurrentUrl() { 
		return checkoutCompletePage.getCurrentUrl(); 
		
	}

	/**
	 * Verifies that user is on Checkout Complete URL.
	 */
	public void verifyCheckoutCompleteUrl() {

		log.info("Verifying Checkout Complete URL");

		Assert.assertTrue(checkoutCompletePage.isCheckoutCompleteUrl(),
				"Current URL should contain checkout-complete.html");

		log.info("Checkout Complete URL verified successfully");
	}

	// =========================================================
	// PAGE TITLE
	// =========================================================

	/**
	 * Verifies Checkout Complete page title.
	 */
	public void verifyPageTitle(String expectedTitle) {

		log.info("Verifying page title: {}", expectedTitle);

		Assert.assertNotNull(expectedTitle, "Expected page title must not be null");

		String actualTitle = checkoutCompletePage.getPageTitle();

		Assert.assertEquals(actualTitle, expectedTitle, "Incorrect Checkout Complete page title");

		log.info("Page title verified successfully: {}", actualTitle);
	}

	/**
	 * Verifies that page title is displayed.
	 */
	public void verifyPageTitleDisplayed() {

		log.info("Verifying page title is displayed");

		Assert.assertTrue(checkoutCompletePage.isPageTitleDisplayed(),
				"Checkout Complete page title should be displayed");

		log.info("Page title is displayed");
	}

	// =========================================================
	// CONFIRMATION MESSAGE
	// =========================================================

	/**
	 * Verifies order confirmation message.
	 *
	 * @param expectedMessage expected confirmation message
	 */
	public void verifyConfirmationMessage(String expectedMessage) {

		log.info("Verifying order confirmation message");

		Assert.assertNotNull(expectedMessage, "Expected confirmation message must not be null");

		String actualMessage = checkoutCompletePage.getConfirmationMessage();

		Assert.assertEquals(actualMessage, expectedMessage, "Incorrect order confirmation message");

		log.info("Order confirmation message verified: {}", actualMessage);
	}

	/**
	 * Verifies confirmation message is displayed.
	 */
	public void verifyConfirmationMessageDisplayed() {

		log.info("Verifying confirmation message is displayed");

		Assert.assertTrue(checkoutCompletePage.isConfirmationMessageDisplayed(),
				"Order confirmation message should be displayed");

		log.info("Confirmation message is displayed");
	}

	/**
	 * Verifies confirmation message is not displayed.
	 */
	public void verifyConfirmationMessageNotDisplayed() {

		log.info("Verifying confirmation message is not displayed");

		Assert.assertFalse(checkoutCompletePage.isConfirmationMessageDisplayed(),
				"Order confirmation message should not be displayed");

		log.info("Confirmation message is not displayed");
	}

	// =========================================================
	// CONFIRMATION TEXT
	// =========================================================

	/**
	 * Verifies complete order confirmation text.
	 *
	 * @param expectedText expected confirmation text
	 */
	public void verifyConfirmationText(String expectedText) {

		log.info("Verifying order confirmation text");

		Assert.assertNotNull(expectedText, "Expected confirmation text must not be null");

		String actualText = checkoutCompletePage.getConfirmationText();

		Assert.assertEquals(actualText, expectedText, "Incorrect order confirmation text");

		log.info("Order confirmation text verified successfully");
	}

	/**
	 * Verifies confirmation text is displayed.
	 */
	public void verifyConfirmationTextDisplayed() {

		log.info("Verifying confirmation text is displayed");

		Assert.assertTrue(checkoutCompletePage.isConfirmationTextDisplayed(), "Confirmation text should be displayed");

		log.info("Confirmation text is displayed");
	}

	// =========================================================
	// PONY EXPRESS IMAGE
	// =========================================================

	/**
	 * Verifies Pony Express confirmation image is displayed.
	 */
	public void verifyPonyExpressImageDisplayed() {

		log.info("Verifying Pony Express confirmation image");

		Assert.assertTrue(checkoutCompletePage.isPonyExpressImageDisplayed(),
				"Pony Express confirmation image should be displayed");

		log.info("Pony Express confirmation image verified");
	}

	/**
	 * Verifies Pony Express confirmation image is not displayed.
	 */
	public void verifyPonyExpressImageNotDisplayed() {

		log.info("Verifying Pony Express image is not displayed");

		Assert.assertFalse(checkoutCompletePage.isPonyExpressImageDisplayed(),
				"Pony Express confirmation image should not be displayed");

		log.info("Pony Express image is not displayed");
	}

	// =========================================================
	// BACK HOME BUTTON
	// =========================================================

	/**
	 * Verifies Back Home button is displayed.
	 */
	public void verifyBackHomeButtonDisplayed() {

		log.info("Verifying Back Home button is displayed");

		Assert.assertTrue(checkoutCompletePage.isBackHomeButtonDisplayed(), "Back Home button should be displayed");

		log.info("Back Home button is displayed");
	}

	/**
	 * Verifies Back Home button is not displayed.
	 */
	public void verifyBackHomeButtonNotDisplayed() {

		log.info("Verifying Back Home button is not displayed");

		Assert.assertFalse(checkoutCompletePage.isBackHomeButtonDisplayed(),
				"Back Home button should not be displayed");

		log.info("Back Home button is not displayed");
	}

	/**
	 * Verifies Back Home button text.
	 */
	public void verifyBackHomeButtonText() {

		log.info("Verifying Back Home button text");

		// Page object already validates the expected text.
		checkoutCompletePage.verifyBackHomeButtonText();

		log.info("Back Home button text verified successfully");
	}

	// =========================================================
	// ORDER COMPLETION
	// =========================================================

	/**
	 * Verifies that the order has been completed successfully.
	 */
	public void verifyOrderCompletedSuccessfully() {

		log.info("Verifying order completed successfully");

		// -----------------------------------------------------
		// URL
		// -----------------------------------------------------

		verifyCheckoutCompleteUrl();

		// -----------------------------------------------------
		// PAGE TITLE
		// -----------------------------------------------------

		verifyPageTitle("Checkout: Complete!");

		// -----------------------------------------------------
		// CONFIRMATION MESSAGE
		// -----------------------------------------------------

		verifyConfirmationMessage("Thank you for your order!");

		// -----------------------------------------------------
		// CONFIRMATION TEXT
		// -----------------------------------------------------

		verifyConfirmationText(
				"Your order has been dispatched, and will arrive just as fast as the pony can get there!");

		// -----------------------------------------------------
		// CONFIRMATION IMAGE
		// -----------------------------------------------------

		verifyPonyExpressImageDisplayed();

		// -----------------------------------------------------
		// BACK HOME BUTTON
		// -----------------------------------------------------

		verifyBackHomeButtonDisplayed();

		verifyBackHomeButtonText();

		log.info("Order completed successfully");
	}

	// =========================================================
	// SCENARIO CONTEXT VALIDATION
	// =========================================================

	/**
	 * Verifies that ScenarioContext is available.
	 */
	public void verifyScenarioContextAvailable() {

		log.info("Verifying ScenarioContext is available");

		Assert.assertNotNull(scenarioContext, "ScenarioContext should be available");

		log.info("ScenarioContext is available");
	}
}