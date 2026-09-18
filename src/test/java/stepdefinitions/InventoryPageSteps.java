package stepdefinitions;

import assertions.InventoryAssertions;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryPageSteps {

	private static final Logger log = LoggerFactory.getLogger(InventoryPageSteps.class);

	private final InventoryAssertions inventoryAssertions;

	public InventoryPageSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.inventoryAssertions = new InventoryAssertions(context.getPageObjectManager().getInventoryPage());

		log.debug("InventoryPageSteps initialized");
	}

	// =========================================================
	// INV001
	// =========================================================
	@Given("the inventory page is displayed")
	@Then("the Inventory page is displayed successfully")
	public void theInventoryPageShouldBeDisplayedSuccessfully() {

		log.info("Verifying inventory page is displayed");

		inventoryAssertions.verifyInventoryPageDisplayed();
	}

	// =========================================================
	// INV002
	// =========================================================

	@Then("the inventory page title should be {string}")
	public void theInventoryPageTitleShouldBe(String expectedTitle) {

		log.info("Verifying inventory page title. Expected: {}", expectedTitle);

		inventoryAssertions.verifyPageTitle(expectedTitle);
	}

	// =========================================================
	// INV003
	// =========================================================

	@Then("the inventory product list should be displayed")
	public void theInventoryProductListShouldBeDisplayed() {

		log.info("Verifying inventory product list is displayed");

		inventoryAssertions.verifyInventoryListDisplayed();
	}

	
	@Then("the Inventory page should be displayed")
	public void theInventoryPageShouldBeDisplayed() {

	    log.info("Step: Verify Inventory page is displayed");

	    inventoryAssertions.verifyInventoryPageDisplayed();
	}
	
	// =========================================================
	// INV004
	// =========================================================

	@Then("products should be displayed on the inventory page")
	public void productsShouldBeDisplayedOnTheInventoryPage() {

		log.info("Verifying products are displayed");

		inventoryAssertions.verifyProductsDisplayed();
	}

	// =========================================================
	// INV005
	// =========================================================

	@Then("the inventory page should display {int} products")
	public void theInventoryPageShouldDisplayProducts(int expectedCount) {

		log.info("Verifying inventory product count. Expected: {}", expectedCount);

		inventoryAssertions.verifyProductCount(expectedCount);
	}
	
	
	@Then("the cart badge should be displayed")
	public void the_cart_badge_should_be_displayed() {
		
		log.info("Verifying Cart Badge displayed");

	    inventoryAssertions.verifyCartBadgeDisplayed();

	    
	}
	


	
	
	
	
	
}