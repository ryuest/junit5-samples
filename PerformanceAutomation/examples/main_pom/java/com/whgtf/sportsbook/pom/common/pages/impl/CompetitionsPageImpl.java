package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.CompetitionsPage;
import com.whgtf.sportsbook.pom.mobile.components.impl.NativeElementsComponentImpl;
import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;


@Component
@Lazy
public class CompetitionsPageImpl extends AbstractSportsPageObject implements CompetitionsPage {

    private static final By COMPETITIONS_TITLE = By.cssSelector(".header-panel__title.cap");

    private static final By NAVIGATION_TAB = By.id("comp-navigation-tabs");

    private static final By COMPETITIONS_TAB_NAVIGATION = By.id("competitions-tab-content");

    private static final By MATCHES_TAB = By.xpath("//a[contains(@data-link-panel,'matches')]");

    private static final By OUTRIGHTS_TAB = By.xpath("//a[contains(@data-link-panel,'outrights')]");

    private static final By MATCHES_CONTENT = By.cssSelector(".matches");

    private static final By OUTRIGHTS_CONTENT = By.cssSelector(".outrights");

    private static final By MATCHES_TAB_CONTENT = By.xpath("//div[@class='matches']");

    private static final By OUTRIGHTS_TAB_CONTENT = By.xpath("//div[@class='outrights']");

    private static final By LEVEL_1 = By.cssSelector("li[class*='level1']");
    private static final By LEVEL_2 = By.cssSelector("li[class*='level2']");
    private static final By LEVEL_3 = By.cssSelector("li[class*='level3']");

    private static final By ALL_REGIONS = By.xpath("//li[contains(@class,'level1')]");

    private static final By ALL_EVENT_TITLES = By.cssSelector("div[id*='OB_EV'] a");

    private static final By ALL_COMPETITIONS = By.xpath("//li[contains(@class,'level2')]");

    private static final By ALL_EVENTS = By.cssSelector("div[id*='OB_EV']");

    private static final By ALL_MARKETS = By.xpath("//section[contains(@class,'event-container scrollable clickable-selections')]/div[contains(@class,'markets-group-container')]/div/..");

    private static final By REGIONS_DISPLAYED = By.cssSelector("li[class*='level1 header-dropdown--large']");

    private static final By MARKETS_SHOW_MORE = By.cssSelector("a[data-show-more-text='Show more']");

    private static final By SUBMARKETS_DISPLAYED = By.xpath("//li[contains(@class,'header-dropdown')]");

    private static final By REGION_EXPANDED = By.xpath("//li[contains(@class, 'level1 header-dropdown--large -expanded')]");

    private static final By ALL_COLLAPSED_REGIONS = By.xpath("//li[contains(@class, 'level1') and not(contains(@class, 'expanded'))]");

    private static final By ALL_SHOW_MORE = By.xpath("//a[contains(@data-toggle-send,'showMore')]");

    private static final By COMPETITION_EXPANDED = By.xpath("//li[contains(@class, 'level2 header-dropdown--large -expanded')]");

    private static final By FIRST_REGION = By.xpath("(//li[contains(@class,'level1')])[1]");

    private static final By FIRST_COMPETITION = By.xpath("(//li[contains(@class,'level2')])[1]");

    private static final By FIRST_SELECTION = By.xpath("(//button[contains(@id, 'OB_OU') and not(contains(@class, 'disabled'))])[1]");

    private static final By ALL_ARROWS = By.cssSelector("i[class*='icon-arrow']");

    private static final By ALL_EXPANDED_ARROWS_FROM_REGIONS = By.xpath("//li[contains(@class,'level1')]//i[contains(@class, 'arrow-up')]");

    private static final By FIRST_ARROW_EXPAND_REGION = By.xpath("(//li[contains(@class,'level1')]//i[@class='icon-arrow-down-slim if-collapsed'])[1]");

    private static final By FIRST_ARROW_COLLAPSE_REGION = By.xpath("(//li[contains(@class,'level1')]//i[@class='icon-arrow-up-slim if-expanded'])[1]");

