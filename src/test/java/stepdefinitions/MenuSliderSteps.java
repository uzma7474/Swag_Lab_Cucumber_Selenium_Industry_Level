package stepdefinitions;

import actions.MenuSliderAction;
import actions.MenuSliderAction;
import assertions.MenuSliderAssertions;
import context.ScenarioContext;
import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MenuSliderPage;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuSliderSteps {

	private static final Logger log = LoggerFactory.getLogger(MenuSliderSteps.class);

	private final MenuSliderAssertions menuSliderAssertions;

	private final MenuSliderAction menuSliderAction;

	private final ScenarioContext context;

	private String initialProductName;

	private String initialProductPrice;

	private String initialImageSrc;

	private String initialImageAlt;

	private int initialActiveDotIndex;

	private int loadedProductCount;

	private MenuSliderPage menuSliderPage;

	private final List<String> displayedProducts = new ArrayList<>();

	private final Set<String> uniqueProducts = new HashSet<>();

	public MenuSliderSteps(ScenarioContext context) {

		if (context == null) {
			throw new IllegalArgumentException("ScenarioContext must not be null");
		}

		this.context = context;

		this.menuSliderAssertions = new MenuSliderAssertions(context.getPageObjectManager().getMenuSliderPage());

		this.menuSliderAction = new MenuSliderAction(context.getPageObjectManager());

		this.menuSliderPage = context.getPageObjectManager().getMenuSliderPage();

		log.debug("MenuSliderSteps initialized");
	}

	// ============================================================
	// SLIDER INITIALIZATION
	// ============================================================

	@Then("the dynamic product slider should be displayed")
	public void theDynamicProductSliderShouldBeDisplayed() {

		log.info("Verifying dynamic product slider is displayed");

		menuSliderAssertions.verifySliderDisplayed();
	}

	@Then("the dynamic product card should be displayed")
	public void theDynamicProductCardShouldBeDisplayed() {

		log.info("Verifying dynamic product card is displayed");

		menuSliderAssertions.verifyProductCardDisplayed();
	}

	@Then("the slider product image should be displayed")
	public void theSliderProductImageShouldBeDisplayed() {

		log.info("Verifying slider product image is displayed");

		menuSliderAssertions.verifyProductImageDisplayed();
	}

	@Then("the slider product name should be displayed")
	public void theSliderProductNameShouldBeDisplayed() {

		log.info("Verifying slider product name is displayed");

		menuSliderAssertions.verifyProductNameDisplayed();
	}

	@Then("the slider product price should be displayed")
	public void theSliderProductPriceShouldBeDisplayed() {

		log.info("Verifying slider product price is displayed");

		menuSliderAssertions.verifyProductPriceDisplayed();
	}

	@Then("the slider should contain {int} navigation dots")
	public void theSliderShouldContainNavigationDots(int expectedCount) {

		log.info("Verifying slider navigation dot count. Expected: {}", expectedCount);

		menuSliderAssertions.verifyNavigationDotCount(expectedCount);
	}

	@Then("exactly one slider navigation dot should be active")
	public void exactlyOneSliderNavigationDotShouldBeActive() {

		log.info("Verifying exactly one slider navigation dot is active");

		menuSliderAssertions.verifyExactlyOneActiveDot();
	}

	@Then("the active slider dot should have aria-current {string}")
	public void theActiveSliderDotShouldHaveAriaCurrent(String expectedValue) {

		log.info("Verifying active slider dot aria-current value: {}", expectedValue);

		menuSliderAssertions.verifyActiveDotAriaCurrent(expectedValue);
	}

	@Then("all inactive slider dots should have aria-current {string}")
	public void allInactiveSliderDotsShouldHaveAriaCurrent(String expectedValue) {

		log.info("Verifying inactive slider dots aria-current value: {}", expectedValue);

		menuSliderAssertions.verifyInactiveDotsAriaCurrent(expectedValue);
	}

	// ============================================================
	// RECORD CURRENT SLIDER STATE
	// ============================================================

	@Given("the current slider product is recorded")
	public void theCurrentSliderProductIsRecorded() {

		initialProductName = menuSliderAction.getProductName();

		log.info("Initial slider product recorded: {}", initialProductName);
	}

	@Given("the initial slider product is recorded")
	public void theInitialSliderProductIsRecorded() {

		log.info("Recording initial slider product");

		String initialProductName = menuSliderAction.getProductName();

		context.set("initialSliderProduct", initialProductName);

		log.info("Initial slider product recorded: {}", initialProductName);
	}

//	@When("the current slider product is captured")
//	public void theCurrentSliderProductIsCaptured() {
//
//		log.info("Capturing current slider product");
//
//		String currentProductName = menuSliderAction.getProductName();
//
//		context.set("currentSliderProduct", currentProductName);
//
//		log.info("Current slider product captured: {}", currentProductName);
//	}

	@When("the current slider product is captured")
	public void theCurrentSliderProductIsCaptured() {

		String currentProductName = menuSliderAction.getProductName();

		String currentProductPrice = menuSliderAction.getProductPrice();

		context.set("currentSliderProduct", currentProductName);
		context.set("currentSliderProductPrice", currentProductPrice);

		log.info("Current product: {}", currentProductName);
		log.info("Current product price: {}", currentProductPrice);
	}

	@When("the slider changes to another product")
	public void theSliderChangesToAnotherProduct() {

		String initialProductName = context.get("initialSliderProduct", String.class);

		if (initialProductName == null) {
			throw new IllegalStateException("Initial slider product was not found in ScenarioContext");
		}

		log.info("Initial slider product: {}", initialProductName);

		menuSliderAction.waitForProductToChange(initialProductName);

		String currentProductName = menuSliderAction.getProductName();

		context.set("currentSliderProduct", currentProductName);

		String currentPrice = menuSliderAction.getProductPrice();

		context.set("currentSliderProductPrice", currentPrice);

		log.info("Current slider product: {}", currentProductName);

		log.info("Current slider price: {}", currentPrice);
	}

	@Then("the slider image should belong to the displayed product")
	public void theSliderImageShouldBelongToTheDisplayedProduct() {

		String currentProductName = context.get("currentSliderProduct", String.class);

		log.info("Current slider product: {}", currentProductName);

		if (currentProductName == null) {
			throw new IllegalStateException("Current slider product was not stored in ScenarioContext");
		}

		menuSliderAssertions.verifySliderImageBelongsToProduct(currentProductName);
	}

	@Then("the displayed product name should match the product data")
	public void theDisplayedProductNameShouldMatchTheProductData() {

		log.info("Verifying displayed product name against product data");

		String expectedProductName = context.get("currentSliderProduct", String.class);

		if (expectedProductName == null) {
			throw new IllegalStateException(
					"Product data was not found in ScenarioContext for key: currentSliderProduct");
		}

		log.info("Expected product name from product data: {}", expectedProductName);

		menuSliderAssertions.verifyProductNameMatchesProductData(expectedProductName);
	}

	@Then("the displayed price should match the product data")
	public void theDisplayedPriceShouldMatchTheProductData() {

		log.info("Verifying displayed price against product data");

		String expectedPrice = context.get("currentSliderProductPrice", String.class);

		if (expectedPrice == null) {
			throw new IllegalStateException(
					"Product price was not found in ScenarioContext " + "for key: currentSliderProductPrice");
		}

		log.info("Expected product price: {}", expectedPrice);

		menuSliderAssertions.verifyPriceMatchesProductData(expectedPrice);
	}

	@Given("the current slider product name is recorded")
	public void theCurrentSliderProductNameIsRecorded() {

		initialProductName = menuSliderAction.getProductName();

		log.info("Initial slider product name recorded: {}", initialProductName);
	}

	@Given("the current slider product price is recorded")
	public void theCurrentSliderProductPriceIsRecorded() {

		initialProductPrice = menuSliderAction.getProductPrice();

		log.info("Initial slider product price recorded: {}", initialProductPrice);
	}

	@Given("the current slider image is recorded")
	public void theCurrentSliderImageIsRecorded() {

		initialImageSrc = menuSliderAction.getImageSrc();

		log.info("Initial slider image source recorded: {}", initialImageSrc);
	}

	@Given("the current slider product information is recorded")
	public void theCurrentSliderProductInformationIsRecorded() {

		initialProductName = menuSliderAction.getProductName();
		initialProductPrice = menuSliderAction.getProductPrice();
		initialImageSrc = menuSliderAction.getImageSrc();
		initialImageAlt = menuSliderAction.getImageAlt();
		initialActiveDotIndex = menuSliderAction.getActiveDotIndex();

		log.info("Initial slider information recorded. Product: {}, Price: {}, Image: {}, Alt: {}, Active Dot: {}",
				initialProductName, initialProductPrice, initialImageSrc, initialImageAlt, initialActiveDotIndex);
	}

	@Given("the current active slider dot is recorded")
	public void theCurrentActiveSliderDotIsRecorded() {

		initialActiveDotIndex = menuSliderAction.getActiveDotIndex();

		log.info("Initial active slider dot recorded: {}", initialActiveDotIndex);
	}

	@Given("the slider is displaying {string}")
	public void theSliderIsDisplaying_(String productName) {

		log.info("Setting slider to display product: {}", productName);

		menuSliderAction.clickSliderDotForProduct(productName);

		String actualProductName = menuSliderAction.getProductName();

		Assert.assertEquals(actualProductName, productName, "Slider is not displaying expected product. " + "Expected: "
				+ productName + ", Actual: " + actualProductName);

		log.info("Slider is displaying expected product: {}", actualProductName);
	}

	@Then("{string} should be displayed")
	public void shouldBeDisplayed(String productName) {

		log.info("Verifying slider product is displayed: {}", productName);

		String actualProductName = menuSliderAction.getProductName();

		Assert.assertEquals(actualProductName, productName,
				"Expected product to be displayed: " + productName + " but actual product was: " + actualProductName);

		log.info("Verified product is displayed: {}", actualProductName);
	}

	@Then("the {string} dot should have aria-current {string}")
	public void theDotShouldHaveAriaCurrent(String productName, String expectedValue) {

		log.info("Verifying aria-current for slider dot: {}", productName);

		log.info("Expected aria-current value: {}", expectedValue);

		menuSliderAssertions.verifyProductDotAriaCurrent(productName, expectedValue);
	}

	@Given("the {string} dot is active")
	public void theDotIsActive(String productName) {

		log.info("Setting slider dot active for product: {}", productName);

		menuSliderAction.clickSliderDotForProduct(productName);

		menuSliderAssertions.verifyProductDotAriaCurrent(productName, "true");

		log.info("Verified '{}' slider dot is active", productName);
	}

	@When("the user clicks different slider dots")
	public void theUserClicksDifferentSliderDots() {

		String[] products = { "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie",
				"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack", "Sauce Labs Fleece Jacket" };

		for (String productName : products) {

			log.info("Clicking slider dot for product: {}", productName);

			menuSliderAction.clickSliderDotForProduct(productName);

			menuSliderAssertions.verifyProductDotAriaCurrent(productName, "true");

			String actualProduct = menuSliderAction.getProductName();

			Assert.assertEquals(actualProduct, productName, "Incorrect product displayed after clicking slider dot. "
					+ "Expected: " + productName + ", Actual: " + actualProduct);

			log.info("Successfully verified slider product: {}", productName);
		}
	}

	@When("the slider automatically rotates")
	public void theSliderAutomaticallyRotates() {

		log.info("Verifying that the slider automatically rotates");

		// Capture the product before automatic rotation
		String initialProductName = menuSliderAction.getProductName();

		if (initialProductName == null || initialProductName.trim().isEmpty()) {

			throw new IllegalStateException("Initial slider product could not be determined");
		}

		log.info("Initial slider product before rotation: {}", initialProductName);

		// Store initial product for later validation
		context.set("initialSliderProduct", initialProductName);

		// Wait until the slider automatically changes
		menuSliderAction.waitForProductToChange(initialProductName);

		// Capture the product after automatic rotation
		String rotatedProductName = menuSliderAction.getProductName();

		String rotatedProductPrice = menuSliderAction.getProductPrice();

		log.info("Slider automatically rotated to product: {}", rotatedProductName);

		log.info("Rotated product price: {}", rotatedProductPrice);

		// Store rotated product details
		context.set("currentSliderProduct", rotatedProductName);

		context.set("currentSliderProductPrice", rotatedProductPrice);

		// Final safety check
		Assert.assertNotEquals(rotatedProductName, initialProductName,
				"Slider did not automatically rotate. " + "Product remained: " + initialProductName);

		log.info("Slider automatically rotated successfully from '{}' to '{}'", initialProductName, rotatedProductName);
	}

	// ============================================================
	// AUTOMATIC ROTATION
	// ============================================================

	@When("the user waits for the slider rotation interval")
	public void theUserWaitsForTheSliderRotationInterval() {

		log.info("Waiting for the slider to automatically rotate");

		String previousProduct = context.get("previousSliderProduct", String.class);

		/*
		 * If the previous product was not stored before this step, capture the
		 * currently displayed product as the baseline.
		 */
		if (previousProduct == null || previousProduct.trim().isEmpty()) {

			log.info("Previous slider product was not found in ScenarioContext. "
					+ "Capturing the currently displayed product.");

			previousProduct = menuSliderAction.getProductName();

			if (previousProduct == null || previousProduct.trim().isEmpty()) {

				throw new IllegalStateException(
						"Unable to capture the current slider product " + "before waiting for automatic rotation");
			}

			context.set("previousSliderProduct", previousProduct);

			log.info("Stored previous slider product: {}", previousProduct);
		}

		log.info("Waiting for slider rotation from product: {}", previousProduct);

		/*
		 * Wait until the slider displays a different product.
		 */
		menuSliderAction.waitForProductChange(previousProduct);

		/*
		 * Capture the newly displayed product.
		 */
		String currentProduct = menuSliderAction.getProductName();

		if (currentProduct == null || currentProduct.trim().isEmpty()) {

			throw new IllegalStateException("Current slider product is null or empty " + "after automatic rotation");
		}

		context.set("currentSliderProduct", currentProduct);

		log.info("Slider automatically rotated from '{}' to '{}'", previousProduct, currentProduct);

		Assert.assertNotEquals(currentProduct, previousProduct,
				"Slider did not automatically rotate. " + "Product remained: " + previousProduct);
	}

	@Given("the dynamic product slider is displayed")
	public void the_dynamic_product_slider_is_displayed() {

		log.info("Verifying that the dynamic product slider is displayed");

		boolean sliderDisplayed = menuSliderAction.isSliderDisplayed();

		Assert.assertTrue(sliderDisplayed, "Dynamic product slider is not displayed");

		log.info("Dynamic product slider is displayed successfully");
	}

	@When("the user waits for the automatic slider rotation")
	public void the_user_waits_for_the_automatic_slider_rotation() {

		log.info("Waiting for the dynamic product slider to rotate automatically");

		/*
		 * Capture the currently displayed product before waiting for automatic
		 * rotation.
		 */
		String previousProduct = menuSliderAction.getProductName();

		if (previousProduct == null || previousProduct.trim().isEmpty()) {

			throw new IllegalStateException(
					"Unable to determine the current slider product " + "before automatic rotation");
		}

		context.set("previousSliderProduct", previousProduct);

		log.info("Previous slider product recorded: {}", previousProduct);

		/*
		 * Wait until the slider displays a different product.
		 */
		menuSliderAction.waitForProductChange(previousProduct);

		/*
		 * Capture the newly displayed product.
		 */
		String currentProduct = menuSliderAction.getProductName();

		if (currentProduct == null || currentProduct.trim().isEmpty()) {

			throw new IllegalStateException("Unable to determine the slider product after rotation");
		}

		context.set("currentSliderProduct", currentProduct);

		log.info("Automatic slider rotation completed. " + "Previous product: '{}', Current product: '{}'",
				previousProduct, currentProduct);

		Assert.assertNotEquals(currentProduct, previousProduct,
				"Slider product did not change after automatic rotation. " + "Product remained: " + previousProduct);
	}

	@When("the user waits for multiple slider rotation intervals")
	public void theUserWaitsForMultipleSliderRotationIntervals() {

		log.info("Waiting for multiple slider rotations");

		for (int i = 0; i < 3; i++) {

			String currentProduct = menuSliderAction.getProductName();

			displayedProducts.add(currentProduct);
			uniqueProducts.add(currentProduct);

			menuSliderAction.waitForProductChange(currentProduct);
		}

		log.info("Products captured during rotation: {}", displayedProducts);
	}

	@When("the slider automatically changes the product")
	public void theSliderAutomaticallyChangesTheProduct() {

		log.info("Waiting for automatic slider product change");

		menuSliderAction.waitForProductChange(initialProductName);
	}

//	@When("the slider changes product")
//	public void theSliderChangesProduct() {
//
//		log.info("Waiting for slider product change");
//
//		menuSliderAction.waitForProductChange(initialProductName);
//	}

	@When("the slider changes product")
	public void theSliderChangesProduct() {

		log.info("Verifying that the slider changes to another product");

		// Try to get the previously recorded product
		String previousProduct = context.get("previousSliderProduct", String.class);

		// If previous product was not recorded, capture it now
		if (previousProduct == null || previousProduct.trim().isEmpty()) {

			previousProduct = menuSliderAction.getProductName();

			if (previousProduct == null || previousProduct.trim().isEmpty()) {

				throw new IllegalStateException("Unable to determine previous slider product");
			}

			context.set("previousSliderProduct", previousProduct);

			log.info("Previous slider product was not recorded. " + "Captured it now: {}", previousProduct);
		}

		log.info("Previous slider product: {}", previousProduct);

		// Capture previous image before rotation
		String previousImage = menuSliderAction.getSliderImageAltText();

		context.set("previousSliderImage", previousImage);

		log.info("Previous slider image: {}", previousImage);

		// Wait for automatic product change
		menuSliderAction.waitForProductChange(previousProduct);

		// Capture new product
		String currentProduct = menuSliderAction.getProductName();

		if (currentProduct == null || currentProduct.trim().isEmpty()) {

			throw new IllegalStateException("Current slider product could not be determined");
		}

		// Capture new image
		String currentImage = menuSliderAction.getSliderImageAltText();

		context.set("currentSliderProduct", currentProduct);

		context.set("currentSliderImage", currentImage);

		log.info("Current slider product: {}", currentProduct);

		log.info("Current slider image: {}", currentImage);

		Assert.assertNotEquals(currentProduct, previousProduct,
				"Slider product did not change. " + "Product remained: " + previousProduct);

		log.info("Slider successfully changed from '{}' to '{}'", previousProduct, currentProduct);
	}

	@Given("the current image source is recorded")
	public void the_current_image_source_is_recorded() {

		log.info("Recording current slider image source");

		String currentImageSource = menuSliderAction.getSliderImageSrc();

		if (currentImageSource == null || currentImageSource.trim().isEmpty()) {

			throw new IllegalStateException("Current slider image source cannot be null or empty");
		}

		context.set("previousSliderImageSource", currentImageSource);

		log.info("Current slider image source recorded: {}", currentImageSource);
	}

	@Then("the image source should correspond to the new product")
	public void the_image_source_should_correspond_to_the_new_product() {

		String currentProduct = context.get("currentSliderProduct", String.class);

		if (currentProduct == null || currentProduct.trim().isEmpty()) {

			throw new IllegalStateException("Current slider product was not found in ScenarioContext");
		}

		String currentImageSource = menuSliderAction.getSliderImageSrc();

		if (currentImageSource == null || currentImageSource.trim().isEmpty()) {

			throw new IllegalStateException("Current slider image source cannot be null or empty");
		}

		log.info("New slider product: {}", currentProduct);

		log.info("New slider image source: {}", currentImageSource);

		String expectedImageName;

		switch (currentProduct) {

		case "Sauce Labs Bike Light":
			expectedImageName = "bike-light";
			break;

		case "Sauce Labs Bolt T-Shirt":
			expectedImageName = "bolt-shirt";
			break;

		case "Sauce Labs Onesie":
			expectedImageName = "onesie";
			break;

		case "Test.allTheThings() T-Shirt (Red)":
			expectedImageName = "test.allthethings-tshirt-red";
			break;

		case "Sauce Labs Backpack":
			expectedImageName = "sauce-backpack";
			break;

		case "Sauce Labs Fleece Jacket":
			expectedImageName = "sauce-fleece-jacket";
			break;

		default:
			throw new IllegalArgumentException("Unknown slider product: " + currentProduct);
		}

		String normalizedImageSource = currentImageSource.toLowerCase();

		Assert.assertTrue(normalizedImageSource.contains(expectedImageName.toLowerCase()),
				"Image source does not correspond to the new product. " + "Product: " + currentProduct
						+ ", Expected image identifier: " + expectedImageName + ", Image Source: "
						+ currentImageSource);

		log.info("Image source correctly corresponds to product '{}'", currentProduct);
	}

	@Then("only one navigation dot should have aria-current {string}")
	public void only_one_navigation_dot_should_have_aria_current(String expectedValue) {

		log.info("Verifying that only one navigation dot has aria-current='{}'", expectedValue);

		menuSliderAssertions.verifyOnlyOneDotHasAriaCurrent(expectedValue);

		log.info("Verified that only one navigation dot has aria-current='{}'", expectedValue);
	}

	@Then("the slider product price should not be empty")
	public void the_slider_product_price_should_not_be_empty() {

		log.info("Verifying slider product price is not empty");

		String productPrice = menuSliderAction.getProductPrice();

		log.info("Current slider product price: {}", productPrice);

		Assert.assertNotNull(productPrice, "Slider product price should not be null");

		Assert.assertFalse(productPrice.trim().isEmpty(), "Slider product price should not be empty");

		log.info("Verified slider product price is not empty: {}", productPrice);
	}

	@Then("only one navigation dot should be active")
	public void only_one_navigation_dot_should_be_active() {

		log.info("Verifying that only one navigation dot is active");

		menuSliderAssertions.verifyOnlyOneDotHasAriaCurrent("true");

		log.info("Verified that exactly one navigation dot is active");
	}

	@Then("one navigation dot should always be active")
	public void one_navigation_dot_should_always_be_active() {

		log.info("Verifying that exactly one navigation dot is always active");

		menuSliderAssertions.verifyOnlyOneDotHasAriaCurrent("true");

		log.info("Verified that exactly one navigation dot is active");
	}

	@Then("the previous product information should not remain visible")
	public void the_previous_product_information_should_not_remain_visible() {

		log.info("Verifying that previous product information is no longer visible");

		String previousProduct = context.get("previousSliderProduct", String.class);

		if (previousProduct == null || previousProduct.trim().isEmpty()) {

			throw new IllegalStateException("Previous slider product was not found in ScenarioContext");
		}

		String currentProduct = menuSliderAction.getProductName();

		if (currentProduct == null || currentProduct.trim().isEmpty()) {

			throw new IllegalStateException("Current slider product is null or empty");
		}

		log.info("Previous slider product: {}", previousProduct);
		log.info("Current slider product: {}", currentProduct);

		Assert.assertNotEquals(currentProduct, previousProduct, "Previous product information is still visible. "
				+ "Previous product: " + previousProduct + ", Current product: " + currentProduct);

		log.info("Verified that previous product '{}' is no longer visible. " + "Current product is '{}'",
				previousProduct, currentProduct);
	}

	@When("the slider product changes")
	public void the_slider_product_changes() {

		log.info("Waiting for the slider product to change automatically");

		String previousProduct = context.get("previousSliderProduct", String.class);

		// Capture the current product if it was not already recorded
		if (previousProduct == null || previousProduct.trim().isEmpty()) {

			previousProduct = menuSliderAction.getProductName();

			if (previousProduct == null || previousProduct.trim().isEmpty()) {

				throw new IllegalStateException("Unable to determine previous slider product");
			}

			context.set("previousSliderProduct", previousProduct);

			log.info("Previous slider product captured: {}", previousProduct);
		}

		log.info("Previous slider product: {}", previousProduct);

		// Store previous image information
		String previousImage = menuSliderAction.getSliderImageAltText();

		context.set("previousSliderImage", previousImage);

		log.info("Previous slider image: {}", previousImage);

		// Wait for automatic slider rotation
		menuSliderAction.waitForProductChange(previousProduct);

		// Capture the newly displayed product
		String currentProduct = menuSliderAction.getProductName();

		if (currentProduct == null || currentProduct.trim().isEmpty()) {

			throw new IllegalStateException("Current slider product could not be determined");
		}

		// Capture the newly displayed image
		String currentImage = menuSliderAction.getSliderImageAltText();

		context.set("currentSliderProduct", currentProduct);

		context.set("currentSliderImage", currentImage);

		log.info("Slider product changed from '{}' to '{}'", previousProduct, currentProduct);

		log.info("Current slider image: {}", currentImage);

		Assert.assertNotEquals(currentProduct, previousProduct, "Slider product did not change. " + "Previous product: "
				+ previousProduct + ", Current product: " + currentProduct);
	}

	@Then("the slider should remain stable")
	public void the_slider_should_remain_stable() {

		log.info("Verifying that the slider remains stable");

		String initialProduct = menuSliderAction.getProductName();

		if (initialProduct == null || initialProduct.trim().isEmpty()) {

			throw new IllegalStateException("Initial slider product cannot be null or empty");
		}

		log.info("Initial slider product: {}", initialProduct);

		// Allow the UI to settle briefly and verify the
		// product remains unchanged during this check.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();

			throw new IllegalStateException("Interrupted while verifying slider stability", e);
		}

		String currentProduct = menuSliderAction.getProductName();

		if (currentProduct == null || currentProduct.trim().isEmpty()) {

			throw new IllegalStateException("Current slider product cannot be null or empty");
		}

		log.info("Current slider product after stability check: {}", currentProduct);

		Assert.assertEquals(currentProduct, initialProduct, "Slider product changed unexpectedly. "
				+ "Initial product: " + initialProduct + ", Current product: " + currentProduct);

		log.info("Slider remained stable with product: {}", currentProduct);
	}

	@Then("a valid product should be displayed")
	public void a_valid_product_should_be_displayed() {

		log.info("Verifying that a valid product is displayed in the slider");

		String productName = menuSliderAction.getProductName();

		if (productName == null || productName.trim().isEmpty()) {

			throw new IllegalStateException("Slider product name cannot be null or empty");
		}

		log.info("Current slider product: {}", productName);

		List<String> validProducts = Arrays.asList("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
				"Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack",
				"Sauce Labs Fleece Jacket");

		Assert.assertTrue(validProducts.contains(productName), "Invalid slider product displayed. " + "Actual product: "
				+ productName + ", Expected products: " + validProducts);

		log.info("Verified valid slider product: {}", productName);
	}

	@When("the user rapidly changes slider products")
	public void the_user_rapidly_changes_slider_products() {

		log.info("Starting rapid slider product changes");

		String[] products = { "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie",
				"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack", "Sauce Labs Fleece Jacket" };

		for (String product : products) {

			log.info("Rapidly selecting slider product: {}", product);

			menuSliderAction.clickSliderDotForProduct(product);

			String currentProduct = menuSliderAction.getProductName();

			Assert.assertEquals(currentProduct, product, "Slider did not display the expected product. " + "Expected: "
					+ product + ", Actual: " + currentProduct);
		}

		log.info("Rapid slider product changes completed successfully");
	}

	@Then("{int} slider navigation dots should be displayed")
	public void slider_navigation_dots_should_be_displayed(Integer expectedCount) {

		log.info("Verifying slider navigation dot count. Expected: {}", expectedCount);

		if (expectedCount == null || expectedCount < 1) {
			throw new IllegalArgumentException("Expected slider navigation dot count must be greater than 0");
		}

		int actualCount = menuSliderAssertions.getSliderNavigationDotCount();

		Assert.assertEquals(actualCount, expectedCount.intValue(), "Incorrect number of slider navigation dots. "
				+ "Expected: " + expectedCount + ", Actual: " + actualCount);

		log.info("Verified slider navigation dots. Count: {}", actualCount);
	}

	@Then("the slider product should change")
	public void theSliderProductShouldChange() {

		String currentProduct = menuSliderAction.getProductName();

		log.info("Initial product: {}, Current product: {}", initialProductName, currentProduct);

		Assert.assertNotEquals(currentProduct, initialProductName,
				"Slider product did not change from: " + initialProductName);
	}

	@Then("the slider image should change")
	public void theSliderImageShouldChange() {

		String currentImage = menuSliderAction.getImageSrc();

		log.info("Initial image: {}, Current image: {}", initialImageSrc, currentImage);

		Assert.assertNotEquals(currentImage, initialImageSrc, "Slider image did not change");
	}

	@Then("the slider product name should change")
	public void theSliderProductNameShouldChange() {

		String currentProduct = menuSliderAction.getProductName();

		log.info("Initial product name: {}, Current product name: {}", initialProductName, currentProduct);

		Assert.assertNotEquals(currentProduct, initialProductName, "Slider product name did not change");
	}

	@Then("the slider price should change")
	public void theSliderPriceShouldChange() {

		String currentPrice = menuSliderAction.getProductPrice();

		log.info("Initial price: {}, Current price: {}", initialProductPrice, currentPrice);

		Assert.assertNotEquals(currentPrice, initialProductPrice, "Slider price did not change");
	}

	@Then("the slider image name and price should represent the new product")
	public void theSliderImageNameAndPriceShouldRepresentTheNewProduct() {

		log.info("Verifying image, product name and price synchronization");

		menuSliderAssertions.verifyProductInformationIsConsistent();
	}

	@Then("the active slider dot should change")
	public void theActiveSliderDotShouldChange() {

		int currentActiveDot = menuSliderAction.getActiveDotIndex();

		log.info("Initial active dot: {}, Current active dot: {}", initialActiveDotIndex, currentActiveDot);

		Assert.assertNotEquals(currentActiveDot, initialActiveDotIndex, "Active slider dot did not change");
	}

	@Then("the active dot should correspond to the displayed product")
	public void theActiveDotShouldCorrespondToTheDisplayedProduct() {

		log.info("Verifying active dot corresponds to displayed product");

		menuSliderAssertions.verifyActiveDotMatchesProduct();
	}

	@Then("the slider should display different products sequentially")
	public void theSliderShouldDisplayDifferentProductsSequentially() {

		log.info("Verifying sequential slider products: {}", displayedProducts);

		Assert.assertTrue(uniqueProducts.size() > 1, "Slider did not display different products");
	}

	@When("the slider changes product by sliding")
	public void theSliderChangesProductPrevious() {

		String previousProduct = context.get("previousSliderProduct", String.class);

		if (previousProduct == null || previousProduct.trim().isEmpty()) {

			throw new IllegalStateException("Previous slider product was not found in ScenarioContext");
		}

		log.info("Previous slider product: {}", previousProduct);

		// Wait for automatic slider/product change
		menuSliderAction.waitForProductChange(previousProduct);

		// Capture the new product
		String currentProduct = menuSliderAction.getProductName();

		if (currentProduct == null || currentProduct.trim().isEmpty()) {

			throw new IllegalStateException("Current slider product could not be determined after rotation");
		}

		context.set("currentSliderProduct", currentProduct);

		log.info("Slider changed from '{}' to '{}'", previousProduct, currentProduct);
	}

	// ============================================================
	// PRODUCT NAVIGATION
	// ============================================================

	@Then("slider navigation dot {int} should be displayed")
	public void sliderNavigationDotShouldBeDisplayed(int index) {

		log.info("Verifying slider navigation dot: {}", index);

		menuSliderAssertions.verifyNavigationDotDisplayed(index);
	}

	@When("the user clicks the {word} slider dot")
	public void theUserClicksTheSliderDot(String productName) {

		log.info("Clicking slider dot for product: {}", productName);

		menuSliderAction.clickProductDot(productName);
	}

	@When("the user clicks the Sauce Labs Bike Light slider dot")
	public void theUserClicksTheSauceLabsBikeLightSliderDot() {

		log.info("Clicking Sauce Labs Bike Light slider dot");

		menuSliderAction.clickProductDot("Sauce Labs Bike Light");
	}

	@When("the user clicks the Sauce Labs Bolt T-Shirt slider dot")
	public void theUserClicksTheSauceLabsBoltTShirtSliderDot() {

		log.info("Clicking Sauce Labs Bolt T-Shirt slider dot");

		menuSliderAction.clickProductDot("Sauce Labs Bolt T-Shirt");
	}

	@When("the user clicks the Sauce Labs Onesie slider dot")
	public void theUserClicksTheSauceLabsOnesieSliderDot() {

		log.info("Clicking Sauce Labs Onesie slider dot");

		menuSliderAction.clickProductDot("Sauce Labs Onesie");
	}

	@When("the user clicks the {string} slider dot")
	public void theUserClicksTheSliderDotProduct(String productName) {

		log.info("Clicking slider dot for product: {}", productName);

		menuSliderAction.clickProductSliderDot(productName);
	}

	@When("the user clicks the Sauce Labs Backpack slider dot")
	public void theUserClicksTheSauceLabsBackpackSliderDot() {

		log.info("Clicking Sauce Labs Backpack slider dot");

		menuSliderAction.clickProductDot("Sauce Labs Backpack");
	}

	@When("the user clicks the Sauce Labs Fleece Jacket slider dot")
	public void theUserClicksTheSauceLabsFleeceJacketSliderDot() {

		log.info("Clicking Sauce Labs Fleece Jacket slider dot");

		menuSliderAction.clickProductDot("Sauce Labs Fleece Jacket");
	}

	// ============================================================
	// PRODUCT VERIFICATION
	// ============================================================

