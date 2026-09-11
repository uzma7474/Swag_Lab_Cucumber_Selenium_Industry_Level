package pages;

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
public class InventoryPage extends BasePage {

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

	public InventoryPage() {

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