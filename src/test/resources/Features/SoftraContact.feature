Feature: feature to test google search functionality

  Scenario: Validate google search is working
    Given browser window is open
    And user is on google search page
    And user accepts privacy prompt
    When user click contact tab
    And check contact email
    Then contact email should be visible
