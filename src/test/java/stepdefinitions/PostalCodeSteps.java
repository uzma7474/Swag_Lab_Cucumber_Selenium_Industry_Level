package stepdefinitions;

import assertions.PostalCodeAssertions;
import io.cucumber.java.en.Then;

public class PostalCodeSteps {

    private final PostalCodeAssertions postalCodeAssertions;

    public PostalCodeSteps() {
        this.postalCodeAssertions = new PostalCodeAssertions();
    }

    @Then("the postal code {string} should be valid for India")
    public void thePostalCodeShouldBeValidForIndia(String postalCode) {

        postalCodeAssertions.assertValidIndianPostalCode(postalCode);
    }

    @Then("the postal code {string} should be invalid for India")
    public void thePostalCodeShouldBeInvalidForIndia(String postalCode) {

        postalCodeAssertions.assertInvalidIndianPostalCode(postalCode);
    }
}