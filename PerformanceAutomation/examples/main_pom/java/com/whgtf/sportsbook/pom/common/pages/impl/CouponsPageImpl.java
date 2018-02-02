package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.CouponsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Component
@Lazy
public class CouponsPageImpl extends AbstractSportsPageObject implements CouponsPage {

    private static final By COUPONS_CONTENT = By.id("highlights-coupons");

    private static final By COUPON_LIST = By.cssSelector("#highlights-coupons section a");

    private static final By COUPON_TITLE = By.cssSelector("#coupon-detail .header-panel__title");

    private static final By DATA_COUPON_ID = By.cssSelector(".event-container.scrollable.clickable-selections.coupon-container");

    private static final By COUPON_MENU = By.cssSelector(".betgroup");

    private static final By COUPON_CONTENT = By.cssSelector("section[data-coupon-id^='OB_CP']");

    private static final By MOBILE_COUPON_MENU = By.cssSelector("#highlights-coupons");

    private static final By ALL_EVENTS_DISPLAYED = By.cssSelector("div[id^='OB_EV']");

    private static final By ALL_EVENTS_SELECTIONS = By.cssSelector(".btmarket__selection");

    private static final By ALL_EVENTS_TIMES = By.cssSelector(".eventStartTime.localisable");

    private static final By ALL_EVENTS_TITLE = By.cssSelector(".btmarket__link-name");

    private static final By ALL_SELECTIONS_DISPLAYED = By.cssSelector("button[id^='OB_OU']");

    private static final By COUPON_CANVAS = By.id("coupons_canvas");

    private static final By COUPONS_PAGE_TITLE = By.cssSelector("");

    private static final By COUPON = By.cssSelector(".sportmenu__link");

    // ------------------------------ NEW -----------------------------------

    // Page 2 - Market Groups
    private static final By COMPETITIONS_CANVAS = By.cssSelector(".event-container.scrollable.clickable-selections.coupon-container");

    private static final By COMPETITIONS_LIST = By.cssSelector("article[id*='comp-OB_TY']");

    private static final By COMPETITIONS_TITLE = By.cssSelector("#coupon-detail .header-panel__title");

    private static final By VIEW_BY = By.cssSelector(".filterlist");

    private static final By VIEW_BY_EXPANDED = By.cssSelector(".filterlist__options.-expanded");

    private static final By VIEW_BY_SELECTED = By.cssSelector(".filterlist__options .filterlist__options__button.-active");

    private static final By VIEW_BY_TIME = By.cssSelector(".filterlist__options .filterlist__options__button.-date");

    private static final By VIEW_BY_COMPETITION = By.cssSelector(".filterlist__options .filterlist__options__button.-competition");


    private static String COUPON_BY_ID = "a[data-coupon-id*='%s']";

    private static String EVENT_BY_ID = "div[id='OB_EV%s']";


    @Override
    public boolean isDisplayed(){
        return isCouponCanvasDisplayed();
    }

    @Override
    public void clickOnGivenCouponList(int listNumber) {
        findElements(COUPON_LIST).get(listNumber - 1).click();
    }

