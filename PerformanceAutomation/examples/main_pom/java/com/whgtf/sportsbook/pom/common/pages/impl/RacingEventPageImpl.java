package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.pom.common.exceptions.NoEventAvailable;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.RacingEventPage;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public abstract class RacingEventPageImpl extends AbstractSportsPageObject implements RacingEventPage {

    protected List<Event> eventList = new ArrayList<>();

    protected static final By RACING_SECTION = By.className("-racing");

    protected static final By CONTENT_ID = By.id("antePost-content");

    protected static final By EVENT = By.cssSelector(".race-event");

    protected static final By MARKET = By.cssSelector(".racecard");

    protected static final By SELECTION = By.cssSelector(".racecard-runner");

    public Event getEventByPosition(int pos){
        return eventList.get(pos);
    }

    public Event getEventById(String id) {
        for (Event event:eventList) {
            if(event.getPdsId().equalsIgnoreCase(id))
                return event;
        }
        throw new NoEventAvailable();
    }

    @Override
    public boolean isDisplayed(){
        return find(RACING_SECTION).isDisplayed() && getCurrentPageName().equals("meetings");
    }
}
