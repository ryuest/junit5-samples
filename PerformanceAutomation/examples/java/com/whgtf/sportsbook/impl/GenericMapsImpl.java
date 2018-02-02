package com.whgtf.sportsbook.impl;

import com.whgtf.sportsbook.interfaces.GenericMaps;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Juri on 27/10/2016.
 */


public class GenericMapsImpl implements GenericMaps {

    @Override
    public Map<Integer, String> getHomeAway() {
        Map<Integer, String> selectionTypeMap = new LinkedHashMap<>();
        selectionTypeMap.put(0, "home");
        selectionTypeMap.put(1, "away");
        return selectionTypeMap;
    }

    @Override
    public Map<Integer, String> getOverUnder() {
        Map<Integer, String> selectionOverUnderMap = new LinkedHashMap<>();
        selectionOverUnderMap.put(0, "over");
        selectionOverUnderMap.put(1, "under");
        return selectionOverUnderMap;
    }

    @Override
    public Map<Integer, String> getHomeAwayName() {
        Map<Integer, String> selectionNameMap = new LinkedHashMap<>();
        selectionNameMap.put(0, "TeamA");
        selectionNameMap.put(1, "TeamB");
        return selectionNameMap;
    }

    @Override
    public Map<Integer, String> getHandicapValues() {
        Map<Integer, String> handicapValues = new LinkedHashMap<>();
        handicapValues.put(0, "-1.0");
        handicapValues.put(1, "-2.0");
        handicapValues.put(2, "-3.0");
        handicapValues.put(3, "1.0");
        handicapValues.put(4, "2.0");
        handicapValues.put(5, "3.0");
        handicapValues.put(6, "-4.0");
        handicapValues.put(7, "4.0");
        return handicapValues;
    }

    @Override
    public Map<Integer, String> getHandicapName() {
        Map<Integer, String> handicapNames = new LinkedHashMap<>();
        handicapNames.put(0, "Home");
        handicapNames.put(1, "Line");
        handicapNames.put(2, "Away");
        return handicapNames;
    }

    @Override
    public Map<Integer, String> getHandicapType() {
        Map<Integer, String> handicapTypes = new LinkedHashMap<>();
        handicapTypes.put(0, "home");
        handicapTypes.put(1, "line");
        handicapTypes.put(2, "away");
        return handicapTypes;
    }

    @Override
    public Map<Integer, String> getHomeDrawAwayName() {
        Map<Integer, String> selectionTypeMap = new LinkedHashMap<>();
        selectionTypeMap.put(0, "TeamA");
        selectionTypeMap.put(1, "Draw");
        selectionTypeMap.put(2, "TeamB");
        return selectionTypeMap;
    }

    public Map<Integer, String> getHomeDrawAway() {
    Map<Integer, String> selectionValuesMap = new LinkedHashMap<>();
        selectionValuesMap.put(0,"home");
        selectionValuesMap.put(1,"draw");
        selectionValuesMap.put(2,"away");
    return selectionValuesMap;
    }

