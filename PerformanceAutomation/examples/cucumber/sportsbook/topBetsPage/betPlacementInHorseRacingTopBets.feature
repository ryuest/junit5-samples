@top-bets
@desktop-only
Feature: Top Bet verifications in Racing pages

  Scenario: Horse Racing Top Bets
    Given user is in William Hill Sportsbook
    When user navigates to 'Horse Racing' 'Top Bets' page
    Then the first Events to be displayed are from 'Horse Racing'
    And the url contains the words: 'betting/en-gb/horse-racing/top-bets'


  Scenario: Add-Remove selections from Horse Racing Top Bets section on the racing homepage and clear betslip
    Given user is in William Hill Sportsbook
    And user navigates to 'Horse Racing' 'Top Bets' page
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added


  @desktop-only
  Scenario: Add-Remove selections from Horse Racing Top Bets section on the racing homepage with odds button
    Given user is in William Hill Sportsbook
    And user navigates to 'Horse Racing' 'Top Bets' page
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clicks on the first available selection
    Then betslip has '0' selections added


  @critical
  Scenario: Place single bet from Horse Racing Top Bets section on the racing homepage
    Given user is in William Hill Sportsbook
    And the user logs in
    And user navigates to 'Horse-Racing' 'Top Bets' page
    When user clicks on the first available selection
    And the user places a bet with amount '0.05'
    Then the user balance is updated