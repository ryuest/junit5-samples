@racing-menu
@sports-menu
Feature: Components in Specials page

  @critical
  Scenario: Correct components are in specials races page
    Given user is in William Hill Sportsbook
    When user navigates to 'Horse Racing' 'Specials' page
    Then correct components are available in 'Specials' racing page