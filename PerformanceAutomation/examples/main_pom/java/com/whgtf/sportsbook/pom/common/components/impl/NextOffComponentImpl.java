package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.NextOffComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by javierg on 30/06/2016.
 */
@Component
@Lazy
public class NextOffComponentImpl extends AbstractCommonObject implements NextOffComponent {

    private static final By NEXT_OFF_HEADER = By.id("next-off-header");

    private static final By NEXT_OFF_CONTENT = By.cssSelector(".race-nav__group-content.nextOff");

    private static final By EXPAND_COLLAPSE_NEXT_OFF = By.cssSelector("div[class^='race-nav__group race-nav__group-next-off']");

    private static final By RACES_DISPLAYED = By.cssSelector("section[class='race-event--highlights next-off-slider__slide']");

    private static final By ALL_RACES_SELECTIONS = By.cssSelector("button[id^='OB_OU']");

    private static final By RACES_TIMES = By.cssSelector("");

    private static final By RACES_TITLES = By.cssSelector("");

    private static final By RACES_TV_ICONS = By.cssSelector("");

    private static final By RACES_SELECTIONS_HORSE_NAMES = By.cssSelector("");

    private static final By RACES_SELECTIONS_JOKEY_NAMES = By.cssSelector("");

    private static final By RACES_SELECTIONS_TRAINER_NAMES = By.cssSelector("");

    private static final By RACES_UNAMED_FAVOURITES = By.cssSelector("");

    private static final By RACES_VIEW_FULL_CARD = By.cssSelector("");

    private static final By RACES_RUNS = By.cssSelector("");


    /**
     * {@inheritDoc}
     */
    @Override
    public void expandNextOff(){
        if(!isNextOffExpanded()){
            click(NEXT_OFF_HEADER);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collapseNextOff(){
        if(isNextOffExpanded()){
            click(NEXT_OFF_HEADER);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNextOffExpanded(){
        return isElementDisplayed(find(EXPAND_COLLAPSE_NEXT_OFF).findElements(RACES_DISPLAYED).get(0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNextOffDisplayed(){
        return isElementDisplayed(EXPAND_COLLAPSE_NEXT_OFF);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnGivenSelectionFromGivenRace(int selectionIndex, int raceIndex){
       findElements(RACES_DISPLAYED).get(raceIndex - 1).findElements(ALL_RACES_SELECTIONS).get(raceIndex - 1).click();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfNextOffRacesDisplayed(){
        return findElements(RACES_DISPLAYED).size();
    }

    @Override
    public boolean areNextOffRacesDisplayed(){
        return isElementDisplayed(NEXT_OFF_CONTENT);
    }

}
