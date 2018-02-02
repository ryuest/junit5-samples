package com.whgtf.sportsbook.pom.mobile.components.impl;


import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractPageObject;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeFirstTimeComponent;
import com.whgtf.sportsbook.pom.mobile.util.NativeAppUtilities;
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
public class NativeFirstTimeComponentImpl extends AbstractPageObject implements NativeFirstTimeComponent {

    @iOSFindBy(accessibility = "Share your location")
    private MobileElement shareYourLocationButton;

    @AndroidFindBy(xpath = "(//android.widget.Button)[2]")
    @iOSFindBy(accessibility = "Allow")
    private MobileElement allowShareLocationButton;

    @AndroidFindBy(xpath = "(//android.widget.Button)[1]")
    @iOSFindBy(accessibility = "Don’t Allow")
    private MobileElement notAllowShareLocationButton;

    @iOSFindBy(accessibility = "Allow notifications")
    private MobileElement allowNotifications;

    // this is the "No, thanks" button.
    @iOSFindBy(accessibility = "No, thanks")
    private MobileElement notAllowNotifications;

    @iOSFindBy(accessibility = "Join Now")
    private MobileElement joinNowButton;

    @iOSFindBy(accessibility = "Log in")
    private MobileElement logInButton;

    @iOSFindBy(accessibility = "I'll do it later")
    private MobileElement iWillDoItLater;

    @AndroidFindBy(xpath = "//*[@text='Select the environment to use']")
    private MobileElement appOptions;

    @AndroidFindBy(xpath = "//*[@text='QA Settings']")
    private MobileElement qaSettings;

    @AndroidFindBy(xpath = "//*[@text='Dev Settings']")
    private MobileElement devSettings;

    @AndroidFindBy(xpath = "//*[contains(@text,'webview')]")
    private MobileElement enableDebug;


    public NativeFirstTimeComponentImpl() {
        PageFactory.initElements(new AppiumFieldDecorator(getWebDriver(), 20, SECONDS), this);
        deviceSwitchToContextNativeApp();
    }

    @Override
    public void skipIntro(){
        deviceSwitchToContextNativeApp();
        if(isDeviceIOS()) {
            clickOnShareLocation();
            clickOnAllowLocation(true);
            clickOnAllowNotifications(false);
            clickOnDoItLater();
        } else {
            clickOnAllowLocation(true);
        }
    }


    @Override
    public void clickOnShareLocation(){
        shareYourLocationButton.click();
    }

    @Override
    public void clickOnAllowLocation(boolean allowOrNot){
        if(allowOrNot){
            allowShareLocationButton.click();
        }else{
            notAllowShareLocationButton.click();
        }
    }

    @Override
    public void clickOnAllowNotifications(boolean allowOrNot){
        if(allowOrNot){
            allowNotifications.click();
        }else{
            notAllowNotifications.click();
        }
    }

    @Override
    public void clickOnJoinNow(){
        joinNowButton.click();
    }

    @Override
    public void clickOnLogIn(){
        logInButton.click();
    }

    @Override
    public void clickOnDoItLater(){
        iWillDoItLater.click();
    }

    @Override
    public void setOptionsToQa(String app, String activity) {
        enableDebug.click();
        new NativeAppUtilities().startActivity(app, activity);
    }
}
