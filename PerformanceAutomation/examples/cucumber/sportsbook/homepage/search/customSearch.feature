@custom_search
Feature: Custom searches (SBSD-115)

  Scenario Outline: Verify Draft Custom Search is not displayed in sports search
    When User Creates and not Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | abc1       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | true      | true                |          |            |
    And User searches for created custom search
    Then Custom search is not displayed as part of sports results
    Examples:
      | language |
      | en-gb    |

  Scenario Outline: Verify newly published Custom Search is displayed in sports search
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | bcd1       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | true      | true                |          |            |
    And User searches for created custom search
    Then Custom search is displayed as part of sports results
    Examples:
      | language |
      | en-gb    |

  Scenario Outline: Verify Unpublished modified Custom Search is not displayed in sports search
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | cde1       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | true      | true                |          |            |
    And User searches for created custom search
    Then Custom search is displayed as part of sports results
    When User Edits and not Publishes custom search
      | url                                                                   | openLinkInNewTab | showOnWeb | showOnNativeDevices |
      | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | false     | false               |
    And User searches for created custom search
    Then Custom search is displayed as part of sports results
    Examples:
      | language |
      | en-gb    |

  Scenario Outline: Verify Modified published Custom Search is displayed in sports search
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | def1       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | true      | true                |          |            |
    And User searches for created custom search
    Then Custom search is displayed as part of sports results
    When User Edits and Publishes custom search
      | url                                                                   | openLinkInNewTab | showOnWeb | showOnNativeDevices |
      | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | false     | false               |
    And User searches for created custom search
    Then Custom search is not displayed as part of sports results
    Examples:
      | language |
      | en-gb    |

  Scenario Outline: Verify Deleted Custom Search is not displayed in sports search
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | test       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | true      | true                |          |            |
    And User searches for created custom search
    Then Custom search is displayed as part of sports results
    When User deletes custom search
    And User searches for created custom search
    Then Custom search is not displayed as part of sports results
    Examples:
      | language |
      | en-gb    |

  ############## Custom Search Ignore Case #########################

  Scenario: Verifying custom search with Ignore case
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | test       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | true      | true                |          |    true        |
    And User searches for created custom search
    Then Custom search is displayed as part of sports results
    When User searches with case sensitive
    Then Custom search is displayed as part of sports results


  Scenario: Verifying custom search without Ignore case
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | test       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | true      | true                |          |            |
    And User searches for created custom search
    Then Custom search is displayed as part of sports results
    When User searches with case sensitive
    Then Custom search is not displayed as part of sports results

    ############## Custom Search Open In New Tab #########################

  Scenario: Verifying custom search without Open In New Tab option
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | test       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | true      | true                |          |            |
    And User searches for created custom search
    And Selects the displayed custom search image link
    Then Redirects to link url


  Scenario: Verifying custom search with Open In New Tab option
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | test       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football | true             | football_rule | true      | true                |          |            |
    And User searches for created custom search
    And Selects the displayed custom search image link
    Then Navigates to link url in new tab

    ####################### Custom Search with out Show On Web ################

  @desktopOnly
  Scenario: Verifying custom search without ShowOnWeb option
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | test       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | false     | true                |          |            |
    And User searches for created custom search
    Then Custom search is not displayed as part of sports results

      ####################### Custom Search with Show on Native ################

  @nativeOnly
  Scenario: Verifying custom search without ShowOnNative option
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | test       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | true      | false               |          |            |
    And User searches for created custom search
    Then Custom search is not displayed as part of sports results

  @nativeOnly
  Scenario: Verifying custom search with ShowOnNative option
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | test       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | true      | true                |          |            |
    And User searches for created custom search
    Then Custom search is displayed as part of sports results

  Scenario: Verifying custom search with out ShowOnWebOption ShowOnNative option
    When User Creates and Publishes custom search
      | searchText | url                                                                   | openLinkInNewTab | iconText      | showOnWeb | showOnNativeDevices | ruleName | ignoreCase |
      | test       | http://sports.williamhill-sportsbettingdev.com/betting/en-gb/football |                  | football_rule | false     | false               |          |            |
    And User searches for created custom search
    Then Custom search is not displayed as part of sports results