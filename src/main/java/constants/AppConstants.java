package constants;

/**
 * Application-wide constants used by the automation framework.
 */
public final class AppConstants {

	/**
	 * Private constructor to prevent object creation.
	 */
	private AppConstants() {
	}

	//================================================================================
	// Configuration Files
	//================================================================================

	public static final String CONFIG_FILE = "config/config.properties";

	public static final String QA_CONFIG_FILE = "config/qa.properties";

	public static final String PROD_CONFIG_FILE = "config/prod.properties";

	//================================================================================
	// Environment
	//================================================================================

	public static final String ENVIRONMENT_KEY = "environment";

	public static final String QA_ENVIRONMENT = "qa";

	public static final String PROD_ENVIRONMENT = "prod";

	public static final String DEFAULT_ENVIRONMENT = QA_ENVIRONMENT;

	//================================================================================
	// Application
	//================================================================================

	public static final String APPLICATION_URL_KEY = "application.url";

	public static final String DEFAULT_APPLICATION_URL = "https://www.saucedemo.com/";
	
	//================================================================================
	// Inventory URL
	//================================================================================
	
	public static final String INVENTORY_URL_KEY = "inventory.url";
	
	public static final String INVENTORY_URL = "https://www.saucedemo.com/inventory.html";
	
	//================================================================================
	// Cart URL
	//================================================================================
	
	public static final String CART_URL_KEY = "cart";
	
	public static final String CART_URL = "https://www.saucedemo.com/cart.html";
		
	//================================================================================
	// Checkout step one  URL
	//================================================================================
	
	public static final String CHECKOUT_STEP_ONE_URL_KEY = "checkout.step.one";
	
	public static final String CHECKOUT_STEP_ONE_URL = "https://www.saucedemo.com/checkout-step-one.html";
		
	//================================================================================
	// Checkout step two  URL
	//================================================================================
	
	public static final String CHECKOUT_STEP_TWO_URL_KEY = "checkout.step.two";
	
	public static final String CHECKOUT_STEP_TWO_URL = "https://www.saucedemo.com/checkout-step-two.html";
				
	//================================================================================
	// Checkout Complete URL
	//================================================================================
	
	public static final String CHECKOUT_COMPLETE_URL_KEY = "checkout.complete";
	
	public static final String CHECKOUT_COMPLETE_URL = "https://www.saucedemo.com/checkout-complete.html";
				

	//================================================================================
	// Browser
	//================================================================================

	public static final String BROWSER_KEY = "browser";

	public static final String CHROME = "chrome";

	public static final String FIREFOX = "firefox";

	public static final String EDGE = "edge";

	public static final String DEFAULT_BROWSER = CHROME;

	//================================================================================
	// Browser Configuration
	//================================================================================

	public static final String HEADLESS_KEY = "headless";

	public static final String MAXIMIZE_KEY = "maximize";

	//================================================================================
	// Timeout Configuration
	//================================================================================

	public static final String IMPLICIT_WAIT_KEY = "implicit.wait";

	public static final String EXPLICIT_WAIT_KEY = "explicit.wait";

	public static final String PAGE_LOAD_TIMEOUT_KEY = "page.load.timeout";

	public static final String SCRIPT_TIMEOUT_KEY = "script.timeout";

	//================================================================================
	// Default Timeout Values
	//================================================================================
	
	public static final int DEFAULT_IMPLICIT_WAIT = 0;

	public static final int DEFAULT_EXPLICIT_WAIT = 15;

	public static final int DEFAULT_PAGE_LOAD_TIMEOUT = 30;

	public static final int DEFAULT_SCRIPT_TIMEOUT = 30;
	
	public static final int DEFAULT_TIMEOUT = 15;

	//================================================================================
	// Screenshot Configuration
	//================================================================================

	public static final String SCREENSHOT_ON_FAILURE_KEY = "screenshot.on.failure";

	public static final String SCREENSHOT_AFTER_STEP_KEY = "screenshot.after.step";

	//================================================================================
	// Reporting
	//================================================================================

	public static final String CUCUMBER_REPORT_KEY = "cucumber.report";

	public static final String EXTENT_REPORT_KEY = "extent.report";

	//================================================================================
	// Logging
	//================================================================================

	public static final String LOG_LEVEL_KEY = "log.level";

	public static final String DEFAULT_LOG_LEVEL = "INFO";
	
	//================================================================================
	// Side Menu bar
	//================================================================================
	
	public static final String LAZY_LOAD_KEY = "lazy.load";
	
	public static final String LAZY_LOAD_URL = "https://www.saucedemo.com/dynamic-catalog-lazy-load.html";
	
	public static final String SPINNER_KEY = "spinner";
	
	public static final String SPINNER_URL = "https://www.saucedemo.com/dynamic-catalog-spinner.html";
	
	public static final String SLIDER_KEY = "slider";
	
	public static final String SLIDER_URL = "https://www.saucedemo.com/dynamic-catalog-slider.html";
	
	//================================================================================
	//
	//================================================================================
	
}