//	@Given("the slider is displaying {word}")
//	public void theSliderIsDisplaying(String productName) {
//
//		log.info("Verifying slider displays product: {}", productName);
//
//		menuSliderAssertions.verifyProductName(productName);
//	}

	@Then("Sauce Labs Bike Light should be displayed")
	public void sauceLabsBikeLightShouldBeDisplayed() {

		menuSliderAssertions.verifyProductName("Sauce Labs Bike Light");
	}

	@Then("Sauce Labs Bolt T-Shirt should be displayed")
	public void sauceLabsBoltTShirtShouldBeDisplayed() {

		menuSliderAssertions.verifyProductName("Sauce Labs Bolt T-Shirt");
	}

	@Then("Sauce Labs Onesie should be displayed")
	public void sauceLabsOnesieShouldBeDisplayed() {

		menuSliderAssertions.verifyProductName("Sauce Labs Onesie");
	}

	@Then("the {string} should be displayed")
	public void theProductShouldBeDisplayed(String productName) {

		log.info("Verifying displayed product: {}", productName);

		menuSliderAssertions.verifyProductDisplayed(productName);
	}

	@Then("Sauce Labs Backpack should be displayed")
	public void sauceLabsBackpackShouldBeDisplayed() {

		menuSliderAssertions.verifyProductName("Sauce Labs Backpack");
	}

	@Then("Sauce Labs Fleece Jacket should be displayed")
	public void sauceLabsFleeceJacketShouldBeDisplayed() {

		menuSliderAssertions.verifyProductName("Sauce Labs Fleece Jacket");
	}

	// ============================================================
	// IMAGE / PRICE VERIFICATION
	// ============================================================

	@Then("the Backpack image should be displayed")
	public void theBackpackImageShouldBeDisplayed() {

		menuSliderAssertions.verifyImageBelongsToProduct("Sauce Labs Backpack");
	}

	@Then("the Backpack price should be displayed")
	public void theBackpackPriceShouldBeDisplayed() {

		menuSliderAssertions.verifyPriceForProduct("Sauce Labs Backpack");
	}

	@Then("the displayed product name should change accordingly")
	public void theDisplayedProductNameShouldChangeAccordingly() {

		String currentProduct = menuSliderAction.getProductName();

		Assert.assertNotEquals(currentProduct, initialProductName, "Product name did not change");

		log.info("Product name changed from '{}' to '{}'", initialProductName, currentProduct);
	}

	@Then("the displayed price should correspond to the new product")
	public void theDisplayedPriceShouldCorrespondToTheNewProduct() {

		menuSliderAssertions.verifyPriceMatchesProduct();
	}

	// ============================================================
	// ACTIVE DOT
	// ============================================================

	@Then("the corresponding dot should be active")
	public void theCorrespondingDotShouldBeActive() {

		menuSliderAssertions.verifyActiveDotMatchesProduct();
	}

	@Given("the Sauce Labs Onesie dot is active")
	public void theSauceLabsOnesieDotIsActive() {

		menuSliderAction.clickProductDot("Sauce Labs Onesie");

		menuSliderAssertions.verifyActiveDotMatchesProduct();

		log.info("Sauce Labs Onesie dot is active");
	}

	@Then("the Sauce Labs Backpack dot should have aria-current {string}")
	public void theSauceLabsBackpackDotShouldHaveAriaCurrent(String expectedValue) {

		menuSliderAssertions.verifyProductDotAriaCurrent("Sauce Labs Backpack", expectedValue);
	}

	@Then("the Sauce Labs Onesie dot should have aria-current {string}")
	public void theSauceLabsOnesieDotShouldHaveAriaCurrent(String expectedValue) {

		menuSliderAssertions.verifyProductDotAriaCurrent("Sauce Labs Onesie", expectedValue);
	}

	@Then("only one slider dot should have aria-current {string}")
	public void onlyOneSliderDotShouldHaveAriaCurrent(String expectedValue) {

		menuSliderAssertions.verifyOnlyOneDotHasAriaCurrent(expectedValue);
	}

	// ============================================================
	// PRODUCT ROTATION
	// ============================================================

	@When("the user waits for the slider to rotate through all products")
	public void theUserWaitsForTheSliderToRotateThroughAllProducts() {

		log.info("Waiting for slider to rotate through all products");

		displayedProducts.clear();
		uniqueProducts.clear();

		String currentProduct = menuSliderAction.getProductName();

		displayedProducts.add(currentProduct);
		uniqueProducts.add(currentProduct);

		for (int i = 0; i < 5; i++) {

			menuSliderAction.waitForProductChange(currentProduct);

			currentProduct = menuSliderAction.getProductName();

			displayedProducts.add(currentProduct);
			uniqueProducts.add(currentProduct);
		}

		log.info("Products displayed during complete rotation: {}", displayedProducts);
	}

	@Then("all {int} slider products should be displayed")
	public void allSliderProductsShouldBeDisplayed(int expectedCount) {

		log.info("Expected products: {}, Actual unique products: {}", expectedCount, uniqueProducts.size());

		Assert.assertEquals(uniqueProducts.size(), expectedCount, "Not all expected slider products were displayed");
	}