    private static final By FIRST_ARROW_EXPAND_COMPETTION = By.xpath("(//li[contains(@class,'level2')]//i[@class='icon-arrow-down-slim if-collapsed'])[1]");

    private static final By FIRST_ARROW_COLLAPSE_COMPETTION = By.xpath("(//li[contains(@class,'level2')]//i[@class='icon-arrow-up-slim if-expanded'])[1]");

    // Event section
    private static final By GROUP_TITLE = By.cssSelector(".group-title");

    private static final By EVENT_TIME = By.cssSelector("time[data-type='event-starttime']");

    private static final By EVENT_TITLE = By.cssSelector(".btmarket__name.btmarket__name--featured");

    private static final By EVENT_SELECTIONS = By.cssSelector("div[id*='OB_MA']");

    private static final String DYNAMIC_ALL_REGIONS = "//div[@class='%s']//li[contains(@class,'level1')]";

    private static final String DYNAMIC_ALL_COMPETITIONS = "//div[@class='%s']//li[contains(@class,'level2')]";

    private static final String COMPETITIONS_FROM_GIVEN_REGION = "//li[@data-toggle-receive='%s']";

    private static final String COMPETITION_ARROWS_FROM_GIVEN_REGION = "//li[@data-toggle-receive='%s']//i[contains(@class, 'arrow')]";

    private static final String EVENTS_FROM_GIVEN_COMPETITION = "//li[@data-toggle-receive='%s']//div[@class='event']";

    private static final String MARKET_GROUPS_FROM_GIVEN_EVENT = "//li[@data-toggle-receive='%s']//div[@class='market-group-wrapper markets-group-container']";

    private static final String MARKET_GROUPS_ARROWS_FROM_GIVEN_EVENT = "//li[@data-toggle-receive='%s']//i[contains(@class, 'arrow')]";

    private static final String COMPETITION_HEADER_FROM_GIVEN_REGION = "(//div[@id='competitions-tab-content']/ul/li[%s]//aside[@class='header__toolbar'])[%s]";

    private static final String ARROW_FROM_GIVEN_REGION_COMPETITION = "(//div[@id='competitions-tab-content']/ul/li[%s]//aside[@class='header__toolbar'])[%s]//i[1]";

    private static final String EVENT_ELEMENTS_FROM_GIVEN_REGION_COMPETITION = "//div[@id='competitions-tab-content']/ul/li[%s]/ul/li[%s]//div[@class='event']";

    private static final String FIRST_SELECTION_FROM_GIVEN_REGION_COMPETITION = "(//div[@id='competitions-tab-content']/ul/li[%s]/ul/li[%s]//button)[1]";

    private static final String FIRST_EVENT_LINK_FROM_GIVEN_REGION_COMPETITION = "(//div[@id='competitions-tab-content']/ul/li[%s]/ul/li[%s]//div[@class='btmarket__link-name'])[1]";

    private static final By COMPETITION_LIST = By.id("competitions-list");



    private List<WebElement> matchesRegions(){
        return find(MATCHES_CONTENT).findElements(ALL_REGIONS);
    }

    private List<WebElement> matchesCompetitions(){
        return find(MATCHES_CONTENT).findElements(ALL_COMPETITIONS);
    }

    private List<WebElement> outrightsRegions(){
        return findElements(ALL_REGIONS);
    }

    private List<WebElement> outrightsCompetitions(){
        return findElements(ALL_COMPETITIONS);
    }

    @Override
    public String getCompetitionsTitle() {
        return find(COMPETITIONS_TITLE).getText();
    }

    @Override
    public void clickOnMatches() {
        find(NAVIGATION_TAB).findElement(MATCHES_TAB).click();
        sleep(3000);
    }

    @Override
    public boolean isMatchesContentDisplayed() {
        return find(MATCHES_CONTENT).isDisplayed();
    }

    @Override
    public void clickOnOutrights() {
        find(NAVIGATION_TAB).findElement(OUTRIGHTS_TAB).click();
        sleep(3000);
    }

    @Override
    public boolean isOutrightsContentDisplayed() {
        return find(OUTRIGHTS_CONTENT).isDisplayed();
    }

