package com.whgtf.sportsbook.pom.common.components.interfaces;

/**
 * Created by cbairaagoni on 10/11/2016.
 */
public interface SearchResultItem {

    /**
     * Gets the event name from search results
     *
     * @return String
     */
    String getEventName();

    /**
     * Gets Date and Time from event details.
     *
     * @return String
     */
    String getDateTime();

    /**
     * Gets Competetion name as string value
     *
     * @return String
     */
    String getCompetetionName();

    /**
     * Verifies is Live icon is displayed or not
     *
     * @return boolean
     */
    boolean isInPlayIconDisplayed();

    /**
     * Verifies the streaming icon is displayed or not.
     *
     * @return boolean
     */
    boolean isStreamingIconDisplayed();
}
