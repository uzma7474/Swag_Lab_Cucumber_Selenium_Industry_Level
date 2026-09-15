
package pages;

import base.BasePage;
import config.EnvironmentManager;
import utils.WaitUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(CartPage.class);

	// =========================================================
	// PAGE LOCATORS
	// =========================================================

	/**
	 * Cart page heading Example: Your Cart
	 */
	@FindBy(css = ".title")
	private WebElement pageTitle;

	/**
	 * Cart list container
	 */
	@FindBy(css = ".cart_list")
	private WebElement cartList;

	/**
	 * All cart items
	 */
	@FindBy(css = ".cart_item")
	private List<WebElement> cartItems;

	/**
	 * Product names inside cart
	 */
	@FindBy(css = ".inventory_item_name")
	private List<WebElement> productNames;

	/**
	 * Product descriptions
	 */
	@FindBy(css = ".inventory_item_desc")
	private List<WebElement> productDescriptions;

	/**
	 * Product prices
	 */
	@FindBy(css = ".inventory_item_price")
	private List<WebElement> productPrices;

	/**
	 * Quantity of each cart item
	 */
	@FindBy(css = ".cart_quantity")
	private List<WebElement> cartQuantities;

	/**
	 * Remove buttons
	 */
	@FindBy(css = "[data-test^='remove']")
	private List<WebElement> removeButtons;

	/**
	 * Continue Shopping button
	 */
	@FindBy(id = "continue-shopping")
	private WebElement continueShoppingButton;

	/**
	 * Checkout button
	 */
	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	/**
	 * Shopping cart container
	 */
	@FindBy(id = "shopping_cart_container")
	private WebElement cartContainer;

	/**
	 * Shopping cart badge
	 */
	@FindBy(css = ".shopping_cart_badge")
	private WebElement shoppingCartBadge;

	/**
	 * Shopping cart link
	 */
	@FindBy(css = ".shopping_cart_link")
	private WebElement shoppingCartLink;

	/*
	 * Shopping Icon is display
	 */
	@FindBy(id = "shopping_cart_container")
	private WebElement shoppingCartIcon;

	/*
	 * * Each product in cart: * <div class="cart_item" data-test="inventory-item">
	 */
