@secondary-header
@back-button
@mobile-only
Feature: Secondary Header and back button on mobile 2

  @manual
  @todo
  @critical
  Scenario: Title and Back functionality is working correctly on Football Competitions page
    Given user is in William Hill Sportsbook
    And user navigates to 'Football' 'Competitions' page
    And the page secondary header is 'Football Betting Competitions'
    When user clicks on back button
    Then the page secondary header is 'Football Betting Highlights'
    And user clicks on back button
    And the 'homepage' page is displayed
    And the secondary header 'is not' displayed


  @manual
  @todo
  Scenario: Title and Back functionality is working correctly on Football Daily List page
    Given user is in William Hill Sportsbook
    And user navigates to 'Football' 'Daily List' page
    Then the page secondary header is 'Daily List Football'
    When user clicks on back button
    Then the page secondary header is 'Football Betting Highlights'
    And user clicks on back button
    And the 'homepage' page is displayed
    And the secondary header 'is not' displayed