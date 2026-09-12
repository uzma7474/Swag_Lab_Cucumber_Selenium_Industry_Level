package files_not_using;

import base.BasePage;
import components.FooterComponent;
import components.HeaderComponent;
import components.MenuComponent;
import components.ProductCardComponent;
import constants.PageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object representing the SauceDemo Inventory / Products page.
 *
 * Responsibilities: - Validate Inventory page - Handle product sorting -
 * Retrieve products - Provide access to reusable header/menu/footer components
 * - Navigate to product details
 *
 * Responsibilities intentionally NOT handled here: - TestNG assertions -
 * ExtentReports - Cucumber steps - Test data management - Detailed product-card
 * operations
 *
 * Product-specific operations are delegated to ProductCardComponent.
 */
public class InventoryPage_not_using extends BasePage {

	// ============================================================
	// LOCATORS
	// ============================================================

	@FindBy(css = ".title")
	private WebElement pageTitle;

	@FindBy(css = ".product_sort_container")
	private WebElement productSortDropdown;

	@FindBy(css = ".inventory_list")
	private WebElement inventoryList;

	@FindBy(css = ".inventory_item")
	private List<WebElement> productCards;

	// ============================================================
	// COMPONENTS
	// ============================================================

	private final HeaderComponent headerComponent;
	private final MenuComponent menuComponent;
	private final FooterComponent footerComponent;

	// ============================================================
	// CONSTRUCTOR
	// ============================================================

	public InventoryPage_not_using() {

		super();

		log.debug("Initializing InventoryPage");

		PageFactory.initElements(driver, this);

		headerComponent = new HeaderComponent();
		menuComponent = new MenuComponent();
		footerComponent = new FooterComponent();

		log.info("InventoryPage initialized successfully");
	}

	// ============================================================
	// PAGE VALIDATION
	// ============================================================

	/**
	 * Checks whether the Inventory page is currently displayed.
	 *
	 * @return true when URL and page title are correct
	 */
	public boolean isInventoryPageDisplayed() {

		log.debug("Checking whether Inventory page is displayed");

		try {

			String currentUrl = getCurrentUrl();

			boolean urlCorrect = currentUrl.contains(PageConstants.INVENTORY_PATH);

			boolean titleDisplayed = isDisplayed(pageTitle);

			boolean titleCorrect = titleDisplayed && PageConstants.INVENTORY_PAGE_TITLE.equals(getText(pageTitle));

			boolean displayed = urlCorrect && titleCorrect;

			log.info("Inventory page displayed: {}", displayed);

			log.debug("Inventory validation - URL correct: {}, " + "Page title correct: {}", urlCorrect, titleCorrect);

			return displayed;

		} catch (Exception e) {

			log.error("Failed to verify Inventory page", e);

			return false;
		}
	}

	/**
	 * Returns Inventory page title.
	 *
	 * @return page title
	 */
	protected String getPageTitle() {

		log.debug("Retrieving Inventory page title");

		String title = getText(pageTitle);

		log.info("Inventory page title: {}", title);

		return title;
	}

	public boolean isInventoryPageDisplayed_() {

		String currentUrl = getCurrentUrl();

		boolean correctUrl = currentUrl.contains(PageConstants.INVENTORY_PATH);

		boolean correctHeading = PageConstants.INVENTORY_PAGE_TITLE.equals(getInventoryPageHeading());

		log.debug("Inventory page validation | URL: {} | Heading: {}", currentUrl, getInventoryPageHeading());

		return correctUrl && correctHeading;
	}

	public String getInventoryPageHeading() {

		log.debug("Getting inventory page heading");

		return getText(pageTitle);
	}

	/**
	 * Checks whether Products title is displayed.
	 */
	public boolean isPageTitleDisplayed() {

		log.debug("Checking whether Products page title is displayed");

		boolean displayed = isDisplayed(pageTitle);

		log.info("Products page title displayed: {}", displayed);

		return displayed;
	}

	// ============================================================
	// INVENTORY
	// ============================================================

