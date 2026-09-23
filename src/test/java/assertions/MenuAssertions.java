
package assertions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import components.MenuComponent;
import config.ConfigManager;
import context.ScenarioContext;
import driver.DriverManager;
import page_object_manager.PageObjectManager;
import pages.LoginPage;

public class MenuAssertions {

	private static final Logger log = LoggerFactory.getLogger(MenuAssertions.class);

	private final MenuComponent menuPage;

	private final LoginPage loginPage;

	private final ScenarioContext scenarioContext;

	private final PageObjectManager pageObjectManager;

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	/**
	 * Creates MenuAssertions using the supplied MenuPage, LoginPage,
	 * PageObjectManager and ScenarioContext.
	 *
	 * @param menuPage          Menu page object
	 * @param loginPage         Login page object
	 * @param pageObjectManager PageObjectManager
	 * @param scenarioContext   Scenario context
	 */
	public MenuAssertions(MenuComponent menuPage, LoginPage loginPage, PageObjectManager pageObjectManager,
			ScenarioContext scenarioContext) {

		if (menuPage == null) {
			log.error("MenuPage is null");
			throw new IllegalArgumentException("MenuPage must not be null");
		}

		if (loginPage == null) {
			log.error("LoginPage is null");
			throw new IllegalArgumentException("LoginPage must not be null");
		}

		if (pageObjectManager == null) {
			log.error("PageObjectManager is null");
			throw new IllegalArgumentException("PageObjectManager must not be null");
		}

		if (scenarioContext == null) {
			log.error("ScenarioContext is null");
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.menuPage = menuPage;
		this.loginPage = loginPage;
		this.pageObjectManager = pageObjectManager;
		this.scenarioContext = scenarioContext;

		log.debug("MenuAssertions initialized successfully");
	}

	// =========================================================
	// LOGIN PAGE VERIFICATION
	// =========================================================

	/**
	 * Verifies that the user is redirected to the Login page after logout.
	 */
	public void verifyRedirectedToLoginPage() {

		log.info("Verifying user is redirected to Login page");

		String currentUrl = DriverManager.getDriver().getCurrentUrl();

		log.info("Current URL after logout: {}", currentUrl);

		// -----------------------------------------------------
		// Verify URL
		// -----------------------------------------------------

		String expectedBaseUrl = ConfigManager.getBaseUrl();

		boolean isLoginUrl = currentUrl.equals(expectedBaseUrl) || currentUrl.equals(expectedBaseUrl + "/")
				|| currentUrl.endsWith("/");

		Assert.assertTrue(isLoginUrl, "User should be redirected to Login page. " + "Expected URL: " + expectedBaseUrl
				+ " but current URL was: " + currentUrl);

		log.info("Login URL verification passed");

		// -----------------------------------------------------
		// Verify Login Page
		// -----------------------------------------------------

		boolean loginPageDisplayed = loginPage.isLoginPageDisplayed();

		Assert.assertTrue(loginPageDisplayed, "Login page should be displayed after logout");

		log.info("Login page is displayed successfully after logout");
	}

	// =========================================================
	// LOGIN PAGE DISPLAYED
	// =========================================================

	/**
	 * Verifies that the Login page is displayed.
	 */
	public void verifyLoginPageDisplayed() {

		log.info("Verifying Login page is displayed");

		boolean displayed = loginPage.isLoginPageDisplayed();

		Assert.assertTrue(displayed, "Login page should be displayed");

		log.info("Login page displayed successfully");
	}

	// =========================================================
	// MENU DISPLAY VERIFICATION
	// =========================================================

	/**
	 * Verifies that the application menu is displayed.
	 */
	public void verifyApplicationMenuDisplayed() {

		log.info("Verifying application menu is displayed");

		boolean displayed = menuPage.isMenuDisplayed();

		Assert.assertTrue(displayed, "Application menu should be displayed");

		log.info("Application menu displayed successfully");
	}
	
	// =========================================================
	// MENU BUTTON / OPEN / CLOSE VERIFICATION
	// =========================================================

	/**
	 * Verifies that the hamburger menu button is displayed.
	 */
	public void verifyMenuButtonDisplayed() {

	    log.info("Verifying hamburger menu button is displayed");

	    boolean displayed = menuPage.isMenuButtonDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Hamburger menu button should be displayed"
	    );

	    log.info("Hamburger menu button is displayed successfully");
	}

