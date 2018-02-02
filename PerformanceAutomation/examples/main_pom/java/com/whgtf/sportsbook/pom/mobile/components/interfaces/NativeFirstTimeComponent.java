package com.whgtf.sportsbook.pom.mobile.components.interfaces;


public interface NativeFirstTimeComponent {

    void skipIntro();

    void clickOnShareLocation();

    void clickOnAllowLocation(boolean allowOrNot);

    void clickOnAllowNotifications(boolean allowOrNot);

    void clickOnJoinNow();

    void clickOnLogIn();

    void clickOnDoItLater();

    void setOptionsToQa(String app, String activity);
}
