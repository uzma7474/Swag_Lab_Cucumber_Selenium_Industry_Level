
package context;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page_object_manager.PageObjectManager;

public class ScenarioContext {

	private static final Logger log = LoggerFactory.getLogger(ScenarioContext.class);

	private PageObjectManager pageObjectManager;

	private List<String> cartProductNames;
	
	private List<String> cartProductPrices;
	
	private String cartSubtotal; 

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

	    log.info(
	            "Getting Cart product prices: {}",
	            cartProductPrices
	    );

	    return cartProductPrices;
	}

	public void setCartProductPrices(
	        List<String> cartProductPrices) {

	    this.cartProductPrices =
	            cartProductPrices;

	    log.info(
	            "Cart product prices stored: {}",
	            cartProductPrices
	    );
	}

	
	/** * Returns the Cart subtotal stored in ScenarioContext. *
	 * * @return Cart subtotal 
	 * */ 
	public String getCartSubtotal() { 
		
		log.info( "Retrieving Cart subtotal from ScenarioContext: {}", cartSubtotal ); 
		
		return cartSubtotal; 
		
	}
	
	/** * Stores the Cart subtotal in ScenarioContext. * 
	 * * @param cartSubtotal Cart subtotal 
	 * */ 
	public void setCartSubtotal(String cartSubtotal) { 
		
		this.cartSubtotal = cartSubtotal; 
		
		log.info( "Cart subtotal stored in ScenarioContext: {}", cartSubtotal ); 
		
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

	/**
	 * Clears scenario-specific data.
	 *
	 * Should be called after the scenario is completed.
	 */
	public void clear() {
	
		cartProductNames = null; 
		cartProductPrices = null; 
		cartSubtotal = null; 
		
		log.info("ScenarioContext data cleared");
	}
}
