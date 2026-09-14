
package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page_object_manager.PageObjectManager;
import pages.CartPage;

/**
 * Business actions for SauceDemo Cart functionality.
 */
public class CartAction {

	private static final Logger log = LoggerFactory.getLogger(CartAction.class);

	private final CartPage cartPage;

	private final PageObjectManager pageObjectManager;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public CartAction(CartPage cartPage) {

		if (cartPage == null) {
			throw new IllegalArgumentException("CartPage must not be null");
		}

		this.cartPage = cartPage;
		this.pageObjectManager = new PageObjectManager();
	}

	public CartAction(CartPage cartPage, PageObjectManager pageObjectManager) {

		if (cartPage == null) {
			throw new IllegalArgumentException("CartPage must not be null");
		}

		this.cartPage = cartPage;

		/*
		 * Use the PageObjectManager supplied by the caller.
		 */
		this.pageObjectManager = pageObjectManager != null ? pageObjectManager : new PageObjectManager();
	}

	// =========================================================
	// NAVIGATION
	// =========================================================

	/**
	 * Opens SauceDemo Cart page directly.
	 */
	public void openCartPage() {

		log.info("Opening SauceDemo Cart page");

		cartPage.open();
	}

	/**
	 * Opens Cart page by clicking shopping cart icon.
	 */
	public void openCartFromHeader() {

		log.info("Opening Cart page from shopping cart icon");

		cartPage.openCartFromHeader();
	}

	
	public String getCurrentUrl() {
		log.info("Current URL");

		return cartPage.getCurrentUrl();
	}
	
	// =========================================================
	// CART ITEMS
	// =========================================================

	/**
	 * Returns the number of products currently present in the cart.
	 */
	public int getCartItemCount() {

		log.info("Getting cart item count");

		return cartPage.getCartItemCount();
	}

	/**
	 * Removes a product from the cart using product name.
	 */
	public void removeProduct(String productName) {

		log.info("Removing product from cart: {}", productName);

		cartPage.removeProduct(productName);
	}

	/**
	 * Removes a product from the cart using index.
	 */
	public void removeProduct(int index) {

		log.info("Removing product from cart at index: {}", index);

		cartPage.removeProduct(index);
	}

	// =========================================================
	// PRODUCT INFORMATION
	// =========================================================

	/**
	 * Returns product name using index.
	 */
	public String getProductName(int index) {

		log.info("Getting cart product name at index: {}", index);

		return cartPage.getProductName(index);
	}

	/**
	 * Returns product price using product name.
	 */
	public String getProductPrice(String productName) {

		log.info("Getting price for cart product: {}", productName);

		return cartPage.getProductPrice(productName);
	}

	/**
	 * Returns product quantity using product name.
	 */
	public int getProductQuantity(String productName) {

		log.info("Getting quantity for cart product: {}", productName);

		return cartPage.getProductQuantity(productName);
	}

	/**
	 * Returns product description using product name.
	 */
	public String getProductDescription(String productName) {

		log.info("Getting description for cart product: {}", productName);

		return cartPage.getProductDescription(productName);
	}

	// =========================================================
	// REMOVE PRODUCT
	// =========================================================

	/**
	 * Removes a product from the cart.
	 */
	public void removeProductFromCart(String productName) {

		log.info("Removing product from cart: {}", productName);

		cartPage.removeProduct(productName);
	}

	// =========================================================
	// CONTINUE SHOPPING
	// =========================================================

	/**
	 * Clicks Continue Shopping button.
	 */
	public void clickContinueShopping() {

		log.info("Clicking Continue Shopping button");

		cartPage.clickContinueShopping();
	}

	/**
	 * Performs Continue Shopping action.
	 */
	public void continueShopping() {

		log.info("Continuing shopping");

		clickContinueShopping();
		
		 log.info("Continue Shopping button clicked successfully");
	}

	// =========================================================
	// CHECKOUT
	// =========================================================

	/**
	 * Clicks Checkout button.
	 */
	public void clickCheckout() {

		log.info("Clicking Checkout button");

		cartPage.clickCheckout();
	}

	/**
	 * Performs checkout action.
	 */
	public void checkout() {

		log.info("Performing checkout");

		clickCheckout();
	}

	// =========================================================
	// CART BADGE
	// =========================================================

	/**
	 * Returns shopping cart badge count.
	 */
	public int getCartBadgeCount() {

		log.info("Getting shopping cart badge count");

		return cartPage.getCartBadgeCount();
	}

	// =========================================================
	// CART STATE
	// =========================================================

	/**
	 * Checks whether cart is empty.
	 */
	public boolean isCartEmpty() {

		log.info("Checking whether cart is empty");

		return cartPage.isCartEmpty();
	}

	/**
	 * Checks whether cart contains products.
	 */
	public boolean isCartNotEmpty() {

		log.info("Checking whether cart is not empty");

		return cartPage.isCartNotEmpty();
	}

	/**
	 * Checks whether product is displayed in cart.
	 */
	public boolean isProductDisplayed(String productName) {

		log.info("Checking product in cart: {}", productName);

		return cartPage.isProductDisplayed(productName);
	}

	// =========================================================
	// REFRESH
	// =========================================================

	/**
	 * Refreshes Cart page.
	 */
	public void refreshCartPage() {

		log.info("Refreshing Cart page");

		cartPage.refresh();
	}
	
	
	public void addAllProductsToCart() { 
		log.info("Adding all available products to the cart"); 
		cartPage.addAllProductsToCart(); 
		//inventoryPage.addAllProductsToCart();
		log.info("All available products added to the cart"); 
		
	}
	
    /**
     * Navigates from Inventory page to Shopping Cart.
     */
    public void clickShoppingCart() {

        log.info("Clicking Shopping Cart icon");

        cartPage.clickShoppingCart();
        

        log.info("Shopping Cart icon clicked successfully");
    }
	
	
	
}
