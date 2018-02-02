package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.pages.interfaces.RacecardPage;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.Fraction;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Component
@Lazy
public class RacecardPageImpl extends RacingEventPageImpl implements RacecardPage {

    private static final By RACE_PANEL = By.cssSelector(".racecard");

    private static final By ALL_HORSE_NUMBER_SORT = By.cssSelector(".racecard-header__number");

    private static final By ALL_DRAW_SORT = By.cssSelector(".racecard-header__draw");

    private static final By ALL_SELECTION_SORT = By.cssSelector(".racecard-header__selection");

    private static final By ALL_RP_SORT = By.cssSelector(".racecard-header__racing-post");

    private static final By ALL_PRICE_SORT = By.cssSelector(".racecard-header__price");

    private static final By RACE_CARD_ELEMENT = By.cssSelector(".racecard-header");

    private static final By ALL_RACES_ON_PAGE = By.cssSelector(".racecard__main");

    private static final By ALL_HORSE_NUMBERS_ON_PAGE = By.cssSelector(".racecard-runner__number.racecard-runner__number--horse");

    private static final By ALL_DOG_NUMBERS_ON_PAGE = By.cssSelector(".racecard-runner__number.racecard-runner__number--greyhound");

    private static final By ALL_DRAWS_ON_PAGE = By.cssSelector(".racecard-runner__draw-number");

    private static final By ALL_SELECTIONS_ON_PAGE = By.cssSelector(".racecard-runner__column-selection--racing");

    private static final By SELECTION_ACTIVE = By.xpath("//button[contains(@class,'oddsbutton') and not(contains(@data-odds, 'SP'))]");

    private static final By ALL_RP_ON_PAGE = By.cssSelector(".racecard-runner__racing-post-container span");

    private static final By ALL_PRICES_ON_PAGE = By.cssSelector(".selectiondetails.racecard-button__price");

    private static final By RACES_ADDED_ON_CARDBOARD = By.cssSelector("#meetings_canvas .racesNumber");

    private static final By RACES_ADDED_MOBILE = By.cssSelector(".race-stickyheader__number");

    private static final By RACE_EVENT_MESSAGE = By.cssSelector(".raceEventMessages");

    private static final By TV_ICON_ENABLED = By.cssSelector(".race-event__header .race-header__toolbar-item--tv");

    private static final By PREVIOUS_ODDS_HEADER = By.cssSelector(".racecard-header__previous-price");

    private static final By MARKET_COLLECTION_MENU = By.xpath("//div[contains(@id,'marketCollectionsMenu_OB_EV')]");

    private static final String MARKET_COLLECTION_LINK = "//div[contains(@id,'marketCollectionsMenu_OB_EV')]//li[contains(@class,'filter-list__item')]//span[contains(text(),'%s')]";

    private static final String EVENT_SECTION_ID = "section[id='%s']";

    private static final String PREVIOUS_ODDS_BY_SELECITON_ID = "//button[@id='%s']/../../div[contains(@class,'racecard-runner__previous-odds')]";

    private static final By RACECARD_COUNTER_HEADER = By.cssSelector("h1[class='header-panel__title header-panel__title--counter']");

    private static final By RACECARD_COUNTER_HEADER_MOBILE = By.cssSelector("p[class='race-stickyheader__number']");

    private static final By RACECARD_DETAILS = By.cssSelector(".racecard__main");

    private static final By RACES_NUMBER = By.cssSelector("em[class='racesNumber']");

    private static final By RACES_NUMBER_MOBILE = By.cssSelector("span[class='racesNumber']");

    private List<Selection> selectionList = new ArrayList<>();

    private static final By RACES_CARD_HEADER = By.cssSelector(".race-event header");

    private static final By MEETING_MARKETS_CONTENT = By.cssSelector("section[data-ismeetingmarket='true']");

    private static final By RACE_TO_RACE_ALL_LINKS = By.cssSelector("#marketCollectionItemsList li[class='filter-list__item']");

    private static final By RACE_TO_RACE_NAVIGATION = By.cssSelector(".race-to-race-navigation");

