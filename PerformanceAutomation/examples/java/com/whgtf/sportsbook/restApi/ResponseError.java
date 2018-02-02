package com.whgtf.sportsbook.restApi;

import com.whgtf.sportsbook.model.ResponseMarket;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;

/**
 * Created by juri on 28/11/2016.
 */
public class ResponseError {
    private String message;

    public List<MessageError> ErrorMessages = new ArrayList<MessageError>();

    public ResponseError() {

    }

    public void addErrorExampleMessage (MessageError messageError) {
        ErrorMessages.add(messageError);
    }

    public ResponseError(String message) {
        this.message = String.format(message);
    }


    public ResponseError(Exception e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return this.message;
    }

}