    @Override
    public void expandTheFirstRegion() {
        find(FIRST_REGION).click();
    }

    @Override
    public void expandTheFirstCompetition() {
        find(FIRST_COMPETITION).click();
    }

    @Override
    public void clickGivenRegion(int regionNumber) {
        List <WebElement> elements = findElements(ALL_REGIONS);
        elements.get(regionNumber -1).click();
    }

//    @Override
//    public void clickGivenCompetition(int regionNumber, int competitionNumber) {
//        regionNumber = regionNumber *2;
//        competitionNumber = competitionNumber *2;
//        find(By.xpath(String.format(COMPETITION_HEADER_FROM_GIVEN_REGION, regionNumber, competitionNumber))).click();
//    }

    @Override
    public void clickGivenCompetition(int regionNumber, int competitionNumber) {
        List <WebElement> regions = findElements(LEVEL_1);
        List <WebElement> competitions = regions.get(regionNumber - 1).findElement(By.xpath("./../li[" + (regionNumber + 1) + "]")).findElements(LEVEL_2);

        competitions.get(competitionNumber -1).click();
    }


    @Override
    public void clickGivenMarketGroup(int regionNumber, int competitionNumber, int eventNumber){

        List <WebElement> regions = findElements(LEVEL_1);
        List <WebElement> competitions = regions.get(regionNumber - 1).findElement(By.xpath("./../li[" + (regionNumber + 1) + "]")).findElements(LEVEL_2);
        List <WebElement> events = competitions.get(competitionNumber -1).findElement(By.xpath("./../li[" + (competitionNumber + 1) + "]")).findElements(LEVEL_3);

        events.get(eventNumber -1).click();
    }

    @Override
    public void expandAllRegions() {
        List <WebElement> elements = findElements(ALL_COLLAPSED_REGIONS);
        for (WebElement element:elements) {
            if(!element.getAttribute("class").contains("-expanded")) {
                clickByJavascript(element);
            }
        }
    }

    private void expandAllCompetitions() {

        List <WebElement> showMores = find(COMPETITIONS_TAB_NAVIGATION).findElements(ALL_SHOW_MORE);
        for (WebElement element:showMores) {
            element.click();
            sleep(1000);
        }

        List <WebElement> competitions = find(COMPETITIONS_TAB_NAVIGATION).findElements(ALL_COMPETITIONS);
        for (WebElement element:competitions) {
            if(!element.getAttribute("class").contains("-expanded")) {
                element.click();
                sleep(1000);
            }
        }
    }

    private void expandAllRegionsAndCompetitions() {
        expandAllRegions();
        expandAllCompetitions();
        clickOnAllTheShowMoreFromMarket();
    }

    private void clickOnAllTheShowMoreFromMarket(){
        List<WebElement> showMoreElements = findElements(MARKETS_SHOW_MORE);

        for (WebElement currentShowMore: showMoreElements) {
            clickByJavascript(currentShowMore);
        }
    }

    @Override
    public boolean doesGivenCompetitionHaveEvents(int region, int competition){

        // This is needed since the first found element is the "Title" of the Region or competition.
        region = region + 1;
        competition = competition * 2;

        sleep(1000);

        return findElements(By.xpath(String.format(EVENT_ELEMENTS_FROM_GIVEN_REGION_COMPETITION, region, competition))).size() > 0;
    }

