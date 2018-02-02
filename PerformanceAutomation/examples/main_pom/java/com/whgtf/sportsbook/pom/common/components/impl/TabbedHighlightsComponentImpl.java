package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.TabbedHighlightsComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.common.pages.impl.CouponsPageImpl;
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
public class TabbedHighlightsComponentImpl extends AbstractCommonObject implements TabbedHighlightsComponent {



    private final static By TABS_CONTAINER = By.cssSelector(".tabs .tabs__nav");

    private final static By ALL_TABS = By.cssSelector(".tabs .tabs__nav a");

    private final static By IN_PLAY_TAB = By.cssSelector(".tabs__nav-item[data-name='In Play']");

    private final static By TOP_BETS_TAB = By.cssSelector(".tabs__nav-item[data-name='Top Bets']");

    private final static By ENHANCED_TAB = By.cssSelector(".tabs__nav-item[data-name='Enhanced']");

    private final static By NEXT_OFF_TAB = By.cssSelector(".tabs__nav-item[data-name='Next Off']");

    private final static By HIGHTLIGHTS_TAB = By.cssSelector(".tabs__nav-item[data-name='Highlights']");

    private final static By COUPONS_TAB = By.cssSelector(".tabs__nav-item[data-name='Coupons']");

    @Autowired
    @Lazy
    private InPlaySectionImpl inPlaySection;

    @Autowired
    @Lazy
    private HighlightsSectionImpl highlightsSection;

    @Autowired
    @Lazy
    private TopBetsPage topBetsPage;

    @Autowired
    @Lazy
    private CouponsPageImpl couponsPage;

    @Override
    public boolean isDisplayed() {
        return isElementDisplayed(TABS_CONTAINER);
    }

    @Override
    public boolean isInPlayTabDisplayed() {
        return isElementDisplayed(IN_PLAY_TAB);
    }

    @Override
    public InPlaySectionImpl clickOnInPlayTab() {
        find(IN_PLAY_TAB).click();
        return inPlaySection;
    }

    @Override
    public boolean isHighlightsTabDisplayed() {
        return isElementDisplayed(HIGHTLIGHTS_TAB);
    }

    @Override
    public HighlightsSectionImpl clickOnHighlightsTab() {
        find(HIGHTLIGHTS_TAB).click();
        return highlightsSection;
    }

    //Only suitable for Mobile.
    @Override
    public List<String> getListOfTabs() {

        List<WebElement> tabsList = findElements(ALL_TABS);
        ArrayList<String> result = new ArrayList<>();

        for (WebElement element:tabsList) {
            result.add(element.getText());
        }

        return result;
    }

    @Override
    public TopBetsPage clickOnTopBetsTab() {
        find(TOP_BETS_TAB).click();
        return topBetsPage;
    }

    @Override
    public CouponsPageImpl clickOnCouponsTab() {
        find(COUPONS_TAB).click();
        return couponsPage;
    }

}
