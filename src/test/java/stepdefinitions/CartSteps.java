
package stepdefinitions;

import actions.CartAction;
import actions.InventoryActions;
import assertions.CartAssertions;
import assertions.InventoryAssertions;
import context.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class CartSteps {

	private static final Logger log = LoggerFactory.getLogger(CartSteps.class);

	private final InventoryActions inventoryActions;
	private final InventoryAssertions inventoryAssertions;

	private final ScenarioContext scenarioContext;

	private final CartAction cartAction;
	private final CartAssertions cartAssertions;

	private final CartPage cartPage;

	/**
	 * Constructor injection using ScenarioContext.
	 *
	 * @param context ScenarioContext containing PageObjectManager
	 */
	public CartSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.scenarioContext = context;

		this.inventoryActions = new InventoryActions(scenarioContext.getPageObjectManager());

		this.inventoryAssertions = new InventoryAssertions(scenarioContext.getPageObjectManager().getInventoryPage());

		this.cartAction = new CartAction(scenarioContext.getPageObjectManager().getCartPage());
		this.cartAssertions = new CartAssertions(scenarioContext.getPageObjectManager().getCartPage(), context);

		this.cartPage = scenarioContext.getPageObjectManager().getCartPage();
		log.debug("InventoryCartSteps initialized");
	}

	/**
	 * * Stores all product names currently displayed * in the Cart into
	 * ScenarioContext.
	 */
	@When("the user stores the cart product names")
	public void storeCartProductNames() {

		log.info("Storing Cart product names in ScenarioContext");

		List<String> cartProductNames = cartPage.getAllProductNames();

		Assert.assertNotNull(cartProductNames, "Cart product names must not be null");

		Assert.assertFalse(cartProductNames.isEmpty(), "Cart should contain at least one product");

		scenarioContext.setCartProductNames(cartProductNames);

		log.info("Cart product names stored successfully: {}", cartProductNames);

	}

	@When("the user stores the cart product prices")
	public void storeCartProductPrices() {

		log.info("Getting product prices from Cart");

		List<String> cartProductPrices = cartPage.getAllProductPrices();

		Assert.assertNotNull(cartProductPrices, "Cart product prices must not be null");

		Assert.assertFalse(cartProductPrices.isEmpty(), "Cart product prices should not be empty");

		scenarioContext.setCartProductPrices(cartProductPrices);

		log.info("Cart product prices stored in ScenarioContext: {}", cartProductPrices);
	}

	// ============================================================
	// ADD PRODUCT TO CART
	// ============================================================

	@When("the user adds {string} to the cart")
	public void theUserAddsToTheCart(String productName) {

		log.info("Adding product to cart: {}", productName);

		inventoryActions.addProductToCart(productName);

		log.info("Product added successfully: {}", productName);
	}

	// ============================================================
	// REMOVE PRODUCT FROM CART
	// ============================================================

	@When("the user removes {string} from the cart")
	public void theUserRemovesFromTheCart(String productName) {

		log.info("Removing product from cart: {}", productName);

		cartAction.removeProductFromCart(productName);

		log.info("Product removed successfully: {}", productName);
	}

	// ============================================================
	// OPEN SHOPPING CART
	// ============================================================

	@When("the user opens the shopping cart")
	public void theUserOpensTheShoppingCart() {

		log.info("Opening shopping cart");

		inventoryActions.openShoppingCart();

		log.info("Shopping cart opened successfully");
	}

	@When("the user click on shopping cart Icon and shopping cart page open")
	public void userClickOnShoppingCartIcon() {
		log.info("Opening shopping cart");

		inventoryActions.openCartPageByClickingOnCartIcon();

		log.info("Shopping cart opened successfully");
	}

	@Then("the cart should not contain {int} products")
	public void the_cart_should_not_contain_products(int expectedCount) {

		log.info("==================================================");
		log.info("STEP: Verify cart should not contain {} products", expectedCount);
		log.info("==================================================");

		cartAssertions.verifyCartItemCountIsNot(expectedCount);

		log.info("Cart correctly does not contain {} products", expectedCount);
	}

	// ============================================================
	// VERIFY CART BADGE COUNT
	// ============================================================

	@Then("the cart badge should show {int}")
	public void theCartBadgeShouldShow(int expectedCount) {

		log.info("Verifying cart badge count. Expected: {}", expectedCount);

		inventoryAssertions.verifyCartBadgeCount(expectedCount);

		log.info("Cart badge count verified successfully. Expected: {}", expectedCount);
	}

	// ============================================================
	// VERIFY CART BADGE NOT DISPLAYED
	// ============================================================

	@Then("the cart badge should not be displayed")
	public void theCartBadgeShouldNotBeDisplayed() {

		log.info("Verifying cart badge is not displayed");

		inventoryAssertions.verifyCartBadgeNotDisplayed();

		log.info("Cart badge is not displayed as expected");
	}

	// ============================================================
	// VERIFY SHOPPING CART PAGE
	// ============================================================

	@Then("the shopping cart should be displayed")
	@Then("the shopping cart page should be displayed")
	public void theShoppingCartPageShouldBeDisplayed() {

		log.info("Verifying shopping cart page is displayed");

		inventoryAssertions.verifyShoppingCartPageDisplayed();

		log.info("Shopping cart page is displayed successfully");
	}

	@When("the user adds the first product to the cart")
	public void theUserAddsTheFirstProductToTheCart() {

		log.info("Adding the first product to the cart");

		inventoryActions.addFirstProductToCart();

		cartAction.storeCartProductNames();

		log.info("First product added to cart successfully");
	}

