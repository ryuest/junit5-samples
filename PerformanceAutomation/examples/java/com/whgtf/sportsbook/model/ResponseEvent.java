package com.whgtf.sportsbook.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juri on 28/11/2016.
 */
public class ResponseEvent {
   public String eventId;

    public List<ResponseMarket> marketIds = new ArrayList<ResponseMarket>();
    public void addMarket (ResponseMarket responseMarket) {
        marketIds.add(responseMarket);
    }

    public List<ResponseSelection> selectionIds = new ArrayList<ResponseSelection>();
    public void addSelection (ResponseSelection responseSelection) {
        selectionIds.add(responseSelection);
    }
}

