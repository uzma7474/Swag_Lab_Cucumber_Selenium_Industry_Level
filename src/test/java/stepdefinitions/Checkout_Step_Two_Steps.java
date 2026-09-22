package stepdefinitions;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import actions.CartAction;
import actions.CheckoutInformationAction;
import actions.Checkout_Step_One_Action;
import actions.Checkout_Step_Two_Action;
import assertions.CheckoutCompleteAssertions;
import assertions.Checkout_Step_Two_Assertions;
import context.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page_object_manager.PageObjectManager;
import pages.Checkout_Step_Two_Page;

/**
 * Step definitions for SauceDemo Checkout Step Two.
 *
 * Responsibilities: - Perform Checkout Step Two actions - Validate Checkout
 * Overview page - Validate product information - Validate payment and shipping
 * information - Validate subtotal, tax and total - Validate Cancel and Finish
 * actions - Validate checkout navigation - Validate order summary consistency
 */
public class Checkout_Step_Two_Steps {

	private static final Logger log = LoggerFactory.getLogger(Checkout_Step_Two_Steps.class);

	private final Checkout_Step_Two_Action checkoutStepTwoAction;
	private final Checkout_Step_Two_Assertions checkoutStepTwoAssertions;

	private final Checkout_Step_Two_Page checkoutStepTwoPage;

	private final CheckoutCompleteAssertions checkoutCompleteAssertions;

	// otica@123+
	private final ScenarioContext scenarioContext;

	public List<String> cartProductNames;
	// = scenarioContext.getCartProductNames();

	/**
	 * Constructor injection using ScenarioContext.
	 *
	 * @param context ScenarioContext containing PageObjectManager
	 */
	public Checkout_Step_Two_Steps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		if (context.getPageObjectManager() == null) {
			throw new IllegalStateException("PageObjectManager must not be null");
		}

		this.scenarioContext = context;
		this.checkoutStepTwoAction = new Checkout_Step_Two_Action(
				context.getPageObjectManager().getCheckoutStepTwoPage());

//		this.checkoutStepTwoAssertions = new Checkout_Step_Two_Assertions(
//				context.getPageObjectManager().getCheckoutStepTwoPage());

		this.checkoutStepTwoAssertions = new Checkout_Step_Two_Assertions(
				context.getPageObjectManager().getCheckoutStepTwoPage(), context);

		this.cartProductNames = context.getCartProductNames();

		this.checkoutCompleteAssertions = new CheckoutCompleteAssertions(
				context.getPageObjectManager().getCheckoutCompletePage(), context);

		this.checkoutStepTwoPage = context.getPageObjectManager().getCheckoutStepTwoPage();
		log.debug("Checkout_Step_Two_Steps initialized");
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

//	@Then("the Checkout Step Two page should be displayed")
//	public void theCheckoutStepTwoPageShouldBeDisplayed() {
//
//		log.info("Verifying Checkout Step Two page is displayed");
//
//		checkoutStepTwoAssertions.verifyCheckoutStepTwoPageDisplayed();
//	}

	@Then("the Checkout Step Two page title should be {string}")
	public void theCheckoutStepTwoPageTitleShouldBe(String expectedTitle) {

		log.info("Verifying Checkout Step Two page title: {}", expectedTitle);

		checkoutStepTwoAssertions.verifyPageTitle(expectedTitle);
	}

	@Then("the Checkout Overview container should be displayed")
	public void the_checkout_overview_container_should_be_displayed() {
		log.info("Verifying checkout overview is displayed");

		checkoutStepTwoAssertions.verifyCheckoutOverviewDisplayed();
	}

	@Then("the product {string} should be displayed in Checkout Overview")
	public void the_product_should_be_displayed_in_checkout_overview(String expectedProduct) {
		log.info("Verifying the product {} is displayed on checkout overview ", expectedProduct);
		checkoutStepTwoAssertions.verifyProductDisplayed(expectedProduct);

	}

	@Then("product {string} should be displayed in Checkout Overview")
	public void product_should_be_displayed_in_checkout_overview(String productName) {

		log.info("Verifying product '{}' is displayed in Checkout Overview", productName);

		checkoutStepTwoAssertions.verifyProductDisplayedInCheckoutOverview(productName);

		log.info("Product '{}' is displayed in Checkout Overview", productName);
	}

	@Then("the Checkout Overview should contain {int} product")
	public void the_checkout_overview_should_contain_product(Integer productCount) {
		log.info("Verifying number of the product {} is displayed on checkout overview ", productCount);
		checkoutStepTwoAssertions.verifyCartItemCount(productCount);
	}

	@Then("the first product name should be {string}")
	public void the_first_product_name_should_be(String expectedProductName) {
		log.info("Verifying the product name is {} ", expectedProductName);
		checkoutStepTwoAssertions.verifyProductName(expectedProductName);
	}

	@Then("Payment Information should be displayed")
	public void payment_information_should_be_displayed() {
		log.info("Verifying the product name price is displayed ");
		checkoutStepTwoAssertions.verifyPaymentInformationDisplayed();
	}

	@Then("Payment Information should be {string}")
	public void payment_information_should_be(String expectedPaymentInfo) {
		log.info("Verifying the product payment {} ", expectedPaymentInfo);
		checkoutStepTwoAssertions.verifyPaymentInformation(expectedPaymentInfo);
	}

