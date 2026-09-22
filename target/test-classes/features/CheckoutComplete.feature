@checkoutComplete

@CHKCOM
Feature: SauceDemo Checkout Complete

  As a SauceDemo customer
  I want to complete my order successfully
  So that I can verify the order confirmation and post-checkout behavior


Background:
    Given the user is logged in to SauceDemo
    And the inventory page is displayed
    Given the user has added the following products to the cart:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the product "Sauce Labs Backpack" should be displayed in the cart
    And the product "Sauce Labs Bike Light" should be displayed in the cart
    And the product "Sauce Labs Bolt T-Shirt" should be displayed in the cart

    When the user clicks the Checkout button
    Then the Checkout Step One page should be displayed
    And the page title should be "Checkout: Your Information"

    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "411042"
    And the user clicks the Continue button

    Then the Checkout Step Two page should be displayed
    And the Checkout Step Two URL should contain "/checkout-step-two.html"
    And all selected products should be displayed in Checkout Overview
   
    When the user clicks the Finish button
    Then the Checkout Complete page should be displayed
    

   




#========================================================================================================
# POSITIVE TEST CASES - ORDER COMPLETION
#========================================================================================================

  @positive @e2e @CHKCOMT001
  Scenario: Successfully complete an order
	Given the user has added the following products to the cart:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the product "Sauce Labs Backpack" should be displayed in the cart
    And the product "Sauce Labs Bike Light" should be displayed in the cart
    And the product "Sauce Labs Bolt T-Shirt" should be displayed in the cart

    When the user clicks the Checkout button
    Then the Checkout Step One page should be displayed
    And the page title should be "Checkout: Your Information"

    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "411042"
    And the user clicks the Continue button

    Then the Checkout Step Two page should be displayed
    And the Checkout Step Two URL should contain "/checkout-step-two.html"
    And all selected products should be displayed in Checkout Overview
   
    When the user clicks the Finish button
    Then the Checkout Complete page should be displayed
    And the Checkout Complete URL should contain "/checkout-complete.html"
    And the order confirmation should be displayed

   
    Then the Checkout Complete page should be displayed
    And the Checkout Complete URL should contain "/checkout-complete.html"


  @positive @e2e @CHKCOMT002
  Scenario: Verify Checkout Complete page title
    Given the user has successfully completed an order
    Then the Checkout Complete page title should be "Checkout: Complete!"


  @positive @e2e @CHKCOMT003
  Scenario: Verify successful order confirmation message
    Given the user has successfully completed an order
    Then the order confirmation message should be displayed
    And the order confirmation header should be displayed
    And the order confirmation header message should contain "Thank you for your order!"
    And the order confirmation message should contain "Your order has been dispatched, and will arrive just as fast as the pony can get there!"


  @positive @e2e @CHKCOMT004
  Scenario: Verify Checkout Complete confirmation container
    Given the user has successfully completed an order
    Then the Checkout Complete container should be displayed


  @positive @e2e @CHKCOMT005
  Scenario: Verify order confirmation icon
    Given the user has successfully completed an order
    Then the order confirmation icon should be displayed


  @positive @e2e @CHKCOMT006
  Scenario: Verify Back Home button
    Given the user has successfully completed an order
    Then the Back Home button should be displayed
    And the Back Home button text should be "Back Home"


  @positive @e2e @CHKCOMT007
  Scenario: Navigate to Inventory using Back Home
    Given the user has successfully completed an order
    When the user clicks the Back Home button
	Then the user should be redirected to the Inventory page
    And the Inventory URL should contain "/inventory.html"


  @positive @e2e @CHKCOMT008
  Scenario: Verify Inventory page after clicking Back Home
    Given the user has successfully completed an order
    When the user clicks the Back Home button
    Then the Products page should be displayed
    And the product list should be visible


  @positive @e2e @CHKCOMT009
  Scenario: Verify cart is empty after successful order
    Given the user has successfully completed an order
    When the user clicks the Back Home button
    Then the user should be redirected to the Inventory page
    And the shopping cart badge should not be displayed


  # ============================================================
  # SINGLE PRODUCT ORDER
  # ============================================================

  @positive @e2e @CHKCOMT010
  Scenario: Complete checkout with one product
  	Given the user has successfully completed an order
    Then the order confirmation message should be displayed
    And the order confirmation header should be displayed
    And the order confirmation header message should contain "Thank you for your order!"
  



  # ============================================================
  # URL VALIDATION
  # ============================================================

  @positive @e2e @CHKCOMT011
  Scenario: Verify Checkout Complete URL
    Given the user has successfully completed an order
    Then the current URL should contain "/checkout-complete.html"


  # ============================================================
  # REFRESH
  # ============================================================

  @positive @e2e @CHKCOMT012
  Scenario: Refresh Checkout Complete page
    Given the user has successfully completed an order
    When the user refreshes the page
    Then the Checkout Complete page should be displayed
    And the order confirmation message should be displayed


  # ============================================================
  # UI VALIDATION
  # ============================================================

  @positive @ui @CHKCOMT013
  Scenario: Verify Checkout Complete heading is visible
    Given the user has successfully completed an order
    Then the Checkout Complete heading should be visible


  @positive @ui @CHKCOMT014
  Scenario: Verify order confirmation message is visible
    Given the user has successfully completed an order
    Then the order confirmation message should be visible


  @positive @ui @CHKCOMT015
  Scenario: Verify Back Home button is visible
    Given the user has successfully completed an order
    Then the Back Home button should be visible


  @positive @ui @CHKCOMT016
  Scenario: Verify Back Home button is enabled
    Given the user has successfully completed an order
    Then the Back Home button should be enabled


  @positive @ui @CHKCOMT017
  Scenario: Verify confirmation message is not empty
    Given the user has successfully completed an order
    Then the confirmation message should not be empty


  # ============================================================
  # BACK HOME FUNCTIONALITY
  # ============================================================

  @positive @navigation @CHKCOMT018
  Scenario: Click Back Home once
    Given the user has successfully completed an order
    When the user clicks the Back Home button
    Then the user should be redirected to the Inventory page


  @positive @navigation @CHKCOMT019
  Scenario: Verify products after returning Home
    Given the user has successfully completed an order
    When the user clicks the Back Home button
    Then the Products title should be displayed
    And the product list should contain products


  @positive @navigation @CHKCOMT020
  Scenario: Verify cart state after returning Home
    Given the user has successfully completed an order
    When the user clicks the Back Home button
    Then the shopping cart should be empty


  # ============================================================
  # NEGATIVE - DIRECT URL ACCESS
  # ============================================================

  @negative @security @CHKCOMT021
  Scenario: Access Checkout Complete page without completing checkout
    Given the user is logged in as "standard_user"
    When the user navigates directly to "/checkout-complete.html"
    Then the Checkout Complete heading should be visible
    And the order confirmation message should be visible


  @negative @security @CHKCOMT023
  Scenario: Access Checkout Complete page without authentication

    Given the user is not logged in

    When the user navigates directly to "/checkout-complete.html"

    Then the user should not see a valid order confirmation


  @negative @checkout @CHKCOMT024
  Scenario: Access Checkout Complete page without products in cart

    Given the user is logged in as "standard_user"
    And the shopping cart is empty

    When the user navigates directly to "/checkout-complete.html"

    Then the user should not see a valid completed-order confirmation


  # ============================================================
  # EMPTY CART
  # ============================================================

  @negative @checkout @CHKCOMT025
  Scenario: Attempt to complete checkout with an empty cart

    Given the user is logged in as "standard_user"
    And the shopping cart is empty

    When the user attempts to proceed to Checkout Step One

    Then the application should not complete the order


  # ============================================================
  # BROWSER NAVIGATION
  # ============================================================

  @negative @navigation @CHKCOMT026
  Scenario: Navigate back after completing an order

    Given the user has successfully completed an order

    When the user navigates back using the browser Back button

    Then the application should not create another order
    And the user should not be able to duplicate the completed order


  @negative @navigation @CHKCOMT027
  Scenario: Navigate forward after leaving Checkout Complete page

    Given the user has successfully completed an order

    When the user clicks the Back Home button
    And the user navigates forward using the browser Forward button

    Then the application should handle the completed-order state correctly


  # ============================================================
  # REFRESH / DUPLICATE ORDER
  # ============================================================

  @negative @order @CHKCOMT028
  Scenario: Refresh completed order page

    Given the user has successfully completed an order

    When the user refreshes the Checkout Complete page

    Then the application should not create a duplicate order
    And the confirmation page should remain consistent


  # ============================================================
  # LOGOUT
  # ============================================================

  @negative @session @CHKCOMT029
  Scenario: Logout after completing an order

    Given the user has successfully completed an order

    When the user opens the application menu
    And the user logs out

    Then the user should be redirected to the Login page


  @negative @session @CHKCOMT030
  Scenario: Access Checkout Complete page after logout

    Given the user has successfully completed an order
    And the user has logged out

    When the user navigates to "/checkout-complete.html"

    Then the user should not see a valid authenticated order confirmation


  # ============================================================
  # ORDER DATA INTEGRITY
  # ============================================================

  @positive @order @CHKCOMT031
  Scenario: Verify confirmation after purchasing one item

    Given the user is logged in as "standard_user"
    And the user purchases "Sauce Labs Backpack"
    And the user completes Checkout Step One
    And the user reaches the Checkout Overview page

    When the user clicks the Finish button

    Then the order confirmation message should be displayed


  @positive @order @CHKCOMT032
  Scenario: Verify confirmation after purchasing multiple items

    Given the user is logged in as "standard_user"
    And the user purchases the following products:
      | Product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

    And the user completes Checkout Step One
    And the user reaches the Checkout Overview page

    When the user clicks the Finish button

    Then the order confirmation message should be displayed


  @negative @order @CHKCOMT033
  Scenario: Prevent duplicate order completion

    Given the user has successfully completed an order

    When the user attempts to complete the same order again

    Then another order should not be created


  # ============================================================
  # BACK HOME NAVIGATION VALIDATION
  # ============================================================

  @positive @navigation @CHKCOMT034
  Scenario: Verify Back Home does not return to checkout

    Given the user has successfully completed an order

    When the user clicks the Back Home button

    Then the current URL should contain "/inventory.html"
    And the current URL should not contain "/checkout-step-one.html"
    And the current URL should not contain "/checkout-step-two.html"


  @negative @navigation @CHKCOMT035
  Scenario: Click Back Home multiple times

    Given the user has successfully completed an order

    When the user clicks the Back Home button
    And the user navigates back using the browser Back button

    Then the application should handle navigation without creating a duplicate order


  # ============================================================
  # CHECKOUT COMPLETE PAGE SHOULD NOT CONTAIN CHECKOUT CONTROLS
  # ============================================================

  @positive @ui @CHKCOMT036
  Scenario: Verify checkout information fields are not displayed

    Given the user has successfully completed an order

    Then the First Name field should not be displayed
    And the Last Name field should not be displayed
    And the Postal Code field should not be displayed


  @positive @ui @CHKCOMT037
  Scenario: Verify Finish button is not displayed on Checkout Complete page

    Given the user has successfully completed an order

    Then the Finish button should not be displayed


  @positive @ui @CHKCOMT038
  Scenario: Verify Cancel button is not displayed on Checkout Complete page

    Given the user has successfully completed an order

    Then the Cancel button should not be displayed