    @Override
    public Map<Integer, String> getPrice() {
        Map<Integer, String> selectionPriceMap = new LinkedHashMap<>();
        selectionPriceMap.put(0,"1/2");
        selectionPriceMap.put(1,"1/3");
        selectionPriceMap.put(2,"1/4");
        selectionPriceMap.put(3,"1/5");
        selectionPriceMap.put(4,"1/6");
        selectionPriceMap.put(5,"1/7");
        selectionPriceMap.put(6,"1/8");
        selectionPriceMap.put(7,"1/9");
        selectionPriceMap.put(8,"1/10");
        selectionPriceMap.put(9,"1/11");
        selectionPriceMap.put(10,"1/12");
        selectionPriceMap.put(11,"1/13");
        selectionPriceMap.put(12,"1/14");
        selectionPriceMap.put(13,"1/15");
        selectionPriceMap.put(14,"1/16");
        selectionPriceMap.put(15,"1/17");
        selectionPriceMap.put(16,"1/18");
        selectionPriceMap.put(17,"1/19");
        selectionPriceMap.put(18,"1/20");
        selectionPriceMap.put(19,"1/21");
        selectionPriceMap.put(20,"1/22");
        selectionPriceMap.put(21,"1/23");
        selectionPriceMap.put(22,"1/24");
        selectionPriceMap.put(23,"1/25");
        selectionPriceMap.put(24,"1/26");
        selectionPriceMap.put(25,"1/27");
        selectionPriceMap.put(26,"1/28");
        selectionPriceMap.put(27,"1/29");
        selectionPriceMap.put(28,"1/30");
        selectionPriceMap.put(29,"1/31");
        selectionPriceMap.put(30,"1/32");
        selectionPriceMap.put(31,"1/33");
        selectionPriceMap.put(32,"1/34");
        selectionPriceMap.put(33,"1/35");
        selectionPriceMap.put(34,"1/36");
        selectionPriceMap.put(35,"1/37");
        selectionPriceMap.put(36,"1/38");
        selectionPriceMap.put(37,"1/39");
        selectionPriceMap.put(38,"1/40");
        selectionPriceMap.put(39,"1/41");
        selectionPriceMap.put(40,"1/42");
        selectionPriceMap.put(41,"1/43");
        selectionPriceMap.put(42,"1/44");
        selectionPriceMap.put(43,"1/45");
        selectionPriceMap.put(44,"1/46");
        selectionPriceMap.put(45,"1/47");
        selectionPriceMap.put(46,"1/48");
        selectionPriceMap.put(47,"1/49");
        selectionPriceMap.put(48,"1/50");
        selectionPriceMap.put(49,"1/51");
        selectionPriceMap.put(50,"1/52");
        selectionPriceMap.put(51,"1/53");
        selectionPriceMap.put(52,"1/54");
        selectionPriceMap.put(53,"1/55");
        selectionPriceMap.put(54,"1/56");
        selectionPriceMap.put(55,"1/57");
        selectionPriceMap.put(56,"1/58");
        selectionPriceMap.put(57,"1/59");
        selectionPriceMap.put(58,"1/60");
        selectionPriceMap.put(59,"1/61");
        selectionPriceMap.put(60,"1/62");
        selectionPriceMap.put(61,"1/63");
        selectionPriceMap.put(62,"1/64");
        selectionPriceMap.put(63,"1/65");
        selectionPriceMap.put(64,"1/66");
        selectionPriceMap.put(65,"1/67");
        selectionPriceMap.put(66,"1/68");
        selectionPriceMap.put(67,"1/69");
        selectionPriceMap.put(68,"1/70");
        selectionPriceMap.put(69,"1/71");
        selectionPriceMap.put(70,"1/72");
        selectionPriceMap.put(71,"1/73");
        selectionPriceMap.put(72,"1/74");
        selectionPriceMap.put(73,"1/75");
        selectionPriceMap.put(74,"1/76");
        selectionPriceMap.put(75,"1/77");
        selectionPriceMap.put(76,"1/78");
        selectionPriceMap.put(77,"1/79");
        selectionPriceMap.put(78,"1/80");
        selectionPriceMap.put(79,"1/81");
        selectionPriceMap.put(80,"1/82");
        selectionPriceMap.put(81,"1/83");
        selectionPriceMap.put(82,"1/84");
        selectionPriceMap.put(83,"1/85");
        selectionPriceMap.put(84,"1/86");
        selectionPriceMap.put(85,"1/87");
        selectionPriceMap.put(86,"1/88");
        selectionPriceMap.put(87,"1/89");
        selectionPriceMap.put(88,"1/90");
        selectionPriceMap.put(89,"1/91");
        selectionPriceMap.put(90,"1/92");
        selectionPriceMap.put(91,"1/93");
        selectionPriceMap.put(92,"1/94");
        selectionPriceMap.put(93,"1/95");
        selectionPriceMap.put(94,"1/96");
        selectionPriceMap.put(95,"1/97");
        selectionPriceMap.put(96,"1/98");
        selectionPriceMap.put(97,"1/99");
        selectionPriceMap.put(98,"1/100");
        selectionPriceMap.put(99,"1/101");
        selectionPriceMap.put(100,"1/102");
        return selectionPriceMap;
    }

