package com.whgtf.sportsbook.main.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by javierg on 14/07/2016.
 */
@Component
@Lazy
public class FooterSteps extends MainSteps {

    @And("^the user clicks on back to the top button in the footer$")
    public void clickOnBackToTheTopButton () {
        genericSportsPage.getFooterComponent().clickOnBackToTopButton();
    }

    @And("^user set price format to '(Decimal|Fractional|American)'$")
    public void userSetPriceFormatToOdd_format(final String odd_type) throws Throwable {
        homePage.setPrices(odd_type);
    }

    @Then("^the price format '(Decimal|Fractional|American)' is highlighted$")
    public void thePriceFormatOdd_formatIsHighlighted(final String odd_type) throws Throwable {
        assertThat(homePage.isPriceHighlighted(odd_type)).isTrue();
    }


}
