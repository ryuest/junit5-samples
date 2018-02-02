package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.util.Msg;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.NotImplementedException;
import org.assertj.core.api.SoftAssertions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.whgtf.sportsbook.main.util.TranslationUtils.LANGUAGE_URL_MAP;
import static com.whgtf.sportsbook.pom.utils.Assertions.assertArrayContains;
import static org.assertj.core.api.Assertions.assertThat;


@Component
@Lazy
public class HorsesSteps extends MainSteps {

    @When("^user clicks on '(today|tomorrow)' tab in horses page$")
    public void clickOnTabHorsesPage(final String tab) {
        if(Msg.TODAY.equalsIgnoreCase(tab))
            meetingsPage.getMeetingComponent().clickOnTodayMeetings();
        else
            meetingsPage.getMeetingComponent().clickOnTomorrowMeetings();
    }

    @When("^user clicks on the first selection in '(.*)' meeting group and '(.*)' race in horses page$")
    public void clickOnSpecificHorsesRace(final String group, final String race) {
        meetingsPage.getMeetingComponent().clickOnTheFirstRaceByGroupNameAndMeetingName(group, race);
    }

    @When("^user clicks on the first selection in the first race$")
    public void clickOnFirstRace() {
        meetingsPage.clickOnTheFirstSelectionInTheFirstRace();
    }

    @When("^user clicks on View Full Card for the '(First|Second|Third)' race$")
    public void clickOnViewFullCard(final String race) {
        meetingsPage.clickOnFirstViewFullCard();
    }

    @Then("^'(Meetings|Racecard|Ante Post|All Races|Specials)' page is displayed in '(.*)'$")
    public void racingPageIsDisplayedInLanguage(final String page, final String language) throws Throwable {
        if(Msg.MEETINGS.equalsIgnoreCase(page)){
            meetingsPage.waitToBeLoaded();
            assertThat(meetingsPage.isDisplayedInLanguage(language)).isTrue();
        } else if (Msg.ANTE_POST.equalsIgnoreCase(page)) {
            antePostPage.waitToBeLoaded();
            assertThat(antePostPage.isDisplayedInLanguage(language)).isTrue();
        } else if (Msg.ALL_RACES.equalsIgnoreCase(page)) {
            antePostAllRacesPage.waitToBeLoaded();
            assertThat(antePostAllRacesPage.isDisplayedInLanguage(language)).isTrue();
        } else if (Msg.SPECIALS.equalsIgnoreCase(page)) {
            specialRacingPage.waitToBeLoaded();
            assertThat(specialRacingPage.isDisplayedInLanguage(language)).isTrue();
        } else {
            racecardPage.waitToBeLoaded();
            assertThat(racecardPage.isDisplayedInLanguage(language)).isTrue();
        }
    }

    @And("^the '(.*)' flag icon is displayed in the '(Meetings|Racecard)'$")
    public void theLanguageFlagIconIsDisplayedInTheRacingPages(final String language,final String page) throws Throwable {
        if(Msg.MEETINGS.equalsIgnoreCase(page)){
            meetingsPage.waitToBeLoaded();
            assertThat(meetingsPage.getFooterComponent().isLanguageFlagDisplayed(language)).isTrue();
        } else {
            racecardPage.waitToBeLoaded();
            assertThat(racecardPage.getFooterComponent().isLanguageFlagDisplayed(language)).isTrue();
        }
    }

    @Then("^all the links are in '(.*)' in the '(Meetings|Racecard|Specials)' page$")
    public void allTheLinksAreInLanguageInTheRacingPages(final String language, final String page) throws Throwable {
        if(Msg.MEETINGS.equalsIgnoreCase(page)){
            meetingsPage.waitToBeLoaded();
            assertArrayContains(LANGUAGE_URL_MAP.get(language), meetingsPage.getAllLinksInThePage());
        } else if (Msg.SPECIALS.equalsIgnoreCase(page)){
            specialRacingPage.waitToBeLoaded();
            assertArrayContains(LANGUAGE_URL_MAP.get(language),specialRacingPage.getAllLinksInThePage());
        } else {
            racecardPage.waitToBeLoaded();
            assertArrayContains(LANGUAGE_URL_MAP.get(language), racecardPage.getAllLinksInThePage());
        }
    }

