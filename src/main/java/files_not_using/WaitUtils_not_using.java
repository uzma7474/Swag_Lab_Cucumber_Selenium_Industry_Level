package files_not_using;

import base.BasePage;
import driver.DriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public final class WaitUtils_not_using {

    private static final Logger log =
            LoggerFactory.getLogger(WaitUtils_not_using.class);

    private static final int DEFAULT_TIMEOUT = 15;

    private WaitUtils_not_using() {
        // Utility class - prevent object creation
    }

    // =========================================================
    // WEB DRIVER
    // =========================================================

    private static WebDriver getDriver() {

        WebDriver driver = DriverManager.getDriver();

        if (driver == null) {

            throw new IllegalStateException(
                    "WebDriver is not initialized for current thread."
            );
        }

        return driver;
    }

    // =========================================================
    // WEB DRIVER WAIT
    // =========================================================

    private static WebDriverWait getWait() {

        return new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(DEFAULT_TIMEOUT)
        );
    }

    private static WebDriverWait getWait(long timeoutInSeconds) {

        return new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(timeoutInSeconds)
        );
    }

    // =========================================================
    // WAIT FOR ELEMENT CLICKABLE
    // =========================================================

    /**
     * Waits until the supplied WebElement is visible and enabled
     * so that it can be clicked.
     *
     * Example:
     *
     * WaitUtils.waitForElementClickable(openMenuButton);
     */
    public static WebElement waitForElementClickable(
            WebElement element) {

        if (element == null) {

            throw new IllegalArgumentException(
                    "Element cannot be null"
            );
        }

        try {

            log.debug(
                    "Waiting for element to be clickable: {}",
                    element
            );

            WebElement clickableElement =
                    getWait().until(
                            ExpectedConditions.elementToBeClickable(element)
                    );

            log.debug(
                    "Element is clickable: {}",
                    element
            );

            return clickableElement;

        } catch (TimeoutException e) {

            log.error(
                    "Element was not clickable within {} seconds: {}",
                    DEFAULT_TIMEOUT,
                    element,
                    e
            );

            throw e;
        }
    }

    // =========================================================
    // WAIT FOR ELEMENT CLICKABLE - CUSTOM TIMEOUT
    // =========================================================

    /**
     * Waits until a WebElement is clickable using a custom timeout.
     */
    public static WebElement waitForElementClickable(
            WebElement element,
            long timeoutInSeconds) {

        if (element == null) {

            throw new IllegalArgumentException(
                    "Element cannot be null"
            );
        }

        try {

            log.debug(
                    "Waiting {} seconds for element to be clickable",
                    timeoutInSeconds
            );

            return getWait(timeoutInSeconds)
                    .until(
                            ExpectedConditions.elementToBeClickable(element)
                    );

        } catch (TimeoutException e) {

            log.error(
                    "Element was not clickable within {} seconds",
                    timeoutInSeconds,
                    e
            );

            throw e;
        }
    }

    // =========================================================
    // WAIT FOR LOCATOR TO BE CLICKABLE
    // =========================================================

    /**
     * Waits until an element identified by By locator
     * is clickable.
     */
    public static WebElement waitForElementClickable(
            By locator) {

        if (locator == null) {

            throw new IllegalArgumentException(
                    "Locator cannot be null"
            );
        }

        try {

            log.debug(
                    "Waiting for locator to be clickable: {}",
                    locator
            );

            return getWait().until(
                    ExpectedConditions.elementToBeClickable(locator)
            );

        } catch (TimeoutException e) {

            log.error(
                    "Locator was not clickable within {} seconds: {}",
                    DEFAULT_TIMEOUT,
                    locator,
                    e
            );

            throw e;
        }
    }

    // =========================================================
    // WAIT FOR VISIBILITY
    // =========================================================

    /**
     * Waits until WebElement is visible.
     */
    public static WebElement waitForVisibility(
            WebElement element) {

        if (element == null) {

            throw new IllegalArgumentException(
                    "Element cannot be null"
            );
        }

        try {

            return getWait().until(
                    ExpectedConditions.visibilityOf(element)
            );

        } catch (TimeoutException e) {

            log.error(
                    "Element was not visible within {} seconds",
                    DEFAULT_TIMEOUT,
                    e
            );

            throw e;
        }
    }

    /**
     * Waits until element identified by locator is visible.
     */
    public static WebElement waitForVisibility(
            By locator) {

        if (locator == null) {

            throw new IllegalArgumentException(
                    "Locator cannot be null"
            );
        }

        try {

            return getWait().until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            );

        } catch (TimeoutException e) {

            log.error(
                    "Element was not visible within {} seconds: {}",
                    DEFAULT_TIMEOUT,
                    locator,
                    e
            );

            throw e;
        }
    }

    // =========================================================
    // WAIT FOR PRESENCE
    // =========================================================

    /**
     * Waits until an element is present in DOM.
     */
    public static WebElement waitForPresence(
            By locator) {

        if (locator == null) {

            throw new IllegalArgumentException(
                    "Locator cannot be null"
            );
        }

        try {

            return getWait().until(
                    ExpectedConditions.presenceOfElementLocated(locator)
            );

        } catch (TimeoutException e) {

            log.error(
                    "Element was not present within {} seconds: {}",
                    DEFAULT_TIMEOUT,
                    locator,
                    e
            );

            throw e;
        }
    }

    // =========================================================
    // WAIT FOR INVISIBILITY
    // =========================================================

    /**
     * Waits until WebElement becomes invisible.
     */
    public static boolean waitForInvisibility(
            WebElement element) {

        if (element == null) {

            throw new IllegalArgumentException(
                    "Element cannot be null"
            );
        }

        try {

            return getWait().until(
                    ExpectedConditions.invisibilityOf(element)
            );

        } catch (TimeoutException e) {

            log.error(
                    "Element did not become invisible within {} seconds",
                    DEFAULT_TIMEOUT,
                    e
            );

            throw e;
        }
    }

    /**
     * Waits until locator becomes invisible.
     */
    public static boolean waitForInvisibility(
            By locator) {

        if (locator == null) {

            throw new IllegalArgumentException(
                    "Locator cannot be null"
            );
        }

        try {

            return getWait().until(
                    ExpectedConditions.invisibilityOfElementLocated(locator)
            );

        } catch (TimeoutException e) {

            log.error(
                    "Element did not become invisible within {} seconds: {}",
                    DEFAULT_TIMEOUT,
                    locator,
                    e
            );

            throw e;
        }
    }

    // =========================================================
    // WAIT FOR TEXT
    // =========================================================

    /**
     * Waits until element contains expected text.
     */
    public static boolean waitForText(
            WebElement element,
            String expectedText) {

        if (element == null) {

            throw new IllegalArgumentException(
                    "Element cannot be null"
            );
        }

        if (expectedText == null) {

            throw new IllegalArgumentException(
                    "Expected text cannot be null"
            );
        }

        try {

            return getWait().until(
                    ExpectedConditions.textToBePresentInElement(
                            element,
                            expectedText
                    )
            );

        } catch (TimeoutException e) {

            log.error(
                    "Expected text '{}' was not found within {} seconds",
                    expectedText,
                    DEFAULT_TIMEOUT,
                    e
            );

            throw e;
        }
    }

    // =========================================================
    // WAIT FOR URL
    // =========================================================

    /**
     * Waits until URL contains expected value.
     */
    public static boolean waitForUrlContains(
            String expectedUrlPart) {

        if (expectedUrlPart == null ||
                expectedUrlPart.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Expected URL cannot be null or empty"
            );
        }

        try {

            return getWait().until(
                    ExpectedConditions.urlContains(expectedUrlPart)
            );

        } catch (TimeoutException e) {

            log.error(
                    "URL did not contain '{}' within {} seconds",
                    expectedUrlPart,
                    DEFAULT_TIMEOUT,
                    e
            );

            throw e;
        }
    }

    // =========================================================
    // WAIT FOR PAGE TITLE
    // =========================================================

    /**
     * Waits until page title contains expected text.
     */
    public static boolean waitForTitleContains(
            String expectedTitle) {

        if (expectedTitle == null ||
                expectedTitle.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Expected title cannot be null or empty"
            );
        }

        try {

            return getWait().until(
                    ExpectedConditions.titleContains(expectedTitle)
            );

        } catch (TimeoutException e) {

            log.error(
                    "Page title did not contain '{}' within {} seconds",
                    expectedTitle,
                    DEFAULT_TIMEOUT,
                    e
            );

            throw e;
        }
    }

    // =========================================================
    // CUSTOM SLEEP
    // =========================================================

    /**
     * Explicit sleep.
     *
     * Use this only when absolutely necessary.
     * Prefer explicit waits whenever possible.
     */
    public static void sleep(long milliseconds) {

        try {

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            log.warn(
                    "Thread interrupted during sleep",
                    e
            );
        }
    }
}

