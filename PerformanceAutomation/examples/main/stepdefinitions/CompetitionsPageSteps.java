package com.whgtf.sportsbook.main.stepdefinitions;

import cucumber.api.java.en.And;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by javierg on 14/07/2016.
 */
@Component
@Lazy
public class CompetitionsPageSteps extends MainSteps {

    @And("^Title is displayed in the Competition page$")
    public void verifyCompetitionsPageTitle() throws Throwable {
        assertThat(competitionsPage.getCompetitionsTitle().equals("Football Betting Competitions")).isTrue();
    }

    @And("^user clicks on '(Matches|Outrights)' tab$")
    public void clickOnMatchesOrOutrightsTab(final String tab) throws InterruptedException {
        if (tab.equals("Matches")) {
            competitionsPage.clickOnMatches();
        } else {
            competitionsPage.clickOnOutrights();
        }
    }

    @And("^user click on the '(.*)(st|nd|th)' region$")
    public void clickOnGivenRegion(final int regionListNumber, final String classyNumberEnding){
        competitionsPage.clickGivenRegion(regionListNumber);
    }

    @And("^user clicks on the '(.*)(st|nd|th)' competition from the '(.*)(st|nd|th)' region")
    public void clickOnGivenCompetition(final int competitionListNumber, final String classyNumberEnding, final int regionListNumber, final String classyNumberEnding2){
        competitionsPage.clickGivenCompetition(regionListNumber, competitionListNumber);
    }

    @And("^user click on '(.*)(st|nd|th)' market group from the '(.*)(st|nd|th)' competition, '(.*)(st|nd|th)' region")
    public void clickOnGivenEvent(final int eventNumber, final String numberEnding, final int competitionNumber, final String numberEnding2, final int regionNumber, final String numberEnding3){
        competitionsPage.clickGivenMarketGroup(regionNumber, competitionNumber, eventNumber);
    }

    @And("^events are displayed on the '(.*)(st|nd|th)' competition from the '(.*)(st|nd|th)' region")
    public void eventsDisplayedOnGivenCompetition(final int competitionNumber, final String classyNumberEnding, final int regionNumber, final String classyNumberEnding2){
        assertThat(competitionsPage.doesGivenCompetitionHaveEvents(regionNumber, competitionNumber)).isTrue();
    }

    @And("^events on the '(.*)(st|nd|th)' competition from the '(.*)(st|nd|th)' region have all its components")
    public void eventHaveAllComponentsFromGivenRegionCompetition(final int competitionNumber, final String classyNumberEnding, final int regionNumber, final String classyNumberEnding2){
        assertThat(competitionsPage.verifySelectionComponentsFromGivenCompetition(regionNumber, competitionNumber)).isTrue();
    }

    @And("^the regions '(are|are not)' displayed in '(matches|outrights)' tab$")
    public void verifyRegionsDisplay(final String regionsDisplayed, final String matchesOrOutrights) {
        if (regionsDisplayed.equals("are")) {
            assertThat(competitionsPage.areRegionsDisplayed(matchesOrOutrights)).isTrue();
        } else {
            assertThat(competitionsPage.areRegionsDisplayed(matchesOrOutrights)).isFalse();
        }
    }

    @And("^the '(.*)(st|nd|th)' region is '(expanded|collapsed)'$")
    public void verifyOnlyFirstRegionsIsExpanded(final int indexNumberOfRegionExpanded, final String classyNumberEnding, final String expandedOrCollapsed) {
        if (("expanded").equals(expandedOrCollapsed)) {
            assertThat(competitionsPage.isGivenRegionExpanded(indexNumberOfRegionExpanded)).isTrue();
        } else {
            assertThat(competitionsPage.isGivenRegionExpanded(indexNumberOfRegionExpanded)).isFalse();
        }
    }

    @And("^the competition on the '(.*)(st|nd|th)' position from the '(.*)(st|nd|th)' region is '(expanded|collapsed)'$")
    public void verifyGivenCompetitionIsExpanded(final int competitionListNumber, final String classyNumberEnding2, final int regionListNumber, final String classyNumberEnding, final String expandedOrCollapsed){

        if(expandedOrCollapsed.equals("expanded")) {
            assertThat(competitionsPage.isGivenCompetitionExpanded(regionListNumber, competitionListNumber)).isTrue();
        }else{
            assertThat(competitionsPage.isGivenCompetitionExpanded(regionListNumber, competitionListNumber)).isFalse();
        }
    }

