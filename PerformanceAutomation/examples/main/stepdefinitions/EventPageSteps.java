package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import cucumber.api.java.en.And;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by javierg on 21/09/2016.
 */
@Component
@Lazy
public class EventPageSteps extends MainSteps {

    @And("^the user clicks on the Load more button in '(home|away)' scorer market and adds the last visible selection$")
    public void thereIsAEventWithActiveSelection(final String eventType) throws Throwable {
        if(genericSportsPage.isMobile())
            eventPage.clickOnTab(eventType);

        eventPage.clickOnHomeAwayLoadMoreButton(eventType);
    }

    @And("^there is a '(.*)' market$")
    public void thereIsAMarketByName(final String marketName) throws Throwable {
        ScenarioContext.saveData("market",eventPage.getMarketDisplayedByName(marketName));
    }

    @And("^market collection menu (is|is not) displayed$")
    public void checkMarketMollectionMenu(final String displayed) throws Throwable {
        if("is".equalsIgnoreCase(displayed))
            assertThat(eventPage.isMarketCollectionListDisplayed()).isTrue();
        else
            assertThat(eventPage.isMarketCollectionListDisplayed()).isFalse();
    }

}