//	@When("the user stores the cart product names")
//	public void theUserStoresTheCartProductNames() {
//
//		cartAction.storeCartProductNames();
//	}

	@When("the user clicks Continue Shopping")
	public void theUserClicksContinueShopping() {

		log.info("User clicks Continue Shopping");

		cartAction.clickContinueShopping();

		log.info("Continue Shopping clicked successfully");
	}

	@When("the user stores the cart subtotal")
	public void storeCartSubtotal() {

		log.info("Calculating and storing Cart subtotal");

		String cartSubtotal = cartPage.getCartSubtotal();

		Assert.assertNotNull(cartSubtotal, "Cart subtotal must not be null");

		Assert.assertFalse(cartSubtotal.isEmpty(), "Cart subtotal must not be empty");

		scenarioContext.setCartSubtotal(cartSubtotal);

		log.info("Cart subtotal stored in ScenarioContext: {}", cartSubtotal);

	}

	@Then("the cart should contain {int} product")
	public void theCartShouldContainProduct(int expectedCount) {

		log.info("Verifying cart product count. Expected count: {}", expectedCount);

		cartAssertions.verifyCartItemCounts(expectedCount);

		log.info("Cart product count verified successfully. Expected count: {}", expectedCount);
	}

	@Then("the cart should contain {int} products")
	public void theCartShouldContainProducts(int expectedCount) {

		log.info("Verifying cart product count. Expected count: {}", expectedCount);

		cartAssertions.verifyCartItemCountInCart(expectedCount);

		log.info("Cart product count verified successfully. Expected count: {}", expectedCount);
	}

	@Then("the cart badge should not show {int}")
	public void the_cart_badge_should_not_show(int expectedCount) {

		log.info("==================================================");
		log.info("STEP: Verify cart badge should not show {}", expectedCount);
		log.info("==================================================");

		cartAssertions.verifyCartBadgeDoesNotShow(expectedCount);

		log.info("Cart badge validation passed. Badge does not show {}", expectedCount);
	}

	@Then("the product {string} should be displayed in the cart")
	public void theProductShouldBeDisplayedInTheCart(String productName) {

		log.info("Verifying product is displayed in the cart: {}", productName);

		cartAssertions.verifyProductDisplayed(productName);

		log.info("Product is displayed in the cart successfully: {}", productName);
	}

	@Then("the shopping cart URL should contain {string}")
	public void theShoppingCartUrlShouldContain(String expectedUrlPart) {
		log.info("Verifying shopping cart URL contains: {}", expectedUrlPart);

		cartAssertions.verifyShoppingCartUrlContains(expectedUrlPart);

		log.info("Shopping cart URL validation passed. Expected part: {}, Actual URL: {}", expectedUrlPart);

	}

	@Then("the shopping cart page heading should be {string}")
	public void the_shopping_cart_page_heading_should_be(String expectedPageTitle) {

		log.info("Verifying Cart Page Title: {}", expectedPageTitle);

		cartAssertions.verifyCartPageHeading(expectedPageTitle);

		log.info("Cart Page Title: {}", expectedPageTitle);
	}

	@Then("the cart list should be displayed")
	public void theCartListShouldBeDisplayed() {

		log.info("Verifying that the cart list is displayed");

		cartAssertions.verifyCartListDisplayed();

		log.info("Cart list is displayed successfully");
	}

	@Then("the shopping cart icon should be displayed")
	public void theShoppingCartIconShouldBeDisplayed() {

		log.info("Verifying that the shopping cart icon is displayed");

		cartAssertions.verifyShoppingCartIconDisplayed();

		log.info("Shopping cart icon is displayed successfully");
	}

	@Then("the cart should be empty")
	public void theCartShouldBeEmpty() {

		log.info("Verifying that the cart is empty");

		cartAssertions.verifyCartIsEmpty();

		log.info("Cart is empty as expected");
	}

