package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;

/**
 * Created by javierg on 15/06/2016.
 */

public interface NextOffComponent extends AbstractCommonInterface {

    /**
     * This method will click on a given Selection Index from a given Race from the races displayed on the
     * Next Off section.
     *
     * @param selectionIndex - This is the Selection Index from the given Race.
     * @param raceIndex - Here we pass the Index Number of the Race displayed on the Next Off.
     */
    void clickOnGivenSelectionFromGivenRace(int selectionIndex, int raceIndex);

    /**
     * This method will return where the Next Off section is Expanded or Collapsed.
     * @return - True if the Next Off section is expanded.
     */
    boolean isNextOffExpanded();

    /**
     * This method will Expand the Next Off Section. If the Section is already expanded, wont click on it again.
     */
    void expandNextOff();

    /**
     * This method will Collapse the Next Off Section. If the Section is already collapsed, wont click on it again.
     */
    void collapseNextOff();

    /**
     * This method will return the number of Races displayed on the Next Off Section.
     * @return Number of Races as an "int".
     */
    int numberOfNextOffRacesDisplayed();

    /**
     * This method returns the status of the Next Off section, if its displayed the section itself (not if is expanded
     * or collapsed).
     * @return True - If section is being displayed on the page, False - If section is not.
     */
    boolean isNextOffDisplayed();

    /**
     * This method will return True in case the Next Off content is being displayed, and by content is refered to
     * whats below from the Next Off Header, the Races.
     * @return - True if Next Off content is displayed, and False if not.
     */
    boolean areNextOffRacesDisplayed();

}
