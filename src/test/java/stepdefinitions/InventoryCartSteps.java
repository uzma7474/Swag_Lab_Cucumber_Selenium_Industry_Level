
package stepdefinitions;

import actions.InventoryActions;
import assertions.InventoryAssertions;
import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryCartSteps {

	private static final Logger log = LoggerFactory.getLogger(InventoryCartSteps.class);

	private final InventoryActions inventoryActions;
	private final InventoryAssertions inventoryAssertions;

	/**
	 * Constructor injection using ScenarioContext.
	 *
	 * @param context ScenarioContext containing PageObjectManager
	 */
	public InventoryCartSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.inventoryActions = new InventoryActions(context.getPageObjectManager());

		this.inventoryAssertions = new InventoryAssertions(context.getPageObjectManager().getInventoryPage());

		log.debug("InventoryCartSteps initialized");
	}

	// ============================================================
	// ADD PRODUCT TO CART
	// ============================================================

	@When("the user adds {string} to the cart")
	public void theUserAddsToTheCart(String productName) {

		log.info("Adding product to cart: {}", productName);

		inventoryActions.addProductToCart(productName);

		log.info("Product added successfully: {}", productName);
	}

	// ============================================================
	// REMOVE PRODUCT FROM CART
	// ============================================================

	@When("the user removes {string} from the cart")
	public void theUserRemovesFromTheCart(String productName) {

		log.info("Removing product from cart: {}", productName);

		inventoryActions.removeProductFromCart(productName);

		log.info("Product removed successfully: {}", productName);
	}

	// ============================================================
	// OPEN SHOPPING CART
	// ============================================================

	@When("the user opens the shopping cart")
	public void theUserOpensTheShoppingCart() {

		log.info("Opening shopping cart");

		inventoryActions.openShoppingCart();

		log.info("Shopping cart opened successfully");
	}

	// ============================================================
	// VERIFY CART BADGE COUNT
	// ============================================================

	@Then("the cart badge should show {int}")
	public void theCartBadgeShouldShow(int expectedCount) {

		log.info("Verifying cart badge count. Expected: {}", expectedCount);

		inventoryAssertions.verifyCartBadgeCount(expectedCount);

		log.info("Cart badge count verified successfully. Expected: {}", expectedCount);
	}

	// ============================================================
	// VERIFY CART BADGE NOT DISPLAYED
	// ============================================================

	@Then("the cart badge should not be displayed")
	public void theCartBadgeShouldNotBeDisplayed() {

		log.info("Verifying cart badge is not displayed");

		inventoryAssertions.verifyCartBadgeNotDisplayed();

		log.info("Cart badge is not displayed as expected");
	}

	// ============================================================
	// VERIFY SHOPPING CART PAGE
	// ============================================================

	@Then("the shopping cart should be displayed")
	@Then("the shopping cart page should be displayed")
	public void theShoppingCartPageShouldBeDisplayed() {

		log.info("Verifying shopping cart page is displayed");

		inventoryAssertions.verifyShoppingCartPageDisplayed();

		log.info("Shopping cart page is displayed successfully");
	}

	
	@When("the user adds the first product to the cart")
	public void theUserAddsTheFirstProductToTheCart() {
		
		log.info("Adding the first product to the cart");
		
		inventoryActions.addFirstProductToCart();
		
		log.info("First product added to cart successfully");
	}

}