//	@Then("each expected product should appear in the rotation")
//	public void eachExpectedProductShouldAppearInTheRotation() {
//
//		List<String> expectedProducts = List.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie",
//				"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack", "Sauce Labs Fleece Jacket");
//
//		for (String expectedProduct : expectedProducts) {
//
//			Assert.assertTrue(uniqueProducts.contains(expectedProduct),
//					"Expected product was not displayed: " + expectedProduct);
//		}
//
//		log.info("All expected products were displayed");
//	}

	@Then("each expected product should appear in the rotation")
	public void eachExpectedProductShouldAppearInTheRotation() {

		String[] expectedProducts = { "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie",
				"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack", "Sauce Labs Fleece Jacket" };

		for (String expectedProduct : expectedProducts) {

			log.info("Checking whether product appears in automatic rotation: {}", expectedProduct);

			boolean productDisplayed = menuSliderAction.waitForProductToAppear(expectedProduct, 15);

			Assert.assertTrue(productDisplayed, "Expected product was not displayed: " + expectedProduct);

			log.info("Verified product appeared in rotation: {}", expectedProduct);
		}
	}

	@Then("the first slider product should be displayed")
	public void theFirstSliderProductShouldBeDisplayed() {

		String expectedFirstProduct = "Sauce Labs Bike Light";

		log.info("Verifying first slider product is displayed: {}", expectedFirstProduct);

		// Get the currently displayed slider product
		String actualProduct = menuSliderAction.getProductName();

		log.info("Expected first slider product: {}", expectedFirstProduct);

		log.info("Actual slider product: {}", actualProduct);

		// Verify product name
		Assert.assertEquals(actualProduct, expectedFirstProduct, "First slider product is not displayed. "
				+ "Expected: " + expectedFirstProduct + ", Actual: " + actualProduct);

		// Verify the corresponding dot is active
		menuSliderAssertions.verifyProductDotAriaCurrent(expectedFirstProduct, "true");

		log.info("First slider product '{}' is displayed and its dot is active", expectedFirstProduct);
	}

