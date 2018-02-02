package com.whgtf.sportsbook.main.stepdefinitions;


import cucumber.api.java.en.When;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class TopBetsSteps extends MainSteps{



    @When("^user clicks on the '(first|second)' available selection in Top Bets page$")
    public void clickFirstSelection(final String position) throws Throwable {
        if ("first".equalsIgnoreCase(position)){
            topBetsPage.clickOnTopBetSelectionByIndex(1);
        }else{
            String firstSelectionEventName = topBetsPage.getListOfEventNames().get(0);
            int iterator = 0;
            for (String eventName:topBetsPage.getListOfEventNames()) {
                if(!firstSelectionEventName.equals(eventName)){
                    break;
                }
                iterator++;
            }
            topBetsPage.clickOnTopBetSelectionByIndex(iterator + 1);
        }
    }

    @When("^user clicks on the \'(.*)(st|nd|rd|th)\' available selection from top bets widget$")
    public void clickOnGivenSelectionNumberInTopBetsWidget(int selectionNumber, String classyEnding) {
        topBetsComponent.clickOnSelectionInTopBetsWidget(selectionNumber);
    }


}
