package com.whgtf.sportsbook.pom.common.pages.interfaces;


public interface CompetitionsPage extends AbstractSportsPage {

    String getCompetitionsTitle();

    String matchesTitle();

    String outrightsTitle();

    boolean isMatchesContentDisplayed();

    boolean isOutrightsContentDisplayed();

    boolean areRegionsDisplayed(String matchesOrOutrights);

    boolean isGivenRegionExpanded(int regionListNumber);

    boolean isGivenCompetitionExpanded(int regionListNumber, int competitionListNumber);

    boolean isGivenMarketGroupExpanded(int region, int competition, int event);

    boolean onlyFirstRegionExpanded(String matchesOrOutrights);

    boolean onlyFirstCompetitionExpanded(String matchesOrOutrights);

    boolean regionArrowExpandDisplay();

    boolean regionArrowCollapseDisplay();

    boolean competitionArrowExpandDisplay();

    boolean competitionArrowCollapseDisplay();

    boolean areMultipleRegionsOpen();

    boolean verifyAllMarketsTitle();

    boolean verifyAllEventsTime();

    boolean verifyAllEventsTitle();

    boolean verifyAllEventsSelections();

    boolean verifyEventsTitleOnMatches(int region, int competition);

    boolean verifyEventsTitleOnOutrights(int region, int competition, int marketGroup);

    boolean isGivenArrowExpandedFromRegionDisplayed(int region);

    boolean isGivenArrowExpandedFromCompetitionDisplayed(int region, int competition);

    boolean isGivenArrowExpandedFromMarketGroupDisplayed(int region, int competition, int marketGroup);

    boolean doesGivenCompetitionHaveEvents(int region, int competition);

    /**
     * This method will check on all Events from the given Region and Competition if these have: Time, Title and Selections.
     *
     * @param region the index of the region
     * @param competition the index of the competition
     * @return true if the events have the right content
     */
    boolean verifySelectionComponentsFromGivenCompetition(int region, int competition);

    boolean isMatchesTabHighlightes();

    boolean isOutrightsTabHighlightes();

    boolean verifyEventPageRedirection();

    void clickOnOutrights();

    void clickOnMatches();

    void expandAllRegions();

    void expandTheFirstRegion();

    void expandTheFirstCompetition();

    void clickGivenRegion (int regionNumber);

    void clickGivenCompetition(int regionNumber, int competitionNumber);

    void clickGivenMarketGroup(int regionNumber, int competitionNumber, int eventNumber);

    void clickOnFirstEventLink();

    boolean isCompetitionListDisplayed();

}
