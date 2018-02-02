package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.SportsNavigationComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class SportsNavigationComponentImpl extends AbstractCommonObject implements SportsNavigationComponent {

    private static final By NAVIGATION_MENU = By.id("contextual-navigation-menu");

    private static final By NAVIGATION_ITEM = By.cssSelector(".contextual-nav__item");

    @Override
    public boolean isDisplayed() {
        return isElementDisplayed(NAVIGATION_MENU);
    }

    @Override
    public void clickOnSportByPosition(int position) {
        click(find(NAVIGATION_MENU).findElements(NAVIGATION_ITEM).get(position));
    }

    @Override
    public List<String> getSportList() {
        List<String> sportList = new ArrayList<>();
        List<WebElement> elementList = find(NAVIGATION_MENU).findElements(NAVIGATION_ITEM);
        for (WebElement sport:elementList) {
            sportList.add(sport.getText());

        }
        return sportList;
    }
}
