package com.whgtf.sportsbook.restApi;

/**
 * Created by juri on 29/11/2016.
 */
public class MessageError {

    public String message;

    public MessageError() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageError(String message) {
        this.message = message;
    }
}
