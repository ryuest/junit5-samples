@sports-menu
@live-suitable
Feature: Navigation in sport pages

  @sport-carousel
  @featured-tabs
  @smoke
  @critical
  Scenario: Navigation to football featured page
    Given user is in William Hill Sportsbook
    When user goes to 'Football' page
    Then correct components are available in 'Featured' page


  @daily-list-filter
  @alternate-market-menu
  @view-by-menu
  @competitions-tabs
  @coupons-menu
  @smoke
  @critical
  Scenario Outline: Navigation to child pages for football
    Given user is in William Hill Sportsbook
    When user navigates to 'Football' '<sport_page>' page
    Then correct components are available in '<sport_page>' page
  Examples:
  | sport_page   |
  | Daily List   |
  | Competitions |
  | In-Play      |
  | Coupons      |
#  | Stats        |

####Desktop view####
#Featured page: football is highlighted in Sports Menu, child pages appear (Daily List, In-Play, Coupons, Competitions
#and Stats), events list, betslip
#Daily List page: daily list is highlighted under Football in Sports Menu, child pages appear (Daily List, In-Play,
#Coupons, Competitions and Stats), daily list filter, events list, betslip
#Coupons page: coupons is highlighted under Football in Sports Menu, child pages appear (Daily List, In-Play, Coupons,
#Competitions and Stats), coupons menu, betslip
#Competitions page: competitions is highlighted under Football in Sports Menu, child pages appear (Daily List, In-Play,
#Coupons, Competitions and Stats), competitions tabs, events list, betslip
#Stats page: competitions is highlighted under Football in Sports Menu, child pages appear (Daily List, In-Play,
#Coupons, Competitions and Stats), stats content, betslip

####Mobile view####
#Featured page: secondary header with back button, sport carousel (no option highlighted), featured tabs
#Daily List page: secondary header with back button, daily list filter, events list
#In-Play page: secondary header with back button, events list
#Coupons page: secondary header with back button, coupons menu
#Competitions page: secondary header with back button, matches tab, outrights tab, events list

  @collections-menu
  @smoke
  @critical
  Scenario: Navigation to pre-match event page
    Given user is in William Hill Sportsbook
    When user goes to 'Top Bets' page
    And the user clicks on the first event
    And correct components are available in 'event' page
#Event page: football is highlighted in Sports Menu, child pages appear (Daily List, In-Play, Coupons, Competitions
#and Stats), events list, betslip

  @collections-menu
  @smoke
  @critical
  Scenario: Navigation to in-play event page
    Given user is in William Hill Sportsbook
    When user goes to 'In-Play' page
    And the user clicks on the first event
    Then correct components are available in 'event' page