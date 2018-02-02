@search
Feature: SportsBook Search By SportName (SBSD-58/56)

  As a customer
  I want to search for a sport by sport name
  So that I can quickly navigate to a sport

  Background:
    Given user is in William Hill Sportsbook

  Scenario Outline: Results are displayed as links
    Given a 'basketball' 'in-play' event 'basketTeam1 vs basketTeam2' with the following markets:
      | marketName    | display | bir | handicap | selections     |
      | \Alternative Margin Of Victory\ | Yes     | Yes  | -1       | Home/Away/Line |
    When user searches for '<searchText>'
    Then search results are displayed as links
    Examples:
      | searchText |
      | bas        |

  Scenario Outline: Result links are opening properly
    Given a 'basketball' 'in-play' event 'basketTeam0 vs basketTeam1' with the following markets:
      | marketName    | display | bir | handicap | selections     |
      | \Alternative Margin Of Victory\ | Yes     | Yes  | -1       | Home/Away/Line |
    When user searches for '<searchText>'
    And user selects event search result link from results list
    Then verify competetion event page is displayed
    Examples:
      | searchText |
      | basket|

  Scenario: 1 or more sports match the search text entered
  #Note: This scenario test data has been set up for until end of this year.
    Given a 'baseball' 'in-play' event 'baseTeam2 vs baseTeam3' with the following markets:
      | marketName    | display | bir | handicap | selections     |
      | \National League\ | Yes     | Yes  | -1       | Home/Away/Line |
    When user searches for 'bas'
    Then matching sports icons are displayed
      | Basketball |
      | Baseball   |
    When user amends 'ketball' to searchText
    Then matching events details are displayed
      | basketball1 vs basketball2   |
      | basketball11 vs basketball22 |

  @critical
  Scenario Outline: Event resuls are displayed underneath the sports results
    When user searches for '<searchText>'
    Then verify matching events are displayed under the sports results
    Examples:
      | searchText |
      | bas        |

  Scenario Outline: Sports results are displayed as Carosel when more results presents
    When user searches for '<searchText>'
    Then verify matching sports icons should be displayed as carousels
    Examples:
      | searchText |
      | bas        |
      | ball       |

  Scenario Outline: No Sports duplicate results
    When user searches for '<searchText>'
    Then verify no duplicate sports results are displayed
    Examples:
      | searchText |
      | bas        |
      | ball       |

  Scenario Outline: No inactive sports are displayed
    When user searches for '<searchText for any inactive sports>'
    Then verify no sports name icons and event details are displayed
    Examples:
      | searchText for any inactive sports |
      | yzx                                |

  Scenario Outline:SBSD-56 No active sports match the text entered
    When user searches for '<searchText>'
    Then search results are displayed as links
    When user amends '<anotherChar>' to searchText
    Then "No results found" error should not be displayed
    And verify results page is displayed with updated results
    Examples:
      | searchText | anotherChar |
      | bas        | k           |

  @critical
  Scenario Outline: Navigate to Sport page from image link on the sports results list
    When user searches for '<sports>'
    And selects the displayed "<sports>" image link
    Then verify "<sports>" home page is displayed
    Examples:
      | sports     |
      #| baseball   |
      | basketball |
      #| cricket    |
      | football   |
      #| TV/Specials |

  Scenario Outline: ORN-278 Sports synonyms
    When user searches for '<sports>'
    Then verify "<expectedSports>" image is displayed as part of sports results
    Examples:
      | sports           | expectedSports    |
      | Dogs             | Greyhounds        |
      | Horses           | Horse Racing      |
      | Footy            | Football          |
      #| F1               | Motor Racing      |
      | Speedway         | Motor Racing      |
      | Moto GP          | Motor Racing      |
      | NFL              | American Football |
      | College Football | American Football |
      | Soccer           | Football          |
      | Gaelic Football  | GAA Football      |
      | Gaelice Hurling  | GAA Hurling       |
      | Crown Green      | Bowls             |
      | Indoor Bowls     | Bowls             |
      | Premier League   | Football          |

  @todo
  Scenario Outline: ORN-278 Sports synonyms with partial search
    When user searches for '<sports>'
    Then verify "<expectedSports>" image is displayed as part of sports results
    Examples:
      | sports    | expectedSports    |
      | Dog       | Greyhounds        |
      | Speedw    | Motor Racing      |
      | Moto G    | Motor Racing      |
      | College F | American Football |
      | Socc      | Football          |
      | Gaelic    | GAA Football      |
      | Gaelice H | GAA Hurling       |
      | Crown G   | Bowls             |
      | Indoor B  | Bowls             |
      | Premier L | Football          |

#  Note: This feature has been removed as part of SBSD-89 search redesign
#
#  Scenario Outline: SBSD-116 Search results message
#    When user goes to '<language>'
#    And user searches for 'basketball1 vs basketball2'
#    Then verify '<message>' is displayed
#    Examples:
#      | language | message             |
#      | German   | 1 Ergebnis gefunden |
#      | English  | 1 Result Found      |
#      | Japanese | 1件の検索結果             |
#      #| Greek    | Βρέθηκε 1 Αποτέλεσμα |
#      | Russian  | 1 результат найден  |
#      #| Swedish  | 1 Resultat hittat    |