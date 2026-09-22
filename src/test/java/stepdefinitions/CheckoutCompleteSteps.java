package stepdefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import actions.CheckoutCompleteAction;
import assertions.CheckoutCompleteAssertions;
import config.ConfigManager;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page_object_manager.PageObjectManager;
import utils.WaitUtils;

/**
 * Step definitions for SauceDemo Checkout Complete page.
 *
 * Responsibilities: - Perform Checkout Complete page actions - Validate
 * Checkout Complete page - Validate order confirmation - Validate order
 * confirmation message - Validate Back Home button - Validate checkout
 * completion URL
 */
public class CheckoutCompleteSteps {

	private static final Logger log = LoggerFactory.getLogger(CheckoutCompleteSteps.class);

	private final CheckoutCompleteAssertions checkoutCompleteAssertions;

	private final CheckoutCompleteAction checkoutCompleteAction;

	private final ScenarioContext scenarioContext;

	private final PageObjectManager pageObjectManager;

	/**
	 * Constructor injection using ScenarioContext.
	 *
	 * @param context ScenarioContext containing PageObjectManager
	 */
	public CheckoutCompleteSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		if (context.getPageObjectManager() == null) {
			throw new IllegalStateException("PageObjectManager must not be null");
		}

		this.scenarioContext = context;
		this.pageObjectManager = context.getPageObjectManager();

		this.checkoutCompleteAction = new CheckoutCompleteAction(pageObjectManager.getCheckoutCompletePage(),
				pageObjectManager);

		this.checkoutCompleteAssertions = new CheckoutCompleteAssertions(pageObjectManager.getCheckoutCompletePage(),
				context);

		log.debug("CheckoutCompleteSteps initialized");
	}

	/**
	 * Validates that Checkout Complete page is displayed.
	 */
	@Then("the Checkout Complete page should be displayed")
	public void the_checkout_complete_page_should_be_displayed() {

		log.info("Verifying Checkout Complete page is displayed");

		checkoutCompleteAssertions.verifyCheckoutCompletePageDisplayed();
	}

	/**
	 * Validates Checkout Complete URL.
	 */
	@Then("the Checkout Complete URL should contain {string}")
	public void the_checkout_complete_url_should_contain(String expectedUrl) {

		log.info("Verifying Checkout Complete URL contains: {}", expectedUrl);

		checkoutCompleteAssertions.verifyCurrentUrlContains(expectedUrl);
	}

	/**
	 * Validates that order confirmation is displayed.
	 */
	@Then("the order confirmation should be displayed")
	public void the_order_confirmation_should_be_displayed() {

		log.info("Verifying order confirmation is displayed");

		checkoutCompleteAssertions.verifyOrderConfirmationDisplayed();
	}

	@Then("the order confirmation message should contain {string}")
	public void the_order_confirmation_message_should_contain(String expectedMessage) {

		log.info("Verifying order confirmation message contains: {}", expectedMessage);

		checkoutCompleteAssertions.verifyConfirmationMessageContains(expectedMessage);

		log.info("Order confirmation message verified successfully");
	}

	@Then("the order confirmation header message should contain {string}")
	public void order_confirmation_header_message_should_contain(String expectedConfirmationHeader) {
		log.info("Verifying order confirmation message contains: {}", expectedConfirmationHeader);

		checkoutCompleteAssertions.verifyConfirmationHeaderMessageContains(expectedConfirmationHeader);

		log.info("Order confirmation message verified successfully");
	}
	
	
	
	/**
	 * Validates the order confirmation message.
	 */
	@Then("the order confirmation message should be displayed")
	public void the_order_confirmation_message_should_be_displayed() {

		log.info("Verifying order confirmation message is displayed");

		checkoutCompleteAssertions.verifyOrderConfirmationMessageDisplayed();
	}

	/**
	 * Clicks the Back Home button from Checkout Complete page.
	 */

	@When("the user clicks the Back Home button")
	public void the_user_clicks_the_back_home_button() {

		checkoutCompleteAction.clickBackHome();
	}

	// ============================================================
	// GIVEN
	// ============================================================

