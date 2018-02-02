package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import cucumber.api.java.en.And;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class BetslipSteps extends MainSteps {


    @And("^the Accumulator '(is|is not)' displayed$")
    public void isAccumulatorDisplayed(final String option) throws Throwable {
        if(option.equals("is")){
            assertThat(genericSportsPage.getBottonBar().isAccumulatorDisplayed()).isTrue();
        }else {
            assertThat(genericSportsPage.getBottonBar().isAccumulatorDisplayed()).isFalse();
        }
    }

    @And("^the Accumulator displays the correct selection price/details$")
    public void isAccumulatorDisplayed() throws Throwable {
        String marketId = (String) ScenarioContext.getSavedData("marketId");
        assertThat(genericSportsPage.getBottonBar().isAcumulatorTextThis(marketId)).isTrue();
    }

}
