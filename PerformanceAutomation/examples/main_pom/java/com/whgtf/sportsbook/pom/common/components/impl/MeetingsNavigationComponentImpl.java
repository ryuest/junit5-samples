package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.pom.common.components.interfaces.MeetingsNavigationComponent;
import com.whgtf.sportsbook.pom.common.exceptions.NoEventAvailable;
import com.whgtf.sportsbook.pom.common.exceptions.NoSelectionAvailable;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.RacecardPage;
import com.whgtf.sportsbook.pom.common.pages.interfaces.RacingEventPage;
import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
@Lazy
public class MeetingsNavigationComponentImpl extends AbstractCommonObject implements MeetingsNavigationComponent {

    private static final By RACING_NAVIGATION = By.cssSelector(".race-nav");

    private static final By ALL_COLLECTIONS_DISPLAYED = By.cssSelector(".race-nav .race-nav__group-header");

    private static final By ALL_EVENTS_DISPLAYED = By.cssSelector("div[id*='OB_TY'] header[class*='race-subnav__group-header']");

    private static final By COMPONENT_SECTION = By.cssSelector(".race-event");

    private static final By RACE_TO_RACE_LINK_ACTIVE = By.cssSelector(".filter-list__link--active");

    private static final By RACE_MEETING_SELECTION = By.cssSelector(".race-meeting__selection");

    private static final By RACE_MEETING_SELECTION_SELECTED = By.cssSelector(".race-meeting__selection--selected");

    private static final String RACE_MEETING_SELECTION_BY_MEETING = "#%s .race-meeting__selection";

    private static final By TODAY_TAB = By.cssSelector(".today");

    private static final By TOMORROW_TAB = By.xpath("//a[contains(@class,'today')]/../a[2]");

    private static final String TAB_BY_GROUP_NAME = "//p[contains(text(),\"%s\")]/../aside";

    private static final String TAB_BY_MEETING_NAME = "//p[contains(text(),\"%s\")]/../aside";

    private static final String ALL_RACES_SELECTION_BY_MEETING_NAME = "//p[contains(text(),\"%s\")]/../..//button[contains(@class,'race-meeting__allRaces')]";

    private static final By FIRST_SELECTION_AVAILABLE = By.cssSelector(".race-meeting__selection--best-odds-guaranteed");

    private static final By FIRST_EVENT_AVAILABLE = By.cssSelector(".race-group-content__item-link");

    private static final By ALL_RACES_BUTTON = By.cssSelector(".race-meeting__allRaces");

    private static final By MEETING_MARKET_BUTTON = By.cssSelector(".race-meeting__selection");

    private static final By COMPETITION_SECTION = By.cssSelector(".race-nav__group-content");

    private static final By MY_WHTV_RACES = By.cssSelector(".myWhtvRaces");

    private static final By ERROR_MESSAGE_MY_WHTV_RACES = By.cssSelector(".myWhtvRaces .race-meeting__header p");

    private static final By RACE_MEETING_ADDED = By.cssSelector("button[class='race-meeting__plus race-meeting__plus--active -added']");

  //  private static final By ALL_COLLAPSED_REGIONS = By.xpath("//i[contains(@class, 'collapsed') and not(contains(@class, 'expanded'))]");

    private static final String RACE_ID_BUTTON_NAVIGATION = "button[data-entityid='%s']";

    private static final String RACE_BUTTON_NAVIGATION_PLUS = "button[class='race-meeting__plus']";

    private static final String MEETING_WITH_MEETING_MARKETS = "//*[@id='%s']//button[contains(@data-name,'Meeting Markets')]";

    private static final By MEETING_HEADER = By.cssSelector(".race-subnav__group");

    private static final By ALL_COLLAPSED_GROUPS = By.cssSelector("header[class='race-nav__group-header']");

    private static final By ALL_COLLAPSED_REGIONS = By.cssSelector("header[class='race-subnav__group-header']");

    private final String NAVIGATION_RACENAME = "button[data-uid='%s']";

    private static final By RACE_MEETING_KEY_ICON_PLACEHOLDER = By.cssSelector("i[class='icon-placeholder']");

    private static final By RACE_MEETING_KEY_ICON_TV = By.xpath("//button[contains(@class,'--tv')]");

    private static final By RACE_MEETING_KEY_ICON_RESULTS = By.xpath(".//i[@class='icon__results']");

