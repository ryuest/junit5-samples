package com.whgtf.sportsbook.pom.common.pages.abstracts;


import com.google.common.collect.Iterables;
import com.whgtf.sportsbook.pom.mobile.util.NativeAppUtilities;
import com.williamhill.whgtf.asl.AutomationScriptingLanguage;
import com.williamhill.whgtf.webdriver.MultiThreadedDriverFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Set;

public class AbstractPageObject {

    private static final By GLOBAL_OVERLAY = By.cssSelector("#wh-global-overlay.wh-hide");

    private static boolean isInFrame = false;

    String browser = System.getProperty("browser", "");

    static final By PAGE_HEADER_TITLE = By.xpath("(//section//h1)[1]");

    private static final AutomationScriptingLanguage asl = new AutomationScriptingLanguage();


    protected WebDriver getWebDriver() {
        return MultiThreadedDriverFactory.getCurrentDriver();
    }

    protected void open(String url) {
        asl.navigateToPage(url);
        // reset implicit wait time (set in WebDriverProperties configureTimeouts() to 5 seconds)
        //getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        // reset explicit wait time (set in AutomationScriptingLanguage to 20 seconds
        //asl.setWaitTime(5);
        waitSportsbook();

        // maximize the browser for desktop
        if (!browser.isEmpty()) {
            getWebDriver().manage().window().maximize();
        }
    }

    void navigateBack() {
        getWebDriver().navigate().back();
    }

