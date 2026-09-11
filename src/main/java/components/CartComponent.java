package components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Reusable component representing cart items on SauceDemo.
 */
public class CartComponent extends BasePage {

	private final By cartItems = By.cssSelector(".cart_item");

	private final By itemName = By.cssSelector(".inventory_item_name");

	private final By itemPrice = By.cssSelector(".inventory_item_price");

	private final By itemQuantity = By.cssSelector(".cart_quantity");

	private final By removeButton = By.cssSelector("button[id^='remove']");

	public CartComponent() {

		super();

		log.debug("Initializing CartComponent");

		PageFactory.initElements(driver, this);

		log.debug("CartComponent initialized successfully");
	}

	/**
	 * Returns number of products currently in cart.
	 */
	public int getCartItemCount() {

		log.debug("Retrieving number of cart items");

		int count = driver.findElements(cartItems).size();

		log.info("Cart contains {} item(s)", count);

		return count;
	}

	/**
	 * Returns all product names in cart.
	 */
	public List<String> getItemNames() {

		log.debug("Retrieving cart item names");

		List<WebElement> items = driver.findElements(cartItems);

		List<String> names = new ArrayList<>();

		for (WebElement item : items) {

			String name = item.findElement(itemName).getText();

			names.add(name);
		}

		log.info("Cart item names: {}", names);

		return names;
	}

	/**
	 * Returns all product prices in cart.
	 */
	public List<String> getItemPrices() {

		log.debug("Retrieving cart item prices");

		List<WebElement> items = driver.findElements(cartItems);

		List<String> prices = new ArrayList<>();

		for (WebElement item : items) {

			String price = item.findElement(itemPrice).getText();

			prices.add(price);
		}

		log.info("Cart item prices: {}", prices);

		return prices;
	}

	/**
	 * Returns all product quantities in cart.
	 */
	public List<String> getItemQuantities() {

		log.debug("Retrieving cart item quantities");

		List<WebElement> items = driver.findElements(cartItems);

		List<String> quantities = new ArrayList<>();

		for (WebElement item : items) {

			String quantity = item.findElement(itemQuantity).getText();

			quantities.add(quantity);
		}

		log.info("Cart item quantities: {}", quantities);

		return quantities;
	}

	/**
	 * Checks whether a product exists in cart.
	 */
	public boolean isItemPresent(String productName) {

		log.debug("Checking whether product exists in cart: {}", productName);

		if (productName == null || productName.trim().isEmpty()) {

			throw new IllegalArgumentException("Product name cannot be null or empty");
		}

		List<WebElement> items = driver.findElements(cartItems);

		for (WebElement item : items) {

			String name = item.findElement(itemName).getText();

			if (productName.equalsIgnoreCase(name)) {

				log.info("Product found in cart: {}", productName);

				return true;
			}
		}

		log.info("Product not found in cart: {}", productName);

		return false;
	}

	/**
	 * Removes a specific product from cart.
	 */
	public void removeItem(String productName) {

		log.info("Removing product from cart: {}", productName);

		if (productName == null || productName.trim().isEmpty()) {

			throw new IllegalArgumentException("Product name cannot be null or empty");
		}

		List<WebElement> items = driver.findElements(cartItems);

		for (WebElement item : items) {

			String name = item.findElement(itemName).getText();

			if (productName.equalsIgnoreCase(name)) {

				WebElement remove = item.findElement(removeButton);

				click(remove);

				log.info("Product removed from cart: {}", productName);

				return;
			}
		}

		log.warn("Product was not found in cart: {}", productName);

		throw new IllegalArgumentException("Product not found in cart: " + productName);
	}

	/**
	 * Removes all products from cart.
	 */
	public void removeAllItems() {

		log.info("Removing all products from cart");

		List<WebElement> items = driver.findElements(cartItems);

		for (WebElement item : items) {

			WebElement remove = item.findElement(removeButton);

			click(remove);
		}

		log.info("All cart products removed successfully");
	}

	/**
	 * Checks whether cart is empty.
	 */
	public boolean isEmpty() {

		boolean empty = getCartItemCount() == 0;

		log.info("Cart empty: {}", empty);

		return empty;
	}
}