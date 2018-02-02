package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.pom.common.exceptions.NoSelectionAvailable;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.VirtualsPage;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class VirtualPageImpl extends AbstractSportsPageObject implements VirtualsPage {


    private static final By PRELOADER = By.cssSelector(".wh-preloader-container__btn.btn.btn--success.-margin-top");

    private static final By VIRTUAL_WORLD_TITLE = By.cssSelector(".header-panel__title");

    private static final By VIRTUAL_WORLD_HEADER = By.cssSelector(".header-dropdown");



    private static final By MENU_SECTION_ACTIVE = By.cssSelector("a[class*='-filter -active']");

    private static final By HORSE_RACING_FLAT = By.cssSelector("#nav-virtual-world-virtualhorsesflat a");

    private static final By HORSE_RACING_NATIONAL_HUNT = By.cssSelector("#nav-virtual-world-virtualhorsesnationalhunt a");

    private static final By FOOTBALL = By.cssSelector("#nav-virtual-world-virtualfootball a");

    private static final By GREYHOUNDS_FLAT = By.cssSelector("#nav-virtual-world-virtualgreyhoundsflat a");

    private static final By GREYHOUNDS_HURDLES = By.cssSelector("#nav-virtual-world-virtualgreyhoundsjumps a");

    private static final By MOTOR_RACING = By.cssSelector("#nav-virtual-world-virtualmotorracing a");

    private static final By SPEEDWAY = By.cssSelector("#nav-virtual-world-virtualspeedway a");

    private static final By CYCLING = By.cssSelector("#nav-virtual-world-virtualcycling a");

    // For Races

    private static final By NEXT_OFF_RACES = By.cssSelector(".race-nav__group-header-name.ng-binding");

    private static final By ALL_MEETINGS = By.xpath("//h2[contains(.,'All Meetings')]");

    private static final By MEETINGS_MARKETS = By.cssSelector(".race-subnav__group.ng-scope");

    private static final By ALL_RACE_MEETINGS = By.cssSelector("button[class*='button-standard race-meeting__selection']");

    protected static final By LOAD_MORE_LINK = By.cssSelector(".race-meeting__link.ng-scope");

    private static final By ALL_RACES_DISPLAYED = By.cssSelector(".race-event--highlights.ng-scope");

    // For Football

    private static final By MARKET_SECTION = By.cssSelector("#market-collections");

    private static final By MARKETS_MENU_HEADER = By.xpath("//h2[text()='Markets']");

    private static final By ALL_MARKETS_DISPLAYED = By.cssSelector("#market-collections li[class*='marketmenu__section']");

    private static final By ALL_EVENTS_DISPLAYED = By.cssSelector("article[data-id^='comp-OB_EV']");

    private static final By ALL_EVENTS_EXPAND_BUTTONS = By.cssSelector("a[data-ng-if*='event.expand']");


    private static final By VIRTUAL_WORLD_RULES = By.cssSelector("#virtual-rules-banner");

    private static final By CONTENT_VIDEO = By.cssSelector("");

    protected static final By CURRENT_MARKET_HEADER = By.cssSelector(".fl.ng-binding");

    protected static final By CURRENT_MARKET_CONTENT = By.cssSelector("#comp-");

    private static final By ALL_SELECTIONS_DISPLAYED = By.cssSelector("button[id^='OB_OU']");

    protected static final By ALL_SELECTIONS_NON_SUSPENDED = By.cssSelector("");





    @Override
    public boolean isPreloaderDisplayed(){
        waitElementNotToBeVisible(PRELOADER, 10);
        return find(PRELOADER).isDisplayed();
    }

    @Override
    public boolean isVirtualsTitleDisplayed(){
        return find(VIRTUAL_WORLD_TITLE).isDisplayed();
    }

    @Override
    public boolean isVirtualWorldHeaderDisplayed(){
        return find(VIRTUAL_WORLD_HEADER).isDisplayed();
    }

    @Override
    public boolean isVirtualWorldRulesLinkDisplayed(){
        return find(VIRTUAL_WORLD_RULES).isDisplayed();
    }


    // FOR RACING

    @Override
    public boolean areMeetingMarketsDisplayed(){
        return findElements(MEETINGS_MARKETS).size() > 0;
    }

    @Override
    public boolean areRaceMeetingsDisplayed(){
        return findElements(ALL_RACE_MEETINGS).size() >= 1;
    }

    @Override
    public boolean areRacesDisplayed(){
        return findElements(ALL_RACES_DISPLAYED).size() >= 1;
    }


    // FOR FOOTBALL

    @Override
    public boolean isMarketSectionDisplayed(){
        return find(MARKET_SECTION).isDisplayed();
    }

    @Override
    public boolean isMarketSectionHeaderDisplayed(){
        return find(MARKETS_MENU_HEADER).isDisplayed();
    }

    @Override
    public boolean areFootballMarketsDisplayed(){
        return findElements(ALL_MARKETS_DISPLAYED).size() >= 1;
    }

    @Override
    public void areFootballMarketsDisplayed(int index){
        findElements(ALL_MARKETS_DISPLAYED).get(index -1).click();
    }

    @Override
    public boolean areEventsDisplayed(){
        return findElements(ALL_EVENTS_DISPLAYED).size() >= 1;
    }

    @Override
    public void expandGivenEventByIndex(int number){
        findElements(ALL_EVENTS_DISPLAYED).get(number -1).findElement(ALL_EVENTS_EXPAND_BUTTONS).click();
    }

    // For both

    @Override
    public boolean areSelectionsDisplayed(){
        return findElements(ALL_SELECTIONS_DISPLAYED).size() >= 1;
    }

    @Override
    public boolean isContentVideoDisplayed(){
        return find(CONTENT_VIDEO).isDisplayed();
    }



    @Override
    public void clickVirtualWorldHeader(){
        find(VIRTUAL_WORLD_HEADER).click();
    }


    @Override
    public boolean areSectionOptionsDisplayedOnDesktop(){

        if(!find(HORSE_RACING_FLAT).isDisplayed()){
            return false;
        }

        if(!find(HORSE_RACING_NATIONAL_HUNT).isDisplayed()){
            return false;
        }

        if(!find(FOOTBALL).isDisplayed()){
            return false;
        }

        if(!find(GREYHOUNDS_FLAT).isDisplayed()){
            return false;
        }

        if(!find(GREYHOUNDS_HURDLES).isDisplayed()){
            return false;
        }

        if(!find(MOTOR_RACING).isDisplayed()){
            return false;
        }

        if(!find(SPEEDWAY).isDisplayed()){
            return false;
        }

        return find(CYCLING).isDisplayed();

    }

    @Override
    public void clickOnHorseRacingFlat(){
        find(HORSE_RACING_FLAT).click();
    }

    @Override
    public void clickOnHorseRacingNationalHunt(){
        find(HORSE_RACING_NATIONAL_HUNT).click();
    }

    @Override
    public void clickOnFootball(){
        find(FOOTBALL).click();
    }

    @Override
    public void clickOnGreyhoundsFlat(){
        find(GREYHOUNDS_FLAT).click();
    }

    @Override
    public void clickOnGreyhoundsHurdles(){
        find(GREYHOUNDS_HURDLES).click();
    }

    @Override
    public void clickOnMotorRacing(){
        find(MOTOR_RACING).click();
    }

    @Override
    public void clickOnSpeedway(){
        find(SPEEDWAY).click();
    }

    @Override
    public void clickOnCycling(){
        find(CYCLING).click();
    }

    @Override
    public void clickOnNextOffRaces(){
        find(NEXT_OFF_RACES).click();
    }

    @Override
    public void clickOnAllMeetings(){
        find(ALL_MEETINGS).click();
    }

    @Override
    public void clickOnFirstAvailableSelection(){
        int lastElement = findElements(ALL_SELECTIONS_DISPLAYED).size();
        try {
            click(findElements(ALL_SELECTIONS_DISPLAYED).get(lastElement - 1));
        } catch (Exception ex) {
            throw new NoSelectionAvailable();
        }
    }

    @Override
    public boolean highlightedOptionInSectionsMenu(String option){
        return find(MENU_SECTION_ACTIVE).getText().equals(option);
    }

}
