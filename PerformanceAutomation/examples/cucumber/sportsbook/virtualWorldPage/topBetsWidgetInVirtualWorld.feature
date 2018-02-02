@top-bets
Feature: Top Bets in Virtual Page

  Scenario: View Top Bets are not available in Virtual World
    Given user is in William Hill Sportsbook
    When user goes to 'Virtual World' page
    Then the top bets widget 'is not' displayed