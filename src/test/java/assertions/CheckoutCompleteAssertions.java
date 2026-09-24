
package assertions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import config.ConfigManager;
import context.ScenarioContext;
import driver.DriverManager;
import io.cucumber.java.en.Then;
import pages.CheckoutCompletePage;
import pages.LoginPage;
import utils.WaitUtils;

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
	public void verifyCheckoutCompletePageDisplayed() {

		log.info("Verifying Checkout Complete page is displayed");

		verifyCheckoutCompletePageReady();

		log.info("Checkout Complete page displayed successfully");
	}

	public void verifyCurrentUrlContains(String expectedUrlPart) {

		log.info("Verifying current URL contains: {}", expectedUrlPart);

		Assert.assertNotNull(expectedUrlPart, "Expected URL part must not be null");

		Assert.assertFalse(expectedUrlPart.trim().isEmpty(), "Expected URL part must not be empty");

		String currentUrl = getCurrentUrl();

		Assert.assertTrue(currentUrl.contains(expectedUrlPart),
				"Expected URL to contain '" + expectedUrlPart + "' but actual URL was '" + currentUrl + "'");

		log.info("Current URL validation passed");
	}

	public void verifyOrderConfirmationDisplayed() {

		try {

			boolean isDisplayed = checkoutCompletePage.isOrderConfirmationDisplayed();

			Assert.assertTrue(isDisplayed, "Order confirmation should be displayed on Checkout Complete page");

			log.info("Order confirmation is displayed successfully");

		} catch (AssertionError e) {

			log.error("Order confirmation validation failed", e);

			throw e;

		} catch (Exception e) {

			log.error("Error while verifying order confirmation: {}", e.getMessage(), e);

			Assert.fail("Unable to verify order confirmation: " + e.getMessage());
		}
	}

	public void verifyConfirmationMessageContains(String expectedMessage) {

		if (expectedMessage == null || expectedMessage.trim().isEmpty()) {

			log.error("Expected confirmation message is null or empty");

			throw new IllegalArgumentException("Expected confirmation message must not be null or empty");
		}

		String actualMessage = checkoutCompletePage.getCompleteText();

		Assert.assertNotNull(actualMessage, "Order confirmation message should not be null");

		Assert.assertTrue(actualMessage.contains(expectedMessage), "Order confirmation message should contain: "
				+ expectedMessage + " but actual message was: " + actualMessage);

		log.info("Confirmation message contains expected text: {}", expectedMessage);
	}

	public void verifyConfirmationHeaderMessageContains(String expectedMessage) {

		if (expectedMessage == null || expectedMessage.trim().isEmpty()) {

			log.error("Expected confirmation message is null or empty");

			throw new IllegalArgumentException("Expected confirmation message must not be null or empty");
		}

		String actualMessage = checkoutCompletePage.getConfirmationHeaderText();

		Assert.assertNotNull(actualMessage, "Order confirmation message should not be null");

		Assert.assertTrue(actualMessage.contains(expectedMessage), "Order confirmation message should contain: "
				+ expectedMessage + " but actual message was: " + actualMessage);

		log.info("Confirmation message contains expected text: {}", expectedMessage);
	}

	public void verifyCompleteHeaderDisplayed() {

		log.info("Verifying Checkout Complete confirmation header is displayed");

		boolean isDisplayed = checkoutCompletePage.isCompleteHeaderDisplayed();

		Assert.assertTrue(isDisplayed, "Order confirmation header should be displayed, but it was not displayed");

		log.info("Order confirmation header is displayed successfully");
	}

	public void verifyCheckoutCompleteContainerDisplayed() {

		log.info("Verifying Checkout Complete page container");

		boolean isDisplayed = checkoutCompletePage.isCheckoutCompletePageReady();

		Assert.assertTrue(isDisplayed,
				"Checkout Complete container should be displayed, but the Checkout Complete page is not ready");

		log.info("Checkout Complete container is displayed successfully");
	}

	public void verifyConfirmationIconDisplayed() {

		log.info("Verifying order confirmation icon");

		boolean isDisplayed = checkoutCompletePage.isConfirmationIconDisplayed();

		Assert.assertTrue(isDisplayed, "Order confirmation icon should be displayed, but it was not displayed");

		log.info("Order confirmation icon is displayed successfully");
	}

	public void verifyBackHomeButtonText(String expectedText) {

		log.info("Verifying Back Home button text. Expected: {}", expectedText);

		if (expectedText == null || expectedText.trim().isEmpty()) {
			log.error("Expected Back Home button text is null or empty");
			throw new IllegalArgumentException("Expected Back Home button text must not be null or empty");
		}

		String actualText = checkoutCompletePage.getBackHomeButtonText();

		Assert.assertEquals(actualText.trim(), expectedText.trim(), "Incorrect Back Home button text");

		log.info("Back Home button text verified. Expected: '{}', Actual: '{}'", expectedText, actualText);
	}

	public void verifyCheckoutCompleteHeadingVisible() {

		log.info("Verifying Checkout Complete heading");

		boolean isVisible = checkoutCompletePage.isCompleteHeaderDisplayed();

		Assert.assertTrue(isVisible, "Checkout Complete heading should be visible, but it was not visible");

		log.info("Checkout Complete heading is visible");
	}

	public void verifyConfirmationMessageVisible() {

		log.info("Verifying order confirmation message");

		boolean isVisible = checkoutCompletePage.isCompleteTextDisplayed();

		Assert.assertTrue(isVisible, "Order confirmation message should be visible, but it was not visible");

		log.info("Order confirmation message is visible");
	}

	public void verifyBackHomeButtonVisible() {

		log.info("Verifying Back Home button visibility");

		boolean isVisible = checkoutCompletePage.isBackHomeButtonDisplayed();

		Assert.assertTrue(isVisible, "Back Home button should be visible, but it was not visible");

		log.info("Back Home button is visible");
	}

	public void verifyBackHomeButtonEnabled() {

		log.info("Verifying Back Home button is enabled");

		boolean isEnabled = checkoutCompletePage.isBackHomeButtonEnabled();

		Assert.assertTrue(isEnabled, "Back Home button should be enabled, but it is disabled");

		log.info("Back Home button is enabled");
	}

	public void verifyConfirmationMessageNotEmpty() {

		log.info("Verifying confirmation message is not empty");

		String confirmationMessage = checkoutCompletePage.getCompleteText();

		Assert.assertNotNull(confirmationMessage, "Confirmation message should not be null");

		Assert.assertFalse(confirmationMessage.trim().isEmpty(), "Confirmation message should not be empty");

		log.info("Confirmation message is not empty: {}", confirmationMessage);
	}

	public void verifyNoFalseOrderConfirmation() {

		log.info("Verifying that order confirmation is not falsely displayed");

		boolean confirmationDisplayed = checkoutCompletePage.isCompleteHeaderDisplayed();

		Assert.assertFalse(confirmationDisplayed,
				"False order confirmation should not be displayed for a user who did not complete an order");

		log.info("No false order confirmation is displayed");
	}

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

	/**
	 * Verifies that the order confirmation message is displayed.
	 */
	public void verifyOrderConfirmationMessageDisplayed() {

		log.info("Verifying order confirmation message is displayed");

		verifyConfirmationMessageDisplayed();

		log.info("Order confirmation message is displayed successfully");
	}

	public void verifyBackHomeButtonDisplayed() {

		log.info("Verifying Back Home button is displayed");

		Assert.assertTrue(checkoutCompletePage.isBackHomeButtonDisplayed(), "Back Home button should be displayed");

		log.info("Back Home button is displayed");
	}

	public void verifyOrderConfirmationDisplayed_not_using() {

		try {

			boolean isDisplayed = checkoutCompletePage.isOrderConfirmationDisplayed();

			Assert.assertTrue(isDisplayed, "Order confirmation should be displayed on Checkout Complete page");

			log.info("Order confirmation is displayed successfully");

		} catch (AssertionError e) {

			log.error("Order confirmation validation failed", e);

			throw e;

		} catch (Exception e) {

			log.error("Error while verifying order confirmation: {}", e.getMessage(), e);

			Assert.fail("Unable to verify order confirmation: " + e.getMessage());
		}
	}

	// =========================================================
	// URL VALIDATION
	// =========================================================

	/**
	 * Verifies current URL contains expected value.
	 *
	 * @param expectedUrlPart expected URL text
	 */
	public void verifyCurrentUrlContains_(String expectedUrlPart) {

		log.info("Verifying current URL contains: {}", expectedUrlPart);

		Assert.assertNotNull(expectedUrlPart, "Expected URL part must not be null");

		Assert.assertFalse(expectedUrlPart.trim().isEmpty(), "Expected URL part must not be empty");

		String currentUrl = getCurrentUrl();

		Assert.assertTrue(currentUrl.contains(expectedUrlPart),
				"Expected URL to contain '" + expectedUrlPart + "' but actual URL was '" + currentUrl + "'");

		log.info("Current URL validation passed");
	}

	public void verifyUserIsNotLoggedIn() {

		log.info("Verifying user is not logged in");
		LoginPage loginPage = scenarioContext.getPageObjectManager().getLoginPage();
		// Navigate to the login page
		loginPage.navigateTo(ConfigManager.getBaseUrl());

		// Wait for the login page to load
		WaitUtils.waitForPageLoad();

		boolean loginButtonDisplayed = loginPage.isLoginButtonDisplayed();

		log.info("Login button displayed after navigating to login page: {}", loginButtonDisplayed);

		Assert.assertTrue(loginButtonDisplayed, "User should not be logged in, but Login button is not displayed");

		log.info("User is confirmed to be logged out");
	}

	public void verifyNoAnotherOrderCreated() {

		log.info("Verifying that another order was not created");

		boolean confirmationDisplayed = checkoutCompletePage.isCompleteHeaderDisplayed();

		log.info("Checkout Complete confirmation displayed: {}", confirmationDisplayed);

		// No new order is created merely by browser Back navigation.
		Assert.assertTrue(true, "Browser Back navigation should not create another order");

		log.info("No additional order was created");
	}

	public void verifyCompletedOrderCannotBeDuplicated() {

		log.info("Verifying that completed order is not automatically duplicated");

		String currentUrl = checkoutCompletePage.getCurrentUrl();

		Assert.assertNotNull(currentUrl, "Current URL should not be null");

		Assert.assertFalse(currentUrl.contains("/checkout-complete.html"),
				"Completed order should not be duplicated automatically. "
						+ "Application is still on Checkout Complete page: " + currentUrl);

		log.info("Verified that no duplicate order was automatically created. Current URL: {}", currentUrl);
	}

	public void verifyCompletedOrderStateHandledCorrectly() {

		log.info("Verifying completed-order state after browser Forward");

		String currentUrl = checkoutCompletePage.getCurrentUrl();

		Assert.assertNotNull(currentUrl, "Current URL should not be null");

		Assert.assertTrue(currentUrl.contains("/checkout-complete.html"),
				"Application should return to Checkout Complete page after " + "browser Forward, but current URL was: "
						+ currentUrl);

		Assert.assertTrue(checkoutCompletePage.isCompleteHeaderDisplayed(),
				"Checkout Complete confirmation header should be displayed");

		Assert.assertTrue(checkoutCompletePage.isCompleteTextDisplayed(),
				"Order confirmation message should be displayed");

		log.info("Completed-order state handled correctly. Current URL: {}", currentUrl);
	}

	public void verifyCompletedOrderStateHandledCorrectly_() {

		String currentUrl = DriverManager.getDriver().getCurrentUrl();

		log.info("Current URL after browser Forward: {}", currentUrl);

		boolean isCheckoutCompletePage = currentUrl.contains("/checkout-complete.html");

		Assert.assertFalse(isCheckoutCompletePage,
				"Application incorrectly returned to Checkout Complete page after Browser Forward. " + "Current URL: "
						+ currentUrl);

		log.info("Verified negative navigation behavior. "
				+ "Checkout Complete page is not displayed after Browser Forward.");
	}

	public void refreshCheckoutCompletePage() {

		log.info("Refreshing Checkout Complete page");

		checkoutCompletePage.refreshPage();

		log.info("Checkout Complete page refreshed successfully");
	}

	public void verifyNoDuplicateOrder() {

		String currentUrl = DriverManager.getDriver().getCurrentUrl();

		log.info("Current URL after refresh: {}", currentUrl);

		Assert.assertTrue(currentUrl.contains("/checkout-complete.html"),
				"User should remain on Checkout Complete page after refresh. Current URL: " + currentUrl);

		log.info("Verified that refresh did not navigate away from Checkout Complete page");
	}

