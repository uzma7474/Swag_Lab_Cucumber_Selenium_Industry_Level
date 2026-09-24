package docs;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

public class MenuLazyLoadPage2 extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(MenuLazyLoadPage2.class);

	private final WebDriverWait wait;

	// =========================================================
	// PAGE LOCATORS
	// =========================================================

	@FindBy(css = ".title")
	private WebElement pageTitle;

	@FindBy(css = ".header_container")
	private WebElement headerContainer;

	@FindBy(css = ".footer")
	private WebElement footer;

	// =========================================================
	// MENU LOCATORS
	// =========================================================

	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuButton;

	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuOpenButton;

	@FindBy(id = "react-burger-cross-btn")
	private WebElement menuCloseButton;

	@FindBy(css = ".bm-menu")
	private WebElement menuContainer;

	@FindBy(css = ".bm-item-list")
	private WebElement menuItemList;

	@FindBy(css = ".bm-item")
	private List<WebElement> menuItems;

	// =========================================================
	// INVENTORY / PRODUCT LOCATORS
	// =========================================================

	@FindBy(css = ".inventory_list")
	private WebElement catalogContainer;

	@FindBy(css = ".inventory_item")
	private List<WebElement> productItems;

	@FindBy(css = ".inventory_item_name")
	private List<WebElement> productNames;

	@FindBy(css = ".inventory_item_desc")
	private List<WebElement> productDescriptions;

	@FindBy(css = ".inventory_item_price")
	private List<WebElement> productPrices;

	@FindBy(css = ".inventory_item_img")
	private List<WebElement> productImages;

	// =========================================================
	// CART LOCATORS
	// =========================================================

	@FindBy(css = ".shopping_cart_link")
	private WebElement shoppingCartLink;

	@FindBy(css = ".shopping_cart_badge")
	private WebElement shoppingCartBadge;

	// =========================================================
	// DYNAMIC LOCATORS
	// =========================================================

	private final By productItemLocator = By.cssSelector(".inventory_item");

	private final By productNameLocator = By.cssSelector(".inventory_item_name");

	private final By productDescriptionLocator = By.cssSelector(".inventory_item_desc");

	private final By productPriceLocator = By.cssSelector(".inventory_item_price");

	private final By productImageLocator = By.cssSelector(".inventory_item_img");

	private final By menuItemLocator = By.cssSelector(".bm-item");

	private final By menuContainerLocator = By.cssSelector(".bm-menu");

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public MenuLazyLoadPage2() {

		super();

		PageFactory.initElements(driver, this);

		wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		log.debug("MenuLazyLoadPage initialized");
	}

	// =========================================================
	// PAGE NAVIGATION
	// =========================================================

	public void open() {

		String baseUrl = EnvironmentManager.getBaseUrl();

		String lazyLoadUrl = baseUrl + "/dynamic-catalog-lazy-load.html";

		log.info("Opening Lazy Load Menu page: {}", lazyLoadUrl);

		driver.get(lazyLoadUrl);

		waitForPageToLoad();

		log.info("Lazy Load Menu page opened successfully");
	}

	public void refresh() {

		log.info("Refreshing Lazy Load Menu page");

		driver.navigate().refresh();

		waitForPageToLoad();

		log.info("Page refreshed successfully");
	}

	private void waitForPageToLoad() {

		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));

		log.debug("Page loading completed");
	}

	// =========================================================
	// PAGE VALIDATION SUPPORT
	// =========================================================

	public boolean isPageDisplayed() {

		try {

			return pageTitle.isDisplayed() && catalogContainer.isDisplayed();

		} catch (Exception e) {

			log.warn("Lazy Load Menu page is not displayed", e);

			return false;
		}
	}

	public String getCurrentUrl() {

		String currentUrl = driver.getCurrentUrl();

		log.debug("Current page URL: {}", currentUrl);

		return currentUrl;
	}

	public boolean isLazyLoadPageOpened() {

		String currentUrl = getCurrentUrl();

		return currentUrl.contains("dynamic-catalog-lazy-load.html");
	}

	// =========================================================
	// MENU OPERATIONS
	// =========================================================

	public void clickMenuButton() {

		log.info("Clicking menu button");

		wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();

		log.debug("Menu button clicked");
	}

	public boolean isMenuDisplayed() {

		try {

			return menuContainer.isDisplayed();

		} catch (Exception e) {

			log.debug("Menu is not currently displayed");

			return false;
		}
	}

	public boolean isMenuButtonDisplayed() {

		try {

			return menuButton.isDisplayed();

		} catch (Exception e) {

			log.debug("Menu button is not displayed");

			return false;
		}
	}

	public void openMenu() {

		if (!isMenuDisplayed()) {

			log.info("Menu is closed. Opening menu");

			clickMenuButton();

			wait.until(ExpectedConditions.visibilityOf(menuContainer));
		}

		log.info("Menu is open");
	}

	public void closeMenu() {

		if (isMenuDisplayed()) {

			log.info("Closing menu");

			try {

				wait.until(ExpectedConditions.elementToBeClickable(menuCloseButton)).click();

			} catch (Exception e) {

				log.debug("Close button unavailable. Toggling menu button");

				clickMenuButton();
			}

			log.info("Menu closed");
		}
	}

	// =========================================================
	// MENU ITEM OPERATIONS
	// =========================================================

	public boolean areMenuItemsDisplayed() {

		try {

			if (!isMenuDisplayed()) {
				return false;
			}

			return !menuItems.isEmpty() && menuItems.stream().allMatch(WebElement::isDisplayed);

		} catch (Exception e) {

			log.warn("Unable to verify menu items", e);

			return false;
		}
	}

	public boolean areAllMenuOptionsDisplayed() {

		return areMenuItemsDisplayed();
	}

	public int getMenuOptionCount() {

		int count = driver.findElements(menuItemLocator).size();

		log.debug("Menu option count: {}", count);

		return count;
	}

	public void clickMenuOption(String menuOption) {

		if (menuOption == null || menuOption.trim().isEmpty()) {

			throw new IllegalArgumentException("Menu option must not be null or empty");
		}

		By menuOptionLocator = By
				.xpath("//*[contains(@class,'bm-item') " + "and normalize-space(text())='" + menuOption + "']");

		log.info("Clicking menu option: {}", menuOption);

		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(menuOptionLocator));

		option.click();

		log.info("Menu option clicked: {}", menuOption);
	}

	public boolean isSelectedMenuOptionDisplayed() {

		try {

			return !menuItems.isEmpty() && menuItems.stream().anyMatch(WebElement::isDisplayed);

		} catch (Exception e) {

			log.debug("Selected menu option could not be identified");

			return false;
		}
	}

	// =========================================================
	// SCROLL OPERATIONS
	// =========================================================

	public void scrollToTop() {

		log.info("Scrolling to top");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0, 0);");

		log.debug("Scrolled to top");
	}

	public void scrollToBottom() {

		log.info("Scrolling to bottom");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		log.debug("Scrolled to bottom");
	}

	public void scrollDown() {

		log.info("Scrolling down");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0, window.innerHeight);");

		log.debug("Scroll down completed");
	}

	public void scrollUp() {

		log.info("Scrolling up");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0, -window.innerHeight);");

		log.debug("Scroll up completed");
	}

	// =========================================================
	// LAZY LOAD OPERATIONS
	// =========================================================

	public int getLoadedProductCount() {

		int count = driver.findElements(productItemLocator).size();

		log.debug("Currently loaded product count: {}", count);

		return count;
	}

	// =========================================================
	// PRODUCT NAME
	// =========================================================

	public List<String> getLoadedProductNames() {

		log.debug("Getting names of all loaded products");

		List<WebElement> productNameElements = driver.findElements(productNameLocator);

		List<String> productNames = new ArrayList<>();

		for (WebElement productNameElement : productNameElements) {

			if (productNameElement.isDisplayed()) {

				String productName = productNameElement.getText().trim();

				productNames.add(productName);

				log.debug("Loaded product name: [{}]", productName);
			}
		}

		log.debug("Total loaded product names found: {}", productNames.size());

		return productNames;
	}
	
	
	public List<String> getLoadedProductDescriptions() {

	    log.debug("Getting descriptions of all loaded products");

	    List<WebElement> productDescriptionElements =
	            driver.findElements(productDescriptionLocator);

	    List<String> productDescriptions = new ArrayList<>();

	    for (WebElement productDescriptionElement
	            : productDescriptionElements) {

	        if (productDescriptionElement.isDisplayed()) {

	            String productDescription =
	                    productDescriptionElement.getText().trim();

	            productDescriptions.add(productDescription);

	            log.debug(
	                    "Loaded product description: [{}]",
	                    productDescription
	            );
	        }
	    }

	    log.debug(
	            "Total loaded product descriptions found: {}",
	            productDescriptions.size()
	    );

	    return productDescriptions;
	}

	public void loadAllProducts() {

		log.info("Starting lazy-load product loading");

		int previousCount = -1;
		int currentCount = getLoadedProductCount();

		int maxScrollAttempts = 20;

		for (int attempt = 1; attempt <= maxScrollAttempts; attempt++) {

			previousCount = currentCount;

			scrollToBottom();

			WaitUtils.waitForSeconds(1);

			currentCount = getLoadedProductCount();

			log.debug("Lazy-load attempt {}. Previous: {}, Current: {}", attempt, previousCount, currentCount);

			if (currentCount == previousCount) {

				log.info("No additional products loaded");

				break;
			}
		}

		log.info("Lazy-load operation completed. Total products: {}", currentCount);
	}

	// =========================================================
	// PRODUCT VALIDATIONS SUPPORT
	// =========================================================

	public boolean areProductNamesDisplayed() {

		return areElementsDisplayed(productNameLocator);
	}

	public boolean areProductDescriptionsDisplayed() {

		return areElementsDisplayed(productDescriptionLocator);
	}

	public boolean areProductPricesDisplayed() {

		return areElementsDisplayed(productPriceLocator);
	}

	public boolean areProductImagesDisplayed() {

		return areElementsDisplayed(productImageLocator);
	}

	private boolean areElementsDisplayed(By locator) {

		try {

			List<WebElement> elements = driver.findElements(locator);

			if (elements.isEmpty()) {
				return false;
			}

			return elements.stream().allMatch(WebElement::isDisplayed);

		} catch (Exception e) {

			log.warn("Unable to verify elements: {}", locator, e);

			return false;
		}
	}

	// =========================================================
	// PRODUCT DATA
	// =========================================================

	public List<WebElement> getProductItems() {

		return driver.findElements(productItemLocator);
	}

	public List<WebElement> getProductNames() {

		return driver.findElements(productNameLocator);
	}

	public List<WebElement> getProductDescriptions() {

		return driver.findElements(productDescriptionLocator);
	}

	public List<WebElement> getProductPrices() {

		return driver.findElements(productPriceLocator);
	}

	public List<WebElement> getProductImages() {

		return driver.findElements(productImageLocator);
	}

	// =========================================================
	// HEADER / FOOTER
	// =========================================================

	public boolean isHeaderDisplayed() {

		try {

			return headerContainer.isDisplayed();

		} catch (Exception e) {

			log.debug("Header is not displayed");

			return false;
		}
	}

	public boolean isFooterDisplayed() {

		try {

			return footer.isDisplayed();

		} catch (Exception e) {

			log.debug("Footer is not displayed");

			return false;
		}
	}

	// =========================================================
	// CART OPERATIONS
	// =========================================================

	public boolean isCartBadgeDisplayed() {

		try {

			return shoppingCartBadge.isDisplayed();

		} catch (Exception e) {

			log.debug("Cart badge is not displayed");

			return false;
		}
	}

	public int getCartBadgeCount() {

		if (!isCartBadgeDisplayed()) {

			log.debug("Cart badge is not displayed");

			return 0;
		}

		String badgeText = shoppingCartBadge.getText();

		log.debug("Cart badge text: {}", badgeText);

		try {

			return Integer.parseInt(badgeText.trim());

		} catch (NumberFormatException e) {

			throw new AssertionError("Cart badge does not contain a valid number: " + badgeText, e);
		}
	}

	public String getPageTitleText() {

		String title = pageTitle.getText();

		log.debug("Page title text: [{}]", title);

		return title;
	}

	public boolean isCatalogContainerDisplayed_not_using() {

		try {

			return catalogContainer.isDisplayed();

		} catch (Exception e) {

			log.warn("Catalog container is not displayed", e);

			return false;
		}
	}

	public boolean isCatalogContainerDisplayed() {

		log.info("Checking whether catalog container is displayed");

		try {

			List<WebElement> containers = driver.findElements(By.cssSelector(".inventory_list"));

			log.info("Found {} element(s) using locator: .inventory_list", containers.size());

			if (containers.isEmpty()) {

				log.error("Catalog container was NOT found using locator: .inventory_list");

				log.error("Current URL: {}", driver.getCurrentUrl());

				log.error("Page title: {}", driver.getTitle());

				return false;
			}

			for (WebElement container : containers) {

				log.info("Catalog container displayed: {}", container.isDisplayed());

				log.info("Catalog container tag: {}", container.getTagName());

				log.info("Catalog container class: {}", container.getAttribute("class"));
			}

			return containers.stream().anyMatch(WebElement::isDisplayed);

		} catch (Exception e) {

			log.error("Error while checking catalog container", e);

			return false;
		}
	}

	public boolean isPageTitleDisplayed() {

		try {

			return pageTitle.isDisplayed();

		} catch (Exception e) {

			log.warn("Page title is not displayed", e);

			return false;
		}
	}

	public void clickShoppingCart() {

		log.info("Clicking shopping cart");

		wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();

		log.info("Shopping cart clicked");
	}

	// =========================================================
	// BROWSER NAVIGATION
	// =========================================================

	public void browserBack() {

		log.info("Navigating browser back");

		driver.navigate().back();

		waitForPageToLoad();

		log.info("Browser back navigation completed");
	}

	public void browserForward() {

		log.info("Navigating browser forward");

		driver.navigate().forward();

		waitForPageToLoad();

		log.info("Browser forward navigation completed");
	}

	// =========================================================
	// DRIVER ACCESS
	// =========================================================

	public WebDriver getDriver() {

		WebDriver currentDriver = DriverManager.getDriver();

		if (currentDriver == null) {

			throw new IllegalStateException("WebDriver is not initialized");
		}

		return currentDriver;
	}
}