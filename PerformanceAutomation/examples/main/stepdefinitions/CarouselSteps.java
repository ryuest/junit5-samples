package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.util.Msg;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class CarouselSteps extends MainSteps {

    @And("^user clicks on '(Daily List|In-Play|Coupons|Competitions|Stats|Meetings|Ante Post|Top Bets|Specials|Next Off)' icon in the carousel$")
    public void userClicksOnIconInTheCarousel(final String option) throws Throwable {
        switch (option.toLowerCase()) {
            case Msg.DAILY_LIST:
                genericSportsPage.getCarouselComponent().clickOnDailyListIcon();
                break;
            case Msg.IN_PLAY:
                genericSportsPage.getCarouselComponent().clickOnInPlaySection();
                break;
            case Msg.COUPONS:
                genericSportsPage.getCarouselComponent().clickOnCouponsIcon();
                break;
            case Msg.COMPETITIONS:
                genericSportsPage.getCarouselComponent().clickOnCompetitionsIcon();
                break;
            case Msg.STATS:
                //TODO
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.MEETINGS:
                genericSportsPage.getCarouselComponent().clickOnMeetingsIcon();
                break;
            case Msg.ANTE_POST:
                genericSportsPage.getCarouselComponent().clickOnAntePostIcon();
                break;
            case Msg.TOP_BETS:
                genericSportsPage.getCarouselComponent().clickOnTopBetsIcon();
                break;
            case Msg.SPECIALS:
                genericSportsPage.getCarouselComponent().clickOnSpecialIcon();
                break;
            case Msg.NEXT_OFF:
                genericSportsPage.getCarouselComponent().clickOnNextOffIcon();
                break;

        }
    }

   

}
