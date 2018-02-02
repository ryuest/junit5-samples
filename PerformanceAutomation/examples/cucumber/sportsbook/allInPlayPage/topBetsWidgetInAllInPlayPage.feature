@top-bets
Feature: Top Bets widget in All In-Play page

  @desktop-only
  @critical
  Scenario: Top Bets widget is available in in-play page in desktop
    Given user is in William Hill Sportsbook
    When user goes to 'In-Play' page
    Then the top bets widget 'is' displayed


  @mobile-only
  @critical
  Scenario: Top Bets widget is not available in in-play page in mobile
    Given user is in William Hill Sportsbook
    When user goes to 'In-Play' page
    Then the top bets widget 'is not' displayed