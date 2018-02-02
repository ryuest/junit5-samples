package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.util.Msg;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class BottomMenuSteps extends MainSteps {


    @And("^user clicks on '(search|sports|betslip|open bets|search|top games)' in the footer menu$")
    public void userClickOnOptionInFooterMenu(final String option) throws Throwable {
        switch (option.toLowerCase()) {
            case Msg.SEARCH:
                genericSportsPage.getBottonBar().clickOnSearchMenu();
                break;
            case Msg.SPORTS:
                genericSportsPage.getBottonBar().clickOnSportsMenu().clickOnSport(option);
                break;
            case Msg.TOP_GAMES:
                genericSportsPage.getBottonBar().clickOnTopGamesMenu();
                break;
        }
    }

    @Then("^the top games menu (is|is not) displayed in the bottom menu$")
    public void theTopGamesMenuIsNotDisplayedInTheBottomMenu(String topGamesDisplayed) throws Throwable {
        if(Msg.IS.equalsIgnoreCase(topGamesDisplayed))
            assertThat(genericSportsPage.getBottonBar().isTopGamesMenuDisplayed()).isTrue();
        else
            assertThat(genericSportsPage.getBottonBar().isTopGamesMenuDisplayed()).isFalse();
    }

    @And("^the bottom menu is not displayed$")
    public void theBottomMenuIsNotDisplayed() throws Throwable {
        assertThat(genericSportsPage.getBottonBar().isDisplayed()).isFalse();
    }

    @And("^the Accumulator box is displayed$")
    public void isAccumulatorDisplayed() throws Throwable {
        assertThat(genericSportsPage.getBottonBar().isAccumulatorDisplayed()).isFalse();
    }

    @And("^ACCA popup '(is|is not)' displayed on the Bottom Bar$")
    public void isAccumulatorDisplayed(final String isOrNot) throws Throwable {
        if("is".equals(isOrNot)){
            assertThat(bottomBarComponent.isAccumulatorDisplayed()).isTrue();
        }else{
            assertThat(bottomBarComponent.isAccumulatorDisplayed()).isFalse();
        }
    }

    @And("^the ACCA message on the Footer says '(.*)'$")
    public void getAccumulatorText(final String message) throws Throwable {
        assertThat(bottomBarComponent.isAcumulatorTextThis(message)).isTrue();
    }

}