    @When("^the user sorts '(ascending|descending)' by '(.*)' the '(.*)(st|nd|th)' race")
    public void sortByColumn(final String sorted, final String column, final int race, final String classyEnd) {
        boolean ascending = false;
        if("ascending".equalsIgnoreCase(sorted))
            ascending = true;
        switch (column) {
            case "number":
                racecardPage.clickOnSortByNumberByRace(race, ascending);
                break;
            case "draw":
                racecardPage.clickOnSortByDrawByRace(race, ascending);
                break;
            case "selection":
                racecardPage.clickOnSortBySelectionByRace(race, ascending);
                break;
            case "rp rating":
                racecardPage.clickOnSortByRPByRace(race, ascending);
                break;
            case "price":
                racecardPage.clickOnSortByPriceByRace(race, ascending);
                break;
        }
    }

    @Then("^the racecard will be sorted by '(ascending|descending)' '(.*)' on the '(.*)(st|nd|th)' race$")
    public void isGivenRaceSortedBy(final String ascendingOrDescending, final String sort, final int race, final String classyEnd) throws Throwable {

        boolean ascOrDesc = false;
        if(ascendingOrDescending.equals("ascending")){
            ascOrDesc = true;
        }

        switch (sort) {
            case "number":
                assertThat(racecardPage.isRaceOrderedByHorseNumber(race, ascOrDesc)).isTrue();
                break;
            case "draw":
                assertThat(racecardPage.isRaceOrderedByDrawn(race, ascOrDesc)).isTrue();
                break;
            case "selection":
                assertThat(racecardPage.isRaceOrderedBySelection(race, ascOrDesc)).isTrue();
                break;
            case "rp rating":
                assertThat(racecardPage.isRaceOrderedByRP(race, ascOrDesc)).isTrue();
                break;
            case "price":
                assertThat(racecardPage.isRaceOrderedByPrice(race, ascOrDesc)).isTrue();
                break;
            case "trap":
                assertThat(racecardPage.isRaceOrderedByDogNumber(race, ascOrDesc)).isTrue();
                break;
        }

    }

    @And("^the user adds '(.*)' racecards from competition '(.*)' to My Racecard$")
    public void addRacecard(int number, String competition) {
        meetingsNavigationComponent.addSeveralRacecardsForCompetition(competition,number);
    }

    @And("^there are '(.*)' racecards added to My Racecard$")
    public void checkRacecardsAdded(final int number) {
        assertThat(racecardPage.numberOfRacesDisplayedOnRacecard()).isEqualTo(number);
    }

    @And("^the user clicks on '(All Races|Meeting Markets)' button from competition '(.*)' in left racing menu$")
    public void clickOnLeftRacingMenu(final String button, final String competitionId) {
        if("All Races".equalsIgnoreCase(button))
            meetingsNavigationComponent.clickOnAllRacesButtonByCompetition(competitionId);
        else
            meetingsNavigationComponent.clickOnMeetingMarketsButtonByCompetition(competitionId);
    }

    @Then("^race event message is '(.*)'$")
    public void checkRaceEventMessage(final String message) throws Throwable {
        assertThat(racecardPage.getRaceEventMessage()).isEqualTo(message);
    }

    @And("^a yellow TV icon (is|is not) displayed$")
    public void checkYellowTVIconIsDisplayed(final String isDisplayed) throws Throwable {
        if("is".equalsIgnoreCase(isDisplayed))
            assertThat(racecardPage.isTvIconEnabled()).isTrue();
        else
            assertThat(racecardPage.isTvIconEnabled()).isFalse();
    }