    @And("^the market group on the '(.*)(st|nd|th)' position from the '(.*)(st|nd|th)' competition, '(.*)(st|nd|th)' region  is '(expanded|collapsed)'$")
    public void verifyGivenEventIsExpanded(final int eventNumber, final String numberEnding, final int competitionNumber, final String numberEnding2, final int regionNumber, final String numberEnding3, final String expandedOrCollapsed){
        if(expandedOrCollapsed.equals("expanded")) {
            assertThat(competitionsPage.isGivenMarketGroupExpanded(regionNumber, competitionNumber, eventNumber)).isTrue();
        }else{
            assertThat(competitionsPage.isGivenMarketGroupExpanded(regionNumber, competitionNumber, eventNumber)).isFalse();
        }
    }

    @And("^rest of regions are collapsed in '(matches|outrights)' tab$")
    public void onlyFirstCompetitionExpanded(final String matchesOrOutrights) {
        assertThat(competitionsPage.onlyFirstRegionExpanded(matchesOrOutrights)).isTrue();
    }

    @And("^user validate '(Matches|Outrights)' tab title$")
    public void verifyTabTitle(final String tab) {
        if (tab.equals("Matches")) {
            assertThat(competitionsPage.matchesTitle().contains("Matches")).isTrue();
        } else {
            assertThat(competitionsPage.outrightsTitle().contains("Outrights")).isTrue();
        }
    }

    @And("^ user verifies that regions appear for '(Matches|Outrights)' tab$")
    public void verifyRegionsAppear(final String tab) {
        if (tab.equals("Matches")) {
            assertThat(competitionsPage.matchesTitle().contains("Matches")).isTrue();
        } else {
            assertThat(competitionsPage.outrightsTitle().contains("Outrights")).isTrue();
        }
    }

    @And("^'(Matches Content|Outrights Content)' element is displayed in Competitions page$")
    public void verifyMatchesOrOutrightsContent(final String tab) {
        if (tab.equals("Matches Content")) {
            competitionsPage.isMatchesContentDisplayed();
        } else {
            competitionsPage.isOutrightsContentDisplayed();
        }
    }

    @And("^user clicks on the first available Selection from the expanded market$")
    public void clickOnFirstSelectionFromExpandedMarket() throws InterruptedException {
        competitionsPage.clickOnTheFirstAvailableSelection();
    }

    @And("^the region arrow is '(down|up)' for the '(.*)(st|nd|th)' region$")
//    @And("^the '(expand|collapse)' arrow of '(.*)(st|nd|th)' region is displayed$")
    public void isGivenArrowExpandedFromRegionDisplayed(final String expandOrCollapse, final int region, final String classyEnding) {

        if(expandOrCollapse.equals("down")){
            assertThat(competitionsPage.isGivenArrowExpandedFromRegionDisplayed(region)).isFalse();
        }else {
            assertThat(competitionsPage.isGivenArrowExpandedFromRegionDisplayed(region)).isTrue();
        }
    }

    @And("^the competition arrow is '(down|up)' for the '(.*)(st|nd|th)' competition from the '(.*)(st|nd|th)' region$")
    public void isGivenArrowExpandedFromCompetitionDisplayed(final String expandOrCollapse, final int competition, final String classyEnding, final int region, final String classyEnding2) {

        if(expandOrCollapse.equals("up")){
            assertThat(competitionsPage.isGivenArrowExpandedFromCompetitionDisplayed(region, competition)).isTrue();
        }else {
            assertThat(competitionsPage.isGivenArrowExpandedFromCompetitionDisplayed(region, competition)).isFalse();
        }
    }

