package models;

public class DynamicCatalogProduct {

	private final String name;
	private final String price;
	private final String imageAlt;

	public DynamicCatalogProduct(String name, String price, String imageAlt) {

		this.name = name;
		this.price = price;
		this.imageAlt = imageAlt;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public String getImageAlt() {
		return imageAlt;
	}

	@Override
	public String toString() {
		return "DynamicCatalogProduct{" + "name='" + name + '\'' + ", price='" + price + '\'' + ", imageAlt='"
				+ imageAlt + '\'' + '}';
	}
}