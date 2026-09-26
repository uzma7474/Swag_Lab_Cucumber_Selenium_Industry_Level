package pages;

import base.BasePage;
import config.EnvironmentManager;
import models.DynamicCatalogProduct;
import utils.WaitUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuSliderPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(MenuSliderPage.class);

	// =========================================================
	// PAGE LOCATORS
	// =========================================================

	/**
	 * Page title.
	 *
	 * Expected: Dynamic Catalog - Slider
	 */
	@FindBy(css = "[data-test='title']")
	private WebElement pageTitle;

	/**
	 * Header container.
	 */
	@FindBy(css = "[data-test='header-container']")
	private WebElement headerContainer;

	/**
	 * Primary header.
	 */
	@FindBy(css = "[data-test='primary-header']")
	private WebElement primaryHeader;

	/**
	 * Secondary header.
	 */
	@FindBy(css = "[data-test='secondary-header']")
	private WebElement secondaryHeader;

	// =========================================================
	// BURGER MENU
	// =========================================================

	/**
	 * Open Menu button.
	 */
	@FindBy(id = "react-burger-menu-btn")
	private WebElement openMenuButton;

	/**
	 * Open Menu icon.
	 */
	@FindBy(css = "[data-test='open-menu']")
	private WebElement openMenuIcon;

	/**
	 * Close Menu button.
	 */
	@FindBy(id = "react-burger-cross-btn")
	private WebElement closeMenuButton;

	/**
	 * Close Menu icon.
	 */
	@FindBy(css = "[data-test='close-menu']")
	private WebElement closeMenuIcon;

	/**
	 * Menu wrapper.
	 */
	@FindBy(css = ".bm-menu-wrap")
	private WebElement menuWrapper;

	/**
	 * Menu navigation.
	 */
	@FindBy(css = ".bm-item-list")
	private WebElement menuItemList;

	// =========================================================
	// MENU ITEMS
	// =========================================================

	/**
	 * All Items menu option.
	 */
	@FindBy(css = "[data-test='inventory-sidebar-link']")
	private WebElement allItemsMenuItem;

	/**
	 * Dynamic Catalog menu option.
	 */
	@FindBy(css = "[data-test='dynamic-catalog-sidebar-link']")
	private WebElement dynamicCatalogMenuItem;

	/**
	 * About menu option.
	 */
	@FindBy(css = "[data-test='about-sidebar-link']")
	private WebElement aboutMenuItem;

	/**
	 * Logout menu option.
	 */
	@FindBy(css = "[data-test='logout-sidebar-link']")
	private WebElement logoutMenuItem;

	/**
	 * Reset App State menu option.
	 */
	@FindBy(css = "[data-test='reset-sidebar-link']")
	private WebElement resetAppStateMenuItem;

	// =========================================================
	// CART
	// =========================================================

	/**
	 * Shopping cart container.
	 */
	@FindBy(id = "shopping_cart_container")
	private WebElement shoppingCartContainer;

	/**
	 * Shopping cart link.
	 */
	@FindBy(css = "[data-test='shopping-cart-link']")
	private WebElement shoppingCartLink;

	/**
	 * Shopping cart badge.
	 */
	@FindBy(css = ".shopping_cart_badge")
	private WebElement shoppingCartBadge;

	// =========================================================
	// DYNAMIC CATALOG
	// =========================================================

	/**
	 * Dynamic catalog container.
	 *
	 * This locator is intentionally generic because the supplied DOM does not
	 * contain the actual slider markup.
	 */
	@FindBy(css = "main")
	private WebElement mainContent;

	// ==========================================================================
	// WebElement
	// ==========================================================================

	@FindBy(css = "[data-test='dynamic-catalog-slider-container']")
	private WebElement sliderContainer;

	@FindBy(css = "[data-test='dynamic-catalog-slider-item']")
	private WebElement productCard;

	@FindBy(css = "[data-test^='dynamic-catalog-slider-dot-']")
	private List<WebElement> navigationDots;

	@FindBy(css = "[data-test='dynamic-catalog-slider-item-img']")
	private WebElement productImage;

	@FindBy(css = "[data-test='dynamic-catalog-slider-item-name']")
	private WebElement productName;

	@FindBy(css = "[data-test='dynamic-catalog-slider-item-price']")
	private WebElement productPrice;

	@FindBy(css = "[data-test='dynamic-catalog-slider-dot-0']")
	private WebElement bikeLightDot;

	@FindBy(css = "[data-test='dynamic-catalog-slider-dot-1']")
	private WebElement boltTShirtDot;

	@FindBy(css = "[data-test='dynamic-catalog-slider-dot-2']")
	private WebElement onesieDot;

	@FindBy(css = "[data-test='dynamic-catalog-slider-dot-3']")
	private WebElement testAllTheThingsDot;

	@FindBy(css = "[data-test='dynamic-catalog-slider-dot-4']")
	private WebElement backpackDot;

	@FindBy(css = "[data-test='dynamic-catalog-slider-dot-5']")
	private WebElement fleeceJacketDot;

	// =========================================================
	// LOCATORS
	// =========================================================

	// private final By sliderContainer =
	// By.cssSelector("[data-test='dynamic-catalog-slider-container']");

	// private final By productName =
	// By.cssSelector("[data-test='dynamic-catalog-slider-item-name']");

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public MenuSliderPage() {

		super();

		PageFactory.initElements(driver, this);

		log.debug("MenuSliderPage initialized");
	}

	// =========================================================
	// PAGE NAVIGATION
	// =========================================================

	/**
	 * Opens Dynamic Catalog Slider page directly.
	 */
	public void open() {

		String baseUrl = EnvironmentManager.getBaseUrl();

		String sliderUrl = baseUrl.endsWith("/") ? baseUrl + "dynamic-catalog-slider.html"
				: baseUrl + "/dynamic-catalog-slider.html";

		log.info("Opening Dynamic Catalog Slider page: {}", sliderUrl);

		driver.get(sliderUrl);

		waitForPageToLoad();

		log.info("Dynamic Catalog Slider page opened successfully");
	}

