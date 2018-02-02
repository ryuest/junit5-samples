package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;

public interface MyAccountComponent extends AbstractCommonInterface {

    void filterTransactions(String filter);

    void clickOnOpenBets();

    void clickOnSettledBets();

    boolean areOpenBetsTransactionsDisplayed();

    boolean areSettledBetsTransactionsDisplayed();

    String getFirstTransactionSelection();

    void expandTransactionFilter();
}
