@top-bets
@translations
Feature: Horse Racing Top Bets Urls

  Scenario Outline: Top Bets are translated in the url
    Given user is in William Hill Sportsbook
    And user switches to '<language>'
    When user navigates to 'Horse-Racing' 'Top Bets' page by url
    Then the sport is translated to '<urlContains>' in the url
    And the Page Title displayed is '<pageTitle>'
    And the top bets page is displayed

    Examples:
      |language|pageTitle                         | urlContains                           |
      |English |Horse Racing Top Bets             | betting/en-gb/horse-racing/top-bets   |
      |Greek   |Ιπποδρομίες – Δημοφιλή Στοιχήματα | betting/el-gr/horse-racing/top-bets   |
      |Japanese|競馬 トップベット                  | betting/ja-jp/horse-racing/top-bets   |
      |Russian |Топ-ставки на скачки              | betting/ru-ru/horse-racing/top-bets   |
      |Swedish |Hästar Topp Bets                  | betting/sv-se/horse-racing/top-bets   |