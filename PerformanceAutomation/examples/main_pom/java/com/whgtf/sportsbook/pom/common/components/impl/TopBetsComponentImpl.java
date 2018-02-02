package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.components.interfaces.TopBetsComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class TopBetsComponentImpl extends AbstractCommonObject implements TopBetsComponent {

    private static final By TOP_BET_ELEMENT = By.id("topBetsFragmentContainer");

    private static final By SHOW_MORE_LINK = By.cssSelector(".topbets__footer");

    private static final By TOP_BET_SELECTION = By.cssSelector(".topbets__list-item [id^='OB_MA'][data-status='A']:not(.disabled-market) [id^='OB_OU']" +
            "[data-status='A']:not(.disabled)");



    //list with all the event web elements
    private static List<Event> eventList  = new ArrayList<>();


    public void load() {
        List<WebElement> list = find(TOP_BET_ELEMENT).findElements(By.tagName("li"));
        for (WebElement element:list) {
            Event event = new Event(element.getAttribute("id"));
            WebElement marketElement = element.findElement(By.className("grid-col--shrink"));
            Market market = new Market(marketElement.getAttribute("id"));
            Selection selection = new Selection(marketElement.findElement(By.tagName("button"))
                    .getAttribute("id"),marketElement.findElement(By.tagName("button"))
                    .getAttribute("data-odds"));
            if(existInEventList(event.getPdsId())){
                if(!existMarketInEventList(event.getPdsId(),market.getPdsId())) {
                    market.addSelection(selection);
                    event.addMarket(market);
                }
            } else {
                market.addSelection(selection);
                event.addMarket(market);
                eventList.add(event);
            }
        }
    }

    @Override
    public int getEventListSize() {
        if(eventList.isEmpty())
            load();
        return eventList.size();
    }

    @Override
    public void clickOnShowMoreLink() {
        find(TOP_BET_ELEMENT).findElement(SHOW_MORE_LINK).
                findElement(By.tagName("a")).click();
    }

    private boolean existInEventList(String pdsId) {
        if(!eventList.isEmpty()) {
            for (Event event : eventList) {
                if (event.getPdsId().equalsIgnoreCase(pdsId))
                    return true;
            }

        }
        return false;
    }

    private boolean existMarketInEventList(String eventPdsId, String marketPdsId) {
        if(!eventList.isEmpty()) {
            for (Event event : eventList) {
                if (event.getPdsId().equalsIgnoreCase(eventPdsId)) {
                    for (Market market : event.getMarkets()) {
                        if (market.getPdsId().equalsIgnoreCase(marketPdsId)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isDisplayed() {
        return isElementDisplayed(TOP_BET_ELEMENT);
    }

    @Override
    public boolean isEventDisplayed(String eventId) {
        if(!eventList.isEmpty()) {
            for (Event event : eventList) {
                if (event.getPdsId().equalsIgnoreCase(eventId))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean isMarketDisplayed(String marketId) {
        if(!eventList.isEmpty()) {
            for (Event event : eventList) {
                for (Market market : event.getMarkets()) {
                    if (market.getPdsId().equalsIgnoreCase(marketId))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clickOnSelectionInTopBetsWidget(int number) {
        List<WebElement> selectionList = find(TOP_BET_ELEMENT).findElements(TOP_BET_SELECTION);
        selectionList.get(number - 1).click();
    }

}
