@bet-placement
@competitions-tabs
Feature: Bet placement in Football Competitions page

  @critical
  Scenario: Single bet placement in Matches Tab
    Given user is in William Hill Sportsbook
    When the user logs in
    When user navigates to 'Football' 'Competitions' page
    And user clicks on 'Matches' tab
    Then user clicks on the '1st' available selection
    And the user places a bet with amount '0.05'
    And the user balance is updated


  @critical
  Scenario: Single bet placement in Outrights Tab
    Given user is in William Hill Sportsbook
    When the user logs in
    When user navigates to 'Football' 'Competitions' page
    And user clicks on 'Outrights' tab
    Then user clicks on the '1st' available selection
    And the user places a bet with amount '0.05'
    And the user balance is updated