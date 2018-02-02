package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.pages.interfaces.SpecialRacingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class SpecialRacingPageImpl extends RacingEventPageImpl implements SpecialRacingPage {


    private static final By SPECIAL_RACING_SECTION = By.className("specials");

    @Override
    public void load() {
        eventList = new ArrayList<>();
        if(isElementPresent(CONTENT_ID)) {
            List<WebElement> elementList = find(CONTENT_ID).findElements(EVENT);
            for (WebElement element : elementList) {
                Event event = new Event(element.getAttribute("id"));
                List<WebElement> marketList = element.findElements(MARKET);
                for (WebElement markElement : marketList) {
                    Market market = new Market(markElement.getAttribute("id"));
                    List<WebElement> selecList = markElement.findElements(SELECTION);
                    for (WebElement selectElement : selecList) {
                        Selection selection = new Selection(selectElement.getAttribute("id"), selectElement.getAttribute("data-odds"));
                        market.addSelection(selection);
                    }
                    event.addMarket(market);
                }
                eventList.add(event);
            }
        }
    }

    @Override
    public boolean isDisplayed(){
        return find(SPECIAL_RACING_SECTION).isDisplayed() && getCurrentPageName().equals("specials");
    }

    @Override
    public void clickOnFirstSelectionFirstEvent() {
        if(eventList.isEmpty())
            load();
        find(By.id(getEventByPosition(1).getFirstMarket().getFirstSelection().getPdsId())).click();
    }

    @Override
    public void clickOnFirstSelectionByEvent(String event) {
        if(eventList.isEmpty())
            load();
        find(By.id(getEventById(event).getFirstMarket().getFirstSelection().getPdsId())).click();
    }
}
