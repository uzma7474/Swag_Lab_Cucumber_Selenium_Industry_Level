
@Cart
Feature: Inventory Cart Operations

  Background:
    Given the user is logged in to SauceDemo
    And the inventory page is displayed


#==========================================================================================================
# Cart Page Navigation — Positive
#==========================================================================================================

#==========================================================================================================
# CART001 — Open cart from inventory
#==========================================================================================================
@positive @cart @CART001
Scenario: Open shopping cart from inventory page
  When the user opens the shopping cart
  Then the shopping cart page should be displayed
  And the shopping cart URL should contain "/cart.html"

#==========================================================================================================
# CART002 — Cart page title
#==========================================================================================================

@positive @cart @CART002
Scenario: Verify shopping cart page title
  When the user opens the shopping cart
  Then the shopping cart page heading should be "Your Cart"

#==========================================================================================================
# CART003 — Cart list displayed
#==========================================================================================================

@positive @cart @CART003
Scenario: Verify shopping cart list is displayed
  When the user opens the shopping cart
  Then the cart list should be displayed

#==========================================================================================================
# CART004 — Shopping cart icon displayed
#==========================================================================================================

@positive @cart @CART004
Scenario: Verify shopping cart icon is displayed
  When the user opens the shopping cart
  Then the shopping cart icon should be displayed


#==========================================================================================================
# 2. Empty Cart — Positive
#==========================================================================================================

#==========================================================================================================
# CART005 — Empty cart
#==========================================================================================================

@positive @cart @CART005
Scenario: Verify shopping cart is empty
  When the user opens the shopping cart
  Then the cart should be empty
  And the cart should contain 0 products

#==========================================================================================================
# CART006 — Empty cart badge
#==========================================================================================================

@positive @cart @CART006
Scenario: Verify cart badge is not displayed when cart is empty
  When the user opens the shopping cart
  Then the cart badge should not be displayed

#==========================================================================================================
# CART007 — Empty cart Continue Shopping
#==========================================================================================================

@positive @cart @CART007
Scenario: Continue shopping from an empty cart
  When the user opens the shopping cart
  And the user clicks Continue Shopping
  Then the inventory page should be displayed


#==========================================================================================================
# 3. Single Product — Positive
#==========================================================================================================

#==========================================================================================================
# CART008 — One product displayed
#==========================================================================================================

@positive @cart @CART008
Scenario: Verify one product is displayed in cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the cart should contain 1 product
  And the product "Sauce Labs Backpack" should be displayed in the cart

#==========================================================================================================
# CART009 — Product name
#==========================================================================================================

@positive @cart @CART009
Scenario: Verify product name in cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the product "Sauce Labs Backpack" should be displayed in the cart

#==========================================================================================================
# CART010 — Product price
#==========================================================================================================

@positive @cart @CART010
Scenario: Verify product price in cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the price of "Sauce Labs Backpack" should be "$29.99"


#==========================================================================================================
# CART011 — Product quantity
#==========================================================================================================

@positive @cart @CART011
Scenario: Verify product quantity in cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the quantity of "Sauce Labs Backpack" should be 1

#==========================================================================================================
# CART012 — Product description
#==========================================================================================================

@positive @cart @CART012
Scenario: Verify product description in cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the product description should be displayed for "Sauce Labs Backpack"

#==========================================================================================================
# CART013 — Remove button
#==========================================================================================================

@positive @cart @CART013
Scenario: Verify Remove button is displayed for cart product
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the Remove button should be displayed for "Sauce Labs Backpack"


#==========================================================================================================
# 4. Multiple Products — Positive
#==========================================================================================================

#==========================================================================================================
# CART014 — Two products
#==========================================================================================================

@positive @cart @CART014
Scenario: Verify two products in cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user adds "Sauce Labs Bike Light" to the cart
  And the user opens the shopping cart
  Then the cart should contain 2 products
  And the product "Sauce Labs Backpack" should be displayed in the cart
  And the product "Sauce Labs Bike Light" should be displayed in the cart

#==========================================================================================================
# CART015 — Three products
#==========================================================================================================

@positive @cart @CART015
Scenario: Verify three products in cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user adds "Sauce Labs Bike Light" to the cart
  And the user adds "Sauce Labs Bolt T-Shirt" to the cart
  And the user opens the shopping cart
  Then the cart should contain 3 products

