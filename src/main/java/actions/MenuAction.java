package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import components.MenuComponent;
import page_object_manager.PageObjectManager;
import pages.InventoryPage;

public class MenuAction {

	private static final Logger log = LoggerFactory.getLogger(MenuAction.class);

	private final MenuComponent menu;
	private final PageObjectManager pageObjectManager;
	private final InventoryPage inventoryPage;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	/**
	 * Creates MenuAction using the supplied MenuComponent and PageObjectManager.
	 *
	 * @param menu              MenuComponent
	 * @param pageObjectManager PageObjectManager
	 */
	public MenuAction(MenuComponent menu, PageObjectManager pageObjectManager) {

		if (menu == null) {
			log.error("MenuComponent is null");
			throw new IllegalArgumentException("MenuComponent must not be null");
		}

		if (pageObjectManager == null) {
			log.error("PageObjectManager is null");
			throw new IllegalArgumentException("PageObjectManager must not be null");
		}

		this.menu = menu;
		this.pageObjectManager = pageObjectManager;
		this.inventoryPage = pageObjectManager.getInventoryPage();

		log.info("MenuAction initialized successfully");
	}

	// =========================================================
	// OPEN APPLICATION MENU
	// =========================================================

	/**
	 * Clicks the hamburger Menu button.
	 */
	public void clickMenuButton() {

		log.info("Clicking sidebar menu button");

		try {

			menu.clickMenuButton();

			log.info("Sidebar menu button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click sidebar menu button", e);

			throw e;
		}
	}

	/**
	 * Opens the application sidebar menu.
	 */
	public void openApplicationMenu() {

		log.info("Opening application menu");

		try {

			menu.open();

			log.info("Application menu opened successfully");

		} catch (Exception e) {

			log.error("Failed to open application menu", e);

			throw e;
		}
	}

	// =========================================================
	// MENU DISPLAY
	// =========================================================

	/**
	 * Checks whether the menu button is displayed.
	 */
	public boolean isMenuDisplayed() {

		log.info("Checking whether sidebar menu button is displayed");

		return menu.isMenuDisplayed();
	}

	/**
	 * Checks whether the application menu is open.
	 */
	public boolean isMenuOpen() {

		log.info("Checking whether application menu is open");

		return menu.isMenuOpen();
	}

	/**
	 * Checks whether the menu button is displayed.
	 */
	public boolean isMenuButtonDisplayed() {

		log.info("Checking whether menu button is displayed");

		return menu.isMenuButtonDisplayed();
	}

	// =========================================================
	// CLOSE MENU
	// =========================================================

	/**
	 * Closes the sidebar menu.
	 */
	public void closeMenu() {

		log.info("Closing application menu");

		try {

			menu.close();

			log.info("Application menu closed successfully");

		} catch (Exception e) {

			log.error("Failed to close application menu", e);

			throw e;
		}
	}

	/**
	 * Alias method for closing the menu.
	 */
	public void clickCloseMenuButton() {

		log.info("Clicking Close Menu button");

		closeMenu();
	}

	// =========================================================
	// ALL ITEMS / INVENTORY
	// =========================================================

	/**
	 * Clicks Inventory / All Items sidebar link.
	 */
	public void clickInventorySidebarLink() {

		log.info("Clicking Inventory sidebar link");

		try {

			menu.clickInventorySidebarLink();

			log.info("Inventory sidebar link clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click Inventory sidebar link", e);

			throw e;
		}
	}

	/**
	 * Clicks All Items menu option.
	 */
	public void clickAllItems() {

		log.info("Clicking All Items menu option");

		try {

			menu.clickAllItems();

			log.info("All Items menu option clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click All Items menu option", e);

			throw e;
		}
	}

	/**
	 * Checks whether Inventory / All Items is displayed.
	 */
	public boolean isInventorySidebarLinkDisplayed() {

		log.info("Checking whether Inventory sidebar link is displayed");

		return menu.isInventorySidebarLinkDisplayed();
	}

	// =========================================================
	// DYNAMIC CATALOG
	// =========================================================

	/**
	 * Clicks Dynamic Catalog menu option.
	 */
	public void clickDynamicCatalog() {

		log.info("Clicking Dynamic Catalog menu option");

		try {

			menu.clickDynamicCatalog();

			log.info("Dynamic Catalog clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click Dynamic Catalog", e);

			throw e;
		}
	}

