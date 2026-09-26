@slider
Feature: Dynamic Product Slider

  Background:
    Given the user is logged in to SauceDemo
    When the user clicks the Menu button
    And  the user clicks the Dynamic Catalog option
    
    Then the Dynamic Catalog submenu should be displayed
    And the Dynamic Catalog should be expanded
     
    And the user clicks the Slider option
    Then the Slider page should be displayed 
     

  # ============================================================
  # SLIDER INITIALIZATION
  # ============================================================

  @SLD001 @positive @smoke
  Scenario: Verify dynamic product slider is displayed
    Then the dynamic product slider should be displayed

  @SLD002 @positive @smoke
  Scenario: Verify product card is displayed inside slider
    Then the dynamic product card should be displayed

  @SLD003 @positive @smoke
  Scenario: Verify product image is displayed
    Then the slider product image should be displayed

  @SLD004 @positive @smoke
  Scenario: Verify product name is displayed
    Then the slider product name should be displayed

  @SLD005 @positive @smoke
  Scenario: Verify product price is displayed
    Then the slider product price should be displayed

  @SLD006 @positive @smoke
  Scenario: Verify slider contains six navigation dots
    Then the slider should contain 6 navigation dots

  @SLD007 @positive @smoke
  Scenario: Verify exactly one slider dot is active
    Then exactly one slider navigation dot should be active

  @SLD008 @positive
  Scenario: Verify active dot has aria-current true
    Then the active slider dot should have aria-current "true"

  @SLD009 @positive
  Scenario: Verify inactive dots have aria-current false
    Then all inactive slider dots should have aria-current "false"


  # ============================================================
  # AUTOMATIC SLIDER ROTATION
  # ============================================================

  @SLD010 @positive @smoke @autoRotation
  Scenario: Verify slider automatically changes product
    Given the current slider product is recorded
    When the user waits for the slider rotation interval
    Then the slider product should change

  @SLD011 @positive @autoRotation
  Scenario: Verify slider image changes automatically
    Given the current slider product is recorded
    And the current slider image is recorded
    When the user waits for the slider rotation interval
    Then the slider image should change

  @SLD012 @positive @autoRotation
  Scenario: Verify slider product name changes automatically
    Given the current slider product name is recorded
    When the user waits for the slider rotation interval
    Then the slider product name should change

  @SLD013 @positive @autoRotation
  Scenario: Verify slider price changes automatically
    Given the current slider product is recorded
    And the current slider product price is recorded
    When the user waits for the slider rotation interval
    Then the slider price should change

  @SLD014 @positive @autoRotation
  Scenario: Verify complete product information changes together
    Given the current slider product information is recorded
    When the user waits for the slider rotation interval
    Then the slider image name and price should represent the new product

  @SLD015 @positive @autoRotation
  Scenario: Verify active dot changes after automatic rotation
    Given the current slider product is recorded
    And the current active slider dot is recorded
    When the user waits for the slider rotation interval
    Then the active slider dot should change

  @SLD016 @positive @smoke @autoRotation
  Scenario: Verify product and active dot remain synchronized
    Given the current slider product is recorded
    When the slider automatically changes the product
    Then the active dot should correspond to the displayed product

  @SLD017 @positive @autoRotation
  Scenario: Verify slider continues automatic rotation
    Given the initial slider product is recorded
    When the user waits for multiple slider rotation intervals
    Then the slider should display different products sequentially


  # ============================================================
  # PRODUCT DATA SYNCHRONIZATION
  # ============================================================

  @SLD018 @positive @dataValidation
  Scenario: Verify displayed image belongs to displayed product
    When the current slider product is captured
    Then the slider image should belong to the displayed product

  @SLD019 @positive @dataValidation
  Scenario: Verify displayed name belongs to displayed product
    When the current slider product is captured
    Then the displayed product name should match the product data

  @SLD020 @positive @dataValidation
  Scenario: Verify displayed price belongs to displayed product
    When the current slider product is captured
    Then the displayed price should match the product data

  @SLD021 @positive @dataValidation
  Scenario: Verify image name and price belong to same product
    When the current slider product information is captured
    Then the image name and price should belong to the same product

  @SLD022 @positive @dataValidation
  Scenario: Verify each slider product has unique product information
    When all slider products are captured
    Then each product should have valid product information

  @SLD023 @positive @dataValidation
  Scenario: Verify slider automatically changes product
    Given the initial slider product is recorded
    When the slider changes to another product
    Then the displayed product name should match the product data
    And the slider image should belong to the displayed product
    And the displayed price should match the product data


  # ============================================================
  # NAVIGATION DOTS
  # ============================================================

  @SLD024 @positive @navigation
  Scenario: Verify first navigation dot is displayed
    Then slider navigation dot 0 should be displayed

  @SLD025 @positive @navigation
  Scenario: Verify last navigation dot is displayed
    Then slider navigation dot 5 should be displayed

  @SLD026 @positive @navigation @smoke
  Scenario: Navigate to Sauce Labs Bike Light
    When the user clicks the "Sauce Labs Bike Light" slider dot
    Then the "Sauce Labs Bike Light" should be displayed
    And the corresponding dot should be active

  @SLD027 @positive @navigation
  Scenario: Navigate to Sauce Labs Bolt T-Shirt
    When the user clicks the "Sauce Labs Bolt T-Shirt" slider dot
    Then the "Sauce Labs Bolt T-Shirt" should be displayed
    And the corresponding dot should be active

  @SLD028 @positive @navigation
  Scenario: Navigate to Sauce Labs Onesie
    When the user clicks the "Sauce Labs Onesie" slider dot
    Then the "Sauce Labs Onesie" should be displayed
    And the corresponding dot should be active

  @SLD029 @positive @navigation
  Scenario: Navigate to Test.allTheThings() T-Shirt
    When the user clicks the "Test.allTheThings() T-Shirt (Red)" slider dot
    Then the "Test.allTheThings() T-Shirt (Red)" should be displayed
    And the corresponding dot should be active

  @SLD030 @positive @navigation
  Scenario: Navigate to Sauce Labs Backpack
    When the user clicks the "Sauce Labs Backpack" slider dot
    Then the "Sauce Labs Backpack" should be displayed
    And the corresponding dot should be active

  @SLD031 @positive @navigation
  Scenario: Navigate to Sauce Labs Fleece Jacket
    When the user clicks the "Sauce Labs Fleece Jacket" slider dot
    Then the "Sauce Labs Fleece Jacket" should be displayed
    And the corresponding dot should be active


  # ============================================================
  # DOT AND PRODUCT SYNCHRONIZATION
  # ============================================================

  @SLD032 @positive @synchronization
  Scenario: Verify clicking dot changes product
    Given the slider is displaying Sauce Labs Onesie
    When the user clicks the Sauce Labs Backpack slider dot
    Then Sauce Labs Backpack should be displayed

  @SLD033 @positive @synchronization
  Scenario: Verify clicking dot changes image
    Given the slider is displaying Sauce Labs Onesie
    When the user clicks the Sauce Labs Backpack slider dot
    Then the Backpack image should be displayed

  @SLD034 @positive @synchronization
  Scenario: Verify clicking dot changes price
    Given the slider is displaying Sauce Labs Onesie
    When the user clicks the Sauce Labs Backpack slider dot
    Then the Backpack price should be displayed

  @SLD035 @positive @synchronization
  Scenario: Verify clicked dot becomes active
    When the user clicks the Sauce Labs Backpack slider dot
    Then the Sauce Labs Backpack dot should have aria-current "true"

  @SLD036 @positive @synchronization
  Scenario: Verify previously active dot becomes inactive
    Given the Sauce Labs Onesie dot is active
    When the user clicks the Sauce Labs Backpack slider dot
    Then the Sauce Labs Onesie dot should have aria-current "false"

  @SLD037 @positive @synchronization
  Scenario: Verify only one dot remains active
    When the user clicks different slider dots
    Then only one slider dot should have aria-current "true"


  # ============================================================
  # COMPLETE PRODUCT ROTATION
  # ============================================================

  @SLD038 @positive @rotation @regression
  Scenario: Verify slider displays all six products
    When the user waits for the slider to rotate through all products
    Then all 6 slider products should be displayed

  @SLD039 @positive @rotation @regression
  Scenario: Verify slider does not skip products
    When the slider automatically rotates
    Then each expected product should appear in the rotation

  @SLD040 @positive @rotation @regression
  Scenario: Verify slider does not display duplicate products consecutively
    When the slider automatically rotates
    Then consecutive slider products should not be identical

  @SLD041 @positive @rotation @regression
  Scenario: Verify slider returns to first product after last product
    Given the slider reaches the last product
    When the next automatic rotation occurs
    Then the first slider product should be displayed


  # ============================================================
  # BOUNDARY NAVIGATION
  # ============================================================

  @SLD042 @positive @boundary
  Scenario: Verify first slider product
    When the user selects the first slider dot
    Then Sauce Labs Bike Light should be displayed

  @SLD043 @positive @boundary
  Scenario: Verify last slider product
    When the user selects the last slider dot
    Then Sauce Labs Fleece Jacket should be displayed

  @SLD044 @positive @boundary
  Scenario: Verify slider wraps from last product to first product
    Given the last slider product is displayed
    When the next automatic rotation occurs
    Then Sauce Labs Bike Light should be displayed

  @SLD045 @positive @boundary
  Scenario: Verify slider wraps correctly after complete cycle
    Given the slider has displayed all products
    When the next rotation occurs
    Then the first product should be displayed


  # ============================================================
  # PRICE VALIDATION
  # ============================================================

  @SLD046 @positive @price
  Scenario: Verify slider price starts with dollar symbol
    Then the slider price should start with "$"

  @SLD047 @positive @price
  Scenario: Verify slider price has valid currency format
    Then the slider price should match the expected currency format

  @SLD048 @positive @price
  Scenario: Verify slider price contains two decimal places
    Then the slider price should contain two decimal places

  @SLD049 @positive @price
  Scenario: Verify slider price is not empty
    Then the slider price should not be empty

  @SLD050 @positive @price
  Scenario: Verify slider price changes with product
    Given the current product price is recorded
    When the slider changes product
    Then the displayed price should correspond to the new product


  # ============================================================
  # PRODUCT NAME VALIDATION
  # ============================================================

  @SLD051 @positive @productName
  Scenario: Verify product name is not empty
    Then the slider product name should not be empty

  @SLD052 @positive @productName
  Scenario: Verify product name is visible
    Then the slider product name should be visible

  @SLD053 @positive @productName
  Scenario: Verify product name does not contain unexpected whitespace
    Then the slider product name should not contain leading or trailing whitespace

  @SLD054 @positive @productName
  Scenario: Verify product name changes with slider
    Given the current product name is recorded
    When the slider changes product
    Then the displayed product name should change accordingly


  # ============================================================
  # IMAGE VALIDATION
  # ============================================================

  @SLD055 @positive @image
  Scenario: Verify slider image is displayed
    Then the slider product image should be displayed

  @SLD056 @positive @image @accessibility
  Scenario: Verify slider image has alt text
    Then the slider product image should have a non-empty alt attribute

  @SLD057 @positive @image @accessibility
  Scenario: Verify image alt text matches product name
    Then the slider image alt text should match the product name

  @SLD058 @positive @image
  Scenario: Verify image changes when product changes
    Given the current slider image is recorded
    When the slider changes product
    Then the slider image should change

  @SLD059 @positive @image
  Scenario: Verify image source is not empty
    Then the slider image src should not be empty

  @SLD060 @positive @image
  Scenario: Verify image source changes with product
    Given the current image source is recorded
    When the slider changes product
    Then the image source should correspond to the new product


  # ============================================================
  # ACCESSIBILITY
  # ============================================================

  @SLD061 @positive @accessibility
  Scenario: Verify every navigation dot has aria-label
    Then every slider navigation dot should have an aria-label

  @SLD062 @positive @accessibility
  Scenario: Verify aria-label identifies product
    When the slider navigation dots are inspected
    Then each aria-label should identify its corresponding product

  @SLD063 @positive @accessibility
  Scenario: Verify active dot uses aria-current true
    Then the active slider dot should have aria-current "true"

  @SLD064 @positive @accessibility
  Scenario: Verify inactive dots use aria-current false
    Then all inactive slider dots should have aria-current "false"

  @SLD065 @positive @accessibility
  Scenario: Verify only one dot has aria-current true
    Then only one navigation dot should have aria-current "true"

  @SLD066 @positive @accessibility
  Scenario: Verify image has meaningful alt text
    Then the slider image should have meaningful alt text


  # ============================================================
  # NEGATIVE / ROBUSTNESS
  # ============================================================

  @SLD067 @negative
  Scenario: Verify slider does not display empty product name
    Then the slider product name should not be empty

  @SLD068 @negative
  Scenario: Verify slider does not display empty price
    Then the slider product price should not be empty

  @SLD069 @negative
  Scenario: Verify slider does not display invalid price format
    Then the slider price should not contain invalid currency format

  @SLD070 @negative
  Scenario: Verify slider does not display broken image
    Then the slider image should load successfully

  @SLD071 @negative
  Scenario: Verify slider does not have multiple active dots
    Then only one navigation dot should be active

  @SLD072 @negative
  Scenario: Verify slider does not have zero active dots
    Then one navigation dot should always be active

  @SLD073 @negative
  Scenario: Verify slider does not show stale product information
    When the slider changes product
    Then the previous product information should not remain visible

  @SLD074 @negative
  Scenario: Verify slider does not show mismatched product information
    When the slider product changes
    Then the image name and price should represent the same product

  @SLD075 @negative
  Scenario: Verify invalid slider dot index does not crash application
    When an invalid slider dot index is requested
    Then the application should not crash

  @SLD076 @negative @stress
  Scenario: Verify slider remains stable after rapid dot clicks
    When the user rapidly clicks different slider dots
    Then the slider should display a valid product
    And only one dot should be active


  # ============================================================
  # RAPID USER INTERACTION
  # ============================================================

  @SLD077 @positive @stress
  Scenario: Rapidly click multiple slider dots
    When the user rapidly clicks different slider dots
    Then the slider should remain stable
    And a valid product should be displayed

  @SLD078 @positive @stress
  Scenario: Click navigation dot during automatic rotation
    Given automatic slider rotation is active
    When the user clicks a navigation dot during rotation
    Then the selected product should be displayed correctly

  @SLD079 @positive @stress
  Scenario: Verify active dot after rapid navigation
    When the user rapidly changes slider products
    Then only the currently displayed product dot should be active


  # ============================================================
  # PAGE REFRESH / NAVIGATION
  # ============================================================

  @SLD080 @positive @regression
  Scenario: Verify slider is displayed after page refresh
    When the user refreshes the page
    Then the dynamic product slider should be displayed

  @SLD081 @positive @regression
  Scenario: Verify slider starts with valid product after refresh
    When the user refreshes the page
    Then a valid slider product should be displayed

  @SLD082 @positive @regression
  Scenario: Verify slider contains navigation dots after refresh
    When the user refreshes the slider menu page
    Then 6 slider navigation dots should be displayed

  @SLD083 @positive @regression
  Scenario: Verify automatic rotation works after refresh
    When the user refreshes the inventory page while on slider menu
    And the user waits for the slider rotation interval
    Then the slider product should change


  # ============================================================
  # END-TO-END / COMPLETE SLIDER VALIDATION
  # ============================================================

  @SLD084 @positive @smoke @regression @endToEnd
  Scenario: Verify complete dynamic slider functionality
    Given the dynamic product slider is displayed
    When the user captures the current slider product
    And the user waits for the automatic slider rotation
    Then the slider product should change
    And the slider image should represent the displayed product
    And the slider name should represent the displayed product
    And the slider price should represent the displayed product
    And exactly one navigation dot should be active
    And the active dot should correspond to the displayed product