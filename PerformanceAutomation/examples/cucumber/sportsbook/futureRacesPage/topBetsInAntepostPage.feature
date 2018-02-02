@top-bets
Feature: Top Bets widget in Future Races page

  @desktop-only
  Scenario Outline: View Horse Racing Top Bets widget from Ante Post page
    Given user is in William Hill Sportsbook
    When user navigates to '<racing>' 'Future Races' page
    Then the top bets widget 'is' displayed
  Examples:
    | racing       |
    | Horse Racing |
    | Greyhounds   |


  @mobile-only
  Scenario Outline: View Horse Racing Top Bets widget from Ante Post page
    Given user is in William Hill Sportsbook
    When user navigates to '<racing>' 'Future Races' page
    Then the top bets widget 'is not' displayed
  Examples:
    | racing       |
    | Horse Racing |
    | Greyhounds   |