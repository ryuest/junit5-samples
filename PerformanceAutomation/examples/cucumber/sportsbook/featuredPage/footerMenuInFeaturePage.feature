@footer-menu
@live-suitable
Feature: Footer menu in Football Homepage

  @manual
  @mobile-only
  Scenario: Footer menu disappears while scrolling in Football Homepage
    Given user is in William Hill Sportsbook
    When user goes to 'Football' page
    Then scroll down and verify that footer menu disappears for a brief time