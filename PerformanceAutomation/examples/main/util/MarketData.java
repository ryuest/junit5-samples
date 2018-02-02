package com.whgtf.sportsbook.main.util;

/**
 * Created by javierg on 14/09/2016.
 */
public class MarketData {

    private String marketName;

    private String display;

    private String bir;

    private String selections;

    private String handicap;

    public String getMarketGroup() {
        return marketName;
    }

    public void setMarketGroup(String marketGroup) {
        this.marketName = marketGroup;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getBir() {
        return bir;
    }

    public void setBir(String bir) {
        this.bir = bir;
    }

    public String getSelections() {
        return this.selections;
    }

    public String getHandicap() {
        return handicap;
    }

    public void setHandicap(String handicap) {
        this.handicap = handicap;
    }
}
