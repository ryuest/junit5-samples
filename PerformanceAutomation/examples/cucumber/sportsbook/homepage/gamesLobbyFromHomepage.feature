@games-lobby
@live-suitable
@mobile-only
Feature: Games Lobby Overlay in Homepage

  Scenario: Top Games icon non EN-GB Customer
    Given user is in William Hill Sportsbook
    When user switches to 'Japanese'
    Then the top games menu is not displayed in the bottom menu


  @manual
  @todo
  Scenario: Game launched from Games Lobby - Not Logged In Customers - Embedded game
    Given user is in William Hill Sportsbook
    When user clicks on 'top games' in the footer menu
    And selects an embedded game from the top games overlay
    Then login form is displayed
    When the user logs in
    Then the embedded game is launched in the same top games overlay


  Scenario: Game launched from Games Lobby - Not Logged In Customers - Non Embedded game
    Given user is in William Hill Sportsbook
    When user clicks on 'top games' in the footer menu
    And selects a non embedded game from the top games overlay
    And user changes to the latest browser tab
    Then login form las vegas is displayed
    When the user logs in in las vegas
    Then the non embedded game is displayed


  @manual
  @todo
  Scenario: Embedded Game in Games Lobby launches directly in Overlay
    Given user is in William Hill Sportsbook
    And the user logs in
    When user clicks on 'top games' in the footer menu
    And selects an embedded game from the top games overlay
    Then the embedded game is displayed
    And the top games overlay is not displayed
    When the embeded game is closed
    Then the top games overlay is displayed
    When user clicks on 'top games' in the footer menu
    And selects an embedded game from the top games overlay
    Then the embedded game is launched from the background in the same top games overlay


  Scenario: Non Embedded Games in Games Lobby launches in New Window
    Given user is in William Hill Sportsbook
    And the user logs in
    When user clicks on 'top games' in the footer menu
    And selects a non embedded game from the top games overlay
    And user changes to the latest browser tab
    Then the non embedded game is displayed


    Scenario: Closing the Games Lobby Overlay takes customer to previous Sportsbook page
      Given user is in William Hill Sportsbook
      And user goes to 'Football' page
      And user clicks on 'top games' in the footer menu
      When user close top games overlay
      Then the top games overlay is not displayed
      And the feature page is displayed


  @manual
  @todo
  Scenario: Check if game titles are displayed
    Given user is in William Hill Sportsbook
    When user clicks on 'top games' in the footer menu
    Then game titles are displayed correctly


  @manual
  @todo
  Scenario Outline: : Check if game titles are displayed on different languages
    Given user is in William Hill Sportsbook in <language>
    When user clicks on 'top games' in the footer menu
    Then game displayed are the expected ones
    Examples:
      | language |
      | German   |
      | Greek    |
      | Japanese |
      | English  |
      | Russian  |