	@Then("the checkout overview should be displayed")
	public void theCheckoutOverviewShouldBeDisplayed() {

		log.info("Verifying checkout overview is displayed");

		checkoutStepTwoAssertions.verifyCheckoutOverviewDisplayed();
	}

	@Then("the checkout overview should be ready")
	public void theCheckoutOverviewShouldBeReady() {

		log.info("Verifying Checkout Step Two page is ready");

		checkoutStepTwoAssertions.verifyCheckoutStepTwoPageReady();
	}

	@Then("the current URL should contain {string}")
	public void theCurrentUrlShouldContain(String expectedUrlPart) {

		log.info("Verifying current URL contains: {}", expectedUrlPart);

		checkoutStepTwoAssertions.verifyCurrentUrlContains(expectedUrlPart);
	}

	// =========================================================
	// PRODUCT VALIDATION
	// =========================================================

	@Then("the checkout overview should contain {int} product")
	public void theCheckoutOverviewShouldContainProduct(int expectedCount) {

		log.info("Verifying checkout overview product count: {}", expectedCount);

		checkoutStepTwoAssertions.verifyProductCount(expectedCount);
	}

	@Then("the checkout overview should contain {int} products")
	public void theCheckoutOverviewShouldContainProducts(int expectedCount) {

		log.info("Verifying checkout overview product count: {}", expectedCount);

		checkoutStepTwoAssertions.verifyProductCount(expectedCount);
	}

	@Then("the product {string} should be displayed in the checkout overview")
	public void theProductShouldBeDisplayedInCheckoutOverview(String productName) {

		log.info("Verifying product displayed in checkout overview: {}", productName);

		checkoutStepTwoAssertions.verifyProductDisplayed(productName);
	}

	@Then("the product {string} should not be displayed in the checkout overview")
	public void theProductShouldNotBeDisplayedInCheckoutOverview(String productName) {

		log.info("Verifying product is not displayed in checkout overview: {}", productName);

		checkoutStepTwoAssertions.verifyProductNotDisplayed(productName);
	}

	@Then("the product name should be {string}")
	public void theProductNameShouldBe(String expectedProductName) {

		log.info("Verifying product name: {}", expectedProductName);

		checkoutStepTwoAssertions.verifyProductName(expectedProductName);
	}

	@Then("the checkout product name should be {string}")
	public void theCheckoutProductNameShouldBe(String expectedProductName) {

		log.info("Verifying checkout product name: '{}'", expectedProductName);

		checkoutStepTwoAssertions.verifyProductName(expectedProductName);

	}

	@Then("the page title of checkout overview should be {string}")
	public void the_page_title_of_checkout_overview_should_be(String expectedPageTitle) {
		log.info("Verifying checkout page title {} displayed", expectedPageTitle);

		String actualPageTitle = checkoutStepTwoAction.getPageTitle();

		Assert.assertEquals(actualPageTitle, expectedPageTitle);
	}

	@Then("the product {string} should not be displayed on checkout page")
	public void theProductShouldNotBeDisplayedOnCheckoutStepTwo(String productName) {

		log.info("Verifying product '{}' is not displayed", productName);

		checkoutStepTwoAssertions.verifyProductNotDisplayed(productName);
	}

//	@Then("the product description should be displayed")
//	public void theProductDescriptionShouldBeDisplayed() {
//
//		log.info("Verifying product description is displayed");
//
//		checkoutStepTwoAssertions.verifyProductDescriptionDisplayed();
//	}

	@Then("the product description {string} should be displayed")
	public void theProductDescriptionShouldBeDisplayed(String expectedDescription) {

		log.info("Verifying product description is displayed: '{}'", expectedDescription);

		checkoutStepTwoAssertions.verifyProductDescriptionDisplayed(expectedDescription);

	}
	
	
	@Given("the user click on finish button")
	public void the_user_click_on_finish_button() {

	    log.info("Starting checkout completion flow");

	    checkoutStepTwoAction.clickFinish();

	    log.info("Finish button clicked successfully");
	}
	
	

//	@Then("the product price should be displayed")
//	public void theProductPriceShouldBeDisplayed() {
//
//		log.info("Verifying product price is displayed");
//
//		checkoutStepTwoAssertions.verifyProductPriceDisplayed();
//	}

	@Then("the product {string} price should be {string}")
	public void theProductPriceShouldBe(String productName, String expectedPrice) {

		log.info("Verifying price for product '{}': '{}'", productName, expectedPrice);

		checkoutStepTwoAssertions.verifyProductPriceDisplayed(productName, expectedPrice);

	}

	@Then("the product {string} should have price {string}")
	public void theProductShouldHavePrice(String productName, String expectedPrice) {

		log.info("Verifying price for product: {} = {}", productName, expectedPrice);

		checkoutStepTwoAssertions.verifyProductPrice(productName, expectedPrice);
	}

	@Then("all checkout products should have valid prices")
	public void allCheckoutProductsShouldHaveValidPrices() {

		log.info("Verifying all checkout products have valid prices");

		checkoutStepTwoAssertions.verifyAllProductPricesAreValid();
	}

	// =========================================================
	// PAYMENT INFORMATION
	// =========================================================

	@Then("the payment information should be displayed")
	public void thePaymentInformationShouldBeDisplayed() {

		log.info("Verifying payment information is displayed");

		checkoutStepTwoAssertions.verifyPaymentInformationDisplayed();
	}

