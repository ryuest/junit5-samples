package com.whgtf.sportsbook.main.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class AntePostPageSteps extends MainSteps {

    @When("^user clicks on All Races in Ante Post page$")
    public void userClicksOnAllRacesInAntePostPage() throws Throwable {
        antePostPage.getFutureRaceComponent().clickOnTheFirstAllRaces();
    }

    @And("^verifies that the Antepost Header Section is not displayed$")
    public void antePostHeaderIsNotDisplayed() throws Throwable {
        assertThat(antePostPage.antePostHeaderDisplay()).isFalse();
    }
}
