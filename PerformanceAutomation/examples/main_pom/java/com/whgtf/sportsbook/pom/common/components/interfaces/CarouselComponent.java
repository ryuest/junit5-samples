package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.components.impl.InPlaySectionImpl;
import com.whgtf.sportsbook.pom.common.components.impl.MeetingsNavigationComponentImpl;
import com.whgtf.sportsbook.pom.common.pages.interfaces.*;

import java.util.List;


public interface CarouselComponent extends AbstractCommonInterface {

    /**
     * check if the carousel is displayed
     * @return true or false
     */
    boolean isDisplayed();

    MeetingsNavigationComponentImpl clickOnMeetingsIcon();

    MeetingsNavigationComponentImpl clickOnAntePostIcon();

    TopBetsPage clickOnTopBetsIcon();

    SpecialRacingPage clickOnSpecialIcon();

    CompetitionsPage clickOnCompetitionsIcon();

    InPlaySectionImpl clickOnInPlaySection();

    CouponsPage clickOnCouponsIcon();

    DailyListPage clickOnDailyListIcon();

    void clickOnGivenCarouselElement(String elementName);

    /**
     * On this method we will click on the given element if its displayed on the Carousel.
     * @param elementName - The element name (complete name) on the carousel that we want to click.
     */
    void clickOnGivenAllInPlayCarouselElement(String elementName);

    boolean highlightedOptionInVirtualWorldCarousel(String option);

    List<String> getVirtualWorldCarouselSportNames();

    List<String> getExpectedVirtualSportNames();

    boolean isMeetingsTabSelected();

    boolean isNextOffTabSelected();

    void clickOnNextOffIcon();

    boolean isTopBetIconDisplayed();

    List<String> getIconsText();

    /**
     * This method will return us the current highlighted option on the Carousel.
     * @return - Name of the selected option.
     */
    String highlightedOptionInAllInPlayCarousel();

    /**
     * This will return how many Highlighted Options are on the Carousel.
     * Note: Should always return one, since can be only one highlighted.
     * @return - Number of highlighted options on the carousel.
     */
    int amountOfHighlightedOptionsInAllInPlayCarousel();

}
