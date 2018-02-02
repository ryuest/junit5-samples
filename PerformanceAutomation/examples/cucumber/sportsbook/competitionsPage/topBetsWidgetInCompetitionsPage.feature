@top-bets
Feature: Top Bets widget in Competitions page

  @critical
  Scenario: Top Bets widget is available in competitions page
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'Competitions' page
    Then the top bets widget 'is' displayed