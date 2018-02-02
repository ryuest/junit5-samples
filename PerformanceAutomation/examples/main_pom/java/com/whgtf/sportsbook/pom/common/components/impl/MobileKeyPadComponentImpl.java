package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.MobileKeyPadComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class MobileKeyPadComponentImpl extends AbstractCommonObject implements MobileKeyPadComponent {

    private static final By NUMBER_PAD = By.id("numberpad");

    private static final String NUMBER = "//button[@data-value='%s']";

    private static final By OK_BUTTON = By.cssSelector(".btn--success");


    @Override
    public boolean isDisplayed() {
        return find(NUMBER_PAD).isDisplayed();
    }

    @Override
    public void typeStake(final String stake) {
        if("10".equalsIgnoreCase(stake) || "25".equalsIgnoreCase(stake) || "50".equalsIgnoreCase(stake)) {
            click(find(By.xpath(String.format(NUMBER,stake))));
        } else {
            for(int i=0 ; i<stake.length() ; i++) {
                click(find(By.xpath(String.format(NUMBER,stake.charAt(i)))));
            }
            clickOnOk();
        }
    }

    private void clickOnOk() {
        click(find(NUMBER_PAD).findElement(OK_BUTTON));
    }
}
