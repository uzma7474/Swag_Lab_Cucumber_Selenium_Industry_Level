
@Checkout_Two_Step
Feature: SauceDemo Checkout Step Two - Checkout Overview

  As a SauceDemo customer
  I want to review my order before completing checkout
  So that I can verify my products, payment information,
  shipping information and final price before placing the order


  # =========================================================
  # BACKGROUND
  # =========================================================

  Background:
    Given the user is logged in to SauceDemo
    And the inventory page is displayed
    And the user adds "Sauce Labs Backpack" to the cart
    And the user click on shopping cart Icon and shopping cart page open   
    Given the user has at least one product in the cart
    When the user clicks the Checkout button
    Then the Checkout Step One page should be displayed
    When the user enters first name "John" 
   	And the user enters last name "Doe"
   	And the user enters postal code "411042" 
   	And the user clicks the Continue button
    
    #Then the Checkout Step Two page should be displayed

    
 


  # =========================================================
  # POSITIVE TEST CASES
  # =========================================================

  @checkoutStepTwo @positive @smoke @CST201
  Scenario: Verify Checkout Step Two page is displayed
    Then the Checkout Step Two page should be displayed
    And the page title of checkout overview should be "Checkout: Overview"


  @checkoutStepTwo @positive @CST202
  Scenario: Verify Checkout Overview container is displayed
    Then the Checkout Overview container should be displayed


  @checkoutStepTwo @positive @CST203
  Scenario: Verify product is displayed in Checkout Overview
    Then the product "Sauce Labs Backpack" should be displayed in Checkout Overview


  @checkoutStepTwo @positive @CST204
  Scenario: Verify product quantity in Checkout Overview
    Then the Checkout Overview should contain 1 product


  @checkoutStepTwo @positive @CST205
  Scenario: Verify product name in Checkout Overview
    Then the first product name should be "Sauce Labs Backpack"


  @checkoutStepTwo @positive @CST206
  Scenario: Verify product description is displayed
    Then the product description should be displayed for "Sauce Labs Backpack"


  @checkoutStepTwo @positive @CST207
  Scenario: Verify product price is displayed
    Then the product price should be displayed for "Sauce Labs Backpack"


  @checkoutStepTwo @positive @CST208
  Scenario: Verify Payment Information is displayed
    Then Payment Information should be displayed


  @checkoutStepTwo @positive @CST209
  Scenario: Verify Payment Information value
    Then Payment Information should be "SauceCard #31337"


  @checkoutStepTwo @positive @CST210
  Scenario: Verify Shipping Information is displayed
    Then Shipping Information should be displayed


  @checkoutStepTwo @positive @CST211
  Scenario: Verify Shipping Information value
    Then Shipping Information should be "Free Pony Express Delivery!"


  @checkoutStepTwo @positive @CST212
  Scenario: Verify subtotal is displayed
    Then the subtotal should be displayed


  @checkoutStepTwo @positive @CST213
  Scenario: Verify tax is displayed
    Then the tax should be displayed


  @checkoutStepTwo @positive @CST214
  Scenario: Verify total is displayed
    Then the total should be displayed


  @checkoutStepTwo @positive @CST215
  Scenario: Verify subtotal for one product
    Then the subtotal should be "Item total: $29.99"


  @checkoutStepTwo @positive @CST216
  Scenario: Verify tax calculation for one product
    Then the tax should be "Tax: $2.40"


  @checkoutStepTwo @positive @CST217
  Scenario: Verify total calculation for one product
    Then the total should be "Total: $32.39"


  @checkoutStepTwo @positive @CST218
  Scenario: Verify Finish button is displayed
    Then the Finish button should be displayed


  @checkoutStepTwo @positive @CST219
  Scenario: Verify Cancel button is displayed
    Then the Cancel button should be displayed on Checkout step two page


  @checkoutStepTwo @positive @CST220
  Scenario: Verify Finish button is enabled
    Then the Finish button should be enabled


  @checkoutStepTwo @positive @CST221
  Scenario: Verify Cancel button is enabled
    Then the Cancel button should be enabled


  @checkoutStepTwo @positive @CST222
  Scenario: Complete checkout successfully from Checkout Step Two
    When the user clicks the Finish button
    Then the Checkout Complete page should be displayed


  @checkoutStepTwo @positive @CST223
  Scenario: Verify successful navigation after clicking Finish
    When the user clicks the Finish button
    Then the URL should contain "checkout-complete.html"


  @checkoutStepTwo @positive @CST224
  Scenario: Cancel checkout from Checkout Step Two
    When the user clicks the Cancel button on checkout step two page
    Then the user should be redirected to the Inventory page


  @checkoutStepTwo @positive @CST225
  Scenario: Verify all Checkout Step Two controls are displayed
    Then all Checkout Step Two controls should be displayed


  @checkoutStepTwo @positive @CST226
  Scenario: Verify Checkout Step Two page is ready for completion
    Then the Checkout Step Two page should be ready for checkout completion


  