
@lazyLoad
Feature: SauceDemo Dynamic Catalog Lazy Load

  As a SauceDemo user
  I want to browse products using lazy loading
  So that additional products are loaded as I scroll through the catalog

  
  Background:
    Given the user is logged in to SauceDemo
    When the user clicks the Menu button
    And  the user clicks the Dynamic Catalog option
    
    Then the Dynamic Catalog submenu should be displayed
    And the Dynamic Catalog should be expanded
     
    When the user clicks the Lazy Load option
    Then the Lazy Load page should be displayed
    
    

  # =========================================================
  # PAGE NAVIGATION
  # =========================================================

  @LL001 @positive @smoke
  Scenario: Open the dynamic catalog lazy-load page 
    When the user clicks the Lazy Load option
    Then the Lazy Load page should be displayed
    And the page URL should contain "dynamic-catalog-lazy-load.html"


  @LL002 @positive
  Scenario: Verify lazy-load catalog page title
    Then the page title should be displayed
    And the page title should not be empty


  @LL003 @positive
  Scenario: Verify catalog container is displayed
    Then the catalog container should be displayed


#======================================================================================================
# INITIAL PRODUCT LOADING
#=======================================================================================================

  @LL004 @positive @smoke
  Scenario: Verify initial products are displayed
    Then at least one product should be displayed


  @LL005 @positive
  Scenario: Verify initial loaded product count
    When the user checks the loaded product count
    Then the loaded product count should be greater than zero


  @LL006 @positive
  Scenario: Verify loaded products have names
    Then every loaded product should have a product name


  @LL007 @positive
  Scenario: Verify loaded products have prices
    Then every loaded product should have a price


  @LL008 @positive
  Scenario: Verify loaded products have images
    Then every loaded product should display an image


  # =========================================================
  # LAZY LOADING
  # =========================================================

  @LL009 @positive @smoke
  Scenario: Load additional products when the user scrolls 
    Given the user records the initial product count
    When the user scrolls down the catalog
    And waits for lazy loading to complete
    Then additional products should be loaded


  @LL010 @positive
  Scenario: Verify product count increases after scrolling
    Given the user records the initial product count
    And the user records the initial product count
    When the user scrolls toward the bottom of the page
    And lazy loading completes
    Then the current product count should be greater than the initial product count


  @LL011 @positive
  Scenario: Load products through multiple scrolling operations
    When the user repeatedly scrolls down the page
    Then additional products should continue loading
    And already loaded products should remain available


  @LL012 @positive @regression
  Scenario: Load all available products
    When the user continuously scrolls until no additional products are loaded
    Then all available products should be displayed


  @LL013 @positive
  Scenario: Stop lazy loading at the end of the catalog
    When the user scrolls until the end of the catalog
    And the user performs another scroll operation
    Then no additional duplicate products should be loaded


#=======================================================================================================
# DUPLICATE PRODUCT VALIDATION
#========================================================================================================

  @LL014 @positive @regression
  Scenario: Verify lazy loading does not create duplicate products
    When the user scrolls through the entire catalog
    Then no product should appear more than once


  @LL015 @positive
  Scenario: Verify previously loaded products remain available
    Given the user has loaded additional products
    When the user scrolls back to the top
    Then previously loaded products should still be displayed


  @LL016 @positive
  Scenario: Verify product count does not unexpectedly decrease
    Given  the user records the loaded product count
    When the user scrolls down and then back up
    Then the loaded product count should not unexpectedly decrease


#=====================================================================================================
# PRODUCT INTERACTION
#====================================================================================================

  @LL017 @positive @smoke
 Scenario: Verify a lazily loaded target product
    Given the user is on the Dynamic Catalog Lazy Load page
    When the user scrolls until product "Sauce Labs Fleece Jacket (M)" is loaded
    Then the target product should be displayed
  
  
  
  @LL018 @positive
  Scenario: Add a product after scrolling back to it
     When the user scrolls until product "Sauce Labs Fleece Jacket (M)" is loaded
    And the user scrolls back to previously loaded product "Sauce Labs Fleece Jacket (M)"
    And the user scrolls back to a previously loaded product
  



#========================================================================================================
# SCROLLING
#========================================================================================================

  @LL019 @positive
  Scenario: Scroll to the bottom of the catalog
    When the user scrolls to the bottom
    Then the browser should reach the bottom of the catalog


  @LL020 @positive
  Scenario: Scroll back to the top after loading products
    Given  the user has loaded additional products
    When the user scrolls back to the top
    Then the top of the catalog should be displayed


  @LL021 @positive
  Scenario: Repeatedly scroll up and down through the catalog
    When the user repeatedly scrolls up and down
    Then the catalog should remain stable
    And products should not disappear unexpectedly
    And products should not be duplicated