    private final String RACE_MEETING_SELECTION_BY_GROUP_ID = "//*[@id='%s']//button[contains(@class,'race-meeting__selection')]";

    private final By ALL_REGIONS = By.xpath("//p[contains(@class, 'race-nav__group-header-name') and not(contains(.,'Next Off'))]");

    // This one is the "expandable / collapse" area
    private final By ALL_RACE_MEETINGS = By.cssSelector(".race-subnav__group header[class^='race-subnav__group']");

    // And this one is the content of the Race Meetings
    private final By ALL_RACE_MEETINGS_SECTIONS = By.cssSelector("section[class^='race-subnav__group-content']");

    // These are All the Races from the Meetings - Note that this can return all the races displayed (Selections).
    private final By SINGLE_RACE = By.cssSelector("button[data-uid^='OB_EV']");

    // These are All the Resulted Races
    private final By ALL_RESULTED_RACES = By.cssSelector(".icon__results");

    private static final By ALL_ALL_RACES_BUTTONS = By.cssSelector("button[data-name='All Races']");


    @Autowired
    @Lazy
    private RacingEventPage racingEventPage;

    @Autowired
    @Lazy
    private RacecardPage racecardPage;

    @Override
    public boolean todayAndTomorrowTabsDisplayed(){
        return find(TODAY_TAB).isDisplayed() && find(TOMORROW_TAB).isDisplayed();
    }

    @Override
    public boolean isTodayTabSelected(){
        return find(TODAY_TAB).getAttribute("class").contains("active");
    }

    @Override
    public boolean isTomorrowTabSelected(){
        return find(TOMORROW_TAB).getAttribute("class").contains("active");
    }

    @Override
    public void clickOnTodayMeetings() {
        click(TODAY_TAB);
    }

    @Override
    public void clickOnTomorrowMeetings() {
        click(TOMORROW_TAB);
    }

    @Override
    public void openGroupByName(String name) {
        if (!find(By.xpath(String.format(TAB_BY_GROUP_NAME, name))).getAttribute("class").contains("-opened")) {
            click(By.xpath(String.format(TAB_BY_GROUP_NAME, name)));
        }
    }

    @Override
    public void openRaceMeetingByName(String name) {
        if (!find(By.xpath(String.format(TAB_BY_MEETING_NAME, name))).getAttribute("class").contains("-opened")) {
            click(By.xpath(String.format(TAB_BY_MEETING_NAME, name)));
        }
    }

    @Override
    public void clickOnAllRacesByGroupNameAndMeetingName(String groupName, String meetingName) {
        openGroupByName(groupName);
        openRaceMeetingByName(meetingName);
        click(By.xpath(String.format(ALL_RACES_SELECTION_BY_MEETING_NAME, meetingName)));
    }

    @Override
    public RacingEventPage clickOnTheFirstAllRaces() {
        click(By.xpath("(//button[contains(@class,'race-meeting__allRaces')])[1]"));
        return racingEventPage;
    }


    @Override
    public void clickOnRandomRace() {
        click(RACE_MEETING_SELECTION);
    }

    @Override
    public void clickOnFutureRaceByMeetingId(String meetingId, int eventNumber) {
        List<WebElement> validEvents = new ArrayList<>();
        List<WebElement> events = findElements(By.cssSelector(String.format(RACE_MEETING_SELECTION_BY_MEETING, meetingId)));
        for (WebElement element:events) {
            try {
                String eventId = element.getAttribute("data-entityid");
                if ((eventId!=null)) {
                    if (!getRaceByMeetingKeyForId(eventId, "Results")) {
                        validEvents.add(element);
                    }
                }
            } catch (NoSuchElementException e) {
            }
        }
        click(validEvents.get(eventNumber));
    }

