package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.BetSlipComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.MobileKeyPadComponent;
import com.whgtf.sportsbook.pom.common.exceptions.KnownIssueException;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.mobile.components.impl.NativeElementsComponentImpl;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeElementsComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


/**
 * <p>
 * <img src="doc-files/betslipComponent.png" alt="Example of the application GUI">
 * </p>
 */

@Component
@Lazy
public class BetSlipComponentImpl extends AbstractCommonObject implements BetSlipComponent {

    // Betslip
    private static final By BETSLIP_WRAPPER = By.id("betslipwrapper");

    private static final By BETSLIP_SECTION = By.id("betslip-section");

    private static final By OPEN_BETSLIP_BUTTON = By.id("open-betslip");


    // Betslip Navigation
    private static final By BET_SLIP_TAB = By.id("betslip-tab");

    private static final By OPEN_BET_TAB = By.id("openbets-tab");


    // Betslip Header
    private static final By BETSLIP_HEADER = By.id("betslip-header");

    private static final By CLOSE_BETSLIP = By.cssSelector("#betslip-header .toolbar-button");


    // Betslip Header (Post Bet)
    private static final By PLACED_BET_MESSAGE = By.id("receipt-notice-box");

    private static final By REUSE_SELECTIONS = By.cssSelector(".betslip-receipt__options-container  .o-btn--hollow");

    private static final By CONTINUE_BETTING = By.cssSelector(".betslip-receipt__options-container  #continue-button");


    // Betslip Content
    private static final By BETSLIP_CONTENT = By.id("betslip-content");


    // Single Bet Types
    private static final By SINGLES_BETS_SECTION = By.id("bets-container-singles");

    private static final By SINGLE_BET = By.cssSelector(".betslip-selection");

    private static final By SINGLE_SELECTION_ID = By.id("contains(@id,'single-bet')");

    private static final By SINGLE_SELECTION_NAME = By.cssSelector(".betslip-selection__name label");

    private static final By SINGLE_SELECTION_PRICE = By.cssSelector(".betslip-selection__price");

    private static final By STAKE_FIELD = By.cssSelector("input.betslip-selection__stake-input");

    private static final By REMOVE_BET = By.className("betslip-selection__remove-bet");

    private static final String BET_BY_ID = "//div[@id='bets-container-singles']/div[@id='single-bet_%sL']";

    private static final By SINGLE_EACH_WAY_BOX = By.cssSelector(".betslip-selection__stake-container [id^='bet-ew'");

    private static final By SINGLE_TAKE_PRICE_BUTTON = By.cssSelector(".slip-name [data-ng-show*='take_price'");


    // Multiple Bet Types
    private static final By MULTIPLES_BETS_SECTION = By.id("bets-container-multiples");

    private static final By BET_HEADER = By.className("bet-header");

    private static final By DOUBLE_BET = By.id("multiple-type-dbl");

    private static final By TREBLE_BET = By.id("multiple-type-tbl");

    private static final By TRIXIE_BET = By.id("multiple-type-trx");

    private static final By PATENT_BET = By.id("multiple-type-pat");

    private static final By YANKEE_BET = By.id("multiple-type-yan");

    private static final By LUCKY15_BET = By.id("multiple-type-l15");

    private static final By FOUR_FOLD_BET = By.id("multiple-type-acc4");

    private static final By FIVE_FOLD_BET = By.id("multiple-type-acc5");

    private static final By SIX_FOLD_BET = By.id("multiple-type-acc6");


    // Cast Bet Types
    private static final By CASTS_BETS_SECTION = By.id("bets-container-casts");

    private static final By STRAIGHT_FORECAST_BET = By.id("cast-bet_0SF");

    private static final By REVERSE_FORECAST_BET = By.id("cast-bet_0RF");

    private static final By STRAIGHT_TRICAST_BET = By.id("cast-bet_0TC");

    private static final By COMBINATION_FORECAST_BET = By.id("cast-bet_0CF"); // stake-input__0CF

    private static final By COMBINATION_TRICAST_BET = By.id("cast-bet_0CF");


    // Perm Cast Bet Types
    private static final By PERM_CASTS_BETS_SECTION = By.id("bets-container-cast-combis");

    private static final By STRAIGHT_FORECAST_DOUBLE_BET = By.id("cast-combi_SF|DBL");

    private static final By REVERSE_FORECAST_DOUBLE_BET = By.id("cast-combi_RF|DBL");

    private static final By COMBINATION_FORECAST_DOUBLE_BET = By.id("cast-combi_CF|DBL");


