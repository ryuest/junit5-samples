@bet-placement
Feature: Bet placement in Football Pre-Match Event Page

  @live-suitable
  Scenario: Add selection from pre-match event page
    Given user is in William Hill Sportsbook
    And user goes to 'Football' page
    And the user clicks on the first 'highlight' event
    When the user clicks the first active selection in the event page
    Then the selection is added to the betslip
    And betslip has '1' selections added


  @live-suitable
  Scenario: Add-Remove selection to betslip from Football Pre-Match event page and clear betslip
    Given user is in William Hill Sportsbook
    And user goes to 'Football' page
    And the user clicks on the first 'highlight' event
    When the user clicks the first active selection in the event page
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added


  @desktop-only
  Scenario: Add-remove selection to betslip from Football Pre-Match event page and with odds button
    Given user is in William Hill Sportsbook
    And user goes to 'Football' page
    And the user clicks on the first 'highlight' event
    When the user clicks the first active selection in the event page
    Then betslip has '1' selections added
    When the user clicks the first active selection in the event page
    Then betslip has '0' selections added


  @critical
  @live-suitable
  Scenario: Place a single bet in a Football Pre-Match event
    Given user is in William Hill Sportsbook
    And user goes to 'Football' page
    And the user logs in
    When the user clicks on the first 'highlight' event
    And the user clicks the first active selection in the event page
    And the user places a bet with amount '0.05'
    Then the user balance is updated


  @smoke
  @critical
  Scenario: Handicap bet placement in pre-match event page
    Given a 'football' 'pre-match' event with the following markets:
      | marketName           | display | bir | handicap | selections   |
      | \Handicap -1\        | Yes     | No  |   -1     |Home/Away/Line|
    And user is in William Hill Sportsbook
    And the user logs in
    And user goes to created 'football' event
    When user clicks on the first available selection
    And sleep '2' seconds
    And the user places a bet with amount '0.05'
    Then the user balance is updated