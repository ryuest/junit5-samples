package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;

public interface AccountPreferencesComponent extends AbstractCommonInterface {

    void setCurrentPassword(String password);

    void setNewPassword(String password);

    void setEmail(String email);

    void setAddressLine1(String address);

    void setAddressLine2(String address);

    void setAddressLine3(String address);

    void setCity(String city);

    void setPostcode(String postcode);

    void setMobile(String mobile);

    void clickUpdatePassword();

    void clickUpdateContactDetails();

    boolean isPasswordSuccessfullyUpdatedAlertDisplayed();

    boolean isDetailsSuccessfullyUpdatedAlertDisplayed();

    String getCurrentPassword();

    String getNewPassword();

    String getFullName();

    String getEmail();

    String getAddressLine1();

    String getAddressLine2();

    String getAddressLine3();

    String getCity();

    String getPostcode();

    String getCountry();

    String getMobile();
}
