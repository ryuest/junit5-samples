package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;


public interface JoinFormComponent extends AbstractCommonInterface {

    void setFirstName(String name);

    void setSurname(String name);

    void setTitle(String title);

    void setDateOfBirth(String day, String month, String year);

    void setDayOfBirth(String day);

    void setMonthOfBirth(String month);

    void setYearOfBirth(String year);

    void setEmail(String email);

    void setMobile(String mobile);

    void setCountry(String country);

    void setAddressLine1(String addressLine1);

    void setAddressLine2(String addressLine2);

    void setCity(String city);

    void setCounty(String county);

    void setPostalCode(String postalCode);

    void setUsername(String username);

    void setPassword(String password);

    void setSecurityQuestion(String question);

    void setSecurityAnswer(String answer);

    void setCurrency(String currency);

    void setDepositLimit();

    void setTypeOfLimit(String typeOfLimit);

    void setLimitAmount(String limitAmount);

    void setPromoCode(String promoCode);

    void checkOver18();

    void uncheckOver18();

    void checkReceiveFreeBets();

    void uncheckReceiveFreeBets();

    String getFirstName();

    String getSurname();

    String getTitle();

    String getDateOfBirth();

    String getDayOfBirth();

    String getMonthOfBirth();

    String getYearOfBirth();

    String getEmail();

    String getMobile();

    String getCountry();

    String getAddressLine1();

    String getAddressLine2();

    String getCity();

    String getCounty();

    String getPostalCode();

    String getUsername();

    String getPassword();

    String getSecurityQuestion();

    String getSecurityAnswer();

    String getCurrency();

    String getDepositLimit();

    String getPromoCode();

    void clickOnMrTitle();

    void clickOnMsTitle();

    void clickOnMrsTitle();

    void clickOnMissTitle();

    void clickOnHide();

    void clickOnDailyLimit();

    void clickOnWeeklyLimit();

    void clickOnMonthlyLimit();

    void clickOnCreateAccount();

    boolean isDisplayed();

    void clickOnEnterAddressManualy();
}
