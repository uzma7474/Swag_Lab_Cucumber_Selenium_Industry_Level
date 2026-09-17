
@Checkout_One_Step
Feature: SauceDemo Checkout Information

  Background:
     Given the user is logged in to SauceDemo
    And the inventory page is displayed
    And the user adds "Sauce Labs Backpack" to the cart
    And the user click on shopping cart Icon and shopping cart page open   
    
#===========================================================================================================================
#
#===========================================================================================================================     
    
    #And the user clicks Checkout
    #And the Checkout Step One page should be displayed 
    
#==========================================================================================================
# 1. Positive BDD Scenarios
#==========================================================================================================
#==========================================================================================================
# CHK001 — Verify Checkout Step One page
#==========================================================================================================

@checkout @stepOne @positive @smoke @CHK001
Scenario: Verify Checkout Step One page is displayed
    Given the user has at least one product in the cart
    When the user clicks the Checkout button
    Then the Checkout Step One page should be displayed
    And the page title should be "Checkout: Your Information"
    And the First Name field should be displayed
    And the Last Name field should be displayed
    And the Postal Code field should be displayed
    And the Continue button should be displayed
    And the Cancel button should be displayed      

#==========================================================================================================
# CHK002 — Enter valid checkout information
#==========================================================================================================

@checkout @stepOne @positive @CHK002
Scenario: Enter valid checkout information
   When the user clicks the Checkout button 
   Then the Checkout Step One page should be displayed 
   When the user enters first name "John" 
   And the user enters last name "Doe" 
   And the user enters postal code "12345" 
   Then the First Name field should contain "John" 
   And the Last Name field should contain "Doe" 
   And the Postal Code field should contain "12345"
    
#==========================================================================================================
# CHK003 — Continue with valid information
#==========================================================================================================

@checkout @stepOne @positive @CHK003
Scenario: Continue checkout with valid information
   When the user clicks the Checkout button 
   Then the Checkout Step One page should be displayed 
   When the user enters first name "John" 
   And the user enters last name "Doe" 
   And the user enters postal code "12345" 
   And the user clicks the Continue button
   Then the Checkout Step Two page should be displayed

#==========================================================================================================
# CHK004 — Valid data with alphabetic names
#==========================================================================================================

@checkout @stepOne @positive @CHK004
Scenario: Submit checkout information with alphabetic names
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed 
    When the user enters first name "Robert"
    And the user enters last name "Smith"
    And the user enters postal code "90210"
    And the user clicks the Continue button
    Then the Checkout Step Two page should be displayed

#==========================================================================================================
# CHK005 — Valid numeric postal code
#==========================================================================================================

@checkout @stepOne @positive @CHK005
Scenario: Submit checkout information with numeric postal code
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed 
    When the user enters first name "David"
    And the user enters last name "Johnson"
    And the user enters postal code "560001"
    And the user clicks the Continue button
    Then the Checkout Step Two page should be displayed

#==========================================================================================================
# CHK006 — Submit using realistic customer data
#==========================================================================================================

@checkout @stepOne @positive @CHK006
Scenario: Submit checkout information using realistic customer data
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed 
    When the user enters first name "Michael"
    And the user enters last name "Williams"
    And the user enters postal code "10001"
    And the user clicks the Continue button
    Then the Checkout Step Two page should be displayed


#==========================================================================================================
# 2. Negative — Required Field Validation
#
# These are particularly important because the application validates the fields in sequence.
#==========================================================================================================

#==========================================================================================================
# CHK007 — All fields empty
#==========================================================================================================

@checkout @stepOne @negative @validation @CHK007
Scenario: Continue without entering checkout information
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
   	When the user enters first name ""
   	And the user clicks the Continue button
    Then the checkout error message should be displayed
    And the checkout error message should be "Error: First Name is required"
    And the Checkout Step One page should remain displayed

#==========================================================================================================
# CHK008 — First Name missing
#==========================================================================================================

@checkout @stepOne @negative @validation @CHK008
Scenario: Continue without entering first name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters last name "Doe"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the checkout error message should be displayed
    And the checkout error message should be "Error: First Name is required"
    And the Checkout Step One page should remain displayed


