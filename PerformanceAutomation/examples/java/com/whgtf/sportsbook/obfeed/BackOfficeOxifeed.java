package com.whgtf.sportsbook.obfeed;


import com.google.common.base.CharMatcher;
import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.reader.PropertyPomReader;
import com.whgtf.sportsbook.templates.TemplateCreator;
import com.whgtf.sportsbook.utils.Timer;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by javierg on 08/09/2016.
 */


public class BackOfficeOxifeed {

    private static BackOfficeOxifeed instance = null;

    private static String oxifeedUrl;

    private static String user;

    private static String password ;

    private PropertyPomReader propertyPomReader;

    private TemplateCreator templateCreator;

    private static final Logger logger = LoggerFactory.getLogger(BackOfficeOxifeed.class);


    public BackOfficeOxifeed() {
        propertyPomReader = new PropertyPomReader();
        templateCreator = new TemplateCreator();
        oxifeedUrl = propertyPomReader.getOxifeedUrl();
        user = propertyPomReader.getOxifeedUser();
        password = propertyPomReader.getOxifeedPass();
    }

    public static BackOfficeOxifeed getInstance() {
        if(instance == null) {
            instance = new BackOfficeOxifeed();
        }
        return instance;
    }


    public Event addEvent(Event event) {

        String request = templateCreator.createRequest(event);
        logger.info("request--> " + request);
        String response = sendRequest(request);
        logger.info("response--> " + response);
        updateEvent(event,response);

        return event;

    }
    /**
     * Updates the attributes for the given Selection}.
     *
     * @param selection
     * @return a selection with the updated attributes.
     */
    public void updateSelection(Selection selection) {

        String request = templateCreator.createRequest(selection);
        sendRequest(request);

    }

    /**
     * Updates the attributes for the given Selection}.
     *
     * @param market
     * @return a market with the updated attributes.
     */
    public void updateMarket(Market market) {

        String request = templateCreator.createRequest(market);
        sendRequest(request);

    }