	@Then("the payment information should contain {string}")
	public void thePaymentInformationShouldContain(String expectedPaymentInformation) {

		log.info("Verifying payment information: {}", expectedPaymentInformation);

		checkoutStepTwoAssertions.verifyPaymentInformation(expectedPaymentInformation);
	}

	@Then("the payment information should not be empty")
	public void thePaymentInformationShouldNotBeEmpty() {

		log.info("Verifying payment information is not empty");

		checkoutStepTwoAssertions.verifyPaymentInformationNotEmpty();
	}

	// =========================================================
	// SHIPPING INFORMATION
	// =========================================================

	@Then("Shipping Information should be displayed")
	public void shipping_information_should_be_displayed() {
		log.info("Verifying shipping information is displayed");

		checkoutStepTwoAssertions.verifyShippingInformationDisplayed();
	}

	@Then("the shipping information should contain {string}")
	public void theShippingInformationShouldContain(String expectedShippingInformation) {

		log.info("Verifying shipping information: {}", expectedShippingInformation);

		checkoutStepTwoAssertions.verifyShippingInformation(expectedShippingInformation);
	}

	@Then("all Checkout Step Two controls should be displayed")
	public void all_checkout_step_two_controls_should_be_displayed() {

		log.info("Verifying all Checkout Step Two controls are displayed");

		checkoutStepTwoAssertions.verifyAllCheckoutStepTwoControlsDisplayed();
	}

	@Then("the Checkout Step Two page should be ready for checkout completion")
	public void the_checkout_step_two_page_should_be_ready_for_checkout_completion() {

		log.info("Verifying Checkout Step Two page is ready for checkout completion");

		checkoutStepTwoAssertions.verifyCheckoutStepTwoPageReady();

		log.info("Checkout Step Two page is ready for checkout completion");
	}

	@Then("Shipping Information should be {string}")
	public void shipping_information_should_be(String expectedShippingInfo) {
		log.info("Verifying shipping information: {}", expectedShippingInfo);

		checkoutStepTwoAssertions.verifyShippingInformation(expectedShippingInfo);

	}

	@Then("the subtotal should be displayed")
	public void the_subtotal_should_be_displayed() {
		log.info("Verifying subtotal should be displayed");
		checkoutStepTwoAssertions.verifySubtotalDisplayed();
	}

	@Then("the subtotal should be {string}")
	public void the_subtotal_should_be_value(String expectedItemTotal) {
		log.info("Verifying subtotal should be {}", expectedItemTotal);
		checkoutStepTwoAssertions.verifySubtotal(expectedItemTotal);
	}

	@Then("the shipping information should not be empty")
	public void theShippingInformationShouldNotBeEmpty() {

		log.info("Verifying shipping information is not empty");

		checkoutStepTwoAssertions.verifyShippingInformationNotEmpty();
	}

	// =========================================================
	// PRICE SUMMARY
	// =========================================================

	@Then("the item subtotal should be displayed")
	public void theItemSubtotalShouldBeDisplayed() {

		log.info("Verifying item subtotal is displayed");

		checkoutStepTwoAssertions.verifySubtotalDisplayed();
	}

	@Then("the item subtotal should be {string}")
	public void theItemSubtotalShouldBe(String expectedSubtotal) {

		log.info("Verifying item subtotal: {}", expectedSubtotal);

		checkoutStepTwoAssertions.verifySubtotal(expectedSubtotal);
	}

	@Then("the tax should be displayed")
	public void theTaxShouldBeDisplayed() {

		log.info("Verifying tax is displayed");

		checkoutStepTwoAssertions.verifyTaxDisplayed();
	}

	@Then("the tax should be {string}")
	public void theTaxShouldBe(String expectedTax) {

		log.info("Verifying tax: {}", expectedTax);

		checkoutStepTwoAssertions.verifyTax(expectedTax);
	}

	@Then("the total should be displayed")
	public void theTotalShouldBeDisplayed() {

		log.info("Verifying total is displayed");

		checkoutStepTwoAssertions.verifyTotalDisplayed();
	}

	@Then("the total should be {string}")
	public void theTotalShouldBe(String expectedTotal) {

		log.info("Verifying total: {}", expectedTotal);

		checkoutStepTwoAssertions.verifyTotal(expectedTotal);
	}



	@Then("the tax should not be negative")
	public void theTaxShouldNotBeNegative() {

		log.info("Verifying tax is not negative");

		checkoutStepTwoAssertions.verifyTaxIsNotNegative();
	}

	@Then("the checkout tax should not be negative")
	public void theCheckoutTaxShouldNotBeNegative() {

		log.info("Verifying Checkout Tax is not negative");

		checkoutStepTwoAssertions.verifyTaxIsNotNegative();

	}

	@Then("the checkout total should not be less than the subtotal")
	public void theCheckoutTotalShouldNotBeLessThanTheSubtotal() {

		log.info("Verifying Checkout Total is not less than Subtotal");

		checkoutStepTwoAssertions.verifyTotalNotLessThanSubtotal();

	}

	@Then("the checkout total should equal subtotal plus tax")
	public void theCheckoutTotalShouldEqualSubtotalPlusTax() {

		log.info("Verifying Checkout Total = Subtotal + Tax");

		checkoutStepTwoAssertions.verifyTotalEqualsSubtotalPlusTax();

	}

	@Then("all price summary values should be valid currency values")
	public void allPriceSummaryValuesShouldBeValidCurrencyValues() {

		log.info("Verifying checkout price summary values");

		checkoutStepTwoAssertions.verifyPriceSummaryValuesAreValid();
	}

