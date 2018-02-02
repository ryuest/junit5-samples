package com.whgtf.sportsbook.main.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;


@Component
@Lazy
public class RecentSearchesSteps extends MainSteps {
    private static final Logger logger =
            Logger.getLogger(RecentSearchesSteps.class.getName());
    private String recentSearchItem;

    public RecentSearchesSteps(){
    }

    @Given("^the browser did not have any recent searches$")
    public void the_browser_did_not_have_any_recent_searches() throws Throwable {
        searchComponent.clearBrowserLocalStore();
    }

    @Then("^recent searches container (is not|is) displayed$")
    public void recent_searches_container_is_not_displayed(String param) throws Throwable {
        if(param.contains("not")){
            assertThat(searchComponent.isRecentSearchesContainerDisplayed()).isFalse();
        }else{
            assertThat(searchComponent.isRecentSearchesContainerDisplayed()).isTrue();
        }
    }

    @Given("^the browser have recent searches$")
    public void the_browser_have_recent_searches() throws Throwable {
        searchComponent.open().search("bask");
        searchComponent.clickOnResultLink(1);
    }

    @Then("^\"([^\"]*)\" header is displayed above the recent search items$")
    public void header_is_displayed_above_the_recent_search_items(String header) throws Throwable {
        assertThat(searchComponent.getRecentSearchesHeader()).contains(header);
    }

    @Given("^recent search container is displayed with recent searches$")
    public void recent_search_container_is_displayed_with_recent_searches() throws Throwable {
        the_browser_have_recent_searches();
        searchComponent.open();
    }

    @When("^user selects any of the recent search item$")
    public void user_selects_any_of_the_recent_search_item() throws Throwable {
        recentSearchItem = searchComponent.getRecentSearches().get(0);
        searchComponent.selectRecentSearch(0);
    }

    @Then("^search results are displayed for the item$")
    public void search_results_are_displayed_for_the_item() throws Throwable {
       assertThat(searchComponent.isEventsCountDisplayed() || searchComponent.isMessagePresent("No Results Found")).isTrue();
    }

    @Then("^the selected search term is present in the search input$")
    public void the_selected_search_term_is_present_in_the_search_input() throws Throwable {
       assertThat(searchComponent.getSearchText()).isEqualTo(recentSearchItem);
    }

    @When("^recent search container is displayed with less than /5 search items$")
    public void recent_search_container_is_displayed_with_less_than_search_items() throws Throwable {
        searchComponent.clearBrowserLocalStore();
        List<String> searchTerms = new ArrayList<>();
        searchTerms.add("bask");
        searchTerms.add("ball");
        for(String str : searchTerms){
            recentSearchItem = str;
            searchComponent.open().search(str);
        }
    }

    @Then("^scrollbar (is not|is) displayed$")
    public void scrollbar_is_not_displayed(String param) throws Throwable {
        if(param.contains("not")){
            assertThat(searchComponent.isScrollBarPresentOnRecentSearchesList()).isFalse();
        }else{
            assertThat(searchComponent.isScrollBarPresentOnRecentSearchesList()).isTrue();
        }
    }

    @Given("^recent search container is displayed with more recent searches$")
    public void recent_search_container_is_displayed_with_more_recent_searches() throws Throwable {

        List<String> searchTerms = new ArrayList<>();
        searchTerms.add("basket");
        searchTerms.add("ball");
        searchTerms.add("basketball");
        searchTerms.add("bal");
        searchTerms.add("bas");
        searchTerms.add("football");
        searchTerms.add("foo");
        searchTerms.add("foot");
        searchTerms.add("more");
        searchTerms.add("ball");

        for(String str : searchTerms){
            recentSearchItem = str;
            searchComponent.open().search(str);
        }
    }

    @When("^recent search has more than (\\d+) items$")
    public void recent_search_has_more_than_items(int limit) throws Throwable {
      assertThat(searchComponent.getEventResultsCount()).isGreaterThan(limit);
    }

    @Then("^user can see more results by scrolling on recent search container$")
    public void user_can_see_more_results_by_scrolling_on_recent_search_container() throws Throwable {
        //TODO: Need to rewrite the below code according to recent searches.
        int count = searchComponent.getEventResultsCount();
        int val = 0;
        while (val < count) {
            val = searchComponent.getAllEventResults().size();
            logger.info("Scrolled into "+val+" elements");
            assertThat(searchComponent.scrollDownRecentSearch()).isTrue();
        }
        assertThat(count).isEqualTo(val);
}

    @Given("^'(.*)' on search results$")
    public void selects_any_search_result_or_does_scrolling_on_search_results(String type) throws Throwable {
        if(type.contains("scrolling")){
            searchComponent.scrollDownSearchResults();
        }if(type.contains("selecting")){
            searchComponent.clickOnResultLink(1);
        }
    }

    @When("^reopen search overlay again$")
    public void closes_and_opens_search_overlay_again() throws Throwable {
        searchComponent.open();
    }

    @Then("^the search term (is|is not) displayed on the top of the recent searches items list$")
    public void the_search_term_is_displayed_on_the_top_of_the_recent_searches_items_list(String param) throws Throwable {
        if(param.contains("not")){
            assertThat(searchComponent.getRecentSearches().get(0)).isNotEqualTo(recentSearchItem);
        }else{
            assertThat(searchComponent.getRecentSearches().get(0)).isEqualTo(recentSearchItem);
        }
    }

    @Given("^did not select any search result or does not scroll on search results$")
    public void did_not_select_any_search_result_or_does_not_scroll_on_search_results() throws Throwable {
        // No need of any action
    }

    @When("^user search for '(.*)' that doesn't found results$")
    public void user_search_for_xyz_that_doesn_t_found_results(String searchItem) throws Throwable {
        searchComponent.open().search(searchItem);
    }

    @Then("^Recent Searches container is displayed underneath the No Results Found error message$")
    public void recent_Searches_container_is_displayed_underneath_the_No_Results_Found_error_message() throws Throwable {
        assertThat(searchComponent.isMessagePresent("No Results Found")).isTrue();
        assertThat(searchComponent.isRecentSearchesContainerDisplayed()).isTrue();
    }
}
