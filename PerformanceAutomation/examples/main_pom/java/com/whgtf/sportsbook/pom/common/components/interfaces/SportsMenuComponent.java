package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;
import com.whgtf.sportsbook.pom.common.pages.interfaces.InPlayPage;

import java.util.List;


public interface SportsMenuComponent extends AbstractCommonInterface {

    /**
     *  Clicks the sports from the the left menu.
     *
     * @param sport  The sport to select.
     */
    void clickOnSportByName(final String sport);

    /**
     *  Clicks a page from the menu belonging to a sport.
     *
     * @param Sport The sport the page belongs to
     * @param pageName The name of the page to select
     */
    void clickOnSportPageByName(final String Sport, final String pageName);

    /**
     * Clicks the 'Home' option from the menu
     *
     */
    void clickOnHome();

    /**
     *  Clicks the 'Search' option from the menu.
     *
     * @return the {@link SearchComponent} object.
     */
    SearchComponent clickOnSearch();

    /**
     *  Clicks the 'In-Play' option from the menu.
     *
     *  @return the {@link InPlayPage} object.
     */
    InPlayPage clickOnInPlay();

    /**
     *  Gets a list with all the sport names present in the menu.
     *
     * @return a list of strings containing the sport names.
     */
    List<String> getListSports();

    /**
     *  Clicks a sport by its position in the menu.
     *
     * @param position  The position of the sport to click.
     */
    void clickOnSportByPosition(int position);

    /**
     *  Checks if the menu is displayed.
     *
     * @return {@code true} if the menu is displayed
     */
    boolean isDisplayed();

    /**
     *  Checks if the given page is selected for the current active sport.
     *
     * @param pageId The id selector of the page.
     * @return {@code true} if the page is selected.
     */
    boolean isSportPageSelected(String pageId);

    /**
     *  Checks if the 'Daily List' page is selected/active in the menu.
     *
     * @return {@code true} if the page is selected and active.
     */
    boolean isDailyListSelected();

    /**
     *  Checks if the 'In-Play' page is selected/active in the menu.
     *
     * @return {@code true} if the page is selected and active.
     */
    boolean isInPlaySelected();

    /**
     *  Checks if the 'Competitions' page is selected/active in the menu.
     *
     * @return {@code true} if the page is selected and active.
     */
    boolean isCompetitionsSelected();

    /**
     *  Checks if the 'Coupons' page is selected/active in the menu.
     *
     * @return {@code true} if the page is selected and active.
     */
    boolean isCouponsSelected();

    /**
     *  Checks if the 'Future Races' page is selected/active in the menu.
     *
     * @return {@code true} if the page is selected and active.
     */
    boolean isFutureRacesSelected();

    /**
     *  Checks if the 'Specials' page is selected/active in the menu.
     *
     * @return {@code true} if the page is selected and active.
     */
    boolean isSpecialsSelected();

    /**
     *  Checks if the 'Top Bets' page is selected/active in the menu.
     *
     * @return {@code true} if the page is selected and active.
     */
    boolean isTopBetsSelected();




}
