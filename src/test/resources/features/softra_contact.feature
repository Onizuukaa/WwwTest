@SoftraContact
Feature: Support Email Availability

  Scenario: Validate support email is available
    Given user is on Softra home page
    When user clicks contact tab
    And user expands support section
    Then contact email should be visible
