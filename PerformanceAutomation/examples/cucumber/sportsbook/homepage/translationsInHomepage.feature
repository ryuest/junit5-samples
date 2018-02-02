@translations
@live-suitable
Feature: Translations in Homepage

  As a William Hill customer
  I want Titles on Sports Pages to be translated into <Language>
  So that I can read in my native language

  @smoke
  @critical
  Scenario: Sports Homepage shows correct links in German site
    Given user is in William Hill Sportsbook
    When user switches to 'German'
    Then HomePage is displayed in 'German'
    And the 'German' flag icon is displayed in the Home Page
    And user switches to 'English'
    And HomePage is displayed in 'English'
    And the 'English' flag icon is displayed in the Home Page


  Scenario Outline: Switch language in Homepage
    Given user is in William Hill Sportsbook
    When user switches to '<language>'
    Then HomePage is displayed in '<language>'
    And the '<language>' flag icon is displayed in the Home Page
    And user switches to '<language2>'
    And HomePage is displayed in '<language2>'
    And the '<language2>' flag icon is displayed in the Home Page
    Examples:
      | language | language2 |
      | Greek    | English   |
      | Japanese | English   |
      | English  | English   |
      | Russian  | English   |


  @critical
  Scenario: Sports Homepage displays the link in correct language
    Given user is in William Hill Sportsbook
    When user switches to 'German'
    Then all the links are in 'German' in the Home Page


  Scenario Outline: Sports Homepage displays the link in correct language
    Given user is in William Hill Sportsbook
    When user switches to '<language>'
    Then all the links are in '<language>' in the Home Page
    Examples:
      | language |
      | Greek    |
      | Japanese |
      | English  |
      | Russian  |


  @search
  Scenario Outline: Search Page Translated
    Given user is in William Hill Sportsbook
    And user switches to '<language>'
    When user searches for 'Man'
    Then searching results have links to '<language>'
    Examples:
      | language |
      | English  |
      | German   |
      | Greek    |
      | Japanese |
      | Russian  |


  @login
  Scenario Outline: Sports Home page displayed in correct language after login and logout
    Given user is in William Hill Sportsbook
    And user switches to '<language>'
    And the user logs in
    Then HomePage is displayed in '<language>'
    When the user logs out
    Then HomePage is displayed in '<language>'
    Examples:
      | language |
      | German   |
      | Greek    |
      | Japanese |
      | English  |
      | Russian  |


  Scenario Outline: Sports Home page displayed the flag grayed out
    Given user is in William Hill Sportsbook
    And user switches to '<language>'
    Then '<language>' flag is grayed out
    Examples:
      | language |
      | German   |
      | Greek    |
      | Japanese |
      | English  |
      | Russian  |


  @manual
  @todo
  @sports-menu
  Scenario Outline: Pages and child pages are correctly translated in Sports Menu
    Given user is in William Hill Sportsbook
    When user switches to '<language>'
    Then 'Home' in Sports Menu is displayed as '<Home>'
    And 'In-Play' in Sports Menu is displayed as '<In-Play>'
    And 'Top Bets' in Sports Menu is displayed as '<Top Bets>'
    And 'Football' in Sports Menu is displayed as '<Football>'
    And 'Competitions' in Sports Menu is displayed as '<Competitions>'
    And 'Horse Racing' in Sports Menu is displayed as '<Horse Racing>'
    And 'Ante Post' in Sports Menu is displayed as '<Ante Post>'
    Examples:
      | language | Home | In-Play | Top Bets | Football | Competitions | Horse Racing | Ante Post |
      | German   ||||||||
      | Greek    ||||||||
      | Japanese ||||||||
      | English  ||||||||
      | Russian  ||||||||