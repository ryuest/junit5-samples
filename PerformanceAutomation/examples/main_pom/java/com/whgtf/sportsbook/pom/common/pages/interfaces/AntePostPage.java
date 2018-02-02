package com.whgtf.sportsbook.pom.common.pages.interfaces;

import com.whgtf.sportsbook.pom.common.components.impl.LeftFutureRacesNavigationComponentImpl;


public interface AntePostPage extends RacingEventPage {

    LeftFutureRacesNavigationComponentImpl getFutureRaceComponent();

    boolean antePostHeaderDisplay();

    boolean isRaceItemsDisplayedInGroupNavigation();

}
