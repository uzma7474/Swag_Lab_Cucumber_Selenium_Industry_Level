package components;


import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Reusable component representing the common SauceDemo header.
 *
 * Responsibilities: - Application logo - Hamburger menu - Shopping cart -
 * Shopping cart badge
 *
 * This class does not contain assertions. It only performs UI interactions and
 * exposes UI state.
 */
public class HeaderComponent extends BasePage {

	@FindBy(css = ".app_logo")
	private WebElement appLogo;

	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuButton;

	@FindBy(css = ".shopping_cart_link")
	private WebElement shoppingCartLink;

	@FindBy(css = ".shopping_cart_badge")
	private WebElement cartBadge;

	public HeaderComponent() {
		super();

		log.debug("Initializing HeaderComponent");

		PageFactory.initElements(driver, this);

		log.debug("HeaderComponent initialized successfully");
	}

	/**
	 * Checks whether application logo is displayed.
	 */
	public boolean isLogoDisplayed() {

		log.debug("Checking whether SauceDemo logo is displayed");

		boolean displayed = isDisplayed(appLogo);

		log.info("SauceDemo logo displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Returns application logo text.
	 */
	public String getLogoText() {

		log.debug("Retrieving SauceDemo logo text");

		String logoText = getText(appLogo);

		log.debug("Logo text: {}", logoText);

		return logoText;
	}

	/**
	 * Opens hamburger menu.
	 */
	public void openMenu() {

		log.info("Opening hamburger menu");

		try {

			click(menuButton);

			log.info("Hamburger menu opened successfully");

		} catch (Exception e) {

			log.error("Failed to open hamburger menu", e);

			throw e;
		}
	}

	/**
	 * Opens shopping cart.
	 */
	public void openCart() {

		log.info("Opening shopping cart");

		try {

			click(shoppingCartLink);

			log.info("Shopping cart opened successfully");

		} catch (Exception e) {

			log.error("Failed to open shopping cart", e);

			throw e;
		}
	}

	/**
	 * Checks whether shopping cart icon is displayed.
	 */
	public boolean isCartDisplayed() {

		log.debug("Checking whether shopping cart is displayed");

		boolean displayed = isDisplayed(shoppingCartLink);

		log.info("Shopping cart displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether cart badge is displayed.
	 */
	public boolean isCartBadgeDisplayed() {

		log.debug("Checking whether shopping cart badge is displayed");

		boolean displayed = isDisplayed(cartBadge);

		log.info("Shopping cart badge displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Returns number displayed in cart badge.
	 *
	 * Returns 0 when badge is not displayed.
	 */
	public int getCartItemCount() {

		log.debug("Retrieving cart item count");

		if (!isDisplayed(cartBadge)) {

			log.debug("Cart badge is not displayed. Cart item count = 0");

			return 0;
		}

		String badgeText = getText(cartBadge);

		try {

			int count = Integer.parseInt(badgeText);

			log.info("Cart item count: {}", count);

			return count;

		} catch (NumberFormatException e) {

			log.error("Unable to parse cart badge value: {}", badgeText, e);

			throw new IllegalStateException("Invalid cart badge value: " + badgeText, e);
		}
	}

	/**
	 * Returns raw cart badge text.
	 */
	public String getCartBadgeText() {

		log.debug("Retrieving cart badge text");

		if (!isDisplayed(cartBadge)) {

			log.debug("Cart badge is not displayed");

			return "";
		}

		String badgeText = getText(cartBadge);

		log.debug("Cart badge text: {}", badgeText);

		return badgeText;
	}
}