//	@Then("the cart should contain {int} products")
//	public void theCartShouldContainZeroProducts(Integer productCount) {
//
//		log.info("Verifying that the cart contains 0 products");
//
//		cartAssertions.verifyCartItemCount(productCount);
//
//		log.info("Cart contains 0 products as expected");
//	}

	@Then("the price of {string} should be {string}")
	public void thePriceOfShouldBe(String productName, String expectedPrice) {

		log.info("Verifying price of product: {}. Expected price: {}", productName, expectedPrice);

		cartAssertions.verifyProductPrice(productName, expectedPrice);

		log.info("Product price verified successfully. Product: {}, Expected price: {}", productName, expectedPrice);
	}

	@Then("the quantity of {string} should be {int}")
	public void theQuantityOfShouldBe(String productName, int expectedQuantity) {

		log.info("Verifying quantity of product: {}. Expected quantity: {}", productName, expectedQuantity);
		cartAssertions.verifyProductQuantity(productName, expectedQuantity);
		log.info("Product quantity verified successfully. Product: {}, Expected quantity: {}", productName,
				expectedQuantity);

	}

	@Then("the product description should be displayed for {string}")
	public void theProductDescriptionShouldBeDisplayedFor(String productName) {
		log.info("Verifying product description is displayed for: {}", productName);
		cartAssertions.verifyProductDescriptionDisplayed(productName);
		log.info("Product description is displayed successfully for: {}", productName);

	}

	@Then("the Remove button should be displayed for {string}")
	public void theRemoveButtonShouldBeDisplayedFor(String productName) {
		log.info("Verifying Remove button is displayed for product: {}", productName);
		cartAssertions.verifyRemoveButtonDisplayed(productName);
		log.info("Remove button is displayed successfully for product: {}", productName);

	}

