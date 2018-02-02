@top-bets
Feature: Top Bets in racing homepage

  @manual
  @todo
  @critical
  @mobile-only
  Scenario Outline: Top Bets icon is available in horse racing homepage mobile
    Given user is in William Hill Sportsbook
    When user goes to '<racing>' page
    Then top bets icon '<displayed>' displayed in the carousel
  Examples:
  | racing       | displayed |
  | Horse Racing | is        |
  | Greyhounds   | is not    |

  @critical
  @desktop-only
  Scenario Outline: View Top Bets available in Racing homepage
    Given user is in William Hill Sportsbook
    When user goes to '<racing>' page
    Then the top bets widget 'is' displayed
  Examples:
  | racing       |
  | Horse Racing |
  | Greyhounds   |