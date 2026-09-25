package stepdefinitions;

import assertions.MenuLazyLoadAssertions;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		int initialProductCount = menuLazyLoadActions.getLoadedProductCount();

		context.setData("initialProductCount", initialProductCount);

		log.info("Initial loaded product count recorded: {}", initialProductCount);
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

		int initialProductCount = (Integer) context.getData("initialProductCount");

		int currentProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(currentProductCount > initialProductCount, "Additional products should be loaded. "
				+ "Initial count: " + initialProductCount + ", Current count: " + currentProductCount);

		log.info("Additional products loaded successfully. Initial: {}, Current: {}", initialProductCount,
				currentProductCount);
	}

	@When("the user scrolls toward the bottom of the page")
	public void the_user_scrolls_toward_the_bottom_of_the_page() {

		log.info("User scrolls toward the bottom of the Dynamic Catalog page");

		menuLazyLoadActions.scrollToBottom();
	}

	@When("lazy loading completes")
	public void lazy_loading_completes() {

		log.info("Waiting for Dynamic Catalog lazy loading to complete");

		menuLazyLoadActions.waitForLazyLoadingToComplete();
	}

	@Then("the current product count should be greater than the initial product count")
	public void the_current_product_count_should_be_greater_than_the_initial_product_count() {

		log.info("Verifying current product count is greater than initial product count");

		Integer initialProductCount = (Integer) context.getData("initialProductCount");

		Assert.assertNotNull(initialProductCount, "Initial product count was not recorded in ScenarioContext");

		int currentProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(currentProductCount > initialProductCount,
				"Current product count should be greater than initial product count. " + "Initial count: "
						+ initialProductCount + ", Current count: " + currentProductCount);

		log.info("Product count increased successfully. Initial: {}, Current: {}", initialProductCount,
				currentProductCount);
	}

	@When("the user repeatedly scrolls down the page")
	public void the_user_repeatedly_scrolls_down_the_page() {

		log.info("User repeatedly scrolls down the Dynamic Catalog page");

		int initialProductCount = menuLazyLoadActions.getLoadedProductCount();

		context.setData("initialProductCount", initialProductCount);

		log.info("Initial product count before repeated scrolling: {}", initialProductCount);

		menuLazyLoadActions.repeatedlyScrollDown();
	}

	@Then("additional products should continue loading")
	public void additional_products_should_continue_loading() {

		log.info("Verifying additional products were loaded after repeated scrolling");

		Integer initialProductCount = (Integer) context.getData("initialProductCount");

		Assert.assertNotNull(initialProductCount, "Initial product count was not recorded");

		int currentProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(currentProductCount > initialProductCount, "Additional products should continue loading. "
				+ "Initial count: " + initialProductCount + ", Current count: " + currentProductCount);

		log.info("Additional products loaded successfully. Initial: {}, Current: {}", initialProductCount,
				currentProductCount);
	}

	@Then("already loaded products should remain available")
	public void already_loaded_products_should_remain_available() {

		log.info("Verifying previously loaded products remain available");

		Integer initialProductCount = (Integer) context.getData("initialProductCount");

		Assert.assertNotNull(initialProductCount, "Initial product count was not recorded");

		int currentProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(currentProductCount >= initialProductCount,
				"Previously loaded products should remain available. " + "Initial count: " + initialProductCount
						+ ", Current count: " + currentProductCount);

		log.info("Previously loaded products remain available. Initial: {}, Current: {}", initialProductCount,
				currentProductCount);
	}

	@When("the user continuously scrolls until no additional products are loaded")
	public void the_user_continuously_scrolls_until_no_additional_products_are_loaded() {

		log.info("User continuously scrolls until no additional products are loaded");

		menuLazyLoadActions.loadAllProducts();

		log.info("Continuous scrolling completed. No additional products are expected to load");
	}

	@Then("all available products should be displayed")
	public void all_available_products_should_be_displayed() {

		log.info("Verifying all available products are displayed");

		menuLazyLoadAssertions.verifyAllProductsLoaded();

		log.info("All available products are displayed successfully");
	}

	@When("the user scrolls until the end of the catalog")
	public void the_user_scrolls_until_the_end_of_the_catalog() {

		log.info("Scrolling until the end of the Dynamic Catalog");

		// Load all currently available products
		menuLazyLoadActions.loadAllProducts();

		// Record stable product IDs
		List<String> productIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(productIds.isEmpty(), "No products were loaded before reaching the end of the catalog");

		context.setData("productIdsBeforeFinalScroll", new ArrayList<>(productIds));

		log.info("Recorded {} product IDs before final scroll: {}", productIds.size(), productIds);
	}

	@When("the user performs another scroll operation")
	public void the_user_performs_another_scroll_operation() {

		log.info("Performing another scroll operation after reaching catalog end");

		menuLazyLoadActions.scrollDown();

		WaitUtils.waitForSeconds(1);

		log.info("Additional scroll operation completed");
	}

	@Then("no additional duplicate products should be loaded")
	public void no_additional_duplicate_products_should_be_loaded() {

		log.info("Verifying no additional products are loaded after final scroll");

		@SuppressWarnings("unchecked")
		List<String> productIdsBeforeFinalScroll = (List<String>) context.getData("productIdsBeforeFinalScroll");

		Assert.assertNotNull(productIdsBeforeFinalScroll, "Product IDs before final scroll were not recorded");

		List<String> productIdsAfterFinalScroll = menuLazyLoadActions.getLoadedProductIds();

		log.info("Before final scroll: {}", productIdsBeforeFinalScroll);

		log.info("After final scroll: {}", productIdsAfterFinalScroll);

		Assert.assertEquals(productIdsAfterFinalScroll, productIdsBeforeFinalScroll,
				"Additional or duplicate product cards were loaded after " + "reaching the end of the catalog");

		log.info("No additional product cards were loaded after reaching the end");
	}

//	@Then("no additional duplicate products should be loaded")
//	public void no_additional_duplicate_products_should_be_loaded() {
//
//		log.info("Verifying that no additional duplicate products were loaded");
//
//		@SuppressWarnings("unchecked")
//		List<String> productsBeforeFinalScroll = (List<String>) context.getData("productsBeforeFinalScroll");
//
//		Assert.assertNotNull(productsBeforeFinalScroll, "Product list before final scroll was not recorded");
//
//		List<String> productsAfterFinalScroll = menuLazyLoadActions.getLoadedProductNames();
//
//		log.info("Products before final scroll: {}", productsBeforeFinalScroll);
//
//		log.info("Products after final scroll: {}", productsAfterFinalScroll);
//
//		/*
//		 * Verify that the final scroll did not add any new product occurrence beyond
//		 * the previously recorded list.
//		 */
//		Assert.assertEquals(productsAfterFinalScroll.size(), productsBeforeFinalScroll.size(),
//				"Additional product cards were loaded after reaching the " + "end of the catalog. Before: "
//						+ productsBeforeFinalScroll.size() + ", After: " + productsAfterFinalScroll.size());
//
//		/*
//		 * Verify that the previously loaded product list is unchanged.
//		 */
//		Assert.assertEquals(productsAfterFinalScroll, productsBeforeFinalScroll,
//				"The product list changed after the final scroll");
//
//		log.info("No additional product cards were loaded after the final scroll");
//	}

