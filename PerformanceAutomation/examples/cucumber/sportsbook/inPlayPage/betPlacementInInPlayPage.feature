@bet-placement
@live-suitable
Feature: Bet placement in Football In-Play page

  Scenario: Add-Remove selections to betslip from Football In-Play page and clear betslip
    Given user is in William Hill Sportsbook
    When user navigates to 'Football' 'In-Play' page
    And user clicks on the first available selection
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added


  @desktop-only
  Scenario: Add-Remove selections to betslip from Football In-Play page with odds button
    Given user is in William Hill Sportsbook
    And user navigates to 'Football' 'In-Play' page
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clicks on the first available selection
    Then betslip has '0' selections added


  @critical
  Scenario: Place a single bet in Football In-Play page
    Given user is in William Hill Sportsbook
    When the user logs in
    When user navigates to 'Football' 'In-Play' page
    Then user clicks on the first available selection
    And the user places a bet with amount '0.05'
    And the user balance is updated