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

	// ============================================================
	// AUTOMATIC ROTATION
	// ============================================================

	@When("the user waits for the slider rotation interval")
	public void theUserWaitsForTheSliderRotationInterval() {

		log.info("Waiting for slider automatic rotation");

		menuSliderAction.waitForProductChange(initialProductName);
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

	@When("the slider changes product")
	public void theSliderChangesProduct() {

		log.info("Waiting for slider product change");

		menuSliderAction.waitForProductChange(initialProductName);
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

	@Given("the slider is displaying {word}")
	public void theSliderIsDisplaying(String productName) {

		log.info("Verifying slider displays product: {}", productName);

		menuSliderAssertions.verifyProductName(productName);
	}

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

	@Then("each expected product should appear in the rotation")
	public void eachExpectedProductShouldAppearInTheRotation() {

		List<String> expectedProducts = List.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie",
				"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack", "Sauce Labs Fleece Jacket");

		for (String expectedProduct : expectedProducts) {

			Assert.assertTrue(uniqueProducts.contains(expectedProduct),
					"Expected product was not displayed: " + expectedProduct);
		}

		log.info("All expected products were displayed");
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