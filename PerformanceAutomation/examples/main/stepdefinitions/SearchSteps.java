package com.whgtf.sportsbook.main.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.whgtf.sportsbook.main.util.TranslationUtils.LANGUAGE_URL_MAP;
import static com.whgtf.sportsbook.pom.utils.Assertions.assertArrayContains;
import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class SearchSteps extends MainSteps {

    private String eventName;

    @When("^user searches for '(.*)'$")
    public void userSearchesForMan(final String search) throws Throwable {
        searchComponent.open().search(search);
    }

    @Then("^searching results have links to '(.*)'$")
    public void searchingResultsHaveLinksToLanguage(final String language) throws Throwable {
        assertArrayContains(LANGUAGE_URL_MAP.get(language), searchComponent.getAllLinksResults());
    }

    @Then("^search results are displayed as links$")
    public void searchResultsAreDisplayedAsLinks() {
        assertThat(searchComponent.getAllLinksResults()).isNotEmpty();
    }

    @When("^user selects event search result link from results list$")
    public void userSelectsEventSearchResultLink() {
        eventName = searchComponent.getEventNameFromResultsList(0);
        eventPage = searchComponent.clickOnResultLink(0);
    }

    @Then("^verify competetion event page is displayed$")
    public void verifyCompetetionEventPageIsDisplayed() {
        assertThat(eventPage.isEventHeaderDisplayed(eventName)).isTrue();
    }

    @Then("^matching sports icons are displayed$")
    public void matchingSportsIconsAreDisplayed(List<String> sports) {
        SoftAssertions softly = new SoftAssertions();
        for (String sportsName : sports) {
            softly.assertThat(searchComponent.isSportsIconDisplayed(sportsName)).isTrue();
            softly.assertThat(searchComponent.isSportsResultDisplayed(sportsName)).isTrue();
        }
        softly.assertAll();
    }

    @Then("^matching events details are displayed$")
    public void matchingEventDetailsAreDisplayed(List<String> eventResults) {
        List<String> actualResults = searchComponent.getAllEventResults();
        SoftAssertions softly = new SoftAssertions();
        for (String eventName : eventResults) {
            softly.assertThat(actualResults).contains(eventName);
        }
        softly.assertAll();
    }

    @Then("^verify matching events are displayed under the sports results$")
    public void verifyMatchingEventsAreDisplayedUnderTheSportsResults() {
        assertThat(searchComponent.areEventsDisplayedUnderSportsResults()).isTrue();
    }

    @Then("^verify matching sports icons should be displayed as carousels$")
    public void verifyMatchingSportsIconsAsCarousels() {
        assertThat(searchComponent.isSportsResultsDisplayedAsCarousel()).isTrue();
    }

    @Then("^verify no duplicate sports results are displayed$")
    public void verifyNoDuplicateSports() {
        List<String> sports = searchComponent.getSportsResults();
        Set<String> sportsSet = new HashSet<>(sports);

        assertThat(sports).hasSameSizeAs(sportsSet);
    }

    @Then("^verify no sports name icons and event details are displayed$")
    public void verifyNoInactiveSportsResults() {
        assertThat(searchComponent.getSportsResults()).isEmpty();
        assertThat(searchComponent.getAllEventResults()).isEmpty();
    }

    @When("^user amends '(.*)' to searchText$")
    public void userAmendsSearch(String extraSearchChar) throws Throwable {
        searchComponent.search(extraSearchChar);
    }

    @Then("^\"(.*?)\" error should not be displayed$")
    public void error_should_not_be_displayed(String errMsg) throws Throwable {
        assertThat(searchComponent.isMessagePresent(errMsg)).isFalse();
    }

    @Then("^verify results page is displayed with updated results$")
    public void verifyResultsPageIsDisplayedWithUpdatedResults() throws Throwable {
        assertThat(searchComponent.getAllLinksResults()).isNotEmpty();
    }

    @When("^selects the displayed \"(.*?)\" image link$")
    public void selects_the_displayed_sports_image_link(String sportsName) throws Throwable {
        searchComponent.selectSportFromResults(sportsName);
    }

    private String environment = System.getProperty("env");

    @Then("^verify \"(.*?)\" home page is displayed$")
    public void verify_sports_home_page_is_displayed(String sportsName) throws Throwable {
        if((!environment.equalsIgnoreCase("dev")) || ! "cricket".equals(sportsName)) {
            searchComponent.isSportsHomePageDisplayed(sportsName);
        }
    }

    @Then("^verify \"(.*?)\" image is displayed as part of sports results$")
    public void verify_expectedSport_is_displayed(String expectedSports) {
        List<String> sports = new ArrayList<>();
        sports.add("Motor Racing");
        sports.add("GAA Hurling");
        sports.add("GAA Football");
        sports.add("Bowls");
        sports.add("American Football");
        if((!environment.equalsIgnoreCase("dev")) || !sports.contains(expectedSports)){
            assertThat(searchComponent.isSportsResultDisplayed(expectedSports)).isTrue();
        }
    }

    @When("^user goes to '(.*)'$")
    public void userGoesToGivenLanguage(String language) throws Throwable {
        homePage.openLanguage(language);
    }

    @Then("^verify '(.*)' is displayed$")
    public void verifyMessageIsDisplayed(String message) throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(searchComponent.isMessagePresent(message)).isTrue();
        softly.assertThat(searchComponent.getAllEventResults().size() == 1).isTrue();
        softly.assertAll();
    }
}