@secondary-header
Feature: Secondary Header not displayed


  @critical
  Scenario: Secondary Header not displayed on homepage
    Given user is in William Hill Sportsbook
    Then the secondary header 'is not' displayed


  @desktop-only
  @critical
  Scenario: Secondary Header not displayed on football page in desktop
    Given user is in William Hill Sportsbook
    When user goes to 'Football' page
    Then the secondary header 'is not' displayed


  @desktop-only
  Scenario: Secondary Header not displayed on football competition page in desktop
    Given user is in William Hill Sportsbook
    And user navigates to 'Football' 'Competitions' page
    Then the secondary header 'is not' displayed


  @desktop-only
  Scenario: Secondary Header not displayed on football daily list page in desktop
    Given user is in William Hill Sportsbook
    When user navigates to 'Football' 'Daily List' page
    Then the secondary header 'is not' displayed


  @desktop-only
  Scenario: Secondary Header not displayed on horse racing page in desktop
    Given user is in William Hill Sportsbook
    When user goes to 'Horse-Racing' page
    Then the secondary header 'is not' displayed


  @desktop-only
  Scenario: Secondary Header not displayed on greyhounds page in desktop
    Given user is in William Hill Sportsbook
    When user goes to 'Greyhounds' page
    Then the secondary header 'is not' displayed