#==========================================================================================================
# CHK009 — Last Name missing
#==========================================================================================================

@checkout @stepOne @negative @validation @CHK009
Scenario: Continue without entering last name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the checkout error message should be displayed
    And the checkout error message should be "Error: Last Name is required"
    And the Checkout Step One page should remain displayed

#==========================================================================================================
# CHK010 — Postal Code missing
#==========================================================================================================

@checkout @stepOne @negative @validation @CHK010
Scenario: Continue without entering postal code
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user clicks the Continue button
    Then the checkout error message should be displayed
    And the checkout error message should be "Error: Postal Code is required"
    And the Checkout Step One page should remain displayed

#==========================================================================================================
# 3. Negative — Field Combination Tests
#==========================================================================================================

#==========================================================================================================
# CHK011
#==========================================================================================================

@checkout @stepOne @negative @CHK011
Scenario: Submit checkout with only first name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user clicks the Continue button
    Then the checkout error message should be "Error: Last Name is required"

#==========================================================================================================
# CHK012
#==========================================================================================================

@checkout @stepOne @negative @CHK012
Scenario: Submit checkout with only last name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters last name "Doe"
    And the user clicks the Continue button
    Then the checkout error message should be "Error: First Name is required"

#==========================================================================================================
# CHK013
#==========================================================================================================

@checkout @stepOne @negative @CHK013
Scenario: Submit checkout with only postal code
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters postal code "12345"
    And the user clicks the Continue button
    Then the checkout error message should be "Error: First Name is required"

#==========================================================================================================
# CHK014
#==========================================================================================================

@checkout @stepOne @negative @CHK014
Scenario: Submit checkout with first name and last name only
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user clicks the Continue button
    Then the checkout error message should be "Error: Postal Code is required"

#==========================================================================================================
# CHK015
#==========================================================================================================

@checkout @stepOne @negative @CHK015
Scenario: Submit checkout with first name and postal code only
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the checkout error message should be "Error: Last Name is required"

#==========================================================================================================
# CHK016
#==========================================================================================================

@checkout @stepOne @negative @CHK016
Scenario: Submit checkout with last name and postal code only
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters last name "Doe"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the checkout error message should be "Error: First Name is required"


#==========================================================================================================
# 4. Negative — Invalid Postal Code Tests
#==========================================================================================================

#==========================================================================================================
# CHK017 — Alphabetic postal code
#==========================================================================================================

@checkout @stepOne @negative @validation @CHK017
Scenario: Enter alphabetic postal code
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "ABCDE"
    And the user clicks the Continue button    
    Then the Checkout Step Two page should be displayed
    
    #Then the system should handle the postal code according to its validation rules

#==========================================================================================================
# CHK018 — Alphanumeric postal code
#==========================================================================================================

@checkout @stepOne @negative @validation @CHK018
Scenario: Enter alphanumeric postal code
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "12ABC"
    And the user clicks the Continue button
    Then the Checkout Step Two page should be displayed
    #Then the system should handle the postal code according to its validation rules

#==========================================================================================================
# CHK019 — Special characters in postal code
#==========================================================================================================

@checkout @stepOne @negative @validation @CHK019
Scenario: Enter special characters in postal code
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "@#$%"
    And the user clicks the Continue button
    Then the Checkout Step Two page should be displayed
    
    #Then the system should handle the postal code according to its validation rules

#==========================================================================================================
# CHK020 — Negative numeric postal code
#==========================================================================================================

@checkout @stepOne @negative @validation @CHK020
Scenario: Enter negative numeric postal code
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "-12345"
    And the user clicks the Continue button
    Then the Checkout Step Two page should be displayed
    
    #Then the system should handle the postal code according to its validation rules

#==========================================================================================================
# CHK021 — Decimal postal code
#==========================================================================================================

@checkout @stepOne @negative @validation @CHK021
Scenario: Enter decimal postal code
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "123.45"
    And the user clicks the Continue button
    Then the Checkout Step Two page should be displayed
    #Then the system should handle the postal code according to its validation rules