	@Then("the checkout price summary values should be valid")
	public void theCheckoutPriceSummaryValuesShouldBeValid() {

		log.info("Verifying Checkout price summary values are valid");

		checkoutStepTwoAssertions.verifyPriceSummaryValuesAreValid();
	}

	// =========================================================
	// PRODUCT / CART CONSISTENCY
	// =========================================================

	@Then("the checkout product count should match the cart count {int}")
	public void theCheckoutProductCountShouldMatchTheCartCount(int expectedCartCount) {

		log.info("Verifying Checkout product count matches Cart count: {}", expectedCartCount);

		checkoutStepTwoAssertions.verifyCheckoutProductCountMatchesCart(expectedCartCount);

	}

	@Then("the checkout product names should match the cart")
	public void theCheckoutProductNamesShouldMatchTheCart() {

		log.info("Verifying Checkout product names match Cart");

		List<String> cartProductNames = scenarioContext.getCartProductNames();

		Assert.assertNotNull(cartProductNames, "Cart product names should be available " + "in ScenarioContext");

		log.info("Cart product names retrieved from ScenarioContext: {}", cartProductNames);

		checkoutStepTwoAssertions.verifyProductNamesMatchCart(cartProductNames);
	}

	@Then("all selected products should be displayed in Checkout Overview")
	public void all_selected_products_should_be_displayed_in_checkout_overview() {

		log.info("Verifying all selected products are displayed in Checkout Overview");

		List<String> expectedProducts = scenarioContext.getCartProductNames();

		checkoutStepTwoAssertions.verifyAllSelectedProductsDisplayed(expectedProducts);

		log.info("All selected products are displayed in Checkout Overview: {}", expectedProducts);
	}

	@Then("the Checkout Overview should contain {int} products")
	public void the_checkout_overview_should_contain_products(Integer expectedProductCount) {

		log.info("Verifying Checkout Overview contains {} products", expectedProductCount);

		Assert.assertNotNull(expectedProductCount, "Expected product count must not be null");

		Assert.assertTrue(expectedProductCount > 0, "Expected product count must be greater than 0");

		checkoutStepTwoAssertions.verifyProductCount(expectedProductCount);

		log.info("Checkout Overview contains {} products", expectedProductCount);
	}

	@Then("the following products should be displayed:")
	public void the_following_products_should_be_displayed(DataTable dataTable) {

		log.info("Verifying selected products are displayed in Checkout Overview");

		List<String> expectedProducts = dataTable.asMaps(String.class, String.class).stream()
				.map(row -> row.get("product")).collect(Collectors.toList());

		log.info("Expected products: {}", expectedProducts);

		checkoutStepTwoAssertions.verifyAllSelectedProductsDisplayed(expectedProducts);

		log.info("All expected products are displayed in Checkout Overview");
	}

	@Then("the subtotal should equal the sum of all selected product prices")
	public void the_subtotal_should_equal_the_sum_of_all_selected_product_prices() {

		log.info("Verifying Checkout Overview subtotal equals sum of all selected product prices");

		checkoutStepTwoAssertions.verifySubtotalEqualsSumOfProductPrices();

		log.info("Verified that subtotal equals the sum of all selected product prices");
	}

	@Then("the tax should be calculated from the subtotal")
	public void the_tax_should_be_calculated_from_the_subtotal() {

		log.info("Verifying tax is calculated from the subtotal");

		checkoutStepTwoAssertions.verifyTaxCalculatedFromSubtotal();

		log.info("Tax calculation from subtotal verified successfully");
	}

	@Then("the total should equal subtotal plus tax")
	public void the_total_should_equal_subtotal_plus_tax() {

		log.info("Verifying total equals subtotal plus tax");

		checkoutStepTwoAssertions.verifyTotalEqualsSubtotalPlusTax();

		log.info("Verified that total equals subtotal plus tax");
	}

	@Then("the subtotal should contain a valid dollar amount")
	public void the_subtotal_should_contain_a_valid_dollar_amount() {

	    log.info("Verifying Checkout Overview subtotal contains a valid dollar amount");

	    checkoutStepTwoAssertions.verifySubtotalContainsValidDollarAmount();

	    log.info("Checkout Overview subtotal contains a valid dollar amount");
	}
	
	@Then("the tax should contain a valid dollar amount")
	public void the_tax_should_contain_a_valid_dollar_amount() {

	    log.info("Verifying Checkout Overview tax contains a valid dollar amount");

	    checkoutStepTwoAssertions.verifyTaxContainsValidDollarAmount();

	    log.info("Checkout Overview tax contains a valid dollar amount");
	}
	
	@Then("the total should contain a valid dollar amount")
	public void the_total_should_contain_a_valid_dollar_amount() {

	    log.info("Verifying Checkout Overview total contains a valid dollar amount");

	    checkoutStepTwoAssertions.verifyTotalContainsValidDollarAmount();

	    log.info("Checkout Overview total contains a valid dollar amount");
	}
	
