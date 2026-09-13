package pages;

import base.BasePage;
import config.EnvironmentManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

		return productNames.stream().map(WebElement::getText).toList();
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

	// =========================================================
	// PRODUCT PRICES
	// =========================================================

	public List<String> getProductPrices() {

		return productPrices.stream().map(WebElement::getText).toList();
	}
	
	public String getProductPrice_(String productName) { 
		validateProductName(productName); 
		String productXPath = "//div[contains(@class,'inventory_item')]" + "[.//div[contains(@class,'inventory_item_name') " + "and normalize-space()=" + xpathLiteral(productName) + "]]"; 
		WebElement productCard = driver.findElement( By.xpath(productXPath) ); 
		return productCard .findElement(By.cssSelector(".inventory_item_price")) .getText();
		
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

	

}