package com.whgtf.sportsbook.main.beans;

import org.springframework.stereotype.Component;

/**
 * Created by jgvera on 06/12/2016.
 */
@Component
public class SquizCarouselElements {

    private HomePageIcon[] icons;

    public HomePageIcon[] getIcons() {
        return icons;
    }

    public void setIcons(HomePageIcon[] icons) {
        this.icons = icons;
    }

}
