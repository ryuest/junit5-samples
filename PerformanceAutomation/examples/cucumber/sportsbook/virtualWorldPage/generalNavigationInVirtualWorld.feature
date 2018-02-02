@live-suitable
@virtual-carousel
@sports-menu
Feature: General Navigation in Virtual World page

  @critical
  Scenario: Correct components in Virtual World page
    Given user is in William Hill Sportsbook
    When user goes to 'Virtual World' page
    Then correct components are available in 'Virtual World' page


  @mobile-only
  @critical
  Scenario: Correct options available in Virtual World carousel
    Given user is in William Hill Sportsbook
    When user goes to 'Virtual World' page
    Then the virtual world carousel 'is' displayed
    And options in virtual world carousel are displayed in correct order
    And highlighted option in virtual world carousel is 'Featured'


  @mobile-only
  @smoke
  @critical
  Scenario: Options in Virtual World Carousel redirect to correct pages
    Given user is in William Hill Sportsbook
    When user goes to 'Virtual World' page
    Then the virtual world carousel 'is' displayed
    And user navigates through all options on the virtual world carousel


  @desktop-only
  @smoke
  @critical
  Scenario: Options in Virtual World menu redirect to correct pages
    Given user is in William Hill Sportsbook
    When user goes to 'Virtual World' page
    Then the virtual world carousel 'is not' displayed
    And user navigates through all virtual child pages in sports menu


  @desktop-only
  @critical
  Scenario Outline: Correct components in Virtual World page in desktop
    Given user is in William Hill Sportsbook
    And user goes to '<virtual_page>' virtual page
    Then correct components are available in 'Virtual World' page
    And option '<virtual_page>' is highlighted on sports menu
    Examples:
      | virtual_page                 |
      | Horse Racing - Flat          |
      | Horse Racing - National Hunt |
      | Football                     |
      | Greyhounds - Flat            |
      | Greyhounds - Hurdles         |
      | Motor racing                 |
      | Speedway                     |
      | Cycling                      |


  @mobile-only
  @critical
  Scenario Outline: Correct components in Virtual World page in mobile
    Given user is in William Hill Sportsbook
    And user goes to '<virtual_page>' virtual page
    Then correct components are available in 'Virtual World' page
    And highlighted option in virtual world carousel is '<virtual_page>'
    Examples:
      | virtual_page                 |
      | Horse Racing - Flat          |
      | Horse Racing - National Hunt |
      | Football                     |
      | Greyhounds - Flat            |
      | Greyhounds - Hurdles         |
      | Motor racing                 |
      | Speedway                     |
      | Cycling                      |