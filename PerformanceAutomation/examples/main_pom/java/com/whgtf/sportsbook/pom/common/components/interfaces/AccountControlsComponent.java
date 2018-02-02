package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;

public interface AccountControlsComponent extends AbstractCommonInterface {

    boolean isSectionDisplayed(String title);

    void expandDropdownMenu();

    void selectSection(String title);

    void clickOnInformation();

    void clickOnDepositLimits();

    void clickOnSessionTimeReminders();

    void clickOnAccountClosure();

    void clickOnGamblingControls();
}
