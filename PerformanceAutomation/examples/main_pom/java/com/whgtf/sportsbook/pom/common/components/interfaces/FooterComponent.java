package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface FooterComponent extends AbstractCommonInterface {

    LanguageSelectionComponent clickOnChangeLanguage();

    boolean isLanguageDisplayed(final String language);

    boolean isLanguageFlagDisplayed(final String language);

    void clickOnBackToTopButton();



}
