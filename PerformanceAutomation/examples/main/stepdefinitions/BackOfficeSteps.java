package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.util.MarketData;
import com.whgtf.sportsbook.model.*;
import com.whgtf.sportsbook.obfeed.BackOfficeOxifeed;
import com.whgtf.sportsbook.pom.utils.BackOfficeUtils;
import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import com.whgtf.sportsbook.utils.BackOfficeConstants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


@Component
@Lazy
public class BackOfficeSteps extends MainSteps{


    @Given("^a '(.*)' race at (\\d+):(\\d+)$")
    public void a_race_at(String meetingName, int hour, int minutes) throws Throwable {
        // set the race start time

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);

        // format the start time
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fullDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
        String raceStartTime = fullDateFormat.format(calendar.getTime());
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm");
        timeFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        String raceDisplayedStartTime = timeFormat.format(calendar.getTime());

        // add the event to OB
        Event event = new Event(raceDisplayedStartTime + " " + meetingName,
                CompetitionIds.getCompetitionId(meetingName));
        event.setStartTime(raceStartTime);
        event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_NO);
        // add market
        Market market = new Market();
        market.setMarketGroup("|Win|");
        List<String> horseSelections = Arrays.asList(BackOfficeConstants.horses);
        for (int i = 0; i < 4; i++) {
            Selection selection = new Selection();
            selection.setName(horseSelections.get(i));
            selection.setRunnerOrder(String.valueOf(i + 1));
            selection.setPrice((i + 2) + "/1");
            market.addSelection(selection);
        }
        event.addMarket(market);
        event = BackOfficeOxifeed.getInstance().addEvent(event);


        // add the event to the context.
        ScenarioContext.setCurrentEvent(event);
        ScenarioContext.getInstance().addEvent(event);

        backOfficeOxifeed.checkEventsReadyForTesting(ScenarioContext.getInstance().getEvents());

    }

    @And("^streaming is available for the event in '(Horse-Racing|horse-racing|greyhounds|Greyhounds)'$")
    public void streamingIsAvailableForTheEvent(final String sport) throws Throwable {
        Event event = ScenarioContext.getCurrentEvent();
        BackOfficeUtils.getInstance().addStreamToRace(event.getPdsId(),event.getCompetition(),sport);
    }

    @Given("^a '(.*)' '(pre-match|in-play)' event with the following markets:$")
    public void createEvent(String sport, String eventType, List<MarketData> markets) {
        createEventWithEventName(sport, eventType, null, markets);
    }

    private void createSelections(Event event , Market mar, String selectionsType) {

        List<String> selectionList = Arrays.asList(selectionsType.split("/"));
        if("Home/Draw/Away".equals(selectionsType) || "Home/Away/Line".equals(selectionsType) ||
                "HomeDraw/DrawAway/HomeAway".equals(selectionsType) || "Over/Middle/Under".equals(selectionsType)){

            for(int sel=0 ; sel<selectionList.size() ; sel++) {
                Selection selection = new Selection();
                selection.setPrice((sel + 1) + "/" + (sel + 2));
                switch (selectionList.get(sel)) {
                    case "Home":
                        selection.setName("home_selection_"+ sel);
                        selection.setType(BackOfficeConstants.SelectionTypes.home.name());
                        break;

                    case "Draw":
                        selection.setName("Draw");
                        selection.setType(BackOfficeConstants.SelectionTypes.draw.name());
                        break;

                    case "Away":
                        selection.setName("away_selection_" + sel);
                        selection.setType(BackOfficeConstants.SelectionTypes.away.name());
                        break;

                    case "Line":
                        selection.setName("Line");
                        selection.setType(BackOfficeConstants.SelectionTypes.line.name());
                        break;

                    case "HomeDraw":
                        selection.setName("home_draw_selection_" + sel);
                        selection.setType(SelectionTypeEnum.homedraw.name());
                        break;

                    case "DrawAway":
                        selection.setName("draw_away_selection_" + sel);
                        selection.setType(SelectionTypeEnum.drawaway.name());
                        break;

                    case "HomeAway":
                        selection.setName("home_away_selection_" + sel);
                        selection.setType(SelectionTypeEnum.homeaway.name());
                        break;

                    default:
                        selection.setName("selection_" + RandomStringUtils.randomAlphabetic(4));
                        if("Middle".equals(selectionList.get(sel)))
                            selection.setType(SelectionTypeEnum.homedraw.name());
                        else if("Over".equals(selectionList.get(sel)))
                            selection.setType(SelectionTypeEnum.over.name());
                        else if("Under".equals(selectionList.get(sel)))
                            selection.setType(SelectionTypeEnum.under.name());
                        break;
                }

                mar.addSelection(selection);
            }
        } else if("High/Low".equals(selectionsType) || "Home/Away".equals(selectionsType) || "Odd/Even".equals(selectionsType)) {

                for(int sel=0 ; sel<selectionList.size() ; sel++) {
                    Selection selection = new Selection();
                    selection.setPrice((sel + 1) + "/" + (sel + 2));
                    selection.setName(selectionList.get(sel));
                    mar.addSelection(selection);
                }


        } else if ("HomeScore/DrawScore/AwayScore".equals(selectionsType)){
                for(int j=1; j<4 ; j++) {
                    Selection selection = new Selection();
                    selection.setName(String.format("%s-%s", j, j - 1));
                    selection.setType(BackOfficeConstants.SelectionTypes.score.name());
                    mar.addSelection(selection);
                    selection.setName(String.format("%s-%s", j, j));
                    selection.setType(BackOfficeConstants.SelectionTypes.score.name());
                    mar.addSelection(selection);
                    selection.setName(String.format("%s-%s", j-1, j));
                    selection.setType(BackOfficeConstants.SelectionTypes.score.name());
                    mar.addSelection(selection);
                }
        } else {
            for (int i = 0; i < 4; i++) {
                Selection selection = new Selection();
                selection.setPrice((i + 2) + "/" + (i + 3));
                selection.setName("Selection"+i);
                mar.addSelection(selection);
            }
        }
        event.addMarket(mar);
    }

    @Given("^a '(.*)' '(pre-match|in-play)' event '(.*)' with the following markets:$")
    public void createEventWithEventName(String sport, String eventType, String eventName, List<MarketData> markets) {
        Event event = new Event();
        event.setCompetitionId(CompetitionIds.getFirstCompetitionId(sport));
        if (StringUtils.isEmpty(eventName)) {
            event.setName("|Auto_Competitor1" + RandomStringUtils.randomAlphabetic(2) + "| |vs| |" + "Auto_Competitor2" + RandomStringUtils.randomAlphabetic(2) + "|");
        } else {
            event.setName(eventName+System.currentTimeMillis());
        }
        // TODO: 06/12/2016 pass an enum to setStartTime
        event.setStartTime("ONEHOURFROMNOW");
        event.setStatus(BackOfficeConstants.EVENT_STATUS_ACTIVE);
        event.setDisplayed(BackOfficeConstants.EVENT_DISPLAYED_YES);
        if ("pre-match".equals(eventType))
            event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_NO);
        else if ("in-play".equals(eventType))
            event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);

        for (MarketData market : markets) {
            Market mar = new Market();
            if (!market.getHandicap().isEmpty()) {
                mar.setHandicap(market.getHandicap());
            }
            mar.setMarketGroup(market.getMarketGroup().replace('\\', '|'));
            mar.setDisplayed(market.getDisplay().toLowerCase());
            mar.setBir(market.getBir().toLowerCase());
            createSelections(event, mar, market.getSelections());
        }

        try {
            event = backOfficeOxifeed.addEvent(event);
        } catch (Exception e){
            e.printStackTrace();
        }


        ScenarioContext.setCurrentEvent(event);
        ScenarioContext.getInstance().addEvent(event);
    }
}
