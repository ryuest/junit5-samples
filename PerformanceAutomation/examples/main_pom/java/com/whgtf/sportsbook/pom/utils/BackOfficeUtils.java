package com.whgtf.sportsbook.pom.utils;

import com.whgtf.sportsbook.model.User;
import com.whgtf.sportsbook.utils.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.openqa.selenium.By.xpath;

/**
 * Created by javierg on 05/09/2016.
 */
public class BackOfficeUtils {

    private static BackOfficeUtils instance = null;

    private static final Logger logger =
            Logger.getLogger(BackOfficeUtils.class.getName());

    private String user = "mrichardson";

    private String password = "mrichardson01";

    private WebDriver officeDriver;

    private String url;

    private WebDriver driver;

    private boolean loggedIn = false;

    private BackOfficeUtils() {
        url = "https://backoffice.williamhill-" + System.getProperty("env") + ".remote/admin";
        driver = new FirefoxDriver();
    }

    public static BackOfficeUtils getInstance() {
        if(instance == null) {
            instance = new BackOfficeUtils();

        }
        return instance;
    }

    public void loginBackOffice() {
        driver.switchTo().parentFrame();
        driver.get(url);
        if(!loggedIn) {
            driver.findElement(By.name("username")).sendKeys(user);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.xpath("//input[@value='Login']")).click();
            Timer.sleep(2, TimeUnit.SECONDS);
            loggedIn=true;
        }
    }


    public boolean addStreamToRace(final String eventId, final String competitionId, final String sport) {

        try {

            //driver = new RemoteWebDriver(new URL("http://10.180.18.93:4444/wd/hub"), DesiredCapabilities.firefox());

            loginBackOffice();

            // Store the current window handle
            driver.get(url.replaceAll("/office","/admin") + "?action=ADMIN::ATR::GoMapATRStreams");
            String winHandleBefore = driver.getWindowHandle();

            new Select(driver.findElement(xpath("//select[@name = 'days']"))).selectByIndex(1);
            new Select(driver.findElement(xpath("//select[@name = 'mapped']"))).selectByIndex(0);
            driver.findElement(xpath("//input[@type = 'button' and @value = 'Show Events']")).click();

            // click first Map Event
            Timer.sleep(2, TimeUnit.SECONDS);
            driver.findElement(xpath(String.format("//*[.='Map Event']"))).click();

            // Switch to new window opened
            for(String winHandle : driver.getWindowHandles())
                driver.switchTo().window(winHandle);

            if(sport.equals("horse-racing")) {
                driver.findElement(By.linkText("HORSE_RACING")).click();
                Timer.sleep(1, TimeUnit.SECONDS);
                driver.findElement(By.linkText("|Horse Racing - Live|")).click();
            } else if(sport.equals("greyhounds")) {
                driver.findElement(By.linkText("GREYHOUNDS")).click();
                Timer.sleep(1, TimeUnit.SECONDS);
                driver.findElement(By.linkText("|Greyhounds - Live|")).click();
            }  else if(sport.equals("football")) {
                driver.findElement(By.linkText("FOOTBALL")).click();
                Timer.sleep(1, TimeUnit.SECONDS);
                driver.findElement(By.linkText("|Football - Live|")).click();
            } else {
                throw new IllegalArgumentException(String.format("The provided sport: %s doesn't match. " +
                        "Valid values are 'horse-racing' and 'greyhounds'.", sport));
            }
            Timer.sleep(1, TimeUnit.SECONDS);
            driver.findElement(By.linkText(competitionId)).click();
            driver.findElement(xpath(String.format("//input[@value=%s]/../input[@type='radio']", eventId))).click();
            driver.findElement(xpath("//input[@type = 'button' and @value = 'Select']")).click();

            driver.switchTo().window(winHandleBefore);
            driver.findElement(xpath("//input[@type = 'button' and @value = 'Set Mappings']")).click();
            Timer.sleep(1, TimeUnit.SECONDS);

            String mapResponse = driver.findElement(By.cssSelector(".infoyes")).getText();
            return mapResponse.contains("1 Events have been mapped.");

        } catch(Throwable e) {
            e.printStackTrace();
            return false;
        }

    }

    public User createUser() {
        loginBackOffice();
        User user = new User();
        driver.switchTo().frame("TopBar");
        driver.findElement(By.xpath("//li/span[text()='Queries']")).click();
        driver.findElement(By.xpath("//li/a[text()='Customers']")).click();
        driver.switchTo().parentFrame();
        driver.switchTo().frame("MainArea");
        driver.findElement(By.xpath("//input[@value='Add Customer']")).click();
        driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(user.getForename());
        driver.findElement(By.xpath("//input[@name='lname']")).sendKeys(user.getSurname());
        driver.findElement(By.xpath("//input[@name='addr_street_1']")).sendKeys(user.getHouseNumber());
        driver.findElement(By.xpath("//input[@name='addr_street_2']")).sendKeys(user.getAddress2());
        driver.findElement(By.xpath("//input[@name='addr_postcode']")).sendKeys(user.getPostCode());
        driver.findElement(By.id("find_address_button")).click();
        driver.findElement(By.xpath("//input[@name='occupation']")).sendKeys(user.getOccupation());
        driver.findElement(By.xpath("//input[@name='dob']")).sendKeys(user.getDob());

        new Select(driver.findElement(xpath("//select[@name = 'hear_about']"))).selectByIndex(1);
        new Select(driver.findElement(xpath("//select[@name = 'hear_about_txt']"))).selectByIndex(1);

        driver.findElement(By.xpath("//input[@name='credit_limit']")).clear();
        driver.findElement(By.xpath("//input[@name='credit_limit']")).sendKeys(user.getCreditLimit());
        driver.findElement(By.xpath("//input[@value='Add Customer']")).click();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user.setUsername(driver.findElement(By.xpath("//input[@name='Username']")).getAttribute("value"));

        driver.findElement(By.name("Password_1")).sendKeys(user.getPassword());
        driver.findElement(By.name("Password_2")).sendKeys(user.getPassword());
        driver.findElement(By.xpath("//input[@value='Update Password']")).click();
        return user;
    }

    public void closeUser(final String user) {
        loginBackOffice();
        driver.switchTo().frame("TopBar");
        driver.findElement(By.xpath("//li/span[text()='Queries']")).click();
        driver.findElement(By.xpath("//li/a[text()='Customers']")).click();
        driver.switchTo().parentFrame();
        driver.switchTo().frame("MainArea");
        driver.findElement(By.xpath("//input[@name='Username']")).sendKeys(user);
        driver.findElement(By.name("custSearch")).click();
        new Select(driver.findElement(By.id("Status"))).selectByIndex(2);
        new Select(driver.findElement(By.id("StatusReason"))).selectByValue("106");
        driver.findElement(By.id("UpdateCustomerButton")).click();
        driver.quit();
    }

}
