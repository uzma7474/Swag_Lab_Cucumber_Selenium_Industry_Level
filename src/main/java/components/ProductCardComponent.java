package components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Reusable component representing a single SauceDemo product card.
 *
 * The component is scoped to one product using its product name.
 */
public class ProductCardComponent extends BasePage {

	private final WebElement productCard;

	private final By productName = By.cssSelector(".inventory_item_name");

	private final By productDescription = By.cssSelector(".inventory_item_desc");

	private final By productPrice = By.cssSelector(".inventory_item_price");

	private final By productImage = By.cssSelector(".inventory_item_img img");

	private final By addToCartButton = By.cssSelector("button[id^='add-to-cart']");

	private final By removeButton = By.cssSelector("button[id^='remove']");

	/**
	 * Creates a product component for the supplied product name.
	 *
	 * @param productName product name displayed on SauceDemo
	 */
	public ProductCardComponent(String productName) {

		super();

		if (productName == null || productName.trim().isEmpty()) {

			throw new IllegalArgumentException("Product name cannot be null or empty");
		}

		log.debug("Initializing ProductCardComponent for product: {}", productName);

		String escapedProductName = productName.replace("'", "\\'");

		By cardLocator = By
				.xpath("//div[contains(@class,'inventory_item')]" + "[.//div[contains(@class,'inventory_item_name')"
						+ " and normalize-space()='" + escapedProductName + "']]");

		this.productCard = driver.findElement(cardLocator);

		PageFactory.initElements(driver, this);

		log.info("ProductCardComponent initialized for product: {}", productName);
	}

	/**
	 * Returns product name.
	 */
	public String getProductName() {

		log.debug("Retrieving product name");

		String name = productCard.findElement(productName).getText();

		log.info("Product name: {}", name);

		return name;
	}

	/**
	 * Returns product description.
	 */
	public String getProductDescription() {

		log.debug("Retrieving product description");

		String description = productCard.findElement(productDescription).getText();

		log.debug("Product description: {}", description);

		return description;
	}

	/**
	 * Returns product price as displayed.
	 *
	 * Example: $29.99
	 */
	public String getProductPrice() {

		log.debug("Retrieving product price");

		String price = productCard.findElement(productPrice).getText();

		log.info("Product price: {}", price);

		return price;
	}

	/**
	 * Returns product price as numeric value.
	 *
	 * Example: "$29.99" -> 29.99
	 */
	public double getProductPriceValue() {

		String priceText = getProductPrice();

		try {

			double price = Double.parseDouble(priceText.replace("$", "").trim());

			log.debug("Product price numeric value: {}", price);

			return price;

		} catch (NumberFormatException e) {

			log.error("Unable to convert product price: {}", priceText, e);

			throw new IllegalStateException("Invalid product price: " + priceText, e);
		}
	}

	/**
	 * Checks whether product image is displayed.
	 */
	public boolean isImageDisplayed() {

		log.debug("Checking product image visibility");

		boolean displayed = isDisplayed(productCard.findElement(productImage));

		log.info("Product image displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Adds product to cart.
	 */
	public void addToCart() {

		log.info("Adding product to cart: {}", getProductName());

		try {

			WebElement button = productCard.findElement(addToCartButton);

			click(button);

			log.info("Product added to cart: {}", getProductName());

		} catch (Exception e) {

			log.error("Failed to add product to cart: {}", getProductName(), e);

			throw e;
		}
	}

	/**
	 * Removes product from cart.
	 */
	public void removeFromCart() {

		log.info("Removing product from cart: {}", getProductName());

		try {

			WebElement button = productCard.findElement(removeButton);

			click(button);

			log.info("Product removed from cart: {}", getProductName());

		} catch (Exception e) {

			log.error("Failed to remove product from cart: {}", getProductName(), e);

			throw e;
		}
	}

	/**
	 * Checks whether Add to Cart button is displayed.
	 */
	public boolean isAddToCartDisplayed() {

		boolean displayed;

		try {

			displayed = isDisplayed(productCard.findElement(addToCartButton));

		} catch (Exception e) {

			displayed = false;
		}

		log.debug("Add to Cart button displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether Remove button is displayed.
	 */
	public boolean isRemoveDisplayed() {

		boolean displayed;

		try {

			displayed = isDisplayed(productCard.findElement(removeButton));

		} catch (Exception e) {

			displayed = false;
		}

		log.debug("Remove button displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether product card itself is displayed.
	 */
	public boolean isDisplayed() {

		boolean displayed = isDisplayed(productCard);

		log.debug("Product card displayed: {}", displayed);

		return displayed;
	}
}