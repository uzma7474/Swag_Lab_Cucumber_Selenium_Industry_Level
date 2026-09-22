package assertions;

import java.util.List;

import org.testng.Assert;

import actions.Checkout_Step_Two_Action;
import context.ScenarioContext;
import io.cucumber.java.en.Then;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.CartPage;
import pages.Checkout_Step_Two_Page;
import utils.WaitUtils;

public class Checkout_Step_Two_Assertions {

	private static final Logger log = LoggerFactory.getLogger(Checkout_Step_Two_Assertions.class);

	private final Checkout_Step_Two_Page checkoutStepTwoPage;

	private Checkout_Step_Two_Action checkoutStepTwoAction;

	private final ScenarioContext scenarioContext;

	private final CartPage cartPage;

	private String cartSubtotal;

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	public Checkout_Step_Two_Assertions(Checkout_Step_Two_Page checkoutStepTwoPage, ScenarioContext scenarioContext) {

		if (checkoutStepTwoPage == null) {
			throw new IllegalArgumentException("Checkout_Step_Two_Page must not be null");
		}

		if (scenarioContext == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.checkoutStepTwoPage = checkoutStepTwoPage;
		this.scenarioContext = scenarioContext;
		this.cartPage = scenarioContext.getPageObjectManager().getCartPage();
		this.checkoutStepTwoAction = new Checkout_Step_Two_Action(checkoutStepTwoPage);

		log.debug("Checkout_Step_Two_Assertions initialized");
	}

	/**
	 * Verifies Checkout Step Two page is displayed.
	 */
	public void verifyCheckoutStepTwoPageDisplayed() {

		log.info("Verifying Checkout Step Two page is displayed");

		Assert.assertTrue(checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed(),
				"Checkout Step Two / Overview page should be displayed");

		log.info("Checkout Step Two page is displayed successfully");
	}

	public void verifyCheckoutStepTwoPageReady() {

		log.info("Verifying Checkout Step Two page readiness");

		Assert.assertTrue(checkoutStepTwoPage.isCheckoutStepTwoPageReady(), "Checkout Step Two page is not ready");

		log.info("Checkout Step Two page is ready");
	}

	/**
	 * Verifies that the Checkout subtotal matches the Cart subtotal.
	 */
	public void verifySubtotalMatchesCartSubtotal() {

		log.info("Verifying Checkout subtotal matches Cart subtotal");

		String cartSubtotal = scenarioContext.getCartSubtotal();

		Assert.assertNotNull(cartSubtotal, "Cart subtotal should be available in ScenarioContext");

		Assert.assertFalse(cartSubtotal.trim().isEmpty(), "Cart subtotal should not be empty");

		String checkoutSubtotal = checkoutStepTwoPage.getSubtotal();

		Assert.assertNotNull(checkoutSubtotal, "Checkout subtotal must not be null");

		Assert.assertFalse(checkoutSubtotal.trim().isEmpty(), "Checkout subtotal should not be empty");

		log.info("Cart subtotal: {}", cartSubtotal);

		log.info("Checkout subtotal: {}", checkoutSubtotal);

		Assert.assertEquals(checkoutSubtotal, cartSubtotal, "Checkout subtotal does not match Cart subtotal. "
				+ "Expected: " + cartSubtotal + ", Actual: " + checkoutSubtotal);

		log.info("Checkout subtotal successfully matches Cart subtotal: {}", checkoutSubtotal);
	}

	public void verifyCurrentUrlContains(String expectedUrlPart) {

		log.info("Verifying current URL contains: {}", expectedUrlPart);

		String currentUrl = checkoutStepTwoPage.getCurrentUrl();

		Assert.assertTrue(currentUrl.contains(expectedUrlPart),
				"Expected URL to contain: " + expectedUrlPart + " but actual URL was: " + currentUrl);

		log.info("URL validation passed. Current URL: {}", currentUrl);
	}

	/**
	 * Verifies the number of products displayed on Checkout Step Two.
	 *
	 * @param expectedCount expected number of products
	 */
	public void verifyProductCount(int expectedCount) {

		int actualCount = checkoutStepTwoPage.getProductCount();

		log.info("Verifying product count. Expected: {}, Actual: {}", expectedCount, actualCount);

		Assert.assertEquals(actualCount, expectedCount, "Incorrect product count on Checkout Step Two. Expected: "
				+ expectedCount + " but found: " + actualCount);
	}

	/**
	 * * Verifies that the specified product is not displayed * on the Checkout Step
	 * Two page. * * @param productName product name that should not be displayed
	 */
	public void verifyProductNotDisplayed(String productName) {

		boolean isDisplayed = checkoutStepTwoPage.isProductDisplayed(productName);

		log.info("Verifying product is not displayed. Product: '{}', Displayed: {}", productName, isDisplayed);

		Assert.assertFalse(isDisplayed,
				"Product should NOT be displayed on Checkout Step Two, " + "but it was found: " + productName);
	}

	public void verifyAllCheckoutStepTwoControlsDisplayed() {

		log.info("Verifying all Checkout Step Two controls are displayed");

		Assert.assertTrue(checkoutStepTwoPage.isProductListDisplayed(), "Product list should be displayed");

		Assert.assertTrue(checkoutStepTwoPage.isPaymentInformationDisplayed(),
				"Payment information should be displayed");

		Assert.assertTrue(checkoutStepTwoPage.isShippingInformationDisplayed(),
				"Shipping information should be displayed");

		Assert.assertTrue(checkoutStepTwoPage.isSubtotalDisplayed(), "Subtotal should be displayed");

		Assert.assertTrue(checkoutStepTwoPage.isTaxDisplayed(), "Tax should be displayed");

		Assert.assertTrue(checkoutStepTwoPage.isTotalDisplayed(), "Total should be displayed");

		Assert.assertTrue(checkoutStepTwoPage.isFinishButtonDisplayed(), "Finish button should be displayed");

		Assert.assertTrue(checkoutStepTwoPage.isCancelButtonDisplayed(), "Cancel button should be displayed");

		log.info("All Checkout Step Two controls are displayed successfully");
	}

	public void verifyAllSelectedProductsDisplayed(List<String> expectedProducts) {

		List<String> actualProducts = checkoutStepTwoPage.getProductNames();

		log.info("Expected Checkout Overview products: {}", expectedProducts);
		log.info("Actual Checkout Overview products: {}", actualProducts);

		Assert.assertEquals(actualProducts.size(), expectedProducts.size(),
				"Checkout Overview product count does not match");

		for (String expectedProduct : expectedProducts) {

			Assert.assertTrue(actualProducts.contains(expectedProduct),
					"Product not found in Checkout Overview: " + expectedProduct);
		}

		log.info("All selected products are displayed in Checkout Overview");
	}

	/**
	 * * Verifies that the expected product price is displayed * on Checkout Step
	 * Two. * * @param productName product whose price should be verified *
	 * 
	 * @param expectedPrice expected product price
	 */
	public void verifyProductPriceDisplayed(String productName, String expectedPrice) {

		boolean isDisplayed = checkoutStepTwoPage.isProductPriceDisplayed(productName, expectedPrice);

		log.info("Verifying product price. Product: '{}', Expected Price: '{}', Displayed: {}", productName,
				expectedPrice, isDisplayed);

		Assert.assertTrue(isDisplayed, "Product price should be displayed on Checkout Step Two. " + "Product: '"
				+ productName + "', Expected Price: '" + expectedPrice + "'");

	}

	/**
	 * Verifies the price of a specific product on Checkout Step Two.
	 *
	 * @param productName   product name
	 * @param expectedPrice expected product price
	 */
	public void verifyProductPrice(String productName, String expectedPrice) {

		String actualPrice = checkoutStepTwoPage.getProductPrice(productName);

		log.info("Verifying product price. Product: '{}', Expected: '{}', Actual: '{}'", productName, expectedPrice,
				actualPrice);

		Assert.assertEquals(actualPrice, expectedPrice, "Incorrect product price for '" + productName + "'. Expected: '"
				+ expectedPrice + "' but found: '" + actualPrice + "'");
	}

	/**
	 * * Verifies that all products displayed on Checkout Step Two * have valid
	 * prices. * * A valid price: * - Must not be null or empty * - Must start with
	 * "$" * - Must contain a valid numeric amount * * Example: $29.99
	 */
	public void verifyAllProductPricesAreValid() {

		List<String> productPrices = checkoutStepTwoPage.getAllProductPrices();

		log.info("Verifying all product prices. Total products: {}", productPrices.size());

		Assert.assertFalse(productPrices.isEmpty(), "No product prices were displayed on Checkout Step Two");

		for (String price : productPrices) {

			boolean isValid = price != null && !price.trim().isEmpty() && price.trim().matches("^\\$\\d+(\\.\\d{2})?$");

			log.info("Product price: '{}', Valid: {}", price, isValid);

			Assert.assertTrue(isValid, "Invalid product price displayed on Checkout Step Two: " + price);

		}
		log.info("All {} product prices are valid", productPrices.size());

	}

	/**
	 * Verifies that Payment Information is displayed and is not empty on Checkout
	 * Step Two.
	 */
	public void verifyPaymentInformationNotEmpty() {

		String paymentInformation = checkoutStepTwoPage.getPaymentInformation();

		log.info("Verifying Payment Information. Actual: '{}'", paymentInformation);

		Assert.assertNotNull(paymentInformation, "Payment Information should not be null");

		Assert.assertFalse(paymentInformation.trim().isEmpty(), "Payment Information should not be empty");

		log.info("Payment Information is displayed and not empty: '{}'", paymentInformation);
	}

	/**
	 * * Verifies that Shipping Information is displayed * and is not empty on
	 * Checkout Step Two.
	 */
	public void verifyShippingInformationNotEmpty() {

		String shippingInformation = checkoutStepTwoPage.getShippingInformation();

		log.info("Verifying Shipping Information. Actual: '{}'", shippingInformation);

		Assert.assertNotNull(shippingInformation, "Shipping Information should not be null");

		Assert.assertFalse(shippingInformation.trim().isEmpty(), "Shipping Information should not be empty");

		log.info("Shipping Information is displayed and not empty: '{}'", shippingInformation);

	}

	/**
	 * * Converts a currency string such as "$29.99" * into a numeric value.
	 * * @param priceText currency text * @return numeric price
	 */
	private double parsePrice(String priceText) {

		Assert.assertNotNull(priceText, "Price value should not be null");

		Assert.assertFalse(priceText.trim().isEmpty(), "Price value should not be empty");

		try {

			String numericValue = priceText.replace("$", "").replace(",", "").trim();

			return Double.parseDouble(numericValue);

		} catch (NumberFormatException e) {

			Assert.fail("Invalid price format: " + priceText);

			return 0.0;

		}

	}

	/**
	 * * Verifies that the Checkout Total equals * Subtotal + Tax.
	 */
	public void verifyTotalEqualsSubtotalPlusTax_not_using() {

		String subtotalText = checkoutStepTwoPage.getSubtotal();

		String taxText = checkoutStepTwoPage.getTax();

		String totalText = checkoutStepTwoPage.getTotal();

		log.info("Checkout price validation. Subtotal: '{}', Tax: '{}', Total: '{}'", subtotalText, taxText, totalText);

		double subtotal = parsePrice(subtotalText);

		double tax = parsePrice(taxText);

		double total = parsePrice(totalText);

		double expectedTotal = Math.round((subtotal + tax) * 100.0) / 100.0;

		double actualTotal = Math.round(total * 100.0) / 100.0;

		log.info("Calculated Total: {}, Displayed Total: {}", expectedTotal, actualTotal);

		Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Incorrect checkout total. Expected: $"
				+ String.format("%.2f", expectedTotal) + " but found: $" + String.format("%.2f", actualTotal));

		log.info("Verified successfully: Total = Subtotal + Tax");

	}

