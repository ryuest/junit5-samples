@push
Feature: Market updates in Event Page

  @smoke
  @critical
  Scenario: Price update in pre-match event page
    Given a 'football' 'pre-match' event with the following markets:
      | marketName           | display | bir | handicap | selections   |
      | \Handicap -1\        | Yes     | No  |   -1     |Home/Away/Line|
    And user is in William Hill Sportsbook
    And user goes to created 'football' event
    And there is an active selection
    When the selection price is increased
    Then the price is increased
    And the selection is displayed in green