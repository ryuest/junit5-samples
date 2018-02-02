@sports-menu
@live-suitable
Feature: Navigation in racing pages


  @next-off
  @racing-carousel
  @racing-menu
  @smoke
  @critical
  Scenario: Correct components are displayed in Horse Racing Homepage
    Given user is in William Hill Sportsbook
    When user goes to 'Horse Racing' page
    Then correct components are available in 'Racing Homepage' racing page


  @race-to-race-menu
  @alternate-meeting
  @smoke
  @critical
  Scenario: Correct components are displayed in Horse Racing Racecard
    Given user is in William Hill Sportsbook
    When user goes to 'Horse Racing' page
    And user clicks on the first future race in the meeting nav
    And correct components are available in 'Racecard' racing page


  @racing-menu
  @smoke
  @critical
  Scenario Outline: Correct components are displayed in Horse Racing
    Given user is in William Hill Sportsbook
    When user navigates to 'Horse Racing' '<racing_page>' page
    And correct components are available in '<racing_page>' racing page
  Examples:
  | racing_page  |
  | Specials     |
  | Top Bets     |
  | Future Races |

####Desktop view####
#Racing Homepage: secondary header without back button, no racing carousel, next off bar,  3 next off races, today and
#tomorrow tabs, racing menu, banners (if it applies)
#Racecard page: no secondary header, alternate meeting dropdown, Race To Race menu, racecard details
#Ante Post page: Ante Post is highlighted under Horse Racing in Sports Menu, rest of child pages appears,
#racing menu, racecard details, betslip
#Specials page: Specials is highlighted under Horse Racing in Sports Menu, rest of child pages appears,
#racing menu, racecard details, betslip
#Horse Racing Top Bets page: Horse Racing Top Bets is highlighted under Horse Racing in Sports Menu, rest of child pages appears,
#top bet event details, betslip

####Mobile view####
#Racing Homepage: secondary header with back button, racing carousel (ante post, top bets, specials), next off bar,
#today and tomorrow tabs, racing menu, banners (if it applies)
#Racecard page: secondary header with back button, alternate meeting dropdown, Race To Race menu, racecard details
#Ante Post page: secondary header with back button, racing menu
#Specials page: secondary header with back button, racing menu
#Horse Racing Top Bets page: secondary header with back button, top bets event details


  @next-off
  @racing-carousel
  @racing-menu
  Scenario: Correct components are displayed in Greyhounds Homepage
    Given user is in William Hill Sportsbook
    When user goes to 'Greyhounds' page
    Then correct components are available in 'Racing Homepage' racing page


  @race-to-race-menu
  @alternate-meeting
  Scenario: Correct components are displayed in Greyhounds Racecard
    Given user is in William Hill Sportsbook
    When user goes to 'Greyhounds' page
    And user clicks on the first future race in the meeting nav
    And correct components are available in 'Racecard' racing page


  @racing-menu
  Scenario Outline: Correct components are displayed in Greyhounds child pages
    Given user is in William Hill Sportsbook
    When user navigates to 'Greyhounds' '<racing_page>' page
    And correct components are available in '<racing_page>' racing page
    Examples:
      | racing_page  |
      | Future Races |
      | Specials     |