package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface BottomBarComponent extends AbstractCommonInterface {

    boolean isDisplayed();

    /**
     * <p>Left it with the Thread.sleep since was the one working, these other methods were not waiting for the
     * Accumulator box to be displayed.</p>
     *
     * {@code waitElementToBePresent(ACCUMULATOR_BOX, 5);
     * wait(2000);}
     *
     * @return true if the accumulator is displayed
     */
    boolean isAccumulatorDisplayed();

    boolean isSportsMenuDisplayed();

    boolean isBetSlipMenuDisplayed();

    boolean isOpenBetsMenuDisplayed();

    boolean isSearchMenuDisplayed();

    boolean isRouletteMenuDisplayed();

    boolean isTopGamesMenuDisplayed();

    /**
     * Reason for this is that the way was implemented before was returning the text that was displayed FIRST on the
     * Accumulator section, problem is, when testing TREBLE (3 selections from different markets) was always returning
     * the first one displayed - the DOUBLE -, which would make the test fail everytime its not DOUBLE.
     *
     * @param input The input to assert
     * @return true if matches
     */
    boolean isAcumulatorTextThis(String input);

    QuickLinksComponent clickOnSportsMenu();

    BetSlipComponent clickOnBetSlipMenu();

    SearchComponent clickOnSearchMenu();

    TopGamesComponent clickOnTopGamesMenu();

}
