package page_object_manager;

import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutInformationPage;
import pages.Checkout_Step_One_Page;
import pages.InventoryPage;
import pages.ProductDetailsPage;
import pages.LoginPage;

public class PageObjectManager {

	private LoginPage loginPage;

	private InventoryPage inventoryPage;

	private ProductDetailsPage productDetailsPage;

	private CartPage cartPage;

	private CheckoutInformationPage checkoutInformationPage;

	private Checkout_Step_One_Page checkoutStepOnePage;

	private CheckoutCompletePage checkoutCompletePage;

	public LoginPage getLoginPage() {

		if (loginPage == null) {

			loginPage = new LoginPage();
		}

		return loginPage;
	}

	public InventoryPage getInventoryPage() {

		if (inventoryPage == null) {

			inventoryPage = new InventoryPage();
		}

		return inventoryPage;
	}

	public ProductDetailsPage getProductDetailsPage() {

		if (productDetailsPage == null) {

			productDetailsPage = new ProductDetailsPage();
		}

		return productDetailsPage;
	}

	public CartPage getCartPage() {

		if (cartPage == null) {

			cartPage = new CartPage();
		}

		return cartPage;
	}

	public CheckoutInformationPage getCheckoutInformationPage() {

		if (checkoutInformationPage == null) {

			checkoutInformationPage = new CheckoutInformationPage();
		}

		return checkoutInformationPage;
	}

	public Checkout_Step_One_Page getCheckout_Step_One_Page() {

		if (checkoutStepOnePage == null) {

			checkoutStepOnePage = new Checkout_Step_One_Page();
		}

		return checkoutStepOnePage;
	}

	public CheckoutCompletePage getCheckoutCompletePage() {

		if (checkoutCompletePage == null) {

			checkoutCompletePage = new CheckoutCompletePage();
		}

		return checkoutCompletePage;
	}
}