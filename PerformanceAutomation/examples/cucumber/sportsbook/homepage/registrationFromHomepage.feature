@registration
@live-suitable
Feature: Registration from Homepage

  @smoke
  @critical
  Scenario: Check Join pop up is displayed in homepage
    Given user is in William Hill Sportsbook
    When the user clicks on 'Join' button
    Then join form is displayed


  @critical
  @desktop-only
  Scenario: Full registration from homepage in desktop
    Given user is in William Hill Sportsbook
    Then user clicks on join button in the header
    And fills the registration form correctly
    And user close the Deposit window
    Then the user is logged 'in'



  @critical
  @mobile-only
  Scenario: Full registration from homepage in mobile
    Given user is in William Hill Sportsbook
    Then user clicks on join button in the header
    And fills the registration form correctly
    And user click the back button from Deposit
    Then the user is logged 'in'