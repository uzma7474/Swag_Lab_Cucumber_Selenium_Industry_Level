@PDF @Checkout
Feature: Generate and validate the Swag Labs order receipt PDF

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
    

    
#=======================================================================================================
# PDF Generator Test Cases
#=======================================================================================================

@CHK_PDF
@CHK_PDF001
Scenario: Generate PDF order from checkout complete page

    Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    And the downloaded PDF should not be empty    
    
#=======================================================================================================
# PDF Content Validation
#========================================================================================================

  # --------------------------------------------------
  # POSITIVE TEST CASES
  # --------------------------------------------------

  @PDF001 @Smoke
  Scenario: Verify checkout complete page is displayed
    Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed

  @PDF002 @Smoke
  Scenario: Verify Generate PDF Order button is displayed
    Then the Generate PDF Order button is displayed
    And the Generate PDF Order button text should be "Generate PDF order"

  @PDF003 @Smoke
  Scenario: Download order receipt PDF successfully
    Given the previous PDF files are cleaned up
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully

  @PDF004
  Scenario: Verify downloaded PDF is not empty
    When the user clicks the Generate PDF Order button
    Then the downloaded PDF should not be empty

  @PDF005
  Scenario: Verify downloaded file is a valid PDF
     Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    And the downloaded PDF should not be empty 
    Then the downloaded file should have a PDF extension
    And the downloaded file should have a valid PDF signature
    And the downloaded file should be readable by a PDF parser

  @PDF006
  Scenario: Verify receipt title and sections
    Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    And the downloaded PDF should not be empty
    Then the order PDF should contain "Swag Labs"
    And the order PDF should contain "Order Receipt"
    And the order PDF should contain "ORDER DETAILS"
    And the order PDF should contain "SHIP TO"
    And the order PDF should contain "ITEMS"

  @PDF007
  Scenario: Verify shipping information in the receipt
    Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    And the downloaded PDF should not be empty
    Then the order PDF should contain the customer name "John Doe"
    And the order PDF should contain the postal code "411042"

  @PDF008
  Scenario: Verify purchased products appear in the receipt
    Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    And the downloaded PDF should not be empty
    Then the order PDF should contain product "Sauce Labs Backpack"
    And the order PDF should contain product "Sauce Labs Bike Light"
    And the order PDF should contain product "Sauce Labs Bolt T-Shirt"

  @PDF009
  Scenario Outline: Verify product prices in the receipt
    Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    And the downloaded PDF should not be empty
    Then the order PDF should show product "<product>" with price "<price>"

    Examples:
      | product                 | price  |
      | Sauce Labs Backpack     | $29.99 |
      | Sauce Labs Bike Light   | $9.99  |
      | Sauce Labs Bolt T-Shirt | $15.99 |

  @PDF010
  Scenario: Verify order subtotal, tax, and total
    Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    And the downloaded PDF should not be empty
    Then the order PDF should show item total "$55.97"
    And the order PDF should show tax "$4.48"
    And the order PDF should show grand total "$60.45"

  @PDF011
  Scenario: Verify grand total calculation
    Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    Then the order PDF subtotal plus tax should equal the grand total

  
  @PDF012
  Scenario: Verify receipt has at least one page
    Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    Then the order PDF should contain at least 1 page

  @PDF013
  Scenario: Generate a fresh PDF for the current order
    Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    And the downloaded PDF should belong to the current order

  @PDF015
  Scenario: Verify download directory is created when missing
     Given the user is on the checkout complete page
    And the Generate PDF Order button is displayed
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    When the user clicks the Generate PDF Order button
    Then the order PDF should be downloaded successfully
    Given the PDF download directory does not exist
    When the PDF download directory is initialized
    Then the PDF download directory should be created

  # --------------------------------------------------
  # NEGATIVE TEST CASES
  # --------------------------------------------------


 