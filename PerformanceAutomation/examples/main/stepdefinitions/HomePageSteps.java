package com.whgtf.sportsbook.main.stepdefinitions;

import com.whgtf.sportsbook.main.beans.Data;
import com.whgtf.sportsbook.main.beans.HomePageIcon;
import com.whgtf.sportsbook.main.beans.SquizCarouselData;
import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.whgtf.sportsbook.main.util.TranslationUtils.LANGUAGE_URL_MAP;
import static com.whgtf.sportsbook.pom.utils.Assertions.assertArrayContains;
import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class HomePageSteps extends MainSteps {


    @And("^user switches to '(.*)'$")
    public void userSwitchToLanguage(final String language) throws Throwable {
        throwKnownIssueExceptionIfNeeded(!homePage.getFooterComponent().isLanguageDisplayed(language), "SBNPI-6: Languages not set up on test environments");
        homePage.getFooterComponent().clickOnChangeLanguage().clickOnLanguage(language);
    }

    @Then("^HomePage is displayed in '(.*)'$")
    public void homepageIsDisplayedInLanguage(final String language) throws Throwable {
        homePage.waitToBeLoaded();
        assertThat(homePage.isDisplayedInLanguage(language)).isTrue();
    }

    @And("^the '(.*)' flag icon is displayed in the Home Page$")
    public void theLanguageFlagIconIsDisplayedInTheHomePage(final String language) throws Throwable {
        homePage.getFooterComponent().isLanguageFlagDisplayed(language);
    }

    @Then("^all the links are in '(.*)' in the Home Page$")
    public void allTheLinksAreInLanguageInTheHomePage(final String language) throws Throwable {
        assertArrayContains(LANGUAGE_URL_MAP.get(language), homePage.getAllLinksInThePage());
    }

    @And("^the top bets widget '(is|is not)' displayed$")
    public void theTopBetsWidgetIsNotDisplayed(final String isIsnot) throws Throwable {
//        TODO: shouldn't we scroll to the bottom only in mobile. In desktop, top bets widget is under betslip
        homePage.scrollToTheBottom();
        if(!homePage.isMobile()) {
            if ("is".equals(isIsnot)){
                assertThat(homePage.getTopBetsComponent().isDisplayed()).isTrue();
            }else{
                assertThat(homePage.getTopBetsComponent().isDisplayed()).isFalse();
            }
        }else{
            if ("is".equals(isIsnot)){
                assertThat(homePage.getTopBetsComponent().isDisplayed()).isTrue();
            }else{
                assertThat(homePage.getTopBetsComponent().isDisplayed()).isFalse();
            }
        }
    }

    @Then("^Homepage tabs info is taken from CEI$")
    public void verifyHomepageTabs() throws Throwable {

        Data jsonTabs = jsonReader.readJSON(properties.getSquizHomepageTabs(), Data.class);
        ScenarioContext.saveData("jsonTabs",jsonTabs);

    }

    @Then("^correct tabs are displayed in Homepage$")
    public void verifyTabsDisplayedInHomepage() throws Throwable {

        Data jsonTabs = (Data) ScenarioContext.getSavedData("jsonTabs");
        List<String> currentHomepageTabs = tabbedHighlightsComponent.getListOfTabs();

        int iterator = 0;

        for (String tabText:currentHomepageTabs) {

            if(jsonTabs.getData().getTabs().get(iterator).getEnabled() == true) {
                assertThat(tabText).contains(jsonTabs.getData().getTabs().get(iterator).getText());
                iterator++;
            }
        }
    }

    @And("^homepage tabs are not displayed$")
    public void homepageTabsNotDisplayed() throws Throwable {
        assertThat(tabbedHighlightsComponent.isDisplayed()).isFalse();
    }

    @And("^user selects '(.*)' from Homepage carousel$")
    public void userSelectFromHomepageCarousel(String input) throws Throwable {
        homePage.clickOnGivenCarouselElement(input);
    }


    @Then("^correct components are displayed in Home page$")
    public void correctComponentsAreDisplayed() throws Throwable {
        if(!homePage.isMobile()) {
            assertThat(betSlipComponent.isDisplayed()).as("Betslip component").isTrue();
            assertThat(homePage.getSportsMenuComponent().isDisplayed()).as("Sports menu").isTrue();
            assertThat(tabbedHighlightsComponent.isDisplayed()).as("Homepage tabs is displayed").isFalse();
        } else {
            assertThat(betSlipComponent.isDisplayed()).as("Betslip component").isFalse();
            assertThat(homePage.getSportsMenuComponent().isDisplayed()).as("Sports menu").isFalse();
            assertThat(tabbedHighlightsComponent.isDisplayed()).as("Homepage tabs is not displayed").isTrue();
        }
        assertThat(homePage.getCarouselComponent().isDisplayed()).as("Carousel").isTrue();
        assertThat(homePage.isOddFormatDisplayed()).as("Odd Format displayed").isTrue();
        assertThat(homePage.isBackButtonDisplayed()).as("Backbutton is displayed").isFalse();
    }


    @Then("^options in homepage carousel are displayed correctly$")
    public void optionsInHomepageCarouselAreDisplayedInCorrectOrder() throws Throwable {
        SquizCarouselData squizCarouselData = jsonReader.readJSON(properties.getSquizHomepageCarouselIcons(), SquizCarouselData.class);
        List<HomePageIcon> iconList = Arrays.asList(squizCarouselData.getData());
        List<HomePageIcon> resultList = iconList.stream().filter(x -> x.isEnabled()).collect(Collectors.toList());
        List<String> iconIdList = homePage.getCarouselComponent().getIconsText();
        SoftAssertions softAssertion = new SoftAssertions();
        int lenght = 0;
        if(!homePage.isMobile()) {
            assertThat(resultList.stream().collect(Collectors.toList()).subList(0, 10)).size().isEqualTo(10).as("icon list size is different");
            lenght = 10;
        }
        else {
            assertThat(resultList).size().isEqualTo(iconIdList.size()).as("icon list size is different");
            lenght = resultList.size();
        }
        int pos = 0;
        for (HomePageIcon icon : resultList) {
            if (resultList.get(pos).getText() != null && !resultList.get(pos).getText().isEmpty())
                softAssertion.assertThat(icon.getText()).isEqualToIgnoringCase(iconIdList.get(pos));
            else if (icon.getGameNameId() != null)
                softAssertion.assertThat(icon.getGameNameId()).isEqualToIgnoringCase(iconIdList.get(pos));
            else
                softAssertion.assertThat("Live").isEqualToIgnoringCase(iconIdList.get(pos));
            pos++;
            if(pos>=lenght)
                break;
        }
        softAssertion.assertAll();

    }
}
