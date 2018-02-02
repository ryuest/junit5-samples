package com.whgtf.sportsbook.pom.common.pages.interfaces;


import com.whgtf.sportsbook.pom.common.components.interfaces.HighlightsSection;
import com.whgtf.sportsbook.pom.common.components.interfaces.InPlaySection;


public interface HomePage extends AbstractSportsPage {

    void open();

    void openLanguage(final String language);

    InPlaySection getInPlaySection();

    HighlightsSection getHighlightsSection();

    void load();

    boolean isDisplayedInLanguage(String language);

    void setPrices(final String type);

    boolean isPriceHighlighted(final String type);

    void clickOnGivenCarouselElement(String input);

    boolean isOddFormatDisplayed();

}
