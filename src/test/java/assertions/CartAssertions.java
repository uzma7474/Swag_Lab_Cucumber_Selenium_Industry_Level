
package assertions;

import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.CartPage;
import pages.CheckoutInformationPage;
import utils.WaitUtils;

public class CartAssertions {

	private static final Logger log = LoggerFactory.getLogger(CartAssertions.class);

	private final CartPage cartPage;
	// private final CheckoutInformationPage checkoutInformationPage;

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	public CartAssertions() {

		this.cartPage = new CartPage();

		log.debug("CartAssertions initialized");
	}

	public CartAssertions(CartPage cartPage) {

		if (cartPage == null) {
			throw new IllegalArgumentException("CartPage must not be null");
		}

		this.cartPage = cartPage;

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

	/**
	 * Verifies cart item count.
	 */
	public void verifyCartItemCount(int expectedCount) {

		int actualCount = cartPage.getCartItemCount();

		log.info("Verifying cart item count. Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(actualCount, expectedCount, "Cart item count is incorrect");
	}

	public void verifyCartItemCounts(int expectedCount) {

		int actualCount = cartPage.getCartItemCounts();

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

	public void verifyCartItemCountInCart(int expectedCount) {

		log.info("Verifying cart item count. Expected: {}", expectedCount);

		WaitUtils.waitForUrlContains("/cart.html");

		int actualCount = cartPage.getCartItemCounts();

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
