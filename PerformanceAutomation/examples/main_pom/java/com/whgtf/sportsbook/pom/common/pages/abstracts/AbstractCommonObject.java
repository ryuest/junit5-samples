package com.whgtf.sportsbook.pom.common.pages.abstracts;

import com.whgtf.sportsbook.model.Selection;
import com.whgtf.sportsbook.pom.common.exceptions.NoSelectionAvailable;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;
import com.whgtf.sportsbook.pom.mobile.components.impl.NativeElementsComponentImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
@Lazy
public class AbstractCommonObject extends AbstractPageObject implements AbstractCommonInterface{



    @Override
    public List<String> getAllLinksInThePage() {
        @SuppressWarnings("unchecked")
        List<RemoteWebElement> list = (List<RemoteWebElement>) executeJavaScript("return $(\"a:visible\")");
        List<String> urlLinks = new ArrayList<>();
        for (RemoteWebElement href : list) {
            if ((href.getAttribute("href") != null) && (href.getAttribute("href").contains("/betting/")))
                urlLinks.add(href.getAttribute("href"));
        }
        return urlLinks;
    }

    @Override
    public Selection clickOnSelection(final String selectionId) {

        Selection selection = new Selection(selectionId);
        try {
            // save and return the clicked selection for later use.
            WebElement selectionElement = find(By.id(selectionId));
            selection.setPrice(selectionElement.getAttribute("data-odds"));
            selection.setName(selectionElement.getAttribute("data-name"));

            if (!isNativeMobileApp()) {
                click(By.id(selectionId));
                // allows to tick selections on the functional box
                executeJavaScript("WH.messageBus.publish('BetslipAdapter.getBets', []);");
            } else {
                waitElementToBeClickable(By.id(selectionId),5);
                new NativeElementsComponentImpl().clickOnSelection(selectionId);
            }
        } catch (NoSuchElementException ex) {
            throw new NoSelectionAvailable();
        }
        return selection;
    }

    @Override
    public boolean isSelectionClickable(String selectionId) {
        try {
            find(By.id(selectionId)).click();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public String getPriceFromSelectionId(final String selectionId) {
        waitSportsbook();
        waitElementToBeVisible(By.cssSelector("#"+selectionId+" .racecard-button__price"),10);
        return find(By.cssSelector("#"+selectionId+" .racecard-button__price")).getAttribute("data-odds");
    }

    @Override
    public String getOddFromSelection(String selectionId) {
        return find(By.id(selectionId)).getAttribute("data-odds");
    }

    @Override
    public boolean isSelectionDisplayed(final String selectionId) {
        return isElementDisplayed(By.id(selectionId));
    }

    @Override
    public void clickAndWaitSportsbook(By locator) {
        click(locator);
        waitSportsbook();
    }

    @Override
    public void checkAlert() {
        super.clickAlertAccept();
    }

    @Override
    public boolean sleep(final long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isSportsHomePageDisplayed(String sportsName) {
        return getCurrentUrl().contains(sportsName);
    }


    @Override
    public boolean isMobile() {
        return isNativeMobileApp() || (browser.isEmpty() && isElementDisplayed(By.id("toolbar")));
    }
}
