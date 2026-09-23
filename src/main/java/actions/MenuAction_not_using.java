
package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import components.MenuComponent;
import driver.DriverManager;
import page_object_manager.PageObjectManager;
import pages.InventoryPage;
import utils.WaitUtils;

public class MenuAction_not_using {

	private static final Logger log = LoggerFactory.getLogger(MenuAction_not_using.class);

	private final MenuComponent menu;

	private final PageObjectManager pageObjectManager;
	
	private final InventoryPage inventoryPage;

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	/**
	 * Creates MenuAction using the supplied MenuPage and PageObjectManager.
	 *
	 * @param menuPage          Menu page object
	 * @param pageObjectManager PageObjectManager
	 */
	public MenuAction_not_using(MenuComponent menu, PageObjectManager pageObjectManager) {

		if (menu == null) {
			log.error("MenuPage is null");
			throw new IllegalArgumentException("MenuPage must not be null");
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
     * Opens the sidebar menu.
     */
    public void clickMenuButton() {
        log.info("Clicking sidebar menu button");
        menu.clickMenuButton();
    }

    /**
     * Clicks Inventory / All Items from sidebar.
     */
    public void clickInventorySidebarLink() {
        log.info("Clicking Inventory sidebar link");
        menu.clickInventorySidebarLink();
    }

    /**
     * Verifies sidebar menu is displayed.
     */
    public boolean isMenuDisplayed() {
        log.info("Checking whether sidebar menu button is displayed");
        return menu.isMenuDisplayed();
    }
	
	/**
	 * Opens the SauceDemo application menu.
	 */
	public void openApplicationMenu() {

		log.info("Opening application menu");

		try {

			menu.clickMenuButton();

			log.info("Application menu opened successfully");

		} catch (Exception e) {

			log.error("Failed to open application menu", e);

			throw e;
		}
	}

	// =========================================================
	// LOGOUT
	// =========================================================

	 /**
     * Verifies Inventory sidebar link is displayed.
     */
    public boolean isInventorySidebarLinkDisplayed() {
        log.info("Checking whether Inventory sidebar link is displayed");
        
        return menu.isInventorySidebarLinkDisplayed();
    }
    
    
    public void clickLogout() {
        log.info("Clicking Logout option");
        menu.clickLogout();
    }
	
	/**
	 * Logs the user out of the SauceDemo application.
	 */
	public void logout() {

		log.info("Logging out from SauceDemo application");

		try {

			menu.clickLogout();

			log.info("Logout action completed successfully");

		} catch (Exception e) {

			log.error("Failed to logout from SauceDemo application", e);

			throw e;
		}
	}
}
