@sports-menu
@collections-menu
@live-suitable
Feature: Components in event page

  @critical
  Scenario: Components in pre-match event page
    Given user is in William Hill Sportsbook
    When user goes to 'Top Bets' page
    And the user clicks on the first event
    And correct components are available in 'event' page


  @critical
  Scenario: Components in in-play event page
    Given user is in William Hill Sportsbook
    When user goes to 'In-Play' page
    And the user clicks on the first event
    Then correct components are available in 'event' page