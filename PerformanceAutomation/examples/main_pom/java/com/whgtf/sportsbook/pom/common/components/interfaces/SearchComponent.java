package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractSportsPage;
import com.whgtf.sportsbook.pom.common.pages.interfaces.EventPage;

import java.util.List;


public interface SearchComponent extends AbstractCommonInterface {

    void search(final String search);

    /**
     * Opens the search component so the user can perform a search.
     * The method handles already the desktop/mobile logic.
     *
     * @return {@link SearchComponent} to chain any other search method.
     */
    SearchComponent open();

    void close();

    void clickOnLoadMore();

    AbstractSportsPage clickOnFirstLink();

    List<String> getAllLinksResults();

    /**
     * Gets the event name from the results list based on given index of the result list.
     *
     * @param index of the event you want to get.
     * @return String with the event name.
     */
    String getEventNameFromResultsList(int index);

    /**
     * Clicks on result link based on given index of results list.
     *
     * @param index of the result link you want to click.
     * @return EventPage
     */
    EventPage clickOnResultLink(int index);

    /**
     * Gets all events results names from the search results.
     *
     * @return List a list with the search results
     */
    List<String> getAllEventResults();

    /**
     * Verifies the given sports icon is displayed as result and returns true otherwise false.
     *
     * @param sportsName The sport name you want to assert.
     * @return true if the sport icon is displayed
     */
    boolean isSportsIconDisplayed(String sportsName);

    /**
     * * Verifies the given sports name is displayed as result and returns true otherwise false.
     *
     * @param sportsName The sport name you want to assert.
     * @return boolean
     */
    boolean isSportsResultDisplayed(String sportsName);

    /**
     * This method verifies the events results are displayed under the sports results block and returns true otherwise false.
     *
     * @return false
     */
    boolean areEventsDisplayedUnderSportsResults();

    /**
     * Verifies the sports results are displayed as carousel view and returns true otherwise false.
     *
     * @return boolean
     */
    boolean isSportsResultsDisplayedAsCarousel();

    /**
     * This method gets the sports names from results and return as list of string values.
     *
     * @return List list of sports
     */
    List<String> getSportsResults();

    /**
     * This method verifies the error message is displayed when no search results are present.
     *
     * @param msg The message you want to assert
     * @return boolean
     */
    boolean isMessagePresent(String msg);

    /**
     * * Selects the given sports name from results
     *
     * @param sportsName the sport name
     */
    void selectSportFromResults(String sportsName);

    /**
     * Verifies the given custom search icon is displayed as result and returns true otherwise false.
     *
     * @param text         the text to search
     * @param customSearch the text to search
     * @return boolean if the search icon is displayed
     */
    boolean isCustomSearchIconDisplayed(String text, String customSearch);

    /**
     * * Selects the given sports name is displayed as result.
     *
     * @param sportsName the sport name to click on
     */
    void clickOnSportsResultIcon(String sportsName);

    /**
     * This method verifies the selected sports link is opened in new tab/window and returns the boolean value accordingly.
     *
     * @param url the url
     * @return boolean true if the sport icon is displayed
     */
    boolean isSportsIconLinkOpenedInNewTab(String url);

    /**
     * This method gets the search input place holder value as string
     *
     * @return String
     */
    String getSearchInputPlaceHolder();

    /**
     * Clears the text by pressing back space in search input
     */
    void clearTextByUsingBackSpace();

    /**
     * Gets the entered search input value
     *
     * @return String
     */
    String getSearchText();

    /**
     * Closes the search overlay by pressing on cross link on search overlay.
     */
    void closeSearchOverlay();

    /**
     * Verifies the search overlay is closed or not and returns the boolean value accordingly.
     *
     * @return boolean
     */
    boolean isSearchOverlayClosed();

    /**
     * Verifying Quick Links Header is above the sports results.
     *
     * @param quickLinksHeader the header text.
     * @return {@code true} if the quick links header is displayed above the sports results.
     */
    boolean isQuickLinksHeaderDisplayedAboveSportsResults(String quickLinksHeader);

    /**
     * Verifying QuickLinks Count value is displayed or not and returns boolean value accordingly.
     *
     * @return boolean
     */
    boolean isQuickLinksCountDisplayed();

    /**
     * Verifying Events Header is above the Events results.
     *
     * @param eventHeaderName the header text.
     * @return {@code true} if the event header is displayed above the sports results.
     */
    boolean isEventLinksHeaderDisplayedAboveEventsResults(String eventHeaderName);

    /**
     * Verifying Events Count value is displayed or not and returns boolean value accordingly.
     *
     * @return {@code true} if event count is displayed
     */
    boolean isEventsCountDisplayed();

    /**
     * Verify the event results are seprerated by line in between those results.
     *
     * @return {@code true} if the results are separated by line
     */
    boolean areEventResultsSeperatedByLine();

    /**
     * Gets the events results count.
     *
     * @return int
     */
    int getEventResultsCount();

    /**
     * Scroll downs to the search results section.
     *
     * @return boolean
     */
    boolean scrollDownSearchResults();

    /**
     * Clears the browser local store values. This is mainly to delete the recent search items on Search overlay.
     *
     * @return boolean
     */
    boolean clearBrowserLocalStore();

    /**
     * Verifies the recent searches are displayed and return true otherwise false.
     *
     * @return boolean
     */
    boolean isRecentSearchesContainerDisplayed();

    /**
     * This method is to get the recent searches header value as string.
     *
     * @return String
     */
    String getRecentSearchesHeader();

    /**
     * Selects the recent search item from list based on given index value.
     *
     * @param itemIndex the index to select
     */
    void selectRecentSearch(int itemIndex);

    /**
     * Return all recent searches as list of string values.
     *
     * @return List<String>
     */
    List<String> getRecentSearches();

    /**
     * Verifies the scrollbar is present on recent searches container and returns true otherwise false.
     *
     * @return boolean
     */
    boolean isScrollBarPresentOnRecentSearchesList();

    /**
     * Scroll downs the recent search list of items and returns true otherwise false.
     *
     * @return boolean
     */
    boolean scrollDownRecentSearch();

    /**
     * Gets the Event result item based on given result index i.
     *
     * @param i index of the result item
     * @return SearchResultItem
     */
    SearchResultItem getEventResult(int i);
}
