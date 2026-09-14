
@INVCart
Feature: SauceDemo Shopping Cart

  Background:
    Given the user is logged in to SauceDemo
    And the inventory page is displayed


  @positive @INV020
  Scenario: Add one product to cart
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge should show 1


  @positive @INV021
  Scenario: Add two products to cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    Then the cart badge should show 2


  @positive @INV022
  Scenario: Add three products to cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user adds "Sauce Labs Bolt T-Shirt" to the cart
    Then the cart badge should show 3


  @positive @INV023
  Scenario: Remove product from cart
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge should show 1
    When the user removes "Sauce Labs Backpack" from the cart
    Then the cart badge should not be displayed


  @positive @INV026
  Scenario: Open shopping cart
    When the user opens the shopping cart
    Then the shopping cart page should be displayed


  @positive @INV027
  Scenario: Add product and open cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user opens the shopping cart
    Then the shopping cart page should be displayed
    

  @positive @inventory @INV028
  Scenario: Add multiple products and open shopping cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user opens the shopping cart
    Then the shopping cart page should be displayed
   