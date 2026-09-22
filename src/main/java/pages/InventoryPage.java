package pages;

import base.BasePage;
import config.EnvironmentManager;
import utils.WaitUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(InventoryPage.class);

	// =========================================================
	// PAGE LOCATORS
	// =========================================================

	@FindBy(css = ".title")
	private WebElement pageTitle;

	@FindBy(css = ".inventory_list")
	private WebElement inventoryList;

	@FindBy(css = ".inventory_item")
	private List<WebElement> inventoryItems;

	@FindBy(css = ".product_sort_container")
	private WebElement productSortDropdown;

	@FindBy(css = ".shopping_cart_link")
	private WebElement shoppingCartLink;

	@FindBy(css = ".shopping_cart_badge")
	private WebElement shoppingCartBadge;

	@FindBy(css = ".inventory_item_name")
	private List<WebElement> productNames;

	@FindBy(css = ".inventory_item_price")
	private List<WebElement> productPrices;

	@FindBy(css = "[data-test^='add-to-cart']")
	private List<WebElement> addToCartButtons;

	@FindBy(css = "[data-test^='remove']")
	private List<WebElement> removeButtons;

	@FindBy(id = "shopping_cart_container")
	private WebElement cartContainer;

	@FindBy(css = ".inventory_item")
	private List<WebElement> productCards;

	private static final By SHOPPING_CART_LINK = By.cssSelector(".shopping_cart_link");

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public InventoryPage() {

		super();

		PageFactory.initElements(driver, this);

		log.debug("InventoryPage initialized");
	}

	// =========================================================
	// NAVIGATION
	// =========================================================

	/**
	 * Opens Inventory page directly.
	 *
	 * Normally this should NOT be used for:
	 *
	 * Login -> Inventory
	 *
	 * because successful login already redirects to inventory.
	 */
	public void open() {

		log.info("Navigating to SauceDemo inventory page");

		navigateTo(EnvironmentManager.getBaseUrl() + "/inventory.html");
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

	public boolean isInventoryPageDisplayed() {

		String currentUrl = getCurrentUrl();

		boolean urlValid = currentUrl.contains("/inventory.html");

		boolean titleDisplayed = isDisplayed(pageTitle);

		boolean inventoryDisplayed = isDisplayed(inventoryList);

		log.debug("Inventory page validation - URL: {}, Title: {}, Inventory: {}", urlValid, titleDisplayed,
				inventoryDisplayed);

		return urlValid && titleDisplayed && inventoryDisplayed;
	}

	public String getPageTitle() {

		return getText(pageTitle);
	}

	public boolean isPageTitleDisplayed() {

		return isDisplayed(pageTitle);
	}

	public boolean isInventoryListDisplayed() {

		return isDisplayed(inventoryList);
	}

	public boolean isCartIconDisplayed() {
		return isDisplayed(cartContainer);
	}

	// =========================================================
	// INVENTORY
	// =========================================================

	public int getProductCount() {

		return inventoryItems.size();
	}

	public boolean areProductsDisplayed() {

		return !inventoryItems.isEmpty();
	}

	// =========================================================
	// PRODUCT NAMES
	// =========================================================

	public List<String> getProductNames() {

		// return productNames.stream().map(WebElement::getText).toList();

		return productNames.stream().map(WebElement::getText).map(String::trim).collect(Collectors.toList());
	}

	public boolean isProductDisplayed(String productName) {

		validateProductName(productName);

		String normalizedProductName = productName.trim();

		return productNames.stream()
				.anyMatch(element -> element.getText().trim().equalsIgnoreCase(normalizedProductName));
	}

	public String getProductName(int index) {

		if (index < 0 || index >= productNames.size()) {

			throw new IndexOutOfBoundsException("Invalid product index: " + index);
		}

		return getText(productNames.get(index));
	}

	public List<String> getAllProductNames_not_using() {

		return productCards.stream()
				.map(product -> product.findElement(By.cssSelector(".inventory_item_name")).getText().trim())
				.collect(Collectors.toList());
	}

	public List<String> getAllProductNames() {

		WaitUtils.waitForProductCards(driver, productCards);

		return productCards.stream()
				.map(product -> product.findElement(By.cssSelector(".inventory_item_name")).getText().trim())
				.collect(Collectors.toList());
	}

	public int getProductCountOnInventory() {

		try {
			WaitUtils.waitForProductCards(driver, productCards);

			return productCards.size();

		} catch (Exception e) {

			log.error("Unable to retrieve inventory product count", e);

			return 0;
		}
	}

	// =========================================================
	// PRODUCT PRICES
	// =========================================================

	public List<String> getProductPrices() {

		return productPrices.stream().map(WebElement::getText).toList();
	}

	public String getProductPrice_(String productName) {
		validateProductName(productName);
		String productXPath = "//div[contains(@class,'inventory_item')]"
				+ "[.//div[contains(@class,'inventory_item_name') " + "and normalize-space()="
				+ xpathLiteral(productName) + "]]";
		WebElement productCard = driver.findElement(By.xpath(productXPath));
		return productCard.findElement(By.cssSelector(".inventory_item_price")).getText();

	}

	public String getProductPrice(String productName) {

		validateProductName(productName);

		By productCard = By.xpath("//div[contains(@class,'inventory_item')]" + "[.//div[contains("
				+ "@class,'inventory_item_name') " + "and normalize-space()=" + xpathLiteral(productName) + "]]");

		WebElement card = driver.findElement(productCard);

		WebElement price = card.findElement(By.cssSelector(".inventory_item_price"));

		return getText(price);
	}

	// =========================================================
	// ADD TO CART
	// =========================================================

	public void addProductToCart(String productName) {

		validateProductName(productName);

		log.info("Adding product to cart: {}", productName);

		By addToCartButton = By.xpath("//div[contains(@class,'inventory_item')]" + "[.//div[contains("
				+ "@class,'inventory_item_name') " + "and normalize-space()=" + xpathLiteral(productName) + "]]"
				+ "//button[contains(" + "@data-test,'add-to-cart')]");

		WebElement button = driver.findElement(addToCartButton);

		click(button);

		WaitUtils.waitForUrlContains("/inventory.html");
	}

	public void addFirstProductToCart() {

		if (addToCartButtons.isEmpty()) {

			throw new IllegalStateException("No Add To Cart button is available");
		}

		log.info("Adding first product to cart");

		click(addToCartButtons.get(0));
	}

	// =========================================================
	// REMOVE FROM CART
	// =========================================================

	public void removeProductFromCart(String productName) {

		validateProductName(productName);

		log.info("Removing product from cart: {}", productName);

		By removeButton = By.xpath("//div[contains(@class,'inventory_item')]" + "[.//div[contains("
				+ "@class,'inventory_item_name') " + "and normalize-space()=" + xpathLiteral(productName) + "]]"
				+ "//button[contains(" + "@data-test,'remove')]");

		WebElement button = driver.findElement(removeButton);

		click(button);
	}

	// =========================================================
	// SHOPPING CART
	// =========================================================

	public void clickShoppingCart() {

		log.info("Clicking shopping cart");

		click(shoppingCartLink);

		log.debug("Clicked shopping cart");
	}

	// =========================================================
	// SHOPPING CART
	// =========================================================

	/**
	 * Clicks the Shopping Cart link and waits until the Cart page is loaded.
	 */
	public void clickShoppingCartIcon() {

		log.info("Clicking Shopping Cart");

		try {

			// Wait until element is displayed and clickable
			// WaitUtils.waitForClickable(By.cssSelector(".shopping_cart_link"));

			WebElement cartLink = WaitUtils.waitForClickable(SHOPPING_CART_LINK);

			log.debug("Shopping Cart icon is clickable");

			// Click the exact element returned by the wait

			// Click Shopping Cart
			// click(shoppingCartLink);

			try {
				cartLink.click();
				log.debug("Shopping Cart clicked using normal Selenium click");

			} catch (Exception clickException) {

				log.warn("Normal click failed. Attempting JavaScript click", clickException);

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartLink);

				log.debug("Shopping Cart clicked using JavaScript");
			}

			log.debug("Shopping Cart clicked");

			// Wait for Cart page
			// WaitUtils.waitForUrlContains("/cart.html");

			log.info("Successfully navigated to Cart page");

		} catch (Exception e) {

			log.error("Failed to navigate to Cart page", e);

			throw e;
		}
	}

	public boolean isShoppingCartDisplayed() {

		return isDisplayed(shoppingCartLink);
	}

	public boolean isCartBadgeDisplayed() {

		return isDisplayed(shoppingCartBadge);
	}

	public String getCartBadgeCount() {

		if (!isCartBadgeDisplayed()) {

			return "0";
		}

		return getText(shoppingCartBadge);
	}

	// =========================================================
	// SORTING
	// =========================================================

	public void selectSortOption(String visibleText) {

		if (visibleText == null || visibleText.isBlank()) {

			throw new IllegalArgumentException("Sort option must not be null or blank");
		}

		log.info("Selecting inventory sort option: {}", visibleText);

		selectByVisibleText(productSortDropdown, visibleText);
	}

	public String getSelectedSortOption() {

		return getSelectedOption(productSortDropdown);
	}

	// =========================================================
	// REFRESH
	// =========================================================

	public void refresh() {

		log.info("Refreshing inventory page");

		driver.navigate().refresh();
	}

	// =========================================================
	// VALIDATION HELPERS
	// =========================================================

	private void validateProductName(String productName) {

		if (productName == null || productName.isBlank()) {

			throw new IllegalArgumentException("Product name must not be null or blank");
		}
	}

	// =========================================================
	// XPATH HELPER
	// =========================================================

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

	public String getInventoryPageHeading() {

		log.debug("Getting inventory page heading");

		return getText(pageTitle);
	}

	public void addAllProductsToCart_() {
		log.info("Starting to add all available products to the cart");
		int totalProducts = inventoryItems.size();
		log.info("Total products available on inventory page: {}", totalProducts);
		if (totalProducts == 0) {
			throw new IllegalStateException("No products are available on the inventory page");

		}
		for (int i = 0; i < totalProducts; i++) {
			// Re-fetch inventory items because the DOM changes
			// after clicking Add to Cart.
			List<WebElement> currentItems = driver.findElements(By.cssSelector(".inventory_item"));
			WebElement product = currentItems.get(i);
			WebElement productName = product.findElement(By.cssSelector(".inventory_item_name"));
			String name = productName.getText().trim();
			log.info("Adding product {}/{}: {}", i + 1, totalProducts, name);

			WebElement addButton = product.findElement(By.cssSelector("button[data-test^='add-to-cart']"));

			if (addButton.isDisplayed() && addButton.isEnabled()) {
				addButton.click();
				log.info("Successfully added product: {}", name);

			} else {

				log.warn("Add to Cart button is not available for product: {}", name);

			}
		}
		log.info("Finished adding all available products to the cart");

	}

	/**
	 * * Adds all available products to the shopping cart. * * The inventory page
	 * contains 6 products. * The Add to Cart button changes to Remove after
	 * clicking, * so the DOM is re-evaluated for every product.
	 */
	public void addAllProductsToCartNotUsing() {
		log.info("==================================================");
		log.info("Starting add all products to cart operation");
		log.info("==================================================");
		// Get total number of products currently displayed
		List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item"));
		int totalProducts = products.size();
		log.info("Total products available on inventory page: {}", totalProducts);
		if (totalProducts == 0) {
			throw new IllegalStateException("No products found on inventory page");

		}
		/*
		 * * Re-find the inventory item during every iteration. * This is important
		 * because clicking Add to Cart changes * the DOM: * * Add to Cart -> Remove
		 */
		for (int i = 0; i < totalProducts; i++) {
			log.info("Processing product {}/{}", i + 1, totalProducts);
			// Re-fetch current inventory items
			List<WebElement> currentProducts = driver.findElements(By.cssSelector(".inventory_item"));

			if (currentProducts.size() <= i) {
				throw new IllegalStateException("Unable to locate product at index " + i);

			}
			WebElement product = currentProducts.get(i);
			// Get product name
			WebElement productNameElement = product.findElement(By.cssSelector("[data-test='inventory-item-name']"));
			String productName = productNameElement.getText().trim();
			log.info("Product {} of {}: {}", i + 1, totalProducts, productName);
			// Find Add to Cart button INSIDE this product
			List<WebElement> addButtons = product.findElements(By.cssSelector("button[data-test^='add-to-cart']"));
			if (addButtons.isEmpty()) {
				log.warn("Add to Cart button not found for product: {}", productName);
				continue;

			}
			WebElement addToCartButton = addButtons.get(0);
			if (!addToCartButton.isDisplayed()) {
				log.warn("Add to Cart button is not displayed for product: {}", productName);
				continue;

			}
			if (!addToCartButton.isEnabled()) {
				log.warn("Add to Cart button is disabled for product: {}", productName);
				continue;

			}
			// Click Add to Cart
			addToCartButton.click();
			log.info("Successfully clicked Add to Cart for: {}", productName);
			/*
			 * * Verify that Add to Cart changed to Remove. * This confirms that the click
			 * actually worked.
			 */
			List<WebElement> removeButtons = product.findElements(By.cssSelector("button[data-test^='remove-']"));
			if (!removeButtons.isEmpty()) {
				log.info("Product successfully added to cart: {}", productName);

			} else {
				/*
				 * * The DOM may have changed, so re-locate the product * before checking again.
				 * 
				 */
				WebElement refreshedProduct = driver.findElements(By.cssSelector(".inventory_item")).get(i);
				List<WebElement> refreshedRemoveButtons = refreshedProduct
						.findElements(By.cssSelector("button[data-test^='remove-']"));
				if (!refreshedRemoveButtons.isEmpty()) {
					log.info("Confirmed product added to cart: {}", productName);

				} else {
					log.error("Product may NOT have been added to cart: {}", productName);
					throw new IllegalStateException("Failed to add product to cart: " + productName);
				}

			}

		}
		log.info("==================================================");
		log.info("Finished add all products to cart operation");
		log.info("==================================================");

	}

	public void addAllProductsToCart() {
		log.info("Starting add all products to cart");
		List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item"));

		int totalProducts = products.size();
		log.info("Inventory product count: {}", totalProducts);

		if (totalProducts != 6) {
			log.warn("Expected 6 products but found: {}", totalProducts);

		}

		for (int i = 0; i < totalProducts; i++) {
			/* * IMPORTANT: * Re-fetch the product every iteration. */
			List<WebElement> currentProducts = driver.findElements(By.cssSelector(".inventory_item"));

			WebElement product = currentProducts.get(i);

			String productName = product.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText()
					.trim();

			log.info("Adding product {}/{}: {}", i + 1, totalProducts, productName);

			WebElement addButton = product.findElement(By.cssSelector("button[data-test^='add-to-cart']"));
			addButton.click();

			log.info("Clicked Add to Cart: {}", productName);
			/* * Re-read badge from DOM. */
			List<WebElement> badges = driver.findElements(By.cssSelector(".shopping_cart_badge"));

			if (badges.isEmpty()) {
				log.error("Cart badge is NOT displayed after adding: {}", productName);

			} else {
				String badgeText = badges.get(0).getText().trim();
				log.info("Cart badge after adding '{}': {}", productName, badgeText);

			}

		}
		log.info("All product processing completed");

	}

	private int getCartBadgeCounts() {

		List<WebElement> badges = driver.findElements(By.cssSelector(".shopping_cart_badge"));

		if (badges.isEmpty()) {
			return 0;
		}

		String badgeText = badges.get(0).getText().trim();

		if (badgeText.isEmpty()) {
			return 0;
		}

		return Integer.parseInt(badgeText);
	}

	public boolean addProductToCartByName(String productName) {

		for (WebElement product : productCards) {

			String actualProductName = product.findElement(By.cssSelector(".inventory_item_name")).getText().trim();

			if (actualProductName.equalsIgnoreCase(productName)) {

				WebElement addToCartButton = product.findElement(By.cssSelector("button[data-test^='add-to-cart']"));

				WaitUtils.waitForVisibility(driver, addToCartButton);

				addToCartButton.click();

				log.info("Added product to cart: {}", productName);

				return true;
			}
		}

		log.warn("Product not found on Inventory page: {}", productName);

		return false;
	}

	public boolean isProductsPageDisplayed() {

	    try {
	        return pageTitle.isDisplayed()
	                && "PRODUCTS".equalsIgnoreCase(pageTitle.getText().trim());

	    } catch (Exception e) {

	        log.error("Unable to verify Products page", e);
	        return false;
	    }
	}
	
	public boolean isProductListVisible() {

	    try {
	        return !inventoryItems.isEmpty()
	                && inventoryItems.stream().allMatch(WebElement::isDisplayed);

	    } catch (Exception e) {

	        log.error("Unable to verify product list", e);
	        return false;
	    }
	}
	
	public boolean isProductsTitleDisplayed() {

	    try {
	        return pageTitle.isDisplayed()
	                && "PRODUCTS".equalsIgnoreCase(pageTitle.getText().trim());

	    } catch (Exception e) {

	        log.error("Unable to verify Products title", e);
	        return false;
	    }
	}
	
	public boolean hasProducts() {

	    try {
	        return inventoryItems != null
	                && !inventoryItems.isEmpty();

	    } catch (Exception e) {

	        log.error("Unable to verify product list", e);
	        return false;
	    }
	}
	
	
	
	/**
	 * * Adds every available product to the cart. * Important: * The Add to Cart
	 * button changes to Remove after clicking. * Therefore, the button is located
	 * fresh on every iteration.
	 */
