@top-bets
Feature: Top Bets in Featured page

  @desktop-only
  @critical
  Scenario: View Top Bets from Events Page
    Given user is in William Hill Sportsbook
    When user goes to 'Football' page
    Then the top bets widget 'is' displayed


  @mobile-only
  @critical
  Scenario: Top Bets widget is available in homepage
    Given user is in William Hill Sportsbook
    And user goes to 'Football' page
    When the user clicks on the tab 'Top Bets' in the tabbed navigation
    Then the top bets widget 'is' displayed


  @manual
  @todo
  @critical
  @bet-placement
  Scenario: Place single bet in Top Bets page
    Given user is in William Hill Sportsbook
    And user goes to 'Football' page
    When the user logs in
    Then user clicks on the 'first' available selection in Top Bets widget
    And the user places a bet with amount '0.05'
    And the user balance is updated