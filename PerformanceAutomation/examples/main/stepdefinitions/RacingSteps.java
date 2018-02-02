package com.whgtf.sportsbook.main.stepdefinitions;


import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class RacingSteps extends MainSteps {


    @And("^user click on the '(.*)(st|nd|rd|th)' Collection displayed")
    public void clickOnGivenSelection(final int input, final String ending) throws Throwable {
        racecardPage.clickOnGivenSelection(input);
    }

    @And("^user click on the '(.*)(st|nd|rd|th)' Event displayed from the '(.*)(st|nd|rd|th)' Collection")
    public void clickOnGivenEvent(final int event, final String ending1, final int collection, final String ending2) throws Throwable {
        ScenarioContext.saveData("EVENT_NAME", racecardPage.getEventName(event, collection));
        racecardPage.clickOnGivenEvent(event, collection);
    }

    @When("^user clicks on any All Races$")
    public void clickOnAnyAllRaces() {
        meetingsPage.getMeetingComponent().clickOnTheFirstAllRaces();
    }

    @And("^user click on All Races from the '(.*)(st|nd|rd|th)' Event, '(.*)(st|nd|rd|th)' Collection Menu")
    public void clickAllRacesFromGivenEventMenu(final int event, final String ending1, final int collection, final String ending2) throws Throwable {
        ScenarioContext.saveData("EVENT_NAME", racecardPage.getEventName(event, collection));
        ScenarioContext.saveData("RACE_TITLES_FROM_MENU", racecardPage.getAllRacesMenuTitlesFromGivenCollectionEvent(collection, event));
        racecardPage.clickAllRacesFromGivenEventMenu(collection, event);
    }

    @And("^all racecards are displayed$")
    public void allRacecardsAreDisplayed() throws Throwable {
        List<String> titlesDisplayedRacecard = racecardPage.getAllRacesTitlesDisplayed();
        List<String> titlesDisplayedMenu = (List<String>) ScenarioContext.getSavedData("RACE_TITLES_FROM_MENU");
        int iterator = 0;
        for (String menuTitle:titlesDisplayedMenu) {
            assertThat(titlesDisplayedRacecard.get(iterator)).contains(menuTitle);
            iterator++;
        }
    }

    @And("^user click on the first Resulted Race from the '(.*)(st|nd|rd|th)' Event, '(.*)(st|nd|rd|th)' Collection Menu")
    public void clickOnResultedRaceFromGivenEventMenu(final int event, final String ending1, final int collection, final String ending2) throws Throwable {
        ScenarioContext.saveData("EVENT_NAME", racecardPage.getEventName(event, collection));
        racecardPage.clickOnResultedRaceFromGivenEventMenu(collection, event);
    }

    @And("^user click on the first Non-Resulted Race from the '(.*)(st|nd|rd|th)' Event, '(.*)(st|nd|rd|th)' Collection Menu")
    public void clickOnRaceFromGivenEventMenu(final int event, final String ending1, final int collection, final String ending2) throws Throwable {
        ScenarioContext.saveData("EVENT_NAME", racecardPage.getEventName(event, collection));
        racecardPage.clickOnRaceFromGivenEventMenu(collection, event);
    }

    @And("^user click on the '(.*)(st|nd|rd|th)' Race from the '(.*)(st|nd|rd|th)' Collection Menu '(.*)(st|nd|rd|th)' Event")
    public void clickOnGivenRace(int race, String ending, int collection, String ending2, int event, String ending1) throws Throwable {
        racecardPage.clickOnGivenRace(race, collection, event);
    }

    @And("^the page Title is the same as the Event previously selected$")
    public void verifyPageTitleIsSameAsEventSelected() throws Throwable {
        assertThat(ScenarioContext.getSavedData("EVENT_NAME")).isEqualTo(racecardPage.getCurrentRaceEventTitle());
    }

    @And("^user click on All Races link from Race to Race navigation$")
    public void clickAllRacesLink() throws Throwable {
        racecardPage.clickAllRacesLink();
    }

    @And("^on the Race to Race navigation the 'All Races' option is displayed$")
    public void isAllRacesLinkDisplayed() throws Throwable {
        assertThat(racecardPage.isAllRacesLinkDisplayed()).isTrue();
    }

    @And("^the alternate meeting dropdown '(is|is not)' displayed$")
    public void isRaceDropdownListDisplayed(final String isOrNot) throws Throwable {
        if("is".equals(isOrNot)){
            assertThat(racecardPage.isAlternateMeetingDropdownDisplayed()).isTrue();
        }else{
            assertThat(racecardPage.isAlternateMeetingDropdownDisplayed()).isFalse();
        }
    }

    @And("^user '(expand|collapse)' the alternate meeting dropdown")
    public void expandOrCollapseAlternateMeetings(final String expandCollapse) throws Throwable {
        if("expand".equals(expandCollapse)){
            racecardPage.expandAlternateMeetingDropdown();
        }else{
            racecardPage.collapseAlternateMeetingDropdown();
        }
    }

    @And("^the alternate meeting dropdown '(is|is not)' expanded")
    public void isRaceDropdownListExpanded(final String isOrNot) throws Throwable {
        if("is".equals(isOrNot)){
            assertThat(racecardPage.isAlternateMeetingDropdownExpanded()).isTrue();
        }else{
            assertThat(racecardPage.isAlternateMeetingDropdownExpanded()).isFalse();
        }
    }

    @And("^user stores all Events from competition '(.*)' displayed on the Meetings Page$")
    public void storeAllEventsFromGivenCompetition(final String input) throws Throwable {
        ScenarioContext.saveData(input, meetingsPage.getAllEventsFromGivenCompetition(input));
    }

    @And("^user select from the Alternate Meeting dropdown the '(.*)(st|nd|rd|th)' option$")
    public void selectRaceFromAlternateMeeting(final int input, final String ending) throws Throwable {
        ScenarioContext.saveData("RACE_EXPECTED", racecardPage.getRaceNameFromAlternateMeetingIndex(input));
        racecardPage.selectRaceFromAlternateMeetingDropdown(input);
    }

    @And("^user selects '(.*)' from the alternate meeting dropdown$")
    public void selectGivenOptionFromAlternateMeeting(final String input) throws Throwable {
        ScenarioContext.saveData("RACE_EXPECTED", input);
        racecardPage.selectRaceFromAlternateMeetingDropdown(input);
    }

    @And("^the Alternative Meeting is displayed$")
    public void alternativeMeetingIsDisplayed() throws Throwable {
        assertThat(racecardPage.getAllRacesTitlesDisplayed().get(0)).contains((String) ScenarioContext.getSavedData("RACE_EXPECTED"));

    }

    @And("^user select All Races from the Alternate Meeting dropdown$")
    public void selectAllRacesFromAlternateMeeting() throws Throwable {
        racecardPage.selectRaceFromAlternateMeetingDropdown(1);
    }

    @And("^the option selected on the Race to Race dropdown is the correct one$")
    public void correctRaceIsSelectedOnDropdown() throws Throwable {
        assertThat(racecardPage.currentAlternateMeetingSelected()).matches((String) ScenarioContext.getSavedData("RACE_SELECTED"));
    }

    @And("^the alternate meeting is not empty$")
    public void theDropDownIsNotEmpty() throws Throwable {
        assertThat(racecardPage.getOptionsFromAlternateMeetingDropdown().size()).isGreaterThan(0);
    }

    @And("^the alternate meeting first option is 'Next Off'$")
    public void alternateMeetingFirstOptionIsNextOffRace() throws Throwable {
        assertThat(racecardPage.getOptionsFromAlternateMeetingDropdown().get(0)).isEqualTo("Next Off");
    }

    @And("^user selects 'Next off Race' from the Alternate Meeting$")
    public void selectNextOffRaceFromAlternateMeeting() throws Throwable {

        List<String> dropdownOptions = racecardPage.getOptionsFromAlternateMeetingDropdown();
        int iterator = 0;

        for (String currentOption:dropdownOptions) {
            if(currentOption.equals(racecardPage.currentAlternateMeetingSelected())){
                break;
            }
            iterator++;
        }

        ScenarioContext.saveData("RACE_EXPECTED",racecardPage.getRaceNameFromAlternateMeetingIndex(iterator));
        racecardPage.selectRaceFromAlternateMeetingDropdown(1);
    }

    @And("^the races on the Race to Race dropdown are ordered Alphabetically$")
    public void dropDownOrderAlphabetically() throws Throwable {
        List<String> dropDownList = racecardPage.getOptionsFromAlternateMeetingDropdown();
        //First one is the "Next Off Race" option, which should not be verified if its alphabetically in order.
        dropDownList.remove(0);
        assertThat(dropDownList).isSorted();
    }

    @And("^the Racecard '(is|is not)' displayed$")
    public void isRacecardDisplayed(final String input) throws Throwable {
        if(input.equals("is")){
            assertThat(racecardPage.isRacecardDisplayed()).isTrue();
        }else{
            assertThat(racecardPage.isRacecardDisplayed()).isFalse();
        }
    }

    @And("^user '(expand|collapse)' the Next Off Section$")
    public void expandCollapseNextOff(final String expandCollapse) throws Throwable {
        if("expand".equals(expandCollapse)){
            nextOffComponent.expandNextOff();
        }else{
            nextOffComponent.collapseNextOff();
        }
    }

    @And("^user clicks on the '(.*)(st|nd|rd|th)' selection of '(.*)(st|nd|rd|th)' race from Next Off section")
    public void clickOnGivenSelectionFromGivenRace(int selection, String ending, int race, String ending2) throws Throwable {
        nextOffComponent.expandNextOff();
        nextOffComponent.clickOnGivenSelectionFromGivenRace(selection, race);
    }

    @And("^the Next Off section is '(expanded|collapsed)'$")
    public void isNextOffSectionExpanded(final String expandCollapse) throws Throwable {
        if("expanded".equals(expandCollapse)){
            assertThat(nextOffComponent.isNextOffExpanded()).isTrue();
        }else{
            assertThat(nextOffComponent.isNextOffExpanded()).isFalse();
        }
    }

    @And("^the number of Races displayed on the Next Off section is '(.*)'$")
    public void isNextOffSectionExpanded(final int numberRacesDisplayed) throws Throwable {
        assertThat(nextOffComponent.numberOfNextOffRacesDisplayed()).isEqualTo(numberRacesDisplayed);
    }

    @And("^the Next Off races '(are|are not)' displayed$")
    public void areNextOffRacesDisplayed(final String isOrNot) throws Throwable {
        if("are".equals(isOrNot)){
            assertThat(nextOffComponent.areNextOffRacesDisplayed()).isTrue();
        }else{
            assertThat(nextOffComponent.areNextOffRacesDisplayed()).isFalse();
        }
    }

    // ------------------------- New! -------------------------

    @And("^user expands all Regions and Meetings$")
    public void expandAllRegionsAndMeetings() throws Throwable {
        meetingsNavigationComponent.expandAllRegionsAndMeetings();
    }

    @And("^user goes to race '(.*)' from competition '(.*)'")
    public void clickOnGivenRaceFromGivenCompetition(final String eventId, final String competitionId) throws Throwable {
        meetingsNavigationComponent.clickOnGivenRaceFromGivenCompetition(eventId, competitionId);
    }

    @And("^the '(.*)' racecard '(is|is not)' displayed")
    public void isGivenRacecardDisplayed(final String eventId, final String isOrNot) throws Throwable {
        if("is".equals(isOrNot)){
            assertThat(racecardPage.isGivenRacecardDisplayed(eventId)).isTrue();
        }else{
            assertThat(racecardPage.isGivenRacecardDisplayed(eventId)).isFalse();
        }
    }

    @And("^all events for competition '(.*)' are displayed")
    public void areAllEventsForCompetitionIdDisplayed(final String competitionID) throws Throwable {
        List<String> eventIDs = (List<String>) ScenarioContext.getSavedData(competitionID);
        for (String eventID:eventIDs) {
            assertThat(racecardPage.isGivenRacecardDisplayed(eventID)).isTrue();
        }
    }

    @And("user goes to All Events from competition '(.*)'")
    public void clickOnGivenAllRacesFromCompetitionId(String competitionId) throws Throwable {
            ScenarioContext.saveData("all_eventIds_from_given_competition", meetingsNavigationComponent.getAllRacesIDsFromGivenCompetition(competitionId));
            meetingsNavigationComponent.clickOnGivenAllRaces(competitionId);
    }

    @And("all events for the competition are displayed")
    public void allEventsAreDisplayed() throws Throwable {
        List<String> racesDisplayed = (List <String>) ScenarioContext.getSavedData("all_eventIds_from_given_competition");
        for (String eventId : racesDisplayed) {
            assertThat(racecardPage.isGivenRacecardDisplayed(eventId)).isTrue();
        }
    }

    @And("user goes to Meeting Markets from competition '(.*)'")
    public void clickMeetingMarketFromGivenCompetition(String competitionId) throws Throwable {
        racecardPage.clickMeetingMarketFromGivenCompetition(competitionId);
    }

    @And("Meeting Markets page 'is' displayed")
    public void isMeetingMarketsPageDisplay() throws Throwable {
        assertThat(meetingsPage.isMeetingMarketsPageDisplay()).isTrue();
    }

    @And("selected option in alternate meeting menu is '(.*)'")
    public void isGivenOptionSelectedInAlternateMeetings(final String option) throws Throwable {
        assertThat(racecardPage.currentAlternateMeetingSelected()).isEqualTo(option);
    }

    @When("^user clicks on the '(.*)(st|nd|rd|th)' available in Racecard selection$")
    public void clickOnGivenSelectionNumberInRaceCard(final int selectionNumber, final String classyEnding) {
        racecardPage.clickOnSelectionInRaceCard(selectionNumber);
    }

}
