@bet-placement
@live-suitable
Feature: Bet placement in Football homepage
  
  Scenario: Add-Remove selections in betslip from Football homepage and clear betslip
    Given user is in William Hill Sportsbook
    When user goes to 'Football' page
    And user clicks on the first available selection
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added

  @desktop-only
  Scenario: Add-Remove selections in betslip from Football homepage with odds button
    Given user is in William Hill Sportsbook
    And user goes to 'Football' page
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clicks on the first available selection
    Then betslip has '0' selections added

  @critical
  Scenario: Place a single bet in Football homepage
    Given user is in William Hill Sportsbook
    And the user logs in
    And user goes to 'Football' page
    When user clicks on the first available selection
    And the user places a bet with amount '0.05'
    Then the user balance is updated