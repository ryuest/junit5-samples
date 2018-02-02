package com.whgtf.sportsbook.restApi;

import com.whgtf.sportsbook.common.EventMain;
import com.whgtf.sportsbook.common.SettleMain;
import com.whgtf.sportsbook.model.ResponseEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by juri on 25/11/2016.
 */
public class EventService {

    public static List<ResponseEvent> createEvent(HashMap<String, String> settingsList, MessageError messageError) {
        try {
            EventMain eventMain = new EventMain();
            List<ResponseEvent> event = new ArrayList<ResponseEvent>();
            event = eventMain.createEvent(settingsList, messageError);
            return event;
        } catch (Exception e) {
            return null;
        }
    }

    public static ResponseEvent SettleAllEvents(HashMap<String, String> settingsList, MessageError messageError) {
        try {
            SettleMain settleMain = new SettleMain();
            ResponseEvent event = settleMain.settleEvent(settingsList, messageError);
            return event;
        } catch (Exception e) {
            return null;
        }
    }
}
