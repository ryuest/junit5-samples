package com.whgtf.sportsbook.main.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

/**
 * Created by martin on 29/07/2016.
 */
@Component
@JsonIgnoreProperties(ignoreUnknown=true)
public class Tab {


    // Variables initializations
    private boolean enabled;
    private String id;
    private String text;


    // Gets
    public boolean getEnabled() {
        return enabled;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }


    // Sets
    public void setDisplayed(final boolean displayed) {
        this.enabled=displayed;
    }

    public void setId(final String id) {
        this.id=id;
    }

    public void setText(final String text) {
        this.text=text;
    }

}
