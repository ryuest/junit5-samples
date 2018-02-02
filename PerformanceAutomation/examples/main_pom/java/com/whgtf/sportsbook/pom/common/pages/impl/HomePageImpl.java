package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.HighlightsSection;
import com.whgtf.sportsbook.pom.common.components.interfaces.InPlaySection;
import com.whgtf.sportsbook.pom.common.components.interfaces.TabbedHighlightsComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.HomePage;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;



@Component
@Lazy
public class HomePageImpl extends AbstractSportsPageObject implements HomePage {


    private static final String FRACTIONAL = "fractional";

    private static final String DECIMAL = "decimal";

    private static final String AMERICAN = "american";

    private static final By HOME_SECTION = By.className("home");

    private static final By PRICES_FIELD = By.id("odds-format-filter");

    private static final String ODDS_OPTION = "button[data-odds-format='%s']";

    private static final By FRACTION_TYPE = By.cssSelector(String.format(ODDS_OPTION,"fraction"));

    private static final By DECIMAL_TYPE = By.cssSelector(String.format(ODDS_OPTION,"decimal"));

    private static final By AMERICAN_TYPE = By.cssSelector(String.format(ODDS_OPTION,"american"));

    private static final By CAROUSEL = By.cssSelector("#carousel");

    private static final String CAROUSEL_ELEMENTS = "//nav[@id='carousel']//li[contains(.,'%s')]";


    @Autowired
    @Lazy
    private InPlaySection inPlaySection;

    @Autowired
    @Lazy
    private HighlightsSection highlightsSection;

    @Autowired
    @Lazy
    private TabbedHighlightsComponent tabbedComponent;


    public void open() {
        open(BASE_URL);
    }

    public void openLanguage(final String language) {
        open(BASE_URL.replace("en-gb",LANGUAGE_URL_MAP.get(language)));
    }

    @Override
    public InPlaySection getInPlaySection() {
        if(isMobile()){
            tabbedComponent.clickOnInPlayTab();
        }
        return inPlaySection;
    }

    @Override
    public HighlightsSection getHighlightsSection() {
        if(isMobile()){
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
        return find(HOME_SECTION).isDisplayed() && getCurrentPageName().equals("home");
    }

    @Override
    public void setPrices(String type) {
        find(PRICES_FIELD);
        int times=0;
        while(times<3) {
            scrollToTheBottom();
            sleep(1000);
            times++;
        }
        executeJavaScript("window.scrollBy(0,-150)");
        click(PRICES_FIELD);
        if(FRACTIONAL.equalsIgnoreCase(type))
            click(FRACTION_TYPE);
        else if(DECIMAL.equalsIgnoreCase(type))
            click(DECIMAL_TYPE);
        else if(AMERICAN.equalsIgnoreCase(type))
            click(AMERICAN_TYPE);
        else
            throw new NotImplementedException("Option price:" + type + " not implemented");
    }

    @Override
    public boolean isPriceHighlighted(String type) {
        switch (type) {
            case FRACTIONAL:
                return find(FRACTION_TYPE).getAttribute("class").contains("-active");
            case DECIMAL:
                return find(DECIMAL_TYPE).getAttribute("class").contains("-active");
            case AMERICAN:
                return find(AMERICAN_TYPE).getAttribute("class").contains("-active");
            default:
                return false;
        }
    }

    @Override
    public void clickOnGivenCarouselElement(String input){
        click(find(By.xpath(String.format(CAROUSEL_ELEMENTS, input))));
    }

    @Override
    public boolean isOddFormatDisplayed() {
        return isElementDisplayed(PRICES_FIELD);
    }


}
