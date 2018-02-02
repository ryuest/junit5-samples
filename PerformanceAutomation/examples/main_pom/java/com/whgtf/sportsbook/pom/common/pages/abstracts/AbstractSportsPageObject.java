package com.whgtf.sportsbook.pom.common.pages.abstracts;

import com.google.common.collect.ImmutableMap;
import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.components.interfaces.*;
import com.whgtf.sportsbook.pom.common.exceptions.NoEventAvailable;
import com.whgtf.sportsbook.pom.common.exceptions.NoMarketAvailable;
import com.whgtf.sportsbook.pom.common.exceptions.NoSelectionAvailable;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractSportsPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
@Lazy
public class AbstractSportsPageObject extends AbstractCommonObject implements AbstractSportsPage {

    public static final By GENERIC_SELECTION = By.cssSelector("[id^='OB_EV']:not(.disabled-event)" +
            ":not(.topbets__list-item) [id^='OB_MA'][data-status='A']:not(.disabled-market) [id^='OB_OU']" +
            "[data-status='A']:not(.disabled)");

    protected static final By GENERIC_EVENT = By.cssSelector("[id^='OB_EV'] a");

    private static final By SELECTION = By.xpath(".//button[contains(@id,'OB_OU')]");

    protected static final By ALL_MARKETS_DISPLAYED = By.cssSelector("article[id*='comp-OB_TY']");

    protected static final String BASE_URL = getBaseUrl();

    private static final By ALL_MAIN_TITLES_ON_RACING_PAGES = By.cssSelector(".racing-highlights .header-panel__title");

    private static final By ALL_MAIN_TITLES_ON_SPORTS_PAGES = By.cssSelector(".h1.fl.sectionheading__text.tscri");

    private static final By BACK_BUTTON = By.id("back-button");

    private static final By SECONDARY_HEADER = By.cssSelector(".header-panel__title.cap");

    private static final By SECONDARY_HEADER_TITLE = By.xpath("(//section[contains(@class,'header-panel')])[1]");

    private static final By SELECTION_SELECTED = By.className("-selectionadded");

    private static final String MARKET_BLURB = "//div[@id='%s']//*[contains(@class,'btmarket__blurb')]";

    private static final By PRE_MATCH_ACTIVE_SELECTION =
            By.cssSelector("[id^='OB_EV'][data-betinrun='false']:not(.disabled-event):not(.topbets__list-item) [id^='OB_MA'][data-betinrun='false'][data-status='A']:not(.disabled-market) [id^='OB_OU'][data-status='A']:not(.disabled)");

    private static final By IN_PLAY_ACTIVE_SELECTION =
            By.cssSelector("[id^='OB_EV'][data-betinrun='true']:not(.disabled-event):not(.topbets__list-item) [id^='OB_MA'][data-betinrun='true'][data-status='A']:not(.disabled-market) [id^='OB_OU'][data-status='A']:not(.disabled)");

    private static final By IN_PLAY_ACTIVE_MARKET =
            By.cssSelector("[id^='OB_EV'][data-betinrun='true']:not(.disabled-event):not(.topbets__list-item) " +
                    "[id^='OB_MA'][data-betinrun='true']:not(.disabled-market)");


    private static final By PRE_MATCH_ACTIVE_MARKET =
            By.cssSelector("[id^='OB_EV'][data-betinrun='false']:not(.disabled-event):not(.topbets__list-item) " +
                    "[id^='OB_MA'][data-betinrun='false']:not(.disabled-market)");

    private static final By IN_PLAY_ACTIVE_EVENT =
            By.cssSelector("[id^='OB_EV'][data-betinrun='true']:not(.disabled-event):not(.topbets__list-item)");

    private static final By PRE_MATCH_ACTIVE_EVENT =
            By.cssSelector("[id^='OB_EV'][data-betinrun='false']:not(.disabled-event):not(.topbets__list-item)");