#==========================================================================================================
# CART016 — All products
#==========================================================================================================

@positive @cart @CART016
Scenario: Verify all products can be added to cart
  When the user adds all available products to the cart
  And the user opens the shopping cart
  Then the cart should contain 6 products
  And the cart badge should show 6

#==========================================================================================================
# 5. Cart Badge — Positive
#==========================================================================================================
#==========================================================================================================
# CART017 — Badge shows one
#==========================================================================================================

@positive @cart @CART017
Scenario: Verify cart badge after adding one product
  When the user adds "Sauce Labs Backpack" to the cart
  Then the cart badge should show 1

#==========================================================================================================
# CART018 — Badge shows two
#==========================================================================================================

@positive @cart @CART018
Scenario: Verify cart badge after adding two products
  When the user adds "Sauce Labs Backpack" to the cart
  And the user adds "Sauce Labs Bike Light" to the cart
  Then the cart badge should show 2

#==========================================================================================================
# CART019 — Badge shows six
#==========================================================================================================

@positive @cart @CART019
Scenario: Verify cart badge after adding all products
  When the user adds all available products to the cart
  Then the cart badge should show 6


#==========================================================================================================
# CART020 — Badge decrements after removal
#==========================================================================================================

@positive @cart @CART020
Scenario: Verify cart badge decreases after removing product
  When the user adds "Sauce Labs Backpack" to the cart
  And the user adds "Sauce Labs Bike Light" to the cart
  Then the cart badge should show 2
  When the user opens the shopping cart
  And the user removes "Sauce Labs Backpack" from the cart
  Then the cart badge should show 1

#==========================================================================================================
# CART021 — Badge disappears after removing last product
#==========================================================================================================

@positive @cart @CART021
Scenario: Verify cart badge disappears after removing last product
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  And the user removes "Sauce Labs Backpack" from the cart
  Then the cart badge should not be displayed
  
#==========================================================================================================
# 6. Remove Product — Positive
#==========================================================================================================
#==========================================================================================================
# CART022 — Remove product from cart
#==========================================================================================================

@positive @cart @CART022
Scenario: Remove product from cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  When the user removes "Sauce Labs Backpack" from the cart
  Then the product "Sauce Labs Backpack" should not be displayed in the cart

#==========================================================================================================
# CART023 — Cart becomes empty after removal
#==========================================================================================================

@positive @cart @CART023
Scenario: Cart becomes empty after removing the only product
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  And the user removes "Sauce Labs Backpack" from the cart
  Then the cart should be empty

#==========================================================================================================
# CART024 — Remove one of multiple products
#==========================================================================================================

@positive @cart @CART024
Scenario: Remove one product from multiple products
  When the user adds "Sauce Labs Backpack" to the cart
  And the user adds "Sauce Labs Bike Light" to the cart
  And the user opens the shopping cart
  When the user removes "Sauce Labs Backpack" from the cart
  Then the cart should contain 1 product
  And the product "Sauce Labs Bike Light" should be displayed in the cart
  And the product "Sauce Labs Backpack" should not be displayed in the cart


#==========================================================================================================
# CART025 — Remove all products
#==========================================================================================================

@positive @cart @CART025
Scenario: Remove all products from cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user adds "Sauce Labs Bike Light" to the cart
  And the user adds "Sauce Labs Bolt T-Shirt" to the cart
  And the user opens the shopping cart
  When the user removes "Sauce Labs Backpack" from the cart
  And the user removes "Sauce Labs Bike Light" from the cart
  And the user removes "Sauce Labs Bolt T-Shirt" from the cart
  Then the cart should be empty
  And the cart badge should not be displayed

#==========================================================================================================
# 7. Continue Shopping — Positive
#==========================================================================================================
#==========================================================================================================
# CART026 — Continue Shopping
#==========================================================================================================

@positive @cart @CART026
Scenario: Continue shopping from cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  When the user clicks Continue Shopping
  Then the inventory page should be displayed

#==========================================================================================================
# CART027 — Cart retained after Continue Shopping
#==========================================================================================================

@positive @cart @CART027
Scenario: Verify cart is retained after Continue Shopping
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  And the user clicks Continue Shopping
  Then the inventory page should be displayed
  And the cart badge should show 1

#==========================================================================================================
# CART028 — Continue shopping and add another product
#==========================================================================================================

