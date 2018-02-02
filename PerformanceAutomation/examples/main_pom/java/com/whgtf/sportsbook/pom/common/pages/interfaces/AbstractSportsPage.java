package com.whgtf.sportsbook.pom.common.pages.interfaces;

import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.components.interfaces.*;

import java.util.List;


public interface AbstractSportsPage extends AbstractCommonInterface {

    BottomBarComponent getBottonBar();

    CarouselComponent getCarouselComponent();

    HeaderComponent getHeaderComponent();

    FooterComponent getFooterComponent();

    TopBetsComponent getTopBetsComponent();

    SportsMenuComponent getSportsMenuComponent();

    BetSlipComponent openBetSlip();

    AbstractSportsPage waitToBeLoaded();

    boolean isDisplayedInLanguage(String language);

    List<String> getAllLinksInThePage();

    boolean isWordInTheUrl(final String word);

    void refreshPage();

    String getCurrentPageName();

    boolean isDisplayed();

    String getUrl();

    String getPageTitle();

    boolean isInTheTopPosition();

    /**
     * This method will scroll to the bottom of the current page.
     * Note: This will scroll only the "body".
     */
    void scrollToTheBottom();

    /**
     * This method will scroll to the top of the current page.
     * Note: This will scroll only the "body".
     */
    void scrollToTheTop();

    void scrollToTheBottomOnLazyLoading() throws InterruptedException;

    List<String> getAllSelectionPricesInThePage();

    void changeLatestTab();

    void navigateToUrl(final String url);

    List<String> getHandicapInEvent(String eventId);

    List<Selection> getSelectionsInMarket(final String marketId);

    List<String> getSelectionsPricesInMarket(final String marketId);

    List<String> getUnderOverInEvent(String eventId);

    boolean isEventDisplayed(final String eventId);

    /**
     * This method will refresh the page up to 5 times until just created event will be shown.
     * Note: force sportsbook prioritizer to render event.
     * @param eventId
     */
    void waitEventDisplayed(final String eventId);

    boolean isSelectionInRed(String selectionId);

    boolean isSelectionInGreen(String selectionId);

    boolean isSelectionInBlue(String selectionId);

    boolean isSelectionGrayedOut(final String selectionId);

    String getHeaderPanelTitlePage();

    boolean headerPanelTitlePageIsDisplayed();

    void clickBackButton();

    boolean isBackButtonDisplayed();

    boolean isBackButtonDisplayedForRaceCard();

    String getSecondaryHeaderText();

    /**
     * This method returns True in case the Secondary Header is displayed.
     * @return - True if Secondary header is displayed, false if not.
     */
    boolean isSecondaryHeaderDisplayed();

    boolean areSelectionsInMarketGrayedOut(final String marketId);

    boolean areSelectionsInMarketClickable(String marketId);

    boolean areSelectionsInTheEventOrMarketGrayedOut(final String id);

    boolean areSelectionsInTheEventClickable(String marketId);

    boolean areSelectionsInTheEventOrMarketGrayedOutInBlue(String id);

    Selection clickOnTheFirstAvailableSelection();

    Selection clickOnTheLastAvailableSelection();

    void clickOnTheFirstAvailableSPSelection();

    void clickOnSelectionByIndex(int number);

    void clickOnGivenNumberOfSelections(int number);

    void clickOnlyOnFirstSelectionFromMarkets(int number);

    List<String> getMarketBlurb(final String eventId);

    Event clickOnTheFirstEvent();

    List<Selection> getActiveSelections();

    List<Selection> getActivePreMatchSelections();

    List<Selection> getActiveInPlaySelections();

    void changeTemporaryPassword(final String currentPassword, final String newPassword);

    boolean desktopSportTitleisDisplayed();

    boolean isNativeMobileApp();

    String getPriceFromSelectionId(final String selectionId);

    /**
     * Waits up to 10 seconds for a selection to have the given price. Normally used after a push update.
     *
     * @param selectionId The selection id in the format OB_OUxxx
     * @param expectedPrice The expected price
     * @return a String representing the price.
     */
    String waitSelectionPriceToBe(final String selectionId, final String expectedPrice);

    String getMarketActive(final String type);

    String getEventActive(final String type);

    int getSelectionsSelected();

    Selection getSelectionActiveByType(final String type);

    boolean isSelectionDisplayed(final String selectionId);

    boolean isMarketDisplayed(final String marketId);

    List<Market> getMarketsFromEvent(final String eventId);

    void clickOnEventById(final String eventId);

    List<Selection> getAllSelectionsInThePage();

    boolean isRightSidebarDisplayed();

    boolean isTopBetsDetailsDisplayed();

    List<String> getSelectionsInEvent(final String eventId);

    String getLabelFromSelectionId(final String selectionId);


}
