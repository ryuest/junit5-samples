package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.pom.common.components.interfaces.SearchResultItem;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class SearchDesignSteps extends MainSteps {
    private static final Logger logger =
            Logger.getLogger(SearchDesignSteps.class.getName());

    @When("^user navigates to Search$")
    public void userNavigatesToSearch() {
        searchComponent.open();

    }

    @Then("^verify Search placeholder '(.*)' is displayed$")
    public void verifySearchPlaceHolder(String placeholder) {
        assertThat(searchComponent.getSearchInputPlaceHolder()).isEqualTo(placeholder);
    }

    @When("^user clears the search text by pressing back space$")
    public void userClearTheSearchTextByBackSpace() {
        searchComponent.clearTextByUsingBackSpace();
    }

    @Then("^verify '(.*)' is cleared$")
    public void verifySearchTextIsEmpty(String searchText) {
        String actualSearchText = searchComponent.getSearchText();
        assertThat(actualSearchText).isNotEqualTo(searchText);
        assertThat(searchComponent.getSearchText()).isEmpty();
    }

    @When("^user closes the search overlay$")
    public void userClosesTheSearchOverlay() {
        searchComponent.closeSearchOverlay();
    }

    @Then("verify search overlay is closed")
    public void verifySearchOverlayIsClosed() {
        assertThat(searchComponent.isSearchOverlayClosed()).isFalse();
    }



    @Then("'(.*)' header is displayed above sports results")
    public void quickLinksHeaderIsDisplayed(String quickLinksHeader) {
        assertThat(searchComponent.isQuickLinksHeaderDisplayedAboveSportsResults(quickLinksHeader)).isTrue();
    }

    @Then("^sports results count is displayed with QuickLinks header$")
    public void sportsResultsCountIsDisplayed() {
        assertThat(searchComponent.isQuickLinksCountDisplayed()).isTrue();
    }

    @Then("'(.*)' header is displayed above event results")
    public void eventsHeaderIsDisplayed(String eventHeaderName) {
        assertThat(searchComponent.isEventLinksHeaderDisplayedAboveEventsResults(eventHeaderName)).isTrue();
    }

    @Then("^events count is displayed with Events header$")
    public void eventsCountIsDisplayed() {
        assertThat(searchComponent.isEventsCountDisplayed()).isTrue();
    }

    @Then("^events are displayed with details$")
    public void eventDetailsAreDisplayed() {
        // Verifying the below details for the displayed search result item.
        //  |Event name|
        //  |Start time|
        //  |Parent competition name (If event is created with competition)|
        //  |In play Icon (If event is in play)|
        //  |Streaming Icon (If the event has streaming available) |

        int count = searchComponent.getEventResultsCount();
        int loop = 0, val = 0, inPlayCount= 0, nonInplayCount = 0,j=0;
        boolean breakLoop = false;
        while (count != 0 && ((val < count)||(loop<=count/5))) {
                j = j+5;
            for (int i = 5*loop; i < j; i++) {
                SoftAssertions softAssertions = new SoftAssertions();
                SearchResultItem searchResultItem = searchComponent.getEventResult(i);

                boolean inPlayIconDisplayed = searchResultItem.isInPlayIconDisplayed();

                if (!inPlayIconDisplayed) {
                    softAssertions.assertThat(searchResultItem.getCompetetionName()).isNotEmpty();
                    softAssertions.assertThat(searchResultItem.getDateTime()).isNotEmpty();
                    nonInplayCount++;
                } else {
                    softAssertions.assertThat(inPlayIconDisplayed).isTrue();
                    inPlayCount++;
                }

                softAssertions.assertThat(searchResultItem.getEventName()).isNotEmpty();
                //softAssertions.assertThat(searchResultItem.isStreamingIconDisplayed()).isTrue();

                softAssertions.assertAll();

                if(nonInplayCount >0 && inPlayCount >0){
                    breakLoop = true;
                    break;
                }

            }
            if(breakLoop)break;
            searchComponent.scrollDownSearchResults();
            loop++;
        }
    }

    @Then("^verify event results are seperated by line between the events$")
    public void verifyEventResultsAreSeperatedByLine() {
        assertThat(searchComponent.areEventResultsSeperatedByLine()).isTrue();
    }

    @Then("^verify more event results are displayed while scrolling down until end of results$")
    public void verifyMoreEventsOnScrolling() {
        int count = searchComponent.getEventResultsCount();
        int loop = 0;
        int val = 0;
        while (count != 0 && ((val < count)||(loop<=count/5))) {
            val = searchComponent.getAllEventResults().size();
            logger.info("Scrolled into "+val+" elements");
            assertThat(searchComponent.scrollDownSearchResults()).isTrue();
            loop++;
        }
        assertThat(count).isEqualTo(val);
    }
}