package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}