//	@Then("the first slider product should be displayed")
//	public void theFirstSliderProductShouldBeDisplayed() {
//
//		String expectedFirstProduct = "Sauce Labs Bike Light";
//
//		String actualProduct = menuSliderAction.getProductName();
//
//		log.info("Expected first slider product: {}", expectedFirstProduct);
//
//		log.info("Actual first slider product: {}", actualProduct);
//
//		Assert.assertEquals(actualProduct, expectedFirstProduct, "First slider product is not displayed. "
//				+ "Expected: " + expectedFirstProduct + ", Actual: " + actualProduct);
//	}

	@Given("the last slider product is displayed")
	public void theLastSliderProductIsDisplayed() {

		String expectedLastProduct = "Sauce Labs Fleece Jacket";

		log.info("Setting slider to last product: {}", expectedLastProduct);

		// Select the last product using its slider dot
		menuSliderAction.clickSliderDotForProduct(expectedLastProduct);

		// Get the currently displayed product
		String actualProduct = menuSliderAction.getProductName();

		log.info("Expected last slider product: {}", expectedLastProduct);

		log.info("Actual slider product: {}", actualProduct);

		// Verify last product is displayed
		Assert.assertEquals(actualProduct, expectedLastProduct, "Last slider product is not displayed. " + "Expected: "
				+ expectedLastProduct + ", Actual: " + actualProduct);

		// Verify the last product's dot is active
		menuSliderAssertions.verifyProductDotAriaCurrent(expectedLastProduct, "true");

		log.info("Last slider product '{}' is displayed " + "and its slider dot is active", expectedLastProduct);
	}

	@Then("the first product should be displayed")
	public void theFirstProductShouldBeDisplayed() {

		String expectedFirstProduct = "Sauce Labs Bike Light";

		log.info("Verifying first slider product is displayed: {}", expectedFirstProduct);

		String actualProduct = menuSliderAction.getProductName();

		log.info("Expected first product: {}", expectedFirstProduct);

		log.info("Actual displayed product: {}", actualProduct);

		Assert.assertEquals(actualProduct, expectedFirstProduct, "First product is not displayed. " + "Expected: "
				+ expectedFirstProduct + ", Actual: " + actualProduct);

		// Verify the first product's slider dot is active
		menuSliderAssertions.verifyProductDotAriaCurrent(expectedFirstProduct, "true");

		log.info("First product '{}' is displayed and its dot is active", expectedFirstProduct);
	}