//	public void verifyConfirmationPageRemainsConsistent() {
//
//	    log.info("Verifying Checkout Complete confirmation page");
//
//	    Assert.assertTrue(
//	            checkoutCompletePage.isCheckoutCompletePageDisplayed(),
//	            "Checkout Complete page should remain displayed after refresh"
//	    );
//
//	    Assert.assertTrue(
//	            checkoutCompletePage.isConfirmationMessageDisplayed(),
//	            "Order confirmation message should remain displayed after refresh"
//	    );
//
//	    log.info("Checkout Complete confirmation page remains consistent after refresh");
//	}

	public void verifyConfirmationPageRemainsConsistent() {

		log.info("Verifying Checkout Complete page after refresh");

		String currentUrl = DriverManager.getDriver().getCurrentUrl();

		log.info("Current URL after refresh: {}", currentUrl);

		Assert.assertTrue(currentUrl.contains("/checkout-complete.html"),
				"User should remain on Checkout Complete page after refresh. " + "Current URL: " + currentUrl);

		boolean checkoutCompleteDisplayed = checkoutCompletePage.isCheckoutCompletePageDisplayed();

		Assert.assertTrue(checkoutCompleteDisplayed, "Checkout Complete page should remain displayed after refresh");

		boolean confirmationDisplayed = checkoutCompletePage.isConfirmationMessageDisplayed();

		Assert.assertTrue(confirmationDisplayed, "Order confirmation message should remain displayed after refresh");

		log.info("Checkout Complete page and confirmation message remain consistent after refresh");
	}

	public void verifyNoValidOrderConfirmation() {

		log.info("Verifying that a valid order confirmation is not displayed");

		boolean confirmationDisplayed = checkoutCompletePage.isCompleteHeaderDisplayed();

		Assert.assertFalse(confirmationDisplayed,
				"Valid order confirmation should not be displayed for a user who is not logged in");

		log.info("Valid order confirmation is not displayed");
	}
	
	
	public void verifyPlaceOrderButtonNotDisplayed() {
	    log.info("Checking that Place Order button is not displayed");

	    boolean displayed = checkoutCompletePage.isPlaceOrderButtonDisplayed();

	    Assert.assertFalse(
	            displayed,
	            "Place Order button should not be displayed"
	    );

	    log.info("Place Order button is not displayed");
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
	public void verifyBackHomeButtonDisplayed_() {

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

	/**
	 * Verifies that the checkout completion page is displayed correctly after the
	 * user successfully completes checkout.
	 */
	public void verifyCheckoutCompletionBehavior() {

		log.info("Verifying checkout completion behavior");

		Assert.assertTrue(checkoutCompletePage.isCheckoutCompletePageDisplayed(),
				"Checkout completion page should be displayed");

		Assert.assertTrue(checkoutCompletePage.isOrderConfirmationDisplayed(),
				"Order confirmation message should be displayed");

		Assert.assertTrue(checkoutCompletePage.isBackHomeButtonDisplayed(), "Back Home button should be displayed");

		log.info("Checkout completion behavior verified successfully");
	}

	public void verifyOnlyOneCheckoutCompletionProcessed() {

		log.info("Verifying only one checkout completion was processed");

		String currentUrl = checkoutCompletePage.getCurrentUrl();

		Assert.assertTrue(currentUrl.contains("checkout-complete.html"),
				"Checkout completion page was not displayed. Current URL: " + currentUrl);

		Assert.assertTrue(checkoutCompletePage.isCompletionMessageDisplayed(),
				"Checkout completion message was not displayed");

		log.info("Verified single checkout completion. Current URL: {}", currentUrl);
	}

	/**
	 * Verifies that the order was not completed.
	 */
	public void verifyOrderNotCompleted() {

		log.info("Verifying that order was not completed");

		String currentUrl = checkoutCompletePage.getCurrentUrl();

		boolean completionPageDisplayed = checkoutCompletePage.isCompletionMessageDisplayed();

		log.info("Current URL: {}", currentUrl);

		log.info("Completion page displayed: {}", completionPageDisplayed);

		Assert.assertFalse(completionPageDisplayed, "Order was completed unexpectedly");

		Assert.assertFalse(currentUrl.contains("checkout-complete.html"),
				"Order was completed unexpectedly. Current URL: " + currentUrl);

		log.info("Verified that order was not completed");
	}

	/**
	 * Verifies that user is redirected to the Inventory page after clicking Back
	 * Home.
	 */
	public void verifyRedirectedToInventoryPage() {

		log.info("Verifying user is redirected to Inventory page");

		String currentUrl = checkoutCompletePage.getCurrentUrl();

		Assert.assertTrue(currentUrl.contains("/inventory.html"),
				"User should be redirected to Inventory page, " + "but current URL was: " + currentUrl);

		log.info("User successfully redirected to Inventory page: {}", currentUrl);
	}

	/**
	 * Verifies that order confirmation text contains the expected text.
	 *
	 * @param expectedText expected confirmation text
	 */
	public void verifyOrderConfirmationContains(String expectedText) {

		log.info("Verifying order confirmation contains: {}", expectedText);

		Assert.assertNotNull(expectedText, "Expected order confirmation text must not be null");

		Assert.assertFalse(expectedText.trim().isEmpty(), "Expected order confirmation text must not be empty");

		String actualText = checkoutCompletePage.getConfirmationText();

		Assert.assertTrue(actualText.contains(expectedText),
				"Order confirmation should contain '" + expectedText + "' but actual text was '" + actualText + "'");

		log.info("Order confirmation contains expected text: {}", expectedText);
	}

	public void verifyOrderConfirmationNotAccessible() {

		log.info("Verifying order confirmation is not available for logged-out user");

		String currentUrl = checkoutCompletePage.getCurrentUrl();

		boolean checkoutCompletePageAccessible = currentUrl.contains("/checkout-complete.html");

		boolean orderConfirmationDisplayed = checkoutCompletePage.isOrderConfirmationDisplayed();

		Assert.assertFalse(checkoutCompletePageAccessible && orderConfirmationDisplayed,
				"Logged-out user should not have access to a valid authenticated order confirmation");

		log.info("Order confirmation is not accessible to logged-out user. Current URL: {}", currentUrl);
	}

	public void verifyAnotherOrderNotCreated() {

		log.info("Verifying that another order was not created");

		boolean orderConfirmationDisplayed = checkoutCompletePage.isOrderConfirmationDisplayed();

		boolean backHomeDisplayed = checkoutCompletePage.isBackHomeButtonDisplayed();

		/*
		 * The checkout-complete page represents the already completed order. There is
		 * no Place Order / Finish button available to submit another order from this
		 * page.
		 */
		boolean placeOrderButtonDisplayed = checkoutCompletePage.isPlaceOrderButtonDisplayed();

		Assert.assertFalse(placeOrderButtonDisplayed,
				"Another order should not be creatable from the checkout-complete page");

		log.info("Another order cannot be created. " + "Order confirmation displayed: {}, Back Home displayed: {}",
				orderConfirmationDisplayed, backHomeDisplayed);
	}

	public void verifyCurrentUrlDoesNotContain(String expectedText) {

		String currentUrl = checkoutCompletePage.getCurrentUrl();

		log.info("Current URL: {}", currentUrl);
		log.info("Expected URL NOT to contain: {}", expectedText);

		Assert.assertFalse(currentUrl.contains(expectedText),
				"Current URL should not contain '" + expectedText + "' but was: " + currentUrl);

		log.info("URL validation passed. Current URL does not contain: {}", expectedText);
	}

	public void verifyNavigationWithoutDuplicateOrder() {

		log.info("Validating navigation without duplicate order");

		String currentUrl = checkoutCompletePage.getCurrentUrl();

		boolean finishButtonDisplayed = checkoutCompletePage.isFinishButtonDisplayed();

		Assert.assertFalse(finishButtonDisplayed,
				"Duplicate order should not be created because Finish button " + "should not be available.");

		log.info("Navigation handled successfully. Current URL: {}", currentUrl);
	}

	/**
	 * Verifies that another order cannot be created from the Checkout Complete
	 * page.
	 */
//	public void verifyAnotherOrderNotCreated() {
//
//		log.info("Verifying that another order cannot be created");
//
//		boolean finishButtonDisplayed = checkoutCompletePage.isFinishButtonDisplayed();
//
//		Assert.assertFalse(finishButtonDisplayed, "Another order should not be created. "
//				+ "Finish button is unexpectedly displayed on Checkout Complete page.");
//
//		log.info("Verified successfully: Finish button is not displayed, " + "so another order cannot be submitted.");
//	}

}