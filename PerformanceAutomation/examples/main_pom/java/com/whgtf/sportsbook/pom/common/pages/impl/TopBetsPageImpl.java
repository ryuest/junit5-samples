package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractSportsPage;
import com.whgtf.sportsbook.pom.common.pages.interfaces.TopBetsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class TopBetsPageImpl extends AbstractSportsPageObject implements TopBetsPage{

    private List<Selection> selectionList = new ArrayList<>();

    private static final By SECTION_ID = By.id("topBetsContainer");

    private static final By SHOW_MORE_LINK = By.cssSelector(".-margin-top.topbets__footer.-text-center a");

    private static final By ALL_TOP_BETS_EVENTS = By.cssSelector("li[id^='OB_EV']");

    private static final By ALL_TOP_BETS_EVENTS_NAMES = By.cssSelector("li[id^='OB_EV'] .topbets__list-item--link");


    @Override
    public void load() {
        waitElementToBeClickable(SHOW_MORE_LINK,5);
        List<WebElement> selecList = findElements(By.xpath("//div[@id='topBetsContainer']//li"));
        for (WebElement selection: selecList) {

            Selection sel= new Selection(selection.findElement(By.tagName("button")).getAttribute("id"),
                    selection.findElement(By.tagName("button")).getAttribute("data-odds"));
            selectionList.add(sel);
        }
    }


    @Override
    public boolean isDisplayed(){
        return isElementDisplayed(SECTION_ID) && this.getListOfEventNames().size()>0;
    }

    @Override
    public AbstractSportsPage clickOnSelection(int position) {
        if(selectionList.isEmpty())
            load();
        clickOnSelection(selectionList.get(position).getPdsId());
        return new AbstractSportsPageObject();
    }

    @Override
    public AbstractSportsPage clickOnFirstSelection() {
        if(selectionList.isEmpty())
            load();
        clickOnSelection(selectionList.get(0).getPdsId());
        return new AbstractSportsPageObject();
    }

    @Override
    public void clickOnShowMoreLink() {
        try {
            click(SHOW_MORE_LINK);
        } catch (WebDriverException ex) {
            executeJavaScript("window.scrollBy(0,100)");
            click(SHOW_MORE_LINK);
        }
    }

    @Override
    public boolean isPageDisplayed() {
        return find(SECTION_ID).isDisplayed() && getCurrentPageName().equals("top-bets");
    }

    @Override
    public List<String> getTopBetEventsSportName(){
        List<String> result = new ArrayList<>();
        List<WebElement> eventsDisplayed = findElements(ALL_TOP_BETS_EVENTS);
        for (WebElement element:eventsDisplayed) {
            result.add(element.findElement(By.xpath(".//span[@class='topbets__icon-name']")).getText().replace(" |", ""));
        }
        return result;
    }

    @Override
    public List<String> getListOfEventNames(){
        List<String> result = new ArrayList<>();
        List<WebElement> eventNames = findElements(ALL_TOP_BETS_EVENTS_NAMES);
        for (WebElement element:eventNames) {
            result.add(element.getText());
        }
        return result;
    }

    @Override
    public void clickOnTopBetSelectionByIndex(int index) {
        findElements(ALL_TOP_BETS_EVENTS).get(index -1).findElement(By.tagName("button")).click();
    }

}
