package com.whgtf.sportsbook.main.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Lazy
public class HealthCheckSteps extends MainSteps {



    /**
     * On this Step we will verify on all the nodes of the current chosen environment the Sportsbook API.
     * Note: We set the Version from the Run Configurations.
     *
     * @throws Throwable
     */
    @Given("^user verify that the version for the Sportsbook API is the correct one$")
    public void verifySportsbookVersionApi() throws Throwable {

        // Here is where we get the version given from the run parameters
        String version = System.getProperty("sportsbookApi");

        List<String> links = properties.ppNodesApi();

        for (String url : links) {
            String response = jsonReader.readJSON(url).getJSONObject("info").getJSONObject("version").get("version").toString();
            assertThat(response.contains(version)).isTrue();
        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the PDS service is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the PDS service is working as expected$")
    public void verifyPDSservices() throws Throwable {

        List<String> links = properties.ppNodesApi();

        for (String url : links) {
            // Here we check the PDS service
            String responsePDS = jsonReader.readJSON(url + "/full").getJSONObject("pds").getJSONObject("status").get("info").toString();
            assertThat(responsePDS.equals("OK")).isTrue();
        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the SQUIZ service is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the SQUIZ service is working as expected$")
    public void verifySquizServices() throws Throwable {

        List<String> links = properties.ppNodesApi();

        for (String url : links) {
            // Here we check the SQUIZ service
            String responseSquiz = jsonReader.readJSON(url + "/full").getJSONObject("squiz").getJSONObject("status").get("info").toString();
            assertThat(responseSquiz.equals("OK")).isTrue();
        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the Top Bets service is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the Top Bets service is working as expected$")
    public void verifyTopBetsServices() throws Throwable {

        List<String> links = properties.ppNodesApi();

        for (String url : links) {
            // Here we check the Top Bets service
            String responseTopBets = jsonReader.readJSON(url + "/full").getJSONObject("topbets").getJSONObject("status").get("info").toString();
            assertThat(responseTopBets.equals("OK")).isTrue();
        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the Funnelback service is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the Funnelback service is working as expected$")
    public void verifyFunnelbackServices() throws Throwable {

        List<String> links = properties.ppNodesApi();

        for (String url : links) {
            // Here we check the Funnelback service
            String responseFunnelback = jsonReader.readJSON(url + "/full").getJSONObject("funnelback").getJSONObject("status").get("info").toString();
            assertThat(responseFunnelback.equals("OK")).isTrue();
        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the Betslip service is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the Betslip service is working as expected$")
    public void verifyBetslipServices() throws Throwable {

        List<String> links = properties.ppNodesApi();

        for (String url : links) {

            // Here we check the Betslip service
            String responseBetslip = jsonReader.readJSON(url + "/full").getJSONObject("betslip").getJSONObject("status").get("info").toString();
            assertThat(responseBetslip.equals("OK")).isTrue();

        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the Coupons service Health is Ok,
     * Status is Up and Count is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the Coupons service is working as expected$")
    public void verifyCouponsServices() throws Throwable {

        List<String> links = properties.ppNodesApi();

        for (String url : links) {

            // Here we check the Coupons service
            String responseCouponsHealth = jsonReader.readJSON(url + "/full").getJSONObject("coupons").getJSONObject("coupons").getJSONObject("health").get("info").toString();
            assertThat(responseCouponsHealth.equals("OK")).isTrue();

            String responseCouponsStatus = jsonReader.readJSON(url + "/full").getJSONObject("coupons").getJSONObject("coupons").getJSONObject("health").getJSONObject("detail").get("status").toString();
            assertThat(responseCouponsStatus.equals("UP")).isTrue();

            String responseCouponsCount = jsonReader.readJSON(url + "/full").getJSONObject("coupons").getJSONObject("coupons").getJSONObject("count").get("info").toString();
            assertThat(responseCouponsCount.equals("OK")).isTrue();
        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the API Static version .
     * Note: We set the Version from the Run Configurations.
     * @throws Throwable
     */
    @Given("^user verify that the version for the Sportsbook Static is the correct one$")
    public void verifySportsbookVersionStatic() throws Throwable {

        // Here is where we get the version given from the run parameters
        String version = System.getProperty("sportsbookStaticApi");
        List<String> links = properties.ppNodesStaticApi();

        for (String url : links) {
            String response = jsonReader.readJSON(url).getJSONObject("info").get("version").toString();
            assertThat(response.equals(version)).isTrue();
        }
    }

}
