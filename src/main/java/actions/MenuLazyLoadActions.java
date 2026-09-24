package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page_object_manager.PageObjectManager;
import pages.MenuLazyLoadPage;

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
	
	
	
	public int getLoadedProductCount() {

	    log.info("Getting currently loaded product count");

	    int productCount =
	            menuLazyLoadPage.getLoadedProductCount();

	    log.info(
	            "Currently loaded product count: {}",
	            productCount
	    );

	    return productCount;
	}
	
	
	
}