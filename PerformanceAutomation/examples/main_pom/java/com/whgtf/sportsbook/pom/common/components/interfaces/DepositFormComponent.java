package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface DepositFormComponent extends AbstractCommonInterface {

    void closeWindow();

    void setDebitCardNumber(String debitCardNumber);

    void setDebitCardHolderName(String holderName);

    void setDebitCardExpireMonth(String month);

    void setDebitCardExpireYear(String year);

    void setSecurityCode(String securityCode);

    void setAmount(String amount);

    void clickSecurityCheckbox();

    void clickDeposit();

    void clickOnBackArrow();

    void waitForDepositToLoad();

    void clickOnContinuePlaying();

    void clickOnDebitCard();

    void clickOnCreditCard();

    void clickOnPayPal();

    void clickOnWilliamHillCashdirect();

    void clickOnPingit();

    void clickOnSkrill1tap();

    void clickOnSkrill();

    void clickOnNeteller();

    void clickOnPaysafeCard();

    void clickOnEntroPay();

    void clickOnKalibra();

    void clickOnFastBankTransfer();

    void clickOnBankTransfer();

    void clickOnCheque();

    boolean isDisplayed();


}
