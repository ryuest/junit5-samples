package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.exceptions.NoEventAvailable;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.EventPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class EventPageImpl extends AbstractSportsPageObject implements EventPage {

    private static final By EVENT_SECTION = By.cssSelector("#contentarea.-event");

    private static final By MARKET_BLURB = By.className("btmarket__blurb");

    private static final By GOAL_SCORER = By.className("cpt-goalscorer");

    private static final By GOAL_SCORER_HOME = By.xpath("//section[contains(@class,'cpt-goalscorer')]//section[1]");

    private static final By GOAL_SCORER_AWAY = By.xpath("//section[contains(@class,'cpt-goalscorer')]//section[2]");

    private static final By HEADER_PANEL_TITLE = By.className("header-panel__title");

    private static final By IFRAME = By.cssSelector("iframe");

    private static final By SCOREBOARD_WRAPPER = By.cssSelector("#scoreboard .tableRowWrapper");

    private static final By LOAD_MORE_BUTTON = By.cssSelector(".show-more-button");

    private static final By NO_GOALSCORER_BUTTON = By.xpath("//button[@data-name='No Goalscorer']");

    private static final By TABS = By.cssSelector(".tabs--enabled a");

    private static final By MARKET_TABS_LIST = By.cssSelector(".carousel .filter-list__item a");

    private static final By MARKET_CONTAINER = By.cssSelector("#markets-container");

    private static final By MARKET_IN_CONTAINER = By.cssSelector(".events__market-container");

    private static final By MARKET_COLLECTION_MENU = By.xpath("//ul[contains(@id,'marketCollectionItemsList')]");

    private static final String MARKET_COLLECTION_LINK = "//div[contains(@id,'marketCollectionsMenu_OB_EV')]//li[contains(@class,'filter-list__item')]//span[contains(text(),'%s')]";


    @Override
    public String getUrl() {
        return getCurrentUrl();
    }

    @Override
    public boolean isDisplayed(String event) {
        return getUrl().contains(event);
    }

    @Override
    public Selection clickOnSelection(String selectionId) {
        Selection clickedSelection = null;
        waitForScoreboard();
        try {
            clickedSelection = super.clickOnSelection(selectionId);
        } catch (NoEventAvailable noEventAvailable) {
            noEventAvailable.printStackTrace();
        }
        return clickedSelection;
    }

    private void waitForScoreboard() {
        if(isNativeMobileApp() && isElementDisplayed(By.id("scoreboard_frame"))){
            try{
                getWebDriver().switchTo().frame(find(IFRAME));
                waitElementToBeVisible(SCOREBOARD_WRAPPER, 40);
            }catch(NoSuchElementException|TimeoutException ex){
                ex.printStackTrace();
            }

        }
        getWebDriver().switchTo().parentFrame();
    }

    @Override
    public String getMarketBlurb() {
        List<WebElement> listMarketBlurb;
        List<String> finalList = new ArrayList<>();
        try {
            listMarketBlurb = findElements(MARKET_BLURB);
        } catch (TimeoutException ex) {
            return "";
        }
        for (WebElement marketBlurb:listMarketBlurb) {
            finalList.add(marketBlurb.getText());
        }
        return finalList.toString();
    }

    @Override
    public void addSelectionForEachColumnGoalscorerHomeAway(String option) {
        List<WebElement> elementList;
        if("home".equals(option)) {
            elementList = find(GOAL_SCORER_HOME).findElements(By.cssSelector(".cpt-goalscorer__player"));
        } else {
            elementList = find(GOAL_SCORER_AWAY).findElements(By.cssSelector(".cpt-goalscorer__player"));
        }
        int odds = 0;
        List<WebElement> mobileTabs = findElements(MARKET_TABS_LIST);
        for (WebElement player:elementList) {
            if(this.isMobile()) {
                mobileTabs.get(odds).click();
                clickOnSelection(player.findElements(By.tagName("button")).get(odds).getAttribute("id"));
            }
            else {
                clickOnSelection(player.findElements(By.tagName("button")).get(odds).getAttribute("id"));
            }

            if(odds==player.findElements(By.tagName("button")).size()-1)
                break;
            odds++;
        }
    }

    @Override
    public boolean isDisplayed() {
        return find(EVENT_SECTION).isDisplayed() && getCurrentPageName().equals("events");
    }

    @Override
    public boolean isEventHeaderDisplayed(String eventName) {
        return eventName.contains(find(HEADER_PANEL_TITLE).getText());
    }

    @Override
    public void clickOnHomeAwayLoadMoreButton(String option) {
        if("home".equals(option)) {
            click(find(GOAL_SCORER_HOME).findElement(LOAD_MORE_BUTTON));
        } else {
            click(find(GOAL_SCORER_AWAY).findElement(LOAD_MORE_BUTTON));
        }
    }

    @Override
    public void clickSelectionInTheLastVisibleRow(String option) {
        List<WebElement> elementList;
        if("home".equals(option)) {
            elementList = find(GOAL_SCORER_HOME).findElements(By.cssSelector(".cpt-goalscorer__player"));
        } else {
            elementList = find(GOAL_SCORER_AWAY).findElements(By.cssSelector(".cpt-goalscorer__player"));
        }
        clickOnSelection(elementList.get(elementList.size()-1).findElements(By.tagName("button")).get(0).getAttribute("id"));
    }

    @Override
    public void clickOnNoGoalscorer() {
        click(NO_GOALSCORER_BUTTON);
    }

    @Override
    public void clickOnTab(String tab) {
        if("home".equalsIgnoreCase(tab))
            findElements(TABS).get(0).click();
        else
            findElements(TABS).get(1).click();
    }

    @Override
    public Market getMarketDisplayedByName(final String marketName) {
        List<WebElement> marketList = find(MARKET_CONTAINER).findElements(MARKET_IN_CONTAINER);
        Market mar = new Market();
        for (WebElement market: marketList) {
            if(market.findElement(By.tagName("h2")).getText().equalsIgnoreCase(marketName)){
                String marketId = market.getAttribute("id");
                mar.setPdsId(marketId);
                mar.setName(market.findElement(By.tagName("h2")).getText());
            }
        }
        return mar;
    }

    @Override
    public void clickOnMarketCollectionLinkByText(final String text) {
        find(By.xpath(String.format(MARKET_COLLECTION_LINK,text))).click();
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
    public boolean isMarketCollectionListDisplayed() {
        return isElementDisplayed(MARKET_COLLECTION_MENU);
    }
}
