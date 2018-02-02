package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface LanguageSelectionComponent extends AbstractCommonInterface {

    void clickOnLanguage(final String language);

    boolean isFlagGrayedOut(final String language);
}