//	public DynamicCatalogProduct getCurrentProduct() {
//
//	    String name = getProductName();
//	    String price = getProductPrice();
//	    String imageAlt = getProductImageAlt();
//
//	    return new DynamicCatalogProduct(
//	            name,
//	            price,
//	            imageAlt
//	    );
//	}

	// =========================================================
	// Return price as string
	// =========================================================
	public String getProductPrice() {

		log.debug("Getting currently displayed slider product price");

		String price = productPrice.getText().trim();

		log.debug("Current slider product price: {}", price);

		return price;
	}

	// =========================================================
	// Return product name as string
	// =========================================================
	public String getProductName() {

		log.debug("Getting currently displayed slider product name");

		String name = productName.getText().trim();

		log.debug("Current slider product name: {}", name);

		return name;
	}

	// =========================================================
	// Return src value as String
	// =========================================================
	public String getImageSrc() {

		log.debug("Getting currently displayed slider product image source");

		String imageSrc = productImage.getAttribute("src");

		log.debug("Current slider product image source: {}", imageSrc);

		return imageSrc;
	}

	// =========================================================
	// Return alt value
	// =========================================================
	public String getImageAlt() {

		log.debug("Getting currently displayed slider product image alt text");

		String imageAlt = productImage.getAttribute("alt");

		log.debug("Current slider product image alt text: {}", imageAlt);

		return imageAlt;
	}

	// =========================================================
	// ACTIVE DOT INDEX
	// =========================================================

	public int getActiveDotIndex() {

		log.debug("Getting active slider dot index");

		for (int i = 0; i < navigationDots.size(); i++) {

			WebElement dot = navigationDots.get(i);

			String ariaCurrent = dot.getAttribute("aria-current");

			if ("true".equalsIgnoreCase(ariaCurrent)) {

				log.debug("Active slider dot index: {}", i);

				return i;
			}
		}

		log.warn("No active slider dot found");

		return -1;
	}

	// MenuSliderPage.java

	public void clickProductSliderDot(String productName) {

	    log.info("Locating slider dot for product: {}", productName);

	    WebElement dot = driver.findElement(
	        By.xpath("//button[contains(@aria-label,'" + productName + "')]")
	    );

	    WaitUtils.waitForElementToBeClickable(driver, dot);

	    dot.click();

	    log.info("Clicked slider dot for product: {}", productName);
	}
	
	
	
