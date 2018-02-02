package com.whgtf.sportsbook.impl;

import com.whgtf.sportsbook.interfaces.FootballMarketsMaps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Juri on 26/10/2016.
 */
public class FootballMarketsMapsImp extends GenericMapsImpl implements FootballMarketsMaps {

    @Override
    public Map<Integer, String> topMarkets() {
        Map<Integer, String> topMarketsMap = new LinkedHashMap<>();
        topMarketsMap.put(0, "90 Minutes");
        return topMarketsMap;
    }
    @Override
    public Map<Integer, String> topMarketsLive() {
        Map<Integer, String> topMarketsLiveMap = new LinkedHashMap<>();
        topMarketsLiveMap.put(0, "Match Betting Live");
        topMarketsLiveMap.put(1, "Half Time Live");
        return topMarketsLiveMap;
    }
    @Override
    public Map<Integer, String> footballMarketsWinDrawWin() {
        Map<Integer, String> WinDrawWinMap = new LinkedHashMap<>();
        WinDrawWinMap.put(0, "Corners Match Bet");
        WinDrawWinMap.put(1, "Period Betting");
        WinDrawWinMap.put(2, "Match Result and Both Teams To Score");
        WinDrawWinMap.put(3, "Both Teams To Score?");
        return WinDrawWinMap;
    }
    @Override
    public Map<Integer, String> footballMarketsWinDrawWinLive() {
        Map<Integer, String> WinDrawWinLiveMap = new LinkedHashMap<>();
        WinDrawWinLiveMap.put(0, "Corners Match Bet Live");
        WinDrawWinLiveMap.put(1, "Bookings Match Bet Live");
        WinDrawWinLiveMap.put(2, "Period Betting Live");
        WinDrawWinLiveMap.put(3, "Match Result and Both Teams To Score| |Live");
        WinDrawWinLiveMap.put(4, "Both Teams To Score?");
        return WinDrawWinLiveMap;
    }