    @Override
    public Map<Integer, String> getRacingGenericSelectionText() {
        Map<Integer, String> selectionPriceMap = new LinkedHashMap<>();
        selectionPriceMap.put(0, "207 or Less");
        selectionPriceMap.put(1, "60 lengths");
        selectionPriceMap.put(2, "27 to 35 Lengths");
        selectionPriceMap.put(3, "Race 2");
        return selectionPriceMap;
    }

    @Override
    public Map<Integer, String> getRacingSpecialsSelectionText() {
        Map<Integer, String> selectionPriceMap = new LinkedHashMap<>();
        selectionPriceMap.put(0, "Any horse to win 2016");
        selectionPriceMap.put(1, "Richard Johnson To Ride");
        selectionPriceMap.put(2, "Resolution Bay");
        return selectionPriceMap;
    }

    @Override
    public Map<Integer, String> getScorersNames() {
        Map<Integer, String> ScorersNamesMap = new LinkedHashMap<>();
        ScorersNamesMap.put(0, "Team A Player 1");
        ScorersNamesMap.put(1, "Team A Player 2");
        ScorersNamesMap.put(2, "Team A Player 3");
        ScorersNamesMap.put(3, "Team A Player 4");
        ScorersNamesMap.put(4, "Team A Player 5");
        ScorersNamesMap.put(5, "Team A Player 6");
        ScorersNamesMap.put(6, "Team A Player 7");
        ScorersNamesMap.put(7, "Team A Player 8");
        ScorersNamesMap.put(8, "Team A Player 9");
        ScorersNamesMap.put(9, "Team A Player 10");
        ScorersNamesMap.put(10, "Team A Player 11");
        ScorersNamesMap.put(11, "Team A Player 12");
        ScorersNamesMap.put(12, "Team A Player 13");
        ScorersNamesMap.put(13, "Team A Player 14");
        ScorersNamesMap.put(14, "Team A Player 15");
        ScorersNamesMap.put(15, "Team A Player 16");
        ScorersNamesMap.put(16, "Team A Player 17");
        ScorersNamesMap.put(17, "Team A Player 18");
        ScorersNamesMap.put(18, "Team A Player 19");
        ScorersNamesMap.put(19, "Team A Player 20");
        ScorersNamesMap.put(20, "Team B Player 1");
        ScorersNamesMap.put(21, "Team B Player 2");
        ScorersNamesMap.put(22, "Team B Player 3");
        ScorersNamesMap.put(23, "Team B Player 4");
        ScorersNamesMap.put(24, "Team B Player 5");
        ScorersNamesMap.put(25, "Team B Player 6");
        ScorersNamesMap.put(26, "Team B Player 7");
        ScorersNamesMap.put(27, "Team B Player 8");
        ScorersNamesMap.put(28, "Team B Player 9");
        ScorersNamesMap.put(29, "Team B Player 10");
        ScorersNamesMap.put(30, "Team B Player 11");
        ScorersNamesMap.put(31, "Team B Player 12");
        ScorersNamesMap.put(32, "Team B Player 13");
        ScorersNamesMap.put(33, "Team B Player 14");
        ScorersNamesMap.put(34, "Team B Player 15");
        ScorersNamesMap.put(35, "Team B Player 16");
        ScorersNamesMap.put(36, "Team B Player 17");
        ScorersNamesMap.put(37, "Team B Player 18");
        ScorersNamesMap.put(38, "Team B Player 19");
        ScorersNamesMap.put(39, "Team B Player 20");
        ScorersNamesMap.put(40, "No Goalscorer");
        return ScorersNamesMap;
    }

