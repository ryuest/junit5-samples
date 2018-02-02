@bet-placement
@live-suitable
Feature: Bet placement in Football Daily List page

  @juri
  @critical
  Scenario: Place single bet in default market in Football Daily List
    Given user is in William Hill Sportsbook
    And the user logs in
    And user navigates to 'football' 'Daily List' page
    And user clicks on the first available selection
    When the user places a bet with amount '0.05'
    Then the user balance is updated


  @critical
  Scenario: Place single bet in alternate market in Football Daily List
    Given user is in William Hill Sportsbook
    And the user logs in
    And user navigates to 'football' 'Daily List' page
    And user changes alternate market to 'Both Teams To Score?'
    And user clicks on the first available selection
    When the user places a bet with amount '0.05'
    Then the user balance is updated