    @Override
    public Map<Integer, String> footballMarketsStandardTreeSelections() {
        Map<Integer, String> StandardTreeSelectionsMap = new LinkedHashMap<>();
        StandardTreeSelectionsMap.put(0, "1st Goal Time");
        StandardTreeSelectionsMap.put(1, "1st Goal To Be Own Goal?");
        StandardTreeSelectionsMap.put(2, "1st Half Goals");
        StandardTreeSelectionsMap.put(3, "1st Half Result|/|2nd Half Result");
        StandardTreeSelectionsMap.put(4, "1st Team GoalScorer");
        StandardTreeSelectionsMap.put(5, "2nd Goal Time");
        StandardTreeSelectionsMap.put(6, "2nd Half Goals");
        StandardTreeSelectionsMap.put(7, "3rd Goal Time");
        StandardTreeSelectionsMap.put(8, "4th Goal Time");
        StandardTreeSelectionsMap.put(9, "5th Goal Time");
        StandardTreeSelectionsMap.put(10, "6th Goal Time");
        StandardTreeSelectionsMap.put(11, "Anytime Own Goal");
        StandardTreeSelectionsMap.put(12, "Anytime Goalscorers Doubled");
        StandardTreeSelectionsMap.put(13, "Anytime Wincast DNU");
        StandardTreeSelectionsMap.put(14, "Away Team Clean Sheet");
        StandardTreeSelectionsMap.put(15, "Away Team Performance");
        StandardTreeSelectionsMap.put(16, "Away Team To Score In Both Halves");
        StandardTreeSelectionsMap.put(17, "Both Teams To Score?");
        StandardTreeSelectionsMap.put(18, "Corners Handicap");
        StandardTreeSelectionsMap.put(19, "Corners x Cards");
        StandardTreeSelectionsMap.put(20, "DNU - Double Chance");
        StandardTreeSelectionsMap.put(21, "Esito Finale 1x2 Goal No Goal");
        StandardTreeSelectionsMap.put(22, "First Card");
        StandardTreeSelectionsMap.put(23, "First Corner");
        StandardTreeSelectionsMap.put(24, "First Goal Scorers(Coupled)");
        StandardTreeSelectionsMap.put(25, "First/Last Card");
        StandardTreeSelectionsMap.put(26, "Goal In X Mins Bands");
        StandardTreeSelectionsMap.put(27, "Goal Shirt Number");
        StandardTreeSelectionsMap.put(28, "Goals x Cards");
        StandardTreeSelectionsMap.put(29, "Goals x Corners");
        StandardTreeSelectionsMap.put(30, "Half Both Teams To Score");
        StandardTreeSelectionsMap.put(31, "Half Cards");
        StandardTreeSelectionsMap.put(32, "Half Corners Match Bet");
        StandardTreeSelectionsMap.put(33, "Half Double Chance");
        StandardTreeSelectionsMap.put(34, "Half Handicaps");
        StandardTreeSelectionsMap.put(35, "Half Of 1st Goal");
        StandardTreeSelectionsMap.put(36, "Half Of 1st Team Goal");
        StandardTreeSelectionsMap.put(37, "Half Team Goals");
        StandardTreeSelectionsMap.put(38, "Half Teams To Score");
        StandardTreeSelectionsMap.put(39, "Half Total Goals");
        StandardTreeSelectionsMap.put(40, "Half With Most Corners");
        StandardTreeSelectionsMap.put(41, "Total Team Goals");
        StandardTreeSelectionsMap.put(42, "Highest Scoring Half");
        StandardTreeSelectionsMap.put(43, "Home Team Clean Sheet");
        StandardTreeSelectionsMap.put(44, "Home Team Performance");
        StandardTreeSelectionsMap.put(45, "Home Team To Score In Both Halves");
        StandardTreeSelectionsMap.put(46, "In Both Halves");
        StandardTreeSelectionsMap.put(47, "Last Card");
        StandardTreeSelectionsMap.put(48, "Last Corner");
        StandardTreeSelectionsMap.put(49, "Margin Of Victory");
        StandardTreeSelectionsMap.put(50, "Match Action");
        StandardTreeSelectionsMap.put(51, "Match Handicaps");
        StandardTreeSelectionsMap.put(52, "Win Both Halves");
        StandardTreeSelectionsMap.put(53, "Match Trebles");
        StandardTreeSelectionsMap.put(54, "Match x Goals");
        StandardTreeSelectionsMap.put(55, "Most Booking Points");
        StandardTreeSelectionsMap.put(56, "Outright");
        StandardTreeSelectionsMap.put(57, "Penalty Specials");
        StandardTreeSelectionsMap.put(58, "Race To X Goals");
        StandardTreeSelectionsMap.put(59, "Red Card In The Match?");
        StandardTreeSelectionsMap.put(60, "Win Either Half");
        StandardTreeSelectionsMap.put(61, "Stats Trebles");
        StandardTreeSelectionsMap.put(62, "Team Performance");
        StandardTreeSelectionsMap.put(63, "Team To Kick Off Match");
        StandardTreeSelectionsMap.put(64, "Teams To Score");
        StandardTreeSelectionsMap.put(65, "Time Of 1st Card");
        StandardTreeSelectionsMap.put(66, "Time Of 1st Goal Over/Under");
        StandardTreeSelectionsMap.put(67, "Time Of Next Goal Bands");
        StandardTreeSelectionsMap.put(68, "Time Of Team Goal");
        StandardTreeSelectionsMap.put(69, "Time Of Team Goal Over/Under");
        StandardTreeSelectionsMap.put(70, "To Keep a Clean Sheet");
        StandardTreeSelectionsMap.put(71, "To Lift The Trophy");
        StandardTreeSelectionsMap.put(72, "To Score In Both Halves");
        StandardTreeSelectionsMap.put(73, "To Win To Nil");
        StandardTreeSelectionsMap.put(74, "Total Goal Minutes");
        StandardTreeSelectionsMap.put(75, "Total Match Goals");
        StandardTreeSelectionsMap.put(76, "Total Team Cards");
        return StandardTreeSelectionsMap;
    }

