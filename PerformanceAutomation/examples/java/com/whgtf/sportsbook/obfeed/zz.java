package com.whgtf.sportsbook.obfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.zeroturnaround.zip.ZipUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Juri on 11/11/2016.
 */
public class zz {

    static final Logger logger = LoggerFactory.getLogger(zz.class);
    static Integer t;
    static Integer oldT;

    public static class MyKey {
        private int number;
        private String backofficeName;
        private String requiredName;

        public MyKey(int number, String backofficeName, String requiredName) {
            this.number = number;
            this.backofficeName = backofficeName;
            this.requiredName = requiredName;
        }

        public int getNumber() {
            return number;
        }

        public String getbackofficeName() {
            return backofficeName;
        }

        public String getrequiredName() {
            return requiredName;
        }
        // ctors, getters, etc.
    }


    public static Map<Integer, String> footballSpecialBettingGoalFirstLastLive() {
        Map<Integer, String> specialBettingGoalFirstLastGoalMapLive = new LinkedHashMap<>();
        specialBettingGoalFirstLastGoalMapLive.put(0, "First Goalscorer Live");
        specialBettingGoalFirstLastGoalMapLive.put(1, "Last Goalscorer Live");
        specialBettingGoalFirstLastGoalMapLive.put(2, "Player To Score 2 Or More Live");
        specialBettingGoalFirstLastGoalMapLive.put(3, "Hatrick Live");
        return specialBettingGoalFirstLastGoalMapLive;
    }

    public static Map<String, String> footballSpecialBettingGoalXLive() {
        Map<String, String> specialBettingGoalXLive = new LinkedHashMap<>();
        specialBettingGoalXLive.put("Half Correct Score Live", "|1st Half Correct Score| |Live");
        specialBettingGoalXLive.put("Hatrick Live", "Hattrick| |Live");
        return specialBettingGoalXLive;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, InterruptedException {

    //    System.out.println(getResourceFiles("/"));
        setTemperature (60);
        System.out.println(footballSpecialBettingGoalXLive().get("Half Correct Score Live"));
        System.out.println(footballSpecialBettingGoalXLive().get(footballSpecialBettingGoalFirstLastLive().get(3)));
    }

          public static void setTemperature(Integer temperature) {

               oldT = t;
                 t = temperature;

                logger.debug("Temperature set to {}. Old temperature was {}.", t, oldT);

               if(temperature.intValue() > 50) {
                      logger.info("Temperature has risen above 50 degrees.");
                    }
              }


    private static List<String> getResourceFiles( String path ) throws IOException {
        List<String> filenames = new ArrayList<>();

        try(
                InputStream in = getResourceAsStream( path );
                BufferedReader br = new BufferedReader( new InputStreamReader( in ) ) ) {
            String resource;

            while( (resource = br.readLine()) != null ) {
                filenames.add( resource );
            }
        }

        return filenames;
    }

    private static InputStream getResourceAsStream( String resource ) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream( resource );

        return in == null ? Thread.currentThread().getContextClassLoader().getResourceAsStream( resource ) : in;
    }

    private static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


}
