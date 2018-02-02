package com.whgtf.sportsbook.common;

import com.whgtf.sportsbook.impl.HorseRacingMarketsMapsImp;
import com.whgtf.sportsbook.interfaces.HorseRacingMarketsMaps;
import com.whgtf.sportsbook.model.EventSettings;
import com.whgtf.sportsbook.model.ResponseEvent;
import com.whgtf.sportsbook.obfeed.request.Request;
import com.whgtf.sportsbook.templates.RestTemplateCreator;
import com.whgtf.sportsbook.utils.ResponseFiles;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Juri on 18/11/2016.
 */
public class HorseRacingEventCreator {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HorseRacingEventCreator.class.getName());

    Request request = new Request();
    ResponseFiles responseFiles = new ResponseFiles();

    HorseRacingMarketsMaps horseRacingMarketsMaps = new HorseRacingMarketsMapsImp();

    public List<ResponseEvent> createRacingEvent(EventSettings eventSettings) throws IOException, InterruptedException, ParserConfigurationException, SAXException {

        List<ResponseEvent> event = new ArrayList<ResponseEvent>();

        eventSettings.setHorseRacing(true);

        RestTemplateCreator templateCreator = new RestTemplateCreator(eventSettings.getEnv());

        try {
            switch (eventSettings.racingEventType.toLowerCase()) {
                case "meetings":
                    eventSettings.setMeetings(true);
                    event.add(request.sendRequest(templateCreator.createEventRequest(eventSettings, horseRacingMarketsMaps.placeInsure(),
                            horseRacingMarketsMaps.getHorses(), false), eventSettings.env));

                    eventSettings.setWinDrawWin(true);
                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, horseRacingMarketsMaps.winWin(),
                            horseRacingMarketsMaps.getHomeAwayName(), false), eventSettings.env));
                    eventSettings.setWinDrawWin(false);

                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, horseRacingMarketsMaps.meetingsMarketsForecast(),
                            horseRacingMarketsMaps.getHorses(), false), eventSettings.env));

                    eventSettings.setMeetings(false);
                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, horseRacingMarketsMaps.meetingsMarkets(),
                            horseRacingMarketsMaps.getRacingGenericSelectionText(), false), eventSettings.env));

                    Map<Integer, String> horsesNamesMap = horseRacingMarketsMaps.getHorses();
                    horsesNamesMap.remove(12);
                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, horseRacingMarketsMaps.bettingWithout(),
                            horsesNamesMap, false), eventSettings.env));

               //     horsesNamesMap.remove(11);
               //     System.out.println("removed " +horsesNamesMap);
               //     request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, horseRacingMarketsMaps.bettingWithout(), horsesNamesMap, false));

               //     horsesNamesMap.remove(10);
              //      System.out.println("removed " +horsesNamesMap);
               //     request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, horseRacingMarketsMaps.bettingWithout(), horsesNamesMap, false));
                    break;

                case "antepost":
                    eventSettings.setAntepost(true);
                    event.add(request.sendRequest(templateCreator.createEventRequest(eventSettings,  horseRacingMarketsMaps.antepostSpecials(),
                            horseRacingMarketsMaps.getHorses(), false), eventSettings.env));
                    eventSettings.setStandard(false);
                    break;

                case "specials":
                    event.add(request.sendRequest(templateCreator.createEventRequest(eventSettings,  horseRacingMarketsMaps.antepostSpecials(),
                            horseRacingMarketsMaps.getRacingSpecialsSelectionText(), false), eventSettings.env));
                    break;
                default : throw new IllegalArgumentException("Not valid racingEventType: " + eventSettings.racingEventType.toLowerCase()
                        + ". Valid examples: meetings, antepost, specials");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        responseFiles.splitIds(false, eventSettings.env);

        logger.info("Event adding is finished");
        return event;
    }



}
