package actions;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import page_object_manager.PageObjectManager;
import pages.InventoryPage;

public class InventoryActions {

	private static final Logger log = LoggerFactory.getLogger(InventoryActions.class);

	private final InventoryPage inventoryPage;

	private final PageObjectManager pageObjectManager;

	public InventoryActions(PageObjectManager pageObjectManager) {

		if (pageObjectManager == null) {
			throw new IllegalArgumentException("PageObjectManager must not be null");
		}

		this.pageObjectManager = pageObjectManager;
		this.inventoryPage = pageObjectManager.getInventoryPage();

		log.debug("InventoryActions initialized");
	}

	// =========================
	// Navigation
	// =========================

	public void openInventoryPage() {

		log.info("Opening Inventory page");

		inventoryPage.open();
	}

	public void openCartPageByClickingOnCartIcon() {
		log.info("Opening Cart page");
		inventoryPage.clickShoppingCartIcon();
	}

	// =========================
	// Product Actions
	// =========================

	public void addProductToCart(String productName) {

		log.info("Action: Add product to cart - {}", productName);

		inventoryPage.addProductToCart(productName);
	}

	public void removeProductFromCart(String productName) {

		log.info("Action: Remove product from cart - {}", productName);

		inventoryPage.removeProductFromCart(productName);
	}

	public void addFirstProductToCart() {

		log.info("Action: Add first product to cart");

		inventoryPage.addFirstProductToCart();
	}

	// =========================
	// Sorting Actions
	// =========================

	public void sortProductsBy(String sortOption) {

		log.info("Action: Sort products by {}", sortOption);

		inventoryPage.selectSortOption(sortOption);
	}

	// =========================
	// Cart Actions
	// =========================

	public void openCart() {

		log.info("Action: Open shopping cart");

		inventoryPage.clickShoppingCart();
	}

	/** * Opens the shopping cart from the inventory page. */
	public void openShoppingCart() {

		log.info("Opening shopping cart");

		inventoryPage.clickShoppingCart();

		log.info("Shopping cart opened successfully");
	}

	// =========================
	// Business Flows
	// =========================

	public void addProductAndOpenCart(String productName) {

		log.info("Action: Add product '{}' and open cart", productName);

		inventoryPage.addProductToCart(productName);

		inventoryPage.clickShoppingCart();
	}

	public void addMultipleProductsAndOpenCart(String firstProduct, String secondProduct) {

		log.info("Adding products '{}' and '{}'", firstProduct, secondProduct);

		inventoryPage.addProductToCart(firstProduct);
		inventoryPage.addProductToCart(secondProduct);

		inventoryPage.clickShoppingCart();
	}

	public void refreshInventoryPage() {

		log.info("Action: Refresh Inventory page");

		inventoryPage.refresh();
	}

	public void selectSortOption(String sortOption) {
		if (sortOption == null || sortOption.isBlank()) {
			throw new IllegalArgumentException("Sort option must not be null or blank");

		}

		log.info("Selecting inventory sort option: {}", sortOption);
		inventoryPage.selectSortOption(sortOption);

	}

	/**
	 * Adds the specified products to the shopping cart.
	 *
	 * @param productNames list of product names to add
	 */
	public void addProductsToCart(List<String> productNames) {

		Assert.assertNotNull(productNames, "Product list must not be null");

		Assert.assertFalse(productNames.isEmpty(), "Product list must not be empty");

		log.info("Adding {} product(s) to cart: {}", productNames.size(), productNames);

		for (String productName : productNames) {

			Assert.assertNotNull(productName, "Product name must not be null");

			String trimmedProductName = productName.trim();

			Assert.assertFalse(trimmedProductName.isEmpty(), "Product name must not be empty");

			log.info("Adding product to cart: {}", trimmedProductName);

			boolean productFound = inventoryPage.addProductToCartByName(trimmedProductName);

			Assert.assertTrue(productFound, "Unable to find product on Inventory page: " + trimmedProductName);

			log.info("Successfully added product: {}", trimmedProductName);
		}

		log.info("All requested products have been added to cart");
	}

	public void addFirstNProductsToCart(int productCount) {

		log.info("Attempting to add first {} products to cart", productCount);

		Assert.assertTrue(productCount > 0, "Product count must be greater than 0");

		int availableProductCount = inventoryPage.getProductCount();

		log.info("Available products on Inventory page: {}", availableProductCount);

		Assert.assertTrue(productCount <= availableProductCount, "Requested " + productCount + " products, but only "
				+ availableProductCount + " products are available");

		List<String> productNames = inventoryPage.getAllProductNames();

		List<String> productsToAdd = productNames.subList(0, productCount);

		log.info("Products selected for cart: {}", productsToAdd);

		addProductsToCart(productsToAdd);

		log.info("Successfully added {} products to cart", productCount);
	}

	public void addFirstNProductsToCart_Not_using(int productCount) {

		log.info("Adding first {} products to cart", productCount);

		List<String> availableProducts = inventoryPage.getAllProductNames();

		Assert.assertTrue(productCount <= availableProducts.size(), "Requested " + productCount + " products, but only "
				+ availableProducts.size() + " products are available");

		List<String> productsToAdd = availableProducts.subList(0, productCount);

		log.info("Products selected for cart: {}", productsToAdd);

		addProductsToCart(productsToAdd);
	}

}