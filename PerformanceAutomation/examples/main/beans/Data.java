package com.whgtf.sportsbook.main.beans;

import org.springframework.stereotype.Component;

/**
 * Created by martin on 29/07/2016.
 */
@Component
public class Data {
    private Tabs data;

    public Tabs getData() {
        return data;
    }

    public void setData(Tabs data) {
        this.data = data;
    }

}
