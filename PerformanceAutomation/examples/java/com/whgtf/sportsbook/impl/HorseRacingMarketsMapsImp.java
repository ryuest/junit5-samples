package com.whgtf.sportsbook.impl;

import com.whgtf.sportsbook.interfaces.HorseRacingMarketsMaps;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Juri on 15/11/2016.
 */
public class HorseRacingMarketsMapsImp extends GenericMapsImpl implements HorseRacingMarketsMaps {

    @Override
    public Map<Integer, String> winWin() {
        Map<Integer, String> winWinMap = new LinkedHashMap<>();
        winWinMap.put(0, "Match Bets");
        winWinMap.put(1, "Trainer Match Bets");
        winWinMap.put(2, "Jockey Match Bets");
        return winWinMap;
    }
    @Override
    public Map<Integer, String> placeInsure() {
        Map<Integer, String> placeInsureMap = new LinkedHashMap<>();
        placeInsureMap.put(0, "Place Only| - 2 |Places");
        placeInsureMap.put(1, "Place Only| - 3 |Places");
        placeInsureMap.put(2, "Insure| - 2 |Places");
        placeInsureMap.put(3, "Insure| - 3 |Places");
        placeInsureMap.put(4, "Win");
        return placeInsureMap;
    }

    @Override
    public Map<Integer, String> bettingWithout() {
        Map<Integer, String> bettingWithoutMap = new LinkedHashMap<>();
        bettingWithoutMap.put(0, "Betting Without");
        return bettingWithoutMap;
    }

    @Override
    public Map<Integer, String> antepostSpecials() {
        Map<Integer, String> antepostSpecialsMap = new LinkedHashMap<>();
        antepostSpecialsMap.put(0, "Outright");
        return antepostSpecialsMap;
    }

    @Override
    public Map<Integer, String> meetingsMarkets() {
        Map<Integer, String> meetingsMarketsMap = new LinkedHashMap<>();
        meetingsMarketsMap.put(0, "Jockey Challenge");
        meetingsMarketsMap.put(1, "Jockey To Ride At Least");
        meetingsMarketsMap.put(2, "Winning Distances| - |5 Way");
        meetingsMarketsMap.put(3, "Winning Distances| - |3 Way");
        meetingsMarketsMap.put(4, "Favourites Performance| - |3 Way");
        meetingsMarketsMap.put(5, "Trainer To Saddle At Least");
        meetingsMarketsMap.put(6, "Total Racecard Numbers| - |3 Way");
        meetingsMarketsMap.put(7, "Total SPs| - |3 Way");
        meetingsMarketsMap.put(8, "Race With Longest Winning Distance");
        meetingsMarketsMap.put(9, "Trainer Challenge");
        return meetingsMarketsMap;
    }

    @Override
    public Map<Integer, String> meetingsMarketsForecast() {
        Map<Integer, String> meetingsMarketsForecastMap = new LinkedHashMap<>();
        meetingsMarketsForecastMap.put(0, "Jockey Challenge Straight Forecast");
        meetingsMarketsForecastMap.put(1, "Jockey Challenge Reverse Forecast");
        meetingsMarketsForecastMap.put(2, "Trainer Challenge Straight Forecast");
        meetingsMarketsForecastMap.put(3, "Trainer Challenge Reverse Forecast");
        return meetingsMarketsForecastMap;
    }
}
