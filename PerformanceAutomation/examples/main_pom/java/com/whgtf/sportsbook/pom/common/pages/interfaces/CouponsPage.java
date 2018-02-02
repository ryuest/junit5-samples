package com.whgtf.sportsbook.pom.common.pages.interfaces;

import java.util.List;

/**
 * Created by javierg on 02/08/2016.
 */
public interface CouponsPage extends AbstractSportsPage {

    boolean verifyCouponsEventStructures();

    void clickOnGivenCouponList(int listNumber);

    void clickOnGivenEventByID(String eventId);

    boolean areSelectionsDisplayed();

    boolean correctComponentsOnCouponsPageDesktop();

    boolean verifyCouponsMenuInMobile();

    boolean verifyCouponsContentInMobile();

    boolean firstCouponIsSelectedOnDesktop();

    String getOB_CPfromGivenCoupon(int input);

    String getTitlefromGivenCoupon(int input);

    String getTitlefromGivenCouponID(String input);

    String getSelectedCouponOB_CP();

    String getSelectedCouponPageTitle();

    String clickOnGivenCollection(int listNumber);


    boolean isCouponCanvasDisplayed();

    boolean isCouponListDisplayed();


    boolean isCouponTitleDisplayed();

    boolean isCompetitionsMainContentDisplayed();

    boolean areCompetitionsDisplayed();


    boolean isCompetitionsTitleDisplayed();

    boolean isViewByDisplayed();

    boolean areViewByOptionsDisplayed();

    String getViewBySelected();

    void clickOnViewByTime();

    void clickOnViewByCompetition();

    void clickOnCouponByCouponID(String couponId);

    int getAmountOfEventsDisplayed();

    List<String> allEventTimesDisplayed();

    /**
     * Clicks a given coupon by index
     *
     * @param number The coupon index to click
     */
    void clickOnCouponByIndex(int number);
}
