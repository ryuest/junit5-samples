package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.components.interfaces.BottomBarComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.InPlaySection;
import com.whgtf.sportsbook.pom.common.exceptions.NoEventAvailable;
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
 * <p>
 * <img src="doc-files/inPlaySectionComponent.png" alt="Example of the application GUI">
 * </p>
 */

@Component
@Lazy
public class InPlaySectionImpl extends AbstractCommonObject implements InPlaySection {

    private final static By IN_PLAY_SECTION = By.cssSelector("section[data-panel='panel-in-play']");

    private final static By MARKET_WRAPPER = By.className("btmarket__wrapper");

    private final static By EVENT_SELECTOR = By.cssSelector(".event");

    private final static String MARKET_SECTION = "btmarket__actions";

    private final static String MARKET_SELECTION = "btmarket__selection";

    private final static By VIEW_ALL_LINK = By.className("link-viewall");

    private final static By LIVE_ICON = By.className("area-livescore");

    private final static By MARKET = By.xpath(".//div[contains(@id,'OB_MA')]");

    private final static By WARNING_MESSAGE = By.cssSelector(".alert.alert-info");

    private final static By HEADER_TEXT = By.cssSelector(".header-dropdown");

    private final static By ACTIVE_IN_PLAY = By.cssSelector("[data-panel='panel-in-play'] [id='in-play-now']");


    //----------------------- New! -----------------------

    private final static By IN_PLAY_SPORT_MENU = By.cssSelector("");

    private final static By IN_PLAY_SPORT_MENU_OPTIONS = By.cssSelector("");






    //list with all the event
    private static List<Event> eventList;
    private static List<Event> activeEventList;

    @Autowired
    @Lazy
    public BottomBarComponent bottomBarComponent;

    /**
     * Constructor
     */
    public InPlaySectionImpl() {
        eventList = new ArrayList<>();
        activeEventList = new ArrayList<>();
    }

    /**
     * {@inheritDoc} in the in play section
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
                eventListElement = find(IN_PLAY_SECTION).findElements(EVENT_SELECTOR);
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

    /**
     * Return a boolean which specifies if the event list is loaded or not
     * @return true if the event list has data and false if is empty
     */
    private boolean isEventListLoaded() {
        return !(eventList == null || eventList.isEmpty());
    }


    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public int getEventListSize() {
        if(!isEventListLoaded())
            load();
        return eventList.size();
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public List<Event> getEventList() {
        if(!isEventListLoaded())
            load();
        return eventList;
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public List<Event> getActiveList() {
        if(!isEventListLoaded())
            load();
        return activeEventList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnEvent(String event) {
        if(eventList.isEmpty())
            load();
        if(eventList.isEmpty())
            throw new NoEventAvailable();
        find(By.id(event)).findElement(By.tagName("a")).click();
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public String clickOnFirstSelection() {
        if(eventList.isEmpty())
            load();
        if(eventList.isEmpty())
            throw new NoEventAvailable();

        boolean found = false;
        for (Event event: eventList) {
            if(event.isEventActive()) {
                for (Market market : event.getMarkets()) {
                    if (market.getStatus().equalsIgnoreCase("A")) {
                        for (Selection selection : market.getSelections()) {
                            if (selection.getStatus().equalsIgnoreCase("A")) {
                                boolean exception = false;
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
        throw new NoEventAvailable();
    }


    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public void clickOnFirstMarket() {
        if(eventList.isEmpty())
            load();
        if(eventList.isEmpty())
            throw new NoEventAvailable();
        else
            find(By.id(eventList.get(0).getFirstMarket().getPdsId())).click();
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public void clickOnFirstEvent() {
        if(eventList.isEmpty())
            load();
        if(eventList.isEmpty())
            throw new NoEventAvailable();
        else {
            find(By.cssSelector("#" + eventList.get(0).getPdsId() + " a")).click();
        }

    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    public void clickOnViewAll(){
        clickByJavascript(By.cssSelector("#in-play-now .link-viewall"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisplayed() {
        try {
            find(IN_PLAY_SECTION);
        } catch (Exception ex) {
            return false;
        }
        return find(IN_PLAY_SECTION).isDisplayed();
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public boolean isEventDisplayed(String eventId) {
        return find(IN_PLAY_SECTION).findElement(By.id(eventId)).isDisplayed();
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public boolean isLiveIconDisplayedInEvent(final String eventId){
        return find(IN_PLAY_SECTION).findElement(By.id(eventId)).findElement(LIVE_ICON).isDisplayed();
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public String getWarningMessage() {
        return find(WARNING_MESSAGE).getText();
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public boolean isViewAllInPlayLinkDisplayed() {
        try {
            find(IN_PLAY_SECTION).findElement(VIEW_ALL_LINK);
        } catch (NoSuchElementException ex) {
            return false;
        }
        return find(IN_PLAY_SECTION).findElement(VIEW_ALL_LINK).isDisplayed();
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public String getHeaderText() {
        return find(IN_PLAY_SECTION).findElement(HEADER_TEXT).getText();
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public boolean isHeaderDisplayed() {
        return find(IN_PLAY_SECTION).isDisplayed();
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    public boolean isMarketHeaderByTitleDisplayed(String marketName) {
        try {
            List<WebElement> headerList = find(IN_PLAY_SECTION).findElements(HEADER_TEXT);
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
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public Selection getActiveSelection() {
        List<WebElement> selectionList;
        try {
            selectionList = find(ACTIVE_IN_PLAY).findElements(IN_PLAY_ACTIVE_SELECTION);
        } catch (NoSuchElementException ex) {
            return null;
        }
        return new Selection(selectionList.get(0).getAttribute("id"),selectionList.get(0).getText());
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public Market getActiveMarket() {
        List<WebElement> selectionList;
        try {
            selectionList = find(ACTIVE_IN_PLAY).findElements(IN_PLAY_ACTIVE_MARKET);
        } catch (NoSuchElementException ex) {
            return null;
        }

        return new Market(selectionList.get(0).getAttribute("id"));
    }

    /**
     * {@inheritDoc} in the In-Play section
     */
    @Override
    public Event getActiveEvent() {
        List<WebElement> selectionList;
        try {
            selectionList = find(ACTIVE_IN_PLAY).findElements(IN_PLAY_ACTIVE_EVENT);
        } catch (NoSuchElementException ex) {
            return null;
        }

        return new Event(selectionList.get(0).getAttribute("id"));
    }
}
