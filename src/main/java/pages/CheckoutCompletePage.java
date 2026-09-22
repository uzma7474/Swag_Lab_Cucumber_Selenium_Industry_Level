package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.BasePage;
import driver.DriverManager;
import utils.WaitUtils;

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
	private WebElement orderConfirmation;

	@FindBy(css = ".complete-text")
	private WebElement confirmationText;

	@FindBy(css = ".complete-text")
	private WebElement completeText;

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

	@FindBy(css = "[data-test='finish']")
	private WebElement finishButton;

	@FindBy(css = ".pony_express")
	private WebElement confirmationIcon;
	
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

	public boolean isCheckoutCompletePageDisplayed() {

		try {
			return pageTitle != null && pageTitle.isDisplayed()
					&& "Checkout: Complete".equalsIgnoreCase(pageTitle.getText().trim());

		} catch (Exception e) {
			log.error("Checkout Complete page is not displayed", e);
			return false;
		}
	}
	
	public boolean isBackHomeButtonEnabled() {

	    try {
	        boolean enabled = backHomeButton.isEnabled();

	        log.info("Back Home button enabled: {}", enabled);

	        return enabled;

	    } catch (Exception e) {

	        log.error("Unable to verify Back Home button enabled state", e);

	        return false;
	    }
	}

	public boolean isConfirmationIconDisplayed() {

	    try {
	        boolean displayed = confirmationIcon.isDisplayed();

	        log.info("Order confirmation icon displayed: {}", displayed);

	        return displayed;

	    } catch (Exception e) {

	        log.error("Unable to verify order confirmation icon", e);

	        return false;
	    }
	}
	
	public String getBackHomeButtonText() {

	    String text = backHomeButton.getText();

	    log.info("Back Home button text: {}", text);

	    return text;
	}
	
	
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

			return pageTitle.isDisplayed() && checkoutCompleteContainer.isDisplayed() && orderConfirmation.isDisplayed()
					&& backHomeButton.isDisplayed();

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

	public boolean isOrderConfirmationDisplayed() {

		try {
			return orderConfirmation != null && orderConfirmation.isDisplayed()
					&& !orderConfirmation.getText().trim().isEmpty();

		} catch (Exception e) {
			log.error("Order confirmation is not displayed", e);
			return false;
		}
	}

	public String getCompleteText() {

		String text = completeText.getText();

		log.info("Checkout confirmation message: {}", text);

		return text;
	}

	public String getConfirmationHeaderText() {

		String text = orderConfirmation.getText();

		log.info("Checkout confirmation message: {}", text);

		return text;
	}

	public boolean isCompleteHeaderDisplayed() {

		try {
			boolean displayed = orderConfirmation.isDisplayed();

			log.info("Order confirmation header displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.error("Unable to verify order confirmation header", e);

			return false;
		}
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

		String message = getText(orderConfirmation);

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

			return orderConfirmation.isDisplayed();

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
	public boolean isBackHomeButtonDisplayed_() {

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

	public boolean isCompleteTextDisplayed() {

	    try {
	        boolean displayed = completeText.isDisplayed();

	        log.info("Order confirmation message displayed: {}", displayed);

	        return displayed;

	    } catch (Exception e) {

	        log.error("Unable to verify order confirmation message", e);

	        return false;
	    }
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

	/**
	 * * Verifies whether the order confirmation message is displayed. * @return
	 * true if the confirmation message is displayed, otherwise false *
	 */
	public boolean isOrderConfirmationDisplayed_not_using() {
		try {
			boolean displayed = orderConfirmation.isDisplayed();
			log.info("Order confirmation message displayed: {}", displayed);
			return displayed;

		} catch (Exception e) {
			log.warn("Order confirmation message is not displayed: {}", e.getMessage());
			return false;

		}

	}

	public void clickFinish() {

		try {
			if (finishButton == null) {
				log.error("Finish button is null");
				throw new IllegalStateException("Finish button is not initialized");
			}

			WaitUtils.waitForElementToBeClickable(driver, finishButton);
			finishButton.click();

			log.info("Finish button clicked successfully");

		} catch (Exception e) {
			log.error("Failed to click Finish button", e);
			throw e;
		}
	}

	public String getOrderConfirmation() {

		try {
			return orderConfirmation.getText().trim();
		} catch (Exception e) {
			log.error("Unable to get order confirmation", e);
			return "";
		}
	}

	/**
	 * * Verifies whether the Back Home button is displayed. * @return true if the
	 * Back Home button is displayed, otherwise false
	 */
	public boolean isBackHomeButtonDisplayed() {
		try {
			boolean displayed = backHomeButton.isDisplayed();
			log.info("Back Home button displayed: {}", displayed);
			return displayed;

		} catch (Exception e) {
			log.warn("Back Home button is not displayed: {}", e.getMessage());
			return false;

		}

	}

	/**
	 * * Verifies whether the Checkout Complete page is displayed. * @return true if
	 * the Checkout Complete page is displayed, * otherwise false
	 */
	public boolean isCheckoutCompletePageDisplayed_() {
		try {
			boolean urlValid = driver.getCurrentUrl().contains("/checkout-complete.html");
			boolean confirmationDisplayed = orderConfirmation.isDisplayed();
			boolean backHomeDisplayed = backHomeButton.isDisplayed();
			boolean pageDisplayed = urlValid && confirmationDisplayed && backHomeDisplayed;
			log.info("Checkout Complete page verification - URL: {}, " + "Confirmation: {}, Back Home: {}, Result: {}",
					urlValid, confirmationDisplayed, backHomeDisplayed, pageDisplayed);
			return pageDisplayed;

		} catch (Exception e) {
			log.warn("Checkout Complete page verification failed: {}", e.getMessage());
			return false;

		}

	}

	/**
	 * Checks whether the checkout completion message is displayed.
	 *
	 * SauceDemo completion page contains: "Thank you for your order!"
	 */
	public boolean isCompletionMessageDisplayed() {

		try {

			log.info("Checking checkout completion message");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			WebElement completionMessage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header")));

			boolean displayed = completionMessage.isDisplayed()
					&& "Thank you for your order!".equalsIgnoreCase(completionMessage.getText().trim());

			log.info("Checkout completion message displayed: {}", displayed);

			return displayed;

		} catch (Exception e) {

			log.error("Checkout completion message was not displayed", e);

			return false;
		}
	}

}