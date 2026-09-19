package stepdefinitions;

import assertions.InventoryAssertions;
import context.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import page_object_manager.PageObjectManager;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import actions.InventoryActions;

public class InventoryProductsSteps {

	private static final Logger log = LoggerFactory.getLogger(InventoryProductsSteps.class);

	private final InventoryAssertions inventoryAssertions;

	private final InventoryActions inventoryActions;

	public ScenarioContext context;

	public InventoryProductsSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.context = context;
		this.inventoryActions = new InventoryActions(context.getPageObjectManager());

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

	@Given("the user has added the following products to the cart:")
	public void the_user_has_added_the_following_products_to_the_cart(DataTable dataTable) {

		List<String> productNames = dataTable.asMaps(String.class, String.class).stream().map(row -> row.get("product"))
				.collect(Collectors.toList());

		log.info("Products to be added to cart: {}", productNames);

		// Store expected products in ScenarioContext
		context.setCartProductNames(productNames);

		inventoryActions.addProductsToCart(productNames);
	}



	@Given("the user has added {int} products to the cart")
	public void the_user_has_added_products_to_the_cart(Integer productCount) {

		log.info("Adding {} products to cart", productCount);

		PageObjectManager pageObjectManager = context.getPageObjectManager();

		String currentUrl = pageObjectManager.getInventoryPage().getCurrentUrl();

		log.info("Current URL before adding products: {}", currentUrl);

		Assert.assertTrue(currentUrl.contains("/inventory.html"),
				"User must be on Inventory page before adding products. " + "Actual URL: " + currentUrl);

		inventoryActions.addFirstNProductsToCart(productCount);
	}
	
	
//	@Given("the user has added {int} products to the cart")
//	public void the_user_has_added_products_to_the_cart(Integer productCount) {
//
//		log.info("Adding {} products to the cart", productCount);
//
//		Assert.assertNotNull(productCount, "Product count must not be null");
//
//		Assert.assertTrue(productCount > 0, "Product count must be greater than 0");
//
//		inventoryActions.addFirstNProductsToCart(productCount);
//
//		log.info("Successfully added {} products to the cart", productCount);
//	}
	
	

}