package com.whgtf.sportsbook.pom.mobile.components.interfaces;


public interface NativeLoginComponent {

    void setUserField(final String user);

    void setPasswordField(final String password);

    void checkRememberMe(final boolean remember);

    void clickOnLostLoginDetails();

    void login(final String user, final String password);

    void loginWithRememberMe(final String user, final String password, final boolean remember);

    boolean isDisplayed();

}
