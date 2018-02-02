package com.whgtf.sportsbook.pom.mobile.components.impl;


import com.whgtf.sportsbook.pom.common.components.impl.DepositFormComponentImpl;
import com.whgtf.sportsbook.pom.common.components.interfaces.DepositFormComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.JoinFormComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractPageObject;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeAccountComponent;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeHeaderComponent;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeLoginComponent;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;


@Component
@Lazy
public class NativeHeaderComponentImpl extends AbstractPageObject implements NativeHeaderComponent {

    @AndroidFindBy(xpath = "//android.widget.ImageView")
    @iOSFindBy(accessibility = "WHLogoMenu")
    private MobileElement williamHillLogo;

    @AndroidFindBy(id = "toolbarJoinOrDeposit")
    @iOSFindBy(accessibility = "JoinNowButton")
    private MobileElement joinNowButton;

    @AndroidFindBy(id = "progressButton_button")
    @iOSFindBy(accessibility = "Login")
    private MobileElement logInButtonOnMenu;


    @AndroidFindBy(id = "toolbarJoinOrDeposit")
    @iOSFindBy(accessibility = "DepositButton")
    private MobileElement depositButton;

    @AndroidFindBy(id = "progressButton_button")
    @iOSFindBy(accessibility = "AccountBalanceButton")
    private MobileElement accountBalanceButton;


    @AndroidFindBy(accessibility = "Navigate up")
    private MobileElement backButton;


    @Autowired
    @Lazy
    private JoinFormComponent joinFormComponent;

    @Autowired
    @Lazy
    private NativeLoginComponent nativeLoginComponent;

    public NativeHeaderComponentImpl() {
        //Default webdriver timeout (1 second) is too short for android. Timeout values should be agreed for both iOS and Android and put somewhere in properties
        PageFactory.initElements(new AppiumFieldDecorator(
                getWebDriver(), 20, SECONDS), this);
        deviceSwitchToContextNativeApp();
    }

    /*
    When the user is logged in, then the account balance is displayed in format £0.00
     */
    @Override
    public boolean isUserLoggedIn() {
        return accountBalanceButton.getText().matches(".*\\d+\\.\\d{2}");
    }


    @Override
    public NativeAccountComponent clickOnAccountButton() {
        accountBalanceButton.click();
        return new NativeAccountComponentImpl();
    }

    @Override
    public JoinFormComponent clickOnJoinButton() {
        joinNowButton.click();
        return joinFormComponent;
    }


    @Override
    public NativeLoginComponent clickOnLoginButton() {
        logInButtonOnMenu.click();
        return new NativeLoginComponentImpl();
    }

    @Override
    public DepositFormComponent clickOnDepositButton() {
        depositButton.click();
        return new DepositFormComponentImpl();
    }

    @Override
    public void clickOnBackButton() {
        backButton.click();
    }

    @Override
    public String getBalance() {
        String balance = accountBalanceButton.getText();
        int i = 0;
        while ((i < balance.length()) && !Character.isDigit(balance.charAt(i))) i++;
        return balance.substring(i);
    }

    @Override
    public void clickOnWhLogo() {
        williamHillLogo.click();
    }
}
