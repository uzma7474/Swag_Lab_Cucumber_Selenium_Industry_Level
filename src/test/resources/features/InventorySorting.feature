@ProductSorting
Feature: Inventory Product Sorting

  Background:
    Given the user is logged in to SauceDemo
    And the inventory page is displayed


  @positive @INV029
  Scenario: Sort products by name ascending
    When the user sorts products by "Name (A to Z)"
    Then the selected sort option should be "Name (A to Z)"


  @positive @INV030
  Scenario: Sort products by name descending
    When the user sorts products by "Name (Z to A)"
    Then the selected sort option should be "Name (Z to A)"


  @positive @INV031
  Scenario: Sort products by price low to high
    When the user sorts products by "Price (low to high)"
    Then the selected sort option should be "Price (low to high)"


  @positive @INV032
  Scenario: Sort products by price high to low
    When the user sorts products by "Price (high to low)"
    Then the selected sort option should be "Price (high to low)"


  @positive @INV033
  Scenario Outline: Verify inventory sorting options
    When the user sorts products by "<sortOption>"
    Then the selected sort option should be "<sortOption>"

    Examples:
      | sortOption          |
      | Name (A to Z)       |
      | Name (Z to A)       |
      | Price (low to high) |
      | Price (high to low) |
      
 
 # ============================================================
  # CASE INSENSITIVITY
  # ============================================================

  @positive @inventory @INV034
  Scenario: Verify product search validation is case insensitive
    Then the product "sauce labs backpack" should be displayed


  @positive @inventory @INV035
  Scenario: Verify product validation with leading and trailing spaces
    Then the product "  Sauce Labs Backpack  " should be displayed



  # ============================================================
  # MULTIPLE PRODUCT FLOW
  # ============================================================

  @positive @inventory @INV036
  Scenario: Add multiple products using business flow
    When the user adds "Sauce Labs Backpack" and "Sauce Labs Bike Light" and opens the cart
    Then the shopping cart page should be displayed


  @positive @inventory @INV037
  Scenario: Add first product and verify cart count
    When the user adds the first product to the cart
    Then the cart badge should be displayed
    And the cart badge should show 1



 # ============================================================
  # INVENTORY PAGE REFRESH
  # ============================================================

  @positive @inventory @INV038
  Scenario: Refresh inventory page
    When the user refreshes the inventory page
    Then the inventory page should be displayed


  @positive @inventory @INV039
  Scenario: Verify products remain displayed after page refresh
    When the user refreshes the inventory page
    Then products should be displayed on the inventory page
 
 