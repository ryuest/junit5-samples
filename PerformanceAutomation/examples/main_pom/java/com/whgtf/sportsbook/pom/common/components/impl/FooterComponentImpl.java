package com.whgtf.sportsbook.pom.common.components.impl;

import com.google.common.collect.ImmutableMap;
import com.whgtf.sportsbook.pom.common.components.interfaces.FooterComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.LanguageSelectionComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Lazy
public class FooterComponentImpl extends AbstractCommonObject implements FooterComponent {

    private static By BACK_TO_TOP_BUTTON = By.className("button__back-to-top");

    public static final Map<String, String> LANGUAGE_MAP = ImmutableMap.<String, String>builder()
            .put("German", "de")
            .put("English", "en")
            .put("Japanese", "ja")
            .put("Greek", "el")
            .put("Russian", "ru")
            .put("Swedish", "sv")
            .build();


    private static final By FLAG = By.cssSelector(".languageSelectionToggle");


    @Autowired
    @Lazy
    private LanguageSelectionComponentImpl languageSelection;

    @Override
    public LanguageSelectionComponent clickOnChangeLanguage() {
        executeJavaScript("$(\".button__back-to-top\")[0].scrollIntoView()");
        click(FLAG);
        return languageSelection;
    }

    public boolean isLanguageFlagDisplayed(final String language) {
        return find(FLAG).getAttribute("class").contains(LANGUAGE_MAP.get(language));
    }

    @Override
    public void clickOnBackToTopButton() {
        find(BACK_TO_TOP_BUTTON);
        executeJavaScript("window.scrollBy(0,-50)");
        click(find(BACK_TO_TOP_BUTTON));
    }

    @Override
    public boolean isLanguageDisplayed(String language) {
        waitSportsbook();
        return isElementPresent(By.id(LANGUAGE_MAP.get(language)));
    }


}
