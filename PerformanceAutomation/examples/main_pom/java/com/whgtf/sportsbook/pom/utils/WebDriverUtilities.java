package com.whgtf.sportsbook.pom.utils;

import com.williamhill.whgtf.webdriver.MultiThreadedDriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class WebDriverUtilities {

    private WebDriverUtilities() {

    }

    private static WebDriver getDriver() {
        return MultiThreadedDriverFactory.getCurrentDriver();
    }

    public static boolean isDriverSet() {
        return getDriver() != null;
    }

    public static void deleteAllCookies() {
        getDriver().manage().deleteAllCookies();
    }

    public static void clearLocalStorage() {
        ((JavascriptExecutor) getDriver())
                .executeScript("window.localStorage.clear();");
    }

    public static void closeAnyExtraTab() {
        Set<String> windows = getDriver().getWindowHandles();
        List<String> windowList = new ArrayList<>(windows);
        if (windows.size() > 1) {
            for (int i = 1; i < windowList.size(); i++) {
                getDriver().switchTo().window(windowList.get(i)).close();
            }
            getDriver().switchTo().window(windowList.get(0));
        }
    }

}
