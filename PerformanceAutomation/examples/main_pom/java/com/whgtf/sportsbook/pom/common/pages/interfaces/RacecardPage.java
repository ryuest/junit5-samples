package com.whgtf.sportsbook.pom.common.pages.interfaces;

import com.whgtf.sportsbook.model.Selection;

import java.util.List;


public interface RacecardPage extends RacingEventPage {

    String PATH = "/";

    void load();

    void clickOnFirstSelection();

    void clickOnSelectionById(final String id);

    void clickOnSelectionByPosition(final int position);

    List<Selection> getSelectionList();

    Selection getFirstSelection();

    boolean isDisplayedInLanguage(String language);

    int numberOfRacesDisplayedOnRacecard();

    boolean areStringsSorted(List<String> input, boolean ascending);

    void clickOnSortByNumberByRace(int raceNumber,boolean ascending);

    void clickOnSortByDrawByRace(int raceNumber,boolean ascending);

    void clickOnSortBySelectionByRace(int raceNumber,boolean ascending);

    void clickOnSortByRPByRace(int raceNumber,boolean ascending);

    void clickOnSortByPriceByRace(int raceNumber,boolean ascending);

    boolean isRaceOrderedByHorseNumber(int race, boolean ascending);

    boolean isRaceOrderedByDogNumber(int race, boolean ascending);

    boolean isRaceOrderedByDrawn(int race, boolean ascending);

    boolean isRaceOrderedBySelection(int race, boolean ascending);

    boolean isRaceOrderedByRP(int race, boolean ascending);

    boolean isRaceOrderedByPrice(int race, boolean ascending);

    String getRaceEventMessage();

    boolean isTvIconEnabled();

    String getPreviousOddbySelectionId(final String selectionId);

    boolean isPreviousOddsDisplayed();

    boolean isHeaderDisplayed(final String header);

    void clickOnMarketCollectionLinkByText(final String text);

    List<String> getMarketCollectionItems();

    boolean isEventIsDisplayedInRaceCard(String eventId);

    boolean isRacecardDisplayed();

    boolean isMeetingMarketsDisplayed();

    /**
     * This method will return True if the Race to Race Menu Section is begin displayed.
     * @return - True if Race to Race Menu is displayed on the page.
     */
    boolean isRaceToRaceNavigationDisplayed();

    /**
     * This method returns the Option that is Highlighted on the Race to Race.
     */
    String getHighlightedOptionFromRaceToRace();

    void clickRaceToRaceLink(String link);

    /**
     * This methods expands the Alternate Meetings in case it wasnt expanded before.
     */
    void expandAlternateMeetingDropdown();

    /**
     * This methods collapse the Alternate Meetings in case it wasnt collapsed before.
     */
    void collapseAlternateMeetingDropdown();

    void clickOnGivenSelection(int collection);

    void clickOnGivenEvent(int event, int collection);

    void clickOnGivenRace(int race, int collection, int event);

    /**
     * This method will return True if the Alternate Meetins Dropdown is being displayed.
     * @return - True if Alternate Meetings is displayed.
     */
    boolean isAlternateMeetingDropdownDisplayed();

    /**
     * In this method we will return True if the Dropdown is Expanded.
     * @return - True if the Dropdown menu options are being displayed (mean, its expanded).
     */
    boolean isAlternateMeetingDropdownExpanded();

    /**
     * On this method we check if the Racing Page has content.
     * @return True - If the page have content, False - If its empty or something happen.
     */
    boolean isRacePageContentDisplayed();

    boolean areRaceDetailsDisplayed();

    /**
     * This method returns the current Alternate Meeting option selected.
     * @return - current Alternate Meeting option selected.
     */
    String currentAlternateMeetingSelected();

    /**
     * This method selects a given option (by index) from the Alternate Meeting
     * dropdown.
     * @param indexNumber - The index number from the list of options on the Alternate Meeting.
     */
    void selectRaceFromAlternateMeetingDropdown(int indexNumber);

    /**
     * This method selects a given option (by text) from the Alternate Meeting
     * dropdown.
     * @param raceName - The name of the option on the Alternate Meeting.
     */
    void selectRaceFromAlternateMeetingDropdown(String raceName);

    String getRaceNameFromAlternateMeetingIndex(int index);

    List<String> getOptionsFromAlternateMeetingDropdown();

    boolean isAllRacesLinkDisplayed();

    void clickAllRacesLink();

    String getEventName(int event, int collection);

    String getCurrentRaceEventTitle();

    void clickAllRacesFromGivenEventMenu(int collection, int event);

    void clickOnResultedRaceFromGivenEventMenu(int collection, int event);

    void clickOnRaceFromGivenEventMenu(int collection, int event);

    List <String> getAllRacesMenuTitlesFromGivenCollectionEvent(int collection, int event);

    List <String> getAllRacesTitlesDisplayed();

    boolean areRacesAreSortedByTimeInRaceToRace();

    boolean isMeetingMarketDisplayedLast();

    boolean isAllRacesDisplayedFirst();

    void clickRaceToRaceIndex(int index);

    /**
     * This method will return True if the Given Race/Event is being displayed on the page.
     * @param eventId - The event ID of the Race/Event that we want to verify if its displayed or not.
     * @return - True if the Given Race/Event is being displayed.
     */
    boolean isGivenRacecardDisplayed(String eventId);

    /**
     * This method clicks on the Meeting Market Button from a given Competition, by its Competition ID.
     * @param competitionId - Given Competition ID where is the Meeting Market that we want to click.
     */
    void clickMeetingMarketFromGivenCompetition(String competitionId);

    /**
     * Clicks on the given selection number in the displayed racecard.
     *
     * @param number    The number of the selection to click on.
     */
    void clickOnSelectionInRaceCard(int number);

}