//	@When("the user adds all available products to the cart")
//	public void theUserAddsAllAvailableProductsToTheCart() {
//		log.info("Adding all available products to the cart");
//		cartAction.addAllProductsToCart();
//		log.info("All available products added to the cart successfully");
//
//	}

	@When("the user adds all available products to the cart")
	public void the_user_adds_all_available_products_to_the_cart() {

		log.info("==================================================");
		log.info("STEP: User adds all available products to the cart");
		log.info("==================================================");

		cartAction.addAllProductsToCartInventory();

		log.info("All available products have been added to the cart successfully");
	}

	@When("the user removes the last product from cart")
	public void the_user_removes_the_last_product_from() {

		log.info("==================================================");
		log.info("STEP: User removes the last product from the cart");
		log.info("==================================================");

		String lastProductName = cartAction.getLastProductName();

		log.info("Last product found in cart: '{}'", lastProductName);

		cartAction.removeProductFromCart(lastProductName);

		log.info("Last product '{}' removed successfully", lastProductName);
	}

	@Then("the product {string} should not be displayed in the cart")
	public void theProductShouldNotBeDisplayedInTheCart(String productName) {

		log.info("Verifying product '{}' is not displayed in the cart", productName);

		cartAssertions.verifyProductIsNotDisplayedInCart(productName);

		log.info("Product '{}' is not displayed in the cart as expected", productName);
	}

	@Then("the Checkout button should be displayed")
	public void theCheckoutButtonShouldBeDisplayed() {

		log.info("Verifying Checkout button is displayed on Cart page");

		cartAssertions.verifyCheckoutButtonIsDisplayed();

		log.info("Checkout button is displayed successfully");
	}

	@When("the user clicks Checkout")
	public void theUserClicksCheckout() {

		log.info("User is clicking the Checkout button");

		cartAction.clickCheckout();

		log.info("User clicked the Checkout button successfully");
	}

	@When("the user refreshes the shopping cart page")
	public void the_user_refreshes_the_shopping_cart_page() {

		log.info("==================================================");
		log.info("STEP: User refreshes the Shopping Cart page");
		log.info("==================================================");

		// Refresh the current Shopping Cart page
		cartAction.refreshCartPage();

		log.info("Shopping Cart page refreshed successfully");
	}

	@When("the user navigates to the shopping cart")
	public void the_user_navigates_to_the_shopping_cart() {

		log.info("==================================================");
		log.info("STEP: User navigates to the Shopping Cart");
		log.info("==================================================");

		cartAction.clickShoppingCart();

		log.info("Successfully navigated to the Shopping Cart");
		log.info("Current URL: {}", cartAction.getCurrentUrl());
	}

	@When("the user attempts to remove {string}")
	public void the_user_attempts_to_remove(String productName) {

		log.info("==================================================");
		log.info("STEP: User attempts to remove product: {}", productName);
		log.info("==================================================");

		cartAction.removeProduct(productName);

		log.info("Remove product action completed for: {}", productName);
	}

	@Then("the cart should remain empty")
	public void the_cart_should_remain_empty() {

		log.info("==================================================");
		log.info("STEP: Verify cart remains empty");
		log.info("==================================================");

		cartAssertions.verifyCartItemCount(0);

		log.info("Cart is empty as expected");
	}

	@When("the user attempts to remove {string} again")
	public void the_user_attempts_to_remove_again(String productName) {

		log.info("==================================================");
		log.info("STEP: User attempts to remove product again: {}", productName);
		log.info("==================================================");

		cartAction.removeProduct(productName);

		log.info("Second remove attempt completed for product: {}", productName);
	}

	@When("the user attempts to remove {string} from cart again")
	public void the_user_attempts_to_remove_from_cart(String productName) {

		log.info("==================================================");
		log.info("STEP: User attempts to remove product again: {}", productName);
		log.info("==================================================");

		cartAction.removeProductFromCart(productName);

		log.info("Second remove attempt completed for product: {}", productName);
	}

	@Then("the price of {string} should not be {string}")
	public void the_price_of_should_not_be(String productName, String expectedPrice) {

		log.info("==================================================");
		log.info("STEP: Verify price of '{}' is not '{}'", productName, expectedPrice);
		log.info("==================================================");

		cartAssertions.verifyProductPriceIsNot(productName, expectedPrice);

		log.info("Verified that price of '{}' is not '{}'", productName, expectedPrice);
	}

	@Then("the quantity of {string} should not be {int}")
	public void the_quantity_of_should_not_be(String productName, int expectedQuantity) {

		log.info("==================================================");
		log.info("STEP: Verify quantity of '{}' should not be {}", productName, expectedQuantity);
		log.info("==================================================");

		cartAssertions.verifyProductQuantityIsNot(productName, expectedQuantity);

		log.info("Verified quantity of '{}' is not {}", productName, expectedQuantity);
	}

