package com.whgtf.sportsbook.pom.mobile.components.interfaces;


public interface NativeElementsComponent {

    void selectFromDropdownList(String value);

    void selectFromSortedDropdownList(String value);

    void typeStakeInNativeKeypad(String stake);

    void clickOkInNativeKeypad();

    void clickOnSelection(String id);

    void clickOnFirstCompetitionSelection();

    String getBrowserUrl();
}
