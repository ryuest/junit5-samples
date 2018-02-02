package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.GamePage;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class GamePageImpl extends AbstractSportsPageObject implements GamePage{

    private static final By GAME = By.id("gameBody");

    public boolean isDisplayed(){
        return find(GAME).isDisplayed();
    }
}
