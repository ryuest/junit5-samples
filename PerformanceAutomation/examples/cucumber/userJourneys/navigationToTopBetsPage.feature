@top-bets
@sports-menu
@live-suitable
Feature: Navigation to Top Bets page

  Scenario: Navigation to Top Bets from homepage
    Given user is in William Hill Sportsbook
    When user click on show more in top bets section
    Then correct components are available in 'Top Bets' page


  @critical
  @desktop-only
  Scenario: Navigation to Top Bets from competitions
    Given user is in William Hill Sportsbook
    And user navigates to 'Football' 'Competitions' page
    And option 'Competitions' is highlighted on sports menu
    When user click on show more in top bets section
    Then the top bets page is displayed
    And option 'Top Bets' is highlighted on sports menu


  @manual
  @todo
  Scenario: Navigation to event from Top Bets
    Given user is in William Hill Sportsbook
    When user goes to 'Top Bets' page
    Then the user clicks on the first event
    And option 'Top Bets' is not highlighted on sports menu
    And option 'Featured' is highlighted on sports menu
