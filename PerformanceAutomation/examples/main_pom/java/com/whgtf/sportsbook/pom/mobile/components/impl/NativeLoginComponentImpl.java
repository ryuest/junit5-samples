package com.whgtf.sportsbook.pom.mobile.components.impl;

import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractPageObject;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeLoginComponent;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
@Lazy
public class NativeLoginComponentImpl extends AbstractPageObject implements NativeLoginComponent {

    public NativeLoginComponentImpl() {
        PageFactory.initElements(new AppiumFieldDecorator(
                getWebDriver(), 20, SECONDS), this);
        deviceSwitchToContextNativeApp();
    }

    @AndroidFindBy(id = "login_username")
    @iOSFindBy(accessibility = "usernameTextField")
    private MobileElement usernameTextfield;

    @AndroidFindBy(id = "login_password")
    @iOSFindBy(accessibility = "passwordTextField")
    private MobileElement passwordTextfield;

    @AndroidFindBy(id = "login_button")
    @iOSFindBy(accessibility = "loginButton")
    private MobileElement logInButton;

    @AndroidFindBy(xpath = "//android.widget.CheckBox")
    @iOSFindBy(accessibility = "autologinSwitch")
    private MobileElement rememberMe;

    @AndroidFindBy(id = "lostCredentials")
    private MobileElement lostLogInDetails;


    @Override
    public void setUserField(String user) {
        usernameTextfield.sendKeys(user);
    }

    @Override
    public void setPasswordField(String password) {
        passwordTextfield.sendKeys(password);
    }

    @Override
    public void checkRememberMe(boolean remember) {
        boolean isChecked = Boolean.valueOf(rememberMe.getAttribute("checked"));
        if((remember && !isChecked)
                || (!remember && isChecked)){
            rememberMe.click();
        }
    }

    @Override
    public void clickOnLostLoginDetails() {
        lostLogInDetails.click();
    }


    @Override
    public void login(String user, String password) {
        setUserField(user);
        setPasswordField(password);
        logInButton.click();
    }

    @Override
    public void loginWithRememberMe(String user, String password, boolean remember) {
        checkRememberMe(remember);
        login(user, password);
    }

    @Override
    public boolean isDisplayed(){
        return usernameTextfield.isDisplayed();
    }
}
