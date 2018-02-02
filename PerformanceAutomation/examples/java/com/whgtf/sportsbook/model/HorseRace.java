package com.whgtf.sportsbook.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javierg on 23/06/2016.
 */
public class HorseRace {

    private String id;

    private String time;

    private String place;

    private List<Selection> selectionList;

    public HorseRace(final String id) {
        this.id = id;
        time = StringUtils.EMPTY;
        place = StringUtils.EMPTY;
        selectionList = new ArrayList<>();
    }

    public HorseRace() {
        this.id = "-1";
        time = StringUtils.EMPTY;
        place = StringUtils.EMPTY;
        selectionList = new ArrayList<>();
    }

    public void addSelection(final Selection selection) {
        if(!isSelectionAdded(selection.getPdsId()))
            selectionList.add(selection);
    }

    private boolean isSelectionAdded(final String id) {
        for (Selection selection:selectionList) {
            if(selection.getPdsId().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public List<Selection> getSelectionList(){
        return selectionList;
    }
}
