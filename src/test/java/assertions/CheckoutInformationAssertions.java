package assertions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import pages.CheckoutInformationPage;

public class CheckoutInformationAssertions {

	private static final Logger log = LoggerFactory.getLogger(CheckoutInformationAssertions.class);

	private final CheckoutInformationPage checkoutInformationPage;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public CheckoutInformationAssertions() {

		this.checkoutInformationPage = new CheckoutInformationPage();

		log.debug("CheckoutInformationAssertions initialized");
	}

	public CheckoutInformationAssertions(CheckoutInformationPage checkoutInformationPage) {

		if (checkoutInformationPage == null) {

			throw new IllegalArgumentException("CheckoutInformationPage must not be null");
		}

		this.checkoutInformationPage = checkoutInformationPage;

		log.debug("CheckoutInformationAssertions initialized with page object");
	}

	// =========================================================
	// PAGE ASSERTIONS
	// =========================================================

	/**
	 * Verifies Checkout Information page is displayed.
	 */
	public void verifyCheckoutInformationPageDisplayed() {

		log.info("Verifying Checkout Information page is displayed");

		Assert.assertTrue(checkoutInformationPage.isPageDisplayed(), "Checkout Information page should be displayed");
	}

	/**
	 * Verifies Checkout Information form is displayed.
	 */
	public void verifyCheckoutInformationFormDisplayed() {

		log.info("Verifying Checkout Information form is displayed");

		Assert.assertTrue(checkoutInformationPage.isCheckoutInfoFormDisplayed(),
				"Checkout Information form should be displayed");
	}

	// =========================================================
	// FIELD ASSERTIONS
	// =========================================================

	/**
	 * Verifies First Name field is displayed.
	 */
	public void verifyFirstNameFieldDisplayed() {

		log.info("Verifying First Name field is displayed");

		Assert.assertTrue(checkoutInformationPage.isFirstNameDisplayed(), "First Name field should be displayed");
	}

	/**
	 * Verifies Last Name field is displayed.
	 */
	public void verifyLastNameFieldDisplayed() {

		log.info("Verifying Last Name field is displayed");

		Assert.assertTrue(checkoutInformationPage.isLastNameDisplayed(), "Last Name field should be displayed");
	}

	/**
	 * Verifies Postal Code field is displayed.
	 */
	public void verifyPostalCodeFieldDisplayed() {

		log.info("Verifying Postal Code field is displayed");

		Assert.assertTrue(checkoutInformationPage.isPostalCodeDisplayed(), "Postal Code field should be displayed");
	}

	// =========================================================
	// BUTTON ASSERTIONS
	// =========================================================

	/**
	 * Verifies Continue button is displayed.
	 */
	public void verifyContinueButtonDisplayed() {

		log.info("Verifying Continue button is displayed");

		Assert.assertTrue(checkoutInformationPage.isContinueButtonDisplayed(), "Continue button should be displayed");
	}

	/**
	 * Verifies Cancel button is displayed.
	 */
	public void verifyCancelButtonDisplayed() {

		log.info("Verifying Cancel button is displayed");

		Assert.assertTrue(checkoutInformationPage.isCancelButtonDisplayed(), "Cancel button should be displayed");
	}

	/**
	 * Verifies Continue button is enabled.
	 */
	public void verifyContinueButtonEnabled() {

		log.info("Verifying Continue button is enabled");

		Assert.assertTrue(checkoutInformationPage.isContinueButtonEnabled(), "Continue button should be enabled");
	}

	/**
	 * Verifies Cancel button is enabled.
	 */
	public void verifyCancelButtonEnabled() {

		log.info("Verifying Cancel button is enabled");

		Assert.assertTrue(checkoutInformationPage.isCancelButtonEnabled(), "Cancel button should be enabled");
	}

	// =========================================================
	// PAGE TITLE ASSERTIONS
	// =========================================================

	/**
	 * Verifies Checkout Information page title.
	 */
	public void verifyPageTitle(String expectedTitle) {

		log.info("Verifying Checkout Information page title");

		Assert.assertEquals(checkoutInformationPage.getPageTitle(), expectedTitle,
				"Checkout Information page title is incorrect");
	}

	// =========================================================
	// ERROR ASSERTIONS
	// =========================================================

	/**
	 * Verifies checkout error message is displayed.
	 */
	public void verifyErrorMessageDisplayed() {

		log.info("Verifying checkout error message is displayed");

		Assert.assertTrue(checkoutInformationPage.isErrorMessageDisplayed(),
				"Checkout error message should be displayed");
	}

	/**
	 * Verifies checkout error message.
	 */
	public void verifyErrorMessage(String expectedMessage) {

		log.info("Verifying checkout error message");

		Assert.assertEquals(checkoutInformationPage.getErrorMessage(), expectedMessage,
				"Checkout error message is incorrect");
	}

	public void verifyCheckoutPageIsDisplayed() {

		log.info("Verifying Checkout Information page is displayed");

		Assert.assertTrue(checkoutInformationPage.isPageDisplayed(), "Checkout Information page should be displayed");

		log.info("Checkout Information page is displayed");
	}

}