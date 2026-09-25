package actions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page_object_manager.PageObjectManager;
import pages.MenuLazyLoadPage;
import utils.WaitUtils;

public class MenuLazyLoadActions {

	private static final Logger log = LoggerFactory.getLogger(MenuLazyLoadActions.class);

	private final MenuLazyLoadPage menuLazyLoadPage;

	private final PageObjectManager pageObjectManager;

	public MenuLazyLoadActions(PageObjectManager pageObjectManager) {

		if (pageObjectManager == null) {
			throw new IllegalArgumentException("PageObjectManager must not be null");
		}

		this.pageObjectManager = pageObjectManager;
		this.menuLazyLoadPage = pageObjectManager.getMenuLazyLoadPage();

		if (this.menuLazyLoadPage == null) {
			throw new IllegalStateException("MenuLazyLoadPage must not be null");
		}

		log.debug("MenuLazyLoadActions initialized");
	}

	// =========================================================
	// PAGE NAVIGATION
	// =========================================================

	public void openLazyLoadMenuPage() {

		log.info("Opening Lazy Load Menu page");

		menuLazyLoadPage.open();

		log.info("Lazy Load Menu page opened successfully");
	}

	// =========================================================
	// MENU OPERATIONS
	// =========================================================

	public void openMenu() {

		log.info("Opening menu");

		menuLazyLoadPage.clickMenuButton();

		log.info("Menu opened successfully");
	}

	public void closeMenu() {

		log.info("Closing menu");

		menuLazyLoadPage.clickMenuButton();

		log.info("Menu closed successfully");
	}

	public void toggleMenu() {

		log.info("Toggling menu");

		menuLazyLoadPage.clickMenuButton();

		log.info("Menu toggle completed");
	}

	// =========================================================
	// MENU OPTION OPERATIONS
	// =========================================================

	public void clickMenuOption(String menuOption) {

		if (menuOption == null || menuOption.trim().isEmpty()) {
			throw new IllegalArgumentException("Menu option must not be null or empty");
		}

		log.info("Clicking menu option: {}", menuOption);

		menuLazyLoadPage.clickMenuOption(menuOption);

		log.info("Menu option clicked successfully: {}", menuOption);
	}

	// =========================================================
	// SCROLL OPERATIONS
	// =========================================================

	public void scrollToTop() {

		log.info("Scrolling to the top of the page");

		menuLazyLoadPage.scrollToTop();

		log.info("Scrolled to the top successfully");
	}

	public void scrollToBottom() {

		log.info("Scrolling to the bottom of the page");

		menuLazyLoadPage.scrollToBottom();

		log.info("Scrolled to the bottom successfully");
	}

	public void scrollToBottom_() {

		log.info("Scrolling to the bottom of the page");

		menuLazyLoadPage.scrollToBottom_();

		log.info("Scrolled to the bottom successfully");
	}

	public void scrollDown() {

		log.info("Scrolling down the page");

		menuLazyLoadPage.scrollDown();

		log.info("Scroll down completed");
	}

	public void scrollUp() {

		log.info("Scrolling up the page");

		menuLazyLoadPage.scrollUp();

		log.info("Scroll up completed");
	}

	public void repeatedlyScrollUpAndDown() {

		log.info("Starting repeated up and down scrolling");

		for (int iteration = 1; iteration <= 5; iteration++) {

			log.debug("Scroll iteration {} - scrolling down", iteration);

			menuLazyLoadPage.scrollDown();
			WaitUtils.waitForSeconds(1);

			menuLazyLoadPage.waitForLazyLoadingToComplete();

			log.debug("Scroll iteration {} - scrolling up", iteration);

			menuLazyLoadPage.scrollUp();
			WaitUtils.waitForSeconds(1);
		}

		log.info("Completed repeated up and down scrolling");
	}

	public boolean isProductNameLoaded(String productName) {

		if (productName == null || productName.trim().isEmpty()) {
			return false;
		}

		String expectedProductName = productName.trim();

		List<String> loadedProductNames = getLoadedProductNames();

		for (String loadedProductName : loadedProductNames) {

			if (loadedProductName.equalsIgnoreCase(expectedProductName)) {

				log.debug("Product [{}] found in currently loaded DOM", expectedProductName);

				return true;
			}
		}

		log.debug("Product [{}] was not found in currently loaded DOM", expectedProductName);

		return false;
	}

	public boolean hasProductAction(String productId) {

		if (productId == null || productId.trim().isEmpty()) {
			throw new IllegalArgumentException("Product ID must not be null or empty");
		}

		boolean actionAvailable = menuLazyLoadPage.hasProductAction(productId.trim());

		log.debug("Product [{}] action available: {}", productId, actionAvailable);

		return actionAvailable;
	}

	public void interactWithFirstProduct() {

		log.info("Interacting with first loaded Dynamic Catalog product");

		menuLazyLoadPage.interactWithFirstProduct();

		log.info("First product interaction completed");
	}

	public String getFirstLoadedProductId() {

		List<String> productIds = menuLazyLoadPage.getLoadedProductIds();

		if (productIds.isEmpty()) {
			log.warn("No loaded products found in Dynamic Catalog");
			return null;
		}

		String firstProductId = productIds.get(0);

		log.debug("First loaded product ID: {}", firstProductId);

		return firstProductId;
	}