    @Override
    public Map<Integer, String> getScorersHomeAwayNone() {
        Map<Integer, String> ScorersTypeMap = new LinkedHashMap<>();
        ScorersTypeMap.put(0, "home");
        ScorersTypeMap.put(1, "home");
        ScorersTypeMap.put(2, "home");
        ScorersTypeMap.put(3, "home");
        ScorersTypeMap.put(4, "home");
        ScorersTypeMap.put(5, "home");
        ScorersTypeMap.put(6, "home");
        ScorersTypeMap.put(7, "home");
        ScorersTypeMap.put(8, "home");
        ScorersTypeMap.put(9, "home");
        ScorersTypeMap.put(10, "home");
        ScorersTypeMap.put(11, "home");
        ScorersTypeMap.put(12, "home");
        ScorersTypeMap.put(13, "home");
        ScorersTypeMap.put(14, "home");
        ScorersTypeMap.put(15, "home");
        ScorersTypeMap.put(16, "home");
        ScorersTypeMap.put(17, "home");
        ScorersTypeMap.put(18, "home");
        ScorersTypeMap.put(19, "home");
        ScorersTypeMap.put(20, "away");
        ScorersTypeMap.put(21, "away");
        ScorersTypeMap.put(22, "away");
        ScorersTypeMap.put(23, "away");
        ScorersTypeMap.put(24, "away");
        ScorersTypeMap.put(25, "away");
        ScorersTypeMap.put(26, "away");
        ScorersTypeMap.put(27, "away");
        ScorersTypeMap.put(28, "away");
        ScorersTypeMap.put(29, "away");
        ScorersTypeMap.put(30, "away");
        ScorersTypeMap.put(31, "away");
        ScorersTypeMap.put(32, "away");
        ScorersTypeMap.put(33, "away");
        ScorersTypeMap.put(34, "away");
        ScorersTypeMap.put(35, "away");
        ScorersTypeMap.put(36, "away");
        ScorersTypeMap.put(37, "away");
        ScorersTypeMap.put(38, "away");
        ScorersTypeMap.put(39, "away");
        ScorersTypeMap.put(40, "none");
        return ScorersTypeMap;
    }

    @Override
    public Map<Integer, String> getScorersPrice() {
        Map<Integer, String> ScorersPriceMap = new LinkedHashMap<>();
        ScorersPriceMap.put(0, "1/2");
        ScorersPriceMap.put(1, "1/3");
        ScorersPriceMap.put(2, "1/4");
        ScorersPriceMap.put(3, "1/5");
        ScorersPriceMap.put(4, "1/6");
        ScorersPriceMap.put(5, "1/7");
        ScorersPriceMap.put(6, "1/8");
        ScorersPriceMap.put(7, "1/9");
        ScorersPriceMap.put(8, "1/10");
        ScorersPriceMap.put(9, "1/11");
        ScorersPriceMap.put(10, "1/12");
        ScorersPriceMap.put(11, "1/13");
        ScorersPriceMap.put(12, "1/14");
        ScorersPriceMap.put(13, "1/15");
        ScorersPriceMap.put(14, "1/16");
        ScorersPriceMap.put(15, "1/17");
        ScorersPriceMap.put(16, "1/18");
        ScorersPriceMap.put(17, "1/19");
        ScorersPriceMap.put(18, "1/20");
        ScorersPriceMap.put(19, "1/21");
        ScorersPriceMap.put(20, "1/22");
        ScorersPriceMap.put(21, "1/23");
        ScorersPriceMap.put(22, "1/24");
        ScorersPriceMap.put(23, "1/25");
        ScorersPriceMap.put(24, "1/26");
        ScorersPriceMap.put(25, "1/27");
        ScorersPriceMap.put(26, "1/28");
        ScorersPriceMap.put(27, "1/29");
        ScorersPriceMap.put(28, "1/30");
        ScorersPriceMap.put(29, "1/31");
        ScorersPriceMap.put(30, "1/32");
        ScorersPriceMap.put(31, "1/33");
        ScorersPriceMap.put(32, "1/34");
        ScorersPriceMap.put(33, "1/35");
        ScorersPriceMap.put(34, "1/36");
        ScorersPriceMap.put(35, "1/37");
        ScorersPriceMap.put(36, "1/38");
        ScorersPriceMap.put(37, "1/39");
        ScorersPriceMap.put(38, "1/40");
        ScorersPriceMap.put(39, "1/41");
        ScorersPriceMap.put(40, "1/42");
        ScorersPriceMap.put(41, "1/43");
        ScorersPriceMap.put(42, "1/44");
        ScorersPriceMap.put(43, "1/45");
        ScorersPriceMap.put(44, "1/46");
        ScorersPriceMap.put(45, "1/47");
        ScorersPriceMap.put(46, "1/48");
        ScorersPriceMap.put(47, "1/49");
        ScorersPriceMap.put(48, "1/50");
        ScorersPriceMap.put(49, "1/51");
        return ScorersPriceMap;
    }

