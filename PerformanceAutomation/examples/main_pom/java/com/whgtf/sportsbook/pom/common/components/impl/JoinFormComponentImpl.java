package com.whgtf.sportsbook.pom.common.components.impl;

import com.google.common.collect.ImmutableMap;
import com.whgtf.sportsbook.pom.common.components.interfaces.JoinFormComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.NativeElementsComponent;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


@Component
@Lazy
public class JoinFormComponentImpl extends AbstractCommonObject implements JoinFormComponent {

    private static final ImmutableMap<String, String> MONTHS_MAP = ImmutableMap.<String, String>builder()
            .put("January", "01")
            .put("February", "02")
            .put("March", "03")
            .put("April", "04")
            .put("May", "05")
            .put("June", "06")
            .put("July", "07")
            .put("August", "08")
            .put("September", "09")
            .put("October", "10")
            .put("November", "11")
            .put("December", "12")
            .build();

    private static final By JOIN_FORM_IFRAME = By.cssSelector("iframe.mfp-iframe");

    private static final By JOIN_FORM = By.id("signupForm");

    private static final By MR_BUTTON = By.cssSelector("a[title='Mr']");

    private static final By MS_BUTTON = By.cssSelector("a[title='Ms']");

    private static final By MRS_BUTTON = By.cssSelector("a[title='Mrs']");

    private static final By MISS_BUTTON = By.cssSelector("a[title='Miss']");

    private static final By FIRST_NAME_FIELD = By.id("input-firstName");

    private static final By SURNAME_FIELD = By.id("input-lastName");

    private static final By DAY_OF_BIRTH_FIELD = By.id("dobSelectorDay");

    private static final By MONTH_OF_BIRTH_FIELD = By.id("dobSelectorMonth");

    private static final By YEAR_OF_BIRTH_FIELD = By.id("dobSelectorYear");

    private static final By EMAIL_FIELD = By.id("input-email");

    private static final By MOBILE_FIELD = By.id("input-mobile");

    private static final By COUNTRY_FIELD = By.id("input-countryCode");

    private static final By ADDRESS_LINE1_FIELD = By.id("input-street1");

    private static final By ADDRESS_LINE2_FIELD = By.id("input-street2");

    private static final By CITY_FIELD = By.id("input-city");

    private static final By COUNTY_FIELD = By.id("input-county");

    private static final By POSTAL_CODE_FIELD = By.id("input-postcode");

    private static final By USERNAME_FIELD = By.id("input-username");

    private static final By PASSWORD_FIELD = By.id("input-password");

    private static final By HIDE_BUTTON = By.cssSelector(".pos-rel button");

    private static final By SECURITY_QUESTION_FIELD = By.id("input-challenge");

    private static final By SECURITY_ANWER_FIELD = By.id("input-response");

    private static final By CURRENCY_FIELD = By.cssSelector("label[for='input-currencyCode']");

    private static final By CURRENCY_INPUT = By.id("input-currencyCode");

    private static final By LIMIT_AMOUNT_FIELD = By.id("input-ddl");

    private static final By PROMO_FIELD = By.id("input-promoCode");

    private static final By OVER_18_CHECK = By.id("input-acceptTerms");

    private static final By RECIEVE_OFFERS = By.id("input-contactable");

    private static final By CREATE_ACCOUNT_BUTTON = By.id("button-createAccount");

    private static final By DEPOSIT_DEBIT_CARD_OPTION = By.cssSelector("#scrollContent #paymentGROUP_DEBITCARD_CC");

    private static final By LIMIT_DAILY_BUTTON = By.cssSelector(".deposit-limit .button-group a[ng-repeat]:nth-child(1)");

    private static final By LIMIT_WEEKLY_BUTTON = By.cssSelector(".deposit-limit .button-group a[ng-repeat]:nth-child(2)");

    private static final By LIMIT_MONTHLY_BUTTON = By.cssSelector(".deposit-limit .button-group a[ng-repeat]:nth-child(3)");

    private static final String COUNTRY_SELECT = "#countryCode option[label='%s']";

    private static final String MONTH = "#dobSelectorMonth option[value='%s']";

    private static final String LIMIT_AMOUNT = "#input-ddl option:contains('%s')";

