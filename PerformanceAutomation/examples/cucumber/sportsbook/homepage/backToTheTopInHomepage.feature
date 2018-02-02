@back-to-top
@live-suitable
Feature: Back To Top Button in Homepage

  @critical
  Scenario: Back To Top button is clickable and scrolls to top
    Given user is in William Hill Sportsbook
    When user scroll down to the bottom
    And the user clicks on back to the top button in the footer
    Then the page scroll is in the top