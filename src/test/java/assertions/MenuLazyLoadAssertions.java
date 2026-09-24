package assertions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import driver.DriverManager;
import pages.MenuLazyLoadPage;
import utils.WaitUtils;

public class MenuLazyLoadAssertions {

	private static final Logger log = LoggerFactory.getLogger(MenuLazyLoadAssertions.class);

	private final MenuLazyLoadPage menuLazyLoadPage;

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	public MenuLazyLoadAssertions() {

		this.menuLazyLoadPage = new MenuLazyLoadPage();

		log.debug("MenuLazyLoadAssertions initialized");
	}

	public MenuLazyLoadAssertions(MenuLazyLoadPage menuLazyLoadPage) {

		if (menuLazyLoadPage == null) {
			throw new IllegalArgumentException("MenuLazyLoadPage must not be null");
		}

		this.menuLazyLoadPage = menuLazyLoadPage;

		log.debug("MenuLazyLoadAssertions initialized with MenuLazyLoadPage");
	}

	// =========================================================
	// PAGE VALIDATIONS
	// =========================================================

	public void verifyLazyLoadMenuPageDisplayed() {

		log.info("Verifying Lazy Load Menu page is displayed");

		Assert.assertTrue(menuLazyLoadPage.isPageDisplayed(), "Lazy Load Menu page should be displayed");

		log.info("Lazy Load Menu page is displayed successfully");
	}

	public void verifyPageTitleDisplayed() {

		log.info("Verifying page title is displayed");

		Assert.assertTrue(menuLazyLoadPage.isPageTitleDisplayed(), "Page title should be displayed");

		log.info("Page title is displayed successfully");
	}

	public void verifyPageTitleNotEmpty() {

		log.info("Verifying page title is not empty");

		String actualTitle = menuLazyLoadPage.getPageTitleText();

		Assert.assertNotNull(actualTitle, "Page title should not be null");

		Assert.assertFalse(actualTitle.trim().isEmpty(), "Page title should not be empty");

		log.info("Page title is not empty: [{}]", actualTitle);
	}

	public void verifyCatalogContainerDisplayed() {

		log.info("Verifying catalog container is displayed");

		Assert.assertTrue(menuLazyLoadPage.isCatalogContainerDisplayed(), "Catalog container should be displayed");

		log.info("Catalog container is displayed successfully");
	}

	// =========================================================
	// PRODUCT VALIDATIONS
	// =========================================================

	public void verifyAtLeastOneProductDisplayed() {

		log.info("Verifying at least one product is displayed");

		int productCount = menuLazyLoadPage.getLoadedProductCount();

		log.info("Currently displayed product count: {}", productCount);

		Assert.assertTrue(productCount > 0, "At least one product should be displayed, but found: " + productCount);

		log.info("At least one product is displayed successfully. Count: {}", productCount);
	}

	public void verifyLoadedProductCountGreaterThanZero(int loadedProductCount) {

		log.info("Verifying loaded product count is greater than zero. Actual: {}", loadedProductCount);

		Assert.assertTrue(loadedProductCount > 0,
				"Loaded product count should be greater than zero, but found: " + loadedProductCount);

		log.info("Loaded product count is greater than zero. Count: {}", loadedProductCount);
	}

	public void verifyPageUrlContains(String expectedUrlPart) {

		if (expectedUrlPart == null || expectedUrlPart.trim().isEmpty()) {
			throw new IllegalArgumentException("Expected URL part must not be null or empty");
		}

		String actualUrl = DriverManager.getDriver().getCurrentUrl();

		log.info("Validating page URL");
		log.info("Expected URL part: {}", expectedUrlPart);
		log.info("Actual URL: {}", actualUrl);

		Assert.assertTrue(actualUrl.contains(expectedUrlPart),
				"Expected URL to contain [" + expectedUrlPart + "] but actual URL was [" + actualUrl + "]");
	}

	// =========================================================
	// MENU VALIDATIONS
	// =========================================================

