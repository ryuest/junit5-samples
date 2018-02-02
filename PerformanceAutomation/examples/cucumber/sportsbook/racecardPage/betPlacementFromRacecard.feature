@bet-placement
@live-suitable
Feature: Bet placement in Racecard

  Scenario: Add-Remove selections to betslip from Horse Racing racecard and clear betslip
    Given user is in William Hill Sportsbook
    And user goes to 'Horse-Racing' page
    And user clicks on the first future race in the meeting nav
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added


  @desktop-only
  Scenario: Add-Remove selections to betslip from Horse Racing racecard with odds button
    Given user is in William Hill Sportsbook
    And user goes to 'Horse-Racing' page
    And user clicks on the first future race in the meeting nav
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clicks on the first available selection
    Then betslip has '0' selections added


  Scenario: Add-Remove selections to betslip from Greyhounds racecard and clear betslip
    Given user is in William Hill Sportsbook
    And user goes to 'Greyhounds' page
    And user clicks on the first future race in the meeting nav
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added


  @desktop-only
  Scenario: Add-Remove selections to betslip from Greyhounds racecard with odds button
    Given user is in William Hill Sportsbook
    And user goes to 'Greyhounds' page
    And user clicks on the first future race in the meeting nav
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clicks on the first available selection
    Then betslip has '0' selections added


  @smoke
  @critical
  @desktop-only
  Scenario: Single bet placement in Horse Racing racecard in desktop
    Given user is in William Hill Sportsbook
    And the user logs in
    And user goes to 'Horse-Racing' page
    And user clicks on the first future race in the meeting nav
    And user clicks on the first available selection
    When the user places a bet with amount '0.05'
    Then the user balance is updated


  @smoke
  @critical
  @mobile-only
  Scenario: Single bet placement in Horse Racing racecard in mobile
    Given user is in William Hill Sportsbook
    And the user logs in
    And user goes to 'Horse-Racing' page
    And user clicks on the first future race in the meeting nav
    And user clicks on the last available selection
    When the user places a bet with amount '0.05'
    And sleep '1' seconds
    Then the user balance is updated


  Scenario: Single bet placement in Greyhounds racecard
    Given user is in William Hill Sportsbook
    And the user logs in
    When user goes to 'Greyhounds' page
    And user clicks on the first future race in the meeting nav
    And user clicks on the first available selection
    And the user places a bet with amount '0.05'
    And the user balance is updated