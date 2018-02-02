package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;
import com.whgtf.sportsbook.pom.common.pages.interfaces.RacingEventPage;


public interface LeftFutureRacesNavigationComponent extends AbstractCommonInterface {

    RacingEventPage clickOnTheFirstAllRaces();

    void clickOnAllRacesButtonByCompetition(final String competitionId);

    void clickOnMeetingMarketsButtonByCompetition(final String competitionId);

    void clickOnFirstRaceMeetingSelection();

    boolean isDisplayed();

    void clickOnMeetingMarketsSelection();

}