@positive @cart @CART028
Scenario: Add another product after Continue Shopping
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  And the user clicks Continue Shopping
  And the user adds "Sauce Labs Bike Light" to the cart
  Then the cart badge should show 2

#==========================================================================================================
# 8. Checkout Navigation — Positive
#==========================================================================================================
#==========================================================================================================
# CART029 — Checkout button displayed
#==========================================================================================================

@positive @cart @CART029
Scenario: Verify Checkout button is displayed
  When the user opens the shopping cart
  Then the Checkout button should be displayed

#==========================================================================================================
# CART030 — Checkout with one product
#==========================================================================================================

@positive @cart @CART030
Scenario: Navigate to checkout with one product
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  When the user clicks Checkout
  Then the checkout page should be displayed

#==========================================================================================================
# CART031 — Checkout with multiple products
#==========================================================================================================

@positive @cart @CART031
Scenario: Navigate to checkout with multiple products
  When the user adds "Sauce Labs Backpack" to the cart
  And the user adds "Sauce Labs Bike Light" to the cart
  And the user opens the shopping cart
  When the user clicks Checkout
  Then the checkout page should be displayed

#==========================================================================================================
# 9. Cart Persistence — Positive
#==========================================================================================================
#==========================================================================================================
# CART032 — Refresh cart with one product
#==========================================================================================================

@positive @cart @CART032
Scenario: Verify cart product remains after refresh
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  And the user refreshes the shopping cart page
  Then the product "Sauce Labs Backpack" should be displayed in the cart
  And the cart badge should show 1


#==========================================================================================================
# CART033 — Refresh cart with multiple products
#==========================================================================================================

#@positive @cart @CART033
#Scenario: Verify multiple cart products remain after refresh
  #When the user adds "Sauce Labs Backpack" to the cart
  #And the user adds "Sauce Labs Bike Light" to the cart
  #And the user opens the shopping cart
  #And the user refreshes the shopping cart page
 #Then the cart should contain 2 products
 
 
 @positive @cart @CART033
Scenario: Verify multiple cart products remain after refresh
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user navigates to the shopping cart
    Then the cart should contain 2 products
    When the user refreshes the shopping cart page
    Then the cart should contain 2 products

#==========================================================================================================
# 10. Negative Test Cases
#==========================================================================================================
#==========================================================================================================
# CARTN001 — Access empty cart
#==========================================================================================================

@negative @cart @CARTN001
Scenario: Access shopping cart without adding products
  When the user opens the shopping cart
  Then the shopping cart page should be displayed
  And the cart should be empty
  And the cart badge should not be displayed

#==========================================================================================================
# CARTN002 — Remove product that does not exist
#==========================================================================================================

@negative @cart @CARTN002
Scenario: Attempt to remove a product that is not in cart
  When the user adds "Sauce Labs Backpack" to the cart
  And  the user opens the shopping cart  
  And the user attempts to remove "Sauce Labs Backpack"
  Then the cart should remain empty

#==========================================================================================================
# CARTN003 — Remove already removed product
#==========================================================================================================

@negative @cart @CARTN003
Scenario: Attempt to remove the same product twice
   When the user adds "Sauce Labs Backpack" to the cart
  And  the user opens the shopping cart  
  And the user attempts to remove "Sauce Labs Backpack"
  Then the cart should remain empty
  When the user attempts to remove "Sauce Labs Backpack"
  Then the cart should remain empty
 


  
#==========================================================================================================
# CARTN004 — Invalid product
#==========================================================================================================

@negative @cart @CARTN004
Scenario: Attempt to access an invalid product in cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the product "Invalid Product" should not be displayed in the cart

#==========================================================================================================
# CARTN005 — Incorrect product price
#==========================================================================================================

@negative @cart @CARTN005
Scenario: Verify product does not have incorrect price
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the price of "Sauce Labs Backpack" should not be "$99.99"


#==========================================================================================================
# CARTN006 — Incorrect quantity
#==========================================================================================================

@negative @cart @CARTN006
Scenario: Verify product does not have incorrect quantity
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the quantity of "Sauce Labs Backpack" should not be 2

#==========================================================================================================
# CARTN007 — Incorrect cart count
#==========================================================================================================

@negative @cart @CARTN007
Scenario: Verify cart does not contain an incorrect item count
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the cart should contain 1 product
  And the cart should not contain 2 products

