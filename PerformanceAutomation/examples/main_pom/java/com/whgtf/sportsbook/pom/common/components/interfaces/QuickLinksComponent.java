package com.whgtf.sportsbook.pom.common.components.interfaces;

import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractCommonInterface;
import com.whgtf.sportsbook.pom.common.pages.interfaces.AbstractSportsPage;

import java.util.List;


public interface QuickLinksComponent extends AbstractCommonInterface {

    Object clickOnSport(final String sport);

    AbstractSportsPage clickOnFootball();

    AbstractSportsPage clickOnDarts();

    AbstractSportsPage clickOnGreyhounds();

    AbstractSportsPage clickOnHorses();

    AbstractSportsPage clickOnTennis();

    AbstractSportsPage clickOnVirtualWorld();

    AbstractSportsPage clickOnInPlay();

    List<String> getListSports();

    /**
     * This method will click on any given sport name from the A-Z Menu.
     * Important Note: The "Name" of the sport must be the exact same of it, otherwise wont work.
     *                 e.g.: "football" wont work, but "Football" will work.
     * @param name - Exact name of the sport.
     */
    void clickOnSportByName(String name);
}
