@SoftraContact
Feature: feature to test that email for support is available

  Scenario: Validate support email is available
    Given user is on Softra home page
    And user accepts privacy prompt
    When user click contact tab
    And user expands support section
    Then contact email should be visible
