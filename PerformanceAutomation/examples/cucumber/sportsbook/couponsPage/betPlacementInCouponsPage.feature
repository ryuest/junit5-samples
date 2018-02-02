@bet-placement
@live-suitable
Feature: Bet placement in Football Coupons page

  @critical
  Scenario: Place a single bet from a coupon
    Given user is in William Hill Sportsbook
    And the user logs in
    And user navigates to 'football' 'Coupons' page
    When user clicks on the '1st' coupon from the list
    Then user clicks on the first available selection
    And the user places a bet with amount '0.05'
    And the user balance is updated