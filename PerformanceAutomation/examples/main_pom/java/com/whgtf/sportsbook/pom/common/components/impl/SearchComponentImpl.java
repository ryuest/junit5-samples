package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.BottomBarComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.SearchComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.SearchResultItem;
import com.whgtf.sportsbook.pom.common.components.interfaces.SportsMenuComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.common.pages.impl.EventPageImpl;
import com.whgtf.sportsbook.pom.common.pages.interfaces.EventPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class SearchComponentImpl extends AbstractCommonObject implements SearchComponent {

    private static final By COMPONENT_ID = By.id("search-overlay");

    private static final By LOAD_MORE_BUTTON = By.cssSelector(".load-more");

    private static final By INPUT_FIELD = By.cssSelector(".search-input__field");

    private static final By CLOSE_ICON = By.id("search-close");

    private static final By RESULT_DIV = By.xpath("//a[@class='search-events__name']");

    private static final String SPORTS_ICON = ".search-quick-links__item i.icon-%s";

    private static final String SPORTS_RESULT = ".//p[@class='search-quick-links__title'][text()='%s']";

    private static final By EVENTS_UNDER_SPORTS = By.cssSelector("div.search-quick-links+div.search-events");

    private static final String SPORTS_CAROUSEL = ".search-quick-links";

    private static final By SPORTS_NAMES = By.cssSelector("li.search-quick-links__item p");

    private static final String SEARCH_RESULTS_MESSAGE = "//span[text()='%s']";

    private static final By RESULTS_PRELOADER = By.cssSelector(".wh-preloader.wh-hide");
    private static final String SEARCH_EVENTS_ITEM = ".search-events__item";
    private static final String SEARCH_EVENTS_COUNT = ".search-events__count";
    private static final String SEARCH_EVENTS_MSG = ".search-events__msg";
    private static final String SEARCH_QUICK_LINKS_COUNT = ".search-quick-links__count";
    private static final String SEARCH_QUICK_LINKS_MSG = ".search-quick-links__msg";
    private static final String SEARCH_OVERLAY = "#search-overlay";
    private static final String SEARCH_INPUT_FIELD = ".search-input__field";
    private static final String PLACEHOLDER_ATTRIBUTE = "placeholder";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final String KEYSTROKE_A = "a";
    private static final String BORDER_BOTTOM_CSS_VALUE = "border-bottom";
    private static final String PX_SOLID = "1px solid";
    private static final String HREF = "href";
    private static final String A_CLASS_SEARCH_EVENTS_NAME = "//a[@class='search-events__name']";
    private static final String SEARCH_QUICK_LINKS_ITEM_I = ".search-quick-links__item i";
    private static final String SEARCH_EVENTS_ITEM_LAST_CHILD = ".search-events__item:last-child";
    private static final String CLEAR_WINDOW_LOCAL_STORAGE = "window.localStorage.clear();";
    private static final int TIME_OUT_IN_SECONDS = 20;

    @Autowired
    @Lazy
    BottomBarComponent bottomBarComponent;

    @Autowired
    @Lazy
    SportsMenuComponent sportsMenuComponent;


    @Override
    public void search(String search) {
        find(INPUT_FIELD).sendKeys(search);
        sleep(1000);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchComponent open() {
        if(isMobile())
            return bottomBarComponent.clickOnSearchMenu();
        return sportsMenuComponent.clickOnSearch();
    }

    @Override
    public void close() {
        find(CLOSE_ICON).click();
    }

    @Override
    public void clickOnLoadMore() {
        find(LOAD_MORE_BUTTON).click();
    }

    @Override
    public EventPage clickOnFirstLink() {
        return clickOnResultLink(0);
    }

    @Override
    public List<String> getAllLinksResults() {
        waitForResults();
        List<String> list = new ArrayList<>();
        waitForEventResultsLinks();
        List<WebElement> elemList = findElements(RESULT_DIV);
        for (WebElement elem : elemList) {
            list.add(elem.getAttribute(HREF));
        }
        return list;
    }

    private void waitForResults() {
        if (isNativeMobileApp()) {
            deviceSwitchToContextWebView();
        }
        waitUntilElementIsPresent(RESULTS_PRELOADER);
    }

    @Override
    public String getEventNameFromResultsList(int index) {
        return getEventWebElement(index).getText();
    }

    @Override
    public EventPage clickOnResultLink(int index) {
        getEventWebElement(index).click();
        return new EventPageImpl();
    }

    private WebElement getEventWebElement(int index) {
        waitForResults();
        waitForEventResultsLinks();
        return findElements(RESULT_DIV).get(index);
    }

    @Override
    public boolean isSportsIconDisplayed(String sportsName) {
        waitForSportsIcons();
        return find(By.cssSelector(String.format(SPORTS_ICON, sportsName.toLowerCase()))).isDisplayed();
    }

    @Override
    public boolean isSportsResultDisplayed(String sportsName) {
        waitElementToBeClickable(By.xpath(String.format(SPORTS_RESULT, sportsName)), TIME_OUT_IN_SECONDS);
        return isElementDisplayed(By.xpath(String.format(SPORTS_RESULT, sportsName)));
    }

    @Override
    public boolean areEventsDisplayedUnderSportsResults() {
        waitForEventResultsLinks();
        return find(EVENTS_UNDER_SPORTS).isDisplayed();
    }

    private boolean waitForEventResultsLinks() {
        waitForResults();
        return waitUntilElementIsPresent(By.xpath(A_CLASS_SEARCH_EVENTS_NAME));
    }

    private boolean waitForSportsIcons() {
        waitForResults();
        return waitUntilElementIsPresent(By.cssSelector(SEARCH_QUICK_LINKS_ITEM_I));
    }

    @Override
    public boolean isSportsResultsDisplayedAsCarousel() {
        waitForResults();
        try{
            return waitElementToBeClickable(By.cssSelector(SPORTS_CAROUSEL), TIME_OUT_IN_SECONDS).isDisplayed();
        } catch(TimeoutException te) {
            return false;
        }

    }

    @Override
    public List<String> getSportsResults() {
        waitForResults();
        List<String> list = new ArrayList<>();
        waitForSportsIcons();
        List<WebElement> elemList = findElements(SPORTS_NAMES);
        for (WebElement elem : elemList) {
            list.add(elem.getText());
        }
        return list;
    }

    @Override
    public List<String> getAllEventResults() {
        List<String> list = new ArrayList<>();
        waitForEventResultsLinks();
        List<WebElement> elemList = findElements(RESULT_DIV);
        for (WebElement elem : elemList) {
            list.add(elem.getText());
        }
        return list;
    }

    @Override
    public boolean isMessagePresent(String msg) {
        try {
            waitForResults();
            waitUntilElementIsPresent(By.xpath(String.format(SEARCH_RESULTS_MESSAGE, msg)));
            return find(By.xpath(String.format(SEARCH_RESULTS_MESSAGE, msg))).isDisplayed();
        } catch (TimeoutException | NoSuchElementException te) {
            return false;
        }
    }

    @Override
    public void selectSportFromResults(String sportsName) {
        waitForResults();
        waitUntilElementIsPresent(By.cssSelector(String.format(SPORTS_ICON, sportsName.toLowerCase())));
        find(By.cssSelector(String.format(SPORTS_ICON, sportsName.toLowerCase()))).click();
    }

    @Override
    public boolean isCustomSearchIconDisplayed(String text, String customSearch) {
        waitForResults();
        if (StringUtils.isEmpty(customSearch)) {
            throw new UnsupportedOperationException("Please make sure custom search icon url should not be empty");
        }
        try {
            waitUntilElementIsPresent(By.xpath("//p[@class='search-nav__title'][text()='" + text + "']/../..//i[@class='search-nav__icon contextual-nav__link-img']"));
            return find(By.xpath("//p[@class='search-nav__title'][text()='" + text + "']/../..//i[@class='search-nav__icon contextual-nav__link-img']")).getAttribute("style").contains(customSearch);
        } catch (NoSuchElementException nse) {
            return false;
        }
    }

    @Override
    public void clickOnSportsResultIcon(String iconText) {
        waitForResults();
        waitUntilElementIsPresent(By.xpath(String.format(SPORTS_RESULT, iconText)));
        find(By.xpath(String.format(SPORTS_RESULT, iconText))).click();
    }


    @Override
    public boolean isSportsIconLinkOpenedInNewTab(String url) {
        sleep(1000);
        navigateToOtherWindow();
        boolean result = getCurrentUrl().contains(url);
        returnToDefaultWindow();
        return result;
    }

    public String getSearchInputPlaceHolder() {
        return getSearchInputElement().getAttribute(PLACEHOLDER_ATTRIBUTE);
    }

    private WebElement getSearchInputElement() {
        waitUntilElementIsPresent(By.cssSelector(SEARCH_INPUT_FIELD));
        return find(INPUT_FIELD);
    }


    @Override
    public void clearTextByUsingBackSpace() {
        WebElement element = getSearchInputElement();
        element.sendKeys(Keys.CONTROL + Keys.chord(KEYSTROKE_A));
        element.sendKeys(Keys.BACK_SPACE);
    }


    @Override
    public String getSearchText() {
        return getSearchInputElement().getAttribute(VALUE_ATTRIBUTE);
    }

    private WebElement getSearchOverlay() {
        waitUntilElementIsPresent(By.cssSelector(SEARCH_OVERLAY));
        return find(COMPONENT_ID);
    }


    @Override
    public void closeSearchOverlay() {
        getSearchOverlay();
        find(CLOSE_ICON).click();
    }


    @Override
    public boolean isSearchOverlayClosed() {
        return getSearchOverlay().isDisplayed();
    }


    @Override
    public boolean isQuickLinksHeaderDisplayedAboveSportsResults(String quickLinksHeader) {
        try {
            waitForResults();
            waitUntilElementIsPresent(By.cssSelector(SEARCH_QUICK_LINKS_MSG));
            return find(By.cssSelector(SEARCH_QUICK_LINKS_MSG)).getText().contains(quickLinksHeader);
        } catch (NoSuchElementException nse) {
            return false;
        }
    }


    @Override
    public boolean isQuickLinksCountDisplayed() {
        try {
            waitForResults();
            waitUntilElementIsPresent(By.cssSelector(SEARCH_QUICK_LINKS_MSG));
            return find(By.cssSelector(SEARCH_QUICK_LINKS_COUNT)).isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        }

    }


    @Override
    public boolean isEventLinksHeaderDisplayedAboveEventsResults(String eventHeaderName) {
        try {
            waitForResults();
            waitUntilElementIsPresent(By.cssSelector(SEARCH_EVENTS_MSG));
            return find(By.cssSelector(SEARCH_EVENTS_MSG)).getText().contains(eventHeaderName);
        } catch (NoSuchElementException nse) {
            return false;
        }

    }


    @Override
    public boolean isEventsCountDisplayed() {
        try {
            waitForResults();
            return getEventsCountWebElement().isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        }

    }

    private WebElement getEventsCountWebElement() {
        waitForResults();
        waitUntilElementIsPresent(By.cssSelector(SEARCH_EVENTS_COUNT));
        return find(By.cssSelector(SEARCH_EVENTS_COUNT));
    }


    @Override
    public boolean areEventResultsSeperatedByLine() {
        try {
            waitForResults();
            waitUntilElementIsPresent(By.cssSelector(SEARCH_EVENTS_ITEM));
            return find(By.cssSelector(SEARCH_EVENTS_ITEM)).getCssValue(BORDER_BOTTOM_CSS_VALUE).contains(PX_SOLID);
        } catch (NoSuchElementException nse) {
            return false;
        }

    }


    @Override
    public int getEventResultsCount() {
        try {
            return Integer.parseInt(getEventsCountWebElement().getText());
        } catch (Exception nse) {
            return 0;
        }

    }


    @Override
    public boolean scrollDownSearchResults() {
        try {
            waitForResults();
            waitUntilElementIsPresent(By.cssSelector(SEARCH_EVENTS_ITEM));
            scrollIntoElement(SEARCH_EVENTS_ITEM_LAST_CHILD);
            return true;
        } catch (Exception nse) {
            return false;
        }
    }


    @Override
    public boolean clearBrowserLocalStore() {
        try {
            executeJavaScript(CLEAR_WINDOW_LOCAL_STORAGE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean isRecentSearchesContainerDisplayed() {
        try {
            waitUntilElementIsPresent(By.cssSelector(""));
            return find(By.cssSelector("")).isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        }

    }


    @Override
    public String getRecentSearchesHeader() {
        String headerName;
        try {
            waitUntilElementIsPresent(By.cssSelector(""));
            return find(By.cssSelector("")).getText();
        } catch (NoSuchElementException nse) {
            return StringUtils.EMPTY;
        }
    }


    @Override
    public void selectRecentSearch(int itemIndex) {
        waitElementToBePresent(By.cssSelector(""), TIME_OUT_IN_SECONDS);
        findElements(By.cssSelector("")).get(itemIndex).getText();
    }


    @Override
    public List<String> getRecentSearches() {
        List<String> elementNames = new ArrayList<>();
        waitUntilElementIsPresent(By.cssSelector(""));
        for (WebElement ele : findElements(By.cssSelector(""))) {
            elementNames.add(ele.getText());
        }
        return elementNames;
    }


    @Override
    public boolean isScrollBarPresentOnRecentSearchesList() {
        try {
            waitUntilElementIsPresent(By.cssSelector(""));
            return find(By.cssSelector("")).isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        }

    }


    @Override
    public boolean scrollDownRecentSearch() {
        try {
            waitUntilElementIsPresent(By.cssSelector(""));
            scrollIntoElement("");
            return true;
        } catch (Exception nse) {
            return false;
        }
    }


    @Override
    public SearchResultItem getEventResult(int i) {
        try {
            waitForResults();
            waitUntilElementIsPresent(By.cssSelector(SEARCH_EVENTS_ITEM));
            return new SearchResultItemImpl(findElements(By.cssSelector(SEARCH_EVENTS_ITEM)).get(i));
        } catch (NoSuchElementException nse) {
            throw new RuntimeException("Problem in finding the search result item element attributes." + nse.getMessage());
        }
    }

    private boolean waitUntilElementIsPresent(By by) {
        try {
            return waitElementToBePresent((by), TIME_OUT_IN_SECONDS).isDisplayed();
        }catch(TimeoutException te){
            return false;
        }
    }
}