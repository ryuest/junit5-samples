package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.AccountControlsComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class AccountControlsComponentImpl extends AbstractCommonObject implements AccountControlsComponent {

    private static final By ACCOUNT_IFRAME = By.cssSelector("iframe.mfp-iframe");

    private static final By PAGE_TITLE = By.cssSelector(".info h2");

    private static final By LANDING_PAGE_TITLE = By.cssSelector("h3");

    private static final By INFORMATION_LINK = By.cssSelector("a[href*='index']");

    private static final By DEPOSIT_LIMITS_LINK = By.cssSelector("a[href*='deposit_limit']");

    private static final By SESSION_TIME_LINK = By.cssSelector("a[href*='reality_check']");

    private static final By ACCOUNT_CLOSURE_LINK = By.cssSelector("a[href*='account_closure']");

    private static final By GAMBLING_CONTROLS_LINK = By.cssSelector("a[href*='timeout']");

    private static final By DROPDOWN_MENU = By.cssSelector(".dropdown");

    @Override
    public boolean isSectionDisplayed(String title) {
        boolean isMobile = isMobile();
        switchToiFrame(ACCOUNT_IFRAME);
        boolean isDisplayed;
        if(title.equalsIgnoreCase("information") && isMobile){
            isDisplayed = find(LANDING_PAGE_TITLE).getText().equalsIgnoreCase(title);
        }else {
            isDisplayed = find(PAGE_TITLE).getText().equalsIgnoreCase(title);
        }
        switchToParentFrame();
        return isDisplayed;
    }

    @Override
    public void expandDropdownMenu() {
        switchToiFrame(ACCOUNT_IFRAME);
        click(DROPDOWN_MENU);
        switchToParentFrame();
    }

    @Override
    public void selectSection(String title) {
        if (isMobile()) {
            expandDropdownMenu();
        }
        switch (title) {
            case "Information":
                clickOnInformation();
                break;
            case "Deposit Limits":
                clickOnDepositLimits();
                break;
            case "Session Time Reminders":
                clickOnSessionTimeReminders();
                break;
            case "Account Closure":
                clickOnAccountClosure();
                break;
            case "Gambling Controls":
                clickOnGamblingControls();
                break;
        }
    }

    @Override
    public void clickOnInformation() {
        switchToiFrame(ACCOUNT_IFRAME);
        click(INFORMATION_LINK);
        switchToParentFrame();
    }

    @Override
    public void clickOnDepositLimits() {
        switchToiFrame(ACCOUNT_IFRAME);
        click(DEPOSIT_LIMITS_LINK);
        switchToParentFrame();
    }

    @Override
    public void clickOnSessionTimeReminders() {
        switchToiFrame(ACCOUNT_IFRAME);
        click(SESSION_TIME_LINK);
        switchToParentFrame();
    }

    @Override
    public void clickOnAccountClosure() {
        switchToiFrame(ACCOUNT_IFRAME);
        click(ACCOUNT_CLOSURE_LINK);
        switchToParentFrame();
    }

    @Override
    public void clickOnGamblingControls() {
        switchToiFrame(ACCOUNT_IFRAME);
        click(GAMBLING_CONTROLS_LINK);
        switchToParentFrame();
    }
}
