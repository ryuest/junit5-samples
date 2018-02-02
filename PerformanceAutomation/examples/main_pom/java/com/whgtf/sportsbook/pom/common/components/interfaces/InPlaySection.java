package com.whgtf.sportsbook.pom.common.components.interfaces;


public interface InPlaySection extends Section {


    /**
     * Return whether the live icon for an specific event (event Id) is displayed
     * @param eventId the event Id
     * @return true or false depending of the live icon is displayed or not
     */
    boolean isLiveIconDisplayedInEvent(final String eventId);

    /**
     * Return the warning message
     * @return the warning message as String
     */
    String getWarningMessage();

    /**
     * Return whether the View All link is displayed
     * @return true or false if is displayed
     */
    boolean isViewAllInPlayLinkDisplayed();

    /**
     * Click on View All link
     */
    void clickOnViewAll();
}