    private void updateEvent(Event event, String response) {

        response =
                CharMatcher.JAVA_ISO_CONTROL.removeFrom(response);
        response = response.replaceAll(">\\s*<", "><");


        DocumentBuilder db = null;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(response));
            logger.info("Response is --> " + response);
            Document doc = db.parse(is);
            logger.info("Document doc is --> " + doc.toString());
            logger.info("doc.getElementsByTagName(\"eventId\") --> " +doc.getElementsByTagName("eventId"));
            logger.info("doc.getElementsByTagName(\"eventId\").item(0) --> " +doc.getElementsByTagName("eventId").item(0));
            logger.info("doc.getElementsByTagName(\"eventId\").item(0).getChildNodes().item(0) --> " +doc.getElementsByTagName("eventId").item(0).getChildNodes().item(0).getTextContent());
            logger.info("doc.getElementsByTagName(\"eventId\").item(0).getChildNodes().item(1).getTextContent() --> " +doc.getElementsByTagName("eventId").item(0).getChildNodes().item(1).getTextContent());
            String eventId = doc.getElementsByTagName("eventId").item(0).getChildNodes().item(0).getTextContent();
            String eventExternalId = RandomStringUtils.randomAlphanumeric(6);
            event.setId(Integer.parseInt(eventId));
            event.setExternal_id(eventExternalId);
            NodeList marketList = doc.getElementsByTagName("marketInsert");
            logger.info("marketList.size --> " + marketList.getLength());
            int marketNumber = 0;
            for (int i = 0; i < marketList.getLength(); i++) {
                Node market = marketList.item(i);
                NodeList marketChild = market.getChildNodes();
                int selectionNumber = 0;
                for (int j = 0; j < marketChild.getLength(); j++) {
                    if (marketChild.item(j).getNodeName().equals("marketId")) {
                        event.getMarkets().get(marketNumber).setId(Integer.parseInt(marketChild.item(j).getChildNodes().item(0).getTextContent()));
                    } else if (marketChild.item(j).getNodeName().equals("selectionInsert")) {
                        NodeList selectionData = marketChild.item(j).getChildNodes();
                        for (int k = 0; k < selectionData.getLength(); k++) {
                            if (selectionData.item(k).getChildNodes().item(0).getNodeName().equals("openbetId")) {
                                event.getMarkets().get(marketNumber).getSelections().get(selectionNumber).setId(selectionData.item(k).getChildNodes().item(0).getTextContent());
                            }
                        }
                        selectionNumber++;
                    }
                }
                marketNumber++;

            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sendRequest(String request) {

        try {
            HttpPost postRequest = new HttpPost(this.oxifeedUrl);
            StringEntity entity = new StringEntity(request,
                    ContentType.create("text/xml", "UTF-8"));
            postRequest.setEntity(entity);

            CloseableHttpResponse response;
            int retries = 5;
            int statusCode = 200;
            do {
                response = HttpClients.createDefault().execute(postRequest);
                statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == 503) {
                    // oxifeed service unavailable
                    Timer.sleep(200, TimeUnit.MILLISECONDS);
                    retries--;
                }

            } while(statusCode == 503 && retries > 0);
            if(statusCode == 503)
                throw new RuntimeException("Failed to send the request. The Oxifeed service is unavailable.");
            else {
                return EntityUtils.toString(response.getEntity());
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    private Integer getOpenbetID(String response) {
        Integer openbetId = -1;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            InputSource inStream = new InputSource();
            inStream.setCharacterStream(new StringReader(response));

            Pattern pattern = Pattern.compile("<debug>.*");
            Matcher matcher = pattern.matcher(response);
            if (matcher.find()) {
                throw new RuntimeException(String.format(
                        "Failed to POST Oxifeed with message: %s",
                        matcher.group(0)));
            }

            Document doc = db.parse(inStream);

            NodeList nl = doc.getElementsByTagName("openbetId");
            for (int i = 0; i < 1; i++) {
                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element nameElement = (Element) nl.item(i);
                    openbetId = Integer.valueOf(nameElement.getFirstChild().getNodeValue().trim());
                }
            }

        } catch (DOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return openbetId;

    }







    public void checkEventsReadyForTesting(ArrayList<Event> eventList) {
        PDSCatalogChecker pds = PDSCatalogChecker.getInstance();
        // wait for the events to be present in the PDS catalog
        if(!pds.areEventsInCatalog(eventList, 10))
            throw new RuntimeException("No assertions possible. Some of the events aren't present in the PDS catalog.");


        // give some additional seconds for the frontend.
        Timer.sleep(2, TimeUnit.SECONDS);

    }

    /**
     * Updates the attributes for the given {@link Event}.
     *
     * @param event
     * @return an {@link Event} with the updated attributes.
     */
    public Event updateEvent(Event event) {
        StringBuffer eventUpdateRequest = new StringBuffer();

        // build event request header
        eventUpdateRequest.append("<?xml version='1.0' encoding='UTF-8'?>");
        eventUpdateRequest.append("<oxiFeedRequest version='1.0'>");
        eventUpdateRequest.append(String.format("<auth username='%s' password='%s'/>", this.user, this.password));
        eventUpdateRequest.append("<eventUpdate>");

        // set mandatory fields
        eventUpdateRequest.append(String.format("<eventId><openbetId>%s</openbetId></eventId>", event.getId()));

        // set optional fields
        if (!event.name.isEmpty())
            eventUpdateRequest.append(String.format("<eventName>%s</eventName>", event.name));
        if (!event.sort.isEmpty())
            eventUpdateRequest.append(String.format("<sort>%s</sort>", event.sort));
        if (!event.startTime.isEmpty() && !event.offFlag.isEmpty())
            eventUpdateRequest.append(String.format("<startTime isOff='%s'>%s</startTime>", event.offFlag, event.startTime));
        if (!event.status.isEmpty())
            eventUpdateRequest.append(String.format("<status>%s</status>", event.status.toLowerCase()));
        if (!event.displayed.isEmpty())
            eventUpdateRequest.append(String.format("<display displayed='%s'/>", event.displayed.toLowerCase()));


        // close market request headers
        eventUpdateRequest.append("</eventUpdate>");
        eventUpdateRequest.append("</oxiFeedRequest>");

        sendRequest(eventUpdateRequest.toString());
        //logger.info(String.format("event with id:%s updated", event.id));

        return event;
    }


    public void voidSelectionResult(Selection selection) {
        String selectionResultRequest = String
                .format("<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'>"
                                + "<auth username='%s' password='%s'/>"
                                + "<selectionResult>"
                                + "<selectionId><openbetId>%s</openbetId></selectionId>"
                                + "<result>void</result>"
                                + "</selectionResult></oxiFeedRequest>", this.user,
                        this.password, selection.id);

        sendRequest(selectionResultRequest);
        //logger.info(String.format("selection with id:%s result confirmed", selection.id));
    }

    /**
     * Settles a market in Back Office. All included selections must have a
     * confirmed result.
     *
     * @param {@link Market} to settle
     */
    public void settleMarket(Market market) {
        String selectionResultRequest = String.format(
                "<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'>"
                        + "<auth username='%s' password='%s'/>"
                        + "<marketSettle>"
                        + "<marketId><openbetId>%s</openbetId></marketId>"
                        + "</marketSettle></oxiFeedRequest>", this.user,
                this.password, market.id);

        sendRequest(selectionResultRequest);
        //logger.info(String.format("market with id:%s queued for settlement", market.id));
    }


}