    @Override
    public void clickOnFutureRaceInAnyExpandedMeeting(int eventNumber) {
        List<WebElement> competitionList = findElements(COMPETITION_SECTION);
        String competitionId = null;
        outerloop:
        for (WebElement competition: competitionList) {
            if (competition.getAttribute("class").contains("-expanded")) {
                competitionId = competition.findElement(By.xpath(".//div[contains(@id,'OB_TY')]")).getAttribute("id");
                break outerloop;
            }
        }
        List<WebElement> validEvents = new ArrayList<>();
        List<WebElement> events = findElements(By.cssSelector(String.format(RACE_MEETING_SELECTION_BY_MEETING, competitionId)));
        for (WebElement element:events) {
            try {
                String eventId = element.getAttribute("data-entityid");
                if ((eventId!=null)) {
                    if (!getRaceByMeetingKeyForId(eventId, "Results")) {
                        validEvents.add(element);
                    }
                }
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
        click(validEvents.get(eventNumber));
    }

    @Override
    public void expandAllRegions() {
        List<WebElement> allRegions = findElements(ALL_REGIONS);
        for (WebElement region:allRegions) {
            if(!region.findElement(By.xpath("./..")).getAttribute("class").contains("-expanded")){
                waitElementToBeVisible(region, 2);
                click(region);
            }
        }
    }

    @Override
    public void expandAllMeetings() {
        List<WebElement> allMeetings = findElements(ALL_RACE_MEETINGS);
        for (WebElement meetings : allMeetings) {
            if(!meetings.getAttribute("class").contains("expanded")){
                waitElementToBeVisible(meetings, 2);
                click(meetings);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void expandAllRegionsAndMeetings() {
        expandAllRegions();
        expandAllMeetings();
    }

    @Override
    public void clickOnActiveRaceThatHasMoreThanOneRaceOnEvent() {

        // Here we expand all Regions and Meetings
        expandAllRegionsAndMeetings();

        // Here we look for our specific scenario
        List<WebElement> allRaceContents = findElements(ALL_RACE_MEETINGS_SECTIONS);
        for (WebElement raceContent : allRaceContents) {
            if(raceContent.findElements(SINGLE_RACE).size() >= 2){
                if(raceContent.findElements(SINGLE_RACE).size() - raceContent.findElements(ALL_RESULTED_RACES).size() >= 2){
                    click(raceContent.findElements(SINGLE_RACE).get(1));
                }
            }
        }
    }


    public boolean getRaceByMeetingKeyForId(String eventId, String key) {
        try {
            Color expectedColor;
            Color currentColor;
            List<WebElement> type;
            switch (key) {
                case "Early Prices":
                    try{
                    expectedColor = Color.fromString("#a9da11");
                    type = find(By.cssSelector(String.format(NAVIGATION_RACENAME, eventId))).findElements(RACE_MEETING_KEY_ICON_PLACEHOLDER);
                    currentColor = Color.fromString(type.get(1).getCssValue("background-color"));
                        return (currentColor.equals(expectedColor));
                    } catch (NoSuchElementException w) {
                        return false;
                    }
                case "Best Odds Guaranteed":
                    try{
                    expectedColor = Color.fromString("#f1b849");
                    type = find(By.cssSelector(String.format(NAVIGATION_RACENAME, eventId))).findElements(RACE_MEETING_KEY_ICON_PLACEHOLDER);
                    currentColor = Color.fromString(type.get(1).getCssValue("background-color"));
                        return (currentColor.equals(expectedColor));
                    } catch (NoSuchElementException w) {
                        return false;
                    }
                case "Qualified Race":
                    try{
                    expectedColor = Color.fromString("#ffd400");
                    type = find(By.cssSelector(String.format(NAVIGATION_RACENAME, eventId))).findElements(RACE_MEETING_KEY_ICON_PLACEHOLDER);
                    currentColor = Color.fromString(type.get(1).getCssValue("background-color"));
                        return (currentColor.equals(expectedColor));
                    } catch (NoSuchElementException w) {
                        return false;
                    }
                case "Results":
                    try {
                        return find(By.cssSelector(String.format(NAVIGATION_RACENAME, eventId))).findElement(RACE_MEETING_KEY_ICON_RESULTS).isDisplayed();
                    } catch (NoSuchElementException w) {
                        return false;
                    }
                case "Race Replay":
                    try {
                        return find(By.cssSelector(String.format(NAVIGATION_RACENAME, eventId))).findElement(RACE_MEETING_KEY_ICON_TV).isDisplayed();
                    } catch (NoSuchElementException w) {
                        return false;
                    }
                default:
                    throw new IllegalArgumentException("Invalid sport: " + key);
            }
        } catch (IllegalArgumentException z) {
            return false;
        }
    }

    @Override
    public int numberOfRacesDisplayedByMeetingId(String meetingId) {
        List<WebElement> elementList = findElements(By.cssSelector(String.format(RACE_MEETING_SELECTION_BY_MEETING, meetingId)));
        List<WebElement> elementListDisplayed = new ArrayList<>();
        for (WebElement race : elementList) {
            if (race.isDisplayed()) {
                elementListDisplayed.add(race);
            }
        }
        return elementListDisplayed.size();
    }

    @Override
    public List<String> findMeetingsWithMeetingMarkets() {
        List<WebElement>  meetings = findElements(MEETING_HEADER);
        List<String> resultList = new ArrayList<>();
        for (WebElement meetingsActive:meetings) {
            if (meetingsActive.isDisplayed()) {
                try {
                    meetingsActive.findElement(By.xpath(String.format(MEETING_WITH_MEETING_MARKETS, meetingsActive.getAttribute("id"))));
                    resultList.add(meetingsActive.getAttribute("id"));
                } catch (WebDriverException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }

    @Override
    public List<String> findMeetingsWithoutMeetingMarkets() {
        List<WebElement> meetings = findElements(MEETING_HEADER);
        List<String> resultList = new ArrayList<>();
        for (WebElement meetingsActive:meetings) {
            if (meetingsActive.isDisplayed() && meetingsActive.getAttribute("id").contains("OB_TY")) {
                try {
                    meetingsActive.findElement(By.xpath(String.format(MEETING_WITH_MEETING_MARKETS, meetingsActive.getAttribute("id"))));
                } catch (WebDriverException zz) {
                    resultList.add(meetingsActive.getAttribute("id"));
                }
            }
        }
        return resultList;
    }

    @Override
    public List<String> findOneMeetingWithOrWithoutMeetingMarkets(boolean withMeetingMarket) {
        List<WebElement> meetings = findElements(MEETING_HEADER);
        List<String> resultList = new ArrayList<>();
        for (WebElement meetingsActive : meetings) {
            if (meetingsActive.isDisplayed() && meetingsActive.getAttribute("id").contains("OB_TY")) {
                if (meetingsActive.findElements(By.xpath(String.format(RACE_MEETING_SELECTION_BY_GROUP_ID, meetingsActive.getAttribute("id")))).size() < 2) {
                    try {
                        meetingsActive.findElement(By.xpath(String.format(MEETING_WITH_MEETING_MARKETS, meetingsActive.getAttribute("id"))));
                    } catch (WebDriverException zz) {
                        if (!withMeetingMarket) {
                            resultList.add(meetingsActive.getAttribute("id"));
                        }
                    }
                }
            }
        }
        return resultList;
    }


    @Override
    public void clickOnTheFirstRaceByGroupNameAndMeetingName(String groupName, String meetingName) {
        openGroupByName(groupName);
        openRaceMeetingByName(meetingName);
        click(find(COMPONENT_SECTION).findElement(By.xpath("//button[0]")));
    }

    @Override
    public RacecardPage clickOnTheFirstSelectionAvailable() {
        if(isElementPresent(FIRST_SELECTION_AVAILABLE)) {
            click(FIRST_SELECTION_AVAILABLE);
            return racecardPage;
        } else {
            throw new NoSelectionAvailable();
        }
    }


    public RacecardPage clickOnTheFirstEvent() {
        List<WebElement> elementList = findElements(FIRST_EVENT_AVAILABLE);
        if(!elementList.isEmpty()) {
            click(elementList.get(1));
            return racecardPage;
        } else {
            throw new NoEventAvailable();
        }
    }

    @Override
    public void clickOnAllRacesButtonByCompetition(String competitionId) {
        WebElement element = find(By.id(competitionId)).findElement(ALL_RACES_BUTTON);
        click(element);
    }

    @Override
    public void clickOnMeetingMarketsButtonByCompetition(String competitionId) {
        click(find(By.id(competitionId)).findElement(MEETING_MARKET_BUTTON));
    }

    @Override
    public void addRaceForCompetitionByEventId(String competitionId, String eventId) {
        click(find(By.id(competitionId)).findElement(By.cssSelector(String.format(RACE_ID_BUTTON_NAVIGATION, eventId))));
    }

    @Override
    public void addSeveralRacecardsForCompetition(String competitionId, int number) {
        List<WebElement> elementList = find(By.id(competitionId)).findElements(By.cssSelector(RACE_BUTTON_NAVIGATION_PLUS));
        for (WebElement element:elementList) {
            if (number > 0) {
                click(element);
                sleep(1000);
            }
            number--;
        }
    }

    @Override
    public void removeSeveralRacecardsForCompetition(String competitionId, int number) {
        List<WebElement> elementList = findElements(By.cssSelector(String.format("#%s .race-meeting__plus--active.-added", competitionId)));
        for (WebElement element : elementList) {
            if (number > 0) {
                click(element);
                sleep(1000);
            }
            number--;
        }
    }

    @Override
    public void clickOnFirstRaceMeetingSelection() {

        List<WebElement> competitionList = findElements(COMPETITION_SECTION);
        outerloop:
        for (WebElement competition: competitionList) {
            if(competition.getAttribute("class").contains("-expanded")){
                String competitionId = competition.findElement(By.xpath(".//div[contains(@id,'OB_TY')]")).getAttribute("id");
                List<WebElement> elementList = competition.findElements(MEETING_MARKET_BUTTON);
                String competitionName = competition.findElement(By.tagName("p")).getText();
                for (WebElement element:elementList) {
                    click(element);
                    if(!element.getAttribute("class").contains("race-meeting__allRaces") &&
                            (!element.getAttribute("data-name").equalsIgnoreCase("Meeting Markets") && !element.getAttribute("data-status").equals("S"))) {
                        String eventId = element.getAttribute("data-uid");
                        Event event = new Event(eventId);
                        event.setCompetition(competitionName);
                        ScenarioContext.setCurrentEvent(event);
                        break outerloop;
                    }
                }
            }
        }
    }

    @Override
    public void clickOnMeetingMarketsSelection() {
        sleep(2000);
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
                        click(element);
                        break outerloop;
                    }
                }
            }
        }
    }

    @Override
    public void clickOnAllRacesSelection() {
        List<WebElement> competitionList = findElements(COMPETITION_SECTION);
        outerloop:
        for (WebElement competition: competitionList) {
            if(competition.getAttribute("class").contains("-expanded")){
                String competitionId = competition.findElement(By.xpath(".//div[contains(@id,'OB_TY')]")).getAttribute("id");
                List<WebElement> elementList = competition.findElements(MEETING_MARKET_BUTTON);
                for (WebElement element:elementList) {
                    if(element.getAttribute("class").contains("race-meeting__allRaces")) {
                        click(element);
                        break outerloop;
                    }
                }
            }
        }
    }

    public void clickOnRaceMeetingSelectionByPosition(int position){
        List<WebElement> competitionList = findElements(COMPETITION_SECTION);
        int pos = 1;
        outerloop:
        for (WebElement competition: competitionList) {
            if(competition.getAttribute("class").contains("-expanded")){
                String competitionId = competition.findElement(By.xpath(".//div[contains(@id,'OB_TY')]")).getAttribute("id");
                List<WebElement> elementList = competition.findElements(MEETING_MARKET_BUTTON);
                String competitionName = competition.findElement(By.tagName("p")).getText();
                for (WebElement element:elementList) {
                    click(element);
                    if(!element.getAttribute("class").contains("race-meeting__allRaces") &&
                            (!element.getAttribute("data-name").equalsIgnoreCase("Meeting Markets") && !element.getAttribute("data-status").equals("S"))) {
                        if(position==pos) {
                            String eventId = element.getAttribute("data-uid");
                            Event event = new Event(eventId);
                            event.setCompetition(competitionName);
                            ScenarioContext.setCurrentEvent(event);
                            break outerloop;
                        } else {
                            pos++;
                        }
                    }
                }
            }
        }
    }

    public boolean isRaceMeetingSelectionHighlightedByPosition(int position) {
        List<WebElement> competitionList = findElements(COMPETITION_SECTION);
        int pos = 1;
        for (WebElement competition: competitionList) {
            if(competition.getAttribute("class").contains("-expanded")){
                String competitionId = competition.findElement(By.xpath(".//div[contains(@id,'OB_TY')]")).getAttribute("id");
                List<WebElement> elementList = competition.findElements(MEETING_MARKET_BUTTON);
                for (WebElement element:elementList) {
                    click(element);
                    sleep(500);
                    if(!element.getAttribute("class").contains("race-meeting__allRaces") &&
                            (!element.getAttribute("data-name").equalsIgnoreCase("Meeting Markets") && !element.getAttribute("data-status").equals("S"))) {
                        if(position==pos) {
                            Color colorObtained = Color.fromString(element.getCssValue("background-color"));
                            Color expecterColor = Color.fromString("#ffc703");
                            return element.getAttribute("class").contains("race-meeting__selection--qualified") && colorObtained.equals(expecterColor);
                        } else {
                            pos++;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkQualifiesRacesHighlightedInYellow(final int races){
        openMyWHTVRacesGroup();
        List<WebElement> buttonList = find(MY_WHTV_RACES).findElements(By.tagName("button"));
        if(buttonList.size()!=races)
            return false;
        for (WebElement button:buttonList) {
            if(!Color.fromString(button.getCssValue("background-color")).equals(Color.fromString("#ffd400")))
                return false;
        }
        return true;
    }

    public void openMyWHTVRacesGroup(){
        if(!find(MY_WHTV_RACES).findElement(By.xpath("../header")).getAttribute("class").contains("-expanded")) {
            click(find(MY_WHTV_RACES).findElement(By.xpath("../header/aside")));
        }
    }

    public String getMessageInMyWHTVRacesGroup(){
        openMyWHTVRacesGroup();
        List<WebElement> messagesList = findElements(ERROR_MESSAGE_MY_WHTV_RACES);
        for (WebElement message:messagesList) {
            if(message.isDisplayed())
                return message.getText();
        }

        return StringUtils.EMPTY;
    }

    @Override
    public boolean isDisplayed(){
        return isElementDisplayed(RACING_NAVIGATION);
    }

    @Override
    public int getRacesAddedActiveNumberInLeftNavigation(){
         return findElements(RACE_MEETING_ADDED).size();
    }

    @Override
    public boolean isRaceHighlightedAndInRaceCard() {
        return find(RACE_MEETING_SELECTION_SELECTED).getAttribute("data-uid").equals(find(RACE_TO_RACE_LINK_ACTIVE).getAttribute("data-uid"));
    }


    public List<WebElement> getDisplayedRacesInExpandedGroup() {
        List<WebElement> elementList = find(By.xpath("//section[contains(@class,'-expanded')]")).findElements(By.xpath("//*[@class='race-group-content__item']"));
        List<WebElement> elementListDisplayed = new ArrayList<>();
        for (WebElement list : elementList) {
            if (list.isDisplayed()) {
                elementListDisplayed.add(list);
            }
        }
        return elementListDisplayed;
    }

    @Override
    public int getNumberDisplayedRacesInExpandedGroup() {
        return getDisplayedRacesInExpandedGroup().size();
    }

    @Override
    public void clickOnTheNumberEvent(int number) {
        List<WebElement> elementList = getDisplayedRacesInExpandedGroup();
        if(!elementList.isEmpty()) {
            click(elementList.get(number));
        } else {
            throw new NoEventAvailable();
        }
    }

    @Override
    public void expandAllCollectionsAndEvents(){

        // Here we expand all collections
        List<WebElement> allCollectionsDisplayed = findElements(ALL_COLLECTIONS_DISPLAYED);

        for (WebElement element:allCollectionsDisplayed) {
            if(!element.getAttribute("class").contains("-expanded")){
                click(element);
            }
        }

        // Here we expand all Events
        List<WebElement> allEventsDisplayed = findElements(ALL_EVENTS_DISPLAYED);
        for (WebElement element:allEventsDisplayed) {
            if(!element.getAttribute("class").contains("-expanded")){
                click(element);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnGivenRaceFromGivenCompetition(String eventId, String competitionId){
        click(find(By.id(competitionId)).findElement(By.cssSelector(String.format("button[data-uid='%s']", eventId))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnGivenAllRaces(String competitionId){
        click(find(By.id(competitionId)).findElement(ALL_ALL_RACES_BUTTONS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllRacesIDsFromGivenCompetition(String competitionId){

        List<WebElement> allRaceFromGivenCompetition = find(By.id(competitionId)).findElements(By.cssSelector("button[data-uid^='OB_EV']"));
        List<String> allRacesIDs = new ArrayList<>();

        for (WebElement race:allRaceFromGivenCompetition) {
            allRacesIDs.add(race.getAttribute("data-uid"));
        }
        return allRacesIDs;
    }


}
