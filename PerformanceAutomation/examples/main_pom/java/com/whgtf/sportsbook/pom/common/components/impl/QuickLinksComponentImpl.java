package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.QuickLinksComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractSportsPage;
import com.whgtf.sportsbook.pom.common.pages.interfaces.FeaturedPage;
import com.whgtf.sportsbook.pom.common.pages.interfaces.TopBetsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class QuickLinksComponentImpl extends AbstractCommonObject implements QuickLinksComponent {

    private final static By QUICKLINKS_COMPONENT = By.id("localNavigationMobileSports");

    private final static By FOOTBALL_LINK = By.className("icon-football");

    private final static By TENNIS_LINK = By.className("icon-tennis");

    private final static By VIRTUAL_WORLD_LINK = By.cssSelector(".quick-links-container .gaming-icon.icon-virtual-world");

    private final static By HORSES_LINK = By.className("icon-horse-racing");

    private final static By GREYHOUNDS_LINK = By.className("icon-greyhounds");

    private final static By DARTS_LINK = By.className("icon-darts");

    private final static By IN_PLAY_LINK = By.className("icon-in-play");

    private final static By TOP_BETS_LINK = By.cssSelector("#localNavigationMobileSports .icon-most-popular-bets2");

    private static final By SPORT_LOCATOR = By.xpath("//section[@class='localnavigation__column-dropdown']/a");

    private static final String GIVEN_SPORT = ".icon-%s";


    @Autowired
    @Lazy
    private FeaturedPage sportsPage;

    @Autowired
    @Lazy
    private TopBetsPage topBetsPage;

    public Object clickOnSport(final String sport) {
        switch (sport.toLowerCase()) {
            case "football":
                return clickOnFootball();
            case "tennis":
                return clickOnTennis();
            case "horse-racing":
                return clickOnHorses();
            case "horse racing":
                return clickOnHorses();
            case "greyhounds":
                return clickOnGreyhounds();
            case "darts":
                return clickOnDarts();
            case "in-play":
                return clickOnInPlay();
            case "virtual world":
                return clickOnVirtualWorld();
            case "top bets":
                return clickOnTopBets();
            default:
                return null;
        }
    }

    public AbstractSportsPage clickOnFootball() {
        click(FOOTBALL_LINK);
        return sportsPage;
    }

    public AbstractSportsPage clickOnDarts() {
        click(DARTS_LINK);
        return sportsPage;
    }

    public AbstractSportsPage clickOnVirtualWorld() {
        click(VIRTUAL_WORLD_LINK);
        return sportsPage;
    }

    @Override
    public AbstractSportsPage clickOnInPlay() {
        click(IN_PLAY_LINK);
        return sportsPage;
    }

    public AbstractSportsPage clickOnGreyhounds() {
        click(GREYHOUNDS_LINK);
        return sportsPage;
    }

    public AbstractSportsPage clickOnHorses() {
        click(HORSES_LINK);
        return sportsPage;
    }

    public AbstractSportsPage clickOnTennis() {
        click(TENNIS_LINK);
        return sportsPage;
    }

    public AbstractSportsPage clickOnTopBets() {
        click(TOP_BETS_LINK);
        return topBetsPage;
    }

    public List<String> getListSports() {
        List<WebElement> elementList = findElements(SPORT_LOCATOR);
        List<String> nameList = new ArrayList<>();
        for (WebElement element:elementList) {
            String name = element.getText();
            nameList.add(name);
        }
        return nameList;
    }

    /**
     * {@inheritDoc}
     */
    public void clickOnSportByName(String name){
        find(By.cssSelector(String.format(GIVEN_SPORT, name))).click();
    }




}
