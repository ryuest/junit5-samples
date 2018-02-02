@live-suitable
@mobile-only
@acca-price-notification
Feature: Acca Price content verification

  @critical
  Scenario: Double is displayed when two allowed selections are added
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'In-Play' page
    Then user adds '2' of the 1st selection/s from the Market/s to the Betslip
    And the ACCA message on the Footer says 'Double'


  Scenario: Treble is displayed when three allowed selections are added
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'In-Play' page
    Then user adds '3' of the 1st selection/s from the Market/s to the Betslip
    And the ACCA message on the Footer says 'Treble'


  Scenario: Accumulator 4 is displayed when four allowed selections are added
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'In-Play' page
    Then user adds '4' of the 1st selection/s from the Market/s to the Betslip
    And the ACCA message on the Footer says 'Accumulator (4)'


  @manual
  Scenario: Accumulator 25 is displayed when 25 allowed selections are added
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'In-Play' page
    Then user adds '25' of the 1st selection/s from the Market/s to the Betslip
    And the ACCA message on the Footer says 'Accumulator (25)'