package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.components.impl.LeftFutureRacesNavigationComponentImpl;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AntePostPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class AntePostPageImpl extends RacingEventPageImpl implements AntePostPage {

    private static final By SELECTION_BUTTON = By.xpath("//button[@class='oddsbutton']");

    private static final By ANTE_POST_SECTION = By.className("racecard--antepost");

    private static final By ANTEPOST_HEADER = By.cssSelector("header[data-toggle-send='Antepost']");

    public static final By RACE_SUBNAV_GROUP = By.cssSelector(".race-subnav__group");

    public static final By RACE_GROUP_ITEM = By.cssSelector(".race-group-content__item");

    private List<Selection> selectionList;

    private String PATH = "/ante-post";

    @Autowired
    @Lazy
    private LeftFutureRacesNavigationComponentImpl futureRaceComponent;

    @Override
    public void load() {
        selectionList = new ArrayList<>();
        List<WebElement> elementList = findElements(SELECTION_BUTTON);
        for (WebElement element:elementList) {
            Selection selection = new Selection(element.getAttribute("id"),element.getAttribute("data-odds"));
            selectionList.add(selection);
        }
    }

    @Override
    public LeftFutureRacesNavigationComponentImpl getFutureRaceComponent() {
        return futureRaceComponent;
    }

    @Override
    public void clickOnFirstSelectionFirstEvent() {

    }

    @Override
    public void clickOnFirstSelectionByEvent(String event) {

    }

    @Override
    public boolean isDisplayed() {
        return find(ANTE_POST_SECTION).isDisplayed() && getCurrentPageName().equals("antePost");
    }

    @Override
    public boolean isDisplayedInLanguage(String language){
        return getCurrentUrl().contains(PATH) && super.isDisplayedInLanguage(language);
    }

    @Override
    public boolean antePostHeaderDisplay(){
        return isElementDisplayed(ANTEPOST_HEADER);
    }

    @Override
    public boolean isRaceItemsDisplayedInGroupNavigation(){
        return (isElementDisplayed(RACE_SUBNAV_GROUP) && isElementDisplayed(RACE_GROUP_ITEM));
    }

}
