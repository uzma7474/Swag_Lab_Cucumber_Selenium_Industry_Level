
@Checkout_Multi
Feature: SauceDemo Checkout Step Two - Checkout Overview

  # =========================================================
  # BACKGROUND
  # =========================================================

  Background:
    Given the user is logged in to SauceDemo
    And the inventory page is displayed
    
    
    
# =========================================================
# MULTIPLE PRODUCT TEST CASES
# =========================================================


@checkoutStepTwo @positive @CST227
Scenario: Verify multiple products are displayed in Checkout Overview

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


@checkoutStepTwo @positive @CST228
Scenario: Verify multiple product count in Checkout Overview

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
    And the Checkout Overview should contain 3 products
    And all selected products should be displayed in Checkout Overview


    
 
  #@checkoutStepTwo @positive @CST228
  #Scenario: Verify multiple product count in Checkout Overview
   # Given the user has added 3 products to the cart
   # And the user has proceeded to Checkout Step Two
    #Then the Checkout Overview should contain 3 products


  @checkoutStepTwo @positive @CST229
  Scenario: Verify all selected product names
   
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
    And the Checkout Overview should contain 3 products
    And all selected products should be displayed in Checkout Overview
    Then the following products should be displayed:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |


  @checkoutStepTwo @positive @CST230
  Scenario: Verify subtotal for multiple products
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
    And the Checkout Overview should contain 3 products
    And all selected products should be displayed in Checkout Overview
    Then the following products should be displayed:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

    And the subtotal should equal the sum of all selected product prices


  @checkoutStepTwo @positive @CST231
  Scenario: Verify tax for multiple products
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
    And the Checkout Overview should contain 3 products
    And all selected products should be displayed in Checkout Overview
    Then the following products should be displayed:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

    And the subtotal should equal the sum of all selected product prices
    And the tax should be calculated from the subtotal


  @checkoutStepTwo @positive @CST232
  Scenario: Verify total for multiple products
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
    And the Checkout Overview should contain 3 products
    And all selected products should be displayed in Checkout Overview
    Then the following products should be displayed:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

    And the subtotal should equal the sum of all selected product prices
    And the total should equal subtotal plus tax


  # =========================================================
  # NEGATIVE / EDGE CASES
  # =========================================================

  @checkoutStepTwo @negative @CST301
  Scenario: Attempt to access Checkout Step Two without completing Checkout Step One
    Given the user attempts to directly open Checkout Step Two
    Then the user should not be able to complete checkout without valid checkout information


  @checkoutStepTwo @negative @CST302
 Scenario: Verify Checkout Step Two cannot be completed with an empty cart
    Given the user has no products in the cart
    When the user attempts to proceed through checkout
    Then the user should not reach Checkout Step Two
 
 
 # Scenario: Verify Checkout Step Two cannot be completed with an empty cart
  #  Given the user has no products in the cart
   # And the user proceeds through checkout
   # When the user reaches Checkout Step Two
   # Then the Checkout Overview should not contain any product


  @checkoutStepTwo @negative @CST303
  Scenario: Verify Finish button behavior with an empty cart
    Given the user has no products in the cart
    And the user proceeds through checkout
    When the user reaches Checkout Step Two
    And the user clicks the Finish button
    Then the checkout completion behavior should be handled correctly


  @checkoutStepTwo @negative @CST304
  Scenario: Verify Checkout Step Two after refreshing the page
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
    And the Checkout Overview should contain 3 products
    And all selected products should be displayed in Checkout Overview
    Then the following products should be displayed:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

    When the user refreshes the Checkout Step Two page
    Then the Checkout Step Two page should remain displayed
    And the selected product should remain displayed


  @checkoutStepTwo @negative @CST305
  Scenario: Verify Checkout Step Two after browser back navigation
    When the user navigates back using the browser
    Then the user should be returned to the previous checkout page


  @checkoutStepTwo @negative @CST306
  Scenario: Verify Checkout Step Two after browser forward navigation
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
    
    Given the user has navigated back from Checkout Step Two
    When the user navigates forward using the browser
    Then Checkout Step Two should be displayed


  @checkoutStepTwo @negative @CST307
  Scenario: Verify Finish button is not accidentally triggered by double click
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
    When the user double clicks the Finish button
    Then only one checkout completion should be processed


  @checkoutStepTwo @negative @CST308
  Scenario: Verify Cancel button does not complete the order
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
    
    When the user clicks the Cancel button
    Then the order should not be completed


  @checkoutStepTwo @negative @CST309
  Scenario: Verify Checkout Step Two does not allow modification of checkout information
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
    
    Then the customer checkout information should be displayed as review information
    And the user should not be able to edit checkout information on Checkout Step Two


  # =========================================================
  # DATA CONSISTENCY TESTS
  # =========================================================

  @checkoutStepTwo @positive @CST401
  Scenario: Verify product in Checkout Overview matches product in Cart
 #Scenario: Verify products from Cart are displayed in Checkout Overview

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

    And the product "Sauce Labs Backpack" should be displayed in Checkout Overview
    And the product "Sauce Labs Bike Light" should be displayed in Checkout Overview
    And the product "Sauce Labs Bolt T-Shirt" should be displayed in Checkout Overview



