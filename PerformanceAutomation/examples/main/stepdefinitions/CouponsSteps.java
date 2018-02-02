package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



@Component
@Lazy
public class CouponsSteps  extends MainSteps {

    @And("^user verifies that the structure of the events is displayed correctly$")
    public void verifyEventsStructure() throws Throwable {
        couponsPage.verifyCouponsEventStructures();
    }

    @And("^user clicks on the '(.*)(st|nd|rd|th)' coupon from the list$")
    public void clickOnGivenCoupon(final int listNumber, final String termination) throws Throwable {
        ScenarioContext.saveData("coupon_id", couponsPage.getOB_CPfromGivenCoupon(listNumber));
        ScenarioContext.saveData("coupon_title", couponsPage.getTitlefromGivenCoupon(listNumber));
        couponsPage.clickOnGivenCouponList(listNumber);
    }

    @And("^user clicks on the Coupon with the ID '(.*)' from the list$")
    public void clickOnGivenCouponByID(final String couponId) throws Throwable {
        ScenarioContext.saveData("coupon_id", couponId);
        ScenarioContext.saveData("coupon_title", couponsPage.getTitlefromGivenCouponID(couponId));
        couponsPage.clickOnCouponByCouponID(couponId);
    }

    @And("^user clicks on the Event with the ID '(.*)' from the list$")
    public void clickOnGivenEventByID(final String eventId) throws Throwable {
        couponsPage.clickOnGivenEventByID(eventId);
    }

    @And("^the amount of events displayed is '(.*)'$")
    public void verifyAmountOfEventsDisplayed(final int events) throws Throwable {
        assertThat(couponsPage.getAmountOfEventsDisplayed()).isEqualTo(events);
    }

    @And("^correct components are displayed in coupon events list page$")
    public void couponEventsListPageComponentsDisplay() throws Throwable {
        assertThat((String) ScenarioContext.getSavedData("coupon_id")).isEqualTo(couponsPage.getSelectedCouponOB_CP());
        assertThat((String) ScenarioContext.getSavedData("coupon_title")).isEqualTo(couponsPage.getSelectedCouponPageTitle());
        assertThat(couponsPage.areSelectionsDisplayed()).isTrue();
    }

    @And("^Coupons menu is displayed in mobile view$")
    public void verifyCouponsMenuInMobile() throws Throwable {
        assertThat(couponsPage.verifyCouponsMenuInMobile()).isTrue();
    }

    @And("^Coupons content is displayed in mobile view$")
    public void verifyCouponsContentInMobile() throws Throwable {
        assertThat(couponsPage.verifyCouponsContentInMobile()).isTrue();
    }


    @And("^the Coupons Menu Page 'is' displayed$")
    public void verifyCouponsPageDisplay() throws Throwable {
        assertThat(couponsPage.isCouponCanvasDisplayed()).isTrue();
        assertThat(couponsPage.isCouponListDisplayed()).isTrue();
    }

    @And("^the Coupons Content page 'is' displayed$")
    public void verifyCouponsCompetitionsPageDisplay() throws Throwable {
        assertThat(couponsPage.isCompetitionsMainContentDisplayed()).isTrue();
        assertThat(couponsPage.areCompetitionsDisplayed()).isTrue();
        assertThat(couponsPage.isCompetitionsTitleDisplayed()).isTrue();
    }

    @And("^the View By filter '(is|is not)' displayed$")
    public void isViewByDisplayed(final String isOrIsnot) throws Throwable {
        if ("is".equals(isOrIsnot)) {
            assertThat(couponsPage.isViewByDisplayed()).isTrue();
        } else {
            throwKnownIssueExceptionIfNeeded(couponsPage.isViewByDisplayed(), "TDRD-378: The View By is being displayed.");
            assertThat(couponsPage.isViewByDisplayed()).isFalse();
        }
    }

    @And("^the View By filter contains correct options$")
    public void viewByDisplayCorrectOptions() throws Throwable {
        assertThat(couponsPage.areViewByOptionsDisplayed()).isTrue();
    }

    @And("^the option '(Competition|Time)' in view by menu is highlighted$")
    public void isGivenViewByOptionHighlighted(final String viewBy) throws Throwable {
        if ("Competition".equals(viewBy)) {
            assertThat(couponsPage.getViewBySelected()).isEqualToIgnoringCase("Competition");
        } else {
            assertThat(couponsPage.getViewBySelected()).isEqualToIgnoringCase("Time");
        }
    }

    @And("^user click on '(Competition|Time)' in view by menu option$")
    public void clickOnGivenViewByOption(final String viewBy) throws Throwable {
        if ("Competition".equals(viewBy)) {
            couponsPage.clickOnViewByCompetition();
        } else {
            couponsPage.clickOnViewByTime();
        }
    }

    @And("^the Events are order by '(Competition|Time)'$")
    public void areEventsOrderedBy(final String orderBy) throws Throwable {

        SoftAssertions softAssertion = new SoftAssertions();

        if ("Competition".equals(orderBy)) {

            // TODO: For this to be completed we have 2 options:
            // TODO: Option A: Verify HARDCODED DATA.
            // TODO: Option B: Do it properly by, verify on BackOffice the "DispOrder" field.

        } else {

            // Here we set which is going to be the format that the date will be displayed on the site.
            SimpleDateFormat dt = new SimpleDateFormat("dd MMM HH:mm");

            // Here we get the List of Dates displayed from all events in String format.
            List<String> eventTimes = couponsPage.allEventTimesDisplayed();

            String current = eventTimes.get(0);
            Date previousDate = new Date();
            boolean isLive = false;

            if ("Live".equalsIgnoreCase(current))
                isLive = true;
            else {
                previousDate = dt.parse(current);
            }

            for (int j = 1; j < eventTimes.size(); j++) {

                if (isLive) {
                    if (!("Live").equalsIgnoreCase(eventTimes.get(j)))
                        isLive = false;
                } else {
                    softAssertion.assertThat(previousDate).isBefore(eventTimes.get(j));
                    previousDate = dt.parse(eventTimes.get(j));
                    isLive = false;
                }
            }

        }
    }

    @When("^user clicks '(.*)(st|nd|rd|th)' Coupon page$")
    public void clickOnGivenCouponNumber(final int couponNumber, final String classyEnding) {
        couponsPage.clickOnCouponByIndex(couponNumber);
    }


}
