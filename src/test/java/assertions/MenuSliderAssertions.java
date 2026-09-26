
package assertions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import pages.MenuSliderPage;
import utils.WaitUtils;

public class MenuSliderAssertions {

	private static final Logger log = LoggerFactory.getLogger(MenuSliderAssertions.class);

	private final MenuSliderPage menuSliderPage;

	// =========================================================
	// EXPECTED PRODUCTS
	// =========================================================

	private static final Set<String> EXPECTED_PRODUCTS = Set.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
			"Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack",
			"Sauce Labs Fleece Jacket");

	// =========================================================
	// CONSTRUCTORS
	// =========================================================

	public MenuSliderAssertions() {

		this.menuSliderPage = new MenuSliderPage();

		log.debug("MenuSliderAssertions initialized");
	}

	public MenuSliderAssertions(MenuSliderPage menuSliderPage) {

		if (menuSliderPage == null) {
			throw new IllegalArgumentException("MenuSliderPage must not be null");
		}

		this.menuSliderPage = menuSliderPage;

		log.debug("MenuSliderAssertions initialized with MenuSliderPage");
	}

	// ==========================================================
	//
	// ==========================================================

	// =========================================================
	// SLIDER DISPLAY ASSERTIONS
	// =========================================================

	public void verifySliderDisplayed() {

		log.info("Verifying dynamic product slider is displayed");

		Assert.assertTrue(menuSliderPage.isSliderDisplayed(), "Dynamic product slider should be displayed");
	}

	public void verifyProductCardDisplayed() {

		log.info("Verifying dynamic product card is displayed");

		Assert.assertTrue(menuSliderPage.isProductCardDisplayed(), "Dynamic product card should be displayed");
	}

	public void verifyProductImageDisplayed() {

		log.info("Verifying product image is displayed");

		Assert.assertTrue(menuSliderPage.isProductImageDisplayed(), "Product image should be displayed");
	}

	public void verifyProductNameDisplayed() {

		log.info("Verifying product name is displayed");

		Assert.assertTrue(menuSliderPage.isProductNameDisplayed(), "Product name should be displayed");
	}

	public void verifyProductPriceDisplayed() {

		log.info("Verifying product price is displayed");

		Assert.assertTrue(menuSliderPage.isProductPriceDisplayed(), "Product price should be displayed");
	}

	// =========================================================
	// NAVIGATION DOT ASSERTIONS
	// =========================================================

	public void verifyNavigationDotCount(int expectedCount) {

		log.info("Verifying navigation dot count. Expected: {}", expectedCount);

		int actualCount = menuSliderPage.getNavigationDotCount();

		Assert.assertEquals(actualCount, expectedCount, "Incorrect navigation dot count");
	}

	public void verifyExactlyOneActiveDot() {

		log.info("Verifying exactly one slider dot is active");

		List<WebElement> dots = menuSliderPage.getNavigationDots();

		int activeCount = 0;

		for (WebElement dot : dots) {

			String ariaCurrent = dot.getAttribute("aria-current");

			if ("true".equalsIgnoreCase(ariaCurrent)) {
				activeCount++;
			}
		}
		Assert.assertEquals(activeCount, 1, "Exactly one navigation dot should have aria-current='true'");
	}

	public void verifyProductNameMatchesProductData(String expectedProductName) {

		log.info("Verifying displayed product name against expected product: {}", expectedProductName);

		String actualProductName = menuSliderPage.getProductName();

		log.info("Expected product name: {}", expectedProductName);
		log.info("Actual displayed product name: {}", actualProductName);

		Assert.assertEquals(actualProductName, expectedProductName,
				"Displayed product name does not match product data");

		log.info("Displayed product name successfully matched product data: {}", expectedProductName);
	}

	public void verifyPriceMatchesProductData(String expectedPrice) {

		String actualPrice = menuSliderPage.getProductPrice();

		log.info("Expected price: {}", expectedPrice);
		log.info("Actual displayed price: {}", actualPrice);

		Assert.assertEquals(actualPrice, expectedPrice, "Displayed price does not match product data");

		log.info("Displayed price successfully matched: {}", expectedPrice);
	}

	public void verifySliderImageBelongsToProduct(String productName) {

		log.info("Verifying slider image belongs to product: {}", productName);

		boolean imageMatches = menuSliderPage.isSliderImageForProduct(productName);

		Assert.assertTrue(imageMatches, "Slider image does not belong to displayed product: " + productName);

		log.info("Slider image successfully verified for product: {}", productName);
	}

	public void verifyActiveDotAriaCurrent() {

		log.info("Verifying active dot has aria-current='true'");

		WebElement activeDot = menuSliderPage.getActiveDot();

		Assert.assertNotNull(activeDot, "Active navigation dot should be present");

		Assert.assertEquals(activeDot.getAttribute("aria-current"), "true",
				"Active navigation dot should have aria-current='true'");
	}

	public void verifyInactiveDotsAriaCurrent() {

		log.info("Verifying inactive dots have aria-current='false'");

		List<WebElement> dots = menuSliderPage.getNavigationDots();

		for (WebElement dot : dots) {

			String ariaCurrent = dot.getAttribute("aria-current");

			if (!"true".equalsIgnoreCase(ariaCurrent)) {

				Assert.assertEquals(ariaCurrent, "false", "Inactive navigation dots should have aria-current='false'");
			}
		}
	}

	public void verifyActiveDotAriaCurrent(String expectedValue) {

		if (expectedValue == null || expectedValue.isBlank()) {
			throw new IllegalArgumentException("Expected aria-current value must not be null or blank");
		}

		log.debug("Verifying active slider dot aria-current value. Expected: {}", expectedValue);

		WebElement activeDot = menuSliderPage.getActiveDot();

		Assert.assertNotNull(activeDot, "Active slider navigation dot should not be null");

		String actualValue = activeDot.getAttribute("aria-current");

		log.debug("Active slider dot aria-current. Expected: {}, Actual: {}", expectedValue, actualValue);

		Assert.assertEquals(actualValue, expectedValue, "Incorrect aria-current value for active slider dot");

		log.info("Active slider dot aria-current verified successfully: {}", actualValue);
	}

	public void verifyInactiveDotsAriaCurrent(String expectedValue) {

		if (expectedValue == null || expectedValue.isBlank()) {
			throw new IllegalArgumentException("Expected aria-current value must not be null or blank");
		}

		log.debug("Verifying inactive slider dots aria-current value. Expected: {}", expectedValue);

		List<WebElement> navigationDots = menuSliderPage.getNavigationDots();

		Assert.assertFalse(navigationDots.isEmpty(), "Slider navigation dots should not be empty");

		int inactiveDotCount = 0;

		for (int i = 0; i < navigationDots.size(); i++) {

			WebElement dot = navigationDots.get(i);

			String ariaCurrent = dot.getAttribute("aria-current");

			if (!"true".equalsIgnoreCase(ariaCurrent)) {

				inactiveDotCount++;

				log.debug("Inactive dot index: {} | Expected aria-current: {} | Actual: {}", i, expectedValue,
						ariaCurrent);

				Assert.assertEquals(ariaCurrent, expectedValue, "Incorrect aria-current value for inactive slider dot "
						+ "at index: " + i + ". Expected: " + expectedValue + ", Actual: " + ariaCurrent);
			}
		}

		Assert.assertTrue(inactiveDotCount > 0, "At least one inactive slider dot should exist");

		log.info("All {} inactive slider dots have aria-current='{}'", inactiveDotCount, expectedValue);
	}

	public void verifyPriceForProduct(String productName) {

		if (productName == null || productName.isBlank()) {
			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		log.debug("Verifying price for slider product: {}", productName);

		String expectedPrice;

		switch (productName) {
		case "Sauce Labs Bike Light":
			expectedPrice = "$9.99";
			break;

		case "Sauce Labs Bolt T-Shirt":
			expectedPrice = "$15.99";
			break;

		case "Sauce Labs Onesie":
			expectedPrice = "$7.99";
			break;

		case "Test.allTheThings() T-Shirt (Red)":
			expectedPrice = "$15.99";
			break;

		case "Sauce Labs Backpack":
			expectedPrice = "$29.99";
			break;

		case "Sauce Labs Fleece Jacket":
			expectedPrice = "$49.99";
			break;

		default:
			throw new IllegalArgumentException("Unknown slider product: " + productName);
		}

		String actualProductName = menuSliderPage.getProductName();
		String actualPrice = menuSliderPage.getProductPrice();

		Assert.assertEquals(actualProductName, productName, "Incorrect product displayed in slider. " + "Expected: "
				+ productName + ", Actual: " + actualProductName);

		Assert.assertEquals(actualPrice, expectedPrice, "Incorrect price for product: " + productName + ". Expected: "
				+ expectedPrice + ", Actual: " + actualPrice);

		log.info("Price verified successfully for product: {}. Price: {}", productName, actualPrice);
	}

	public void verifyPriceMatchesProduct() {

		log.debug("Verifying that displayed slider product price matches product");

		String productName = menuSliderPage.getProductName();
		String actualPrice = menuSliderPage.getProductPrice();

		if (productName == null || productName.isBlank()) {
			Assert.fail("Displayed slider product name should not be null or blank");
		}

		if (actualPrice == null || actualPrice.isBlank()) {
			Assert.fail("Displayed price should not be null or blank for product: " + productName);
		}

		String expectedPrice;

		switch (productName) {

		case "Sauce Labs Bike Light":
			expectedPrice = "$9.99";
			break;

		case "Sauce Labs Bolt T-Shirt":
			expectedPrice = "$15.99";
			break;

		case "Sauce Labs Onesie":
			expectedPrice = "$7.99";
			break;

		case "Test.allTheThings() T-Shirt (Red)":
			expectedPrice = "$15.99";
			break;

		case "Sauce Labs Backpack":
			expectedPrice = "$29.99";
			break;

		case "Sauce Labs Fleece Jacket":
			expectedPrice = "$49.99";
			break;

		default:
			Assert.fail("Unknown product displayed in slider: " + productName);
			return;
		}

		log.debug("Product: {} | Expected price: {} | Actual price: {}", productName, expectedPrice, actualPrice);

		Assert.assertEquals(actualPrice, expectedPrice, "Price does not match product. " + "Product: " + productName
				+ ", Expected price: " + expectedPrice + ", Actual price: " + actualPrice);

		log.info("Price matches product successfully. Product: {}, Price: {}", productName, actualPrice);
	}

	public void verifyNavigationDotDisplayed(int index) {

		log.debug("Verifying navigation dot is displayed at index: {}", index);

		List<WebElement> navigationDots = menuSliderPage.getNavigationDots();

		Assert.assertFalse(navigationDots.isEmpty(), "Slider navigation dots should not be empty");

		Assert.assertTrue(index >= 0 && index < navigationDots.size(),
				"Invalid navigation dot index: " + index + ". Valid range: 0 to " + (navigationDots.size() - 1));

		WebElement navigationDot = navigationDots.get(index);

		boolean displayed = navigationDot.isDisplayed();

		log.debug("Navigation dot index: {} | Displayed: {}", index, displayed);

		Assert.assertTrue(displayed, "Navigation dot at index " + index + " should be displayed");

		log.info("Navigation dot at index {} is displayed successfully", index);
	}

	public void verifyProductDotAriaCurrent(String productName, String expectedValue) {

		if (productName == null || productName.isBlank()) {
			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		if (expectedValue == null || expectedValue.isBlank()) {
			throw new IllegalArgumentException("Expected aria-current value must not be null or blank");
		}

		log.debug("Verifying aria-current for product: {}. Expected: {}", productName, expectedValue);

		String actualValue = menuSliderPage.getProductDotAriaCurrent(productName);

		log.debug("Product: {} | Expected: {} | Actual: {}", productName, expectedValue, actualValue);

		Assert.assertEquals(actualValue, expectedValue, "Incorrect aria-current for product: " + productName
				+ ". Expected: " + expectedValue + ", Actual: " + actualValue);

		log.info("Verified aria-current for product: {} = {}", productName, actualValue);
	}

	public void verifyOnlyOneDotHasAriaCurrent(String expectedValue) {

		if (expectedValue == null || expectedValue.isBlank()) {
			throw new IllegalArgumentException("Expected aria-current value must not be null or blank");
		}

		log.debug("Verifying that exactly one slider dot has aria-current='{}'", expectedValue);

		List<WebElement> navigationDots = menuSliderPage.getNavigationDots();

		Assert.assertFalse(navigationDots.isEmpty(), "Slider navigation dots should not be empty");

		int matchingDotCount = 0;
		int matchingDotIndex = -1;

		for (int i = 0; i < navigationDots.size(); i++) {

			WebElement dot = navigationDots.get(i);

			String ariaCurrent = dot.getAttribute("aria-current");

			log.debug("Dot index: {} | aria-current: {}", i, ariaCurrent);

			if (expectedValue.equalsIgnoreCase(ariaCurrent)) {
				matchingDotCount++;
				matchingDotIndex = i;
			}
		}

		Assert.assertEquals(matchingDotCount, 1, "Expected exactly one slider dot to have aria-current='"
				+ expectedValue + "', but found " + matchingDotCount);

		log.info("Exactly one slider dot has aria-current='{}'. Index: {}", expectedValue, matchingDotIndex);
	}

	// =========================================================
	// PRODUCT INFORMATION ASSERTIONS
	// =========================================================

	public void verifyProductDisplayed(String expectedProductName) {

		String actualProductName = menuSliderPage.getProductName();

		log.info("Expected product: {}", expectedProductName);
		log.info("Actual product: {}", actualProductName);

		Assert.assertEquals(actualProductName, expectedProductName, "Incorrect product displayed");
	}

	public void verifyProductName(String expectedProductName) {

		if (expectedProductName == null || expectedProductName.isBlank()) {
			throw new IllegalArgumentException("Expected product name must not be null or blank");
		}

		String actualProductName = menuSliderPage.getProductName();

		log.info("Verifying product name. Expected: {}, Actual: {}", expectedProductName, actualProductName);

		Assert.assertEquals(actualProductName, expectedProductName, "Incorrect slider product name");
	}

	public void verifyProductPrice(String expectedPrice) {

		if (expectedPrice == null || expectedPrice.isBlank()) {
			throw new IllegalArgumentException("Expected product price must not be null or blank");
		}

		String actualPrice = menuSliderPage.getProductPrice();

		log.info("Verifying product price. Expected: {}, Actual: {}", expectedPrice, actualPrice);

		Assert.assertEquals(actualPrice, expectedPrice, "Incorrect slider product price");
	}

	public void verifyProductInformationIsConsistent() {

		log.info("Verifying product name, price and image information");

		String productName = menuSliderPage.getProductName();
		String productPrice = menuSliderPage.getProductPrice();
		String imageSrc = menuSliderPage.getImageSrc();
		String imageAlt = menuSliderPage.getImageAlt();

		Assert.assertFalse(productName == null || productName.isBlank(), "Product name should not be empty");

		Assert.assertFalse(productPrice == null || productPrice.isBlank(), "Product price should not be empty");

		Assert.assertFalse(imageSrc == null || imageSrc.isBlank(), "Product image src should not be empty");

		Assert.assertFalse(imageAlt == null || imageAlt.isBlank(), "Product image alt should not be empty");

		Assert.assertEquals(imageAlt, productName, "Image alt text should match product name");
	}

	// =========================================================
	// PRODUCT / DOT SYNCHRONIZATION
	// =========================================================

	public void verifyActiveDotMatchesProduct() {

		log.info("Verifying active navigation dot matches displayed product");

		WebElement activeDot = menuSliderPage.getActiveDot();

		Assert.assertNotNull(activeDot, "Active navigation dot should be present");

		String dotProductName = activeDot.getAttribute("aria-label");
		String displayedProductName = menuSliderPage.getProductName();

		String expectedLabel = "Show " + displayedProductName;

		Assert.assertEquals(dotProductName, expectedLabel, "Active dot should correspond to displayed product");
	}

	public void verifyProductDotAriaCurrent(String productName) {

		if (productName == null || productName.isBlank()) {
			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		WebElement dot = menuSliderPage.getProductDot(productName);

		Assert.assertNotNull(dot, "Slider dot should exist for product: " + productName);

		String ariaCurrent = dot.getAttribute("aria-current");

		Assert.assertEquals(ariaCurrent, "true", "Slider dot should be active for product: " + productName);
	}

	public void verifyNavigationDotDisplayed(String productName) {

		if (productName == null || productName.isBlank()) {
			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		log.info("Verifying navigation dot is displayed for product: {}", productName);

		WebElement dot = menuSliderPage.getProductDot(productName);

		Assert.assertNotNull(dot, "Navigation dot should exist for product: " + productName);

		Assert.assertTrue(dot.isDisplayed(), "Navigation dot should be displayed for product: " + productName);
	}

	// =========================================================
	// PRODUCT VALIDATION
	// =========================================================

	public void verifyValidDisplayedProduct() {

		String productName = menuSliderPage.getProductName();

		log.info("Validating currently displayed product: {}", productName);

		Assert.assertTrue(EXPECTED_PRODUCTS.contains(productName),
				"Unexpected product displayed in slider: " + productName);
	}

	public void verifyAllProductsHaveValidInformation() {

		log.info("Verifying all expected products have valid slider information");

		List<WebElement> dots = menuSliderPage.getNavigationDots();

		Assert.assertEquals(dots.size(), EXPECTED_PRODUCTS.size(),
				"Navigation dot count should match expected product count");

		Set<String> productsFound = new HashSet<>();

		for (WebElement dot : dots) {

			String ariaLabel = dot.getAttribute("aria-label");

			Assert.assertNotNull(ariaLabel, "Every slider dot should have an aria-label");

			Assert.assertTrue(ariaLabel.startsWith("Show "), "Slider dot aria-label should start with 'Show '");

			String productName = ariaLabel.replaceFirst("^Show ", "");

			Assert.assertTrue(EXPECTED_PRODUCTS.contains(productName),
					"Unexpected product found in slider: " + productName);

			productsFound.add(productName);
		}

		Assert.assertEquals(productsFound, EXPECTED_PRODUCTS, "Slider should contain all expected products");
	}

	// =========================================================
	// PRICE ASSERTIONS
	// =========================================================

	public void verifyValidPriceFormat() {

		String price = menuSliderPage.getProductPrice();

		log.info("Verifying price format: {}", price);

		Assert.assertNotNull(price, "Product price should not be null");

		Assert.assertTrue(price.matches("^\\$\\d+\\.\\d{2}$"), "Invalid product price format: " + price);
	}

	public void verifyPriceHasTwoDecimalPlaces() {

		String price = menuSliderPage.getProductPrice();

		Assert.assertNotNull(price, "Product price should not be null");

		Assert.assertTrue(price.matches("^\\$\\d+\\.\\d{2}$"),
				"Product price should contain exactly two decimal places: " + price);
	}

	public void verifyPriceMatchesProduct(String expectedProductName, String expectedPrice) {

		if (expectedProductName == null || expectedProductName.isBlank()) {

			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		if (expectedPrice == null || expectedPrice.isBlank()) {

			throw new IllegalArgumentException("Expected price must not be null or blank");
		}

		String actualProductName = menuSliderPage.getProductName();
		String actualPrice = menuSliderPage.getProductPrice();

		Assert.assertEquals(actualProductName, expectedProductName, "Unexpected product displayed");

		Assert.assertEquals(actualPrice, expectedPrice, "Incorrect price for product: " + expectedProductName);
	}

	// =========================================================
	// IMAGE ASSERTIONS
	// =========================================================

	public void verifyImageLoaded() {

		log.info("Verifying slider product image is loaded");

		WebElement image = menuSliderPage.getProductImage();

		Assert.assertNotNull(image, "Product image should be present");

		Assert.assertTrue(image.isDisplayed(), "Product image should be displayed");

		String src = image.getAttribute("src");

		Assert.assertNotNull(src, "Product image src should not be null");

		Assert.assertFalse(src.isBlank(), "Product image src should not be blank");

		Assert.assertTrue(menuSliderPage.isImageLoaded(), "Product image should be completely loaded");
	}

	public void verifyMeaningfulImageAltText() {

		String productName = menuSliderPage.getProductName();
		String imageAlt = menuSliderPage.getImageAlt();

		log.info("Verifying image alt text. Product: {}, Alt: {}", productName, imageAlt);

		Assert.assertNotNull(imageAlt, "Product image alt text should not be null");

		Assert.assertFalse(imageAlt.isBlank(), "Product image alt text should not be blank");

		Assert.assertEquals(imageAlt, productName, "Image alt text should match the displayed product name");
	}

	public void verifyImageBelongsToProduct(String expectedProductName) {

		if (expectedProductName == null || expectedProductName.isBlank()) {

			throw new IllegalArgumentException("Expected product name must not be null or blank");
		}

		String actualProductName = menuSliderPage.getProductName();
		String imageAlt = menuSliderPage.getImageAlt();

		Assert.assertEquals(actualProductName, expectedProductName, "Unexpected product displayed");

		Assert.assertEquals(imageAlt, expectedProductName, "Product image does not belong to expected product");
	}

	// =========================================================
	// ACCESSIBILITY ASSERTIONS
	// =========================================================

	public void verifyEveryDotHasAriaLabel() {

		log.info("Verifying every slider dot has aria-label");

		List<WebElement> dots = menuSliderPage.getNavigationDots();

		for (WebElement dot : dots) {

			String ariaLabel = dot.getAttribute("aria-label");

			Assert.assertNotNull(ariaLabel, "Every slider dot should have aria-label");

			Assert.assertFalse(ariaLabel.isBlank(), "Slider dot aria-label should not be blank");
		}
	}

	public void verifyDotAriaLabels() {

		log.info("Verifying slider dot aria-label values");

		List<WebElement> dots = menuSliderPage.getNavigationDots();

		for (WebElement dot : dots) {

			String ariaLabel = dot.getAttribute("aria-label");

			Assert.assertTrue(ariaLabel.startsWith("Show "), "Invalid slider dot aria-label: " + ariaLabel);

			String productName = ariaLabel.substring(5);

			Assert.assertTrue(EXPECTED_PRODUCTS.contains(productName),
					"Slider dot aria-label contains unknown product: " + ariaLabel);
		}
	}

	public void verifyOnlyOneDotHasAriaCurrent() {

		List<WebElement> dots = menuSliderPage.getNavigationDots();

		int activeDots = 0;

		for (WebElement dot : dots) {

			if ("true".equalsIgnoreCase(dot.getAttribute("aria-current"))) {

				activeDots++;
			}
		}

		Assert.assertEquals(activeDots, 1, "Only one slider dot should have aria-current='true'");
	}

	// =========================================================
	// SLIDER STATE ASSERTIONS
	// =========================================================

	public void verifyDisplayedProductChanged(String previousProductName) {

		if (previousProductName == null || previousProductName.isBlank()) {

			throw new IllegalArgumentException("Previous product name must not be null or blank");
		}

		String currentProductName = menuSliderPage.getProductName();

		log.info("Verifying slider product changed. Previous: {}, Current: {}", previousProductName,
				currentProductName);

		Assert.assertNotEquals(currentProductName, previousProductName, "Slider product should change after rotation");
	}

	public void verifyDisplayedProductIs(String expectedProductName) {

		verifyProductName(expectedProductName);
	}

	// =========================================================
	// PRODUCT ROTATION ASSERTIONS
	// =========================================================

	public void verifyProductRotationOccurred(String initialProductName, String currentProductName) {

		if (initialProductName == null || initialProductName.isBlank()) {

			throw new IllegalArgumentException("Initial product name must not be null or blank");
		}

		if (currentProductName == null || currentProductName.isBlank()) {

			throw new IllegalArgumentException("Current product name must not be null or blank");
		}

		log.info("Verifying product rotation. Initial: {}, Current: {}", initialProductName, currentProductName);

		Assert.assertNotEquals(currentProductName, initialProductName,
				"Slider product should rotate to another product");
	}

	// =========================================================
	// GETTER
	// =========================================================

	public MenuSliderPage getMenuSliderPage() {

		return menuSliderPage;
	}
}