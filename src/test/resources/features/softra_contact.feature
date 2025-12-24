@SoftraContact
Feature: feature to test that email for support is available

  Scenario: Validate support email is available
    Given browser window is open
    And user is on google search page
    And user accepts privacy prompt
    When user click contact tab
    And check contact email
    Then contact email should be visible
