package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.MyAccountComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class MyAccountComponentImpl extends AbstractCommonObject implements MyAccountComponent {

    private static final By OPEN_BETS_BUTTON = By.id("transactions-types-link-open-bets");

    private static final By SETTLED_BETS_BUTTON = By.id("transactions-types-link-settled-bets");

    private static final By TRANSACTION_FILTER_TOGGLE = By.id("transactions-header-link");

    private static final By TRANSACTION_FILTER_30DAYS = By.cssSelector("#transactions-filters-30days input");

    private static final By TRANSACTION_FILTER_7DAYS = By.cssSelector("#transactions-filters-7days input");

    private static final By TRANSACTION_FILTER_24HOURS = By.cssSelector("#transactions-filters-24hours input");

    private static final By FILTER_TRANSACTION_BUTTON = By.id("transactions-filters-submit");

    private static final By TRANSACTION_IN_OPEN_BETS = By.cssSelector(".statement--open-bets .transactions__group");

    private static final By TRANSACTION_IN_SETTLED_BETS = By.cssSelector(".statement--settled-bets .transactions__group");

    private static final By SELECTION_IN_TRANSACTION = By.cssSelector(".cell__selection");

    private static final By ACCOUNT_IFRAME = By.cssSelector("iframe.mfp-iframe");

    private static final By TRANSACTION_LOADER = By.cssSelector(".statement__loading ");


    @Override
    public void filterTransactions(String filter) {
        if (isMobile()) {
            expandTransactionFilter();
        }
        switchToiFrame(ACCOUNT_IFRAME);
        switch (filter) {
            case "Last 30 days":
                click(TRANSACTION_FILTER_30DAYS);
                break;
            case "Last 7 days":
                click(TRANSACTION_FILTER_7DAYS);
                break;
            case "Last 24 hours":
                click(TRANSACTION_FILTER_24HOURS);
        }
        click(FILTER_TRANSACTION_BUTTON);
        switchToParentFrame();
    }

    @Override
    public void expandTransactionFilter() {
        switchToiFrame(ACCOUNT_IFRAME);
        click(TRANSACTION_FILTER_TOGGLE);
        switchToParentFrame();
    }

    @Override
    public void clickOnOpenBets() {
        switchToiFrame(ACCOUNT_IFRAME);
        click(OPEN_BETS_BUTTON);
        switchToParentFrame();
    }

    @Override
    public void clickOnSettledBets() {
        switchToiFrame(ACCOUNT_IFRAME);
        click(SETTLED_BETS_BUTTON);
        switchToParentFrame();
    }

    @Override
    public boolean areOpenBetsTransactionsDisplayed() {
        switchToiFrame(ACCOUNT_IFRAME);
        waitElementNotToBeVisible(TRANSACTION_LOADER, 20);
        int transactionCount = findElements(TRANSACTION_IN_OPEN_BETS).size();
        switchToParentFrame();
        return transactionCount > 0;
    }

    @Override
    public boolean areSettledBetsTransactionsDisplayed() {
        switchToiFrame(ACCOUNT_IFRAME);
        waitElementNotToBeVisible(TRANSACTION_LOADER, 20);
        int transactionCount = findElements(TRANSACTION_IN_SETTLED_BETS).size();
        switchToParentFrame();
        return transactionCount > 0;
    }

    @Override
    public String getFirstTransactionSelection() {
        switchToiFrame(ACCOUNT_IFRAME);
        String selection = find(SELECTION_IN_TRANSACTION).getText().replace("@", "at");
        switchToParentFrame();
        return selection;
    }


}