	@Then("the total should not be less than the subtotal")
	public void the_total_should_not_be_less_than_the_subtotal() {

	    log.info("Verifying Total is not less than Subtotal");

	    checkoutStepTwoAssertions.verifyTotalNotLessThanSubtotal();

	    log.info("Total is not less than Subtotal");
	}
	
	
//	@Then("the checkout product count should match the cart product count")
//	public void theCheckoutProductCountShouldMatchTheCartProductCount() {
//
//		log.info("Verifying checkout product count matches cart");
//
//		checkoutStepTwoAssertions.verifyCheckoutProductCountMatchesCart();
//	}

//	@Then("the checkout product names should match the cart")
//	public void theCheckoutProductNamesShouldMatchTheCart() {
//
//		log.info("Verifying checkout product names match cart");
//
//		checkoutStepTwoAssertions.verifyProductNamesMatchCart();
//	}

//	@Then("the checkout product names should match the cart")
//	public void theCheckoutProductNamesShouldMatchTheCart() {
//
//		log.info("Verifying Checkout product names match Cart");
//
//		List<String> cartProductNames = scenarioContext.get("cartProductNames", List.class);
//
//		checkoutStepTwoAssertions.verifyProductNamesMatchCart(cartProductNames);
//	}

	@Then("the checkout product prices should match the cart")
	public void theCheckoutProductPricesShouldMatchTheCart() {

		log.info("Verifying checkout product prices match cart");

		checkoutStepTwoAssertions.verifyProductPricesMatchCart();
	}

	@Then("the checkout subtotal should match the cart subtotal")
	public void theCheckoutSubtotalShouldMatchTheCartSubtotal() {

		log.info("Verifying checkout subtotal matches cart subtotal");

		checkoutStepTwoAssertions.verifySubtotalMatchesCartSubtotal();
	}

	@Then("the subtotal should match the cart subtotal")
	public void theSubtotalShouldMatchTheCartSubtotal() {
		log.info("Verifying subtotal matches Cart subtotal");
		checkoutStepTwoAssertions.verifySubtotalMatchesCartSubtotal();

		log.info("Subtotal successfully matched Cart subtotal");

	}

	// =========================================================
	// BUTTON VALIDATION
	// =========================================================

	@Then("the Finish button should be displayed")
	public void theFinishButtonShouldBeDisplayed() {

		log.info("Verifying Finish button is displayed");

		checkoutStepTwoAssertions.verifyFinishButtonDisplayed();
	}

	@Then("the Finish button should be enabled")
	public void theFinishButtonShouldBeEnabled() {

		log.info("Verifying Finish button is enabled");

		checkoutStepTwoAssertions.verifyFinishButtonEnabled();
	}

	@Then("the Cancel button should be displayed on Checkout step two page")
	public void theCancelButtonShouldBeDisplayedCheckoutStepTwo() {

		log.info("Verifying Cancel button is displayed");

		checkoutStepTwoAssertions.verifyCancelButtonDisplayed();
	}

	@Then("the Cancel button should be enabled")
	public void theCancelButtonShouldBeEnabled() {

		log.info("Verifying Cancel button is enabled");

		checkoutStepTwoAssertions.verifyCancelButtonEnabled();
	}

	// =========================================================
	// CHECKOUT ACTIONS
	// =========================================================

	@When("the user clicks the Finish button")
	public void theUserClicksTheFinishButton() {

		log.info("User clicks Finish button");

		checkoutStepTwoAction.clickFinish();
	}

	@When("the user completes the checkout")
	public void theUserCompletesTheCheckout() {

		log.info("User completes the checkout");

		checkoutStepTwoAction.completeCheckout();
	}

	@When("the user clicks the Cancel button on checkout step two page")
	public void theUserClicksTheCancelButtonOnCheckoutStepTwoPage() {

		log.info("User clicks Cancel button");

		checkoutStepTwoAction.clickCancel();
	}

	@When("the user cancels the checkout")
	public void theUserCancelsTheCheckout() {

		log.info("User cancels the checkout");

		checkoutStepTwoAction.cancelCheckout();
	}

	// =========================================================
	// NAVIGATION VALIDATION
	// =========================================================

	@Then("the user should be redirected to the Checkout Complete page")
	public void theUserShouldBeRedirectedToCheckoutCompletePage() {

		log.info("Verifying navigation to Checkout Complete page");

		checkoutStepTwoAssertions.verifyCheckoutStepTwoPageDisplayed();
	}

	@Then("the user should be redirected to the Inventory page")
	public void theUserShouldBeRedirectedToInventoryPage() {

		log.info("Verifying navigation to Inventory page");

		checkoutStepTwoAssertions.verifyInventoryPageDisplayed();
	}

	@Then("the URL should contain {string}")
	public void theUrlShouldContain(String expectedUrlPart) {

		log.info("Verifying URL contains: {}", expectedUrlPart);

		checkoutStepTwoAssertions.verifyCurrentUrlContains(expectedUrlPart);
	}

	// =========================================================
	// NEGATIVE / EDGE CASE VALIDATION
	// =========================================================

	@Then("the Checkout Step Two page should not be displayed")
	public void theCheckoutStepTwoPageShouldNotBeDisplayed() {

		log.info("Verifying Checkout Step Two page is not displayed");

		checkoutStepTwoAssertions.verifyCheckoutStepTwoPageNotDisplayed();
	}

	@Then("the checkout overview should not be empty")
	public void theCheckoutOverviewShouldNotBeEmpty() {

		log.info("Verifying checkout overview is not empty");

		checkoutStepTwoAssertions.verifyCheckoutOverviewNotEmpty();
	}

	@Then("the checkout product list should not be empty")
	public void theCheckoutProductListShouldNotBeEmpty() {

		log.info("Verifying checkout product list is not empty");

		checkoutStepTwoAssertions.verifyProductListNotEmpty();
	}