    @And("^the '(expand|collapse)' arrow of '(.*)(st|nd|th)' Market group from '(.*)(st|nd|th)' competition '(.*)(st|nd|th)' region is displayed$")
    public void isGivenArrowExpandedFromMarketGroupDisplayed(final String expandOrCollapse, final int marketGroup, final String classyEnding, final int competition, final String classyEnding2, final int region, final String classyEnding3) {

        if(expandOrCollapse.equals("expand")){
            assertThat(competitionsPage.isGivenArrowExpandedFromMarketGroupDisplayed(region, competition, marketGroup)).isTrue();
        }else {
            assertThat(competitionsPage.isGivenArrowExpandedFromMarketGroupDisplayed(region, competition, marketGroup)).isFalse();
        }
    }

//    @And("^user verifies that '(expand|collapse)' arrow from the first Region '(is|is not)' displayed$")
//    public void verifyRegionArrowsDisplay(final String expandCollapse, final String isIsnot) {
//
//        if (expandCollapse.equals("expand")) {
//            if (isIsnot.equals("is")) {
//                assertThat(competitionsPage.regionArrowExpandDisplay()).isTrue();
//            } else {
//                assertThat(competitionsPage.regionArrowExpandDisplay()).isFalse();
//            }
//        } else {
//            if (isIsnot.equals("is")) {
//                assertThat(competitionsPage.regionArrowCollapseDisplay()).isTrue();
//            } else {
//                assertThat(competitionsPage.regionArrowCollapseDisplay()).isFalse();
//            }
//        }
//    }
//
//    @And("^user verifies that '(expanded|collapsed)' arrow from the first Competition '(is|is not)' displayed$")
//    public void verifyCompetitionArrowsDisplay(final String expandCollapse, final String isIsnot) {
//
//        if (expandCollapse.equals("expanded")) {
//            if (isIsnot.equals("is")) {
//                assertThat(competitionsPage.competitionArrowExpandDisplay()).isTrue();
//            } else {
//                assertThat(competitionsPage.competitionArrowExpandDisplay()).isFalse();
//            }
//        } else {
//            if (isIsnot.equals("is")) {
//                assertThat(competitionsPage.competitionArrowCollapseDisplay()).isTrue();
//            } else {
//                assertThat(competitionsPage.competitionArrowCollapseDisplay()).isFalse();
//            }
//        }
//    }

    @And("^user expands all the Regions$")
    public void userExpandsAllTheRegions() {
        competitionsPage.expandAllRegions();
    }

    @And("^user click on the first Region$")
    public void userClickOnTheFirstRegion() {
        competitionsPage.expandTheFirstRegion();
    }

    @And("^user click on the first Competition")
    public void userClickOnTheFirstCompetition() {
        competitionsPage.expandTheFirstCompetition();
    }

    @And("^multiple Regions are expanded at the same time$")
    public void verifyIfMultipleRegionsOpenAtSameTime() {
        assertThat(competitionsPage.areMultipleRegionsOpen()).isTrue();
    }

    @And("^And user verifies that all Events in the '(.*)(st|nd|th)' Market Group from the '(.*)(st|nd|th)' competition from '(.*)(st|nd|th)' region in outrights tab have Title$")
    public void verifyEventsTitleOnMatches(final int marketGroup, final String classyNumberEnding3, final int competition, final String classyNumberEnding2, final int region, final String classyNumberEnding){
        assertThat(competitionsPage.verifyEventsTitleOnOutrights(region, competition, marketGroup)).isTrue();
    }

    @And("^user verifies that all Events have Title$")
    public void verifyAllEventTitles() throws InterruptedException {
        assertThat(competitionsPage.verifyAllEventsTitle()).isTrue();
    }

    @And("^user click on the first event link$")
    public void clickOnFirstEvenLinkFromGivenRegionCompetition(){
        competitionsPage.clickOnFirstEventLink();
    }

//    @And("^user click on the first event link from the '(.*)(st|nd|th)' competition from '(.*)(st|nd|th)' region$")
//    public void clickOnFirstEvenLinkFromGivenRegionCompetition(final int competition, final String classyNumberEnding2, final int region, final String classyNumberEnding){
//        competitionsPage.clickOnFirstEvenLinkFromGivenRegionCompetition(region, competition);
//    }

    @And("^user verifies that is being taken to Event Page$")
    public void verifyEventPageRedirection() throws InterruptedException {
        assertThat(competitionsPage.verifyEventPageRedirection()).isTrue();
    }

    @And("^'(matches|outrights)' tab is highlighted$")
    public void matchesTabHighlighted(final String matchesOrOutrights) throws InterruptedException {
        if(matchesOrOutrights.equals("matches")) {
            assertThat(competitionsPage.isMatchesTabHighlightes()).isTrue();
        }else{
            assertThat(competitionsPage.isOutrightsTabHighlightes()).isTrue();
        }
    }



}
