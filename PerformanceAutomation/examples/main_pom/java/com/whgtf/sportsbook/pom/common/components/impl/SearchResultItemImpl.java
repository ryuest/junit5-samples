package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.SearchResultItem;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Created by cbairaagoni on 10/11/2016.
 */
public class SearchResultItemImpl extends AbstractCommonObject implements SearchResultItem {

    private final static By SEARCH_EVENTS_NAME = By.cssSelector(".search-events__name");

    private final static By SEARCH_EVENTS_DATE = By.cssSelector(".search-events__date-comp");

    private final static By SEARCH_EVENTS_IN_PLAY = By.cssSelector(".search-events__inplay");

    private final static By SEARCH_EVENTS_STREAMING = By.cssSelector(".search-events__streaming");

    private WebElement resultItem;

    public SearchResultItemImpl(WebElement resultItem) {
        this.resultItem = resultItem;
    }


    @Override
    public String getEventName() {
        try {
            return resultItem.findElement(SEARCH_EVENTS_NAME).getText();
        } catch (NoSuchElementException nse) {
            return StringUtils.EMPTY;
        }
    }


    @Override
    public String getDateTime() {
        try {
            return resultItem.findElement(SEARCH_EVENTS_DATE).getText().split(",")[0];
        } catch (NoSuchElementException nse) {
            return StringUtils.EMPTY;
        }
    }


    @Override
    public String getCompetetionName() {
        try {
            return resultItem.findElement(SEARCH_EVENTS_DATE).getText().split(",")[1];
        } catch (NoSuchElementException nse) {
            return StringUtils.EMPTY;
        }
    }


    @Override
    public boolean isInPlayIconDisplayed() {
        return isElementDisplayed(resultItem.findElement(SEARCH_EVENTS_IN_PLAY));
    }


    @Override
    public boolean isStreamingIconDisplayed() {
        return isElementDisplayed(resultItem.findElement(SEARCH_EVENTS_STREAMING));
    }
}