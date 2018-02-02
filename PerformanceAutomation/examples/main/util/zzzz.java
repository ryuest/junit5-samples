package com.whgtf.sportsbook.main.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.sun.jna.StringArray;
import com.whgtf.sportsbook.pom.utils.ScenarioContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.xpath.SourceTreeManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.Color;


/**
 * Created by Juri on 14/10/2016.
 */
public class zzzz {

    public static WebDriver driver;

    public static void main(String[] args) {

        String GC_DRIVER_PROPERTY = "webdriver.chrome.driver";
        String GC_DRIVER_PATH = "C:\\LOAD PAGE SPEED\\chromedriver.exe";

        System.setProperty(GC_DRIVER_PROPERTY, GC_DRIVER_PATH);

        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", "Apple iPhone 6");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

    //    driver.manage().deleteAllCookies();
    //    ((JavascriptExecutor)driver).executeScript("window.localStorage.clear();", new Object[0]);
         driver = new ChromeDriver(capabilities);
    //    driver = new ChromeDriver();
    //    driver.manage().window().maximize();

        driver.navigate().to("http://www.google.com");
        driver.get("http://sports.williamhill-pp1.com/betting/en-gb/horse-racing/meetings/today/OB_TY36/kempton");

     //   driver.findElement(By.cssSelector(".race-meeting__selection--best-odds-guaranteed")).click();
        driver.findElement(By.xpath("//button[contains(@class,'oddsbutton') and not(contains(@data-odds, 'SP'))]")).click();



        List<WebElement> elementList = driver.findElement(By.id("marketCollectionItemsList")).findElements(By.xpath("//a[contains(@class,'filter-list__link filter-list__otherRace') and not(contains(@class, '-active'))]"));

        elementList.get(elementList.size()-1).click();
        for (WebElement meetingsActive : elementList) {
            System.out.println(meetingsActive.getText());
       }

    }

    public static List<String> findMeetingsWithMeetingMarkets(boolean withMeetingMarkert) {

        By MEETING_HEADER = By.cssSelector(".race-subnav__group");

        String MEETING_WITH_MEETING_MARKETS = "//*[@id='%s']//button[contains(@data-name,'Meeting Markets')]";

        List<WebElement> meetings = driver.findElements(MEETING_HEADER);
        List<String> resultList = new ArrayList<>();
        for (WebElement meetingsActive : meetings) {
            if (meetingsActive.isDisplayed()) {
                if (meetingsActive.findElements(By.xpath(String.format("//*[@id='%s']//button[contains(@class,'race-meeting__selection')]", meetingsActive.getAttribute("id")))).size() < 2) {
                    try {
                        meetingsActive.findElement(By.xpath(String.format(MEETING_WITH_MEETING_MARKETS, meetingsActive.getAttribute("id"))));
                    } catch (WebDriverException zz) {
                        if (withMeetingMarkert) {
                        } else {
                            resultList.add(meetingsActive.getAttribute("id"));
                        }
                    }


                }
            }

        }
        return resultList;
    }

}
