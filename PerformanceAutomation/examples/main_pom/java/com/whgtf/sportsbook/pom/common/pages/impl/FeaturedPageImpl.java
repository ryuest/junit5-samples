package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.pom.common.components.impl.HighlightsSectionImpl;
import com.whgtf.sportsbook.pom.common.components.impl.InPlaySectionImpl;
import com.whgtf.sportsbook.pom.common.components.impl.TabbedHighlightsComponentImpl;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.FeaturedPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class FeaturedPageImpl extends AbstractSportsPageObject implements FeaturedPage {

    private static final By FEATURED_SECTION = By.cssSelector(".-highlights");

    private static final By MARKET_BLURB = By.className("btmarket__blurb");

    private final static By HIGHTLIGHTS_TAB = By.cssSelector(".tabs__nav-item[data-name='Highlights']");

    private final static By IN_PLAY_TAB = By.cssSelector(".tabs__nav-item[data-name='In Play']");

    @Autowired
    @Lazy
    private InPlaySectionImpl inPlaySection;

    @Autowired
    @Lazy
    private HighlightsSectionImpl highlightsSection;


    public static String PATH = "/";


    @Autowired
    private TabbedHighlightsComponentImpl tabbedComponent;


    public FeaturedPageImpl() {
        PATH=PATH.concat(System.getProperty("sport", "football"));
    }

    public void open() {
        open(BASE_URL.concat(PATH));
    }


    public boolean isInPlayTabDisplayed() {
        return isElementDisplayed(IN_PLAY_TAB);
    }

    @Override
    public InPlaySectionImpl getInPlaySection() {
        if(isMobile())
            if(tabbedComponent.isDisplayed())
                if(isInPlayTabDisplayed())
                tabbedComponent.clickOnInPlayTab();
        return inPlaySection;
    }

    public boolean isHighlightsTabDisplayed() {
        return isElementDisplayed(HIGHTLIGHTS_TAB);
    }

    @Override
    public HighlightsSectionImpl getHighlightsSection() {
        if(isMobile()){
            if(tabbedComponent.isDisplayed())
                if(isHighlightsTabDisplayed())
                tabbedComponent.clickOnHighlightsTab();
        }
        return highlightsSection;
    }

    @Override
    public void load() {
        if(isMobile()){
            tabbedComponent.clickOnHighlightsTab();
            highlightsSection.load();
            tabbedComponent.clickOnInPlayTab();
            inPlaySection.load();
        } else {
            inPlaySection.load();
            highlightsSection.load();
        }
    }

    @Override
    public boolean isDisplayed(){
        return isElementDisplayed(FEATURED_SECTION) && getCurrentPageName().equals("highlights");
    }

    @Override
    public String getMarketBlurbInEvent(final String eventId) {
        try {
            find(By.id(eventId)).findElement(MARKET_BLURB);
        } catch (TimeoutException ex) {
            return "";
        }
        return find(By.id(eventId)).findElement(MARKET_BLURB).getText();
    }
}
