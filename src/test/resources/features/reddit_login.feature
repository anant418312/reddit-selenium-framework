@login @reddit
Feature: Reddit Login Functionality
  As a Reddit user
  I want to be able to log in to my account
  So that I can access personalized content and features

  Background:
    Given I navigate to the Reddit login page

  @smoke @login
  Scenario: Successful login with valid credentials
    When I login with valid credentials
    Then I should be successfully logged in

  @login
  Scenario: Login with invalid credentials
    When I login with invalid credentials
    Then I should see an error message
    And I should not be logged in

  @login
  Scenario: Login form validation
    Then I should see the login form
    And I should see the username field
    And I should see the password field
    And I should see the login button

  @login
  Scenario: Login form fields are enabled
    Then the username field should be enabled
    And the password field should be enabled

  @login
  Scenario: Field clearing functionality
    When I enter username "testuser"
    And I enter password "testpass"
    And I clear the username field
    And I clear the password field
    Then the username field should be empty
    And the password field should be empty

  @login
  Scenario: Remember me checkbox functionality
    When I check the remember me checkbox
    And I uncheck the remember me checkbox
    Then I should see the login form

  @login
  Scenario: Navigation links
    When I click the forgot password link
    And I click the signup link
    Then I should see the login form

  @login
  Scenario: Login with specific credentials
    When I enter username "specificuser"
    And I enter password "specificpass"
    And I click the login button
    Then I should see an error message containing "Invalid"

  @login
  Scenario: Login form field values
    When I enter username "testuser"
    And I enter password "testpass"
    Then the username field should contain "testuser"
    And the password field should contain "testpass"

  @login
  Scenario: Login page navigation from home page
    Given I am on the Reddit home page
    When I click on the login button
    Then I should be redirected to the login page
    And I should see the login form
