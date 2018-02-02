package com.whgtf.sportsbook.pom.common.pages.interfaces;

import com.whgtf.sportsbook.pom.common.components.impl.MeetingsNavigationComponentImpl;

import java.util.List;

public interface MeetingsPage extends AbstractSportsPage {

    void clickOnTheFirstSelectionInTheEvent(final String eventId);

    RacecardPage clickOnViewFullCard(String eventId);

    MeetingsNavigationComponentImpl getMeetingComponent();

    void clickOnTheFirstSelectionInTheFirstRace();

    void load();

    boolean isDisplayedInLanguage(String language);

    RacecardPage clickOnFirstViewFullCard();

    boolean isMeetingsDisplayed ();

    int getRaceCardsNumber();

    boolean isMeetingsItemsDisplayed();

    boolean isNextOffItemsDisplayed();

    /**
     * This method returns all the events displayed on the Meetings page
     * from a given Competition.
     * @param competitionID - Given Competition ID.
     * @return - List of Strings containing the Events IDs from the given Competition.
     */
    List<String> getAllEventsFromGivenCompetition(String competitionID);

    /**
     * On this method will return True if the main meetings section
     * is displayed, otherwise, will return false.
     * @return True if the main meetings section is displayed on page.
     */
    boolean isMeetingsSectionDisplayed();

    /**
     * This method will return True if the Market Collections Menu is being displayed
     * (the whole menu), if not, will return False.
     * @return True if the Market Collections Menu is being displayed.
     */
    boolean isMarketCollectionsMenuDisplayed();

    /**
     * This method will return how many Market Collections Markets are displayed on the
     * page.
     * @return Number of Markets of Market Collections displayed on page.
     */
    int marketCollectionsContainersDisplayed();

    /**
     * This method will return true if "isMeetingsSectionDisplayed", "isMarketCollectionsMenuDisplayed"
     * and "marketCollectionsContainersDisplayed" (is more than 0) return all true.
     *
     * @return True if all the verifications are being displayed on page.
     */
    boolean isMeetingMarketsPageDisplay();

}
