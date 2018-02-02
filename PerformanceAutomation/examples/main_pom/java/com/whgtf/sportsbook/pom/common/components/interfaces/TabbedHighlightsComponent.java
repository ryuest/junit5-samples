package com.whgtf.sportsbook.pom.common.components.interfaces;


import com.whgtf.sportsbook.pom.common.components.impl.HighlightsSectionImpl;
import com.whgtf.sportsbook.pom.common.components.impl.InPlaySectionImpl;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;
import com.whgtf.sportsbook.pom.common.pages.interfaces.CouponsPage;
import com.whgtf.sportsbook.pom.common.pages.interfaces.TopBetsPage;

import java.util.List;


public interface TabbedHighlightsComponent extends AbstractCommonInterface {

    boolean isDisplayed();

    InPlaySectionImpl clickOnInPlayTab();

    boolean isInPlayTabDisplayed();

    HighlightsSectionImpl clickOnHighlightsTab();

    boolean isHighlightsTabDisplayed();

    TopBetsPage clickOnTopBetsTab();

    List<String> getListOfTabs();

    CouponsPage clickOnCouponsTab();


}
