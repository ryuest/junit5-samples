package com.whgtf.sportsbook.common;

import com.whgtf.sportsbook.model.EventSettings;
import com.whgtf.sportsbook.model.ResponseEvent;
import com.whgtf.sportsbook.restApi.MessageError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Juri on 10/11/2016.
 */
public class EventMain {

    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EventMain.class.getName());

    public static void checkSettings(Map<String, String> settingsList){

        switch (settingsList.get("sportType").toUpperCase().replaceAll(" ", "")) {
            case "FOOTBALL":
                checkForNull(settingsList, "eventType");
                checkForNull(settingsList, "startTime");
                checkForNull(settingsList, "env");
                checkForNull(settingsList, "competition");
                checkForNull(settingsList, "sport");
                break;
            case "HORSERACING":
                checkForNull(settingsList, "startTime");
                checkForNull(settingsList, "env");
                checkForNull(settingsList, "competition");
                checkForNull(settingsList, "sport");
                checkForNull(settingsList, "racingEventType");
                break;
            case "GENERIC":
                checkForNull(settingsList, "eventType");
                checkForNull(settingsList, "startTime");
                checkForNull(settingsList, "env");
                checkForNull(settingsList, "competition");
                checkForNull(settingsList, "sport");
                checkForNull(settingsList, "eventsNumber");
                checkForNull(settingsList, "marketsNumber");
                checkForNull(settingsList, "selectionsNumber");
                break;
            default : throw new IllegalArgumentException("Not valid sportType: " + settingsList.get("sportType").toLowerCase()
                    +". Valid examples FOOTBALL, HORSERACING, GENERIC");
        }
    }

    public static void checkForNull (Map<String, String> settingsList, String argument) {
        if (settingsList.get(argument) == null) {
            logger.info("missed needed argument "+ argument+": "+settingsList.get(argument));
            throw new EmptyStackException();
        }
    }

    public static void main(String[] args)  {

        HashMap<String, String> settingsList = new HashMap<String, String>();
        settingsList.put("sportType", getVMargs("sportType"));              // FOOTBALL, HORSERACING, GENERIC(valid for all sports)
        settingsList.put("eventType", getVMargs("eventType"));              // valid for Football -> inplay or prematch
        settingsList.put("eventName", getVMargs("eventName"));              // any name for event
        settingsList.put("startTime", getVMargs("startTime"));              // YESTERDAY, TODAY, INFIVEMINUTES, MIDDAY, ONEHOURFROMNOW, SIXHOURSFROMNOW, TOMORROW
        settingsList.put("env", getVMargs("env"));                          // dev, pp1, pp2, pp3
        settingsList.put("competition", getVMargs("competition"));          // "English Premier League", Cheltenham, "Betfred Gymcrack"
        settingsList.put("sport", getVMargs("sport"));                      // football, horseracing, softball, canoeing
        settingsList.put("racingEventType", getVMargs("racingEventType"));  // for racing -> meetings, antepost, specials
        settingsList.put("eventsNumber", getVMargs("eventsNumber"));        // for Generic only -> number of events
        settingsList.put("marketsNumber", getVMargs("marketsNumber"));      // for Generic only -> number of markets per event
        settingsList.put("selectionsNumber", getVMargs("selectionsNumber"));// for Generic only -> number of selections per market

        // create event
        createEventStatic (settingsList);
    }

    public static List<ResponseEvent> createEventStatic (HashMap<String, String> settingsList)  {
        try {
        checkSettings(settingsList);
        EventSettings eventSettings = new EventSettings();

        // set settings for event
        eventSettings.setEventVariables(settingsList);
            return  sportTypeCreateStatic(eventSettings, settingsList.get("sportType"));
        } catch (IOException | InterruptedException | ParserConfigurationException | EmptyStackException |  SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getVMargs (String argument) {
        String variable = null;
        try {
            variable = System.getProperty(argument);
        } catch (NullPointerException e) {
            return variable;
        }
        return variable;
    }

    public List<ResponseEvent> sportTypeCreate(EventSettings eventSettings, String sportType) throws IOException,
            InterruptedException, ParserConfigurationException, SAXException{

        List<ResponseEvent> event = new ArrayList<ResponseEvent>();
        switch(sportType.toUpperCase().replaceAll(" ", "")) {
            case "FOOTBALL":
                FootballEventCreator footballEventCreator = new FootballEventCreator();
                event = footballEventCreator.createFootballEvent(eventSettings);
                break;
            case "HORSERACING":
                HorseRacingEventCreator horseRacingEventCreator = new HorseRacingEventCreator();
                event = horseRacingEventCreator.createRacingEvent(eventSettings);
                break;
            case "GENERIC":
                GenericEventCreator genericEventCreator = new GenericEventCreator();
                event = genericEventCreator.createGenericEvent(eventSettings);
                break;
            default : throw new IllegalArgumentException("Not valid sportType: " + eventSettings.sportType.toLowerCase()
                    +". Valid examples FOOTBALL, HORSERACING, GENERIC");
        }
        logger.info("<----------------->");
        logger.info("EVENT ID: "+getLastEventId(eventSettings.env));
        logger.info("<----------------->");
        return event;
    }

    public static List<ResponseEvent> sportTypeCreateStatic(EventSettings eventSettings, String sportType) throws IOException,
            InterruptedException, ParserConfigurationException, SAXException{
        List<ResponseEvent> event = new ArrayList<ResponseEvent>();
        switch(sportType.toUpperCase().replaceAll(" ", "")) {
            case "FOOTBALL":
                FootballEventCreator footballEventCreator = new FootballEventCreator();
                event = footballEventCreator.createFootballEvent(eventSettings);
                break;
            case "HORSERACING":
                HorseRacingEventCreator horseRacingEventCreator = new HorseRacingEventCreator();
                event = horseRacingEventCreator.createRacingEvent(eventSettings);
                break;
            case "GENERIC":
                GenericEventCreator genericEventCreator = new GenericEventCreator();
                event = genericEventCreator.createGenericEvent(eventSettings);
                break;
            default : throw new IllegalArgumentException("Not valid sportType: " + eventSettings.sportType.toLowerCase()
                    +". Valid examples FOOTBALL, HORSERACING, GENERIC");
        }
        logger.info("<----------------->");
        logger.info("EVENT ID: "+getLastEventId(eventSettings.env));
        logger.info("<----------------->");
        return event;
    }

        public List<ResponseEvent> createEvent (HashMap<String, String> settingsList, MessageError messageError) throws InterruptedException,
                ParserConfigurationException, SAXException, IOException {

            EventSettings eventSettings = new EventSettings();
            eventSettings.setEventVariables(settingsList);
            try {
               return  sportTypeCreate(eventSettings, settingsList.get("sportType"));
            } catch (IllegalArgumentException e) {
                logger.info(e.getLocalizedMessage());
                messageError.setMessage(e.getLocalizedMessage());
            } catch (Exception e) {
                logger.info(e.getMessage());
                messageError.setMessage(e.getMessage());
            }
            return null;
        }

    public static String getLastEventId(String env) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader eventIDList = Files.newBufferedReader(Paths.get("EventData" + "/" + env.toLowerCase().trim() + "/" + "EventIDList.txt"));
            Scanner scanner = new Scanner(eventIDList);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (Exception e) {
            logger.info("Cannot find EventIDList.txt");
        }
        try {
            return (list.get(list.size() - 1));
        } catch (ArrayIndexOutOfBoundsException w) {
            logger.info("Cannot find any ID in EventIDList.txt, maybe need update the file");
            logger.info("Printing Last Response from OBFEED");
            try {
                BufferedReader lastResponse = Files.newBufferedReader(Paths.get("EventData" + "/" + env.toLowerCase().trim() + "/" + "LastResponse.txt"));
                String line = null;
                while ((line = lastResponse.readLine()) != null) {
                    logger.info(line);
                }

            } catch (Exception e) {
                logger.info("Cannot find LastResponse.txt");
            }
        }
        return (list.get(list.size() - 1));
    }

}