@checkoutStepTwo @positive @CST402
Scenario: Verify product price in Checkout Overview matches Cart price

    Given the user has added the following product to the cart:
      | product             |
      | Sauce Labs Backpack |

    When the user opens the shopping cart

    Then the shopping cart page should be displayed
    And the product "Sauce Labs Backpack" should be displayed in the cart
    And the product "Sauce Labs Backpack" price should be captured from the Cart

    When the user clicks the Checkout button

    Then the Checkout Step One page should be displayed
    And the page title should be "Checkout: Your Information"

    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "411042"
    And the user clicks the Continue button

    Then the Checkout Step Two page should be displayed
    And the Checkout Step Two URL should contain "/checkout-step-two.html"
    And the product "Sauce Labs Backpack" should be displayed in Checkout Overview
    And the product "Sauce Labs Backpack" price should match the Cart price

@checkoutStepTwo @positive @CST403
Scenario: Verify product count in Checkout Overview matches Cart

    Given the user has added the following products to the cart:
      | product                |
      | Sauce Labs Backpack    |
      | Sauce Labs Bike Light  |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart

    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the Cart item count should be 3

    When the user clicks the Checkout button

    Then the Checkout Step One page should be displayed
    And the page title should be "Checkout: Your Information"

    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "411042"
    And the user clicks the Continue button

    Then the Checkout Step Two page should be displayed
    And the Checkout Step Two URL should contain "/checkout-step-two.html"
    And the Checkout Overview item count should be 3
    And the Checkout Overview item count should match the Cart item count
    
     
 @checkoutStepTwo @positive @CST404
Scenario: Verify Checkout Overview subtotal matches the sum of Cart item prices

    Given the user has added the following products to the cart:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices
    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two
    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    And the Checkout Overview subtotal should match the sum of Cart item prices
    
    

@checkoutStepTwo @positive @CST405
Scenario: Verify total calculation

    Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    And the Checkout Overview subtotal should match the sum of Cart item prices
    And the total should equal subtotal plus tax



  # =========================================================
  # PRICE / CALCULATION TESTS
  # =========================================================

  @checkoutStepTwo @positive @CST406
  Scenario: Verify subtotal contains a valid currency amount
    Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    And the Checkout Overview subtotal should match the sum of Cart item prices
    And the total should equal subtotal plus tax
	And the subtotal should contain a valid dollar amount


@checkoutStepTwo @positive @CST407
Scenario: Verify tax contains a valid currency amount

    Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    And the tax should contain a valid dollar amount

 @checkoutStepTwo @positive @CST408
Scenario: Verify total contains a valid currency amount

    Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    And the total should contain a valid dollar amount
    
    
@checkoutStepTwo @negative @CST409
Scenario: Verify total is not less than subtotal

    Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    And the total should not be less than the subtotal



@checkoutStepTwo @negative @CST410
Scenario: Verify tax is not negative

    Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    And the tax amount should not be negative
    
    

  # =========================================================
  # UI / CONTROL TESTS
  # =========================================================

  @checkoutStepTwo @positive @CST411
  Scenario: Verify Checkout Step Two page title
     Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    Then the page title should be "Checkout: Overview"


  @checkoutStepTwo @positive @CST412
  Scenario: Verify Finish button text
     Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    Then the Finish button text should be "Finish"


  @checkoutStepTwo @positive @CST413
  Scenario: Verify Cancel button text
    Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    Then the Cancel button text should be "Cancel"


  @checkoutStepTwo @positive @CST414
  Scenario: Verify Checkout Overview contains payment information
    Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    Then Payment Information should be visible


  @checkoutStepTwo @positive @CST415
  Scenario: Verify Checkout Overview contains shipping information
    Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart
    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    Then Shipping Information should be visible


@checkoutStepTwo @positive @CST416
Scenario: Verify Checkout Overview contains price summary

    Given the user has added the following products to the cart:
      | product                  |
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |

    When the user opens the shopping cart

    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"
    And the user should verify the Cart item prices

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"
    And subtotal should be visible
    And tax should be visible
    And total should be visible

  # =========================================================
  # END-TO-END TESTS
  # =========================================================

@checkoutStepTwo @e2e @smoke @CST501
Scenario: Complete checkout with one product

    Given the user has added the following products to the cart:
      | product             |
      | Sauce Labs Backpack |

    When the user opens the shopping cart

    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"

    When the user clicks the Finish button

    Then the Checkout Complete page should be displayed
    And the Checkout Complete URL should contain "/checkout-complete.html"
    And the order confirmation should be displayed
    
    
    

@checkoutStepTwo @e2e @CST502
Scenario: Complete checkout with multiple products

    Given the user has added the following products to the cart:
      | product                  |
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |

    When the user opens the shopping cart

    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"

    When the user clicks the Finish button

    Then the Checkout Complete page should be displayed
    And the Checkout Complete URL should contain "/checkout-complete.html"
    And the order confirmation should be displayed
    
    

 @checkoutStepTwo @e2e @CST503
Scenario: Cancel checkout before completing order

    Given the user has added the following products to the cart:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Bolt T-Shirt |

    When the user opens the shopping cart

    Then the shopping cart page should be displayed
    And the shopping cart URL should contain "/cart.html"

    When the user proceeds to Checkout
    And the user enters valid checkout information
    And the user continues to Checkout Step Two

    Then the Checkout Overview page should be displayed
    And the Checkout Overview URL should contain "/checkout-step-two.html"

    When the user clicks the Cancel button

    Then the user should be redirected to the Inventory page
    And the Inventory URL should contain "/inventory.html"
    And the order should not be completed
    
    
    
    
    