	public void verifyProductNamesMatchCart(List<String> cartProductNames) {

		Assert.assertNotNull(cartProductNames, "Cart product names must not be null");

		List<String> checkoutProductNames = checkoutStepTwoPage.getAllProductNames();

		log.info("Cart product names: {}", cartProductNames);

		log.info("Checkout product names: {}", checkoutProductNames);

		Assert.assertEquals(checkoutProductNames.size(), cartProductNames.size(),
				"Product count mismatch between Cart and Checkout. " + "Cart count: " + cartProductNames.size()
						+ ", Checkout count: " + checkoutProductNames.size());

		Assert.assertEquals(checkoutProductNames, cartProductNames, "Product names in Checkout do not match Cart. "
				+ "Expected: " + cartProductNames + ", Actual: " + checkoutProductNames);

		log.info("Cart and Checkout product names match successfully");
	}

	/**
	 * Verifies that the Checkout Tax is not negative.
	 */
	public void verifyTaxIsNotNegativeString() {

		String taxText = checkoutStepTwoPage.getTax();

		log.info("Verifying tax is not negative. Tax: '{}'", taxText);

		double tax = parsePrice(taxText);

		log.info("Parsed tax value: {}", tax);

		Assert.assertTrue(tax >= 0.0, "Tax should not be negative, but found: $" + String.format("%.2f", tax));

		log.info("Tax validation passed. Tax is not negative: {}", tax);
	}

	/**
	 * * Verifies that all price summary values on Checkout Step Two * are valid. *
	 * Valid values: * - Must not be null * - Must not be empty * - Must contain a
	 * valid numeric currency value - Must not be negative
	 */
	public void verifyPriceSummaryValuesAreValid() {

		String subtotalText = checkoutStepTwoPage.getSubtotal();

		String taxText = checkoutStepTwoPage.getTax();

		String totalText = checkoutStepTwoPage.getTotal();

		log.info("Validating price summary values. " + "Subtotal: '{}', Tax: '{}', Total: '{}'", subtotalText, taxText,
				totalText);

		// Validate Subtotal
		double subtotal = parsePrice(subtotalText);
		Assert.assertTrue(subtotal >= 0.0, "Subtotal should not be negative: " + subtotalText);

		// Validate Tax
		double tax = parsePrice(taxText);
		Assert.assertTrue(tax >= 0.0, "Tax should not be negative: " + taxText);

		// Validate Total
		double total = parsePrice(totalText);

		Assert.assertTrue(total >= 0.0, "Total should not be negative: " + totalText);

		log.info("Price summary validation passed. " + "Subtotal: {}, Tax: {}, Total: {}", subtotal, tax, total);
	}

	/**
	 * Verifies that the number of products displayed on Checkout Step Two matches
	 * the number of products in the Cart.
	 *
	 * @param expectedCartCount expected product count from Cart
	 */
	public void verifyCheckoutProductCountMatchesCart(int expectedCartCount) {

		int checkoutProductCount = checkoutStepTwoPage.getProductCount();

		log.info("Verifying checkout product count against cart. " + "Expected Cart Count: {}, Checkout Count: {}",
				expectedCartCount, checkoutProductCount);

		Assert.assertEquals(checkoutProductCount, expectedCartCount,
				"Checkout product count does not match Cart product count. " + "Expected: " + expectedCartCount
						+ " but found: " + checkoutProductCount);

		log.info("Checkout product count matches Cart successfully: {}", checkoutProductCount);
	}

	/**
	 * Verifies that Checkout product prices match the product prices stored from
	 * Cart.
	 */
	public void verifyProductPricesMatchCart() {

		log.info("Verifying Checkout product prices match Cart");

		List<String> cartProductPrices = scenarioContext.getCartProductPrices();

		Assert.assertNotNull(cartProductPrices, "Cart product prices should be available " + "in ScenarioContext");

		List<String> checkoutProductPrices = checkoutStepTwoPage.getAllProductPrices();

		Assert.assertNotNull(checkoutProductPrices, "Checkout product prices must not be null");

		log.info("Cart product prices: {}", cartProductPrices);

		log.info("Checkout product prices: {}", checkoutProductPrices);

		Assert.assertEquals(checkoutProductPrices.size(), cartProductPrices.size(),
				"Product price count mismatch between Cart and Checkout. " + "Cart count: " + cartProductPrices.size()
						+ ", Checkout count: " + checkoutProductPrices.size());

		for (int i = 0; i < cartProductPrices.size(); i++) {

			String expectedPrice = cartProductPrices.get(i);

			String actualPrice = checkoutProductPrices.get(i);

			Assert.assertEquals(actualPrice, expectedPrice, "Product price mismatch at index " + i + ". Expected: "
					+ expectedPrice + ", Actual: " + actualPrice);

			log.info("Product price matched at index {}: {}", i, actualPrice);
		}

		log.info("All Cart product prices match Checkout product prices");
	}

