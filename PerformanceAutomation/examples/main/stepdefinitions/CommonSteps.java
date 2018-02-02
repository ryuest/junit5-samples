package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.util.Msg;
import com.whgtf.sportsbook.model.Event;
import com.whgtf.sportsbook.model.Market;
import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.model.User;
import com.whgtf.sportsbook.pom.common.components.interfaces.AccountTabComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.LanguageSelectionComponent;
import com.whgtf.sportsbook.pom.common.components.interfaces.LoginComponent;
import com.whgtf.sportsbook.pom.utils.BackOfficeUtils;
import com.whgtf.sportsbook.pom.utils.MockedPush;
import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.whgtf.sportsbook.pom.utils.Assertions.assertArrayContainsRegex;
import static org.assertj.core.api.Assertions.assertThat;


@Component
@Lazy
public class CommonSteps extends MainSteps {

    private static String DECIMAL_SYNTAX = "\\d+[.]\\d+";

    private static String FRACTIONAL_SYNTAX = "\\d+[/]\\d+|EVS|SP";

    private static String AMERICAN_SYNTAX = "[+-]\\d+";

    @Autowired
    @Lazy
    private HorsesSteps horsesSteps;

    /**
     * Summary: On this method is often used as the first step on all the "Main Test" Scenarios since
     * it opens the Web Browser on the William Hill Home Page.
     *
     * @throws Throwable
     */
    @Given("^user is in William Hill Sportsbook$")
    public void userIsUrl() throws Throwable {
        homePage.open();
    }

    /**
     * Summary: This method changes the Language of the Site for any of the given options available, Its often used in
     * Scenarios to Test same functionality on different languages.
     *
     * General Note: that the way we are changing the language on this method is via URL and not by changing the language using
     * the Language Selector Dropdown at the bottom of the Home Page.
     *
     * @param language - As parameter the method accept the following inputs: English, German, Greek, Russian, Japanese and Swedish.
     *
     * Developers Note: Please update if some of these languages are removed, or if we add new ones to this method.
     *
     * @throws Throwable
     */
    @Given("^user is in William Hill Sportsbook in (English|German|Greek|Russian|Japanese|Swedish)$")
    public void userGoesTo(final String language) throws Throwable {
        homePage.openLanguage(language);
    }

    /**
     * Summary: This method lets us navigate to "Virtual World", "Tennis", "Greyhounds", "Darts", "Football",
     * "Horse-Racing", "In-Play" and "Top Bets".
     *
     *  If the Test is being run in Mobile, will navigate to these pages by the Sports Menu ("A to Z" on the Footer)
     *  and if the Test is being run in Desktop will be through the Header.
     *
     * @param sport - This method will accept as input parameter for "sport": "Virtual World", "Tennis", "Greyhounds",
     *                "Darts", "Football", "Horse-Racing", "In-Play" and "Top Bets".
     *
     * Developers Note: Please update these info if input options are added or removed.
     *
     * @throws Throwable
     */
    @When("^user goes to '(Virtual World|Tennis|Greyhounds|Darts|Football|Horse-Racing|Horse Racing|In-Play|Top Bets)' page$")
    public void userGoesToSportsPage(final String sport) throws Throwable {
        if (homePage.isMobile()) {
            homePage.getBottonBar().clickOnSportsMenu().clickOnSport(sport);
        } else {
            homePage.getSportsMenuComponent().clickOnSportByName(sport);
        }
    }

    /**
     * Summary:
     *
     *
     *
     *
     * @throws Throwable
     */
    @Then("^user verify the navigation to all sports$")
    public void verifyAllSports() throws Throwable {
        if (homePage.isMobile()) {
            homePage.getBottonBar().clickOnSportsMenu();
            List<String> sports = quickLinksComponent.getListSports();
            SoftAssertions softly = new SoftAssertions();
            String previousUrl = homePage.getUrl();
            for (String sportName : sports) {
                quickLinksComponent.clickOnSportByName(sportName);
                String url = homePage.getUrl();
                softly.assertThat(url).isNotEqualTo(previousUrl);
                softly.assertThat(homePage.getAllSelectionsInThePage().size()).isGreaterThan(0);
                homePage.getBottonBar().clickOnSportsMenu();
            }
        } else {
            homePage.getHeaderComponent().clickOnShowMore();
            List<String> sports = homePage.getHeaderComponent().getListSports();
            sports.remove(0);
            sports.remove(sports.size() - 1);
            SoftAssertions softly = new SoftAssertions();
            String previousUrl = homePage.getUrl();
            for (String sportName : sports) {
                homePage.getHeaderComponent().clickOnSportByName(sportName);
                String url = homePage.getUrl();
                softly.assertThat(url).isNotEqualTo(previousUrl);
                softly.assertThat(homePage.getAllSelectionsInThePage().size()).isGreaterThan(0);
                homePage.getHeaderComponent().clickOnShowMore();
                previousUrl = url;
            }
            softly.assertAll();
        }
    }

    @Then("^selected event page is displayed$")
    public void selectedEventPageIsDisplayed() throws Throwable {
        String eventId = ScenarioContext.getCurrentEvent().getLocalId();
        assertThat(eventPage.isDisplayed(eventId)).isTrue();
    }

