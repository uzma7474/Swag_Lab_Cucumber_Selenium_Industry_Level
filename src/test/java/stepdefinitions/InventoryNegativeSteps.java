package stepdefinitions;

import assertions.InventoryAssertions;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import actions.InventoryActions;

public class InventoryNegativeSteps {
	private static final Logger log = LoggerFactory.getLogger(InventoryPageSteps.class);

	private final InventoryAssertions inventoryAssertions;
	
	private final InventoryActions inventoryActions;

	private NoSuchElementException productLookupException;

	// Stores the exception raised during the When step
	private IllegalArgumentException illegalArgumentException;

	public InventoryNegativeSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.inventoryAssertions = new InventoryAssertions(context.getPageObjectManager().getInventoryPage());

		this.inventoryActions = new InventoryActions(context.getPageObjectManager());
		log.debug("InventoryPageSteps initialized");
	}

	// =========================================================
	// PRODUCT NOT DISPLAYED
	// =========================================================
	@Then("the product {string} should not be displayed")
	public void the_product_should_not_be_displayed(String productName) {

		inventoryAssertions.verifyProductNotDisplay(productName);

		log.debug("Product is not displayed");
	}

	// =========================================================
	// INVALID PRODUCT VERIFICATION
	// =========================================================
	@When("the user tries to verify product {string}")
	public void the_user_tries_to_verify_product(String productName) {
		log.info("User tries to verify product: '{}'", productName);
		// Reset previous exception
		illegalArgumentException = null;
		try {
			inventoryAssertions.verifyProductDisplayed(productName);

		} catch (IllegalArgumentException e) {
			illegalArgumentException = e;
			log.info("IllegalArgumentException captured: {}", e.getMessage());

		}

	}

	@Then("an illegal argument error should be raised")
	public void an_illegal_argument_error_should_be_raised() {

		log.info("Verifying IllegalArgumentException was raised");

		Assert.assertNotNull(illegalArgumentException, "Expected IllegalArgumentException was not raised");

		log.info("IllegalArgumentException was raised successfully: {}", illegalArgumentException.getMessage());

	}

	@When("the user tries to get the price of a null product") 
	public void the_user_tries_to_get_the_price_of_a_null_product() { 
		log.info("User tries to get the price of a null product"); 
		// Reset previous exceptions 
		illegalArgumentException = null; 
		productLookupException = null; 
		try { 
				// IMPORTANT: This is actual Java null 
				inventoryAssertions.getProductPrice(null); 
				log.error("No exception was raised while retrieving price for null product"); 
				
		} catch (IllegalArgumentException e) { 
			illegalArgumentException = e; 
			log.info( "Expected IllegalArgumentException captured: {}", e.getMessage() ); 
		} catch (NoSuchElementException e) { 
			productLookupException = e; 
			log.info( "NoSuchElementException captured: {}", e.getMessage() ); 
			
		}
		
	}

	@Then("the price of product {string} should not be {string}")
	public void the_price_of_product_should_not_be(String productName, String unexpectedPrice) {

		log.info("Verifying price of product '{}' should not be '{}'", productName, unexpectedPrice);

		inventoryAssertions.verifyProductPriceNot(productName, unexpectedPrice);

		log.debug("Product '{}' price is not '{}'", productName, unexpectedPrice);
	}

