package stepdefinitions;

import assertions.MenuLazyLoadAssertions;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import actions.MenuLazyLoadActions;

public class MenuLazyLoadSteps {

	private static final Logger log = LoggerFactory.getLogger(MenuLazyLoadSteps.class);

	private final MenuLazyLoadAssertions menuLazyLoadAssertions;

	private final MenuLazyLoadActions menuLazyLoadActions;
	
	private final ScenarioContext context;

	private int loadedProductCount;

	public MenuLazyLoadSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}
		this.context = context;

		this.menuLazyLoadAssertions = new MenuLazyLoadAssertions(context.getPageObjectManager().getMenuLazyLoadPage());

		this.menuLazyLoadActions = new MenuLazyLoadActions(context.getPageObjectManager());

		log.debug("MenuLazyLoadSteps initialized");
	}

	// =========================================================
	// LL001
	// =========================================================
	@Given("the Lazy Load menu page is displayed")
	@Then("the Lazy Load menu page is displayed successfully")
	public void theLazyLoadMenuPageShouldBeDisplayedSuccessfully() {

		log.info("Verifying Lazy Load menu page is displayed");

		menuLazyLoadAssertions.verifyLazyLoadMenuPageDisplayed();
	}

	// =========================================================
	// LL002
	// =========================================================
	@Given("the user is on the dynamic catalog lazy-load page")
	public void the_user_is_on_the_dynamic_catalog_lazy_load_page() {

		log.info("Navigating to the dynamic catalog lazy-load page");

		menuLazyLoadActions.openLazyLoadMenuPage();

		log.info("User is on the dynamic catalog lazy-load page");
	}

	@Then("the page title should be displayed")
	public void the_page_title_should_be_displayed() {

		log.info("Verifying page title is displayed");

		menuLazyLoadAssertions.verifyPageTitleDisplayed();
	}

	@Then("the page title should not be empty")
	public void the_page_title_should_not_be_empty() {

		log.info("Verifying page title is not empty");

		menuLazyLoadAssertions.verifyPageTitleNotEmpty();
	}

	@Then("the catalog container should be displayed")
	public void the_catalog_container_should_be_displayed() {

		log.info("Verifying catalog container is displayed");

		menuLazyLoadAssertions.verifyCatalogContainerDisplayed();
	}

	@Then("the page URL should contain {string}")
	public void thePageUrlShouldContain(String expectedUrlPart) {

		log.info("Verifying page URL contains: {}", expectedUrlPart);

		menuLazyLoadAssertions.verifyPageUrlContains(expectedUrlPart);
	}

	// =========================================================
	// LL003
	// =========================================================
	@Then("the menu should be displayed")
	public void theMenuShouldBeDisplayed() {

		log.info("Verifying menu is displayed");

		menuLazyLoadAssertions.verifyMenuDisplayed();
	}

	// =========================================================
	// LL004
	// =========================================================
	@When("the user opens the menu")
	public void theUserOpensTheMenu() {

		log.info("Opening menu");

		menuLazyLoadActions.openMenu();
	}

	// =========================================================
	// LL005
	// =========================================================

	@When("the user checks the loaded product count")
	public void the_user_checks_the_loaded_product_count() {

		log.info("Checking the loaded product count");

		loadedProductCount = menuLazyLoadActions.getLoadedProductCount();

		log.info("Loaded product count captured: {}", loadedProductCount);
	}

	// =========================================================
	// LL006
	// =========================================================

	@Then("every loaded product should have a product name")
	public void every_loaded_product_should_have_a_product_name() {

		log.info("Verifying every loaded product has a product name");

		menuLazyLoadAssertions.verifyEveryLoadedProductHasProductName();
	}


	@Then("every loaded product should have a price")
	public void every_loaded_product_should_have_a_price() {

	    log.info("Verifying every loaded product has a price");

	    menuLazyLoadAssertions.verifyEveryLoadedProductHasPrice();
	}
	
	@Then("every loaded product should display an image")
	public void every_loaded_product_should_display_an_image() {

	    log.info("Verifying every loaded product displays an image");

	    menuLazyLoadAssertions.verifyEveryLoadedProductHasImage();
	}
	
	@Given("the user records the initial product count")
	public void the_user_records_the_initial_product_count() {

	    log.info("Recording initial loaded product count");

	    int initialProductCount =
	            menuLazyLoadActions.getLoadedProductCount();

	    context.setData("initialProductCount", initialProductCount);

	    log.info(
	            "Initial loaded product count recorded: {}",
	            initialProductCount
	    );
	}

	@When("the user scrolls down the catalog")
	public void the_user_scrolls_down_the_catalog() {

	    log.info("User scrolls down the Dynamic Catalog");

	    menuLazyLoadActions.scrollDown();
	}

	@When("waits for lazy loading to complete")
	public void waits_for_lazy_loading_to_complete() {

	    log.info("Waiting for Dynamic Catalog lazy loading to complete");

	    menuLazyLoadActions.waitForLazyLoadingToComplete();
	}

	@Then("additional products should be loaded")
	public void additional_products_should_be_loaded() {

	    log.info("Verifying additional products were loaded");

	    int initialProductCount =
	            (Integer) context.getData("initialProductCount");

	    int currentProductCount =
	            menuLazyLoadActions.getLoadedProductCount();

	    Assert.assertTrue(
	            currentProductCount > initialProductCount,
	            "Additional products should be loaded. " +
	            "Initial count: " + initialProductCount +
	            ", Current count: " + currentProductCount
	    );

	    log.info(
	            "Additional products loaded successfully. Initial: {}, Current: {}",
	            initialProductCount,
	            currentProductCount
	    );
	}

	@Then("the {string} menu option should be displayed")
	public void the_menu_option_should_be_displayed(String menuOption) {

		log.info("Verifying menu option is displayed: {}", menuOption);

		boolean displayed = context.getPageObjectManager().getMenuLazyLoadPage().isSelectedMenuOptionDisplayed(menuOption);

		Assert.assertTrue(displayed, "Menu option [" + menuOption + "] should be displayed");
	}

	@Then("the loaded product count should be greater than zero")
	public void the_loaded_product_count_should_be_greater_than_zero() {

		log.info("Verifying loaded product count is greater than zero. Actual count: {}", loadedProductCount);

		menuLazyLoadAssertions.verifyLoadedProductCountGreaterThanZero(loadedProductCount);
	}

	// =========================================================
	// LL004
	// =========================================================
	@Then("at least one product should be displayed")
	public void at_least_one_product_should_be_displayed() {

		log.info("Verifying at least one product is displayed");

		menuLazyLoadAssertions.verifyAtLeastOneProductDisplayed();
	}

	// =========================================================
	// LL005
	// =========================================================
	@Then("the menu items should be displayed")
	public void theMenuItemsShouldBeDisplayed() {

		log.info("Verifying menu items are displayed");

		menuLazyLoadAssertions.verifyMenuItemsDisplayed();
	}

	// =========================================================
	// LL006
	// =========================================================
	@When("the user closes the menu")
	public void theUserClosesTheMenu() {

		log.info("Closing menu");

		menuLazyLoadActions.closeMenu();
	}

	// =========================================================
	// LL007
	// =========================================================
	@Then("the menu should not be displayed")
	public void theMenuShouldNotBeDisplayed() {

		log.info("Verifying menu is not displayed");

		menuLazyLoadAssertions.verifyMenuNotDisplayed();
	}

	// =========================================================
	// LL008
	// =========================================================
	@When("the user scrolls to the bottom of the page")
	public void theUserScrollsToTheBottomOfThePage() {

		log.info("Scrolling to the bottom of the page");

		menuLazyLoadActions.scrollToBottom();
	}

	// =========================================================
	// LL009
	// =========================================================
	@When("the user scrolls to the top of the page")
	public void theUserScrollsToTheTopOfThePage() {

		log.info("Scrolling to the top of the page");

		menuLazyLoadActions.scrollToTop();
	}

	// =========================================================
	// LL010
	// =========================================================
	@Then("the menu should remain accessible")
	public void theMenuShouldRemainAccessible() {

		log.info("Verifying menu remains accessible");

		menuLazyLoadAssertions.verifyMenuAccessible();
	}

	// =========================================================
	// LL011
	// =========================================================
	@Then("all menu options should be displayed")
	public void allMenuOptionsShouldBeDisplayed() {

		log.info("Verifying all menu options are displayed");

		menuLazyLoadAssertions.verifyAllMenuOptionsDisplayed();
	}

	// =========================================================
	// LL012
	// =========================================================
	@When("the user clicks the {string} menu option")
	public void theUserClicksTheMenuOption(String menuOption) {

		log.info("Clicking menu option: {}", menuOption);

		menuLazyLoadActions.clickMenuOption(menuOption);
	}

	// =========================================================
	// LL013
	// =========================================================
	@Then("the selected menu option should be displayed")
	public void theSelectedMenuOptionShouldBeDisplayed() {

		log.info("Verifying selected menu option is displayed");

		menuLazyLoadAssertions.verifySelectedMenuOptionDisplayed();
	}
	
	
	@When("the user loads all products")
	public void the_user_loads_all_products() {

	    log.info("User loads all Dynamic Catalog products");

	    menuLazyLoadActions.loadAllProducts();
	}

	// =========================================================
	// LL014
	// =========================================================
	@Then("the menu should be closed")
	public void theMenuShouldBeClosed() {

		log.info("Verifying menu is closed");

		menuLazyLoadAssertions.verifyMenuClosed();
	}

	// =========================================================
	// LL015
	// =========================================================
	@When("the user reopens the menu")
	public void theUserReopensTheMenu() {

		log.info("Reopening menu");

		menuLazyLoadActions.openMenu();
	}

	// =========================================================
	// LL016
	// =========================================================
	@Then("the menu should contain {int} options")
	public void theMenuShouldContainOptions(int expectedCount) {

		log.info("Verifying menu contains {} options", expectedCount);

		menuLazyLoadAssertions.verifyMenuOptionCount(expectedCount);
	}
}