//	@Given("the user has successfully completed an order")
//	public void the_user_has_successfully_completed_an_order() {
//
//		log.info("Starting successful checkout completion flow");
//
//		checkoutCompleteAction.completeCheckout();
//
//		log.info("User successfully completed the order");
//	}
//	
	@Given("the user has successfully completed an order")
	public void the_user_has_successfully_completed_an_order() {

		log.info("Verifying user is on Checkout Complete page");

		checkoutCompleteAssertions.verifyCheckoutCompletePageReady();

		log.info("User is successfully on Checkout Complete page");
	}

	// ============================================================
	// THEN
	// ============================================================

	@Then("the Checkout Complete page title should be {string}")
	public void the_checkout_complete_page_title_should_be(String expectedTitle) {

		log.info("Verifying Checkout Complete page title: {}", expectedTitle);

		checkoutCompleteAssertions.verifyPageTitle(expectedTitle);

		log.info("Checkout Complete page title verified successfully");
	}

	/**
	 * Validates that Back Home button is displayed.
	 */
	@Then("the Back Home button should be displayed")
	public void the_back_home_button_should_be_displayed() {

		log.info("Verifying Back Home button is displayed");

		checkoutCompleteAssertions.verifyBackHomeButtonDisplayed();
	}
	
	@Then("the Back Home button text should be {string}")
	public void the_back_home_button_text_should_be(String expectedText) {

	    log.info("Verifying Back Home button text: {}", expectedText);

	    checkoutCompleteAssertions.verifyBackHomeButtonText(expectedText);

	    log.info("Back Home button text verified successfully");
	}
	
	@Then("the Checkout Complete heading should be visible")
	public void the_checkout_complete_heading_should_be_visible() {

	    log.info("Verifying Checkout Complete heading is visible");

	    checkoutCompleteAssertions.verifyCheckoutCompleteHeadingVisible();

	    log.info("Checkout Complete heading is visible successfully");
	}
	
	@Then("the order confirmation message should be visible")
	public void the_order_confirmation_message_should_be_visible() {

	    log.info("Verifying order confirmation message is visible");

	    checkoutCompleteAssertions.verifyConfirmationMessageVisible();

	    log.info("Order confirmation message is visible successfully");
	}
	
	@Then("the order confirmation header should be displayed")
	public void the_order_confirmation_header_should_be_displayed() {

	    log.info("Verifying order confirmation header is displayed");

	    checkoutCompleteAssertions.verifyCompleteHeaderDisplayed();

	    log.info("Order confirmation header is displayed successfully");
	}
	
	@Then("the Back Home button should be visible")
	public void the_back_home_button_should_be_visible() {

	    log.info("Verifying Back Home button is visible");

	    checkoutCompleteAssertions.verifyBackHomeButtonVisible();

	    log.info("Back Home button is visible successfully");
	}
	
	@Then("the Back Home button should be enabled")
	public void the_back_home_button_should_be_enabled() {

	    log.info("Verifying Back Home button is enabled");

	    checkoutCompleteAssertions.verifyBackHomeButtonEnabled();

	    log.info("Back Home button is enabled successfully");
	}
	
	@Then("the confirmation message should not be empty")
	public void the_confirmation_message_should_not_be_empty() {

	    log.info("Verifying confirmation message is not empty");

	    checkoutCompleteAssertions.verifyConfirmationMessageNotEmpty();

	    log.info("Confirmation message is not empty");
	}
	

	@When("the user navigates directly to {string}")
	public void the_user_navigates_directly_to(String path) {
	
	    log.info("Navigating directly to: {}", path);
	
	    String baseUrl = ConfigManager.getBaseUrl();
	
	    if (baseUrl == null || baseUrl.trim().isEmpty()) {
	        throw new IllegalStateException("Base URL is not configured");
	    }
	
	    String targetUrl = baseUrl + path;
	
	    checkoutCompleteAction.navigateTo(targetUrl);
	
	    WaitUtils.waitForPageLoad();
	
	    log.info("Navigated to: {}", targetUrl);
	}

	
	@Then("the user should not see a false order confirmation")
	public void the_user_should_not_see_a_false_order_confirmation() {

	    log.info("Verifying that a false order confirmation is not displayed");

	    checkoutCompleteAssertions.verifyNoFalseOrderConfirmation();

	    log.info("False order confirmation was not displayed");
	}

	
	@Then("the Checkout Complete container should be displayed")
	public void the_checkout_complete_container_should_be_displayed() {

	    log.info("Verifying Checkout Complete container is displayed");

	    checkoutCompleteAssertions.verifyCheckoutCompleteContainerDisplayed();

	    log.info("Checkout Complete container is displayed successfully");
	}
	
	@Then("the order confirmation icon should be displayed")
	public void the_order_confirmation_icon_should_be_displayed() {

	    log.info("Verifying order confirmation icon is displayed");

	    checkoutCompleteAssertions.verifyConfirmationIconDisplayed();

	    log.info("Order confirmation icon is displayed successfully");
	}

	/**
	 * Validates navigation back to Inventory page.
	 */
//	@Then("the user should be redirected to the Inventory page")
//	public void the_user_should_be_redirected_to_the_inventory_page() {
//
//		log.info("Verifying user is redirected to Inventory page");
//
//		checkoutCompleteAssertions.verifyRedirectedToInventoryPage();
//	}

	/**
	 * Validates Checkout Complete page title.
	 */
	@Then("the Checkout Complete page title should be displayed")
	public void the_checkout_complete_page_title_should_be_displayed() {

		log.info("Verifying Checkout Complete page title");

		checkoutCompleteAssertions.verifyPageTitleDisplayed();
	}

	/**
	 * Validates that the order confirmation contains expected text.
	 */
	@Then("the order confirmation should contain {string}")
	public void the_order_confirmation_should_contain(String expectedText) {

		log.info("Verifying order confirmation contains: {}", expectedText);

		checkoutCompleteAssertions.verifyOrderConfirmationContains(expectedText);
	}
}