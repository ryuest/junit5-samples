  @search_design @search
Feature:(SBSD-89) Search Redesign

  Background:
    Given user is in William Hill Sportsbook

  Scenario: I want the search field to contain placeholder text before I begin typing
    When user navigates to Search
    Then verify Search placeholder 'Search…' is displayed

  Scenario Outline: I want to clear search text
    When user searches for '<searchText>'
    And user clears the search text by pressing back space
    Then verify '<searchText>' is cleared
    Examples:
      | searchText |
      | xyz        |

  Scenario: I want to close search overlay
    When user navigates to Search
    And user closes the search overlay
    Then verify search overlay is closed

  Scenario Outline: I want to see sports results with QuickLinks Header
    Given a 'basketball' 'in-play' event 'foo3 vs foo4' with the following markets:
      | marketName    | display | bir | handicap | selections     |
      | \Alternative Margin Of Victory\ | Yes     | Yes  | -1       | Home/Away/Line |
    When user searches for '<searchText>'
    Then 'QUICK LINKS' header is displayed above sports results
    Examples:
      | searchText |
      | basketball |

  Scenario Outline: I want to see sports results count with QuickLinks Header
    Given a 'basketball' 'in-play' event 'foo4 vs foo5' with the following markets:
      | marketName    | display | bir | handicap | selections     |
      | \Alternative Margin Of Victory\ | Yes     | Yes  | -1       | Home/Away/Line |
    When user searches for '<searchText>'
    Then sports results count is displayed with QuickLinks header
    Examples:
      | searchText |
      | ball       |

  Scenario Outline: I want to see events results with Events Header
    When user searches for '<searchText>'
    Then 'EVENTS' header is displayed above event results
    Examples:
      | searchText |
      | bas        |

  @critical
  Scenario Outline: I want to see events results count with Events Header
    When user searches for '<searchText>'
    Then events count is displayed with Events header
    Examples:
      | searchText |
      | bas        |

  Scenario Outline: I want to see the event details for each event display
    Given a 'football' 'in-play' event '<searchText>' with the following markets:
      | marketName           | display | bir | handicap | selections   |
      | \Handicap -1\        | Yes     | No |   -1   |Home/Away/Line|
    #And streaming is available for the event in 'football'
    When user searches for '<searchText>'
    Then events are displayed with details
    Examples:
      | searchText   |
      | foo5 vs foo6 |

  Scenario Outline: I want to see the events are seperated by line
    When user searches for '<searchText>'
    Then verify event results are seperated by line between the events
    Examples:
      | searchText |
      | bas        |

  Scenario Outline: I want to view events paginated with infinite scroll
    When user searches for '<searchText>'
    Then verify more event results are displayed while scrolling down until end of results
    Examples:
      | searchText |
      | football   |

  Scenario:  I want to see custom search results count with QuickLinks Header
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | axyz       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | fb_rule  | true      | true                |          |            |
    And User searches for created custom search
    Then 'QUICK LINKS' header is displayed above sports results
    And sports results count is displayed with QuickLinks header