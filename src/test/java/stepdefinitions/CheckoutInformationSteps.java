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
		this.cartAssertions = new CartAssertions(context.getPageObjectManager().getCartPage(), context);
		
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
	
	
	@Then("the First Name field should not be displayed")
	public void the_first_name_field_should_not_be_displayed() {

	    log.info("Verifying First Name field is not displayed");

	    checkoutInformationAssertions.verifyFirstNameFieldNotDisplayed();

	    log.info("First Name field is not displayed");
	}

	@Then("the Last Name field should not be displayed")
	public void the_last_name_field_should_not_be_displayed() {

	    log.info("Verifying Last Name field is not displayed");

	    checkoutInformationAssertions.verifyLastNameFieldNotDisplayed();

	    log.info("Last Name field is not displayed");
	}

	@Then("the Postal Code field should not be displayed")
	public void the_postal_code_field_should_not_be_displayed() {

	    log.info("Verifying Postal Code field is not displayed");

	    checkoutInformationAssertions.verifyPostalCodeFieldNotDisplayed();

	    log.info("Postal Code field is not displayed");
	}
	
	
	@Then("the Cancel button should not be displayed")
	public void the_cancel_button_should_not_be_displayed() {

	    log.info("Verifying Cancel button is not displayed");

	    checkoutInformationAssertions.verifyCancelButtonNotDisplayed();

	    log.info("Cancel button is not displayed");
	}
	
	
	

}
