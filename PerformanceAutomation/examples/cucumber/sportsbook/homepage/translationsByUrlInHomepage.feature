@translations
@live-suitable
Feature: Translations by URL In Homepage

  As a William Hill customer
  I want Titles on Sports Pages to be translated into <Language>
  So that I can read in my native language

  @critical
  Scenario: Sports Homepage shows correct links in German accessing by URL
    Given user is in William Hill Sportsbook in German
    Then HomePage is displayed in 'German'
    And the 'German' flag icon is displayed in the Home Page
    When user switches to 'English'
    Then HomePage is displayed in 'English'
    And the 'English' flag icon is displayed in the Home Page


  Scenario Outline: Sports Homepage shows correct links accessing by URL
    Given user is in William Hill Sportsbook in <language>
    Then HomePage is displayed in '<language>'
    And the '<language>' flag icon is displayed in the Home Page
    When user switches to '<language2>'
    Then HomePage is displayed in '<language2>'
    And the '<language2>' flag icon is displayed in the Home Page
    Examples:
      | language | language2 |
      | Greek    | English   |
      | Japanese | English   |
      | English  | German    |
      | Russian  | English   |


  @critical
  Scenario: Sports Homepage displays the link in German accessing by URL
    Given user is in William Hill Sportsbook in German
    When user switches to 'German'
    Then all the links are in 'German' in the Home Page


  Scenario Outline: Sports Homepage displays the link in correct language accessing by URL
    Given user is in William Hill Sportsbook in <language>
    When user switches to '<language>'
    Then all the links are in '<language>' in the Home Page
    Examples:
      | language |
      | Greek    |
      | Japanese |
      | English  |
      | Russian  |

  @search
  Scenario Outline: Search Page Translated accessing by URL
    Given user is in William Hill Sportsbook in <language>
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
  Scenario Outline: Sports Home page displayed in correct language after login and logout accessing by URL
    Given user is in William Hill Sportsbook in <language>
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


  Scenario Outline: Sports Home page displayed the flag grayed out accessing by URL
    Given user is in William Hill Sportsbook in <language>
    And user switches to '<language>'
    Then '<language>' flag is grayed out
    Examples:
      | language |
      | German   |
      | Greek    |
      | Japanese |
      | English  |
      | Russian  |