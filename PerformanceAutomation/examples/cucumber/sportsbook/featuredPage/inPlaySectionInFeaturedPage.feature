@inplay-section
@live-suitable
Feature: In-Play section in featured page

  Scenario: Add-Remove selections from in-play section
    Given user is in William Hill Sportsbook
    When user goes to 'Football' page
    And user clicks on the first selection in the in-play section
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added