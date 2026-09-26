
package actions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import driver.DriverManager;
import page_object_manager.PageObjectManager;
import pages.MenuSliderPage;
import utils.WaitUtils;

public class MenuSliderAction {

	private static final Logger log = LoggerFactory.getLogger(MenuSliderAction.class);

	private final MenuSliderPage menuSliderPage;

	private final PageObjectManager pageObjectManager;

	public MenuSliderAction(PageObjectManager pageObjectManager) {

		if (pageObjectManager == null) {
			throw new IllegalArgumentException("PageObjectManager must not be null");
		}

		this.pageObjectManager = pageObjectManager;

		this.menuSliderPage = pageObjectManager.getMenuSliderPage();

		if (this.menuSliderPage == null) {
			throw new IllegalStateException("MenuSliderPage must not be null");
		}

		log.debug("MenuSliderActions initialized");
	}

	// ============================================================
	// SLIDER INFORMATION
	// ============================================================

	/**
	 * Returns the currently displayed product name.
	 */
	public String getProductName() {

		String productName = menuSliderPage.getProductName();

		log.debug("Current slider product name: {}", productName);

		return productName;
	}

	/**
	 * Returns the currently displayed product price.
	 */
	public String getProductPrice() {

		String productPrice = menuSliderPage.getProductPrice();

		log.debug("Current slider product price: {}", productPrice);

		return productPrice;
	}

	/**
	 * Returns the current product image source.
	 */
	public String getImageSrc() {

		String imageSrc = menuSliderPage.getImageSrc();

		log.debug("Current slider image source: {}", imageSrc);

		return imageSrc;
	}

	/**
	 * Returns the current product image alt text.
	 */
	public String getImageAlt() {

		String imageAlt = menuSliderPage.getImageAlt();

		log.debug("Current slider image alt text: {}", imageAlt);

		return imageAlt;
	}


	//=======================================================================================
	// Returns the index of the currently active slider dot.
	//========================================================================================
	public int getActiveDotIndex_() {

		int activeDotIndex = menuSliderPage.getActiveDotIndex();

		log.debug("Current active slider dot index: {}", activeDotIndex);

		return activeDotIndex;
	}
	
	

	// ============================================================
	// SLIDER DISPLAY
	// ============================================================

