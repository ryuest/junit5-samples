@bet-placement
@live-suitable
Feature: Bet placement in Racing Homepage

  Scenario: Add-Remove selections to betslip from Horse Racing Next off section on the racing homepage and clear betslip
    Given user is in William Hill Sportsbook
    And user goes to 'Horse-Racing' page
    When user clicks on the '1st' selection of '1st' race from Next Off section
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added


  @desktop-only
  Scenario: Add-Remove selections to betslip from Horse Racing Next off section on the racing homepage with odds button
    Given user is in William Hill Sportsbook
    And user goes to 'Horse-Racing' page
    When user clicks on the '1st' selection of '1st' race from Next Off section
    Then betslip has '1' selections added
    When user clicks on the '1st' selection of '1st' race from Next Off section
    Then betslip has '0' selections added


  Scenario: Add-Remove selections to betslip from Greyhounds Next off section on the racing homepage and clear betslip
    Given user is in William Hill Sportsbook
    And user goes to 'Greyhounds' page
    When user clicks on the '1st' selection of '1st' race from Next Off section
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added


  @desktop-only
  Scenario: Add-Remove selections to betslip from Greyhounds Next off section on the racing homepage with odds button
    Given user is in William Hill Sportsbook
    And user goes to 'Greyhounds' page
    When user clicks on the '1st' selection of '1st' race from Next Off section
    Then betslip has '1' selections added
    When user clicks on the '1st' selection of '1st' race from Next Off section
    Then betslip has '0' selections added


  @critical
  Scenario: Place a single bet in Horse Racing Next off section on the racing homepage
    Given user is in William Hill Sportsbook
    And the user logs in
    And user goes to 'Horse-Racing' page
    When user clicks on the '1st' selection of '1st' race from Next Off section
    And the user places a bet with amount '0.05'
    Then the user balance is updated


  Scenario: Place a single bet in Greyhounds Next off section on the racing homepage
    Given user is in William Hill Sportsbook
    And the user logs in
    And user goes to 'Greyhounds' page
    When user clicks on the '1st' selection of '1st' race from Next Off section
    And the user places a bet with amount '0.05'
    Then the user balance is updated