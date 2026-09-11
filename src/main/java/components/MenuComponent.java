package components;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Reusable SauceDemo hamburger menu component.
 */
public class MenuComponent extends BasePage {

	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuButton;

	@FindBy(id = "react-burger-cross-btn")
	private WebElement closeMenuButton;

	@FindBy(id = "inventory_sidebar_link")
	private WebElement allItemsLink;

	@FindBy(id = "about_sidebar_link")
	private WebElement aboutLink;

	@FindBy(id = "logout_sidebar_link")
	private WebElement logoutLink;

	@FindBy(id = "reset_sidebar_link")
	private WebElement resetAppStateLink;

	public MenuComponent() {

		super();

		log.debug("Initializing MenuComponent");

		PageFactory.initElements(driver, this);

		log.debug("MenuComponent initialized successfully");
	}

	/**
	 * Opens hamburger menu.
	 */
	public void open() {

		log.info("Opening SauceDemo hamburger menu");

		try {

			click(menuButton);

			log.info("Hamburger menu opened successfully");

		} catch (Exception e) {

			log.error("Failed to open hamburger menu", e);

			throw e;
		}
	}

	/**
	 * Closes hamburger menu.
	 */
	public void close() {

		log.info("Closing SauceDemo hamburger menu");

		try {

			click(closeMenuButton);

			log.info("Hamburger menu closed successfully");

		} catch (Exception e) {

			log.error("Failed to close hamburger menu", e);

			throw e;
		}
	}

	/**
	 * Navigates to All Items.
	 */
	public void clickAllItems() {

		log.info("Clicking All Items menu option");

		click(allItemsLink);

		log.info("All Items selected successfully");
	}

	/**
	 * Navigates to About page.
	 */
	public void clickAbout() {

		log.info("Clicking About menu option");

		click(aboutLink);

		log.info("About option selected successfully");
	}

	/**
	 * Logs out current user.
	 */
	public void logout() {

		log.info("Logging out from SauceDemo");

		try {

			click(logoutLink);

			log.info("Logout completed successfully");

		} catch (Exception e) {

			log.error("Failed to logout from SauceDemo", e);

			throw e;
		}
	}

	/**
	 * Resets SauceDemo application state.
	 */
	public void resetAppState() {

		log.info("Resetting SauceDemo application state");

		try {

			click(resetAppStateLink);

			log.info("SauceDemo application state reset successfully");

		} catch (Exception e) {

			log.error("Failed to reset SauceDemo application state", e);

			throw e;
		}
	}

	/**
	 * Checks whether menu is displayed.
	 */
	public boolean isMenuOpen() {

		boolean displayed = isDisplayed(closeMenuButton);

		log.debug("Hamburger menu open: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether menu button is displayed.
	 */
	public boolean isMenuButtonDisplayed() {

		boolean displayed = isDisplayed(menuButton);

		log.debug("Menu button displayed: {}", displayed);

		return displayed;
	}
}