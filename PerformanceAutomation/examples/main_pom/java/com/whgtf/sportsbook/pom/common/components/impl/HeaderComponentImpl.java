package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.*;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractSportsPage;
import com.whgtf.sportsbook.pom.common.pages.interfaces.FeaturedPage;
import com.whgtf.sportsbook.pom.common.pages.interfaces.TopBetsPage;
import com.whgtf.sportsbook.pom.mobile.components.impl.NativeHeaderComponentImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Lazy
public class HeaderComponentImpl extends AbstractCommonObject implements HeaderComponent {

    private static final By HEADER_BAR = By.id("localNav");

    private static final By SHOW_MORE = By.id("localnavigationButtonShowMore");

    private static final By FOOTBALL_LINK = By.cssSelector("#localNav .icon-football");

    private static final By DARTS_LINK = By.cssSelector("#localNav .icon-darts");

    private static final By VIRTUAL_WORLD_LINK = By.cssSelector("#localNav .icon-virtual-world");

    private static final By GREYHOUNDS_LINK = By.cssSelector("#localNav .icon-greyhounds");

    private static final By HORSES_LINK = By.cssSelector("#localNav .icon-horse-racing");

    private static final By TENNIS_LINK = By.cssSelector("#localNav .icon-tennis");

    private static final By IN_PLAY_LINK = By.cssSelector("#localNav .icon-in-play");

    private final static By TOP_BETS_LINK = By.cssSelector("#localNav .icon-most-popular-bets2");

    private static final By LOGIN_BUTTON = By.cssSelector(".account-tab__button");

    private static final By JOIN_BUTTON = By.id("account_joinLink");

    private static final By BALANCE_BUTTON = By.id("account_desktop_balanceLink");

    private static final By BALANCE_LINK_MOBILE = By.id("account_mobile_balanceLink");

    private static final By ALL_SPORTS_NAMES_DISPLAYED_ON_HEADER = By.cssSelector("#localNav a");


    // Elements for Smoke Testing Navigation

    private static String givenSport = "//div[@id='localNav']/a/span[text()='%s']";


    @Autowired
    @Lazy
    private FeaturedPage sportsPage;

    @Autowired
    @Lazy
    private LoginComponent loginComponent;

    @Autowired
    @Lazy
    private AccountTabComponent accountTabComponent;

    @Autowired
    @Lazy
    private JoinFormComponent joinFormComponent;

    @Autowired
    @Lazy
    private BottomBarComponent bottomBarComponent;

    @Autowired
    @Lazy
    private TopBetsPage topBetsPage;

    public HeaderComponentImpl clickOnShowMore() {
        waitSportsbook();
        click(find(HEADER_BAR).findElement(SHOW_MORE));
        return this;
    }

    @Override
    public AbstractSportsPage clickOnSport(final String sport) {
        String lowerCaseSport = sport.toLowerCase();
        switch (lowerCaseSport) {
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
            case "virtual world":
                return clickOnVirtualWorld();
            case "in-play":
                return clickOnInPlay();
            case "top bets":
                return clickOnTopBets();
            default:
                throw new IllegalArgumentException("Invalid sport: " + sport);
        }
    }

    public List<String> getListSports() {
        List<WebElement> elementList = findElements(ALL_SPORTS_NAMES_DISPLAYED_ON_HEADER);
        List<String> nameList = new ArrayList<>();
        for (WebElement element : elementList) {
            String name = element.getText();
            nameList.add(name);
        }
        return nameList;
    }

    public void clickOnSportByName(String name) {
        click(By.xpath(String.format(givenSport, name)));
    }

    @Override
    public boolean waitForBalanceValue(String expectedValue, long timeout) {
        try {
            if (isMobile()) {
                if (isElementDisplayed(By.id("balanceLink")))
                    return waitElementAttributeToContain(By.id("balanceLink"), "text", expectedValue, timeout);
                else
                    return waitElementAttributeToContain(BALANCE_LINK_MOBILE, "text", expectedValue, timeout);
            } else {
                if (isElementDisplayed(By.id("userBalance")))
                    return waitElementAttributeToContain(By.id("userBalance"), "text", expectedValue, timeout);
                else
                    return waitElementAttributeToContain(BALANCE_BUTTON, "text", expectedValue, timeout);
            }
        } catch(TimeoutException ex) {
            return false;
        }
    }

    public FeaturedPage clickOnFootball() {
        clickAndWaitSportsbook(FOOTBALL_LINK);
        return sportsPage;
    }

    public FeaturedPage clickOnDarts() {
        clickAndWaitSportsbook(DARTS_LINK);
        return sportsPage;
    }

    public FeaturedPage clickOnVirtualWorld() {
        clickAndWaitSportsbook(VIRTUAL_WORLD_LINK);
        return sportsPage;
    }

    public FeaturedPage clickOnGreyhounds() {
        clickAndWaitSportsbook(GREYHOUNDS_LINK);
        return sportsPage;
    }

    public FeaturedPage clickOnHorses() {
        clickAndWaitSportsbook(HORSES_LINK);
        return sportsPage;
    }

    public FeaturedPage clickOnTennis() {
        clickAndWaitSportsbook(TENNIS_LINK);
        return sportsPage;
    }

    @Override
    public FeaturedPage clickOnInPlay() {
        clickAndWaitSportsbook(IN_PLAY_LINK);
        return sportsPage;
    }

    @Override
    public LoginComponent clickOnLoginButton() {
        if (!loginComponent.isDisplayed())
            clickAndWaitSportsbook(LOGIN_BUTTON);
        return loginComponent;
    }

    @Override
    public AccountTabComponent clickOnAccountComponent() {
        clickAndWaitSportsbook(LOGIN_BUTTON);
        return accountTabComponent;
    }

    @Override
    public JoinFormComponent clickOnJoinButton() {
        clickAndWaitSportsbook(JOIN_BUTTON);
        return joinFormComponent;
    }

    @Override
    public AbstractSportsPage clickOnTopBets() {
        click(TOP_BETS_LINK);
        return topBetsPage;
    }

    @Override
    public boolean isBalanceDisplayed() {
        //TODO
        if (isMobile()) {
            if(isElementDisplayed(By.id("balanceLink")))
                return waitElementToBeVisible(By.id("balanceLink"),10).isDisplayed();
            else
                return waitElementToBeVisible(BALANCE_LINK_MOBILE, 10).isDisplayed();
        } else {
            if(isElementDisplayed(By.id("userBalance")))
                return waitElementToBeVisible(By.id("userBalance"),10).isDisplayed();
            else
                return waitElementToBeVisible(BALANCE_BUTTON, 10).isDisplayed();
        }
    }

    @Override
    public boolean isJoinNowDisplayed() {
        //TODO
        if(isElementDisplayed(By.id("joinLink")))
            return waitElementToBeClickable(By.id("joinLink"), 5).isDisplayed();
        else
            return waitElementToBeClickable(JOIN_BUTTON, 5).isDisplayed();
    }

    @Override
    public String getBalance() {
        //TODO
        if (isNativeMobileApp()) {
            return new NativeHeaderComponentImpl().getBalance();
        } else if (isMobile()) {
            if(isElementDisplayed(By.id("balanceLink")))
                return find(By.id("balanceLink")).getText().substring(1);
            else
                return find(BALANCE_LINK_MOBILE).getText().substring(1);
        }
        else {
            if(isElementDisplayed(By.id("userBalance")))
                return find(By.id("userBalance")).getText().substring(1);
            else
                return find(BALANCE_BUTTON).getText().substring(1);
        }
    }

    @Override
    public LoginComponent getLoginComponent() {
        return loginComponent;
    }


}
