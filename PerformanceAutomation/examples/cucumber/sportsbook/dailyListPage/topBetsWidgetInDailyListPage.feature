@top-bets
Feature: Top Bets widget in Daily List page

  @critical
  Scenario: Top Bets widget is available in daily list page
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'Daily List' page
    Then the top bets widget 'is' displayed