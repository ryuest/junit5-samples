package com.whgtf.sportsbook.common;

import com.whgtf.sportsbook.reader.PropertyReader;

import com.whgtf.sportsbook.model.EventSettings;

/**
 * Created by Juri on 10/11/2016.
 */
public class PropArgs {
    PropertyReader props;

public PropArgs(String env) {
    props = new PropertyReader (env);
}
 //   PropertyReader props = new PropertyReader(EventSettings.env);

    public String getOBfeedUrl() {
        return props.getProperty("urlOBFeed");
    }
    public String getOxifeedUrl() {
        return props.getProperty("oxifeedUrl");
    }
    public int getMarketCurrentNumbers() {
        return Integer.parseInt(props.getProperty("marketCurrentNumbers"));
    }
    public int getSelectionCurrentNumbers() {
        return Integer.parseInt(props.getProperty("selectionCurrentNumbers"));
    }
    public String getOpenbetUsername() {
        return props.getProperty("openbetUsername");
    }
    public String getOpenbetPassword() {
        return props.getProperty("openbetPassword");
    }
    public String getPdsRestEndpoint() {
        return props.getProperty("pdsRestEndpoint");
    }



}
