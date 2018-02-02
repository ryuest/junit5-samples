package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.AccountPreferencesComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class AccountPreferencesComponentImpl extends AbstractCommonObject implements AccountPreferencesComponent {

    private static final By PREFERENCES_IFRAME = By.cssSelector("iframe#overlayPreferences");

    private static final By CURRENT_PASSWORD_INPUT = By.id("input-current-password");

    private static final By NEW_PASSWORD_INPUT = By.id("input-new-password");

    private static final By FULL_NAME_INPUT = By.id("name");

    private static final By EMAIL_INPUT = By.id("email");

    private static final By ADDRESS_LINE1_INPUT = By.id("input-street1");

    private static final By ADDRESS_LINE2_INPUT = By.id("input-street2");

    private static final By ADDRESS_LINE3_INPUT = By.id("input-county");

    private static final By CITY_INPUT = By.id("input-city");

    private static final By POSTCODE_INPUT = By.id("input-postcode");

    private static final By COUNTRY_INPUT = By.id("input-countryCode");

    private static final By MOBILE_INPUT = By.id("input-mobile");

    private static final By UPDATE_PASSWORD_BUTTON = By.id("btn-updatePassword");

    private static final By UPDATE_DETAILS_BUTTON = By.id("btn-updateContactDetails");

    private static final By UPDATE_DETAILS_SUCCESS_ALERT = By.id("test-success-update-details");

    private static final By UPDATE_PASSWORD_SUCCESS_ALERT = By.id("test-success-update-password");

    @Override
    public void setCurrentPassword(String password) {
        switchToiFrame(PREFERENCES_IFRAME);
        find(CURRENT_PASSWORD_INPUT).clear();
        find(CURRENT_PASSWORD_INPUT).sendKeys(password);
        switchToParentFrame();
    }

    @Override
    public void setNewPassword(String password) {
        switchToiFrame(PREFERENCES_IFRAME);
        find(NEW_PASSWORD_INPUT).clear();
        find(NEW_PASSWORD_INPUT).sendKeys(password);
        switchToParentFrame();
    }

    @Override
    public void setEmail(String email) {
        switchToiFrame(PREFERENCES_IFRAME);
        find(EMAIL_INPUT).clear();
        find(EMAIL_INPUT).sendKeys(email);
        switchToParentFrame();
    }

    @Override
    public void setAddressLine1(String address) {
        switchToiFrame(PREFERENCES_IFRAME);
        find(ADDRESS_LINE1_INPUT).clear();
        find(ADDRESS_LINE1_INPUT).sendKeys(address);
        switchToParentFrame();
    }

    @Override
    public void setAddressLine2(String address) {
        switchToiFrame(PREFERENCES_IFRAME);
        find(ADDRESS_LINE2_INPUT).clear();
        find(ADDRESS_LINE2_INPUT).sendKeys(address);
        switchToParentFrame();
    }

    @Override
    public void setAddressLine3(String address) {
        switchToiFrame(PREFERENCES_IFRAME);
        find(ADDRESS_LINE3_INPUT).clear();
        find(ADDRESS_LINE3_INPUT).sendKeys(address);
        switchToParentFrame();
    }

    @Override
    public void setCity(String city) {
        switchToiFrame(PREFERENCES_IFRAME);
        find(CITY_INPUT).clear();
        find(CITY_INPUT).sendKeys(city);
        switchToParentFrame();
    }

    @Override
    public void setPostcode(String postcode) {
        switchToiFrame(PREFERENCES_IFRAME);
        find(POSTCODE_INPUT).clear();
        find(POSTCODE_INPUT).sendKeys(postcode);
        switchToParentFrame();
    }

    @Override
    public void setMobile(String mobile) {
        switchToiFrame(PREFERENCES_IFRAME);
        find(MOBILE_INPUT).clear();
        find(MOBILE_INPUT).sendKeys(mobile);
        switchToParentFrame();
    }

    @Override
    public void clickUpdatePassword() {
        switchToiFrame(PREFERENCES_IFRAME);
        click(UPDATE_PASSWORD_BUTTON);
        switchToParentFrame();
    }

    @Override
    public void clickUpdateContactDetails() {
        switchToiFrame(PREFERENCES_IFRAME);
        click(UPDATE_DETAILS_BUTTON);
        switchToParentFrame();
    }

    @Override
    public boolean isPasswordSuccessfullyUpdatedAlertDisplayed() {
        switchToiFrame(PREFERENCES_IFRAME);
        boolean isDisplayed = isElementDisplayed(UPDATE_PASSWORD_SUCCESS_ALERT);
        switchToParentFrame();
        return isDisplayed;
    }

    @Override
    public boolean isDetailsSuccessfullyUpdatedAlertDisplayed() {
        switchToiFrame(PREFERENCES_IFRAME);
        boolean isDisplayed = isElementDisplayed(UPDATE_DETAILS_SUCCESS_ALERT);
        switchToParentFrame();
        return isDisplayed;
    }

    @Override
    public String getCurrentPassword() {
        switchToiFrame(PREFERENCES_IFRAME);
        String password = find(CURRENT_PASSWORD_INPUT).getAttribute("value");
        switchToParentFrame();
        return password;
    }

    @Override
    public String getNewPassword() {
        switchToiFrame(PREFERENCES_IFRAME);
        String password = find(NEW_PASSWORD_INPUT).getAttribute("value");
        switchToParentFrame();
        return password;
    }

    @Override
    public String getFullName() {
        switchToiFrame(PREFERENCES_IFRAME);
        String name = find(FULL_NAME_INPUT).getAttribute("value");
        switchToParentFrame();
        return name;
    }

    @Override
    public String getEmail() {
        switchToiFrame(PREFERENCES_IFRAME);
        String email = find(EMAIL_INPUT).getAttribute("value");
        switchToParentFrame();
        return email;
    }

    @Override
    public String getAddressLine1() {
        switchToiFrame(PREFERENCES_IFRAME);
        String address = find(ADDRESS_LINE1_INPUT).getAttribute("value");
        switchToParentFrame();
        return address;
    }

    @Override
    public String getAddressLine2() {
        switchToiFrame(PREFERENCES_IFRAME);
        String address = find(ADDRESS_LINE2_INPUT).getAttribute("value");
        switchToParentFrame();
        return address;
    }

    @Override
    public String getAddressLine3() {
        switchToiFrame(PREFERENCES_IFRAME);
        String address = find(ADDRESS_LINE3_INPUT).getAttribute("value");
        switchToParentFrame();
        return address;
    }

    @Override
    public String getCity() {
        switchToiFrame(PREFERENCES_IFRAME);
        String city = find(CITY_INPUT).getAttribute("value");
        switchToParentFrame();
        return city;
    }

    @Override
    public String getPostcode() {
        switchToiFrame(PREFERENCES_IFRAME);
        String postcode = find(POSTCODE_INPUT).getAttribute("value");
        switchToParentFrame();
        return postcode;
    }

    @Override
    public String getCountry() {
        switchToiFrame(PREFERENCES_IFRAME);
        String country = find(COUNTRY_INPUT).getAttribute("value");
        switchToParentFrame();
        return country;
    }

    @Override
    public String getMobile() {
        switchToiFrame(PREFERENCES_IFRAME);
        String mobile = find(MOBILE_INPUT).getAttribute("value");
        switchToParentFrame();
        return mobile;
    }
}
