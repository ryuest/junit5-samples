@highlights-section
@live-suitable
Feature: Highlights section in featured page

  Scenario: Add-Remove selections from highlights section
    Given user is in William Hill Sportsbook
    When user goes to 'Football' page
    And user clicks on the first selection in the highlights section
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added