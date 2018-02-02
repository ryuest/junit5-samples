package com.whgtf.sportsbook.pom.common.pages.interfaces;

import java.util.List;


public interface AllInPlayPage extends AbstractSportsPage {

    boolean isDisplayed(final String event);

    boolean isMainContentDisplayed();

    boolean isLeftMarketCollectionDisplayed();

    void clickOnSportByPosition(int position);

    List<String> getSportList();

    boolean isCarouselDisplayed();

    void clickOnCarouselIconByPosition(final Integer position);

    Integer getCarouselElementsNumber();

}