	public void verifyMenuDisplayed() {

		log.info("Verifying menu is displayed");

		Assert.assertTrue(menuLazyLoadPage.isMenuDisplayed(), "Menu should be displayed");

		log.info("Menu is displayed successfully");
	}

	public void verifyMenuNotDisplayed() {

		log.info("Verifying menu is not displayed");

		Assert.assertFalse(menuLazyLoadPage.isMenuDisplayed(), "Menu should not be displayed");

		log.info("Menu is not displayed as expected");
	}

	public void verifyMenuClosed() {

		log.info("Verifying menu is closed");

		Assert.assertFalse(menuLazyLoadPage.isMenuDisplayed(), "Menu should be closed");

		log.info("Menu is closed successfully");
	}

	public void verifyMenuAccessible() {

		log.info("Verifying menu remains accessible");

		Assert.assertTrue(menuLazyLoadPage.isMenuButtonDisplayed(), "Menu button should remain accessible");

		log.info("Menu remains accessible");
	}

	// =========================================================
	// MENU OPTION VALIDATIONS
	// =========================================================

	public void verifyMenuItemsDisplayed() {

		log.info("Verifying menu items are displayed");

		Assert.assertTrue(menuLazyLoadPage.areMenuItemsDisplayed(), "Menu items should be displayed");

		log.info("Menu items are displayed successfully");
	}

	public void verifyAllMenuOptionsDisplayed() {

		log.info("Verifying all menu options are displayed");

		Assert.assertTrue(menuLazyLoadPage.areAllMenuOptionsDisplayed(),
				"All expected menu options should be displayed");

		log.info("All menu options are displayed successfully");
	}

	public void verifySelectedMenuOptionDisplayed(String menuOption) {

		log.info("Verifying selected menu option is displayed");

		Assert.assertTrue(menuLazyLoadPage.isSelectedMenuOptionDisplayed(menuOption),
				"Selected menu option should be displayed");

		log.info("Selected menu option is displayed");
	}