	/**
	 * Verifies that the sidebar menu is open.
	 */
	public void verifyMenuOpen() {

	    log.info("Verifying sidebar menu is open");

	    boolean open = menuPage.isMenuOpen();

	    Assert.assertTrue(
	            open,
	            "Sidebar menu should be open"
	    );

	    log.info("Sidebar menu is open");
	}

	
	/**
	 * Verifies that the application menu button is displayed.
	 */
	public void verifyMenuDisplayed() {

	    log.info("Verifying application menu button is displayed");

	    boolean displayed = menuPage.isMenuDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Application menu button should be displayed"
	    );

	    log.info("Application menu button is displayed successfully");
	}
	
	/**
	 * Verifies that the sidebar menu is closed.
	 */
	public void verifyMenuClosed() {

	    log.info("Verifying sidebar menu is closed");

	    boolean open = menuPage.isMenuOpen();

	    Assert.assertFalse(
	            open,
	            "Sidebar menu should be closed"
	    );

	    log.info("Sidebar menu is closed successfully");
	}

	public void verifyCloseMenuDisplayed() {

	    log.info("Verifying Close Menu button is displayed");

	    boolean displayed = menuPage.isCloseMenuButtonDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Close Menu button should be displayed"
	    );

	    log.info("Close Menu button is displayed successfully");
	}


	// =========================================================
	// SIDEBAR MENU ITEM VERIFICATION
	// =========================================================

	/**
	 * Verifies that the All Items/Inventory sidebar link is displayed.
	 */
	public void verifyInventorySidebarLinkDisplayed() {

	    log.info("Verifying Inventory sidebar link is displayed");

	    boolean displayed = menuPage.isInventorySidebarLinkDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Inventory sidebar link should be displayed"
	    );

	    log.info("Inventory sidebar link is displayed successfully");
	}

	/**
	 * Verifies that the Dynamic Catalog menu item is displayed.
	 */
	public void verifyDynamicCatalogDisplayed() {

	    log.info("Verifying Dynamic Catalog menu item is displayed");

	    boolean displayed = menuPage.isDynamicCatalogDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Dynamic Catalog menu item should be displayed"
	    );

	    log.info("Dynamic Catalog menu item is displayed successfully");
	}

	/**
	 * Verifies that the About link is displayed.
	 */
	public void verifyAboutDisplayed() {

	    log.info("Verifying About link is displayed");

	    boolean displayed = menuPage.isAboutDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "About link should be displayed"
	    );

	    log.info("About link is displayed successfully");
	}

	/**
	 * Verifies that the Logout link is displayed.
	 */
	public void verifyLogoutDisplayed() {

	    log.info("Verifying Logout link is displayed");

	    boolean displayed = menuPage.isLogoutDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Logout link should be displayed"
	    );

	    log.info("Logout link is displayed successfully");
	}

	/**
	 * Verifies that the Reset App State link is displayed.
	 */
	public void verifyResetAppStateDisplayed() {

	    log.info("Verifying Reset App State link is displayed");

	    boolean displayed = menuPage.isResetAppStateDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Reset App State link should be displayed"
	    );

	    log.info("Reset App State link is displayed successfully");
	}


	// =========================================================
	// DYNAMIC CATALOG VERIFICATION
	// =========================================================

	/**
	 * Verifies that Dynamic Catalog submenu is displayed.
	 */
	public void verifyDynamicCatalogSubmenuDisplayed() {

	    log.info("Verifying Dynamic Catalog submenu is displayed");

	    boolean displayed = menuPage.isDynamicCatalogSubmenuDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Dynamic Catalog submenu should be displayed"
	    );

	    log.info("Dynamic Catalog submenu is displayed successfully");
	}

	/**
	 * Verifies that Dynamic Catalog is expanded.
	 */
	public void verifyDynamicCatalogExpanded() {

	    log.info("Verifying Dynamic Catalog is expanded");

	    boolean expanded = menuPage.isDynamicCatalogExpanded();

	    Assert.assertTrue(
	            expanded,
	            "Dynamic Catalog should be expanded"
	    );

	    log.info("Dynamic Catalog is expanded successfully");
	}

	/**
	 * Verifies that Dynamic Catalog is collapsed.
	 */
	public void verifyDynamicCatalogCollapsed() {

	    log.info("Verifying Dynamic Catalog is collapsed");

	    boolean expanded = menuPage.isDynamicCatalogExpanded();

	    Assert.assertFalse(
	            expanded,
	            "Dynamic Catalog should be collapsed"
	    );

	    log.info("Dynamic Catalog is collapsed successfully");
	}


	// =========================================================
	// DYNAMIC CATALOG SUBMENU ITEMS
	// =========================================================

	/**
	 * Verifies that Lazy Load submenu item is displayed.
	 */
	public void verifyLazyLoadDisplayed() {

	    log.info("Verifying Lazy Load submenu item is displayed");

	    boolean displayed = menuPage.isLazyLoadDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Lazy Load submenu item should be displayed"
	    );

	    log.info("Lazy Load submenu item is displayed successfully");
	}

	/**
	 * Verifies that Spinner submenu item is displayed.
	 */
	public void verifySpinnerDisplayed() {

	    log.info("Verifying Spinner submenu item is displayed");

	    boolean displayed = menuPage.isSpinnerDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Spinner submenu item should be displayed"
	    );

	    log.info("Spinner submenu item is displayed successfully");
	}

	/**
	 * Verifies that Slider submenu item is displayed.
	 */
	public void verifySliderDisplayed() {

	    log.info("Verifying Slider submenu item is displayed");

	    boolean displayed = menuPage.isSliderDisplayed();

	    Assert.assertTrue(
	            displayed,
	            "Slider submenu item should be displayed"
	    );

	    log.info("Slider submenu item is displayed successfully");
	}


	// =========================================================
	// NEGATIVE VERIFICATIONS
	// =========================================================

	/**
	 * Verifies that Dynamic Catalog submenu is not displayed.
	 */
	public void verifyDynamicCatalogSubmenuNotDisplayed() {

	    log.info("Verifying Dynamic Catalog submenu is not displayed");

	    boolean displayed = menuPage.isDynamicCatalogSubmenuDisplayed();

	    Assert.assertFalse(
	            displayed,
	            "Dynamic Catalog submenu should not be displayed"
	    );

	    log.info("Dynamic Catalog submenu is not displayed");
	}

	/**
	 * Verifies that Lazy Load submenu item is not displayed.
	 */
	public void verifyLazyLoadNotDisplayed() {

	    log.info("Verifying Lazy Load submenu item is not displayed");

	    boolean displayed = menuPage.isLazyLoadDisplayed();

	    Assert.assertFalse(
	            displayed,
	            "Lazy Load submenu item should not be displayed"
	    );

	    log.info("Lazy Load submenu item is not displayed");
	}

	/**
	 * Verifies that Spinner submenu item is not displayed.
	 */
	public void verifySpinnerNotDisplayed() {

	    log.info("Verifying Spinner submenu item is not displayed");

	    boolean displayed = menuPage.isSpinnerDisplayed();

	    Assert.assertFalse(
	            displayed,
	            "Spinner submenu item should not be displayed"
	    );

	    log.info("Spinner submenu item is not displayed");
	}

	/**
	 * Verifies that Slider submenu item is not displayed.
	 */
	public void verifySliderNotDisplayed() {

	    log.info("Verifying Slider submenu item is not displayed");

	    boolean displayed = menuPage.isSliderDisplayed();

	    Assert.assertFalse(
	            displayed,
	            "Slider submenu item should not be displayed"
	    );

	    log.info("Slider submenu item is not displayed");
	}
	
	
}
