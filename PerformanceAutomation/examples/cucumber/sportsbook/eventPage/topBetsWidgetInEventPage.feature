@top-bets
Feature: Top Bets in Event page

  @critical
  @desktop-only
  Scenario: Top Bets widget is available in events page in desktop
    Given user is in William Hill Sportsbook
    And user navigates to 'Football' 'Competitions' page
    And user clicks on 'Matches' tab
    When the user clicks on the first event
    Then the top bets widget 'is' displayed


  @critical
  @mobile-only
  Scenario: Top Bets widget is not available in events page in mobile
    Given user is in William Hill Sportsbook
    And user navigates to 'Football' 'Competitions' page
    And user clicks on 'Matches' tab
    When the user clicks on the first event
    Then the top bets widget 'is not' displayed