    private static final By HIGHLIGHTED_RACE_IN_RACE_TO_RACE = By.cssSelector("#marketCollectionItemsList a[class*='active']");

    private static final By RACE_TO_RACE_LINK_ALL_RACES = By.xpath("//a[contains(@class,'filter-list__link filter-list__allRaces')]");

    private static final By RACE_TO_RACE_LINK_OTHER_RACE_INACTIVE = By.xpath("//a[contains(@class,'filter-list__link filter-list__otherRace') and not(contains(@class, '-active'))]");

    private static final By RACE_TO_RACE_LINK_MEETING_MARKETS = By.xpath("//a[contains(@class,'filter-list__link filter-list__meetingMarkets')]");


    // --------------------------- New! ---------------------------

    private static final By RACE_TITLE = By.cssSelector(".race-event-wrapper .header-panel__title");

    private static final By ALL_COLLECTIONS_DISPLAYED = By.cssSelector(".race-nav .race-nav__group");

    private static final By ALL_EVENTS_DISPLAYED = By.cssSelector(".race-subnav div[id*='OB_TY']");

    private static final By ALL_RACES_DISPLAYED_ON_MENU = By.cssSelector("button[data-racename*=':']");

    private static final By ALTERNATE_MEETING_DROPDOWN = By.cssSelector(".dropdown__inner");

    private static final By ALL_ALTERNATE_MEETINGS_OPTIONS = By.cssSelector(".dropdown__select.dropdown__select--noborder li");

    private static final By ALTERNATE_MEETING_OPTIONS_UNDER_DROPDOWN = By.cssSelector("li[class='dropdown__option'] a");

    private static final By ALTERNATE_MEETING_SELECTED_OPTION = By.cssSelector(".dropdown__option.dropdown__option--active");

    private static final By ALL_RACES_R2R_NAVIGATION_LINK = By.cssSelector("#marketCollectionItemsList a[class*='allRaces']");

    private static final By ALL_RACES_TITLES_DISPLAYED_ON_RACECARD = By.cssSelector(".race-header__name");

    private static final By ALL_ALL_RACES_BUTTONS_UNDER_EVENTS_MENU = By.xpath(".//button[@data-name='All Races']");

    private static final By ALL_RESULTED_RACES_BUTTONS_UNDER_EVENTS_MENU = By.xpath(".//button[@data-settled='true']");

    private static final By ALL_RACES_BUTTONS_UNDER_EVENTS_MENU = By.xpath(".//button[@data-settled='false']");

    private static final By ALL_EVENT_TITLES_DISPPLAYED = By.cssSelector(".race-subnav__group-header-name");

    private static final By RACE_CONTENT = By.cssSelector(".race-event-wrapper");

    private static final By RACE_TO_RACE_LINK_OTHER_RACE = By.xpath("//a[contains(@class,'filter-list__link filter-list__otherRace')]");

    private static final By RACE_TO_RACE_LINK = By.cssSelector("li[class='filter-list__item']");

    private static final By MEETING_MARKET_BUTTON = By.cssSelector("button[data-name='Meeting Markets']");

    private static final By EVENT_SECTION = By.cssSelector("#contentarea.-racing");



    @Override
    public void load() {
        waitSportsbook();
        if(find(RACE_PANEL).isDisplayed()) {
            List<WebElement> selectionList = find(RACE_PANEL).findElements(By.tagName("article"));
            for (WebElement selection : selectionList) {
                if (!selection.findElement(By.tagName("section")).getAttribute("class").contains("racecard-runner__non-runner")
                        && !selection.findElement(By.tagName("section")).getAttribute("class").contains("racecard-runner__main-details--unnamed-favourite-runner--unnamed-favourite")) {
                    Selection sel = new Selection(selection.findElement(By.tagName("button")).getAttribute("id"),
                            selection.findElement(By.tagName("button")).getAttribute("data-odds"));
                    this.selectionList.add(sel);
                }
            }
        }
    }