	public void verifyMenuOptionCount(int expectedCount) {

		int actualCount = menuLazyLoadPage.getMenuOptionCount();

		log.info("Validating menu option count. Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(actualCount, expectedCount, "Incorrect menu option count");
	}

	// =========================================================
	// PRODUCT VALIDATIONS
	// =========================================================

	public void verifyInitialProductsDisplayed() {

		log.info("Verifying initial products are displayed");

		int productCount = menuLazyLoadPage.getLoadedProductCount();

		Assert.assertTrue(productCount > 0, "At least one product should be displayed initially");

		log.info("Initial product count: {}", productCount);
	}

	public void verifyEveryLoadedProductHasProductPrice() {

		log.info("Verifying every loaded product has a product price");

		int loadedProductCount = menuLazyLoadPage.getLoadedProductCount();

		List<String> productPrices = menuLazyLoadPage.getLoadedProductPrices();

		log.info("Loaded products: {}", loadedProductCount);

		log.info("Product prices found: {}", productPrices.size());

		Assert.assertTrue(loadedProductCount > 0, "No loaded products were found");

		Assert.assertEquals(productPrices.size(), loadedProductCount, "Every loaded product should have a price. "
				+ "Products: " + loadedProductCount + ", Prices: " + productPrices.size());

		for (int i = 0; i < productPrices.size(); i++) {

			String price = productPrices.get(i);

			Assert.assertNotNull(price, "Product price should not be null for product " + (i + 1));

			Assert.assertFalse(price.trim().isEmpty(), "Product price should not be empty for product " + (i + 1));

			Assert.assertTrue(price.matches("^\\$\\d+\\.\\d{2}$"),
					"Invalid product price format for product " + (i + 1) + ": " + price);
		}

		log.info("Every loaded product has a valid price");
	}

	public void verifyProductCountIncreasesAfterScrolling() {

		int initialCount = menuLazyLoadPage.getLoadedProductCount();

		log.info("Initial loaded product count: {}", initialCount);

		menuLazyLoadPage.scrollToBottom();

		WaitUtils.waitForSeconds(1);

		int updatedCount = menuLazyLoadPage.getLoadedProductCount();

		log.info("Product count after scrolling: {}", updatedCount);

		Assert.assertTrue(updatedCount > initialCount, "Product count should increase after lazy loading. "
				+ "Initial: " + initialCount + ", Updated: " + updatedCount);
	}

	public void verifyProductsRemainAfterScrolling() {

		int initialCount = menuLazyLoadPage.getLoadedProductCount();

		log.info("Initial product count: {}", initialCount);

		menuLazyLoadPage.scrollToBottom();

		WaitUtils.waitForSeconds(1);

		int finalCount = menuLazyLoadPage.getLoadedProductCount();

		log.info("Final product count: {}", finalCount);

		Assert.assertTrue(finalCount >= initialCount, "Product count should not decrease after scrolling");
	}

	public void verifyAllProductsLoaded() {

		log.info("Verifying all products are loaded");

		menuLazyLoadPage.loadAllProducts();

		int productCount = menuLazyLoadPage.getLoadedProductCount();

		Assert.assertTrue(productCount > 0, "At least one product should be loaded");

		log.info("Total products loaded: {}", productCount);
	}

	public void verifySelectedMenuOptionDisplayed() {

		log.info("Verifying selected menu option is displayed");

		boolean displayed = menuLazyLoadPage.isSelectedMenuOptionDisplayed("Dynamic Catalog");

		Assert.assertTrue(displayed, "Selected menu option [Dynamic Catalog] should be displayed");

		log.info("Selected menu option [Dynamic Catalog] is displayed successfully");
	}

	// =========================================================
	// PRODUCT CONTENT VALIDATIONS
	// =========================================================

	public void verifyProductNamesDisplayed() {

		log.info("Verifying product names are displayed");

		Assert.assertTrue(menuLazyLoadPage.areProductNamesDisplayed(), "Product names should be displayed");

		log.info("Product names are displayed successfully");
	}

	public void verifyProductPricesDisplayed() {

		log.info("Verifying product prices are displayed");

		Assert.assertTrue(menuLazyLoadPage.areProductPricesDisplayed(), "Product prices should be displayed");

		log.info("Product prices are displayed successfully");
	}

	public void verifyProductImagesDisplayed_not_using() {

		log.info("Verifying product images are displayed");

		Assert.assertTrue(menuLazyLoadPage.areProductImagesDisplayed(), "Product images should be displayed");

		log.info("Product images are displayed successfully");
	}

	public void verifyEveryLoadedProductHasImage() {

		log.info("Verifying every loaded product has a displayed image");

		List<WebElement> products = menuLazyLoadPage.getProductItems();

		Assert.assertFalse(products.isEmpty(), "No loaded products were found");

		int productIndex = 0;

		for (WebElement product : products) {

			productIndex++;

			List<WebElement> images = product.findElements(By.cssSelector(".dynamic_catalog_card_img"));

			Assert.assertFalse(images.isEmpty(), "Loaded product #" + productIndex + " does not have an image");

			WebElement image = images.get(0);

			Assert.assertTrue(image.isDisplayed(),
					"Image for loaded product #" + productIndex + " should be displayed");

			String src = image.getAttribute("src");

			Assert.assertNotNull(src, "Image src for loaded product #" + productIndex + " should not be null");

			Assert.assertFalse(src.trim().isEmpty(),
					"Image src for loaded product #" + productIndex + " should not be empty");

			log.info("Product #{} image verified successfully. src={}", productIndex, src);
		}

		log.info("All {} loaded products display a valid image", productIndex);
	}

	// =========================================================
	// PRODUCT NAME VALIDATION
	// =========================================================

	public void verifyEveryLoadedProductHasProductName() {

		log.info("Verifying every loaded product has a product name");

		List<String> productNames = menuLazyLoadPage.getLoadedProductNames();

		log.info("Number of loaded product names found: {}", productNames.size());

		Assert.assertFalse(productNames.isEmpty(), "No loaded product names were found");

		for (int i = 0; i < productNames.size(); i++) {

			String productName = productNames.get(i);

			log.info("Validating product name for product {}: [{}]", i + 1, productName);

			Assert.assertNotNull(productName, "Product name should not be null for product " + (i + 1));

			Assert.assertFalse(productName.trim().isEmpty(), "Product name should not be empty for product " + (i + 1));
		}

		log.info("Every loaded product has a valid product name. Total products: {}", productNames.size());
	}

	public void verifyEveryLoadedProductHasPrice() {

		log.info("Verifying every loaded product has a price");

		List<WebElement> products = menuLazyLoadPage.getProductItems();

		Assert.assertFalse(products.isEmpty(), "No loaded products were found");

		int productIndex = 0;

		for (WebElement product : products) {

			productIndex++;

			List<WebElement> prices = product.findElements(By.cssSelector(".dynamic_catalog_card_price"));

			Assert.assertFalse(prices.isEmpty(), "Loaded product #" + productIndex + " does not have a price");

			WebElement priceElement = prices.get(0);

			Assert.assertTrue(priceElement.isDisplayed(),
					"Price for loaded product #" + productIndex + " should be displayed");

			String price = priceElement.getText().trim();

			Assert.assertFalse(price.isEmpty(), "Price for loaded product #" + productIndex + " should not be empty");

			Assert.assertTrue(price.matches("^\\$\\d+\\.\\d{2}$"),
					"Invalid price format for loaded product #" + productIndex + ": " + price);

			log.info("Product #{} price verified: {}", productIndex, price);
		}

		log.info("All {} loaded products have a valid price", productIndex);
	}

	public void verifyProductImagesDisplayed() {

		List<WebElement> images = menuLazyLoadPage.getProductImages();

		Assert.assertFalse(images.isEmpty(), "No loaded product images were found");

		for (int i = 0; i < images.size(); i++) {

			Assert.assertTrue(images.get(i).isDisplayed(), "Product image #" + (i + 1) + " should be displayed");

			Assert.assertFalse(images.get(i).getAttribute("src").isEmpty(),
					"Product image #" + (i + 1) + " should have a valid src");
		}

		log.info("All {} loaded product images are displayed", images.size());
	}
	// =========================================================
	// HEADER / FOOTER
	// =========================================================

	public void verifyHeaderDisplayed() {

		log.info("Verifying header is displayed");

		Assert.assertTrue(menuLazyLoadPage.isHeaderDisplayed(), "Header should be displayed");

		log.info("Header is displayed successfully");
	}

	public void verifyFooterDisplayed() {

		log.info("Verifying footer is displayed");

		Assert.assertTrue(menuLazyLoadPage.isFooterDisplayed(), "Footer should be displayed");

		log.info("Footer is displayed successfully");
	}

	// =========================================================
	// CART VALIDATIONS
	// =========================================================

	public void verifyCartBadgeCount(int expectedCount) {

		int actualCount = menuLazyLoadPage.getCartBadgeCount();

		log.info("Validating cart badge count. Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(actualCount, expectedCount, "Incorrect cart badge count");
	}

	public void verifyCartBadgeDisplayed() {

		log.info("Verifying cart badge is displayed");

		Assert.assertTrue(menuLazyLoadPage.isCartBadgeDisplayed(), "Cart badge should be displayed");

		log.info("Cart badge is displayed successfully");
	}

	public void verifyCartBadgeNotDisplayed() {

		log.info("Verifying cart badge is not displayed");

		Assert.assertFalse(menuLazyLoadPage.isCartBadgeDisplayed(), "Cart badge should not be displayed");

		log.info("Cart badge is not displayed as expected");
	}

	// =========================================================
	// NAVIGATION VALIDATIONS
	// =========================================================

	public void verifyCurrentUrlContains(String expectedUrlPart) {

		String actualUrl = DriverManager.getDriver().getCurrentUrl();

		log.info("Current URL: {}", actualUrl);
		log.info("Expected URL part: {}", expectedUrlPart);

		Assert.assertTrue(actualUrl.contains(expectedUrlPart),
				"Current URL should contain [" + expectedUrlPart + "] but was [" + actualUrl + "]");
	}
}