    @Override
    public Selection clickOnTheFirstAvailableSelection() {

        // The selection to return when clicked.
        Selection selection = new Selection();

        // This variable will turn True when we successfully click on a Selection
        boolean selectionClicked = false;

        //Here we find the list of competitions
        List<WebElement> competitions = find(COMPETITIONS_TAB_NAVIGATION).findElements(REGIONS_DISPLAYED);

        for (WebElement competition: competitions) {

            // Here we expand the competition if its not already expanded
            // Note: I was getting "stale element reference: element is not attached to the page document" here - Intermittent issue.
            if(!competition.getAttribute("class").contains("-expanded")) {
                competition.click();
            }

            // Here we click on the Show more sections...
            clickOnAllTheShowMoreFromMarket();

            //Here we get the list of available submarkets FROM THE FIRST COMPETITION
            List<WebElement> markets = competitions.get(0).findElements(ALL_COMPETITIONS);

            for (WebElement market: markets) {
                if (!market.getAttribute("class").contains("-expanded")) {
                    market.click();
                }

                try {
                    // save and return the clicked selection
                    WebElement selectionElement = find(FIRST_SELECTION);
                    selection.setPdsId(selectionElement.getAttribute("id"));
                    selection.setPrice(selectionElement.getAttribute("data-odds"));
                    selection.setName(selectionElement.getAttribute("data-name"));

                    if(!isNativeMobileApp()) {
                        selectionElement.click();
                    }else{
                        new NativeElementsComponentImpl().clickOnFirstCompetitionSelection();
                    }
                    selectionClicked = true;
                    break;
                }catch (NoSuchElementException e){
                    e.printStackTrace();
                }
            }

            if(selectionClicked){
                break;
            }

            // This is to close the competition
            competition.click();
            sleep(1000);
        }
        return selection;
    }

    @Override
    public String matchesTitle(){
        return find(MATCHES_TAB).getText();
    }

    @Override
    public String outrightsTitle(){
        return find(OUTRIGHTS_TAB).getText();
    }

    @Override
    public boolean areRegionsDisplayed(String matchesOrOutrights){

        boolean allRegionsDisplayed = true;
        navigateToUrl(getUrl());
        List<WebElement> regions;

        if(matchesOrOutrights.equals("matches")){
            regions = find(MATCHES_CONTENT).findElements(REGIONS_DISPLAYED);
        }else{
            regions = find(OUTRIGHTS_CONTENT).findElements(REGIONS_DISPLAYED);
        }

        for (WebElement region: regions) {
            if(!region.isDisplayed()){
                allRegionsDisplayed = false;
                break;
            }
        }
        return allRegionsDisplayed;
    }

    @Override
    public boolean isGivenRegionExpanded(int regionListNumber) {

        regionListNumber = regionListNumber -1;

        waitElementToBePresent(ALL_REGIONS, 10);

        List<WebElement> regions = findElements(ALL_REGIONS);
        return regions.get(regionListNumber).getAttribute("class").contains("expanded");

    }

    @Override
    public boolean isGivenCompetitionExpanded(int regionListNumber, int competitionListNumber){

        regionListNumber = regionListNumber -1;
        competitionListNumber = competitionListNumber -1;

        List<WebElement> regions = findElements(ALL_REGIONS);
        List<WebElement> competitions = findElements(ALL_COMPETITIONS);

        return regions.get(regionListNumber).getAttribute("class").contains("expanded") && competitions.get(competitionListNumber).getAttribute("class").contains("expanded");
    }

    @Override
    public boolean isGivenMarketGroupExpanded(int region, int competition, int event){

        region = region -1;
        competition = competition -1;
        event = event - 1;

        List<WebElement> regions = findElements(LEVEL_1);
        List<WebElement> competitions = findElements(LEVEL_2);
        List<WebElement> events = findElements(LEVEL_3);


        return regions.get(region).getAttribute("class").contains("expanded") && competitions.get(competition).getAttribute("class").contains("expanded") && events.get(event).getAttribute("class").contains("expanded");
    }

    @Override
    public boolean onlyFirstRegionExpanded(String matchesOrOutrights){

        List<WebElement> competitions = findElements(By.xpath(String.format(DYNAMIC_ALL_REGIONS, matchesOrOutrights)));

        boolean onlyFirstRegionExpanded = true;

        for (int i = 1; i<competitions.size(); i++) {
            if(competitions.get(i).getAttribute("class").contains("expanded")){
                onlyFirstRegionExpanded = false;
                break;
            }
        }

        return onlyFirstRegionExpanded;
    }

