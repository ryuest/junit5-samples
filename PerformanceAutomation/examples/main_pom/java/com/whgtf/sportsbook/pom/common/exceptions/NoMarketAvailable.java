package com.whgtf.sportsbook.pom.common.exceptions;


import cucumber.api.PendingException;

public class NoMarketAvailable extends PendingException {

    public NoMarketAvailable() {
        this("There is no market available in that section");
    }

    public NoMarketAvailable(String message) {
        super(message);
    }
}
