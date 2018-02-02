package com.whgtf.sportsbook.pom.mobile.components.impl;

import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractPageObject;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeLoginAlertComponent;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
@Lazy
public class NativeLoginAlertComponentImpl extends AbstractPageObject implements NativeLoginAlertComponent {

    public NativeLoginAlertComponentImpl() {
        PageFactory.initElements(new AppiumFieldDecorator(
                getWebDriver(), 20, SECONDS), this);
        deviceSwitchToContextNativeApp();
    }

    @AndroidFindBy(id = "alertTitle")
    private MobileElement alertTitle;

    @AndroidFindBy(id = "message")
    private MobileElement alertMessage;

    @AndroidFindBy(id = "button2")
    private MobileElement moreInfoButton;

    @AndroidFindBy(id = "button1")
    private MobileElement okButton;


    @Override
    public String getAlertMessage() {
        return alertMessage.getText();
    }

    @Override
    public String getAlertTitle() {
        return alertTitle.getText();
    }

    @Override
    public void clickMoreInfoButton() {
        moreInfoButton.click();
    }

    @Override
    public void clickOkButton() {
        okButton.click();
    }

    @Override
    public boolean isDisplayed(){
        return alertTitle.isDisplayed();
    }
}