//	@Then("no additional duplicate products should be loaded")
//	public void no_additional_duplicate_products_should_be_loaded() {
//
//		log.info("Verifying no duplicate products were loaded after reaching catalog end");
//
//		@SuppressWarnings("unchecked")
//		List<String> productsBeforeFinalScroll = (List<String>) context.getData("productsBeforeFinalScroll");
//
//		Assert.assertNotNull(productsBeforeFinalScroll, "Product list before final scroll was not recorded");
//
//		List<String> productsAfterFinalScroll = menuLazyLoadActions.getLoadedProductNames();
//
//		// Verify no duplicate names exist in the final product list
//		Set<String> uniqueProducts = new HashSet<>(productsAfterFinalScroll);
//
//		Assert.assertEquals(uniqueProducts.size(), productsAfterFinalScroll.size(),
//				"Duplicate products were found after the final scroll: " + productsAfterFinalScroll);
//
//		// Verify the product count did not increase after reaching the end
//		Assert.assertEquals(productsAfterFinalScroll.size(), productsBeforeFinalScroll.size(),
//				"Additional products were loaded after reaching the end. " + "Before final scroll: "
//						+ productsBeforeFinalScroll.size() + ", After final scroll: "
//						+ productsAfterFinalScroll.size());
//
//		// Verify every previously loaded product is still available
//		Assert.assertTrue(productsAfterFinalScroll.containsAll(productsBeforeFinalScroll),
//				"Previously loaded products are missing after the final scroll");
//
//		log.info("No duplicate or additional products were loaded after the final scroll. " + "Total products: {}",
//				productsAfterFinalScroll.size());
//	}

	@Then("the {string} menu option should be displayed")
	public void the_menu_option_should_be_displayed(String menuOption) {

		log.info("Verifying menu option is displayed: {}", menuOption);

		boolean displayed = context.getPageObjectManager().getMenuLazyLoadPage()
				.isSelectedMenuOptionDisplayed(menuOption);

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

	@When("the user scrolls through the entire catalog")
	public void the_user_scrolls_through_the_entire_catalog() {

		log.info("Scrolling through the entire Dynamic Catalog");

		// Scroll until all lazy-loaded products are loaded
		menuLazyLoadActions.loadAllProducts();

		// Capture the final loaded product card IDs
		List<String> productIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(productIds.isEmpty(), "No products were loaded after scrolling through the entire catalog");

		// Store product IDs for the Then step
		context.setData("allLoadedProductIds", new ArrayList<>(productIds));

		log.info("Completed scrolling through catalog. Total loaded product cards: {}", productIds.size());
	}

	@Then("no product should appear more than once")
	public void no_product_should_appear_more_than_once() {

		log.info("Verifying that no product card appears more than once");

		@SuppressWarnings("unchecked")
		List<String> productIds = (List<String>) context.getData("allLoadedProductIds");

		Assert.assertNotNull(productIds, "Loaded product IDs were not recorded in ScenarioContext");

		Assert.assertFalse(productIds.isEmpty(), "No loaded product IDs were found");

		// HashSet removes duplicate IDs
		Set<String> uniqueProductIds = new HashSet<>(productIds);

		log.info("Total product cards: {}, Unique product card IDs: {}", productIds.size(), uniqueProductIds.size());

		Assert.assertEquals(uniqueProductIds.size(), productIds.size(),
				"Duplicate product card IDs were found. Product IDs: " + productIds);

		log.info("No duplicate product cards were found");
	}

	@Given("the user has loaded additional products")
	public void the_user_has_loaded_additional_products() {

		log.info("Loading additional products in the Dynamic Catalog");

		// Record the initial number of loaded products
		int initialProductCount = menuLazyLoadActions.getLoadedProductCount();

		// Scroll to trigger lazy loading
		menuLazyLoadActions.scrollDown();

		// Wait for newly loaded products
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		int currentProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(currentProductCount > initialProductCount, "Additional products should be loaded. "
				+ "Initial count: " + initialProductCount + ", Current count: " + currentProductCount);

		// Store all currently loaded product IDs
		List<String> loadedProductIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(loadedProductIds.isEmpty(), "No loaded product IDs were found");

		// Store the IDs in ScenarioContext
		context.setData("loadedProductIdsBeforeScrollTop", new ArrayList<>(loadedProductIds));

		log.info("Additional products loaded successfully. Initial: {}, Current: {}", initialProductCount,
				currentProductCount);

		log.debug("Product IDs before scrolling to top: {}", loadedProductIds);
	}

	@When("the user scrolls back to the top")
	public void the_user_scrolls_back_to_the_top() {

		log.info("Scrolling back to the top of the Dynamic Catalog");

		menuLazyLoadActions.scrollToTop();

		// Give the browser time to complete scrolling/rendering
		WaitUtils.waitForSeconds(1);

		log.info("Successfully scrolled back to the top");
	}

	@Then("previously loaded products should still be displayed")
	public void previously_loaded_products_should_still_be_displayed() {

		log.info("Verifying previously loaded products remain displayed");

		@SuppressWarnings("unchecked")
		List<String> productIdsBeforeScrollTop = (List<String>) context.getData("loadedProductIdsBeforeScrollTop");

		Assert.assertNotNull(productIdsBeforeScrollTop,
				"Previously loaded product IDs were not recorded in ScenarioContext");

		Assert.assertFalse(productIdsBeforeScrollTop.isEmpty(),
				"Previously loaded product ID list should not be empty");

		// Get currently loaded product IDs after scrolling to top
		List<String> currentProductIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(currentProductIds.isEmpty(),
				"No products are currently displayed after scrolling to the top");

		log.debug("Product IDs before scrolling to top: {}", productIdsBeforeScrollTop);

		log.debug("Current product IDs after scrolling to top: {}", currentProductIds);

		// Verify every previously loaded product is still present
		for (String productId : productIdsBeforeScrollTop) {

			Assert.assertTrue(currentProductIds.contains(productId),
					"Previously loaded product [" + productId + "] is no longer displayed after scrolling to the top");
		}

		log.info("All previously loaded products are still displayed. " + "Verified {} product(s)",
				productIdsBeforeScrollTop.size());
	}

	@Given("the user records the loaded product count")
	public void the_user_records_the_loaded_product_count() {

		log.info("Recording the current loaded product count");

		int loadedProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(loadedProductCount > 0,
				"At least one product should be loaded, but found: " + loadedProductCount);

		context.setData("loadedProductCountBeforeScroll", loadedProductCount);

		log.info("Loaded product count before scrolling: {}", loadedProductCount);
	}

	@When("the user scrolls down and then back up")
	public void the_user_scrolls_down_and_then_back_up() {

		log.info("Scrolling down the Dynamic Catalog");

		// Scroll down to trigger lazy loading
		menuLazyLoadActions.scrollDown();

		// Wait for lazy-loaded products
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		log.info("Scrolling back to the top");

		// Scroll back to the top
		menuLazyLoadActions.scrollToTop();

		// Allow rendering/scrolling to complete
		WaitUtils.waitForSeconds(1);

		log.info("Completed scroll down and back up operation");
	}

	@Then("the loaded product count should not unexpectedly decrease")
	public void the_loaded_product_count_should_not_unexpectedly_decrease() {

		log.info("Verifying loaded product count did not unexpectedly decrease");

		Integer previousProductCount = (Integer) context.getData("loadedProductCountBeforeScroll");

		Assert.assertNotNull(previousProductCount, "Loaded product count was not recorded in ScenarioContext");

		int currentProductCount = menuLazyLoadActions.getLoadedProductCount();

		log.info("Product count before scrolling: {}, current product count: {}", previousProductCount,
				currentProductCount);

		Assert.assertTrue(currentProductCount >= previousProductCount, "Loaded product count unexpectedly decreased. "
				+ "Before scrolling: " + previousProductCount + ", After scrolling: " + currentProductCount);

		log.info("Loaded product count remained stable or increased. " + "Before: {}, After: {}", previousProductCount,
				currentProductCount);
	}

	@When("the user scrolls until a target product is loaded")
	public void the_user_scrolls_until_a_target_product_is_loaded() {

		log.info("Scrolling Dynamic Catalog until target product is loaded");

		// Target product should have been stored in ScenarioContext
		String targetProductName = (String) context.getData("targetProductName");

		Assert.assertNotNull(targetProductName, "Target product name was not provided in ScenarioContext");

		Assert.assertFalse(targetProductName.trim().isEmpty(), "Target product name must not be empty");

		log.info("Target product: {}", targetProductName);

		boolean productLoaded = false;

		for (int attempt = 1; attempt <= 20; attempt++) {

			log.debug("Checking for target product. Attempt: {}", attempt);

			List<String> loadedProductNames = menuLazyLoadActions.getLoadedProductNames();

			if (loadedProductNames.stream().anyMatch(name -> name.equalsIgnoreCase(targetProductName.trim()))) {

				productLoaded = true;

				log.info("Target product [{}] is loaded after {} attempt(s)", targetProductName, attempt);

				break;
			}

			// Scroll to trigger lazy loading
			menuLazyLoadActions.scrollDown();

			// Wait for newly loaded products
			menuLazyLoadActions.waitForLazyLoadingToComplete();
		}

		Assert.assertTrue(productLoaded,
				"Target product [" + targetProductName + "] was not loaded after scrolling through the catalog");
	}

	@When("the user scrolls until product {string} is loaded")
	public void the_user_scrolls_until_product_is_loaded(String targetProductName) {

		log.info("Scrolling Dynamic Catalog until product [{}] is loaded", targetProductName);

		Assert.assertNotNull(targetProductName, "Target product name must not be null");

		Assert.assertFalse(targetProductName.trim().isEmpty(), "Target product name must not be empty");

		boolean productLoaded = false;

		for (int attempt = 1; attempt <= 20; attempt++) {

			List<String> loadedProductNames = menuLazyLoadActions.getLoadedProductNames();

			log.debug("Attempt {} - Loaded products: {}", attempt, loadedProductNames);

			productLoaded = loadedProductNames.stream()
					.anyMatch(name -> name.equalsIgnoreCase(targetProductName.trim()));

			if (productLoaded) {

				log.info("Target product [{}] found after {} attempt(s)", targetProductName, attempt);

				// Store only after the product is actually found
				context.setData("targetProductName", targetProductName.trim());

				break;
			}

			log.debug("Target product [{}] not found. Scrolling down", targetProductName);

			menuLazyLoadActions.scrollDown();

			menuLazyLoadActions.waitForLazyLoadingToComplete();
		}

		Assert.assertTrue(productLoaded,
				"Target product [" + targetProductName + "] was not loaded after scrolling through the catalog");
	}

	@Then("the target product should be displayed")
	public void the_target_product_should_be_displayed() {

		String targetProductName = (String) context.getData("targetProductName");

		Assert.assertNotNull(targetProductName, "Target product name was not recorded in ScenarioContext");

		log.info("Verifying target product [{}] is displayed", targetProductName);

		List<String> loadedProductNames = menuLazyLoadActions.getLoadedProductNames();

		boolean productDisplayed = loadedProductNames.stream()
				.anyMatch(name -> name.equalsIgnoreCase(targetProductName));

		Assert.assertTrue(productDisplayed, "Target product [" + targetProductName + "] should be displayed");

		log.info("Target product [{}] is displayed successfully", targetProductName);
	}

	@Given("the user is on the Dynamic Catalog Lazy Load page")
	public void user_is_on_the_dynamic_catalog_lazy_load_page() {

		log.info("Opening Dynamic Catalog Lazy Load page");

		menuLazyLoadActions.openLazyLoadMenuPage();

		log.info("Dynamic Catalog Lazy Load page opened successfully");
	}

	@When("the user scrolls back to previously loaded product {string}")
	public void the_user_scrolls_back_to_previously_loaded_product(String targetProductName) {

		log.info("Scrolling back to previously loaded product [{}]", targetProductName);

		Assert.assertNotNull(targetProductName, "Previously loaded product name must not be null");

		Assert.assertFalse(targetProductName.trim().isEmpty(), "Previously loaded product name must not be empty");

		boolean productFound = false;

		for (int attempt = 1; attempt <= 20; attempt++) {

			List<String> loadedProductNames = menuLazyLoadActions.getLoadedProductNames();

			log.debug("Attempt {} - Currently loaded products: {}", attempt, loadedProductNames);

			productFound = loadedProductNames.stream()
					.anyMatch(name -> name.equalsIgnoreCase(targetProductName.trim()));

			if (productFound) {

				log.info("Previously loaded product [{}] found on attempt {}", targetProductName, attempt);

				// Store it for the next Then step
				context.setData("targetProductName", targetProductName.trim());

				break;
			}

			// Scroll upward
			menuLazyLoadActions.scrollUp();

			WaitUtils.waitForSeconds(1);
		}

		Assert.assertTrue(productFound,
				"Previously loaded product [" + targetProductName + "] was not found after scrolling back");

		log.info("Successfully scrolled back to product [{}]", targetProductName);
	}

	@When("the user scrolls back to a previously loaded product")
	public void the_user_scrolls_back_to_a_previously_loaded_product() {

		log.info("Scrolling back to a previously loaded product");

		String targetProductName = (String) context.getData("targetProductName");

		Assert.assertNotNull(targetProductName, "Target product name was not recorded in ScenarioContext. "
				+ "Make sure the previous step stores 'targetProductName'.");

		Assert.assertFalse(targetProductName.trim().isEmpty(), "Target product name must not be empty");

		log.info("Previously loaded target product: [{}]", targetProductName);

		boolean productFound = false;

		for (int attempt = 1; attempt <= 20; attempt++) {

			List<String> loadedProductNames = menuLazyLoadActions.getLoadedProductNames();

			log.debug("Attempt {} - Loaded product names: {}", attempt, loadedProductNames);

			if (loadedProductNames.stream().anyMatch(name -> name.equalsIgnoreCase(targetProductName.trim()))) {

				productFound = true;

				log.info("Previously loaded product [{}] found on attempt {}", targetProductName, attempt);

				break;
			}

			// Scroll upward
			menuLazyLoadActions.scrollUp();

			WaitUtils.waitForSeconds(1);
		}

		Assert.assertTrue(productFound,
				"Previously loaded product [" + targetProductName + "] was not found after scrolling back");

		log.info("Successfully scrolled back to previously loaded product [{}]", targetProductName);
	}

	@When("the user scrolls to the bottom")
	public void the_user_scrolls_to_the_bottom() {

		log.info("Scrolling Dynamic Catalog to the bottom");

		menuLazyLoadActions.scrollToBottom();

		// Allow lazy-loaded content and browser scrolling to settle
		WaitUtils.waitForSeconds(1);

		log.info("User has scrolled to the bottom of the catalog");
	}

	@Then("the browser should reach the bottom of the catalog")
	public void the_browser_should_reach_the_bottom_of_the_catalog() {

		log.info("Verifying browser reached the bottom of the catalog");

		menuLazyLoadAssertions.verifyBrowserAtBottom();
	}

	@Then("the top of the catalog should be displayed")
	public void the_top_of_the_catalog_should_be_displayed() {

		log.info("Verifying that the top of the catalog is displayed");

		menuLazyLoadAssertions.verifyBrowserAtTop();

		log.info("Top of the catalog is displayed successfully");
	}

	@When("the user repeatedly scrolls up and down")
	public void the_user_repeatedly_scrolls_up_and_down() {

		log.info("User repeatedly scrolling up and down the Dynamic Catalog");

		// Capture the currently loaded products before scrolling
		List<String> initialProductIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(initialProductIds.isEmpty(), "No products are loaded before starting repeated scrolling");

		context.setData("initialProductIdsBeforeRepeatedScroll", new ArrayList<>(initialProductIds));

		context.setData("initialProductCountBeforeRepeatedScroll", initialProductIds.size());

		menuLazyLoadActions.repeatedlyScrollUpAndDown();

		log.info("Repeated up/down scrolling completed. Initial loaded product count: {}", initialProductIds.size());
	}

	@Then("the catalog should remain stable")
	public void the_catalog_should_remain_stable() {

		log.info("Verifying Dynamic Catalog remains stable after repeated scrolling");

		Integer initialProductCount = (Integer) context.getData("initialProductCountBeforeRepeatedScroll");

		Assert.assertNotNull(initialProductCount, "Initial product count was not recorded in ScenarioContext");

		int currentProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(currentProductCount >= initialProductCount,
				"Catalog should remain stable. Initial product count: " + initialProductCount
						+ ", Current product count: " + currentProductCount);

		log.info("Catalog stability verified. Initial count: {}, Current count: {}", initialProductCount,
				currentProductCount);
	}

	@Then("products should not disappear unexpectedly")
	public void products_should_not_disappear_unexpectedly() {

		log.info("Verifying previously loaded products have not disappeared");

		@SuppressWarnings("unchecked")
		List<String> initialProductIds = (List<String>) context.getData("initialProductIdsBeforeRepeatedScroll");

		Assert.assertNotNull(initialProductIds, "Initial product IDs were not recorded in ScenarioContext");

		Assert.assertFalse(initialProductIds.isEmpty(), "Initial product ID list should not be empty");

		List<String> currentProductIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(currentProductIds.isEmpty(), "No products are currently loaded after repeated scrolling");

		for (String productId : initialProductIds) {

			Assert.assertTrue(currentProductIds.contains(productId),
					"Previously loaded product [" + productId + "] disappeared unexpectedly after repeated scrolling");
		}

		log.info("Verified that all {} initially loaded products are still available", initialProductIds.size());
	}

	@Then("products should not be duplicated")
	public void products_should_not_be_duplicated() {

		log.info("Verifying that Dynamic Catalog does not contain duplicate products");

		List<String> currentProductIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(currentProductIds.isEmpty(), "No loaded product IDs were found");

		Set<String> uniqueProductIds = new HashSet<>(currentProductIds);

		Assert.assertEquals(uniqueProductIds.size(), currentProductIds.size(),
				"Duplicate product cards were found after repeated scrolling. " + "Product IDs: " + currentProductIds);

		log.info("Duplicate validation passed. Total products: {}, Unique products: {}", currentProductIds.size(),
				uniqueProductIds.size());
	}

	@Then("the dynamic catalog page should load successfully")
	public void the_dynamic_catalog_page_should_load_successfully() {

		log.info("Verifying Dynamic Catalog page loaded successfully");

		menuLazyLoadAssertions.verifyLazyLoadMenuPageDisplayed();

		log.info("Dynamic Catalog page loaded successfully");
	}

	@Then("the catalog should be displayed")
	public void the_catalog_should_be_displayed() {

		log.info("Verifying Dynamic Catalog is displayed");

		menuLazyLoadAssertions.verifyCatalogContainerDisplayed();

		log.info("Dynamic Catalog is displayed successfully");
	}

	@Then("the initial product loading behavior should work correctly")
	public void the_initial_product_loading_behavior_should_work_correctly() {

		log.info("Verifying initial product loading behavior");

		menuLazyLoadAssertions.verifyInitialProductsDisplayed();

		log.info("Initial product loading behavior verified successfully");
	}

	@When("the user loads all available products")
	public void the_user_loads_all_available_products() {

		log.info("Loading all available products from Dynamic Catalog");

		menuLazyLoadActions.loadAllProducts();
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		List<String> loadedProductNames = menuLazyLoadActions.getLoadedProductNames();

		Assert.assertFalse(loadedProductNames.isEmpty(),
				"No products were loaded after loading all available products");

		context.setData("productNamesAfterLoadingAll", new ArrayList<>(loadedProductNames));

		log.info("All available products loaded successfully. Product count: {}", loadedProductNames.size());
	}

	@Then("each product should have a non-empty name")
	public void each_product_should_have_a_non_empty_name() {

		log.info("Verifying every loaded product has a non-empty name");

		List<String> productNames = menuLazyLoadActions.getLoadedProductNames();

		Assert.assertFalse(productNames.isEmpty(), "No loaded products were found");

		for (int i = 0; i < productNames.size(); i++) {

			String productName = productNames.get(i);

			Assert.assertNotNull(productName, "Product #" + (i + 1) + " name should not be null");

			Assert.assertFalse(productName.trim().isEmpty(), "Product #" + (i + 1) + " name should not be empty");

			log.debug("Product #{} name verified: [{}]", i + 1, productName);
		}

		log.info("All {} loaded products have non-empty names", productNames.size());
	}

	@Then("product names should remain unchanged while scrolling")
	public void product_names_should_remain_unchanged_while_scrolling() {

		log.info("Verifying product names remain unchanged after scrolling");

		@SuppressWarnings("unchecked")
		List<String> productNamesBeforeScrolling = (List<String>) context.getData("productNamesAfterLoadingAll");

		Assert.assertNotNull(productNamesBeforeScrolling, "Product names were not recorded in ScenarioContext");

		Assert.assertFalse(productNamesBeforeScrolling.isEmpty(),
				"Product names recorded before scrolling should not be empty");

		// Scroll through the catalog and return to the top
		menuLazyLoadActions.scrollToTop();
		WaitUtils.waitForSeconds(1);

		menuLazyLoadActions.scrollToBottom();
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		List<String> productNamesAfterScrolling = menuLazyLoadActions.getLoadedProductNames();

		Assert.assertEquals(productNamesAfterScrolling, productNamesBeforeScrolling,
				"Product names changed after scrolling. " + "Before: " + productNamesBeforeScrolling + ", After: "
						+ productNamesAfterScrolling);

		log.info("Product names remained unchanged after scrolling. Total products: {}",
				productNamesAfterScrolling.size());
	}

	@Given("the user is at the top of the dynamic catalog")
	public void the_user_is_at_the_top_of_the_dynamic_catalog() {

		log.info("Verifying user is at the top of the Dynamic Catalog");

		menuLazyLoadActions.scrollToTop();
		WaitUtils.waitForSeconds(1);

		boolean atTop = context.getPageObjectManager().getMenuLazyLoadPage().isBrowserAtTop();

		Assert.assertTrue(atTop, "User should be at the top of the Dynamic Catalog");

		log.info("User is successfully at the top of the Dynamic Catalog");
	}

	@When("the user searches for product {string}")
	public void the_user_searches_for_product(String productName) {

		log.info("Searching for product [{}] in currently loaded Dynamic Catalog", productName);

		Assert.assertNotNull(productName, "Product name must not be null");

		Assert.assertFalse(productName.trim().isEmpty(), "Product name must not be empty");

		boolean productFound = menuLazyLoadActions.isProductNameLoaded(productName.trim());

		context.setData("searchedProductName", productName.trim());
		context.setData("searchedProductFound", productFound);

		log.info("Product [{}] found in currently loaded catalog: {}", productName, productFound);
	}

	@Then("the product should not be found")
	public void the_product_should_not_be_found() {

		log.info("Verifying searched product is not found");

		String searchedProductName = (String) context.getData("searchedProductName");

		Boolean productFound = (Boolean) context.getData("searchedProductFound");

		Assert.assertNotNull(searchedProductName, "Searched product name was not recorded in ScenarioContext");

		Assert.assertNotNull(productFound, "Product search result was not recorded in ScenarioContext");

		Assert.assertFalse(productFound,
				"Product [" + searchedProductName + "] should not be found in the currently loaded catalog");

		log.info("Verified product [{}] was not found in the currently loaded catalog", searchedProductName);
	}

	@When("the user attempts to add {string} to the cart")
	public void the_user_attempts_to_add_to_the_cart(String productName) {

		log.info("Attempting to add product [{}] to cart from Dynamic Catalog", productName);

		Assert.assertNotNull(productName, "Product name must not be null");

		Assert.assertFalse(productName.trim().isEmpty(), "Product name must not be empty");

		int initialCartCount = menuLazyLoadActions.getCartBadgeCount();

		context.setData("initialCartCountBeforeDynamicCatalogAdd", initialCartCount);

		context.setData("attemptedCartProduct", productName.trim());

		/*
		 * Dynamic Catalog cards do not contain Add to Cart buttons. Therefore, we
		 * intentionally do not call addProductToCart().
		 *
		 * Verify that the requested product is not directly addable from this catalog.
		 */
		boolean productHasAddToCartButton = menuLazyLoadActions.hasAddToCartButton(productName.trim());

		context.setData("dynamicCatalogProductHasAddToCartButton", productHasAddToCartButton);

		log.info("Product [{}] has Add to Cart button in Dynamic Catalog: {}", productName, productHasAddToCartButton);
	}

	@Then("the product should not be added to the cart")
	public void the_product_should_not_be_added_to_the_cart() {

		log.info("Verifying product was not added to the cart");

		Integer initialCartCount = (Integer) context.getData("initialCartCountBeforeDynamicCatalogAdd");

		Boolean hasAddToCartButton = (Boolean) context.getData("dynamicCatalogProductHasAddToCartButton");

		String productName = (String) context.getData("attemptedCartProduct");

		Assert.assertNotNull(initialCartCount, "Initial cart count was not recorded in ScenarioContext");

		Assert.assertNotNull(hasAddToCartButton, "Dynamic Catalog Add to Cart state was not recorded");

		Assert.assertNotNull(productName, "Attempted product name was not recorded");

		Assert.assertFalse(hasAddToCartButton,
				"Product [" + productName + "] should not have an Add to Cart button in Dynamic Catalog");

		int currentCartCount = menuLazyLoadActions.getCartBadgeCount();

		Assert.assertEquals(currentCartCount, initialCartCount.intValue(),
				"Cart count should not change when attempting to add product [" + productName
						+ "] from Dynamic Catalog");

		log.info("Verified product [{}] was not added to cart. Cart count remained: {}", productName, currentCartCount);
	}

	@When("the target product has not yet been loaded")
	public void the_target_product_has_not_yet_been_loaded() {

		log.info("Verifying target product has not yet been loaded");

		String unloadedProductId = menuLazyLoadActions.getFirstUnloadedProductId();

		Assert.assertNotNull(unloadedProductId, "No unloaded product was found in the Dynamic Catalog");

		Assert.assertFalse(unloadedProductId.trim().isEmpty(), "Unloaded product ID should not be empty");

		context.setData("unloadedTargetProductId", unloadedProductId);

		boolean productLoaded = menuLazyLoadActions.isProductLoadedById(unloadedProductId);

		context.setData("unloadedTargetProductLoaded", productLoaded);

		log.info("Target product [{}] loaded status: {}", unloadedProductId, productLoaded);

		Assert.assertFalse(productLoaded, "Target product [" + unloadedProductId + "] should not be loaded yet");
	}

	@Then("the product action should not be available")
	public void the_product_action_should_not_be_available() {

		log.info("Verifying no product action is available for unloaded product");

		String unloadedProductId = (String) context.getData("unloadedTargetProductId");

		Assert.assertNotNull(unloadedProductId, "Unloaded target product ID was not recorded in ScenarioContext");

		boolean actionAvailable = menuLazyLoadActions.hasProductAction(unloadedProductId);

		Assert.assertFalse(actionAvailable,
				"Product action should not be available for unloaded product [" + unloadedProductId + "]");

		log.info("Verified no product action is available for unloaded product [{}]", unloadedProductId);
	}

	@Given("the user has reached the end of the catalog")
	public void the_user_has_reached_the_end_of_the_catalog() {

		log.info("Loading all products to reach the end of the Dynamic Catalog");

		menuLazyLoadActions.loadAllProducts();
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		int loadedProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(loadedProductCount > 0, "At least one product should be loaded after reaching the end");

		Assert.assertEquals(context.getPageObjectManager().getMenuLazyLoadPage().getLoadingPlaceholderCount(), 0,
				"Loading placeholders should not remain after reaching the end");

		List<String> productIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(productIds.isEmpty(), "No product IDs were found after reaching the end of the catalog");

		context.setData("productIdsAtCatalogEnd", new ArrayList<>(productIds));

		log.info("Reached end of Dynamic Catalog. Loaded products: {}", loadedProductCount);
	}

	@When("the user rapidly scrolls from top to bottom")
	public void the_user_rapidly_scrolls_from_top_to_bottom() {

		log.info("Starting rapid scrolling from top to bottom");

		// Make sure the test starts from the top.
		menuLazyLoadActions.scrollToTop();
		WaitUtils.waitForSeconds(1);

		List<String> initialProductIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(initialProductIds.isEmpty(), "No products are loaded at the start of rapid scrolling");

		context.setData("rapidScrollInitialProductIds", new ArrayList<>(initialProductIds));

		context.setData("rapidScrollInitialProductCount", initialProductIds.size());

		// Rapidly scroll through the catalog.
		for (int iteration = 1; iteration <= 5; iteration++) {

			log.debug("Rapid scroll iteration {} - scrolling down", iteration);

			menuLazyLoadActions.scrollDown();
			WaitUtils.waitForSeconds(1);

			log.debug("Rapid scroll iteration {} - scrolling up", iteration);

			menuLazyLoadActions.scrollUp();
			WaitUtils.waitForSeconds(1);
		}

		// Finally reach the bottom so lazy-loaded products are loaded.
		menuLazyLoadActions.scrollToBottom();
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		List<String> finalProductIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(finalProductIds.isEmpty(), "No products are loaded after rapid scrolling");

		context.setData("rapidScrollFinalProductIds", new ArrayList<>(finalProductIds));

		log.info("Rapid scrolling completed. Initial products: {}, Final products: {}", initialProductIds.size(),
				finalProductIds.size());
	}

	@Then("the application should remain stable")
	public void the_application_should_remain_stable() {

		log.info("Verifying application remains stable after rapid scrolling");

		Assert.assertTrue(context.getPageObjectManager().getMenuLazyLoadPage().isPageDisplayed(),
				"Dynamic Catalog page should remain displayed after rapid scrolling");

		Assert.assertTrue(context.getPageObjectManager().getMenuLazyLoadPage().isCatalogContainerDisplayed(),
				"Dynamic Catalog container should remain displayed after rapid scrolling");

		String currentUrl = context.getPageObjectManager().getMenuLazyLoadPage().getCurrentUrl();

		Assert.assertTrue(currentUrl.contains("dynamic-catalog-lazy-load"),
				"Application should remain on Dynamic Catalog page, but current URL is: " + currentUrl);

		log.info("Application remained stable after rapid scrolling");
	}

	@When("the user interacts with the first product")
	public void the_user_interacts_with_the_first_product() {

		log.info("Interacting with the first loaded product");

		String firstProductId = menuLazyLoadActions.getFirstLoadedProductId();

		Assert.assertNotNull(firstProductId, "First loaded product ID should not be null");

		Assert.assertFalse(firstProductId.trim().isEmpty(), "First loaded product ID should not be empty");

		context.setData("firstProductId", firstProductId);

		menuLazyLoadActions.interactWithFirstProduct();

		log.info("Successfully interacted with first product [{}]", firstProductId);
	}

	@Then("the product interaction should work successfully")
	public void the_product_interaction_should_work_successfully() {

		log.info("Verifying first product interaction was successful");

		String firstProductId = (String) context.getData("firstProductId");

		Assert.assertNotNull(firstProductId, "First product ID was not recorded in ScenarioContext");

		boolean productDisplayed = menuLazyLoadActions.isProductLoadedById(firstProductId);

		Assert.assertTrue(productDisplayed,
				"First product [" + firstProductId + "] should remain loaded after interaction");

		String productName = menuLazyLoadActions.getProductNameById(firstProductId);

		Assert.assertNotNull(productName, "First product name should not be null");

		Assert.assertFalse(productName.trim().isEmpty(), "First product name should not be empty");

		log.info("First product interaction verified successfully. Product: [{}]", productName);
	}

	@When("the user scrolls to the end of the catalog")
	public void the_user_scrolls_to_the_end_of_the_catalog() {

		log.info("Scrolling to the end of the Dynamic Catalog");

		menuLazyLoadActions.loadAllProducts();
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		int loadedProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(loadedProductCount > 0, "At least one product should be loaded at the end of the catalog");

		List<String> productIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(productIds.isEmpty(), "No loaded product IDs were found at the end of the catalog");

		String lastProductId = productIds.get(productIds.size() - 1);

		context.setData("lastProductId", lastProductId);

		log.info("Reached end of catalog. Last loaded product ID: {}", lastProductId);
	}

	@When("the user interacts with the last product")
	public void the_user_interacts_with_the_last_product() {

		log.info("Interacting with the last loaded product");

		String lastProductId = (String) context.getData("lastProductId");

		Assert.assertNotNull(lastProductId, "Last product ID was not recorded in ScenarioContext");

		Assert.assertFalse(lastProductId.trim().isEmpty(), "Last product ID should not be empty");

		menuLazyLoadActions.interactWithProduct(lastProductId);

		context.setData("interactedLastProductId", lastProductId);

		log.info("Successfully interacted with last product [{}]", lastProductId);
	}

	@Given("the dynamic catalog initially contains one loaded product")
	public void the_dynamic_catalog_initially_contains_one_loaded_product() {

		log.info("Verifying Dynamic Catalog has initially loaded products");

		int initialProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(initialProductCount > 0,
				"Dynamic Catalog should initially contain at least one loaded product, but found: "
						+ initialProductCount);

		context.setData("initialProductCount", initialProductCount);

		log.info("Dynamic Catalog initially contains {} loaded product(s)", initialProductCount);
	}

	@When("the user continuously scrolls until the end")
	public void the_user_continuously_scrolls_until_the_end() {

		log.info("User is continuously scrolling through the Dynamic Catalog until the end");

		// Load all dynamically available products
		menuLazyLoadActions.loadAllProducts();

		// Wait until remaining lazy-load placeholders are processed
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		// Capture all loaded product IDs at the end of the catalog
		List<String> loadedProductIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(loadedProductIds.isEmpty(),
				"No products were loaded after continuously scrolling to the end");

		// Store product IDs for duplicate validation
		context.setData("productIdsAfterContinuousScroll", new ArrayList<>(loadedProductIds));

		log.info("Continuous scrolling completed. Total loaded products: {}", loadedProductIds.size());
	}

	@Then("all available products should be loaded")
	public void all_available_products_should_be_loaded() {

		log.info("Verifying that all available products are loaded");

		// Verify that no loading placeholders remain
		menuLazyLoadAssertions.verifyAllProductsLoaded();

		int loadedProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(loadedProductCount > 0,
				"At least one product should be loaded, but found: " + loadedProductCount);

		// Verify every loaded product has required data
		menuLazyLoadAssertions.verifyEveryLoadedProductHasProductName();
		menuLazyLoadAssertions.verifyEveryLoadedProductHasPrice();
		menuLazyLoadAssertions.verifyEveryLoadedProductHasImage();

		log.info("All available products are loaded successfully. Total products: {}", loadedProductCount);
	}

	@Then("no product should be duplicated")
	public void no_product_should_be_duplicated() {

		log.info("Verifying that no duplicate products exist");

		@SuppressWarnings("unchecked")
		List<String> productIds = (List<String>) context.getData("productIdsAfterContinuousScroll");

		Assert.assertNotNull(productIds, "Product IDs were not recorded in ScenarioContext");

		Assert.assertFalse(productIds.isEmpty(), "Product ID list should not be empty");

		// HashSet removes duplicates
		Set<String> uniqueProductIds = new HashSet<>(productIds);

		Assert.assertEquals(uniqueProductIds.size(), productIds.size(),
				"Duplicate products were found in the Dynamic Catalog. " + "Product IDs: " + productIds);

		log.info("Duplicate validation passed. Total products: {}, Unique products: {}", productIds.size(),
				uniqueProductIds.size());
	}

	@Then("the header should be displayed")
	public void the_header_should_be_displayed() {

		log.info("Verifying Dynamic Catalog header is displayed");

		menuLazyLoadAssertions.verifyHeaderDisplayed();

		log.info("Dynamic Catalog header is displayed successfully");
	}

	@When("the user scrolls through the catalog")
	public void the_user_scrolls_through_the_catalog() {

		log.info("User is scrolling through the Dynamic Catalog");

		// Scroll through the complete catalog and trigger lazy loading
		menuLazyLoadActions.loadAllProducts();

		// Wait for any remaining lazy-loaded products
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		log.info("User successfully scrolled through the Dynamic Catalog");
	}

	@Then("each loaded product image should be displayed")
	public void each_loaded_product_image_should_be_displayed() {

		log.info("Verifying each loaded product image is displayed");

		menuLazyLoadAssertions.verifyEveryLoadedProductHasImage();

		log.info("All loaded product images are displayed successfully");
	}

	@When("products are lazy loaded")
	public void products_are_lazy_loaded() {

		log.info("Triggering Dynamic Catalog lazy loading");

		// Scroll through the catalog to trigger lazy loading
		menuLazyLoadActions.loadAllProducts();

		// Wait until lazy loading is completed
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		int loadedProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(loadedProductCount > 0,
				"At least one product should be loaded after lazy loading, but found: " + loadedProductCount);

		log.info("Lazy loading completed successfully. Loaded products: {}", loadedProductCount);
	}

	@Then("every product image should have a valid source")
	public void every_product_image_should_have_a_valid_source() {

		log.info("Verifying every loaded product image has a valid source");

		menuLazyLoadAssertions.verifyEveryLoadedProductHasImage();

		log.info("Every loaded product image has a valid source");
	}

	@When("the user repeatedly scrolls through the catalog")
	public void the_user_repeatedly_scrolls_through_the_catalog() {

		log.info("User is repeatedly scrolling through the Dynamic Catalog");

		// Scroll down and up multiple times while allowing
		// lazy-loaded products to render.
		menuLazyLoadActions.repeatedlyScrollUpAndDown();

		log.info("Repeated scrolling through the Dynamic Catalog completed");
	}

	@Then("the page should remain responsive")
	public void the_page_should_remain_responsive() {

		log.info("Verifying Dynamic Catalog page remains responsive");

		// Verify the page is still displayed
		Assert.assertTrue(context.getPageObjectManager().getMenuLazyLoadPage().isPageDisplayed(),
				"Dynamic Catalog page should remain displayed after repeated scrolling");

		// Verify catalog container is still available
		Assert.assertTrue(context.getPageObjectManager().getMenuLazyLoadPage().isCatalogContainerDisplayed(),
				"Dynamic Catalog container should remain displayed after repeated scrolling");

		// Verify the browser is still on the expected page
		String currentUrl = context.getPageObjectManager().getMenuLazyLoadPage().getCurrentUrl();

		Assert.assertTrue(currentUrl.contains("dynamic-catalog-lazy-load"),
				"Browser should remain on Dynamic Catalog page, but current URL is: " + currentUrl);

		// Verify products can still be accessed after repeated scrolling
		int loadedProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(loadedProductCount > 0, "Loaded products should remain accessible after repeated scrolling");

		log.info("Dynamic Catalog page remained responsive. Loaded products: {}", loadedProductCount);
	}

	@When("the user scrolls continuously to the bottom")
	public void the_user_scrolls_continuously_to_the_bottom() {

		log.info("User is continuously scrolling to the bottom of the Dynamic Catalog");

		// Load all currently available products
		menuLazyLoadActions.loadAllProducts();

		// Wait for any remaining lazy-loaded products
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		int finalProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(finalProductCount > 0, "At least one product should be loaded after scrolling to the bottom");

		List<String> finalProductIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(finalProductIds.isEmpty(), "No loaded product IDs were found after scrolling to the bottom");

		// Store the final state of the catalog
		context.setData("finalProductIdsAtBottom", new ArrayList<>(finalProductIds));

		context.setData("finalProductCountAtBottom", finalProductCount);

		log.info("Reached bottom of Dynamic Catalog. Final product count: {}", finalProductCount);
	}

	@Then("lazy loading should eventually stop")
	public void lazy_loading_should_eventually_stop() {

		log.info("Verifying that lazy loading eventually stops");

		Integer finalProductCount = (Integer) context.getData("finalProductCountAtBottom");

		@SuppressWarnings("unchecked")
		List<String> finalProductIds = (List<String>) context.getData("finalProductIdsAtBottom");

		Assert.assertNotNull(finalProductCount, "Final product count was not recorded in ScenarioContext");

		Assert.assertNotNull(finalProductIds, "Final product IDs were not recorded in ScenarioContext");

		// No loading placeholders should remain once lazy loading has completed
		int placeholderCount = context.getPageObjectManager().getMenuLazyLoadPage().getLoadingPlaceholderCount();

		Assert.assertEquals(placeholderCount, 0,
				"Lazy loading should eventually stop, but " + placeholderCount + " loading placeholder(s) remain");

		// Wait once more to make sure the catalog has reached a stable state
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		int currentProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertEquals(currentProductCount, finalProductCount.intValue(),
				"Product count changed after lazy loading was expected to stop. " + "Expected: " + finalProductCount
						+ ", Actual: " + currentProductCount);

		log.info("Lazy loading stopped successfully. Final product count: {}", currentProductCount);
	}

	@When("the user scrolls to trigger lazy loading")
	public void the_user_scrolls_to_trigger_lazy_loading() {

		log.info("Scrolling Dynamic Catalog to trigger lazy loading");

		int initialProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(initialProductCount > 0,
				"At least one product should be loaded before triggering lazy loading");

		context.setData("productCountBeforeLazyLoad", initialProductCount);

		// Scroll to trigger lazy loading
		menuLazyLoadActions.scrollDown();

		log.info("Lazy loading triggered. Initial loaded product count: {}", initialProductCount);
	}

	@Then("the test should wait until newly loaded products are available")
	public void the_test_should_wait_until_newly_loaded_products_are_available() {

		log.info("Waiting for newly loaded products to become available");

		Integer initialProductCount = (Integer) context.getData("productCountBeforeLazyLoad");

		Assert.assertNotNull(initialProductCount, "Initial product count was not recorded in ScenarioContext");

		// Use the framework's lazy-load wait mechanism
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		int currentProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(currentProductCount >= initialProductCount,
				"Loaded product count should not decrease after lazy loading. " + "Initial: " + initialProductCount
						+ ", Current: " + currentProductCount);

		Assert.assertTrue(currentProductCount > 0, "Newly loaded products should be available after lazy loading");

		log.info("Lazy loading completed. Initial products: {}, Current products: {}", initialProductCount,
				currentProductCount);
	}

	@Then("the test should not rely on unnecessary fixed delays")
	public void the_test_should_not_rely_on_unnecessary_fixed_delays() {

		log.info("Verifying lazy loading completed without requiring unnecessary fixed delays");

		// waitForLazyLoadingToComplete() is the framework-level synchronization
		// used instead of adding arbitrary Thread.sleep() calls here.
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		int loadedProductCount = menuLazyLoadActions.getLoadedProductCount();

		int placeholderCount = context.getPageObjectManager().getMenuLazyLoadPage().getLoadingPlaceholderCount();

		Assert.assertTrue(loadedProductCount > 0, "At least one loaded product should be available");

		Assert.assertEquals(placeholderCount, 0,
				"Lazy loading should be complete and no loading placeholders should remain");

		Assert.assertTrue(context.getPageObjectManager().getMenuLazyLoadPage().isCatalogContainerDisplayed(),
				"Dynamic Catalog should remain displayed after lazy loading");

		log.info("Lazy loading synchronization completed successfully. "
				+ "Loaded products: {}, Remaining placeholders: {}", loadedProductCount, placeholderCount);
	}

	@Then("the page should not continuously create products")
	public void the_page_should_not_continuously_create_products() {

		log.info("Verifying page does not continuously create products");

		@SuppressWarnings("unchecked")
		List<String> finalProductIds = (List<String>) context.getData("finalProductIdsAtBottom");

		Integer finalProductCount = (Integer) context.getData("finalProductCountAtBottom");

		Assert.assertNotNull(finalProductIds, "Final product IDs were not recorded in ScenarioContext");

		Assert.assertNotNull(finalProductCount, "Final product count was not recorded in ScenarioContext");

		// Perform additional downward scrolls after reaching the end.
		for (int iteration = 1; iteration <= 5; iteration++) {

			log.debug("Post-end scroll validation iteration: {}", iteration);

			menuLazyLoadActions.scrollDown();

			WaitUtils.waitForSeconds(1);

			menuLazyLoadActions.waitForLazyLoadingToComplete();
		}

		// Capture the catalog state after additional scrolling
		List<String> productIdsAfterAdditionalScrolling = menuLazyLoadActions.getLoadedProductIds();

		int productCountAfterAdditionalScrolling = menuLazyLoadActions.getLoadedProductCount();

		// Product count must remain unchanged
		Assert.assertEquals(productCountAfterAdditionalScrolling, finalProductCount.intValue(),
				"Page continuously created products after reaching the end. " + "Expected: " + finalProductCount
						+ ", Actual: " + productCountAfterAdditionalScrolling);

		// Product IDs must remain unchanged
		Assert.assertEquals(productIdsAfterAdditionalScrolling, finalProductIds,
				"Product list changed after reaching the end. " + "Products may be continuously recreated or added.");

		// Final duplicate check
		Set<String> uniqueProductIds = new HashSet<>(productIdsAfterAdditionalScrolling);

		Assert.assertEquals(uniqueProductIds.size(), productIdsAfterAdditionalScrolling.size(),
				"Duplicate product cards were found after reaching the end. " + "Product IDs: "
						+ productIdsAfterAdditionalScrolling);

		log.info("Catalog remained stable after additional scrolling. " + "Total products: {}",
				productCountAfterAdditionalScrolling);
	}

	@Then("each loaded product should be displayed as a product card")
	public void each_loaded_product_should_be_displayed_as_a_product_card() {

		log.info("Verifying each loaded product is displayed as a product card");

		menuLazyLoadAssertions.verifyEachLoadedProductDisplayedAsProductCard();

		log.info("All loaded products are displayed as product cards successfully");
	}

	@When("the user scrolls down")
	public void the_user_scrolls_down() {

		log.info("User is scrolling down the Dynamic Catalog");

		menuLazyLoadActions.scrollDown();

		// Allow lazy-load mechanism to start loading additional products
		WaitUtils.waitForSeconds(1);

		log.info("User successfully scrolled down the Dynamic Catalog");
	}

	@Then("the lazy-load mechanism should handle the catalog correctly")
	public void the_lazy_load_mechanism_should_handle_the_catalog_correctly() {

		log.info("Verifying Dynamic Catalog lazy-load mechanism");

		Integer initialProductCount = (Integer) context.getData("initialProductCount");

		Assert.assertNotNull(initialProductCount, "Initial product count was not recorded in ScenarioContext");

		// Wait for dynamically loaded products
		menuLazyLoadActions.waitForLazyLoadingToComplete();

		int currentProductCount = menuLazyLoadActions.getLoadedProductCount();

		Assert.assertTrue(currentProductCount >= initialProductCount,
				"Lazy-load mechanism should not reduce the number of loaded products. " + "Initial count: "
						+ initialProductCount + ", Current count: " + currentProductCount);

		// Verify at least one product is still available
		Assert.assertTrue(currentProductCount > 0,
				"Dynamic Catalog should contain at least one loaded product after scrolling");

		// Verify every loaded product has a valid name
		menuLazyLoadAssertions.verifyEveryLoadedProductHasProductName();

		// Verify every loaded product has a valid price
		menuLazyLoadAssertions.verifyEveryLoadedProductHasPrice();

		// Verify loaded products have images
		menuLazyLoadAssertions.verifyEveryLoadedProductHasImage();

		log.info("Lazy-load mechanism handled the catalog correctly. Initial products: {}, Current products: {}",
				initialProductCount, currentProductCount);
	}

	@Then("loaded products should not be duplicated")
	public void loaded_products_should_not_be_duplicated() {

		log.info("Verifying loaded products are not duplicated");

		List<String> productIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(productIds.isEmpty(), "No loaded products were found");

		Set<String> uniqueProductIds = new HashSet<>(productIds);

		Assert.assertEquals(uniqueProductIds.size(), productIds.size(),
				"Duplicate loaded product IDs were found after rapid scrolling. " + "Product IDs: " + productIds);

		log.info("Duplicate validation passed. Total loaded products: {}, Unique products: {}", productIds.size(),
				uniqueProductIds.size());
	}

	@Then("loaded products should not disappear unexpectedly")
	public void loaded_products_should_not_disappear_unexpectedly() {

		log.info("Verifying loaded products did not disappear unexpectedly");

		@SuppressWarnings("unchecked")
		List<String> initialProductIds = (List<String>) context.getData("rapidScrollInitialProductIds");

		Assert.assertNotNull(initialProductIds, "Initial product IDs were not recorded in ScenarioContext");

		Assert.assertFalse(initialProductIds.isEmpty(), "Initial product ID list should not be empty");

		List<String> finalProductIds = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(finalProductIds.isEmpty(), "No products are currently loaded");

		for (String productId : initialProductIds) {

			Assert.assertTrue(finalProductIds.contains(productId),
					"Previously loaded product [" + productId + "] disappeared unexpectedly after rapid scrolling");
		}

		log.info("All {} initially loaded products are still available", initialProductIds.size());
	}

	@When("the user repeatedly scrolls down")
	public void the_user_repeatedly_scrolls_down() {

		log.info("Repeatedly scrolling down after reaching the end of the catalog");

		for (int iteration = 1; iteration <= 5; iteration++) {

			log.debug("Additional downward scroll iteration: {}", iteration);

			menuLazyLoadActions.scrollDown();
			WaitUtils.waitForSeconds(1);
			menuLazyLoadActions.waitForLazyLoadingToComplete();
		}

		log.info("Completed repeated downward scrolling");
	}

	@Then("no additional duplicate products should appear")
	public void no_additional_duplicate_products_should_appear() {

		log.info("Verifying no additional duplicate products appeared");

		@SuppressWarnings("unchecked")
		List<String> productIdsAtCatalogEnd = (List<String>) context.getData("productIdsAtCatalogEnd");

		Assert.assertNotNull(productIdsAtCatalogEnd,
				"Product IDs at the end of the catalog were not recorded in ScenarioContext");

		Assert.assertFalse(productIdsAtCatalogEnd.isEmpty(),
				"Product ID list at the end of the catalog should not be empty");

		List<String> productIdsAfterScrolling = menuLazyLoadActions.getLoadedProductIds();

		Assert.assertFalse(productIdsAfterScrolling.isEmpty(),
				"No products are currently loaded after repeated scrolling");

		// Verify there are no duplicate IDs in the current catalog.
		Set<String> uniqueProductIds = new HashSet<>(productIdsAfterScrolling);

		Assert.assertEquals(uniqueProductIds.size(), productIdsAfterScrolling.size(),
				"Duplicate product IDs were found after repeatedly scrolling down. " + "Product IDs: "
						+ productIdsAfterScrolling);

		// Verify that no NEW product appeared after reaching the end.
		Assert.assertEquals(productIdsAfterScrolling, productIdsAtCatalogEnd,
				"Additional or duplicate products appeared after reaching the end. " + "Before scrolling: "
						+ productIdsAtCatalogEnd + ", After scrolling: " + productIdsAfterScrolling);

		log.info("Verified catalog remained unchanged after reaching the end. " + "Total products: {}",
				productIdsAfterScrolling.size());
	}

	@When("the user attempts to access a product that is not currently loaded")
	public void the_user_attempts_to_access_a_product_that_is_not_currently_loaded() {

		log.info("Attempting to identify a product that is not currently loaded");

		String unloadedProductId = menuLazyLoadActions.getFirstUnloadedProductId();

		Assert.assertNotNull(unloadedProductId, "No unloaded product placeholder was found");

		Assert.assertFalse(unloadedProductId.trim().isEmpty(), "Unloaded product ID should not be empty");

		context.setData("unloadedProductId", unloadedProductId);

		log.info("Identified currently unloaded product/card: {}", unloadedProductId);
	}

	@Then("the product should not be found in the currently loaded DOM")
	public void the_product_should_not_be_found_in_the_currently_loaded_dom() {

		log.info("Verifying unloaded product is not present as a loaded product in the DOM");

		String unloadedProductId = (String) context.getData("unloadedProductId");

		Assert.assertNotNull(unloadedProductId, "Unloaded product ID was not recorded in ScenarioContext");

		boolean loadedProductPresent = menuLazyLoadActions.isProductLoadedById(unloadedProductId);

		Assert.assertFalse(loadedProductPresent,
				"Product [" + unloadedProductId + "] should not be present as a loaded product in the DOM");

		log.info("Product [{}] is not currently loaded in the DOM", unloadedProductId);
	}

//======================================================================================================	

	@When("the user adds product {string} to the cart")
	public void the_user_adds_product_to_the_cart(String productName) {

		log.info("Adding product [{}] to the cart", productName);

		Assert.assertNotNull(productName, "Product name must not be null");

		Assert.assertFalse(productName.trim().isEmpty(), "Product name must not be empty");

		String loadedTargetProduct = (String) context.getData("targetProductName");

		Assert.assertNotNull(loadedTargetProduct, "Target product was not recorded in ScenarioContext");

		Assert.assertEquals(loadedTargetProduct, productName.trim(), "Target product mismatch");

		menuLazyLoadActions.addTargetProductToCart(productName.trim());

		log.info("Product [{}] added to cart successfully", productName);
	}

	@When("the user adds the target product to the cart")
	public void the_user_adds_the_target_product_to_the_cart() {

		String targetProductName = (String) context.getData("targetProductName");

		Assert.assertNotNull(targetProductName, "Target product name was not recorded in ScenarioContext");

		log.info("Adding target product [{}] to cart", targetProductName);

		menuLazyLoadActions.addTargetProductToCart(targetProductName);
	}

	@Then("the cart count should be {int}")
	public void the_cart_count_should_be(Integer expectedCartCount) {

		log.info("Verifying cart count. Expected: {}", expectedCartCount);

		Assert.assertNotNull(expectedCartCount, "Expected cart count must not be null");

		int actualCartCount = menuLazyLoadActions.getCartBadgeCount();

		log.info("Cart count - Expected: {}, Actual: {}", expectedCartCount, actualCartCount);

		Assert.assertEquals(actualCartCount, expectedCartCount.intValue(), "Incorrect cart count");
	}

}