package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AllInPlayPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class AllInPlayPageImpl extends AbstractSportsPageObject implements AllInPlayPage{

    private static By MAIN_CONTENT_SECTION = By.cssSelector(".off-canvas__main--left");

    private static By LEFT_MARKET_COLLECTION = By.id("market-collections");

    private static String SPORT_BY_NAME = "//nav[@id='market-collections']/section[contains(@class,'marketmenu__section')][%s]/a";

    private static By MARKET_MENU_SECTION = By.cssSelector(".marketmenu__section");

    private static By CAROUSEL_ELEMENT = By.cssSelector("#contextual-navigation");

    private static By CAROUSEL_SPORTS_ELEMENT = By.cssSelector(".contextual-nav__item");

    @Override
    public boolean isDisplayed(final String event) {
        return isMainContentDisplayed() && isLeftMarketCollectionDisplayed();
    }

    @Override
    public boolean isMainContentDisplayed() {
        return isElementDisplayed(MAIN_CONTENT_SECTION);
    }

    @Override
    public boolean isLeftMarketCollectionDisplayed() {
        return isElementDisplayed(LEFT_MARKET_COLLECTION);
    }

    @Override
    public void clickOnSportByPosition(int position) {
        click(By.xpath(String.format(SPORT_BY_NAME,position)));
    }

    @Override
    public List<String> getSportList() {
        List<WebElement> elementList = find(LEFT_MARKET_COLLECTION).findElements(MARKET_MENU_SECTION);
        List<String> sportsList = new ArrayList<>();
        for (WebElement sport : elementList) {
            sportsList.add(sport.getText().replaceAll("[\n\r]", ""));
        }
        return sportsList;
    }

    @Override
    public boolean isCarouselDisplayed() {
        return isElementDisplayed(CAROUSEL_ELEMENT);
    }

    @Override
    public void clickOnCarouselIconByPosition(Integer position) {
        click(find(CAROUSEL_ELEMENT).findElements(CAROUSEL_SPORTS_ELEMENT).get(position));
    }

    @Override
    public Integer getCarouselElementsNumber() {
        return find(CAROUSEL_ELEMENT).findElements(CAROUSEL_SPORTS_ELEMENT).size();
    }

}
