
@menu
Feature: SauceDemo Sidebar Menu Operations

   Background:
    Given the user is logged in to SauceDemo
    When the user clicks the Menu button

#======================================================================================================
# MENU VISIBILITY
#========================================================================================================

  @MENU01 @Positive
  Scenario: Verify sidebar menu is displayed
    Then the sidebar menu should be displayed

  @MENU02 @Positive
  Scenario: Verify menu options are displayed
    Then the All Items option should be displayed
    And the Dynamic Catalog option should be displayed
    And the About option should be displayed
    And the Logout option should be displayed
    And the Reset App State option should be displayed

  @MENU03 @Positive
  Scenario: Verify Close Menu button is displayed
    Then the Close Menu button should be displayed


#=====================================================================================================
# ALL ITEMS
#======================================================================================================

  @MENU04 @ALL_ITEMS @Positive
  Scenario: Navigate to All Items from sidebar
    When the user clicks the All Items option
    Then the Inventory page should be displayed

  @MENU05 @ALL_ITEMS @Positive
  Scenario: Verify All Items navigation URL
    When the user clicks the All Items option
    Then the current URL should contain "/inventory.html"


#==================================================================================================
# DYNAMIC CATALOG
#==================================================================================================

  @MENU06 @DYNAMIC_CATALOG @Positive
  Scenario: Verify Dynamic Catalog option is displayed
    Then the Dynamic Catalog option should be displayed

  @MENU07 @DYNAMIC_CATALOG @Positive
  Scenario: Expand Dynamic Catalog submenu
    When the user clicks the Dynamic Catalog option
    Then the Dynamic Catalog submenu should be displayed

  @MENU08 @DYNAMIC_CATALOG @Positive
  Scenario: Verify Dynamic Catalog is expanded
    When the user clicks the Dynamic Catalog option
    Then the Dynamic Catalog should be expanded

  @MENU09 @DYNAMIC_CATALOG @Positive
  Scenario: Verify Dynamic Catalog submenu options
    When the user clicks the Dynamic Catalog option
    Then the Lazy Load option should be displayed
    And the Spinner option should be displayed
    And the Slider option should be displayed


#=======================================================================================================
# DYNAMIC CATALOG - LAZY LOAD
#========================================================================================================

  @MENU10 @LAZY_LOAD @Positive
  Scenario: Navigate to Lazy Load page
    When the user clicks the Dynamic Catalog option
    And the user clicks the Lazy Load option
    Then the Lazy Load page should be displayed

  @MENU11 @LAZY_LOAD @Positive
  Scenario: Verify Lazy Load navigation
    When the user clicks the Dynamic Catalog option
    And the user clicks the Lazy Load option
    Then the current URL should contain "lazy"


#====================================================================================================
# DYNAMIC CATALOG - SPINNER
#=======================================================================================================

  @MENU12 @SPINNER @Positive
  Scenario: Navigate to Spinner page
    When the user clicks the Dynamic Catalog option
    And the user clicks the Spinner option
    Then the Spinner page should be displayed

  @MENU13 @SPINNER @Positive
  Scenario: Verify Spinner navigation
    When the user clicks the Dynamic Catalog option
    And the user clicks the Spinner option
    Then the current URL should contain "spinner"


  # ============================================================
  # DYNAMIC CATALOG - SLIDER
  # ============================================================

  @MENU14 @SLIDER @Positive
  Scenario: Navigate to Slider page
    When the user clicks the Dynamic Catalog option
    And the user clicks the Slider option
    Then the Slider page should be displayed

  @MENU15 @SLIDER @Positive
  Scenario: Verify Slider navigation
    When the user clicks the Dynamic Catalog option
    And the user clicks the Slider option
    Then the current URL should contain "slider"


#=======================================================================================================
# DYNAMIC CATALOG TOGGLE
#========================================================================================================

  @MENU16 @DYNAMIC_CATALOG @Toggle
  Scenario: Collapse Dynamic Catalog submenu
    When the user clicks the Dynamic Catalog option
    And the user clicks the Dynamic Catalog option
    Then the Dynamic Catalog submenu should not be displayed

  @MENU17 @DYNAMIC_CATALOG @Toggle
  Scenario: Expand Dynamic Catalog submenu again
    When the user clicks the Dynamic Catalog option
    And the user clicks the Dynamic Catalog option
    And the user clicks the Dynamic Catalog option
    Then the Dynamic Catalog submenu should be displayed


#=======================================================================================================
# ABOUT
#=======================================================================================================

  @MENU18 @ABOUT @Positive
  Scenario: Navigate to About page
    When the user clicks the About option
    Then the About page should be opened

  @MENU19 @ABOUT @Positive
  Scenario: Verify About link
    Then the About option should be displayed


#=======================================================================================================
# RESET APP STATE
#=======================================================================================================

  @MENU20 @RESET @Positive
  Scenario: Reset application state
    When the user clicks the Reset App State option
    Then the application state should be reset


#=======================================================================================================
# LOGOUT
#=======================================================================================================

  @MENU21 @LOGOUT @Positive
  Scenario: Logout using sidebar menu
    When the user clicks the Logout option
    Then the Login page should be displayed

  @MENU22 @LOGOUT @Positive
  Scenario: Verify authenticated user cannot remain after logout
    When the user clicks the Logout option
    Then the Login page should be displayed
    And the current URL should not contain "/inventory.html"


#======================================================================================================
# CLOSE MENU
#======================================================================================================

  @MENU23 @CLOSE @Positive
  Scenario: Close sidebar menu
    When the user clicks the Close Menu button
    Then the sidebar menu should not be displayed

  @MENU24 @CLOSE @Positive
  Scenario: Reopen sidebar menu after closing
    When the user clicks the Close Menu button
    And the user clicks the Menu button
    Then the sidebar menu should be displayed


#=====================================================================================================
# NEGATIVE / BOUNDARY TESTS
#=======================================================================================================

  @MENU25 @Negative
  Scenario: Verify Dynamic Catalog submenu is not visible before expansion
    When the user clicks the Dynamic Catalog option
    And the user clicks the Dynamic Catalog option
    Then the Dynamic Catalog submenu should not be displayed

  @MENU26 @Negative
  Scenario: Verify checkout controls are not displayed in sidebar
    Then the Finish button should not be displayed
    And the Place Order button should not be displayed

  @MENU27 @Negative
  Scenario: Verify checkout information fields are not displayed in sidebar
    Then the First Name field should not be displayed
    And the Last Name field should not be displayed
    And the Postal Code field should not be displayed

  @MENU28 @Negative
  Scenario: Verify Cancel button is not displayed in sidebar
    Then the Cancel button should not be displayed