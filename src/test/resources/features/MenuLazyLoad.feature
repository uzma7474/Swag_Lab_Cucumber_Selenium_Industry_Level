
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


  @LL011 @positive
  Scenario: Verify product count increases after scrolling
    Given the user is on the dynamic catalog lazy-load page
    And the user records the initial product count
    When the user scrolls toward the bottom of the page
    And lazy loading completes
    Then the current product count should be greater than the initial product count


  @LL012 @positive
  Scenario: Load products through multiple scrolling operations
    Given the user is on the dynamic catalog lazy-load page
    When the user repeatedly scrolls down the page
    Then additional products should continue loading
    And already loaded products should remain available


  @LL013 @positive @regression
  Scenario: Load all available products
    Given the user is on the dynamic catalog lazy-load page
    When the user continuously scrolls until no additional products are loaded
    Then all available products should be displayed


  @LL014 @positive
  Scenario: Stop lazy loading at the end of the catalog
    Given the user is on the dynamic catalog lazy-load page
    When the user scrolls until the end of the catalog
    And the user performs another scroll operation
    Then no additional duplicate products should be loaded


  # =========================================================
  # DUPLICATE PRODUCT VALIDATION
  # =========================================================

  @LL015 @positive @regression
  Scenario: Verify lazy loading does not create duplicate products
    Given the user is on the dynamic catalog lazy-load page
    When the user scrolls through the entire catalog
    Then no product should appear more than once


  @LL016 @positive
  Scenario: Verify previously loaded products remain available
    Given the user is on the dynamic catalog lazy-load page
    And the user has loaded additional products
    When the user scrolls back to the top
    Then previously loaded products should still be displayed


  @LL017 @positive
  Scenario: Verify product count does not unexpectedly decrease
    Given the user is on the dynamic catalog lazy-load page
    And the user records the loaded product count
    When the user scrolls down and then back up
    Then the loaded product count should not unexpectedly decrease


  # =========================================================
  # PRODUCT INTERACTION
  # =========================================================

  @LL018 @positive @smoke
  Scenario: Add a lazily loaded product to the cart
    Given the user is on the dynamic catalog lazy-load page
    When the user scrolls until a target product is loaded
    And the user adds the target product to the cart
    Then the cart badge should be displayed
    And the cart count should be 1


  @LL019 @positive
  Scenario: Add multiple lazily loaded products to the cart
    Given the user is on the dynamic catalog lazy-load page
    When the user loads multiple products
    And the user adds multiple products to the cart
    Then the cart badge should show the correct number of products


  @LL020 @positive
  Scenario: Add a product after scrolling back to it
    Given the user is on the dynamic catalog lazy-load page
    When the user scrolls down the catalog
    And the user scrolls back to a previously loaded product
    And the user adds that product to the cart
    Then the product should be added successfully


  @LL021 @positive
  Scenario: Verify product action button
    Given the user is on the dynamic catalog lazy-load page
    When a product is loaded
    Then its action button should be displayed
    And its action button should be clickable


  # =========================================================
  # CART INTEGRATION
  # =========================================================

  @LL022 @positive
  Scenario: Verify cart badge after adding one lazy-loaded product
    Given the user is on the dynamic catalog lazy-load page
    When the user adds a product to the cart
    Then the cart badge should display "1"


  @LL023 @positive
  Scenario: Verify cart badge after adding multiple products
    Given the user is on the dynamic catalog lazy-load page
    When the user adds 3 loaded products to the cart
    Then the cart badge should display "3"


  @LL024 @positive
  Scenario: Navigate from lazy-load catalog to cart
    Given the user is on the dynamic catalog lazy-load page
    And the user has added a product to the cart
    When the user clicks the shopping cart
    Then the cart page should be displayed


  # =========================================================
  # SCROLLING
  # =========================================================

  @LL025 @positive
  Scenario: Scroll to the bottom of the catalog
    Given the user is on the dynamic catalog lazy-load page
    When the user scrolls to the bottom
    Then the browser should reach the bottom of the catalog


  @LL026 @positive
  Scenario: Scroll back to the top after loading products
    Given the user is on the dynamic catalog lazy-load page
    And the user has loaded additional products
    When the user scrolls back to the top
    Then the top of the catalog should be displayed


  @LL027 @positive
  Scenario: Repeatedly scroll up and down through the catalog
    Given the user is on the dynamic catalog lazy-load page
    When the user repeatedly scrolls up and down
    Then the catalog should remain stable
    And products should not disappear unexpectedly
    And products should not be duplicated


  # =========================================================
  # REFRESH
  # =========================================================

  @LL028 @positive
  Scenario: Refresh the page after lazy loading products
    Given the user is on the dynamic catalog lazy-load page
    And the user has loaded additional products
    When the user refreshes the page
    Then the dynamic catalog page should load successfully


  @LL029 @positive
  Scenario: Verify catalog after page refresh
    Given the user is on the dynamic catalog lazy-load page
    And the user has loaded additional products
    When the user refreshes the page
    Then the catalog should be displayed
    And the initial product loading behavior should work correctly


  # =========================================================
  # PRODUCT DATA INTEGRITY
  # =========================================================

  @LL030 @positive
  Scenario: Verify product names after lazy loading
    Given the user is on the dynamic catalog lazy-load page
    When the user loads all available products
    Then each product should have a non-empty name
    And product names should remain unchanged while scrolling


  @LL031 @positive
  Scenario: Verify product prices after lazy loading
    Given the user is on the dynamic catalog lazy-load page
    When the user loads all available products
    Then each product should have a valid price
    And product prices should remain unchanged while scrolling


  @LL032 @positive
  Scenario: Verify product descriptions after lazy loading
    Given the user is on the dynamic catalog lazy-load page
    When the user loads all available products
    Then product descriptions should remain associated with the correct products


  # =========================================================
  # NEGATIVE TEST CASES
  # =========================================================

  @LL033 @negative
  Scenario: Attempt to access a product that is not currently loaded
    Given the user is at the top of the dynamic catalog
    When the user attempts to access a product that is not currently loaded
    Then the product should not be found in the currently loaded DOM


  @LL034 @negative
  Scenario: Attempt to access a non-existing product
    Given the user is on the dynamic catalog lazy-load page
    When the user searches for product "Invalid Product"
    Then the product should not be found


  @LL035 @negative
  Scenario: Attempt to add a non-existing product to the cart
    Given the user is on the dynamic catalog lazy-load page
    When the user attempts to add "Invalid Product" to the cart
    Then the product should not be added to the cart


  @LL036 @negative
  Scenario: Attempt to interact with an unloaded product
    Given the user is at the top of the dynamic catalog
    When the target product has not yet been loaded
    Then the product action should not be available


  @LL037 @negative
  Scenario: Repeatedly click the add-to-cart button
    Given the user is on the dynamic catalog lazy-load page
    When the user rapidly clicks the add-to-cart button for the same product
    Then the cart should not contain unintended duplicate entries


  @LL038 @negative
  Scenario: Continue scrolling after reaching the end of the catalog
    Given the user is on the dynamic catalog lazy-load page
    And the user has reached the end of the catalog
    When the user repeatedly scrolls down
    Then no additional duplicate products should appear


  @LL039 @negative
  Scenario: Rapidly scroll through the catalog
    Given the user is on the dynamic catalog lazy-load page
    When the user rapidly scrolls from top to bottom
    Then the application should remain stable
    And loaded products should not be duplicated
    And loaded products should not disappear unexpectedly


  # =========================================================
  # BOUNDARY TEST CASES
  # =========================================================

  @LL040 @boundary
  Scenario: Interact with the first available product
    Given the user is on the dynamic catalog lazy-load page
    When the user interacts with the first product
    Then the product interaction should work successfully


  @LL041 @boundary
  Scenario: Interact with the last available product
    Given the user is on the dynamic catalog lazy-load page
    When the user scrolls to the end of the catalog
    And the user interacts with the last product
    Then the product interaction should work successfully


  @LL042 @boundary
  Scenario: Handle a catalog containing one loaded product
    Given the dynamic catalog initially contains one loaded product
    When the user scrolls down
    Then the lazy-load mechanism should handle the catalog correctly


  @LL043 @boundary @regression
  Scenario: Load the complete product catalog
    Given the user is on the dynamic catalog lazy-load page
    When the user continuously scrolls until the end
    Then all available products should be loaded
    And no product should be duplicated


  # =========================================================
  # BROWSER NAVIGATION
  # =========================================================

  @LL044 @positive
  Scenario: Navigate back from the lazy-load catalog
    Given the user is on the dynamic catalog lazy-load page
    When the user navigates back using the browser back button
    Then the previous page should be displayed


  @LL045 @positive
  Scenario: Navigate forward to the lazy-load catalog
    Given the user has navigated away from the lazy-load catalog
    When the user uses the browser forward button
    Then the dynamic catalog page should be displayed


  @LL046 @positive
  Scenario: Open lazy-load catalog using its direct URL
    Given the user has a valid SauceDemo session
    When the user navigates directly to the lazy-load catalog URL
    Then the dynamic catalog page should be displayed


  # =========================================================
  # AUTHENTICATION NEGATIVE CASES
  # =========================================================

  @LL047 @negative
  Scenario: Access lazy-load catalog without authentication
    Given the user is not logged into SauceDemo
    When the user navigates directly to the lazy-load catalog page
    Then the application should handle the unauthenticated request according to its access rules


  @LL048 @negative
  Scenario: Access lazy-load catalog after logout
    Given the user is logged into SauceDemo
    And the user logs out
    When the user navigates to the lazy-load catalog URL
    Then the application should handle the unauthenticated request according to its access rules


  # =========================================================
  # UI VALIDATION
  # =========================================================

  @LL049 @ui
  Scenario: Verify header on lazy-load catalog page
    Given the user is on the dynamic catalog lazy-load page
    Then the header should be displayed


  @LL050 @ui
  Scenario: Verify footer after scrolling to the bottom
    Given the user is on the dynamic catalog lazy-load page
    When the user scrolls to the bottom
    Then the footer should be displayed


  @LL051 @ui
  Scenario: Verify product cards are displayed correctly
    Given the user is on the dynamic catalog lazy-load page
    Then each loaded product should be displayed as a product card


  # =========================================================
  # IMAGE VALIDATION
  # =========================================================

  @LL052 @ui
  Scenario: Verify product images load during lazy loading
    Given the user is on the dynamic catalog lazy-load page
    When the user scrolls through the catalog
    Then each loaded product image should be displayed


  @LL053 @ui
  Scenario: Verify loaded product images have valid sources
    Given the user is on the dynamic catalog lazy-load page
    When products are lazy loaded
    Then every product image should have a valid source


  # =========================================================
  # PERFORMANCE AND SYNCHRONIZATION
  # =========================================================

  @LL054 @performance
  Scenario: Verify page remains responsive during lazy loading
    Given the user is on the dynamic catalog lazy-load page
    When the user repeatedly scrolls through the catalog
    Then the page should remain responsive


  @LL055 @negative @performance
  Scenario: Verify lazy loading does not continue indefinitely
    Given the user is on the dynamic catalog lazy-load page
    When the user scrolls continuously to the bottom
    Then lazy loading should eventually stop
    And the page should not continuously create products


  @LL056 @performance
  Scenario: Verify synchronization during lazy loading
    Given the user is on the dynamic catalog lazy-load page
    When the user scrolls to trigger lazy loading
    Then the test should wait until newly loaded products are available
    And the test should not rely on unnecessary fixed delays


  # =========================================================
  # CROSS BROWSER
  # =========================================================

  @LL057 @browser @chrome
  Scenario: Verify lazy loading in Chrome
    Given the user opens the dynamic catalog in Chrome
    When the user scrolls through the catalog
    Then products should load correctly


  @LL058 @browser @firefox
  Scenario: Verify lazy loading in Firefox
    Given the user opens the dynamic catalog in Firefox
    When the user scrolls through the catalog
    Then products should load correctly


  @LL059 @browser @edge
  Scenario: Verify lazy loading in Edge
    Given the user opens the dynamic catalog in Edge
    When the user scrolls through the catalog
    Then products should load correctly


  # =========================================================
  # RESPONSIVE TESTING
  # =========================================================

  @LL060 @responsive
  Scenario: Verify lazy loading with a small browser viewport
    Given the browser has a small viewport
    And the user is on the dynamic catalog lazy-load page
    When the user scrolls through the catalog
    Then products should continue loading correctly


  @LL061 @responsive
  Scenario: Verify lazy loading with a large browser viewport
    Given the browser has a large viewport
    And the user is on the dynamic catalog lazy-load page
    When the user scrolls through the catalog
    Then products should load correctly