	@Then("the Finish button should not be displayed")
	public void theFinishButtonShouldNotBeDisplayed() {

		log.info("Verifying Finish button is not displayed");

		checkoutStepTwoAssertions.verifyFinishButtonNotDisplayed();
	}

	@Then("the Cancel button should not complete the order")
	public void theCancelButtonShouldNotCompleteTheOrder() {

		log.info("Verifying Cancel does not complete the order");

		checkoutStepTwoAssertions.verifyOrderNotCompleted();
	}

	// =========================================================
	// REFRESH / NAVIGATION
	// =========================================================

	@When("the user refreshes the Checkout Step Two page")
	public void theUserRefreshesTheCheckoutStepTwoPage() {

		log.info("Refreshing Checkout Step Two page");

		checkoutStepTwoAction.refreshPage();
	}

	@Then("the checkout overview should remain displayed")
	public void theCheckoutOverviewShouldRemainDisplayed() {

		log.info("Verifying checkout overview remains displayed");

		checkoutStepTwoAssertions.verifyCheckoutOverviewDisplayed();
	}

	@When("the user navigates back from Checkout Step Two")
	public void theUserNavigatesBackFromCheckoutStepTwo() {

		log.info("Navigating back from Checkout Step Two");

		checkoutStepTwoAction.navigateBack();
	}

	@When("the user navigates forward to Checkout Step Two")
	public void theUserNavigatesForwardToCheckoutStepTwo() {

		log.info("Navigating forward to Checkout Step Two");

		checkoutStepTwoAction.navigateForward();
	}

	// =========================================================
	// MULTI-PRODUCT VALIDATION
	// =========================================================

	@Then("the checkout overview should contain all selected products")
	public void theCheckoutOverviewShouldContainAllSelectedProducts() {

		log.info("Verifying all selected products are displayed");

		checkoutStepTwoAssertions.verifyAllSelectedProductsDisplayed();
	}

	@Then("the checkout overview should contain {int} selected products")
	public void theCheckoutOverviewShouldContainSelectedProducts(int expectedCount) {

		log.info("Verifying selected product count: {}", expectedCount);

		checkoutStepTwoAssertions.verifyProductCount(expectedCount);
	}

	@Then("the checkout subtotal should be calculated correctly")
	public void theCheckoutSubtotalShouldBeCalculatedCorrectly() {

		log.info("Verifying checkout subtotal calculation");

		checkoutStepTwoAssertions.verifySubtotalCalculatedCorrectly();
	}

	@Then("the checkout total should be calculated correctly")
	public void theCheckoutTotalShouldBeCalculatedCorrectly() {

		log.info("Verifying checkout total calculation");

		checkoutStepTwoAssertions.verifyTotalCalculatedCorrectly();
	}

	// =========================================================
	// E2E CHECKOUT VALIDATION
	// =========================================================

	@Then("the order should be completed successfully")
	public void theOrderShouldBeCompletedSuccessfully() {

		log.info("Verifying order was completed successfully");

		checkoutStepTwoAssertions.verifyOrderCompletedSuccessfully();
	}

	@Then("the cart should be empty after checkout")
	public void theCartShouldBeEmptyAfterCheckout() {

		log.info("Verifying cart is empty after checkout");

		checkoutStepTwoAssertions.verifyCartIsEmptyAfterCheckout();
	}

	@Given("the user has proceeded to Checkout Step Two")
	public void the_user_has_proceeded_to_checkout_step_two() {

		log.info("Proceeding to Checkout Step Two");

		PageObjectManager pageObjectManager = scenarioContext.getPageObjectManager();

		// Step 1: Open Cart
		CartAction cartAction = new CartAction(pageObjectManager.getCartPage());

		cartAction.clickCheckoutButton();

		log.info("Checkout button clicked");

		// Step 2: Enter Checkout Information
		CheckoutInformationAction checkoutInformationAction = new CheckoutInformationAction(
				pageObjectManager.getCheckoutInformationPage());

		checkoutInformationAction.enterFirstName("John");
		checkoutInformationAction.enterLastName("Doe");
		checkoutInformationAction.enterPostalCode("411042");

		log.info("Checkout information entered");

		// Step 3: Continue to Checkout Step Two
		checkoutInformationAction.clickContinue();

		log.info("Successfully proceeded to Checkout Step Two");
	}

	@Then("the Checkout Step Two URL should contain {string}")
	public void the_checkout_step_two_url_should_contain(String expectedUrlPart) {

		log.info("Verifying Checkout Step Two URL contains: {}", expectedUrlPart);

		checkoutStepTwoAssertions.verifyCurrentUrlContains(expectedUrlPart);

		log.info("Checkout Step Two URL contains: {}", expectedUrlPart);
	}

	/**
	 * Negative test: Attempt to access Checkout Step Two without completing
	 * Checkout Step One.
	 */

	@Given("the user attempts to directly open Checkout Step Two")
	public void theUserAttemptsToDirectlyOpenCheckoutStepTwo() {

		log.info("Attempting to directly open Checkout Step Two without completing Checkout Step One");

		checkoutStepTwoAction.openCheckoutStepTwoDirectly();
	}

	@Then("the user should not be able to complete checkout without valid checkout information")
	public void theUserShouldNotBeAbleToCompleteCheckoutWithoutValidCheckoutInformation() {

		log.info("Verifying that checkout cannot be completed without valid checkout information");

		checkoutStepTwoAssertions.verifyCheckoutStepTwoAccessRequiresValidCheckoutInformation();
	}

