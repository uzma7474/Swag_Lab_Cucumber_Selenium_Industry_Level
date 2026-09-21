package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import base.BasePage;
import config.EnvironmentManager;
import utils.WaitUtils;

public class Checkout_Step_Two_Page extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(Checkout_Step_Two_Page.class);

	// =========================================================
	// PAGE LOCATORS
	// =========================================================

	@FindBy(css = ".title")
	private WebElement pageTitle;

	@FindBy(css = ".summary_info")
	private WebElement checkoutOverviewContainer;

	// =========================================================
	// PAYMENT INFORMATION
	// =========================================================

	@FindBy(css = ".summary_info .summary_info_label")
	private List<WebElement> summaryInfoLabels;

	@FindBy(css = ".summary_value_label")
	private List<WebElement> summaryValueLabels;

	@FindBy(css = ".summary_info_label")
	private List<WebElement> paymentAndShippingLabels;

	@FindBy(css = ".summary_value_label")
	private List<WebElement> paymentAndShippingValues;

	@FindBy(css = ".inventory_item_price")
	private List<WebElement> productPrices;

	@FindBy(css = ".complete-header")
	private WebElement confirmationMessage;

	// Checkout Overview product prices
	@FindBy(css = "[data-test='inventory-item-price']")
	private List<WebElement> cartItemPriceElements;

	// =========================================================
	// CART ITEMS
	// =========================================================

	@FindBy(css = ".cart_item")
	private List<WebElement> cartItems;

	@FindBy(css = ".cart_item")
	private List<WebElement> productList;

	@FindBy(css = ".inventory_item_name")
	private List<WebElement> itemNames;

	@FindBy(css = ".inventory_item_desc")
	private List<WebElement> itemDescriptions;

	@FindBy(css = ".inventory_item_price")
	private List<WebElement> itemPrices;

	// =========================================================
	// SUMMARY PRICE
	// =========================================================

	@FindBy(css = ".summary_subtotal_label")
	private WebElement subtotalLabel;

	@FindBy(css = ".summary_subtotal_label")
	private WebElement subtotal;

	@FindBy(css = ".summary_tax_label")
	private WebElement taxLabel;

	@FindBy(css = ".summary_tax_label")
	private WebElement tax;

	@FindBy(css = ".summary_total_label")
	private WebElement totalLabel;

	@FindBy(css = ".summary_total_label")
	private WebElement total;

	// =========================================================
	//
	// =========================================================

	@FindBy(css = ".summary_info")
	private WebElement checkoutOverview;

	@FindBy(css = ".cart_item")
	private List<WebElement> productItems;

	// =========================================================
	// BUTTONS
	// =========================================================

	@FindBy(id = "cancel")
	private WebElement cancelButton;

	@FindBy(id = "finish")
	private WebElement finishButton;

	// =========================================================
	//
	// ==========================================================
	@FindBy(className = "summary_subtotal_label")
	private WebElement subtotalElement;

	@FindBy(className = "summary_tax_label")
	private WebElement taxElement;

	@FindBy(className = "summary_total_label")
	private WebElement totalElement;

	@FindBy(css = "h2[data-test='complete-header']")
	private WebElement completeHeader;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public Checkout_Step_Two_Page() {

		super();

		PageFactory.initElements(driver, this);

		log.debug("Checkout_Step_Two_Page initialized");
	}

	// =========================================================
	// NAVIGATION
	// =========================================================

	/**
	 * Opens Checkout Step Two page directly.
	 *
	 * Normally the page should be reached through:
	 *
	 * Login -> Inventory -> Cart -> Checkout Step One -> Checkout Step Two
	 */
	public void open() {

		log.info("Navigating to SauceDemo Checkout Step Two page");

		navigateTo(EnvironmentManager.getBaseUrl() + "/checkout-step-two.html");
	}

	public void navigateToUrl(String url) {
		navigateTo("https://www.saucedemo.com/checkout-step-two.html");
	}

	/**
	 * Checks whether the Checkout Complete page is available.
	 *
	 * @return true if the Checkout Complete page is displayed, otherwise false
	 */
	/**
	 * Checks whether the Checkout Complete page is available.
	 *
	 * @return true if the Checkout Complete page is displayed, otherwise false
	 */
	public boolean isCheckoutCompleteAvailable() {

		try {
			boolean urlValid = driver.getCurrentUrl().contains("checkout-complete.html");

			boolean headerDisplayed = completeHeader.isDisplayed();

			boolean titleDisplayed = pageTitle.isDisplayed();

			return urlValid && headerDisplayed && titleDisplayed;

		} catch (Exception e) {
			log.warn("Checkout Complete page is not available: {}", e.getMessage());
			return false;
		}
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

	/**
	 * Returns Checkout Step Two page title.
	 */
	public String getPageTitle() {

		log.debug("Getting Checkout Step Two page title");

		return getText(pageTitle);
	}

	public int getProductCount() {

		waitForProductList();

		int count = productItems.size();

		log.info("Products displayed on Checkout Step Two: {}", count);

		return count;
	}
	
	public String getSubtotalText() {

	    log.debug("Getting raw subtotal text");

	    String subtotalText = subtotalElement.getText().trim();

	    log.info("Subtotal text retrieved: '{}'", subtotalText);

	    return subtotalText;
	}
	
	
//	public void waitForProductList() {
//
//	    WebDriverWait wait = new WebDriverWait(
//	            driver,
//	            Duration.ofSeconds(15)
//	    );
//
//	    wait.until(driver -> !productList.isEmpty());
//
//	    log.info("Checkout product list is displayed");
//	}

	public int getProductCountWithoutWaiting() {

		try {
			return productItems.size();

		} catch (Exception e) {

			log.warn("Unable to retrieve Checkout Overview product count: {}", e.getMessage());

			return 0;
		}
	}

	/**
	 * Checks whether Checkout Step Two / Overview page is displayed.
	 */
	public boolean isCheckoutStepTwoPageDisplayed() {

		log.debug("Checking whether Checkout Step Two page is displayed");

		try {

			return isDisplayed(pageTitle) && "Checkout: Overview".equalsIgnoreCase(getText(pageTitle));

		} catch (Exception e) {

			log.error("Unable to verify Checkout Step Two page", e);

			return false;
		}
	}

	/**
	 * Checks whether Checkout Overview container is displayed.
	 */
	public boolean isCheckoutOverviewDisplayed() {

		log.debug("Checking Checkout Overview container visibility");

		return isDisplayed(checkoutOverviewContainer);
	}

	/**
	 * Checks whether current URL belongs to Checkout Step Two.
	 */
	public boolean isCheckoutStepTwoUrlDisplayed() {

		log.debug("Checking Checkout Step Two URL");

		try {

			return driver.getCurrentUrl().contains("checkout-step-two.html");

		} catch (Exception e) {

			log.error("Unable to verify Checkout Step Two URL", e);

			return false;
		}
	}

	// =========================================================
	// CART ITEMS
	// =========================================================

	/**
	 * Returns number of products displayed in Checkout Overview.
	 */
	public int getCartItemCount() {

		log.debug("Getting Checkout Overview item count");

		return cartItems.size();
	}

	/**
	 * Checks whether at least one product is displayed.
	 */
	public boolean isCartItemDisplayed() {

		log.debug("Checking whether cart item is displayed");

		return !cartItems.isEmpty();
	}

	/**
	 * Returns all product names displayed in Checkout Overview.
	 */
	public List<String> getProductNames() {

		log.debug("Getting product names from Checkout Overview");

		return itemNames.stream().map(WebElement::getText).toList();
	}

	/**
	 * Returns all product descriptions.
	 */
	public List<String> getProductDescriptions() {

		log.debug("Getting product descriptions");

		return itemDescriptions.stream().map(WebElement::getText).toList();
	}

	/**
	 * Returns all product prices.
	 */
	public List<String> getProductPrices() {

		log.debug("Getting product prices");

		return itemPrices.stream().map(WebElement::getText).toList();
	}

	/**
	 * Returns product name at specified index.
	 */
	public String getProductName(int index) {

		log.debug("Getting product name at index: {}", index);

		return itemNames.get(index).getText();
	}

	/**
	 * Returns product price at specified index.
	 */
	public String getProductPrice(int index) {

		log.debug("Getting product price at index: {}", index);

		return itemPrices.get(index).getText();
	}

	/**
	 * Checks whether specified product is displayed.
	 */
	public boolean isProductDisplayed(String productName) {

		log.debug("Checking whether product is displayed: {}", productName);

		return itemNames.stream().anyMatch(element -> element.getText().equalsIgnoreCase(productName));
	}

	public boolean isProductListDisplayed() {

		try {
			WaitUtils.waitForVisibility(driver, productList.get(0));

			return !productList.isEmpty() && productList.stream().allMatch(WebElement::isDisplayed);

		} catch (Exception e) {

			log.warn("Checkout Step Two product list is not displayed", e);

			return false;
		}
	}

	public List<String> getProductNames_() {

		return itemNames.stream().map(WebElement::getText).map(String::trim).collect(Collectors.toList());
	}

	/**
	 * Gets the complete cart item element by product name.
	 */
	public WebElement getCartItem(String productName) {

		log.debug("Getting cart item for product: {}", productName);

		return cartItems.stream()
				.filter(item -> item.findElement(org.openqa.selenium.By.cssSelector(".inventory_item_name")).getText()
						.equalsIgnoreCase(productName))
				.findFirst().orElse(null);
	}

	// =========================================================
	// PAYMENT INFORMATION
	// =========================================================

	/**
	 * Returns all summary labels.
	 */
	public List<String> getSummaryLabels() {

		log.debug("Getting payment/shipping summary labels");

		return paymentAndShippingLabels.stream().map(WebElement::getText).toList();
	}

	/**
	 * Returns all summary values.
	 */
	public List<String> getSummaryValues() {

		log.debug("Getting payment/shipping summary values");

		return paymentAndShippingValues.stream().map(WebElement::getText).toList();
	}

	/**
	 * Checks whether payment information is displayed.
	 */
	public boolean isPaymentInformationDisplayed() {

		log.debug("Checking payment information visibility");

		return paymentAndShippingLabels.stream().anyMatch(element -> element.getText().contains("Payment Information"));
	}

	/**
	 * Checks whether shipping information is displayed.
	 */
	public boolean isShippingInformationDisplayed() {

		log.debug("Checking shipping information visibility");

		return paymentAndShippingLabels.stream()

				.anyMatch(element -> element.getText().contains("Shipping Information"));
	}

	public List<String> getProductPricesIn() {

		WaitUtils.waitForVisibility(driver, productPrices.get(0));

		return productPrices.stream().map(WebElement::getText).map(price -> price.replace("$", "").trim())
				.collect(Collectors.toList());
	}

	/**
	 * Returns payment information value.
	 */
	public String getPaymentInformation() {

		log.debug("Getting payment information");

		for (int i = 0; i < paymentAndShippingLabels.size(); i++) {

			String label = paymentAndShippingLabels.get(i).getText();

			if (label.contains("Payment Information") && i < paymentAndShippingValues.size()) {

				return paymentAndShippingValues.get(i).getText();
			}
		}

		return "";
	}

	/**
	 * Returns shipping information value.
	 */
	public String getShippingInformation() {

		log.debug("Getting shipping information");

		for (int i = 0; i < paymentAndShippingLabels.size(); i++) {

			String label = paymentAndShippingLabels.get(i).getText();

			if (label.contains("Shipping Information") && i < paymentAndShippingValues.size()) {

				return paymentAndShippingValues.get(i).getText();
			}
		}

		return "";
	}

	// =========================================================
	// PRICE SUMMARY
	// =========================================================

	/**
	 * Returns subtotal text.
	 *
	 * Example: Item total: $29.99
	 */
	public String getSubtotal() {

		log.debug("Getting subtotal");

		String subtotal = subtotalElement.getText().trim();

		log.info("Subtotal retrieved: '{}'", subtotal);

		return subtotal;
	}

//	public double getSubtotalInDouble() {
//
//	    log.debug("Getting subtotal");
//
//	    String subtotalText = subtotalElement.getText().trim();
//
//	    log.info("Raw subtotal text: '{}'", subtotalText);
//
//	    // Example:
//	    // "Item total: $29.99"
//	    //              ↓
//	    // "$29.99"
//	    //              ↓
//	    // "29.99"
//
//	    String amount = subtotalText
//	            .replace("Item total:", "")
//	            .replace("$", "")
//	            .trim();
//
//	    double subtotal = Double.parseDouble(amount);
//
//	    log.info("Subtotal retrieved as double: {}", subtotal);
//
//	    return subtotal;
//	}

	public double getSubtotalInDouble() {

		WaitUtils.waitForVisibility(driver, subtotal);

		String subtotalText = subtotal.getText();

		log.info("Subtotal displayed on Checkout Overview: {}", subtotalText);

		String cleanedSubtotal = subtotalText.replace("Item total:", "").replace("$", "").replace(",", "").trim();

		try {

			return Double.parseDouble(cleanedSubtotal);

		} catch (NumberFormatException e) {

			throw new IllegalStateException("Unable to parse subtotal: " + subtotalText, e);
		}
	}

	/**
	 * Returns tax text.
	 *
	 * Example: Tax: $2.40
	 */
	public String getTax() {

		log.debug("Getting tax");

		String tax = taxElement.getText().trim();

		log.info("Tax retrieved: '{}'", tax);

		return tax;
	}

	public String getTaxText() {

		WaitUtils.waitForVisibility(driver, tax);

		String taxText = tax.getText();

		log.info("Tax text displayed on Checkout Overview: {}", taxText);

		return taxText;
	}

	public double getTaxOnProduct() {

		WaitUtils.waitForVisibility(driver, tax);

		String taxText = tax.getText();

		log.info("Tax displayed on Checkout Overview: {}", taxText);

		String cleanedTax = taxText.replace("Tax:", "").replace("$", "").replace(",", "").trim();

		try {

			return Double.parseDouble(cleanedTax);

		} catch (NumberFormatException e) {

			throw new IllegalStateException("Unable to parse tax value: " + taxText, e);
		}
	}

	public double getTotalInDouble() {

		WaitUtils.waitForVisibility(driver, total);

		String totalText = total.getText();

		log.info("Total displayed on Checkout Overview: {}", totalText);

		String cleanedTotal = totalText.replace("Total:", "").replace("$", "").replace(",", "").trim();

		try {

			return Double.parseDouble(cleanedTotal);

		} catch (NumberFormatException e) {

			throw new IllegalStateException("Unable to parse total value: " + totalText, e);
		}
	}

	/**
	 * * Checks whether a product is displayed on Checkout Step Two. * * @param
	 * productName product name to search for * @return true if product is
	 * displayed, otherwise false
	 */
	public boolean isProductDisplayed_(String productName) {
		waitForProductList();

		for (WebElement product : productItems) {

			String actualProductName = product.findElement(By.className("inventory_item_name")).getText().trim();

			if (actualProductName.equalsIgnoreCase(productName.trim())) {

				log.info("Product '{}' is displayed on Checkout Step Two", productName);

				return true;

			}

		}
		log.info("Product '{}' is not displayed on Checkout Step Two", productName);

		return false;

	}

	/**
	 * Gets the product name displayed on Checkout Step Two.
	 *
	 * @param expectedProductName product name to find
	 * @return actual product name
	 */
	public String getProductName(String expectedProductName) {

		waitForProductList();

		for (WebElement product : productItems) {

			WebElement productNameElement = product.findElement(By.className("inventory_item_name"));

			String actualProductName = productNameElement.getText().trim();

			if (actualProductName.equalsIgnoreCase(expectedProductName.trim())) {

				log.info("Product found on Checkout Step Two: '{}'", actualProductName);

				return actualProductName;
			}
		}

		log.error("Product '{}' was not found on Checkout Step Two", expectedProductName);

		Assert.fail("Product was not found on Checkout Step Two: " + expectedProductName);

		return null;
	}

	/**
	 * * Checks whether the specified product description is displayed * on Checkout
	 * Step Two. * @param expectedDescription expected product description *
	 * 
	 * @return true if the description is displayed, otherwise false
	 */
	public boolean isProductDescriptionDisplayed(String expectedDescription) {

		waitForProductList();

		for (WebElement product : productItems) {

			WebElement descriptionElement = product.findElement(By.className("inventory_item_desc"));

			String actualDescription = descriptionElement.getText().trim();

			if (actualDescription.equalsIgnoreCase(expectedDescription.trim())) {

				log.info("Product description is displayed: '{}'", actualDescription);

				return true;

			}

		}

		log.warn("Product description was not found: '{}'", expectedDescription);

		return false;

	}

	/**
	 * * Checks whether the expected price is displayed * for the specified product.
	 * * @param productName product name *
	 * 
	 * @param expectedPrice expected product price * @return true if the expected
	 *                      price is displayed, otherwise false
	 */
	public boolean isProductPriceDisplayed(String productName, String expectedPrice) {

		waitForProductList();

		for (WebElement product : productItems) {

			WebElement productNameElement = product.findElement(By.className("inventory_item_name"));

			String actualProductName = productNameElement.getText().trim();

			if (actualProductName.equalsIgnoreCase(productName.trim())) {

				WebElement priceElement = product.findElement(By.className("inventory_item_price"));

				String actualPrice = priceElement.getText().trim();
				log.info("Product '{}' price. Expected: '{}', Actual: '{}'", productName, expectedPrice, actualPrice);

				return actualPrice.equalsIgnoreCase(expectedPrice.trim());

			}

		}

		log.warn("Product '{}' was not found while verifying price", productName);

		return false;

	}

	/**
	 * Returns total text.
	 *
	 * Example: Total: $32.39
	 */
//	public String getTotal() {
//
//		log.debug("Getting total");
//
//		return getText(totalLabel);
//	}

	/**
	 * Checks whether subtotal is displayed.
	 */
	public boolean isSubtotalDisplayed() {

		log.debug("Checking subtotal visibility");

		return isDisplayed(subtotalLabel);
	}

	/**
	 * Checks whether tax is displayed.
	 */
	public boolean isTaxDisplayed() {

		log.debug("Checking tax visibility");

		return isDisplayed(taxLabel);
	}

	/**
	 * Checks whether total is displayed.
	 */
	public boolean isTotalDisplayed() {

		log.debug("Checking total visibility");

		return isDisplayed(totalLabel);
	}

	// =========================================================
	// BUTTON ACTIONS
	// =========================================================

	/**
	 * Clicks Finish button.
	 *
	 * Expected navigation:
	 *
	 * checkout-step-two.html -> checkout-complete.html
	 */
	public void clickFinish() {

		log.info("Clicking Finish button on Checkout Step Two page");

		click(finishButton);
	}

	/**
	 * Clicks Cancel button.
	 *
	 * Expected navigation:
	 *
	 * checkout-step-two.html -> inventory.html
	 */
	public void clickCancel() {

		log.info("Clicking Cancel button on Checkout Step Two page");

		click(cancelButton);
	}

	/**
	 * Checks whether Finish button is displayed.
	 */
	public boolean isFinishButtonDisplayed() {

		log.debug("Checking Finish button visibility");

		return isDisplayed(finishButton);
	}

	/**
	 * Checks whether Cancel button is displayed.
	 */
	public boolean isCancelButtonDisplayed() {

		log.debug("Checking Cancel button visibility");

		return isDisplayed(cancelButton);
	}

	/**
	 * Checks whether Finish button is enabled.
	 */
	public boolean isFinishButtonEnabled() {

		log.debug("Checking Finish button enabled state");

		return finishButton.isEnabled();
	}

	/**
	 * Checks whether Cancel button is enabled.
	 */
	public boolean isCancelButtonEnabled() {

		log.debug("Checking Cancel button enabled state");

		return cancelButton.isEnabled();
	}

	// =========================================================
	// PAGE READINESS
	// =========================================================

	/**
	 * Checks whether all major Checkout Step Two components are displayed.
	 */
	public boolean isPageReadyForCheckoutCompletion() {

		log.debug("Checking Checkout Step Two page readiness");

		return isCheckoutStepTwoPageDisplayed() && isCheckoutOverviewDisplayed() && isCartItemDisplayed()
				&& isSubtotalDisplayed() && isTaxDisplayed() && isTotalDisplayed() && isFinishButtonDisplayed()
				&& isCancelButtonDisplayed();
	}

	/**
	 * Verifies that the Checkout Step Two page is fully ready.
	 *
	 * A Checkout Step Two page is considered ready when: - Current URL contains
	 * checkout-step-two.html - Page title is "Checkout: Overview" - Checkout
	 * overview container is displayed - At least one checkout item is displayed -
	 * Finish button is displayed - Cancel button is displayed
	 *
	 * @return true if Checkout Step Two page is ready, otherwise false
	 */
	public boolean isCheckoutStepTwoPageReady() {

		log.info("Checking whether Checkout Step Two page is ready");

		try {

			boolean urlValid = driver.getCurrentUrl().contains("checkout-step-two.html");

			boolean titleValid = "Checkout: Overview".equalsIgnoreCase(getPageTitle());

			boolean overviewDisplayed = checkoutOverview != null && checkoutOverview.isDisplayed();

			boolean productsDisplayed = productItems != null && !productItems.isEmpty();

			boolean finishDisplayed = finishButton != null && finishButton.isDisplayed();

			boolean cancelDisplayed = cancelButton != null && cancelButton.isDisplayed();

			boolean pageReady = urlValid && titleValid && overviewDisplayed && productsDisplayed && finishDisplayed
					&& cancelDisplayed;

			log.info(
					"Checkout Step Two readiness: {} | URL: {} | Title: {} | "
							+ "Overview: {} | Products: {} | Finish: {} | Cancel: {}",
					pageReady, urlValid, titleValid, overviewDisplayed, productsDisplayed, finishDisplayed,
					cancelDisplayed);

			return pageReady;

		} catch (Exception e) {

			log.error("Failed to verify Checkout Step Two page readiness", e);

			return false;
		}
	}

	/**
	 * Waits until the product list is loaded and contains at least one product.
	 */
	public void waitForProductList() {

		log.info("Waiting for product list to be displayed");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(d -> !productItems.isEmpty() && productItems.stream().anyMatch(WebElement::isDisplayed));

		log.info("Product list is displayed successfully. Product count: {}", productItems.size());
	}

	/**
	 * * Gets the price of the specified product * from Checkout Step Two. * @param
	 * productName product name *
	 * 
	 * @return actual product price
	 */
	public String getProductPrice(String productName) {

		waitForProductList();

		for (WebElement product : productItems) {

			WebElement productNameElement = product.findElement(By.className("inventory_item_name"));

			String actualProductName = productNameElement.getText().trim();

			if (actualProductName.equalsIgnoreCase(productName.trim())) {

				WebElement priceElement = product.findElement(By.className("inventory_item_price"));

				String actualPrice = priceElement.getText().trim();
				log.info("Product '{}' price found: '{}'", productName, actualPrice);

				return actualPrice;

			}

		}
		log.error("Product '{}' was not found on Checkout Step Two", productName);

		Assert.fail("Product was not found on Checkout Step Two: " + productName);
		return null;

	}

	/**
	 * * Gets all product prices displayed on Checkout Step Two. * * @return list of
	 * product prices
	 */
	public List<String> getAllProductPrices() {

		waitForProductList();

		List<String> prices = new ArrayList<>();

		for (WebElement product : productItems) {

			WebElement priceElement = product.findElement(By.className("inventory_item_price"));

			String price = priceElement.getText().trim();
			prices.add(price);

			log.info("Product price found: '{}'", price);
		}
		return prices;
	}

	/** * Gets Total. */
	public String getTotal() {

		String total = totalElement.getText().trim();

		log.info("Total retrieved: '{}'", total);

		return total;

	}

	/**
	 * * Gets all product names displayed on Checkout Step Two. * @return list of
	 * product names
	 */
	public List<String> getAllProductNames() {

		waitForProductList();

		List<String> productNames = new ArrayList<>();

		for (WebElement product : productItems) {

			WebElement productNameElement = product.findElement(By.className("inventory_item_name"));

			String productName = productNameElement.getText().trim();

			productNames.add(productName);

			log.info("Checkout product name found: '{}'", productName);

		}
		return productNames;

	}

	/**
	 * Checks whether the Checkout Complete page is displayed.
	 *
	 * @return true if Checkout Complete page is displayed, otherwise false
	 */
	public boolean isCheckoutCompletePageDisplayed() {

		log.debug("Checking whether Checkout Complete page is displayed");

		try {

			return driver.getCurrentUrl().contains("checkout-complete.html") && isDisplayed(pageTitle)
					&& "Checkout: Complete!".equalsIgnoreCase(getText(pageTitle));

		} catch (Exception e) {

			log.debug("Checkout Complete page is not displayed");

			return false;
		}
	}

	/**
	 * Returns the order confirmation message.
	 */
	public String getConfirmationMessage() {

		log.debug("Getting order confirmation message");

		return getText(confirmationMessage).trim();
	}

	public void refreshPage() {

		log.info("Refreshing inventory page");

		driver.navigate().refresh();

	}

	public List<String> getProductPricesInCheckoutOvervview() {

		WaitUtils.waitForVisibility(driver, productPrices.get(0));

		return productPrices.stream().map(WebElement::getText).map(price -> price.replace("$", "").trim())
				.collect(Collectors.toList());
	}

	public double getSubtotalOfProductPrice() {

		WaitUtils.waitForVisibility(driver, subtotal);

		String subtotalText = subtotal.getText();

		log.debug("Subtotal text displayed: {}", subtotalText);

		return Double.parseDouble(subtotalText.replace("Item total: $", "").trim());
	}

	public void doubleClickFinishButton() {

		log.info("Double clicking Finish button");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement finishButtonElement = wait.until(ExpectedConditions.elementToBeClickable(finishButton));

		new Actions(driver).doubleClick(finishButtonElement).perform();

		log.info("Finish button double-click performed");
	}

	public boolean isCustomerCheckoutInformationDisplayed() {

		try {

			log.info("Checking customer checkout review information");

			boolean paymentInformationDisplayed = driver.findElements(By.cssSelector(".summary_info")).stream()
					.anyMatch(WebElement::isDisplayed);

			boolean paymentLabelDisplayed = driver.findElements(By.cssSelector(".summary_info_label")).stream()
					.anyMatch(WebElement::isDisplayed);

			boolean valueDisplayed = driver.findElements(By.cssSelector(".summary_value_label")).stream()
					.anyMatch(WebElement::isDisplayed);

			boolean displayed = paymentInformationDisplayed && paymentLabelDisplayed && valueDisplayed;

			log.info("Customer checkout review information displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.error("Unable to verify customer checkout review information", e);

			return false;
		}
	}

	public boolean areCheckoutInformationFieldsEditable() {

		try {

			log.info("Checking for editable checkout information fields");

			List<WebElement> inputFields = driver.findElements(By.cssSelector(
					"input[name='firstName'], " + "input[name='lastName'], " + "input[name='postalCode']"));

			boolean editable = inputFields.stream().anyMatch(WebElement::isDisplayed);

			log.info("Editable checkout information fields present: {}", editable);

			return editable;

		} catch (Exception e) {

			log.error("Error checking editable checkout information fields", e);

			return false;
		}
	}

	public boolean isPaymentInformationDisplayed_() {

		try {

			log.info("Checking Payment Information");

			WebElement paymentLabel = driver.findElement(By.xpath(
					"//div[contains(@class,'summary_info_label') " + "and normalize-space()='Payment Information']"));

			boolean displayed = paymentLabel.isDisplayed();

			log.info("Payment Information displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.error("Payment Information is not displayed", e);

			return false;
		}
	}

	public boolean isShippingInformationDisplayed_() {

		try {

			log.info("Checking Shipping Information");

			WebElement shippingLabel = driver.findElement(By.xpath(
					"//div[contains(@class,'summary_info_label') " + "and normalize-space()='Shipping Information']"));

			boolean displayed = shippingLabel.isDisplayed();

			log.info("Shipping Information displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.error("Shipping Information is not displayed", e);

			return false;
		}
	}

	public boolean isOrderSummaryDisplayed() {

		try {

			log.info("Checking order summary");

			WebElement summaryContainer = driver.findElement(By.cssSelector(".summary_info"));

			boolean displayed = summaryContainer.isDisplayed();

			log.info("Order summary displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.error("Order summary is not displayed", e);

			return false;
		}
	}

	public boolean _isProductDisplayed(String productName) {

		try {

			WebElement product = driver.findElement(By.xpath(
					"//div[contains(@class,'inventory_item_name') " + "and normalize-space()='" + productName + "']"));

			return product.isDisplayed();

		} catch (Exception e) {

			log.error("Product '{}' was not found in Checkout Overview", productName, e);

			return false;
		}
	}

	/** * Safely creates an XPath string literal. */
	private String xpathLiteral(String value) {
		if (!value.contains("'")) {
			return "'" + value + "'";
		}
		if (!value.contains("\"")) {
			return "\"" + value + "\"";

		}
		String[] parts = value.split("'");
		StringBuilder result = new StringBuilder("concat(");

		for (int i = 0; i < parts.length; i++) {
			if (i > 0) {
				result.append(", \"'\", ");

			}
			result.append("'").append(parts[i]).append("'");
		}
		result.append(")");
		return result.toString();

	}

	public String getProductPriceInCheckoutOverview(String productName) {

		try {

			By productPriceLocator = By.xpath("//div[@data-test='inventory-item']"
					+ "[.//div[@data-test='inventory-item-name' " + "and normalize-space()=" + xpathLiteral(productName)
					+ "]]" + "//div[@data-test='inventory-item-price']");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLocator));

			String price = priceElement.getText().trim();

			log.info("Checkout Overview price for '{}' is '{}'", productName, price);

			return price;

		} catch (Exception e) {

			log.error("Unable to retrieve Checkout Overview price for '{}'", productName, e);

			return null;
		}
	}

	/**
	 * Checks whether a specific product is displayed on Checkout Step Two /
	 * Checkout Overview.
	 *
	 * @param productName product name to verify
	 * @return true if product is displayed, otherwise false
	 */
	public boolean isProductDisplayedInCheckoutOverview(String productName) {

		try {

			log.info("Checking product '{}' in Checkout Overview", productName);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			By productLocator = By.xpath("//div[contains(@class,'inventory_item_name') " + "and normalize-space()="
					+ xpathLiteral(productName) + "]");

			WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));

			boolean displayed = product.isDisplayed();

			log.info("Product '{}' displayed in Checkout Overview: {}", productName, displayed);

			return displayed;

		} catch (Exception e) {

			log.error("Product '{}' was not found in Checkout Overview", productName, e);

			return false;
		}
	}

//	public double getSubtotalDouble() {
//
//		log.debug("Getting subtotal");
//
//		String subtotalText = subtotalElement.getText().trim();
//
//		log.info("Raw subtotal text: '{}'", subtotalText);
//
//		/*
//		 * SauceDemo DOM:
//		 *
//		 * Item total: $55.97
//		 *
//		 * Extract only the numeric amount.
//		 */
//		String amount = subtotalText.replaceAll("[^0-9.]", "").trim();
//
//		if (amount.isEmpty()) {
//
//			log.error("Unable to extract numeric subtotal from: '{}'", subtotalText);
//
//			throw new IllegalStateException("Invalid Checkout Overview subtotal format: " + subtotalText);
//		}
//
//		try {
//
//			double subtotal = Double.parseDouble(amount);
//
//			log.info("Subtotal retrieved successfully: {}", subtotal);
//
//			return subtotal;
//
//		} catch (NumberFormatException e) {
//
//			log.error("Unable to convert subtotal '{}' to double", amount, e);
//
//			throw new IllegalStateException("Invalid Checkout Overview subtotal format: " + subtotalText, e);
//		}
//	}
//	

	public double getSubtotalDouble() {

		log.debug("Getting subtotal");

		String subtotalText = subtotalElement.getText().trim();

		log.info("Raw subtotal text: '{}'", subtotalText);

		/*
		 * SauceDemo:
		 *
		 * Item total: $55.97
		 *
		 * Extract:
		 *
		 * 55.97
		 */

		String amount = subtotalText.replaceAll("[^0-9.]", "");

		if (amount.isEmpty()) {

			throw new IllegalStateException("Invalid Checkout Overview subtotal format: " + subtotalText);
		}

		double subtotal;

		try {

			subtotal = Double.parseDouble(amount);

		} catch (NumberFormatException e) {

			throw new IllegalStateException("Invalid Checkout Overview subtotal format: " + subtotalText, e);
		}

		log.info("Parsed Checkout Overview subtotal: {}", subtotal);

		return subtotal;
	}

	/**
	 * * Calculates the total of all product prices displayed * on the Checkout
	 * Overview page. * * Example: * $29.99 * $9.99 * $15.99 * * Total = 55.97
	 */
	public double getCartItemsTotal() {
		log.debug("Getting total of Checkout Overview item prices");

		if (cartItemPriceElements == null || cartItemPriceElements.isEmpty()) {
			log.warn("No Checkout Overview item prices found");
			return 0.0;

		}
		double total = 0.0;
		for (WebElement priceElement : cartItemPriceElements) {
			String priceText = priceElement.getText().trim();
			log.debug("Checkout Overview item price: '{}'", priceText);
			// "$29.99" -> "29.99"
			String amount = priceText.replaceAll("[^0-9.]", "");
			if (amount.isEmpty()) {
				throw new IllegalStateException("Invalid product price format: " + priceText);

			}
			double price;
			try {
				price = Double.parseDouble(amount);

			} catch (NumberFormatException e) {
				throw new IllegalStateException("Unable to convert product price to double: " + priceText, e);

			}
			total += price;
			log.debug("Price added: {} | Running total: {}", price, total);

		}
		log.info("Total of Checkout Overview item prices: {}", total);
		return total;

	}

//	public double getSubtotal() {
//
//	    log.debug("Getting subtotal");
//
//	    String subtotalText = subtotalElement.getText().trim();
//
//	    log.info("Raw subtotal text: '{}'", subtotalText);
//
//	    // Extract numeric value from text such as:
//	    // "Item total: $29.99"
//	    String amount = subtotalText.replaceAll("[^0-9.]", "");
//
//	    if (amount.isEmpty()) {
//	        throw new IllegalStateException(
//	                "Unable to extract subtotal from: " + subtotalText
//	        );
//	    }
//
//	    double subtotal = Double.parseDouble(amount);
//
//	    log.info("Subtotal retrieved as double: {}", subtotal);
//
//	    return subtotal;
//	}
//	

	/**
	 * Verify Checkout Step Two page is loaded.
	 */
//	public boolean isCheckoutStepTwoPageReady() {
//
//		try {
//
//			log.info("Checking whether Checkout Step Two page is ready");
//
//			WaitUtils.waitForUrlContains(driver, "checkout-step-two.html");
//
//			WaitUtils.waitForVisibility(driver, By.cssSelector(".title"));
//
//			WaitUtils.waitForVisibility(driver, By.cssSelector(".checkout_summary_container"));
//
//			String currentUrl = driver.getCurrentUrl();
//
//			String title = pageTitle.getText().trim();
//
//			boolean urlCorrect = currentUrl.contains("checkout-step-two.html");
//
//			boolean titleCorrect = "Checkout: Overview".equalsIgnoreCase(title);
//
//			boolean summaryDisplayed = checkoutSummaryContainer.isDisplayed();
//
//			boolean ready = urlCorrect && titleCorrect && summaryDisplayed;
//
//			log.info("Checkout Step Two readiness: url={}, title={}, summary={}, ready={}", urlCorrect, titleCorrect,
//					summaryDisplayed, ready);
//
//			return ready;
//
//		} catch (Exception e) {
//
//			log.error("Checkout Step Two page is not ready. Current URL: {}", driver.getCurrentUrl(), e);
//
//			return false;
//		}
//	}

	/**
	 * Gets Payment Information displayed on Checkout Step Two. * @return payment
	 * information text
	 */
//	public String getPaymentInformation() { 
//		
//		WebElement paymentInformationElement = driver.findElement( By.cssSelector(".summary_info .summary_value") ); 
//		
//		String paymentInformation = paymentInformationElement.getText().trim(); 
//		
//		log.info( "Payment Information retrieved: '{}'", paymentInformation ); 
//		
//		return paymentInformation; 
//		
//	}

}