#=======================================================================================================
# REFRESH
#======================================================================================================

  @LL022 @positive
  Scenario: Refresh the page after lazy loading products
    Given  the user has loaded additional products
    When the user refreshes the page
    Then the dynamic catalog page should load successfully


  @LL023 @positive
  Scenario: Verify catalog after page refresh
    Given the user has loaded additional products
    When the user refreshes the page
    Then the catalog should be displayed
    And the initial product loading behavior should work correctly


#======================================================================================================
# PRODUCT DATA INTEGRITY
#======================================================================================================

  @LL024 @positive
  Scenario: Verify product names after lazy loading
    When the user loads all available products
    Then each product should have a non-empty name
    And product names should remain unchanged while scrolling


  @LL025  @positive
  Scenario: Verify product prices after lazy loading
    When the user loads all available products
    Then each product should have a valid price
    And product prices should remain unchanged while scrolling




#=====================================================================================================
# NEGATIVE TEST CASES
#=====================================================================================================

  @LL026 @negative
  Scenario: Attempt to access a product that is not currently loaded
    Given the user is at the top of the dynamic catalog
    When the user attempts to access a product that is not currently loaded
    Then the product should not be found in the currently loaded DOM


  @LL027  @negative
  Scenario: Attempt to access a non-existing product
    When the user searches for product "Invalid Product"
    Then the product should not be found


  @LL028  @negative
  Scenario: Attempt to add a non-existing product to the cart
    Given the user is on the dynamic catalog lazy-load page
    When the user attempts to add "Invalid Product" to the cart
    Then the product should not be added to the cart


  @LL029 @negative
  Scenario: Attempt to interact with an unloaded product
    Given the user is at the top of the dynamic catalog
    When the target product has not yet been loaded
    Then the product action should not be available


  @LL030 @negative
  Scenario: Continue scrolling after reaching the end of the catalog
    Given the user has reached the end of the catalog
    When the user repeatedly scrolls down
    Then no additional duplicate products should appear


  @LL031  @negative
  Scenario: Rapidly scroll through the catalog
    When the user rapidly scrolls from top to bottom
    Then the application should remain stable
    And loaded products should not be duplicated
    And loaded products should not disappear unexpectedly


#======================================================================================================
# BOUNDARY TEST CASES
#=======================================================================================================

  @LL032 @boundary
  Scenario: Interact with the first available product
    When the user interacts with the first product
    Then the product interaction should work successfully


  @LL033 @boundary
  Scenario: Interact with the last available product
    When the user scrolls to the end of the catalog
    And the user interacts with the last product
    Then the product interaction should work successfully


  @LL034  @boundary
  Scenario: Handle a catalog containing one loaded product
    Given the dynamic catalog initially contains one loaded product
    When the user scrolls down
    Then the lazy-load mechanism should handle the catalog correctly


  @LL035  @boundary @regression
  Scenario: Load the complete product catalog
    Given the user is on the dynamic catalog lazy-load page
    When the user continuously scrolls until the end
    Then all available products should be loaded
    And no product should be duplicated



#======================================================================================================
# UI VALIDATION
#=======================================================================================================

  @LL036 @ui
  Scenario: Verify header on lazy-load catalog page
    Then the header should be displayed


  @LL037  @ui
  Scenario: Verify footer after scrolling to the bottom
    When the user scrolls to the bottom
    Then the footer should be displayed


  @LL038 @ui
  Scenario: Verify product cards are displayed correctly
    Given the user is on the dynamic catalog lazy-load page
    Then each loaded product should be displayed as a product card


#======================================================================================================
# IMAGE VALIDATION
#=======================================================================================================

  @LL039  @ui
  Scenario: Verify product images load during lazy loading
    When the user scrolls through the catalog
    Then each loaded product image should be displayed


  @LL040  @ui
  Scenario: Verify loaded product images have valid sources
    When products are lazy loaded
    Then every product image should have a valid source


#======================================================================================================
# PERFORMANCE AND SYNCHRONIZATION
#=======================================================================================================

  @LL041 @performance
  Scenario: Verify page remains responsive during lazy loading
    When the user repeatedly scrolls through the catalog
    Then the page should remain responsive


  @LL042 @negative @performance
  Scenario: Verify lazy loading does not continue indefinitely
    When the user scrolls continuously to the bottom
    Then lazy loading should eventually stop
    And the page should not continuously create products


  @LL043 @performance
  Scenario: Verify synchronization during lazy loading
    When the user scrolls to trigger lazy loading
    Then the test should wait until newly loaded products are available
    And the test should not rely on unnecessary fixed delays


#=======================================================================================================
# RESPONSIVE TESTING
#=======================================================================================================

  @LL044 @responsive
  Scenario: Verify lazy loading with a small browser viewport
    When the user scrolls through the catalog
    Then products should continue loading correctly


  @LL045 @responsive
  Scenario: Verify lazy loading with a large browser viewport
    When the user scrolls through the catalog
    Then products should load correctly