    @Override
    public Map<Integer, String> footballMarketsStandardTwoSelections() {
        Map<Integer, String> StandardTwoSelectionsMap = new LinkedHashMap<>();
        StandardTwoSelectionsMap.put(0, "1st Half Over/Under 0.5 Goals");
        StandardTwoSelectionsMap.put(1, "1st Half Over/Under 1.5 Goals");
        StandardTwoSelectionsMap.put(2, "1st Half Over/Under 2.5 Goals");
        StandardTwoSelectionsMap.put(3, "1st Half Over/Under 3.5 Goals");
        StandardTwoSelectionsMap.put(4, "1st Half Over/Under 4.5 Goals");
        StandardTwoSelectionsMap.put(5, "2nd Half Over/Under 0.5 Goals");
        StandardTwoSelectionsMap.put(6, "2nd Half Over/Under 1.5 Goals");
        StandardTwoSelectionsMap.put(7, "2nd Half Over/Under 2.5 Goals");
        StandardTwoSelectionsMap.put(8, "2nd Half Over/Under 3.5 Goals");
        StandardTwoSelectionsMap.put(9, "Total Match Goals Odd/Even");
        StandardTwoSelectionsMap.put(10, "Total Match Goals Over/Under 0.5 Goals");
        StandardTwoSelectionsMap.put(11, "Total Match Goals Over/Under 1.5 Goals");
        StandardTwoSelectionsMap.put(12, "Total Match Goals Over/Under 2.5 Goals");
        StandardTwoSelectionsMap.put(13, "Total Match Goals Over/Under 3.5 Goals");
        StandardTwoSelectionsMap.put(14, "Total Match Goals Over/Under 4.5 Goals");
        StandardTwoSelectionsMap.put(15, "Total Match Goals Over/Under 5.5 Goals");
        return StandardTwoSelectionsMap;
    }

    @Override
    public Map<Integer, String> footballMarketsStandardTwoSelectionsLive() {
        Map<Integer, String> StandardTwoSelectionsMapLive= new LinkedHashMap<>();
        StandardTwoSelectionsMapLive.put(0, "ET||Team Penalties Live");
        StandardTwoSelectionsMapLive.put(1, "Tempo Over/Under Goals Live");
        StandardTwoSelectionsMapLive.put(2, "Total Corners Live");
        StandardTwoSelectionsMapLive.put(3, "Penalties Live");
        StandardTwoSelectionsMapLive.put(4, "ET||Teams To Score Live");
        StandardTwoSelectionsMapLive.put(5, "To Qualify Live");
        StandardTwoSelectionsMapLive.put(6, "Anytime Own Goal Live");
        StandardTwoSelectionsMapLive.put(7, "Winning Margin Live");
        StandardTwoSelectionsMapLive.put(8, "Next Goal Live");
        StandardTwoSelectionsMapLive.put(9, "Both Team To Score Live");
        StandardTwoSelectionsMapLive.put(10, "Half Both Teams To Score| |Live");
        StandardTwoSelectionsMapLive.put(11, "Teams To Score Live");
        StandardTwoSelectionsMapLive.put(12, "To Score In Both Halves Live");
        StandardTwoSelectionsMapLive.put(13, "Clean Sheet Live");
        StandardTwoSelectionsMapLive.put(14, "First Goalscorers (Coupled) Live");
        StandardTwoSelectionsMapLive.put(15, "To Win To Nil Live");
        StandardTwoSelectionsMapLive.put(16, "Anytime Goal Scorers Doubled Live");
        StandardTwoSelectionsMapLive.put(17, "Total Number Of Goals Live");
        StandardTwoSelectionsMapLive.put(18, "Time Of Next Goal Before/After Live");
        StandardTwoSelectionsMapLive.put(19, "2nd Half Goals Live");
        StandardTwoSelectionsMapLive.put(20, "Half Number Of Goals Live");
        StandardTwoSelectionsMapLive.put(21, "1st Half Goals Live");
        StandardTwoSelectionsMapLive.put(22, "Team Goals Live");
        StandardTwoSelectionsMapLive.put(23, "Half Of Next Team Goal Live");
        StandardTwoSelectionsMapLive.put(24, "Corners Handicap Live");
        StandardTwoSelectionsMapLive.put(25, "Total Bookings Live");
        StandardTwoSelectionsMapLive.put(26, "Penalty Awarded/Scored Live");
        StandardTwoSelectionsMapLive.put(27, "Result and Under/Over 2.5 Goals Live");
        StandardTwoSelectionsMapLive.put(28, "Race To X Goals Live");
        StandardTwoSelectionsMapLive.put(29, "Team To Score Live");
        StandardTwoSelectionsMapLive.put(30, "Half With Most Goals Live");
        StandardTwoSelectionsMapLive.put(31, "Half Of Next Goal Live");
        StandardTwoSelectionsMapLive.put(32, "Over/Under Goals Casa Ospite| |Live");
        StandardTwoSelectionsMapLive.put(33, "ET||Win on Penalties Live");
        StandardTwoSelectionsMapLive.put(34, "ET||Halves With A Goal Live?");
        return StandardTwoSelectionsMapLive;
    }

