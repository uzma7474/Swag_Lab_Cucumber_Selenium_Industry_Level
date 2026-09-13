
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


  @negative @inventory @INV042
  Scenario: Verify blank product name is rejected
    When the user tries to verify product ""
    Then an illegal argument error should be raised


  @negative @inventory @INV043
  Scenario: Verify null product name is rejected
    When the user tries to verify a null product
    Then an illegal argument error should be raised




  

  # ============================================================
  # NEGATIVE SCENARIOS - PRICE
  # ============================================================

  @negative @inventory @INV044
  Scenario: Verify incorrect product price
    Then the price of product "Sauce Labs Backpack" should not be "$99.99"


  @negative @inventory @INV045
  Scenario: Verify price of non-existing product
    When the user tries to get the price of "Invalid Product"
    Then the product lookup should fail


  @negative @inventory @INV046
  Scenario: Verify blank product name while retrieving price
    When the user tries to get the price of ""
    Then an illegal argument error should be raised


  @negative @inventory @INV047
  Scenario: Verify null product name while retrieving price
    When the user tries to get the price of a null product
    Then an illegal argument error should be raised


  # ============================================================
  # NEGATIVE SCENARIOS - ADD TO CART
  # ============================================================





  @negative @INV048
  Scenario: Try to add invalid product
    When the user tries to add "Invalid Product" to the cart
    Then the product lookup should fail


  @negative @INV049
  Scenario: Try to add blank product
    When the user tries to add "" to the cart
    Then an illegal argument error should be raised


  @negative @inventory @INV050
  Scenario: Try to add null product to cart
    When the user tries to add a null product to the cart
    Then an illegal argument error should be raised


  # ============================================================
  # NEGATIVE SCENARIOS - REMOVE FROM CART
  # ============================================================
  @negative @INV051
  Scenario: Try to remove product that is not in cart
    When the user tries to remove "Sauce Labs Backpack" from the cart
    Then the product removal lookup should fail


  @negative @inventory @INV052
  Scenario: Try to remove invalid product from cart
    When the user tries to remove "Invalid Product" from the cart
    Then the product removal lookup should fail


  @negative @inventory @INV053
  Scenario: Try to remove blank product from cart
    When the user tries to remove "" from the cart
    Then an illegal argument error should be raised


  @negative @inventory @INV054
  Scenario: Try to remove null product from cart
    When the user tries to remove a null product from the cart
    Then an illegal argument error should be raised


  # ============================================================
  # NEGATIVE SCENARIOS - SORTING
  # ============================================================



  @negative @INV055
  Scenario: Try to sort using blank option
    When the user tries to sort products by ""
    Then an illegal argument error should be raised


  @negative @inventory @INV056
  Scenario: Try to sort using null sort option
    When the user tries to sort products using a null option
    Then an illegal argument error should be raised


  @negative @INV057
  Scenario: Try to sort using invalid option
    When the user tries to sort products by "Invalid Sort"
    Then the sort operation should fail


  # ============================================================
  # NEGATIVE SCENARIOS - PRODUCT COUNT
  # ============================================================
  
  @negative @INV058
  Scenario: Verify incorrect product count
    Then the inventory page should not display 10 products
    
    
@negative @inventory @INV059
  Scenario: Verify inventory is not empty
    Then the inventory product count should be greater than 0


  # ============================================================
  # NEGATIVE SCENARIOS - CART
  # ============================================================

  @negative @inventory @INV060
  Scenario: Verify cart badge is not displayed before adding product
    Then the cart badge should not be displayed


  @negative @inventory @INV061
  Scenario: Verify cart count is zero before adding product
    Then the cart badge count should be 0
        

   
