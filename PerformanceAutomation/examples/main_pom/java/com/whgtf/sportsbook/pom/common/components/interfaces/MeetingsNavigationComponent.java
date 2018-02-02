package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;
import com.whgtf.sportsbook.pom.common.pages.interfaces.RacecardPage;
import com.whgtf.sportsbook.pom.common.pages.interfaces.RacingEventPage;
import java.util.List;


public interface MeetingsNavigationComponent extends AbstractCommonInterface {

    boolean todayAndTomorrowTabsDisplayed();

    boolean isTodayTabSelected();

    boolean isTomorrowTabSelected();

    void clickOnTodayMeetings();

    void clickOnTomorrowMeetings();

    void openGroupByName(final String name);

    void openRaceMeetingByName(final String name);

    void clickOnAllRacesByGroupNameAndMeetingName(final String groupName, final String meetingName);

    RacingEventPage clickOnTheFirstAllRaces();

    void clickOnRandomRace();

    void clickOnTheFirstRaceByGroupNameAndMeetingName(final String groupName, final String meetingName);

    RacecardPage clickOnTheFirstSelectionAvailable();

    RacecardPage clickOnTheFirstEvent();

    void clickOnAllRacesButtonByCompetition(final String competitionId);

    void clickOnMeetingMarketsButtonByCompetition(final String competitionId);

    void addSeveralRacecardsForCompetition(String competitionId, int number);

    void addRaceForCompetitionByEventId(final String competitionId, String eventId);

    boolean isDisplayed();

    void removeSeveralRacecardsForCompetition(String competitionId, int number);

    void clickOnFirstRaceMeetingSelection();

    int getRacesAddedActiveNumberInLeftNavigation();

    int numberOfRacesDisplayedByMeetingId(String meetingId);

    void clickOnFutureRaceByMeetingId(String meetingId, int eventNumber);

    List<String> findMeetingsWithMeetingMarkets();

    List<String> findMeetingsWithoutMeetingMarkets();

    List<String> findOneMeetingWithOrWithoutMeetingMarkets(boolean withMeetingMarket);

    boolean isRaceHighlightedAndInRaceCard();

    boolean checkQualifiesRacesHighlightedInYellow(final int races);

    String getMessageInMyWHTVRacesGroup();

    void clickOnFutureRaceInAnyExpandedMeeting(int eventNumber);

    void clickOnMeetingMarketsSelection();

    void clickOnAllRacesSelection();

    void clickOnTheNumberEvent(int number);

    int getNumberDisplayedRacesInExpandedGroup();

    void expandAllCollectionsAndEvents();

    void expandAllRegions();

    void expandAllMeetings();

    /**
     * This method expands all Regions first, and then all the meetings inside these.
     */
    void expandAllRegionsAndMeetings();

    void clickOnActiveRaceThatHasMoreThanOneRaceOnEvent();

    /**
     * On this method we click on a given Race, from a Given competition.
     * @param eventId - The given event ID / Button that we want to click on.
     * @param competitionId - The given competition ID where the event is.
     */
    void clickOnGivenRaceFromGivenCompetition(String eventId, String competitionId);

    /**
     * On this method we will click on the "All Races" button from a given competition.
     * @param competitionId - The competition Id where the All Races button is located.
     */
    void clickOnGivenAllRaces(String competitionId);

    /**
     * On this method we return all the List of Races from a given Competition.
     * @param competitionId - The competition ID from where we want the list of all Races IDs.
     * @return - A List of Strings of all Race IDs from the given Competition.
     */
    List<String> getAllRacesIDsFromGivenCompetition(String competitionId);







}
