package page_object_manager;

import pages.CartPage;
import pages.Checkout_Step_Two_Page;
import pages.CheckoutInformationPage;
import pages.Checkout_Step_One_Page;
import pages.InventoryPage;
import pages.CheckoutCompletePage;
import pages.LoginPage;

public class PageObjectManager {

	private LoginPage loginPage;

	private InventoryPage inventoryPage;

	private CheckoutCompletePage productDetailsPage;

	private CartPage cartPage;

	private CheckoutInformationPage checkoutInformationPage;

	private Checkout_Step_One_Page checkoutStepOnePage;

	private Checkout_Step_Two_Page checkoutStepTwoPage;

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

	public CheckoutCompletePage getProductDetailsPage() {

		if (productDetailsPage == null) {

			productDetailsPage = new CheckoutCompletePage();
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

	public Checkout_Step_Two_Page getCheckoutStepTwoPage() {

		if (checkoutStepTwoPage == null) {

			checkoutStepTwoPage = new Checkout_Step_Two_Page();
		}

		return checkoutStepTwoPage;
	}
}