	/**
	 * * Verifies that the product names displayed on Checkout Step Two * match the
	 * product names in the Cart. * @param expectedCartProductNames product names
	 * captured from Cart
	 */
//	public void verifyProductNamesMatchCart(List<String> expectedCartProductNames) {
//
//		Assert.assertNotNull(expectedCartProductNames, "Cart product names should not be null");
//
//		List<String> checkoutProductNames = checkoutStepTwoPage.getAllProductNames();
//
//		log.info("Verifying product names between Cart and Checkout. " + "Cart Products: {}, Checkout Products: {}",
//				expectedCartProductNames, checkoutProductNames);
//
//		Assert.assertEquals(checkoutProductNames.size(), expectedCartProductNames.size(),
//				"Product count mismatch between Cart and Checkout. " + "Cart count: " + expectedCartProductNames.size()
//						+ ", Checkout count: " + checkoutProductNames.size());
//
//		Assert.assertEquals(checkoutProductNames, expectedCartProductNames,
//				"Product names in Checkout do not match the Cart. " + "Expected: " + expectedCartProductNames
//						+ ", Actual: " + checkoutProductNames);
//
//		log.info("Product names successfully match between Cart and Checkout");
//
//	}

	/**
	 * * Verifies that the expected product description is displayed * on Checkout
	 * Step Two. * @param expectedDescription expected product description
	 */
	public void verifyProductDescriptionDisplayed(String expectedDescription) {

		boolean isDisplayed = checkoutStepTwoPage.isProductDescriptionDisplayed(expectedDescription);

		log.info("Verifying product description. Expected: '{}', Displayed: {}", expectedDescription, isDisplayed);

		Assert.assertTrue(isDisplayed, "Product description should be displayed on Checkout Step Two, "
				+ "but was not found: " + expectedDescription);

	}

	/**
	 * Verifies that product prices displayed on Checkout Step Two match the product
	 * prices displayed in the Cart.
	 */
	public void verifyProductPricesMatchCartItem() {

		log.info("Verifying Checkout product prices match Cart product prices");

		/*
		 * Get product prices from Cart
		 */
		List<String> cartProductPrices = scenarioContext.getCartProductPrices();

		/*
		 * Get product prices from Checkout Step Two
		 */
		List<String> checkoutProductPrices = checkoutStepTwoPage.getAllProductPrices();

		Assert.assertNotNull(cartProductPrices, "Cart product prices must not be null");

		Assert.assertNotNull(checkoutProductPrices, "Checkout product prices must not be null");

		Assert.assertFalse(cartProductPrices.isEmpty(), "Cart product prices should not be empty");

		Assert.assertFalse(checkoutProductPrices.isEmpty(), "Checkout product prices should not be empty");

		log.info("Cart product prices: {}", cartProductPrices);

		log.info("Checkout product prices: {}", checkoutProductPrices);

		/*
		 * Verify product count
		 */
		Assert.assertEquals(checkoutProductPrices.size(), cartProductPrices.size(),
				"Product price count mismatch between Cart and Checkout. " + "Cart count: " + cartProductPrices.size()
						+ ", Checkout count: " + checkoutProductPrices.size());

		/*
		 * Verify each product price
		 */
		for (int i = 0; i < cartProductPrices.size(); i++) {

			String expectedPrice = cartProductPrices.get(i);

			String actualPrice = checkoutProductPrices.get(i);

			log.info("Comparing product price at index {}: Cart = {}, Checkout = {}", i, expectedPrice, actualPrice);

			Assert.assertEquals(actualPrice, expectedPrice, "Product price mismatch at index " + i
					+ ". Expected Cart price: " + expectedPrice + ", Actual Checkout price: " + actualPrice);
		}

		log.info("All Cart product prices match Checkout product prices successfully");
	}

	/**
	 * * Verifies that the expected product name is displayed * on Checkout Step
	 * Two. * @param expectedProductName expected product name
	 */
	public void verifyProductName(String expectedProductName) {

		String actualProductName = checkoutStepTwoPage.getProductName(expectedProductName);

		log.info("Verifying product name. Expected: '{}', Actual: '{}'", expectedProductName, actualProductName);

		Assert.assertEquals(actualProductName, expectedProductName,
				"Incorrect product name on Checkout Step Two. Expected: '" + expectedProductName + "' but found: '"
						+ actualProductName + "'");

	}

	/**
	 * Verifies page title.
	 */
	public void verifyPageTitle(String expectedTitle) {

		log.info("Verifying Checkout Step Two page title. Expected: {}", expectedTitle);

		Assert.assertEquals(checkoutStepTwoPage.getPageTitle(), expectedTitle,
				"Checkout Step Two page title is incorrect");

		log.info("Checkout Step Two page title verified successfully");
	}

	/**
	 * Verifies Checkout Overview container.
	 */
	public void verifyCheckoutOverviewDisplayed() {

		log.info("Verifying Checkout Overview container");

		Assert.assertTrue(checkoutStepTwoPage.isCheckoutOverviewDisplayed(),
				"Checkout Overview container should be displayed");
	}

	// =========================================================
	// PRODUCT ASSERTIONS
	// =========================================================

	/**
	 * Verifies at least one cart item is displayed.
	 */
	public void verifyCartItemDisplayed() {

		log.info("Verifying Checkout Overview contains product");

		Assert.assertTrue(checkoutStepTwoPage.isCartItemDisplayed(),
				"At least one product should be displayed in Checkout Overview");
	}

	/**
	 * Verifies expected cart item count.
	 */
	public void verifyCartItemCount(int expectedCount) {

		log.info("Verifying Checkout Overview item count. Expected: {}", expectedCount);

		int actualCount = checkoutStepTwoPage.getCartItemCount();

		Assert.assertEquals(actualCount, expectedCount, "Incorrect Checkout Overview item count");

		log.info("Checkout Overview item count verified. Actual: {}", actualCount);
	}

	/**
	 * Verifies cart contains more than zero products.
	 */
	public void verifyCartItemCountGreaterThanZero() {

		log.info("Verifying Checkout Overview contains at least one product");

		int actualCount = checkoutStepTwoPage.getCartItemCount();

		Assert.assertTrue(actualCount > 0, "Checkout Overview should contain at least one product");
	}

	/**
	 * Verifies product is displayed.
	 */
	public void verifyProductDisplayed(String productName) {

		log.info("Verifying product is displayed: {}", productName);

		Assert.assertTrue(checkoutStepTwoPage.isProductDisplayed(productName),
				"Product should be displayed: " + productName);
	}

	/**
	 * Verifies expected product names.
	 */
	public void verifyProductNames(List<String> expectedProductNames) {

		log.info("Verifying product names in Checkout Overview");

		List<String> actualProductNames = checkoutStepTwoPage.getProductNames();

		Assert.assertEquals(actualProductNames, expectedProductNames, "Checkout Overview product names are incorrect");

		log.info("Product names verified successfully");
	}

	/**
	 * Verifies product name by index.
	 */
	public void verifyProductName(int index, String expectedProductName) {

		log.info("Verifying product name at index: {}", index);

		String actualProductName = checkoutStepTwoPage.getProductName(index);

		Assert.assertEquals(actualProductName, expectedProductName, "Incorrect product name at index: " + index);
	}

	/**
	 * Verifies product price by index.
	 */
	public void verifyProductPrice(int index, String expectedPrice) {

		log.info("Verifying product price at index: {}", index);

		String actualPrice = checkoutStepTwoPage.getProductPrice(index);

		Assert.assertEquals(actualPrice, expectedPrice, "Incorrect product price at index: " + index);
	}

	// =========================================================
	// PAYMENT ASSERTIONS
	// =========================================================

