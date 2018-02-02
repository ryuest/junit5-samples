package com.whgtf.sportsbook.templates;

import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.reader.PropertyPomReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by javierg on 13/09/2016.
 */
@Component
@Lazy
public class TemplateCreator {


    private String pattern = "\\$\\{%s}";

    private String user;

    private String password;

    private PropertyPomReader propertyPomReader;

    private StringBuilder request;


    public TemplateCreator() {
        request = new StringBuilder();
        propertyPomReader = new PropertyPomReader();
        user = propertyPomReader.getOxifeedUser();
        password = propertyPomReader.getOxifeedPass();
    }

    public String createRequest(Event event) {

        String externalEventId = new SimpleDateFormat("MMddHHmmss").format(new Date());

        request.append(String.format(TemplateTags.COMMON_TAGS.HEADER_TAG, user, password));
        request.append(TemplateTags.EVENT_TAGS.EVENT_OPEN_TAG);
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_OPEN_BET_ID_TAG,event.getCompetitionId().toString()));
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_EXTERNAL_ID_TAG,externalEventId));
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_NAME_TAG,event.getName()));
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_START_TIME,event.getOffFlag(),event.getStartTime()));
        String homeTeam = "IFFK";
        String awayTeam = "ARC";
        if(event.getHomeTeam().isEmpty())
            event.setHomeTeam(homeTeam);
        if(event.getAwayTeam().isEmpty())
            event.setAwayTeam(awayTeam);
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_HOME_TEAM_TAG,event.getHomeTeam()));
        request.append(String.format(TemplateTags.EVENT_TAGS.EVENT_AWAY_TEAM_TAG,event.getAwayTeam()));

        if (!event.getSort().isEmpty())
            request.append(String.format(TemplateTags.COMMON_TAGS.SORT_TAG,event.getSort()));
        if (!event.getStatus().isEmpty())
            request.append(String.format(TemplateTags.COMMON_TAGS.STATUS_TAG,event.getStatus()));
        if (!event.getDisplayed().isEmpty())
            request.append(String.format(TemplateTags.COMMON_TAGS.DISPLAY_TAG,event.getDisplayed()));

        buildMarketsBody(event, externalEventId);

        request.append(TemplateTags.EVENT_TAGS.EVENT_CLOSE_TAG);
        request.append(TemplateTags.COMMON_TAGS.CLOSE_BODY_TAG);

        StringBuilder req = new StringBuilder(request);
        clearRequest();
        return req.toString();
    }

    public String createRequest(Selection selection) {

        request.append(String.format(TemplateTags.COMMON_TAGS.HEADER_TAG, user, password));

        request.append(TemplateTags.SELECTION_TAGS.SELECTION_UPDATE_OPEN_TAG);

        // set mandatory fields
        request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_ID_TAG, selection.getId()));

        // set optional fields
        if (!selection.name.isEmpty())
            request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_NAME_TAG, selection.name));
        if(!selection.price.isEmpty())
            request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_PRICE_TAG, selection.price));
        if(!selection.status.isEmpty())
            request.append(String.format(TemplateTags.COMMON_TAGS.STATUS_TAG, selection.status.toLowerCase()));
        if(!selection.displayed.isEmpty())
            request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_DISPLAY_ORDER,
                    selection.displayed.toLowerCase(), selection.getOrder()));

        // close selection request headers
        request.append(TemplateTags.SELECTION_TAGS.SELECTION_UPDATE_CLOSE_TAG);
        request.append(TemplateTags.COMMON_TAGS.CLOSE_BODY_TAG);

        //logger.info(String.format("selection with id:%s updated", selection.id));
        StringBuilder req = new StringBuilder(request);
        clearRequest();
        return req.toString();
    }

    public String createRequest(Market market) {

        request.append(String.format(TemplateTags.COMMON_TAGS.HEADER_TAG, user, password));

        request.append(TemplateTags.MARKET_TAGS.MARKET_UPDATE_OPEN_TAG);

        request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_ID_TAG,market.getId()));

        if (!market.getName().isEmpty())
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_NAME_TAG, market.getName()));
        if (!market.getStatus().isEmpty())
            request.append(String.format(TemplateTags.COMMON_TAGS.STATUS_TAG, market.getStatus()));
        if (!market.getDisplayed().isEmpty())
            request.append(String.format(TemplateTags.COMMON_TAGS.DISPLAY_TAG, market.getDisplayed()));
        if (!market.getBir().isEmpty())
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_BETTING_IN_RUNNING_TAG, market.getBir()));


        if (!market.getHandicap().isEmpty())
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_HANDICAP_TAG, market.getHandicap()));
        if (!market.getStartPrice().isEmpty()) {
            String market_pricing = String.format(TemplateTags.MARKET_TAGS.MARKET_PRICING_TAG, market.getLivePrice(), market.getStartPrice());
            request.append(market_pricing);
        }
        if (!market.getGuaranteedPrice().isEmpty())
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_GUARANTEED_PRICE_TAG, market.getGuaranteedPrice()));
        if (!market.getEarlyPrices().isEmpty())
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EARLY_PRICES_TAG, market.getEarlyPrices()));
        if (!market.getEachWay().isEmpty())
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EACHWAY_TAG, market.getEachWay()));
        if (!market.getEachWayPlaces().isEmpty())
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EACHWAYPLACES_TAG, market.getEachWayPlaces()));
        if (!market.getEachWayPlacesAt().isEmpty()) {
            String[] placesAt = market.getEachWayPlacesAt().trim().split("/");
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EACHWAYPLACESAT_TAG, placesAt[0], placesAt[1]));
        }


        if (!market.isForecastAvailable())
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_FORECAST_TAG, "yes"));
        else
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_FORECAST_TAG, "no"));
        if (!market.isTricastAvailable())
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TRICAST_TAG, "yes"));
        else
            request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TRICAST_TAG, "no"));

        //logger.info(String.format("selection with id:%s updated", selection.id));

        StringBuilder req = new StringBuilder(request);
        clearRequest();

        return req.toString();
    }

    private void buildMarketsBody(Event event, final String externalEventId){
        if (event.getMarkets().size() > 0) {
            int marketNumber = 1;
            for (Market market : event.getMarkets()) {
                String marketId = externalEventId + "-M" + marketNumber;
                request.append(TemplateTags.MARKET_TAGS.MARKET_OPEN_TAG);
                request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EXTERNAL_EVENT_ID_TAG, externalEventId));
                request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EXTERNAL_ID_TAG, marketId));
                request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TEMPLATE_TAG, market.getMarketGroup()));
                if (!market.getName().isEmpty())
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_NAME_TAG, market.getName()));
                if (!market.getStatus().isEmpty())
                    request.append(String.format(TemplateTags.COMMON_TAGS.STATUS_TAG, market.getStatus()));
                if (!market.getDisplayed().isEmpty())
                    request.append(String.format(TemplateTags.COMMON_TAGS.DISPLAY_TAG, market.getDisplayed()));
                if (!market.getBir().isEmpty())
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_BETTING_IN_RUNNING_TAG, market.getBir()));


                if (!market.getHandicap().isEmpty())
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_HANDICAP_TAG, market.getHandicap()));
                if (!market.getStartPrice().isEmpty()) {
                    String market_pricing = String.format(TemplateTags.MARKET_TAGS.MARKET_PRICING_TAG, market.getLivePrice(), market.getStartPrice());
                    request.append(market_pricing);
                }
                if (!market.getGuaranteedPrice().isEmpty())
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_GUARANTEED_PRICE_TAG, market.getGuaranteedPrice()));
                if (!market.getEarlyPrices().isEmpty())
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EARLY_PRICES_TAG, market.getEarlyPrices()));
                if (!market.getEachWay().isEmpty())
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EACHWAY_TAG, market.getEachWay()));
                if (!market.getEachWayPlaces().isEmpty())
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EACHWAYPLACES_TAG, market.getEachWayPlaces()));
                if (!market.getEachWayPlacesAt().isEmpty()) {
                    String[] placesAt = market.getEachWayPlacesAt().trim().split("/");
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_EACHWAYPLACESAT_TAG, placesAt[0], placesAt[1]));
                }


                if (!market.isForecastAvailable())
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_FORECAST_TAG, "yes"));
                else
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_FORECAST_TAG, "no"));
                if (!market.isTricastAvailable())
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TRICAST_TAG, "yes"));
                else
                    request.append(String.format(TemplateTags.MARKET_TAGS.MARKET_TRICAST_TAG, "no"));

                buildSelectionssBody(market, marketId);

                request.append(TemplateTags.MARKET_TAGS.MARKET_CLOSE_TAG);
                marketNumber++;
            }
        }
    }

    private void buildSelectionssBody(Market market, String marketId){
        if(market.getSelections().size()>0) {
            int selectionNumber = 1;
            for (Selection selection: market.getSelections()) {
                String selectionId = marketId + "-S" + selectionNumber;
                request.append(TemplateTags.SELECTION_TAGS.SELECTION_OPEN_TAG);
                request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_EXTERNAL_MARKET_ID_TAG,marketId));
                request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_EXTERNAL_ID_TAG,selectionId));
                request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_NAME_TAG,selection.getName()));
                request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_PRICE_TAG,selection.getPrice()));


                if(!selection.getStatus().isEmpty())
                    request.append(String.format(TemplateTags.COMMON_TAGS.SORT_TAG,selection.getStatus()));
                if (!selection.getDisplayed().isEmpty())
                    request.append(String.format(TemplateTags.COMMON_TAGS.DISPLAY_TAG,selection.getDisplayed()));
                if (!selection.getType().isEmpty())
                    request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_TYPE_TAG,selection.getType()));
                if (!selection.getRunnerOrder().isEmpty())
                    request.append(String.format(TemplateTags.SELECTION_TAGS.SELECTION_RUNNER_TAG,selection.getRunnerOrder()));

                request.append(TemplateTags.SELECTION_TAGS.SELECTION_CLOSE_TAG);
                selectionNumber++;

            }

        }
    }

    private void clearRequest() {
        request = new StringBuilder();
    }
}