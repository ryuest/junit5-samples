package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;

import java.util.List;


public interface SportsNavigationComponent extends AbstractCommonInterface {

    /**
     * check if the carousel is displayed
     * @return true or false
     */
    boolean isDisplayed();

    void clickOnSportByPosition(int position);

    List<String> getSportList();


}