	/**
	 * Verifies Payment Information is displayed.
	 */
	public void verifyPaymentInformationDisplayed() {

		log.info("Verifying Payment Information is displayed");

		Assert.assertTrue(checkoutStepTwoPage.isPaymentInformationDisplayed(),
				"Payment Information should be displayed");
	}

	/**
	 * Verifies Payment Information value.
	 */
	public void verifyPaymentInformation(String expectedPaymentInformation) {

		log.info("Verifying Payment Information");

		String actualPaymentInformation = checkoutStepTwoPage.getPaymentInformation();

		Assert.assertEquals(actualPaymentInformation, expectedPaymentInformation, "Incorrect Payment Information");
	}

	// =========================================================
	// SHIPPING ASSERTIONS
	// =========================================================

	/**
	 * Verifies Shipping Information is displayed.
	 */
	public void verifyShippingInformationDisplayed() {

		log.info("Verifying Shipping Information is displayed");

		Assert.assertTrue(checkoutStepTwoPage.isShippingInformationDisplayed(),
				"Shipping Information should be displayed");
	}

	/**
	 * Verifies Shipping Information value.
	 */
	public void verifyShippingInformation(String expectedShippingInformation) {

		log.info("Verifying Shipping Information");

		String actualShippingInformation = checkoutStepTwoPage.getShippingInformation();

		Assert.assertEquals(actualShippingInformation, expectedShippingInformation, "Incorrect Shipping Information");
	}

	// =========================================================
	// PRICE ASSERTIONS
	// =========================================================

	/**
	 * Verifies subtotal is displayed.
	 */
	public void verifySubtotalDisplayed() {

		log.info("Verifying subtotal is displayed");

		Assert.assertTrue(checkoutStepTwoPage.isSubtotalDisplayed(), "Subtotal should be displayed");
	}

	/**
	 * Verifies subtotal text.
	 */
	public void verifySubtotal(String expectedSubtotal) {

		log.info("Verifying subtotal. Expected: {}", expectedSubtotal);

		Assert.assertEquals(checkoutStepTwoPage.getSubtotal(), expectedSubtotal, "Incorrect subtotal");
	}

	/**
	 * Verifies tax is displayed.
	 */
	public void verifyTaxDisplayed() {

		log.info("Verifying tax is displayed");

		Assert.assertTrue(checkoutStepTwoPage.isTaxDisplayed(), "Tax should be displayed");
	}

	/**
	 * Verifies tax text.
	 */
	public void verifyTax(String expectedTax) {

		log.info("Verifying tax. Expected: {}", expectedTax);

		Assert.assertEquals(checkoutStepTwoPage.getTax(), expectedTax, "Incorrect tax");
	}

	/**
	 * Verifies total is displayed.
	 */
	public void verifyTotalDisplayed() {

		log.info("Verifying total is displayed");

		Assert.assertTrue(checkoutStepTwoPage.isTotalDisplayed(), "Total should be displayed");
	}

	/**
	 * Verifies total text.
	 */
	public void verifyTotal(String expectedTotal) {

		log.info("Verifying total. Expected: {}", expectedTotal);

		Assert.assertEquals(checkoutStepTwoPage.getTotal(), expectedTotal, "Incorrect total");
	}

	// =========================================================
	// BUTTON ASSERTIONS
	// =========================================================

	/**
	 * Verifies Finish button is displayed.
	 */
	public void verifyFinishButtonDisplayed() {

		log.info("Verifying Finish button is displayed");

		Assert.assertTrue(checkoutStepTwoPage.isFinishButtonDisplayed(), "Finish button should be displayed");
	}

	/**
	 * Verifies Cancel button is displayed.
	 */
	public void verifyCancelButtonDisplayed() {

		log.info("Verifying Cancel button is displayed");

		Assert.assertTrue(checkoutStepTwoPage.isCancelButtonDisplayed(), "Cancel button should be displayed");
	}

	/**
	 * Verifies Finish button is enabled.
	 */
	public void verifyFinishButtonEnabled() {

		log.info("Verifying Finish button is enabled");

		Assert.assertTrue(checkoutStepTwoPage.isFinishButtonEnabled(), "Finish button should be enabled");
	}

	/**
	 * Verifies Cancel button is enabled.
	 */
	public void verifyCancelButtonEnabled() {

		log.info("Verifying Cancel button is enabled");

		Assert.assertTrue(checkoutStepTwoPage.isCancelButtonEnabled(), "Cancel button should be enabled");
	}

	/**
	 * Verifies all Checkout Step Two controls.
	 */
	public void verifyAllCheckoutControlsDisplayed() {

		log.info("Verifying all Checkout Step Two controls");

		Assert.assertTrue(checkoutStepTwoPage.isFinishButtonDisplayed(), "Finish button should be displayed");

		Assert.assertTrue(checkoutStepTwoPage.isCancelButtonDisplayed(), "Cancel button should be displayed");

		log.info("All Checkout Step Two controls verified");
	}

	// =========================================================
	// PAGE READINESS ASSERTION
	// =========================================================

	/**
	 * Verifies Checkout Step Two page is completely ready.
	 */
	public void verifyPageReadyForCheckoutCompletion() {

		log.info("Verifying Checkout Step Two page readiness");

		Assert.assertTrue(checkoutStepTwoPage.isPageReadyForCheckoutCompletion(),
				"Checkout Step Two page should be ready for completion");

		log.info("Checkout Step Two page is ready for completion");
	}

	// =========================================================
	// WAIT BASED ASSERTIONS
	// =========================================================

	/**
	 * Waits for Checkout Step Two page and verifies it.
	 */
	public void waitAndVerifyCheckoutStepTwoPageDisplayed() {

		log.info("Waiting for Checkout Step Two page");

		boolean pageDisplayed = WaitUtils
				.waitForCondition(driver -> checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed());

		Assert.assertTrue(pageDisplayed, "Checkout Step Two page should be displayed after waiting");

		log.info("Checkout Step Two page displayed successfully");
	}

	/**
	 * Waits for Checkout Overview and verifies it.
	 */
	public void waitAndVerifyCheckoutOverviewDisplayed() {

		log.info("Waiting for Checkout Overview");

		boolean overviewDisplayed = WaitUtils
				.waitForCondition(driver -> checkoutStepTwoPage.isCheckoutOverviewDisplayed());

		Assert.assertTrue(overviewDisplayed, "Checkout Overview should be displayed after waiting");
	}

	// =========================================================
	// COMBINED ASSERTIONS
	// =========================================================

	/**
	 * Verifies complete Checkout Overview.
	 */
	public void verifyCompleteCheckoutOverview() {

		log.info("Verifying complete Checkout Overview");

		verifyCheckoutStepTwoPageDisplayed();
		verifyCheckoutOverviewDisplayed();

		verifyCartItemDisplayed();

		verifyPaymentInformationDisplayed();
		verifyShippingInformationDisplayed();

		verifySubtotalDisplayed();
		verifyTaxDisplayed();
		verifyTotalDisplayed();

		verifyFinishButtonDisplayed();
		verifyCancelButtonDisplayed();

		log.info("Complete Checkout Overview verified successfully");
	}

	/**
	 * Verifies that the Checkout Complete page is displayed.
	 */
	public void verifyCheckoutCompletePageDisplayed() {

		log.info("Verifying Checkout Complete page is displayed");

		// boolean isDisplayed = checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed();

		boolean isDisplayed = checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed();

		Assert.assertTrue(isDisplayed, "Checkout Complete page should be displayed after clicking Finish");

		log.info("Checkout Complete page is displayed successfully");
	}

	

	/**
	 * Verifies that the Inventory page is displayed.
	 */
	public void verifyInventoryPageDisplayed() {

		log.info("Verifying Inventory page is displayed");

		boolean inventoryPageDisplayed = scenarioContext.getPageObjectManager().getInventoryPage()
				.isInventoryPageDisplayed();

		Assert.assertTrue(inventoryPageDisplayed, "Inventory page should be displayed after clicking Cancel");

		log.info("Inventory page is displayed successfully");
	}

