package com.whgtf.sportsbook.main.beans;

import org.springframework.stereotype.Component;

/**
 * Created by jgvera on 06/12/2016.
 */
@Component
public class SquizCarouselData {

    private HomePageIcon[] data;

    public HomePageIcon[] getData() {
        return data;
    }

    public void setData(HomePageIcon[] data) {
        this.data = data;
    }

}