    @Override
    public boolean verifyCouponsEventStructures() {

        // First we need to know how many Competitions are displayed
        for (WebElement competition:findElements(COMPETITIONS_LIST)) {

            // We get the structure of the first one, how many columns does it have
            int numberOfSelectionsPerEvent = competition.findElements(ALL_EVENTS_DISPLAYED).get(0).findElements(ALL_EVENTS_SELECTIONS).size();

            // Here we get all the Events displayed on Page
            List<WebElement> eventsDisplayed = competition.findElements(ALL_EVENTS_DISPLAYED);

            for (WebElement event:eventsDisplayed) {
                if(!isElementDisplayed(event.findElement(By.cssSelector(".eventStartTime.localisable")))){
                    return false;
                }
                if(!isElementDisplayed(event.findElement(By.cssSelector("a[title]")))){
                    return false;
                }
                if(event.findElements(ALL_EVENTS_SELECTIONS).size() != numberOfSelectionsPerEvent){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getOB_CPfromGivenCoupon(int input){
        return findElements(COUPON_LIST).get(input - 1).getAttribute("data-coupon-id");
    }

    @Override
    public String getTitlefromGivenCoupon(int input){
        return findElements(COUPON_LIST).get(input - 1).findElement(By.xpath(".//span[@class='inner']")).getText();
    }

    @Override
    public String getTitlefromGivenCouponID(String input){
        return find(By.cssSelector(String.format(COUPON_BY_ID,input))).findElement(By.xpath("./span")).getText();
    }

    @Override
    public String getSelectedCouponOB_CP(){
        return find(DATA_COUPON_ID).getAttribute("data-coupon-id");
    }

    @Override
    public String getSelectedCouponPageTitle(){
        return find(COUPON_TITLE).getText();
    }

    @Override
    public boolean areSelectionsDisplayed() {
        boolean couponsHaveSelections = false;
        if(findElements(ALL_SELECTIONS_DISPLAYED).size() > 0){
            couponsHaveSelections = true;
        }
        return couponsHaveSelections;
    }

    @Override
    public boolean firstCouponIsSelectedOnDesktop() {

        boolean firstCouponSelected = findElements(COUPON_LIST).get(0).findElement(By.xpath("./a")).getAttribute("class").contains("active");
        boolean titleMatchesFirstCouponSelected = findElements(COUPON_LIST).get(0).getText().equals(find(COUPON_TITLE).getText());

        return firstCouponSelected && titleMatchesFirstCouponSelected;
    }

    @Override
    public boolean correctComponentsOnCouponsPageDesktop(){

        boolean titleVerification;
        boolean couponsMenu;
        boolean couponsSection;

        try{
            find(COUPON_TITLE);
            titleVerification = true;
        }catch (NoSuchElementException e){
            titleVerification = false;
        }

        try{
            find(COUPON_MENU);
            couponsMenu = true;
        }catch (NoSuchElementException e){
            couponsMenu = false;
        }

        try{
            find(COUPON_CONTENT);
            couponsSection = true;
        }catch (NoSuchElementException e){
            couponsSection = false;
        }


        return titleVerification && couponsMenu && couponsSection;
    }

    @Override
    public boolean verifyCouponsMenuInMobile(){
        return find(MOBILE_COUPON_MENU).isDisplayed();
    }

    @Override
    public boolean verifyCouponsContentInMobile(){
        return find(COUPON_CONTENT).isDisplayed();
    }

    // ------------------------------ NEW -----------------------------------

    // Coupon Page Display Verifications

    @Override
    public boolean isCouponCanvasDisplayed() {
        return isElementDisplayed(COUPON_CANVAS);
    }

    @Override
    public boolean isCouponListDisplayed() {
        return isElementDisplayed(COUPONS_CONTENT);
    }

    @Override
    public boolean isCouponTitleDisplayed() {
        return isElementDisplayed(COUPONS_PAGE_TITLE);
    }


    // Competitions Page Display Verifications

    @Override
    public boolean isCompetitionsMainContentDisplayed() {
        return isElementDisplayed(COUPON_MENU);
    }

    @Override
    public boolean areCompetitionsDisplayed() {
        return isElementDisplayed(COMPETITIONS_LIST);
    }

    @Override
    public boolean isCompetitionsTitleDisplayed() {
        return isElementDisplayed(COMPETITIONS_TITLE);
    }

    // ------------------------------ NEW -----------------------------------

    @Override
    public String clickOnGivenCollection(int listNumber) {
        String result = findElements(COMPETITIONS_LIST).get(listNumber - 1).getText();
        findElements(COMPETITIONS_LIST).get(listNumber - 1).click();
        return result;
    }

    @Override
    public boolean isViewByDisplayed() {
        return isElementDisplayed(VIEW_BY);
    }

    private boolean isViewByExpanded() {
        return isElementDisplayed(VIEW_BY_EXPANDED);
    }

    @Override
    public boolean areViewByOptionsDisplayed() {
        waitElementToBeVisible((find(VIEW_BY)),10);
        find(VIEW_BY).click();
        return isElementDisplayed(VIEW_BY_TIME) && isElementDisplayed(VIEW_BY_COMPETITION);
    }

    @Override
    public String getViewBySelected() {
        waitElementToBeVisible((find(VIEW_BY)),10);
        scrollToTheTop();
        if (!isViewByExpanded()) {
            click(VIEW_BY);
        }
        sleep(2000);
        return find(VIEW_BY_SELECTED).getText();
    }

    @Override
    public void clickOnViewByTime() {
        waitElementToBeVisible((find(VIEW_BY)),10);
        scrollToTheTop();
        click(VIEW_BY);
        click(VIEW_BY_TIME);
    }

    @Override
    public void clickOnViewByCompetition() {
        waitElementToBeVisible((find(VIEW_BY)),10);
        scrollToTheTop();
        click(VIEW_BY);
        click(VIEW_BY_COMPETITION);
    }

    @Override
    public void clickOnCouponByCouponID(String couponId) {
        find(By.cssSelector(String.format(COUPON_BY_ID, couponId))).click();
    }

    @Override
    public int getAmountOfEventsDisplayed() {
        return findElements(ALL_EVENTS_DISPLAYED).size();
    }

    @Override
    public void clickOnGivenEventByID(String eventId){
        find(By.cssSelector(String.format(EVENT_BY_ID, eventId))).click();
    }

    @Override
    public List<String> allEventTimesDisplayed(){
        List<WebElement> eventTimes = findElements(ALL_EVENTS_TIMES);
        List<String> result = new ArrayList<>();
        for (WebElement element:eventTimes) {
            if (!element.getText().equals(""))
            result.add(element.getText());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnCouponByIndex(int number) {
        List<WebElement> couponList = findElements(COUPON);
        couponList.get(number - 1).click();
    }

}
