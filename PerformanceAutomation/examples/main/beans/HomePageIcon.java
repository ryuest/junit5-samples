package com.whgtf.sportsbook.main.beans;

import org.springframework.stereotype.Component;

/**
 * Created by jgvera on 06/12/2016.
 */
@Component
public class HomePageIcon {

    private boolean enabled;

    private String id;

    private String text;

    private String type;

    private String url;

    private String icon;

    private String background;


    private String gameNameId;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getGameNameId() {
        return gameNameId;
    }

    public void setGameNameId(String gameNameId) {
        this.gameNameId = gameNameId;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
