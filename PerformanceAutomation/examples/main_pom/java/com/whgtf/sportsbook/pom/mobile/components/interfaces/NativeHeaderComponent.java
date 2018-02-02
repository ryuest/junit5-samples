package com.whgtf.sportsbook.pom.mobile.components.interfaces;


import com.whgtf.sportsbook.pom.common.components.interfaces.DepositFormComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.JoinFormComponent;

public interface NativeHeaderComponent {

    boolean isUserLoggedIn();

    NativeAccountComponent clickOnAccountButton();

    JoinFormComponent clickOnJoinButton();

    NativeLoginComponent clickOnLoginButton();

    DepositFormComponent clickOnDepositButton();

    void clickOnBackButton();

    String getBalance();

    void clickOnWhLogo();
}
