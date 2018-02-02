package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.components.interfaces.BottomBarComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.HighlightsSection;
import com.whgtf.sportsbook.pom.common.exceptions.NoEventAvailable;
import com.whgtf.sportsbook.pom.common.exceptions.NoMarketAvailable;
import com.whgtf.sportsbook.pom.common.exceptions.NoSelectionAvailable;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The main Class launches the application.
 * <p>
 * <img src="doc-files/inPlaySectionComponent.png" alt="Example of the application GUI">
 * </p>
 */
@Component
@Lazy
public class HighlightsSectionImpl extends AbstractCommonObject implements HighlightsSection {

    private final static By HIGHLIGHT_SECTION = By.id("match-highlights");

    private final static String MARKET_SECTION = "btmarket__actions";

    private final static By MARKET = By.xpath(".//div[contains(@id,'OB_MA')]");

    private final static String MARKET_SELECTION = "btmarket__selection";

    private final static By VIEW_ALL_LINK = By.className("link-viewall");

    private final static By EVENT_SELECTOR = By.cssSelector(".event");

    private final static By HEADER_TEXT = By.cssSelector(".sectionheading__column");

    private final static By HEADERS_DROPDOWN = By.cssSelector(".header-dropdown");

    private final static By ACTIVE_IN_HIGHLIGHT = By.id("match-highlights");

    //list with all the event
    private static List<Event> eventList;

    private static List<Event> activeEventList;

