package com.whgtf.sportsbook.pom.common.pages.impl;

import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.DailyListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class DailyListPageImpl extends AbstractSportsPageObject implements DailyListPage {

    private static final By TODAY_LINK = By.id("filterSelectionToday");

    private static final By MONDAY_LINK = By.id("filterSelectionMonday");

    private static final By TUESDAY_LINK = By.id("filterSelectionTuesday");

    private static final By WEDNESDAY_LINK = By.id("filterSelectionWednesday");

    private static final By THURSDAY_LINK = By.id("filterSelectionThursday");

    private static final By FRIDAY_LINK = By.id("filterSelectionFriday");

    private static final By SATURDAY_LINK = By.id("filterSelectionSaturday");

    private static final By SUNDAY_LINK = By.id("filterSelectionSunday");

    private static final By FUTURE_LINK = By.id("filterSelectionFuture");

    private static final String DAILY_LIST_FILTER_OPTION_PARTIAL_ID = "filterSelection";

    private static final String ACTIVE_LINK = "active";

    private static final By DAILY_LIST_SCROLLER = By.id("swiperNavigationList");

    private static String VIEW_BY_FILTER_LIST_BUTTON = ".filterlist__options__button.-%s";

    private static final By VIEW_BY_FILTER_LIST = By.cssSelector(".icon-sort-icon");

    private static final By VIEW_BY_FILTER_IS_EXPANDED = By.cssSelector(".header-panel__toolbar-inner aside");

    private static final By VIEW_BY_FILTER_EXPANDED = By.cssSelector(".filterlist__options.-expanded");

    private static final By ALTERNATE_MARKET_GROUP_DROPDOWN = By.id("market-group-dropdown");

    private static String ALTERNATE_MARKET_GROUP_DROPDOWN_LABEL = ".dropdown__label";

    private static By ALTERNATE_MARKET_GROUP_DROPDOWN_SELECTION = By.cssSelector(".dropdown__select");

    private static String ALTERNATE_MARKET_GROUP_DROPDOWN_OPTION = "dropdown__option";

    private static By ALTERNATE_MARKET_ACTIVE_DROPDOWN_OPTION = By.cssSelector(".dropdown__option--active");

    private static By DROPDOWN_SELECTOR = By.cssSelector("#market-group-dropdown input");

    public DailyListPageImpl() {
        List<WebElement> elements = new ArrayList<>();
    }

    /**
     * Gets a list of WebElements representing  the daily filters
     *
     * @return a list of WebElements representing the day filters.
     */
    private List<WebElement> getDailyListFilters() {
        return find(DAILY_LIST_SCROLLER).findElements(By.tagName("a"));
    }

    @Override
    public List<String> getDailyListFilterNames() {
        List<String> dailyList = new ArrayList<>();
        for (WebElement element : getDailyListFilters()) {
            if (element.isDisplayed()) {
                dailyList.add(element.getAttribute("data-filter"));
            }
        }

        return dailyList;
    }

    @Override
    public void selectFilterFromDailyList(int option) {
        getDailyListFilters().get(option).click();
        sleep(1000);
        waitSportsbook();
    }

    @Override
    public boolean isFilterFromDailyListSelected(String option) {
        return find(By.id(DAILY_LIST_FILTER_OPTION_PARTIAL_ID + option)).getAttribute("class").contains(ACTIVE_LINK);
    }

    @Override
    public boolean isFilterFromDailyListSelected(int option) {
        return getDailyListFilters().get(option).getAttribute("class").contains(ACTIVE_LINK);
    }

    @Override
    public void clickOnTodayLink() {
        find(TODAY_LINK).click();
    }

    @Override
    public boolean isTodayLinkSelected() {
        String color = find(TODAY_LINK).getCssValue("border-bottom");
        color = color.substring(color.indexOf("("));
        Color borderBottom = Color.fromString("rgb" + color);
        Color colorObtained = Color.fromString(find(TODAY_LINK).getCssValue("color"));
        Color expecterColor = Color.fromString("#248cb3");
        return (find(TODAY_LINK).getAttribute("class").contains(ACTIVE_LINK)) &&
                (borderBottom).equals(expecterColor) &&
                (colorObtained).equals(expecterColor);
    }

    @Override
    public void clickOnMondayLink() {
        find(TODAY_LINK).click();
    }

    @Override
    public boolean isMondayLinkSelected() {
        return find(MONDAY_LINK).getAttribute("class").contains(ACTIVE_LINK);
    }

    @Override
    public void clickOnTuesdayLink() {
        find(TODAY_LINK).click();
    }

    @Override
    public boolean isTuesdayLinkSelected() {
        return find(TUESDAY_LINK).getAttribute("class").contains(ACTIVE_LINK);
    }

    @Override
    public void clickOnWednesdayLink() {
        find(TODAY_LINK).click();
    }

    @Override
    public boolean isWednesdayLinkSelected() {
        return find(WEDNESDAY_LINK).getAttribute("class").contains(ACTIVE_LINK);
    }

    @Override
    public void clickOnThursdayLink() {
        find(TODAY_LINK).click();
    }

    @Override
    public boolean isThursdayLinkSelected() {
        return find(THURSDAY_LINK).getAttribute("class").contains(ACTIVE_LINK);
    }

    @Override
    public void clickOnFridayLink() {
        find(TODAY_LINK).click();
    }

    @Override
    public boolean isFridayLinkSelected() {
        return find(FRIDAY_LINK).getAttribute("class").contains(ACTIVE_LINK);
    }

    @Override
    public void clickOnSaturdayLink() {
        find(TODAY_LINK).click();
    }

    @Override
    public boolean isSaturdayLinkSelected() {
        return find(SATURDAY_LINK).getAttribute("class").contains(ACTIVE_LINK);
    }

    @Override
    public void clickOnSundayLink() {
        find(TODAY_LINK).click();
    }

    @Override
    public boolean isSundayLinkSelected() {
        return find(SUNDAY_LINK).getAttribute("class").contains(ACTIVE_LINK);
    }

    @Override
    public void clickOnFutureLink() {
        find(FUTURE_LINK).click();
    }

    @Override
    public boolean isFutureLinkSelected() {
        String color = find(FUTURE_LINK).getCssValue("border-bottom");
        color = color.substring(color.indexOf("("));
        Color borderBottom = Color.fromString("rgb" + color);
        Color colorObtained = Color.fromString(find(FUTURE_LINK).getCssValue("color"));
        Color expecterColor = Color.fromString("#248cb3");
        return find(FUTURE_LINK).getAttribute("class").contains(ACTIVE_LINK) &&
                borderBottom.equals(expecterColor) &&
                        (colorObtained).equals(expecterColor);
    }

    @Override
    public boolean dayFilterScrollerIsDisplay() {
        return find(DAILY_LIST_SCROLLER).isDisplayed() && !getDailyListFilters().isEmpty();
    }

    @Override
    public void selectOptionFromViewByMenu(String viewByOption) {
        click(By.cssSelector(String.format(VIEW_BY_FILTER_LIST_BUTTON,viewByOption)));
    }

    @Override
    public boolean isOptionSelectedFromViewByMenu(String viewByOption) {
        boolean colorValidation;
        if(isMobile()){
            colorValidation = isGivenElementBackgroundColorThis(find(By.cssSelector(String.format(VIEW_BY_FILTER_LIST_BUTTON, viewByOption))), "#248cb3");
        }else{
            colorValidation = isGivenElementColorThis(find(By.cssSelector(String.format(VIEW_BY_FILTER_LIST_BUTTON, viewByOption))).findElement(By.xpath(".//span")), "#248cb3");
        }
        return find(By.cssSelector(String.format(VIEW_BY_FILTER_LIST_BUTTON, viewByOption))).getAttribute("class").contains("active") && colorValidation;
    }

    @Override
    public void expandViewByFilter() {
        scrollToTheTop();
        if (!isNativeMobileApp() || !isViewByFilterExpanded()) {
            if(!find(VIEW_BY_FILTER_IS_EXPANDED).getAttribute("class").contains("opened")){
                click(VIEW_BY_FILTER_LIST);
            }
        }
    }

    @Override
    public void collapseViewByFilter() {
        if(!find(VIEW_BY_FILTER_IS_EXPANDED).getAttribute("class").contains("opened")){
            click(VIEW_BY_FILTER_LIST);
        }
    }

    @Override
    public boolean isDisplayedTimeOnViewByMenu() {
        return   find(By.cssSelector(String.format(VIEW_BY_FILTER_LIST_BUTTON,"date"))).isDisplayed();
    }

    @Override
    public boolean isDisplayedCompetitionOnViewByMenu() {
      return   find(By.cssSelector(String.format(VIEW_BY_FILTER_LIST_BUTTON,"competition"))).isDisplayed();
    }

    @Override
    public void selectAlternateMarket(String option) {
        click(DROPDOWN_SELECTOR);
        click(By.linkText(option));
    }

    @Override
    public void selectAlternateMarket(int option) {
        find(ALTERNATE_MARKET_GROUP_DROPDOWN).click();
        List<WebElement> elements;
        elements = find(ALTERNATE_MARKET_GROUP_DROPDOWN).findElements(By.className(ALTERNATE_MARKET_GROUP_DROPDOWN_OPTION));
        elements.get(option).click();
        sleep(1000);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSelectedAlternateMarket() {
        waitElementToBeVisible(ALTERNATE_MARKET_GROUP_DROPDOWN, 5);
        WebElement activeOption = find(ALTERNATE_MARKET_GROUP_DROPDOWN).findElement(ALTERNATE_MARKET_ACTIVE_DROPDOWN_OPTION);
        return activeOption.getText();
    }

    @Override
    public boolean alternateMarketIsSelected(int option) {
        List<WebElement> elements;
        elements = find(ALTERNATE_MARKET_GROUP_DROPDOWN).findElements(By.className(ALTERNATE_MARKET_GROUP_DROPDOWN_OPTION));
        return elements.get(option).getAttribute("class").contains("active");
    }

    @Override
    public List<String> getAlternateMarketsName() {
        List<WebElement> elements;
        List<String> alternateMarketsName = new ArrayList<>();
        elements = find(ALTERNATE_MARKET_GROUP_DROPDOWN).findElements(By.className(ALTERNATE_MARKET_GROUP_DROPDOWN_OPTION));
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                alternateMarketsName.add(element.getText());
            }

        }
        return alternateMarketsName;
    }


    @Override
    public boolean isAlternateMarketMenuDisplayed() {
        boolean alternateMenuDisplayed;
        alternateMenuDisplayed = isElementDisplayed(ALTERNATE_MARKET_GROUP_DROPDOWN) &&
                isElementDisplayed(ALTERNATE_MARKET_GROUP_DROPDOWN_SELECTION);
        if(!isMobile()) {
            alternateMenuDisplayed = isElementDisplayed(By.cssSelector(ALTERNATE_MARKET_GROUP_DROPDOWN_LABEL));
        }
        return alternateMenuDisplayed;
    }

    @Override
    public boolean isViewByFilterExpanded() {
        return isElementDisplayed(VIEW_BY_FILTER_EXPANDED);

    }

    @Override
    public boolean isDisplayed() {
        return dayFilterScrollerIsDisplay() && getCurrentPageName().equalsIgnoreCase("matches");
    }
}
