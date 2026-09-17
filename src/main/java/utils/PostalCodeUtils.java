package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PostalCodeUtils {

    private static final Logger log = LoggerFactory.getLogger(PostalCodeUtils.class);

    private static final String INDIA_PIN_CODE_REGEX = "^[1-9][0-9]{5}$";

    private PostalCodeUtils() {
        // Utility class
    }

    /**
     * Validates Indian PIN code format.
     *
     * Rules:
     * - Exactly 6 digits
     * - First digit must be 1-9
     * - Remaining five characters must be digits
     *
     * @param postalCode Indian PIN code
     * @return true if valid, otherwise false
     */
    public static boolean isValidIndianPostalCode(String postalCode) {

        if (postalCode == null || postalCode.trim().isEmpty()) {
            log.warn("Postal code is null or empty");
            return false;
        }

        boolean valid = postalCode.matches(INDIA_PIN_CODE_REGEX);

        log.info("Postal code validation result: {} -> {}", postalCode, valid);

        return valid;
    }
}