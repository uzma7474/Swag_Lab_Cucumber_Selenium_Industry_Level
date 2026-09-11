package assertions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pages.LoginPage;

/**
 * Assertions for SauceDemo Login page.
 */
public class LoginAssertions {

	private static final Logger log = LoggerFactory.getLogger(LoginAssertions.class);

	private final LoginPage loginPage;

	public LoginAssertions(LoginPage loginPage) {

		if (loginPage == null) {
			throw new IllegalArgumentException("LoginPage must not be null");
		}

		this.loginPage = loginPage;
	}

	public void verifyLoginPageDisplayed() {

		log.info("Verifying login page");

		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "SauceDemo login page should be displayed");
	}

	public void verifyErrorMessageDisplayed() {

		log.info("Verifying login error message");

		Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Login error message should be displayed");
	}

	public void verifyErrorMessageNotDisplayed() {

		log.info("Verifying login error message is not displayed");

		Assert.assertFalse(loginPage.isErrorMessageDisplayed(), "Login error message should not be displayed");
	}

	public void verifyErrorMessageContains(String expectedMessage) {

		log.info("Verifying login error message contains: {}", expectedMessage);

		String actualMessage = loginPage.getErrorMessage();

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				String.format("Expected login error message to contain '%s' but actual message was '%s'",
						expectedMessage, actualMessage));
	}

	public void verifyErrorMessageEquals(String expectedMessage) {

		log.info("Verifying exact login error message");

		String actualMessage = loginPage.getErrorMessage();

		Assert.assertEquals(actualMessage, expectedMessage, "Login error message does not match");
	}

	public void verifyPasswordIsMasked() {

		log.info("Verifying password field is masked");

		Assert.assertTrue(loginPage.isPasswordMasked(), "Password field should be masked");
	}

	public void verifyUsernameFieldDisplayed() {

		log.info("Verifying username field is displayed");

		Assert.assertTrue(loginPage.isUsernameDisplayed(), "Username field should be displayed");
	}

	public void verifyPasswordFieldDisplayed() {

		log.info("Verifying password field is displayed");

		Assert.assertTrue(loginPage.isPasswordDisplayed(), "Password field should be displayed");
	}

	public void verifyLoginButtonDisplayed() {

		log.info("Verifying Login button is displayed");

		Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be displayed");
	}

	public void verifyLoginButtonEnabled() {

		log.info("Verifying Login button is enabled");

		Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled");
	}
}