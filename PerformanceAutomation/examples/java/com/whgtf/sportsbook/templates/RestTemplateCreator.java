package com.whgtf.sportsbook.templates;

import com.whgtf.sportsbook.common.PropArgs;
import com.whgtf.sportsbook.impl.FootballMarketsMapsImp;
import com.whgtf.sportsbook.impl.HorseRacingMarketsMapsImp;
import com.whgtf.sportsbook.interfaces.FootballMarketsMaps;
import com.whgtf.sportsbook.interfaces.HorseRacingMarketsMaps;
import com.whgtf.sportsbook.model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Lazy
public class RestTemplateCreator {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RestTemplateCreator.class.getName());

    Event event = new Event();

    Market market= new Market();

    private String user;

    private String password;

    private StringBuilder request;

    public int marketNumberXML = 1;

    String order = "1";

    FootballMarketsMaps footballMarketsMaps = new FootballMarketsMapsImp();

    HorseRacingMarketsMaps horseRacingMarketsMaps = new HorseRacingMarketsMapsImp();

    public RestTemplateCreator(String env) {
        PropArgs props = new PropArgs(env);
        request = new StringBuilder();
        user = props.getOpenbetUsername();
        password = props.getOpenbetPassword();
    }

    public String createEventRequest(EventSettings eventSettings, Map<Integer, String> marketsMap, Map<Integer, String> selectionsMap, boolean live) {
        clearRequest();
        request.append(String.format(TemplateTags.COMMON_TAGS.HEADER_TAG, user, password));
        request.append("\t");
        request.append(TemplateTags.EVENT_TAGS.EVENT_OPEN_TAG);
        request.append("\t\t");
        event.setCompetitionId(CompetitionIds.getCompetitionId(eventSettings.getSport(), eventSettings.getCompetition()));
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_OPEN_BET_ID_TAG, event.getCompetitionId().toString()));
        event.setRandomNameWithPrefix(eventSettings.getEventName());
        event.setExternal_id(RandomStringUtils.randomAlphanumeric(6));
        request.append("\t\t\t");
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_EXTERNAL_ID_TAG, event.externalEventId));
        request.append("\t\t\t");
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_NAME_TAG, event.getName()));
        request.append("\t\t\t");
        event.setStartTime(eventSettings.getStartTime());
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_START_TIME, eventSettings.getOffFlag(), event.getStartTime()));
        String homeTeam = "AIFK";
        String awayTeam = "AFC";
        if (event.getHomeTeam().isEmpty())
            event.setHomeTeam(homeTeam);
        if (event.getAwayTeam().isEmpty())
            event.setAwayTeam(awayTeam);
        if (!eventSettings.env.toLowerCase().trim().equals("dev")) {
            if (eventSettings.getIsFootball()) {
                request.append("\t\t");
                request.append(TemplateTags.EVENT_TAGS.TEAM_OPEN_TAG);
                request.append("\t\t\t");
                request.append(String.format(TemplateTags.EVENT_TAGS.TEAM_NAME_TAG, "|"+event.getHomeTeam()+"|"));
                request.append("\t\t\t");
                request.append(TemplateTags.EVENT_TAGS.TEAM_HOME_TAG);
                request.append("\t\t");
                request.append(TemplateTags.EVENT_TAGS.TEAM_CLOSE_TAG);
                request.append("\t\t");
                request.append(TemplateTags.EVENT_TAGS.TEAM_OPEN_TAG);
                request.append("\t\t\t");
                request.append(String.format(TemplateTags.EVENT_TAGS.TEAM_NAME_TAG, "|"+event.getAwayTeam()+"|"));
                request.append("\t\t\t");
                request.append(TemplateTags.EVENT_TAGS.TEAM_AWAY_TAG);
                request.append("\t\t");
                request.append(TemplateTags.EVENT_TAGS.TEAM_CLOSE_TAG);
            }
        } else {
            request.append("\t\t\t");
            request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_HOME_TEAM_TAG, event.getHomeTeam()));
            request.append("\t\t\t");
            request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_AWAY_TEAM_TAG, event.getAwayTeam()));
        }
        request.append("\t\t\t");
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_ALLOW_SETTLE, event.getAllowSettle()));
        request.append("\t\t\t");
        request.append(String.format(TemplateTags.COMMON_TAGS.SORT_TAG, event.getSort()));
        request.append("\t\t\t");
        request.append(String.format(TemplateTags.COMMON_TAGS.STATUS_TAG, event.getStatus()));
        request.append("\t\t\t");
        request.append(String.format(TemplateTags.COMMON_TAGS.DISPLAY_TAG, event.getDisplayed()));

        // add topMarkets
        buildMarketsBody(eventSettings, marketsMap, selectionsMap, live);

        request.append("\t");
        request.append(TemplateTags.EVENT_TAGS.EVENT_CLOSE_TAG);
        request.append(TemplateTags.COMMON_TAGS.CLOSE_BODY_TAG);

        StringBuilder req = new StringBuilder(request);

        logger.debug(request);

        return req.toString();
    }

    public String createAddMoreMarketsRequest(EventSettings eventSettings, Map<Integer, String> marketsMap, Map<Integer, String> selectionsMap, boolean live) {

        order = "100";

        clearRequest();

        addOpenBodyLines();

        buildMarketsBody(eventSettings, marketsMap, selectionsMap, live);

        addCloseBodyLines();

        StringBuilder req = new StringBuilder(request);

        logger.debug(request);

        return req.toString();
    }

    private void buildMarketsBody(EventSettings eventSettings, Map<Integer, String> marketsMap, Map<Integer, String> selectionsMap, boolean live) {

        Map<Integer, String> mapTemplateRename = footballMarketsMaps.footballMarketsCorrectScore();
        String handicapTemplateName = "Handicap -1";
        if ((eventSettings.sport.equals("american"))) {
            handicapTemplateName = "Match Spread";
        }

        String genericTemplateName = "Outright";
        String xGoalScorerTemplateName = "X Goalscorer";
        if (live) {
            mapTemplateRename = footballMarketsMaps.footballMarketsCorrectScoreLive();
            if ((eventSettings.sport.equals("american"))) {
                handicapTemplateName = "Match Spread";
            } else {
                handicapTemplateName = "Match Handicap Live";
            }
            genericTemplateName = "To Qualify Live";
            xGoalScorerTemplateName = "X Goalscorer";

        }
        if (marketsMap.size() > 0) {
            for (int marketsNumber = 0; marketsNumber < marketsMap.size(); marketsNumber++) {
                request.append("\t\t");
                request.append(TemplateTags.MARKET_TAGS.MARKET_OPEN_TAG);
                request.append("\t\t");
                request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EXTERNAL_EVENT_ID_TAG, event.externalEventId));
                request.append("\t\t\t");
                request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EXTERNAL_ID_TAG, event.externalEventId + "-M" + marketNumberXML));
                if (eventSettings.getIsCorrectScore()) {
                    if (eventSettings.getIsSpecial()) {
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_NAME_TAG_WITH_PIPES,
                                footballMarketsMaps.footballSpecialBettingCorrectNames().get(marketsMap.get(marketsNumber))));
                    } else {
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_NAME_TAG_WITH_PIPES, mapTemplateRename.get(marketsNumber)));
                    }
                } else if (eventSettings.getIsSpecial()) {

                    if (eventSettings.sport.equals("american")) {
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_NAME_TAG_WITH_PIPES,
                                marketsMap.get(marketsNumber)));
                    } else {

                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_NAME_TAG_WITH_PIPES,
                                footballMarketsMaps.footballSpecialBettingCorrectNames().get(marketsMap.get(marketsNumber))));

                    }


                } else {
                    request.append("\t\t\t");
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_NAME_TAG_WITH_PIPES, marketsMap.get(marketsNumber)));
                }
                if (eventSettings.env.toLowerCase().trim().equals("dev")) {
                    if (!eventSettings.getIsHandicap()) {
                        if (eventSettings.getIsGeneric()) {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TEMPLATE_TAG_WITH_PIPES, genericTemplateName));
                        } else if (eventSettings.getSportMarketList().get(0).toLowerCase().equals("special") && eventSettings.getIsSpecial()) {
                            if (eventSettings.getIsScorers()) {
                                request.append("\t\t\t");
                                request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TEMPLATE_TAG_WITH_PIPES, xGoalScorerTemplateName));
                            } else {
                                request.append("\t\t\t");
                                request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TEMPLATE_TAG_WITH_PIPES, marketsMap.get(marketsNumber)));
                            }
                        } else{
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TEMPLATE_TAG_WITH_PIPES, marketsMap.get(marketsNumber)));
                        }
                    } else {
                        if (!eventSettings.getIsSpecial()) {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TEMPLATE_TAG_WITH_PIPES, handicapTemplateName));
                        } else {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TEMPLATE_TAG_WITH_PIPES, marketsMap.get(marketsNumber)));
                        }
                    }
                } else {
                    if (!eventSettings.getIsHandicap()) {
                        if (eventSettings.getIsGeneric()) {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_SORT_TAG_WITH_PIPES, genericTemplateName));
                        } else if (eventSettings.getSportMarketList().get(0).toLowerCase().equals("special") && eventSettings.getIsSpecial()) {
                            if (eventSettings.getIsScorers()) {
                                request.append("\t\t\t");
                                request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_SORT_TAG_WITH_PIPES, xGoalScorerTemplateName));
                            } else {
                                request.append("\t\t\t");
                                request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_SORT_TAG_WITH_PIPES, marketsMap.get(marketsNumber)));
                            }
                        } else{
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_SORT_TAG_WITH_PIPES, marketsMap.get(marketsNumber)));
                        }
                    } else {
                        if (!eventSettings.getIsSpecial()) {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_SORT_TAG_WITH_PIPES, handicapTemplateName));
                        } else {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_SORT_TAG_WITH_PIPES, marketsMap.get(marketsNumber)));
                        }
                    }
                }
                if (eventSettings.getIsHandicap()) {
                    request.append("\t\t\t");
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_HANDICAP_TAG, footballMarketsMaps.getHandicapValues().get(marketsNumber)));
                }
                request.append("\t\t\t");
                if (live) {
                    request.append(String.format(TemplateTags.COMMON_TAGS.DISPLAY_TAG, eventSettings.getDisplayed_live()));
                    request.append("\t\t\t");
                    request.append(String.format(TemplateTags.COMMON_TAGS.STATUS_TAG, eventSettings.getStatus_live()));
                    request.append("\t\t\t");
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_BETTING_IN_RUNNING_TAG, eventSettings.getBir_live()));
                } else {
                    request.append(String.format(TemplateTags.COMMON_TAGS.DISPLAY_TAG, eventSettings.getDisplayed()));
                    request.append("\t\t\t");
                    request.append(String.format(TemplateTags.COMMON_TAGS.STATUS_TAG, eventSettings.getStatus()));
                    request.append("\t\t\t");
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_BETTING_IN_RUNNING_TAG, eventSettings.getBir()));
                }
                request.append("\t\t\t");
                market.setStartPrice("yes");
                market.setLivePrice("yes");
                request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_PRICING_TAG, market.getLivePrice(), market.getStartPrice()));

                if (eventSettings.getIsHorseRacing()) {
                    if (eventSettings.getIsMeetings()) {
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_GUARANTEED_PRICE_TAG, "yes"));
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EARLY_PRICES_TAG, "yes"));
                    }
                        /*
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EACHWAY_TAG, "yes"));
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EACHWAYPLACES_TAG, "yes"));

                        String[] placesAt = market.getEachWayPlacesAt().trim().split("/");
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EACHWAYPLACESAT_TAG, placesAt[0], placesAt[1]));
                        */
                    if (eventSettings.getIsAntepost()) {
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_ANTEPOST_TAG, "yes"));
                    }
                    if (market.isForecastAvailable()) {
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_FORECAST_TAG, "yes"));
                    }
                    if (market.isTricastAvailable()) {
                        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TRICAST_TAG, "yes"));
                    }
                }
                // add selections to current market
                buildSelectionsBody(eventSettings, selectionsMap, live);

                request.append("\t\t");
                request.append(TemplateTags.MARKET_TAGS.MARKET_CLOSE_TAG);
                marketNumberXML++;
            }
        }
    }

    private void buildSelectionsBody(EventSettings eventSettings, Map<Integer, String> selectionsMap, boolean live) {
        if (selectionsMap.size() > 0) {

            for (int selectionsNumber = 0; selectionsNumber < selectionsMap.size(); selectionsNumber++) {
                int selectionsNumberXML = 1 + selectionsNumber;

                request.append("\t\t");
                request.append(TemplateTags.SELECTION_TAGS.SELECTION_OPEN_TAG);
                request.append("\t\t");
                request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_EXTERNAL_MARKET_ID_TAG, event.externalEventId + "-M" + marketNumberXML));
                request.append("\t\t\t");
                request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_EXTERNAL_ID_TAG, event.externalEventId + "-M" + marketNumberXML + "-S" + selectionsNumberXML));
                request.append("\t\t\t");
                request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_NAME_TAG, selectionsMap.get(selectionsNumber)));
                if (eventSettings.getIsFootball()) {
                    if (!eventSettings.getIsStandard() && !eventSettings.getIsCorrectScore()) {
                        if (eventSettings.getIsScorers()) {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_TYPE_TAG, footballMarketsMaps.getScorersHomeAwayNone().get(selectionsNumber)));
                        } else {
                            if (eventSettings.getIsHandicap()) {
                                if (!eventSettings.getIsSpecial()) {
                                    request.append("\t\t\t");
                                    request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_TYPE_TAG, footballMarketsMaps.getHandicapType().get(selectionsNumber)));
                                } else {
                                    request.append("\t\t\t");
                                    request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_TYPE_TAG, footballMarketsMaps.getOverUnder().get(selectionsNumber)));
                                }
                            } else {
                                if (eventSettings.getIsWinDrawWin()) {
                                    request.append("\t\t\t");
                                    request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_TYPE_TAG, footballMarketsMaps.getHomeDrawAway().get(selectionsNumber)));
                                } else {
                                    request.append("\t\t\t");
                                    request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_TYPE_TAG, footballMarketsMaps.getHomeAway().get(selectionsNumber)));
                                }
                            }
                        }
                    }
                    if (eventSettings.getIsScorers() || eventSettings.getIsCorrectScore()) {
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_PRICE_TAG, footballMarketsMaps.getScorersPrice().get(selectionsNumber)));
                    } else {
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_PRICE_TAG, footballMarketsMaps.getPrice().get(selectionsNumber)));
                    }
                    if (eventSettings.getIsCorrectScore()) {
                        if (eventSettings.env.toLowerCase().trim().equals("dev")) {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_TYPE_TAG, "score"));
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_SCORE, footballMarketsMaps.getCorrectScoreValues().get(selectionsNumber)));
                        } else {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_SELECTION_SCORE, footballMarketsMaps.getCorrectScoreValues().get(selectionsNumber)));
                        }
                    }
                } else {
                    if (eventSettings.getIsHorseRacing()) {
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_PRICE_TAG, footballMarketsMaps.getScorersPrice().get(selectionsNumber)));
                        if (eventSettings.getIsWinDrawWin()) {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_TYPE_TAG, footballMarketsMaps.getHomeAway().get(selectionsNumber)));
                        }
                        if (eventSettings.getIsMeetings()) {
                            request.append("\t\t\t");
                            request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_RUNNER_TAG, String.valueOf(selectionsNumberXML)));
                        }
                    } else {
                        request.append("\t\t\t");
                        request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_PRICE_TAG, footballMarketsMaps.getPrice().get(selectionsNumber)));
                    }
                }

                request.append("\t\t\t");
                if (live) {
                    request.append(String.format(TemplateTags.COMMON_TAGS.DISPLAY_TAG, eventSettings.getDisplayed_live()));
                    request.append("\t\t\t");
                    request.append(String.format(TemplateTags.COMMON_TAGS.STATUS_TAG, eventSettings.getStatus_live()));
                } else {
                    request.append(String.format(TemplateTags.COMMON_TAGS.DISPLAY_TAG, eventSettings.getDisplayed()));
                    request.append("\t\t\t");
                    request.append(String.format(TemplateTags.COMMON_TAGS.STATUS_TAG, eventSettings.getStatus()));
                }
                request.append("\t\t");
                request.append(TemplateTags.SELECTION_TAGS.SELECTION_CLOSE_TAG);
            }
        }
    }

    private void clearRequest() {
        request = new StringBuilder();
    }

    public void addOpenBodyLines() {
        request.append(String.format(TemplateTags.COMMON_TAGS.HEADER_TAG, user, password));
        request.append("\t");
    }

    public void addCloseBodyLines() {
        request.append(TemplateTags.COMMON_TAGS.CLOSE_BODY_TAG);
        request.append("\t");
    }
}
