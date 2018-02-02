package com.whgtf.sportsbook.restApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.whgtf.sportsbook.model.ResponseEvent;
import spark.Request;

import java.lang.reflect.Type;
import java.util.*;

import static com.whgtf.sportsbook.restApi.JsonUtil.json;
import static spark.Spark.*;
/**
 * Created by juri on 25/11/2016.
 */
public class RestMain {
    public static void main(String[] args) {

        post("/events", (req, res) -> {
            Map<String, String> body = parseBody(req);
            MessageError messageError = new MessageError();
            HashMap<String, String> settingsList = new HashMap<String, String>();
            if (!checkAndAddArgument(body, settingsList, "eventName")) {
                res.status(400);
                return new ResponseError("missed argument "+ "eventName"+": " +
                        ""+settingsList.get("eventName") + " {example: anyname}");}

            if (!checkAndAddArgument(body, settingsList, "eventType")) {
                res.status(400);
                return new ResponseError("missed argument "+ "eventType"+": "
                        +settingsList.get("eventType")+ " {example: valid for Football - inplay or prematch}");}

            if (!checkAndAddArgument(body, settingsList, "startTime")) {
                res.status(400);
                return new ResponseError("missed argument "+ "startTime"+": "
                        +settingsList.get("startTime")+ " {example: YESTERDAY, TODAY, INFIVEMINUTES, MIDDAY, ONEHOURFROMNOW, SIXHOURSFROMNOW, TOMORROW}");}

            if (!checkAndAddArgument(body, settingsList, "competition")) {
                res.status(400);
                return new ResponseError("missed argument "+ "competition"+": "
                        +settingsList.get("competition")+ " {example: \"English Premier League\", Cheltenham, \"Betfred Gymcrack\"}");}

            if (!checkAndAddArgument(body, settingsList, "sport")) {
                res.status(400);
                return new ResponseError("missed argument "+ "sport"+": "
                        +settingsList.get("sport")+ " {example: football, horseracing, softball, canoeing}");}

            if (!checkAndAddArgument(body, settingsList, "env")) {
                res.status(400);
                return new ResponseError("missed argument "+ "env"+": "
                        +settingsList.get("env")+ " {example: dev, pp1, pp2, pp3}");}

            if (!checkAndAddArgument(body, settingsList, "sportType")) {
                res.status(400);
                return new ResponseError("missed argument "+ "sportType"+": "
                        +settingsList.get("sportType")+ " {example: FOOTBALL, HORSERACING, GENERIC(valid for all sports)}");}

            if (!checkAndAddArgument(body, settingsList, "racingEventType")) {
                res.status(400);
                return new ResponseError("missed argument "+ "racingEventType"+": "+settingsList.get("racingEventType")+
                        " {example: for racing - meetings, antepost, specials}");}

            if (!checkAndAddArgument(body, settingsList, "eventsNumber")) {
                res.status(400);
                res.type("application/json");
                return new ResponseError("missed argument "+ "eventsNumber"+": "+settingsList.get("eventsNumber")+
                        " {example: for Generic only - add number of events}");}

            if (!checkAndAddArgument(body, settingsList, "marketsNumber")) {
                res.status(400);
                return new ResponseError("missed argument "+ "marketsNumber"+": "+settingsList.get("marketsNumber")+
                        " {example: for Generic only - add number of markets per event}");}

            if (!checkAndAddArgument(body, settingsList, "selectionsNumber")) {
                res.status(400);
                return new ResponseError("missed argument "+ "selectionsNumber"+": "+settingsList.get("selectionsNumber")+
                        " {example: for Generic only - add number of selections per market}");}
            List<ResponseEvent> event = new ArrayList<ResponseEvent>();
            event = EventService.createEvent(settingsList, messageError);
            res.type("application/json");
            if (event != null) {
                return event;
            } else {
                res.status(400);
                return exampleMessage("Event creation failed " + messageError.getMessage()); //
            }
        }, json());

        post("/settle", (req, res) -> {
            Map<String, String> body = parseBody(req);
            MessageError messageError = new MessageError();
            HashMap<String, String> settingsList = new HashMap<String, String>();

            if (!checkAndAddArgument(body, settingsList, "env")) {
                res.status(400);
                return new ResponseError("missed argument "+ "env"+": "+settingsList.get("env")+ " {example: dev, pp1, pp2, pp3}");}

            ResponseEvent event = EventService.SettleAllEvents(settingsList, messageError);
            res.type("application/json");
            if (event != null) {
                return event;
            } else {
                res.status(400);
                return exampleMessage("Event creation failed " + messageError.getMessage());
            }
        }, json());
    }

    private static Map<String, String> parseBody(Request request) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        return gson.fromJson(request.body(), type);
    }

    private static boolean checkAndAddArgument ( Map<String, String> body,HashMap<String,
            String> settingsList, String argument) {
        if (body.get(argument) != null && body.get(argument).length()> 0) {
            settingsList.put(argument, body.get(argument));
            return true;
        } else {
            return false;
        }
    }

    public static ResponseError exampleMessage(String message) {

        ResponseError errorsList = new ResponseError();
        errorsList.addErrorExampleMessage(new MessageError(message));
        errorsList.addErrorExampleMessage(new MessageError("remove not mandatory arguments or add valid values"));
        errorsList.addErrorExampleMessage(new MessageError("eventName: any - mandatory argument"));
        errorsList.addErrorExampleMessage(new MessageError("eventType: inplay/prematch"));
        errorsList.addErrorExampleMessage(new MessageError("startTime: YESTERDAY, TODAY, INFIVEMINUTES, MIDDAY, ONEHOURFROMNOW, SIXHOURSFROMNOW, TOMORROW - mandatory argument"));
        errorsList.addErrorExampleMessage(new MessageError("competition: competition name (check hostname:port/competitions) - mandatory argument"));
        errorsList.addErrorExampleMessage(new MessageError("sport: football, horseracing, softball, canoeing - mandatory argument"));
        errorsList.addErrorExampleMessage(new MessageError("sportType: FOOTBALL, HORSERACING, GENERIC - mandatory argument"));
        errorsList.addErrorExampleMessage(new MessageError("env: dev, pp1, pp2, pp3 - mandatory argument"));
        errorsList.addErrorExampleMessage(new MessageError("racingEventType: meetings, antepost, specials - mandatory only for HORSERACING ->"));
        errorsList.addErrorExampleMessage(new MessageError("eventsNumber: number of events (1,2..100) - mandatory only for GENERIC only"));
        errorsList.addErrorExampleMessage(new MessageError("marketsNumber: number of markets per event (1,2..100) - mandatory only for GENERIC only"));
        errorsList.addErrorExampleMessage(new MessageError("selectionsNumber: number of selections per market (1,2..100) - mandatory only for GENERIC only"));

        return errorsList;
    }
}
