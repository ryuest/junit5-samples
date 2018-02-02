package com.whgtf.sportsbook.pom.common.exceptions;

import cucumber.api.PendingException;


public class NoEventAvailable extends PendingException {

    public NoEventAvailable() {
        this("There is no event available in that section");
    }

    public NoEventAvailable(String message) {
        super(message);
    }
}
