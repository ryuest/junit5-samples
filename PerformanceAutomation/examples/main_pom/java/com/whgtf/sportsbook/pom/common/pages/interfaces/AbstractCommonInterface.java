package com.whgtf.sportsbook.pom.common.pages.interfaces;

import com.whgtf.sportsbook.model.Selection;
import org.openqa.selenium.By;

import java.util.List;

public interface AbstractCommonInterface {


    List<String> getAllLinksInThePage();

    Selection clickOnSelection(final String selectionId);

    boolean isSelectionClickable(String selectionId);

    String getPriceFromSelectionId(final String selectionId);

    String getOddFromSelection(String selectionId);

    boolean isSelectionDisplayed(final String selectionId);

    void clickAndWaitSportsbook(By locator);

    void checkAlert();

    boolean sleep(final long milliseconds);

    /**
     * Verify the given sports home page is displayed and returns true otherwise false.
     *
     * @param sportsName The sport name
     * @return boolean
     */
    boolean isSportsHomePageDisplayed(String sportsName);

    String getSportPageName();

    boolean isMobile();
}
