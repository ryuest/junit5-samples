package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface AccountTabComponent extends AbstractCommonInterface {


    /**
     * <p>Clicks to expand the account button from the header.</p>
     */
    void openAccounTab();

    void clickOnLogOut();

    boolean isAccountSectionOpen();

    void clickOnOpenBets();

    void clickOnDeposit();

    void clickOnWithdraw();

    void clickOnMyAccount();

    void clickOnFreeBetsBonuses();

    void clickOnGamblingControls();

    void clickOnSettledBets();

    void clickOnBalanceTransfer();

    void clickOnReverseWithdrawal();

    void clickOnPreferences();

    void clickOnMessages();
}
