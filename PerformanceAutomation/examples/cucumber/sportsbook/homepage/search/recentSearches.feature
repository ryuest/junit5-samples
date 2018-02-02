@recentSearches @search @todo
Feature: Recent Searches (sbsd-194)

  Background:
    Given user is in William Hill Sportsbook

  Scenario:Recent Searches are not displayed
    Given the browser did not have any recent searches
    When user navigates to Search
    Then recent searches container is not displayed

  Scenario:Recent Searches Header is displayed
    Given the browser have recent searches
    When user navigates to Search
    Then recent searches container is displayed
    And "Recent Searches" header is displayed above the recent search items

  Scenario: Select Recent Search to display the search results
    Given recent search container is displayed with recent searches
    When user selects any of the recent search item
    Then search results are displayed for the item
    And the selected search term is present in the search input

  @desktopOnly
  Scenario: Recent Searches doesnt have scrollbar when results are fitting in a page
    When recent search container is displayed with less than /5 search items
    Then scrollbar is not displayed

  @desktopOnly
  Scenario:Recent Searches displays scrollbar when results are more
    Given recent search container is displayed with more recent searches
    When recent search has more than 5 items
    Then scrollbar is displayed
    And user can see more results by scrolling on recent search container

  Scenario Outline: Verify SearchTerm is the top of Recent Searches list when user did scrolling or selection on its displayed search result item
    Given user searches for 'ball'
    And '<type>' on search results
    When reopen search overlay again
    Then the search term is displayed on the top of the recent searches items list
    Examples:
      | type      |
      | scrolling |
      | selecting |

  Scenario Outline: Verify SearchTerm is the not top of Recent Searches list when user did not scroll or select its displayed search result item
    Given a 'basketball' 'in-play' event 'bas3 vs bas4' with the following markets:
      | marketName    | display | bir | handicap | selections     |
      | \Alternative Margin Of Victory\ | Yes     | Yes  | -1       | Home/Away/Line |
    When user searches for '<searchTerm>'
    And did not select any search result or does not scroll on search results
    And reopen search overlay again
    Then the search term is not displayed on the top of the recent searches items list
    Examples:
      | searchTerm |
      | bask       |

  Scenario: No Results Found error message is displayed with recent searches container underneath it.
    When user search for 'xzzz' that doesn't found results
    Then Recent Searches container is displayed underneath the No Results Found error message