//	@When("the user tries to get the price of {string}")
//	public void the_user_tries_to_get_the_price_of(String productName) {
//
//		log.info("User tries to get the price of product: '{}'", productName);
//
//		illegalArgumentException = null;
//
//		try {
//
//			inventoryAssertions.verifyProductPriceNot(productName, "");
//
//		} catch (IllegalArgumentException e) {
//
//			illegalArgumentException = e;
//
//			log.info("Product price lookup failed as expected: {}", e.getMessage());
//		}
//	}
//	

	@When("the user tries to get the price of {string}")
	public void the_user_tries_to_get_the_price_of(String productName) {

		log.info("Trying to get price of product: '{}'", productName);

		productLookupException = null;
		illegalArgumentException = null;

		try {

			inventoryAssertions.getProductPrice(productName);

		} catch (NoSuchElementException e) {

			productLookupException = e;
			log.info("Expected product lookup failure: {}", e.getMessage());

		} catch (IllegalArgumentException e) {

			illegalArgumentException = e;

			log.info("Product price lookup failed as expected: {}", e.getMessage());
		}

	}

	@Then("the product lookup should fail")
	public void the_product_lookup_should_fail() {

		log.info("Verifying product lookup failure");

		boolean lookupFailed = productLookupException != null || illegalArgumentException != null;

		Assert.assertTrue(lookupFailed,
				"Expected product lookup to fail with either " + "NoSuchElementException or IllegalArgumentException");

		if (productLookupException != null) {

			log.info("Product lookup failed with NoSuchElementException: {}", productLookupException.getMessage());

		} else if (illegalArgumentException != null) {

			log.info("Product lookup failed with IllegalArgumentException: {}", illegalArgumentException.getMessage());
		}

		log.info("Product lookup failed as expected");
	}
	
	
	
	@When("the user tries to add {string} to the cart") 
	public void the_user_tries_to_add_to_the_cart(String productName) { 
		
		log.info("User tries to add product '{}' to the cart", productName); 
		try { 
				inventoryActions.addProductToCart(productName); 								
				log.info( "Product '{}' was added to the cart successfully", productName ); 
				
		} catch (IllegalArgumentException e) { 
			
			log.info( "IllegalArgumentException while adding product '{}': {}", productName, e.getMessage() ); 
			illegalArgumentException = e; 
			
		} catch (NoSuchElementException e) { 
			
			log.info( "NoSuchElementException while adding product '{}': {}", productName, e.getMessage() ); 
			productLookupException = e; 
			
		} 
		
	}
	
	@When("the user tries to add a null product to the cart") 
	public void the_user_tries_to_add_a_null_product_to_the_cart() { 
		log.info("User tries to add a null product to the cart"); 
		// Reset previously captured exceptions 
		illegalArgumentException = null; 
		productLookupException = null; 
		try { 
			// Pass actual Java null, not the String "null" 
			inventoryActions.addProductToCart(null); 
			log.error( "No exception was raised while trying to add a null product to the cart" ); 
			
		} catch (IllegalArgumentException e) { 
			illegalArgumentException = e; 
			log.info( "Expected IllegalArgumentException captured: {}", e.getMessage() ); 
			
		} catch (NoSuchElementException e) { 
			productLookupException = e; 
			log.info( "NoSuchElementException captured: {}", e.getMessage() ); 
			
		} 
	}
	
	@When("the user tries to remove {string} from the cart") 
	public void the_user_tries_to_remove_from_the_cart(String productName) { 
		log.info("User tries to remove product '{}' from the cart", productName); 
		// Reset previously captured exceptions 
		productLookupException = null; 
		illegalArgumentException = null; 
		try { 
			
			inventoryActions.removeProductFromCart(productName); 
			log.error( "No exception was raised while trying to remove product '{}' from the cart", productName ); 
			
		} catch (NoSuchElementException e) { 
			productLookupException = e; 
			log.info( "Expected NoSuchElementException captured while removing '{}': {}", productName, e.getMessage() ); 
			
		} catch (IllegalArgumentException e) { 
			illegalArgumentException = e; 
			log.info( "Expected IllegalArgumentException captured while removing '{}': {}", productName, e.getMessage() ); 
			
		} 
		
	} 
	
	
	@Then("the product removal lookup should fail") public void the_product_removal_lookup_should_fail() { 
		log.info("Verifying product removal lookup failure"); 
		boolean removalLookupFailed = productLookupException != null || illegalArgumentException != null; 
		Assert.assertTrue( removalLookupFailed, "Expected product removal lookup to fail with either " + "NoSuchElementException or IllegalArgumentException" ); 
		if (productLookupException != null) { 
			log.info( "Product removal lookup failed with NoSuchElementException: {}", productLookupException.getMessage() ); 
			
		} else { 
			log.info( "Product removal lookup failed with IllegalArgumentException: {}", illegalArgumentException.getMessage() ); 
		} 
		log.info("Product removal lookup failed as expected"); 
		
	}
	
	@When("the user tries to remove a null product from the cart") 
	public void the_user_tries_to_remove_a_null_product_from_the_cart() { 
		log.info("User tries to remove a null product from the cart"); 
		// Reset previously captured exceptions 
		productLookupException = null; 
		illegalArgumentException = null; 
		try { 
			// Pass actual Java null, not the String "null" 
			inventoryActions.removeProductFromCart(null); 
			log.error( "No exception was raised while trying to remove a null product from the cart" ); 
			
		} catch (NoSuchElementException e) { 
			productLookupException = e; 
			log.info( "NoSuchElementException captured while removing null product: {}", e.getMessage() ); 
			
		} catch (IllegalArgumentException e) { 
			illegalArgumentException = e; 
			log.info( "IllegalArgumentException captured while removing null product: {}", e.getMessage() ); 
			
		} 
		
	}
		

	
	@When("the user tries to sort products by {string}") 
	public void the_user_tries_to_sort_products_by(String sortOption) { 
		log.info("User tries to sort products by '{}'", sortOption); 
		// Reset previous exceptions 
		illegalArgumentException = null; 
		productLookupException = null; 
		
		try { 
			inventoryActions.sortProductsBy(sortOption); 
			log.error( "No exception was raised for invalid sort option '{}'", sortOption ); 
			
		} catch (NoSuchElementException e) { 
			productLookupException = e; 
			log.info( "Expected NoSuchElementException captured for sort option '{}': {}", sortOption, e.getMessage() ); 
			
		} catch (IllegalArgumentException e) { 
			illegalArgumentException = e; 
			log.info( "IllegalArgumentException captured for sort option '{}': {}", sortOption, e.getMessage() ); 
			
		} 
		
	}
	
	@When("the user tries to sort products using a null option") 
	public void the_user_tries_to_sort_products_using_a_null_option() { 
		log.info("User tries to sort products using a null option"); 
		// Reset previous exception 
		illegalArgumentException = null; 
		try { 
			// Pass actual Java null, not the String "null" 
			inventoryActions.sortProductsBy(null); 
			log.error( "No exception was raised while sorting products with a null option" ); 
			
		} catch (IllegalArgumentException e) { 
			illegalArgumentException = e; 
			log.info( "Expected IllegalArgumentException captured for null sort option: {}", e.getMessage() ); 
			
		} 
		
	}
	
	@Then("the sort operation should fail") 
	public void the_sort_operation_should_fail() { 
		log.info("Verifying that the sort operation failed"); 
		boolean sortOperationFailed = productLookupException != null || illegalArgumentException != null; 
		
		Assert.assertTrue( sortOperationFailed, "Expected sort operation to fail with either " + "NoSuchElementException or IllegalArgumentException" ); 
		
		if (productLookupException != null) { 
			log.info( "Sort operation failed with NoSuchElementException: {}", productLookupException.getMessage() ); 
			
		} else { 
			log.info( "Sort operation failed with IllegalArgumentException: {}", illegalArgumentException.getMessage() ); 
			
		} 
		
		log.info("Sort operation failure verified successfully"); 
		
	}
	
	@Then("the inventory page should not display {int} products") 
	public void the_inventory_page_should_not_display_products(Integer expectedCount) { 
		log.info( "Verifying inventory page should not display {} products", expectedCount ); 
		Assert.assertNotNull( expectedCount, "Expected product count must not be null" ); 
		inventoryAssertions.verifyProductCount(expectedCount); 
		 
	}
	
	@Then("the inventory product count should be greater than {int}")
	public void theInventoryProductCountShouldBeGreaterThan(Integer expectedCount) {

		log.info("Verifying that inventory product count greater than {}", expectedCount); 
		inventoryAssertions.verifyProductCountOnPageGreaterThan(expectedCount);
		
		log.debug("Verified that actual product count is greater than {}", expectedCount);

	    
	}
	
	@Then("the cart badge count should be {int}")
	public void theCartBadgeCountShouldBe(Integer expectedCount) {

		log.info("Verifying that the cart badge should be {}", expectedCount); 
	    inventoryAssertions.verifyCartBadgeCount(expectedCount);
	    log.debug("Verifying that the cart badge should be {}", expectedCount);

	   
	}
	
	
	
	
	
	//	@When("the user tries to sort products by {string}") 
