package com.whgtf.sportsbook.pom.common.pages.interfaces;


import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;

import java.util.List;


public interface EventPage extends AbstractSportsPage {

    String getUrl();

    boolean isDisplayed(final String event);

    boolean isDisplayedInLanguage(String language);

    String getMarketBlurb();

    void addSelectionForEachColumnGoalscorerHomeAway(final String option);

    /**
     * Verifies the selected event page is displaying or not, by given eventName and returns true otherwise false.
     *
     * @param eventName The name of the event
     * @return boolean true if the event header is displayed
     */
    boolean isEventHeaderDisplayed(String eventName);

    void clickOnHomeAwayLoadMoreButton(String option);

    void clickSelectionInTheLastVisibleRow(String option);

    void clickOnNoGoalscorer();

    void clickOnTab(final String tab);

    Market getMarketDisplayedByName(final String marketName);

    void clickOnMarketCollectionLinkByText(final String text);

    List<String> getMarketCollectionItems();

    boolean isMarketCollectionListDisplayed();
}