//	@Given("the current product price is recorded")
//	public void theCurrentProductPriceIsRecorded() {
//
//		log.info("Recording current slider product price");
//
//		String currentProductPrice = menuSliderAction.getProductPrice();
//
//		if (currentProductPrice == null || currentProductPrice.trim().isEmpty()) {
//
//			throw new IllegalStateException("Current product price could not be determined");
//		}
//
//		context.set("currentSliderProductPrice", currentProductPrice);
//
//		log.info("Current slider product price recorded: {}", currentProductPrice);
//	}

	@Given("the current product details are recorded")
	public void theCurrentProductDetailsAreRecorded() {

		String productName = menuSliderAction.getProductName();

		String productPrice = menuSliderAction.getProductPrice();

		if (productName == null || productName.trim().isEmpty()) {
			throw new IllegalStateException("Current product name could not be determined");
		}

		if (productPrice == null || productPrice.trim().isEmpty()) {
			throw new IllegalStateException("Current product price could not be determined");
		}

		context.set("currentSliderProduct", productName);
		context.set("currentSliderProductPrice", productPrice);

		log.info("Current slider product recorded: {}", productName);

		log.info("Current slider product price recorded: {}", productPrice);
	}

	@Given("the current product price is recorded")
	public void the_current_product_price_is_recorded() {

		log.info("Recording the current slider product price");

		String currentProductPrice = menuSliderAction.getProductPrice();

		if (currentProductPrice == null || currentProductPrice.trim().isEmpty()) {

			throw new IllegalStateException("Current product price cannot be null or empty");
		}

		// Store price in ScenarioContext for later validation
		context.set("currentSliderProductPrice", currentProductPrice);

		log.info("Current slider product price recorded: {}", currentProductPrice);
	}