	public String getProductNameById(String productId) {

		if (productId == null || productId.trim().isEmpty()) {
			throw new IllegalArgumentException("Product ID must not be null or empty");
		}

		return menuLazyLoadPage.getProductNameById(productId.trim());
	}

	public void interactWithProduct(String productId) {

		if (productId == null || productId.trim().isEmpty()) {
			throw new IllegalArgumentException("Product ID must not be null or empty");
		}

		log.info("Interacting with Dynamic Catalog product [{}]", productId);

		menuLazyLoadPage.interactWithProduct(productId.trim());

		log.info("Interaction completed for product [{}]", productId);
	}

	public void repeatedlyScrollUpAndDown_not_using() {

		log.info("Starting repeated up and down scrolling");

		for (int iteration = 1; iteration <= 5; iteration++) {

			log.debug("Scroll iteration {} - scrolling down", iteration);

			menuLazyLoadPage.scrollDown();
			WaitUtils.waitForSeconds(1);

			menuLazyLoadPage.waitForLazyLoadingToComplete();

			log.debug("Scroll iteration {} - scrolling up", iteration);

			menuLazyLoadPage.scrollUp();
			WaitUtils.waitForSeconds(1);
		}

		log.info("Completed repeated up and down scrolling");
	}

	public boolean hasAddToCartButton(String productName) {

		if (productName == null || productName.trim().isEmpty()) {
			throw new IllegalArgumentException("Product name must not be null or empty");
		}

		boolean hasButton = menuLazyLoadPage.hasAddToCartButton(productName.trim());

		log.debug("Product [{}] has Add to Cart button: {}", productName, hasButton);

		return hasButton;
	}

	public boolean isProductLoadedById(String productId) {

		if (productId == null || productId.trim().isEmpty()) {
			throw new IllegalArgumentException("Product ID must not be null or empty");
		}

		boolean loaded = menuLazyLoadPage.isProductLoadedById(productId.trim());

		log.debug("Product [{}] loaded in DOM: {}", productId, loaded);

		return loaded;
	}

	public String getFirstUnloadedProductId() {

		log.info("Searching for the first unloaded product card");

		String productId = menuLazyLoadPage.getFirstUnloadedProductId();

		log.debug("First unloaded product ID: {}", productId);

		return productId;
	}
	// =========================================================
	// LAZY LOAD OPERATIONS
	// =========================================================

	public void loadMoreProducts() {

		log.info("Triggering lazy loading by scrolling");

		menuLazyLoadPage.scrollToBottom();

		log.info("Lazy load scroll completed");
	}

	public void loadAllProducts() {

		log.info("Loading all lazy-loaded products");

		menuLazyLoadPage.loadAllProducts();

		log.info("All available products loaded successfully");
	}

	// =========================================================
	// REFRESH
	// =========================================================

	public void refreshPage() {

		log.info("Refreshing Lazy Load Menu page");

		menuLazyLoadPage.refresh();

		log.info("Lazy Load Menu page refreshed successfully");
	}

	// =========================================================
	// BROWSER NAVIGATION
	// =========================================================

	public void browserBack() {

		log.info("Navigating back using browser");

		menuLazyLoadPage.browserBack();

		log.info("Browser back navigation completed");
	}

	public void browserForward() {

		log.info("Navigating forward using browser");

		menuLazyLoadPage.browserForward();

		log.info("Browser forward navigation completed");
	}

	public void waitForLazyLoadingToComplete() {

		log.info("Waiting for lazy loading to complete");

		menuLazyLoadPage.waitForLazyLoadingToComplete();

		log.info("Lazy loading completed");
	}

	public void repeatedlyScrollDown() {

		log.info("Starting repeated scrolling of Dynamic Catalog");

		for (int i = 1; i <= 5; i++) {

			log.debug("Scroll iteration: {}", i);

			menuLazyLoadPage.scrollDown();

			WaitUtils.waitForSeconds(1);
		}

		log.info("Completed repeated scrolling");
	}

	public List<String> getLoadedProductNames() {

		log.info("Getting loaded product names");

		List<String> productNames = menuLazyLoadPage.getLoadedProductNames();

		log.info("Loaded product names count: {}", productNames.size());

		return productNames;
	}

	public void addTargetProductToCart(String productName) {

		if (productName == null || productName.trim().isEmpty()) {
			throw new IllegalArgumentException("Product name must not be null or empty");
		}

		log.info("Adding target product [{}] to cart", productName);

		menuLazyLoadPage.addProductToCart(productName);
	}

	public int getCartBadgeCount() {

		return menuLazyLoadPage.getCartBadgeCount_Menu();
	}

	public List<String> getLoadedProductIds() {

		log.info("Getting loaded product IDs");

		List<String> productIds = menuLazyLoadPage.getLoadedProductIds();

		log.info("Loaded product ID count: {}", productIds.size());

		return productIds;
	}

	public int getLoadedProductCount() {

		log.info("Getting currently loaded product count");

		int productCount = menuLazyLoadPage.getLoadedProductCount();

		log.info("Currently loaded product count: {}", productCount);

		return productCount;
	}

}