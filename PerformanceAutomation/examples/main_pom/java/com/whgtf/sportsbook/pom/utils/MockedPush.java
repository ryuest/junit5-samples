package com.whgtf.sportsbook.pom.utils;

import com.whgtf.sportsbook.model.Openbet;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractSportsPageObject;
import org.apache.commons.lang3.math.Fraction;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MockedPush extends AbstractSportsPageObject{

    public void setSelectionPrice(String selectionId, String price) {
        executeJavaScript(String.format("SB.push('%s', 'currentprice', '%s')", selectionId, price));
    }

    public String increaseSelectionPrice(String selectionId, String currentPrice) {
        Fraction fraction = Fraction.getFraction(currentPrice);
        String newPrice = String.valueOf(fraction.getNumerator() + 1) + "/" +
                fraction.getDenominator();
        setSelectionPrice(selectionId, newPrice);
        return newPrice;
    }

    public String decreaseSelectionPrice(String selectionId, String currentPrice) {
        Fraction fraction = Fraction.getFraction(currentPrice);
        String newPrice = fraction.getNumerator() + "/" +
                String.valueOf(fraction.getDenominator() + 1);
        setSelectionPrice(selectionId, newPrice);
        return newPrice;
    }

    public void setStatus(String elementId, Openbet.Status status) {
        executeJavaScript(String.format("SB.push('%s', 'status', '%s')", elementId, status.getStatus()));
    }

    public void setDisplay(String elementId, Openbet.Display display) {
        executeJavaScript(String.format("SB.push('%s', 'displayed', '%s')", elementId, display.getDisplay()));
    }

    public void setBetInRun(String elementId, Openbet.BetInRun betInRun) {
        executeJavaScript(String.format("SB.push('%s', 'betInRun', '%s')", elementId, betInRun.getBetInRun()));
    }


    public void pushAllDataForRaceCard(final String eventId) {
        try {
            getSelectionAndPush(eventId);
        } catch (StaleElementReferenceException e) {
            // temporary solution, after page fully loaded, "loading screen" could appear again for 1-2 seconds at all env.
            waitSportsbook();
            waitElementToBePresent(By.cssSelector(".wh-hide"), 10);
            getSelectionAndPush(eventId);
        }
    }

    public void getSelectionAndPush (final String eventId) {
        waitElementToBePresent(By.cssSelector(String.format("section#%s article.racecard-runner", eventId)), 20);
        List<WebElement> rowList = findElements(By.cssSelector(String.format("section#%s article.racecard-runner", eventId)));
        for (WebElement row:rowList) {
            if(!row.getAttribute("class").contains("racecard-runner--unnamed-favourite")){
                String order = row.findElement(By.cssSelector(".racecard-runner__number")).getText();
                List<WebElement> selectionList = row.findElements(By.cssSelector("[id^='OB_OU']"));
                for (WebElement selection:selectionList) {
                    executeJavaScript(String.format("SB.push('%s', 'order', '%s')", selection.getAttribute("id"),order));
                    executeJavaScript(String.format("SB.push('%s', 'name', '%s')", selection.getAttribute("id"), selection.getAttribute("data-name")));
                    setSelectionPrice(selection.getAttribute("id"),selection.getAttribute("data-odds"));
                }
            }
        }
    }
}
