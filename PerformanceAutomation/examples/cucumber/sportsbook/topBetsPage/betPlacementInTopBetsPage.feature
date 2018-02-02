@top-bets
@bet-placement
@live-suitable
Feature: Bet placement in Top Bets page
  
  Scenario: Add-Remove selections to betslip from Top Bets and clear betslip
    Given user is in William Hill Sportsbook
    And user goes to 'Top Bets' page
    When user clicks on the 'first' available selection in Top Bets page
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added

  @desktop-only
  Scenario: Add-Remove selections to betslip from Top Bets with odds button
    Given user is in William Hill Sportsbook
    And user goes to 'Top Bets' page
    When user clicks on the 'first' available selection in Top Bets page
    Then betslip has '1' selections added
    When user clicks on the 'first' available selection in Top Bets page
    Then betslip has '0' selections added


  @critical
  Scenario: Place single bet in Top Bets page
    Given user is in William Hill Sportsbook
    When the user logs in
    When user goes to 'Top Bets' page
    Then user clicks on the 'first' available selection in Top Bets page
    And the user places a bet with amount '0.05'
    And the user balance is updated

#  UPDATE: the step "When the user places a double bet with stake '0.05'"
  @smoke
  @critical
  Scenario: Double bet placement in Top Bets Tab
    Given user is in William Hill Sportsbook
    And the user logs in
    When user goes to 'Top Bets' page
    And sleep '1' seconds
    And user clicks on the 'first' available selection in Top Bets page
    And user clicks on the 'second' available selection in Top Bets page
    When the user places a double bet with stake '0.05'
    Then the user balance is updated