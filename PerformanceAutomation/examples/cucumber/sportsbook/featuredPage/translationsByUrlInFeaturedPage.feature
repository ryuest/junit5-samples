@translations
@live-suitable
Feature: Translations by URL in Featured page

  As a William Hill customer
  I want Titles on Sports Pages to be translated into <Language>
  So that I can read in my native language

  @critical
  Scenario: Sports translations are correctly translated in the url accessing by URL
    Given user is in William Hill Sportsbook in German
    When user goes to 'Football' page
    Then the sport is translated to 'fußball' in the url


  Scenario Outline: Sports translations are correctly translated in the url accessing by URL
    Given user is in William Hill Sportsbook in <language>
    When user goes to '<sport>' page
    Then the sport is translated to '<translatedSport>' in the url
    Examples:
      | sport         |language| translatedSport |
      | Football      |Greek   |ποδόσφαιρο       |
      | Football      |Japanese|フットボール      |
      | Football      |English |football         |
      | Football      |Russian |футбол           |
      | Darts         |German  |darts            |
      | Darts         |Greek   |νταρτς           |
      | Darts         |Japanese|ダーツ           |
      | Darts         |English |darts            |
      | Darts         |Russian |дартс            |