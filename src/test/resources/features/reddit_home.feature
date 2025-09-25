@home @reddit
Feature: Reddit Home Page Functionality
  As a Reddit user
  I want to access the home page
  So that I can browse content and use Reddit features

  Background:
    Given I am on the Reddit home page

  @smoke @home
  Scenario: Home page elements are displayed
    Then I should see the Reddit home page
    And I should see the search box
    And I should see the login button
    And I should see the signup button

  @home
  Scenario: Search functionality
    When I search for "technology"
    Then the current URL should contain "search"

  @home
  Scenario: Navigation buttons
    When I click on the login button
    Then I should be redirected to the login page

  @home
  Scenario: Home page content
    Then I should see posts on the page
    And I should see subreddit links

  @home
  Scenario: Minimum content validation
    Then I should see at least 1 posts
    And I should see at least 1 subreddit links

  @home
  Scenario: Page title and URL validation
    Then the page title should contain "Reddit"
    And the current URL should contain "reddit.com"

  @home
  Scenario: User not logged in state
    Given I am not logged in
    Then I should not be logged in
    And I should see the login button
    And I should see the signup button

  @home
  Scenario: Post sorting functionality
    When I sort posts by Hot
    And I sort posts by New
    And I sort posts by Top
    Then I should see posts on the page

  @home
  Scenario: Home page load validation
    Given I wait for the page to load
    Then I should see the Reddit home page
    And I should see posts on the page