	/**
	 * Verifies that Checkout Step Two page is no longer displayed.
	 *
	 * Checks both page title and URL.
	 */
	public void verifyCheckoutStepTwoPageNotDisplayed() {

		log.info("Verifying Checkout Step Two page is not displayed");

		boolean pageDisplayed = checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed();

		boolean urlDisplayed = checkoutStepTwoPage.isCheckoutStepTwoUrlDisplayed();

		Assert.assertFalse(pageDisplayed, "Checkout Step Two page should not be displayed");

		Assert.assertFalse(urlDisplayed, "Checkout Step Two URL should not be displayed");

		log.info("Checkout Step Two page is not displayed. Page: {}, URL: {}", pageDisplayed, urlDisplayed);
	}

	/**
	 * Verifies that the Checkout Overview is not empty.
	 */
	public void verifyCheckoutOverviewNotEmpty() {

		log.info("Verifying Checkout Overview is not empty");

		int productCount = checkoutStepTwoPage.getProductCount();

		Assert.assertTrue(productCount > 0,
				"Checkout Overview should not be empty. Expected at least one product, but found: " + productCount);

		log.info("Checkout Overview is not empty. Product count: {}", productCount);
	}

	/**
	 * Verifies that the product list on Checkout Step Two is not empty.
	 */
	public void verifyProductListNotEmpty() {

		log.info("Verifying Checkout Step Two product list is not empty");

		int productCount = checkoutStepTwoPage.getProductCount();

		Assert.assertTrue(productCount > 0,
				"Product list should not be empty. Expected at least one product, but found: " + productCount);

		log.info("Checkout Step Two product list is not empty. Product count: {}", productCount);
	}

	/**
	 * Verifies that the Finish button is not displayed.
	 */
	public void verifyFinishButtonNotDisplayed() {

		log.info("Verifying Finish button is not displayed");

		boolean finishButtonDisplayed = checkoutStepTwoPage.isFinishButtonDisplayed();

		Assert.assertFalse(finishButtonDisplayed, "Finish button should not be displayed");

		log.info("Finish button is not displayed");
	}

	/**
	 * Verifies that the order has not been completed.
	 *
	 * The order is considered not completed when the Checkout Complete page is not
	 * displayed.
	 */
	public void verifyOrderNotCompleted() {

		log.info("Verifying that the order has not been completed");

		boolean checkoutCompleteDisplayed = checkoutStepTwoPage.isCheckoutCompletePageDisplayed();

		Assert.assertFalse(checkoutCompleteDisplayed,
				"Order should not be completed. Checkout Complete page should not be displayed.");

		log.info("Order has not been completed");
	}

	/**
	 * Verifies that all products selected in the cart are displayed on Checkout
	 * Step Two.
	 */
	public void verifyAllSelectedProductsDisplayed() {

		log.info("Verifying all selected products are displayed on Checkout Step Two");

		List<String> selectedProductNames = scenarioContext.getCartProductNames();

		Assert.assertNotNull(selectedProductNames, "Selected product list should not be null");

		Assert.assertFalse(selectedProductNames.isEmpty(), "Selected product list should not be empty");

		List<String> checkoutProductNames = checkoutStepTwoPage.getAllProductNames();

		Assert.assertNotNull(checkoutProductNames, "Checkout product list should not be null");

		Assert.assertEquals(checkoutProductNames.size(), selectedProductNames.size(),
				"Checkout product count should match selected product count");

		for (String selectedProduct : selectedProductNames) {

			boolean productDisplayed = checkoutProductNames.stream()
					.anyMatch(checkoutProduct -> checkoutProduct.equalsIgnoreCase(selectedProduct));

			Assert.assertTrue(productDisplayed,
					"Selected product should be displayed on Checkout Step Two: " + selectedProduct);

			log.info("Selected product '{}' is displayed on Checkout Step Two", selectedProduct);
		}

		log.info("All {} selected product(s) are displayed on Checkout Step Two", selectedProductNames.size());
	}

	/**
	 * Verifies that the Checkout Step Two subtotal is calculated correctly.
	 *
	 * The expected subtotal is calculated by adding all displayed product prices
	 * and comparing the result with the Item total displayed on the page.
	 */
	public void verifySubtotalCalculatedCorrectly() {

		log.info("Verifying Checkout Step Two subtotal is calculated correctly");

		List<String> productPrices = checkoutStepTwoPage.getAllProductPrices();

		Assert.assertNotNull(productPrices, "Product price list should not be null");

		Assert.assertFalse(productPrices.isEmpty(), "Product price list should not be empty");

		double calculatedSubtotal = 0.0;

		for (String priceText : productPrices) {

			Assert.assertNotNull(priceText, "Product price should not be null");

			String cleanedPrice = priceText.replace("$", "").replace(",", "").trim();

			double price = Double.parseDouble(cleanedPrice);

			calculatedSubtotal += price;

			log.debug("Product price: {} | Running subtotal: {}", price, calculatedSubtotal);
		}

		String actualSubtotalText = checkoutStepTwoPage.getSubtotal();

		Assert.assertNotNull(actualSubtotalText, "Checkout subtotal should not be null");

		Assert.assertFalse(actualSubtotalText.trim().isEmpty(), "Checkout subtotal should not be empty");

		String cleanedSubtotal = actualSubtotalText.replace("Item total:", "").replace("$", "").replace(",", "").trim();

		double displayedSubtotal = Double.parseDouble(cleanedSubtotal);

		log.info("Calculated subtotal: {} | Displayed subtotal: {}", calculatedSubtotal, displayedSubtotal);

		Assert.assertEquals(displayedSubtotal, calculatedSubtotal, 0.01, "Checkout subtotal is calculated incorrectly");

		log.info("Checkout subtotal is calculated correctly");
	}

	/**
	 * Verifies that the Checkout Step Two total is calculated correctly.
	 *
	 * Expected Total = Subtotal + Tax
	 */
	public void verifyTotalCalculatedCorrectly() {

		log.info("Verifying Checkout Step Two total is calculated correctly");

		// Get subtotal
		String subtotalText = checkoutStepTwoPage.getSubtotal();

		Assert.assertNotNull(subtotalText, "Subtotal should not be null");

		Assert.assertFalse(subtotalText.trim().isEmpty(), "Subtotal should not be empty");

		// Get tax
		String taxText = checkoutStepTwoPage.getTax();

		Assert.assertNotNull(taxText, "Tax should not be null");

		Assert.assertFalse(taxText.trim().isEmpty(), "Tax should not be empty");

		// Get total
		String totalText = checkoutStepTwoPage.getTotal();

		Assert.assertNotNull(totalText, "Total should not be null");

		Assert.assertFalse(totalText.trim().isEmpty(), "Total should not be empty");

		// Convert Subtotal
		double subtotal = Double
				.parseDouble(subtotalText.replace("Item total:", "").replace("$", "").replace(",", "").trim());

		// Convert Tax
		double tax = Double.parseDouble(taxText.replace("Tax:", "").replace("$", "").replace(",", "").trim());

		// Convert Total
		double actualTotal = Double
				.parseDouble(totalText.replace("Total:", "").replace("$", "").replace(",", "").trim());

		// Calculate expected total
		double expectedTotal = subtotal + tax;

		log.info("Subtotal: {} | Tax: {} | Expected Total: {} | Actual Total: {}", subtotal, tax, expectedTotal,
				actualTotal);

		// Verify total
		Assert.assertEquals(actualTotal, expectedTotal, 0.01,
				"Checkout total is calculated incorrectly. Expected: " + expectedTotal + " but found: " + actualTotal);

		log.info("Checkout Step Two total is calculated correctly");
	}

	/**
	 * Verifies that the shopping cart is empty after checkout.
	 */
	public void verifyCartIsEmptyAfterCheckout() {

		log.info("Verifying that cart is empty after checkout");

		int cartProductCount = checkoutStepTwoPage.getProductCount();

		Assert.assertEquals(cartProductCount, 0,
				"Cart should be empty after checkout, but found " + cartProductCount + " product(s)");

		log.info("Cart is empty after checkout. Product count: {}", cartProductCount);
	}

