package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.util.Msg;
import cucumber.api.java.en.And;
import org.assertj.core.api.SoftAssertions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class VirtualWorldSteps extends MainSteps {

    @And("^user clicks on '(Horse Racing Flat|Horse Racing National Hunt|Football|Greyhounds Flat|Greyhounds Hurdles|Motor Racing|Speedway|Cycling)' from the sports menu$")
    public void clickOnVirtualFromSportsMenu(final String option) throws Throwable {
        sportsMenuComponent.clickOnSportPageByName("Virtual World",option);
    }

    @And("^user clicks on '(Horse Racing - Flat|Horse Racing - National Hunt|Football|Greyhounds - Flat|Greyhounds - Hurdles|Motor racing|Speedway|Cycling)' from the Virtual World Carousel")
    public void clickOnVirtualWorldCarousel(final String option) throws Throwable {
        genericSportsPage.getCarouselComponent().clickOnGivenCarouselElement(option);
    }

    @And("^user goes to '(Horse Racing - Flat|Horse Racing - National Hunt|Football|Greyhounds - Flat|Greyhounds - Hurdles|Motor racing|Speedway|Cycling)' virtual page")
    //TODO: this method hasn't been verified
    public void navigateToVirtualSport(final String option) throws Throwable {
        if (genericSportsPage.isMobile()) {
            genericSportsPage.getBottonBar().clickOnSportsMenu().clickOnSportByName("Virtual World");
            genericSportsPage.getCarouselComponent().clickOnGivenCarouselElement(option);
        } else {
            genericSportsPage.getSportsMenuComponent().clickOnSportPageByName("Virtual World", option);
        }
    }

    @And("^Virtual World is displayed correctly in Desktop")
    public void virtualWorldDisplayedCorrectly() throws Throwable {
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(virtualsPage.isPreloaderDisplayed()).as(Msg.VIRTUAL_PRELOADER_DISPLAYED).isFalse();
        soft.assertThat(virtualsPage.isVirtualsTitleDisplayed()).as(Msg.VIRTUAL_TITLE_DISPLAYED).isTrue();
        soft.assertThat(virtualsPage.isVirtualWorldHeaderDisplayed()).as(Msg.VIRTUAL_HEADER_DISPLAYED).isTrue();
        soft.assertAll();
    }

    @And("^correct options available in virtual world menu")
    public void areSectionOptionsDisplayedOnDesktop() throws Throwable {
        assertThat(virtualsPage.areSectionOptionsDisplayedOnDesktop()).isTrue();
    }


    @And("^user clicks on the last available selection on Virtual World")
    public void clickOnFirstAvailableSelectionVirtualWorld() throws Throwable {
        virtualsPage.clickOnFirstAvailableSelection();
    }

    @And("^user clicks on Next Off Races in the meeting menu")
    public void clickOnNextOff() throws Throwable {
        if (genericSportsPage.isMobile()) {
            virtualsPage.clickOnNextOffRaces();
        }
    }


    @And("^user clicks on the '(.*)(st|nd|rd|th)' Football Market on the Virtual World")
    public void clickOnGivenFootballMarket(final int index, final String classyEnding) throws Throwable {
        virtualsPage.areFootballMarketsDisplayed(index);
    }

    @And("^the virtual world carousel '(is|is not)' displayed")
    public void isVirtualWorldCarouselDisplayed(final String input) throws Throwable {
        if(Msg.IS.equals(input)) {
            throwKnownIssueExceptionIfNeeded(!virtualsPage.getCarouselComponent().isDisplayed(), Msg.EMPTY);
            assertThat(virtualsPage.getCarouselComponent().isDisplayed()).isTrue();
        }else{
            assertThat(virtualsPage.getCarouselComponent().isDisplayed()).isFalse();
        }
    }

    @And("^options in virtual world carousel are displayed in correct order")
    public void isVirtualWorldCarouselOrderRight() throws Throwable {
        List<String> expectedOrder = virtualsPage.getCarouselComponent().getExpectedVirtualSportNames();
        List<String> readOrder = virtualsPage.getCarouselComponent().getVirtualWorldCarouselSportNames();
        assertThat(readOrder).containsExactlyElementsOf(expectedOrder);
    }

    @And("^highlighted option in virtual world carousel is '(Featured|Horse Racing - Flat|Horse Racing - National Hunt|Football|Greyhounds - Flat|Greyhounds - Hurdles|Motor racing|Speedway|Cycling)'")
    public void highlightedOptionInVirtualWorldCarousel(final String option) throws Throwable {
        assertThat(virtualsPage.getCarouselComponent().highlightedOptionInVirtualWorldCarousel(option)).isTrue();
    }

    @And("^user navigates through all options on the virtual world carousel")
    public void verifyVirtualWorldCarouselRedirection() throws Throwable {
        List<String> sportsNames = virtualsPage.getCarouselComponent().getExpectedVirtualSportNames();
        SoftAssertions softAssertion = new SoftAssertions();
        String currentUrl = Msg.EMPTY;
        for (String sport : sportsNames) {
            virtualsPage.getCarouselComponent().clickOnGivenCarouselElement(sport);
            softAssertion.assertThat(virtualsPage.getCarouselComponent().highlightedOptionInVirtualWorldCarousel(sport)).describedAs(sport + " is highlighted").isTrue();
            softAssertion.assertThat(virtualsPage.getUrl().equalsIgnoreCase(currentUrl)).describedAs("sport url -->  " + sport + " has no changed. Is " + virtualsPage.getUrl()).isFalse();
            currentUrl = virtualsPage.getUrl();
        }
        softAssertion.assertAll();
    }

    @And("^highlighted option in virtual world menu is '(Horse Racing - Flat|Horse Racing - National Hunt|Football|Greyhounds - Flat|Greyhounds - Hurdles|Motor racing|Speedway|Cycling)'")
    public void highlightedOptionInSectionsMenu(final String option) throws Throwable {
        assertThat(virtualsPage.highlightedOptionInSectionsMenu(option)).isTrue();
    }

    @And("^user navigates through all virtual child pages in sports menu")
    public void verifyVirtualWorldSectionsMenuRedirection() throws Throwable {
        SoftAssertions softAssertions = new SoftAssertions();
        virtualsPage.clickOnHorseRacingFlat();
        softAssertions.assertThat(virtualsPage.getUrl()).as("horse-racing/flat is in the url").contains("horse-racing/flat");
        throwKnownIssueExceptionIfNeeded(!virtualsPage.highlightedOptionInSectionsMenu("Horse Racing - Flat"),"@TDRD-245");
        softAssertions.assertThat(virtualsPage.highlightedOptionInSectionsMenu("Horse Racing - Flat")).as("Horse Racing - Flat not highlited").isTrue();
        softAssertions.assertThat(virtualsPage.areSelectionsDisplayed()).as("Selection displayed").isTrue();
        virtualsPage.clickOnHorseRacingNationalHunt();
        softAssertions.assertThat(virtualsPage.getUrl()).as("horse-racing/national-hunt is in the url").contains("horse-racing/national-hunt");
        throwKnownIssueExceptionIfNeeded(!virtualsPage.highlightedOptionInSectionsMenu("Horse Racing - National Hunt"),"@TDRD-245");
        softAssertions.assertThat(virtualsPage.highlightedOptionInSectionsMenu("Horse Racing - National Hunt")).as("Horse Racing - National Hunt not highlited").isTrue();
        softAssertions.assertThat(virtualsPage.areSelectionsDisplayed()).as("Selection displayed").isTrue();
        virtualsPage.clickOnFootball();
        softAssertions.assertThat(virtualsPage.getUrl()).as("virtual/football is in the url").contains("virtual/football");
        throwKnownIssueExceptionIfNeeded(!virtualsPage.highlightedOptionInSectionsMenu("Football"),"@TDRD-245");
        softAssertions.assertThat(virtualsPage.highlightedOptionInSectionsMenu("Football")).as("Football not highlited").isTrue();
        softAssertions.assertThat(virtualsPage.areSelectionsDisplayed()).as("Selection displayed").isTrue();
        virtualsPage.clickOnGreyhoundsFlat();
        softAssertions.assertThat(virtualsPage.getUrl()).as("greyhounds/flat is in the url").contains("greyhounds/flat");
        throwKnownIssueExceptionIfNeeded(!virtualsPage.highlightedOptionInSectionsMenu("Greyhounds - Flat"),"@TDRD-245");
        softAssertions.assertThat(virtualsPage.highlightedOptionInSectionsMenu("Greyhounds - Flat")).as("Greyhounds - Flat not highlited").isTrue();
        softAssertions.assertThat(virtualsPage.areSelectionsDisplayed()).as("Selection displayed").isTrue();
        virtualsPage.clickOnMotorRacing();
        softAssertions.assertThat(virtualsPage.getUrl()).as("virtual/motor-racing is in the url").contains("virtual/motor-racing");
        throwKnownIssueExceptionIfNeeded(!virtualsPage.highlightedOptionInSectionsMenu("Motor racing"),"@TDRD-245");
        softAssertions.assertThat(virtualsPage.highlightedOptionInSectionsMenu("Motor racing")).as("Motor racing not highlited").isTrue();
        softAssertions.assertThat(virtualsPage.areSelectionsDisplayed()).as("Selection displayed").isTrue();
        virtualsPage.clickOnSpeedway();
        softAssertions.assertThat(virtualsPage.getUrl()).as("virtual/speedway is in the url").contains("virtual/speedway");
        throwKnownIssueExceptionIfNeeded(!virtualsPage.highlightedOptionInSectionsMenu("Speedway"),"@TDRD-245");
        softAssertions.assertThat(virtualsPage.highlightedOptionInSectionsMenu("Speedway")).as("Speedway not highlited").isTrue();
        softAssertions.assertThat(virtualsPage.areSelectionsDisplayed()).as("Selection displayed").isTrue();
        virtualsPage.clickOnCycling();
        softAssertions.assertThat(virtualsPage.getUrl()).as("virtual/cycling is in the url").contains("virtual/cycling");
        throwKnownIssueExceptionIfNeeded(!virtualsPage.highlightedOptionInSectionsMenu("Cycling"),"@TDRD-245");
        softAssertions.assertThat(virtualsPage.highlightedOptionInSectionsMenu("Cycling")).as("Cycling not highlited").isTrue();
        softAssertions.assertThat(virtualsPage.areSelectionsDisplayed()).as("Selection displayed").isTrue();

        softAssertions.assertAll();
    }
}
