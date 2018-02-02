package com.whgtf.sportsbook.obfeed.request;

/**
 * Created by Juri on 21/10/2016.
 */

import com.whgtf.sportsbook.common.PropArgs;
import com.whgtf.sportsbook.model.ResponseEvent;
import com.whgtf.sportsbook.model.ResponseMarket;
import com.whgtf.sportsbook.model.ResponseSelection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

     org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Parser.class.getName());

    public ResponseEvent getOpenbetID(String response, String env) throws ParserConfigurationException, SAXException, IOException {PropArgs props = new PropArgs(env);
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = factory.newDocumentBuilder();
    InputSource inStream = new InputSource();
    inStream.setCharacterStream(new StringReader(response));

        logger.debug(response);

    Pattern pattern = Pattern.compile("Error*");
    Matcher matcher = pattern.matcher(response);
    if (matcher.find()) {
        logger.debug("Error in POST Oxifeed with message:"
                + matcher.group(0));
    }

    Pattern pattern2 = Pattern.compile("Failed*");
    Matcher matcher2 = pattern2.matcher(response);
    if (matcher2.find()) {
        logger.info("Failed in POST Oxifeed with message:"
                + matcher2.group(0));
    }
    Document doc = db.parse(inStream);
    ArrayList<String> list = new ArrayList<String>();

        BufferedWriter bufferedWriterID = Files.newBufferedWriter(Paths.get("EventData" + "/" + env + "/IDList.txt"),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    String openbetId = "empty";
    NodeList nl = doc.getElementsByTagName("openbetId");
        ResponseEvent responseEvent = new ResponseEvent();


    for (int i = 0; i < nl.getLength(); i++) {
        if (nl.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

            org.w3c.dom.Element nameElement = (org.w3c.dom.Element) nl
                    .item(i);
            openbetId = nameElement.getFirstChild().getNodeValue().trim();

            list.add(openbetId);

            int id = Integer.parseInt(openbetId);

            if (id > 5000) {
                if (id < props.getMarketCurrentNumbers()) {
                    responseEvent.eventId = openbetId;
                } else if (id < props.getSelectionCurrentNumbers()) {
                    responseEvent.addMarket(new ResponseMarket(openbetId));
                } else {
                    responseEvent.addSelection(new ResponseSelection(openbetId));
                }
            }

            try {
                bufferedWriterID.write(list.get(list.size() - 1));
                bufferedWriterID.newLine();
                Thread.sleep(100);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    try {
        bufferedWriterID.close();
    } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
    return responseEvent;
}

}
