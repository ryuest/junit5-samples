@footer-menu
Feature: Footer menu in Racing Home Page

  @manual
  @mobile-only
  Scenario Outline: Footer menu disappears while scrolling in racing page
    Given user is in William Hill Sportsbook
    When user goes to '<racing>' page
    Then scroll down and verify that footer menu disappears for a brief time
    Examples:
      | racing       |
      | Horse-Racing |
      | Greyhounds   |