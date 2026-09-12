package files_not_using;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pages.InventoryPage;

/**
 * Assertions for SauceDemo Inventory page.
 */
public class InventoryAssertions_not_using {

	private static final Logger log = LoggerFactory.getLogger(InventoryAssertions_not_using.class);

	private final InventoryPage inventoryPage;

	public InventoryAssertions_not_using(InventoryPage inventoryPage) {

		if (inventoryPage == null) {
			throw new IllegalArgumentException("InventoryPage must not be null");
		}

		this.inventoryPage = inventoryPage;
	}

	public void verifyInventoryPageDisplayed() {

		log.info("Verifying Inventory page is displayed");

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page should be displayed");
	}

	public void verifyInventoryPageTitle(String expectedTitle) {

		log.info("Verifying inventory page heading");

		Assert.assertEquals(inventoryPage.getInventoryPageHeading(), expectedTitle,
				"Inventory page heading does not match");
	}
}