    @And("^the user logs in$")
    public void theUserLogsIn() throws Throwable {
        LoginComponent loginComponent = genericSportsPage.getHeaderComponent().clickOnLoginButton();
        loginComponent.login(properties.getUsername(), properties.getPassword());
        ScenarioContext.saveData("isLoggedIn", true);
    }

    @And("^the user '(.*)' with password '(.*)' logs in$")
    public void theSpecificUserLogsIn(String user, String password) throws Throwable {
        LoginComponent loginComponent = genericSportsPage.getHeaderComponent().clickOnLoginButton();
        loginComponent.login(user, password);
        ScenarioContext.saveData("isLoggedIn", true);
    }

    @When("^the user logs out$")
    public void theUserLogsOut() {
        AccountTabComponent loginComponent = genericSportsPage.getHeaderComponent().clickOnAccountComponent();
        loginComponent.clickOnLogOut();
        ScenarioContext.saveData("isLoggedIn", false);
    }

    @Then("^'(.*)' flag is grayed out$")
    public void languageFlagIsGrayedOut(final String language) throws Throwable {
        LanguageSelectionComponent languageComponent = genericSportsPage.getFooterComponent().clickOnChangeLanguage();
        assertThat(languageComponent.isFlagGrayedOut(language)).isTrue();
    }

    @Then("^the sport is translated to '(.*)' in the url$")
    public void sportIsTranslatedToLanguageInTheUrl(final String translatedWord) throws Throwable {
        assertThat(genericSportsPage.getUrl()).contains(translatedWord);
    }

    @Then("^the Page Title displayed is '(.*)'$")
    public void isPageTitle(final String title) throws Throwable {
        assertThat(genericSportsPage.getHeaderPanelTitlePage()).contains(title);
    }

    @When("^user click on show more in top bets section$")
    public void userClickOnShowMoreInOpenBetsSection() throws Throwable {
        homePage.scrollToTheBottom();
        if (genericSportsPage.isMobile()) {
            topBetsPage.clickOnShowMoreLink();
        } else {
            genericSportsPage.getTopBetsComponent().clickOnShowMoreLink();
        }
    }

    @Then("^the top bets page is displayed$")
    public void theTopBetsPageIsDisplayed() throws Throwable {
        assertThat(topBetsPage.isPageDisplayed()).isTrue();
    }

    @When("^the user refresh the page$")
    public void theUserRefreshThePage() throws Throwable {
        homePage.refreshPage();
    }

    @When("^user clicks on join button in the header$")
    public void userClicksOnJoinButtonInTheHeader() throws Throwable {
        genericSportsPage.getHeaderComponent().clickOnJoinButton();
    }

    // Registration Steps

    @And("^fills the registration form correctly$")
    public void userFillRegistrationForm() throws Throwable {

        User user = new User();

        joinFormComponent.setFirstName(user.getForename());
        joinFormComponent.setSurname(user.getSurname());
        joinFormComponent.setDateOfBirth(user.getDob().split("-")[2], user.getDob().split("-")[1], user.getDob().split("-")[0]);
        joinFormComponent.setEmail(user.getEmail());
        joinFormComponent.setMobile(user.getMobile());
        joinFormComponent.clickOnEnterAddressManualy();
        joinFormComponent.setPostalCode(user.getPostCode());
        joinFormComponent.setAddressLine1(user.getAddress2());
        joinFormComponent.setCity(user.getCity());
        joinFormComponent.setCountry(user.getCountry());

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd hh:mm");
        String strDate = dateFormat.format(date).replace(" ", "_").replace(":", "_");

        joinFormComponent.setUsername("AU_".concat(strDate).concat(RandomStringUtils.randomAlphanumeric(4)));
        joinFormComponent.setPassword("Tester123");
        joinFormComponent.setSecurityQuestion("My first car");
        joinFormComponent.setSecurityAnswer("A Hot Wheels");
        joinFormComponent.checkOver18();
        joinFormComponent.clickOnCreateAccount();
    }

    // End of Registration Steps
    // Deposit Steps

    @And("^the user selects Debit Card payment method$")
    public void userSelectDebitCardPayment() throws Throwable {
        depositFormComponent.clickOnDebitCard();
    }

    @And("^user close the Deposit window$")
    public void userCloseDeposit() throws Throwable {
        depositFormComponent.waitForDepositToLoad();
        depositFormComponent.closeWindow();
    }

    @And("^user click the back button from Deposit$")
    public void userClickOnBackArrowFromDeposit() throws Throwable {
        depositFormComponent.waitForDepositToLoad();
        depositFormComponent.clickOnBackArrow();
    }

    @And("^user makes a deposit$")
    public void userPerformDefaultDeposit() throws Throwable {
        if (!genericSportsPage.isNativeMobileApp()) {
            depositFormComponent.waitForDepositToLoad();
        }
        depositFormComponent.setDebitCardNumber("4929124495824680");
        depositFormComponent.setDebitCardExpireMonth("01");
        depositFormComponent.setDebitCardExpireYear("20");
        depositFormComponent.setSecurityCode("123");
        depositFormComponent.setAmount("10");
        depositFormComponent.clickSecurityCheckbox();
        depositFormComponent.clickDeposit();
        depositFormComponent.clickOnContinuePlaying();
    }