    @Override
    public Map<Integer, String> footballMarketsScorersThreeSelections() {
        Map<Integer, String> ScorersThreeSelectionsMap= new LinkedHashMap<>();
        ScorersThreeSelectionsMap.put(0, "First Goalscorer");
        ScorersThreeSelectionsMap.put(1, "Last Goalscorer");
        ScorersThreeSelectionsMap.put(2, "Anytime Goalscorer");
        ScorersThreeSelectionsMap.put(3, "Player To Score 2 Or More");
        ScorersThreeSelectionsMap.put(4, "Player To Score First OR Last Goal");
        ScorersThreeSelectionsMap.put(5, "Player To Score First AND Last Goal");
        ScorersThreeSelectionsMap.put(6, "Hatrick");
        return ScorersThreeSelectionsMap;
    }
    @Override
    public Map<Integer, String> footballMarketsScorersThreeSelectionsLive() {
        Map<Integer, String> ScorersThreeSelectionsMapLive= new LinkedHashMap<>();
        ScorersThreeSelectionsMapLive.put(0, "First Goalscorer Live");
        ScorersThreeSelectionsMapLive.put(1, "Last Goalscorer Live");
        ScorersThreeSelectionsMapLive.put(2, "Anytime Goalscorer Live");
        ScorersThreeSelectionsMapLive.put(3, "Player To Score 2 Or More Live");
        ScorersThreeSelectionsMapLive.put(4, "Player To Score First OR Last Goal Live");
        ScorersThreeSelectionsMapLive.put(5, "Player To Score First AND Last Goal Live");
        ScorersThreeSelectionsMapLive.put(6, "Hatrick Live");
        return ScorersThreeSelectionsMapLive;
    }

    @Override
    public Map<Integer, String> footballMarketsCorrectScore() {
        Map<Integer, String> CorrectScoreMap= new LinkedHashMap<>();
        CorrectScoreMap.put(0, "1st Half Correct Score");
        CorrectScoreMap.put(1, "2nd Half Correct Score");
        CorrectScoreMap.put(2, "Correct Score");
        return CorrectScoreMap;
    }

    @Override
    public Map<Integer, String> footballMarketsCorrectScoreSortTypes() {
        Map<Integer, String> CorrectScoreSortTypesMap= new LinkedHashMap<>();
        CorrectScoreSortTypesMap.put(0, "Half Time Correct Score");
        CorrectScoreSortTypesMap.put(1, "Half Time Correct Score");
        CorrectScoreSortTypesMap.put(2, "Correct Score");
        return CorrectScoreSortTypesMap;
    }

    @Override
    public Map<Integer, String> footballMarketsCorrectScoreLive() {
        Map<Integer, String> CorrectScoreMapLive= new LinkedHashMap<>();
        CorrectScoreMapLive.put(0, "Live Score");
        CorrectScoreMapLive.put(1, "1st Half Correct Score Live");
        CorrectScoreMapLive.put(2, "2nd Half Correct Score Live");
        return CorrectScoreMapLive;
    }

