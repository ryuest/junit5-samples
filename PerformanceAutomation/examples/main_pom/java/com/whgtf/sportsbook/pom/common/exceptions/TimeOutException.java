package com.whgtf.sportsbook.pom.common.exceptions;

import cucumber.api.Pending;

@Pending
public class TimeOutException extends RuntimeException {

    public TimeOutException() {
        this("William Hill page took longer than 20 seconds to load");
    }

    public TimeOutException(String message) {
        super(message);
    }
}
