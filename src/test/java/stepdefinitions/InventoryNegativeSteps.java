//package stepdefinitions;
//
//import actions.InventoryActions;
//import assertions.InventoryAssertions;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.Assert;
//
//public class InventoryNegativeSteps {
//
//	private static final Logger log = LoggerFactory.getLogger(InventoryNegativeSteps.class);
//
//	private final InventoryActions inventoryActions;
//	private final InventoryAssertions inventoryAssertions;
//
//	private Exception capturedException;
//
//	public InventoryNegativeSteps() {
//
//		inventoryActions = new InventoryActions();
//		inventoryAssertions = new InventoryAssertions();
//
//		log.debug("InventoryNegativeSteps initialized");
//	}
//
//	// ============================================================
//	// PRODUCT VALIDATION
//	// ============================================================
//
//	@When("the user tries to verify product {string}")
//	public void theUserTriesToVerifyProduct(String productName) {
//
//		log.info("Trying to verify product: {}", productName);
//
//		try {
//
//			new pages.InventoryPage().isProductDisplayed(productName);
//
//		} catch (Exception e) {
//
//			capturedException = e;
//
//			log.debug("Expected exception captured: {}", e.getClass().getSimpleName());
//		}
//	}
//
//	@When("the user tries to verify a null product")
//	public void theUserTriesToVerifyANullProduct() {
//
//		log.info("Trying to verify null product");
//
//		try {
//
//			new pages.InventoryPage().isProductDisplayed(null);
//
//		} catch (Exception e) {
//
//			capturedException = e;
//
//			log.debug("Expected exception captured: {}", e.getClass().getSimpleName());
//		}
//	}
//
//	// ============================================================
//	// PRICE
//	// ============================================================
//
//	@When("the user tries to get the price of {string}")
//	public void theUserTriesToGetThePriceOf(String productName) {
//
//		log.info("Trying to get price of: {}", productName);
//
//		try {
//
//			new pages.InventoryPage().getProductPrice(productName);
//
//		} catch (Exception e) {
//
//			capturedException = e;
//
//			log.debug("Expected exception captured: {}", e.getClass().getSimpleName());
//		}
//	}
//
//	@When("the user tries to get the price of a null product")
//	public void theUserTriesToGetThePriceOfANullProduct() {
//
//		log.info("Trying to get price of null product");
//
//		try {
//
//			new pages.InventoryPage().getProductPrice(null);
//
//		} catch (Exception e) {
//
//			capturedException = e;
//
//			log.debug("Expected exception captured: {}", e.getClass().getSimpleName());
//		}
//	}
//
//	// ============================================================
//	// ADD PRODUCT
//	// ============================================================
//
//	@When("the user tries to add {string} to the cart")
//	public void theUserTriesToAddToTheCart(String productName) {
//
//		log.info("Trying to add product: {}", productName);
//
//		try {
//
//			inventoryActions.addProductToCart(productName);
//
//		} catch (Exception e) {
//
//			capturedException = e;
//
//			log.debug("Expected exception captured: {}", e.getClass().getSimpleName());
//		}
//	}
//
//	@When("the user tries to add a null product to the cart")
//	public void theUserTriesToAddANullProductToTheCart() {
//
//		log.info("Trying to add null product");
//
//		try {
//
//			inventoryActions.addProductToCart(null);
//
//		} catch (Exception e) {
//
//			capturedException = e;
//
//			log.debug("Expected exception captured: {}", e.getClass().getSimpleName());
//		}
//	}
//
//	// ============================================================
//	// REMOVE PRODUCT
//	// ============================================================
//
//	@When("the user tries to remove {string} from the cart")
//	public void theUserTriesToRemoveFromTheCart(String productName) {
//
//		log.info("Trying to remove product: {}", productName);
//
//		try {
//
//			inventoryActions.removeProductFromCart(productName);
//
//		} catch (Exception e) {
//
//			capturedException = e;
//
//			log.debug("Expected exception captured: {}", e.getClass().getSimpleName());
//		}
//	}
//
//	@When("the user tries to remove a null product from the cart")
//	public void theUserTriesToRemoveANullProductFromTheCart() {
//
//		log.info("Trying to remove null product");
//
//		try {
//
//			inventoryActions.removeProductFromCart(null);
//
//		} catch (Exception e) {
//
//			capturedException = e;
//
//			log.debug("Expected exception captured: {}", e.getClass().getSimpleName());
//		}
//	}
//
//	// ============================================================
//	// SORTING
//	// ============================================================
//
//	@When("the user tries to sort products using a null option in the filter")
//	public void theUserTriesToSortProductsUsingANullOption_() {
//
//		log.info("Trying to sort using null option");
//
//		try {
//
//			inventoryActions.sortProductsBy(null);
//
//		} catch (Exception e) {
//
//			capturedException = e;
//
//			log.debug("Expected exception captured: {}", e.getClass().getSimpleName());
//		}
//	}
//
//	// ============================================================
//	// EXCEPTION ASSERTION
//	// ============================================================
//
//	@Then("an illegal argument error should be raised")
//	public void anIllegalArgumentErrorShouldBeRaised() {
//
//		log.info("Verifying IllegalArgumentException was raised");
//
//		Assert.assertNotNull(capturedException, "Expected IllegalArgumentException but no exception was captured");
//
//		Assert.assertTrue(capturedException instanceof IllegalArgumentException,
//				"Expected IllegalArgumentException but got: " + capturedException.getClass().getSimpleName());
//	}
//
//	@Then("the product lookup should fail")
//	public void theProductLookupShouldFail() {
//
//		log.info("Verifying product lookup failed");
//
//		Assert.assertNotNull(capturedException, "Expected product lookup to fail");
//	}
//
//	@Then("the product removal lookup should fail")
//	public void theProductRemovalLookupShouldFail() {
//
//		log.info("Verifying product removal lookup failed");
//
//		Assert.assertNotNull(capturedException, "Expected product removal lookup to fail");
//	}
//
//	@Then("the sort operation should fail")
//	public void theSortOperationShouldFail() {
//
//		log.info("Verifying sort operation failed");
//
//		Assert.assertNotNull(capturedException, "Expected sort operation to fail");
//	}
//}