package com.whgtf.sportsbook.pom.mobile.components.impl;

import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractPageObject;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeElementsComponent;
import com.whgtf.sportsbook.pom.mobile.util.NativeAppUtilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
@Lazy
public class NativeElementsComponentImpl extends AbstractPageObject implements NativeElementsComponent{

    public NativeElementsComponentImpl() {
        PageFactory.initElements(new AppiumFieldDecorator(
                getWebDriver(), 20, SECONDS), this);
        deviceSwitchToContextNativeApp();
        utilities = new NativeAppUtilities();
    }

    private NativeAppUtilities utilities;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView")
    public List<MobileElement> dropdownList;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='OK']")
    public MobileElement okButton;

    @AndroidFindBy(xpath = "(//android.widget.Button[contains(@resource-id, 'OB_OU')])[1]")
    public MobileElement firstSelection;

    final String AMOUNT_BTN_ANDROID = "//android.widget.Button[@content-desc='%s']";

    @Override
    public void selectFromDropdownList(String value) {
        utilities.clickElementInList(dropdownList, value);
    }

    @Override
    public void selectFromSortedDropdownList(String value) {
        utilities.clickElementInSortedList(dropdownList, value);
    }

    @Override
    public void typeStakeInNativeKeypad(String stake) {
        for(int i=0 ; i<stake.length() ; i++) {
            getWebDriver().findElement(By.xpath(String.format(AMOUNT_BTN_ANDROID, stake.charAt(i)))).click();
        }
    }


    @Override
    public void clickOkInNativeKeypad() {
        okButton.click();
    }

    @Override
    public void clickOnSelection(String id) {
        String context = ((AppiumDriver) getWebDriver()).getContext();
        Point p = ((MobileElement) getWebDriver().findElement(By.xpath("//android.widget.Button[contains(@resource-id,\""+id+"\")]"))).getCenter();
        deviceTapOnCoordinates(p.x, p.y);
        deviceSwitchToContext(context);
    }



    @Override
    public void clickOnFirstCompetitionSelection() {
        firstSelection.click();
    }

    /*
    The method is used only for browser opened by the link from app. In any other cases getCurrentUrl()
    should be used.
    For the method to work default browser has to be set to chrome. getCurrentUrl() does not work in this case
    because it returns url from app web view and not form the new browser.
     */
    @Override
    public String getBrowserUrl() {
        String url = getWebDriver().findElement(By.xpath("//*[contains(@resource-id, 'url_bar')]")).getText();
        getWebDriver().navigate().back();
        return url;
    }
}
