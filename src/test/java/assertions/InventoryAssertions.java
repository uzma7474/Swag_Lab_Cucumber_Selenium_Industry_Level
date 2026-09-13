package assertions;

import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.InventoryPage;
import utils.WaitUtils;

public class InventoryAssertions {

	private static final Logger log = LoggerFactory.getLogger(InventoryAssertions.class);

	private final InventoryPage inventoryPage;

	public InventoryAssertions() {

		this.inventoryPage = new InventoryPage();

		log.debug("InventoryAssertions initialized");
	}

	public InventoryAssertions(InventoryPage inventoryPage) {

		if (inventoryPage == null) {
			throw new IllegalArgumentException("InventoryPage must not be null");
		}

		this.inventoryPage = inventoryPage;
	}

	// =========================
	// Page Assertions
	// =========================

	public void verifyInventoryPageDisplayed() {

		log.info("Verifying Inventory page is displayed");

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page should be displayed");
	}

	public void verifyPageTitle(String expectedTitle) {

		log.info("Verifying Inventory page title. Expected: {}", expectedTitle);

		Assert.assertEquals(inventoryPage.getPageTitle(), expectedTitle, "Inventory page title is incorrect");
	}

	public void verifyInventoryListDisplayed() {

		log.info("Verifying inventory list is displayed");

		Assert.assertTrue(inventoryPage.isInventoryListDisplayed(), "Inventory list should be displayed");
	}

	// =========================
	// Product Assertions
	// =========================

	public void verifyProductsDisplayed() {

		log.info("Verifying products are displayed");

		Assert.assertTrue(inventoryPage.areProductsDisplayed(), "Products should be displayed on Inventory page");
	}

	public void verifyProductCount(int expectedCount) {

		int actualCount = inventoryPage.getProductCount();

		log.info("Verifying product count. Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(actualCount, expectedCount, "Product count is incorrect");
	}

	public void verifyProductCountOnPageGreaterThan(int expectedCount) {
		int actualProductCount = inventoryPage.getProductCount();

		log.info("Verifying product count. Expected: {}, Actual: {}", expectedCount, actualProductCount);

		// Assert.assertTrue(actualCount > expectedCount , "Actual Product Count
		// "+actualCount+" is not greater than "+expectedCount);

		Assert.assertTrue(actualProductCount > expectedCount, "Expected inventory product count to be greater than "
				+ expectedCount + ", but actual count was " + actualProductCount);
	}

	public void verifyProductDisplayed(String productName) {

		log.info("Verifying product is displayed: {}", productName);

		Assert.assertTrue(inventoryPage.isProductDisplayed(productName), "Product should be displayed: " + productName);
	}

	public void verifyProductPrice(String productName, String expectedPrice) {

		String actualPrice = inventoryPage.getProductPrice(productName);

		log.info("Verifying product price. Product: {}, Expected: {}, Actual: {}", productName, expectedPrice,
				actualPrice);

		Assert.assertEquals(actualPrice, expectedPrice, "Product price is incorrect for: " + productName);
	}

	// =========================
	// Cart Assertions
	// =========================

	public void verifyCartDisplayed() {

		log.info("Verifying shopping cart is displayed");

		Assert.assertTrue(inventoryPage.isShoppingCartDisplayed(), "Shopping cart should be displayed");
	}

	public void verifyCartBadgeDisplayed() {

		log.info("Verifying cart badge is displayed");

		Assert.assertTrue(inventoryPage.isCartBadgeDisplayed(), "Cart badge should be displayed");
	}

	public void verifyCartItemCount(int expectedCount) {

		String actualCount = inventoryPage.getCartBadgeCount();

		log.info("Verifying cart count. Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(actualCount, String.valueOf(expectedCount), "Cart item count is incorrect");
	}

	// =========================
	// Sorting Assertions
	// =========================

	public void verifySelectedSortOption(String expectedSortOption) {

		String actualSortOption = inventoryPage.getSelectedSortOption();

		log.info("Verifying sort option. Expected: {}, Actual: {}", expectedSortOption, actualSortOption);

		Assert.assertEquals(actualSortOption, expectedSortOption, "Selected sort option is incorrect");
	}

	public void verifySelectedSortOption_(String expectedSortOption) {

		if (expectedSortOption == null || expectedSortOption.isBlank()) {

			throw new IllegalArgumentException("Expected sort option must not be null or blank");
		}

		String actualSortOption = inventoryPage.getSelectedSortOption();

		log.info("Verifying selected sort option. Expected: {}, Actual: {}", expectedSortOption, actualSortOption);

		Assert.assertEquals(actualSortOption, expectedSortOption, "Incorrect inventory sort option");
	}

	// ==================================================================================================
	// Cart
	// ==================================================================================================

	public void verifyCartBadgeCount(int expectedCount) {

		String actualCount = inventoryPage.getCartBadgeCount();

		log.info("Verifying cart badge count. Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(Integer.parseInt(actualCount), expectedCount, "Incorrect cart badge count");
		Assert.assertEquals(Integer.parseInt(actualCount), expectedCount,
				"Cart badge count mismatch. Expected: " + expectedCount + ", Actual: " + actualCount);
	}

	public void verifyCartBadgeNotDisplayed() {

		boolean displayed = inventoryPage.isCartBadgeDisplayed();

		log.info("Verifying cart badge is not displayed. Actual: {}", displayed);

		Assert.assertFalse(displayed, "Cart badge should not be displayed");
	}

	public void verifyShoppingCartPageDisplayed() {

		boolean displayed = inventoryPage.isCartIconDisplayed();

		log.info("Verifying cart Icon is displayed. Actual: {}", displayed);

		Assert.assertTrue(displayed, "Cart badge should not be displayed");

	}

	public void verifyProductNotDisplay(String productName) {
		boolean displayed = inventoryPage.isProductDisplayed(productName);

		log.info("Verifying cart Icon is displayed. Actual: {}", displayed);

		Assert.assertFalse(displayed, "Cart badge should not be displayed");
	}

	public void verifyShoppingCartPageDisplayed_() {

		WaitUtils.waitForUrlContains("/cart.html");

		String currentUrl = inventoryPage.getCurrentUrl();

		boolean cartPageDisplayed = currentUrl.contains("/cart.html");

		log.info("Verifying shopping cart page. URL: {}, Displayed: {}", currentUrl, cartPageDisplayed);

		Assert.assertTrue(cartPageDisplayed, "Shopping cart page should be displayed. " + "Current URL: " + currentUrl);
	}

	public void verifyProductPriceNot(String productName, String unexpectedPrice) {

		log.info("Verifying product price should not be. Product: {}, Unexpected Price: {}", productName,
				unexpectedPrice);

		String actualPrice = inventoryPage.getProductPrice(productName);

		Assert.assertNotEquals(actualPrice, unexpectedPrice, "Product price should not be: " + unexpectedPrice);

		log.info("Product price verified. Actual: {}, Unexpected: {}", actualPrice, unexpectedPrice);
	}

	public String getProductPrice(String productName) {

		log.info("Getting price for product: {}", productName);

		return inventoryPage.getProductPrice(productName);

	}

}