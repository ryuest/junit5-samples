@translations
@live-suitable
Feature: Translations in Daily List page

  @critical
  Scenario: Navigation through day filter options in Football Daily List in German
    Given user is in William Hill Sportsbook
    When user switches to 'German'
    And user navigates to 'football' 'Daily List' page by url
    Then user navigates through all day filter options


  @critical
  Scenario: Navigation through day filter options in Basketball Daily List in German
    Given user is in William Hill Sportsbook
    When user switches to 'German'
    And user navigates to 'basketball' 'Daily List' page by url
    Then user navigates through all day filter options