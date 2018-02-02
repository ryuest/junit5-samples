package com.whgtf.sportsbook.common;

import com.whgtf.sportsbook.impl.FootballMarketsMapsImp;
import com.whgtf.sportsbook.interfaces.FootballMarketsMaps;
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


/**
 * Created by Juri on 14/11/2016.
 */
public class FootballEventCreator {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FootballEventCreator.class.getName());

    Request request = new Request();
    ResponseFiles responseFiles = new ResponseFiles();

    FootballMarketsMaps footballMarketsMaps = new FootballMarketsMapsImp();

    public List<ResponseEvent> createFootballEvent(EventSettings eventSettings) throws IOException, InterruptedException, ParserConfigurationException, SAXException {


        RestTemplateCreator templateCreator = new RestTemplateCreator(eventSettings.getEnv());

        List<ResponseEvent> event = new ArrayList<ResponseEvent>();
        if (eventSettings.getSportMarketList().get(0).toLowerCase().equals("special")) {
            eventSettings.setSpecial(true);
        }

        eventSettings.setFootball(true);

        if ((eventSettings.sport.equals("american"))) {

            eventSettings.setWinDrawWin(true);
            event.add(request.sendRequest(templateCreator.createEventRequest(eventSettings, footballMarketsMaps.americanFootballWinWin(),
                    footballMarketsMaps.getHomeDrawAwayName(), true), eventSettings.env));
            eventSettings.setWinDrawWin(false);
            responseFiles.splitIds(true, eventSettings.env);


            eventSettings.setHandicap(true);
            event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.americanFootballHandicap(),
                    footballMarketsMaps.getHandicapName(), true), eventSettings.env));
            eventSettings.setHandicap(false);
            responseFiles.splitIds(true, eventSettings.env);

            eventSettings.setSpecial(true);
            eventSettings.setHandicap(true);
            event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.americanFootballOverUnder(),
                    footballMarketsMaps.getHomeAway(), true), eventSettings.env));
            responseFiles.splitIds(true, eventSettings.env);

        } else {


            eventSettings.setWinDrawWin(true);

            // Add event with 2 Top Markets -> 90 Minutes pre-match
            event.add(request.sendRequest(templateCreator.createEventRequest(eventSettings,
                    footballMarketsMaps.topMarkets(), footballMarketsMaps.getHomeDrawAwayName(), false), eventSettings.env));
            responseFiles.splitIds(false, eventSettings.env);
            // and Match Betting Live inplay
            event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings,
                    footballMarketsMaps.topMarketsLive(), footballMarketsMaps.getHomeDrawAwayName(), true), eventSettings.env));
            responseFiles.splitIds(true, eventSettings.env);
            eventSettings.setWinDrawWin(false);

            if (!eventSettings.eventType.toUpperCase().replaceAll(" ", "").equals("INPLAY")) {
                // Pre-Match markets -> live=false
                for (int x = 0; x < eventSettings.getSportMarketList().size(); x++) {
                    event.add(footballMarketsTypeSend(eventSettings, templateCreator, eventSettings.getSportMarketList().get(x)));
                    responseFiles.splitIds(false, eventSettings.env);
                }
            } else {
                // InPlay markets -> live=true
                for (int x = 0; x < eventSettings.getSportMarketList().size(); x++) {
                    event.add(footballMarketsTypeSendLive(eventSettings, templateCreator, eventSettings.getSportMarketList().get(x)));
                    responseFiles.splitIds(true, eventSettings.env);
                }
            }

            if (eventSettings.getSportMarketList().get(0).toLowerCase().equals("special")) {
                boolean live = false;
                List<ResponseEvent> eventSpecials = new ArrayList<ResponseEvent>();
                if (eventSettings.eventType.toUpperCase().replaceAll(" ", "").equals("INPLAY")) {
                    eventSpecials = footballMarketsSpecialLive(eventSettings, templateCreator, eventSettings.getSportMarketList().get(1));
                } else {
                    // @TODO
                }

                // add Special Event objects to Main Event List
                for (int y = 0; y < eventSpecials.size(); y++) {
                    event.add(eventSpecials.get(y));
                }

            } else {
                eventSettings.setStandard(true);
                event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsStandardTwoSelections(),
                        footballMarketsMaps.getHomeAwayName(), false), eventSettings.env));
                responseFiles.splitIds(false, eventSettings.env);
            }

            eventSettings.setSpecial(true);
            eventSettings.setHandicap(true);
            event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsOverUnderLive(),
                    footballMarketsMaps.getHomeAway(), true), eventSettings.env));
            responseFiles.splitIds(true, eventSettings.env);

        }
            logger.info("Event adding is finished");
            return event;
        }


    public ResponseEvent footballMarketsTypeSend(EventSettings eventSettings, RestTemplateCreator templateCreator, String type) {
        ResponseEvent event = null;
        try {
            switch (type.toLowerCase()) {
                case "windrawwin":
                    eventSettings.setWinDrawWin(true);
                    event = request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsWinDrawWin(),
                            footballMarketsMaps.getHomeDrawAwayName(), false), eventSettings.env);
                    eventSettings.setWinDrawWin(false);
                    break;

                case "standard":
                    eventSettings.setStandard(true);
                    event = request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsStandardTreeSelections(),
                            footballMarketsMaps.getHomeDrawAwayName(), false), eventSettings.env);
                    eventSettings.setStandard(false);
                    break;

                case "correctscore":
                    eventSettings.setCorrectScore(true);
                    event = request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings,  footballMarketsMaps.footballMarketsCorrectScoreSortTypes(),
                            footballMarketsMaps.getCorrectScoreNames(), false), eventSettings.env);
                    eventSettings.setCorrectScore(false);
                    break;

                case "scorers":
                    eventSettings.setScorers(true);
                    event = request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsScorersThreeSelections(),
                            footballMarketsMaps.getScorersNames(), false), eventSettings.env);
                    eventSettings.setScorers(false);
                    break;

                case "handicap":
                    eventSettings.setHandicap(true);
                    event = request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsHandicaps(),
                            footballMarketsMaps.getHandicapName(), false), eventSettings.env);
                    eventSettings.setHandicap(false);
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return event;
    }

    public ResponseEvent footballMarketsTypeSendLive(EventSettings eventSettings, RestTemplateCreator templateCreator, String type) {
        ResponseEvent event = null;
        try {
            switch (type.toLowerCase()) {
                case "windrawwin":
                    eventSettings.setWinDrawWin(true);
                    event = request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsWinDrawWinLive(),
                            footballMarketsMaps.getHomeDrawAwayName(), true), eventSettings.env);
                    eventSettings.setWinDrawWin(false);
                    break;

                case "standard":
                    eventSettings.setStandard(true);
                    event = request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsStandardTwoSelectionsLive(),
                            footballMarketsMaps.getHomeAwayName(), true), eventSettings.env);
                    eventSettings.setStandard(false);
                    break;

                case "correctscore":
                    eventSettings.setCorrectScore(true);
                    event = request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings,  footballMarketsMaps.footballMarketsCorrectScoreSortTypesLive(),
                            footballMarketsMaps.getCorrectScoreNames(), true), eventSettings.env);
                    eventSettings.setCorrectScore(false);
                    break;

                case "scorers":
                    eventSettings.setScorers(true);
                    event = request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsScorersThreeSelectionsLive(),
                            footballMarketsMaps.getScorersNames(), true), eventSettings.env);
                    eventSettings.setScorers(false);
                    break;

                case "handicap":
                    eventSettings.setHandicap(true);
                    event = request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsHandicapsLive(),
                            footballMarketsMaps.getHandicapName(), true), eventSettings.env);
                    eventSettings.setHandicap(false);
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return event;
    }

    public List<ResponseEvent> footballMarketsSpecialLive(EventSettings eventSettings, RestTemplateCreator templateCreator, String type) throws InterruptedException {
        List<ResponseEvent> event = new ArrayList<ResponseEvent>();
        try {
            switch (type.toLowerCase()) {
                case "specialset1":
                    eventSettings.setStandard(false);
                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballSpecialBettingLive(),
                            footballMarketsMaps.getHomeAwayName(), true), eventSettings.env));

                    eventSettings.setHandicap(true);
                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsHandicapsLive(),
                            footballMarketsMaps.getHandicapName(), true), eventSettings.env));
                    eventSettings.setHandicap(false);
                    responseFiles.splitIds(true, eventSettings.env);
                    break;

                case "specialset2":
                    eventSettings.setStandard(false);
                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballSpecialBettingLive(),
                            footballMarketsMaps.getHomeAwayName(), true), eventSettings.env));

                    eventSettings.setCorrectScore(true);
                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings,
                            footballMarketsMaps.footballMarketsCorrectScoreSortTypesSpecialLive(),
                            footballMarketsMaps.getCorrectScoreNames(), true), eventSettings.env));
                    eventSettings.setCorrectScore(false);

                    eventSettings.setScorers(true);
                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballSpecialBettingGoalFirstLastLive(),
                            footballMarketsMaps.getScorersNames(), true), eventSettings.env));
                    eventSettings.setSpecial(true);

                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballSpecialBettingGoalXLive(),
                            footballMarketsMaps.getScorersNames(), true), eventSettings.env));
                    eventSettings.setSpecial(false);
                    eventSettings.setScorers(false);

                    eventSettings.setHandicap(true);
                    event.add(request.sendRequest(templateCreator.createAddMoreMarketsRequest(eventSettings, footballMarketsMaps.footballMarketsHandicapsLive(),
                            footballMarketsMaps.getHandicapName(), true), eventSettings.env));
                    eventSettings.setHandicap(false);
                    responseFiles.splitIds(true, eventSettings.env);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return event;
    }

}
