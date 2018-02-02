package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.pom.common.components.interfaces.LeftFutureRacesNavigationComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.RacecardPage;
import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Lazy
public class LeftFutureRacesNavigationComponentImpl extends AbstractCommonObject implements LeftFutureRacesNavigationComponent {

    private static final By RACING_NAVIGATION = By.cssSelector(".race-nav");

    private static final By ALL_RACES = By.cssSelector(".race-group-content__item-allRaces");

    private static final By ALL_RACES_BUTTON = By.cssSelector(".race-meeting__allRaces");

    private static final By MEETING_MARKET_BUTTON = By.cssSelector(".race-meeting__selection");

    private static final By COMPETITION_SECTION = By.cssSelector(".race-nav__group-content");

    @Autowired
    @Lazy
    private RacecardPage racecardPage;


    @Override
    public RacecardPage clickOnTheFirstAllRaces() {
        find(ALL_RACES).click();
        return racecardPage;
    }

    @Override
    public void clickOnAllRacesButtonByCompetition(String competitionId) {
        find(By.id(competitionId)).findElement(ALL_RACES_BUTTON).click();
    }

    @Override
    public void clickOnMeetingMarketsButtonByCompetition(String competitionId) {
        find(By.id(competitionId)).findElement(MEETING_MARKET_BUTTON).click();
    }

    @Override
    public void clickOnFirstRaceMeetingSelection() {

        List<WebElement> competitionList = findElements(COMPETITION_SECTION);
        outerloop:
        for (WebElement competition: competitionList) {
            if(competition.getAttribute("class").contains("-expanded")){
                String competitionId = competition.findElement(By.xpath(".//div[contains(@id,'OB_TY')]")).getAttribute("id");
                List<WebElement> elementList = competition.findElements(MEETING_MARKET_BUTTON);
                for (WebElement element:elementList) {
                    if(!element.getAttribute("class").contains("race-meeting__allRaces") &&
                            !element.getAttribute("data-name").equalsIgnoreCase("Meeting Markets")) {
                        String eventId = element.getAttribute("data-uid");
                        Event event = new Event(eventId);
                        event.setCompetition(competitionId);
                        ScenarioContext.setCurrentEvent(event);
                        element.click();
                        break outerloop;
                    }
                }
            }
        }
    }

    @Override
    public void clickOnMeetingMarketsSelection() {
        List<WebElement> competitionList = findElements(COMPETITION_SECTION);
        outerloop:
        for (WebElement competition: competitionList) {
            if(competition.getAttribute("class").contains("-expanded")){
                String competitionId = competition.findElement(By.xpath(".//div[contains(@id,'OB_TY')]")).getAttribute("id");
                List<WebElement> elementList = competition.findElements(MEETING_MARKET_BUTTON);
                for (WebElement element:elementList) {
                    if(element.getAttribute("data-name").equalsIgnoreCase("Meeting Markets")) {
                        String eventId = element.getAttribute("data-uid");
                        Event event = new Event(eventId);
                        event.setCompetition(competitionId);
                        ScenarioContext.setCurrentEvent(event);
                        element.click();
                        break outerloop;
                    }
                }
            }
        }
    }

    @Override
    public boolean isDisplayed(){
        return isElementDisplayed(RACING_NAVIGATION);
    }
}
