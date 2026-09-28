
@LGNData
Feature: SauceDemo Login

   As a user
  I want to login to Swag Labs
  So that I can access inventory page

  Background:
    Given the user is on the SauceDemo login page

  # =========================================================
  # POSITIVE LOGIN
  # =========================================================

  @LGNData @Positive @Smoke @LGNData001
  Scenario: Login successfully with standard user
    When the user logs in using "standard" user from users.json
    Then the user should be successfully logged in
    And the inventory page should be displayed


  @LGNData @Positive @LGNData002
  Scenario: Login successfully with problem user
    When the user logs in using "problem" user from users.json
    Then the user should be successfully logged in
    And the inventory page should be displayed


  @LGNData @Positive @LGNData003
  Scenario: Login successfully with performance glitch user
    When the user logs in using "performance_glitch" user from users.json
    Then the user should be successfully logged in
    And the inventory page should be displayed


  @LGNData @Positive @LGNData004
  Scenario: Login successfully with error user
    When the user logs in using "error" user from users.json
    Then the user should be successfully logged in
    And the inventory page should be displayed


  @LGNData @Positive @LGNData005
  Scenario: Login successfully with visual user
    When the user logs in using "visual" user from users.json
    Then the user should be successfully logged in
    And the inventory page should be displayed


  # =========================================================
  # LOCKED USER
  # =========================================================

  @LGNData @Negative @LGNData006
  Scenario: Locked out user cannot login
    When the user logs in using "locked" user from users.json
    Then the login should not be successful
    And the login error message should be displayed


  # =========================================================
  # INVALID CREDENTIALS
  # =========================================================

  @LGNData @Negative @LGNData007
  Scenario: Login fails with invalid username and password
    When the user logs in using "invalidCredentials" credentials from users.json
    Then the login should not be successful
    And the login error message should be displayed


  @LGNData @Negative @LGNData008
  Scenario: Login fails with valid username and invalid password
    When the user logs in using "invalid_password" credentials from users.json
    Then the login should not be successful
    And the login error message should be displayed


  @LGNData @Negative @LGNData009
  Scenario: Login fails with invalid username and valid password
    When the user logs in using "invalid_username" credentials from users.json
    Then the login should not be successful
    And the login error message should be displayed


  # =========================================================
  # EMPTY CREDENTIALS
  # =========================================================

  @LGNData @Negative @LGNData010
  Scenario: Login fails with empty username and password
    When the user logs in using "empty" credentials from users.json
    Then the login should not be successful
    And the login error message should be displayed


  @LGN @Negative @LGNData011
  Scenario: Login fails with empty username
    When the user logs in using "empty_username" credentials from users.json
    Then the login should not be successful
    And the login error message should be displayed


  @LGNData @Negative @LGNData012
  Scenario: Login fails with empty password
    When the user logs in using "empty_password" credentials from users.json
    Then the login should not be successful
    And the login error message should be displayed


  # =========================================================
  # LOGIN PAGE UI
  # =========================================================

  @LGNData @UI @LGNData013
  Scenario: Username field is displayed
    Then the username field should be displayed


  @LGNData @UI @LGNData014
  Scenario: Password field is displayed
    Then the password field should be displayed


  @LGNData @UI @LGNData015
  Scenario: Login button is displayed
    Then the Login button should be displayed


  @LGNData @UI @LGNData016
  Scenario: Login button is enabled
    Then the Login button should be enabled


  @LGNData @UI @LGNData017
  Scenario: Password field is masked
    Then the password field should be masked


  # =========================================================
  # ERROR HANDLING
  # =========================================================

  @LGNData @Negative @LGNData018
  Scenario: Login error message can be closed
    When the user logs in using "invalidCredentials" credentials from users.json
    Then the login error message should be displayed
    When the user closes the login error message
    Then the login error message should not be displayed


  @LGNData @Negative @LGNData019
  Scenario: Login page remains displayed after closing error
    When the user logs in using "invalidCredentials" credentials from users.json
    And the user closes the login error message
    Then the login page should be displayed


  # =========================================================
  # REFRESH
  # =========================================================

  @LGNData @Regression @LGNData020
  Scenario: Login page remains available after refresh
    When the user refreshes the login page
    Then the login page should be displayed


  @LGN @Regression @LGNData021
  Scenario: Credentials are cleared after refresh
    When the user logs in using "standard" user from users.json
    And the user refreshes the login page
    Then the inventory page should be displayed 


  # =========================================================
  # RETRY LOGIN
  # =========================================================

  @LGNData @Regression @LGNData022
  Scenario: User can retry login after invalid credentials
    When the user logs in using "invalid_password" credentials from users.json
    Then the login error message should be displayed
    When the user refreshes the login page
    And the user logs in using "standard" user from users.json
    Then the user should be successfully logged in


  @LGNData @Regression @LGNData023
  Scenario: User can retry login after locked account
    When the user logs in using "locked" user from users.json
    Then the login error message should be displayed
    When the user refreshes the login page
    And the user logs in using "standard" user from users.json
    Then the user should be successfully logged in