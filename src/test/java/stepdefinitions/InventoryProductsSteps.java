package stepdefinitions;

import assertions.InventoryAssertions;
import context.ScenarioContext;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryProductsSteps {

	private static final Logger log = LoggerFactory.getLogger(InventoryProductsSteps.class);

	private final InventoryAssertions inventoryAssertions;

	public InventoryProductsSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.inventoryAssertions = new InventoryAssertions(context.getPageObjectManager().getInventoryPage());

		log.debug("InventoryProductsSteps initialized");
	}

	/**
	 * Verify that a specific product is displayed
	 *
	 * Example: Then the product "Sauce Labs Backpack" should be displayed
	 */
	@Then("the product {string} should be displayed")
	public void theProductShouldBeDisplayed(String productName) {

		log.info("Verifying product is displayed: {}", productName);

		inventoryAssertions.verifyProductDisplayed(productName);
	}

	/**
	 * Verify the price of a specific product
	 *
	 * Example: And the price of product "Sauce Labs Backpack" should be "$29.99"
	 */
	@Then("the price of product {string} should be {string}")
	public void thePriceOfProductShouldBe(String productName, String expectedPrice) {

		log.info("Verifying product price. Product: {}, Expected Price: {}", productName, expectedPrice);

		inventoryAssertions.verifyProductPrice(productName, expectedPrice);
	}
}