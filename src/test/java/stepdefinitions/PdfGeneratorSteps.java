package stepdefinitions;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page_object_manager.PageObjectManager;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import actions.MenuAction;
import assertions.MenuAssertions;
import context.ScenarioContext;
import pages.CheckoutCompletePage;
import utils.PdfDownloadUtils;

public class PdfGeneratorSteps {

	private CheckoutCompletePage checkoutCompletePage;

	private Path downloadedPdf;
	private Path fixturePdf;

	private Path pdfPath;

	private ScenarioContext scenarioContext;

	private boolean simulateDownloadBlocked;

	private String pdfDownloadDirectory = System.getProperty("user.dir") + "\\downloads\\pdf";

	private static final Logger log = LoggerFactory.getLogger(PdfGeneratorSteps.class);

	public PdfGeneratorSteps(ScenarioContext scenarioContext) {

		this.scenarioContext = scenarioContext;

		PageObjectManager pageObjectManager = scenarioContext.getPageObjectManager();

		log.info("MenuSteps initialized successfully");
	}

	// ==============================================================================================
	// PDF HELPER METHODS
	// ==============================================================================================

	private Path currentPdf() {

		if (fixturePdf != null) {

			log.info("Using PDF test fixture: {}", fixturePdf);

			return fixturePdf;
		}

		if (downloadedPdf != null) {

			log.info("Using downloaded PDF: {}", downloadedPdf);

			return downloadedPdf;
		}

		log.warn("No PDF is currently available");

		return null;
	}

	private void deleteFixture() {

		if (fixturePdf == null) {

			log.info("No PDF test fixture found to delete");

			return;
		}

		try {

			boolean deleted = Files.deleteIfExists(fixturePdf);

			if (deleted) {

				log.info("PDF test fixture deleted successfully: {}", fixturePdf);

			} else {

				log.warn("PDF test fixture does not exist: {}", fixturePdf);
			}

		} catch (IOException e) {

			log.error("Failed to delete PDF test fixture: {}", fixturePdf, e);

		} finally {

			fixturePdf = null;

			log.info("PDF fixture reference cleared");
		}
	}

