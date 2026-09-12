@INV
Feature: SauceDemo Inventory Page

  Background:
    Given the user is logged in to SauceDemo
   
    
#############################################################################################################
# Positive Inventory Test Cases
#############################################################################################################
  
  @positive @INV001
  Scenario: Verify inventory page is displayed after successful login
    Then the Inventory page is displayed successfully    
    
    
  @positive @INV002
  Scenario: Verify inventory page title
    Then the inventory page title should be "Products"


  @positive @INV003
  Scenario: Verify inventory product list is displayed
    Then the inventory product list should be displayed


  @positive @INV004
  Scenario: Verify products are displayed
    Then products should be displayed on the inventory page
    

  @positive @INV005
  Scenario: Verify total number of products
    Then the inventory page should display 6 products    