    boolean clickAlertAccept() {
        try {
            new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.alertIsPresent());
            Alert alert = getWebDriver().switchTo().alert();
            alert.accept();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }

    }

    void refresh() {
        getWebDriver().navigate().refresh();
    }

    boolean scrollToElementByXpath(String xpath) {
        return asl.scrollToElementByXpath(xpath);
    }

    protected boolean deviceSwitchToContextNativeApp() {
        return asl.deviceSwitchToContextNativeApp();
    }

    protected boolean isDeviceIOS() {
        return asl.isDeviceIOS();
    }

    protected boolean deviceSwitchToContext(String context) {
        return asl.deviceSwitchToContext(context);
    }

    protected boolean deviceTapOnCoordinates(int x, int y) {
        return asl.deviceTapOnCoordinates(x, y);
    }

    protected boolean isSimulator() {
        return asl.isSimulator();
    }

    protected boolean openApp(String... appArguments) {
        return asl.openApp(appArguments);
    }

    public boolean sleep(long millis) {
        return asl.sleep(millis);
    }

    protected boolean deviceBackgroundApp(int seconds) {
        return asl.deviceBackgroundApp(seconds);
    }

    protected boolean deviceResetApp() {
        return asl.deviceResetApp();
    }

    protected boolean deviceLaunchApp() {
        return asl.deviceLaunchApp();
    }

    protected boolean deviceCloseApp() {
        return asl.deviceCloseApp();
    }

    protected boolean deviceSwipe(int startX, int startY, int endX, int endY, int duration) {
        return asl.deviceSwipe(startX, startY, endX, endY, duration);
    }

    protected boolean deviceSwitchToContextWebView() {
        return asl.deviceSwitchToContextWebView();
    }

    protected boolean navigateToOtherWindow() {
        return asl.navigateToOtherWindow();
    }

    protected boolean returnToDefaultWindow() {
        return asl.returnToDefaultWindow();
    }


    protected String getCurrentUrl() {
        waitSportsbook();
        try {
            return URLDecoder.decode(getWebDriver().getCurrentUrl(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }

    public String getPageTitle() {
        waitSportsbook();
        return getWebDriver().getTitle();
    }

    protected WebElement find(By locator) {
        switchToWebViewContextIfNeeded();
        waitSportsbook();
        return getWebDriver().findElement(locator);
    }

    protected void setText(By selector, String text) {
        WebElement we = find(selector);
        we.clear();
        we.sendKeys(text);
    }

    protected void waitSportsbook() {
        switchToWebViewContextIfNeeded();
        if (!isInFrame) {
            waitElementToBePresent(GLOBAL_OVERLAY, 20);
        }
    }


    protected void switchToWebViewContextIfNeeded() {
        if (isNativeMobileApp())
            asl.deviceSwitchToContextWebView();
    }

    protected List<WebElement> findElements(By locator) {
        switchToWebViewContextIfNeeded();
        waitSportsbook();
        return getWebDriver().findElements(locator);
    }

    protected void click(final By element) {
        click(find(element));
    }

    protected void click(final WebElement element) {
        try {
            element.click();
        } catch (Exception ex) {
            try {
                Actions actions = new Actions(getWebDriver());
                actions.moveToElement(element).perform();
                executeJavaScript("window.scrollBy(0,100)");
                element.click();
            } catch(Exception exc) {
                clickByJavascript(element);
            }
        }
        waitSportsbook();
    }

    protected void clickByJavascript(By locator) {
        executeJavaScript("window.scrollBy(0,100)");
        clickByJavascript(getWebDriver().findElement(locator));
    }

    protected void clickByJavascript(WebElement element) {
        executeJavaScript("window.scrollBy(0,100)");
        asl.executeScript("arguments[0].click();", element);
    }

    protected Object executeJavaScript(String code) {
        switchToWebViewContextIfNeeded();
        return asl.executeScript(code);
    }

    protected WebElement waitElementToBeClickable(By locator, long timeOutInSeconds) {
        return new WebDriverWait(getWebDriver(), timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitElementToBePresent(By locator, long timeOutInSeconds) {
        return new WebDriverWait(getWebDriver(), timeOutInSeconds)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected boolean waitElementNotToBeVisible(By locator, long timeOutInSeconds) {
        return new WebDriverWait(getWebDriver(), timeOutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected WebElement waitElementToBeVisible(By locator, long timeOutInSeconds) {
        return new WebDriverWait(getWebDriver(), timeOutInSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitElementToBeVisible(WebElement element, long timeOutInSeconds) {
        return new WebDriverWait(getWebDriver(), timeOutInSeconds)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected Boolean waitElementAttributeToContain(By locator, String attribute, String value, long timeOutInSeconds) {
        return new WebDriverWait(getWebDriver(), timeOutInSeconds)
                .until(ExpectedConditions.attributeContains(find(locator), attribute, value));
    }

    protected Boolean waitElementAttributeToContain(WebElement element, String attribute, String value, long timeOutInSeconds) {
        return new WebDriverWait(getWebDriver(), timeOutInSeconds)
                .until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    protected Boolean waitElementAttributeNotContain(By locator, String attribute, String value, long timeOutInSeconds) {
        return new WebDriverWait(getWebDriver(), timeOutInSeconds)
                .until(ExpectedConditions.not(ExpectedConditions.attributeContains(find(locator), attribute, value)));
    }

    protected Boolean waitElementAttributeNotContain(WebElement element, String attribute, String value, long timeOutInSeconds) {
        return new WebDriverWait(getWebDriver(), timeOutInSeconds)
                .until(ExpectedConditions.not(ExpectedConditions.attributeContains(element, attribute, value)));
    }

    protected boolean isElementPresent(By locator) {
        try {
            find(locator);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected boolean isElementPresent(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            waitElementToBeVisible(locator,5);
            return find(locator).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * This method will return "True" if the element was successfully clicked.
     *
     * @param locator - "By" from the given target Web Element.
     * @return - Will return "True" in case element was clicked, false if not.
     */
    protected boolean isElementClickable(By locator) {
        try {
            find(locator).click();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * This method will return "True" if the element was successfully clicked.
     *
     * @param element - a "WebElement" is passed as input.
     * @return - Will return "True" in case element was clicked, false if not.
     */
    protected boolean isElementClickable(WebElement element) {
        try {
            element.click();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }


    protected void switchToiFrame(By locator) {
        if (!isNativeMobileApp()) {
            getWebDriver().switchTo().frame(find(locator));
        }
        isInFrame = true;
    }


    protected void switchToParentFrame() {
        if (!isNativeMobileApp()) {
            getWebDriver().switchTo().parentFrame();
        }
        isInFrame = false;
    }


    void switchToLastBrowserTab() {
        Set<String> windowsList = getWebDriver().getWindowHandles();
        String lastBrowserTab = Iterables.getLast(windowsList);
        getWebDriver().switchTo().window(lastBrowserTab);
    }


    public void hoverOverElement(final WebElement element) {
        Actions builder = new Actions(getWebDriver());
        builder.moveToElement(element).perform();
    }

    public String getHeaderPanelTitlePage() {
        return find(PAGE_HEADER_TITLE).getText();
    }

    public boolean isNativeMobileApp() {
        return NativeAppUtilities.isNativeApp;
    }

    public String getSportPageName() {
        return (String) executeJavaScript("return WH.sportsbook.sport;");
    }

    /**
     * Scrolls on given cssselector element.
     *
     * @param cssSelector The selector of the element to scroll to
     */
    protected void scrollIntoElement(String cssSelector){
        executeJavaScript(String.format("document.querySelector('%s').scrollIntoView();",cssSelector));
    }
}
