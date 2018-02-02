package com.whgtf.sportsbook.pom.utils;


import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.User;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractPageObject;
import cucumber.api.Scenario;

import java.util.*;


public class ScenarioContext {

    private static ScenarioContext instance = null;

    private static ArrayList<Event> events;

    private Date timestamp;

    private Scenario scenario;

    private boolean checkRiak;

    private static AbstractPageObject currentPage;

    private static Event currentEvent;

    private static Map<String,Object> savedData  = new HashMap<>();

    private static User user;

    private ScenarioContext() {
        // Exists only to defeat instantiation.
        events = new ArrayList<>();
    }

    public static ScenarioContext getInstance() {
        if(instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public void setup(Scenario scenario) {
        events = new ArrayList<>();
        this.scenario = scenario;
        this.checkRiak = false;
    }

    public static Object getSavedData(String key) {
        return savedData.get(key);
    }

    public static void saveData(String key, Object data) {
        savedData.put(key, data);
    }

    public static void clearSavedData() {
        savedData.clear();
    }

    /**
     * Method to be called to reset scenario context values.
     */
//    public void e2eCleanup() {
//        // events created using oxifeed. need oxifeed settlement.
//        if (isRiakCheckNeeded()) {
//            try {
//                for (Event event : getEvents()) {
//                    // Update events info for settlement
//                    event.setDisplayed(BackOfficeConstants.EVENT_DISPLAYED_NO);
//                    BackOfficeOxifeed.getInstance().updateEvent(event);
//                    event.setStartTime(Timer.getDate(Timer.DayFilter.Yesterday));
//                    event.setOffFlag(BackOfficeConstants.EVENT_OFF_FLAG_YES);
//                    BackOfficeOxifeed.getInstance().updateEvent(event);
//
//                    // Void selection results and settle markets
//                    for (Market market : event.getMarkets()) {
//                        for (Selection selection : market.getSelections())
//                            BackOfficeOxifeed.getInstance().voidSelectionResult(selection);
//                        BackOfficeOxifeed.getInstance().settleMarket(market);
//                    }
//                    //logger.info(String.format("event with id:%s queued for settlement", event.id));
//                }
//            } catch (Throwable e) {
//                e.printStackTrace();
//                // the event has been settled by a step method
//            }
//        }
//        events.clear();
//        checkRiak = false;
//    }

    public void functionalCleanup() {
        events.clear();
    }


    public Scenario getScenario() {
        return scenario;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    /*public SquizFilters getSquizFilters() {
        return this.squizFilters;
    }*/

    public Event getFirstEvent() {
        if(!events.isEmpty())
            return events.get(0);
        else
            throw new IllegalArgumentException("No events set in the scenario context.");
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void setEvents(List<Event> events) {
        ScenarioContext.events.addAll(events);
    }

    public Event getEventByEventPdsId(final String id){
        for (Event event: events) {
            if(event.getPdsId().equals(id)){
                return event;
            }
        }
        throw new IllegalArgumentException("No event existing with id " + id);
    }

    public boolean isRiakCheckNeeded() {
        return checkRiak;
    }

    public void setRiakCheck(boolean checkRiak) {
        this.checkRiak = checkRiak;
    }

    public static void setCurrentPage(AbstractPageObject page) {
        currentPage = page;
    }

    public static AbstractPageObject getCurrentPage() {
        return currentPage;
    }

    public static Event getCurrentEvent() {
        return currentEvent;
    }

    public static void setCurrentEvent(Event event) {
        currentEvent = event;
    }

    public static void setCurrentUser(User newUser) {
        user = newUser;
    }

    public static User getCurrentUser() {
        return user;
    }
}