#==========================================================================================================
# 5. First Name Data Validation
#==========================================================================================================

#==========================================================================================================
# CHK022 — First name containing numbers
#==========================================================================================================

@checkout @stepOne @negative @CHK022
Scenario: Enter numeric characters in first name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John123"
    And the user enters last name "Doe"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the Checkout Step Two page should be displayed
    Then the system should handle the first name "John123" according to its validation rules

#==========================================================================================================
# CHK023 — Special characters in first name
#==========================================================================================================

@checkout @stepOne @negative @CHK023
Scenario: Enter special characters in first name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "@John#"
    And the user enters last name "Doe"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the system should handle the first name "@John#" according to its validation rules

#==========================================================================================================
# CHK024 — First name containing spaces
#==========================================================================================================

@checkout @stepOne @boundary @CHK024
Scenario: Enter first name containing spaces
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John Doe"
    And the user enters last name "Smith"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the system should handle the first name "John Doe" according to its validation rules

#==========================================================================================================
# 6. Last Name Data Validation
#==========================================================================================================

#==========================================================================================================
# CHK025 — Last name containing numbers
#==========================================================================================================

@checkout @stepOne @negative @CHK025
Scenario: Enter numeric characters in last name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "Doe123"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the system should reject the last name "Doe123" according to its validation rules

#==========================================================================================================
# CHK026 — Special characters in last name
#==========================================================================================================

@checkout @stepOne @negative @CHK026
Scenario: Enter special characters in last name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "@Doe#"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the system should reject the last name "@Doe#" according to its validation rules

#==========================================================================================================
# 7. Whitespace Tests
#==========================================================================================================

#==========================================================================================================
# CHK027 — First Name spaces only
#==========================================================================================================

@checkout @stepOne @negative @CHK027
Scenario: Submit checkout with spaces in first name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "   "
    And the user enters last name "Doe"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the system should reject the first name "   " according to its validation rules

#==========================================================================================================
# CHK028 — Last Name spaces only
#==========================================================================================================

@checkout @stepOne @negative @CHK028
Scenario: Submit checkout with spaces in last name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "   "
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the system should reject the last name "   " according to its validation rules


#==========================================================================================================
# CHK029 — Postal Code spaces only
#==========================================================================================================

@checkout @stepOne @negative @CHK029
Scenario: Submit checkout with spaces in postal code
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "   "
    And the user clicks the Continue button
    Then the system should reject the postal code "   " according to its validation rules

#==========================================================================================================
# 8. Boundary Tests
#==========================================================================================================

#==========================================================================================================
#  CHK030 — Single-character first name
#==========================================================================================================

@checkout @stepOne @boundary @CHK030
Scenario: Submit checkout with single character first name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "J"
    And the user enters last name "Doe"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the system should accept the first name "J" according to its validation rules

#==========================================================================================================
# CHK031 — Single-character last name
#==========================================================================================================

@checkout @stepOne @boundary @CHK031
Scenario: Submit checkout with single character last name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "D"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the system should accept the last name "D" according to its validation rules

#==========================================================================================================
# CHK032 — Long first name
#==========================================================================================================

@checkout @stepOne @boundary @CHK032
Scenario: Submit checkout with a very long first name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ"
    And the user enters last name "Doe"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the system should accept the first name "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ" according to its validation rules

#==========================================================================================================
# CHK033 — Long last name
#==========================================================================================================

@checkout @stepOne @boundary @CHK033
Scenario: Submit checkout with a very long last name
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ"
    And the user enters postal code "12345"
    And the user clicks the Continue button
    Then the system should accept the last name "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ" according to its validation rules

#==========================================================================================================
# 9. Cancel Button Tests
#==========================================================================================================

#==========================================================================================================
# CHK034 — Cancel checkout
#==========================================================================================================

@checkout @stepOne @positive @navigation @CHK034
Scenario: Cancel checkout from Checkout Step One
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user clicks the Cancel button
    Then the Cart page should be displayed

#==========================================================================================================
# CHK035 — Cancel after entering information
#==========================================================================================================

@checkout @stepOne @positive @navigation @CHK035
Scenario: Cancel checkout after entering customer information
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters postal code "12345"
    And the user clicks the Cancel button
    Then the Cart page should be displayed