	/**
	 * Checks whether the dynamic slider is displayed.
	 */
	public boolean isSliderDisplayed() {

		boolean displayed = menuSliderPage.isSliderDisplayed();

		log.debug("Dynamic slider displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether the current product card is displayed.
	 */
	public boolean isProductCardDisplayed() {

		boolean displayed = menuSliderPage.isProductCardDisplayed();

		log.debug("Slider product card displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether the product image is displayed.
	 */
	public boolean isProductImageDisplayed() {

		boolean displayed = menuSliderPage.isProductImageDisplayed();

		log.debug("Slider product image displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether the product name is displayed.
	 */
	public boolean isProductNameDisplayed() {

		boolean displayed = menuSliderPage.isProductNameDisplayed();

		log.debug("Slider product name displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether the product price is displayed.
	 */
	public boolean isProductPriceDisplayed() {

		boolean displayed = menuSliderPage.isProductPriceDisplayed();

		log.debug("Slider product price displayed: {}", displayed);

		return displayed;
	}

	// ============================================================
	// NAVIGATION DOTS
	// ============================================================

	/**
	 * Returns all slider navigation dots.
	 */
	public List<WebElement> getNavigationDots() {

		List<WebElement> dots = menuSliderPage.getNavigationDots();

		log.debug("Number of slider navigation dots: {}", dots.size());

		return dots;
	}

	/**
	 * Returns number of navigation dots.
	 */
	public int getNavigationDotCount() {

		int count = menuSliderPage.getNavigationDotCount();

		log.debug("Slider navigation dot count: {}", count);

		return count;
	}
	
	public void clickProductSliderDot(String productName) {

	    log.info("Finding slider dot for product: {}", productName);

	    menuSliderPage.clickProductSliderDot(productName);
	}

	/**
	 * Clicks a slider navigation dot by index.
	 */
	public void clickDot(int index) {

		log.info("Clicking slider navigation dot: {}", index);

		menuSliderPage.clickDot(index);
	}

	/**
	 * Clicks the navigation dot corresponding to a product.
	 */
	public void clickProductDot(String productName) {

		if (productName == null || productName.trim().isEmpty()) {

			throw new IllegalArgumentException("Product name must not be null or empty");
		}

		log.info("Clicking slider dot for product: {}", productName);

		menuSliderPage.clickProductDot(productName);
	}

	/**
	 * Returns the aria-current value of a product's navigation dot.
	 */
	public String getProductDotAriaCurrent(String productName) {

		if (productName == null || productName.trim().isEmpty()) {

			throw new IllegalArgumentException("Product name must not be null or empty");
		}

		String ariaCurrent = menuSliderPage.getProductDotAriaCurrent(productName);

		log.debug("Product '{}' aria-current: {}", productName, ariaCurrent);

		return ariaCurrent;
	}

	/**
	 * Returns the aria-label of a navigation dot.
	 */
	public String getDotAriaLabel(int index) {

		String ariaLabel = menuSliderPage.getDotAriaLabel(index);

		log.debug("Slider dot {} aria-label: {}", index, ariaLabel);

		return ariaLabel;
	}

	// ============================================================
	// AUTOMATIC ROTATION
	// ============================================================

	/**
	 * Waits until the slider displays a different product.
	 *
	 * This is preferable to Thread.sleep() because the slider automatically changes
	 * after a dynamic interval.
	 */
	public void waitForProductChange(String previousProductName) {

		if (previousProductName == null || previousProductName.trim().isEmpty()) {

			throw new IllegalArgumentException("Previous product name must not be null or empty");
		}

		log.info("Waiting for slider product to change from: {}", previousProductName);

		WaitUtils.waitForCondition(driver -> {

			String currentProduct = menuSliderPage.getProductName();

			return currentProduct != null && !currentProduct.equals(previousProductName);
		});

		log.info("Slider product changed from: {} to: {}", previousProductName, getProductName());
	}

	/**
	 * Waits until the active navigation dot changes.
	 */
	public void waitForActiveDotChange(int previousDotIndex) {

		log.info("Waiting for active slider dot to change from: {}", previousDotIndex);

		WaitUtils.waitForCondition(driver -> menuSliderPage.getActiveDotIndex() != previousDotIndex);

		log.info("Active slider dot changed from {} to {}", previousDotIndex, menuSliderPage.getActiveDotIndex());
	}

	/**
	 * Waits until a specific product is displayed.
	 */
	public void waitForProduct(String expectedProductName) {

		if (expectedProductName == null || expectedProductName.trim().isEmpty()) {

			throw new IllegalArgumentException("Expected product name must not be null or empty");
		}

		log.info("Waiting for slider product: {}", expectedProductName);

		WaitUtils.waitForCondition(driver -> expectedProductName.equals(menuSliderPage.getProductName()));

		log.info("Slider product displayed: {}", expectedProductName);
	}

	// ============================================================
	// PRODUCT INFORMATION
	// ============================================================

	/**
	 * Returns the complete current slider product information.
	 */
	public String getCurrentProductInformation() {

		String productName = getProductName();

		String productPrice = getProductPrice();

		String imageAlt = getImageAlt();

		String imageSrc = getImageSrc();

		String information = "Product=" + productName + ", Price=" + productPrice + ", ImageAlt=" + imageAlt
				+ ", ImageSrc=" + imageSrc;

		log.debug("Current slider product information: {}", information);

		return information;
	}

	/**
	 * Returns whether the currently displayed product is one of the valid slider
	 * products.
	 */
	public boolean isValidDisplayedProduct() {

		String productName = getProductName();

		boolean valid = menuSliderPage.isValidProduct(productName);

		log.debug("Is '{}' a valid slider product: {}", productName, valid);

		return valid;
	}

	// ============================================================
	// IMAGE VALIDATION SUPPORT
	// ============================================================

	/**
	 * Checks whether the current product image is loaded.
	 */
	public boolean isImageLoaded() {

		boolean loaded = menuSliderPage.isImageLoaded();

		log.debug("Slider product image loaded: {}", loaded);

		return loaded;
	}

	// ============================================================
	// PAGE ACTIONS
	// ============================================================

	/**
	 * Refreshes the current page.
	 */
	public void refreshPage() {

		log.info("Refreshing current page");

		WebDriver driver = DriverManager.getDriver();

		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized");
		}

		driver.navigate().refresh();

		log.info("Page refresh completed");
	}

	/**
	 * Scrolls to the slider.
	 */
	public void scrollToSlider() {

		log.debug("Scrolling to dynamic product slider");

		menuSliderPage.scrollToSlider();
	}
	
	public int getActiveDotIndex() {

	    log.debug("Getting active slider navigation dot index");

	    int activeDotIndex = menuSliderPage.getActiveDotIndex();

	    log.debug(
	            "Current active slider navigation dot index: {}",
	            activeDotIndex
	    );

	    return activeDotIndex;
	}

	
	
	
	// ============================================================
	// UTILITY
	// ============================================================

	/**
	 * Gets the underlying MenuSliderPage.
	 */
	public MenuSliderPage getMenuSliderPage() {

		return menuSliderPage;
	}

	/**
	 * Gets the PageObjectManager.
	 */
	public PageObjectManager getPageObjectManager() {

		return pageObjectManager;
	}
}
