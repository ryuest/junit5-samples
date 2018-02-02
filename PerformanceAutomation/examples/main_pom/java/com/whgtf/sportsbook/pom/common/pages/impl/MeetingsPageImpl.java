package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.model.HorseRace;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.components.impl.MeetingsNavigationComponentImpl;
import com.whgtf.sportsbook.pom.common.exceptions.NoEventAvailable;
import com.whgtf.sportsbook.pom.common.exceptions.NoSelectionAvailable;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.MeetingsPage;
import com.whgtf.sportsbook.pom.common.pages.interfaces.RacecardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class MeetingsPageImpl extends AbstractSportsPageObject implements MeetingsPage {

    private static final By MEETINGS_SECTION = By.className("meetings");

    private static final By MARKET_COLLECTIONS_MENU = By.id("marketCollectionsMenu");

    private static final By MARKET_COLLECTIONS_CONTAINERS = By.cssSelector(".events__market-container");

    private static final By RACE_AREA = By.cssSelector(".racing-highlights");

    private static final By VIEW_FULL_CARD = By.cssSelector(".racecard__button--footer");

    private static String PATH = "meetings";

    private static final By TOP_RACE_EVENT_HEADER = By.cssSelector("section[class='race-event--highlights']");

    private static final By MEETINGS = By.cssSelector(".race-meeting");

    private static final By MEETINGS_ITEM = By.cssSelector(".race-meeting__item");

    private static final By NEXTOFF = By.cssSelector("div[class='racecard racecard--highlights']");

    private static final By NEXTOFF_ITEM = By.cssSelector(".racecard-runner");

    private static final By ALL_EVENT_BUTTONS = By.cssSelector("button[data-name*=':']");




    @Autowired
    @Lazy
    private MeetingsNavigationComponentImpl meetingComponent;

    @Autowired
    @Lazy
    private RacecardPage racecardPage;

    private List<HorseRace> racesList  = new ArrayList<>();



    @Override
    public void load(){
        if(!isMobile()) {
            List<WebElement> racingList = waitElementToBeClickable(RACE_AREA, 2).findElements(By.cssSelector(".race-event--highlights"));
            for (int i = 0; i < 3; i++) {
                if(!racingList.get(i).getAttribute("class").contains("race-event--highlights--extended") &&
                        ( racingList.get(i).getAttribute("data-status")==null || racingList.get(i).getAttribute("data-status").isEmpty() ||
                                racingList.get(i).getAttribute("data-status").equalsIgnoreCase("A"))) {
                    WebElement race = racingList.get(i).findElement(By.cssSelector(".racecard"));
                    HorseRace horseRace = new HorseRace(race.getAttribute("id"));
                    List<WebElement> jockeysList = race.findElements(By.tagName("button"));
                    for (WebElement jockeys : jockeysList) {
                        if(jockeys.getAttribute("data-status").equalsIgnoreCase("A")) {
                            Selection selection = new Selection(jockeys.getAttribute("id").replace("_SP", ""), jockeys.getAttribute("data-odds"));
                            horseRace.addSelection(selection);
                        }
                    }
                    if(!horseRace.getSelectionList().isEmpty())
                        racesList.add(horseRace);
                }
            }
        }
    }

    @Override
    public boolean isMeetingsDisplayed () {
        return isElementDisplayed(MEETINGS_SECTION);
    }

    @Override
    public RacecardPage clickOnFirstViewFullCard() {
        waitToBeLoaded();
        find(VIEW_FULL_CARD).click();
        return racecardPage;
    }

    @Override
    public MeetingsNavigationComponentImpl getMeetingComponent() {
        return meetingComponent;
    }

    @Override
    public void clickOnTheFirstSelectionInTheFirstRace() {
        if(racesList.isEmpty()){
            load();
        }
        if(!isMobile()) {
            find(By.xpath("//button[contains(@id,'" + racesList.get(0).getSelectionList().get(0).getPdsId() + "')]")).click();
        } else {
            try {
                meetingComponent.clickOnTheFirstSelectionAvailable();
                racecardPage.clickOnFirstSelectionFirstEvent();
            } catch (NoEventAvailable noEventAvailable) {
                noEventAvailable.printStackTrace();
            }
        }

    }

    @Override
    public void clickOnTheFirstSelectionInTheEvent(String eventId) {
        HorseRace race = getHorseRaceByEventId(eventId);
        if(race!=null) {
            find(By.id(race.getSelectionList().get(0).getPdsId())).click();
        } else {
            throw new NoSelectionAvailable();
        }

    }

    @Override
    public RacecardPage clickOnViewFullCard(String eventId) {
        find(By.id(eventId)).findElement(VIEW_FULL_CARD).click();
        racecardPage.load();
        return racecardPage;
    }

    private HorseRace getHorseRaceByEventId(final String eventId) {
        for (HorseRace horseRace:racesList) {
            if(horseRace.getId().equalsIgnoreCase(eventId)){
                return horseRace;
            }
        }
        return null;
    }

    @Override
    public boolean isDisplayedInLanguage(String language){
        return getCurrentPageName().contains(PATH) && super.isDisplayedInLanguage(language);
    }

    @Override
    public boolean isDisplayed(){
        return find(MEETINGS_SECTION).isDisplayed() && getCurrentPageName().equals("meetings");
    }

    @Override
    public int getRaceCardsNumber(){
        return findElements(TOP_RACE_EVENT_HEADER).size();
    }

    @Override
    public boolean isMeetingsItemsDisplayed(){
        return (isElementDisplayed(MEETINGS) && isElementDisplayed(MEETINGS_ITEM));
    }

    @Override
    public boolean isNextOffItemsDisplayed() {
        return isElementDisplayed(find(NEXTOFF).findElement(NEXTOFF_ITEM));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllEventsFromGivenCompetition(String competitionID){
        List<WebElement> eventsFromGivenCompetition = find(By.id(competitionID)).findElements(ALL_EVENT_BUTTONS);
        List<String> allEventIDs = new ArrayList<>();
        for (WebElement element:eventsFromGivenCompetition) {
            allEventIDs.add(element.getAttribute("data-uid"));
        }
        return allEventIDs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMeetingsSectionDisplayed(){
        return find(MEETINGS_SECTION).isDisplayed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMarketCollectionsMenuDisplayed(){
        return find(MARKET_COLLECTIONS_MENU).isDisplayed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int marketCollectionsContainersDisplayed(){
        return findElements(MARKET_COLLECTIONS_CONTAINERS).size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMeetingMarketsPageDisplay(){
        return isMeetingsSectionDisplayed() && isMarketCollectionsMenuDisplayed() && marketCollectionsContainersDisplayed() > 0;
    }
}