//	public void the_user_tries_to_sort_products_by(String sortOption) { 
//		log.info("User tries to sort products by '{}'", sortOption); 
//		illegalArgumentException = null; 
//		try { 
//			inventoryActions.sortProductsBy(sortOption); 
//			log.info( "Products sorted successfully using option '{}'", sortOption ); 
//			
//		} catch (IllegalArgumentException e) { 
//			illegalArgumentException = e; 
//			log.info( "IllegalArgumentException captured for sort option '{}': {}", sortOption, e.getMessage() ); 
//			
//		} 
//		
//	}
	
	
	
	
	
//	@When("the user tries to get the price of a null product") 
//	public void the_user_tries_to_get_the_price_of_a_null_product() { 
//		
//		log.info("User tries to get the price of a null product"); 
//		productLookupException = null; 
//		illegalArgumentException = null; 
//		try {
//
//		} catch (NoSuchElementException e) { 
//			productLookupException = e; 
//			log.info( "NoSuchElementException captured while looking up null product: {}", e.getMessage() ); 
//		} catch (IllegalArgumentException e) { 
//			illegalArgumentException = e; log.info( "IllegalArgumentException captured while looking up null product: {}", e.getMessage() ); 
//			
//		} 
//		
//	}

//	@Then("the product lookup should fail")
//	public void the_product_lookup_should_fail() {
//
//		log.info("Verifying that product lookup failed");
//
//		Assert.assertNotNull(illegalArgumentException,
//				"Expected product price lookup to fail, but no IllegalArgumentException was raised");
//
//		log.info("Product lookup failed successfully: {}", illegalArgumentException.getMessage());
//	}

}