#==========================================================================================================
# CHK036 — Cancel after validation error
#==========================================================================================================

@checkout @stepOne @negative @navigation @CHK036
Scenario: Cancel checkout after validation error
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user clicks the Continue button
    And the checkout error message is displayed
    And the user clicks the Cancel button
    Then the Cart page should be displayed


#==========================================================================================================
# 10. Error Message Tests
#==========================================================================================================

#==========================================================================================================
# CHK037 — Error message displayed
#==========================================================================================================

@checkout @stepOne @negative @CHK037
Scenario: Verify checkout validation error is displayed
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user clicks the Continue button
    Then the checkout error message should be displayed


#==========================================================================================================
# CHK038 — Close error message
#==========================================================================================================

@checkout @stepOne @negative @CHK038
Scenario: Close checkout validation error message
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user clicks the Continue button
    And the checkout error message is displayed
    When the user closes the checkout error message
    Then the checkout error message should not be displayed

#==========================================================================================================
# CHK039 — Correct error after fixing first name
#==========================================================================================================

@checkout @stepOne @negative @CHK039
Scenario: Verify validation moves to the next missing field
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user clicks the Continue button
    Then the checkout error message should be "Error: First Name is required"
    When the user enters first name "John"
    And the user clicks the Continue button
    Then the checkout error message should be "Error: Last Name is required"

#==========================================================================================================
# CHK040 — Correct error after fixing last name
#==========================================================================================================

@checkout @stepOne @negative @CHK040
Scenario: Verify postal code validation after entering names
   When the user clicks the Checkout button 
   Then the Checkout Step One page should be displayed
   When the user enters first name "John"
   And the user enters last name "Doe"
   And the user clicks the Continue button
   Then the checkout error message should be "Error: Postal Code is required"

#==========================================================================================================
# 11. Field Interaction Tests
#==========================================================================================================

#==========================================================================================================
# CHK041 — Clear First Name
#==========================================================================================================

@checkout @stepOne @positive @CHK041
Scenario: Clear First Name field
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "John"
    And the user clears the First Name field
    Then the First Name field should be empty


#==========================================================================================================
# CHK042 — Clear Last Name
#==========================================================================================================

@checkout @stepOne @positive @CHK042
Scenario: Clear Last Name field
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters last name "Doe"
    And the user clears the Last Name field
    Then the Last Name field should be empty

#==========================================================================================================
# CHK043 — Clear Postal Code
#==========================================================================================================

@checkout @stepOne @positive @CHK043
Scenario: Clear Postal Code field
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters postal code "12345"
    And the user clears the Postal Code field
    Then the Postal Code field should be empty

#==========================================================================================================
# 12. Data-Driven BDD Scenario Outline
#==========================================================================================================

@checkout @stepOne @validation @CHK044 
Scenario Outline: Validate required checkout information
   When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "<firstName>"
    And the user enters last name "<lastName>"
    And the user enters postal code "<postalCode>"
    And the user clicks the Continue button
    Then the checkout error message should be "<expectedError>"

Examples:
    | firstName | lastName | postalCode | expectedError                  |
    |           | Doe      | 12345      | Error: First Name is required  |
    | John      |          | 12345      | Error: Last Name is required   |
    | John      | Doe      |            | Error: Postal Code is required |


#==========================================================================================================
# 13. Positive Data-Driven Scenario
#==========================================================================================================

@checkout @stepOne @positive @CHK045
Scenario Outline: Submit checkout information with valid customer data
    When the user clicks the Checkout button 
   	Then the Checkout Step One page should be displayed
    When the user enters first name "<firstName>"
    And the user enters last name "<lastName>"
    And the user enters postal code "<postalCode>"
    And the user clicks the Continue button
    Then the Checkout Step Two page should be displayed

Examples:
    | firstName | lastName | postalCode |
    | John      | Doe      | 12345      |
    | Robert    | Smith    | 90210      |
    | Michael   | Johnson  | 560001     |
    | David     | Williams | 10001      |


#==========================================================================================================
#
#==========================================================================================================

   