//	@FindBy(css = "div.cart_item[data-test='inventory-item']") 
//	private List<WebElement> cartItems;
//	
	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public CartPage() {

		super();

		PageFactory.initElements(driver, this);

		log.debug("CartPage initialized");
	}

	// =========================================================
	// NAVIGATION
	// =========================================================

	/**
	 * Opens the Cart page directly.
	 *
	 * Normally the recommended flow is:
	 *
	 * Login -> Inventory -> Add Product -> Cart
	 *
	 * This method is useful when the test specifically needs to navigate directly
	 * to the cart page.
	 */
	public void open() {

		log.info("Navigating to SauceDemo cart page");

		navigateTo(EnvironmentManager.getBaseUrl() + "/cart.html");
	}

	/**
	 * Navigates to the Cart page by clicking the shopping cart icon.
	 */
	public void openCartFromHeader() {

		log.info("Opening cart from shopping cart icon");

		click(shoppingCartLink);
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

	/**
	 * Verifies whether the Cart page is displayed.
	 */
	public boolean isCartPageDisplayed() {

		String currentUrl = getCurrentUrl();

		boolean urlValid = currentUrl.contains("/cart.html");

		boolean titleDisplayed = isDisplayed(pageTitle);

		boolean cartListDisplayed = isDisplayed(cartList);

		log.debug("Cart page validation - URL: {}, Title: {}, Cart List: {}", urlValid, titleDisplayed,
				cartListDisplayed);

		return urlValid && titleDisplayed && cartListDisplayed;
	}

	/**
	 * Returns the Cart page heading.
	 */
	public String getPageTitle() {

		return getText(pageTitle);
	}

	/**
	 * Verifies Cart page heading.
	 */
	public boolean isPageTitleDisplayed() {

		return isDisplayed(pageTitle);
	}

	/**
	 * Verifies cart list.
	 */
	public boolean isCartListDisplayed() {

		return isDisplayed(cartList);
	}

	/**
	 * Returns the Cart page heading.
	 */
	public String getCartPageHeading() {

		log.debug("Getting cart page heading");

		return getText(pageTitle);
	}

	// =========================================================
	// CART ITEMS
	// =========================================================

	/**
	 * Returns total number of products in the cart.
	 */
//    public int getCartItemCount() {
//
//        return cartItems.size();
//    }

	public boolean isShoppingCartIconDisplayed() {
		return isDisplayed(shoppingCartIcon);
	}

	/**
	 * Verifies whether the cart contains at least one product.
	 */
	public boolean areCartItemsDisplayed() {

		return !cartItems.isEmpty();
	}

	/**
	 * Returns all product names present in the cart.
	 */
	public List<String> getProductNames() {

		return productNames.stream().map(WebElement::getText).toList();
	}

	/**
	 * Verifies whether a particular product exists in the cart.
	 */
	public boolean isProductDisplayed(String productName) {

		validateProductName(productName);

		String normalizedProductName = productName.trim();

		return productNames.stream()
				.anyMatch(element -> element.getText().trim().equalsIgnoreCase(normalizedProductName));
	}

	/**
	 * Returns product name based on index.
	 */
	public String getProductName(int index) {

		validateIndex(index, productNames.size());

		return getText(productNames.get(index));
	}

	// =========================================================
	// PRODUCT DESCRIPTION
	// =========================================================

	/**
	 * Returns all product descriptions.
	 */
	public List<String> getProductDescriptions() {

		return productDescriptions.stream().map(WebElement::getText).toList();
	}

	/**
	 * Returns description of a specific product.
	 */
	public String getProductDescription(String productName) {

		validateProductName(productName);

		WebElement productCard = getCartItem(productName);

		WebElement description = productCard.findElement(By.cssSelector(".inventory_item_desc"));

		return getText(description);
	}

	// =========================================================
	// PRODUCT PRICE
	// =========================================================

	/**
	 * Returns all product prices from cart.
	 */
	public List<String> getProductPrices() {

		return productPrices.stream().map(WebElement::getText).toList();
	}

	/**
	 * Returns product price using product name.
	 */
	public String getProductPrice(String productName) {

		validateProductName(productName);

		WebElement productCard = getCartItem(productName);

		WebElement price = productCard.findElement(By.cssSelector(".inventory_item_price"));

		return getText(price);
	}

	public String getProductPrice_(String productName) {

		log.info("Getting price for product: {}", productName);

		try {
			waitForCartPage();

			List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));

			for (WebElement cartItem : cartItems) {

				String actualProductName = cartItem.findElement(By.cssSelector(".inventory_item_name")).getText()
						.trim();

				if (actualProductName.equalsIgnoreCase(productName)) {

					String price = cartItem.findElement(By.cssSelector(".inventory_item_price")).getText().trim();

					log.info("Price of product '{}' is '{}'", productName, price);

					return price;
				}
			}

			throw new IllegalArgumentException("Product not found in cart: " + productName);

		} catch (Exception e) {

			log.error("Failed to get price for product '{}': {}", productName, e.getMessage(), e);

			throw e;
		}
	}

	public int getProductQuantity_(String productName) {

		log.info("Getting quantity for product: {}", productName);

		try {

			waitForCartPage();

			List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));

			for (WebElement cartItem : cartItems) {

				String actualProductName = cartItem.findElement(By.cssSelector(".inventory_item_name")).getText()
						.trim();

				if (actualProductName.equalsIgnoreCase(productName)) {

					String quantityText = cartItem.findElement(By.cssSelector(".cart_quantity")).getText().trim();

					int quantity = Integer.parseInt(quantityText);

					log.info("Product '{}' quantity is {}", productName, quantity);

					return quantity;
				}
			}

			throw new IllegalArgumentException("Product not found in cart: " + productName);

		} catch (Exception e) {

			log.error("Failed to get quantity for product '{}': {}", productName, e.getMessage(), e);

			throw e;
		}
	}

	/**
	 * Returns product price using index.
	 */
	public String getProductPrice(int index) {

		validateIndex(index, productPrices.size());

		return getText(productPrices.get(index));
	}

	// =========================================================
	// CART QUANTITY
	// =========================================================

	/**
	 * Returns all cart quantities.
	 */
	public List<String> getCartQuantities() {

		return cartQuantities.stream().map(WebElement::getText).toList();
	}

	/**
	 * Returns quantity of a particular product.
	 */
	public int getProductQuantity(String productName) {

		validateProductName(productName);

		WebElement productCard = getCartItem(productName);

		WebElement quantity = productCard.findElement(By.cssSelector(".cart_quantity"));

		return Integer.parseInt(getText(quantity));
	}

	/**
	 * Returns quantity using product index.
	 */
	public int getProductQuantity(int index) {

		validateIndex(index, cartQuantities.size());

		return Integer.parseInt(getText(cartQuantities.get(index)));
	}

	// =========================================================
	// REMOVE PRODUCT
	// =========================================================

	/**
	 * Removes a specific product from the cart.
	 */
	public void removeProduct(String productName) {

		validateProductName(productName);

		log.info("Removing product from cart: {}", productName);

		WebElement productCard = getCartItem(productName);

		WebElement removeButton = productCard.findElement(By.cssSelector("[data-test^='remove']"));

		log.info("Try To click on remove button");
		click(removeButton);
		log.info("Clicked remove button");
	}

	/**
	 * Removes product using index.
	 */
	public void removeProduct(int index) {

		validateIndex(index, cartItems.size());

		log.info("Removing cart product at index: {}", index);

		WebElement productCard = cartItems.get(index);

		WebElement removeButton = productCard.findElement(By.cssSelector("[data-test^='remove']"));

		click(removeButton);
	}

	public void removeProductCart(String productName) {

		log.info("Attempting to remove product from cart: {}", productName);

		try {

			waitForCartPage();

			List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));

			// Cart is already empty
			if (cartItems.isEmpty()) {
				log.info("Cart is already empty. Product '{}' is not available to remove.", productName);
				return;
			}

			for (WebElement cartItem : cartItems) {

				WebElement productNameElement = cartItem.findElement(By.cssSelector(".inventory_item_name"));

				String actualProductName = productNameElement.getText().trim();

				if (actualProductName.equalsIgnoreCase(productName)) {

					WebElement removeButton = cartItem.findElement(By.cssSelector("button[id^='remove-']"));

					log.info("Product '{}' found in cart. Clicking Remove button.", productName);

					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

					wait.until(ExpectedConditions.elementToBeClickable(removeButton));

					removeButton.click();

					log.info("Product '{}' removed successfully from cart.", productName);

					return;
				}
			}

			// Product does not exist in cart
			log.info("Product '{}' is not present in cart. " + "No remove action performed.", productName);

		} catch (Exception e) {

			log.error("Failed while attempting to remove product '{}': {}", productName, e.getMessage(), e);

			throw e;
		}
	}

	/**
	 * Verifies whether Remove button is displayed for a product.
	 */
	public boolean isRemoveButtonDisplayed(String productName) {

		validateProductName(productName);

		WebElement productCard = getCartItem(productName);

		WebElement removeButton = productCard.findElement(By.cssSelector("[data-test^='remove']"));

		return isDisplayed(removeButton);
	}

	// =========================================================
	// CONTINUE SHOPPING
	// =========================================================

	/**
	 * Clicks Continue Shopping button.
	 */
	public void clickContinueShopping() {

		log.info("Clicking Continue Shopping");

		click(continueShoppingButton);
	}

	/**
	 * Verifies Continue Shopping button.
	 */
	public boolean isContinueShoppingButtonDisplayed() {

		log.debug("Checking whether Continue Shopping button is displayed");

		return isDisplayed(continueShoppingButton);
	}

	// =========================================================
	// CHECKOUT
	// =========================================================

	/**
	 * Clicks Checkout button.
	 */
	public void clickCheckout() {

		log.info("Clicking Checkout button");

		click(checkoutButton);
	}

	/**
	 * Verifies Checkout button.
	 */
	public boolean isCheckoutButtonDisplayed() {

		return isDisplayed(checkoutButton);
	}

	// =========================================================
	// SHOPPING CART HEADER
	// =========================================================

	/**
	 * Verifies shopping cart icon.
	 */
	public boolean isShoppingCartDisplayed() {

		return isDisplayed(shoppingCartLink);
	}

	/**
	 * Verifies shopping cart container.
	 */
	public boolean isCartContainerDisplayed() {

		return isDisplayed(cartContainer);
	}

	/**
	 * Verifies shopping cart badge.
	 */
	public boolean isCartBadgeDisplayed() {

		return isDisplayed(shoppingCartBadge);
	}

	/**
	 * Returns shopping cart badge count.
	 *
	 * If badge is not displayed, returns 0.
	 */
	public int getCartBadgeCount() {

		if (!isCartBadgeDisplayed()) {

			return 0;
		}

		return Integer.parseInt(getText(shoppingCartBadge));
	}

	// =========================================================
	// CART STATE
	// =========================================================

	/**
	 * Verifies whether cart is empty.
	 */
	public boolean isCartEmpty() {

		return cartItems.isEmpty();
	}

	/**
	 * Verifies whether cart contains products.
	 */
	public boolean isCartNotEmpty() {

		return !cartItems.isEmpty();
	}

	/**
	 * Returns the number of items currently present in cart.
	 */
	public int getNumberOfItemsInCart() {

		return cartItems.size();
	}

	// =========================================================
	// PRODUCT CARD
	// =========================================================

	/**
	 * Returns a cart item WebElement based on product name.
	 *
	 * Example:
	 *
	 * Sauce Labs Backpack Sauce Labs Bike Light
	 */
	private WebElement getCartItem(String productName) {

		validateProductName(productName);

		By productLocator = By
				.xpath("//div[contains(@class,'cart_item')]" + "[.//div[contains(@class,'inventory_item_name') "
						+ "and normalize-space()=" + xpathLiteral(productName) + "]]");

		List<WebElement> matchingItems = driver.findElements(productLocator);

		if (matchingItems.isEmpty()) {

			throw new IllegalArgumentException("Product not found in cart: " + productName);
		}

		return matchingItems.get(0);
	}

	// =========================================================
	// VALIDATION HELPERS
	// =========================================================

	/**
	 * Validates product name.
	 */
	private void validateProductName(String productName) {

		if (productName == null || productName.isBlank()) {

			throw new IllegalArgumentException("Product name must not be null or blank");
		}
	}

	/**
	 * Validates list index.
	 */
	private void validateIndex(int index, int size) {

		if (index < 0 || index >= size) {

			throw new IndexOutOfBoundsException("Invalid index: " + index + ". Valid range: 0 to " + (size - 1));
		}
	}

	// =========================================================
	// XPATH HELPER
	// =========================================================

	/**
	 * Safely creates an XPath string literal.
	 *
	 * Handles:
	 *
	 * 1. Text containing single quote 2. Text containing double quote 3. Text
	 * containing both
	 */
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

	// =========================================================
	// REFRESH
	// =========================================================

	/**
	 * Refreshes Cart page.
	 */
	public void refresh() {

		log.info("Refreshing cart page");

		driver.navigate().refresh();
	}

	private void waitForCartPage() {

		WaitUtils.waitForUrlContains("/cart.html");
	}

	public int getCartItemCount() {

		log.info("Getting number of products displayed in cart");

		try {

			waitForCartPage();

			int count = cartItems.size();

			log.info("Number of products displayed in cart: {}", count);

			return count;

		} catch (Exception e) {

			log.error("Failed to get cart item count: {}", e.getMessage(), e);

			return 0;
		}
	}

	public int getCartItemCounts() {

		log.info("========== CART COUNT DEBUG ==========");

		log.info("Current URL: {}", driver.getCurrentUrl());

		log.info("Getting number of products displayed in cart");

		waitForCartPage();

		List<WebElement> items = driver.findElements(By.cssSelector(".cart_item"));

		int count = items.size();

		log.info("Number of products displayed in cart: {}", count);

		return count;
	}

	public String getCartBadgeText() {

		log.info("Getting Shopping Cart badge text");

		try {

			List<WebElement> badges = driver.findElements(By.cssSelector(".shopping_cart_badge"));

			if (badges.isEmpty()) {

				log.info("Shopping Cart badge is not displayed");

				return null;
			}

			String badgeText = badges.get(0).getText().trim();

			log.info("Shopping Cart badge text: {}", badgeText);

			return badgeText;

		} catch (Exception e) {

			log.error("Failed to get Shopping Cart badge text: {}", e.getMessage(), e);

			throw e;
		}
	}

	public void removeProductFromPage(String productName) {

		log.info("==================================================");
		log.info("Removing product from Shopping Cart: {}", productName);
		log.info("==================================================");

		try {

			waitForCartPage();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));

			log.info("Products currently displayed in cart: {}", cartItems.size());

			for (WebElement cartItem : cartItems) {

				String actualProductName = cartItem.findElement(By.cssSelector(".inventory_item_name")).getText()
						.trim();

				log.info("Checking cart product: '{}'", actualProductName);

				if (actualProductName.equalsIgnoreCase(productName)) {

					WebElement removeButton = cartItem.findElement(By.cssSelector("button[id^='remove-']"));

					log.info("Product '{}' found. Clicking Remove button.", productName);

					wait.until(ExpectedConditions.elementToBeClickable(removeButton));

					removeButton.click();

					log.info("Remove button clicked for '{}'", productName);

					// Wait until the product disappears from the cart
					wait.until(driver -> {

						List<WebElement> remainingItems = driver.findElements(By.cssSelector(".cart_item"));

						for (WebElement item : remainingItems) {

							String remainingProductName = item.findElement(By.cssSelector(".inventory_item_name"))
									.getText().trim();

							if (remainingProductName.equalsIgnoreCase(productName)) {

								return false;
							}
						}

						return true;
					});

					log.info("Product '{}' removed successfully.", productName);

					return;
				}
			}

			throw new IllegalArgumentException("Product not found in cart: " + productName);

		} catch (Exception e) {

			log.error("Failed to remove product '{}'. Current URL: {}", productName, driver.getCurrentUrl(), e);

			throw e;
		}
	}

	public boolean isProductDisplayedInCart(String productName) {

		log.info("Checking whether product '{}' is displayed in cart", productName);

		try {
			return cartItems.stream().anyMatch(item -> item.findElement(By.className("inventory_item_name")).getText()
					.equalsIgnoreCase(productName));

		} catch (Exception e) {
			log.info("Product '{}' is not displayed in cart", productName);
			return false;
		}
	}

	public boolean isProductDescriptionDisplayed(String productName) {
		if (productName == null || productName.trim().isEmpty()) {
			throw new IllegalArgumentException("Product name must not be null or blank");

		}

		for (WebElement cartItem : cartItems) {
			WebElement nameElement = cartItem.findElement(By.cssSelector(".inventory_item_name"));
			if (nameElement.getText().trim().equalsIgnoreCase(productName.trim())) {
				WebElement descriptionElement = cartItem.findElement(By.cssSelector(".inventory_item_desc"));
				String description = descriptionElement.getText().trim();
				log.info("Product description for '{}': {}", productName, description);
				return !description.isEmpty() && descriptionElement.isDisplayed();
			}

		}
		throw new IllegalArgumentException("Product '" + productName + "' was not found in the cart");

	}

	public String getFirstProductName() {

		log.info("Getting the first product name from the cart");

		try {

			waitForCartPage();

			List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));

			if (cartItems.isEmpty()) {

				throw new IllegalStateException("Cannot remove first product because the cart is empty");
			}

			String productName = cartItems.get(0).findElement(By.cssSelector(".inventory_item_name")).getText().trim();

			log.info("First product in cart: '{}'", productName);

			return productName;

		} catch (Exception e) {

			log.error("Failed to get the first product from the cart. Current URL: {}", driver.getCurrentUrl(), e);

			throw e;
		}
	}

	public String getLastProductName() {

		log.info("Getting the last product name from the cart");

		try {

			waitForCartPage();

			List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));

			if (cartItems.isEmpty()) {

				throw new IllegalStateException("Cannot remove last product because the cart is empty");
			}

			WebElement lastCartItem = cartItems.get(cartItems.size() - 1);

			String productName = lastCartItem.findElement(By.cssSelector(".inventory_item_name")).getText().trim();

			log.info("Last product in cart: '{}'", productName);

			return productName;

		} catch (Exception e) {

			log.error("Failed to get the last product from the cart. Current URL: {}", driver.getCurrentUrl(), e);

			throw e;
		}
	}