	@Then("the Checkout Overview should not contain any product")
	public void the_checkout_overview_should_not_contain_any_product() {

		log.info("Verifying that Checkout Overview does not contain any product");

		checkoutStepTwoAssertions.verifyProductCount(0);

		log.info("Verified that Checkout Overview contains no products");
	}

	@Then("the user should not reach Checkout Step Two")
	public void the_user_should_not_reach_checkout_step_two() {

		log.info("Verifying that Checkout Step Two is not reached");

		checkoutStepTwoAssertions.verifyCheckoutStepTwoNotAvailableForEmptyCart();

		log.info("Verified that Checkout Step Two was not reached");
	}

	@Then("the checkout completion behavior should be handled correctly")
	public void the_checkout_completion_behavior_should_be_handled_correctly() {

		log.info("Verifying checkout completion behavior is handled correctly");

		checkoutCompleteAssertions.verifyCheckoutCompletionBehavior();

		log.info("Checkout completion behavior verified successfully");
	}

	@Then("the Checkout Step Two page should remain displayed")
	public void the_checkout_step_two_page_should_remain_displayed() {

		log.info("Verifying Checkout Step Two page remains displayed");

		checkoutStepTwoAssertions.verifyCheckoutStepTwoPageReady();

		log.info("Checkout Step Two page remains displayed successfully");
	}

//	@Then("the selected product should remain displayed")
//	public void the_selected_product_should_remain_displayed() {
//
//	    log.info("Verifying selected product remains displayed on Checkout Step Two");
//
//	    checkoutStepTwoAssertions.verifyProductCount(1);
//
//	    log.info("Selected product remains displayed successfully");
//	}

	@Then("the selected product should remain displayed")
	public void the_selected_product_should_remain_displayed() {

		log.info("Verifying selected products remain displayed on Checkout Step Two");

		checkoutStepTwoAssertions.verifySelectedProductsDisplayed();

		log.info("Selected products remain displayed successfully");
	}

	@When("the user navigates back using the browser")
	public void the_user_navigates_back_using_the_browser() {

		log.info("User navigates back using the browser");

		checkoutStepTwoAction.navigateBackUsingBrowser();

		log.info("Browser back navigation completed");
	}

	@Then("the user should be returned to the previous checkout page")
	public void the_user_should_be_returned_to_the_previous_checkout_page() {

		log.info("Verifying user is returned to the previous checkout page");

		checkoutStepTwoAssertions.verifyPreviousCheckoutPageDisplayed();

		log.info("Previous checkout page is displayed successfully");
	}

	@Then("the user should be returned to Checkout Step One")
	public void the_user_should_be_returned_to_checkout_step_one() {

		log.info("Verifying user is returned to Checkout Step One");

		String currentUrl = checkoutStepTwoPage.getCurrentUrl();

		log.info("Current URL after browser back: {}", currentUrl);

		Assert.assertTrue(currentUrl.contains("/checkout-step-one.html"),
				"Expected Checkout Step One, but current URL is: " + currentUrl);

		log.info("User successfully returned to Checkout Step One");
	}

	/**
	 * * Navigate back from Checkout Step Two using browser Back.
	 */
	@Given("the user has navigated back from Checkout Step Two")
	public void the_user_has_navigated_back_from_checkout_step_two() {

		log.info("Navigating back from Checkout Step Two using browser Back");

		checkoutStepTwoAction.navigateBack();

		log.info("User has navigated back from Checkout Step Two");

	}

	/**
	 * * Navigate forward using browser Forward.
	 */
	@When("the user navigates forward using the browser")
	public void the_user_navigates_forward_using_the_browser() {

		log.info("Navigating forward using browser Forward");

		checkoutStepTwoAction.navigateForward();

		log.info("Browser forward navigation completed");

	}

	/** * Verify that Checkout Step Two is displayed. */
	@Then("Checkout Step Two should be displayed")
	public void checkout_step_two_should_be_displayed() {
		log.info("Verifying Checkout Step Two is displayed");

		checkoutStepTwoAssertions.verifyCheckoutStepTwoPageReady();

		log.info("Checkout Step Two is displayed successfully");

	}

	/** * Double-click the Finish button. */
	@When("the user double clicks the Finish button")
	public void the_user_double_clicks_the_finish_button() {
		log.info("STEP: User double clicks the Finish button");
		checkoutStepTwoAction.doubleClickFinishButton();
		log.info("STEP: Finish button double-click completed");

	}

	/** * Verify that only one checkout completion was processed. */
	@Then("only one checkout completion should be processed")
	public void only_one_checkout_completion_should_be_processed() {
		log.info("STEP: Verify only one checkout completion was processed");

		checkoutCompleteAssertions.verifyOnlyOneCheckoutCompletionProcessed();

		log.info("STEP: Only one checkout completion was processed");

	}

	@Then("the order should not be completed")
	public void the_order_should_not_be_completed() {
		log.info("STEP: Verify that the order was not completed");

		checkoutCompleteAssertions.verifyOrderNotCompleted();

		log.info("STEP: Order was not completed successfully");

	}

	@Then("the customer checkout information should be displayed as review information")
	public void the_customer_checkout_information_should_be_displayed_as_review_information() {

		log.info("STEP: Verify customer checkout information is displayed as review information");

		checkoutStepTwoAssertions.verifyCustomerCheckoutInformationDisplayedAsReviewInformation();

		log.info("STEP: Customer checkout information is displayed correctly as review information");
	}

