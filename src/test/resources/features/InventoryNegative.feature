
@INVNegative
Feature: Inventory Negative Scenarios

  Background:
    Given the user is logged in to SauceDemo
    And the inventory page is displayed


  @negative @INV040
  Scenario: Verify invalid product is not displayed
    Then the product "Invalid Product" should not be displayed


  @negative @INV041
  Scenario: Verify non-existing product is not displayed
    Then the product "iPhone 15" should not be displayed


  @negative @INV044
  Scenario: Verify incorrect product price
    Then the price of product "Sauce Labs Backpack" should not be "$99.99"


  @negative @INV048
  Scenario: Try to add invalid product
    When the user tries to add "Invalid Product" to the cart
    Then the product lookup should fail


  @negative @INV049
  Scenario: Try to add blank product
    When the user tries to add "" to the cart
    Then an illegal argument error should be raised


  @negative @INV051
  Scenario: Try to remove product that is not in cart
    When the user tries to remove "Sauce Labs Backpack" from the cart
    Then the product removal lookup should fail


  @negative @INV055
  Scenario: Try to sort using blank option
    When the user tries to sort products by ""
    Then an illegal argument error should be raised


  @negative @INV057
  Scenario: Try to sort using invalid option
    When the user tries to sort products by "Invalid Sort"
    Then the sort operation should fail


  @negative @INV058
  Scenario: Verify incorrect product count
    Then the inventory page should not display 10 products
    

   