	/**
	 * Checks whether inventory list is displayed.
	 */
	public boolean isInventoryListDisplayed() {

		log.debug("Checking whether inventory list is displayed");

		boolean displayed = isDisplayed(inventoryList);

		log.info("Inventory list displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Returns number of products displayed.
	 */
	public int getProductCount() {

		log.debug("Retrieving product count");

		int count = productCards.size();

		log.info("Number of products displayed: {}", count);

		return count;
	}

	/**
	 * Returns names of all products displayed.
	 */
	public List<String> getProductNames() {

		log.debug("Retrieving all inventory product names");

		List<String> productNames = new ArrayList<>();

		By productNameLocator = By.cssSelector(".inventory_item_name");

		for (WebElement card : productCards) {

			String name = card.findElement(productNameLocator).getText();

			productNames.add(name);
		}

		log.info("Inventory products: {}", productNames);

		return productNames;
	}

	/**
	 * Checks whether a product exists in inventory.
	 *
	 * @param productName product name
	 * @return true if product exists
	 */
	public boolean isProductDisplayed(String productName) {

		log.debug("Checking whether product is displayed: {}", productName);

		if (productName == null || productName.trim().isEmpty()) {

			throw new IllegalArgumentException("Product name cannot be null or empty");
		}

		boolean displayed = getProductNames().stream().anyMatch(name -> name.equalsIgnoreCase(productName));

		log.info("Product '{}' displayed: {}", productName, displayed);

		return displayed;
	}

	// ============================================================
	// PRODUCT COMPONENT
	// ============================================================

	/**
	 * Returns a ProductCardComponent for the requested product.
	 *
	 * Example:
	 *
	 * ProductCardComponent product = inventoryPage.getProduct( "Sauce Labs
	 * Backpack" );
	 *
	 * @param productName product name
	 * @return ProductCardComponent
	 */
	public ProductCardComponent getProduct(String productName) {

		log.debug("Creating ProductCardComponent for: {}", productName);

		if (!isProductDisplayed(productName)) {

			log.error("Product does not exist in inventory: {}", productName);

			throw new IllegalArgumentException("Product not found in inventory: " + productName);
		}

		return new ProductCardComponent(productName);
	}

	/**
	 * Opens product details by clicking product name.
	 *
	 * @param productName product name
	 */
	public void openProductDetails(String productName) {

		log.info("Opening product details for: {}", productName);

		if (productName == null || productName.trim().isEmpty()) {

			throw new IllegalArgumentException("Product name cannot be null or empty");
		}

		String xpath = "//div[contains(@class,'inventory_item')]" + "[.//div[contains("
				+ "@class,'inventory_item_name') " + "and normalize-space()='" + productName + "']]" + "//div[contains("
				+ "@class,'inventory_item_name')]";

		try {

			WebElement productNameElement = driver.findElement(By.xpath(xpath));

			click(productNameElement);

			log.info("Product details opened for: {}", productName);

		} catch (Exception e) {

			log.error("Failed to open product details for: {}", productName, e);

			throw e;
		}
	}

	// ============================================================
	// SORTING
	// ============================================================

	/**
	 * Sorts products using SauceDemo's available sort options.
	 *
	 * Supported values: - az - za - lohi - hilo
	 *
	 * @param sortOption sorting option
	 */
	public void sortProducts(String sortOption) {

		log.info("Sorting products using option: {}", sortOption);

		if (sortOption == null || sortOption.trim().isEmpty()) {

			throw new IllegalArgumentException("Sort option cannot be null or empty");
		}

		String normalizedOption = sortOption.trim().toLowerCase();

		List<String> supportedOptions = List.of("az", "za", "lohi", "hilo");

		if (!supportedOptions.contains(normalizedOption)) {

			throw new IllegalArgumentException(
					"Unsupported sort option: " + sortOption + ". Supported options: " + supportedOptions);
		}

		try {

			org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(
					productSortDropdown);

			select.selectByValue(normalizedOption);

			log.info("Products sorted successfully using: {}", normalizedOption);

		} catch (Exception e) {

			log.error("Failed to sort products using: {}", normalizedOption, e);

			throw e;
		}
	}

	/**
	 * Sort products alphabetically A-Z.
	 */
	public void sortProductsAtoZ() {

		log.info("Sorting products A-Z");

		sortProducts("az");
	}

	/**
	 * Sort products alphabetically Z-A.
	 */
	public void sortProductsZtoA() {

		log.info("Sorting products Z-A");

		sortProducts("za");
	}

	/**
	 * Sort products by price low to high.
	 */
	public void sortProductsLowToHigh() {

		log.info("Sorting products by price low to high");

		sortProducts("lohi");
	}

	/**
	 * Sort products by price high to low.
	 */
	public void sortProductsHighToLow() {

		log.info("Sorting products by price high to low");

		sortProducts("hilo");
	}

	// ============================================================
	// HEADER COMPONENT
	// ============================================================

	/**
	 * Returns HeaderComponent.
	 */
	public HeaderComponent getHeader() {

		log.debug("Returning HeaderComponent");

		return headerComponent;
	}

	// ============================================================
	// MENU COMPONENT
	// ============================================================

	/**
	 * Returns MenuComponent.
	 */
	public MenuComponent getMenu() {

		log.debug("Returning MenuComponent");

		return menuComponent;
	}

	// ============================================================
	// FOOTER COMPONENT
	// ============================================================

	/**
	 * Returns FooterComponent.
	 */
	public FooterComponent getFooter() {

		log.debug("Returning FooterComponent");

		return footerComponent;
	}
	
	
}


/*

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

	// =========================
	// Page Locators
	// =========================

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

	// Product names
	@FindBy(css = ".inventory_item_name")
	private List<WebElement> productNames;

	// Product prices
	@FindBy(css = ".inventory_item_price")
	private List<WebElement> productPrices;

	// Add to cart buttons
	@FindBy(css = "[data-test^='add-to-cart']")
	private List<WebElement> addToCartButtons;

	// Remove buttons
	@FindBy(css = "[data-test^='remove']")
	private List<WebElement> removeButtons;

	// =========================
	// Constructor
	// =========================

	public InventoryPage() {

		super();

		PageFactory.initElements(driver, this);

		log.debug("InventoryPage initialized");
	}

	// =========================
	// Navigation
	// =========================

	public void open() {

		log.info("Navigating to SauceDemo inventory page");

		navigateTo(EnvironmentManager.getBaseUrl() + "/inventory.html");
	}

	// =========================
	// Page Validation
	// =========================

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

	// =========================
	// Inventory
	// =========================

	public boolean isInventoryListDisplayed() {

		return isDisplayed(inventoryList);
	}

	public int getProductCount() {

		return inventoryItems.size();
	}

	public boolean areProductsDisplayed() {

		return !inventoryItems.isEmpty();
	}

	// =========================
	// Product Names
	// =========================

	public List<String> getProductNames() {

		return productNames.stream().map(WebElement::getText).toList();
	}

	public boolean isProductDisplayed(String productName) {

		if (productName == null || productName.isBlank()) {
			throw new IllegalArgumentException("Product name must not be null or blank");
		}

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

	// =========================
	// Product Prices
	// =========================

	public List<String> getProductPrices() {

		return productPrices.stream().map(WebElement::getText).toList();
	}

	public String getProductPrice(String productName) {

		if (productName == null || productName.isBlank()) {
			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		By productCard = By
				.xpath("//div[contains(@class,'inventory_item')]" + "[.//div[contains(@class,'inventory_item_name') "
						+ "and normalize-space()=" + xpathLiteral(productName) + "]]");

		WebElement card = driver.findElement(productCard);

		WebElement price = card.findElement(By.cssSelector(".inventory_item_price"));

		return getText(price);
	}

	// =========================
	// Add To Cart
	// =========================

	public void addProductToCart(String productName) {

		if (productName == null || productName.isBlank()) {
			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		log.info("Adding product to cart: {}", productName);

		By addToCartButton = By.xpath("//div[contains(@class,'inventory_item')]"
				+ "[.//div[contains(@class,'inventory_item_name') " + "and normalize-space()="
				+ xpathLiteral(productName) + "]]" + "//button[contains(@data-test,'add-to-cart')]");

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

	// =========================
	// Remove From Cart
	// =========================

	public void removeProductFromCart(String productName) {

		if (productName == null || productName.isBlank()) {
			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		log.info("Removing product from cart: {}", productName);

		By removeButton = By.xpath("//div[contains(@class,'inventory_item')]"
				+ "[.//div[contains(@class,'inventory_item_name') " + "and normalize-space()="
				+ xpathLiteral(productName) + "]]" + "//button[contains(@data-test,'remove')]");

		WebElement button = driver.findElement(removeButton);

		click(button);
	}

	// =========================
	// Cart
	// =========================

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

	
	public String getInventoryPageHeading() {

		log.debug("Getting inventory page heading");

		return getText(pageTitle);
	}
	
	
	// =========================
	// Sorting
	// =========================

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

	// =========================
	// Helper
	// =========================

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
	
	public void refresh() {

		log.info("Refreshing login page");

		driver.navigate().refresh();
	}
	
	
}

*/