    @Override
    public Map<Integer, String> getCorrectScoreValues() {
        Map<Integer, String> correctScoreValuesMap = new LinkedHashMap<>();
        correctScoreValuesMap.put(0, "home=\"1\" away=\"0\"");
        correctScoreValuesMap.put(1, "home=\"2\" away=\"0\"");
        correctScoreValuesMap.put(2, "home=\"2\" away=\"1\"");
        correctScoreValuesMap.put(3, "home=\"3\" away=\"0\"");
        correctScoreValuesMap.put(4, "home=\"3\" away=\"1\"");
        correctScoreValuesMap.put(5, "home=\"3\" away=\"2\"");
        correctScoreValuesMap.put(6, "home=\"4\" away=\"0\"");
        correctScoreValuesMap.put(7, "home=\"4\" away=\"1\"");
        correctScoreValuesMap.put(8, "home=\"4\" away=\"2\"");
        correctScoreValuesMap.put(9, "home=\"4\" away=\"3\"");
        correctScoreValuesMap.put(10, "home=\"5\" away=\"0\"");
        correctScoreValuesMap.put(11, "home=\"5\" away=\"1\"");
        correctScoreValuesMap.put(12, "home=\"5\" away=\"2\"");
        correctScoreValuesMap.put(13, "home=\"6\" away=\"0\"");
        correctScoreValuesMap.put(14, "home=\"6\" away=\"1\"");
        correctScoreValuesMap.put(15, "home=\"7\" away=\"0\"");
        correctScoreValuesMap.put(16, "home=\"7\" away=\"1\"");
        correctScoreValuesMap.put(17, "home=\"8\" away=\"0\"");
        correctScoreValuesMap.put(18, "home=\"9\" away=\"0\"");
        correctScoreValuesMap.put(19, "home=\"10\" away=\"0\"");
        correctScoreValuesMap.put(20, "home=\"0\" away=\"0\"");
        correctScoreValuesMap.put(21, "home=\"1\" away=\"1\"");
        correctScoreValuesMap.put(22, "home=\"2\" away=\"2\"");
        correctScoreValuesMap.put(23, "home=\"3\" away=\"3\"");
        correctScoreValuesMap.put(24, "home=\"4\" away=\"4\"");
        correctScoreValuesMap.put(25, "home=\"0\" away=\"1\"");
        correctScoreValuesMap.put(26, "home=\"0\" away=\"2\"");
        correctScoreValuesMap.put(27, "home=\"1\" away=\"2\"");
        correctScoreValuesMap.put(28, "home=\"0\" away=\"3\"");
        correctScoreValuesMap.put(29, "home=\"1\" away=\"3\"");
        correctScoreValuesMap.put(30, "home=\"2\" away=\"3\"");
        correctScoreValuesMap.put(31, "home=\"0\" away=\"4\"");
        correctScoreValuesMap.put(32, "home=\"1\" away=\"4\"");
        correctScoreValuesMap.put(33, "home=\"2\" away=\"4\"");
        correctScoreValuesMap.put(34, "home=\"3\" away=\"4\"");
        correctScoreValuesMap.put(35, "home=\"0\" away=\"5\"");
        correctScoreValuesMap.put(36, "home=\"1\" away=\"5\"");
        correctScoreValuesMap.put(37, "home=\"2\" away=\"5\"");
        correctScoreValuesMap.put(38, "home=\"0\" away=\"6\"");
        correctScoreValuesMap.put(39, "home=\"1\" away=\"6\"");
        correctScoreValuesMap.put(40, "home=\"0\" away=\"7\"");
        correctScoreValuesMap.put(41, "home=\"1\" away=\"7\"");
        correctScoreValuesMap.put(42, "home=\"0\" away=\"8\"");
        correctScoreValuesMap.put(43, "home=\"0\" away=\"9\"");
        correctScoreValuesMap.put(44, "home=\"0\" away=\"10\"");
        return correctScoreValuesMap;
    }

