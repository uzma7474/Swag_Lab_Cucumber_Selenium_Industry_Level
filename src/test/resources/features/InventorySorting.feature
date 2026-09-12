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
      
 
 