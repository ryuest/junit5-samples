@secondary-header
@back-button
@mobile-only
Feature: Secondary Header and back button on mobile

  @critical
  Scenario: Title and Back functionality is working correctly on Football Homepage
    Given user is in William Hill Sportsbook
    And user goes to 'Football' page
    And the page secondary header is 'Football Betting Highlights'
    When user clicks on back button
    Then correct components are displayed in Home page
    And the secondary header 'is not' displayed


  Scenario: Title and Back functionality is working correctly on Greyhounds Homepage
    Given user is in William Hill Sportsbook
    And user goes to 'Greyhounds' page
    And the page secondary header is 'Greyhounds Racing'
    When user clicks on back button
    Then correct components are displayed in Home page
    And the secondary header 'is not' displayed


  @critical
  Scenario: Title and Back functionality is working correctly on Horse Racing Homepage
    Given user is in William Hill Sportsbook
    And user goes to 'Horse-Racing' page
    And the page secondary header is 'Horse Racing'
    When user clicks on back button
    Then correct components are displayed in Home page
    And the secondary header 'is not' displayed