package com.whgtf.sportsbook.pom.mobile.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.*;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.FeaturedPage;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeAccountComponent;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import static java.util.concurrent.TimeUnit.SECONDS;


public class NativeAccountComponentImpl extends AbstractPageObject implements NativeAccountComponent {

    public NativeAccountComponentImpl() {
        PageFactory.initElements(new AppiumFieldDecorator(getWebDriver(), 20, SECONDS), this);
        deviceSwitchToContextNativeApp();
    }

    @AndroidFindBy(xpath = "(//android.widget.TextView)[2]")
    private MobileElement openBetsButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView)[4]")
    private MobileElement depositButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView)[6]")
    private MobileElement myAccountButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView)[8]")
    private MobileElement accountControlsButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView)[3]")
    private MobileElement settledBetsButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView)[5]")
    private MobileElement withdrawButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView)[7]")
    private MobileElement preferencesButton;

    @AndroidFindBy(id = "account_logout")
    @iOSFindBy(accessibility = "Log Out")
    private MobileElement logoutButton;


    @Autowired
    @Lazy
    private DepositFormComponent depositFormComponent;

    @Autowired
    @Lazy
    private MyAccountComponent myAccountComponent;

    @Autowired
    @Lazy
    private WithdrawalComponent withdrawalComponent;

    @Autowired
    @Lazy
    private AccountControlsComponent accountControlsComponent;

    @Autowired
    @Lazy
    private AccountPreferencesComponent accountPreferencesComponent;

    @Override
    public void clickOnLogoutButton() {
        logoutButton.click();
    }

    @Override
    public Object clickOnAccountOption(String option) {
        switch(option){
            case "Deposit":
                return clickOnDeposit();
            case "Open Bets":
                return clickOnOpenBets();
            case "Settled Bets":
                return clickOnSettledBets();
            case "My Account":
                return clickOnMyAccount();
            case "Withdraw":
                return clickOnWithdraw();
            case "Preferences":
                return clickOnAccountPreferences();
            case "Account Controls":
                return clickOnAccountControls();
            default:
                return null;
        }
    }

    @Override
    public DepositFormComponent clickOnDeposit() {
        depositButton.click();
        return depositFormComponent;
    }

    @Override
    public MyAccountComponent clickOnOpenBets() {
        openBetsButton.click();
        return myAccountComponent;
    }

    @Override
    public MyAccountComponent clickOnSettledBets() {
       settledBetsButton.click();
        return myAccountComponent;
    }

    @Override
    public MyAccountComponent clickOnMyAccount() {
        myAccountButton.click();
        return myAccountComponent;
    }

    @Override
    public WithdrawalComponent clickOnWithdraw() {
        withdrawButton.click();
        return withdrawalComponent;
    }

    @Override
    public AccountPreferencesComponent clickOnAccountPreferences() {
        preferencesButton.click();
        return accountPreferencesComponent;
    }

    @Override
    public AccountControlsComponent clickOnAccountControls() {
        accountControlsButton.click();
        return accountControlsComponent;
    }
}
