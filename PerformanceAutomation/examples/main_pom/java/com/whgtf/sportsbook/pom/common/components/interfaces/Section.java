package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;
import org.openqa.selenium.By;

import java.util.List;


interface Section extends AbstractCommonInterface {

    By IN_PLAY_ACTIVE_SELECTION = By.cssSelector(
            "[id^='OB_EV'][data-betinrun='true']:not(.disabled-event):not(.topbets__list-item) [id^='OB_MA'][data-betinrun='true'][data-status='A']:not(.disabled-market) [id^='OB_OU'][data-status='A']:not(.disabled)");
    By PRE_MATCH_ACTIVE_SELECTION =By.cssSelector(
            "[id^='OB_EV'][data-betinrun='false']:not(.disabled-event):not(.topbets__list-item) " +
                    "[id^='OB_MA'][data-betinrun='false'][data-status='A']:not(.disabled-market) " +
                    "[id^='OB_OU'][data-status='A']:not(.disabled)");

    By IN_PLAY_ACTIVE_MARKET = By.cssSelector(
            "[id^='OB_EV'][data-betinrun='true']:not(.disabled-event):not(.topbets__list-item) " +
                    "[id^='OB_MA'][data-betinrun='true']:not(.disabled-market)");

    By PRE_MATCH_ACTIVE_MARKET =By.cssSelector(
            "[id^='OB_EV'][data-betinrun='false']:not(.disabled-event):not(.topbets__list-item) " +
                    "[id^='OB_MA'][data-betinrun='false']:not(.disabled-market)");

    By PRE_MATCH_ACTIVE_EVENT = By.cssSelector(
            "[id^='OB_EV'][data-betinrun='false']:not(.disabled-event):not(.topbets__list-item)");

    By IN_PLAY_ACTIVE_EVENT = By.cssSelector(
            "[id^='OB_EV'][data-betinrun='true']:not(.disabled-event):not(.topbets__list-item)");


    /**
     * Click on a selection specified by selection Id
     * @param selection to click on
     * @return {@link Selection} the clicked selection
     */
    Selection clickOnSelection(final String selection);

    /**
     * Click on the event given an event id passed as a parameter
     * @param event String with the id of the event
     */
    void clickOnEvent(final String event);

    /**
     * Click on the first selection available
     * @return the event id clicked
     */
    String clickOnFirstSelection();

    /**
     * Click on the first market available
     */
    void clickOnFirstMarket();

    /**
     * Click on the first event available
     */
    void clickOnFirstEvent();

    /**
     * Load all the data structure of the page. This method will set the event List and active event list
     */
    void load();

    /**
     * Return the size of the event list size
     * @return an integer as list size
     */
    int getEventListSize();

    /**
     * Return a list with all the event
     * @return a List of Event objects
     */
    List<Event> getEventList();

    /**
     * Return a list with all the active events
     * @return a List of active Event objects
     */
    List<Event> getActiveList();

    /**
     * Return if the event specified by the event id is displayed
     * @param eventId the event id
     * @return true or false indicating if the event is displayed or not
     */
    boolean isEventDisplayed(final String eventId);

    /**
     * Return the header text
     * @return the String containing the header text
     */
    String getHeaderText();

    /**
     * Return if the header is displayed
     * @return true or false if is displayed
     */
    boolean isHeaderDisplayed();

    /**
     * Return if a market is displayed given a market name
     * @param market market name
     * @return true or false if the market given the name is displayed or not
     */
    boolean isMarketHeaderByTitleDisplayed(final String market);

    /**
     * Return the first active selection
     * @return a {@link com.whgtf.sportsbook.model.Selection} object
     */
    Selection getActiveSelection();

    /**
     * Return the first active market
     * @return a {@link com.whgtf.sportsbook.model.Market} object
     */
    Market getActiveMarket();

    /**
     * Return the first active event
     * @return a {@link com.whgtf.sportsbook.model.Event} object
     */
    Event getActiveEvent();

    /**
     * Return is the In-Play section is displayed in the page.
     * @return true or false indicating if is displayed
     */
    boolean isDisplayed();


}
