@bet-placement
@live-suitable
Feature: Bet Placement in Football In-Play Event Page
  
  Scenario: Add-Remove selections in betslip on In-Play event page and clear betslip
    Given user is in William Hill Sportsbook
    And user navigates to 'Football' 'In-Play' page
    When the user clicks on the first 'in-play' event
    And the user clicks the first active selection in the event page
    Then betslip has '1' selections added
    When user clear betslip
    Then betslip has '0' selections added


  @desktop-only
  Scenario: Add-Remove selections in betslip on In-Play event page with odds button
    Given user is in William Hill Sportsbook
    And user navigates to 'Football' 'In-Play' page
    And the user clicks on the first 'in-play' event
    When the user clicks the first active selection in the event page
    Then betslip has '1' selections added
    When the user clicks the first active selection in the event page
    Then betslip has '0' selections added


  @critical
  Scenario: Place a bet in an In-Play Event page
    Given user is in William Hill Sportsbook
    And user navigates to 'Football' 'In-Play' page
    And the user logs in
    When the user clicks on the first 'in-play' event
    And the user clicks the first active selection in the event page
    And the user places a bet with amount '0.05'
    Then the user balance is updated