    // Betslip Receipt
    private static final By BETSLIP_RECEIPT = By.id("bet-receipt");

    private static final By BETSLIP_RECEIPT_DROPDOWN = By.cssSelector("#bet-receipt .betslip-header");

    private static final By BETSLIP_RECEIPT_CONTENT = By.cssSelector("#bet-receipt .betslip-receipt__details");

    private static final By RECEIPT_CONTENT_REF = By.className("betslip-receipt-transaction__reference");

    private static final By RECEIPT_COTNENT_SELECTION_NAME = By.cssSelector(".betslip-receipt-selection [id^=selection-name]");

    private static final By RECEIPT_CONTENT_SELECTION_PRICE = By.cssSelector(".betslip-receipt-selection [data-ng-show*=takePrice]");

    private static final By RECEIPT_CONTENT_SELECTION_STAKE = By.cssSelector(".betslip-receipt-returns .u-bold");

    private static final By RECEIPT_CONTENT_SELECTION_RETURNS = By.cssSelector(".betslip-receipt-returns .betslip-receipt-returns__amount");


    // Betslip Footer
    private static final By BETSLIP_FOOTER = By.id("betslip-footer");

    private static final By PLACE_BET_BUTTON = By.id("place-bet-button");

    private static final By TOTAL_STAKE = By.cssSelector(".betslip-footer__total-stake-price-container #total-stake-price");

    private static final By BETTING_RULES = By.id("betslip-footer-rules");

    private static final By BETTING_RULES_LINK = By.cssSelector("#betslip-footer-rules a");

    private static final By CLEAR_SLIP = By.cssSelector(".betslip-selection__remove-bet");

    private static final By NEED_HELP_LINK = By.cssSelector(".betslip-footer__sub .betslip-sub-menu li:nth-child(1) a");

    private static final By BET_TYPES_LINK = By.cssSelector(".betslip-footer__sub .betslip-sub-menu li:nth-child(2) a");

    private static final By CUSTOMER_SERVICES_LINK = By.cssSelector(".betslip-footer__sub .betslip-sub-menu li:nth-child(3) a");


    // Other Crap
    private static final By ACCUMULATOR_BOX = By.cssSelector("");

    private static final By ACCUMULATOR_BET_TEXT = By.cssSelector("");

