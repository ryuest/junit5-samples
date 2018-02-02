package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.HeaderComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.LoginComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class LoginComponentImpl extends AbstractCommonObject implements LoginComponent {

    private final By LOGIN_DIV = By.id("account");

    private final By LOGIN_VEGAS_DIV = By.id("loginForm");

    private final By USERNAME_FIELD = By.id("loginUsernameInput");

    private final By PASSWORD_FIELD = By.id("loginPasswordInput");

    private final By LOGIN_BUTTON = By.xpath("//form[@id='account']//button[contains(@class,'button')]");

    private final By LOGIN_BUTTON_VEGAS = By.id("login-button");

    private final By SAVE_USERNAME_CHECK = By.id("rememberMe");

    private final By RECOVERY_LINK = By.id("passwordRecoveryLink");

    private final By LOGIN_BUTTON_TEXT = By.cssSelector(".account-tab__text.-login");

    private final By CLOSE_WELCOME_MESSAGE_BUTTON = By.cssSelector("#welcomeMessage .btn--cancel");

    @Autowired
    @Lazy
    HeaderComponent headerComponent;


    @Override
    public void clickOnLoginButton() {
        waitElementToBeClickable(LOGIN_BUTTON,10);
        click(LOGIN_BUTTON);
        try {
            waitElementToBeVisible(CLOSE_WELCOME_MESSAGE_BUTTON, 5);
            if(isElementDisplayed(CLOSE_WELCOME_MESSAGE_BUTTON)) {
                click(CLOSE_WELCOME_MESSAGE_BUTTON);
            }
        } catch (Exception ex) {
            // welcome message not displayed but we don't throw any exception.
        }

    }

    @Override
    public void setUserField(String user) {
        setText(USERNAME_FIELD, user);
    }

    @Override
    public void setPasswordField(String password) {
        setText(PASSWORD_FIELD, password);
    }

    @Override
    public void checkSaveUsername() {
        if(!find(SAVE_USERNAME_CHECK).isSelected())
            find(SAVE_USERNAME_CHECK).click();
    }

    @Override
    public void unCheckUsername() {
        if(find(SAVE_USERNAME_CHECK).isSelected())
            find(SAVE_USERNAME_CHECK).click();
    }

    @Override
    public void clickOnRetrieveYourLoginLink() {
        waitElementToBeClickable(RECOVERY_LINK,5).findElement(RECOVERY_LINK).click();
    }

    @Override
    public boolean login(String user, String password) {
        waitElementToBeClickable(USERNAME_FIELD,10);
        setUserField(user);
        setPasswordField(password);
        clickOnLoginButton();
        return headerComponent.isBalanceDisplayed();
    }

    @Override
    public boolean isDisplayed(){
        return isElementDisplayed(LOGIN_DIV);
    }

    @Override
    public boolean isLoaded() {
        return !find(LOGIN_BUTTON_TEXT).getText().isEmpty();
    }

    @Override
    public boolean isLoginVegasDisplayed() {
        return find(USERNAME_FIELD).isDisplayed();
    }

    @Override
    public void clickOnLoginButtonVegas() {
        find(LOGIN_BUTTON_VEGAS).click();
    }
}
