
package assertions;

import org.testng.Assert;

import context.ScenarioContext;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.CartPage;
import pages.CheckoutInformationPage;
import utils.WaitUtils;

public class CartAssertions {

	private static final Logger log = LoggerFactory.getLogger(CartAssertions.class);

	private final CartPage cartPage;
	// private final CheckoutInformationPage checkoutInformationPage;

	private final ScenarioContext scenarioContext;

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	public CartAssertions(CartPage cartPage, ScenarioContext scenarioContext) {

		if (cartPage == null) {
			throw new IllegalArgumentException("CartPage must not be null");
		}

		this.cartPage = cartPage;
		this.scenarioContext = scenarioContext;

		log.debug("CartAssertions initialized");
	}

	// =========================================================
	// PAGE ASSERTIONS
	// =========================================================

	/**
	 * Verifies that Cart page is displayed.
	 */
	public void verifyCartPageDisplayed() {

		log.info("Verifying Cart page is displayed");

		Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");
	}

	public void verifyShoppingCartIconDisplayed() {

		log.info("Checking whether the shopping cart icon is displayed");

		boolean isDisplayed = cartPage.isShoppingCartIconDisplayed();

		Assert.assertTrue(isDisplayed, "Shopping cart icon should be displayed, but it was not displayed");

		log.info("Shopping cart icon is displayed successfully");
	}

	/**
	 * Verifies Cart page title.
	 */
	public void verifyPageTitle(String expectedTitle) {

		log.info("Verifying Cart page title. Expected: {}", expectedTitle);

		Assert.assertEquals(cartPage.getPageTitle(), expectedTitle, "Cart page title is incorrect");
	}

	/**
	 * Verifies Cart page heading.
	 */
	public void verifyCartPageHeading(String expectedHeading) {

		log.info("Verifying Cart page heading. Expected: {}", expectedHeading);

		String actualHeading = cartPage.getCartPageHeading();

		log.info("Cart page heading. Expected: {}, Actual: {}", expectedHeading, actualHeading);

		Assert.assertEquals(actualHeading, expectedHeading, "Cart page heading is incorrect");
	}

	/**
	 * Verifies cart list is displayed.
	 */
	public void verifyCartListDisplayed() {

		log.info("Verifying cart list is displayed");

		Assert.assertTrue(cartPage.isCartListDisplayed(), "Cart list should be displayed");
	}

	/**
	 * Verifies page title is displayed.
	 */
	public void verifyPageTitleDisplayed() {

		log.info("Verifying Cart page title is displayed");

		Assert.assertTrue(cartPage.isPageTitleDisplayed(), "Cart page title should be displayed");
	}

	// =========================================================
	// CART ITEM ASSERTIONS
	// =========================================================

	/**
	 * Verifies that products are displayed in cart.
	 */
	public void verifyCartItemsDisplayed() {

		log.info("Verifying cart items are displayed");

		Assert.assertTrue(cartPage.areCartItemsDisplayed(), "Cart should contain products");
	}

	/**
	 * Verifies that cart is not empty.
	 */
	public void verifyCartIsNotEmpty() {

		log.info("Verifying cart is not empty");

		Assert.assertTrue(cartPage.isCartNotEmpty(), "Cart should not be empty");
	}

	/**
	 * Verifies that cart is empty.
	 */
	public void verifyCartIsEmpty() {

		log.info("Verifying cart is empty");

		Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty");
	}

