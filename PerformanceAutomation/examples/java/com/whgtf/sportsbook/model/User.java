package com.whgtf.sportsbook.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by javierg on 07/09/2016.
 */
public class User {

    private String username;

    private String password;

    private String title;

    private String forename;

    private String surname;

    private String city;

    private String country;

    private String houseNumber;

    private String address2;

    private String postCode;

    private String occupation;

    private String dob;

    private String creditLimit;

    private String email;

    private String mobile;

    public User() {
        int randomNumber = RandomUtils.nextInt(1,9999);
        String randomStringLonger = RandomStringUtils.randomAlphabetic(7);
        setPassword("automation");
        setTitle("Mr");
        setForename("AUTOMATION" + RandomStringUtils.randomAlphabetic(3));
        setSurname("AUTOMATION" + RandomStringUtils.randomAlphabetic(3));
        setCountry("United Kingdom");
        setHouseNumber(String.valueOf(RandomStringUtils.randomNumeric(2)));
        setAddress2(RandomStringUtils.randomAlphabetic(7));
        setPostCode(RandomStringUtils.randomAlphabetic(3));
        setOccupation(RandomStringUtils.randomAlphabetic(7));
        setDob(generateRandomDob());
        setCreditLimit("5000");
        setEmail(ramdomEmail());
        setMobile(RandomStringUtils.randomNumeric(7));
        setCity(RandomStringUtils.randomAlphabetic(7));
    }

    private String ramdomEmail() {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd hh:mm");
        String strDate = dateFormat.format(date).replace(" ","_").replace(":","_");

        return "WHATA_".concat(strDate).concat("@williamhill.com");
    }

    public void setUsername(final String user) {
        this.username = user;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCity() {
        return city;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setEmail(String input) {
        this.email = input;
    }

    public void setMobile(String input) {
        this.mobile = input;
    }

    public void setCity(String input) {
        this.city = input;
    }


    private String generateRandomDob() {
        String year = String.valueOf(RandomUtils.nextInt(1900, 1997));
        int day = RandomUtils.nextInt(1,28);
        String stringDay = String.valueOf(day);
        if(day<10)
            stringDay = "0".concat(stringDay);
        int month = RandomUtils.nextInt(1,12);
        String stringMonth = String.valueOf(month);
        if(month<10)
            stringMonth = "0".concat(stringMonth);

        return year.concat("-").concat(stringMonth).concat("-").concat(stringDay);
    }


}
