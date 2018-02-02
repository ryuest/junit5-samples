@bet-placement
Feature: Bet placement in Ante Post page

  Scenario Outline: Add-Remove selections to betslip from racing Ante Post page and clear betslip
    Given user is in William Hill Sportsbook
    And user navigates to '<racing>' 'Future Races' page
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added
    Examples:
    | racing       |
    | Horse Racing |
    | Greyhounds   |

  @desktop-only
  Scenario Outline: Add-Remove selections to betslip from racing Ante Post page with odds button
    Given user is in William Hill Sportsbook
    And user navigates to '<racing>' 'Future Races' page
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clicks on the first available selection
    Then betslip has '0' selections added
    Examples:
      | racing       |
      | Horse Racing |
      | Greyhounds   |


  @critical
  Scenario: Place single bet in Horse Racing Ante Post page
    Given user is in William Hill Sportsbook
    And the user logs in
    And user navigates to 'Horse Racing' 'Future Races' page
    When user clicks on the first available selection
    And the user places a bet with amount '0.05'
    Then the user balance is updated


  Scenario: Place single bet in Greyhounds Ante Post page
    Given user is in William Hill Sportsbook
    And the user logs in
    And user navigates to 'Greyhounds' 'Future Races' page
    When user clicks on the first available selection
    And the user places a bet with amount '0.05'
    Then the user balance is updated