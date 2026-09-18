package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BasePage;
import driver.DriverManager;

public class CheckoutCompletePage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(CheckoutCompletePage.class);

	// =========================================================
	// PAGE LOCATORS
	// =========================================================

	@FindBy(css = ".title")
	private WebElement pageTitle;

	// =========================================================
	// ORDER CONFIRMATION
	// =========================================================

	@FindBy(css = ".checkout_complete_container")
	private WebElement checkoutCompleteContainer;

	@FindBy(css = ".complete-header")
	private WebElement confirmationMessage;

	@FindBy(css = ".complete-text")
	private WebElement confirmationText;

	// =========================================================
	// CONFIRMATION IMAGE
	// =========================================================

	@FindBy(css = ".pony_express")
	private WebElement ponyExpressImage;

	// =========================================================
	// NAVIGATION
	// =========================================================

	@FindBy(id = "back-to-products")
	private WebElement backHomeButton;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================

	public CheckoutCompletePage() {

		super();

		PageFactory.initElements(driver, this);

		log.info("CheckoutCompletePage initialized");
	}

	// =========================================================
	// PAGE VALIDATION
	// =========================================================

	/**
	 * Verifies that Checkout Complete page is displayed.
	 */
	public void verifyCheckoutCompletePageReady() {

		log.info("Verifying Checkout Complete page is ready");

		if (!isCheckoutCompletePageReady()) {

			throw new AssertionError("Checkout Complete page is not displayed");
		}

		log.info("Checkout Complete page is displayed successfully");
	}

	/**
	 * Checks whether Checkout Complete page is displayed.
	 */
	public boolean isCheckoutCompletePageReady() {

		try {

			return pageTitle.isDisplayed() && checkoutCompleteContainer.isDisplayed()
					&& confirmationMessage.isDisplayed() && backHomeButton.isDisplayed();

		} catch (Exception e) {

			log.error("Checkout Complete page is not ready", e);

			return false;
		}
	}

	// =========================================================
	// PAGE TITLE
	// =========================================================

	/**
	 * Returns the Checkout Complete page title.
	 */
	public String getPageTitle() {

		String title = getText(pageTitle);

		log.info("Checkout Complete page title: {}", title);

		return title;
	}

	/**
	 * Verifies the page title.
	 */
	public boolean isPageTitleDisplayed() {

		try {

			return pageTitle.isDisplayed();

		} catch (Exception e) {

			log.error("Checkout Complete page title is not displayed", e);

			return false;
		}
	}

	/**
	 * Verifies expected page title.
	 */
	public void verifyPageTitle(String expectedTitle) {

		String actualTitle = getPageTitle();

		if (!actualTitle.equals(expectedTitle)) {

			throw new AssertionError("Expected page title: " + expectedTitle + " but found: " + actualTitle);
		}

		log.info("Page title verified successfully: {}", expectedTitle);
	}

	// =========================================================
	// CONFIRMATION MESSAGE
	// =========================================================

	/**
	 * Returns the order confirmation message.
	 */
	public String getConfirmationMessage() {

		String message = getText(confirmationMessage);

		log.info("Order confirmation message: {}", message);

		return message;
	}

	/**
	 * Verifies confirmation message.
	 */
	public void verifyConfirmationMessage(String expectedMessage) {

		String actualMessage = getConfirmationMessage();

		if (!actualMessage.equals(expectedMessage)) {

			throw new AssertionError(
					"Expected confirmation message: " + expectedMessage + " but found: " + actualMessage);
		}

		log.info("Confirmation message verified successfully: {}", expectedMessage);
	}

	/**
	 * Checks whether confirmation message is displayed.
	 */
	public boolean isConfirmationMessageDisplayed() {

		try {

			return confirmationMessage.isDisplayed();

		} catch (Exception e) {

			log.error("Confirmation message is not displayed", e);

			return false;
		}
	}

	// =========================================================
	// CONFIRMATION TEXT
	// =========================================================

	/**
	 * Returns the complete confirmation text.
	 */
	public String getConfirmationText() {

		String text = getText(confirmationText);

		log.info("Checkout confirmation text: {}", text);

		return text;
	}

	/**
	 * Verifies the confirmation text.
	 */
	public void verifyConfirmationText(String expectedText) {

		String actualText = getConfirmationText();

		if (!actualText.equals(expectedText)) {

			throw new AssertionError("Expected confirmation text: " + expectedText + " but found: " + actualText);
		}

		log.info("Confirmation text verified successfully");
	}

	/**
	 * Checks whether confirmation text is displayed.
	 */
	public boolean isConfirmationTextDisplayed() {

		try {

			return confirmationText.isDisplayed();

		} catch (Exception e) {

			log.error("Confirmation text is not displayed", e);

			return false;
		}
	}

	// =========================================================
	// PONY EXPRESS IMAGE
	// =========================================================

	/**
	 * Checks whether Pony Express image is displayed.
	 */
	public boolean isPonyExpressImageDisplayed() {

		try {

			return ponyExpressImage.isDisplayed();

		} catch (Exception e) {

			log.error("Pony Express image is not displayed", e);

			return false;
		}
	}

	/**
	 * Verifies Pony Express image.
	 */
	public void verifyPonyExpressImageDisplayed() {

		if (!isPonyExpressImageDisplayed()) {

			throw new AssertionError("Pony Express confirmation image should be displayed");
		}

		log.info("Pony Express confirmation image verified successfully");
	}

	// =========================================================
	// BACK HOME BUTTON
	// =========================================================

	/**
	 * Checks whether Back Home button is displayed.
	 */
	public boolean isBackHomeButtonDisplayed() {

		try {

			return backHomeButton.isDisplayed();

		} catch (Exception e) {

			log.error("Back Home button is not displayed", e);

			return false;
		}
	}

	/**
	 * Clicks Back Home button.
	 */
	public void clickBackHome() {

		log.info("Clicking Back Home button");

		click(backHomeButton);

		log.info("Back Home button clicked successfully");
	}

	/**
	 * Verifies Back Home button text.
	 */
	public void verifyBackHomeButtonText() {

		String actualText = getText(backHomeButton);

		String expectedText = "Back Home";

		if (!actualText.equals(expectedText)) {

			throw new AssertionError("Expected Back Home button text: " + expectedText + " but found: " + actualText);
		}

		log.info("Back Home button text verified successfully");
	}

	// =========================================================
	// URL VALIDATION
	// =========================================================

	/**
	 * Verifies current URL contains expected URL text.
	 */
	public void verifyCurrentUrlContains(String expectedUrlPart) {

		String currentUrl = driver.getCurrentUrl();

		log.info("Current URL: {}", currentUrl);

		if (!currentUrl.contains(expectedUrlPart)) {

			throw new AssertionError(
					"Expected URL to contain: " + expectedUrlPart + " but actual URL was: " + currentUrl);
		}

		log.info("Current URL contains expected value: {}", expectedUrlPart);
	}

	/**
	 * Checks whether current page is Checkout Complete URL.
	 */
	public boolean isCheckoutCompleteUrl() {

		String currentUrl = driver.getCurrentUrl();

		boolean result = currentUrl.contains("checkout-complete.html");

		log.info("Checkout Complete URL validation result: {}", result);

		return result;
	}

	// =========================================================
	// ORDER COMPLETION VALIDATION
	// =========================================================

	/**
	 * Verifies that order was completed successfully.
	 */
	public void verifyOrderCompletedSuccessfully() {

		log.info("Verifying order completion");

		verifyCurrentUrlContains("checkout-complete.html");

		verifyPageTitle("Checkout: Complete!");

		verifyConfirmationMessage("Thank you for your order!");

		verifyConfirmationText(
				"Your order has been dispatched, and will arrive just as fast as the pony can get there!");

		verifyPonyExpressImageDisplayed();

		verifyBackHomeButtonText();

		log.info("Order completed successfully");
	}

	// =========================================================
	// NAVIGATION
	// =========================================================

	/**
	 * Navigates back to previous page.
	 */
	public void navigateBack() {

		log.info("Navigating back from Checkout Complete page");

		driver.navigate().back();

		log.info("Browser navigated back successfully");
	}

	/**
	 * Navigates forward to Checkout Complete page.
	 */
	public void navigateForward() {

		log.info("Navigating forward to Checkout Complete page");

		driver.navigate().forward();

		log.info("Browser navigated forward successfully");
	}
	
	public String getCurrentUrl() { 
		return driver.getCurrentUrl(); 
		
	}

	/**
	 * Refreshes Checkout Complete page.
	 */
	public void refreshPage() {

		log.info("Refreshing Checkout Complete page");

		driver.navigate().refresh();

		log.info("Checkout Complete page refreshed successfully");
	}
}