    @And("^the '(.*)' header (is|is not) displayed in the racecard$")
    public void checkHeader(final String header, final String isDisplayed) throws Throwable {
        if("is".equals(isDisplayed)) {
            racecardPage.isDisplayed();
            assertThat(racecardPage.isHeaderDisplayed(header)).isTrue();
        }
        else {
            assertThat(racecardPage.isHeaderDisplayed(header)).isFalse();
        }
    }

    @And("^user clicks in '(.*)' market collection link in the racecard")
    public void clickOnCollection(final String marketCollection) throws Throwable {
        racecardPage.clickOnMarketCollectionLinkByText(marketCollection);
    }

    @Then ("^'(.*)' page is displayed$")
    public void isRaceCardPageDisplayed(String page) {
        switch (page.toLowerCase()) {
            case Msg.RACECARD:
                assertThat(racecardPage.isRacecardDisplayed()).isTrue();
                break;
            case Msg.ALL_RACES:
                assertThat(racecardPage.isRacecardDisplayed()).isTrue();
                break;
            case Msg.MEETING_MARKETS:
                assertThat(racecardPage.isMeetingMarketsDisplayed()).isTrue();
                break;
            default:
                assertThat(meetingsPage.isMeetingsDisplayed()).isTrue();
        }
    }

    @Then ("^Race To Race menu (is|is not) displayed$")
    public void isRaceToRaceDisplayed(String displayed) {
        if (Msg.IS.equalsIgnoreCase(displayed)) {
            assertThat(racecardPage.isRaceToRaceNavigationDisplayed()).isTrue();
        } else {
            assertThat(racecardPage.isRaceToRaceNavigationDisplayed()).isFalse();
        }
    }

    @Then ("^option '(.*)' is highlighted in race to race menu$")
    public void isGivenOptionHighlightedInRaceToRace(String displayed) {
        assertThat(racecardPage.getHighlightedOptionFromRaceToRace()).isEqualTo(displayed);
    }

    @Then ("^user click on each event in first expanded group and Race To Race menu is not displayed$")
    public void isRaceToRaceNotDisplayedForAllEvents() {
            for (int number=0; number<meetingsNavigationComponent.getNumberDisplayedRacesInExpandedGroup(); number++) {
                meetingsNavigationComponent.clickOnTheNumberEvent(number);
                assertThat(racecardPage.isRaceToRaceNavigationDisplayed()).isFalse();
            }
    }

    @When ("^user clicks on '(.*)' link in the Race To Race menu$")
    public void clickRaceToRaceLink (String link) {
            racecardPage.clickRaceToRaceLink(link);
    }