//	public void addAllProductsToCart_() {
//		log.info("Finding all Add to Cart buttons");
//		List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("button[data-test^='add-to-cart']"));
//
//		if (addToCartButtons.isEmpty()) {
//			log.warn("No Add to Cart buttons were found");
//			throw new IllegalStateException("No products are available to add to the cart");
//
//		}
//		log.info("Found {} products available to add to the cart", addToCartButtons.size());
//
//		for (int i = 0; i < addToCartButtons.size(); i++) {
//			WebElement addToCartButton = addToCartButtons.get(i);
//			log.debug("Adding product {} of {} to cart", i + 1, addToCartButtons.size());
//			click(addToCartButton);
//
//		}
//		log.info("Successfully added {} products to the cart", addToCartButtons.size());
//
//	}

//	public void addAllProductsToCart() {
//
//		log.info("==================================================");
//		log.info("Starting: Add all available products to cart");
//		log.info("==================================================");
//
//		By inventoryItems = By.cssSelector(".inventory_item");
//
//		By addToCartButtons = By.cssSelector("button[data-test^='add-to-cart-']");
//
//		By cartBadge = By.cssSelector(".shopping_cart_badge");
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//
//		/*
//		 * Get total number of inventory products.
//		 */
//		int totalProducts = driver.findElements(inventoryItems).size();
//
//		log.info("Total inventory products found: {}", totalProducts);
//
//		if (totalProducts == 0) {
//			throw new IllegalStateException("No inventory products found");
//		}
//
//		/*
//		 * We process the product by product.
//		 */
//		for (int i = 0; i < totalProducts; i++) {
//
//			/*
//			 * ALWAYS re-find the products.
//			 */
//			List<WebElement> products = driver.findElements(inventoryItems);
//
//			WebElement product = products.get(i);
//
//			/*
//			 * Product name.
//			 */
//			String productName = product.findElement(By.cssSelector(".inventory_item_name")).getText().trim();
//
//			log.info("Processing product {}/{}: {}", i + 1, totalProducts, productName);
//
//			/*
//			 * Find Add To Cart button.
//			 */
//			WebElement addButton = product.findElement(addToCartButtons);
//
//			/*
//			 * Capture button attributes BEFORE clicking.
//			 */
//			String beforeDataTest = addButton.getAttribute("data-test");
//
//			String beforeText = addButton.getText().trim();
//
//			log.info("Before click - product: {}, data-test: {}, text: {}", productName, beforeDataTest, beforeText);
//
//			/*
//			 * Get current cart count.
//			 */
//			int beforeCartCount = getCartBadgeCount();
//
//			log.info("Cart count before '{}': {}", productName, beforeCartCount);
//
//			/*
//			 * Scroll into view.
//			 */
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", addButton);
//
//			/*
//			 * Wait until button is visible and enabled.
//			 */
//			wait.until(ExpectedConditions.visibilityOf(addButton));
//
//			wait.until(ExpectedConditions.elementToBeClickable(addButton));
//
//			/*
//			 * IMPORTANT:
//			 *
//			 * Use JavaScript only as a fallback. First attempt normal Selenium click.
//			 */
//			try {
//
//				addButton.click();
//
//				log.info("Normal Selenium click executed for: {}", productName);
//
//			} catch (Exception e) {
//
//				log.warn("Normal click failed for '{}'. " + "Using JavaScript click. Error: {}", productName,
//						e.getMessage());
//
//				((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
//
//				log.info("JavaScript click executed for: {}", productName);
//			}
//
//			/*
//			 * Give the application a moment to process the click and update its UI.
//			 */
//			wait.until(driver -> {
//
//				/*
//				 * Re-read badge from DOM.
//				 */
//				int currentCount = getCartBadgeCount();
//
//				return currentCount > beforeCartCount;
//			});
//
//			/*
//			 * Read final count.
//			 */
//			int afterCartCount = getCartBadgeCount();
//
//			log.info("Cart count after '{}': {}", productName, afterCartCount);
//
//			/*
//			 * Validate increment.
//			 */
//			if (afterCartCount != beforeCartCount + 1) {
//
//				throw new IllegalStateException("Cart count did not increase correctly for " + productName
//						+ ". Expected: " + (beforeCartCount + 1) + ", Actual: " + afterCartCount);
//			}
//
//			/*
//			 * Re-find product AFTER click.
//			 */
//			List<WebElement> refreshedProducts = driver.findElements(inventoryItems);
//
//			WebElement refreshedProduct = refreshedProducts.get(i);
//
//			/*
//			 * Find current button.
//			 */
//			List<WebElement> currentButtons = refreshedProduct.findElements(By.cssSelector("button[data-test]"));
//
//			if (!currentButtons.isEmpty()) {
//
//				WebElement currentButton = currentButtons.get(0);
//
//				String afterDataTest = currentButton.getAttribute("data-test");
//
//				String afterText = currentButton.getText().trim();
//
//				log.info("After click - product: {}, data-test: {}, text: {}", productName, afterDataTest, afterText);
//			}
//
//			log.info("Successfully added product: {}", productName);
//		}
//
//		/*
//		 * ================================================== FINAL VERIFICATION
//		 * ==================================================
//		 */
//
//		int finalCartCount = getCartBadgeCount();
//
//		log.info("FINAL CART BADGE COUNT: {}", finalCartCount);
//
//		if (finalCartCount != totalProducts) {
//
//			throw new IllegalStateException(
//					"Incorrect cart badge count. Expected " + totalProducts + " but found " + finalCartCount);
//		}
//
//		log.info("Successfully added all {} products to cart", totalProducts);
//
//		log.info("==================================================");
//		log.info("Completed: Add all available products to cart");
//		log.info("==================================================");
//	}

	public void addAllProductsToCart() {

		log.info("==================================================");
		log.info("Starting: Add all available products to cart");
		log.info("==================================================");

		By productLocator = By.cssSelector(".inventory_item");

		By addButtonLocator = By.cssSelector("button[data-test^='add-to-cart-']");

		By cartBadgeLocator = By.cssSelector(".shopping_cart_badge");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		/*
		 * Find total products.
		 */
		int totalProducts = driver.findElements(productLocator).size();

		log.info("Total products found: {}", totalProducts);

		if (totalProducts == 0) {

			throw new IllegalStateException("No products found on Inventory page");
		}

		/*
		 * Add products one at a time.
		 */
		for (int i = 0; i < totalProducts; i++) {

			/*
			 * Re-find buttons on every iteration.
			 *
			 * Do NOT store the complete list before the loop.
			 */
			List<WebElement> buttons = driver.findElements(addButtonLocator);

			log.info("Currently available Add to Cart buttons: {}", buttons.size());

			/*
			 * IMPORTANT:
			 *
			 * After each click, the Add button changes to Remove. Therefore buttons.size()
			 * decreases.
			 *
			 * Always click the FIRST currently available Add button.
			 */
			if (buttons.isEmpty()) {

				log.info("No Add to Cart buttons remain.");

				break;
			}

			WebElement addButton = buttons.get(0);

			/*
			 * Get product name from parent inventory item.
			 */
			WebElement product = addButton.findElement(By.xpath("./ancestor::div[contains(@class,'inventory_item')]"));

			String productName = product.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText()
					.trim();

			log.info("Adding product {}/{}: {}", i + 1, totalProducts, productName);

			/*
			 * Get cart count before clicking.
			 */
			int beforeCount = getCartBadgeCount();

			log.info("Cart badge before '{}': {}", productName, beforeCount);

			/*
			 * Scroll button into view.
			 */
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", addButton);

			/*
			 * Wait until clickable.
			 */
			wait.until(ExpectedConditions.elementToBeClickable(addButton));

			/*
			 * Click.
			 */
			addButton.click();

			log.info("Clicked Add to Cart: {}", productName);

			/*
			 * Expected count.
			 */
			int expectedCount = beforeCount + 1;

			/*
			 * Wait for cart badge to update.
			 */
			wait.until(driver -> {

				List<WebElement> badges = driver.findElements(cartBadgeLocator);

				if (badges.isEmpty()) {
					return false;
				}

				String text = badges.get(0).getText().trim();

				if (text.isEmpty()) {
					return false;
				}

				return Integer.parseInt(text) == expectedCount;
			});

			/*
			 * Confirm.
			 */
			int actualCount = getCartBadgeCount();

			log.info("Cart badge after '{}': {}", productName, actualCount);

			if (actualCount != expectedCount) {

				throw new IllegalStateException("Failed to add product: " + productName + ". Expected cart count: "
						+ expectedCount + ", Actual: " + actualCount);
			}

			log.info("Successfully added: {}", productName);
		}

		/*
		 * Final verification.
		 */
		int finalCount = getCartBadgeCount();

		log.info("FINAL CART BADGE COUNT: {}", finalCount);

		if (finalCount != totalProducts) {

			throw new IllegalStateException(
					"Incorrect cart badge count. Expected " + totalProducts + " but found " + finalCount);
		}

		log.info("Successfully added all {} products to cart.", totalProducts);

		log.info("==================================================");
		log.info("Completed: Add all available products to cart");
		log.info("==================================================");
	}

	
