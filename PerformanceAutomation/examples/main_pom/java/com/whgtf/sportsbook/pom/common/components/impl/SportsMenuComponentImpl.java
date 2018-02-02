package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.SearchComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.SportsMenuComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.InPlayPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Lazy
public class SportsMenuComponentImpl extends AbstractCommonObject implements SportsMenuComponent {

    private final static By DESKTOP_SIDEBAR = By.id("desktop-sidebar");

    private final static By SPORTS_COMPONENT_ID = By.id("desktop-sidebar-az");

    private final static By HOME_COMPONENT_ID = By.id("desktop-sidebar-head");

    private final static By CROSS_SELL_COMPONENT = By.id("desktop-sidebar-crosssell");

    private final static By EXTRAS_COMPONENT = By.id("desktop-sidebar-extras");

    private final static By ALL_SPORTS_NAMES = By.cssSelector("li a");

    private final static By SEARCH_ELEMENT = By.id("nav-Search");

    private final static By IN_PLAY_ELEMENT = By.id("nav-in-play");

    private final static By HOME_ELEMENT = By.id("nav-homepage");

    private final static String SPORT_MENU_ELEMENT = "#nav-%s a";

    private final static String MENU_SUBMENU_ELEMENT = "#nav-%s-%s a";

    private final static String ACTIVE_ELEMENT = "li[id^='nav-'][id*='-%s'] a[class*='c-list__item--active']";

    private final static String SECTION_ELEMENT = "li[id*='-%s']";

    @Autowired
    @Lazy
    SearchComponent searchComponent;

    @Autowired
    @Lazy
    InPlayPage inPlayPage;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnSportByName(String sport) {
        sport = sport.toLowerCase().replaceAll(" ","-").replaceAll("/","");
        click(find(DESKTOP_SIDEBAR).findElement(By.cssSelector(String.format(SPORT_MENU_ELEMENT,sport))));
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void clickOnSportPageByName(String sport, String subSport) {
        sport = sport.toLowerCase().replaceAll(" ","-");
        clickOnSportByName(sport);
        if("Daily list".equalsIgnoreCase(subSport))
            subSport = "matches";
        else if("Future Races".equalsIgnoreCase(subSport))
            subSport = "antepost";
        else if("Horse Racing - Flat".equalsIgnoreCase(subSport))
            subSport = "horsesflat";
        else if("Horse Racing - National Hunt".equalsIgnoreCase(subSport))
            subSport = "horsesnationalhunt";
        else if("Greyhounds - Hurdles".equalsIgnoreCase(subSport))
            subSport = "greyhoundsjumps";
        else
            subSport = subSport.toLowerCase().replaceAll(" ","").replaceAll("-","");
        if(sport.contains("virtual"))
            subSport="virtual"+subSport;

        click(find(By.cssSelector(String.format(MENU_SUBMENU_ELEMENT,sport, subSport))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnHome() {
        click(find(HOME_COMPONENT_ID).findElement(HOME_ELEMENT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchComponent clickOnSearch() {
        find(HOME_COMPONENT_ID).findElement(SEARCH_ELEMENT).click();
        return searchComponent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InPlayPage clickOnInPlay() {
        find(HOME_COMPONENT_ID).findElement(IN_PLAY_ELEMENT).click();
        return inPlayPage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getListSports() {
        List<WebElement> elementList = find(DESKTOP_SIDEBAR).findElement(SPORTS_COMPONENT_ID).findElements(ALL_SPORTS_NAMES);
        List<String> nameList = new ArrayList<>();
        for (WebElement element : elementList) {
            String name = element.getText();
            nameList.add(name);
        }
        return nameList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisplayed() {
        return isElementDisplayed(DESKTOP_SIDEBAR) && isElementDisplayed(SPORTS_COMPONENT_ID) &&
                isElementDisplayed(HOME_COMPONENT_ID) &&
                isElementDisplayed(CROSS_SELL_COMPONENT) &&
                isElementDisplayed(EXTRAS_COMPONENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSportPageSelected(String pageId) {
        return isElementDisplayed(By.cssSelector(String.format(ACTIVE_ELEMENT, pageId)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDailyListSelected() {
        return isElementDisplayed(By.cssSelector(String.format(ACTIVE_ELEMENT,"matches")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInPlaySelected() {
        return isElementDisplayed(By.cssSelector(String.format(ACTIVE_ELEMENT,"inplay")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCompetitionsSelected() {
        return isElementDisplayed(By.cssSelector(String.format(ACTIVE_ELEMENT,"competitions")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCouponsSelected() {
        return isElementDisplayed(By.cssSelector(String.format(ACTIVE_ELEMENT,"coupons")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFutureRacesSelected() {
        return isElementDisplayed(By.cssSelector(String.format(ACTIVE_ELEMENT,"antepost")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSpecialsSelected() {
        return isElementDisplayed(By.cssSelector(String.format(ACTIVE_ELEMENT,"specials")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTopBetsSelected() {
        return isElementDisplayed(By.cssSelector(String.format(ACTIVE_ELEMENT,"topbets")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnSportByPosition(int position) {
        click(find(DESKTOP_SIDEBAR).findElements(By.tagName("a")).get(position));
    }

}
