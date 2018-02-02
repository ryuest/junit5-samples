package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface LoginComponent extends AbstractCommonInterface {

    void clickOnLoginButton();

    void setUserField(final String user);

    void setPasswordField(final String password);

    void checkSaveUsername();

    void unCheckUsername();

    void clickOnRetrieveYourLoginLink();

    boolean login(final String user, final String password);

    boolean isDisplayed();

    boolean isLoginVegasDisplayed();

    void clickOnLoginButtonVegas();

    /**
     * This method returns a True value if the Text of "Login" is displayed.
     *
     * @return true if the component has been loaded properly on the page load.
     */
    boolean isLoaded();
}