    @And("^user verify that the balance its not 0$")
    public void valanceItsNotZero() throws Throwable {
        assertThat(headerComponent.getBalance().equals("£0")).isFalse();
    }

    // End of Deposit Steps

    @And("^the url contains the words: '(.*)'$")
    public void theUrlContainsTheWords(List<String> wordsList) throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        for (String word : wordsList) {
            softly.assertThat(genericSportsPage.getUrl()).contains(word.toLowerCase());
        }
        softly.assertAll();
    }

    @Then("^the page scroll is in the top$")
    public void thePageScrollIsInTheTop() throws Throwable {
        assertThat(genericSportsPage.isInTheTopPosition()).isTrue();
    }

    @When("^user scroll down to the bottom$")
    public void userScrollDownToTheBottom() throws Throwable {
        genericSportsPage.scrollToTheBottom();
    }


    @When("^user scroll down to the bottom - Page with lazy loading$")
    public void scrollToTheBottomOnLazyLoading() throws Throwable {
        genericSportsPage.scrollToTheBottomOnLazyLoading();
    }

    @When("^the user clicks on 'Join' button$")
    public void theUserClicksOnJoinButton() throws Throwable {
        genericSportsPage.getHeaderComponent().clickOnJoinButton();
    }

    @Then("^the user is logged '(in|out)'$")
    public void theUserIsLoggedIn(final String input) throws Throwable {
        if ("in".equals(input)) {
            assertThat(genericSportsPage.getHeaderComponent().isBalanceDisplayed()).isTrue();
        } else {
            assertThat(genericSportsPage.getHeaderComponent().isJoinNowDisplayed()).isTrue();
        }
    }

    @When("^the user clicks the first active selection in the event page$")
    public void theUserClicksTheFirstActiveSelection() throws Throwable {
        Event event = ScenarioContext.getCurrentEvent();
        if (event != null && event.getMarkets() != null && event.getMarkets().size() > 0) {
            eventPage.clickOnSelection(event.getFirstMarket().getFirstSelection().getPdsId());
            ScenarioContext.saveData("selection", event.getFirstMarket().getFirstSelection().getPdsId());
        } else {
            eventPage.clickOnSelection(eventPage.getActiveSelections().get(0).getPdsId());
            ScenarioContext.saveData("selection", eventPage.getActiveSelections().get(0).getPdsId());
        }

    }

    @Then("^the selection prices are displayed in '(Decimal|Fractional|American)'$")
    public void theSelectionPricesAreDisplayedInOdd_format(final String odd_format) throws Throwable {
        final String price;
        switch (odd_format) {
            case "Fractional":
                price = FRACTIONAL_SYNTAX;
                break;
            case "American":
                price = AMERICAN_SYNTAX;
                break;
            default:
                price = DECIMAL_SYNTAX;
                break;
        }
        //Java 8 lambda --> check that all the selections are in the correct format. If at least one of then is not, if the scenario has tag @bug,
        //throw pending exception
        if (!genericSportsPage.getAllSelectionPricesInThePage().stream().allMatch(p -> p.matches(price)) &&
                ((Scenario) ScenarioContext.getSavedData("scenario")).getSourceTagNames().contains("@bug")) {
            throw new PendingException("Ticket TDRD-226 with this bug");
        }
        assertArrayContainsRegex(price, genericSportsPage.getAllSelectionPricesInThePage());
    }

    @Then("^user changes to the latest browser tab$")
    public void loginFormIsDisplayedInANewTab() throws Throwable {
        genericSportsPage.changeLatestTab();
    }

    @And("^login form las vegas is displayed$")
    public void loginFormLasVegasIsDisplayed() throws Throwable {
        TimeUnit.SECONDS.sleep(1);
        assertThat(genericSportsPage.getHeaderComponent().getLoginComponent().isLoginVegasDisplayed()).isTrue();
    }

    @When("^the user logs in in las vegas$")
    public void theUserLogsInInLasVegas() throws Throwable {
        loginComponent.setUserField(properties.getUsername());
        loginComponent.setPasswordField(properties.getPassword());
        loginComponent.clickOnLoginButtonVegas();
    }

    @Then("^the events '(.*)' (are|are not) displayed$")
    public void checkEventsDisplayed(final List<String> eventList, final String displayed) throws Throwable {
        SoftAssertions softly = new SoftAssertions();

        for (String eventId : eventList) {
            if ("are".equalsIgnoreCase(displayed))
                softly.assertThat(this.genericSportsPage.isEventDisplayed(eventId)).isTrue();
            else {
                softly.assertThat(this.genericSportsPage.isEventDisplayed(eventId)).isFalse();
            }
        }
        softly.assertAll();
    }

    @Then("^user navigates to '(.*)' '(Competitions|In-Play|Coupons|Daily List|Future Races|Specials|Top Bets|Meetings)' page$")
    public void navigateToPageInGivenSport(final String sport, String page) throws Throwable {
        if (("Daily List").equalsIgnoreCase(page)) {
            page = "matches";
        } else if("Future Races".equalsIgnoreCase(page)){
            page = "antepost";
        }

        if(genericSportsPage.isMobile()){
            genericSportsPage.getBottonBar().clickOnSportsMenu().clickOnSportByName(sport);
            genericSportsPage.getCarouselComponent().clickOnGivenCarouselElement(page.toLowerCase());

        } else {
            genericSportsPage.getSportsMenuComponent().clickOnSportPageByName(sport,page);
        }
    }

    @Then("^user navigates to '(.*)' '(Competitions|In-Play|Coupons|Daily List|Future Races|Specials|Top Bets|Meetings)' page by url$")
    public void navigateToPageInGivenSportByUrl(final String sport, String page) throws Throwable {
        if (("Daily List").equalsIgnoreCase(page)) {
            page = "matches";
        } else if("Future Races".equalsIgnoreCase(page)){
            page = "antepost";
        }
        genericSportsPage.navigateToUrl(genericSportsPage.getUrl().concat("/" + sport.toLowerCase().replace(" ", "-")).concat("/" + page.toLowerCase().replace(" ","-")));
    }

    @And("^the (selection|selections) (?:is|are) displayed in (red|green|blue)$")
    public void theSelectionIsInDisplayedInColour(final String sel, final String colour) throws Throwable {

        if ("selection".equalsIgnoreCase(sel)) {
            Selection selection = (Selection) ScenarioContext.getSavedData("selection");
            if ("red".contains(colour)) {
                assertThat(genericSportsPage.isSelectionInRed(selection.getPdsId())).isTrue();
            } else if ("green".contains(colour)) {
                assertThat(genericSportsPage.isSelectionInGreen(selection.getPdsId())).isTrue();
            } else {
                assertThat(genericSportsPage.isSelectionInBlue(selection.getPdsId())).isTrue();
            }
        } else {
            List<Selection> selections = (List<Selection>) ScenarioContext.getSavedData("selections");
            SoftAssertions soft = new SoftAssertions();
            for (Selection selection : selections) {
                if ("red".contains(colour)) {
                    soft.assertThat(genericSportsPage.isSelectionInRed(selection.getPdsId())).isTrue();
                } else if ("green".contains(colour)) {
                    soft.assertThat(genericSportsPage.isSelectionInGreen(selection.getPdsId())).isTrue();
                } else {
                    soft.assertThat(genericSportsPage.isSelectionInBlue(selection.getPdsId())).isTrue();
                }
            }
            soft.assertAll();
        }
    }

    @And("^the user clicks on the active selection$")
    public void clinkOnTheSelection() throws Throwable {
        genericSportsPage.clickOnSelection(((Selection) ScenarioContext.getSavedData("selection")).getPdsId());
    }

    @And("^sleep '(.*)' seconds$")
    public void sleepSeconds(final String seconds) throws Throwable {
        genericSportsPage.sleep(Long.parseLong(seconds) * 1000);
    }

    @And("^sleep '(.*)' milliseconds$")
    public void sleepMilliseconds(final String milliseconds) throws Throwable {
        genericSportsPage.sleep(Long.parseLong(milliseconds));
    }

    @When("^the selection price is (increased|decreased)$")
    public void theSelectionPriceIsIncreased(final String option) throws Throwable {
        Selection selection = (Selection) ScenarioContext.getSavedData("selection");
        MockedPush mockedPush = new MockedPush();
        String newPrice;
        if ("increased".equalsIgnoreCase(option))
            newPrice = mockedPush.increaseSelectionPrice(selection.getPdsId(), selection.getPrice());
        else
            newPrice = mockedPush.decreaseSelectionPrice(selection.getPdsId(), selection.getPrice());

        ScenarioContext.saveData("newPrice", newPrice);
    }

    @Then("^the selection (is|is not) grayed out$")
    public void theSelectionIsGrayedOut(String grayedOut) throws Throwable {
        Selection selection = (Selection) ScenarioContext.getSavedData("selection");
        TimeUnit.SECONDS.sleep(1);
        if ("is".equals(grayedOut))
            assertThat(genericSportsPage.isSelectionGrayedOut(selection.getPdsId())).isTrue();
        else
            assertThat(genericSportsPage.isSelectionGrayedOut(selection.getPdsId())).isFalse();
    }

    @Then("^the selection (is|is not) clickable$")
    public void theSelectionIsClickable(String isClickable) {
        Selection selection = (Selection) ScenarioContext.getSavedData("selection");

        if ("is".equalsIgnoreCase(isClickable))
            assertThat(genericSportsPage.isSelectionClickable(selection.getPdsId())).isTrue();
        else
            assertThat(genericSportsPage.isSelectionClickable(selection.getPdsId())).isFalse();
    }

    @Then("^the (event|market) selections (are|are not) clickable$")
    public void theSelectionsInEventAreClickable(String type, String areClickable) {
        Event event;
        Market market;
        String pdsId = "";
        if (ScenarioContext.getSavedData("event") != null) {
            event = (Event) ScenarioContext.getSavedData("event");
            pdsId = event.getPdsId();
        }
        if (ScenarioContext.getSavedData("market") != null) {
            market = (Market) ScenarioContext.getSavedData("market");
            pdsId = market.getPdsId();
        }
        if ("are".equalsIgnoreCase(areClickable))
            assertThat(genericSportsPage.areSelectionsInTheEventClickable(pdsId)).isTrue();
        else
            assertThat(genericSportsPage.areSelectionsInTheEventClickable(pdsId)).isFalse();
    }

    @Then("^the (event|market) selections (are|are not) grayed out$")
    public void theEventAreGrayedOut(String type, String grayedOut) throws Throwable {
        Event event;
        Market market;
        String pdsId = "";

        switch (type) {
            case "event":
                event = (Event) ScenarioContext.getSavedData("event");
                pdsId = event.getPdsId();
                break;
            case "market":
                market = (Market) ScenarioContext.getSavedData("market");
                pdsId = market.getPdsId();
                break;
            default:
                break;
        }

        TimeUnit.SECONDS.sleep(1);
        if (Msg.ARE.equals(grayedOut))
            assertThat(genericSportsPage.areSelectionsInTheEventOrMarketGrayedOut(pdsId)).isTrue();
        else
            assertThat(genericSportsPage.areSelectionsInTheEventOrMarketGrayedOut(pdsId)).isFalse();
    }

    @Then("^the (event|market) selections (are|are not) grayed out in blue$")
    public void theMarketAreGrayedOut(String type, String grayedOut) throws Throwable {
        Event event;
        Market market;
        String pdsId = "";
        if (ScenarioContext.getSavedData("event") != null) {
            event = (Event) ScenarioContext.getSavedData("event");
            pdsId = event.getPdsId();
        }
        if (ScenarioContext.getSavedData("market") != null) {
            market = (Market) ScenarioContext.getSavedData("market");
            pdsId = market.getPdsId();
        }
        TimeUnit.SECONDS.sleep(1);
        if (Msg.ARE.equals(grayedOut))
            assertThat(genericSportsPage.areSelectionsInTheEventOrMarketGrayedOutInBlue(pdsId)).isTrue();
        else
            assertThat(genericSportsPage.areSelectionsInTheEventOrMarketGrayedOutInBlue(pdsId)).isFalse();
    }

    @And("^user selects '(.*)' from sports menu$")
    public void userSelectsFootballFromSportsMenu(String sport) throws Throwable {
        userGoesToSportsPage(sport);
    }

    @When("^user clicks on the (first|last) available selection$")
    public void clickFirstSelection(final String sel) throws Throwable {
        Selection selection;
        if("first".equalsIgnoreCase(sel))
            selection = genericSportsPage.clickOnTheFirstAvailableSelection();
        else
            selection = genericSportsPage.clickOnTheLastAvailableSelection();
        ScenarioContext.saveData("selection", selection);
    }

    @When("^user clicks on the '(.*)(st|nd|rd|th)' available selection$")
    public void clickOnGivenSelectionNumber(final int selectionNumber, final String classyEnding) {
        genericSportsPage.clickOnSelectionByIndex(selectionNumber);
    }

    @When("^user adds '(.*)' selection/s to the Betslip$")
    public void addGivenNumberOfSelectionsToBetslip(final int numberOfSelections) {
        genericSportsPage.clickOnGivenNumberOfSelections(numberOfSelections);
    }

    @When("^user adds '(.*)' of the 1st selection/s from the Market/s to the Betslip$")
    public void clickOnlyOnFirstSelectionFromMarkets(final int numberOfSelections) {
        genericSportsPage.clickOnlyOnFirstSelectionFromMarkets(numberOfSelections);
    }

    @Then("^the saved event (is|is not) displayed$")
    public void checkEventIsDisplayed(final String displayed) throws Throwable {
        Event event = (Event) ScenarioContext.getSavedData("event");
        if (Msg.IS.equalsIgnoreCase(displayed))
            assertThat(genericSportsPage.isEventDisplayed(event.getPdsId())).isTrue();
        else {
            assertThat(genericSportsPage.isEventDisplayed(event.getPdsId())).isFalse();
        }
    }

    @When("^user clicks on back button$")
    public void userClicksOnBackButton() throws Throwable {
        genericSportsPage.clickBackButton();
    }

    @When("^the secondary header '(is|is not)' displayed$")
    public void isSecondaryHeaderDisplayed(final String input) throws Throwable {
        if("is".equals(input))
            assertThat(genericSportsPage.isSecondaryHeaderDisplayed()).isTrue();
        else
            assertThat(genericSportsPage.isSecondaryHeaderDisplayed()).isFalse();
    }

    @And("^the page secondary header is '(.*)'$")
    public void verifySecondaryHeader(final String input) throws Throwable {
        assertThat(genericSportsPage.getSecondaryHeaderText()).isEqualTo(input);
    }

    @And("^back button '(is|is not)' displayed$")
    public void isBackButtonDisplayed(final String input) throws Throwable {
        if (Msg.IS.equals(input)) {
            assertThat(genericSportsPage.isBackButtonDisplayed()).isTrue();
        } else {
            assertThat(genericSportsPage.isBackButtonDisplayed()).isFalse();
        }
    }

    @When("^the user clicks on the first event$")
    public void clickOnFirstEvent() {
        ScenarioContext.setCurrentEvent(genericSportsPage.clickOnTheFirstEvent());
    }

    @And("^there is an active selection$")
    public void thereIsActiveSelection() throws Throwable {
        List<Selection> selectionList = genericSportsPage.getActiveSelections();
        assertThat(selectionList).size().isNotZero();
        ScenarioContext.saveData("selection", selectionList.get(0));
    }

    @Given("^a new logged in user$")
    public void createAndUserLogsIn() throws Throwable {
        BackOfficeUtils boUtils = BackOfficeUtils.getInstance();
        User user = boUtils.createUser();
        ScenarioContext.setCurrentUser(user);
        homePage.open();
        LoginComponent loginComponent = genericSportsPage.getHeaderComponent().clickOnLoginButton();
        loginComponent.login(user.getUsername(), user.getPassword());
        String newPassword = user.getPassword().concat("123");
        homePage.changeTemporaryPassword(user.getPassword(), newPassword);
        user.setPassword(newPassword);
    }

    @And("^there are '(.*)' selections selected")
    public void checkSelectionsSelected(final String selections) {
        int number = Integer.parseInt(selections);
        assertThat(genericSportsPage.getSelectionsSelected()).isEqualTo(number);
    }

    @And("^user clicks on No Goalscorer$")
    public void clickOnNoScorer() {
        eventPage.clickOnNoGoalscorer();
    }

    @And("^the selection (is|is not) displayed$")
    public void checkSelection(final String displayed) {
        Selection selection = (Selection) ScenarioContext.getSavedData("selection");
        if (Msg.IS.equals(displayed)) {
            assertThat(eventPage.isEventDisplayed(selection.getPdsId())).isTrue();
        } else {
            assertThat(eventPage.isEventDisplayed(selection.getPdsId())).isFalse();
        }
    }

    @And("^the market (is|is not) displayed$")
    public void checkMarket(final String displayed) {
        Market market = (Market) ScenarioContext.getSavedData("market");
        if (Msg.IS.equals(displayed)) {
            assertThat(eventPage.isMarketDisplayed(market.getPdsId())).isTrue();
        } else {
            assertThat(eventPage.isEventDisplayed(market.getPdsId())).isFalse();
        }
    }

    @And("^the event markets (are|are not) displayed$")
    public void checkMarketsInEvent(final String displayed) {
        Event event = (Event) ScenarioContext.getSavedData("event");
        SoftAssertions softly = new SoftAssertions();

        List<Market> marketList = genericSportsPage.getMarketsFromEvent(event.getPdsId());
        for (Market market : marketList) {
            if (Msg.ARE.equalsIgnoreCase(displayed))
                softly.assertThat(this.genericSportsPage.isMarketDisplayed(market.getPdsId())).isTrue();
            else {
                softly.assertThat(this.genericSportsPage.isMarketDisplayed(market.getPdsId())).isFalse();
            }
        }
    }

    @Then("^the login component is loaded properly$")
    public void theLoginComponentIsLoadedProperly() throws Throwable {
        assertThat(loginComponent.isLoaded()).isTrue();
    }

    @And("^the user clicks on the first '(highlight|in-play)' event$")
    public void theUserClicksOnTheFirstEventType(final String eventType) throws Throwable {
        String eventId = genericSportsPage.getEventActive(eventType);
        Event ev = new Event(eventId);
        genericSportsPage.clickOnEventById(eventId);
        ScenarioContext.setCurrentEvent(ev);
    }

    @And("^there is an (pre-match|in-play) event with an active (selection|market)$")
    public void thereIsAEventWithActiveSelection(final String eventType, final String typeActive) throws Throwable {
        if ("market".equals(typeActive)) {
            String marketId = genericSportsPage.getMarketActive(eventType);
            Market market = new Market(marketId);
            ScenarioContext.saveData("market", market);
        } else {
            Selection selection = genericSportsPage.getSelectionActiveByType(eventType);
            ScenarioContext.saveData("selection", selection);
        }
    }

    @And("^there is an (pre-match|in-play) active event$")
    public void thereIsAEvent(final String eventType) throws Throwable {
        String eventId = genericSportsPage.getEventActive(eventType);
        Event event = new Event(eventId);
        ScenarioContext.saveData("event", event);
    }

    @And("^user goes to created '(.*)' event$")
    public void userGoesToCreatedEvent(final String sport) throws Throwable {
        Event event = ScenarioContext.getCurrentEvent();
        homePage.navigateToUrl(homePage.getUrl().concat("/").concat(sport).concat("/").concat(event.getPdsId()));
        genericSportsPage.waitEventDisplayed(event.getPdsId());
    }

    @And("^the first Events to be displayed are from '(Horse Racing|Greyhounds)'$")
    public void firtstEventsDisplayedOnTopBetsShouldBe(final String sport) throws Throwable {

        List<String> sportsDisplayed = topBetsPage.getTopBetEventsSportName();
        for (int i = 0; i < sportsDisplayed.size(); i++) {

            if (!sportsDisplayed.get(i).equals(sport)) {
                for (int j = i; j < sportsDisplayed.size(); j++) {
                    assertThat(sportsDisplayed.get(j)).as(Msg.INCORRECT_FIRST_SPORTS_DISPLAYED).doesNotContain(sport);
                }
            } else {
                assertThat(sportsDisplayed.get(i)).matches(sport);
            }
        }
    }


    @Then("^verify that footer menu disappears when user scrolls down$")
    public void verifyFooterMenuDissapearsWhenScrollDown() {
        genericSportsPage.scrollToTheBottom();
        assertThat(bottomBarComponent.isDisplayed()).isFalse();
    }

    @And("^the footer menu '(is|is not)' displayed$")
    public void isFooterMenuDisplayed(final String input) throws Throwable {
        if (Msg.IS.equals(input)) {
            assertThat(bottomBarComponent.isDisplayed()).isTrue();
        } else {
            assertThat(bottomBarComponent.isDisplayed()).isFalse();
        }
    }

    @Then("^A to Z Sports menu is being displayed in the footer menu$")
    public void isSportMenuDisplayedInBottonBar() {
        assertThat(bottomBarComponent.isSportsMenuDisplayed()).isTrue();
    }

    @And("^Open Bets is being displayed in the footer menu$")
    public void isOpenBetsMenuDisplayedInBottonBar() {
        assertThat(bottomBarComponent.isOpenBetsMenuDisplayed()).isTrue();
    }

    @And("^Betslip is being displayed in the footer menu$")
    public void isBetSlipMenuDisplayedInBottonBar() {
        assertThat(bottomBarComponent.isBetSlipMenuDisplayed()).isTrue();
    }

    @And("^Search is being displayed in the footer menu$")
    public void isSearchMenuDisplayedInBottonBar() {
        assertThat(bottomBarComponent.isSearchMenuDisplayed()).isTrue();
    }

    @And("^Top Games menu is displayed in the footer menu$")
    public void isTopGamesMenuDisplayedInBottonBar() {
        assertThat(bottomBarComponent.isTopGamesMenuDisplayed()).isTrue();
    }

    @Then("^correct components are available in '(Featured|Competitions|Coupons|Daily List|In-Play|event|All In-Play|Virtual World|Top Bets|Homepage|Promotions)' page$")
    public void correctSectionsAvailableInPage(final String page) throws Throwable {
        SoftAssertions softAssertion = new SoftAssertions();
//        TODO: We need to add verifications for mobile, not only desktop
        if (genericSportsPage.isMobile()) {
            softAssertion.assertThat(betSlipComponent.isDisplayed()).as("Betslip is displayed").isFalse();
            softAssertion.assertThat(sportsMenuComponent.isDisplayed()).as("Sport menu component is displayed").isFalse();
            softAssertion.assertThat(featuredPage.isBackButtonDisplayed()).isTrue().as("Back button is not displayed");
        } else {
            softAssertion.assertThat(genericSportsPage.getCarouselComponent().isDisplayed()).as("Carousel is displayed").isFalse();
            softAssertion.assertThat(sportsMenuComponent.isDisplayed()).as("Sport menu component is not displayed").isTrue();
        }
        switch (page.toLowerCase()) {
            case Msg.FEATURED:
                softAssertion.assertThat(featuredPage.isDisplayed()).as("Featured page not displayed correctly").isTrue();
                if (!featuredPage.isMobile()) {
                    softAssertion.assertThat(betSlipComponent.isDisplayed()).as("Right nav is not displayed").isTrue();
                    softAssertion.assertThat(topBetsComponent.isDisplayed()).as("Top bets component is not displayed").isTrue();
                }
                softAssertion.assertThat(featuredPage.getHighlightsSection().isDisplayed() || featuredPage.getInPlaySection().isDisplayed()).as("Highlights and In-Play sections are not displayed").isTrue();

                break;

            case Msg.COMPETITIONS:
                softAssertion.assertThat(competitionsPage.isDisplayed()).as("Competitions page not displayed correctly").isTrue();
                if (!competitionsPage.isMobile()) {
                    softAssertion.assertThat(competitionsPage.getSportsMenuComponent().isCompetitionsSelected()).as("Competitions tab not selected").isTrue();
                    softAssertion.assertThat(betSlipComponent.isDisplayed()).as("Right nav is not displayed").isTrue();
                    softAssertion.assertThat(topBetsComponent.isDisplayed()).as("Top bets component is not displayed").isTrue();
                }
                break;

            case Msg.COUPONS:

                // Here we verify that the Coupon Menu is displayed
                softAssertion.assertThat(couponsPage.isCouponListDisplayed()).as("The Coupon Menu its not being displayed").isTrue();

                // Here we verify that the View By is NOT displayed
                throwKnownIssueExceptionIfNeeded(couponsPage.isViewByDisplayed(),
                        "TDRD-378: Sort by menu appears in coupons menu in mobile view");
                softAssertion.assertThat(couponsPage.isViewByDisplayed()).as("The View By is being displayed on Coupon Menu").isFalse();


                // Here we verify that the Back button is displayed for Mobile
                if (couponsPage.isMobile()) {
                    throwKnownIssueExceptionIfNeeded(!couponsPage.isBackButtonDisplayed(), "TDRD-485: Back button displayed twice");
                    softAssertion.assertThat(couponsPage.isBackButtonDisplayed()).as("The Back Button is not being displayed").isTrue();
                }
                break;


            case Msg.IN_PLAY:
                softAssertion.assertThat(inPlayPage.isDisplayed()).as("In Play page not displayed correctly").isTrue();
                if (!inPlayPage.isMobile()) {
                    softAssertion.assertThat(topBetsComponent.isDisplayed()).as("Top bets component is not displayed").isTrue();
                    throwKnownIssueExceptionIfNeeded(!inPlayPage.getSportsMenuComponent().isInPlaySelected(), "In play tab not selected due to TDRD-494");
                    softAssertion.assertThat(inPlayPage.getSportsMenuComponent().isInPlaySelected()).as("In-Play tab not selected").isTrue();
                    softAssertion.assertThat(betSlipComponent.isDisplayed()).as("Right nav is not displayed").isTrue();
                }
                break;

            case Msg.DAILY_LIST:
//                TODO:to be completed by this:
//                We need to ensure that we verify that correct components are page title, back button in case of mobile
//                view, day filter scroller, View By menu, alternate market menu (only in football, tennis, darts and
//                cricket) and events list
                softAssertion.assertThat(dailyListPage.isDisplayed()).as("Daily List is not displayed").isTrue();
                assertThat(dailyListPage.headerPanelTitlePageIsDisplayed() && dailyListPage.getHeaderPanelTitlePage().toLowerCase().contains("daily list")).isTrue();
                if (!dailyListPage.isMobile()) {
                    softAssertion.assertThat(dailyListPage.getSportsMenuComponent().isDailyListSelected()).as("Daily List tab not selected").isTrue();
                    softAssertion.assertThat(betSlipComponent.isDisplayed()).as("Right nav is not displayed").isTrue();
                    softAssertion.assertThat(topBetsComponent.isDisplayed()).as("Top bets component is not displayed").isTrue();
                }
                break;

            case Msg.EVENT:
//                TODO: We need to verify that collections menu is available in this page
                if (genericSportsPage.getCurrentPageName().equals("events")) {
                    softAssertion.assertThat(eventPage.isDisplayed()).as("Event page is not displayed").isTrue();
                } else {
                    softAssertion.assertThat(racecardPage.isDisplayed()).as("Event page is not displayed").isTrue();
                }
                if (!eventPage.isMobile()) {
                    softAssertion.assertThat(topBetsComponent.isDisplayed()).as("Top bets component is not displayed").isTrue();
                    softAssertion.assertThat(betSlipComponent.isDisplayed()).as("Right nav is not displayed").isTrue();
                }
                break;

            case Msg.ALL_IN_PLAY:
                if (!genericSportsPage.isMobile()) {
                    softAssertion.assertThat(betSlipComponent.isDisplayed()).as("Betslip component").isTrue();
                    softAssertion.assertThat(genericSportsPage.getCarouselComponent().isDisplayed()).as("Carousel").isFalse();
                    softAssertion.assertThat(genericSportsPage.getSportsMenuComponent().isDisplayed()).as("Sports menu").isTrue();
                } else {
                    softAssertion.assertThat(betSlipComponent.isDisplayed()).as("Betslip component").isFalse();
                    softAssertion.assertThat(allInPlayPage.isCarouselDisplayed()).as("Carousel").isTrue();
                    softAssertion.assertThat(genericSportsPage.getSportsMenuComponent().isDisplayed()).as("Sports menu").isFalse();
                }
                break;

            case Msg.VIRTUAL_WORLD:
//                TODO:to be completed by this:
//                *****Desktop*****
//                We need to ensure that correct components are in virtual page: Virtual World as title and virtual sport
//                name as subtitle, streaming and and virtual sport content, and child pages are displayed under Virtual
//                World in sports menu
//                *****Mobile*****
//                We need to ensure that we verify that correct components in mobile are secondary header with back button and Virtual
//                World as title, virtual carousel and virtual sport content
                if (!genericSportsPage.isMobile()) {
                    softAssertion.assertThat(virtualsPage.isPreloaderDisplayed()).as(Msg.VIRTUAL_PRELOADER_DISPLAYED).isFalse();
                    softAssertion.assertThat(virtualsPage.isVirtualsTitleDisplayed()).as(Msg.VIRTUAL_TITLE_DISPLAYED).isTrue();
                    softAssertion.assertThat(virtualsPage.isVirtualWorldHeaderDisplayed()).as(Msg.VIRTUAL_HEADER_DISPLAYED).isTrue();
                }
                break;


            case Msg.TOP_BETS:
//
                softAssertion.assertThat(topBetsPage.isDisplayed()).as("top bet page").isTrue();
                softAssertion.assertThat(topBetsComponent.isDisplayed()).as("Top bet component").isFalse();
                if(!genericSportsPage.isMobile())
                    assertThat(sportsMenuComponent.isTopBetsSelected()).as("Top bet highlighted").isTrue();
                break;

            default:
                softAssertion.assertThat(false).as("This page hasn't been implemented").isTrue();
        }
        softAssertion.assertAll();
    }


    @And("^the user remembers selection name$")
    public void theUserRemembersSelectionName() throws Throwable {
        String selectionLabel = genericSportsPage.getLabelFromSelectionId(ScenarioContext.getSavedData("selection").toString());
        ScenarioContext.saveData("selectionLabel", selectionLabel);
    }
}