	/**
	 * Verifies that the order has been completed successfully.
	 *
	 * Validates: 1. Checkout Complete page is displayed. 2. Confirmation message is
	 * displayed.
	 */
	public void verifyOrderCompletedSuccessfully() {

		log.info("Verifying that the order was completed successfully");

		// Verify Checkout Complete page
		boolean checkoutCompleteDisplayed = checkoutStepTwoPage.isCheckoutCompletePageDisplayed();

		Assert.assertTrue(checkoutCompleteDisplayed,
				"Checkout Complete page should be displayed after clicking Finish");

		// Verify confirmation message
		String confirmationMessage = checkoutStepTwoPage.getConfirmationMessage();

		Assert.assertNotNull(confirmationMessage, "Order confirmation message should not be null");

		Assert.assertFalse(confirmationMessage.trim().isEmpty(), "Order confirmation message should not be empty");

		Assert.assertEquals(confirmationMessage.trim(), "Thank you for your order!",
				"Incorrect order confirmation message");

		log.info("Order completed successfully. Confirmation message: '{}'", confirmationMessage);
	}

	public void verifyProductCountInCheckoutOverviewPage(int expectedProductCount) {

		int actualProductCount = checkoutStepTwoPage.getProductNames().size();

		log.info("Expected Checkout Overview product count: {}", expectedProductCount);

		log.info("Actual Checkout Overview product count: {}", actualProductCount);

		Assert.assertEquals(actualProductCount, expectedProductCount, "Incorrect Checkout Overview product count");
	}

	public void verifyAllSelectedProductsDisplayed_(List<String> expectedProducts) {

		log.info("Expected Checkout Overview products: {}", expectedProducts);

		List<String> actualProducts = checkoutStepTwoPage.getProductNames();

		log.info("Actual Checkout Overview products: {}", actualProducts);

		Assert.assertEquals(actualProducts.size(), expectedProducts.size(),
				"Checkout Overview product count does not match");

		for (String expectedProduct : expectedProducts) {

			Assert.assertTrue(actualProducts.contains(expectedProduct),
					"Product not found in Checkout Overview: " + expectedProduct);

			log.info("Verified product is displayed in Checkout Overview: {}", expectedProduct);
		}
	}

	public void verifySubtotalEqualsSumOfProductPrices() {

		log.info("Verifying subtotal equals sum of all selected product prices");

		List<String> productPriceTexts = checkoutStepTwoPage.getProductPrices();

		Assert.assertFalse(productPriceTexts.isEmpty(), "Checkout Overview should contain at least one product price");

		double expectedSubtotal = 0.0;

		for (String priceText : productPriceTexts) {

			Assert.assertNotNull(priceText, "Product price should not be null");

			String cleanedPrice = priceText.replace("$", "").replace(",", "").trim();

			double productPrice;

			try {
				productPrice = Double.parseDouble(cleanedPrice);
			} catch (NumberFormatException e) {

				Assert.fail("Invalid product price displayed in Checkout Overview: " + priceText);

				return;
			}

			log.info("Product price: {} -> parsed value: {}", priceText, productPrice);

			expectedSubtotal += productPrice;
		}

		double actualSubtotal = checkoutStepTwoPage.getSubtotalOfProductPrice();

		log.info("Expected subtotal: {}", expectedSubtotal);
		log.info("Actual subtotal: {}", actualSubtotal);

		Assert.assertEquals(actualSubtotal, expectedSubtotal, 0.01,
				"Subtotal should equal the sum of all selected product prices");

		log.info("Subtotal validation passed. Expected: {}, Actual: {}", expectedSubtotal, actualSubtotal);
	}

	public void verifyTaxCalculatedFromSubtotal() {

		log.info("Calculating expected tax from subtotal");

		double subtotal = checkoutStepTwoPage.getSubtotalInDouble();

		double actualTax = checkoutStepTwoPage.getTaxOnProduct();

		/*
		 * SauceDemo calculates tax at 8%.
		 */
		double expectedTax = Math.round((subtotal * 0.08) * 100.0) / 100.0;

		log.info("Subtotal: {}", subtotal);
		log.info("Expected tax: {}", expectedTax);
		log.info("Actual tax: {}", actualTax);

		Assert.assertEquals(actualTax, expectedTax, 0.01, "Tax should be calculated as 8% of the subtotal");

		log.info("Tax calculation verified successfully. Expected: {}, Actual: {}", expectedTax, actualTax);
	}

	public void verifyTotalEqualsSubtotalPlusTax() {

		log.info("Calculating expected total from subtotal and tax");

		double subtotal = checkoutStepTwoPage.getSubtotalInDouble();

		double actualTax = checkoutStepTwoPage.getTaxOnProduct();

		double actualTotal = checkoutStepTwoPage.getTotalInDouble();

		double expectedTotal = Math.round((subtotal + actualTax) * 100.0) / 100.0;

		log.info("Subtotal: {}", subtotal);

		log.info("Actual Tax: {}", actualTax);

		log.info("Expected Total: {}", expectedTotal);

		log.info("Actual Total: {}", actualTotal);

		Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Total should equal subtotal plus tax");

		log.info("Total calculation verified successfully. Expected: {}, Actual: {}", expectedTotal, actualTotal);
	}

	/**
	 * Verifies that Checkout Step Two cannot be accessed as a valid checkout flow
	 * without completing Checkout Step One.
	 */
	public void verifyCheckoutStepTwoAccessRequiresValidCheckoutInformation_not_using() {

		log.info("Verifying Checkout Step Two access without Checkout Step One information");

		String currentUrl = checkoutStepTwoPage.getCurrentUrl();

		log.info("Current URL after direct access attempt: {}", currentUrl);

		boolean blockedFromCheckoutStepTwo = !currentUrl.contains("checkout-step-two.html");

		Assert.assertTrue(blockedFromCheckoutStepTwo,
				"User should not be able to complete checkout without valid checkout information. " + "Current URL: "
						+ currentUrl);

		log.info("Verified that user cannot complete checkout without valid checkout information");
	}

	public void verifyCheckoutStepTwoAccessRequiresValidCheckoutInformation() {

		log.info("Verifying Checkout Step Two access without completing Checkout Step One");

		String currentUrl = checkoutStepTwoPage.getCurrentUrl();

		log.info("Current URL after direct access attempt: {}", currentUrl);

		boolean isCheckoutStepTwo = currentUrl.contains("checkout-step-two.html");

		if (isCheckoutStepTwo) {

			log.info("Checkout Step Two URL is accessible directly.");

			/*
			 * Direct URL access alone does not prove that checkout can actually be
			 * completed.
			 *
			 * Verify that required checkout information is not available.
			 */

			Assert.assertFalse(checkoutStepTwoPage.isCheckoutCompleteAvailable(),
					"User should not be able to complete checkout without valid checkout information.");

		} else {

			log.info("User was redirected away from Checkout Step Two. Current URL: {}", currentUrl);

			Assert.assertTrue(true, "User was prevented from accessing Checkout Step Two.");
		}
	}

	/**
	 * Verifies that Checkout Step Two is not available when the cart is empty.
	 */
	public void verifyCheckoutStepTwoNotAvailableForEmptyCart() {

		log.info("Verifying Checkout Step Two is not available for an empty cart");

		String currentUrl = checkoutStepTwoPage.getCurrentUrl();

		log.info("Current URL after attempting Checkout Step Two: {}", currentUrl);

		boolean checkoutStepTwoUnavailable = currentUrl == null || !currentUrl.contains("/checkout-step-two.html");

		Assert.assertTrue(checkoutStepTwoUnavailable,
				"Checkout Step Two should NOT be available for an empty cart. " + "Current URL: " + currentUrl);

		log.info("Checkout Step Two is correctly unavailable for an empty cart");
	}

	/**
	 * Verifies that selected products remain displayed on Checkout Step Two.
	 */
	public void verifySelectedProductsDisplayed() {

		log.info("Verifying selected products are displayed on Checkout Step Two");

		int productCount = checkoutStepTwoPage.getProductCount();

		log.info("Products displayed on Checkout Step Two: {}", productCount);

		Assert.assertTrue(productCount > 0, "At least one selected product should remain displayed "
				+ "on Checkout Step Two, but found: " + productCount);

		log.info("Selected products remain displayed successfully. Product count: {}", productCount);
	}

