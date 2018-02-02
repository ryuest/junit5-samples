package com.whgtf.sportsbook.pom.common.exceptions;

import cucumber.api.Pending;

@Pending
public class KnownIssueException extends RuntimeException {

    public KnownIssueException() {
        this("This issue is already raised.");
    }

    public KnownIssueException(String message) {
        super(message);
    }
}