//	public void addAllProductsToCart_not_using() { 
//		log.info("=================================================="); 
//		log.info("Starting: Add all available products to cart"); 
//		log.info("=================================================="); 
//		/* * Get the number of products only. 
//		 * * We do not keep these WebElements for the whole loop. 
//		 * */ 
//		int totalProducts = driver.findElements( By.cssSelector(".inventory_item") ).size(); 
//		log.info( "Total inventory products found: {}", totalProducts ); 
//		
//		if (totalProducts == 0) { 
//			throw new IllegalStateException( "No inventory products found" ); 
//			
//		} 
//		for (int i = 0; i < totalProducts; i++) { 
//			/* * Re-find inventory items. */ 
//			List<WebElement> currentProducts = driver.findElements( By.cssSelector(".inventory_item") ); 
//			WebElement currentProduct = currentProducts.get(i); 
//			String productName = currentProduct.findElement( By.cssSelector("[data-test='inventory-item-name']") ).getText().trim(); 
//			log.info( "Processing product {}/{}: {}", i + 1, totalProducts, productName ); 
//			
//			/* * IMPORTANT: * Find the Add button fresh from the current DOM. */ 
//			List<WebElement> addButtons = currentProduct.findElements( By.cssSelector("button[data-test^='add-to-cart']") ); 
//			/* * If Add to Cart button does not exist, the product may * already be in the cart. */ 
//			if (addButtons.isEmpty()) { 
//				log.warn( "Add to Cart button not found for product: {}", productName ); 
//				continue; 
//				
//			} 
//			WebElement addButton = addButtons.get(0); 
//			/* * Scroll product into view. */ 
//			((JavascriptExecutor) driver) .executeScript( "arguments[0].scrollIntoView({block:'center'});", addButton ); 
//			
//			/* * Click using normal Selenium click. */ 
//			
//			if (addButton.isDisplayed() && addButton.isEnabled()) { 
//				addButton.click(); log.info( "Clicked Add to Cart for: {}", productName ); 
//				
//			} else { 
//				log.error( "Add button is not clickable for product: {}", productName ); 
//				throw new IllegalStateException( "Add button is not clickable for: " + productName ); 
//				
//			} 
//			/* * Verify that the product changed from * Add to Cart -> Remove. * 
//			 * * Re-locate the product because DOM has changed. 
//			 * */ 
//			List<WebElement> refreshedProducts = driver.findElements( By.cssSelector(".inventory_item") ); 
//			WebElement refreshedProduct = refreshedProducts.get(i); 
//			
//			List<WebElement> removeButtons = refreshedProduct.findElements( By.cssSelector("button[data-test^='remove-']") ); 
//			
//			if (removeButtons.isEmpty()) { 
//				log.error( "Product was NOT confirmed as added: {}", productName ); 
//				throw new IllegalStateException( "Product was not added successfully: " + productName ); 
//				
//			} 
//			log.info( "Confirmed product added: {}", productName ); 
//			/* * Log current badge after every product. */ 
//			List<WebElement> badges = driver.findElements( By.cssSelector(".shopping_cart_badge") ); 
//			if (!badges.isEmpty()) { 
//				String badgeText = badges.get(0).getText().trim(); 
//				log.info( "Cart badge after '{}': {}", productName, badgeText ); 
//				
//			} 
//			
//		} 
//		/* * Final verification. */ 
//		List<WebElement> finalBadge = driver.findElements( By.cssSelector(".shopping_cart_badge") ); 
//		if (finalBadge.isEmpty()) { 
//			throw new IllegalStateException( "Cart badge is not displayed after adding products" ); 
//			
//		} 
//		int finalCount = Integer.parseInt( finalBadge.get(0).getText().trim() ); 
//		log.info( "FINAL CART BADGE COUNT: {}", finalCount ); 
//		log.info("=================================================="); 
//		log.info("Completed: Add all available products to cart"); 
//		log.info("=================================================="); 
//		
//	}
//	

}