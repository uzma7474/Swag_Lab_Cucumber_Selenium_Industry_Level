package components;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Reusable SauceDemo footer component.
 */
public class FooterComponent extends BasePage {

	@FindBy(css = ".footer")
	private WebElement footer;

	@FindBy(css = ".footer_copy")
	private WebElement footerCopy;

	@FindBy(css = ".social_twitter")
	private WebElement twitterLink;

	@FindBy(css = ".social_facebook")
	private WebElement facebookLink;

	@FindBy(css = ".social_linkedin")
	private WebElement linkedinLink;

	public FooterComponent() {

		super();

		log.debug("Initializing FooterComponent");

		PageFactory.initElements(driver, this);

		log.debug("FooterComponent initialized successfully");
	}

	/**
	 * Checks whether footer is displayed.
	 */
	public boolean isDisplayed() {

		boolean displayed = isDisplayed(footer);

		log.debug("Footer displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Returns footer copyright text.
	 */
	public String getFooterText() {

		log.debug("Retrieving footer text");

		String text = getText(footerCopy);

		log.info("Footer text: {}", text);

		return text;
	}

	/**
	 * Checks whether Twitter link is displayed.
	 */
	public boolean isTwitterDisplayed() {

		boolean displayed = isDisplayed(twitterLink);

		log.debug("Twitter link displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether Facebook link is displayed.
	 */
	public boolean isFacebookDisplayed() {

		boolean displayed = isDisplayed(facebookLink);

		log.debug("Facebook link displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Checks whether LinkedIn link is displayed.
	 */
	public boolean isLinkedInDisplayed() {

		boolean displayed = isDisplayed(linkedinLink);

		log.debug("LinkedIn link displayed: {}", displayed);

		return displayed;
	}

	/**
	 * Opens Twitter link.
	 */
	public void openTwitter() {

		log.info("Opening SauceDemo Twitter link");

		click(twitterLink);

		log.debug("Twitter link clicked");
	}

	/**
	 * Opens Facebook link.
	 */
	public void openFacebook() {

		log.info("Opening SauceDemo Facebook link");

		click(facebookLink);

		log.debug("Facebook link clicked");
	}

	/**
	 * Opens LinkedIn link.
	 */
	public void openLinkedIn() {

		log.info("Opening SauceDemo LinkedIn link");

		click(linkedinLink);

		log.debug("LinkedIn link clicked");
	}
}