package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.util.Msg;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.NotImplementedException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LeftBarSteps extends MainSteps {


    @And("^option '(Featured|Daily List|In-Play|Coupons|Competitions|Search|Results|Stats|Meetings|Ante Post|Top Bets|Specials|Results|Horse Racing - Flat|Horse Racing - National Hunt|Football|Greyhounds - Flat|Greyhounds - Hurdles|Motor racing|Speedway|Cycling)' is highlighted on sports menu$")
    public void isOptionHighlightedInSportsMenu(final String option) throws Throwable {

        switch (option.toLowerCase()) {
            case Msg.FEATURED:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.DAILY_LIST:
                Assertions.assertThat(sportsMenuComponent.isDailyListSelected()).isTrue();
            case Msg.IN_PLAY:
                Assertions.assertThat(sportsMenuComponent.isInPlaySelected()).isTrue();
            case Msg.COUPONS:
                Assertions.assertThat(sportsMenuComponent.isCouponsSelected()).isTrue();
                break;
            case Msg.COMPETITIONS:
                Assertions.assertThat(sportsMenuComponent.isCompetitionsSelected()).isTrue();
            case Msg.SEARCH:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.RESULTS:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.STATS:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.MEETINGS:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.ANTE_POST:
                Assertions.assertThat(sportsMenuComponent.isFutureRacesSelected()).isTrue();
            case Msg.TOP_BETS:
                Assertions.assertThat(sportsMenuComponent.isTopBetsSelected()).isTrue();
            case Msg.SPECIALS:
                Assertions.assertThat(sportsMenuComponent.isSpecialsSelected()).isTrue();
            case Msg.HORSE_RACING_FLAT:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.HORSE_RACING_NATIONAL_HUNT:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.FOOTBALL:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.GREYHOUNDS_FLAT:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.GREYHOUNDS_HURDLES:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.MOTOR_RACING:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.CYCLING:
                throw new NotImplementedException(option + " not implemented yet");
            case Msg.SPEEDWAY:
                throw new NotImplementedException(option + " not implemented yet");
        }
    }

}