//	public void clickShoppingCart() {
//
//		log.info("Clicking Shopping Cart icon");
//
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//			
//			 // Make sure we are on Inventory page
//	        wait.until(ExpectedConditions.urlContains("/inventory.html"));
//	        
//	        log.info("Shopping Cart icon is clickable");
//
//			WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink));
//
//			cart.click();
//			waitForCartPage();
//
//			log.info("Shopping Cart icon clicked successfully");
//			log.info("Successfully navigated to Shopping Cart");
//	        log.info("Current URL: {}", driver.getCurrentUrl());
//
//		} catch (Exception e) {
//			log.error("Failed to click Shopping Cart icon: {}", e.getMessage(), e);
//			throw e;
//		}
//	}

	public void clickShoppingCart() {

		log.info("Clicking Shopping Cart icon");

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			// Make sure we are on Inventory page
			wait.until(ExpectedConditions.urlContains("/inventory.html"));

			log.info("Inventory page is displayed");

			WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink));

			log.info("Shopping Cart icon is clickable");

			cart.click();

			log.info("Shopping Cart icon clicked successfully");

			waitForCartPage();

			log.info("Successfully navigated to Shopping Cart");
			log.info("Current URL: {}", driver.getCurrentUrl());

		} catch (Exception e) {

			log.error("Failed to click Shopping Cart icon: {}", e.getMessage(), e);

			throw e;
		}
	}

	public void waitForCartPageToOpen() {

		log.info("Waiting for Shopping Cart page to load");

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			// Wait until Cart URL is loaded
			wait.until(ExpectedConditions.urlContains("/cart.html"));

			// Wait until Cart container is visible
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_list")));

			log.info("Shopping Cart page loaded successfully");
			log.info("Current URL: {}", driver.getCurrentUrl());

		} catch (Exception e) {
			log.error("Shopping Cart page failed to load. Current URL: {}", driver.getCurrentUrl(), e);
			throw e;
		}
	}

	public void removeProductFromCart(String productName) {

		log.info("Attempting to remove product from cart: {}", productName);

		try {
			List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_item"));

			if (cartItems.isEmpty()) {
				log.info("Cart is empty. Product '{}' is not available to remove.", productName);
				return;
			}

			for (WebElement cartItem : cartItems) {

				String itemName = cartItem.findElement(By.cssSelector(".inventory_item_name")).getText().trim();

				if (itemName.equalsIgnoreCase(productName)) {

					WebElement removeButton = cartItem.findElement(By.cssSelector("button[id^='remove-']"));

					removeButton.click();

					log.info("Product '{}' removed successfully", productName);
					return;
				}
			}

			// Negative scenario: product is not present
			log.info("Product '{}' is not present in the cart. No remove action performed.", productName);

		} catch (Exception e) {
			log.error("Failed while attempting to remove product '{}': {}", productName, e.getMessage(), e);
			throw e;
		}
	}

}
