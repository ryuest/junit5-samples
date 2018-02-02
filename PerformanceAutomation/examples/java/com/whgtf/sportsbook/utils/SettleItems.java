package com.whgtf.sportsbook.utils;

import com.whgtf.sportsbook.model.ResponseEvent;
import com.whgtf.sportsbook.obfeed.settle.EventSettle;
import com.whgtf.sportsbook.obfeed.settle.MarketSettle;
import com.whgtf.sportsbook.obfeed.settle.SelectionResult;
import com.whgtf.sportsbook.obfeed.settle.SelectionSettling;
import com.whgtf.sportsbook.obfeed.update.EventTimeUpdate;
import org.xml.sax.SAXException;
import org.zeroturnaround.zip.ZipUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SettleItems {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SettleItems.class.getName());

    public static String startTime;

    public static Map<String, String> eventsIdMap;
    public static Map<String, String> eventsIdMap_temp;
    public static Map<String, String> marketsIdMap;
    public static Map<String, String> marketsIdMap_temp;
    public static Map<String, String> selectionsIdMap;
    public static Map<String, String> selectionsIdMap_temp;

    public static int times;
    public static int number;

    public void updateEvent(String env) throws InterruptedException {
        try {
        BufferedReader eventIDList = null;

        try {
            eventIDList = Files.newBufferedReader(Paths.get("EventData" + "/" + env.toLowerCase().trim() + "/" + "EventIDList.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -2);
        Date eventTime = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        startTime = dateFormat.format(eventTime);
        int number = 0;
        eventsIdMap_temp = new HashMap<>();
        Scanner scanner = new Scanner(eventIDList);
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            if (a != null) {
                eventsIdMap_temp.put("id" + number, a);
            }
            number = number + 1;
        }
        try {
            eventIDList.close();
        } catch (IOException e2) {
            logger.debug("");
        }
        scanner.close();
        int total = eventsIdMap_temp.size();
        times = total / 700;
        times = times + 1;
        logger.debug("Events update cycles:" + times);
        int numberId = 0;
        for (int y = 0; y < times; y++) {
            eventsIdMap = new HashMap<>();
            int size;
            if (times > 1) {
                size = 701;
            } else {
                size = eventsIdMap_temp.size();
            }
            for (int x = 0; x <= size; x++) {
                try {
                    if (eventsIdMap_temp.get("id" + numberId) != null && !eventsIdMap_temp.get("id" + numberId).isEmpty()) {
                        eventsIdMap.put("id" + x,
                                eventsIdMap_temp.get("id" + numberId));
                    }
                    numberId = numberId + 1;
                } catch (NoSuchElementException e) {
                    logger.debug("eventIDList.txt have problem with mapping");
                }
            }
            try {
                EventTimeUpdate eventUpdate = new EventTimeUpdate();
                eventUpdate.setEventYesterday(eventsIdMap, startTime, "undefined", "no", env);
            } catch (IOException | SAXException | ParserConfigurationException e1) {
                e1.printStackTrace();
            }
        }
        logger.debug(eventsIdMap.size() + " events have been updated");
        } catch (NullPointerException e) {
            logger.debug("Event update skipped");
        }
    }

    public void settleMarket(String itemsType, String env) throws InterruptedException {
        try {
        number = 0;
        String thisLine = null;
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(
                    "EventData" + "/" + env.toLowerCase().trim() + "/" + itemsType + ".txt"));
            BufferedReader br2 = Files.newBufferedReader(Paths.get(
                    "EventData" + "/" + env.toLowerCase().trim() + "/" + itemsType + ".txt"));
            marketsIdMap_temp = new HashMap<>();
            while ((thisLine = br2.readLine()) != null) {
                String a = br.readLine();
                if (a != null) {
                    marketsIdMap_temp.put("id" + number, a);
                }
                number = number + 1;
            }
            br.close();
            br2.close();
        } catch (Exception e) {
            logger.debug("Cannot find" + itemsType + ".txt");
        }
        int total = marketsIdMap_temp.size();
        times = total / 700;
        times = times + 1;
        logger.debug("Markets settle cycles:" + times);
        int numberId = 0;
        for (int y = 0; y < times; y++) {
            marketsIdMap = new HashMap<>();
            int size;
            if (times > 1) {
                size = 701;
            } else {
                size = marketsIdMap_temp.size();
            }
            for (int x = 0; x <= size; x++) {
                try {
                    if (marketsIdMap_temp.get("id" + numberId) != null) {
                        marketsIdMap.put("id" + x,
                                marketsIdMap_temp.get("id" + numberId));
                    }
                    numberId = numberId + 1;
                } catch (NoSuchElementException e) {
                    logger.debug(itemsType + ".txt have problem with mapping");
                }
            }
            MarketSettle settleMarket = new MarketSettle();
            try {
                settleMarket.createRequest(marketsIdMap, env);
            } catch (IOException | SAXException | ParserConfigurationException e1) {
                e1.printStackTrace();
            }
            logger.debug("Markets Settled " + y);
        }
        } catch (NullPointerException e) {
            logger.debug("Markets Settle skipped");
        }
        logger.info("All Markets Settled");
    }

    public ResponseEvent settleEvent(String env) throws InterruptedException {

        ResponseEvent event = null;
        try {
        BufferedReader eventIDList = null;
        try {
            eventIDList = Files.newBufferedReader(Paths.get(
                    "EventData" + "/" + env.toLowerCase().trim() + "/" + "eventIDList.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        eventsIdMap_temp = new HashMap<>();
        Scanner s;
        int number = 1;
        s = new Scanner(eventIDList);
        while (s.hasNextLine()) {
            String a = s.nextLine();
            if (a != null) {
                eventsIdMap_temp.put("id" + number, a);
            }
            number = number + 1;
        }
        s.close();
        try {
            eventIDList.close();
        } catch (IOException e3) {
            logger.debug("");
        }
        int total = eventsIdMap_temp.size();
        times = total / 700;
        times = times + 1;
        logger.debug("Events settle cycles:" + times);
        int numberId = 0;
        for (int y = 0; y < times; y++) {
            eventsIdMap = new HashMap<>();
            int size;
            if (times > 1) {
                size = 701;
            } else {
                size = eventsIdMap_temp.size();
            }
            for (int x = 0; x <= size; x++) {
                try {
                    if (eventsIdMap_temp.get("id" + numberId) != null) {
                        eventsIdMap.put("id" + x,
                                eventsIdMap_temp.get("id" + numberId));
                    }
                    numberId = numberId + 1;
                } catch (NoSuchElementException e) {
                    logger.debug("eventIDList.txt have problem with mapping");
                }
            }
            try {
                EventSettle settleEvent = new EventSettle();
                event = settleEvent.createRequest(eventsIdMap, env);

            } catch (IOException | SAXException | ParserConfigurationException e1) {
                e1.printStackTrace();
            }
        }
            logger.info("All events settled");
        } catch (NullPointerException e) {
            logger.debug("events Settle skipped");
        }

        return event;
    }

    public void resultSelection(String itemsType, String env) throws InterruptedException {
        number = 0;
        String thisLine = null;
        try {
            try {
                BufferedReader br = Files.newBufferedReader(Paths.get(
                        "EventData" + "/" + env.toLowerCase().trim() + "/" + itemsType + ".txt"));
                BufferedReader br2 = Files.newBufferedReader(Paths.get(
                        "EventData" + "/" + env.toLowerCase().trim() + "/" + itemsType + ".txt"));
                selectionsIdMap_temp = new HashMap<>();
                while ((thisLine = br2.readLine()) != null) {
                    String a = br.readLine();
                    if (a != null) {
                        selectionsIdMap_temp.put("id" + number, a);
                    }
                    number = number + 1;
                }
                br.close();
                br2.close();
            } catch (Exception e) {
                logger.debug("Cannot find " +
                        "EventData" + "/" + env.toLowerCase().trim() + "/" + itemsType + ".txt");
            }
            int total = selectionsIdMap_temp.size();
            times = total / 700;
            times = times + 1;
            logger.debug("Selection result cycles:" + times);
            int numberId = 0;
            for (int y = 0; y < times; y++) {
                selectionsIdMap = new HashMap<>();
                int size;
                if (times > 1) {
                    size = 701;
                } else {
                    size = selectionsIdMap_temp.size();
                }
                for (int x = 0; x <= size; x++) {
                    try {
                        if (selectionsIdMap_temp.get("id" + numberId) != null) {
                            selectionsIdMap.put("id" + x,
                                    selectionsIdMap_temp.get("id" + numberId));
                        }
                        numberId = numberId + 1;
                    } catch (NoSuchElementException e) {
                        logger.debug(itemsType + ".txt have problem with mapping");
                    }
                }
                try {
                    SelectionResult resultSelection = new SelectionResult();
                    resultSelection.setVoid(selectionsIdMap, env);
                    logger.debug("Selections Resulted " + y);

                } catch (IOException | SAXException | ParserConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (NullPointerException e) {
            logger.debug("Selections Result skipped");
        }
        logger.info("All Selections Resulted");
    }

    public void settleSelection(String itemsType, String env) throws InterruptedException {
        try{
        number = 0;
        logger.debug("Settle Selections in " +
                "EventData" + "/" + env.toLowerCase().trim() + "/" + itemsType + ".txt");
        String thisLine = null;
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(
                    "EventData" + "/" + env.toLowerCase().trim() + "/" + itemsType + ".txt"));
            BufferedReader br2 = Files.newBufferedReader(Paths.get(
                    "EventData" + "/" + env.toLowerCase().trim() + "/" + itemsType + ".txt"));
            selectionsIdMap_temp = new HashMap<>();
            while ((thisLine = br2.readLine()) != null) {
                String a = br.readLine();
                if (a != null) {
                    selectionsIdMap_temp.put("id" + number, a);
                }
                number = number + 1;
            }
            br.close();
            br2.close();
        } catch (Exception e) {
            logger.debug("Cannot find " +
                    "EventData" + "/" + env.toLowerCase().trim() + "/" + itemsType + ".txt");
        }
        int total = selectionsIdMap_temp.size();
        times = total / 700;
        times = times + 1;
        logger.debug("Selections settle cycles:" + times);
        int numberId = 0;
        for (int y = 0; y < times; y++) {
            selectionsIdMap = new HashMap<>();
            int size = 0;
            if (times > 1) {
                size = 701;
            } else {
                size = selectionsIdMap_temp.size();
            }
            for (int x = 0; x <= size; x++) {
                try {
                    if (selectionsIdMap_temp.get("id" + numberId) != null) {
                        selectionsIdMap.put("id" + x,
                                selectionsIdMap_temp.get("id" + numberId));
                    }
                    numberId = numberId + 1;
                } catch (NoSuchElementException e) {
                    logger.debug(itemsType + ".txt have problem with mapping");
                }
            }
            try {
                SelectionSettling settleSelection = new SelectionSettling();
                settleSelection.createRequest(selectionsIdMap, env);
                logger.debug("Selections Settled " + y);

            } catch (IOException | SAXException | ParserConfigurationException e1) {
                e1.printStackTrace();
            }
        }
    } catch (NullPointerException e) {
        logger.debug("Selections Settle skipped");
    }
        logger.info("All Selections Settled");
    }

    public void zipAndDelete(String env) {
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss_");
        long TestTime2 = System.currentTimeMillis();
        Date date = new Date(TestTime2);
        String currentDate = dateFormat.format(date);

        Path logFile = Paths.get("NFT_OLD_EVENTS_DATA/log.txt");
        try {
            Files.createDirectories(logFile.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

		/*
        File directory = new File("C:\\NFT_OLD_EVENTS_DATA");
		if (!directory.exists()) {
				directory.mkdirs();
		}
		*/

        ZipUtil.pack(new File(Paths.get("EventData" + "/" + env).toUri()), new File("NFT_OLD_EVENTS_DATA" + "/"
                + currentDate + "EventData" + ".zip"));

    }

    public void cleanEventDataFiles(File folder) {
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                try {
                    FileWriter fileWriter = new FileWriter(f, false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("");
                    bufferedWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public ResponseEvent settle(String env) throws InterruptedException {
        ResponseEvent event = null;
        updateEvent(env);

        resultSelection("SelectionIDList", env);
        settleSelection("SelectionIDList", env);
        settleMarket("MarketIDList", env);

        resultSelection("SelectionIDListLive", env);
        settleSelection("SelectionIDListLive", env);
        settleMarket("MarketIDListLive", env);

        event = settleEvent(env);
        String osName = System.getProperties().getProperty("os.name");

        if (osName.contains("Windows")) {
            logger.info("zipping");
            zipAndDelete(env);
        }
        cleanEventDataFiles(new File(Paths.get("EventData" + "/" + env).toUri()));
        return event;
    }
}
