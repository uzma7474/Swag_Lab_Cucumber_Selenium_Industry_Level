package constants;

/**
 * Central repository for SauceDemo page-related constants.
 *
 * Responsibilities: - Store page URLs - Store expected page paths - Store
 * expected page titles - Store commonly used page-level values
 *
 * This class must not contain: - WebDriver - Selenium locators - Assertions -
 * Page actions
 */
public final class PageConstants {

	/**
	 * Private constructor prevents object creation.
	 */
	private PageConstants() {
		throw new UnsupportedOperationException("PageConstants class cannot be instantiated");
	}

	// ============================================================
	// APPLICATION URLS
	// ============================================================

	public static final String BASE_URL = "https://www.saucedemo.com/";

	public static final String LOGIN_URL = BASE_URL;

	public static final String INVENTORY_URL = BASE_URL + "inventory.html";

	public static final String CART_URL = BASE_URL + "cart.html";

	public static final String CHECKOUT_INFORMATION_URL = BASE_URL + "checkout-step-one.html";

	public static final String CHECKOUT_OVERVIEW_URL = BASE_URL + "checkout-step-two.html";

	public static final String CHECKOUT_COMPLETE_URL = BASE_URL + "checkout-complete.html";

	// ============================================================
	// PAGE PATHS
	// ============================================================

	public static final String LOGIN_PATH = "/";

	public static final String INVENTORY_PATH = "/inventory.html";

	public static final String CART_PATH = "/cart.html";

	public static final String CHECKOUT_INFORMATION_PATH = "/checkout-step-one.html";

	public static final String CHECKOUT_OVERVIEW_PATH = "/checkout-step-two.html";

	public static final String CHECKOUT_COMPLETE_PATH = "/checkout-complete.html";

	// ============================================================
	// PAGE TITLES
	// ============================================================

	/**
	 * Browser title used by SauceDemo.
	 */
	public static final String APPLICATION_TITLE = "Swag Labs";

	// ============================================================
	// PAGE HEADINGS
	// ============================================================

	public static final String INVENTORY_PAGE_TITLE = "Products";

	public static final String CART_PAGE_TITLE = "Your Cart";

	public static final String CHECKOUT_INFORMATION_PAGE_TITLE = "Checkout: Your Information";

	public static final String CHECKOUT_OVERVIEW_PAGE_TITLE = "Checkout: Overview";

	public static final String CHECKOUT_COMPLETE_PAGE_TITLE = "Checkout: Complete";

	// ============================================================
	// CHECKOUT MESSAGES
	// ============================================================

	public static final String ORDER_COMPLETE_HEADER = "Thank you for your order!";

	public static final String ORDER_COMPLETE_MESSAGE = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

	// ============================================================
	// CART
	// ============================================================

	public static final String EMPTY_CART_COUNT = "0";

	// ============================================================
	// COMMON PAGE VALUES
	// ============================================================

	public static final String PRODUCTS_TEXT = "Products";

	public static final String YOUR_CART_TEXT = "Your Cart";

	public static final String CHECKOUT_YOUR_INFORMATION_TEXT = "Checkout: Your Information";

	public static final String CHECKOUT_OVERVIEW_TEXT = "Checkout: Overview";

	public static final String CHECKOUT_COMPLETE_TEXT = "Checkout: Complete";
}