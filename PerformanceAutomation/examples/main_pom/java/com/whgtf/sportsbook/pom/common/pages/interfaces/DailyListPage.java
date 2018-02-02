package com.whgtf.sportsbook.pom.common.pages.interfaces;

import java.util.List;


public interface DailyListPage extends AbstractSportsPage {

    List<String> getDailyListFilterNames();

    void selectFilterFromDailyList(int option);

    boolean isFilterFromDailyListSelected(String option);

    boolean isFilterFromDailyListSelected(int option);

    void clickOnTodayLink();

    boolean isTodayLinkSelected();

    void clickOnMondayLink();

    boolean isMondayLinkSelected();

    void clickOnTuesdayLink();

    boolean isTuesdayLinkSelected();

    void clickOnWednesdayLink();

    boolean isWednesdayLinkSelected();

    void clickOnThursdayLink();

    boolean isThursdayLinkSelected();

    void clickOnFridayLink();

    boolean isFridayLinkSelected();

    void clickOnSaturdayLink();

    boolean isSaturdayLinkSelected();

    void clickOnSundayLink();

    boolean isSundayLinkSelected();

    void clickOnFutureLink();

    boolean isFutureLinkSelected();

    boolean dayFilterScrollerIsDisplay();

    void selectOptionFromViewByMenu(String viewByOption);

    boolean isOptionSelectedFromViewByMenu(String viewByOption);

    void  expandViewByFilter();

    void  collapseViewByFilter();

    boolean isDisplayedTimeOnViewByMenu();

    boolean isDisplayedCompetitionOnViewByMenu();

    void selectAlternateMarket(String option);

    void selectAlternateMarket(int option);

    /**
     * Gets the name of the selected alternate market from the dropdown.
     *
     * @return String   The name of the selected alternate market
     */
    String getSelectedAlternateMarket();

    boolean alternateMarketIsSelected(int option);

    List<String> getAlternateMarketsName();

    boolean isAlternateMarketMenuDisplayed();

    boolean isViewByFilterExpanded();
}