	private void createPdfFixture(String content) {

		try {

			Path directory = Path.of(System.getProperty("user.dir"), "target", "pdf-test-fixtures");

			Files.createDirectories(directory);

			fixturePdf = directory.resolve("test-fixture.pdf");

			log.info("Creating PDF test fixture: {}", fixturePdf);

			try (PDDocument document = new PDDocument()) {

				PDPage page = new PDPage();

				document.addPage(page);

				if (content != null && !content.isEmpty()) {

					log.info("Writing content into PDF fixture: {}", content);

					try (PDPageContentStream stream = new PDPageContentStream(document, page)) {

						stream.beginText();

						stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);

						stream.newLineAtOffset(50, 700);

						stream.showText(content);

						stream.endText();
					}

				} else {

					log.info("Creating PDF fixture with empty content");
				}

				document.save(fixturePdf.toFile());
			}

			log.info("PDF test fixture created successfully: {}", fixturePdf);

		} catch (IOException e) {

			log.error("Failed to create PDF test fixture", e);

			throw new RuntimeException("Failed to create PDF test fixture", e);
		}
	}

	// ==============================================================================================
	// CHECKOUT COMPLETE PAGE
	// ==============================================================================================

	@Given("the user is on the checkout complete page")
	public void theUserIsOnTheCheckoutCompletePage() {

		log.info("Verifying that user is on Checkout Complete page");

		checkoutCompletePage = new CheckoutCompletePage();

		boolean pageDisplayed = checkoutCompletePage.isCheckoutCompletePageDisplayed();

		log.info("Checkout Complete page displayed: {}", pageDisplayed);

		Assert.assertTrue(pageDisplayed, "Checkout Complete page should be displayed");

		log.info("User is successfully on Checkout Complete page");
	}

	@Given("the Generate PDF Order button is displayed")
	public void theGeneratePdfOrderButtonIsDisplayed() {

		log.info("Verifying Generate PDF Order button is displayed");

		boolean buttonDisplayed = checkoutCompletePage.isGeneratePdfButtonDisplayed();

		log.info("Generate PDF Order button displayed: {}", buttonDisplayed);

		Assert.assertTrue(buttonDisplayed, "Generate PDF Order button should be displayed");

		String actualButtonText = checkoutCompletePage.getGeneratePdfButtonText();

		log.info("Generate PDF Order button text: {}", actualButtonText);

		String expectedButtonText = "Generate PDF order";

		log.info("Expected Generate PDF Order button text: {}", expectedButtonText);

		Assert.assertEquals(actualButtonText, expectedButtonText, "Incorrect Generate PDF Order button text");

		log.info("Generate PDF Order button validation passed");
	}

	@When("the user clicks the Generate PDF Order button")
	public void theUserClicksTheGeneratePdfOrderButton() {

		log.info("User is clicking Generate PDF Order button");

		log.info("PDF download directory: {}", pdfDownloadDirectory);

		downloadedPdf = checkoutCompletePage.downloadOrderPdf(pdfDownloadDirectory);

		log.info("PDF download operation completed");

		scenarioContext.set("ORDER_PDF_PATH", downloadedPdf.toString());

		log.info("Downloaded PDF path: {}", downloadedPdf);
	}

	// ==============================================================================================
	// DOWNLOAD VALIDATION
	// ==============================================================================================

	@Then("the order PDF should be downloaded successfully")
	public void theOrderPdfShouldBeDownloadedSuccessfully() {

		log.info("Validating that order PDF was downloaded successfully");

		Assert.assertNotNull(downloadedPdf, "Downloaded PDF path should not be null");

		log.info("Downloaded PDF path: {}", downloadedPdf);

		boolean fileExists = downloadedPdf.toFile().exists();

		log.info("Downloaded PDF exists: {}", fileExists);

		Assert.assertTrue(fileExists, "Downloaded PDF should exist: " + downloadedPdf);

		log.info("Order PDF download validation passed");
	}

	@Then("the downloaded PDF should not be empty")
	public void theDownloadedPdfShouldNotBeEmpty() {

		log.info("Validating downloaded PDF is not empty");

		log.info("Downloaded PDF: {}", downloadedPdf);

		checkoutCompletePage.verifyDownloadedPdf(downloadedPdf);

		log.info("Downloaded PDF is not empty");
	}

	// ==============================================================================================
	// PDF VALIDATION
	// ==============================================================================================

	@Then("the downloaded file should have a PDF extension")
	public void the_downloaded_file_should_have_a_pdf_extension() {

		log.info("Starting PDF extension validation");

		Path pdfPath = currentPdf();

		log.info("PDF path under validation: {}", pdfPath);

		Assert.assertNotNull(pdfPath, "Downloaded PDF path should not be null");

		boolean fileExists = Files.exists(pdfPath);

		log.info("PDF file exists: {}", fileExists);

		Assert.assertTrue(fileExists, "Downloaded file does not exist: " + pdfPath);

		String fileName = pdfPath.getFileName().toString().toLowerCase(Locale.ROOT);

		log.info("Downloaded file name: {}", fileName);

		boolean hasPdfExtension = fileName.endsWith(".pdf");

		log.info("File has PDF extension: {}", hasPdfExtension);

		Assert.assertTrue(hasPdfExtension, "Downloaded file should have a .pdf extension, but was: " + fileName);

		log.info("PDF extension validation passed");
	}

	@Then("the order PDF should contain {string}")
	public void the_order_pdf_should_contain(String expectedText) {

		log.info("Starting PDF text validation");

		log.info("Expected PDF text: {}", expectedText);

		Path pdfPath = currentPdf();

		log.info("PDF path under validation: {}", pdfPath);

		Assert.assertNotNull(pdfPath, "PDF path should not be null");

		boolean fileExists = Files.exists(pdfPath);

		log.info("PDF file exists: {}", fileExists);

		Assert.assertTrue(fileExists, "PDF file does not exist: " + pdfPath);

		try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {

			log.info("PDF successfully loaded by PDFBox");

			log.info("PDF page count: {}", document.getNumberOfPages());

			PDFTextStripper pdfTextStripper = new PDFTextStripper();

			String actualText = pdfTextStripper.getText(document);

			log.info("Actual PDF text: {}", actualText);

			log.info("Expected PDF text: {}", expectedText);

			boolean textFound = actualText.contains(expectedText);

			log.info("Expected text found in PDF: {}", textFound);

			Assert.assertTrue(textFound,
					"Expected text was not found in the PDF: " + expectedText + "\nActual PDF text:\n" + actualText);

			log.info("PDF text validation passed for: {}", expectedText);

		} catch (IOException e) {

			log.error("Failed to read PDF file: {}", pdfPath, e);

			Assert.fail("Failed to read PDF file: " + pdfPath, e);
		}
	}

	// ==============================================================================================
	// CUSTOMER NAME VALIDATION
	// ==============================================================================================

	@Then("the order PDF should contain the customer name {string}")
	public void the_order_pdf_should_contain_the_customer_name(String expectedCustomerName) {

		log.info("Starting customer name validation in order PDF");

		log.info("Expected customer name: {}", expectedCustomerName);

		Path pdfPath = currentPdf();

		log.info("PDF path under validation: {}", pdfPath);

		Assert.assertNotNull(pdfPath, "PDF path should not be null");

		boolean fileExists = Files.exists(pdfPath);

		log.info("PDF file exists: {}", fileExists);

		Assert.assertTrue(fileExists, "PDF file does not exist: " + pdfPath);

		try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {

			log.info("PDF successfully loaded by PDFBox");

			PDFTextStripper pdfTextStripper = new PDFTextStripper();

			String actualText = pdfTextStripper.getText(document);

			log.info("Extracted PDF text: {}", actualText);

			log.info("Expected customer name: {}", expectedCustomerName);

			boolean customerNameFound = actualText.contains(expectedCustomerName);

			log.info("Customer name found in PDF: {}", customerNameFound);

			Assert.assertTrue(customerNameFound, "Expected customer name was not found in the PDF: "
					+ expectedCustomerName + "\nActual PDF text:\n" + actualText);

			log.info("Customer name validation passed: {}", expectedCustomerName);

		} catch (IOException e) {

			log.error("Failed to read PDF while validating customer name: {}", pdfPath, e);

			Assert.fail("Failed to read PDF while validating customer name: " + pdfPath, e);
		}
	}

	// ==============================================================================================
	// POSTAL CODE VALIDATION
	// ==============================================================================================

	@Then("the order PDF should contain the postal code {string}")
	public void the_order_pdf_should_contain_the_postal_code(String expectedPostalCode) {

		log.info("Starting postal code validation in order PDF");

		log.info("Expected postal code: {}", expectedPostalCode);

		Path pdfPath = currentPdf();

		log.info("PDF path under validation: {}", pdfPath);

		Assert.assertNotNull(pdfPath, "PDF path should not be null");

		boolean fileExists = Files.exists(pdfPath);

		log.info("PDF file exists: {}", fileExists);

		Assert.assertTrue(fileExists, "PDF file does not exist: " + pdfPath);

		try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {

			log.info("PDF successfully loaded by PDFBox");

			PDFTextStripper pdfTextStripper = new PDFTextStripper();

			String actualText = pdfTextStripper.getText(document);

			log.info("Extracted PDF text: {}", actualText);

			log.info("Expected postal code: {}", expectedPostalCode);

			boolean postalCodeFound = actualText.contains(expectedPostalCode);

			log.info("Postal code found in PDF: {}", postalCodeFound);

			Assert.assertTrue(postalCodeFound, "Expected postal code was not found in the PDF: " + expectedPostalCode
					+ "\nActual PDF text:\n" + actualText);

			log.info("Postal code validation passed: {}", expectedPostalCode);

		} catch (IOException e) {

			log.error("Failed to read PDF while validating postal code: {}", pdfPath, e);

			Assert.fail("Failed to read PDF while validating postal code: " + pdfPath, e);
		}
	}

	@Then("the order PDF should contain product {string}")
	public void the_order_pdf_should_contain_product(String productName) {

		log.info("Verifying that the order PDF contains product: {}", productName);

		// Retrieve the exact PDF path stored during PDF download
		String pdfPathString = scenarioContext.get("ORDER_PDF_PATH", String.class);

		Assert.assertNotNull(pdfPathString, "ORDER_PDF_PATH was not found in ScenarioContext");

		log.info("PDF path retrieved from ScenarioContext: {}", pdfPathString);

		Path pdfPath = Paths.get(pdfPathString);

		// Verify PDF exists
		Assert.assertTrue(Files.exists(pdfPath), "Order PDF was not found at: " + pdfPath.toAbsolutePath());

		// Verify it is a regular file
		Assert.assertTrue(Files.isRegularFile(pdfPath),
				"Order PDF path is not a regular file: " + pdfPath.toAbsolutePath());

		log.info("Order PDF found successfully: {}", pdfPath.toAbsolutePath());

		try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {

			log.info("Order PDF loaded successfully");

			PDFTextStripper pdfTextStripper = new PDFTextStripper();

			String pdfText = pdfTextStripper.getText(document);

			log.info("PDF text extracted successfully");
			log.info("Searching for product '{}' in order PDF", productName);

			boolean productExists = pdfText.toLowerCase(Locale.ROOT).contains(productName.toLowerCase(Locale.ROOT));

			log.info("Product '{}' present in PDF: {}", productName, productExists);

			Assert.assertTrue(productExists,
					"Product '" + productName + "' was not found in the order PDF: " + pdfPath.getFileName());

			log.info("PASS: Product '{}' is present in the order PDF", productName);

		} catch (IOException e) {

			log.error("Failed to read order PDF while validating product '{}'", productName, e);

			Assert.fail("Unable to read order PDF: " + pdfPath.toAbsolutePath() + ". Error: " + e.getMessage());
		}
	}

	@Then("the order PDF should show product {string} with price {string}")
	public void the_order_pdf_should_show_product_with_price(String productName, String expectedPrice) {

		log.info("Verifying product '{}' with price '{}' in order PDF", productName, expectedPrice);

		// Retrieve the exact PDF path stored during download
		String pdfPathString = scenarioContext.get("ORDER_PDF_PATH", String.class);

		Assert.assertNotNull(pdfPathString, "ORDER_PDF_PATH was not found in ScenarioContext");

		log.info("PDF path retrieved from ScenarioContext: {}", pdfPathString);

		Path pdfPath = Paths.get(pdfPathString);

		// Verify PDF exists
		Assert.assertTrue(Files.exists(pdfPath), "Order PDF was not found at: " + pdfPath.toAbsolutePath());

		Assert.assertTrue(Files.isRegularFile(pdfPath),
				"Order PDF path is not a regular file: " + pdfPath.toAbsolutePath());

		log.info("Order PDF found successfully: {}", pdfPath.toAbsolutePath());

		try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {

			log.info("Order PDF loaded successfully");

			PDFTextStripper pdfTextStripper = new PDFTextStripper();

			String pdfText = pdfTextStripper.getText(document);

			log.info("PDF text extracted successfully");

			// Normalize PDF text
			String normalizedPdfText = pdfText.replaceAll("\\s+", " ").trim();

			String normalizedProductName = productName.replaceAll("\\s+", " ").trim();

			String normalizedExpectedPrice = expectedPrice.replaceAll("\\s+", "").trim();

			log.info("Searching PDF for product '{}' with price '{}'", normalizedProductName, normalizedExpectedPrice);

			// Check product exists
			boolean productExists = normalizedPdfText.toLowerCase(Locale.ROOT)
					.contains(normalizedProductName.toLowerCase(Locale.ROOT));

			Assert.assertTrue(productExists, "Product '" + productName + "' was not found in the order PDF");

			log.info("Product '{}' found in PDF", productName);

			// Remove spaces from PDF text for flexible price matching
			String pdfTextWithoutSpaces = normalizedPdfText.replaceAll("\\s+", "");

			// Check expected price exists
			boolean priceExists = pdfTextWithoutSpaces.contains(normalizedExpectedPrice);

			Assert.assertTrue(priceExists,
					"Price '" + expectedPrice + "' for product '" + productName + "' was not found in the order PDF");

			log.info("Price '{}' found in PDF", expectedPrice);

			/*
			 * Verify product and price are present in the same nearby section of the PDF.
			 */
			String productAndPricePattern = Pattern.quote(normalizedProductName) + ".{0,300}?"
					+ Pattern.quote(normalizedExpectedPrice);

			boolean productPriceMatch = Pattern
					.compile(productAndPricePattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL)
					.matcher(normalizedPdfText.replaceAll("\\s+", " ")).find();

			Assert.assertTrue(productPriceMatch, "Product '" + productName + "' and price '" + expectedPrice
					+ "' were not found together in the expected PDF section");

			log.info("PASS: Product '{}' is displayed with price '{}' in the order PDF", productName, expectedPrice);

		} catch (IOException e) {

			log.error("Failed to read order PDF while validating product '{}' " + "with price '{}'", productName,
					expectedPrice, e);

			Assert.fail("Unable to read order PDF: " + pdfPath.toAbsolutePath() + ". Error: " + e.getMessage());
		}
	}

	@Then("the order PDF should show item total {string}")
	public void the_order_pdf_should_show_item_total(String expectedItemTotal) {

		log.info("Verifying item total '{}' in order PDF", expectedItemTotal);

		verifyAmountInOrderPdf(expectedItemTotal, "Item Total");
	}

	@Then("the order PDF should show tax {string}")
	public void the_order_pdf_should_show_tax(String expectedTax) {

		log.info("Verifying tax '{}' in order PDF", expectedTax);

		verifyAmountInOrderPdf(expectedTax, "Tax");
	}

	@Then("the order PDF should show grand total {string}")
	public void the_order_pdf_should_show_grand_total(String expectedGrandTotal) {

		log.info("Verifying grand total '{}' in order PDF", expectedGrandTotal);

		verifyAmountInOrderPdf(expectedGrandTotal, "Grand Total");
	}

	/**
	 * Reusable helper method to verify an amount in the order PDF.
	 */
	private void verifyAmountInOrderPdf(String expectedAmount, String amountType) {

		String pdfPathString = scenarioContext.get("ORDER_PDF_PATH", String.class);

		Assert.assertNotNull(pdfPathString, "ORDER_PDF_PATH was not found in ScenarioContext");

		Path pdfPath = Paths.get(pdfPathString);

		log.info("Using order PDF: {}", pdfPath.toAbsolutePath());

		Assert.assertTrue(Files.exists(pdfPath), "Order PDF was not found at: " + pdfPath.toAbsolutePath());

		Assert.assertTrue(Files.isRegularFile(pdfPath),
				"Order PDF path is not a regular file: " + pdfPath.toAbsolutePath());

		try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {

			log.info("Order PDF loaded successfully");

			PDFTextStripper pdfTextStripper = new PDFTextStripper();

			String pdfText = pdfTextStripper.getText(document);

			log.info("Searching PDF for {}: {}", amountType, expectedAmount);

			/*
			 * Normalize spaces because PDF text extraction can sometimes introduce
			 * unexpected spaces between currency symbol and amount.
			 */
			String normalizedPdfText = pdfText.replaceAll("\\s+", " ").trim();

			String normalizedExpectedAmount = expectedAmount.replaceAll("\\s+", "").trim();

			String pdfTextWithoutSpaces = normalizedPdfText.replaceAll("\\s+", "");

			boolean amountExists = pdfTextWithoutSpaces.contains(normalizedExpectedAmount);

			log.info("{} '{}' present in PDF: {}", amountType, expectedAmount, amountExists);

			Assert.assertTrue(amountExists,
					amountType + " '" + expectedAmount + "' was not found in order PDF: " + pdfPath.getFileName());

			log.info("PASS: {} '{}' found in order PDF", amountType, expectedAmount);

		} catch (IOException e) {

			log.error("Failed to read order PDF while validating {} '{}'", amountType, expectedAmount, e);

			Assert.fail("Unable to read order PDF while validating " + amountType + ": " + e.getMessage());
		}
	}

	@Then("the order PDF subtotal plus tax should equal the grand total")
	public void the_order_pdf_subtotal_plus_tax_should_equal_the_grand_total() {

		log.info("Starting subtotal + tax = grand total validation");

		String pdfPathString = scenarioContext.get("ORDER_PDF_PATH", String.class);

		Assert.assertNotNull(pdfPathString, "ORDER_PDF_PATH was not found in ScenarioContext");

		Path pdfPath = Paths.get(pdfPathString);

		log.info("Using order PDF: {}", pdfPath.toAbsolutePath());

		Assert.assertTrue(Files.exists(pdfPath), "Order PDF was not found at: " + pdfPath.toAbsolutePath());

		try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {

			log.info("Order PDF loaded successfully");

			PDFTextStripper pdfTextStripper = new PDFTextStripper();

			String pdfText = pdfTextStripper.getText(document);

			log.info("PDF text extracted successfully");
			/*
			 * * Normalize whitespace. * * Example: * * Item total $55.97 * Tax $4.48 *
			 * Total $60.45
			 */

			String normalizedText = pdfText.replaceAll("\\s+", " ").trim();

			log.info("Searching PDF for Item Total, Tax and Total");

			/* * ITEM TOTAL * * Matches: * Item total $55.97 */
			Pattern itemTotalPattern = Pattern.compile("\\bitem\\s+total\\s*\\$?([0-9]+(?:\\.[0-9]{2})?)\\b",
					Pattern.CASE_INSENSITIVE);

			/* * TAX * * Matches: * Tax $4.48 */
			Pattern taxPattern = Pattern.compile("\\btax\\s*\\$?([0-9]+(?:\\.[0-9]{2})?)\\b", Pattern.CASE_INSENSITIVE);
			/*
			 * * TOTAL * * IMPORTANT: * The negative lookbehind prevents this pattern * from
			 * matching the "total" inside "Item total". * * Matches: * Total $60.45 * *
			 * Does NOT match: * Item total $55.97
			 */ Pattern grandTotalPattern = Pattern.compile("(?<!item\\s)\\btotal\\s*\\$?([0-9]+(?:\\.[0-9]{2})?)\\b",
					Pattern.CASE_INSENSITIVE);

			Matcher itemTotalMatcher = itemTotalPattern.matcher(normalizedText);

			Matcher taxMatcher = taxPattern.matcher(normalizedText);

			Matcher grandTotalMatcher = grandTotalPattern.matcher(normalizedText);

			Assert.assertTrue(itemTotalMatcher.find(), "Item Total was not found in the order PDF");

			Assert.assertTrue(taxMatcher.find(), "Tax was not found in the order PDF");

			Assert.assertTrue(grandTotalMatcher.find(), "Total was not found in the order PDF");

			String itemTotalValue = itemTotalMatcher.group(1);

			String taxValue = taxMatcher.group(1);

			String grandTotalValue = grandTotalMatcher.group(1);

			log.info("Extracted Item Total: ${}", itemTotalValue);

			log.info("Extracted Tax: ${}", taxValue);

			log.info("Extracted Grand Total: ${}", grandTotalValue);
			/*
			 * * Convert extracted values to BigDecimal. * * BigDecimal is preferred for
			 * currency calculations.
			 */
			BigDecimal itemTotal = new BigDecimal(itemTotalValue);

			BigDecimal tax = new BigDecimal(taxValue);

			BigDecimal grandTotal = new BigDecimal(grandTotalValue);
			/* * Calculate: * * Item Total + Tax = Grand Total */

			BigDecimal calculatedGrandTotal = itemTotal.add(tax).setScale(2);

			grandTotal = grandTotal.setScale(2);

			log.info("Item Total: ${}", itemTotal);

			log.info("Tax: ${}", tax);

			log.info("Calculated Grand Total: ${}", calculatedGrandTotal);

			log.info("PDF Grand Total: ${}", grandTotal);
			/* * Final validation */
			Assert.assertEquals(calculatedGrandTotal, grandTotal, "Item Total + Tax does not equal Grand Total");

			log.info("PASS: Item Total + Tax = Grand Total");

			log.info("PASS: ${} + ${} = ${}", itemTotal, tax, grandTotal);

		} catch (IOException e) {
			log.error("Failed to read order PDF while validating totals", e);

			Assert.fail("Unable to read order PDF: " + pdfPath.toAbsolutePath() + ". Error: " + e.getMessage());
		}
	}

	@Then("the order PDF should contain at least {int} page")
	public void the_order_pdf_should_contain_at_least_page(Integer expectedMinimumPages) {

		log.info("Starting PDF page count validation. Expected minimum pages: {}", expectedMinimumPages);

		// Validate input
		Assert.assertNotNull(expectedMinimumPages, "Expected minimum page count must not be null");

		Assert.assertTrue(expectedMinimumPages > 0, "Expected minimum page count must be greater than 0");

		// Retrieve the exact PDF path stored during download
		String pdfPathString = scenarioContext.get("ORDER_PDF_PATH", String.class);

		Assert.assertNotNull(pdfPathString, "ORDER_PDF_PATH was not found in ScenarioContext");

		log.info("PDF path retrieved from ScenarioContext: {}", pdfPathString);

		Path pdfPath = Paths.get(pdfPathString);

		// Verify PDF exists
		Assert.assertTrue(Files.exists(pdfPath), "Order PDF was not found at: " + pdfPath.toAbsolutePath());

		// Verify it is a regular file
		Assert.assertTrue(Files.isRegularFile(pdfPath),
				"Order PDF path is not a regular file: " + pdfPath.toAbsolutePath());

		log.info("Order PDF found: {}", pdfPath.toAbsolutePath());

		try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {

			int actualPageCount = document.getNumberOfPages();

			log.info("Order PDF contains {} page(s)", actualPageCount);

			log.info("Required minimum page count: {}", expectedMinimumPages);

			Assert.assertTrue(actualPageCount >= expectedMinimumPages, "Order PDF should contain at least "
					+ expectedMinimumPages + " page(s), but found " + actualPageCount);

			log.info("PASS: Order PDF contains at least {} page(s). " + "Actual page count: {}", expectedMinimumPages,
					actualPageCount);

		} catch (IOException e) {

			log.error("Failed to read order PDF while validating page count", e);

			Assert.fail("Unable to read order PDF: " + pdfPath.toAbsolutePath() + ". Error: " + e.getMessage());
		}
	}

	@Then("the downloaded PDF should belong to the current order")
	public void the_downloaded_pdf_should_belong_to_the_current_order() {

		log.info("=================================================");
		log.info("Starting current order PDF validation");
		log.info("=================================================");

		// ---------------------------------------------------------
		// Get PDF path
		// ---------------------------------------------------------
		String pdfPathString = scenarioContext.get("ORDER_PDF_PATH", String.class);

		Assert.assertNotNull(pdfPathString, "ORDER_PDF_PATH was not found in ScenarioContext");

		Path pdfPath = Paths.get(pdfPathString);

		log.info("Downloaded order PDF: {}", pdfPath.toAbsolutePath());

		Assert.assertTrue(Files.exists(pdfPath), "Order PDF was not found at: " + pdfPath);

		Assert.assertTrue(Files.isRegularFile(pdfPath), "Order PDF is not a valid file: " + pdfPath);

		// ---------------------------------------------------------
		// Get current order details
		// ---------------------------------------------------------
		String firstName = scenarioContext.get("FIRST_NAME", String.class);

		String lastName = scenarioContext.get("LAST_NAME", String.class);

		String postalCode = scenarioContext.get("POSTAL_CODE", String.class);

		Assert.assertNotNull(firstName, "FIRST_NAME was not found in ScenarioContext. "
				+ "Make sure checkout information is stored before PDF validation.");

		Assert.assertNotNull(lastName, "LAST_NAME was not found in ScenarioContext. "
				+ "Make sure checkout information is stored before PDF validation.");

		Assert.assertNotNull(postalCode, "POSTAL_CODE was not found in ScenarioContext. "
				+ "Make sure checkout information is stored before PDF validation.");

		String fullName = firstName + " " + lastName;

		log.info("Current order customer: {}", fullName);
		log.info("Current order postal code: {}", postalCode);

		// ---------------------------------------------------------
		// Load PDF
		// ---------------------------------------------------------
		try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {

			int pageCount = document.getNumberOfPages();

			log.info("PDF page count: {}", pageCount);

			Assert.assertTrue(pageCount > 0, "Downloaded PDF does not contain any pages");

			// -----------------------------------------------------
			// Extract PDF text
			// -----------------------------------------------------
			PDFTextStripper pdfTextStripper = new PDFTextStripper();

			String pdfText = pdfTextStripper.getText(document);

			Assert.assertNotNull(pdfText, "Unable to extract text from downloaded PDF");

			String normalizedPdfText = pdfText.replaceAll("\\s+", " ").trim();

			log.info("Successfully extracted text from PDF");

			// -----------------------------------------------------
			// Validate ORDER DETAILS section
			// -----------------------------------------------------
			Assert.assertTrue(normalizedPdfText.toLowerCase(Locale.ROOT).contains("order details"),
					"ORDER DETAILS section was not found in PDF");

			log.info("PASS: ORDER DETAILS section found");

			// -----------------------------------------------------
			// Validate SHIP TO section
			// -----------------------------------------------------
			Assert.assertTrue(normalizedPdfText.toLowerCase(Locale.ROOT).contains("ship to"),
					"SHIP TO section was not found in PDF");

			log.info("PASS: SHIP TO section found");

			// -----------------------------------------------------
			// Validate customer name
			// -----------------------------------------------------
			boolean customerFound = normalizedPdfText.toLowerCase(Locale.ROOT)
					.contains(fullName.toLowerCase(Locale.ROOT));

			Assert.assertTrue(customerFound, "Current customer name was not found in PDF. " + "Expected: " + fullName);

			log.info("PASS: Current customer '{}' found in PDF", fullName);

			// -----------------------------------------------------
			// Validate postal code
			// -----------------------------------------------------
			boolean postalCodeFound = normalizedPdfText.contains(postalCode);

			Assert.assertTrue(postalCodeFound,
					"Current postal code was not found in PDF. " + "Expected: " + postalCode);

			log.info("PASS: Current postal code '{}' found in PDF", postalCode);

			// -----------------------------------------------------
			// Final validation
			// -----------------------------------------------------
			log.info("=================================================");
			log.info("PASS: Downloaded PDF belongs to the current order");
			log.info("Customer   : {}", fullName);
			log.info("Postal Code: {}", postalCode);
			log.info("PDF Path   : {}", pdfPath.toAbsolutePath());
			log.info("=================================================");

		} catch (IOException e) {

			log.error("Failed to read order PDF: {}", pdfPath.toAbsolutePath(), e);

			Assert.fail("Unable to validate downloaded order PDF: " + pdfPath.toAbsolutePath() + ". Error: "
					+ e.getMessage());
		}
	}