    @Autowired
    @Lazy
    private MobileKeyPadComponent keyPad;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccumulatorDisplayed() {
        return find(ACCUMULATOR_BOX).isDisplayed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAccumulatorText() {
        return find(ACCUMULATOR_BET_TEXT).getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOpen() {
        return find(BETSLIP_HEADER).isDisplayed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeBetSlip() {
        if (isOpen())
            find(BETSLIP_SECTION).findElement(CLOSE_BETSLIP).click();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openBetSlip() {
        if (!isOpen()) {
            find(OPEN_BETSLIP_BUTTON).click();
            waitElementToBeVisible(BETSLIP_SECTION, 10);
        }
    }

    private void expandAccumulatorIfNeeded(By accumulatorSelector) {
        try {
            if (accumulatorSelector == STRAIGHT_FORECAST_BET) {
                if (!find(accumulatorSelector).getAttribute("class").contains("bet-expanded"))
                    click(find(STRAIGHT_FORECAST_BET).findElement(BET_HEADER));
            } else if (!find(accumulatorSelector).getAttribute("class").contains("bet-expanded"))
                click(find(accumulatorSelector).findElement(By.tagName("header")));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBetsAdded() {
        if (!isOpen())
            openBetSlip();
        if (findElements(SINGLES_BETS_SECTION).size() != 0)
            return find(SINGLES_BETS_SECTION).findElements(SINGLE_BET).size();
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeTotheFirstBet(final String stake) {
        openBetSlip();
        clickOnFirstBetStakeInput();
        if (isNativeMobileApp()) {
            NativeElementsComponent nativeElementsComponent = new NativeElementsComponentImpl();
            nativeElementsComponent.typeStakeInNativeKeypad(stake);
            nativeElementsComponent.clickOkInNativeKeypad();
        } else if (keyPad.isDisplayed()) {
            keyPad.typeStake(stake);
        } else {
            find(SINGLES_BETS_SECTION).findElement(STAKE_FIELD).sendKeys(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeToBet(final String stake, final String bet) {
        openBetSlip();
        find(By.xpath(String.format(BET_BY_ID, bet))).findElement(STAKE_FIELD).sendKeys(stake);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeDouble(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(DOUBLE_BET);
        if (!isMobile()) {
            find(DOUBLE_BET).findElement(STAKE_FIELD).sendKeys(stake);
        } else {
            click(find(DOUBLE_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStakeFieldDisplayed() {
        return isElementDisplayed(STAKE_FIELD);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeTreble(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(TREBLE_BET);
        if (!isMobile())
            find(TREBLE_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(TREBLE_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeTrixie(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(TRIXIE_BET);
        if (!isMobile())
            find(TRIXIE_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(TRIXIE_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakePatent(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(PATENT_BET);
        if (!isMobile())
            find(PATENT_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(PATENT_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeYankee(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(YANKEE_BET);
        if (!isMobile())
            find(YANKEE_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(YANKEE_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeLucky15(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(LUCKY15_BET);
        if (!isMobile())
            find(LUCKY15_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(LUCKY15_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeStraightForecast(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(STRAIGHT_FORECAST_BET);
        if (!isMobile())
            find(STRAIGHT_FORECAST_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(STRAIGHT_FORECAST_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeReverseForecast(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(REVERSE_FORECAST_BET);
        if (!isMobile())
            find(REVERSE_FORECAST_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(REVERSE_FORECAST_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeStraightTricast(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(STRAIGHT_TRICAST_BET);
        if (!isMobile())
            find(STRAIGHT_TRICAST_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(STRAIGHT_TRICAST_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeCombinationForecast(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(COMBINATION_FORECAST_BET);
        if (!isMobile())
            find(COMBINATION_FORECAST_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(COMBINATION_FORECAST_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeCombinationTricast(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(COMBINATION_TRICAST_BET);
        if (!isMobile())
            find(COMBINATION_TRICAST_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(COMBINATION_TRICAST_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeStraightForecastDouble(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(STRAIGHT_FORECAST_DOUBLE_BET);
        if (!isMobile())
            find(STRAIGHT_FORECAST_DOUBLE_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(STRAIGHT_FORECAST_DOUBLE_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeReverseForecastDouble(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(REVERSE_FORECAST_DOUBLE_BET);
        if (!isMobile())
            find(REVERSE_FORECAST_DOUBLE_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(REVERSE_FORECAST_DOUBLE_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeCombinationForecastDouble(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(COMBINATION_FORECAST_DOUBLE_BET);
        if (!isMobile())
            find(COMBINATION_FORECAST_DOUBLE_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(COMBINATION_FORECAST_DOUBLE_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStakeFourFold(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(FOUR_FOLD_BET);
        if (!isMobile())
            find(FOUR_FOLD_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(FOUR_FOLD_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    @Override
    public void addStakeFiveFold(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(FIVE_FOLD_BET);
        if (!isMobile())
            find(FIVE_FOLD_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(FIVE_FOLD_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    @Override
    public void addStakeSixFold(String stake) {
        openBetSlip();
        expandAccumulatorIfNeeded(SIX_FOLD_BET);
        if (!isMobile())
            find(SIX_FOLD_BET).findElement(STAKE_FIELD).sendKeys(stake);
        else {
            click(find(SIX_FOLD_BET).findElement(STAKE_FIELD));
            keyPad.typeStake(stake);
        }
    }

    @Override
    public void clickOnPlaceBet() {
        click(PLACE_BET_BUTTON);
        waitElementToBeVisible(PLACED_BET_MESSAGE, 20);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnClearBetSlip() {
        click(find(CLEAR_SLIP));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearBetFromBetSlip(String bet) {
        click(find(By.xpath(String.format(BET_BY_ID, bet))).findElement(REMOVE_BET));
    }

    @Override
    public void clickOnOpenBetTab() {
        find(OPEN_BET_TAB).click();
    }

    @Override
    public void clickOnBetSlipTab() {
        find(BET_SLIP_TAB).click();
    }

    @Override
    public boolean isSelectionAdded(String selection) {
        selection = selection.replace("OB_OU", "");
        return find(By.xpath(String.format(BET_BY_ID, selection))).isDisplayed();
    }

    @Override
    public void clickOnFirstBetStakeInput() {
        click(find(SINGLES_BETS_SECTION).findElement(STAKE_FIELD));
    }

    @Override
    public boolean isDisplayed() {
        return isElementDisplayed(BETSLIP_SECTION);
    }

    @Override
    public boolean isOpenBetslipButtonDisplayed() {
        return isElementDisplayed(OPEN_BETSLIP_BUTTON);
    }

    public String getTotalStake() {
        return find(TOTAL_STAKE).getText();
    }

    public String getBetPlacementConfirmationText() {
        return find(BETSLIP_HEADER).findElement(PLACED_BET_MESSAGE).getText();
    }

    public String getReuseSelectionsText() {
        return find(BETSLIP_CONTENT).findElement(REUSE_SELECTIONS).getText();
    }

    public String getContinueBettingText() {
        return find(BETSLIP_CONTENT).findElement(CONTINUE_BETTING).getText();
    }

    @Override
    public void clickOnReuseSelectionsButton() {
        click(find(BETSLIP_CONTENT).findElement(REUSE_SELECTIONS));
    }

    @Override
    public void clickOnContinueBettingButton() {
        click(find(BETSLIP_CONTENT).findElement(CONTINUE_BETTING));
    }

    public String getSelectionId() {
        return find(SINGLES_BETS_SECTION).findElement(SINGLE_BET).getAttribute("id");
    }

    public String getSelectionLabel() {
        return find(SINGLES_BETS_SECTION).findElement(SINGLE_SELECTION_NAME).getText();
    }

    public String getSelectionPrice() {
        return find(SINGLES_BETS_SECTION).findElement(SINGLE_SELECTION_PRICE).getText();
    }

    public String getBetReceiptStatus() {
        return find(BETSLIP_RECEIPT).findElement(BETSLIP_RECEIPT_CONTENT).getAttribute("class");
    }

    public String getBetReceiptDropdownText() {
        return find(BETSLIP_RECEIPT).findElement(BETSLIP_RECEIPT_DROPDOWN).getText();
    }

    public void clickOnBetReceiptDropdown() {
        click(find(BETSLIP_RECEIPT).findElement(BETSLIP_RECEIPT_DROPDOWN));
    }

    public String getBetReceiptReference() {
        return find(BETSLIP_RECEIPT_CONTENT).findElement(RECEIPT_CONTENT_REF).getText();
    }

    public String getBetReceiptSelectionName() {
        return find(BETSLIP_RECEIPT_CONTENT).findElement(RECEIPT_COTNENT_SELECTION_NAME).getText();
    }

    public String getBetReceiptSelectionPrice() {
        return find(BETSLIP_RECEIPT_CONTENT).findElement(RECEIPT_CONTENT_SELECTION_PRICE).getText();
    }

    public String getBetReceiptTotalStake() {
        return find(BETSLIP_FOOTER).findElement(TOTAL_STAKE).getText();
    }

    /**
     * {@inheritDoc}
     */
    public void clickOnEachWayCheckbox() {
        click(find(SINGLES_BETS_SECTION).findElement(SINGLE_EACH_WAY_BOX));
    }

    /**
     * {@inheritDoc}
     */
    public void clickOnTakePriceTakeSpButton() {
        click(find(SINGLES_BETS_SECTION).findElement(SINGLE_TAKE_PRICE_BUTTON));
    }

    /**
     * {@inheritDoc}
     */
    public String getBettingRulesText() {
        return find(BETSLIP_FOOTER).findElement(BETTING_RULES).getText();
    }

    /**
     * {@inheritDoc}
     */
    public String getBettingRulesLink() {
        return find(BETSLIP_FOOTER).findElement(BETTING_RULES_LINK).getAttribute("href");
    }

    /**
     * {@inheritDoc}
     */
    public String getNeedHelpText() {
        return find (BETSLIP_FOOTER).findElement(NEED_HELP_LINK).getText();
    }

    /**
     * {@inheritDoc}
     */
    public String getNeedHelpLink() {
        return find (BETSLIP_FOOTER).findElement(NEED_HELP_LINK).getAttribute("href");
    }

    /**
     * {@inheritDoc}
     */
    public String getBetTypesText() {
        return find (BETSLIP_FOOTER).findElement(BET_TYPES_LINK).getText();
    }

    /**
     * {@inheritDoc}
     */
    public String getBetTypesLink() {
        return find (BETSLIP_FOOTER).findElement(BET_TYPES_LINK).getAttribute("href");
    }

    /**
     * {@inheritDoc}
     */
    public String getCustomerServicesText() {
        return find (BETSLIP_FOOTER).findElement(CUSTOMER_SERVICES_LINK).getText();
    }

    /**
     * {@inheritDoc}
     */
    public String getCustomerServicesLink() {
        return find (BETSLIP_FOOTER).findElement(CUSTOMER_SERVICES_LINK).getAttribute("href");
    }



}