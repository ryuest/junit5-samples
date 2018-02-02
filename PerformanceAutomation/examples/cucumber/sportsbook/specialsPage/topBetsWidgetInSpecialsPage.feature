@top-bets
Feature: Top Bets widget in horse racing specials page

  @mobile-only
  Scenario Outline: Top Bets widget is not available in horse racing specials page mobile
    Given user is in William Hill Sportsbook
    When user navigates to '<racing>' 'Specials' page
    Then the top bets widget 'is not' displayed
  Examples:
    | racing       |
    | Horse Racing |
    | Greyhounds   |


  @desktop-only
  Scenario Outline: Top Bets widget is not available in horse racing specials page mobile
    Given user is in William Hill Sportsbook
    When user navigates to '<racing>' 'Specials' page
    Then the top bets widget 'is' displayed
  Examples:
    | racing       |
    | Horse Racing |
    | Greyhounds   |