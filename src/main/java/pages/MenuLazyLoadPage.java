package pages;

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

public class MenuLazyLoadPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(MenuLazyLoadPage.class);

	private final WebDriverWait wait;

	// =========================================================
	// PAGE LOCATORS
	// =========================================================

	@FindBy(css = "header[data-test='header-container']")
	private WebElement headerContainer;

	@FindBy(css = "span[data-test='title']")
	private WebElement pageTitle;

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

	@FindBy(css = "div.bm-menu")
	private WebElement menuContainer;

	@FindBy(css = "nav.bm-item-list")
	private WebElement menuItemList;

	@FindBy(css = "a.bm-item")
	private List<WebElement> menuItems;

	// =========================================================
	// DYNAMIC CATALOG MENU
	// =========================================================

	@FindBy(css = "a[data-test='dynamic-catalog-sidebar-link']")
	private WebElement dynamicCatalogOption;

	@FindBy(css = "a[data-test='dynamic-catalog-sidebar-link'] " + "span.submenu-chevron")
	private WebElement dynamicCatalogChevron;

	// =========================================================
	// DYNAMIC CATALOG LOCATORS
	// =========================================================

	@FindBy(css = "div[data-test='dynamic-catalog-lazy-load-container']")
	private WebElement catalogContainer;

	@FindBy(css = "div.dynamic_catalog_card")
	private List<WebElement> productItems;

	@FindBy(css = "div.dynamic_catalog_card_name")
	private List<WebElement> productNames;

	@FindBy(css = "div.dynamic_catalog_card_price")
	private List<WebElement> productPrices;

	@FindBy(css = "img.dynamic_catalog_card_img")
	private List<WebElement> productImages;

	@FindBy(css = "div.dynamic_catalog_card_placeholder")
	private List<WebElement> loadingPlaceholders;

	// =========================================================
	// CART LOCATORS
	// =========================================================

	@FindBy(css = "a[data-test='shopping-cart-link']")
	private WebElement shoppingCartLink;

	@FindBy(css = ".shopping_cart_badge")
	private WebElement shoppingCartBadge;

	// =========================================================
	// DYNAMIC LOCATORS
	// =========================================================

	private final By productCardLocator = By.cssSelector(".dynamic_catalog_card[data-test^='lazy-load-item-']");

	private final By catalogContainerLocator = By.cssSelector("div[data-test='dynamic-catalog-lazy-load-container']");

	private final By productItemLocator = By.cssSelector("div.dynamic_catalog_card");

	private final By productNameLocator = By.cssSelector("div.dynamic_catalog_card_name");

	private final By productPriceLocator = By.cssSelector("div.dynamic_catalog_card_price");

	private final By productImageLocator = By.cssSelector("img.dynamic_catalog_card_img");

	private final By placeholderLocator = By.cssSelector("div.dynamic_catalog_card_placeholder");

	private final By menuItemLocator = By.cssSelector("a.bm-item");

	private final By menuContainerLocator = By.cssSelector("div.bm-menu");

	private final By dynamicCatalogOptionLocator = By.cssSelector("a[data-test='dynamic-catalog-sidebar-link']");

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public MenuLazyLoadPage() {

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

		log.info("Opening Dynamic Catalog Lazy Load page: {}", lazyLoadUrl);

		driver.get(lazyLoadUrl);

		waitForPageToLoad();

		wait.until(ExpectedConditions.visibilityOf(pageTitle));

		wait.until(ExpectedConditions.visibilityOf(catalogContainer));

		log.info("Dynamic Catalog Lazy Load page opened successfully");

		log.info("Current URL: {}", driver.getCurrentUrl());

		log.info("Page title: {}", getPageTitleText());
	}

	public void refresh() {

		log.info("Refreshing Dynamic Catalog Lazy Load page");

		driver.navigate().refresh();

		waitForPageToLoad();

		wait.until(ExpectedConditions.visibilityOf(catalogContainer));

		log.info("Page refreshed successfully");
	}

	private void waitForPageToLoad() {

		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));

		log.debug("Page loading completed");
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

	public boolean isPageDisplayed() {

		try {

			return pageTitle.isDisplayed() && catalogContainer.isDisplayed();

		} catch (Exception e) {

			log.warn("Dynamic Catalog page is not displayed", e);

			return false;
		}
	}

	public String getCurrentUrl() {

		String currentUrl = driver.getCurrentUrl();

		log.debug("Current page URL: {}", currentUrl);

		return currentUrl;
	}

	public boolean isLazyLoadPageOpened() {

		return getCurrentUrl().contains("dynamic-catalog-lazy-load.html");
	}

	public String getPageTitleText() {

		String title = pageTitle.getText().trim();

		log.debug("Page title text: [{}]", title);

		return title;
	}

	public boolean isPageTitleDisplayed() {

		try {

			return pageTitle.isDisplayed();

		} catch (Exception e) {

			log.debug("Page title is not displayed");

			return false;
		}
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

		if (!isMenuDisplayed()) {

			log.debug("Menu is already closed");

			return;
		}

		log.info("Closing menu");

		wait.until(ExpectedConditions.elementToBeClickable(menuCloseButton)).click();

		log.info("Menu closed");
	}

	// =========================================================
	// MENU ITEM OPERATIONS
	// =========================================================

	public boolean areMenuItemsDisplayed() {

		try {

			if (!isMenuDisplayed()) {
				return false;
			}

			List<WebElement> items = driver.findElements(menuItemLocator);

			return !items.isEmpty() && items.stream().allMatch(WebElement::isDisplayed);

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

		String option = menuOption.trim();

		log.info("Clicking menu option: {}", option);

		By locator;

		if (option.equalsIgnoreCase("Dynamic Catalog")) {

			locator = dynamicCatalogOptionLocator;

		} else {

			locator = By.xpath("//a[contains(@class,'bm-item') " + "and normalize-space(.)='" + option + "']");
		}

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		element.click();

		log.info("Menu option clicked: {}", option);
	}

	// =========================================================
	// DYNAMIC CATALOG MENU
	// =========================================================

	public boolean isDynamicCatalogDisplayed() {

		try {

			return dynamicCatalogOption.isDisplayed();

		} catch (Exception e) {

			log.debug("Dynamic Catalog option is not displayed");

			return false;
		}
	}

	public boolean isDynamicCatalogExpanded() {

		try {

			String expanded = dynamicCatalogOption.getAttribute("aria-expanded");

			log.info("Dynamic Catalog aria-expanded: {}", expanded);

			return "true".equalsIgnoreCase(expanded);

		} catch (Exception e) {

			log.warn("Unable to determine Dynamic Catalog expanded state", e);

			return false;
		}
	}

	public boolean isDynamicCatalogCollapsed() {

		try {

			String expanded = dynamicCatalogOption.getAttribute("aria-expanded");

			return "false".equalsIgnoreCase(expanded);

		} catch (Exception e) {

			log.warn("Unable to determine Dynamic Catalog collapsed state", e);

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
	}

	public void scrollToBottom() {

		log.info("Scrolling to bottom");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void scrollDown() {

		log.info("Scrolling down");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0, window.innerHeight);");
	}

	public void scrollUp() {

		log.info("Scrolling up");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0, -window.innerHeight);");
	}

	// =========================================================
	// LAZY LOAD OPERATIONS
	// =========================================================

	public int getLoadedProductCount() {

		List<WebElement> allCards = driver.findElements(productCardLocator);

		int loadedCount = 0;

		for (WebElement card : allCards) {

			List<WebElement> productName = card.findElements(productNameLocator);

			if (!productName.isEmpty()) {
				loadedCount++;
			}
		}

		log.debug("Currently loaded product count: {}", loadedCount);

		return loadedCount;
	}

	public int getTotalCatalogCardCount() {

		int count = driver.findElements(productItemLocator).size();

		log.debug("Total catalog card count including loading placeholders: {}", count);

		return count;
	}

	public int getLoadingPlaceholderCount() {

		int count = driver.findElements(placeholderLocator).size();

		log.debug("Loading placeholder count: {}", count);

		return count;
	}

	// =========================================================
	// PRODUCT NAMES
	// =========================================================

	public List<String> getLoadedProductNames() {

		log.debug("Getting names of all loaded products");

		List<WebElement> elements = driver.findElements(productNameLocator);

		List<String> names = new ArrayList<>();

		for (WebElement element : elements) {

			if (element.isDisplayed()) {

				String name = element.getText().trim();

				if (!name.isEmpty()) {

					names.add(name);

					log.debug("Loaded product name: [{}]", name);
				}
			}
		}

		log.info("Total loaded product names found: {}", names.size());

		return names;
	}

	public boolean isSelectedMenuOptionDisplayed(String menuOption) {

		if (menuOption == null || menuOption.trim().isEmpty()) {
			throw new IllegalArgumentException("Menu option must not be null or empty");
		}

		try {
			By menuOptionLocator = By.xpath("//a[contains(@class,'bm-item') " + "and contains(@class,'menu-item') "
					+ "and normalize-space(.)='" + menuOption.trim() + "']");

			WebElement selectedMenuOption = wait
					.until(ExpectedConditions.visibilityOfElementLocated(menuOptionLocator));

			boolean displayed = selectedMenuOption.isDisplayed();

			log.info("Menu option [{}] displayed: {}", menuOption, displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("Menu option [{}] is not displayed: {}", menuOption, e.getMessage());

			return false;
		}
	}

	public void loadAllProducts() {

		log.info("Starting Dynamic Catalog lazy-load operation");

		int previousLoadedCount = getLoadedProductCount();

		for (int attempt = 1; attempt <= 20; attempt++) {

			log.debug("Lazy-load attempt {}. Current loaded products: {}, placeholders: {}", attempt,
					getLoadedProductCount(), getLoadingPlaceholderCount());

			// Scroll to the bottom to trigger lazy loading
			scrollToBottom();

			// Give the application time to load additional products
			WaitUtils.waitForSeconds(1);

			int currentLoadedCount = getLoadedProductCount();
			int placeholderCount = getLoadingPlaceholderCount();

			log.info("Attempt {} completed. Loaded products: {}, remaining placeholders: {}", attempt,
					currentLoadedCount, placeholderCount);

			// All placeholders have disappeared
			if (placeholderCount == 0) {

				log.info("All products have been loaded successfully. Total loaded products: {}", currentLoadedCount);

				break;
			}

			// No additional products were loaded
			if (currentLoadedCount == previousLoadedCount) {

				log.debug("No increase in loaded product count on attempt {}", attempt);

				// Try one more scroll before deciding loading has stopped
				scrollDown();
				WaitUtils.waitForSeconds(1);

				int afterAdditionalScroll = getLoadedProductCount();

				if (afterAdditionalScroll == currentLoadedCount) {

					log.warn(
							"Product count did not increase after additional scrolling. "
									+ "Loaded: {}, Remaining placeholders: {}",
							afterAdditionalScroll, getLoadingPlaceholderCount());

					break;
				}

				currentLoadedCount = afterAdditionalScroll;
			}

			previousLoadedCount = currentLoadedCount;
		}

		log.info("Dynamic Catalog lazy-load operation finished. Loaded products: {}, remaining placeholders: {}",
				getLoadedProductCount(), getLoadingPlaceholderCount());
	}

	// =========================================================
	// PRODUCT PRICES
	// =========================================================

	public List<String> getLoadedProductPrices() {

		log.debug("Getting prices of all loaded products");

		List<WebElement> elements = driver.findElements(productPriceLocator);

		List<String> prices = new ArrayList<>();

		for (WebElement element : elements) {

			if (element.isDisplayed()) {

				String price = element.getText().trim();

				if (!price.isEmpty()) {

					prices.add(price);

					log.debug("Loaded product price: [{}]", price);
				}
			}
		}

		log.info("Total loaded product prices found: {}", prices.size());

		return prices;
	}

	// =========================================================
	// PRODUCT IMAGES
	// =========================================================

	public List<WebElement> getProductImages() {

	    List<WebElement> loadedProductImages = new ArrayList<>();

	    List<WebElement> loadedProducts = getProductItems();

	    for (WebElement product : loadedProducts) {

	        List<WebElement> images =
	                product.findElements(productImageLocator);

	        if (!images.isEmpty()) {
	            loadedProductImages.add(images.get(0));
	        }
	    }

	    log.debug(
	            "Loaded product images found: {}",
	            loadedProductImages.size()
	    );

	    return loadedProductImages;
	}
	
	public void waitForLazyLoadingToComplete() {

	    log.info("Waiting for Dynamic Catalog lazy loading");

	    int previousCount = getLoadedProductCount();

	    for (int attempt = 1; attempt <= 10; attempt++) {

	        WaitUtils.waitForSeconds(1);

	        int currentCount = getLoadedProductCount();
	        int placeholderCount = getLoadingPlaceholderCount();

	        log.debug(
	                "Lazy-load attempt {}: loaded={}, placeholders={}",
	                attempt,
	                currentCount,
	                placeholderCount
	        );

	        // No loading placeholders remain
	        if (placeholderCount == 0) {

	            log.info(
	                    "Lazy loading completed. Loaded products: {}",
	                    currentCount
	            );

	            return;
	        }

	        // Product count increased
	        if (currentCount > previousCount) {

	            log.info(
	                    "Additional products loaded. Previous: {}, Current: {}",
	                    previousCount,
	                    currentCount
	            );

	            previousCount = currentCount;
	        }
	    }

	    log.info(
	            "Lazy-load wait completed. Loaded products: {}, placeholders: {}",
	            getLoadedProductCount(),
	            getLoadingPlaceholderCount()
	    );
	}
	
	

	
	
	public List<WebElement> getProductItems() {

	    List<WebElement> allCards =
	            driver.findElements(productCardLocator);

	    List<WebElement> loadedProducts =
	            new ArrayList<>();

	    for (WebElement card : allCards) {

	        List<WebElement> names =
	                card.findElements(productNameLocator);

	        List<WebElement> prices =
	                card.findElements(productPriceLocator);

	        /*
	         * A card is considered a loaded product only when
	         * it contains both a product name and a price.
	         */
	        if (!names.isEmpty() && !prices.isEmpty()) {

	            loadedProducts.add(card);
	        }
	    }

	    log.debug(
	            "Total catalog cards: {}, loaded products: {}",
	            allCards.size(),
	            loadedProducts.size()
	    );

	    return loadedProducts;
	}

	public List<WebElement> getProductNames() {

		return driver.findElements(productNameLocator);
	}

	public List<WebElement> getProductPrices() {

		return driver.findElements(productPriceLocator);
	}

	// =========================================================
	// LOADING PLACEHOLDERS
	// =========================================================

	public boolean areLoadingPlaceholdersDisplayed() {

		try {

			List<WebElement> placeholders = driver.findElements(placeholderLocator);

			return !placeholders.isEmpty() && placeholders.stream().allMatch(WebElement::isDisplayed);

		} catch (Exception e) {

			log.debug("Unable to verify loading placeholders", e);

			return false;
		}
	}

	// =========================================================
	// PRODUCT VALIDATION SUPPORT
	// =========================================================

	public boolean areProductNamesDisplayed() {

		return areElementsDisplayed(productNameLocator);
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
	// CATALOG CONTAINER
	// =========================================================

	public boolean isCatalogContainerDisplayed() {

		try {

			WebElement catalogContainerElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(catalogContainerLocator));

			boolean displayed = catalogContainerElement.isDisplayed();

			log.info("Dynamic Catalog container displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.warn("Dynamic Catalog container is not displayed: {}", e.getMessage());

			return false;
		}
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

			return 0;
		}

		String badgeText = shoppingCartBadge.getText().trim();

		try {

			return Integer.parseInt(badgeText);

		} catch (NumberFormatException e) {

			throw new AssertionError("Cart badge does not contain a valid number: " + badgeText, e);
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
	}

	public void browserForward() {

		log.info("Navigating browser forward");

		driver.navigate().forward();

		waitForPageToLoad();
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