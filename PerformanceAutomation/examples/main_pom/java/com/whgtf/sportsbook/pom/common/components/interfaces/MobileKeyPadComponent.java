package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface MobileKeyPadComponent extends AbstractCommonInterface {

    boolean isDisplayed();

    void typeStake(final String stake);
}
