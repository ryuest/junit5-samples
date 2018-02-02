package com.whgtf.sportsbook.obfeed;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

public class FootballDataCreator {

    public static void main(String[] args) throws IOException {

        // event creation inputs
        String environment = System.getProperty("env", "PP1");
        String teamA = System.getProperty("teamA", "teamA");
        String teamB = System.getProperty("teamB", "teamB");
        String competition = System.getProperty("competition", "English Premier League");
        String eventStartTime = System.getProperty("startTime", "2017-01-19 20:45:00");

        // map for football competitions
        LinkedHashMap<String, String> sportCompetitions = new LinkedHashMap<>();
        sportCompetitions.put("English Premier League", "UK Football");
        sportCompetitions.put("English Championship", "UK Football");
        sportCompetitions.put("English League 1", "UK Football");
        sportCompetitions.put("English League 2", "UK Football");
        sportCompetitions.put("Spanish La Liga Primera", "European Major Leagues");
        sportCompetitions.put("German Bundesliga", "European Major Leagues");
        sportCompetitions.put("Italian Serie A", "European Major Leagues");
        sportCompetitions.put("French Ligue 1", "European Major Leagues");

        // map for network drives
        // https://conf.willhillatlas.com/display/TP/Mapping+Network+Drive+For+Test+Area+Auto+Uploaders
        LinkedHashMap<String, String> mappedNetworkDrive = new LinkedHashMap<>();
        mappedNetworkDrive.put("PP1", "P:/csv_incoming_files/");
        mappedNetworkDrive.put("PP2", "Q:/csv_incoming_files/");
        mappedNetworkDrive.put("PP3", "R:/csv_incoming_files/");

        // get list of template files
        File folder = new File("src/main/java/com/whgtf/sportsbook/templates/football");
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles != null) {
            // map values from the templates and save the event files
            for (File file : listOfFiles) {
                String s = new String(Files.readAllBytes(Paths.get(file.getPath())));
                s = s.replace("$teamA", teamA);
                s = s.replace("$teamB", teamB);
                s = s.replace("$competition", competition);
                s = s.replace("$sport", sportCompetitions.get(competition));
                s = s.replace("$eventStartTime", eventStartTime);
                try (FileWriter fw = new FileWriter(mappedNetworkDrive.get(environment) + file.getName())) {
                    fw.write(s);
                }
            }
        }

    }
}
