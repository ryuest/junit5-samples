@top-bets
Feature: Top Bets in Racecard page

  @mobile-only
  Scenario Outline: Top Bets icon is available in horse racing homepage mobile
    Given user is in William Hill Sportsbook
    And user goes to '<racing>' page
    When user clicks on the first future race in the meeting nav
    Then the top bets widget 'is not' displayed
  Examples:
  | racing       |
  | Horse Racing |
  | Greyhounds   |

  @desktop-only
  Scenario Outline: View Top Bets available in Racing homepage
    Given user is in William Hill Sportsbook
    And user goes to '<racing>' page
    When user clicks on the first future race in the meeting nav
    Then the top bets widget 'is' displayed
  Examples:
  | racing       |
  | Horse Racing |
  | Greyhounds   |