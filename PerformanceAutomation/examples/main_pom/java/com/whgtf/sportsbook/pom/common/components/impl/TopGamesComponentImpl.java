package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.TopGamesComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class TopGamesComponentImpl extends AbstractCommonObject implements TopGamesComponent{

    private static final By OVERLAY_ID = By.id("top-games");

    private static final By CLOSE_BTN = By.cssSelector("#top-games .close");

    private static final By EMBEDDED_GAME = By.cssSelector(".topgames-icon--full");

    private static final By NON_EMBEDDED_GAME = By.className("topgames-icon");


    @Override
    public boolean isDisplayed() {
        return find(OVERLAY_ID).isDisplayed();
    }

    @Override
    public void clickOnEmbeddedGame() {
        waitElementToBeClickable(EMBEDDED_GAME, 5);
        click(EMBEDDED_GAME);
        checkAlert();
    }

    @Override
    public void clickOnNoEmbeddedGame() {
        find(NON_EMBEDDED_GAME).findElement(By.xpath("./a")).click();
    }

    @Override
    public void clickOnCloseCross() {
        find(CLOSE_BTN).click();
    }
}
