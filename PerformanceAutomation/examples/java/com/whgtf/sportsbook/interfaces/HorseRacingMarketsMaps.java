package com.whgtf.sportsbook.interfaces;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Juri on 15/11/2016.
 */
public interface HorseRacingMarketsMaps extends GenericMaps {

     Map<Integer, String> winWin();

     Map<Integer, String> placeInsure();

     Map<Integer, String> bettingWithout();

     Map<Integer, String> antepostSpecials();

     Map<Integer, String> meetingsMarkets();

     Map<Integer, String> meetingsMarketsForecast();

}
