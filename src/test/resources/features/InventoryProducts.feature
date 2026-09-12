@INVProduct
Feature: Inventory Products

  Background:
    Given the user is logged in to SauceDemo
    And the inventory page is displayed


  @positive @INV006
  Scenario: Verify Sauce Labs Backpack
    Then the product "Sauce Labs Backpack" should be displayed
    And the price of product "Sauce Labs Backpack" should be "$29.99"


  @positive @INV007
  Scenario: Verify Sauce Labs Bike Light
    Then the product "Sauce Labs Bike Light" should be displayed
    And the price of product "Sauce Labs Bike Light" should be "$9.99"


  @positive @INV008
  Scenario: Verify Sauce Labs Bolt T-Shirt
    Then the product "Sauce Labs Bolt T-Shirt" should be displayed
    And the price of product "Sauce Labs Bolt T-Shirt" should be "$15.99"


  @positive @INV009
  Scenario: Verify Sauce Labs Fleece Jacket
    Then the product "Sauce Labs Fleece Jacket" should be displayed
    And the price of product "Sauce Labs Fleece Jacket" should be "$49.99"


  @positive @INV010
  Scenario: Verify Sauce Labs Onesie
    Then the product "Sauce Labs Onesie" should be displayed
    And the price of product "Sauce Labs Onesie" should be "$7.99"


  @positive @INV011
  Scenario: Verify Test.allTheThings T-Shirt
    Then the product "Test.allTheThings() T-Shirt (Red)" should be displayed
    And the price of product "Test.allTheThings() T-Shirt (Red)" should be "$15.99"
    

#==========================================================================================================
# PRODUCT PRICE SCENARIOS
#==========================================================================================================

  @positive @inventory @INV012
  Scenario: Verify Sauce Labs Backpack price
    Then the price of product "Sauce Labs Backpack" should be "$29.99"


  @positive @inventory @INV013
  Scenario: Verify Sauce Labs Bike Light price
    Then the price of product "Sauce Labs Bike Light" should be "$9.99"


  @positive @inventory @INV014
  Scenario: Verify Sauce Labs Bolt T-Shirt price
    Then the price of product "Sauce Labs Bolt T-Shirt" should be "$15.99"


  @positive @inventory @INV015
  Scenario: Verify Sauce Labs Fleece Jacket price
    Then the price of product "Sauce Labs Fleece Jacket" should be "$49.99"


  @positive @inventory @INV016
  Scenario: Verify Sauce Labs Onesie price
    Then the price of product "Sauce Labs Onesie" should be "$7.99"


  @positive @inventory @INV017
  Scenario: Verify Test.allTheThings() T-Shirt price
    Then the price of product "Test.allTheThings() T-Shirt (Red)" should be "$15.99"


  # ============================================================
  # CART DISPLAY SCENARIOS
  # ============================================================

	
  @positive @inventory @INV018
  Scenario: Verify shopping cart is displayed
    Then the shopping cart should be displayed


  @positive @inventory @INV019
  Scenario: Verify cart badge is not displayed before adding product
    Then the cart badge should not be displayed


  @positive @inventory @INV020
  Scenario: Add one product to cart
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge should show 1


  @positive @inventory @INV021
  Scenario: Add two different products to cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    Then the cart badge should show 2


  @positive @inventory @INV022
  Scenario: Add three products to cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user adds "Sauce Labs Bolt T-Shirt" to the cart
    Then the cart badge should show 3


  @positive @inventory @INV023
  Scenario: Remove product from cart
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge should show 1
    When the user removes "Sauce Labs Backpack" from the cart
    Then the cart badge should not be displayed


  @positive @inventory @INV024
  Scenario: Add and remove one product
    When the user adds "Sauce Labs Backpack" to the cart
    And the user removes "Sauce Labs Backpack" from the cart
    Then the cart badge should not be displayed


  # ============================================================
  # FIRST PRODUCT SCENARIOS
  # ============================================================

  @positive @inventory @INV025
  Scenario: Add first product to cart
    When the user adds the first product to the cart
    Then the cart badge should show 1


  # ============================================================
  # CART NAVIGATION
  # ============================================================

  @positive @inventory @INV026
  Scenario: Navigate to shopping cart
    When the user opens the shopping cart
    Then the shopping cart page should be displayed


  @positive @inventory @INV027
  Scenario: Add product and open shopping cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user opens the shopping cart
    Then the shopping cart page should be displayed


  @positive @inventory @INV028
  Scenario: Add multiple products and open shopping cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user opens the shopping cart
    Then the shopping cart page should be displayed

  
  
    
    