//	@Then("the checkout page should be displayed")
//	public void theCheckoutPageShouldBeDisplayed() {
//
//	    log.info("Verifying that the Checkout page is displayed");
//
//	    cartAssertions.verifyCheckoutPageIsDisplayed();
//
//	    log.info("Checkout page is displayed successfully");
//	}

	@When("the cart is empty")
	public void the_cart_is_empty() {

		log.info("==================================================");
		log.info("STEP: Verify that the cart is empty");
		log.info("==================================================");

		cartAssertions.verifyCartItemCount(0);

		log.info("Cart is empty. No products are present.");
	}

	@When("the user attempts to checkout")
	public void the_user_attempts_to_checkout() {

		log.info("==================================================");
		log.info("STEP: User attempts to checkout");
		log.info("==================================================");

		cartAction.clickCheckout();

		log.info("Checkout action attempted successfully.");
	}

	@Then("the application should handle the empty cart checkout appropriately")
	public void the_application_should_handle_the_empty_cart_checkout_appropriately() {

		log.info("==================================================");
		log.info("STEP: Verify empty cart checkout is handled appropriately");
		log.info("==================================================");

		cartAssertions.verifyEmptyCartCheckoutHandled();

		log.info("Empty cart checkout was handled appropriately.");
	}

	@When("the user adds one product to the cart")
	public void the_user_adds_one_product_to_the_cart() {

		log.info("==================================================");
		log.info("STEP: User adds one product to the cart");
		log.info("==================================================");

		String productName = "Sauce Labs Backpack";

		cartAction.addProductToCart(productName);

		log.info("Product '{}' added to the cart successfully", productName);
	}

	@When("the user removes the first product")
	public void the_user_removes_the_first_product() {

		log.info("==================================================");
		log.info("STEP: User removes the first product from the cart");
		log.info("==================================================");

		String firstProductName = cartAction.getFirstProductName();

		log.info("First product found in cart: '{}'", firstProductName);

		cartAction.removeProduct(firstProductName);

		log.info("First product '{}' removed from the cart successfully", firstProductName);
	}

