@login @LGN
Feature: Login functionality of Swag Labs

  As a user
  I want to login to Swag Labs
  So that I can access inventory page

  Background:
    Given the user is on the SauceDemo login page

#############################################################################################################
# Positive Login Test Cases
#############################################################################################################

  @positive @smoke @LGN001
  Scenario: Login successfully with standard user
    When the user enters username "standard_user"
    And the user enters password "secret_sauce"
    And the user clicks the Login button
    Then the user should be successfully logged in
    And the inventory page should be displayed   