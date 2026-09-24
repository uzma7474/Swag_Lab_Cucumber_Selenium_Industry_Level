
package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BasePage;
import config.EnvironmentManager;
import driver.DriverManager;
import utils.WaitUtils;

public class MenuLazyLoadPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(MenuLazyLoadPage.class);

	// =========================================================
	// PAGE LOCATORS
	// =========================================================

	/**
	 * Page heading/title.
	 *
	 * Keep this locator flexible because the exact heading on the dynamic/lazy-load
	 * page may differ from the standard inventory page.
	 */
	@FindBy(css = ".title")
	private WebElement pageTitle;

	/**
	 * Main catalog/container.
	 */
	@FindBy(css = ".inventory_list")
	private WebElement catalogContainer;

	/**
	 * Product cards.
	 *
	 * SauceDemo inventory/product cards normally use .inventory_item.
	 */
	@FindBy(css = ".inventory_item")
	private List<WebElement> productItems;

	/**
	 * Product names.
	 */
	@FindBy(css = ".inventory_item_name")
	private List<WebElement> productNames;

	/**
	 * Product descriptions.
	 */
	@FindBy(css = ".inventory_item_desc")
	private List<WebElement> productDescriptions;

	/**
	 * Product prices.
	 */
	@FindBy(css = ".inventory_item_price")
	private List<WebElement> productPrices;

	/**
	 * Product images.
	 */
	@FindBy(css = ".inventory_item_img")
	private List<WebElement> productImages;

	/**
	 * Add-to-cart buttons.
	 */
	@FindBy(css = ".inventory_item button")
	private List<WebElement> addToCartButtons;

	/**
	 * Remove-from-cart buttons.
	 */
	@FindBy(css = ".inventory_item button")
	private List<WebElement> removeButtons;

	/**
	 * Shopping cart link.
	 */
	@FindBy(css = ".shopping_cart_link")
	private WebElement shoppingCartLink;

	/**
	 * Cart badge.
	 */
	@FindBy(css = ".shopping_cart_badge")
	private WebElement shoppingCartBadge;

	/**
	 * Header.
	 */
	@FindBy(css = ".header_container")
	private WebElement headerContainer;

	/**
	 * Footer.
	 */
	@FindBy(css = ".footer")
	private WebElement footer;

	// =========================================================
	// DYNAMIC LOCATORS
	// =========================================================

	/**
	 * Generic product locator.
	 */
	private final By productItemLocator = By.cssSelector(".inventory_item");

	/**
	 * Product name inside a product card.
	 */
	private final By productNameLocator = By.cssSelector(".inventory_item_name");

	/**
	 * Product description inside a product card.
	 */
	private final By productDescriptionLocator = By.cssSelector(".inventory_item_desc");

	/**
	 * Product price inside a product card.
	 */
	private final By productPriceLocator = By.cssSelector(".inventory_item_price");

	/**
	 * Button inside a product card.
	 */
	private final By productButtonLocator = By.cssSelector("button");

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public MenuLazyLoadPage() {

        super();

        PageFactory.initElements(driver, this);

        log.debug("LazyLoadPage initialized");
    }

	// =========================================================
	// NAVIGATION
	// =========================================================

	/**
	 * Opens the lazy-load catalog page.
	 */
	public void open() {

		String baseUrl = EnvironmentManager.getBaseUrl();

		String lazyLoadUrl = baseUrl + "/dynamic-catalog-lazy-load.html";

		log.info("Opening Lazy Load Catalog page: {}", lazyLoadUrl);

		driver.get(lazyLoadUrl);

		waitForPageToLoad();

		log.info("Lazy Load Catalog page opened");
	}

	/**
	 * Navigates directly to the lazy-load page.
	 */
	public void navigateToLazyLoadPage() {

		open();
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

	/**
	 * Checks whether the page title is displayed.
	 */
	public boolean isPageTitleDisplayed() {

		try {

			return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(pageTitle))
					.isDisplayed();

		} catch (Exception e) {

			log.warn("Lazy Load page title is not displayed: {}", e.getMessage());

			return false;
		}
	}

	/**
	 * Returns page title.
	 */
	public String getPageTitle() {

		try {

			return pageTitle.getText().trim();

		} catch (Exception e) {

			log.error("Unable to retrieve Lazy Load page title", e);

			return "";
		}
	}

	/**
	 * Verifies the current URL.
	 */
	public boolean isLazyLoadPageOpened() {

		String currentUrl = driver.getCurrentUrl();

		log.debug("Current URL: {}", currentUrl);

		return currentUrl.contains("dynamic-catalog-lazy-load.html");
	}

	// =========================================================
	// CATALOG
	// =========================================================

	/**
	 * Checks whether catalog container is displayed.
	 */
	public boolean isCatalogDisplayed() {

		try {

			return new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.visibilityOf(catalogContainer)).isDisplayed();

		} catch (Exception e) {

			log.warn("Catalog container is not displayed: {}", e.getMessage());

			return false;
		}
	}

	/**
	 * Returns currently loaded product count.
	 *
	 * IMPORTANT: This method returns only products currently present in the DOM.
	 */
	public int getLoadedProductCount() {

		try {

			int count = driver.findElements(productItemLocator).size();

			log.debug("Currently loaded product count: {}", count);

			return count;

		} catch (Exception e) {

			log.error("Unable to get loaded product count", e);

			return 0;
		}
	}

	/**
	 * Returns all currently loaded product elements.
	 */
	public List<WebElement> getLoadedProducts() {

		return driver.findElements(productItemLocator);
	}

	// =========================================================
	// PRODUCT NAMES
	// =========================================================

	/**
	 * Returns names of currently loaded products.
	 */
	public List<String> getLoadedProductNames() {

		List<WebElement> products = driver.findElements(productItemLocator);

		List<String> names = new ArrayList<>();

		for (WebElement product : products) {

			try {

				String name = product.findElement(productNameLocator).getText().trim();

				names.add(name);

			} catch (Exception e) {

				log.warn("Unable to retrieve product name: {}", e.getMessage());
			}
		}

		log.debug("Loaded product names: {}", names);

		return names;
	}

	/**
	 * Returns all product names as a list.
	 */
	public List<String> getProductNames() {

		return productNames.stream().map(WebElement::getText).map(String::trim).collect(Collectors.toList());
	}

	// =========================================================
	// PRODUCT DETAILS
	// =========================================================

	/**
	 * Gets description for a particular product.
	 */
	public String getProductDescription(String productName) {

		List<WebElement> products = driver.findElements(productItemLocator);

		for (WebElement product : products) {

			String name = product.findElement(productNameLocator).getText().trim();

			if (name.equalsIgnoreCase(productName)) {

				return product.findElement(productDescriptionLocator).getText().trim();
			}
		}

		log.warn("Product not found: {}", productName);

		return "";
	}

	/**
	 * Gets price for a particular product.
	 */
	public String getProductPrice(String productName) {

		List<WebElement> products = driver.findElements(productItemLocator);

		for (WebElement product : products) {

			String name = product.findElement(productNameLocator).getText().trim();

			if (name.equalsIgnoreCase(productName)) {

				return product.findElement(productPriceLocator).getText().trim();
			}
		}

		log.warn("Product price not found for: {}", productName);

		return "";
	}

	/**
	 * Checks whether a product is loaded in the DOM.
	 */
	public boolean isProductLoaded(String productName) {

		return getLoadedProductNames().stream().anyMatch(name -> name.equalsIgnoreCase(productName));
	}

	// =========================================================
	// LAZY LOAD / SCROLL
	// =========================================================

	/**
	 * Scrolls to the bottom of the page.
	 */
	public void scrollToBottom() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		log.debug("Scrolled to bottom of page");
	}

	/**
	 * Scrolls to a particular product.
	 */
	public void scrollToProduct(String productName) {

		List<WebElement> products = driver.findElements(productItemLocator);

		for (WebElement product : products) {

			try {

				String name = product.findElement(productNameLocator).getText().trim();

				if (name.equalsIgnoreCase(productName)) {

					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});",
							product);

					log.debug("Scrolled to product: {}", productName);

					return;
				}

			} catch (Exception e) {

				log.warn("Unable to inspect product while scrolling: {}", e.getMessage());
			}
		}

		log.warn("Product not currently loaded: {}", productName);
	}

	/**
	 * Performs incremental scrolling until no additional products are loaded or the
	 * maximum number of attempts is reached.
	 */
	public void loadAllProducts() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		int previousCount = 0;
		int currentCount = getLoadedProductCount();

		int maxScrollAttempts = 20;

		for (int attempt = 1; attempt <= maxScrollAttempts; attempt++) {

			previousCount = currentCount;

			scrollToBottom();

			WaitUtils.waitForSeconds(1);

			currentCount = getLoadedProductCount();

			log.info("Lazy load attempt {} -> products loaded: {}", attempt, currentCount);

			/*
			 * Stop when another scroll does not increase the number of products.
			 */
			if (currentCount == previousCount) {

				/*
				 * Verify page has actually reached the bottom.
				 */
				Long currentHeight = (Long) js.executeScript("return window.pageYOffset + window.innerHeight;");

				Long totalHeight = (Long) js.executeScript("return document.body.scrollHeight;");

				if (currentHeight >= totalHeight) {

					log.info("No additional products loaded. " + "Total products: {}", currentCount);

					break;
				}
			}
		}
	}

	/**
	 * Returns number of products after triggering lazy loading.
	 */
	public int getTotalLoadedProductCount() {

		loadAllProducts();

		return getLoadedProductCount();
	}

	// =========================================================
	// PRODUCT ACTIONS
	// =========================================================

	/**
	 * Finds a product card by product name.
	 */
	public WebElement getProductCard(String productName) {

		List<WebElement> products = driver.findElements(productItemLocator);

		for (WebElement product : products) {

			String name = product.findElement(productNameLocator).getText().trim();

			if (name.equalsIgnoreCase(productName)) {

				return product;
			}
		}

		throw new IllegalArgumentException("Product was not found: " + productName);
	}

	/**
	 * Gets the Add/Remove button for a product.
	 */
	public WebElement getProductButton(String productName) {

		WebElement product = getProductCard(productName);

		return product.findElement(productButtonLocator);
	}

	/**
	 * Checks whether the product button is displayed.
	 */
	public boolean isProductButtonDisplayed(String productName) {

		try {

			return getProductButton(productName).isDisplayed();

		} catch (Exception e) {

			log.warn("Product button not displayed for {}", productName);

			return false;
		}
	}

	/**
	 * Adds a product to the cart.
	 */
	public void addProductToCart(String productName) {

		WebElement product = getProductCard(productName);

		WebElement button = product.findElement(productButtonLocator);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", product);

		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(button))
				.click();

		log.info("Product added to cart: {}", productName);
	}

	// =========================================================
	// CART
	// =========================================================

	/**
	 * Returns cart badge count.
	 */
	public int getCartItemCount() {

		try {

			if (!shoppingCartBadge.isDisplayed()) {

				return 0;
			}

			return Integer.parseInt(shoppingCartBadge.getText().trim());

		} catch (Exception e) {

			log.debug("Cart badge is not displayed. Assuming cart is empty.");

			return 0;
		}
	}

	/**
	 * Checks whether cart badge is displayed.
	 */
	public boolean isCartBadgeDisplayed() {

		try {

			return shoppingCartBadge.isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * Clicks shopping cart.
	 */
	public void clickShoppingCart() {

		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();

		log.info("Shopping cart clicked");
	}

	// =========================================================
	// PAGE SCROLL UTILITIES
	// =========================================================

	/**
	 * Returns current vertical scroll position.
	 */
	public long getCurrentScrollPosition() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		return ((Number) js.executeScript("return window.pageYOffset;")).longValue();
	}

	/**
	 * Returns page height.
	 */
	public long getPageHeight() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		return ((Number) js.executeScript("return document.body.scrollHeight;")).longValue();
	}

	/**
	 * Returns browser viewport height.
	 */
	public long getViewportHeight() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		return ((Number) js.executeScript("return window.innerHeight;")).longValue();
	}

	/**
	 * Checks whether the browser is currently at the bottom.
	 */
	public boolean isAtBottomOfPage() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		long scrollPosition = ((Number) js.executeScript("return window.pageYOffset;")).longValue();

		long viewportHeight = ((Number) js.executeScript("return window.innerHeight;")).longValue();

		long pageHeight = ((Number) js.executeScript("return document.body.scrollHeight;")).longValue();

		return scrollPosition + viewportHeight >= pageHeight - 5;
	}

	// =========================================================
	// PAGE LOAD
	// =========================================================

	/**
	 * Waits until the document is completely loaded.
	 */
	public void waitForPageToLoad() {

		new WebDriverWait(driver, Duration.ofSeconds(15)).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));

		log.debug("Document readyState is complete");
	}

	// =========================================================
	// HEADER / FOOTER
	// =========================================================

	public boolean isHeaderDisplayed() {

		try {

			return headerContainer.isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	public boolean isFooterDisplayed() {

		try {

			return footer.isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	// =========================================================
	// DRIVER
	// =========================================================

	/**
	 * Returns the WebDriver used by this page.
	 */
	public WebDriver getDriver() {

		return DriverManager.getDriver();
	}
}