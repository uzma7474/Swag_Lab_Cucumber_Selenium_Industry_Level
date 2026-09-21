
package context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page_object_manager.PageObjectManager;

public class ScenarioContext {

	private static final Logger log = LoggerFactory.getLogger(ScenarioContext.class);

	private final Map<String, Object> scenarioData = new HashMap<>();

	private PageObjectManager pageObjectManager;

	private List<String> cartProductNames;

	private List<String> cartProductPrices;

	private String cartSubtotal;
	
	private static double cartSubtotalDouble;

	/**
	 * Constructor.
	 *
	 * Initializes PageObjectManager for the current Cucumber scenario.
	 */
	public ScenarioContext() {

		pageObjectManager = new PageObjectManager();

		log.info("ScenarioContext initialized with PageObjectManager");
	}

	/**
	 * Returns PageObjectManager.
	 *
	 * @return PageObjectManager instance
	 */
	public PageObjectManager getPageObjectManager() {

		return pageObjectManager;
	}

	/**
	 * Sets PageObjectManager.
	 *
	 * @param pageObjectManager PageObjectManager instance
	 */
	public void setPageObjectManager(PageObjectManager pageObjectManager) {

		if (pageObjectManager == null) {

			throw new IllegalArgumentException("PageObjectManager must not be null");
		}

		this.pageObjectManager = pageObjectManager;

		log.info("PageObjectManager updated in ScenarioContext");
	}

	/**
	 * Returns product names stored from Cart.
	 *
	 * @return list of Cart product names
	 */
	public List<String> getCartProductNames() {

		log.info("Retrieving Cart product names from ScenarioContext: {}", cartProductNames);

		return cartProductNames;
	}

	public List<String> getCartProductPrices() {

		log.info("Getting Cart product prices: {}", cartProductPrices);

		return cartProductPrices;
	}

	public void setCartProductPrices(List<String> cartProductPrices) {

		this.cartProductPrices = cartProductPrices;

		log.info("Cart product prices stored: {}", cartProductPrices);
	}

	/**
	 * * Returns the Cart subtotal stored in ScenarioContext. * * @return Cart
	 * subtotal
	 */
	public String getCartSubtotal() {

		log.info("Retrieving Cart subtotal from ScenarioContext: {}", cartSubtotal);

		return cartSubtotal;

	}
	
	/**
	 * * Returns the Cart subtotal stored in ScenarioContext. * * @return Cart
	 * subtotal
	 */
	public Double getCartSubtotalDouble() {

		log.info("Retrieving Cart subtotal from ScenarioContext: {}", cartSubtotal);

		return cartSubtotalDouble;

	}

	/**
	 * * Stores the Cart subtotal in ScenarioContext. * * @param cartSubtotal Cart
	 * subtotal
	 */
	public void setCartSubtotal(String cartSubtotal) {

		this.cartSubtotal = cartSubtotal;

		log.info("Cart subtotal stored in ScenarioContext: {}", cartSubtotal);

	}
	
	

	public static void setCartSubtotal(double subtotal) {
		cartSubtotalDouble = subtotal;
	}

	/**
	 * Stores product names from Cart.
	 *
	 * @param cartProductNames list of Cart product names
	 */
	public void setCartProductNames(List<String> cartProductNames) {

		this.cartProductNames = cartProductNames;

		log.info("Cart product names stored in ScenarioContext: {}", cartProductNames);
	}

	/* * @param key context key * @param value value to store */
	public void set(String key, Object value) {
		scenarioData.put(key, value);

	}
	
	/** * Retrieve a value from the scenario context. * * @param key context key * @param type expected value type * @param <T> generic return type * @return stored value converted to the requested type */ 
	public <T> T get(String key, Class<T> type) { 
		Object value = scenarioData.get(key); 
		if (value == null) { 
			return null; 
			
		} 
		if (!type.isInstance(value)) { 
			throw new IllegalArgumentException( "Value stored for key '" + key + "' is not of expected type " + type.getSimpleName() + ". Actual type: " + value.getClass().getSimpleName() ); 
			
		} 
		return type.cast(value); 
		
	}
	
	/** * Check whether a key exists in the scenario context. 
	 *  * @param key context key *
	 *   @return true if key exists and has a non-null value 
	 *   */ 
	public boolean contains(String key) { 
		return scenarioData.containsKey(key) && scenarioData.get(key) != null; 
		
	} 
	
	
	/** * Remove a value from the scenario context. *
	 *  * @param key context key */ 
	public void remove(String key) { 
		scenarioData.remove(key); 
		
	}
	
	

	/**
	 * Clears scenario-specific data.
	 *
	 * Should be called after the scenario is completed.
	 */
	public void clear() {

		cartProductNames = null;
		cartProductPrices = null;
		cartSubtotal = null;
		scenarioData.clear();
		log.info("ScenarioContext data cleared");
	}
}
