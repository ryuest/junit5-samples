package com.whgtf.sportsbook.main.stepdefinitions;


import com.whgtf.sportsbook.main.spring.PropertyReader;
import com.whgtf.sportsbook.obfeed.BackOfficeOxifeed;
import com.whgtf.sportsbook.pom.common.components.interfaces.*;
import com.whgtf.sportsbook.pom.common.exceptions.KnownIssueException;
import com.whgtf.sportsbook.pom.common.pages.interfaces.*;
import com.whgtf.sportsbook.pom.utils.JsonReader;
import com.whgtf.sportsbook.pom.utils.PropertyPomReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class MainSteps {

    //// Pages Definition

    @Autowired
    @Lazy
    protected GenericSportsPage genericSportsPage;

    @Autowired
    @Lazy
    protected HomePage homePage;

    @Autowired
    @Lazy
    protected FeaturedPage featuredPage;

    @Autowired
    @Lazy
    protected EventPage eventPage;

    @Autowired
    @Lazy
    protected VirtualsPage virtualsPage;

    @Autowired
    @Lazy
    protected CarouselComponent carouselComponent;

    @Autowired
    @Lazy
    protected TopBetsPage topBetsPage;

    @Autowired
    @Lazy
    protected MeetingsPage meetingsPage;

    @Autowired
    @Lazy
    protected RacecardPage racecardPage;

    @Autowired
    @Lazy
    protected AntePostAllRacesPage antePostAllRacesPage;

    @Autowired
    @Lazy
    protected SpecialRacingPage specialRacingPage;

    @Autowired
    @Lazy
    protected AntePostPage antePostPage;

    @Autowired
    @Lazy
    protected GamePage gamePage;

    @Autowired
    @Lazy
    protected CompetitionsPage competitionsPage;

    @Autowired
    @Lazy
    protected CouponsPage couponsPage;

    @Autowired
    @Lazy
    protected JoinFormComponent joinFormComponent;

    @Autowired
    @Lazy
    protected DepositFormComponent depositFormComponent;

    @Autowired
    @Lazy
    protected HeaderComponent headerComponent;

    //// Components Definition
    @Autowired
    @Lazy
    protected NextOffComponent nextOffComponent;

    @Autowired
    @Lazy
    protected SearchComponent searchComponent;

    @Autowired
    @Lazy
    protected TabbedHighlightsComponent tabbedHighlightsComponent;

    @Autowired
    @Lazy
    LoginComponent loginComponent;

    @Autowired
    @Lazy
    protected QuickLinksComponent quickLinksComponent;

    @Autowired
    @Lazy
    protected BottomBarComponent bottomBarComponent;

    @Autowired
    @Lazy
    protected BetSlipComponent betSlipComponent;

    @Autowired
    @Lazy
    protected TopBetsComponent topBetsComponent;

    ///// Shared Step Definitions

    @Autowired
    @Lazy
    protected CarouselSteps carouselSteps;

    @Autowired
    @Lazy
    protected DailyListPage dailyListPage;

    @Autowired
    @Lazy
    protected LeftFutureRacesNavigationComponent LeftFutureRacesNavigationComponent;

    @Autowired
    @Lazy
    protected MeetingsNavigationComponent meetingsNavigationComponent;


    ///// Properties Loader

    @Autowired
    @Lazy
    protected PropertyReader properties;

    @Autowired
    @Lazy
    protected JsonReader jsonReader;

    @Autowired
    @Lazy
    protected PropertyPomReader propertyPomReader;

    @Autowired
    @Lazy
    TopGamesComponent topGamesComponent;

    @Autowired
    @Lazy
    SportsMenuComponent sportsMenuComponent;

    @Autowired
    @Lazy
    AllInPlayPage allInPlayPage;

    @Autowired
    @Lazy
    InPlayPage inPlayPage;


    BackOfficeOxifeed backOfficeOxifeed = BackOfficeOxifeed.getInstance();

    protected void doSearch(String search) {
        if (genericSportsPage.isMobile())
            genericSportsPage.getBottonBar().clickOnSearchMenu().search(search);
    }

    void throwKnownIssueExceptionIfNeeded(Boolean exceptionCondition, String message) {
        if(exceptionCondition)
            throw new KnownIssueException(message);

    }
}
