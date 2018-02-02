package com.whgtf.sportsbook.pom.mobile.util;


import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractPageObject;
import com.williamhill.whgtf.testrunner.GenericUtilities;
import cucumber.api.Scenario;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.Connection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.NoSuchElementException;


import static com.whgtf.sportsbook.pom.utils.Assertions.assertTrue;
import static com.williamhill.whgtf.testrunner.GenericUtilities.isJenkinsExecution;

public class NativeAppUtilities extends AbstractPageObject {

    private Logger logger = LogManager.getRootLogger();
    public static boolean isNativeApp = false;
    public static boolean isAppOpened = false;

    /**
     * Utility to install apps from Nexus Repository
     * Apps need to be uploaded to com.williamhill.whgtf.nativebuild.sports-native{version-number}/
     *
     * @param version The app version to install
     * @throws Throwable if the app can't be installed
     */
    public void installAppVersion(String version) throws Throwable {

        if (isSimulator()) {
            logger.debug("Loading from APP file - Simulator Test");
            assertTrue(openApp("http://maven.apps.local:8082/nexus/content/repositories/releases/com/williamhill/whgtf/nativebuild/sports-native/" + version + "/sports-native-" + version + ".zip"));
        } else {
            logger.debug("Loading from IPA file - Real Device Test");
            assertTrue(openApp("https://rink.hockeyapp.net/api/2/apps/133e139a9a510f718a61c1659a837343/app_versions/910?format=ipa&avtoken=33cc9c1b2b16026568d19cc35de7a4f20105567d"));
            //assertTrue(openApp("http://maven.apps.local:8082/nexus/content/repositories/releases/com/williamhill/whgtf/nativebuild/sports-native/" + version + "/sports-native-" + version + ".ipa"));
        }

    }

    /**
     * Utility to load the pre installed application uses package name
     * App must be preInstalled
     *
     * @param appArguments the app arguments
     * @throws Throwable if the app can't open
     */
    public void openPreInstalledApp(String... appArguments) throws Throwable {
        isNativeApp = true;
        if (isAppOpened) return;
        if (isDeviceIOS()) {
            if (isSimulator()) {
                assertTrue("You should use Install App to Test on Sims", false);
            } else {
                assertTrue(openApp(appArguments[0]));
                //assertTrue(openApp("/Users/martin/Library/Developer/Xcode/DerivedData/WHSportsbook-gqntewvfgihqdhavzwdighuzvytv/Build/Products/Debug-iphoneos/DEBUG.app"));
                sleep(1000);
            }
        } else {
            assertTrue(openApp(appArguments[1], appArguments[2]));
        }
        isAppOpened = true;
    }

    public void sendAppToBackground(int seconds) throws Throwable {
        assertTrue(deviceBackgroundApp(seconds));
        deviceSwitchToContextNativeApp();
    }

    public void resetApp() throws Throwable {
        assertTrue(deviceResetApp());
    }

    public void launchApp() throws Throwable {
        assertTrue(deviceLaunchApp());
    }

    public void closeApp() throws Throwable {
        assertTrue(deviceCloseApp());
    }

    public void swipeToElement(MobileElement element) {

        int counter = 0;
        while (element.getCenter().getY() > 0.9 * getSize().getHeight() && counter < 10) {
            swipeFromBottomToTop();
            counter++;
        }
        while (element.getCenter().getY() < 0.1 * getSize().getHeight() && counter < 10) {
            swipeFromTopToBottom();
            counter++;
        }
    }

    public void swipeFromBottomToTop() {
        int x = getSize().width / 2;
        int startY = (int) (getSize().height * 0.8);
        int endY = (int) (getSize().height * 0.2);
        deviceSwipe(x, startY, x, endY, 700);
    }

    public void swipeFromTopToBottom() {
        int x = getSize().width / 2;
        int endY = (int) (getSize().height * 0.7);
        int startY = (int) (getSize().height * 0.3);
        deviceSwipe(x, startY, x, endY, 700);
    }

    public void clickElementInList(List<MobileElement> list, String text) {
        for (MobileElement element : list) {
            if (element.getText().equals(text)) {
                element.click();
                return;
            }
        }
        throw new NoSuchElementException();
    }

    public void clickElementInSortedList(List<MobileElement> list, String text) {
        int counter = 0;
        while (list.get(0).getText().compareTo(text) > 0 && counter < 10) {
            swipeFromTopToBottom();
            counter++;
        }
        while (list.get(list.size() - 1).getText().compareTo(text) < 0 && counter < 10) {
            swipeFromBottomToTop();
            counter++;
        }
        clickElementInList(list, text);
    }


    private Dimension getSize() {
        return getWebDriver().manage().window().getSize();
    }


    public void takeScreenShot(Scenario scenario) {
        //prepare directory and filename

        String screenshotsDir = System.getProperty("screenshotDirectory");
        String testClassName = scenario.getClass().getName();
        String testMethodName = scenario.getName();

        String screenshotFileName = GenericUtilities.counter + "." + testMethodName + "-" + GenericUtilities.CURRENT_DATE + "_" + GenericUtilities.CURRENT_TIME;
        File outputFolder = null;
        if (isJenkinsExecution() || screenshotsDir == null) {
            screenshotsDir = "src/test/resources/screenshots/";
        }

        String environment = System.getProperty("env");
        String folderString = environment == null ? screenshotsDir + GenericUtilities.CURRENT_DATE + "/" + testClassName
                + "/" + testMethodName.replaceAll("[^a-zA-Z0-9.-]", "") + "/" + GenericUtilities.CURRENT_TIME : screenshotsDir
                + GenericUtilities.CURRENT_DATE + "/" + testClassName + "/" + testMethodName.replaceAll("[^a-zA-Z0-9.-]", "")
                + "/" + environment + "/" + GenericUtilities.CURRENT_TIME;

        try {
            outputFolder = new File(folderString);
            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //take a screenshot
        File scrFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);

        try {
            // Copy paste file at destination folder location
            FileUtils.copyFile(scrFile, new File(outputFolder + "/" + screenshotFileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnOffData() {
        ((AndroidDriver) getWebDriver()).setConnection(Connection.AIRPLANE);
    }

    public void turnOnData() {
        ((AndroidDriver) getWebDriver()).setConnection(Connection.WIFI);
    }

    public String getUrlWithoutWaitingForSportsbook(){
        switchToWebViewContextIfNeeded();
        try {
            return URLDecoder.decode(getWebDriver().getCurrentUrl(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }

    public void startActivity(String appPackage, String appActivity){
        ((AndroidDriver) getWebDriver()).startActivity(appPackage, appActivity,appPackage, appActivity, false);

    }

}
