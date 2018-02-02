package com.whgtf.sportsbook.model;

import com.whgtf.sportsbook.utils.BackOfficeConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Juri on 14/11/2016.
 */
public class EventSettings {


    public String offFlag;
    public String status;
    public String displayed;
    public String starting;
    public String bir;
    public String status_live;
    public String displayed_live;
    public String starting_live;
    public String bir_live;

    public String sportType;
    public String eventType;
    public String eventName;
    public String startTime;
    public String env;
    public String competition;
    public String sport;
    public String racingEventType;
    public String eventsNumber;
    public String marketsNumber;
    public String selectionsNumber;

    boolean football;
    boolean horseRacing;
    boolean standard;
    boolean generic;
    boolean winDrawWin;
    boolean correctScore;
    boolean scorers;
    boolean special;
    boolean handicap;
    boolean antepost;
    boolean meetings;
    Event event = new Event();

    public void setEventVariables(HashMap<String, String> settingsList) {
        this.football = false;
        this.horseRacing = false;
        this.standard = false;
        this.winDrawWin = false;
        this.correctScore = false;
        this.scorers = false;
        this.special = false;
        this.handicap = false;
        this.sportType = settingsList.get("sportType");
        this.eventType = settingsList.get("eventType");
        this.eventName = event.setRandomNameWithPrefix(settingsList.get("eventName"));
        this.startTime = settingsList.get("startTime");
        this.env = settingsList.get("env").toLowerCase().trim();
        this.competition = settingsList.get("competition");
        this.sport = settingsList.get("sport");
        this.racingEventType = settingsList.get("racingEventType");
        this.eventsNumber = settingsList.get("eventsNumber");
        this.marketsNumber = settingsList.get("marketsNumber");
        this.selectionsNumber = settingsList.get("selectionsNumber");

        if (eventType.toUpperCase().replaceAll(" ", "").equals("INPLAY")) {
            this.offFlag = BackOfficeConstants.EVENT_OFF_FLAG_YES;
            this.status = BackOfficeConstants.MARKET_STATUS_SUSPENDED;
            this.displayed = BackOfficeConstants.MARKET_DISPLAYED_NO;
            this.starting = "yes";
            this.bir = BackOfficeConstants.MARKET_BIR_NO;
            this.status_live = BackOfficeConstants.MARKET_STATUS_ACTIVE;
            this.displayed_live = BackOfficeConstants.MARKET_DISPLAYED_YES;
            this.starting_live = "yes";
            this.bir_live = BackOfficeConstants.MARKET_BIR_YES;
        } else {
                this.offFlag = BackOfficeConstants.EVENT_OFF_FLAG_NA;
                this.status = BackOfficeConstants.MARKET_STATUS_ACTIVE;
                this.displayed = BackOfficeConstants.MARKET_DISPLAYED_YES;
                this.starting = "yes";
                this.bir = BackOfficeConstants.MARKET_BIR_NO;
                this.status_live = BackOfficeConstants.MARKET_STATUS_SUSPENDED; // ErrorMessage
                this.displayed_live = BackOfficeConstants.MARKET_DISPLAYED_NO;
                this.starting_live = "no";
                this.bir_live = BackOfficeConstants.MARKET_BIR_YES;
        }

        Path logFile = Paths.get("EventData" + "/" + settingsList.get("env") + "/IDList.txt");
        try {
            Files.createDirectories(logFile.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, String> getSportMarketList() {
        Map<Integer, String> marketList=new LinkedHashMap<>();
        String footballType = sport.toUpperCase().replaceAll(" ", "");
        if (footballType.equals("FOOTBALL")) {
            marketList.put(0, "standard");
            marketList.put(1, "windrawwin");
            marketList.put(2, "correctscore");
            marketList.put(3, "scorers");
            marketList.put(4, "handicap");
        } else if (footballType.equals("SPECIAL")) {
            marketList.put(0, "special");
            marketList.put(1, "specialSet2");
            marketList.put(2, "correctscore");
            marketList.put(3, "windrawwin");
        } else if (footballType.equals("SPECIAL2")) {
            marketList.put(0, "special");
            marketList.put(1, "specialSet1");
            marketList.put(2, "correctscore");
            marketList.put(3, "windrawwin");
        } else {
            marketList.put(0, "");
        }
        return marketList;
    }

    public String getOffFlag() {
        return offFlag;
    }

    public String getStatus() {
        return status;
    }

    public String getDisplayed() {
        return displayed;
    }

    public void setFootball(boolean football){
        this.football = football;
    }

    public boolean getIsFootball(){
        return football;
    }

    public void setHorseRacing(boolean horseRacing){
        this.horseRacing = horseRacing;
    }

    public boolean getIsHorseRacing(){
        return horseRacing;
    }

    public void setMeetings(boolean meetings){
        this.meetings = meetings;
    }

    public boolean getIsMeetings(){
        return meetings;
    }

    public void setStandard(boolean standard){
        this.standard = standard;
    }

    public boolean getIsStandard(){
        return standard;
    }

    public void setGeneric(boolean generic){
        this.generic = generic;
    }

    public boolean getIsGeneric(){
        return generic;
    }

    public void setWinDrawWin(boolean winDrawWin){
        this.winDrawWin = winDrawWin;
    }

    public boolean getIsWinDrawWin(){
        return winDrawWin;
    }

    public void setCorrectScore(boolean correctScore){
        this.correctScore = correctScore;
    }

    public boolean getIsCorrectScore(){
        return correctScore;
    }

    public void setScorers(boolean scorers){
        this.scorers = scorers;
    }

    public boolean getIsScorers(){
        return scorers;
    }

    public void setSpecial(boolean special){
        this.special = special;
    }

    public boolean getIsSpecial(){
        return special;
    }

    public void setHandicap(boolean handicap){
        this.handicap = handicap;
    }

    public boolean getIsHandicap(){
        return handicap;
    }

    public boolean getIsAntepost() {
        return antepost;
    }

    public void setAntepost(boolean antepost) {
        this.antepost = antepost;
    }

    public String getStatus_live() {
        return status_live;
    }

    public void setStatus_live(String status_live) {
        this.status_live = status_live;
    }

    public String getDisplayed_live() {
        return displayed_live;
    }

    public String getBir_live() {
        return bir_live;
    }

    public void setBir(String bir) {
        this.bir = bir;
    }

    public void setBir_live(String bir_live) {
        this.bir_live = bir_live;
    }

    public String getBir() {
        return bir;
    }

    public void setDisplayed_live(String displayed_live) {
        this.displayed_live = displayed_live;

    }

    public String getCompetition() {
        return competition;
    }

    public String getStarting() {
        return starting;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEventName() {
        return eventName;
    }

    public String getSport() {
        return sport;
    }

    public String getEnv() {
        return env;
    }


}