#==========================================================================================================
# CARTN008 — Incorrect badge count
#==========================================================================================================

@negative @cart @CARTN008
Scenario: Verify cart badge does not show incorrect count
  When the user adds "Sauce Labs Backpack" to the cart
  Then the cart badge should not show 2


#==========================================================================================================
# CARTN009 — Removed product should not remain
#==========================================================================================================

@negative @cart @CARTN009
Scenario: Verify removed product is no longer displayed
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  #And the user removes "Sauce Labs Backpack" from the cart
  #Then the product "Sauce Labs Backpack" should not be displayed in the cart

#==========================================================================================================
# CARTN010 — Empty cart checkout
#==========================================================================================================

@negative @cart @CARTN010
Scenario: Attempt to checkout with an empty cart
  When the user opens the shopping cart
  And the cart is empty
  When the user attempts to checkout
  Then the application should handle the empty cart checkout appropriately

#==========================================================================================================
# 11. Boundary / Robustness Test Cases
#==========================================================================================================
#==========================================================================================================
# CARTB001 — One product
#==========================================================================================================

@boundary @cart @CARTB001
Scenario: Verify minimum cart quantity
  When the user adds one product to the cart
  And the user opens the shopping cart
  Then the cart should contain 1 product

#==========================================================================================================
# CARTB002 — All available products
#==========================================================================================================

@boundary @cart @CARTB002
Scenario: Verify cart with all available products
  When the user adds all available products to the cart
  And the user opens the shopping cart
  Then the cart should contain 6 products

#==========================================================================================================
# CARTB003 — Remove first product
#==========================================================================================================

@boundary @cart @CARTB003
Scenario: Remove first product from cart
  When the user adds all available products to the cart
  And the user opens the shopping cart
  And the user removes the first product
  Then the cart should contain 5 products

#==========================================================================================================
# CARTB004 — Remove last product
#==========================================================================================================

@boundary @cart @CARTB004
Scenario: Remove last product from cart
  When the user adds all available products to the cart
  And the user opens the shopping cart
  And the user removes the last product
  Then the cart should contain 5 products

#==========================================================================================================
# CARTB005 — Remove all products sequentially
#==========================================================================================================

@boundary @cart @CARTB005
Scenario: Remove all products sequentially
  When the user adds all available products to the cart
  And the user opens the shopping cart
  And the user removes every product from the cart
  Then the cart should contain 0 products
  And the cart badge should not be displayed

#==========================================================================================================
# 12. End-to-End Cart Scenarios
#==========================================================================================================
#==========================================================================================================
# E2E_CART001
#==========================================================================================================

@positive @e2e @E2E_CART001
Scenario: Add product and verify it in cart
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  Then the product "Sauce Labs Backpack" should be displayed in the cart
  And the price of "Sauce Labs Backpack" should be "$29.99"
  And the quantity of "Sauce Labs Backpack" should be 1


#==========================================================================================================
# E2E_CART002
#==========================================================================================================

@positive @e2e @E2E_CART002
Scenario: Add multiple products and remove one
  When the user adds "Sauce Labs Backpack" to the cart
  And the user adds "Sauce Labs Bike Light" to the cart
  And the user opens the shopping cart
  And the user removes "Sauce Labs Backpack" from the cart
  Then the cart should contain 1 product
  And the product "Sauce Labs Bike Light" should be displayed in the cart

#==========================================================================================================
# E2E_CART003
#==========================================================================================================

@positive @e2e @E2E_CART003
Scenario: Add product and proceed to checkout
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  And the user clicks Checkout
  Then the checkout page should be displayed

#==========================================================================================================
# E2E_CART004
#==========================================================================================================

@positive @e2e @E2E_CART004
Scenario: Add product continue shopping and add another product
  When the user adds "Sauce Labs Backpack" to the cart
  And the user opens the shopping cart
  And the user clicks Continue Shopping
  And the user adds "Sauce Labs Bike Light" to the cart
  And the user opens the shopping cart
  Then the cart should contain 2 products

#==========================================================================================================
# E2E_CART005
#==========================================================================================================

@positive @e2e @E2E_CART005
Scenario: Add all products and verify cart
  When the user adds all available products to the cart
  And the user opens the shopping cart
  Then the cart should contain 6 products
  And the cart badge should show 6



