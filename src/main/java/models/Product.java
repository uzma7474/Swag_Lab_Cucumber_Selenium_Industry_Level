package models;

/**
 * Model representing a SauceDemo product.
 *
 * This class stores product data only.
 *
 * It does not contain: - Selenium locators - WebDriver - Page actions -
 * Assertions
 */
public class Product {

	private String productId;
	private String name;
	private String description;
	private double price;
	private String imageUrl;
	private boolean addedToCart;
	private int quantity;

	/**
	 * Default constructor.
	 */
	public Product() {
	}

	/**
	 * Constructor with basic product information.
	 *
	 * @param name        product name
	 * @param description product description
	 * @param price       product price
	 */
	public Product(String name, String description, double price) {

		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = 1;
	}

	/**
	 * Full constructor.
	 */
	public Product(String productId, String name, String description, double price, String imageUrl,
			boolean addedToCart, int quantity) {

		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
		this.addedToCart = addedToCart;
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isAddedToCart() {
		return addedToCart;
	}

	public void setAddedToCart(boolean addedToCart) {
		this.addedToCart = addedToCart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {

		if (quantity < 0) {
			throw new IllegalArgumentException("Product quantity cannot be negative");
		}

		this.quantity = quantity;
	}

	/**
	 * Calculates total price based on quantity.
	 *
	 * @return price multiplied by quantity
	 */
	public double getTotalPrice() {

		return price * quantity;
	}

	/**
	 * Returns a readable product representation.
	 */
	@Override
	public String toString() {

		return "Product{" + "productId='" + productId + '\'' + ", name='" + name + '\'' + ", description='"
				+ description + '\'' + ", price=" + price + ", imageUrl='" + imageUrl + '\'' + ", addedToCart="
				+ addedToCart + ", quantity=" + quantity + '}';
	}
}