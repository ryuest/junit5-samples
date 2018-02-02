@live-suitable
@login
Feature: Login in Sportsbook Homepage

  @smoke
  @critical
  Scenario: User logs in homepage
    Given user is in William Hill Sportsbook
    When the user logs in
    Then the user is logged 'in'


  @smoke
  @critical
  Scenario: User logs out homepage
    Given user is in William Hill Sportsbook
    And the user logs in
    When the user logs out
    Then the user is logged 'out'


  @critical
  Scenario: Login component loaded properly
    Given user is in William Hill Sportsbook
    Then the login component is loaded properly