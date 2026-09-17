
@Checkout_One_Step
@PostalCode

Feature: Validate Postal Code on SauceDemo Checkout Step One

	Background:
		Given the user is logged in to SauceDemo
		And the inventory page is displayed
		And the user adds "Sauce Labs Backpack" to the cart
		And the user clicks on shopping cart Icon and shopping cart page open
		When the user clicks the Checkout button
		Then the Checkout Step One page should be displayed

#==========================================================================================================
# POSITIVE TEST CASES
#==========================================================================================================

	@Positive
	@PostalCodeValid
	@PC001
	Scenario Outline: Validate valid six digit numeric postal code
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code "<postalCode>"
		And the user clicks the Continue button
		Then the Checkout Step Two page should be displayed
	
	Examples:
	| postalCode |
	| 110001     |
	| 400001     |
	| 411001     |
	| 411014     |
	| 500001     |
	| 560001     |
	| 600001     |
	| 700001     |

#==========================================================================================================
# NEGATIVE TEST CASES - MANDATORY VALIDATION
#==========================================================================================================

	@Negative
	@PostalCodeMandatory
	@PC002
	Scenario: Validate postal code is mandatory
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code ""
		And the user clicks the Continue button
		Then the Checkout Step One page should be displayed
		And the postal code validation error should be displayed
		And the Checkout Step Two page should not be displayed


#==========================================================================================================
# NEGATIVE TEST CASES - LENGTH VALIDATION
#==========================================================================================================

	@Negative
	@PostalCodeLength
	@PC003
	Scenario Outline: Validate postal code with invalid length
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code "<postalCode>"
		And the user clicks the Continue button
		Then the Checkout Step One page should be displayed
		And the postal code validation error should be displayed
		And the Checkout Step Two page should not be displayed
	
	Examples:
	| postalCode |
	| 12345      |
	| 1234       |
	| 123        |
	| 1234567    |
	| 12345678   |

#==========================================================================================================
# NEGATIVE TEST CASES - FIRST DIGIT ZERO
#==========================================================================================================

	@Negative
	@PostalCodeFirstDigit
	@PC004
	Scenario: Validate postal code starting with zero
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code "011001"
		And the user clicks the Continue button
		Then the Checkout Step One page should be displayed
		And the postal code validation error should be displayed
		And the Checkout Step Two page should not be displayed

#==========================================================================================================
# NEGATIVE TEST CASES - ALPHABETS
#==========================================================================================================
	
	@Negative
	@PostalCodeAlphabetic
	@PC005
	Scenario Outline: Validate postal code containing alphabets
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code "<postalCode>"
		And the user clicks the Continue button
		Then the Checkout Step One page should be displayed
		And the postal code validation error should be displayed
		And the Checkout Step Two page should not be displayed
	
	Examples:
	| postalCode |
	| ABCDE      |
	| ABCDEF     |
	| 41100A     |
	| A11001     |
	| 411A01     |
	| ABC123     |
	| 123ABC     |

#==========================================================================================================
# NEGATIVE TEST CASES - SPECIAL CHARACTERS
#==========================================================================================================

	@Negative
	@PostalCodeSpecialCharacter
	@PC006
	Scenario Outline: Validate postal code containing special characters
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code "<postalCode>"
		And the user clicks the Continue button
		Then the Checkout Step One page should be displayed
		And the postal code validation error should be displayed
		And the Checkout Step Two page should not be displayed
	
	Examples:
	| postalCode |
	| 411-001    |
	| 411@001    |
	| 411#001    |
	| 411$001    |
	| 411%001    |
	| 411&001    |
	| 411*001    |
	| 411/001    |

#==========================================================================================================
# NEGATIVE TEST CASES - SPACE
#==========================================================================================================

	@Negative
	@PostalCodeSpace
	@PC007
	Scenario Outline: Validate postal code containing spaces
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code "<postalCode>"
		And the user clicks the Continue button
		Then the Checkout Step One page should be displayed
		And the postal code validation error should be displayed
		And the Checkout Step Two page should not be displayed
	
	Examples:
	| postalCode |
	| 411 001    |
	| 411001     |
	|  411001    |
	| 411001     |

#==========================================================================================================
# NEGATIVE TEST CASES - DECIMAL
#==========================================================================================================

	@Negative
	@PostalCodeDecimal
	@PC008
	Scenario Outline: Validate decimal postal code
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code "<postalCode>"
		And the user clicks the Continue button
		Then the Checkout Step One page should be displayed
		And the postal code validation error should be displayed
		And the Checkout Step Two page should not be displayed
	
	Examples:
	| postalCode |
	| 411001.0   |
	| 411.001    |
	| 411001.5   |
	| 41100.1    |

#==========================================================================================================
# NEGATIVE TEST CASES - NEGATIVE VALUE
#==========================================================================================================

	@Negative
	@PostalCodeNegative
	@PC009
	Scenario Outline: Validate negative postal code
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code "<postalCode>"
		And the user clicks the Continue button
		Then the Checkout Step One page should be displayed
		And the postal code validation error should be displayed
		And the Checkout Step Two page should not be displayed
	
	Examples:
	| postalCode |
	| -411001    |
	| -123456    |
	| -000001    |

#==========================================================================================================
# NEGATIVE TEST CASES - MIXED INVALID VALUES
#==========================================================================================================

	@Negative
	@PostalCodeMixed
	@PC010
	Scenario Outline: Validate postal code with mixed invalid characters
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code "<postalCode>"
		And the user clicks the Continue button
		Then the Checkout Step One page should be displayed
		And the postal code validation error should be displayed
		And the Checkout Step Two page should not be displayed
	
	Examples:
	| postalCode |
	| 411-00A    |
	| 41A@001    |
	| ABC-001    |
	| 411#0A1    |
	| 41 1001    |
	| @411001    |
	| 411001@    |

#==========================================================================================================
# NEGATIVE TEST CASES - NULL / EMPTY / WHITESPACE
#==========================================================================================================

	@Negative
	@PostalCodeEmpty
	@PC011
	Scenario Outline: Validate empty or whitespace postal code
		When the user enters first name "John"
		And the user enters last name "Doe"
		And the user enters postal code "<postalCode>"
		And the user clicks the Continue button
		Then the Checkout Step One page should be displayed
		And the postal code validation error should be displayed
		And the Checkout Step Two page should not be displayed
	
	Examples:
	| postalCode |
	|            |
	|            |
	|            |



#============================================================================================================================
#
#=============================================================================================================================
#Feature: Indian Postal Code Validation

  @PostalCode @Positive
  Scenario Outline: Validate valid Indian postal codes
    Then the postal code "<postalCode>" should be valid for India

    Examples:
      | postalCode |
      | 110001     |
      | 400001     |
      | 411001     |
      | 411014     |
      | 500001     |
      | 560001     |
      | 600001     |
      | 700001     |


  @PostalCode @Negative
  Scenario Outline: Validate invalid Indian postal codes
    Then the postal code "<postalCode>" should be invalid for India

    Examples:
      | postalCode |
      | 12345      |
      | 1234567    |
      | 012345     |
      | 12345A     |
      | ABCDEF     |
      | 123-456    |
      | 123 456    |
      | 123.456    |
      | -123456    |