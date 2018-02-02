package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.InPlayPage;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class InPlayPageImpl extends AbstractSportsPageObject implements InPlayPage {

    private static final By IN_PLAY_SECTION = By.id("in-play-content");

    @Override
    public boolean isDisplayed() {
        return isElementDisplayed(IN_PLAY_SECTION) && getCurrentPageName().equals("inPlay");
    }
}