//======================================================================================================	

//	@Then("the order PDF should contain product {string}")
//	public void the_order_pdf_should_contain_product(String productName) {
//		
//		String pdfPathString = scenarioContext.get("ORDER_PDF_PATH", String.class);
//
//		Path pdfPath = Paths.get(pdfPathString);
//
//		log.info("Starting PDF product validation for: {}", productName);
//
//		Path downloadDirectory = Paths.get(System.getProperty("user.dir"), "downloads");
//
//		log.info("Checking PDF download directory: {}", downloadDirectory.toAbsolutePath());
//
//		Assert.assertTrue(Files.exists(downloadDirectory),
//				"Download directory does not exist: " + downloadDirectory.toAbsolutePath());
//
//		try (Stream<Path> files = Files.list(downloadDirectory)) {
//
//			pdfPath = files.filter(Files::isRegularFile)
//					.filter(path -> path.getFileName().toString().toLowerCase(Locale.ROOT).endsWith(".pdf")).findFirst()
//					.orElse(null);
//
//			Assert.assertNotNull(pdfPath, "No PDF file was found in: " + downloadDirectory.toAbsolutePath());
//
//			log.info("PDF file found: {}", pdfPath.toAbsolutePath());
//
//			try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {
//
//				log.info("PDF loaded successfully");
//
//				PDFTextStripper pdfTextStripper = new PDFTextStripper();
//
//				String pdfText = pdfTextStripper.getText(document);
//
//				log.info("PDF text extracted successfully");
//				log.info("Searching for product '{}' in PDF", productName);
//
//				boolean productExists = pdfText.toLowerCase(Locale.ROOT).contains(productName.toLowerCase(Locale.ROOT));
//
//				log.info("Product '{}' present in PDF: {}", productName, productExists);
//
//				Assert.assertTrue(productExists,
//						"Product '" + productName + "' was not found in the order PDF: " + pdfPath.getFileName());
//
//				log.info("PASS: Product '{}' is present in the order PDF", productName);
//			}
//
//		} catch (IOException e) {
//
//			log.error("Failed to read order PDF while validating product '{}'", productName, e);
//
//			Assert.fail("Unable to read order PDF: " + e.getMessage());
//		}
//	}

}