    @Override
    public Map<Integer, String> footballMarketsCorrectScoreSortTypesLive() {
        Map<Integer, String> CorrectScoreSortTypesMapLive= new LinkedHashMap<>();
        CorrectScoreSortTypesMapLive.put(0, "Live Score");
        return CorrectScoreSortTypesMapLive;
    }

    @Override
    public Map<Integer, String> footballMarketsCorrectScoreSortTypesSpecialLive() {
        Map<Integer, String> CorrectScoreSortTypesSpecialMapLive= new LinkedHashMap<>();
        CorrectScoreSortTypesSpecialMapLive.put(0, "Half Correct Score Live");
        return CorrectScoreSortTypesSpecialMapLive;
    }

    @Override
    public Map<Integer, String> footballMarketsHandicaps() {
        Map<Integer, String> HandicapsMap = new LinkedHashMap<>();
        HandicapsMap.put(0, "Match Handicap|-1");
        HandicapsMap.put(1, "Match Handicap|-2");
        HandicapsMap.put(2, "Match Handicap|-3");
        HandicapsMap.put(3, "Match Handicap|+1");
        HandicapsMap.put(4, "Match Handicap|+2");
        return HandicapsMap;
    }

    @Override
    public Map<Integer, String> footballMarketsHandicapsLive() {
        Map<Integer, String> HandicapsMapLive = new LinkedHashMap<>();
        HandicapsMapLive.put(0, "Match Handicap|-1 Live");
        HandicapsMapLive.put(1, "Match Handicap|-2 Live");
        HandicapsMapLive.put(2, "Match Handicap|-3 Live");
        HandicapsMapLive.put(3, "Match Handicap|+1 Live");
        HandicapsMapLive.put(4, "Match Handicap|+2 Live");
        HandicapsMapLive.put(5, "Match Handicap Live");
        return HandicapsMapLive;
    }

    @Override
    public Map<Integer, String> footballMarketsOverUnderLive() {
        Map<Integer, String> overUnderMapLive = new LinkedHashMap<>();
        overUnderMapLive.put(0, "Team Over/Under Goals Live");
        return overUnderMapLive;
    }

    @Override
    public Map<Integer, String> footballSpecialBettingLive() {
        Map<Integer, String> footballSpecialBettingMapLive = new LinkedHashMap<>();
        footballSpecialBettingMapLive.put(0, "Half Over/Under Goals| |Live");
        footballSpecialBettingMapLive.put(1, "Match Over/Under Goals Live");
        footballSpecialBettingMapLive.put(2, "Half Betting Live");
        footballSpecialBettingMapLive.put(3, "Next Goal Live");
        footballSpecialBettingMapLive.put(4, "Red Card In The Match Live");
        return footballSpecialBettingMapLive;
    }

    @Override
    public Map<Integer, String> footballSpecialBettingGoalFirstLastLive() {
        Map<Integer, String> specialBettingGoalFirstLastGoalMapLive = new LinkedHashMap<>();
        specialBettingGoalFirstLastGoalMapLive.put(0, "First Goalscorer Live");
        specialBettingGoalFirstLastGoalMapLive.put(1, "Last Goalscorer Live");
        specialBettingGoalFirstLastGoalMapLive.put(2, "Player To Score 2 Or More Live");
        specialBettingGoalFirstLastGoalMapLive.put(3, "Hatrick Live");
        return specialBettingGoalFirstLastGoalMapLive;
    }

    @Override
    public Map<Integer, String> footballSpecialBettingGoalXLive() {
        Map<Integer, String> specialBettingGoalXLive = new LinkedHashMap<>();
        specialBettingGoalXLive.put(0, "Second Goalscorer Live");
        specialBettingGoalXLive.put(1, "Third Goalscorer Live");
        specialBettingGoalXLive.put(2, "Fourth Goalscorer Live");
        specialBettingGoalXLive.put(3, "Fifth Goalscorer Live");
        return specialBettingGoalXLive;
    }

