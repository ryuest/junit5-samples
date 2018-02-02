package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.util.Msg;
import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.components.impl.InPlaySectionImpl;
import com.whgtf.sportsbook.pom.common.exceptions.NoEventAvailable;
import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.math.Fraction;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by javierg on 04/08/2016.
 */
@Component
@Lazy
public class FeaturePageSteps extends MainSteps {

    @Then("^the feature page is displayed$")
    public void featurePageIsDisplayed() throws Throwable {
        assertThat(featuredPage.isDisplayed()).isTrue();
    }

    @When("^user check in play section has (\\d+) events")
    public void checkInPlayEvents(final int expectedSize) throws Throwable {
        assertThat(featuredPage.getInPlaySection().getEventList()).hasSize(expectedSize);
    }

    @When("^user clicks on the first event in the (in-play|highlights) section")
    public void clickOnTheFirstEventInInPlaySection(final String section) throws Throwable {
        Event event = null;
        if(Msg.IN_PLAY.equalsIgnoreCase(section)) {
            InPlaySectionImpl inPlaySection = featuredPage.getInPlaySection();
            event = inPlaySection.getEventList().get(0);
            inPlaySection.clickOnFirstEvent();

        } else if(Msg.HIGHLIGHTS.equalsIgnoreCase(section)) {
            event = featuredPage.getHighlightsSection().getEventList().get(0);
            featuredPage.getHighlightsSection().clickOnFirstEvent();
        }
        ScenarioContext.setCurrentEvent(event);
    }

    @When("^user clicks on the first selection in the (in-play|highlights) section")
    public void clickOnTheFirstSelectionInInPlaySection(final String section) throws Throwable {
        String selectionId = "";
        if(Msg.IN_PLAY.equalsIgnoreCase(section))
            selectionId=featuredPage.getInPlaySection().clickOnFirstSelection();
        else if(Msg.HIGHLIGHTS.equalsIgnoreCase(section))
            selectionId=featuredPage.getHighlightsSection().clickOnFirstSelection();
        ScenarioContext.saveData("selection",new Selection(selectionId));
    }

    @Then("^the price is (decreased|increased)$")
    public void thePriceHasChanged(final String option) throws Throwable {
        // comparison result
        Selection selection = (Selection) ScenarioContext.getSavedData("selection");
        int result = Fraction.getFraction(featuredPage.getOddFromSelection(selection.getPdsId())).compareTo(
                Fraction.getFraction(selection.getPrice()));
        if ("increased".equals(option))
            assertThat(result).isEqualTo(1);
        else // is decreased
            assertThat(result).isEqualTo(-1);
    }

    @And("^there is an (pre-match|in-play) event with an active (selection|market) in feature page$")
    public void thereIsAnActiveSelection(final String eventType, final String section) throws Throwable {
        Selection activeSelection = null;
        Market activeMarket = null;
        if("selection".equalsIgnoreCase(section)) {
            if (Msg.PRE_MATCH.equalsIgnoreCase(eventType)) {
                activeSelection = featuredPage.getHighlightsSection().getActiveSelection();
            } else {
                activeSelection = featuredPage.getInPlaySection().getActiveSelection();
            }
            if (activeSelection == null)
                assertThat(false).withFailMessage("There was no active selection");
            else {
                ScenarioContext.saveData("selection", activeSelection);
            }
        } else {
            if(Msg.PRE_MATCH.equalsIgnoreCase(eventType)) {
                activeMarket = this.featuredPage.getHighlightsSection().getActiveMarket();
            } else {
                activeMarket = this.featuredPage.getInPlaySection().getActiveMarket();
            }

            if(activeMarket == null) {
                assertThat(false).withFailMessage("There was no active selection");
            } else {
                ScenarioContext.saveData("market", activeMarket);
            }
        }
    }

    @And("^(in-play|highlights) section (is|is not) displayed$")
    public void sectionIsDisplayed(final String section, final String isDisplayed) throws Throwable {
        if (Msg.IN_PLAY.equalsIgnoreCase(section)) {
            if ("is".equalsIgnoreCase(isDisplayed)) {
                assertThat(featuredPage.getInPlaySection().isDisplayed()).isTrue();
            } else {
                assertThat(featuredPage.getInPlaySection().isDisplayed()).isFalse();
            }
        } else {
            if ("is".equalsIgnoreCase(isDisplayed)) {
                assertThat(featuredPage.getHighlightsSection().isDisplayed()).isTrue();
            } else {
                assertThat(featuredPage.getHighlightsSection().isDisplayed()).isFalse();
            }
        }
    }

    @And("^there is a (pre-match|in-play) active event")
    public void thereIsAnActiveEvent(String eventType) throws Throwable {
        Event activeEvent = null;
        if(Msg.PRE_MATCH.equalsIgnoreCase(eventType)) {
            activeEvent = this.featuredPage.getHighlightsSection().getActiveEvent();
        } else {
            activeEvent = this.featuredPage.getInPlaySection().getActiveEvent();
        }

        if(activeEvent == null) {
            throw new NoEventAvailable();
        } else {
            ScenarioContext.saveData("event", activeEvent);
        }

    }

    @And("^there is an in-play event$")
    public void thereIsAnInPlayEvent() throws Throwable {
        assertThat(featuredPage.getInPlaySection().getActiveList().size()).as("active event list").isNotZero();
    }

    @And("^the user clicks on the first '(highlight|in-play)' event in feature page$")
    public void theUserClicksOnTheFirstInPlayEvent(final String event) throws Throwable {

        Event ev = null;
        TimeUnit.SECONDS.sleep(1);
        if(featuredPage.isMobile() && !featuredPage.isNativeMobileApp()) {
            if(Msg.IN_PLAY.equalsIgnoreCase(event))
                tabbedHighlightsComponent.clickOnInPlayTab();
            else
                tabbedHighlightsComponent.clickOnHighlightsTab();
        }
        if (Msg.IN_PLAY.equalsIgnoreCase(event)) {
            ev = featuredPage.getInPlaySection().getActiveList().get(0);
            featuredPage.getInPlaySection().clickOnEvent(ev.getPdsId());
        } else {
            ev = featuredPage.getHighlightsSection().getActiveList().get(0);
            featuredPage.getHighlightsSection().clickOnEvent(ev.getPdsId());
        }
        ScenarioContext.setCurrentEvent(ev);
    }

    @And("^the user clicks on the tab '(Top Bets|Highlights|In-Play|Coupons)' in the tabbed navigation$")
    public void userClicksOnTab(final String tab) throws Throwable {
        if(Msg.IN_PLAY.equalsIgnoreCase(tab))
            tabbedHighlightsComponent.clickOnInPlayTab();
        else if(Msg.HIGHLIGHTS.equalsIgnoreCase(tab))
            tabbedHighlightsComponent.clickOnHighlightsTab();
        else if(Msg.TOP_BETS.equalsIgnoreCase(tab))
            tabbedHighlightsComponent.clickOnTopBetsTab();
        else if (Msg.COUPONS.equalsIgnoreCase(tab))
            tabbedHighlightsComponent.clickOnCouponsTab();
    }
}
