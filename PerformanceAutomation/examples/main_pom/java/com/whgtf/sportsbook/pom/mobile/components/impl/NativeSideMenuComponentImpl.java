package com.whgtf.sportsbook.pom.mobile.components.impl;


import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractPageObject;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeSideMenuComponent;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class NativeSideMenuComponentImpl extends AbstractPageObject implements NativeSideMenuComponent {

    @iOSFindBy(accessibility = "SideMenuButton")
    private MobileElement burguerMenu;

    public NativeSideMenuComponentImpl() {
        PageFactory.initElements(new AppiumFieldDecorator(getWebDriver()), this);
        deviceSwitchToContextNativeApp();
    }

    @Override
    public void open() {
        burguerMenu.click();
    }

}