	/**
	 * Checks whether Dynamic Catalog is displayed.
	 */
	public boolean isDynamicCatalogDisplayed() {

		log.info("Checking whether Dynamic Catalog is displayed");

		return menu.isDynamicCatalogDisplayed();
	}

	/**
	 * Checks whether Dynamic Catalog submenu is displayed.
	 */
	public boolean isDynamicCatalogSubmenuDisplayed() {

		log.info("Checking whether Dynamic Catalog submenu is displayed");

		return menu.isDynamicCatalogSubmenuDisplayed();
	}

	/**
	 * Checks whether Dynamic Catalog is expanded.
	 */
	public boolean isDynamicCatalogExpanded() {

		log.info("Checking whether Dynamic Catalog is expanded");

		return menu.isDynamicCatalogExpanded();
	}

	/**
	 * Toggles Dynamic Catalog submenu.
	 */
	public void toggleDynamicCatalog() {

		log.info("Toggling Dynamic Catalog submenu");

		try {

			menu.toggleDynamicCatalog();

			log.info("Dynamic Catalog submenu toggled successfully");

		} catch (Exception e) {

			log.error("Failed to toggle Dynamic Catalog submenu", e);

			throw e;
		}
	}

	// =========================================================
	// DYNAMIC CATALOG - LAZY LOAD
	// =========================================================

	/**
	 * Clicks Lazy Load submenu option.
	 */
	public void clickLazyLoad() {

		log.info("Clicking Dynamic Catalog - Lazy Load");

		try {

			menu.clickLazyLoad();

			log.info("Lazy Load clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click Lazy Load", e);

			throw e;
		}
	}

	/**
	 * Checks whether Lazy Load is displayed.
	 */
	public boolean isLazyLoadDisplayed() {

		log.info("Checking whether Lazy Load is displayed");

		return menu.isLazyLoadDisplayed();
	}

	// =========================================================
	// DYNAMIC CATALOG - SPINNER
	// =========================================================

	/**
	 * Clicks Spinner submenu option.
	 */
	public void clickSpinner() {

		log.info("Clicking Dynamic Catalog - Spinner");

		try {

			menu.clickSpinner();

			log.info("Spinner clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click Spinner", e);

			throw e;
		}
	}

	/**
	 * Checks whether Spinner is displayed.
	 */
	public boolean isSpinnerDisplayed() {

		log.info("Checking whether Spinner is displayed");

		return menu.isSpinnerDisplayed();
	}

	// =========================================================
	// DYNAMIC CATALOG - SLIDER
	// =========================================================

	/**
	 * Clicks Slider submenu option.
	 */
	public void clickSlider() {

		log.info("Clicking Dynamic Catalog - Slider");

		try {

			menu.clickSlider();

			log.info("Slider clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click Slider", e);

			throw e;
		}
	}

	/**
	 * Checks whether Slider is displayed.
	 */
	public boolean isSliderDisplayed() {

		log.info("Checking whether Slider is displayed");

		return menu.isSliderDisplayed();
	}

	// =========================================================
	// ABOUT
	// =========================================================

	/**
	 * Clicks About menu option.
	 */
	public void clickAbout() {

		log.info("Clicking About menu option");

		try {

			menu.clickAbout();

			log.info("About option clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click About option", e);

			throw e;
		}
	}

	// =========================================================
	// LOGOUT
	// =========================================================

	/**
	 * Clicks Logout option.
	 */
	public void clickLogout() {

		log.info("Clicking Logout option");

		try {

			menu.clickLogout();

			log.info("Logout option clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click Logout option", e);

			throw e;
		}
	}

	/**
	 * Logs out the current user.
	 */
	public void logout() {

		log.info("Logging out from SauceDemo application");

		try {

			menu.logout();

			log.info("Logout action completed successfully");

		} catch (Exception e) {

			log.error("Failed to logout from SauceDemo application", e);

			throw e;
		}
	}

	// =========================================================
	// RESET APPLICATION STATE
	// =========================================================

	/**
	 * Clicks Reset App State.
	 */
	public void resetAppState() {

		log.info("Clicking Reset App State option");

		try {

			menu.resetAppState();

			log.info("Reset App State clicked successfully");

		} catch (Exception e) {

			log.error("Failed to reset application state", e);

			throw e;
		}
	}
}