    public void open() {
        open(BASE_URL.concat(PATH));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRacePageContentDisplayed() {
        return isElementDisplayed(RACE_CONTENT);
    }

    @Override
    public boolean areRaceDetailsDisplayed() {
        return findElements(RACECARD_DETAILS).size() > 0;
    }

    @Override
    public void clickOnFirstSelection() {
        if(selectionList.isEmpty())
            load();
        String selectionId = selectionList.get(0).getPdsId();
        click(By.id(selectionId));
    }

    @Override
    public void clickOnSelectionById(String id) {
        click(By.id(id));
    }

    @Override
    public void clickOnSelectionByPosition(int position) {
        if(selectionList.isEmpty())
            load();
        String selectionId = selectionList.get(position).getPdsId();
        click(By.id(selectionId));
    }

    @Override
    public List<Selection> getSelectionList() {
        if(selectionList.isEmpty())
            load();
        return selectionList;
    }

    @Override
    public Selection getFirstSelection() {
        if(selectionList.isEmpty())
            load();
        return  selectionList.get(0);
    }

    @Override
    public int numberOfRacesDisplayedOnRacecard() {
        if(!isMobile()) {
            if (find(RACECARD_COUNTER_HEADER).findElement(RACES_NUMBER).getText().equalsIgnoreCase(StringUtils.EMPTY))
                return 0;
            return Integer.parseInt(find(RACECARD_COUNTER_HEADER).findElement(RACES_NUMBER).getText());
        } else {
            if (find(RACECARD_COUNTER_HEADER_MOBILE).findElement(RACES_NUMBER_MOBILE).getText().equalsIgnoreCase(StringUtils.EMPTY))
                return 0;
            return Integer.parseInt((find(RACECARD_COUNTER_HEADER_MOBILE).findElement(RACES_NUMBER_MOBILE).getText()).replaceAll("[A-Za-z ]",StringUtils.EMPTY));
        }
    }

    @Override
    public void clickOnFirstSelectionFirstEvent() {
        click(SELECTION_ACTIVE);
    }

    @Override
    public void clickOnFirstSelectionByEvent(String event) {

    }

    @Override
    public boolean isDisplayed(){
        return find(EVENT_SECTION).isDisplayed() && getCurrentPageName().equals("meetings");
    }

    @Override
    public void clickOnSortByNumberByRace(int raceNumber,boolean ascending) {
//        givenListClickOnGivenElement(ALL_HORSE_NUMBER_SORT, raceNumber);
        raceNumber = raceNumber - 1;
        List<WebElement> sortsByNumber = findElements(RACE_CARD_ELEMENT);
        String sorted = sortsByNumber.get(raceNumber).findElement(ALL_HORSE_NUMBER_SORT).getAttribute("data-sortasc");
        if(sorted==null)
            sorted = StringUtils.EMPTY;
        if (ascending){
            if (!sorted.equals("true")) {
                sortsByNumber.get(raceNumber).findElement(ALL_HORSE_NUMBER_SORT).click();
                sorted = sortsByNumber.get(raceNumber).findElement(ALL_HORSE_NUMBER_SORT).getAttribute("data-sortasc");
            }
            if (!sorted.equals("true"))
                sortsByNumber.get(raceNumber).findElement(ALL_HORSE_NUMBER_SORT).click();
        } else {
            if (!sorted.equals("false")) {
                sortsByNumber.get(raceNumber).findElement(ALL_HORSE_NUMBER_SORT).click();
                sorted = sortsByNumber.get(raceNumber).findElement(ALL_HORSE_NUMBER_SORT).getAttribute("data-sortasc");
            }
            if (!sorted.equals("false"))
                sortsByNumber.get(raceNumber).findElement(ALL_HORSE_NUMBER_SORT).click();
        }
    }

    @Override
    public void clickOnSortByDrawByRace(int raceNumber,boolean ascending) {
//        givenListClickOnGivenElement(ALL_DRAW_SORT, raceNumber);
        raceNumber = raceNumber - 1;
        List<WebElement> sortsByNumber = findElements(RACE_CARD_ELEMENT);
        String sorted = sortsByNumber.get(raceNumber).findElement(ALL_DRAW_SORT).getAttribute("data-sortasc");
        if(sorted==null)
            sorted = StringUtils.EMPTY;
        if (ascending) {
            if (!sorted.equals("true")) {
                sortsByNumber.get(raceNumber).findElement(ALL_DRAW_SORT).click();
                sorted = sortsByNumber.get(raceNumber).findElement(ALL_DRAW_SORT).getAttribute("data-sortasc");
            }
            if (!sorted.equals("true"))
                sortsByNumber.get(raceNumber).findElement(ALL_DRAW_SORT).click();
        } else {
            if (!sorted.equals("false")) {
                sortsByNumber.get(raceNumber).findElement(ALL_DRAW_SORT).click();
                sorted = sortsByNumber.get(raceNumber).findElement(ALL_DRAW_SORT).getAttribute("data-sortasc");
            }
            if (!sorted.equals("false"))
                sortsByNumber.get(raceNumber).findElement(ALL_DRAW_SORT).click();
        }
    }


    @Override
    public void clickOnSortBySelectionByRace(int raceNumber,boolean ascending) {
//        givenListClickOnGivenElement(ALL_SELECTION_SORT, raceNumber);
        raceNumber = raceNumber - 1;
        List<WebElement> sortsByNumber = findElements(RACE_CARD_ELEMENT);
        String sorted = sortsByNumber.get(raceNumber).findElement(ALL_SELECTION_SORT).getAttribute("data-sortasc");
        if(sorted==null)
            sorted = StringUtils.EMPTY;
        if (ascending) {
            if (!sorted.equals("true")) {
                sortsByNumber.get(raceNumber).findElement(ALL_SELECTION_SORT).click();
                sorted = sortsByNumber.get(raceNumber).findElement(ALL_SELECTION_SORT).getAttribute("data-sortasc");
            }
            if (!sorted.equals("true"))
                sortsByNumber.get(raceNumber).findElement(ALL_SELECTION_SORT).click();
        } else {
            if (!sorted.equals("false")) {
                sortsByNumber.get(raceNumber).findElement(ALL_SELECTION_SORT).click();
                sorted = sortsByNumber.get(raceNumber).findElement(ALL_SELECTION_SORT).getAttribute("data-sortasc");
            }
            if (!sorted.equals("false"))
                sortsByNumber.get(raceNumber).findElement(ALL_SELECTION_SORT).click();
        }
    }

    @Override
    public void clickOnSortByRPByRace(int raceNumber,boolean ascending) {
//        givenListClickOnGivenElement(ALL_RP_SORT, raceNumber);
        raceNumber = raceNumber - 1;
        List<WebElement> sortsByNumber = findElements(RACE_CARD_ELEMENT);
        String sorted = sortsByNumber.get(raceNumber).findElement(ALL_RP_SORT).getAttribute("data-sortasc");
        if(sorted==null)
            sorted = StringUtils.EMPTY;
        if (ascending) {
            if (!sorted.equals("true")) {
                sortsByNumber.get(raceNumber).findElement(ALL_RP_SORT).click();
                sorted = sortsByNumber.get(raceNumber).findElement(ALL_RP_SORT).getAttribute("data-sortasc");
            }
            if (!sorted.equals("true"))
                sortsByNumber.get(raceNumber).findElement(ALL_RP_SORT).click();
        } else {
            if (!sorted.equals("false")) {
                sortsByNumber.get(raceNumber).findElement(ALL_RP_SORT).click();
                sorted = sortsByNumber.get(raceNumber).findElement(ALL_RP_SORT).getAttribute("data-sortasc");
            }
            if (!sorted.equals("false"))
                sortsByNumber.get(raceNumber).findElement(ALL_RP_SORT).click();
        }
    }

    @Override
    public void clickOnSortByPriceByRace(int raceNumber,boolean ascending) {
//        givenListClickOnGivenElement(ALL_PRICE_SORT, raceNumber);
        raceNumber = raceNumber - 1;
        List<WebElement> sortsByNumber = findElements(RACE_CARD_ELEMENT);
        String sorted = sortsByNumber.get(raceNumber).findElement(ALL_PRICE_SORT).getAttribute("data-sortasc");
        if(sorted==null)
            sorted = StringUtils.EMPTY;
        if (ascending) {
            if (!sorted.equals("true")) {
                sortsByNumber.get(raceNumber).findElement(ALL_PRICE_SORT).click();
                sorted = sortsByNumber.get(raceNumber).findElement(ALL_PRICE_SORT).getAttribute("data-sortasc");
            }
            if (!sorted.equals("true"))
                sortsByNumber.get(raceNumber).findElement(ALL_PRICE_SORT).click();
        } else {
            if (!sorted.equals("false")) {
                sortsByNumber.get(raceNumber).findElement(ALL_PRICE_SORT).click();
                sorted = sortsByNumber.get(raceNumber).findElement(ALL_PRICE_SORT).getAttribute("data-sortasc");
            }
            if (!sorted.equals("false"))
                sortsByNumber.get(raceNumber).findElement(ALL_PRICE_SORT).click();
        }
    }



    private List<String> currentOrderOf(By locator, int race) {

        race = race - 1;
        List<WebElement> races = findElements(ALL_RACES_ON_PAGE);

        List<WebElement> elements = races.get(race).findElements(locator);
        List<String> results = new ArrayList<>();
        for (WebElement element : elements) {
            if(element.getText().equalsIgnoreCase(StringUtils.EMPTY))
                results.add(element.getAttribute("data-odds"));
            else
                results.add(element.getText());
        }
        return results;
    }

    private boolean areFractionsSorted(List<String> input, boolean ascending) {
        for (int i=0;i<input.size()-1;i++) {


            if (ascending) {
                if (Fraction.getFraction(input.get(i)).compareTo(Fraction.getFraction(input.get(i+1)))>=0)
                    return false;
            } else {
                if (Fraction.getFraction(input.get(i)).compareTo(Fraction.getFraction(input.get(i+1)))<=0)
                    return false;
            }
        }
        return true;
    }


    public boolean areStringsSorted(List<String> input, boolean ascending) {
        Collections.sort(input);

        for (String anInput : input) {
            if (!anInput.equalsIgnoreCase(anInput))
                return false;
        }

        return true;
    }



    private boolean areStringsInAlphabeticOrder(List<String> input, boolean ascending) {
        for (int i=0;i<input.size()-1;i++) {


            if (ascending) {
                if (Integer.parseInt(input.get(i))>(Integer.parseInt(input.get(i+1))))
                    return false;
            } else {
                if (Integer.parseInt(input.get(i))<Integer.parseInt(input.get(i+1)))
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean isRaceOrderedByHorseNumber(int race, boolean ascending) {
        List<String> horseNumberCurrentOrder = currentOrderOf(ALL_HORSE_NUMBERS_ON_PAGE, race);
        return areStringsInAlphabeticOrder(horseNumberCurrentOrder, ascending);
    }

    @Override
    public boolean isRaceOrderedByDogNumber(int race, boolean ascending) {
        List<String> horseNumberCurrentOrder = currentOrderOf(ALL_DOG_NUMBERS_ON_PAGE, race);
        return areStringsInAlphabeticOrder(horseNumberCurrentOrder, ascending);
    }

    @Override
    public boolean isRaceOrderedByDrawn(int race, boolean ascending) {
        List<String> horseNumberCurrentOrder = currentOrderOf(ALL_DRAWS_ON_PAGE, race);
        List<String> numericList = new ArrayList<>();
        for (String draw:horseNumberCurrentOrder) {
            numericList.add(draw.replaceAll("\\(*\\)*", ""));
        }
        return areStringsInAlphabeticOrder(numericList, ascending);
    }

    @Override
    public boolean isRaceOrderedBySelection(int race, boolean ascending) {
        List<String> horseNumberCurrentOrder = currentOrderOf(ALL_SELECTIONS_ON_PAGE, race);
        return areStringsSorted(horseNumberCurrentOrder, ascending);
    }

    @Override
    public boolean isRaceOrderedByRP(int race, boolean ascending) {
        race = race - 1;
        List<WebElement> races = findElements(ALL_RACES_ON_PAGE);

        List<WebElement> elements = races.get(race).findElements(ALL_RP_ON_PAGE);
        List<String> results = new ArrayList<>();
        for (WebElement element : elements) {
            results.add(element.getText());
        }
        return areStringsInAlphabeticOrder(results, ascending);
    }

    @Override
    public boolean isRaceOrderedByPrice(int race, boolean ascending) {
        List<String> horseNumberCurrentOrder = currentOrderOf(ALL_PRICES_ON_PAGE, race);
        return areFractionsSorted(horseNumberCurrentOrder, ascending);
    }

    @Override
    public String getRaceEventMessage() {

        List<WebElement> messageList = findElements(RACE_EVENT_MESSAGE);
        for (WebElement message:messageList) {
            if(message.isDisplayed()){
                return message.getText();
            }
        }

        return StringUtils.EMPTY;
    }


    @Override
    public boolean isTvIconEnabled() {
        return isElementDisplayed(TV_ICON_ENABLED);
    }


    @Override
    public String getPreviousOddbySelectionId(String selectionId) {

        try {
            WebElement previousOddsSelection = waitElementToBeVisible(
                    find(By.id(selectionId)).
                            findElement(By.xpath(String.format(PREVIOUS_ODDS_BY_SELECITON_ID, selectionId))), 2);
            return previousOddsSelection.getText();
        } catch (StaleElementReferenceException | TimeoutException e) {
            this.executeJavaScript("window.scrollBy(0,30)");
            return find(By.id(selectionId)).
                    findElement(By.xpath(String.format(PREVIOUS_ODDS_BY_SELECITON_ID, selectionId))).getText();
        }
    }

    @Override
    public boolean isPreviousOddsDisplayed() {
        return find(PREVIOUS_ODDS_HEADER).isDisplayed();
    }

    @Override
    public boolean isHeaderDisplayed(final String header) {
        sleep(300);
        waitSportsbook();
        if("previous odds".equals(header)) {
            return isElementDisplayed(PREVIOUS_ODDS_HEADER);
        } else {
            throw new NotImplementedException("Header: " + header + " not implemented yet");
        }
    }

    @Override
    public void clickOnMarketCollectionLinkByText(final String text) {
        List<WebElement> marketCollections = find(MARKET_COLLECTION_MENU).findElements(By.cssSelector(".filter-list__item"));
        WebElement marketElement = null;
        for (WebElement market : marketCollections) {
            if(market.findElement(By.tagName("a")).getAttribute("class").contains("--active")){
                marketElement = market.findElement(By.tagName("a"));
            }
        }
        sleep(300);
        click(By.xpath(String.format(MARKET_COLLECTION_LINK,text)));
        if(marketElement!=null)
            waitElementAttributeNotContain(marketElement,"class","--active",5);

    }

    @Override
    public List<String> getMarketCollectionItems() {
        List<WebElement> marketCollections = find(MARKET_COLLECTION_MENU).findElements(By.cssSelector(".filter-list__item"));
        List<String> resultList = new ArrayList<>();
        for (WebElement market:marketCollections) {
            resultList.add(market.getText());
        }
        return resultList;
    }

    @Override
    public boolean isEventIsDisplayedInRaceCard (String eventId) {
        return isElementDisplayed(By.cssSelector(String.format(EVENT_SECTION_ID, eventId)));
    }

    @Override
    public boolean isRacecardDisplayed () {
        return isElementDisplayed(RACES_CARD_HEADER);
    }

    @Override
    public boolean isMeetingMarketsDisplayed () {
        return isElementDisplayed(MEETING_MARKETS_CONTENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRaceToRaceNavigationDisplayed() {
        return isElementDisplayed(RACE_TO_RACE_NAVIGATION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHighlightedOptionFromRaceToRace(){
        return find(HIGHLIGHTED_RACE_IN_RACE_TO_RACE).getText();
    }

    @Override
    public void clickRaceToRaceLink(String link) {
        sleep(300);
        String lowerCaseLink = link.toLowerCase();
        switch (lowerCaseLink) {
            case "all races":
                click(RACE_TO_RACE_LINK_ALL_RACES);
                sleep(300);
                break;
            case "any race":
                List<WebElement> elementList = find(By.id("marketCollectionItemsList")).findElements(RACE_TO_RACE_LINK_OTHER_RACE_INACTIVE);
                click(elementList.get(elementList.size()-1));
                sleep(300);
                break;
            case "meeting markets":
                click(RACE_TO_RACE_LINK_MEETING_MARKETS);
                sleep(300);
                break;
            default:
                throw new IllegalArgumentException("Invalid link: " + link);
        }
    }

    @Override
    public boolean areRacesAreSortedByTimeInRaceToRace() {
        List<WebElement> elementsList = findElements(RACE_TO_RACE_LINK_OTHER_RACE);
        List<String> stringsUnSorted = new ArrayList<>();
        List<String> stringsSorted = new ArrayList<>();

        for (WebElement anElementsList : elementsList) {
            try {
                if (anElementsList.getAttribute("data-name").matches("[0-9]+[:][0-9]+")) {
                    stringsUnSorted.add(anElementsList.getAttribute("data-name"));
                    stringsSorted.add(anElementsList.getAttribute("data-name"));
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        sortedListByTime(stringsSorted);
        for(int i=0;i<stringsUnSorted.size();i++) {
            if(!stringsUnSorted.get(i).equalsIgnoreCase(stringsSorted.get(i))){
                return false;
            }
        }
        return true;
    }

    private List<String> sortedListByTime(List<String> strings) {
        Collections.sort(strings, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return extractInt(o1) - extractInt(o2);
            }
            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });
        return strings;
    }

    @Override
    public boolean isMeetingMarketDisplayedLast(){
        sleep(2000);
        List<WebElement> elementList = findElements(RACE_TO_RACE_LINK);
        return elementList.get(elementList.size()-1).getText().equals("Meeting Markets");
    }

    @Override
    public boolean isAllRacesDisplayedFirst(){
        List<WebElement> elementList = findElements(RACE_TO_RACE_LINK);
        return elementList.get(0).getText().equals("All Races");
    }

    @Override
    public void clickRaceToRaceIndex(int index) {
        findElements(RACE_TO_RACE_ALL_LINKS).get(index -1).click();
    }

    @Override
    public void clickOnGivenSelection(int collection){
        findElements(ALL_COLLECTIONS_DISPLAYED).get(collection -1).click();
    }

    @Override
    public void clickOnGivenEvent(int event, int collection){
        findElements(ALL_COLLECTIONS_DISPLAYED).get(collection -1).findElements(ALL_EVENTS_DISPLAYED).get(event -1).click();
    }

    @Override
    public String getEventName(int event, int collection){
        return findElements(ALL_COLLECTIONS_DISPLAYED).get(collection -1).findElements(ALL_EVENT_TITLES_DISPPLAYED).get(event -1).getText();
    }

    @Override
    public void clickOnGivenRace(int race, int collection, int event){
        findElements(ALL_COLLECTIONS_DISPLAYED).get(collection -1).findElements(ALL_EVENTS_DISPLAYED).get(event -1).findElements(ALL_RACES_DISPLAYED_ON_MENU).get(event -1).click();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlternateMeetingDropdownDisplayed(){
        return isElementDisplayed(ALTERNATE_MEETING_DROPDOWN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlternateMeetingDropdownExpanded(){
        return findElements(ALL_ALTERNATE_MEETINGS_OPTIONS).get(1).isDisplayed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String currentAlternateMeetingSelected(){
        return find(ALTERNATE_MEETING_SELECTED_OPTION).getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectRaceFromAlternateMeetingDropdown(int indexNumber){
        expandAlternateMeetingDropdown();
        raceToRaceDropdown().get(indexNumber -1).click();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectRaceFromAlternateMeetingDropdown(String raceName){
        expandAlternateMeetingDropdown();
        List<WebElement> listOfRaces = raceToRaceDropdown();
        for (WebElement race:listOfRaces) {
            if(race.getText().equals(raceName)){
                click(race);
                break;
            }
        }
    }

    @Override
    public String getRaceNameFromAlternateMeetingIndex(int index){
        expandAlternateMeetingDropdown();
        return raceToRaceDropdown().get(index -1).getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void expandAlternateMeetingDropdown(){
        if(!isElementDisplayed(findElements(ALTERNATE_MEETING_OPTIONS_UNDER_DROPDOWN).get(0))) {
            find(ALTERNATE_MEETING_DROPDOWN).click();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collapseAlternateMeetingDropdown(){
        if(isElementDisplayed(findElements(ALTERNATE_MEETING_OPTIONS_UNDER_DROPDOWN).get(0))) {
            find(ALTERNATE_MEETING_DROPDOWN).click();
        }
    }

    @Override
    public List<String> getOptionsFromAlternateMeetingDropdown(){
        expandAlternateMeetingDropdown();
        List<String> result = new ArrayList<>();
        List<WebElement> raceToRaceElements = findElements(ALTERNATE_MEETING_OPTIONS_UNDER_DROPDOWN);
        for (WebElement element:raceToRaceElements) {
            result.add(element.getText());
        }
        return result;
    }

    private List<WebElement> raceToRaceDropdown(){
        return findElements(ALL_ALTERNATE_MEETINGS_OPTIONS);
    }

    @Override
    public boolean isAllRacesLinkDisplayed(){
        return isElementDisplayed(ALL_RACES_R2R_NAVIGATION_LINK);
    }

    @Override
    public void clickAllRacesLink(){
        find(ALL_RACES_R2R_NAVIGATION_LINK).click();
    }

    @Override
    public String getCurrentRaceEventTitle(){
       return find(RACE_TITLE).getText();
    }

    @Override
    public void clickAllRacesFromGivenEventMenu(int collection, int event){
        findElements(ALL_COLLECTIONS_DISPLAYED).get(collection -1).findElements(ALL_EVENTS_DISPLAYED).get(event -1).findElement(ALL_ALL_RACES_BUTTONS_UNDER_EVENTS_MENU).click();
    }

    @Override
    public void clickOnResultedRaceFromGivenEventMenu(int collection, int event){
        findElements(ALL_COLLECTIONS_DISPLAYED).get(collection -1).findElements(ALL_EVENTS_DISPLAYED).get(event -1).findElements(ALL_RESULTED_RACES_BUTTONS_UNDER_EVENTS_MENU).get(0).click();
    }

    @Override
    public void clickOnRaceFromGivenEventMenu(int collection, int event){
        findElements(ALL_COLLECTIONS_DISPLAYED).get(collection -1).findElements(ALL_EVENTS_DISPLAYED).get(event -1).findElements(ALL_RACES_BUTTONS_UNDER_EVENTS_MENU).get(0).click();
    }

    @Override
    public List <String> getAllRacesMenuTitlesFromGivenCollectionEvent(int collection, int event){
        List<String> result = new ArrayList<>();
        List<WebElement> titlesDisplayed = findElements(ALL_COLLECTIONS_DISPLAYED).get(collection -1).findElements(ALL_EVENTS_DISPLAYED).get(event -1).findElements(ALL_RACES_DISPLAYED_ON_MENU);
        for (WebElement element:titlesDisplayed) {
            result.add(element.getAttribute("data-racename"));
        }
        return result;
    }

    @Override
    public List <String> getAllRacesTitlesDisplayed(){
        List<String> result = new ArrayList<>();
        List<WebElement> titlesDisplayed = findElements(ALL_RACES_TITLES_DISPLAYED_ON_RACECARD);

        for (WebElement element:titlesDisplayed) {
            result.add(element.getText());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGivenRacecardDisplayed(String eventId){
        return isElementDisplayed(By.cssSelector(String.format("section[id='%s']" , eventId)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickMeetingMarketFromGivenCompetition(String competitionId){
        click(find(By.id(competitionId)).findElement(MEETING_MARKET_BUTTON));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnSelectionInRaceCard(int number) {
        List<WebElement> selectionList = find(By.cssSelector(".race-event-wrapper")).findElements(GENERIC_SELECTION);
        selectionList.get(number - 1).click();
    }

}
