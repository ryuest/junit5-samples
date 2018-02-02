@enhanced-section
@live-suitable
Feature: Enhanced Odds section in featured page

  @manual
  @todo
  Scenario: Add-Remove selections from enhanced odds section
    Given user is in William Hill Sportsbook
    When user goes to 'Football' page
    And user clicks on the first selection in the enhanced section
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added