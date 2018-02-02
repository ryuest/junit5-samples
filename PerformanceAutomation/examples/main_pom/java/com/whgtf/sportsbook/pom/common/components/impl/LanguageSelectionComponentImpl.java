package com.whgtf.sportsbook.pom.common.components.impl;

import com.google.common.collect.ImmutableMap;
import com.whgtf.sportsbook.pom.common.components.interfaces.LanguageSelectionComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
@Lazy
public class LanguageSelectionComponentImpl extends AbstractCommonObject implements LanguageSelectionComponent {

    public static final Map<String,String> LANGUAGE_MAP = ImmutableMap.<String,String>builder()
            .put("German", "de")
            .put("English", "en")
            .put("Japanese","ja")
            .put("Greek","el")
            .put("Russian","ru")
            .put("Swedish","sv")
            .build();

    private final static By LANGUAGE_SELECTION = By.id("languageSelection");

    @Override
    public void clickOnLanguage(String language) {
        waitElementToBeClickable(LANGUAGE_SELECTION,5);
        click(By.id(LANGUAGE_MAP.get(language)));
        waitSportsbook();
    }

    @Override
    public boolean isFlagGrayedOut(String language) {
        List<WebElement> flags = find(LANGUAGE_SELECTION).findElements(By.tagName("li"));
        boolean grayedOut = false;
        int count = 0;
        for (WebElement flag:flags) {
            if(LANGUAGE_MAP.get(language).equalsIgnoreCase(flag.findElement(By.tagName("a")).getAttribute("id")) &&
                    flag.findElement(By.tagName("a")).getAttribute("class").contains("-active")){
                count++;
            }
        }
        return count == 1;

    }


}




