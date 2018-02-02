package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface TopGamesComponent extends AbstractCommonInterface {

    boolean isDisplayed();

    void clickOnEmbeddedGame();

    void clickOnNoEmbeddedGame();

    void clickOnCloseCross();
}
