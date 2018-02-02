package com.whgtf.sportsbook.interfaces;

import java.util.Map;

/**
 * Created by Juri on 27/10/2016.
 */
public interface FootballMarketsMaps extends GenericMaps {

    Map<Integer, String> footballMarketsWinDrawWin();

    Map<Integer, String> footballMarketsWinDrawWinLive();

    Map<Integer, String> footballMarketsStandardTreeSelections();

    Map<Integer, String> footballMarketsStandardTwoSelectionsLive();

    Map<Integer, String> footballMarketsStandardTwoSelections();

    Map<Integer, String> topMarkets();

    Map<Integer, String> topMarketsLive();

    Map<Integer, String> footballMarketsScorersThreeSelections();

    Map<Integer, String> footballMarketsScorersThreeSelectionsLive();

    Map<Integer, String> footballMarketsCorrectScore();

    Map<Integer, String> footballMarketsCorrectScoreSortTypes();

    Map<Integer, String> footballMarketsCorrectScoreLive();

    Map<Integer, String> footballMarketsCorrectScoreSortTypesLive();

    Map<Integer, String> footballMarketsCorrectScoreSortTypesSpecialLive();

    Map<Integer, String> footballMarketsHandicaps();

    Map<Integer, String> footballMarketsHandicapsLive();

    Map<Integer, String> footballSpecialBettingLive();

    Map<Integer, String> footballSpecialBettingGoalFirstLastLive();

    Map<Integer, String> footballSpecialBettingGoalXLive();

    Map<Integer, String> footballMarketsOverUnderLive();

    Map<String, String> footballSpecialBettingCorrectNames();

    Map<Integer, String> americanFootballOverUnder();

    Map<Integer, String> americanFootballHandicap();

    Map<Integer, String> americanFootballWinWin();


}