package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.WithdrawalComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class WithdrawalComponentImpl extends AbstractCommonObject implements WithdrawalComponent {

    private static final By WITHDRAWAL_IFRAME = By.cssSelector("iframe.mfp-iframe");

    private static final By ERROR_MESSAGE = By.id("s_error");

    private static final By AMOUNT_INPUT = By.id("txnAmount");

    private static final By WITHDRAW_BUTTON = By.id("submitButton");

    private static final By CLOSE_BUTTON = By.id("close");

    @Override
    public String getErrorMessage() {
        switchToiFrame(WITHDRAWAL_IFRAME);
        String errorMessage = find(ERROR_MESSAGE).getText();
        switchToParentFrame();
        return errorMessage;
    }

    @Override
    public void clickCloseButton() {
        switchToiFrame(WITHDRAWAL_IFRAME);
        click(CLOSE_BUTTON);
        switchToParentFrame();
    }

    @Override
    public void makeWithdrawal(String amount) {
        switchToiFrame(WITHDRAWAL_IFRAME);
        find(AMOUNT_INPUT).sendKeys(amount);
        click(WITHDRAW_BUTTON);
        switchToParentFrame();
    }
}
