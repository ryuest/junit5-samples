@streaming
@bet-placement
Feature: Validate streaming is available after placing one pound bet

  @critical
  Scenario: Streaming is available after placing a one pound bet in Horse Racing
    Given a new logged in user
    And user goes to 'Horse-Racing' page
    And user clicks on the first race in the meeting left nav
    And streaming is available for the event in 'Horse-Racing'
    When user clicks on the first available selection
    And sleep '2' seconds
    Then race event message is 'Bet £1.00 or more to watch this race live'
    And a yellow TV icon is not displayed
    When the user places a bet with amount '1'
    And sleep '2' seconds
    Then race event message is 'Click here to watch the race'
    And a yellow TV icon is displayed


  Scenario: Streaming is available after placing a one pound bet in Greyhounds
    Given a new logged in user
    And user goes to 'Greyhounds' page
    And user clicks on the first race in the meeting left nav
    And streaming is available for the event in 'Greyhounds'
    When user clicks on the first available selection
    And sleep '2' seconds
    Then race event message is 'Bet £1.00 or more to watch this race live'
    And a yellow TV icon is not displayed
    When the user places a bet with amount '1'
    And sleep '2' seconds
    Then race event message is 'Click here to watch the race'
    And a yellow TV icon is displayed


  Scenario Outline: Streaming is not available after placing less than one pound bet
    Given a new logged in user
    And user goes to '<sport>' page
    And user clicks on the first race in the meeting left nav
    And streaming is available for the event in '<sport>'
    When user clicks on the first available selection
    Then race event message is 'Bet £1.00 or more to watch this race live'
    And a yellow TV icon is not displayed
    When the user places a bet with amount '0.5'
    Then race event message is 'Bet £1.00 or more to watch this race live'
    And a yellow TV icon is not displayed
    Examples:
      | sport        |
      | Horse-Racing |
      | Greyhounds   |


  @manual
  @todo
  Scenario Outline: Streaming is available after placing half a pound bet on an Each Way bet
    Given user is in William Hill Sportsbook
    And user goes to '<sport>' page
    And user clicks on the first race in the meeting left nav
    And streaming is available for the event in '<sport>'
    When user clicks on the first available selection
    Then race event message is 'Bet £1.00 or more to watch this race live'
    And a yellow TV icon is not displayed
    When the user places a bet with amount '0.5'
    And the user marks the selection as Each Way
    And sleep '1' seconds
    Then race event message is 'Click here to watch the race'
    And a yellow TV icon is displayed
    Examples:
      | sport        |
      | Horse-Racing |
      | Greyhounds   |


  Scenario Outline: Perform full page load (refresh) after placing a £1.00 bet
    Given a new logged in user
    And user goes to '<sport>' page
    And user clicks on the first race in the meeting left nav
    And streaming is available for the event in '<sport>'
    When user clicks on the first available selection
    Then race event message is 'Bet £1.00 or more to watch this race live'
    And a yellow TV icon is not displayed
    When the user places a bet with amount '1'
    And sleep '1' seconds
    Then race event message is 'Click here to watch the race'
    And a yellow TV icon is displayed
    And the user refresh the page
    And race event message is 'Click here to watch the race'
    And a yellow TV icon is displayed
    Examples:
      | sport        |
      | Horse-Racing |
      | Greyhounds   |