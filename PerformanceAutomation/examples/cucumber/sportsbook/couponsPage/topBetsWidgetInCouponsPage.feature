@top-bets
  Feature: Top Bets widget in Coupons page

  @critical
  @desktop-only
  Scenario: Top Bets widget is available in coupons page in desktop
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'Coupons' page
    Then the top bets widget 'is' displayed


  @critical
  @mobile-only
  Scenario: Top Bets widget is not available in coupons page in mobile
    Given user is in William Hill Sportsbook
    When user navigates to 'football' 'Coupons' page
    Then the top bets widget 'is not' displayed