    @Then ("^correct components are available in '(Racing Homepage|Racecard|Ante Post|Future Races|Specials|Top Bets)' racing page$")
    public void checkCorrectComponentsAreDisplayedInPage(String page){
        SoftAssertions softAssertion = new SoftAssertions();
        switch (page.toLowerCase()) {

            case Msg.RACING_PAGES:

                softAssertion.assertThat(meetingsNavigationComponent.todayAndTomorrowTabsDisplayed()).as("Today and/or Tomorrow Tabs is/are not being displayed").isTrue();
                softAssertion.assertThat(meetingsNavigationComponent.isTodayTabSelected()).as("Today Tab is not selected").isTrue();

                if (genericSportsPage.isMobile()) {
                    softAssertion.assertThat(carouselComponent.isDisplayed()).as("The Carousel is not being displayed").isTrue();
                    softAssertion.assertThat(nextOffComponent.isNextOffDisplayed()).as("The Next Off Section is not being displayed").isTrue();
                    softAssertion.assertThat(nextOffComponent.isNextOffExpanded()).as("The Next Off Section is expanded").isFalse();
                    nextOffComponent.expandNextOff();
                    softAssertion.assertThat(nextOffComponent.numberOfNextOffRacesDisplayed()).as("The Next Off section is empty").isGreaterThan(0);
                    softAssertion.assertThat(nextOffComponent.numberOfNextOffRacesDisplayed()).as("The Next Off section is displaying more than 10 Races").isLessThan(11);
                    nextOffComponent.collapseNextOff();

                } else {
                    softAssertion.assertThat(carouselComponent.isDisplayed()).as("The Carousel is being displayed").isFalse();
                    softAssertion.assertThat(nextOffComponent.isNextOffDisplayed()).as("The Next Off Section is not being displayed").isTrue();
                    softAssertion.assertThat(nextOffComponent.isNextOffExpanded()).as("The Next Off Section is not expanded").isTrue();
                    softAssertion.assertThat(nextOffComponent.numberOfNextOffRacesDisplayed()).as("The Next Off section is empty").isGreaterThan(0);
                    softAssertion.assertThat(nextOffComponent.numberOfNextOffRacesDisplayed()).as("The Next Off section is displaying more than 3 Races").isLessThan(4);
                    softAssertion.assertThat(genericSportsPage.getTopBetsComponent().isDisplayed()).as("Top bets widget").isTrue();

                    // TODO: Verify the banners once we have them implemented and displayed.
                }
                break;

            case Msg.RACECARD:

                softAssertion.assertThat(racecardPage.isAlternateMeetingDropdownDisplayed()).as("The Alternate Meetings dropdown is not being displayed").isTrue();
                softAssertion.assertThat(racecardPage.isRacePageContentDisplayed()).as("The Content of the Racing page is not being displayed").isTrue();
                softAssertion.assertThat(racecardPage.areRaceDetailsDisplayed()).as("The Race Details are not being displayed").isTrue();

                if (genericSportsPage.isMobile()) {
                    softAssertion.assertThat(racecardPage.isRacecardDisplayed()).as("Race Card Displayed").isTrue();
                    softAssertion.assertThat(racecardPage.isRaceToRaceNavigationDisplayed()).as("Race To Race Navigation displayed").isTrue();
                } else {
                    softAssertion.assertThat(genericSportsPage.isBackButtonDisplayed()).as("The back button is being displayed").isFalse();
                    softAssertion.assertThat(racecardPage.isRacecardDisplayed()).as("Race Card Displayed").isTrue();
                    softAssertion.assertThat(racecardPage.isRaceToRaceNavigationDisplayed()).as("Race To Race Navigation displayed").isTrue();
                    softAssertion.assertThat(genericSportsPage.getTopBetsComponent().isDisplayed()).as("Top bets widget").isTrue();
                }
                break;

            case Msg.MEETINGS:
                if (genericSportsPage.isMobile()) {
                    softAssertion.assertThat(genericSportsPage.getCarouselComponent().isDisplayed()).as("Carousel Displayed").isTrue();
                    softAssertion.assertThat(meetingsPage.isMeetingsItemsDisplayed()).as("Races Displayed for Meetings").isTrue();
                    softAssertion.assertThat(genericSportsPage.getCarouselComponent().isMeetingsTabSelected()).as("Meetings Tab Selected").isTrue();
                } else {
                    softAssertion.assertThat(meetingsPage.isMeetingsItemsDisplayed()).as("Races Displayed for Meetings").isTrue();
                    softAssertion.assertThat(genericSportsPage.getTopBetsComponent().isDisplayed()).as("Top bets widget").isTrue();
                }
                break;
            case Msg.NEXT_OFF:
                softAssertion.assertThat(meetingsPage.isNextOffItemsDisplayed()).as("Races Displayed for Next Off").isTrue();
                softAssertion.assertThat(genericSportsPage.getCarouselComponent().isDisplayed()).as("Carousel Displayed").isTrue();
                softAssertion.assertThat(genericSportsPage.getCarouselComponent().isNextOffTabSelected()).as("Next Off Tab Selected").isTrue();
                softAssertion.assertThat(genericSportsPage.getTopBetsComponent().isDisplayed()).as("Top bets widget").isTrue();
                break;
            case Msg.FUTURE_RACES:
                if (genericSportsPage.isMobile()) {
                    softAssertion.assertThat(antePostPage.isRaceItemsDisplayedInGroupNavigation()).as("Races Displayed in Group Navigation").isTrue();
                } else {
                    softAssertion.assertThat(antePostPage.isRaceItemsDisplayedInGroupNavigation()).as("Races Displayed in Group Navigation").isTrue();
                    throwKnownIssueExceptionIfNeeded(!sportsMenuComponent.isFutureRacesSelected(), "TDRD-494: Future Races Menu Highlighted.");
                    softAssertion.assertThat(sportsMenuComponent.isFutureRacesSelected()).as("Future Races menu highlighted").isTrue();
                    softAssertion.assertThat(genericSportsPage.getTopBetsComponent().isDisplayed()).as("Top bets widget").isTrue();
                }
                break;
            case Msg.SPECIALS:
                if (!genericSportsPage.isMobile()) {
                    softAssertion.assertThat(sportsMenuComponent.isSpecialsSelected()).isTrue();
                    softAssertion.assertThat(genericSportsPage.getTopBetsComponent().isDisplayed()).as("Top bets widget").isTrue();
                }
                break;
            case Msg.TOP_BETS:
                if (genericSportsPage.isMobile()) {
                    softAssertion.assertThat(genericSportsPage.isTopBetsDetailsDisplayed()).as("Top Bets Tab Selected").isTrue();
                } else {
                    softAssertion.assertThat(genericSportsPage.isTopBetsDetailsDisplayed()).as("Top Bets Details Displayed").isTrue();
                }
                break;
            default:
                throw new NotImplementedException("Option " + page + " not implemented");
        }
        if (genericSportsPage.isMobile()) {
            if(!genericSportsPage.isBackButtonDisplayed())
                throwKnownIssueExceptionIfNeeded(!genericSportsPage.isBackButtonDisplayed(),"TDRD-715 -> back button is displayed twice in the DOM");
            softAssertion.assertThat(genericSportsPage.isBackButtonDisplayed()).as("The back button is being displayed").isTrue();
            softAssertion.assertThat(sportsMenuComponent.isDisplayed()).as("Sports menu").isFalse();
            softAssertion.assertThat(betSlipComponent.isDisplayed()).as("Betslip").isFalse();
        } else {
            softAssertion.assertThat(genericSportsPage.isBackButtonDisplayed()).as("Back Button displayed").isFalse();
            softAssertion.assertThat(sportsMenuComponent.isDisplayed()).as("Sports menu").isTrue();
            softAssertion.assertThat(betSlipComponent.isDisplayed()).as("Betslip").isTrue();
            if (!page.equalsIgnoreCase(Msg.TOP_BETS))
                softAssertion.assertThat(genericSportsPage.getTopBetsComponent().isDisplayed()).as("Top bets widget").isTrue();
        }
        softAssertion.assertAll();
    }

