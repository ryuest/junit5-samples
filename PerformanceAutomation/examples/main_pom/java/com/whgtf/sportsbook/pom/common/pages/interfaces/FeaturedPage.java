package com.whgtf.sportsbook.pom.common.pages.interfaces;


import com.whgtf.sportsbook.pom.common.components.impl.InPlaySectionImpl;
import com.whgtf.sportsbook.pom.common.components.impl.HighlightsSectionImpl;


public interface FeaturedPage extends AbstractSportsPage {


    void open();

    InPlaySectionImpl getInPlaySection();

    HighlightsSectionImpl getHighlightsSection();

    void load();

    boolean isDisplayedInLanguage(String language);

    String getMarketBlurbInEvent(final String eventId);

}
