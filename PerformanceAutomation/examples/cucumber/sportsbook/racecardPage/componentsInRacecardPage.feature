@sports-menu
@race-to-race-menu
@alternate-meeting
@live-suitable
Feature: Components in Racecard page

  @critical
  Scenario: Correct components are in racecard page
    Given user is in William Hill Sportsbook
    And user goes to 'Horse Racing' page
    When user clicks on the first future race in the meeting nav
    Then correct components are available in 'Racing Homepage' racing page