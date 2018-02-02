@sports-menu
Feature: Sports Menu navigation

  @manual
  @todo
  @critical
  Scenario: Navigation to Homepage through Sports Menu
    Given user is in William Hill Sportsbook
    And user goes to 'Football' page
    When user goes to 'Homepage' page
    Then correct components are available in 'Homepage' page


  @manual
  @todo
  @critical
  Scenario: Navigation to In-Play through Sports Menu
    Given user is in William Hill Sportsbook
    When user goes to 'In-Play' page
    Then correct components are available in 'In-Play' page


  @manual
  @todo
  Scenario: Navigation to Promotions though Sports Menu
    Given user is in William Hill Sportsbook
    When user goes to 'Promotions' page
    Then correct components are available in 'Promotions' page


  @manual
  @todo
  Scenario Outline: Navigation to cross-sell games from Sports menu
    Given user is in William Hill Sportsbook
    Then user goes to '<game>' game
    And the '<game>' game is launched
    Examples:
      | game      |
      | Roulette  |
      | Blackjack |
      | Wish JP   |


  @manual
  @todo
  @desktop-only
  Scenario: Navigation to All In-Play from football in-play page
    Given user is in William Hill Sportsbook
    And only correct options are highlighted when 'Homepage' page is displayed
    When user goes to 'Football' page
    Then only correct options are highlighted when 'Football' page is displayed
    And user goes to 'In-Play' section
    And only correct options are highlighted when 'Football In-Play' page is displayed
    And user clicks on all sports in-play button
    And only correct options are highlighted when 'All In-Play' page is displayed


  @manual
  @todo
  @desktop-only
  Scenario: Navigation to football event from basketball top bets widget
    Given user is in William Hill Sportsbook
    When user goes to 'Basketball' page
    And user goes to 'Competitions' section
    And only correct options are highlighted when 'Basketball Competitions' page is displayed
    And the user clicks on the first football event in top bets widget
    And only correct options are highlighted when 'Football Event' page is displayed