//	@When("the user removes the last product")
//	public void the_user_removes_the_last_product() {
//
//		log.info("==================================================");
//		log.info("STEP: User removes the last product from the cart");
//		log.info("==================================================");
//
//		String lastProductName = cartAction.getLastProductName();
//
//		log.info("Last product found in cart: '{}'", lastProductName);
//
//		cartAction.removeProductFromCart(lastProductName);
//
//		log.info("Last product '{}' removed from the cart successfully", lastProductName);
//	}

	@When("the user removes the last product")
	public void the_user_removes_the_last_product() {

		log.info("==================================================");
		log.info("STEP: User removes the last product");
		log.info("==================================================");

		String lastProductName = cartAction.getLastProductName();

		log.info("Last product identified: '{}'", lastProductName);

		cartAction.removeProductFromCart(lastProductName);

		log.info("Last product '{}' removed successfully", lastProductName);
	}

	@When("the user removes every product from the cart")
	public void the_user_removes_every_product_from_the_cart() {

		log.info("User removes every product from the cart");

		cartAction.removeAllProducts();

		log.info("All products have been removed from the cart");
	}

	@When("the user navigates to the cart")
	public void the_user_navigates_to_the_cart() {

		log.info("User navigates to the cart");

		cartAction.navigateToCart();

		log.info("User successfully navigated to the cart");
	}

	// =========================================================
	// CART PRECONDITION
	// =========================================================

	@Given("the user has at least one product in the cart")
	public void theUserHasAtLeastOneProductInTheCart() {

		log.info("Verifying that the cart contains at least one product");

		// Verify cart contains at least one product
		cartAssertions.verifyCartContainsProducts();

		log.info("Cart contains at least one product");
	}

	// =========================================================
	// CHECKOUT NAVIGATION
	// =========================================================

	@When("the user clicks the Checkout button")
	public void theUserClicksTheCheckoutButton() {

		log.info("Clicking Checkout button from Cart page");

		cartAction.clickCheckout();

		log.info("Checkout button clicked successfully");
	}

	@Then("the Cart page should be displayed")
	public void the_cart_page_should_be_displayed() {

		log.info("Verifying that the Cart page is displayed");

		cartAssertions.verifyCartPageDisplayed();

		log.info("Cart page is displayed successfully");
	}

	@Given("the user has no products in the cart")
	public void the_user_has_no_products_in_the_cart() {

		log.info("Verifying that the user has no products in the cart");

		cartAction.openCartPage();

		cartAction.removeAllProducts();

		log.info("Verified that the cart contains no products");
	}

	@Given("the user proceeds through checkout")
	public void the_user_proceeds_through_checkout() {

		log.info("Proceeding through checkout with an empty cart");

		cartAction.clickCheckout();

		log.info("Checkout button clicked");
	}

	@When("the user attempts to proceed through checkout")
	public void the_user_attempts_to_proceed_through_checkout() {

		log.info("Attempting to proceed through checkout with an empty cart");

		cartAction.clickCheckout();

		log.info("Checkout action attempted");
	}



	@Given("the user has verified the product {string} in the Cart")
	public void the_user_has_verified_the_product_in_the_cart(String productName) {

		log.info("Verifying product '{}' in Cart", productName);

		cartAssertions.verifyProductDisplayedInCart(productName);

		scenarioContext.set("productName", productName);

		log.info("Product '{}' stored in ScenarioContext", productName);
	}
	
	@Given("the user has added the following product to the cart:")
	public void the_user_has_added_the_following_product_to_the_cart(DataTable dataTable) {

		log.info("STEP: User adds product to the cart");

		List<String> products = dataTable.asMaps(String.class, String.class).stream().map(row -> row.get("product"))
				.toList();

		for (String productName : products) {

			log.info("Adding product '{}' to cart", productName);

			cartAction.addProductToCart(productName);

			cartAssertions.verifyProductDisplayedInCart(productName);

			log.info("Product '{}' successfully added to cart", productName);
		}
	}

	@Then("the product {string} price should be captured from the Cart")
	public void the_product_price_should_be_captured_from_the_cart(String productName) {

		log.info("STEP: Capture price of '{}' from Cart", productName);

		cartAssertions.verifyAndCaptureProductPrice(productName);

		log.info("Price of '{}' successfully captured from Cart", productName);
	}
	
	@Then("the Cart item count should be {int}")
	public void the_cart_item_count_should_be(Integer expectedCount) {

		log.info("STEP: Verify Cart item count is {}", expectedCount);

		cartAssertions.verifyCartItemCount(expectedCount);

		log.info("Cart item count verified successfully: {}", expectedCount);
	}
	
	@Then("the Cart item count should be captured")
	public void the_cart_item_count_should_be_captured() {

	    log.info("STEP: Capture Cart item count");

	    cartAssertions.verifyAndCaptureCartItemCount();

	    log.info("Cart item count captured successfully");
	}
	
	// ============================================================
    // Cart Item Prices
    // ============================================================

    @Then("the user should verify the Cart item prices")
    public void the_user_should_verify_the_cart_item_prices() {

        log.info("Verifying Cart item prices");

        cartAssertions.verifyCartItemPrices();

        log.info("Cart item prices verified successfully");
    }
	
	// ============================================================ 
	// CHECKOUT STEP ONE 
	// ============================================================ 
	@When("the user proceeds to Checkout") 
	public void the_user_proceeds_to_checkout() { 
		log.info("User proceeds to Checkout"); 
			
		cartAction.clickCheckoutButton(); 
			
		log.info("Checkout Step One page opened"); 
			
	}
	
	@Then("the shopping cart badge should not be displayed")
	public void the_shopping_cart_badge_should_not_be_displayed() {

	    log.info("Verifying shopping cart badge is not displayed");

	    cartAssertions.verifyCartBadgeNotDisplayed();

	    log.info("Shopping cart badge is not displayed");
	}
	
	@Then("the shopping cart should be empty")
	public void the_shopping_cart_should_be_empty() {

	    log.info("Verifying shopping cart is empty");

	    cartAssertions.verifyShoppingCartIsEmpty();

	    log.info("Shopping cart is empty");
	}
	
	@Given("the shopping cart is empty")
	public void the_shopping_cart_is_empty() {

	    log.info("Verifying that the shopping cart is empty");

	    cartAssertions.verifyShoppingCartIsEmpty();

	    log.info("Shopping cart is confirmed to be empty");
	}
	
	@When("the user attempts to proceed to Checkout Step One")
	public void the_user_attempts_to_proceed_to_checkout_step_one() {

	    log.info("Attempting to proceed to Checkout Step One");

	    cartAction.clickCheckout();

	    log.info("Checkout button action completed");
	}
	
	
	@Then("the user should remain on the Shopping Cart page")
	public void the_user_should_remain_on_the_shopping_cart_page() {

	    log.info("Verifying user remains on Shopping Cart page");

	    cartAssertions.verifyShoppingCartPageDisplayed();

	    log.info("User remains on Shopping Cart page");
	}
	
	
	
	
	@Given("the user is on the Shopping Cart page")
	public void the_user_is_on_the_shopping_cart_page() {

	    log.info("Navigating user to the Shopping Cart page");

	    cartAction.navigateToShoppingCart();

	    log.info("Verifying user is on the Shopping Cart page");

	    cartAssertions.verifyShoppingCartPageDisplayed();

	    log.info("User is successfully on the Shopping Cart page");
	}
	

}
