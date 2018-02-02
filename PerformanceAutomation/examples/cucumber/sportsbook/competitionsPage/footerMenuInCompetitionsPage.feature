@footer-menu
Feature: Footer menu in Competitions page

  @manual
  @mobile-only
  Scenario: Footer menu disappears while scrolling in competitions page
    Given user is in William Hill Sportsbook
    When user navigates to 'Football' 'Competitions' page
    Then scroll down and verify that footer menu disappears for a brief time