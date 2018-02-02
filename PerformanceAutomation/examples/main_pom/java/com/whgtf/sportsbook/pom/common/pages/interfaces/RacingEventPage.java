package com.whgtf.sportsbook.pom.common.pages.interfaces;


public interface RacingEventPage extends AbstractSportsPage {

    void clickOnFirstSelectionFirstEvent();

    void clickOnFirstSelectionByEvent(String event);

    boolean isDisplayed();

    void load();

}
