package com.whgtf.sportsbook.pom.common.exceptions;

import cucumber.api.Pending;
import cucumber.api.PendingException;

@Pending
public class NoSelectionAvailable extends PendingException {

    public NoSelectionAvailable() {
        this("That selection is not displayed");
    }

    public NoSelectionAvailable(String message) {
        super(message);
    }
}