//	public void clickProductSliderDot(String productName) {
//
//		log.info("Clicking slider dot for product: {}", productName);
//
//		WebElement productDot = wait.until(driver -> {
//			List<WebElement> dots = driver.findElements(By.cssSelector(".inventory_list .slick-dots li button"));
//
//			for (WebElement dot : dots) {
//				String ariaLabel = dot.getAttribute("aria-label");
//
//				if (ariaLabel != null && ariaLabel.contains(productName)) {
//					return dot;
//				}
//			}
//
//			return null;
//		});
//
//		if (productDot == null) {
//			throw new NoSuchElementException("Slider dot not found for product: " + productName);
//		}
//
//		productDot.click();
//
//		log.info("Successfully clicked slider dot for product: {}", productName);
//	}

	// =========================================================
	// PRODUCT CARD
	// =========================================================

	public boolean isProductCardDisplayed() {

		log.debug("Checking whether slider product card is displayed");

		boolean displayed = productCard.isDisplayed();

		log.debug("Slider product card displayed: {}", displayed);

		return displayed;
	}

	// =========================================================
	// PRODUCT IMAGE
	// =========================================================

	public boolean isProductImageDisplayed() {

		log.debug("Checking whether slider product image is displayed");

		boolean displayed = productImage.isDisplayed();

		log.debug("Slider product image displayed: {}", displayed);

		return displayed;
	}

	public WebElement getProductImage() {

		log.debug("Getting currently displayed dynamic slider product image");

		return productImage;
	}

	public boolean isImageLoaded() {

		log.debug("Checking whether slider product image is completely loaded");

		try {

			Boolean imageLoaded = (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].complete && " + "arguments[0].naturalWidth > 0;", productImage);

			log.debug("Slider product image loaded: {}", imageLoaded);

			return Boolean.TRUE.equals(imageLoaded);

		} catch (Exception e) {

			log.error("Failed to verify whether slider product image is loaded", e);

			return false;
		}
	}

	// =========================================================
	// PRODUCT NAME
	// =========================================================

	public boolean isProductNameDisplayed() {

		log.debug("Checking whether slider product name is displayed");

		boolean displayed = productName.isDisplayed();

		log.debug("Slider product name displayed: {}", displayed);

		return displayed;
	}

	public boolean isValidProduct(String productName) {

		if (productName == null || productName.isBlank()) {
			log.warn("Product name is null or blank");
			return false;
		}

		log.debug("Checking whether product is valid in slider: {}", productName);

		String expectedAriaLabel = "Show " + productName;

		for (WebElement dot : navigationDots) {

			String ariaLabel = dot.getAttribute("aria-label");

			if (expectedAriaLabel.equals(ariaLabel)) {

				log.debug("Valid slider product found: {}", productName);

				return true;
			}
		}

		log.warn("Product not found in slider: {}", productName);

		return false;
	}

	// =========================================================
	// PRODUCT PRICE
	// =========================================================

	public boolean isProductPriceDisplayed() {

		log.debug("Checking whether slider product price is displayed");

		boolean displayed = productPrice.isDisplayed();

		log.debug("Slider product price displayed: {}", displayed);

		return displayed;
	}

	// =========================================================
	// NAVIGATION DOTS
	// =========================================================

	public List<WebElement> getNavigationDots() {

		log.debug("Getting dynamic slider navigation dots");

		return navigationDots;
	}

	public WebElement getActiveDot() {
		log.debug("Getting active dynamic slider navigation dot");

		for (WebElement dot : navigationDots) {

			String ariaCurrent = dot.getAttribute("aria-current");

			if ("true".equalsIgnoreCase(ariaCurrent)) {

				log.debug("Active slider navigation dot found");

				return dot;
			}
		}

		log.error("No active slider navigation dot found");
		throw new IllegalStateException("No active navigation dot found. Expected one dot with aria-current='true'.");
	}

	public WebElement getProductDot(String productName) {

		if (productName == null || productName.isBlank()) {
			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		log.debug("Getting slider navigation dot for product: {}", productName);

		String expectedAriaLabel = "Show " + productName;

		for (WebElement dot : navigationDots) {

			String ariaLabel = dot.getAttribute("aria-label");

			if (expectedAriaLabel.equals(ariaLabel)) {
				log.debug("Slider navigation dot found for product: {}", productName);
				return dot;
			}
		}

		log.error("No slider navigation dot found for product: {}", productName);

		throw new IllegalArgumentException("No slider navigation dot found for product: " + productName);
	}

	public int getNavigationDotCount() {

		log.debug("Getting dynamic slider navigation dot count");

		int count = navigationDots.size();

		log.debug("Dynamic slider navigation dot count: {}", count);

		return count;
	}

	public void clickDot(int index) {

		log.debug("Clicking slider navigation dot at index: {}", index);

		if (index < 0 || index >= navigationDots.size()) {
			throw new IllegalArgumentException(
					"Invalid slider dot index: " + index + ". Valid range: 0 to " + (navigationDots.size() - 1));
		}

		WebElement dot = navigationDots.get(index);

		dot.click();

		log.debug("Successfully clicked slider navigation dot at index: {}", index);
	}

	public void clickProductDot(String productName) {

		if (productName == null || productName.isBlank()) {

			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		log.debug("Clicking slider navigation dot for product: {}", productName);

		String expectedAriaLabel = "Show " + productName;

		for (WebElement dot : navigationDots) {

			String ariaLabel = dot.getAttribute("aria-label");

			if (expectedAriaLabel.equals(ariaLabel)) {

				dot.click();

				log.debug("Successfully clicked slider dot for product: {}", productName);

				return;
			}
		}

		throw new IllegalArgumentException("No slider navigation dot found for product: " + productName);
	}

	public String getProductDotAriaCurrent(String productName) {

		if (productName == null || productName.isBlank()) {
			throw new IllegalArgumentException("Product name must not be null or blank");
		}

		log.debug("Getting aria-current value for slider product: {}", productName);

		String expectedAriaLabel = "Show " + productName;

		for (WebElement dot : navigationDots) {

			String ariaLabel = dot.getAttribute("aria-label");

			if (expectedAriaLabel.equals(ariaLabel)) {

				String ariaCurrent = dot.getAttribute("aria-current");

				log.debug("Product: {}, aria-current: {}", productName, ariaCurrent);

				return ariaCurrent;
			}
		}

		throw new IllegalArgumentException("No slider navigation dot found for product: " + productName);
	}

	public String getDotAriaLabel(int index) {

		log.debug("Getting aria-label for slider navigation dot at index: {}", index);

		if (index < 0 || index >= navigationDots.size()) {
			throw new IllegalArgumentException(
					"Invalid slider dot index: " + index + ". Valid range: 0 to " + (navigationDots.size() - 1));
		}

		String ariaLabel = navigationDots.get(index).getAttribute("aria-label");

		log.debug("Slider dot index: {}, aria-label: {}", index, ariaLabel);

		return ariaLabel;
	}

	// ====================================================================================
	// Scroll To Slider
	// ====================================================================================
	public void scrollToSlider() {
		log.debug("Scrolling to dynamic catalog slider");

		try {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", sliderContainer);

			log.debug("Successfully scrolled to dynamic catalog slider");

		} catch (Exception e) {
			log.error("Failed to scroll to dynamic catalog slider", e);
			throw new RuntimeException("Unable to scroll to dynamic catalog slider", e);
		}
	}

	// =========================================================
	// Waits until the page is completely loaded.
	// =========================================================
	public void waitForPageToLoad() {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			wait.until(ExpectedConditions.visibilityOf(pageTitle));

			log.debug("Dynamic Catalog Slider page loaded");

		} catch (TimeoutException e) {

			log.error("Dynamic Catalog Slider page did not load within timeout", e);

			throw e;
		}
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

	// =========================================================
	// Returns page title text.
	// =========================================================
	public String getPageTitle() {

		try {

			String title = pageTitle.getText().trim();

			log.debug("Page title: {}", title);

			return title;

		} catch (NoSuchElementException e) {

			log.error("Unable to find Dynamic Catalog page title", e);

			return "";
		}
	}

	// =========================================================
	// Returns true if page title is displayed.
	// =========================================================
	public boolean isPageTitleDisplayed() {

		try {

			boolean displayed = pageTitle.isDisplayed();

			log.debug("Dynamic Catalog page title displayed: {}", displayed);

			return displayed;

		} catch (NoSuchElementException e) {

			log.warn("Dynamic Catalog page title was not found");

			return false;
		}
	}

	// =========================================================
	// Returns current URL.
	// =========================================================
	public String getCurrentUrl() {

		String currentUrl = driver.getCurrentUrl();

		log.debug("Current URL: {}", currentUrl);

		return currentUrl;
	}

	// =========================================================
	// Verifies whether the current page is Dynamic Catalog Slider.
	// =========================================================
	public boolean isDynamicCatalogSliderPage() {

		String currentUrl = driver.getCurrentUrl();

		boolean result = currentUrl.contains("dynamic-catalog-slider.html");

		log.debug("Is Dynamic Catalog Slider page: {}", result);

		return result;
	}

	// =========================================================
	// BURGER MENU OPERATIONS
	// =========================================================

	// =========================================================
	// Opens the hamburger menu.
	// =========================================================
	public void openMenu() {

		try {

			if (!isMenuOpen()) {

				log.info("Opening hamburger menu");

				WaitUtils.waitForElementClickable(openMenuButton);

				openMenuButton.click();

				waitForMenuToOpen();

				log.info("Hamburger menu opened");

			} else {

				log.debug("Hamburger menu is already open");
			}

		} catch (Exception e) {

			log.error("Failed to open hamburger menu", e);

			throw e;
		}
	}

	// =========================================================
	// Closes the hamburger menu.
	// =========================================================
	public void closeMenu() {

		try {

			if (isMenuOpen()) {

				log.info("Closing hamburger menu");

				WaitUtils.waitForElementClickable(closeMenuButton);

				closeMenuButton.click();

				waitForMenuToClose();

				log.info("Hamburger menu closed");

			} else {

				log.debug("Hamburger menu is already closed");
			}

		} catch (Exception e) {

			log.error("Failed to close hamburger menu", e);

			throw e;
		}
	}

	// =========================================================
	// Checks whether menu is currently open.
	// =========================================================
	public boolean isMenuOpen() {

		try {

			String ariaHidden = menuWrapper.getAttribute("aria-hidden");

			boolean open = "false".equalsIgnoreCase(ariaHidden);

			log.debug("Menu open status: {}", open);

			return open;

		} catch (Exception e) {

			log.debug("Unable to determine menu state", e);

			return false;
		}
	}

	// =========================================================
	// Waits until menu becomes visible.
	// =========================================================
	private void waitForMenuToOpen() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(driver -> {

			try {

				return "false".equalsIgnoreCase(menuWrapper.getAttribute("aria-hidden"));

			} catch (Exception e) {

				return false;
			}
		});
	}

	// =========================================================
	// Waits until menu becomes hidden.
	// =========================================================
	private void waitForMenuToClose() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(driver -> {

			try {

				String ariaHidden = menuWrapper.getAttribute("aria-hidden");

				return "true".equalsIgnoreCase(ariaHidden);

			} catch (Exception e) {

				return true;
			}
		});
	}

	// =========================================================
	// MENU ITEM OPERATIONS
	// =========================================================

	// =========================================================
	// Clicks All Items from hamburger menu.
	// =========================================================
	public void clickAllItems() {

		openMenu();

		log.info("Clicking All Items");

		WaitUtils.waitForElementClickable(allItemsMenuItem);

		allItemsMenuItem.click();
	}

	// =========================================================
	// Clicks Dynamic Catalog from hamburger menu.
	// =========================================================
	public void clickDynamicCatalog() {

		openMenu();

		log.info("Clicking Dynamic Catalog");

		WaitUtils.waitForElementClickable(dynamicCatalogMenuItem);

		dynamicCatalogMenuItem.click();

		waitForPageToLoad();

		log.info("Dynamic Catalog selected");
	}

	// =========================================================
	// Clicks About from hamburger menu.
	// =========================================================
	public void clickAbout() {

		openMenu();

		log.info("Clicking About");

		WaitUtils.waitForElementClickable(aboutMenuItem);

		aboutMenuItem.click();
	}

	// =========================================================
	// Clicks Logout from hamburger menu.
	// =========================================================
	public void clickLogout() {

		openMenu();

		log.info("Clicking Logout");

		WaitUtils.waitForElementClickable(logoutMenuItem);

		logoutMenuItem.click();

		log.info("Logout clicked");
	}

	// =========================================================
	// Clicks Reset App State.
	// =========================================================
	public void clickResetAppState() {

		openMenu();

		log.info("Clicking Reset App State");

		WaitUtils.waitForElementClickable(resetAppStateMenuItem);

		resetAppStateMenuItem.click();

		log.info("Reset App State clicked");
	}

	// =========================================================
	// MENU ITEM VALIDATIONS
	// =========================================================

	// =========================================================
	// Returns all visible menu item names.
	// =========================================================
	public List<String> getMenuItemNames() {

		openMenu();

		List<WebElement> menuItems = driver.findElements(By.cssSelector(".bm-item-list .menu-item"));

		List<String> names = menuItems.stream().filter(WebElement::isDisplayed).map(element -> element.getText().trim())
				.filter(text -> !text.isEmpty()).collect(Collectors.toList());

		log.debug("Menu items: {}", names);

		return names;
	}

	// =========================================================
	// Returns number of menu items.
	// =========================================================
	public int getMenuItemCount() {

		openMenu();

		int count = driver.findElements(By.cssSelector(".bm-item-list .menu-item")).size();

		log.debug("Menu item count: {}", count);

		return count;
	}

	// =========================================================
	// Checks whether a particular menu item is displayed.
	// =========================================================
	public boolean isMenuItemDisplayed(String menuItem) {

		openMenu();

		List<WebElement> menuItems = driver.findElements(By.cssSelector(".bm-item-list .menu-item"));

		boolean found = menuItems.stream()
				.anyMatch(element -> element.isDisplayed() && element.getText().trim().equalsIgnoreCase(menuItem));

		log.debug("Menu item '{}' displayed: {}", menuItem, found);

		return found;
	}

	public boolean isSliderDisplayed() {

		log.debug("Checking whether dynamic slider is displayed");

		return sliderContainer.isDisplayed();
	}

	// =========================================================
	// CART OPERATIONS
	// =========================================================

	/**
	 * Opens shopping cart.
	 */
	public void clickShoppingCart() {

		log.info("Opening shopping cart");

		WaitUtils.waitForElementClickable(shoppingCartLink);

		shoppingCartLink.click();

		log.info("Shopping cart opened");
	}

	/**
	 * Checks whether cart icon is displayed.
	 */
	public boolean isShoppingCartDisplayed() {

		try {

			boolean displayed = shoppingCartContainer.isDisplayed();

			log.debug("Shopping cart displayed: {}", displayed);

			return displayed;

		} catch (NoSuchElementException e) {

			return false;
		}
	}

	/**
	 * Returns cart badge count.
	 *
	 * If badge does not exist, returns 0.
	 */
	public int getCartItemCount() {

		try {

			if (!shoppingCartBadge.isDisplayed()) {

				return 0;
			}

			String badgeText = shoppingCartBadge.getText().trim();

			if (badgeText.isEmpty()) {

				return 0;
			}

			int count = Integer.parseInt(badgeText);

			log.debug("Cart item count: {}", count);

			return count;

		} catch (NoSuchElementException e) {

			log.debug("Cart badge not displayed; cart count = 0");

			return 0;

		} catch (NumberFormatException e) {

			log.warn("Unable to parse cart badge value");

			return 0;
		}
	}

	/**
	 * Checks whether cart is empty.
	 */
	public boolean isCartEmpty() {

		int count = getCartItemCount();

		boolean empty = count == 0;

		log.debug("Cart empty: {}", empty);

		return empty;
	}

	// =========================================================
	// MAIN CONTENT
	// =========================================================

	/**
	 * Checks whether main content is displayed.
	 */
	public boolean isMainContentDisplayed() {

		try {

			boolean displayed = mainContent.isDisplayed();

			log.debug("Main content displayed: {}", displayed);

			return displayed;

		} catch (NoSuchElementException e) {

			log.warn("Main content was not found");

			return false;
		}
	}

	// =========================================================
	// JAVASCRIPT HELPERS
	// =========================================================

	/**
	 * Scrolls page to top.
	 */
	public void scrollToTop() {

		log.debug("Scrolling page to top");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0, 0);");
	}

	/**
	 * Scrolls page to bottom.
	 */
	public void scrollToBottom() {

		log.debug("Scrolling page to bottom");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	/**
	 * Scrolls an element into view.
	 */
	public void scrollIntoView(WebElement element) {

		if (element == null) {

			throw new IllegalArgumentException("Element cannot be null");
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	}

	// =========================================================
	// GENERIC ELEMENT HELPERS
	// =========================================================

	/**
	 * Returns visible text from an element.
	 */
	public String getElementText(WebElement element) {

		if (element == null) {

			throw new IllegalArgumentException("Element cannot be null");
		}

		try {

			String text = element.getText().trim();

			log.debug("Element text: {}", text);

			return text;

		} catch (Exception e) {

			log.error("Unable to get element text", e);

			return "";
		}
	}

	/**
	 * Checks whether an element is displayed.
	 */
	public boolean isElementDisplayed(WebElement element) {

		if (element == null) {

			return false;
		}

		try {

			return element.isDisplayed();

		} catch (NoSuchElementException e) {

			return false;
		}
	}

	/**
	 * Performs JavaScript click.
	 */
	public void javaScriptClick(WebElement element) {

		if (element == null) {

			throw new IllegalArgumentException("Element cannot be null");
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", element);

		log.debug("JavaScript click performed");
	}
}