	public void verifyCartItemCounts(int expectedCount) {

		int actualCount = cartPage.getCartItemCounts();

		scenarioContext.set("cartItemCount", actualCount);

		log.info("Verifying cart item count. Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(actualCount, expectedCount, "Cart item count is incorrect");
	}

	public void verifyCartBadgeDoesNotShow(int expectedCount) {

		log.info("Verifying cart badge does not show count: {}", expectedCount);

		String badgeText = cartPage.getCartBadgeText();

		if (badgeText == null || badgeText.trim().isEmpty()) {

			log.info("Cart badge is not displayed. It does not show count: {}", expectedCount);

			return;
		}

		int actualCount = Integer.parseInt(badgeText.trim());

		log.info("Cart badge actual count: {}, expected not to show: {}", actualCount, expectedCount);

		Assert.assertNotEquals(actualCount, expectedCount, "Cart badge should not show " + expectedCount);

		log.info("Cart badge correctly does not show {}", expectedCount);
	}

	/**
	 * Verifies cart item count.
	 */
	public void verifyCartItemCountInCart_(int expectedCount) {

		int actualCount = cartPage.getCartItemCounts();

		log.info("Verifying cart item count. Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(actualCount, expectedCount, "Cart item count is incorrect");
	}

	public void verifyCartItemCount(int expectedCount) {

		log.info("Verifying Cart item count. Expected: {}", expectedCount);

		int actualCount = cartPage.getCartItemCount();

		log.info("Actual Cart item count: {}", actualCount);

		Assert.assertEquals(actualCount, expectedCount,
				"Incorrect Cart item count. Expected: " + expectedCount + " but found: " + actualCount);

		// Store the verified Cart count

		scenarioContext.set("cartItemCount", actualCount);

		log.info("Cart item count '{}' stored in ScenarioContext", actualCount);
	}

	public void verifyCartItemCountInCart(int expectedCount) {

		log.info("Verifying cart item count. Expected: {}", expectedCount);

		WaitUtils.waitForUrlContains("/cart.html");

		int actualCount = cartPage.getCartItemCounts();

		scenarioContext.set("cartItemCount", actualCount);

		log.info("Cart item count - Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(actualCount, expectedCount, "Cart item count is incorrect");
	}

	/**
	 * Verifies cart item count is greater than expected value.
	 */
	public void verifyCartItemCountGreaterThan(int expectedCount) {

		int actualCount = cartPage.getCartItemCount();

		log.info("Verifying cart item count is greater than {}. Actual: {}", expectedCount, actualCount);

		Assert.assertTrue(actualCount > expectedCount, "Expected cart item count to be greater than " + expectedCount
				+ ", but actual count was " + actualCount);
	}

	/**
	 * Verifies cart item count is less than expected value.
	 */
	public void verifyCartItemCountLessThan(int expectedCount) {

		int actualCount = cartPage.getCartItemCount();

		log.info("Verifying cart item count is less than {}. Actual: {}", expectedCount, actualCount);

		scenarioContext.set("cartItemCount", actualCount);

		Assert.assertTrue(actualCount < expectedCount,
				"Expected cart item count to be less than " + expectedCount + ", but actual count was " + actualCount);
	}

	// =========================================================
	// PRODUCT ASSERTIONS
	// =========================================================

	/**
	 * Verifies that a product is displayed in cart.
	 */
	public void verifyProductDisplayed(String productName) {

		log.info("Verifying product is displayed in cart: {}", productName);

		Assert.assertTrue(cartPage.isProductDisplayed(productName),
				"Product should be displayed in cart: " + productName);
	}

	/**
	 * Verifies that a product is NOT displayed in cart.
	 */
	public void verifyProductNotDisplayed(String productName) {

		log.info("Verifying product is not displayed in cart: {}", productName);

		boolean displayed = cartPage.isProductDisplayed(productName);

		log.info("Product: {}, Displayed: {}", productName, displayed);

		Assert.assertFalse(displayed, "Product should not be displayed in cart: " + productName);
	}

	/**
	 * Alias for verifyProductNotDisplayed().
	 */
	public void verifyProductNotDisplay(String productName) {

		verifyProductNotDisplayed(productName);
	}

	// =========================================================
	// PRODUCT PRICE ASSERTIONS
	// =========================================================

	/**
	 * Verifies product price.
	 */
	public void verifyProductPrice(String productName, String expectedPrice) {

		String actualPrice = cartPage.getProductPrice(productName);

		log.info("Verifying product price. Product: {}, Expected: {}, Actual: {}", productName, expectedPrice,
				actualPrice);

		Assert.assertEquals(actualPrice, expectedPrice, "Product price is incorrect for: " + productName);
	}

	/**
	 * Verifies product price is NOT equal to an unexpected price.
	 */
	public void verifyProductPriceNot(String productName, String unexpectedPrice) {

		log.info("Verifying product price should not be. " + "Product: {}, Unexpected Price: {}", productName,
				unexpectedPrice);

		String actualPrice = cartPage.getProductPrice(productName);

		Assert.assertNotEquals(actualPrice, unexpectedPrice, "Product price should not be: " + unexpectedPrice);

		log.info("Product price verified. Actual: {}, Unexpected: {}", actualPrice, unexpectedPrice);
	}

	/**
	 * Gets product price.
	 *
	 * This is useful when the step definition needs the actual price for another
	 * validation.
	 */
	public String getProductPrice(String productName) {

		log.info("Getting price for product: {}", productName);

		return cartPage.getProductPrice(productName);
	}

	// =========================================================
	// PRODUCT QUANTITY ASSERTIONS
	// =========================================================

	/**
	 * Verifies product quantity.
	 */
	public void verifyProductQuantity(String productName, int expectedQuantity) {

		int actualQuantity = cartPage.getProductQuantity(productName);

		log.info("Verifying product quantity. " + "Product: {}, Expected: {}, Actual: {}", productName,
				expectedQuantity, actualQuantity);

		Assert.assertEquals(actualQuantity, expectedQuantity, "Product quantity is incorrect for: " + productName);
	}

	/**
	 * Verifies product quantity is greater than expected value.
	 */
	public void verifyProductQuantityGreaterThan(String productName, int expectedQuantity) {

		int actualQuantity = cartPage.getProductQuantity(productName);

		log.info("Verifying product quantity is greater than {}. " + "Product: {}, Actual: {}", expectedQuantity,
				productName, actualQuantity);

		Assert.assertTrue(actualQuantity > expectedQuantity, "Expected quantity for " + productName
				+ " to be greater than " + expectedQuantity + ", but actual quantity was " + actualQuantity);
	}

	/**
	 * Verifies product quantity is less than expected value.
	 */
	public void verifyProductQuantityLessThan(String productName, int expectedQuantity) {

		int actualQuantity = cartPage.getProductQuantity(productName);

		log.info("Verifying product quantity is less than {}. " + "Product: {}, Actual: {}", expectedQuantity,
				productName, actualQuantity);

		Assert.assertTrue(actualQuantity < expectedQuantity, "Expected quantity for " + productName
				+ " to be less than " + expectedQuantity + ", but actual quantity was " + actualQuantity);
	}

	// =========================================================
	// PRODUCT DESCRIPTION ASSERTIONS
	// =========================================================

	/**
	 * Verifies product description.
	 */
	public void verifyProductDescription(String productName, String expectedDescription) {

		String actualDescription = cartPage.getProductDescription(productName);

		log.info("Verifying product description. Product: {}", productName);

		Assert.assertEquals(actualDescription, expectedDescription,
				"Product description is incorrect for: " + productName);
	}

	// =========================================================
	// REMOVE BUTTON ASSERTIONS
	// =========================================================

	/**
	 * Verifies Remove button is displayed for product.
	 */
	public void verifyRemoveButtonDisplayed(String productName) {

		log.info("Verifying Remove button is displayed for: {}", productName);

		Assert.assertTrue(cartPage.isRemoveButtonDisplayed(productName),
				"Remove button should be displayed for: " + productName);
	}

	// =========================================================
	// CONTINUE SHOPPING ASSERTIONS
	// =========================================================

	/**
	 * Verifies Continue Shopping button is displayed.
	 */
	public void verifyContinueShoppingButtonDisplayed() {

		log.info("Verifying Continue Shopping button is displayed");

		Assert.assertTrue(cartPage.isContinueShoppingButtonDisplayed(), "Continue Shopping button should be displayed");
	}

	/**
	 * Verifies user is returned to Inventory page.
	 */
	public void verifyInventoryPageAfterContinueShopping() {

		WaitUtils.waitForUrlContains("/inventory.html");

		String currentUrl = cartPage.getCurrentUrl();

		boolean inventoryPageDisplayed = currentUrl.contains("/inventory.html");

		log.info("Verifying Inventory page after Continue Shopping. " + "URL: {}, Displayed: {}", currentUrl,
				inventoryPageDisplayed);

		Assert.assertTrue(inventoryPageDisplayed,
				"Inventory page should be displayed after " + "clicking Continue Shopping. Current URL: " + currentUrl);
	}

	// =========================================================
	// CHECKOUT ASSERTIONS
	// =========================================================

	/**
	 * Verifies Checkout button is displayed.
	 */
	public void verifyCheckoutButtonDisplayed() {

		log.info("Verifying Checkout button is displayed");

		Assert.assertTrue(cartPage.isCheckoutButtonDisplayed(), "Checkout button should be displayed");
	}

	/**
	 * Verifies user is navigated to Checkout page.
	 */
	public void verifyCheckoutPageDisplayed() {

		WaitUtils.waitForUrlContains("/checkout-step-one.html");

		String currentUrl = cartPage.getCurrentUrl();

		boolean checkoutPageDisplayed = currentUrl.contains("/checkout-step-one.html");

		log.info("Verifying Checkout page. " + "URL: {}, Displayed: {}", currentUrl, checkoutPageDisplayed);

		Assert.assertTrue(checkoutPageDisplayed, "Checkout page should be displayed. " + "Current URL: " + currentUrl);
	}

	// =========================================================
	// SHOPPING CART HEADER ASSERTIONS
	// =========================================================

	/**
	 * Verifies shopping cart icon is displayed.
	 */
	public void verifyShoppingCartDisplayed() {

		log.info("Verifying shopping cart icon is displayed");

		Assert.assertTrue(cartPage.isShoppingCartDisplayed(), "Shopping cart icon should be displayed");
	}

	/**
	 * Verifies shopping cart container is displayed.
	 */
	public void verifyCartContainerDisplayed() {

		log.info("Verifying shopping cart container is displayed");

		Assert.assertTrue(cartPage.isCartContainerDisplayed(), "Shopping cart container should be displayed");
	}

	// =========================================================
	// CART BADGE ASSERTIONS
	// =========================================================

	/**
	 * Verifies cart badge is displayed.
	 */
	public void verifyCartBadgeDisplayed() {

		log.info("Verifying cart badge is displayed");

		Assert.assertTrue(cartPage.isCartBadgeDisplayed(), "Cart badge should be displayed");
	}

	/**
	 * Verifies cart badge count.
	 */
	public void verifyCartBadgeCount(int expectedCount) {

		int actualCount = cartPage.getCartBadgeCount();

		log.info("Verifying cart badge count. " + "Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(actualCount, expectedCount,
				"Cart badge count mismatch. " + "Expected: " + expectedCount + ", Actual: " + actualCount);
	}

	/**
	 * Verifies cart badge count is greater than expected.
	 */
	public void verifyCartBadgeCountGreaterThan(int expectedCount) {

		int actualCount = cartPage.getCartBadgeCount();

		log.info("Verifying cart badge count is greater than {}. " + "Actual: {}", expectedCount, actualCount);

		Assert.assertTrue(actualCount > expectedCount, "Expected cart badge count to be greater than " + expectedCount
				+ ", but actual count was " + actualCount);
	}

	/**
	 * Verifies cart badge is NOT displayed.
	 */
	public void verifyCartBadgeNotDisplayed() {

		boolean displayed = cartPage.isCartBadgeDisplayed();

		log.info("Verifying cart badge is not displayed. Actual: {}", displayed);

		Assert.assertFalse(displayed, "Cart badge should not be displayed");
	}

	// =========================================================
	// URL ASSERTIONS
	// =========================================================

	/**
	 * Verifies Cart page using URL.
	 */
	public void verifyShoppingCartPageDisplayed() {

		WaitUtils.waitForUrlContains("/cart.html");

		String currentUrl = cartPage.getCurrentUrl();

		boolean cartPageDisplayed = currentUrl.contains("/cart.html");

		log.info("Verifying shopping cart page. " + "URL: {}, Displayed: {}", currentUrl, cartPageDisplayed);

		Assert.assertTrue(cartPageDisplayed, "Shopping cart page should be displayed. " + "Current URL: " + currentUrl);
	}

	/**
	 * Alternative URL validation method.
	 */
	public void verifyCartUrlDisplayed() {

		String currentUrl = cartPage.getCurrentUrl();

		log.info("Verifying Cart URL. Actual URL: {}", currentUrl);

		Assert.assertTrue(currentUrl.contains("/cart.html"),
				"Cart page URL should contain /cart.html. " + "Current URL: " + currentUrl);
	}

	/**
	 * Alternative URL validation method.
	 */
	public void verifyCurrentUrlIsCart(String expectedUrl) {

		String currentUrl = cartPage.getCurrentUrl();

		log.info("Verifying Cart URL. Actual URL: {}", currentUrl);

		Assert.assertTrue(currentUrl.contains(expectedUrl),
				"Cart page URL should contain " + expectedUrl + "  Current URL: " + currentUrl);
	}

	public void verifyShoppingCartUrlContains(String expectedUrlPart) {
		String actualUrl = cartPage.getCurrentUrl();
		log.info("Expected URL to contain: {}", expectedUrlPart);
		log.info("Actual URL: {}", actualUrl);

		Assert.assertNotNull(actualUrl, "Shopping cart URL must not be null");
		Assert.assertTrue(actualUrl.contains(expectedUrlPart), "Expected shopping cart URL to contain '"
				+ expectedUrlPart + "' but actual URL was '" + actualUrl + "'");
		log.info("Shopping cart URL validation passed");

	}

	public void verifyProductDescriptionDisplayed(String productName) {
		log.info("Verifying product description for: {}", productName);
		boolean isDisplayed = cartPage.isProductDescriptionDisplayed(productName);
		Assert.assertTrue(isDisplayed, "Product description should be displayed for product: " + productName);
		log.info("Product description is displayed for: {}", productName);

	}

	public void verifyProductIsNotDisplayedInCart(String productName) {

		log.info("Checking that product '{}' is not present in the cart", productName);

		boolean isDisplayed = cartPage.isProductDisplayedInCart(productName);

		Assert.assertFalse(isDisplayed, "Product '" + productName + "' should NOT be displayed in the cart");

		log.info("Product '{}' is correctly not displayed in the cart", productName);
	}

	public void verifyCheckoutButtonIsDisplayed() {

		log.info("Checking whether Checkout button is displayed");

		Assert.assertTrue(cartPage.isCheckoutButtonDisplayed(), "Checkout button should be displayed on the Cart page");

		log.info("Checkout button is displayed as expected");
	}

	public void verifyProductPriceIsNot(String productName, String expectedPrice) {

		log.info("Verifying price of product '{}' is not '{}'", productName, expectedPrice);

		String actualPrice = cartPage.getProductPrice(productName);

		log.info("Product '{}' actual price: '{}', expected not equal price: '{}'", productName, actualPrice,
				expectedPrice);

		Assert.assertNotEquals(actualPrice, expectedPrice, "Product price should not be " + expectedPrice);

		log.info("Price validation passed. Product '{}' price '{}' is not '{}'", productName, actualPrice,
				expectedPrice);
	}

	public void verifyProductQuantityIsNot(String productName, int expectedQuantity) {

		log.info("Verifying quantity of product '{}' is not {}", productName, expectedQuantity);

		int actualQuantity = cartPage.getProductQuantity(productName);

		log.info("Product '{}' actual quantity: {}, expected not equal quantity: {}", productName, actualQuantity,
				expectedQuantity);

		Assert.assertNotEquals(actualQuantity, expectedQuantity, "Product quantity should not be " + expectedQuantity);

		log.info("Quantity validation passed. Product '{}' quantity {} is not {}", productName, actualQuantity,
				expectedQuantity);
	}

	public void verifyCartItemCountIsNot(int expectedCount) {

		log.info("Verifying cart item count should not be {}", expectedCount);

		int actualCount = cartPage.getCartItemCount();

		log.info("Actual cart item count: {}, Expected not to be: {}", actualCount, expectedCount);

		scenarioContext.set("cartItemCount", actualCount);

		Assert.assertNotEquals(actualCount, expectedCount, "Cart should not contain " + expectedCount + " products");

		log.info("Cart item count validation passed. " + "Actual count {} is not {}", actualCount, expectedCount);
	}

	public void verifyEmptyCartCheckoutHandled() {

		log.info("Verifying empty cart checkout handling");

		String currentUrl = cartPage.getCurrentUrl();

		log.info("Current URL after empty cart checkout attempt: {}", currentUrl);

		Assert.assertTrue(currentUrl.contains("checkout-step-one.html"),
				"Application should remain on the cart page when checkout is attempted with an empty cart. "
						+ "Current URL: " + currentUrl);

		log.info("Empty cart checkout handled correctly. User remains on cart page.");
	}

	/**
	 * Verifies that the cart contains at least one product.
	 */
	public void verifyCartContainsProducts() {

		log.info("Verifying cart contains at least one product");

		int productCount = cartPage.getCartItemCount();

		Assert.assertTrue(productCount > 0, "Cart should contain at least one product");

		log.info("Cart contains {} product(s)", productCount);
	}

	/**
	 * Verifies that the specified product is displayed in the shopping cart.
	 *
	 * @param productName expected product name
	 */
	public void verifyProductDisplayedInCart(String productName) {

		log.info("Verifying product '{}' is displayed in Cart", productName);

		Assert.assertNotNull(productName, "Product name must not be null");

		Assert.assertFalse(productName.trim().isEmpty(), "Product name must not be empty");

		boolean productDisplayed = cartPage.isProductDisplayed(productName);

		Assert.assertTrue(productDisplayed, "Product '" + productName + "' is not displayed in the Cart");

		log.info("Product '{}' is displayed successfully in the Cart", productName);
	}

	public void verifyAndCaptureProductPrice(String productName) {

		log.info("Verifying and capturing price for product '{}' from Cart", productName);

		Assert.assertNotNull(productName, "Product name must not be null");

		Assert.assertFalse(productName.trim().isEmpty(), "Product name must not be empty");

		String cartPrice = cartPage.getProductPrice(productName);

		Assert.assertNotNull(cartPrice, "Cart price was not found for product: " + productName);

		Assert.assertFalse(cartPrice.trim().isEmpty(), "Cart price is empty for product: " + productName);

		log.info("Product '{}' Cart price is: {}", productName, cartPrice);

		// Store product name and price in ScenarioContext
		scenarioContext.set("productName", productName);
		scenarioContext.set("cartProductPrice", cartPrice);

		log.info("Captured Cart price '{}' for product '{}'", cartPrice, productName);
	}

	public void verifyAndCaptureCartItemCount() {

		log.info("Verifying and capturing Cart item count");

		int cartItemCount = cartPage.getCartItemCount();

		log.info("Actual Cart item count: {}", cartItemCount);

		Assert.assertTrue(cartItemCount > 0, "Cart should contain at least one product, but found: " + cartItemCount);

		scenarioContext.set("cartItemCount", cartItemCount);

		log.info("Cart item count '{}' successfully stored in ScenarioContext", cartItemCount);
	}

	/**
	 * Verifies that every item in the Cart has a valid price.
	 *
	 * Also calculates the total of all Cart item prices and stores it in
	 * ScenarioContext for comparison with Checkout Overview subtotal.
	 */
	public void verifyCartItemPrices() {

		log.info("Starting Cart item price verification");

		List<String> prices = cartPage.getCartItemPrices();

		Assert.assertNotNull(prices, "Cart item prices should not be null");

		Assert.assertFalse(prices.isEmpty(), "Cart should contain at least one item with a price");

		double cartTotal = 0.0;

		for (String priceText : prices) {

			Assert.assertNotNull(priceText, "Cart item price should not be null");

			Assert.assertFalse(priceText.trim().isEmpty(), "Cart item price should not be empty");

			String cleanedPrice = priceText.replace("$", "").trim();

			double price;

			try {
				price = Double.parseDouble(cleanedPrice);
			} catch (NumberFormatException e) {

				Assert.fail("Invalid Cart item price format: " + priceText);

				return;
			}

			Assert.assertTrue(price >= 0, "Cart item price should not be negative: " + priceText);

			cartTotal += price;

			log.info("Verified Cart item price: {}", priceText);
		}

		/*
		 * Round to two decimal places to avoid floating-point precision issues.
		 */
		cartTotal = Math.round(cartTotal * 100.0) / 100.0;

		log.info("Calculated Cart total from item prices: ${}", cartTotal);

		/*
		 * Store calculated Cart total for Checkout Step Two subtotal validation.
		 */
		scenarioContext.setCartSubtotal(cartTotal);

		log.info("Cart item price verification completed successfully");
	}

//	public void verifyCartIsEmpty() {
//
//	    log.info("Checking whether the cart is empty");
//
//	    boolean isEmpty = cartPage.isCartEmpty();
//
//	    Assert.assertTrue(
//	            isEmpty,
//	            "Cart should be empty, but it contains products"
//	    );
//
//	    log.info("Cart is empty");
//	}
//
//	public void verifyCartItemCount(int expectedCount) {
//
//	    log.info("Verifying cart item count. Expected: {}", expectedCount);
//
//	    int actualCount = cartPage.getCartItemCount();
//
//	    log.info(
//	            "Cart item count - Expected: {}, Actual: {}",
//	            expectedCount,
//	            actualCount
//	    );
//
//	    Assert.assertEquals(
//	            actualCount,
//	            expectedCount,
//	            "Cart item count mismatch. Expected: "
//	                    + expectedCount
//	                    + ", Actual: "
//	                    + actualCount
//	    );
//
//	    log.info("Cart item count validation passed");
//	}
//	

}