    @Override
    public Map<Integer, String> getCorrectScoreNames() {
        Map<Integer, String> correctScoreNamesMap = new LinkedHashMap<>();
        correctScoreNamesMap.put(0, "|TeamA|1-0");
        correctScoreNamesMap.put(1, "|TeamA|2-0");
        correctScoreNamesMap.put(2, "|TeamA|2-1");
        correctScoreNamesMap.put(3, "|TeamA|3-0");
        correctScoreNamesMap.put(4, "|TeamA|3-1");
        correctScoreNamesMap.put(5, "|TeamA|3-2");
        correctScoreNamesMap.put(6, "|TeamA|4-0");
        correctScoreNamesMap.put(7, "|TeamA|4-1");
        correctScoreNamesMap.put(8, "|TeamA|4-2");
        correctScoreNamesMap.put(9, "|TeamA|4-3");
        correctScoreNamesMap.put(10, "|TeamA|5-0");
        correctScoreNamesMap.put(11, "|TeamA|5-1");
        correctScoreNamesMap.put(12, "|TeamA|5-2");
        correctScoreNamesMap.put(13, "|TeamA|6-0");
        correctScoreNamesMap.put(14, "|TeamA|6-1");
        correctScoreNamesMap.put(15, "|TeamA|7-0");
        correctScoreNamesMap.put(16, "|TeamA|7-1");
        correctScoreNamesMap.put(17, "|TeamA|8-0");
        correctScoreNamesMap.put(18, "|TeamA|9-0");
        correctScoreNamesMap.put(19, "|TeamA|10-0");
        correctScoreNamesMap.put(20, "|Draw|0-0");
        correctScoreNamesMap.put(21, "|Draw|1-1");
        correctScoreNamesMap.put(22, "|Draw|2-2");
        correctScoreNamesMap.put(23, "|Draw|3-3");
        correctScoreNamesMap.put(24, "|Draw|4-4");
        correctScoreNamesMap.put(25, "|TeamB|1-0");
        correctScoreNamesMap.put(26, "|TeamB|2-0");
        correctScoreNamesMap.put(27, "|TeamB|2-1");
        correctScoreNamesMap.put(28, "|TeamB|3-0");
        correctScoreNamesMap.put(29, "|TeamB|3-1");
        correctScoreNamesMap.put(30, "|TeamB|3-2");
        correctScoreNamesMap.put(31, "|TeamB|4-0");
        correctScoreNamesMap.put(32, "|TeamB|4-1");
        correctScoreNamesMap.put(33, "|TeamB|4-2");
        correctScoreNamesMap.put(34, "|TeamB|4-3");
        correctScoreNamesMap.put(35, "|TeamB|5-0");
        correctScoreNamesMap.put(36, "|TeamB|5-1");
        correctScoreNamesMap.put(37, "|TeamB|5-2");
        correctScoreNamesMap.put(38, "|TeamB|6-0");
        correctScoreNamesMap.put(39, "|TeamB|6-1");
        correctScoreNamesMap.put(40, "|TeamB|7-0");
        correctScoreNamesMap.put(41, "|TeamB|7-1");
        correctScoreNamesMap.put(42, "|TeamB|8-0");
        correctScoreNamesMap.put(43, "|TeamB|9-0");
        correctScoreNamesMap.put(44, "|TeamB|10-0");
        return correctScoreNamesMap;
    }

