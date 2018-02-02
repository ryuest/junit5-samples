@translations
@live-suitable
Feature: Translations by URL in Meetings page

  As a William Hill customer
  I want Titles on Sports Pages to be translated into <Language>
  So that I can read in my native language


  Scenario Outline: Sports translations are correctly translated in the url accessing by URL
    Given user is in William Hill Sportsbook in <language>
    When user goes to '<sport>' page
    Then the sport is translated to '<translatedSport>' in the url
    Examples:
      | sport      |language| translatedSport |
      | Greyhounds |German  |windhundrennen  |
      | Greyhounds |Greek   |κυνοδρομίες     |
      | Greyhounds |Japanese|ドッグレース     |
      | Greyhounds |English |greyhound       |
      | Greyhounds |Russian |собачьи-бега    |