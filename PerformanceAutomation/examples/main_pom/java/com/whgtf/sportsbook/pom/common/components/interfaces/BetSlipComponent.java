package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface BetSlipComponent extends AbstractCommonInterface {

    /**
     * Return if the betslip component is displayed or not
     * @return a boolean value indicating if the betslip is displayed or not
     */
    boolean isOpen();

    /**
     * Close the betslip if that one is open (click on the top cross)
     */
    void closeBetSlip();

    /**
     * Open the betslip if is not open yet
     */
    void openBetSlip();

    /**
     * Return the number of bets added to the betslip
     * @return an integer indicating the bet number
     */
    int getBetsAdded();

    /**
     * Add stake to the first bet added in the betslip
     * @param stake the amount for the bet
     */
    void addStakeTotheFirstBet(final String stake);

    /**
     * Add stake to the bet specified as parameter
     * @param stake the amount to add
     * @param bet the bet where the amount will be added to
     */
    void addStakeToBet(final String stake, final String bet);

    /**
     * Add stake to the double bet added in the betslip. Before this, at least two bets should be added to the betslip.
     * @param stake the amount to bet
     */
    void addStakeDouble(final String stake);

    /**
     * Checks if the Stake field is displayed in the betslip. Before this, selection should be added to the betslip.
     * @return true if the stake field is displayed
     */
    boolean isStakeFieldDisplayed();

    /**
     * Add stake to the Treble bet added in the betslip.
     * @param stake the amount to bet
     */
    void addStakeTreble(final String stake);

    /**
     * Add stake to the Trixie bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeTrixie(final String stake);

    /**
     * Add stake to the Patent bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakePatent(final String stake);

    /**
     * Add stake to the Yankee bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeYankee(final String stake);

    /**
     * Add stake to the Lucki15 bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeLucky15(final String stake);

    void clickOnPlaceBet();

    /**
     * Add stake to the StraightForecast bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeStraightForecast(final String stake);

    /**
     * Add stake to the ReverseForecast bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeReverseForecast(final String stake);

    /**
     * Add stake to the StraightTricast bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeStraightTricast(final String stake);

    /**
     * Add stake to the CombinationForecast bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeCombinationForecast(final String stake);

    /**
     * Add stake to the CombinationTricast bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeCombinationTricast(final String stake);

    /**
     * Add stake to the StraightForecast bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeStraightForecastDouble(final String stake);

    /**
     * Add stake to the ReverseForecast bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeReverseForecastDouble(final String stake);

    /**
     * Click on the "Place bet" button in the betslip
     * @param stake the amount to bet
     */
    void addStakeFourFold (final String stake);

    void addStakeFiveFold (final String stake);

    void addStakeSixFold (final String stake);

    /**
     * Add stake to the CombinationForecast bet added to the betslip
     * @param stake the amount to bet
     */
    void addStakeCombinationForecastDouble(final String stake);

    /**
     * Remove all the selections added to the betslip
     */
    void clickOnClearBetSlip();

    /**
     * Remove from the betslip an specific bet specified by the bet Id
     * @param bet the bet Id to remove
     */
    void clearBetFromBetSlip(final String bet);

    void clickOnOpenBetTab();

    void clickOnBetSlipTab();

    boolean isSelectionAdded(final String selection);

    /**
     * Return whether the accumulator is displayed
     * @return true or false according to accumulator is displayed or not
     */
    boolean isAccumulatorDisplayed();

    /**
     * Return the accumulator text
     * @return the text in the accumulator component
     */
    String getAccumulatorText();

    void clickOnFirstBetStakeInput();

    boolean isDisplayed();

    boolean isOpenBetslipButtonDisplayed();

    String getTotalStake();

    String getBetPlacementConfirmationText();

    String getReuseSelectionsText();

    String getContinueBettingText();

    void clickOnReuseSelectionsButton();

    void clickOnContinueBettingButton();

    String getSelectionLabel();

    String getSelectionPrice();

    String getSelectionId();

    String getBetReceiptStatus();

    String getBetReceiptDropdownText();

    void clickOnBetReceiptDropdown();

    String getBetReceiptReference();

    String getBetReceiptSelectionName();

    String getBetReceiptSelectionPrice();

    String getBetReceiptTotalStake();

    /**
     * Click on the Each Way box for single selections
     */
    void clickOnEachWayCheckbox();

    /**
     * Click on the Take SP/Take Price button for racing
     */
    void clickOnTakePriceTakeSpButton();

    /**
     * Return the text in the William Hill Betting Rules
     *
     * @return String with the rules text
     */
    String getBettingRulesText();

    /**
     * Return the href attribute within the William Hill Betting Rules
     *
     * @return String with the rule link
     */
    String getBettingRulesLink();


    /**
     * Return the text in the Need Help link
     */
    String getNeedHelpText();

    /**
     * Return the href attribute within the Need Help link
     */
    String getNeedHelpLink();

    /**
     * Return the text in the Bet Types Explained link
     */
    String getBetTypesText();

    /**
     * Return the href attribute within the Bet Types Explained link
     */
    String getBetTypesLink();

    /**
     * Return the text in the Contact Customer Services link
     */
    String getCustomerServicesText();

    /**
     * Return the href attribute within the Contact Customer Services link
     */
    String getCustomerServicesLink();


}
