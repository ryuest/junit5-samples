package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.DepositFormComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class DepositFormComponentImpl extends AbstractCommonObject implements DepositFormComponent {

    private static final By WINDOW_FRAME = By.cssSelector("iframe.mfp-iframe");

    private static final By DEBIT_CARD_NUMBER = By.id("card_no");

    private static final By CARD_HOLDER_NAME = By.id("hldr_name");

    private static final By EXPIRE_DATE_MONTH = By.id("expiry_month");

    private static final By EXPIRE_DATE_YEAR = By.id("expiry_year");

    private static final By SECURITY_CODE = By.id("security_code");

    private static final By AMOUNT = By.id("txnAmount");

    private static final By SECURITY_CHECKBOX = By.id("acceptTerms");

    private static final By DEPOSIT_BUTTON = By.id("submitButton");

    private static final By CLOSE_WINDOW = By.cssSelector("#overlayFunc span");

    private static final By BACK_ARROW_MOBILE = By.cssSelector("#backToMobenga");

    private static final By CONTINUE_PLAYING = By.cssSelector("#backToSiteBtn");

    private static final By DEBIT_CARD_BUTTON = By.id("paymentGROUP_DEBITCARD_CC");

    private static final By CREDIT_CARD_BUTTON = By.id("paymentGROUP_CREDITCARD_CC");

    private static final By PAYPAL_BUTTON = By.id("paymentSINGLE_PPAL_PPAL");

    private static final By WH_CASHDIRECT_BUTTON = By.id("paymentSINGLE_UKSH_UKSH");

    private static final By PINGIT_BUTTON = By.id("paymentSINGLE_BPNG_BPNG");

    private static final By SKRILL_1TAP_BUTTON = By.id("paymentSINGLE_MB1T_MB1T");

    private static final By SKRILL_BUTTON = By.id("paymentSINGLE_MB_MB");

    private static final By NETELLER_BUTTON = By.id("paymentSINGLE_NTLR_NTLR");

    private static final By PAYSAFE_CARD_BUTTON = By.id("paymentSINGLE_PSC_PSC");

    private static final By ENTROPAY_BUTTON = By.id("paymentSINGLE_CC_EP");

    private static final By KALIBRA_BUTTON = By.id("paymentSINGLE_CC_KAPO");

    private static final By FAST_BANK_TRANSFER_BUTTON = By.id("paymentSINGLE_ENVO_ENFBT");

    private static final By BANK_TRANSFER_BUTTON = By.id("paymentSINGLE_BANK_BANK");

    private static final By CHEQUE_BUTTON = By.id("paymentSINGLE_CHQ_CHQ");

    private static final By DEPOSIT_OVERLAY = By.cssSelector(".regOverlay.deposit");

    private static final By PAYMENT_METHODS_MENU = By.id("paymentMethodsMenu");




    @Override
    public void waitForDepositToLoad(){
        switchToiFrame(WINDOW_FRAME);
        waitElementToBeVisible(PAYMENT_METHODS_MENU, 20);
        switchToParentFrame();
    }

    @Override
    public void closeWindow() {
        switchToiFrame(WINDOW_FRAME);
        find(CLOSE_WINDOW).click();
        switchToParentFrame();
    }

    @Override
    public void setDebitCardNumber(String debitCardNumber) {
        switchToiFrame(WINDOW_FRAME);
        find(DEBIT_CARD_NUMBER).clear();
        find(DEBIT_CARD_NUMBER).sendKeys(debitCardNumber);
        switchToParentFrame();
    }

    @Override
    public void setDebitCardHolderName(String holderName) {
        switchToiFrame(WINDOW_FRAME);
        find(CARD_HOLDER_NAME).clear();
        find(CARD_HOLDER_NAME).sendKeys(holderName);
        switchToParentFrame();
    }

    @Override
    public void setDebitCardExpireMonth(String month) {
        switchToiFrame(WINDOW_FRAME);
        find(EXPIRE_DATE_MONTH).sendKeys(month);
        switchToParentFrame();
    }

    @Override
    public void setDebitCardExpireYear(String year) {
        switchToiFrame(WINDOW_FRAME);
        find(EXPIRE_DATE_YEAR).sendKeys(year);
        switchToParentFrame();
    }

    @Override
    public void setSecurityCode(String securityCode) {
        switchToiFrame(WINDOW_FRAME);
        find(SECURITY_CODE).clear();
        find(SECURITY_CODE).sendKeys(securityCode);
        switchToParentFrame();
    }

    @Override
    public void setAmount(String amount) {
        switchToiFrame(WINDOW_FRAME);
        find(AMOUNT).clear();
        find(AMOUNT).sendKeys(amount);
        switchToParentFrame();
    }

    @Override
    public void clickSecurityCheckbox() {
        switchToiFrame(WINDOW_FRAME);
        find(SECURITY_CHECKBOX).click();
        switchToParentFrame();
    }

    @Override
    public void clickDeposit() {
        switchToiFrame(WINDOW_FRAME);
        find(DEPOSIT_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnBackArrow() {
        switchToiFrame(WINDOW_FRAME);
        find(BACK_ARROW_MOBILE).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnContinuePlaying() {
        switchToiFrame(WINDOW_FRAME);
        find(CONTINUE_PLAYING).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnDebitCard() {
        switchToiFrame(WINDOW_FRAME);
        find(DEBIT_CARD_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnCreditCard() {
        switchToiFrame(WINDOW_FRAME);
        find(CREDIT_CARD_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnPayPal() {
        switchToiFrame(WINDOW_FRAME);
        find(PAYPAL_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnWilliamHillCashdirect() {
        switchToiFrame(WINDOW_FRAME);
        find(WH_CASHDIRECT_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnPingit() {
        switchToiFrame(WINDOW_FRAME);
        find(PINGIT_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnSkrill1tap() {
        switchToiFrame(WINDOW_FRAME);
        find(SKRILL_1TAP_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnSkrill() {
        switchToiFrame(WINDOW_FRAME);
        find(SKRILL_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnNeteller() {
        switchToiFrame(WINDOW_FRAME);
        find(NETELLER_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnPaysafeCard() {
        switchToiFrame(WINDOW_FRAME);
        find(PAYSAFE_CARD_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnEntroPay() {
        switchToiFrame(WINDOW_FRAME);
        find(ENTROPAY_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnKalibra() {
        switchToiFrame(WINDOW_FRAME);
        find(KALIBRA_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnFastBankTransfer() {
        switchToiFrame(WINDOW_FRAME);
        find(FAST_BANK_TRANSFER_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnBankTransfer() {
        switchToiFrame(WINDOW_FRAME);
        find(BANK_TRANSFER_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnCheque() {
        switchToiFrame(WINDOW_FRAME);
        find(CHEQUE_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public boolean isDisplayed() {
        switchToiFrame(WINDOW_FRAME);
        waitElementToBePresent(DEPOSIT_OVERLAY, 20);
        boolean isDisplayed = find(DEPOSIT_OVERLAY).isDisplayed();
        switchToParentFrame();
        return isDisplayed;
    }

}
