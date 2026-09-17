package assertions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import utils.PostalCodeUtils;

public class PostalCodeAssertions {

    private static final Logger log =
            LoggerFactory.getLogger(PostalCodeAssertions.class);

    /**
     * Validates that postal code is a valid Indian PIN code.
     */
    public void assertValidIndianPostalCode(String postalCode) {

        log.info("Validating Indian postal code: {}", postalCode);

        Assert.assertTrue(
                PostalCodeUtils.isValidIndianPostalCode(postalCode),
                "Expected valid Indian postal code but found: " + postalCode
        );

        log.info("Postal code validation passed: {}", postalCode);
    }

    /**
     * Validates that postal code is invalid.
     */
    public void assertInvalidIndianPostalCode(String postalCode) {

        log.info("Validating invalid Indian postal code: {}", postalCode);

        Assert.assertFalse(
                PostalCodeUtils.isValidIndianPostalCode(postalCode),
                "Expected invalid Indian postal code but found: " + postalCode
        );

        log.info("Invalid postal code validation passed: {}", postalCode);
    }
}