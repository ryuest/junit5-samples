@streaming
@live-suitable
Feature: Racing Streaming 'My WHTV Races'

  @manual
  @todo
  Scenario Outline: Checks that My WHTV races is not available when are not logged in
    Given user is in William Hill Sportsbook
    And user goes to '<sport>' page
    When user opens 'My WHTV Races' group in the meetings nav
    Then My WHTV Races message in the meeting left nav is 'Please login to see a list of the races that you have qualified to watch'
    Examples:
      | sport        |
      | Horse-Racing |
      | Greyhounds   |

  @manual
  @todo
  Scenario Outline: Checks the message in the My WHTV races group
    Given user is in William Hill Sportsbook
    And the user logs in
    And user goes to '<sport>' page
    When user opens 'My WHTV Races' group in the meeting nav
    Then My WHTV Races message in the meeting nav is 'Place a bet of £1 or more on any UK or Irish race to qualify for streaming'
    Examples:
      | sport        |
      | Horse-Racing |
      | Greyhounds   |

  @manual
  @todo
  @bet-placement
  @critical
  Scenario: Qualified races highlighted
    Given a new logged in user
    And user goes to 'Horse-Racing' page
    And user clicks on the first race in the meetings nav
    And streaming is available for the event in 'horse-racing'
    When user clicks on the first available selection
    Then race event message is 'Bet £1.00 or more to watch this race live'
    And a yellow TV icon is not displayed
    When the user places a bet with amount '1'
    And sleep '2' seconds
    Then race event message is 'Click here to watch the race'
    And a yellow TV icon is displayed
    And sleep '2' seconds
    And the first race in the meeting navigation on the meetings page is displayed in yellow
    And user clicks on the second race in the meeting nav
    And streaming is available for the event in 'horse-racing'
    When user clicks on the first available selection
    Then race event message is 'Bet £1.00 or more to watch this race live'
    And a yellow TV icon is not displayed
    When the user places a bet with amount '1'
    And sleep '2' seconds
    Then race event message is 'Click here to watch the race'
    And a yellow TV icon is displayed
    And the second race in the meeting navigation is displayed in yellow
    When user opens 'My WHTV Races' group in the meeting nav
    Then My WHTV Races section has '2' qualified races highlighted in yellow