package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface TopBetsComponent extends AbstractCommonInterface {

    boolean isDisplayed();

    boolean isEventDisplayed(final String eventId);

    boolean isMarketDisplayed(String marketId);

    void load();

    int getEventListSize();

    void clickOnShowMoreLink();

    /**
     * Clicks on the given selection number from the Top Bets widget.
     *
     * @param number    The number of the selection to click on.
     */
    void clickOnSelectionInTopBetsWidget(int number);


}
