package stepdefinitions;

import java.nio.file.Path;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import pages.CheckoutCompletePage;

public class PdfGeneratorSteps {

	private CheckoutCompletePage checkoutCompletePage;

	private Path downloadedPdf;

	private final String pdfDownloadDirectory = System.getProperty("user.dir") + "\\downloads\\pdf";

	@Given("the user is on the checkout complete page")
	public void theUserIsOnTheCheckoutCompletePage() {

		checkoutCompletePage = new CheckoutCompletePage();

		Assert.assertTrue(checkoutCompletePage.isCheckoutCompletePageDisplayed(),
				"Checkout Complete page should be displayed");
	}

	@Given("the Generate PDF Order button is displayed")
	public void theGeneratePdfOrderButtonIsDisplayed() {

		Assert.assertTrue(checkoutCompletePage.isGeneratePdfButtonDisplayed(),
				"Generate PDF Order button should be displayed");

		Assert.assertEquals(checkoutCompletePage.getGeneratePdfButtonText(), "Generate PDF order",
				"Incorrect Generate PDF Order button text");
	}

	@When("the user clicks the Generate PDF Order button")
	public void theUserClicksTheGeneratePdfOrderButton() {

		downloadedPdf = checkoutCompletePage.downloadOrderPdf(pdfDownloadDirectory);
	}

	@Then("the order PDF should be downloaded successfully")
	public void theOrderPdfShouldBeDownloadedSuccessfully() {

		Assert.assertNotNull(downloadedPdf, "Downloaded PDF path should not be null");

		Assert.assertTrue(downloadedPdf.toFile().exists(), "Downloaded PDF should exist: " + downloadedPdf);
	}

	@Then("the downloaded PDF should not be empty")
	public void theDownloadedPdfShouldNotBeEmpty() {

		checkoutCompletePage.verifyDownloadedPdf(downloadedPdf);
	}
}
