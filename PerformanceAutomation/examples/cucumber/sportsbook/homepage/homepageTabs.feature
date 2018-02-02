@homepage-tabs
@live-suitable
Feature: Homepage tabs verification with CEI

  @mobile-only
  @critical
  Scenario: Homepage tabs are displayed correctly on mobile
    Given user is in William Hill Sportsbook
    When Homepage tabs info is taken from CEI
    Then correct tabs are displayed in Homepage


  @desktop-only
  @critical
  Scenario: Homepage tabs are not displayed on desktop
    Given user is in William Hill Sportsbook
    Then homepage tabs are not displayed