    @Then ("^active races are displayed ordered by time in the Race To Race menu$")
    public void isRacesAreSortedByTimeInRaceToRace() {
        assertThat(racecardPage.areRacesAreSortedByTimeInRaceToRace()).isTrue();
    }

    @Then ("^All Races link is displayed first in the Race To Race menu$")
    public void isAllRacesDisplayedFirst() {
        assertThat(racecardPage.isAllRacesDisplayedFirst()).isTrue();
    }

    @Then ("^meeting markets link (is|is not) displayed last in the Race To Race menu$")
    public void isMeetingMarketDisplayedLast(String displayed) {
        if (Msg.IS.equalsIgnoreCase(displayed)) {
            assertThat(racecardPage.isMeetingMarketDisplayedLast()).isTrue();
        } else {
            assertThat(racecardPage.isMeetingMarketDisplayedLast()).isFalse();
        }
    }

    @And("^user clicks on \'(.*)(st|nd|rd|th)\' index in the race to race menu")
    public void clickRaceToRaceIndex(int selectionNumber, String classyEnding) {
        this.racecardPage.clickRaceToRaceIndex(selectionNumber);
    }

    @And("user expand all Collections and Events from the Left Racing Navigation")
    public void expandAllCollectionsAndEvents() throws InterruptedException {
        meetingsNavigationComponent.expandAllCollectionsAndEvents();
    }

}