//	@Given("the current product name is recorded")
//	public void the_current_product_name_is_recorded() {
//
//		log.info("Recording the current slider product name");
//
//		String currentProductName = menuSliderAction.getProductName();
//
//		if (currentProductName == null || currentProductName.trim().isEmpty()) {
//
//			throw new IllegalStateException("Current product name cannot be null or empty");
//		}
//
//		// Store product name in ScenarioContext
//		context.set("currentSliderProduct", currentProductName);
//
//		log.info("Current slider product name recorded: {}", currentProductName);
//	}

	@Given("the current product name is recorded")
	public void the_current_product_name_is_recorded() {

		log.info("Recording current slider product name");

		String currentProductName = menuSliderAction.getProductName();

		if (currentProductName == null || currentProductName.trim().isEmpty()) {

			throw new IllegalStateException("Current slider product name cannot be null or empty");
		}

		context.set("previousSliderProduct", currentProductName);

		log.info("Previous slider product stored in ScenarioContext: {}", currentProductName);
	}

	@Then("consecutive slider products should not be identical")
	public void consecutiveSliderProductsShouldNotBeIdentical() {

		for (int i = 1; i < displayedProducts.size(); i++) {

			Assert.assertNotEquals(displayedProducts.get(i), displayedProducts.get(i - 1),
					"Consecutive slider products are identical");
		}

		log.info("Verified no consecutive duplicate products: {}", displayedProducts);
	}

	@Given("the slider reaches the last product")
	public void theSliderReachesTheLastProduct() {

		menuSliderAction.clickProductDot("Sauce Labs Fleece Jacket");

		menuSliderAssertions.verifyProductName("Sauce Labs Fleece Jacket");

		log.info("Slider is displaying the last product");
	}

	@When("the next automatic rotation occurs")
	public void theNextAutomaticRotationOccurs() {

		String currentProduct = menuSliderAction.getProductName();

		menuSliderAction.waitForProductChange(currentProduct);
	}

	@Given("the slider has displayed all products")
	public void theSliderHasDisplayedAllProducts() {

		displayedProducts.clear();
		uniqueProducts.clear();

		String currentProduct = menuSliderAction.getProductName();

		displayedProducts.add(currentProduct);
		uniqueProducts.add(currentProduct);

		for (int i = 0; i < 5; i++) {

			menuSliderAction.waitForProductChange(currentProduct);

			currentProduct = menuSliderAction.getProductName();

			displayedProducts.add(currentProduct);
			uniqueProducts.add(currentProduct);
		}

		Assert.assertEquals(uniqueProducts.size(), 6, "Slider did not display all six products");

		log.info("Slider displayed all six products");
	}

	@When("the next rotation occurs")
	public void theNextRotationOccurs() {

		String currentProduct = menuSliderAction.getProductName();

		menuSliderAction.waitForProductChange(currentProduct);
	}

	// ============================================================
	// BOUNDARY NAVIGATION
	// ============================================================

	@When("the user selects the first slider dot")
	public void theUserSelectsTheFirstSliderDot() {

		log.info("Selecting first slider dot");

		menuSliderAction.clickDot(0);
	}

	@When("the user selects the last slider dot")
	public void theUserSelectsTheLastSliderDot() {

		log.info("Selecting last slider dot");

		menuSliderAction.clickDot(5);
	}

	// ============================================================
	// PRICE VALIDATION
	// ============================================================

	@Then("the slider price should start with {string}")
	public void theSliderPriceShouldStartWith(String symbol) {

		String price = menuSliderAction.getProductPrice();

		Assert.assertTrue(price.startsWith(symbol), "Slider price does not start with: " + symbol);

		log.info("Verified price currency symbol: {}", price);
	}

	@Then("the slider price should match the expected currency format")
	public void theSliderPriceShouldMatchExpectedCurrencyFormat() {

		menuSliderAssertions.verifyValidPriceFormat();
	}

	@Then("the slider price should contain two decimal places")
	public void theSliderPriceShouldContainTwoDecimalPlaces() {

		menuSliderAssertions.verifyPriceHasTwoDecimalPlaces();
	}

	@Then("the slider price should not be empty")
	public void theSliderPriceShouldNotBeEmpty() {

		String price = menuSliderAction.getProductPrice();

		Assert.assertFalse(price == null || price.trim().isEmpty(), "Slider price is empty");
	}

	@Then("the slider price should not contain invalid currency format")
	public void theSliderPriceShouldNotContainInvalidCurrencyFormat() {

		menuSliderAssertions.verifyValidPriceFormat();
	}

	// ============================================================
	// PRODUCT NAME VALIDATION
	// ============================================================

	@Then("the slider product name should not be empty")
	public void theSliderProductNameShouldNotBeEmpty() {

		String productName = menuSliderAction.getProductName();

		Assert.assertFalse(productName == null || productName.trim().isEmpty(), "Slider product name is empty");
	}

	@Then("the slider product name should be visible")
	public void theSliderProductNameShouldBeVisible() {

		menuSliderAssertions.verifyProductNameDisplayed();
	}

	@Then("the slider product name should not contain leading or trailing whitespace")
	public void theSliderProductNameShouldNotContainLeadingOrTrailingWhitespace() {

		String productName = menuSliderAction.getProductName();

		Assert.assertEquals(productName, productName.trim(), "Product name contains leading or trailing whitespace");
	}

	// ============================================================
	// IMAGE VALIDATION
	// ============================================================

	@Then("the slider product image should have a non-empty alt attribute")
	public void theSliderProductImageShouldHaveNonEmptyAltAttribute() {

		String alt = menuSliderAction.getImageAlt();

		Assert.assertFalse(alt == null || alt.trim().isEmpty(), "Slider product image alt attribute is empty");
	}

	@Then("the slider image alt text should match the product name")
	public void theSliderImageAltTextShouldMatchProductName() {

		String imageAlt = menuSliderAction.getImageAlt();

		String productName = menuSliderAction.getProductName();

		Assert.assertEquals(imageAlt, productName, "Image alt text does not match product name");
	}

	@Then("the slider image src should not be empty")
	public void theSliderImageSrcShouldNotBeEmpty() {

		String imageSrc = menuSliderAction.getImageSrc();

		Assert.assertFalse(imageSrc == null || imageSrc.trim().isEmpty(), "Slider image src is empty");
	}

	@Then("the slider image should load successfully")
	public void theSliderImageShouldLoadSuccessfully() {

		menuSliderAssertions.verifyImageLoaded();
	}

	@Then("the slider image should have meaningful alt text")
	public void theSliderImageShouldHaveMeaningfulAltText() {

		menuSliderAssertions.verifyMeaningfulImageAltText();
	}

	@Then("the slider image should represent the displayed product")
	public void theSliderImageShouldRepresentTheDisplayedProduct() {

		menuSliderAssertions.verifyImageBelongsToProduct(menuSliderAction.getProductName());
	}

	// ============================================================
	// DATA VALIDATION
	// ============================================================

	@When("all slider products are captured")
	public void allSliderProductsAreCaptured() {

		displayedProducts.clear();
		uniqueProducts.clear();

		String currentProduct = menuSliderAction.getProductName();

		displayedProducts.add(currentProduct);
		uniqueProducts.add(currentProduct);

		for (int i = 0; i < 5; i++) {

			menuSliderAction.waitForProductChange(currentProduct);

			currentProduct = menuSliderAction.getProductName();

			displayedProducts.add(currentProduct);
			uniqueProducts.add(currentProduct);
		}

		loadedProductCount = uniqueProducts.size();

		log.info("Captured {} unique slider products", loadedProductCount);
	}

	@Then("each product should have valid product information")
	public void eachProductShouldHaveValidProductInformation() {

		menuSliderAssertions.verifyAllProductsHaveValidInformation();
	}

	@When("the current slider product information is captured")
	public void theCurrentSliderProductInformationIsCaptured() {

		initialProductName = menuSliderAction.getProductName();
		initialProductPrice = menuSliderAction.getProductPrice();
		initialImageSrc = menuSliderAction.getImageSrc();
		initialImageAlt = menuSliderAction.getImageAlt();

		log.info("Captured product information: {} | {} | {} | {}", initialProductName, initialProductPrice,
				initialImageSrc, initialImageAlt);
	}

	@Then("the image name and price should belong to the same product")
	public void theImageNameAndPriceShouldBelongToTheSameProduct() {

		menuSliderAssertions.verifyProductInformationIsConsistent();
	}

	// ============================================================
	// ACCESSIBILITY
	// ============================================================

	@Then("every slider navigation dot should have an aria-label")
	public void everySliderNavigationDotShouldHaveAnAriaLabel() {

		menuSliderAssertions.verifyEveryDotHasAriaLabel();
	}

	@When("the slider navigation dots are inspected")
	public void theSliderNavigationDotsAreInspected() {

		log.info("Inspecting slider navigation dots");
	}

	@Then("each aria-label should identify its corresponding product")
	public void eachAriaLabelShouldIdentifyItsCorrespondingProduct() {

		menuSliderAssertions.verifyDotAriaLabels();
	}

	// ============================================================
	// NEGATIVE / ROBUSTNESS
	// ============================================================

	@Then("the previous product information should not remain displayed")
	public void thePreviousProductInformationShouldNotRemainDisplayed() {

		String currentProduct = menuSliderAction.getProductName();

		Assert.assertNotEquals(currentProduct, initialProductName, "Previous product information is still displayed");

		log.info("Previous product '{}' is no longer displayed", initialProductName);
	}

	@Then("the image name and price should represent the same product")
	public void theImageNameAndPriceShouldRepresentTheSameProduct() {

		menuSliderAssertions.verifyProductInformationIsConsistent();
	}

	@When("an invalid slider dot index is requested")
	public void anInvalidSliderDotIndexIsRequested() {

		log.info("Requesting invalid slider dot index");

		try {

			menuSliderAction.clickDot(-1);

		} catch (Exception e) {

			log.warn("Invalid slider dot index was rejected: {}", e.getMessage());
		}
	}

	@Then("the application should not crash")
	public void theApplicationShouldNotCrash() {

		Assert.assertTrue(menuSliderAction.isSliderDisplayed(),
				"Application became unstable after invalid slider interaction");
	}

	@When("the user rapidly clicks different slider dots")
	public void theUserRapidlyClicksDifferentSliderDots() {

		log.info("Rapidly clicking different slider dots");

		for (int i = 0; i < 6; i++) {

			menuSliderAction.clickDot(i);
		}
	}

	@Then("the slider should display a valid product")
	public void theSliderShouldDisplayAValidProduct() {

		menuSliderAssertions.verifyValidDisplayedProduct();
	}

	@Then("only one dot should be active")
	public void onlyOneDotShouldBeActive() {

		menuSliderAssertions.verifyExactlyOneActiveDot();
	}

	// ============================================================
	// STRESS / INTERACTION
	// ============================================================

	@Given("automatic slider rotation is active")
	public void automaticSliderRotationIsActive() {

		log.info("Automatic slider rotation is active");

		menuSliderAssertions.verifySliderDisplayed();
	}

	@When("the user clicks a navigation dot during rotation")
	public void theUserClicksANavigationDotDuringRotation() {

		log.info("Clicking navigation dot while slider is rotating");

		menuSliderAction.clickDot(4);
	}

	@Then("the selected product should be displayed correctly")
	public void theSelectedProductShouldBeDisplayedCorrectly() {

		menuSliderAssertions.verifyValidDisplayedProduct();
	}

	@Then("only the currently displayed product dot should be active")
	public void onlyTheCurrentlyDisplayedProductDotShouldBeActive() {

		menuSliderAssertions.verifyActiveDotMatchesProduct();
	}

	// ============================================================
	// PAGE REFRESH
	// ============================================================

	@When("the user refreshes the slider menu page")
	public void theUserRefreshesThePage() {

		log.info("Refreshing inventory page");

		menuSliderAction.refreshPage();
	}

	@When("the user refreshes the inventory page while on slider menu")
	public void theUserRefreshesTheInventoryPageWhileOnSliderMenu() {

		log.info("Refreshing inventory page");

		menuSliderAction.refreshPage();
	}

	@Then("a valid slider product should be displayed")
	public void aValidSliderProductShouldBeDisplayed() {

		menuSliderAssertions.verifyValidDisplayedProduct();
	}

	// ============================================================
	// COMPLETE SLIDER VALIDATION
	// ============================================================

	@When("the user captures the current slider product")
	public void theUserCapturesTheCurrentSliderProduct() {

		initialProductName = menuSliderAction.getProductName();

		initialProductPrice = menuSliderAction.getProductPrice();

		initialImageSrc = menuSliderAction.getImageSrc();

		initialImageAlt = menuSliderAction.getImageAlt();

		initialActiveDotIndex = menuSliderAction.getActiveDotIndex();

		log.info("Captured initial slider state. Product: {}, Price: {}, Dot: {}", initialProductName,
				initialProductPrice, initialActiveDotIndex);
	}

	@Then("the slider name should represent the displayed product")
	public void theSliderNameShouldRepresentTheDisplayedProduct() {

		menuSliderAssertions.verifyProductName(menuSliderAction.getProductName());
	}

	@Then("the slider price should represent the displayed product")
	public void theSliderPriceShouldRepresentTheDisplayedProduct() {

		menuSliderAssertions.verifyPriceMatchesProduct();
	}

	@Then("exactly one navigation dot should be active")
	public void exactlyOneNavigationDotShouldBeActive() {

		menuSliderAssertions.verifyExactlyOneActiveDot();
	}

}