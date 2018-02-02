package com.whgtf.sportsbook.pom.common.pages.interfaces;


public interface VirtualsPage extends AbstractSportsPage {

    boolean isPreloaderDisplayed();

    boolean isVirtualsTitleDisplayed();

    boolean isVirtualWorldHeaderDisplayed();

    boolean areRacesDisplayed();

    boolean isMarketSectionDisplayed();

    boolean isMarketSectionHeaderDisplayed();

    boolean areFootballMarketsDisplayed();

    boolean areEventsDisplayed();

    boolean areSelectionsDisplayed();

    boolean isContentVideoDisplayed();

    boolean isVirtualWorldRulesLinkDisplayed();

    boolean areMeetingMarketsDisplayed();

    boolean areRaceMeetingsDisplayed();


    boolean highlightedOptionInSectionsMenu(String option);

    boolean areSectionOptionsDisplayedOnDesktop();

    void clickVirtualWorldHeader();

    void clickOnHorseRacingFlat();

    void clickOnHorseRacingNationalHunt();

    void clickOnFootball();

    void clickOnGreyhoundsFlat();

    void clickOnGreyhoundsHurdles();

    void clickOnMotorRacing();

    void clickOnSpeedway();

    void clickOnCycling();

    void expandGivenEventByIndex(int number);

    void clickOnNextOffRaces();

    void clickOnAllMeetings();


    void clickOnFirstAvailableSelection();

    void areFootballMarketsDisplayed(int index);
}
