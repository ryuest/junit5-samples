@top-bets
@live-suitable
Feature: Page refresh in Top Bets page

  @desktop-only
  Scenario: Top Bets app displayed on page refresh - Desktop
    Given user is in William Hill Sportsbook
    When user click on show more in top bets section
    Then the top bets page is displayed
    When the user refresh the page
    Then the top bets page is displayed


  @mobile-only
  Scenario: Top Bets app displayed on page refresh - Mobile
    Given user is in William Hill Sportsbook
    Then the user clicks on the tab 'Top Bets' in the tabbed navigation
    When user click on show more in top bets section
    Then the top bets page is displayed
    When the user refresh the page
    Then the top bets page is displayed