    @Override
    public Map<Integer, String> getHorses() {
        Map<Integer, String> horsesNamesMap = new LinkedHashMap<>();
        horsesNamesMap.put(0, "It's A Close Call");
        horsesNamesMap.put(1, "Final Nudge");
        horsesNamesMap.put(2, "Captain Probus");
        horsesNamesMap.put(3, "Pursuitofhappiness");
        horsesNamesMap.put(4, "Abidjan");
        horsesNamesMap.put(5, "Multimedia");
        horsesNamesMap.put(6, "Queen Of Rock");
        horsesNamesMap.put(7, "Sandford Castle");
        horsesNamesMap.put(8, "Whatthebutlersaw");
        horsesNamesMap.put(9, "Dreamisi");
        horsesNamesMap.put(10, "Master Hide");
        horsesNamesMap.put(11, "Darsi's Dream");
        horsesNamesMap.put(12, "East Hill");
        return horsesNamesMap;
    }

    @Override
    public Map<Integer, String> getJockeys() {
        Map<Integer, String> jockeysNamesMap = new LinkedHashMap<>();
        jockeysNamesMap.put(0, "Rawlinson");
        jockeysNamesMap.put(1, "Alistair");
        jockeysNamesMap.put(2, "Egan, J");
        jockeysNamesMap.put(3, "Quinlan, Mr R P");
        jockeysNamesMap.put(4, "Fox, K");
        jockeysNamesMap.put(5, "Sousa, S De");
        jockeysNamesMap.put(6, "Broome");
        jockeysNamesMap.put(7, "Miss A");
        jockeysNamesMap.put(8, "O'Connell B T");
        jockeysNamesMap.put(9, "TwistonDaviesW");
        jockeysNamesMap.put(10, "Best J");
        jockeysNamesMap.put(11, "O Brien");
        jockeysNamesMap.put(12, "Paul");
        return jockeysNamesMap;
    }

    @Override
    public Map<Integer, String> getGreyhounds() {
        Map<Integer, String> greyhoundsNamesMap = new LinkedHashMap<>();
        greyhoundsNamesMap.put(0, "Jogon Jenny");
        greyhoundsNamesMap.put(1, "Louliediem Gem");
        greyhoundsNamesMap.put(2, "Jayms Brightwood");
        greyhoundsNamesMap.put(3, "Witcombe Triumph");
        greyhoundsNamesMap.put(4, "No Picnic");
        greyhoundsNamesMap.put(5, "Our Girl Una");
        greyhoundsNamesMap.put(6, "Aintgottwobob");
        greyhoundsNamesMap.put(7, "Mottos Rogue");
        greyhoundsNamesMap.put(8, "Minnies Trixie");
        greyhoundsNamesMap.put(9, "Crucial Diva");
        greyhoundsNamesMap.put(10, "Chickadee");
        greyhoundsNamesMap.put(11, "Chilly Baja");
        greyhoundsNamesMap.put(12, "Primo Lisa");
        return greyhoundsNamesMap;
    }

    @Override
    public Map<Integer, String> setGenericMarketPreMatchMap(int number) {
        Map<Integer, String> PreMatchMap = new LinkedHashMap<>();
        for (int x=0; x<number; x++) {
            PreMatchMap.put(x, "To Win Standard "+x);
        }
        return PreMatchMap;
    }

    @Override
    public Map<Integer, String> setGenericMarketInPlayMap(int number) {
        Map<Integer, String> InPlayMap = new LinkedHashMap<>();
        for (int x=0; x<number; x++) {
            InPlayMap.put(x, "To Win Standard Live "+x);
        }
        return InPlayMap;
    }

    @Override
    public Map<Integer, String> setGenericSelectionMap(int number) {
        Map<Integer, String> SelectionMap = new LinkedHashMap<>();
        for (int x=0; x<number; x++) {
            SelectionMap.put(x, "Player "+x);
        }
        return SelectionMap;
    }

}