    @Override
    public boolean onlyFirstCompetitionExpanded(String matchesOrOutrights){

        List<WebElement> competitions = findElements(By.xpath(String.format(DYNAMIC_ALL_COMPETITIONS, matchesOrOutrights)));

        for (int i = 1; i<competitions.size(); i++) {
            if(competitions.get(i).getAttribute("class").contains("expanded")){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean regionArrowExpandDisplay(){
        return find(FIRST_ARROW_EXPAND_REGION).isDisplayed();
    }

    @Override
    public boolean regionArrowCollapseDisplay(){
        return find(FIRST_ARROW_COLLAPSE_REGION).isDisplayed();
    }

    @Override
    public boolean isGivenArrowExpandedFromRegionDisplayed(int region){
        List <WebElement> regionsArrows = findElements(ALL_EXPANDED_ARROWS_FROM_REGIONS);
        return regionsArrows.get(region -1).isDisplayed();
    }

    @Override
    public boolean isGivenArrowExpandedFromCompetitionDisplayed(int region, int competition){
        return find(By.xpath(String.format(ARROW_FROM_GIVEN_REGION_COMPETITION, region +1, competition))).isDisplayed();
    }

    @Override
    public boolean isGivenArrowExpandedFromMarketGroupDisplayed(int region, int competition, int marketGroup){
        List <WebElement> regionsArrows = findElements(ALL_EXPANDED_ARROWS_FROM_REGIONS);
        String dataToggleSendRegion = regionsArrows.get(region -1).getAttribute("data-toggle-send");

        List <WebElement> competitionsArrows = regionsArrows.get(region -1).findElements(By.xpath(String.format(COMPETITION_ARROWS_FROM_GIVEN_REGION, dataToggleSendRegion)));
        String dataToggleSendCompetition = competitionsArrows.get(competition -1).getAttribute("data-toggle-send");

        List <WebElement> marketGroupArrows = competitionsArrows.get(region -1).findElements(By.xpath(String.format(MARKET_GROUPS_ARROWS_FROM_GIVEN_EVENT, dataToggleSendCompetition)));

        return marketGroupArrows.get(marketGroup -1).isDisplayed();
    }


    @Override
    public boolean competitionArrowExpandDisplay(){
        return find(FIRST_ARROW_EXPAND_COMPETTION).isDisplayed();
    }

    @Override
    public boolean competitionArrowCollapseDisplay(){
        return find(FIRST_ARROW_COLLAPSE_COMPETTION).isDisplayed();
    }

    @Override
    public boolean areMultipleRegionsOpen(){
        List<WebElement> regionsOpen = findElements(REGION_EXPANDED);
        return regionsOpen.size() > 1;
    }

    @Override
    public boolean verifyAllMarketsTitle() {
        expandAllRegionsAndCompetitions();
        List <WebElement> allMarkets = findElements(ALL_MARKETS);
        for (WebElement competition: allMarkets) {
            if(competition.findElement(GROUP_TITLE).getText().equals("")){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean verifyAllEventsTime() {
        expandAllRegionsAndCompetitions();
        List <WebElement> allMarkets = findElements(ALL_EVENTS);
        for (WebElement competition: allMarkets) {
            if(competition.findElement(EVENT_TIME).getText().equals("")){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean verifyEventsTitleOnMatches(int region, int competition) {

        List <WebElement> regions = findElements(ALL_COLLAPSED_REGIONS);
        String dataToggleSendRegion = regions.get(region -1).getAttribute("data-toggle-send");

        // Here we get the list of Competitions from the given Region
        List <WebElement> competitionsFromGivenRegion = regions.get(region -1).findElements(By.xpath(String.format(COMPETITIONS_FROM_GIVEN_REGION, dataToggleSendRegion)));
        String dataToggleSendCompetition = competitionsFromGivenRegion.get(competition -1).getAttribute("data-toggle-send");

        // Here we get the list of Events form the given Competition
        List <WebElement> eventsFromGivenCompetition = competitionsFromGivenRegion.get(competition -1).findElements(By.xpath(String.format(EVENTS_FROM_GIVEN_COMPETITION, dataToggleSendCompetition)));

        for (WebElement event: eventsFromGivenCompetition) {
            if(event.findElement(By.xpath("//a[contains(@class, 'btmarket__name')]")).getText() == null){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean verifyEventsTitleOnOutrights(int region, int competition, int marketGroup) {

        List <WebElement> regions = findElements(ALL_COLLAPSED_REGIONS);
        String dataToggleSendRegion = regions.get(region -1).getAttribute("data-toggle-send");

        // Here we get the list of Competitions from the given Region
        List <WebElement> competitionsFromGivenRegion = regions.get(region -1).findElements(By.xpath(String.format(COMPETITIONS_FROM_GIVEN_REGION, dataToggleSendRegion)));
        String dataToggleSendCompetition = competitionsFromGivenRegion.get(competition -1).getAttribute("data-toggle-send");

        // Here we get the list of Events form the given Competition
        List <WebElement> eventsFromGivenCompetition = competitionsFromGivenRegion.get(competition -1).findElements(By.xpath(String.format(EVENTS_FROM_GIVEN_COMPETITION, dataToggleSendCompetition)));
        String dataToggleSendMarketGroup = competitionsFromGivenRegion.get(competition -1).getAttribute("data-toggle-send");

        // Here we get the list of Market Groups
        List <WebElement> marketGroupsFromGivenCompetition = eventsFromGivenCompetition.get(marketGroup -1).findElements(By.xpath(String.format(MARKET_GROUPS_FROM_GIVEN_EVENT, dataToggleSendMarketGroup)));

        for (WebElement event: marketGroupsFromGivenCompetition) {
            if(event.findElement(By.xpath("//a[contains(@class, 'btmarket__name')]")).getText() == null){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean verifyAllEventsTitle() {
        expandAllRegionsAndCompetitions();
        List <WebElement> allMarkets = findElements(ALL_EVENTS);
        for (WebElement competition: allMarkets) {
            if(competition.findElement(EVENT_TITLE).getText().equals("")){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean verifyAllEventsSelections() {
        expandAllRegionsAndCompetitions();
        List <WebElement> allMarkets = findElements(ALL_EVENTS);
        for (WebElement competition: allMarkets) {
            if(competition.findElement(EVENT_SELECTIONS).getText().equals("")){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean verifySelectionComponentsFromGivenCompetition(int region, int competition){

        region = region + 1;
        competition = competition + 1;

        clickOnAllTheShowMoreFromMarket();

        List <WebElement> events = findElements(By.xpath(String.format(EVENT_ELEMENTS_FROM_GIVEN_REGION_COMPETITION, region, competition)));

        for (WebElement event:events) {

            // Here we check the Time of the Market
            WebElement marketTime = event.findElement(By.xpath(".//span[@class='btmarket__name btmarket__name--disabled']"));
            if(!marketTime.isDisplayed()){
                return false;
            }

            // Here we check the title of the Market
            WebElement marketTitle = event.findElement(By.xpath(".//a[@class = 'btmarket__name btmarket__name--featured']"));
            if(!marketTitle.isDisplayed()){
                return false;
            }

            // Here we verify if it has selections
            WebElement marketSelections = event.findElement(By.xpath(".//span[@class='betbutton__odds']"));
            if(!marketSelections.isDisplayed()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void clickOnFirstEventLink() {
        WebElement firstEvent = findElements(ALL_EVENTS).get(0);
        WebElement firstEventTitle = findElements(ALL_EVENT_TITLES).get(0);

        String marketId = firstEvent.getAttribute("id");
        ScenarioContext.saveData("marketId", marketId);

        firstEventTitle.click();
    }

    @Override
    public boolean isCompetitionListDisplayed() {
        return isElementDisplayed(COMPETITION_LIST);
    }

    @Override
    public boolean verifyEventPageRedirection(){
        return getCurrentUrl().contains((String) ScenarioContext.getSavedData("marketId"));
    }

    @Override
    public boolean isMatchesTabHighlightes(){
        return find(MATCHES_TAB).getAttribute("class").contains("active");
    }

    @Override
    public boolean isOutrightsTabHighlightes(){
        return find(OUTRIGHTS_TAB).getAttribute("class").contains("active");
    }


    @Override
    public boolean isDisplayed() {
        return isCompetitionListDisplayed() && isElementDisplayed(NAVIGATION_TAB) && isElementDisplayed(COMPETITIONS_TAB_NAVIGATION) && getCurrentPageName().equals("competitions");
    }
}
