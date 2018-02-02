@live-suitable
@acca-price-notification
Feature: Acca Price display verification

  @mobile-only
  @critical
  Scenario: Acca price notification not displayed when only one bet is added
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'In-Play' page
    And user adds '1' selection/s to the Betslip
    Then ACCA popup 'is not' displayed on the Bottom Bar


  @mobile-only
  @critical
  Scenario: Acca price notification displayed when two allowed selections are added
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'In-Play' page
    And user adds '2' of the 1st selection/s from the Market/s to the Betslip
    Then ACCA popup 'is' displayed on the Bottom Bar


  @desktop-only
  @critical
  Scenario: Acca price notification not displayed in desktop
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'In-Play' page
    And user adds '2' of the 1st selection/s from the Market/s to the Betslip
    Then ACCA popup 'is not' displayed on the Bottom Bar


  @mobile-only
  Scenario: Acca price notification disappears when scrolling
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'In-Play' page
    And user adds '2' of the 1st selection/s from the Market/s to the Betslip
    Then ACCA popup 'is' displayed on the Bottom Bar
    And user scroll down to the bottom
    Then ACCA popup 'is not' displayed on the Bottom Bar


  @mobile-only
  Scenario: Acca price notification disappears over time
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'In-Play' page
    And user adds '2' of the 1st selection/s from the Market/s to the Betslip
    Then ACCA popup 'is' displayed on the Bottom Bar
    And sleep '8' seconds
    Then ACCA popup 'is not' displayed on the Bottom Bar