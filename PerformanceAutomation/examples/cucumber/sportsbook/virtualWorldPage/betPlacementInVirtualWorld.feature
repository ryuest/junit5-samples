@bet-placement
@live-suitable
Feature: Bet Placement in Virtual World page

  @critical
  @mobile-only
  Scenario: Bet placement from Virtual featured page
    Given user is in William Hill Sportsbook
    And the user logs in
    And user goes to 'Virtual World' page
    And sleep '1' seconds
    When user clicks on the last available selection on Virtual World
    And the user places a bet with amount '0.05'
    Then the user balance is updated


  @smoke
  Scenario: Bet placement from Virtual Horse Racing Flat page
    Given user is in William Hill Sportsbook
    And the user logs in
    When user goes to 'Horse Racing - Flat' virtual page
    And user clicks on Next Off Races in the meeting menu
    And user clicks on the last available selection on Virtual World
    And the user places a bet with amount '0.05'
    Then the user balance is updated


  @critical
  Scenario: Bet placement from Virtual Football page
    Given user is in William Hill Sportsbook
    And the user logs in
    When user goes to 'Football' virtual page
    And user clicks on the '1st' Football Market on the Virtual World
    And user clicks on the last available selection on Virtual World
    And the user places a bet with amount '0.05'
    Then the user balance is updated


  Scenario Outline: Bet placement from Virtual Horse Racing National Hunt page
    Given user is in William Hill Sportsbook
    And the user logs in
    When user goes to '<virtual_page>' virtual page
    And user clicks on Next Off Races in the meeting menu
    And user clicks on the last available selection on Virtual World
    And the user places a bet with amount '0.05'
    Then the user balance is updated
  Examples:
    | virtual_page                 |
    | Horse Racing - National Hunt |
    | Greyhounds - Flat            |
    | Greyhounds - Hurdles         |
    | Motor racing                 |
    | Speedway                     |
    | Cycling                      |