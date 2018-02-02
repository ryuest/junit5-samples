@bet-placement
@live-suitable
Feature: Bet placement in All In-Play page

  @desktop-only
  Scenario: Add-Remove selections to betslip in All In-Play page with odds button
    Given user is in William Hill Sportsbook
    And user goes to 'In-Play' page
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clicks on the first available selection
    Then betslip has '0' selections added


  Scenario: Add-Remove selections to betslip in All In-Play page and clear betslip
    Given user is in William Hill Sportsbook
    And user goes to 'In-Play' page
    And user clicks on the first available selection
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added


  @critical
  Scenario: Place single bet in All In-Play page
    Given user is in William Hill Sportsbook
    And the user logs in
    And user goes to 'In-Play' page
    And user clicks on the first available selection
    And the user places a bet with amount '0.05'
    And the user balance is updated