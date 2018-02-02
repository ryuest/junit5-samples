@racing-menu
Feature: Components in Antepost page

  @sports-menu
  @critical
  Scenario: Correct components are in future races page
    Given user is in William Hill Sportsbook
    When user navigates to 'Horse Racing' 'Future Races' page
    Then correct components are available in 'Future Races' racing page


  Scenario Outline: AntePost Header remove verification
    Given user is in William Hill Sportsbook
    When user navigates to '<racing>' 'Future Races' page
    Then the left racing navigation is displayed
    And verifies that the Antepost Header Section is not displayed
  Examples:
  | racing       |
  | Horse Racing |
  | Greyhounds   |


  @desktop-only
  Scenario Outline: AntePost Race To Race removed
    Given user is in William Hill Sportsbook
    When user navigates to '<racing>' 'Future Races' page
    Then user click on each event in first expanded group and Race To Race menu is not displayed
  Examples:
    | racing       |
    | Horse Racing |
    | Greyhounds   |