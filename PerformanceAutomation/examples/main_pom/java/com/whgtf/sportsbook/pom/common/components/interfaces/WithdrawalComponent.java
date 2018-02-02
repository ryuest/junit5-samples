package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;

public interface WithdrawalComponent extends AbstractCommonInterface{

    String getErrorMessage();

    void makeWithdrawal(String amount);

    void clickCloseButton();
}