    @Override
    public Map<String, String> footballSpecialBettingCorrectNames() {
        Map<String, String> specialBettingCorrectNamesMap = new LinkedHashMap<>();
        specialBettingCorrectNamesMap.put("1st Half Correct Score Live",            "1st Half Correct Score| |Live");
        specialBettingCorrectNamesMap.put("Half Correct Score Live",                "1st Half Correct Score| |Live");
        specialBettingCorrectNamesMap.put("2nd Half Correct Score Live",            "2nd Half Correct Score| |Live");
        specialBettingCorrectNamesMap.put("Half Time Live",                         "Half Time| |Live");
        specialBettingCorrectNamesMap.put("Match Betting Live",                     "Match Betting| |Live");
        specialBettingCorrectNamesMap.put("Both Teams To Score?",                   "Both Teams To Score| |Live");
        specialBettingCorrectNamesMap.put("Match Over/Under Goals Live",            "Match Over/Under Goals| |Live");
        specialBettingCorrectNamesMap.put("Half Betting Live",                      "2nd Half Betting| |Live");
        specialBettingCorrectNamesMap.put("Match Handicap Live",                    "Match Handicap Live");
        specialBettingCorrectNamesMap.put("First Goalscorer Live",                  "First Goalscorer| |Live");
        specialBettingCorrectNamesMap.put("Second Goalscorer Live",                 "Second Goalscorer| |Live");
        specialBettingCorrectNamesMap.put("Third Goalscorer Live",                  "Third Goalscorer| |Live");
        specialBettingCorrectNamesMap.put("Fourth Goalscorer Live",                 "Fourth Goalscorer| |Live");
        specialBettingCorrectNamesMap.put("Fifth Goalscorer Live",                  "Fifth Goalscorer| |Live");
        specialBettingCorrectNamesMap.put("Last Goalscorer Live",                   "Last Goalscorer| |Live");
        specialBettingCorrectNamesMap.put("Hatrick Live",                           "Hattrick| |Live");
        specialBettingCorrectNamesMap.put("Player To Score 2 Or More Live",         "Player to Score 2 or More| |Live");
        specialBettingCorrectNamesMap.put("Team Over/Under Goals Live",             "Team Over/Under Goals Live");
        specialBettingCorrectNamesMap.put("90 Minutes",                             "90 Minutes");
        specialBettingCorrectNamesMap.put("Corners Match Bet",                      "Corners Match Bet");
        specialBettingCorrectNamesMap.put("Period Betting",                         "Period Betting");
        specialBettingCorrectNamesMap.put("Match Result and Both Teams To Score",   "Match Result and Both Teams To Score");
        specialBettingCorrectNamesMap.put("Live Score",                             "Live Score");
        specialBettingCorrectNamesMap.put("Corners Match Bet Live",                 "Corners Match Bet Live");
        specialBettingCorrectNamesMap.put("Bookings Match Bet Live",                "Bookings Match Bet Live");
        specialBettingCorrectNamesMap.put("Half Over/Under Goals| |Live",           "Half Over/Under Goals| |Live");
        specialBettingCorrectNamesMap.put("Next Goal Live",                         "Next Goal Live");
        specialBettingCorrectNamesMap.put("Period Betting Live",                           "Period Betting Live");
        specialBettingCorrectNamesMap.put("Match Result and Both Teams To Score| |Live",   "Match Result and Both Teams To Score| |Live");
        specialBettingCorrectNamesMap.put("Red Card In The Match Live",                    "Red Card In The Match Live");
        return specialBettingCorrectNamesMap;
    }

    @Override
    public Map<Integer, String> americanFootballOverUnder() {
        Map<Integer, String> americanFootballMap = new LinkedHashMap<>();
        americanFootballMap.put(0, "Total Points| |Live");
        return americanFootballMap;
    }

    @Override
    public Map<Integer, String> americanFootballWinWin() {
        Map<Integer, String> americanFootballMap = new LinkedHashMap<>();
        americanFootballMap.put(0, "Money Line| |Live");
        return americanFootballMap;
    }

    @Override
    public Map<Integer, String> americanFootballHandicap() {
        Map<Integer, String> americanFootballMap = new LinkedHashMap<>();
        americanFootballMap.put(0, "Match Spread");
        return americanFootballMap;
    }
}
