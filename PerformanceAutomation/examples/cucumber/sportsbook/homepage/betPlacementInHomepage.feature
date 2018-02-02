@bet-placement
@live-suitable
Feature: Bet placement in Homepage

  Scenario: Add-Remove selections to betslip from Homepage and clear betslip
    Given user is in William Hill Sportsbook
    And user clicks on the first available selection
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added

  @desktop-only
  Scenario: Add-Remove selections to betslip from Homepage with odds button
    Given user is in William Hill Sportsbook
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clicks on the first available selection
    Then betslip has '0' selections added

  @critical
  Scenario: Place a single bet in Homepage
    Given user is in William Hill Sportsbook
    And the user logs in
    When user clicks on the first available selection
    And the user places a bet with amount '0.05'
    Then the user balance is updated