    @Autowired
    @Lazy
    public BottomBarComponent bottomBarComponent;

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        waitSportsbook();
        if (eventList == null) {
            eventList = new ArrayList<>();
            activeEventList = new ArrayList<>();
        }
        if (eventList.isEmpty()) {
            List<WebElement> eventListElement;
            if (isMobile()) {
                eventListElement = findElements(EVENT_SELECTOR);
            } else {
                eventListElement = find(HIGHLIGHT_SECTION).findElements(EVENT_SELECTOR);
            }
            for (WebElement element : eventListElement) {
                if (element.isDisplayed()) {
                    Event event = new Event(element.getAttribute("id"));
                    event.setStatus(element.getAttribute("data-status"));
                    if (isElementPresent(element.findElement(MARKET))) {
                        WebElement marketElement = element.findElement(MARKET);
                        Market market = new Market(marketElement.getAttribute("id"));
                        market.setStatus(marketElement.getAttribute("data-status"));
                        List<WebElement> selectionList = marketElement.findElements(By.className(MARKET_SELECTION));
                        for (WebElement selectionElement : selectionList) {
                            Selection selection = new Selection(selectionElement.findElement(By.tagName("button")).getAttribute("id"),
                                    selectionElement.findElement(By.tagName("button")).getAttribute("data-odds"));
                            selection.setStatus(selectionElement.findElement(By.tagName("button")).getAttribute("data-status"));
                            market.addSelection(selection);
                        }
                        event.addMarket(market);
                        eventList.add(event);
                        if ("A".equalsIgnoreCase(event.getStatus()))
                            activeEventList.add(event);
                    }
                }
                if(activeEventList.size()>5)
                    break;
            }
        }
    }


    private boolean isEventListLoaded() {
        return !(eventList == null || eventList.isEmpty());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getEventListSize() {
        if (!isEventListLoaded())
            load();
        return eventList.size();
    }

    /**
     * Return a list with all the event in the Highlights section
     * @return a List of Event objects in the Highlights section
     */
    @Override
    public List<Event> getEventList() {
        if (!isEventListLoaded())
            load();
        return eventList;
    }

    /**
     * Return a list with all the active events in the Highlights section
     * @return a List of active Event objects in the Highlights section
     */
    @Override
    public List<Event> getActiveList() {
        if (!isEventListLoaded())
            load();
        return activeEventList;
    }

    /**
     * Click on the event given an event id passed as a parameter
     * @param event String with the id of the event
     */
    @Override
    public void clickOnEvent(String event) {
        find(By.id(event)).findElement(By.tagName("a")).click();
    }

    @Override
    public String clickOnFirstSelection() {
        boolean found = false;
        if (!isEventListLoaded())
            load();
        for (Event event : eventList) {
            if(event.isEventActive()) {
                for (Market market : event.getMarkets()) {
                    if (market.getStatus().equalsIgnoreCase("A")) {
                        for (Selection selection : market.getSelections()) {
                            if (selection.getStatus().equalsIgnoreCase("A")) {
                                if (find(By.id(selection.getPdsId())).isEnabled()) {
                                    clickOnSelection(selection.getPdsId());
                                    return selection.getPdsId();
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NoSelectionAvailable();

    }

    /**
     * Click on the first selection available in the Highlights section and returning a the event id
     */
    @Override
    public void clickOnFirstEvent() {
        if (eventList.isEmpty())
            load();
        if (eventList.isEmpty())
            throw new NoEventAvailable();
        else {
            find(By.cssSelector("#" + eventList.get(0).getPdsId() + " a")).click();
        }
    }

    /**
     * Click on the first market available in the Highlights section
     */
    @Override
    public void clickOnFirstMarket() {
        throw new NoMarketAvailable("There is no market to click at in In-Play-Section");
    }

    /**
     * Click on View All link in the Highlights section
     */
    public void clickOnViewAll() {
        clickByJavascript(By.cssSelector("#match-highlights .link-viewall"));
    }

    /**
     * Return is the Highlights section is displayed in the page.
     * @return true or false indicating if Highlights section is displayed
     */
    @Override
    public boolean isDisplayed() {
        try {
            find(HIGHLIGHT_SECTION);
        } catch(Exception ex) {
            return false;
        }
        return find(HIGHLIGHT_SECTION).isDisplayed();
    }

    /**
     * Return if the event specified by the event id is displayed in the Highlights section
     * @param eventId the event id
     * @return true or false indicating if the event is displayed or not
     */
    @Override
    public boolean isEventDisplayed(final String eventId) {
        return find(HIGHLIGHT_SECTION).findElement(By.id(eventId)).isDisplayed();
    }

    /**
     * Return the header text in the Highlights Section
     * @return the String containing the header text
     */
    @Override
    public String getHeaderText() {
        return find(HIGHLIGHT_SECTION).findElement(HEADER_TEXT).getText();
    }

    /**
     * Return if the Highlights section header is displayed
     * @return true or false if is displayed
     */
    @Override
    public boolean isHeaderDisplayed() {
        return find(HIGHLIGHT_SECTION).isDisplayed();
    }

    /**
     * Return if a market is displayed given a market name
     * @param marketName market name
     * @return true or false if the market given the name is displayed or not
     */
    @Override
    public boolean isMarketHeaderByTitleDisplayed(String marketName) {
        try {
            List<WebElement> headerList = find(HIGHLIGHT_SECTION).findElements(HEADERS_DROPDOWN);
            for (WebElement element : headerList) {
                String name = element.getText();
                if (StringUtils.equalsIgnoreCase(marketName, name))
                    return true;
            }
        } catch (TimeoutException ex){
            return false;
        } catch (NoSuchElementException ex) {
            return false;
        }
        return false;
    }

    /**
     * Return if the viewAll Highlights link is displayed in the section
     * @return true or false in the view all link is displayed
     */
    @Override
    public boolean isViewAllTodayLinkDisplayed() {
        try {
            find(HIGHLIGHT_SECTION).findElement(VIEW_ALL_LINK);
        } catch (NoSuchElementException ex) {
            return false;
        }
        return find(HIGHLIGHT_SECTION).findElement(VIEW_ALL_LINK).isDisplayed();
    }

    /**
     * Return the first active selection in the Highlights section
     * @return a Selection object
     */
    @Override
    public Selection getActiveSelection() {
        List<WebElement> selectionList;
        try {
            selectionList = find(ACTIVE_IN_HIGHLIGHT).findElements(PRE_MATCH_ACTIVE_SELECTION);
        } catch (NoSuchElementException ex) {
            return null;
        }
        return new Selection(selectionList.get(0).getAttribute("id"),selectionList.get(0).getText());
    }

    /**
     * Return the first active market in the Highlights section
     * @return a {@link Market} object
     */
    @Override
    public Market getActiveMarket() {
        List<WebElement> selectionList = null;
        try {
            selectionList = find(ACTIVE_IN_HIGHLIGHT).findElements(PRE_MATCH_ACTIVE_MARKET);
        } catch (NoSuchElementException ex) {
            return null;
        }

        return new Market(selectionList.get(0).getAttribute("id"),selectionList.get(0).getText());
    }

    /**
     * Return the first active event in the Highlights section
     * @return a Event object
     */
    @Override
    public Event getActiveEvent() {
        List<WebElement> selectionList = null;
        try {
            selectionList = find(ACTIVE_IN_HIGHLIGHT).findElements(PRE_MATCH_ACTIVE_EVENT);
        } catch (NoSuchElementException ex) {
            return null;
        }
        Event event = new Event(selectionList.get(0).getAttribute("id"));

        return event;
    }

}
