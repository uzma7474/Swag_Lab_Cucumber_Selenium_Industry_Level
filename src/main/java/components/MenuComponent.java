package components;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import utils.WaitUtils;

/**
 * Reusable SauceDemo hamburger menu component.
 */
public class MenuComponent extends BasePage {

	// =========================================================
	// MAIN MENU LOCATORS
	// =========================================================

	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuButton;

	@FindBy(id = "react-burger-cross-btn")
	private WebElement closeMenuButton;

	@FindBy(id = "inventory_sidebar_link")
	private WebElement allItemsLink;

	@FindBy(id = "about_sidebar_link")
	private WebElement aboutLink;

	@FindBy(id = "logout_sidebar_link")
	private WebElement logoutLink;

	@FindBy(id = "reset_sidebar_link")
	private WebElement resetAppStateLink;

	@FindBy(css = ".bm-menu-wrap")
	private WebElement menuContainer;

	@FindBy(id = "inventory_sidebar_link")
	private WebElement inventorySidebarLink;

	// =========================================================
	// DYNAMIC CATALOG LOCATORS
	// =========================================================

	@FindBy(id = "dynamic_catalog_sidebar_link")
	private WebElement dynamicCatalogLink;

	@FindBy(id = "dynamic_catalog_submenu")
	private WebElement dynamicCatalogSubmenu;

	@FindBy(id = "dynamic_catalog_lazy_load_link")
	private WebElement lazyLoadLink;

	@FindBy(id = "dynamic_catalog_spinner_link")
	private WebElement spinnerLink;

	@FindBy(id = "dynamic_catalog_slider_link")
	private WebElement sliderLink;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public MenuComponent() {

		super();

		log.debug("Initializing MenuComponent");

		PageFactory.initElements(driver, this);

		log.debug("MenuComponent initialized successfully");
	}

	// =========================================================
	// MENU BUTTON
	// =========================================================

	/**
	 * Opens the SauceDemo hamburger menu.
	 */
	public void open() {

		log.info("Opening SauceDemo hamburger menu");

		try {

			click(menuButton);

			log.info("Hamburger menu opened successfully");

		} catch (Exception e) {

			log.error("Failed to open hamburger menu", e);

			throw e;
		}
	}

	/**
	 * Clicks the sidebar menu button.
	 */
	public void clickMenuButton() {

		log.info("Clicking hamburger menu button");

		try {
			// WaitUtils.waitForVisibility(driver, menuButton);
			// WaitUtils.waitForElementToBeClickable(driver, menuButton);

			click(menuButton);

			log.info("Hamburger menu button clicked successfully");

		} catch (Exception e) {
			log.error("Failed to click hamburger menu button", e);
			throw e;
		}
	}

