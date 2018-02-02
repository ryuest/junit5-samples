@bet-placement
Feature: Bet placement in Racing Specials page

  Scenario Outline: Add-Remove selections from Specials page and clear betslip
    Given user is in William Hill Sportsbook
    And user navigates to '<racing>' 'Specials' page
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added
    Examples:
      | racing       |
      | Horse-Racing |
      | Greyhounds   |


  @desktop-only
  Scenario Outline: Add-Remove selections from Specials page with odds button
    Given user is in William Hill Sportsbook
    And user navigates to '<racing>' 'Specials' page
    When user clicks on the first available selection
    Then betslip has '1' selections added
    When user clicks on the first available selection
    Then betslip has '0' selections added
    Examples:
      | racing       |
      | Horse-Racing |
      | Greyhounds   |


  @critical
  Scenario: Place single bet in Horse Racing Specials page
    Given user is in William Hill Sportsbook
    And the user logs in
    When user navigates to 'Horse Racing' 'Specials' page
    And user clicks on the first available selection
    And the user places a bet with amount '0.05'
    And the user balance is updated


  Scenario: Place single bet in Greyhounds Specials page
    Given user is in William Hill Sportsbook
    And the user logs in
    When user navigates to 'Greyhounds' 'Specials' page
    And user clicks on the first available selection
    And the user places a bet with amount '0.05'
    And the user balance is updated