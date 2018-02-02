package com.whgtf.sportsbook.main.stepdefinitions;

import cucumber.api.java.en.And;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class SportNavigationComponentCarouselSteps extends MainSteps {


    @And("^user navigates through all sports in in-play carousel$")
    public void userNavigatesThroughAllSports() throws Throwable {
        SoftAssertions softAssertion = new SoftAssertions();
        String url = genericSportsPage.getUrl();
        assertThat(allInPlayPage.isCarouselDisplayed()).as("Carousel is not available in All In-Play page").isTrue();
        for (int i=0 ; i<allInPlayPage.getCarouselElementsNumber() ; i++) {
            allInPlayPage.clickOnCarouselIconByPosition(i);
            softAssertion.assertThat(url).isNotEqualTo(allInPlayPage.getUrl());
            url = allInPlayPage.getUrl();
        }
    }
}