	/**
	 * Verifies that the previous checkout page is displayed.
	 */

	/**
	 * Verifies that the user is returned to the previous page after browser back
	 * navigation.
	 */
	public void verifyPreviousCheckoutPageDisplayed() {

		log.info("Verifying previous page after browser back navigation");

		String currentUrl = checkoutStepTwoPage.getCurrentUrl();

		log.info("Current URL after browser back: {}", currentUrl);

		Assert.assertTrue(currentUrl.contains("/inventory.html") || currentUrl.equals("https://www.saucedemo.com/"),
				"User should be returned to the previous page after browser back, " + "but current URL is: "
						+ currentUrl);

		log.info("Previous page displayed successfully. Current URL: {}", currentUrl);
	}

	public void verifyCheckoutInformationCannotBeEdited() {

		log.info("Verifying checkout information cannot be edited on Checkout Step Two");

		boolean editableFieldsDisplayed = checkoutStepTwoPage.areCheckoutInformationFieldsEditable();

		Assert.assertFalse(editableFieldsDisplayed, "Checkout information fields are editable on Checkout Step Two");

		log.info("Verified checkout information cannot be edited on Checkout Step Two");
	}

	/**
	 * Verifies that checkout information is displayed as review information on
	 * Checkout Step Two.
	 *
	 * Checkout Step Two should display the order overview including: - Payment
	 * Information - Shipping Information - Price / order summary
	 */
	public void verifyCustomerCheckoutInformationDisplayedAsReviewInformation() {

		log.info("Verifying customer checkout information is displayed as review information");

		boolean pageReady = checkoutStepTwoPage.isCheckoutStepTwoPageReady();

		Assert.assertTrue(pageReady, "Checkout Step Two page is not ready");

		boolean paymentInformationDisplayed = checkoutStepTwoPage.isPaymentInformationDisplayed();

		Assert.assertTrue(paymentInformationDisplayed, "Payment Information is not displayed on Checkout Step Two");

		boolean shippingInformationDisplayed = checkoutStepTwoPage.isShippingInformationDisplayed();

		Assert.assertTrue(shippingInformationDisplayed, "Shipping Information is not displayed on Checkout Step Two");

		boolean summaryInformationDisplayed = checkoutStepTwoPage.isOrderSummaryDisplayed();

		Assert.assertTrue(summaryInformationDisplayed,
				"Order summary information is not displayed on Checkout Step Two");

		log.info("Customer checkout information is displayed correctly as review information");
	}

	public void verifySameProductDisplayedInCheckoutOverview() {

		log.info("Verifying same product is displayed in Checkout Overview");

		String expectedProduct = scenarioContext.get("productName", String.class);

		Assert.assertNotNull(expectedProduct, "Expected product name was not stored in ScenarioContext");

		boolean displayed = checkoutStepTwoPage.isProductDisplayed(expectedProduct);

		Assert.assertTrue(displayed, "Product '" + expectedProduct + "' is not displayed in Checkout Overview");

		log.info("Product '{}' is displayed in Checkout Overview", expectedProduct);
	}

	/**
	 * Verifies that the specified product is displayed in the Checkout Overview on
	 * Checkout Step Two.
	 *
	 * @param productName expected product name
	 */
	public void verifyProductDisplayedInCheckoutOverview(String productName) {

		log.info("Verifying product '{}' is displayed in Checkout Overview", productName);

		Assert.assertNotNull(productName, "Product name must not be null");

		Assert.assertFalse(productName.trim().isEmpty(), "Product name must not be empty");

		boolean productDisplayed = checkoutStepTwoPage.isProductDisplayedInCheckoutOverview(productName);

		Assert.assertTrue(productDisplayed, "Product '" + productName + "' is not displayed in Checkout Overview");

		log.info("Product '{}' is displayed successfully in Checkout Overview", productName);
	}

	public void verifyProductPriceMatchesCartPrice(String productName) {

		log.info("Verifying product '{}' price in Checkout Overview matches Cart price", productName);

		Assert.assertNotNull(productName, "Product name must not be null");

		Assert.assertFalse(productName.trim().isEmpty(), "Product name must not be empty");

		// Retrieve price captured from Cart
		String cartPrice = scenarioContext.get("cartProductPrice", String.class);

		Assert.assertNotNull(cartPrice, "Cart product price was not captured for product: " + productName);

		// Verify the product exists in Checkout Overview
		boolean productDisplayed = checkoutStepTwoPage.isProductDisplayedInCheckoutOverview(productName);

		Assert.assertTrue(productDisplayed, "Product '" + productName + "' is not displayed in Checkout Overview");

		// Get price from Checkout Overview
		String checkoutPrice = checkoutStepTwoPage.getProductPriceInCheckoutOverview(productName);

		Assert.assertNotNull(checkoutPrice, "Checkout Overview price was not found for product: " + productName);

		Assert.assertFalse(checkoutPrice.trim().isEmpty(),
				"Checkout Overview price is empty for product: " + productName);

		log.info("Cart price for '{}'      : {}", productName, cartPrice);

		log.info("Checkout price for '{}' : {}", productName, checkoutPrice);

		Assert.assertEquals(checkoutPrice.trim(), cartPrice.trim(), "Price mismatch for product '" + productName
				+ "'. Cart price: " + cartPrice + ", Checkout Overview price: " + checkoutPrice);

		log.info("Price verification successful for '{}'. Cart price '{}' " + "matches Checkout Overview price '{}'",
				productName, cartPrice, checkoutPrice);
	}

	public void verifyProductCountMatchesCartCount_not_using() {

		log.info("Verifying Checkout Overview item count matches Cart item count");

		// Get actual Cart item count
		int cartItemCount = cartPage.getCartItemCount();

		log.info("Cart item count: {}", cartItemCount);

		// Get actual Checkout Overview item count
		int checkoutOverviewItemCount = checkoutStepTwoPage.getProductCount();

		log.info("Checkout Overview item count: {}", checkoutOverviewItemCount);

		// Compare Cart and Checkout Overview counts
		Assert.assertEquals(checkoutOverviewItemCount, cartItemCount,
				"Checkout Overview item count does not match Cart item count. " + "Cart count: " + cartItemCount
						+ ", Checkout Overview count: " + checkoutOverviewItemCount);

		log.info("Checkout Overview item count matches Cart item count. " + "Count: {}", cartItemCount);
	}

	public void verifyProductCountMatchesCartCount() {

		log.info("Verifying Checkout Overview item count matches Cart item count");

		// Retrieve Cart count captured earlier
		Integer cartItemCount = scenarioContext.get("cartItemCount", Integer.class);

		Assert.assertNotNull(cartItemCount, "Cart item count was not captured before navigating to Checkout");

		log.info("Captured Cart item count: {}", cartItemCount);

		// Get current Checkout Overview count
		int checkoutOverviewItemCount = checkoutStepTwoPage.getProductCount();

		log.info("Checkout Overview item count: {}", checkoutOverviewItemCount);

		// Compare counts
		Assert.assertEquals(checkoutOverviewItemCount, cartItemCount.intValue(),
				"Checkout Overview item count does not match Cart item count. " + "Cart count: " + cartItemCount
						+ ", Checkout Overview count: " + checkoutOverviewItemCount);

		log.info("Checkout Overview item count matches Cart item count. " + "Cart: {}, Checkout Overview: {}",
				cartItemCount, checkoutOverviewItemCount);
	}

	/**
	 * Verifies that the Checkout Overview subtotal matches the sum of the Cart item
	 * prices.
	 */
	public void verifySubtotalMatchesCartTotal() {

		log.info("Starting Checkout Overview subtotal validation");

		// Expected subtotal calculated from Cart item prices
		double expectedCartTotal = scenarioContext.getCartSubtotalDouble();

		log.info("Expected Cart total: ${}", expectedCartTotal);

		// Actual subtotal displayed on Checkout Overview
		String subtotalText = checkoutStepTwoAction.getSubtotal();

		// String subtotalText = checkoutStepTwoAction.getSubtotal();

		Assert.assertNotNull(subtotalText, "Checkout Overview subtotal should not be null");

		Assert.assertFalse(subtotalText.trim().isEmpty(), "Checkout Overview subtotal should not be empty");

		log.info("Checkout Overview subtotal text: {}", subtotalText);

		// Remove "$" and convert to double
		double actualSubtotal;

		try {

			actualSubtotal = Double.parseDouble(subtotalText.replace("$", "").trim());

		} catch (NumberFormatException e) {

			Assert.fail("Invalid Checkout Overview subtotal format: " + subtotalText);

			return;
		}

		log.info("Actual Checkout Overview subtotal: ${}", actualSubtotal);

		// Compare expected Cart total with actual Checkout subtotal
		Assert.assertEquals(actualSubtotal, expectedCartTotal, 0.01,
				"Checkout Overview subtotal does not match " + "the sum of Cart item prices");

		log.info("Checkout Overview subtotal matches Cart item prices successfully");
	}