	@Then("the user should not be able to edit checkout information on Checkout Step Two")
	public void the_user_should_not_be_able_to_edit_checkout_information_on_checkout_step_two() {

		log.info("STEP: Verify checkout information cannot be edited on Checkout Step Two");

		checkoutStepTwoAssertions.verifyCheckoutInformationCannotBeEdited();

		log.info("STEP: Checkout information cannot be edited on Checkout Step Two");
	}

	@Then("the same product should be displayed in Checkout Overview")
	public void the_same_product_should_be_displayed_in_checkout_overview() {

		log.info("STEP: Verify same product is displayed in Checkout Overview");

		checkoutStepTwoAssertions.verifySameProductDisplayedInCheckoutOverview();

		log.info("STEP: Same product verified in Checkout Overview");

	}

	@Then("the product {string} price should match the Cart price")
	public void the_product_price_should_match_the_cart_price(String productName) {

		log.info("STEP: Verify price of '{}' in Checkout Overview matches Cart price", productName);

		checkoutStepTwoAssertions.verifyProductPriceMatchesCartPrice(productName);

		log.info("Price of '{}' matches the Cart price", productName);
	}

	@Then("the Checkout Overview item count should be {int}")
	public void the_checkout_overview_item_count_should_be(Integer expectedCount) {

		log.info("STEP: Verify Checkout Overview item count is {}", expectedCount);

		checkoutStepTwoAssertions.verifyProductCount(expectedCount);

		log.info("Checkout Overview item count verified successfully: {}", expectedCount);
	}

	@Then("the Checkout Overview item count should match the Cart item count")
	public void the_checkout_overview_item_count_should_match_the_cart_item_count() {

		log.info("STEP: Verify Checkout Overview item count matches Cart item count");

		checkoutStepTwoAssertions.verifyProductCountMatchesCartCount();

		log.info("Checkout Overview item count matches Cart item count");
	}

	// ============================================================
	// Checkout Overview URL
	// ============================================================

	@Then("the Checkout Overview URL should contain {string}")
	public void the_checkout_overview_url_should_contain(String expectedUrlPart) {

		log.info("Verifying Checkout Overview URL contains: {}", expectedUrlPart);

		checkoutStepTwoAssertions.verifyCurrentUrlContains(expectedUrlPart);

		log.info("Checkout Overview URL validation completed");
	}

	// ============================================================
	// Checkout Overview Subtotal
	// ============================================================

	@Then("the Checkout Overview subtotal should match the sum of Cart item prices")
	public void the_checkout_overview_subtotal_should_match_the_sum_of_cart_item_prices() {

		log.info("Verifying Checkout Overview subtotal matches sum of Cart item prices");

		checkoutStepTwoAssertions.verifySubtotalMatchesCartTotalDouble();
		

		log.info("Checkout Overview subtotal matches Cart item prices successfully");
	}

	// ============================================================ 
	// CHECKOUT STEP TWO 
	// ============================================================ 
	@Then("the Checkout Overview page should be displayed") 
	public void the_checkout_overview_page_should_be_displayed() { 
		log.info("Verifying Checkout Overview page"); 
		
		checkoutStepTwoAssertions.verifyCheckoutStepTwoPageReady(); 
		
		log.info("Checkout Overview page is displayed successfully"); 
		
	}
	
	@Then("the tax amount should not be negative")
	public void the_tax_amount_should_not_be_negative() {

	    log.info("Verifying tax amount is not negative");

	    checkoutStepTwoAssertions.verifyTaxIsNotNegative();

	    log.info("Tax amount is not negative");
	}
	
	@Then("the Finish button text should be {string}")
	public void the_finish_button_text_should_be(String expectedText) {

	    log.info("Verifying Finish button text is: '{}'", expectedText);

	    checkoutStepTwoAssertions.verifyFinishButtonText(expectedText);

	    log.info("Finish button text verification completed");
	}
	
	@Then("the Cancel button text should be {string}")
	public void the_cancel_button_text_should_be(String expectedText) {

	    log.info("Verifying Cancel button text is: '{}'", expectedText);

	    checkoutStepTwoAssertions.verifyCancelButtonText(expectedText);

	    log.info("Cancel button text verification completed");
	}
	
	@Then("Payment Information should be visible")
	public void payment_information_should_be_visible() {

	    log.info("Verifying Payment Information is visible");

	    checkoutStepTwoAssertions.verifyPaymentInformationVisible();

	    log.info("Payment Information is visible");
	}
	
	@Then("Shipping Information should be visible")
	public void shipping_information_should_be_visible() {

	    checkoutStepTwoAssertions.verifyShippingInformationVisible();
	}
	
	@Then("subtotal should be visible")
	public void subtotal_should_be_visible() {
	    checkoutStepTwoAssertions.verifySubtotalVisible();
	}

	@Then("tax should be visible")
	public void tax_should_be_visible() {
	    checkoutStepTwoAssertions.verifyTaxVisible();
	}

	@Then("total should be visible")
	public void total_should_be_visible() {
	    checkoutStepTwoAssertions.verifyTotalVisible();
	}
	
	
	

//	@Then("the Checkout Complete page should be displayed")
//	public void the_checkout_complete_page_should_be_displayed() {
//		log.info("Verifying Checkout complete page displayed");
//
//		checkoutStepTwoAssertions.verifyCheckoutCompletePageDisplayed();
//	}
	
}