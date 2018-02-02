package com.whgtf.sportsbook.pom.common.pages.interfaces;

import java.util.List;


public interface TopBetsPage extends AbstractSportsPage {

    AbstractSportsPage clickOnSelection(int position);

    AbstractSportsPage clickOnFirstSelection();

    void clickOnShowMoreLink();

    boolean isPageDisplayed();

    void load();

    List<String> getTopBetEventsSportName();



    List<String> getListOfEventNames();

    void clickOnTopBetSelectionByIndex(int index);

}