	public void verifySubtotalMatchesCartTotalDouble() {

		log.info("Verifying Checkout Overview subtotal matches " + "sum of Cart item prices");

		double checkoutSubtotal = checkoutStepTwoAction.getSubtotalDouble();

		double expectedCartTotal = checkoutStepTwoAction.getCartItemsTotal();

		log.info("Checkout Overview subtotal: {}", checkoutSubtotal);

		log.info("Expected Cart item total: {}", expectedCartTotal);

		Assert.assertEquals(checkoutSubtotal, expectedCartTotal, 0.01, "Checkout Overview subtotal does not match "
				+ "the sum of Cart item prices. " + "Expected: " + expectedCartTotal + ", Actual: " + checkoutSubtotal);

		log.info("Checkout Overview subtotal matches Cart item total successfully");
	}

	public void verifySubtotalMatchesCartTotal_() {

		log.info("==================================================");
		log.info("Verifying Checkout Overview subtotal");
		log.info("==================================================");

		/*
		 * Checkout Overview:
		 *
		 * Item total: $55.97
		 *
		 * getSubtotal() converts this to:
		 *
		 * 55.97
		 */

		double actualSubtotal = checkoutStepTwoAction.getSubtotalDouble();

		/*
		 * Sum of all product prices displayed on Checkout Overview.
		 */
		double expectedSubtotal = checkoutStepTwoAction.getCartItemsTotal();

		log.info("Actual Checkout Overview subtotal: {}", actualSubtotal);

		log.info("Expected subtotal from Cart item prices: {}", expectedSubtotal);

		Assert.assertEquals(actualSubtotal, expectedSubtotal, 0.01, "Checkout Overview subtotal does not match "
				+ "the sum of Cart item prices. " + "Expected: " + expectedSubtotal + ", Actual: " + actualSubtotal);

		log.info("Checkout Overview subtotal matches " + "the sum of Cart item prices");

		log.info("==================================================");
	}

	public void verifySubtotalContainsValidDollarAmount() {

		log.info("Validating subtotal currency format");

		String subtotalText = checkoutStepTwoAction.getSubtotalText();

		Assert.assertNotNull(subtotalText, "Subtotal text should not be null");

		Assert.assertTrue(subtotalText.matches("^Item total:\\s*\\$\\d+\\.\\d{2}$"),
				"Invalid subtotal currency format: " + subtotalText);

		log.info("Valid subtotal currency format: {}", subtotalText);
	}

	public void verifyTaxContainsValidDollarAmount() {

		log.info("Validating tax currency format");

		String taxText = checkoutStepTwoAction.getTaxText();

		Assert.assertNotNull(taxText, "Tax text should not be null");

		Assert.assertTrue(taxText.matches("^Tax:\\s*\\$\\d+\\.\\d{2}$"), "Invalid tax currency format: " + taxText);

		log.info("Valid tax currency format: {}", taxText);
	}

	public void verifyTotalContainsValidDollarAmount() {

		log.info("Validating total currency format");

		String totalText = checkoutStepTwoAction.getTotalText();

		Assert.assertNotNull(totalText, "Total text should not be null");

		Assert.assertTrue(totalText.matches("^Total:\\s*\\$\\d+\\.\\d{2}$"),
				"Invalid total currency format: " + totalText);

		log.info("Valid total currency format: {}", totalText);
	}

	public void verifyTotalNotLessThanSubtotal() {

		log.info("Validating Total is not less than Subtotal");

		double subtotal = checkoutStepTwoAction.getSubtotalDouble();

		double total = checkoutStepTwoAction.getCartItemsTotal();

		Assert.assertTrue(total >= subtotal, "Total should not be less than Subtotal. " + "Subtotal: $"
				+ String.format("%.2f", subtotal) + ", Total: $" + String.format("%.2f", total));

		log.info("Total validation passed. Subtotal: ${}, Total: ${}", String.format("%.2f", subtotal),
				String.format("%.2f", total));
	}

	public void verifyTaxIsNotNegative() {

		log.info("Validating tax amount is not negative");

		double tax = checkoutStepTwoAction.getTaxDouble();

		Assert.assertTrue(tax >= 0.0, "Tax amount should not be negative. Actual Tax: $" + String.format("%.2f", tax));

		log.info("Tax validation passed. Tax amount: ${}", String.format("%.2f", tax));
	}

	public void verifyFinishButtonText(String expectedText) {

		log.info("Validating Finish button text. Expected: '{}'", expectedText);

		String actualText = checkoutStepTwoAction.getFinishButtonText();

		Assert.assertEquals(actualText, expectedText,
				"Incorrect Finish button text. Expected: '" + expectedText + "', Actual: '" + actualText + "'");

		log.info("Finish button text is correct: '{}'", actualText);
	}

	public void verifyCancelButtonText(String expectedText) {

		log.info("Validating Cancel button text. Expected: '{}'", expectedText);

		String actualText = checkoutStepTwoAction.getCancelButtonText();

		Assert.assertEquals(actualText, expectedText,
				"Incorrect Cancel button text. Expected: '" + expectedText + "', Actual: '" + actualText + "'");

		log.info("Cancel button text is correct: '{}'", actualText);
	}

	public void verifyPaymentInformationVisible() {

		log.info("Validating Payment Information visibility");

		boolean isVisible = checkoutStepTwoAction.isPaymentInformationVisible();

		Assert.assertTrue(isVisible, "Payment Information should be visible on Checkout Step Two page");

		log.info("Payment Information is visible: {}", isVisible);
	}

	public void verifyShippingInformationVisible() {

		boolean isVisible = checkoutStepTwoPage.isShippingInformationVisible();

		Assert.assertTrue(isVisible, "Shipping Information should be visible on Checkout Overview page");

		log.info("Shipping Information is visible on Checkout Overview page");
	}

	public void verifySubtotalVisible() {

		try {
			boolean isVisible = checkoutStepTwoPage.isSubtotalVisible();

			Assert.assertTrue(isVisible, "Subtotal should be visible on Checkout Overview page");

			log.info("Subtotal is visible on Checkout Overview page");

		} catch (AssertionError e) {
			log.error("Subtotal visibility validation failed", e);
			throw e;

		} catch (Exception e) {
			log.error("Error while verifying Subtotal visibility", e);
			Assert.fail("Unable to verify Subtotal visibility: " + e.getMessage());
		}
	}

	public void verifyTaxVisible() {

		try {
			boolean isVisible = checkoutStepTwoPage.isTaxVisible();

			Assert.assertTrue(isVisible, "Tax should be visible on Checkout Overview page");

			log.info("Tax is visible on Checkout Overview page");

		} catch (AssertionError e) {
			log.error("Tax visibility validation failed", e);
			throw e;

		} catch (Exception e) {
			log.error("Error while verifying Tax visibility", e);
			Assert.fail("Unable to verify Tax visibility: " + e.getMessage());
		}
	}

	public void verifyTotalVisible() {

		try {
			boolean isVisible = checkoutStepTwoPage.isTotalVisible();

			Assert.assertTrue(isVisible, "Total should be visible on Checkout Overview page");

			log.info("Total is visible on Checkout Overview page");

		} catch (AssertionError e) {
			log.error("Total visibility validation failed", e);
			throw e;

		} catch (Exception e) {
			log.error("Error while verifying Total visibility", e);
			Assert.fail("Unable to verify Total visibility: " + e.getMessage());
		}
	}

}