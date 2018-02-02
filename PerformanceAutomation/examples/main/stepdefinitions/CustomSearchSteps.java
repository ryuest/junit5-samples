package com.whgtf.sportsbook.main.stepdefinitions;

import com.mashape.unirest.http.exceptions.UnirestException;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import squiz.CustomSearchSquizAPI;
import util.CustomSearchBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Component
@Lazy
public class CustomSearchSteps extends MainSteps {
    private java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomSearchSteps.class.getName());
    private static String selection_016_Image = "https://cmssports.staticcache.org/assets/image/0006/410757/TVSpecials-Blank.PNG";
    private CustomSearchSquizAPI customSearchSquizAPI;
    private static String id;

    private static CustomSearchBean customSearchBean;
    String customSearchId;

    public CustomSearchSteps() {
        customSearchSquizAPI = new CustomSearchSquizAPI();
        customSearchBean = new CustomSearchBean();
    }

    @When("^User Creates and (not Publishes|Publishes) custom search$")
    public void user_enters_the_below_details_for_new_custom_search(String publishOrNotPublish, List<CustomSearchBean> list) throws Throwable {
        customSearchBean = list.get(0);
        long ext = System.currentTimeMillis();

        customSearchBean.setIconImage(selection_016_Image);

        boolean isPublish = true;

        if (publishOrNotPublish.contains("not")) {
            isPublish = false;
        }

        customSearchBean.setIconText(customSearchBean.getIconText() + ext);
        customSearchBean.setSearchText(customSearchBean.getSearchText() + ext);

        id = customSearchSquizAPI.createCustomSearch(customSearchBean, isPublish);
        logger.info("New Custom Search rule is created in squiz. ID :" + id);
    }

    @When("^User Edits and (not Publishes|Publishes) custom search$")
    public void user_enters_the_below_details_for_edit_custom_search(String publishOrNotPublish, List<CustomSearchBean> list) throws Throwable {
        CustomSearchBean modifiedObj = null;
        if (customSearchBean != null && !StringUtils.isEmpty(customSearchBean.getSearchText())) {
            modifiedObj = customSearchBean;
        }

        customSearchBean = new CustomSearchBean();
        customSearchBean = list.get(0);

        if (modifiedObj != null) {
            customSearchBean.setRuleName(modifiedObj.getRuleName());
        }
        customSearchBean.setIconImage(selection_016_Image);

        boolean isPublish = true;

        if (publishOrNotPublish.contains("not")) {
            isPublish = false;
        }

        if (modifiedObj != null) {
            customSearchBean.setIconText(modifiedObj.getIconText());
            customSearchBean.setSearchText(modifiedObj.getSearchText());
        }

        customSearchSquizAPI.editCustomSearch(customSearchBean, isPublish, id);
    }

    @When("^User deletes custom search$")
    public void when_user_deletes() throws UnirestException {
        customSearchSquizAPI.deleteCustomSearch(id);
    }

    @When("^User searches for created custom search$")
    public void searchForCusomSearch() throws Throwable {
        homePage.open();
        homePage.sleep(1000);
        searchComponent.open().search(customSearchBean.getSearchText());
    }

    @Then("^Custom search is (displayed|not displayed) as part of sports results$")
    public void matchingSportsIconsAreDisplayed(String isExpected) {
        SoftAssertions softly = new SoftAssertions();
        if (isExpected.equals("not displayed")) {
            softly.assertThat(searchComponent.isCustomSearchIconDisplayed(customSearchBean.getIconText(),customSearchBean.getIconImage())).isFalse();
            softly.assertThat(searchComponent.isSportsResultDisplayed(customSearchBean.getIconText())).isFalse();
        } else {
            softly.assertThat(searchComponent.isCustomSearchIconDisplayed(customSearchBean.getIconText(),customSearchBean.getIconImage())).isTrue();
            softly.assertThat(searchComponent.isSportsResultDisplayed(customSearchBean.getIconText())).isTrue();
        }
        softly.assertAll();
    }

    @When("^User searches with case sensitive$")
    public void user_searches_with_case_sensitive() throws Throwable {
        customSearchBean.setSearchText(customSearchBean.getSearchText().toUpperCase());
        searchForCusomSearch();
    }

    @When("Selects the displayed custom search image link")
    public void selects_displayed_sutom_search_image() {
        searchComponent.clickOnSportsResultIcon(customSearchBean.getIconText());
    }

    @After("@custom_search")
    public void deleteRule() throws UnirestException {
        logger.info("Custom Search rule was requested to delete:" + customSearchBean.toString());
        customSearchSquizAPI.deleteCustomSearch(id);
        logger.info("Delete successful.");
    }

    @Then("Redirects to link url")
    public void redirects_to_url() {
        searchComponent.sleep(1000);
        assertThat(homePage.getUrl()).contains(customSearchBean.getUrl());
    }

    @Then("Navigates to link url in new tab")
    public void navigates_to_url() {
        assertThat(searchComponent.isSportsIconLinkOpenedInNewTab(customSearchBean.getUrl())).isTrue();
    }
}