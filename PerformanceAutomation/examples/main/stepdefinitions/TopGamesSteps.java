package com.whgtf.sportsbook.main.stepdefinitions;


import com.whgtf.sportsbook.main.util.Msg;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class TopGamesSteps extends MainSteps{



    @And("^selects an? (embedded|non embedded) game from the top games overlay$")
    public void selectsAnGameTypeGameFromTheTopGamesOverlay(String gameType) throws Throwable {
        if(Msg.EMBEDDED.equalsIgnoreCase(gameType))
            topGamesComponent.clickOnEmbeddedGame();
        else
            topGamesComponent.clickOnNoEmbeddedGame();
    }


    @Then("^the top games overlay (is|is not) displayed$")
    public void theTopGamesOverlayIsDisplayed(String overlayDisplayed) throws Throwable {
        if(Msg.IS.equalsIgnoreCase(overlayDisplayed))
            assertThat(topGamesComponent.isDisplayed()).isTrue();
        else
            assertThat(topGamesComponent.isDisplayed()).isFalse();
    }

    @Then("^the (non embedded|embedded) game is displayed$")
    public void theNonEmbeddedGameIsDisplayed(final String embedded) throws Throwable {
        assertThat(gamePage.isDisplayed()).isTrue();
    }

    @When("^user close top games overlay$")
    public void userCloseTopGamesOverlay() throws Throwable {
        topGamesComponent.clickOnCloseCross();
    }
}
