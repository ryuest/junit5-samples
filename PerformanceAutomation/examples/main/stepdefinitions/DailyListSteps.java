package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.util.Msg;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Component
@Lazy
public class DailyListSteps extends MainSteps {
    private static String[] dailylistdays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};

    private static String[] alternateMarket = new String[]{"Match Betting","Both Teams To Score?", "Match Result and Both Teams To Score", "Match Result and Both Teams To Score", "Total Match Goals Over/Under 1.5 Goals",
         "Double Chance" };

         //TODO We could create a "MarketUtils" class and move it there


//chande  parameter for string confers to  int
    @And("^user navigates to '(\\d+)(?:st|nd|th)' option in the day filter$")
    public void userNavigatesToAnyOptionInTheDayFilter(int option) throws Throwable {
        dailyListPage.selectFilterFromDailyList(option);
    }


    @And("^the option '(\\d+)(?:st|nd|th)' in day filter is highlighted$")
    public void theOptionInDayFilterIsHighlighted(int option) throws Throwable {
        assertThat(dailyListPage.isFilterFromDailyListSelected(option)).isTrue();
    }


    @And("^the option '(Today|Future)' in day filter (is|is not) highlighted$")
    public void theOptionTodayInDayFilterIsHighlighted(String option, String displayed) throws Throwable {
        if(option.toLowerCase().contains("today")){
            if("is".equals(displayed))
                assertThat(dailyListPage.isFilterFromDailyListSelected(0)).isTrue();
            else
                assertThat(dailyListPage.isFilterFromDailyListSelected(0)).isFalse();
        }
        else {
            if("is".equals(displayed))
                assertThat(dailyListPage.isFilterFromDailyListSelected(1)).isTrue();
            else
                assertThat(dailyListPage.isFilterFromDailyListSelected(1)).isFalse();
        }

    }


    @And("^user navigates to '(Today|Future)' option in the day filter$")
    public void userSelectsTodayFromDayFilter(String option) throws Throwable {
        if(option.toLowerCase().contains("today")) {
            dailyListPage.selectFilterFromDailyList(0);
        }else{
            dailyListPage.selectFilterFromDailyList(1);
        }
    }


    @And("^user changes view filter to '(Time|Competition)'$")
    public void userChangesViewFilterToTime(String option) throws Throwable {
        if(option.toLowerCase().contains("time")){
            option="date";
        }
        if(genericSportsPage.isMobile()) {
            dailyListPage.expandViewByFilter();
        }
        dailyListPage.selectOptionFromViewByMenu(option.toLowerCase());
    }

    @Then("^correct options are available in '(.*)' day filter$")
    public void correctOptionsAreAvailableInFootballDayFilter(String sport) throws Throwable {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        List<String> Dailylistdays = Arrays.asList(dailylistdays);
        List<String> dailyList = dailyListPage.getDailyListFilterNames();
        if (sport.toLowerCase().contains("football")) {
            assertThat(dailyList.size()).isEqualTo(7);
            for (int i = 0; i < dailyList.size(); i++) {
                if (i == 0) {
                    assertThat(dailyList.get(i).contains("Today")).isTrue();
                } else {
                    assertThat(dailyList.get(i).contains(Dailylistdays.get(dayOfWeek))).as("dayOfWeek "+ dayOfWeek).isTrue();
                    dayOfWeek = dayOfWeek + 1;
                }
            }
        }
        else {
            assertThat(dailyList.size()).isEqualTo(2);
            assertThat(dailyList.get(0).contains("Today")).isTrue();
            assertThat(dailyList.get(1).contains("Future")).isTrue();
        }
    }


    @Then("^View By menu contains correct options$")
    public void viewByMenuContainsCorrectOptions() throws Throwable {
        if(genericSportsPage.isMobile()) {
            dailyListPage.expandViewByFilter();
        }
        assertThat(dailyListPage.isDisplayedTimeOnViewByMenu()).isTrue();
        assertThat(dailyListPage.isDisplayedCompetitionOnViewByMenu()).isTrue();
    }

    @And("^the option '(.*)' in View By menu (is|is not) highlighted$")
    public void theOptionCompetitionInViewByMenuIsHighlighted(String option, String displayed) throws Throwable {
        if(genericSportsPage.isMobile()) {
            dailyListPage.expandViewByFilter();
        }

        if(option.toLowerCase().contains("time")){
            option="date";
        }
        if("is".equals(displayed)) {
            dailyListPage.isOptionSelectedFromViewByMenu(option.toLowerCase());
            assertThat(dailyListPage.isOptionSelectedFromViewByMenu(option.toLowerCase())).isTrue();
        }
        else
            assertThat(dailyListPage.isOptionSelectedFromViewByMenu(option.toLowerCase())).isFalse();
    }

    @Then("^back button is not displayed$")
    public void backButtonIsNotDisplayed() throws Throwable {
        assertThat(dailyListPage.isBackButtonDisplayed()).isFalse();
    }

    @Then("^user navigates through all day filter options$")
    public void userNavigatesThroughAllDayFilterOptions() throws Throwable {
        for (int i = 0; i < dailyListPage.getDailyListFilterNames().size(); i++) {
            dailyListPage.selectFilterFromDailyList(i);
            assertThat(dailyListPage.isFilterFromDailyListSelected(i)).isTrue();
        }
    }

    @And("^the first option in alternate market menu is 'Both Teams to score'$")
    public void theFirstOptionInAlternateMarketMenuIsBothTeamsToScore() throws Throwable {
       assertThat(dailyListPage.getAlternateMarketsName().get(0).toLowerCase()).contains("Both Teams to score".toLowerCase());
    }

    @And("^user changes alternate market to '(.*)'$")
    public void userChangesAlternateMarketToBothTeamsToScore(String market) throws Throwable {
       dailyListPage.selectAlternateMarket(market);
       assertThat(dailyListPage.getSelectedAlternateMarket()).isEqualToIgnoringCase(market);

    }

    @Then("^alternate market menu contains correct options$")
    public void alternateMarketMenuContainsCorrectOptions() throws Throwable {
        List<String> AlternateMarket = Arrays.asList(alternateMarket);
        for (int i=0;i<dailyListPage.getAlternateMarketsName().size();i++){
            assertThat(dailyListPage.getAlternateMarketsName().get(i)).contains(AlternateMarket.get(i));
        }
    }

    @And("^the '(\\d+)(st|nd|th)' option in alternate market menu is '(.*)'$")
    public void theStOptionInAlternateMarketMenuIsMatchBetting(int arg0, String option1,String option) throws Throwable {
        arg0=arg0-1;
       assertThat(dailyListPage.getAlternateMarketsName().get(arg0)).contains(option);
    }

    @Then("^user navigates through all alternate markets$")
    public void userNavigatesThroughAllAlternateMarkets() throws Throwable {
        SoftAssertions softAssertion = new SoftAssertions();
        List<String> alternateMarkets = dailyListPage.getAlternateMarketsName();

        for (int i=0;i<alternateMarkets.size();i++) {
          dailyListPage.selectAlternateMarket(i);
           softAssertion.assertThat(alternateMarkets.get(i))
                   .isEqualToIgnoringCase(dailyListPage.getSelectedAlternateMarket());
        }
        softAssertion.assertAll();
    }

    @And("^alternate market menu (is|is not) displayed$")
    public void alternateMarketMenuIsNotAvailable(String display) throws Throwable {
        if(Msg.IS.equals(display))
            assertThat(dailyListPage.isAlternateMarketMenuDisplayed()).isTrue();
        else
            assertThat(dailyListPage.isAlternateMarketMenuDisplayed()).isFalse();
    }

}
