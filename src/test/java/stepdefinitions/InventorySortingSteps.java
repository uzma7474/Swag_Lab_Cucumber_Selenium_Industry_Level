package stepdefinitions;

import actions.InventoryActions;
import assertions.InventoryAssertions;
import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventorySortingSteps {

	private static final Logger log = LoggerFactory.getLogger(InventorySortingSteps.class);

	private final InventoryActions inventoryActions;
	private final InventoryAssertions inventoryAssertions;

	public InventorySortingSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.inventoryActions = new InventoryActions(context.getPageObjectManager());

		this.inventoryAssertions = new InventoryAssertions(context.getPageObjectManager().getInventoryPage());

		log.debug("InventorySortingSteps initialized");
	}

	// ============================================================
	// WHEN STEPS
	// ============================================================

	@When("the user sorts products by {string}")
	public void theUserSortsProductsBy(String sortOption) {

		log.info("Sorting products by option: {}", sortOption);

		inventoryActions.selectSortOption(sortOption);

		log.info("Products sorted successfully by: {}", sortOption);
	}

	// ============================================================
	// THEN STEPS
	// ============================================================

	@Then("the selected sort option should be {string}")
	public void theSelectedSortOptionShouldBe(String expectedSortOption) {

		log.info("Verifying selected sort option. Expected: {}", expectedSortOption);

		inventoryAssertions.verifySelectedSortOption(expectedSortOption);

		log.info("Selected sort option verified successfully: {}", expectedSortOption);
	}

	@When("the user adds {string} and {string} and opens the cart")
	public void the_user_adds_and_and_opens_the_cart(String product1, String product2) {

		// Add first product
		inventoryActions.addProductToCart(product1);

		// Add second product
		inventoryActions.addProductToCart(product2);

		// Open shopping cart
		inventoryActions.openShoppingCart();
	}
	
	@When("the user refreshes the inventory page")
	public void the_user_refreshes_the_inventory_page() {
		log.info("Inventory page are refresh");
		inventoryActions.refreshInventoryPage();
	}

}
