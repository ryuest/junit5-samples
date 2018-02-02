package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.*;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class BottomBarComponentImpl extends AbstractCommonObject implements BottomBarComponent {

    @Autowired
    @Lazy
    public QuickLinksComponent quickLinksComponentImpl;

    @Autowired
    @Lazy
    public BetSlipComponent betSlipComponentImpl;

    @Autowired
    @Lazy
    public SearchComponent searchComponent;

    @Autowired
    @Lazy
    public TopGamesComponent topGamesComponent;


    private static final By BAR_DIV = By.cssSelector("#toolbar .toolbar");

    private static final By BAR_DURING_SCROLL = By.cssSelector(".toolbar--hide");

    private static final By AZ_SPORTS = By.id("sports-menu");

    // The first 2 elements are "Home" and "In-Play".
    private static final By ALL_SPORTS_NAMES = By.cssSelector("a[class='localnavigation__button-dropdown button'] .capitalize.ng-binding");

    private static final By BETSLIP = By.id("betslip-btn-toolbar");

    private static final By OPEN_BETS = By.className("toggle-openbet");

    private static final By SEARCH = By.cssSelector(".toolbar__item .navSearch");

    private static final By ROULETTE = By.className("roulette");

    private static final By TOP_GAMES = By.cssSelector(".toolbar__item .topGamesButton");

    private static final By ACCUMULATOR_BOX = By.cssSelector("#acca-price");

    private static final By ACCUMULATOR_TEXT = By.cssSelector(".acca-price__price");

    private static final By ALL_SELECTIONS_DISPLAYED = By.cssSelector("button[id*='OB_OU']");


    @Override
    public boolean isAccumulatorDisplayed() {
        sleep(2000);
        return isElementDisplayed(ACCUMULATOR_BOX);
    }

    @Override
    public boolean isAcumulatorTextThis(String input){
        return isElementPresent(By.xpath("//p[@class='' and contains(.,'" + input + "')]"));
    }

    public boolean isDisplayed() {
        return isElementDisplayed(BAR_DIV);
    }

    public boolean isSportsMenuDisplayed() {
        return isElementDisplayed(AZ_SPORTS);
    }

    public boolean isBetSlipMenuDisplayed() {
        return isElementDisplayed(BETSLIP);
    }

    public boolean isOpenBetsMenuDisplayed() {
        return isElementDisplayed(OPEN_BETS);
    }

    public boolean isSearchMenuDisplayed() {
        return isElementDisplayed(SEARCH);
    }

    public boolean isRouletteMenuDisplayed() {
        return isElementDisplayed(ROULETTE);
    }

    @Override
    public boolean isTopGamesMenuDisplayed() {
        return isElementDisplayed(TOP_GAMES);
    }

    public QuickLinksComponent clickOnSportsMenu(){
        waitElementNotToBeVisible(BAR_DURING_SCROLL, 2);
        click(AZ_SPORTS);
        return quickLinksComponentImpl;
    }

    public BetSlipComponent clickOnBetSlipMenu() {
        if(!betSlipComponentImpl.isOpen()) {
            waitElementNotToBeVisible(BAR_DURING_SCROLL, 2);
            click(BETSLIP);
        }
        return betSlipComponentImpl;
    }

    @Override
    public SearchComponent clickOnSearchMenu() {
        waitElementNotToBeVisible(BAR_DURING_SCROLL, 2);
        click(SEARCH);
        return searchComponent;
    }

    @Override
    public TopGamesComponent clickOnTopGamesMenu() {
        waitElementNotToBeVisible(BAR_DURING_SCROLL, 2);
        click(TOP_GAMES);
        return topGamesComponent;
    }

}
