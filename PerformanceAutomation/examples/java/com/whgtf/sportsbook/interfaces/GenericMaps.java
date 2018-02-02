package com.whgtf.sportsbook.interfaces;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Juri on 28/10/2016.
 */
public interface GenericMaps {

    Map<Integer, String> getHomeAway();

    Map<Integer, String> getOverUnder();

    Map<Integer, String> getHomeDrawAway();

    Map<Integer, String> getHandicapType();

    Map<Integer, String> getHandicapName();

    Map<Integer, String> getHandicapValues();

    Map<Integer, String> getHomeDrawAwayName();

    Map<Integer, String> getHomeAwayName();

    Map<Integer, String> getPrice();

    Map<Integer, String> getScorersNames();

    Map<Integer, String> getScorersHomeAwayNone();

    Map<Integer, String> getScorersPrice();

    Map<Integer, String> getCorrectScoreValues();

    Map<Integer, String> getCorrectScoreNames();

    Map<Integer, String> getHorses();

    Map<Integer, String> getJockeys();

    Map<Integer, String> getGreyhounds();

    Map<Integer, String> getRacingGenericSelectionText();

    Map<Integer, String> getRacingSpecialsSelectionText();

    Map<Integer, String> setGenericMarketPreMatchMap(int number);

    Map<Integer, String> setGenericMarketInPlayMap(int number);

    Map<Integer, String> setGenericSelectionMap(int number);

}
