package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.AccountTabComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class AccountTabComponentImpl extends AbstractCommonObject implements AccountTabComponent {

    private static final By ACCOUNT_DIV = By.id("logged");

    private static final By ACCOUNT_BUTTON = By.cssSelector(".account-tab__button");

    private static final By LOG_OUT_BUTTON = By.id("logoutLink");

    private static final By OPEN_BETS_BUTTON = By.id("openBetsLink");

    private static final By DEPOSIT_BUTTON = By.id("depositLink");

    private static final By WITHDRAW_BUTTON = By.id("withdrawLink");

    private static final By MY_ACCOUNT_BUTTON = By.id("statementsLink");

    private static final By FREE_BETS_BONUSES_BUTTON = By.id("freeBetsLink");

    private static final By GAMBLING_CONTROLS_BUTTON = By.id("gcHubLink");

    private static final By SETTLED_BETS_BUTTON = By.id("settledBetsLink");

    private static final By BALANCE_TRANSFER_BUTTON = By.id("balanceTransferLink");

    private static final By REVERSE_WITHDRAWAL_BUTTON = By.id("reverseWithdrawalLink");

    private static final By PREFERENCES_BUTTON = By.id("preferencesLink");

    private static final By MESSAGES_BUTTON = By.id("messagesLink");


    @Override
    public void openAccounTab() {
        if(!isAccountSectionOpen()) {
            click(ACCOUNT_BUTTON);
            sleep(1000);

        }

    }

    @Override
    public void clickOnLogOut() {
        openAccounTab();
        click(LOG_OUT_BUTTON);
        waitElementNotToBeVisible(LOG_OUT_BUTTON,10);
    }

    @Override
    public boolean isAccountSectionOpen() {
        return find(ACCOUNT_DIV).isDisplayed();
    }

    @Override
    public void clickOnOpenBets() {
        click(OPEN_BETS_BUTTON);
    }

    @Override
    public void clickOnDeposit() {
        click(DEPOSIT_BUTTON);
    }

    @Override
    public void clickOnWithdraw() {
        click(WITHDRAW_BUTTON);

    }

    @Override
    public void clickOnMyAccount() {
        click(MY_ACCOUNT_BUTTON);

    }

    @Override
    public void clickOnFreeBetsBonuses() {
       click(FREE_BETS_BONUSES_BUTTON);

    }

    @Override
    public void clickOnGamblingControls() {
        click(GAMBLING_CONTROLS_BUTTON);
    }

    @Override
    public void clickOnSettledBets() {
        click(SETTLED_BETS_BUTTON);
    }

    @Override
    public void clickOnBalanceTransfer() {
        click(BALANCE_TRANSFER_BUTTON);
    }

    @Override
    public void clickOnReverseWithdrawal() {
        click(REVERSE_WITHDRAWAL_BUTTON);
    }

    @Override
    public void clickOnPreferences() {
        click(PREFERENCES_BUTTON);
    }

    @Override
    public void clickOnMessages() {
        click(MESSAGES_BUTTON);
    }
}