    private static final By MARKET = By.cssSelector("[id^='OB_MA']");

    private static final By RIGHT_SIDEBAR_NAVIGATION = By.cssSelector("nav.sidebar.-to-right");

    private static final By TOPBETS = By.cssSelector(".topbets");

    private static final By TOPBETS_ITEM = By.cssSelector(".topbets__list-item");


    protected static final Map<String, String> LANGUAGE_URL_MAP = ImmutableMap.<String, String>builder()
            .put("German", "de-de")
            .put("English", "en-gb")
            .put("Japanese", "ja-jp")
            .put("Greek", "el-gr")
            .put("Russian", "ru-ru")
            .put("Swedish", "sv-se")
            .build();


    @Autowired
    @Lazy
    public BottomBarComponent bottomBar;

    @Autowired
    @Lazy
    public FooterComponent footerComponent;

    @Autowired
    @Lazy
    public CarouselComponent carouselComponent;

    @Autowired
    @Lazy
    public HeaderComponent headerComponent;

    @Autowired
    @Lazy
    public BetSlipComponent betSlipComponent;

    @Autowired
    @Lazy
    public TopBetsComponent topBetsComponent;

    @Autowired
    @Lazy
    public SportsMenuComponent sportsMenuComponent;


    public BottomBarComponent getBottonBar() {
        return bottomBar;
    }

    public CarouselComponent getCarouselComponent() {
        return carouselComponent;
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }

    public FooterComponent getFooterComponent() {
        return footerComponent;
    }

    @Override
    public TopBetsComponent getTopBetsComponent() {
        return topBetsComponent;
    }

    @Override
    public SportsMenuComponent getSportsMenuComponent() {
        return sportsMenuComponent;
    }

    private static String getBaseUrl() {
        String env = System.getProperty("env", "pp1");
        String locale = System.getProperty("locale", "en-gb");
        String url = "http://sports.williamhill";
        if ("dev".equalsIgnoreCase(env)) {
            env = "-sportsbettingdev";
        }
        if ("liv".equalsIgnoreCase(env)) {
            env = "";
        }
        if ("pp1".equalsIgnoreCase(env) || "pp2".equalsIgnoreCase(env)
                || "pp3".equalsIgnoreCase(env)) {
            env = "-".concat(env);
        }
        return url + env + ".com/betting/" + locale;

    }

    public String getCurrentPageName() {
        return executeJavaScript("return WH.sportsbook.page").toString();
    }

    @Override
    public boolean isDisplayed() {
        //No page specified
        return false;
    }

    public BetSlipComponent openBetSlip() {
        if (!isBetSlipOpen()) {
            clickOnBetSlip();
        }
        return betSlipComponent;
    }

    @Override
    public AbstractSportsPage waitToBeLoaded() {
        waitSportsbook();
        return this;
    }

    @Override
    public boolean isDisplayedInLanguage(String language) {
        return getCurrentUrl().contains(LANGUAGE_URL_MAP.get(language));
    }

    @Override
    public boolean isWordInTheUrl(String word) {
        return getCurrentUrl().contains(word);
    }

    @Override
    public String getUrl() {
        return getCurrentUrl();
    }

