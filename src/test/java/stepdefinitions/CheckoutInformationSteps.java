package stepdefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import actions.CartAction;
import actions.CheckoutInformationAction;
import actions.InventoryActions;
import assertions.CartAssertions;
import assertions.CheckoutInformationAssertions;
import assertions.InventoryAssertions;
import context.ScenarioContext;
import io.cucumber.java.en.Then;

public class CheckoutInformationSteps {
	private static final Logger log = LoggerFactory.getLogger(CheckoutInformationSteps.class);

	private final CartAction cartAction;
	private final CartAssertions cartAssertions;
	
	private final CheckoutInformationAction checkoutInformationAction;
	private final CheckoutInformationAssertions checkoutInformationAssertions;

	/**
	 * Constructor injection using ScenarioContext.
	 *
	 * @param context ScenarioContext containing PageObjectManager
	 */
	public CheckoutInformationSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.cartAction = new CartAction(context.getPageObjectManager().getCartPage());
		this.cartAssertions = new CartAssertions(context.getPageObjectManager().getCartPage());
		
		this.checkoutInformationAction = new CheckoutInformationAction(context.getPageObjectManager().getCheckoutInformationPage());
		this.checkoutInformationAssertions = new CheckoutInformationAssertions(context.getPageObjectManager().getCheckoutInformationPage());
		log.debug("InventoryCartSteps initialized");
	}
	
	@Then("the checkout page should be displayed")
	public void the_checkout_page_should_be_displayed() {

		log.info("Verifying that the Checkout Information page is displayed");

		checkoutInformationAssertions.verifyCheckoutPageIsDisplayed();

		log.info("Checkout Information page is displayed successfully");
	}

}