    private static final By ENTER_ADDRESS_MANUAL_TOGGLE = By.id("manualFields");

    private static final By SHOW_DEPOSIT_TOGGLE = By.cssSelector(".accordion");

    private static final By LIMIT_CHECKBOX = By.cssSelector(".checkbox.input-constrain");

    @Autowired
    @Lazy
    public NativeElementsComponent nativeElementsComponent;

    @Override
    public void setFirstName(String name) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(FIRST_NAME_FIELD).clear();
        find(FIRST_NAME_FIELD).sendKeys(name);
        switchToParentFrame();
    }

    @Override
    public void setSurname(String name) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(SURNAME_FIELD).clear();
        find(SURNAME_FIELD).sendKeys(name);
        switchToParentFrame();
    }

    @Override
    public void setTitle(String title) {
        switchToiFrame(JOIN_FORM_IFRAME);
        switch (title) {
            case "Mr":
                clickOnMrTitle();
                break;
            case "Mrs":
                clickOnMrsTitle();
                break;
            case "Ms":
                clickOnMsTitle();
                break;
            case "Miss":
                clickOnMissTitle();
                break;

            default:
                throw new NotImplementedException();
        }
    }

    @Override
    public void setDateOfBirth(String day, String month, String year) {
        switchToiFrame(JOIN_FORM_IFRAME);
        setDayOfBirth(day);
        setMonthOfBirth(month);
        setYearOfBirth(year);
        switchToParentFrame();
    }

    @Override
    public void setDayOfBirth(String day) {
        find(DAY_OF_BIRTH_FIELD).clear();
        find(DAY_OF_BIRTH_FIELD).sendKeys(day);
    }

    @Override
    public void setMonthOfBirth(String month) {
        find(MONTH_OF_BIRTH_FIELD).clear();
        find(MONTH_OF_BIRTH_FIELD).sendKeys(month);
    }

    @Override
    public void setYearOfBirth(String year) {
        find(YEAR_OF_BIRTH_FIELD).clear();
        find(YEAR_OF_BIRTH_FIELD).sendKeys(year);
    }

    @Override
    public void setEmail(String email) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(EMAIL_FIELD).clear();
        find(EMAIL_FIELD).sendKeys(email);
        switchToParentFrame();
    }

    @Override
    public void setMobile(String mobile) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(MOBILE_FIELD).clear();
        find(MOBILE_FIELD).sendKeys(mobile);
        switchToParentFrame();
    }

    @Override
    public void setCountry(String country) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(COUNTRY_FIELD).click();
        find(COUNTRY_FIELD).sendKeys(country);
        switchToParentFrame();
    }

    @Override
    public void setAddressLine1(String addressLine1) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(ADDRESS_LINE1_FIELD).clear();
        find(ADDRESS_LINE1_FIELD).sendKeys(addressLine1);
        switchToParentFrame();
    }

    @Override
    public void setAddressLine2(String addressLine2) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(ADDRESS_LINE2_FIELD).clear();
        find(ADDRESS_LINE2_FIELD).sendKeys(addressLine2);
        switchToParentFrame();
    }

    @Override
    public void setCity(String city) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(CITY_FIELD).clear();
        find(CITY_FIELD).sendKeys(city);
        switchToParentFrame();
    }

    @Override
    public void setCounty(String county) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(COUNTY_FIELD).clear();
        find(COUNTY_FIELD).sendKeys(county);
        switchToParentFrame();
    }

    @Override
    public void setPostalCode(String postalCode) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(POSTAL_CODE_FIELD).clear();
        find(POSTAL_CODE_FIELD).sendKeys(postalCode);
        switchToParentFrame();
    }

    @Override
    public void setUsername(String username) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(USERNAME_FIELD).clear();
        find(USERNAME_FIELD).sendKeys(username);
        switchToParentFrame();
    }

    @Override
    public void setPassword(String password) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(PASSWORD_FIELD).clear();
        find(PASSWORD_FIELD).sendKeys(password);
        switchToParentFrame();
    }

    @Override
    public void setSecurityQuestion(String question) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(SECURITY_QUESTION_FIELD).sendKeys(question);
        switchToParentFrame();
    }

    @Override
    public void setSecurityAnswer(String answer) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(SECURITY_ANWER_FIELD).clear();
        find(SECURITY_ANWER_FIELD).sendKeys(answer);
        switchToParentFrame();
    }

    @Override
    public void setCurrency(String currency) {
        switchToiFrame(JOIN_FORM_IFRAME);
        if (!isNativeMobileApp()) {
            find(CURRENCY_FIELD).clear();
            find(CURRENCY_FIELD).sendKeys(currency);
        } else {
            find(CURRENCY_INPUT).click();
            nativeElementsComponent.selectFromDropdownList(currency);
        }
        switchToParentFrame();
    }

    @Override
    public void setDepositLimit() {
        switchToiFrame(JOIN_FORM_IFRAME);
         click(SHOW_DEPOSIT_TOGGLE);
        click(LIMIT_CHECKBOX);
        switchToParentFrame();
    }

    @Override
    public void setTypeOfLimit(String typeOfLimit) {
        switchToiFrame(JOIN_FORM_IFRAME);
        if ("Daily".equalsIgnoreCase(typeOfLimit)) {
            clickOnDailyLimit();
        } else if ("Weekly".equalsIgnoreCase(typeOfLimit)) {
            clickOnWeeklyLimit();
        } else {
            clickOnMonthlyLimit();
        }
        switchToParentFrame();
    }

    @Override
    public void setLimitAmount(String limitAmount) {
        switchToiFrame(JOIN_FORM_IFRAME);
        if (!isNativeMobileApp()) {
            find(LIMIT_AMOUNT_FIELD).click();
            find(LIMIT_AMOUNT_FIELD).findElement(By.cssSelector(String.format(LIMIT_AMOUNT, limitAmount))).click();
        } else {
            find(LIMIT_AMOUNT_FIELD).sendKeys(limitAmount);
        }
        switchToParentFrame();
    }

    @Override
    public void setPromoCode(String promoCode) {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(PROMO_FIELD).clear();
        find(PROMO_FIELD).sendKeys(promoCode);
        switchToParentFrame();
    }

    @Override
    public void checkOver18() {
        switchToiFrame(JOIN_FORM_IFRAME);
        if (!find(OVER_18_CHECK).isSelected())
            find(OVER_18_CHECK).click();
        switchToParentFrame();
    }

    @Override
    public void uncheckOver18() {
        switchToiFrame(JOIN_FORM_IFRAME);
        if (find(OVER_18_CHECK).isSelected())
            find(OVER_18_CHECK).click();
        switchToParentFrame();
    }

    @Override
    public void checkReceiveFreeBets() {
        switchToiFrame(JOIN_FORM_IFRAME);
        if (!find(RECIEVE_OFFERS).isSelected())
            find(RECIEVE_OFFERS).click();
        switchToParentFrame();
    }

    @Override
    public void uncheckReceiveFreeBets() {
        switchToiFrame(JOIN_FORM_IFRAME);
        if (find(RECIEVE_OFFERS).isSelected())
            find(RECIEVE_OFFERS).click();
        switchToParentFrame();
    }

    @Override
    public String getFirstName() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String firstName = find(FIRST_NAME_FIELD).getAttribute("value");
        switchToParentFrame();
        return firstName;
    }

    @Override
    public String getSurname() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String surname = find(FIRST_NAME_FIELD).getAttribute("value");
        switchToParentFrame();
        return surname;
    }

    @Override
    public String getTitle() {
        switchToiFrame(JOIN_FORM_IFRAME);
        if (find(MR_BUTTON).isSelected()) {
            switchToParentFrame();
            return "Mr";
        } else if (find(MRS_BUTTON).isSelected()) {
            switchToParentFrame();
            return "Mrs";
        } else if (find(MS_BUTTON).isSelected()) {
            switchToParentFrame();
            return "Ms";
        } else if (find(MISS_BUTTON).isSelected()) {
            switchToParentFrame();
            return "Miss";
        } else {
            switchToParentFrame();
            throw new NotImplementedException();
        }

    }

    @Override
    public String getDateOfBirth() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String date = getDayOfBirth() + "-" + getMonthOfBirth() + "-" + getYearOfBirth();
        switchToParentFrame();
        return date;
    }

    @Override
    public String getDayOfBirth() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String day = find(DAY_OF_BIRTH_FIELD).getAttribute("value");
        switchToParentFrame();
        return day;
    }

    @Override
    public String getMonthOfBirth() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String month = find(MONTH_OF_BIRTH_FIELD).getAttribute("value");
        switchToParentFrame();
        return month;
    }

    @Override
    public String getYearOfBirth() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String year = find(YEAR_OF_BIRTH_FIELD).getAttribute("value");
        switchToParentFrame();
        return year;
    }

    @Override
    public String getEmail() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String email = find(EMAIL_FIELD).getAttribute("value");
        switchToParentFrame();
        return email;
    }

    @Override
    public String getMobile() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String mobile = find(MOBILE_FIELD).getAttribute("value");
        switchToParentFrame();
        return mobile;
    }

    @Override
    public String getCountry() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String country = find(COUNTRY_FIELD).getAttribute("value");
        switchToParentFrame();
        return country;
    }

    @Override
    public String getAddressLine1() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String address1 = find(ADDRESS_LINE1_FIELD).getAttribute("value");
        switchToParentFrame();
        return address1;
    }

    @Override
    public String getAddressLine2() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String address2 = find(ADDRESS_LINE2_FIELD).getAttribute("value");
        switchToParentFrame();
        return address2;
    }

    @Override
    public String getCity() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String city = find(CITY_FIELD).getAttribute("value");
        switchToParentFrame();
        return city;
    }

    @Override
    public String getCounty() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String county = find(COUNTY_FIELD).getAttribute("value");
        switchToParentFrame();
        return county;
    }

    @Override
    public String getPostalCode() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String postalCode = find(POSTAL_CODE_FIELD).getAttribute("value");
        switchToParentFrame();
        return postalCode;
    }

    @Override
    public String getUsername() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String userName = find(USERNAME_FIELD).getAttribute("value");
        switchToParentFrame();
        return userName;
    }

    @Override
    public String getPassword() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String password = find(PASSWORD_FIELD).getAttribute("value");
        switchToParentFrame();
        return password;
    }

    @Override
    public String getSecurityQuestion() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String security = find(SECURITY_QUESTION_FIELD).getAttribute("value");
        switchToParentFrame();
        return security;
    }

    @Override
    public String getSecurityAnswer() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String security = find(SECURITY_ANWER_FIELD).getAttribute("value");
        switchToParentFrame();
        return security;
    }

    @Override
    public String getCurrency() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String currency = find(CURRENCY_FIELD).getAttribute("value");
        switchToParentFrame();
        return currency;
    }

    @Override
    public String getDepositLimit() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String deposit = find(LIMIT_AMOUNT_FIELD).getAttribute("value");
        switchToParentFrame();
        return deposit;
    }

    @Override
    public String getPromoCode() {
        switchToiFrame(JOIN_FORM_IFRAME);
        String promo = find(PROMO_FIELD).getAttribute("value");
        switchToParentFrame();
        return promo;
    }

    @Override
    public void clickOnMrTitle() {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(MR_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnMsTitle() {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(MS_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnMrsTitle() {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(MRS_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnMissTitle() {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(MISS_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnHide() {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(HIDE_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnDailyLimit() {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(LIMIT_DAILY_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnWeeklyLimit() {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(LIMIT_WEEKLY_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnMonthlyLimit() {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(LIMIT_MONTHLY_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public void clickOnCreateAccount() {
        switchToiFrame(JOIN_FORM_IFRAME);
        find(CREATE_ACCOUNT_BUTTON).click();
        switchToParentFrame();
    }

    @Override
    public boolean isDisplayed() {
        switchToiFrame(JOIN_FORM_IFRAME);
        boolean displayed = isElementDisplayed(JOIN_FORM);
        switchToParentFrame();
        return displayed;
    }

    @Override
    public void clickOnEnterAddressManualy() {
        switchToiFrame(JOIN_FORM_IFRAME);
        click(ENTER_ADDRESS_MANUAL_TOGGLE);
        switchToParentFrame();
    }
}
