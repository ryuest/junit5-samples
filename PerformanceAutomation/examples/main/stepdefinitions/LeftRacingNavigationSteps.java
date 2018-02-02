package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.util.Msg;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class LeftRacingNavigationSteps extends MainSteps {


    @Then("^the left racing navigation is displayed$")
    public void racingNavigationIsDisplay() throws Throwable {
        assertThat(meetingsNavigationComponent.isDisplayed()).isTrue();
    }


    @When("^user clicks on the first race in the future racing nav$")
    public void clickOnTheFirstRaceInTheLeftNav() throws Throwable {
        meetingsPage.getMeetingComponent().clickOnTheFirstEvent();
    }

    @When("^user clicks on an active event of this meeting$")
    public void clickOnTheRaceInTheLeftNav() throws Throwable {
        meetingsPage.getMeetingComponent().clickOnRandomRace();
    }

    @When("^user clicks on the (first|second) race in the meeting left nav$")
    public void clickOnFirst(final String race) throws Throwable {
        if("first".equalsIgnoreCase(race))
            meetingsPage.getMeetingComponent().clickOnFirstRaceMeetingSelection();
        else
            meetingsPage.getMeetingComponent().clickOnRaceMeetingSelectionByPosition(2);
    }

    @When("^user clicks on the (first|second) future race in the meeting nav$")
    public void clickOnFirstFutureRace(final String race) throws Throwable {
        if("first".equalsIgnoreCase(race)) {
            meetingsPage.getMeetingComponent().clickOnTheFirstSelectionAvailable();
        } else {
            meetingsPage.getMeetingComponent().clickOnFutureRaceInAnyExpandedMeeting(1);
        }
    }


    @When("^user click on the first Race from Racing Meeting that has multiple non resulted races")
    public void clickOnFirstActiveRace() throws Throwable {
        meetingsNavigationComponent.clickOnActiveRaceThatHasMoreThanOneRaceOnEvent();
    }


    @When("^the (first|second) race in the meeting left nav is displayed in yellow$")
    public void checkHighligthed(final String race) throws Throwable {
        if("first".equalsIgnoreCase(race))
            assertThat(meetingsPage.getMeetingComponent().isRaceMeetingSelectionHighlightedByPosition(1)).isTrue();
        else
            assertThat(meetingsPage.getMeetingComponent().isRaceMeetingSelectionHighlightedByPosition(2)).isTrue();
    }

    @When("^user opens '(.*)' group in the meeting left nav$")
    public void userClicksOnGroupInTheMeetingLeftNav(final String group) throws Throwable {
        meetingsNavigationComponent.openGroupByName(group);
    }

    @Then("^My WHTV Races section has '(\\d+)' qualified races highlighted in yellow$")
    public void myWHTVRacesSectionHasQualifiedRacesHighlightedInYellow(int races) throws Throwable {
        assertThat(meetingsNavigationComponent.checkQualifiesRacesHighlightedInYellow(races)).isTrue();
    }

    @Then("^My WHTV Races message in the meeting left nav is '(.*)'$")
    public void checkMyWHTVMessageInTheMeetingLeftNav(final String message) throws Throwable {
        assertThat(meetingsNavigationComponent.getMessageInMyWHTVRacesGroup()).isEqualTo(message);
    }


    @When("^user clicks on an active event in a meeting with multiple active races and (with|without) meeting markets$")
    public void checkAndClickActiveRacesWithMeetingMarkets(String displayed) {
        if (Msg.WITH.equalsIgnoreCase(displayed)) {
            assertThat(meetingsNavigationComponent.numberOfRacesDisplayedByMeetingId(meetingsNavigationComponent.findMeetingsWithMeetingMarkets().get(0))).isGreaterThan(1);
            meetingsNavigationComponent.clickOnFutureRaceByMeetingId(meetingsNavigationComponent.findMeetingsWithMeetingMarkets().get(0), 1);
        } else {
            assertThat(meetingsNavigationComponent.numberOfRacesDisplayedByMeetingId(meetingsNavigationComponent.findMeetingsWithoutMeetingMarkets().get(0))).isGreaterThan(1);
            meetingsNavigationComponent.clickOnFutureRaceByMeetingId(meetingsNavigationComponent.findMeetingsWithoutMeetingMarkets().get(0), 1);
        }
    }

    @When("^there an active event in a meeting with multiple active races and (with|without) meeting markets$")
    public void checkActiveRacesWithMeetingMarkets(String displayed) {
        if (Msg.WITH.equalsIgnoreCase(displayed)) {
            assertThat(meetingsNavigationComponent.numberOfRacesDisplayedByMeetingId(meetingsNavigationComponent.findMeetingsWithMeetingMarkets().get(0))).isGreaterThan(1);
        } else {
            assertThat(meetingsNavigationComponent.numberOfRacesDisplayedByMeetingId(meetingsNavigationComponent.findMeetingsWithoutMeetingMarkets().get(0))).isGreaterThan(1);
        }
    }

    @When ("^user clicks on a meeting with one active race and (with|without) meeting markets$")
    public void clickOnMeetingWithMeetingMarkets(String displayed) {
        if (Msg.WITH.equalsIgnoreCase(displayed)) {
            meetingsNavigationComponent.clickOnFutureRaceByMeetingId(meetingsNavigationComponent.findOneMeetingWithOrWithoutMeetingMarkets(true).get(0), 0);
        } else {
            meetingsNavigationComponent.clickOnFutureRaceByMeetingId(meetingsNavigationComponent.findOneMeetingWithOrWithoutMeetingMarkets(false).get(0), 0);
        }
    }

    @Then("^correct race is highlighted in Race To Race menu$")
    public void raceIsHighlightedAndInRacecard() {
        assertThat(meetingsNavigationComponent.isRaceHighlightedAndInRaceCard()).isTrue();
    }

    @When("^user clicks on meeting markets of this meeting$")
    public void clickOnMeetingMarkets() {
        meetingsNavigationComponent.clickOnMeetingMarketsSelection();
    }

    @When("^user clicks on All Races of this meeting$")
    public void clickOnAllRaces() {
        meetingsNavigationComponent.clickOnAllRacesSelection();
    }

}
