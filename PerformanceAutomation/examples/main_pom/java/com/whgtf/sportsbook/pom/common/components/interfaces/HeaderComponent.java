package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.components.impl.HeaderComponentImpl;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractSportsPage;

import java.util.List;


public interface HeaderComponent extends AbstractCommonInterface {

    /**
     * Click on show more button in the header
     * @return the header element
     */
    HeaderComponentImpl clickOnShowMore();

    /**
     * Click on a specific button in the header
     *
     * @param sport The sport name
     * @return the required element
     */
    Object clickOnSport(final String sport);

    /**
     * Click on football button in the header
     * @return the sports page
     */
    AbstractSportsPage clickOnFootball();

    /**
     * Click on Darts button in the header
     * @return the sports page
     */
    AbstractSportsPage clickOnDarts();

    /**
     * Click on Greyhounds button in the header
     * @return the sports page
     */
    AbstractSportsPage clickOnGreyhounds();

    /**
     * Click on Horses button in the header
     * @return the sports page
     */
    AbstractSportsPage clickOnHorses();

    /**
     * Click on Tennis button in the header
     * @return the sports page
     */
    AbstractSportsPage clickOnTennis();

    /**
     * Click on In-Play button in the header
     * @return the sports page
     */
    AbstractSportsPage clickOnInPlay();

    /**
     * Click on Login button
     * @return LoginComponent
     */
    LoginComponent clickOnLoginButton();

    /**
     * Click on Account component (logged in)
     *
     * @return AccountTabComponent
     */
    AccountTabComponent clickOnAccountComponent();

    /**
     *
     * @return JoinFormComponent
     */
    JoinFormComponent clickOnJoinButton();

    /**
     *
     * @return AbstractSportsPage
     */
    AbstractSportsPage clickOnTopBets();

    /**
     * Check if the balance is displayed
     *
     * @return true if the balance is displayed
     */
    boolean isBalanceDisplayed();

    /**
     * Check if the Join Now button is displayed.
     *
     * @return true if the join now button is displayed
     */
    boolean isJoinNowDisplayed();

    /**
     * Get the balance from the header
     *
     * @return the balance
     */
    String getBalance();

    /**
     * Get login component
     *
     * @return LoginComponent
     */
    LoginComponent getLoginComponent();

    /**
     * This method returns a list of Sports Names displayed on the Header.
     * Warning: In order this to work, needs to be expanded the section to display all sports.
     *
     * @return the list of sports
     */
    List<String> getListSports();

    /**
     * On this method we click on the Given Sport Name.
     * Warning: Needs to be expanded the menu, in order to see al sports and be able to click on it.
     *
     * @param name The sport name
     */
    void clickOnSportByName(String name);

    boolean waitForBalanceValue(String expectedValue, long timeout);

}
