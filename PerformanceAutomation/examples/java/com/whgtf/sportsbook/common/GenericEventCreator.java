package com.whgtf.sportsbook.common;

import com.whgtf.sportsbook.impl.GenericMapsImpl;
import com.whgtf.sportsbook.interfaces.GenericMaps;
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
 * Created by juri on 22/11/2016.
 */
public class GenericEventCreator {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GenericEventCreator.class.getName());

    Request request = new Request();
    ResponseFiles responseFiles = new ResponseFiles();

    GenericMaps genericMaps = new GenericMapsImpl();

    public List<ResponseEvent> createGenericEvent(EventSettings eventSettings) throws IOException, InterruptedException, ParserConfigurationException, SAXException {

        List<ResponseEvent> event = new ArrayList<ResponseEvent>();
        if (eventSettings.sport.equals("HORSERACING"))
        eventSettings.setHorseRacing(true);
        else if (eventSettings.sport.equals("FOOTBALL"))
            eventSettings.setFootball(true);

        eventSettings.setGeneric(true);
        RestTemplateCreator templateCreator = new RestTemplateCreator(eventSettings.getEnv());

        int eventsNumber = Integer.valueOf(eventSettings.eventsNumber);
        int marketsNumber = Integer.valueOf(eventSettings.marketsNumber);
        int selectionsNumber = Integer.valueOf(eventSettings.selectionsNumber);

        try {
            for (int x=0; x<eventsNumber; x++) {
                eventSettings.setStandard(true);
                if (eventSettings.eventType.toUpperCase().replaceAll(" ", "").equals("INPLAY")) {
                    event.add(request.sendRequest(templateCreator.createEventRequest(eventSettings, genericMaps.setGenericMarketInPlayMap(marketsNumber),
                            genericMaps.setGenericSelectionMap(selectionsNumber), true), eventSettings.env));
                    responseFiles.splitIds(true, eventSettings.env);
                } else {
                    event.add(request.sendRequest(templateCreator.createEventRequest(eventSettings, genericMaps.setGenericMarketPreMatchMap(marketsNumber),
                            genericMaps.setGenericSelectionMap(selectionsNumber), false), eventSettings.env));
                    responseFiles.splitIds(false, eventSettings.env);
                }
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        logger.info("Event adding is finished");
        return event;
    }

}
