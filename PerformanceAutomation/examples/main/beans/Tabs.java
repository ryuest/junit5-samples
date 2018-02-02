package com.whgtf.sportsbook.main.beans;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by martin on 29/07/2016.
 */
@Component
public class Tabs {

    private Tab[] tabs;
    private String defaultTab;


    public String getDefaultTab() {
        return defaultTab;
    }

    public void setDefaultTab(String defaultTab) {
        this.defaultTab = defaultTab;
    }


    public List<Tab> getTabs() throws IOException {
        return Arrays.asList(tabs);
    }

    public void setTabs(Tab[] tabsList) throws IOException {
        this.tabs = tabsList;
    }
}