    @Override
    public boolean isInTheTopPosition() {
        sleep(1000);
        Long value = (Long) executeJavaScript("return window.scrollY");
        return value == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scrollToTheBottom() {
        executeJavaScript("$(\".button__back-to-top\")[0].scrollIntoView()");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scrollToTheTop() {
        executeJavaScript("window.scrollBy(0, -document.body.scrollHeight)");
    }

    @Override
    public void scrollToTheBottomOnLazyLoading() throws InterruptedException {
        By selBy = By.cssSelector("#contentarea");
        int initialHeight = getWebDriver().findElement(selBy).getSize().getHeight();
        int currentHeight = 0;
        while (initialHeight != currentHeight) {
            initialHeight = getWebDriver().findElement(selBy).getSize().getHeight();

            //Scroll to bottom
            ((JavascriptExecutor) getWebDriver()).executeScript("scroll(0," + initialHeight + ");");
            Thread.sleep(3000);
            currentHeight = getWebDriver().findElement(selBy).getSize().getHeight();
        }
    }

    @Override
    public void refreshPage() {
        refresh();
    }

    private boolean isBetSlipOpen() {
        return betSlipComponent.isOpen();
    }

    private void clickOnBetSlip() {
        if (isMobile())
            bottomBar.clickOnBetSlipMenu();
        betSlipComponent.openBetSlip();
    }

    public List<String> getAllSelectionPricesInThePage() {
        List<WebElement> list = findElements(By.xpath("//button[contains(@id,'OB_OU')]"));
        List<String> selectionList = new ArrayList<>();
        for (WebElement selection : list) {
            if (selection.isDisplayed() && !StringUtils.EMPTY.equalsIgnoreCase(selection.getText())) {
                selectionList.add(selection.getText());
            }
        }
        return selectionList;
    }

    public List<Selection> getAllSelectionsInThePage() {
        List<WebElement> list = findElements(By.xpath("//div[contains(@id,'contentarea')]//button[contains(@id,'OB_OU')]"));
        List<Selection> selectionList = new ArrayList<>();
        for (WebElement selection : list) {
            if (isElementDisplayed(selection)) {
                Selection sel = new Selection(selection.getAttribute("id"));
                sel.setPrice(selection.getAttribute("data-odds"));
                sel.setStatus(selection.getAttribute("data-status"));
                selectionList.add(sel);
            }
        }
        return selectionList;
    }

    public void changeLatestTab() {
        sleep(1000);
        super.switchToLastBrowserTab();
    }

    public void navigateToUrl(final String url) {
        open(url);
    }

    public List<String> getHandicapInEvent(String eventId) {
        List<WebElement> list = find(By.id(eventId)).findElements(By.cssSelector(".selectionhandicap"));
        List<String> handicapList = new ArrayList<>();
        for (WebElement element : list) {
            handicapList.add(element.getText());
        }
        return handicapList;
    }

    public List<Selection> getSelectionsInMarket(final String eventId) {
        List<WebElement> selectionList = find(By.id(eventId)).findElements(SELECTION);
        List<Selection> selections = new ArrayList<>();
        for (WebElement element : selectionList) {
            Selection selection = new Selection(element.getAttribute("id"), element.getText());
            selections.add(selection);
        }
        return selections;
    }

    public List<String> getSelectionsPricesInMarket(final String marketId) {
        List<WebElement> selectionList = find(By.id(marketId)).findElements(SELECTION);
        List<String> selectionPrices = new ArrayList<>();
        for (WebElement element : selectionList) {
            selectionPrices.add(element.getText());
        }
        return selectionPrices;
    }

    public List<String> getUnderOverInEvent(String eventId) {
        List<WebElement> list = find(By.id(eventId)).findElements(By.cssSelector(".btmarket__selection p"));
        List<String> overList = new ArrayList<>();
        for (WebElement element : list) {
            overList.add(element.getText());
        }
        return overList;
    }

    @Override
    public boolean isEventDisplayed(String eventId) {
        try {
            find(By.id(eventId));
        } catch (NoSuchElementException ex) {
            return false;
        } catch (TimeoutException ex) {
            return false;
        }

        return find(By.id(eventId)).isDisplayed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waitEventDisplayed(String eventId) {
        int tries = 5;
        int catchExceptionLimit = 0;
        do {
            refresh();
            catchExceptionLimit++;
        } while (!isElementPresent(By.id(eventId))
                && (catchExceptionLimit < tries));
    }


    public boolean isSelectionInGreen(String selectionId) {
        try {
            return find(By.id(selectionId)).getAttribute("data-odds-change").contains("simple-up");
        } catch (NoSuchElementException ex) {
            return false;
        } catch (TimeoutException ex) {
            return false;
        }
    }


    public boolean isSelectionInRed(String selectionId) {
        try {
            return find(By.id(selectionId)).getAttribute("data-odds-change").contains("simple-down");
        } catch (NoSuchElementException ex) {
            return false;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public boolean isSelectionInBlue(String selectionId) {
        try {
            Color expectedColor = Color.fromString("#248cb3");
            Color currentColor = Color.fromString(find(By.id(selectionId)).getCssValue("background-color"));
            return currentColor.equals(expectedColor);
        } catch (NoSuchElementException ex) {
            return false;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    @Override
    public boolean isSelectionGrayedOut(String selectionId) {
        Color expectedColor = Color.fromString("#f2f2f2");
        Color currentColor = Color.fromString(find(By.id(selectionId)).getCssValue("background-color"));
        return expectedColor.equals(currentColor);
    }

    public boolean headerPanelTitlePageIsDisplayed() {
        return isElementDisplayed(PAGE_HEADER_TITLE);
    }

    public boolean desktopSportTitleisDisplayed() {
        try {
            if (getCurrentUrl().contains("meetings")) {
                findElements(ALL_MAIN_TITLES_ON_RACING_PAGES);
            } else {
                findElements(ALL_MAIN_TITLES_ON_SPORTS_PAGES);
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void clickBackButton() {
        if (isMobile()) {
            click(BACK_BUTTON);
        } else {
            navigateBack();
        }
        waitSportsbook();
    }

    @Override
    public boolean isBackButtonDisplayed() {
        return isElementDisplayed(BACK_BUTTON);
    }

    @Override
    public boolean isBackButtonDisplayedForRaceCard() {
        return isElementDisplayed(find(By.cssSelector("section[class='header-panel']")).findElement(BACK_BUTTON));
    }


    @Override
    public String getSecondaryHeaderText() {
        return find(SECONDARY_HEADER_TITLE).getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSecondaryHeaderDisplayed() {
        return isElementDisplayed(SECONDARY_HEADER);
    }

    @Override
    public boolean areSelectionsInMarketGrayedOut(String marketId) {
        List<WebElement> selectionList = find(By.id(marketId)).findElements(By.tagName("button"));
        Color expectedColor = Color.fromString("#f2f2f2");
        boolean allGrayedOut = true;
        for (WebElement selection : selectionList) {
            Color currentColor = Color.fromString(selection.getCssValue("background-color"));
            boolean isGrayedOut = expectedColor.equals(currentColor);
            if (!isGrayedOut) {
                allGrayedOut = false;
            }
        }
        return allGrayedOut;
    }


    @Override
    public boolean areSelectionsInMarketClickable(String marketId) {
        List<WebElement> selectionList = find(By.id(marketId)).findElements(By.tagName("button"));
        try {
            for (WebElement selection : selectionList) {
                selection.click();
            }

        } catch (Exception ex) {
            return false;
        }
        return true;

    }

    @Override
    public boolean areSelectionsInTheEventClickable(String eventId) {
        List<WebElement> selectionList = find(By.id(eventId)).findElements(By.tagName("button"));
        // for active elements expect result true, for suspended expect result false
        try {
            for (WebElement selection : selectionList) {
                try {
                    selection.click();
                } catch (WebDriverException ex) {
                    // in case element will not be fully visible due the bottom bar, scroll and second try
                    executeJavaScript("window.scrollBy(0,100)");
                    selection.click();
                }
            }

        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean areSelectionsInTheEventOrMarketGrayedOut(String eventId) {
        List<WebElement> selectionList = find(By.id(eventId)).findElements(By.tagName("button"));
        Color expectedColor = Color.fromString("#f2f2f2");
        boolean allGrayedOut = true;
        for (WebElement selection : selectionList) {
            Color currentColor = Color.fromString(selection.getCssValue("background-color"));
            boolean isGrayedOut = expectedColor.equals(currentColor);
            if (!isGrayedOut) {
                allGrayedOut = false;
            }
        }
        return allGrayedOut;
    }

    @Override
    public boolean areSelectionsInTheEventOrMarketGrayedOutInBlue(String eventId) {
        List<WebElement> selectionList = find(By.id(eventId)).findElements(By.tagName("button"));
        Color expectedColor = Color.fromString("#248cb3");
        boolean allGrayedOut = true;
        for (WebElement selection : selectionList) {
            if (selection.getAttribute("class").contains("active")) {
                Color currentColor = Color.fromString(selection.getCssValue("background-color"));
                boolean isGrayedOut = expectedColor.equals(currentColor);
                if (!isGrayedOut) {
                    allGrayedOut = false;
                }
            }
        }
        return allGrayedOut;

    }

    @Override
    public Selection clickOnTheFirstAvailableSelection() {
        Selection clickedSelection = null;
        if(!"top-bets".equalsIgnoreCase(getCurrentPageName())){
            List<WebElement> selectionList = findElements(GENERIC_SELECTION);
            if (!selectionList.isEmpty()) {
                for (WebElement selection : selectionList) {
                    if (selection.isDisplayed()) {
                        clickedSelection = clickOnSelection(selection.getAttribute("id"));
                        break;
                    }
                }
            } else {
                throw new NoSelectionAvailable("No active selections found.");
            }
        } else {
            List<WebElement> selectionList = findElements(SELECTION);
            clickedSelection = clickOnSelection(selectionList.get(0).getAttribute("id"));
        }
        return clickedSelection;
    }

    @Override
    public Selection clickOnTheLastAvailableSelection() {
        List<WebElement> selectionList;
        selectionList = findElements(GENERIC_SELECTION);
        int i=0;
        if(!"top-bets".equalsIgnoreCase(getCurrentPageName())) {
            for(i=selectionList.size()-1; i>=0 ;i--) {
                if(selectionList.get(i).isDisplayed()) {
                    click(selectionList.get(i));
                    break;
                }
            }
        } else {
            selectionList = findElements(SELECTION);
        }
        return new Selection(selectionList.get(i).getAttribute("id"));
    }

    public void clickOnTheFirstAvailableSPSelection() {
        List<WebElement> selectionList = findElements(GENERIC_SELECTION);
        if (!selectionList.isEmpty()) {
            for (WebElement selection : selectionList) {
                if (selection.isDisplayed() && (selection.getAttribute("id").contains("_SP"))) {
                    clickOnSelection(selection.getAttribute("id"));
                    break;
                }
            }
        } else {
            throw new NoSelectionAvailable("No active selections found.");
        }
    }

    @Override
    public void clickOnSelectionByIndex(int number) {
        List<WebElement> selectionList = findElements(GENERIC_SELECTION);
        selectionList.get(number - 1).click();
    }

    @Override
    public void clickOnGivenNumberOfSelections(int number) {
        List<WebElement> selectionList = findElements(GENERIC_SELECTION);
        for (int i = 0; i < number; i++) {
            selectionList.get(i).click();
        }
    }

    @Override
    public void clickOnlyOnFirstSelectionFromMarkets(int number) {
//        List<WebElement> displayedMarkets = findElements(ALL_MARKETS_DISPLAYED);
        for (int i = 1; i <= number; i++) {
//            List<WebElement> markets = findElements(ALL_MARKETS_DISPLAYED);
//            markets.get(i).findElements(By.cssSelector("button[id*='OB_OU']")).get(0).click();
//            clickByJavascript(By.xpath("((//article[contains(@id,'comp-OB_TY')])[" + i + "]//button[contains(@id,'OB_OU')])[1]"));

            scrollToElementByXpath("(//article[contains(@id,'comp-OB_TY')])[" + i + "]");
            clickByJavascript(By.xpath("((//article[contains(@id,'comp-OB_TY')])[" + i + "]//button[contains(@id,'OB_OU')])[1]"));


        }
    }

    @Override
    public List<String> getMarketBlurb(final String eventId) {
        List<WebElement> elementList;
        List<String> blurbList = new ArrayList<>();
        //An event can have more than one blurb text, so we iterate on it.
        try {
            elementList = findElements(By.xpath(String.format(MARKET_BLURB, eventId)));
        } catch (TimeoutException ex) {
            return blurbList;
        }

        for (WebElement element : elementList) {
            blurbList.add(element.getText());
        }
        return blurbList;
    }

    @Override
    public Event clickOnTheFirstEvent() {
        WebElement eventWebElement = find(By.cssSelector("[id^='OB_EV']"));
        String eventId = eventWebElement.getAttribute("id");
        click(eventWebElement.findElement(By.tagName("a")));
        return new Event(eventId);
    }

    @Override
    public List<Selection> getActiveSelections() {
        List<WebElement> selectionList = findElements(GENERIC_SELECTION);
        List<Selection> finalList = new ArrayList<>();
        for (WebElement selectionElement : selectionList) {
            Selection selection = new Selection(selectionElement.getAttribute("id"), selectionElement.getAttribute("data-odds"));
            finalList.add(selection);
        }

        return finalList;
    }

    @Override
    public List<Selection> getActivePreMatchSelections() {
        List<WebElement> selectionList = findElements(PRE_MATCH_ACTIVE_SELECTION);
        List<Selection> finalList = new ArrayList<>();
        for (WebElement selectionElement : selectionList) {
            Selection selection = new Selection(selectionElement.getAttribute("id"), selectionElement.getAttribute("data-odds"));
            finalList.add(selection);
        }

        return finalList;
    }

    @Override
    public List<Selection> getActiveInPlaySelections() {
        List<WebElement> selectionList = findElements(IN_PLAY_ACTIVE_SELECTION);
        List<Selection> finalList = new ArrayList<>();
        for (WebElement selectionElement : selectionList) {
            Selection selection = new Selection(selectionElement.getAttribute("id"), selectionElement.getAttribute("data-odds"));
            finalList.add(selection);
        }

        return finalList;
    }

    @Override
    public void changeTemporaryPassword(final String currentPassword, final String newPassword) {
        waitElementToBeVisible(By.id("overlay"), 10);
        switchToiFrame(By.id("overlay"));
        find(By.id("cur_password")).sendKeys(currentPassword);
        find(By.id("reg_password")).sendKeys(newPassword);
        find(By.id("vfy_password")).sendKeys(newPassword);
        find(By.id("submitButton")).click();
        switchToParentFrame();
    }


    @Override
    public String getMarketActive(final String type) {
        try {
            if ("pre-match".equals(type) || "highlight".equals(type))
                return find(PRE_MATCH_ACTIVE_MARKET).getAttribute("id");
            else
                return find(IN_PLAY_ACTIVE_MARKET).getAttribute("id");
        } catch (Exception e) {
            throw new NoMarketAvailable(
                    String.format("No active %s market found.", type));
        }
    }

    @Override
    public String getEventActive(final String type) {
        try {
            if ("pre-match".equals(type) || "highlight".equals(type))
                return find(PRE_MATCH_ACTIVE_EVENT).getAttribute("id");
            else
                return find(IN_PLAY_ACTIVE_EVENT).getAttribute("id");
        } catch (Exception e) {
            throw new NoEventAvailable(
                    String.format("No active %s event found.", type));
        }

    }

    @Override
    public Selection getSelectionActiveByType(final String type) {
        try {
            Selection selection = new Selection();
            if ("pre-match".equals(type) || "highlight".equals(type)) {
                selection.setPdsId(find(PRE_MATCH_ACTIVE_SELECTION).getAttribute("id"));
                selection.setPrice(find(PRE_MATCH_ACTIVE_SELECTION).getAttribute("data-odds"));
                return selection;
            } else {
                selection.setPdsId(find(IN_PLAY_ACTIVE_SELECTION).getAttribute("id"));
                selection.setPrice(find(IN_PLAY_ACTIVE_SELECTION).getAttribute("data-odds"));
                return selection;
            }
        } catch (Exception e) {
            throw new NoSelectionAvailable(
                    String.format("No active %s selection found.", type));
        }
    }

    @Override
    public int getSelectionsSelected() {
        return findElements(SELECTION_SELECTED).size();
    }


    @Override
    public boolean isMarketDisplayed(final String marketId) {
        return isElementDisplayed(By.id(marketId));
    }

    @Override
    public List<Market> getMarketsFromEvent(String eventId) {
        List<WebElement> marketList = find(By.id(eventId)).findElements(MARKET);
        List<Market> resultList = new ArrayList<>();
        for (WebElement market : marketList) {
            Market mar = new Market(market.getAttribute("id"));
            resultList.add(mar);
        }
        return resultList;
    }

    @Override
    public void clickOnEventById(final String eventId) {
        click(find(By.id(eventId)).findElement(By.tagName("a")));
    }


    @Override
    public String getPriceFromSelectionId(final String selectionId) {
        waitSportsbook();
        return find(By.cssSelector("#" + selectionId + " .racecard-button__price")).getAttribute("data-odds");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String waitSelectionPriceToBe(final String selectionId, final String expectedPrice) {
        waitSportsbook();
        By selectionSelector = By.cssSelector("#" + selectionId + " .racecard-button__price");

        if (waitElementAttributeToContain(selectionSelector, "data-odds", expectedPrice, 10))
            return expectedPrice;
        else
            return find(selectionSelector).getAttribute("data-odds");
    }

    @Override
    public boolean isRightSidebarDisplayed() {
        return isElementDisplayed(RIGHT_SIDEBAR_NAVIGATION);
    }

    @Override
    public boolean isTopBetsDetailsDisplayed(){
        return (isElementDisplayed(TOPBETS) && isElementDisplayed(TOPBETS_ITEM));
    }

    @Override
    public List<String> getSelectionsInEvent(final String eventId) {
        List<WebElement> selectionList = find(By.id(eventId)).findElements(SELECTION);
        List<String> selectionValues = new ArrayList<>();
        for (WebElement element : selectionList) {
            selectionValues.add(element.getText());
        }
        return selectionValues;
    }

    @Override
    public String getLabelFromSelectionId(final String selectionId) {
        return find(By.id(selectionId)).getAttribute("aria-label");
    }

    /**
     * On this method we return a True if the colour of the given WebElement
     * matches the one given as parameter.
     * @param element - Webelement that we would like to verify its colour.
     * @param color - The colour that we expect the WebElement to be.
     * @return - True if colours matches.
     */
    public boolean isGivenElementColorThis(WebElement element, String color){
        Color colorObtained = Color.fromString(element.getCssValue("color"));
        Color expectedColor = !isNativeMobileApp() ? Color.fromString(color) : Color.fromString("#fff");
        return  colorObtained.equals(expectedColor);
    }

    /**
     * On this method we return a True if the background colour of the given WebElement
     * matches the one given as parameter.
     * @param element - Webelement that we would like to verify its background colour.
     * @param color - The background colour that we expect the WebElement to have.
     * @return - True if colours matches.
     */
    public boolean isGivenElementBackgroundColorThis(WebElement element, String color){
        Color colorObtained = Color.fromString(element.getCssValue("background-color"));
        Color expectedColor = !isNativeMobileApp() ? Color.fromString(color) : Color.fromString("#fff");
        return  colorObtained.equals(expectedColor);
    }

}
