package com.whgtf.sportsbook.pom.mobile.components.interfaces;

import com.whgtf.sportsbook.pom.common.components.interfaces.*;

public interface NativeAccountComponent {

    void clickOnLogoutButton();

    Object clickOnAccountOption(String option);

    DepositFormComponent clickOnDeposit();

    MyAccountComponent clickOnOpenBets();

    MyAccountComponent clickOnSettledBets();

    MyAccountComponent clickOnMyAccount();

    WithdrawalComponent clickOnWithdraw();

    AccountPreferencesComponent clickOnAccountPreferences();

    AccountControlsComponent clickOnAccountControls();
}