	/**
	 * Checks whether the menu button is displayed.
	 */
	public boolean isMenuDisplayed() {

		boolean displayed = isDisplayed(menuButton);

		log.info("Sidebar menu button displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether the menu button is displayed.
	 */
	public boolean isMenuButtonDisplayed() {

		boolean displayed = isDisplayed(menuButton);

		log.debug("Menu button displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether the sidebar menu is open.
	 */
	public boolean isMenuOpen() {

		boolean displayed = isDisplayed(closeMenuButton);

		log.debug("Hamburger menu open: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether the menu container is displayed.
	 */
	public boolean isMenuContainerDisplayed() {

		try {

			boolean displayed = WaitUtils.waitForVisibility(driver, menuContainer).isDisplayed();

			log.info("Application menu container displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("Application menu container is not displayed", e);

			return false;
		}
	}

	// =========================================================
	// CLOSE MENU
	// =========================================================

	/**
	 * Closes the hamburger menu.
	 */
	public void close() {

		log.info("Closing SauceDemo hamburger menu");

		try {

			click(closeMenuButton);

			log.info("Hamburger menu closed successfully");

		} catch (Exception e) {

			log.error("Failed to close hamburger menu", e);

			throw e;
		}
	}

	// =========================================================
	// ALL ITEMS
	// =========================================================

	/**
	 * Clicks All Items menu option.
	 */
	public void clickAllItems() {

		log.info("Clicking All Items menu option");

		try {

			click(allItemsLink);

			log.info("All Items selected successfully");

		} catch (Exception e) {

			log.error("Failed to click All Items", e);

			throw e;
		}
	}

	/**
	 * Checks whether All Items is displayed.
	 */
	public boolean isAllItemsDisplayed() {

		try {

			boolean displayed = WaitUtils.waitForVisibility(driver, allItemsLink).isDisplayed();

			log.info("All Items displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("All Items is not displayed");

			return false;
		}
	}

	/**
	 * Checks whether Inventory / All Items sidebar link is displayed.
	 */
	public boolean isInventorySidebarLinkDisplayed() {

		return isAllItemsDisplayed();
	}

	// =========================================================
	// DYNAMIC CATALOG
	// =========================================================

	/**
	 * Clicks Dynamic Catalog menu option.
	 */

	public void clickDynamicCatalog() {

		log.info("Clicking Dynamic Catalog menu item");

		try {
			WaitUtils.waitForVisibility(driver, dynamicCatalogLink);
			WaitUtils.waitForElementToBeClickable(driver, dynamicCatalogLink);

			// Scroll the element into the visible viewport
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView({block:'center', inline:'nearest'});", dynamicCatalogLink);

			// Small wait after scrolling
			WaitUtils.waitForElementToBeClickable(driver, dynamicCatalogLink);

			dynamicCatalogLink.click();

			log.info("Dynamic Catalog menu item clicked successfully");

		} catch (ElementNotInteractableException e) {

			log.warn("Dynamic Catalog was found but not interactable. " + "Attempting JavaScript click.");

			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", dynamicCatalogLink);

				log.info("Dynamic Catalog clicked successfully using JavaScript");

			} catch (Exception jsException) {

				log.error("JavaScript click also failed for Dynamic Catalog", jsException);

				throw jsException;
			}

		} catch (Exception e) {

			log.error("Failed to click Dynamic Catalog menu item", e);

			throw e;
		}
	}

	public void expandDynamicCatalog() {

		log.info("Ensuring Dynamic Catalog is expanded");

		try {

			String expanded = dynamicCatalogLink.getAttribute("aria-expanded");

			log.debug("Current Dynamic Catalog aria-expanded value: {}", expanded);

			if (!"true".equalsIgnoreCase(expanded)) {

				clickDynamicCatalog();

				WaitUtils.waitForElementToBeClickable(driver, dynamicCatalogLink);

				log.info("Dynamic Catalog expanded");

			} else {

				log.info("Dynamic Catalog is already expanded");
			}

		} catch (Exception e) {

			log.error("Failed to ensure Dynamic Catalog is expanded", e);

			throw e;
		}
	}

	public void collapseDynamicCatalog() {

		log.info("Ensuring Dynamic Catalog is collapsed");

		try {

			String expanded = dynamicCatalogLink.getAttribute("aria-expanded");

			if ("true".equalsIgnoreCase(expanded)) {

				clickDynamicCatalog();

				log.info("Dynamic Catalog collapsed");

			} else {

				log.info("Dynamic Catalog is already collapsed");
			}

		} catch (Exception e) {

			log.error("Failed to ensure Dynamic Catalog is collapsed", e);

			throw e;
		}
	}

	/**
	 * Checks whether Dynamic Catalog is displayed.
	 */
	public boolean isDynamicCatalogDisplayed() {

		try {

			boolean displayed = WaitUtils.waitForVisibility(driver, dynamicCatalogLink).isDisplayed();

			log.info("Dynamic Catalog displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("Dynamic Catalog is not displayed");

			return false;
		}
	}

	/**
	 * Checks whether Dynamic Catalog submenu is displayed.
	 */
	public boolean isDynamicCatalogSubmenuDisplayed() {

		try {

			boolean displayed = WaitUtils.waitForVisibility(driver, dynamicCatalogSubmenu).isDisplayed();

			log.info("Dynamic Catalog submenu displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("Dynamic Catalog submenu is not displayed");

			return false;
		}
	}

	/**
	 * Checks whether Dynamic Catalog is expanded.
	 *
	 * Uses aria-expanded from the actual DOM.
	 */
	public boolean isDynamicCatalogExpanded() {

		try {

			String expanded = dynamicCatalogLink.getAttribute("aria-expanded");

			boolean isExpanded = "true".equalsIgnoreCase(expanded);

			log.info("Dynamic Catalog expanded state: {}", isExpanded);

			return isExpanded;

		} catch (Exception e) {

			log.error("Unable to determine Dynamic Catalog expanded state", e);

			return false;
		}
	}

	/**
	 * Toggles Dynamic Catalog submenu.
	 */
	public void toggleDynamicCatalog() {

		log.info("Toggling Dynamic Catalog submenu");

		try {

			click(dynamicCatalogLink);

			log.info("Dynamic Catalog submenu toggled successfully");

		} catch (Exception e) {

			log.error("Failed to toggle Dynamic Catalog submenu", e);

			throw e;
		}
	}

	// =========================================================
	// LAZY LOAD
	// =========================================================

	/**
	 * Clicks Lazy Load submenu option.
	 */
	public void clickLazyLoad() {

		log.info("Clicking Dynamic Catalog - Lazy Load");

		try {

			click(lazyLoadLink);

			log.info("Lazy Load selected successfully");

		} catch (Exception e) {

			log.error("Failed to click Lazy Load", e);

			throw e;
		}
	}

	/**
	 * Checks whether Lazy Load is displayed.
	 */
	public boolean isLazyLoadDisplayed() {

		try {

			boolean displayed = WaitUtils.waitForVisibility(driver, lazyLoadLink).isDisplayed();

			log.info("Lazy Load displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("Lazy Load is not displayed");

			return false;
		}
	}

	// =========================================================
	// SPINNER
	// =========================================================

	/**
	 * Clicks Spinner submenu option.
	 */
	public void clickSpinner() {

		log.info("Clicking Dynamic Catalog - Spinner");

		try {

			click(spinnerLink);

			log.info("Spinner selected successfully");

		} catch (Exception e) {

			log.error("Failed to click Spinner", e);

			throw e;
		}
	}

	/**
	 * Checks whether Spinner is displayed.
	 */
	public boolean isSpinnerDisplayed() {

		try {

			boolean displayed = WaitUtils.waitForVisibility(driver, spinnerLink).isDisplayed();

			log.info("Spinner displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("Spinner is not displayed");

			return false;
		}
	}

	// =========================================================
	// SLIDER
	// =========================================================

	/**
	 * Clicks Slider submenu option.
	 */
	public void clickSlider() {

		log.info("Clicking Dynamic Catalog - Slider");

		try {

			click(sliderLink);

			log.info("Slider selected successfully");

		} catch (Exception e) {

			log.error("Failed to click Slider", e);

			throw e;
		}
	}

	/**
	 * Checks whether Slider is displayed.
	 */
	public boolean isSliderDisplayed() {

		try {

			boolean displayed = WaitUtils.waitForVisibility(driver, sliderLink).isDisplayed();

			log.info("Slider displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("Slider is not displayed");

			return false;
		}
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

			click(aboutLink);

			log.info("About option selected successfully");

		} catch (Exception e) {

			log.error("Failed to click About option", e);

			throw e;
		}
	}

	/**
	 * Checks whether About option is displayed.
	 */
	public boolean isAboutDisplayed() {

		try {

			boolean displayed = WaitUtils.waitForVisibility(driver, aboutLink).isDisplayed();

			log.info("About option displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("About option is not displayed");

			return false;
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

			click(logoutLink);

			log.info("Logout option clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click Logout option", e);

			throw e;
		}
	}

	public boolean isCloseMenuButtonDisplayed_not_using() {

		try {
			boolean displayed = closeMenuButton.isDisplayed();

			log.debug("Close Menu button displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {
			log.debug("Close Menu button is not displayed: {}", e.getMessage());
			return false;
		}
	}

	public boolean isCloseMenuButtonDisplayed() {

		log.info("Checking whether Close Menu button is displayed");

		try {
			WebElement element = WaitUtils.waitForVisibility(driver, closeMenuButton);

			boolean displayed = element.isDisplayed();

			log.info("Close Menu button displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {
			log.warn("Close Menu button is not displayed: {}", e.getMessage());

			return false;
		}
	}

	/**
	 * Logs out the current user.
	 */
	public void logout() {

		log.info("Logging out from SauceDemo");

		try {

			click(logoutLink);

			log.info("Logout completed successfully");

		} catch (Exception e) {

			log.error("Failed to logout from SauceDemo", e);

			throw e;
		}
	}

	/**
	 * Checks whether Logout option is displayed.
	 */
	public boolean isLogoutDisplayed() {

		try {

			boolean displayed = WaitUtils.waitForVisibility(driver, logoutLink).isDisplayed();

			log.info("Logout option displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("Logout option is not displayed");

			return false;
		}
	}

	// =========================================================
	// RESET APP STATE
	// =========================================================

	/**
	 * Resets SauceDemo application state.
	 */
	public void resetAppState() {

		log.info("Resetting SauceDemo application state");

		try {

			click(resetAppStateLink);

			log.info("SauceDemo application state reset successfully");

		} catch (Exception e) {

			log.error("Failed to reset application state", e);

			throw e;
		}
	}

	/**
	 * Checks whether Reset App State is displayed.
	 */
	public boolean isResetAppStateDisplayed() {

		try {

			boolean displayed = WaitUtils.waitForVisibility(driver, resetAppStateLink).isDisplayed();

			log.info("Reset App State displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("Reset App State is not displayed");

			return false;
		}
	}

	public void clickInventorySidebarLink() {
		log.info("Clicking Inventory sidebar link");

		try {
			WaitUtils.waitForVisibility(driver, inventorySidebarLink);
			WaitUtils.waitForElementToBeClickable(driver, inventorySidebarLink);
			click(inventorySidebarLink);

			log.info("Inventory sidebar link clicked successfully");
		} catch (Exception e) {
			log.error("Failed to click Inventory sidebar link", e);
			throw e;
		}
	}

}