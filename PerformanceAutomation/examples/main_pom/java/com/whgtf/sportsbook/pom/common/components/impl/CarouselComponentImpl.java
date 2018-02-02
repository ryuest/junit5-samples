package com.whgtf.sportsbook.pom.common.components.impl;

import com.whgtf.sportsbook.pom.common.components.interfaces.CarouselComponent;
import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractCommonObject;
import com.whgtf.sportsbook.pom.common.pages.interfaces.*;
import org.apache.commons.collections.map.LinkedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Component
@Lazy
public class CarouselComponentImpl extends AbstractCommonObject implements CarouselComponent {

    private static final By CAROUSEL = By.id("carousel");

    private static final By MEETING_ICON = By.cssSelector(".navMeetingsMenu a");

    private static final By ANTEPOST_ICON = By.cssSelector(".navAntePost a");

    private static final By TOPBETS_ICON = By.cssSelector(".navTopBets a");

    private static final By SPECIALS_ICON = By.cssSelector(".navSpecials a");

    private static final By NEXT_OFF_ICON = By.cssSelector(".navMeetingsNextOff a");

    private static final By COMPETITIONS_ICON = By.cssSelector(".navCompetitions a");

    private static final By IN_PLAY_ICON = By.cssSelector(".navInplay a");

    private static final By DAILY_LIST_ICON = By.cssSelector(".navMatches a");

    private static final By COUPONS_ICON = By.cssSelector(".navCoupons a");

    private static final By MEETING_TAB_ACTIVE = By.cssSelector("#carousel li[class='contextual-nav__item contextual-nav__item--active navMeetingsMenu']");

    private static final By NEXT_OFF_TAB_ACTIVE = By.cssSelector("#carousel li[class='contextual-nav__item navMeetingsNextOff contextual-nav__item--active']");

    //Virtual

    private static final By CAROUSEL_ELEMENTS_TEXT = By.cssSelector("#carousel .contextual-nav__title");

    private static final By CAROUSEL_HIGHLIGHTED_ELEMENT_TEXT = By.cssSelector("#carousel li[class*='active'] .contextual-nav__title");

    private static final By CAROUSEL_FEATURED = By.cssSelector("#carousel a[href='/betting/en-gb/apps/virtual']");

    private static final By CAROUSEL_HORSE_RACING_FLAT = By.cssSelector("#carousel a[href*='horse-racing/flat']");

    private static final By CAROUSEL_HORSE_RACING_NATIONAL_HUNT = By.cssSelector("#carousel a[href*='national-hunt']");

    private static final By CAROUSEL_FOOTBALL = By.cssSelector("#carousel a[href*='football']");

    private static final By CAROUSEL_GREYHOUNDS_FLAT = By.cssSelector("#carousel a[href*='greyhounds/flat']");

    private static final By CAROUSEL_GREYHOUNDS_HURDLES = By.cssSelector("#carousel a[href*='jumps']");

    private static final By CAROUSEL_MOTOR_RACING = By.cssSelector("#carousel a[href*='motor-racing']");

    private static final By CAROUSEL_SPEEDWAY = By.cssSelector("#carousel a[href*='speedway']");

    private static final By CAROUSEL_CYCLING = By.cssSelector("#carousel a[href*='cycling']");

    private static final By CAROUSEL_ANTEPOST = By.cssSelector("#carousel a[href*='future-races']");

    private static final By CAROUSEL_TOP_BETS = By.cssSelector("#carousel a[href*='top-bets']");

    private static final By CAROUSEL_SPECIAL = By.cssSelector("#carousel a[href*='specials']");

    private static final By CAROUSEL_COUPON = By.cssSelector("#carousel a[href*='coupons']");

    private static final By CAROUSEL_COMPETITIONS = By.cssSelector("#carousel a[href*='competitions']");

    private static final By CAROUSEL_DAILY_LIST = By.cssSelector("#carousel a[href*='matches']");

    private static final By CAROUSEL_IN_PLAY = By.cssSelector("#carousel a[href*='in-play']");

    // All In Play Carousel

    private static final By HIGHLIGHTED_ELEMENT_TEXT_IN_PLAY = By.cssSelector(".contextual-nav__item.contextual-nav__item--active .grid.grid--vertical");

    private static final By ALL_SPORTS_IN_PLAY = By.cssSelector("a[data-sport-slug='in-play']");

    private static final By FOOTBALL_IN_PLAY = By.cssSelector("a[data-sport-slug='football']");

    private static final By CRICKET_IN_PLAY = By.cssSelector("a[data-sport-slug='cricket']");

    private static final By TENNIS_IN_PLAY = By.cssSelector("a[data-sport-slug='tennis']");

    private static final By BASKETBALL_IN_PLAY = By.cssSelector("a[data-sport-slug='basketball']");


    private static final Map<String, By> allInPlayCarouselElements;
    static {
        Map<String, By> elementMap = new LinkedMap();

        elementMap.put("All Sports", ALL_SPORTS_IN_PLAY);
        elementMap.put("Football", FOOTBALL_IN_PLAY);
        elementMap.put("Cricket", CRICKET_IN_PLAY);
        elementMap.put("Tennis", TENNIS_IN_PLAY);
        elementMap.put("Basketball", BASKETBALL_IN_PLAY);

        allInPlayCarouselElements = Collections.unmodifiableMap(elementMap);
    }


    private static final Map<String, By> virtualCarouselElements;
    static {
        Map<String, By> elementMap = new LinkedMap();

        elementMap.put("Featured", CAROUSEL_FEATURED);
        elementMap.put("Horse Racing - Flat", CAROUSEL_HORSE_RACING_FLAT);
        elementMap.put("Horse Racing - National Hunt", CAROUSEL_HORSE_RACING_NATIONAL_HUNT);
        elementMap.put("Football", CAROUSEL_FOOTBALL);
        elementMap.put("Greyhounds - Flat", CAROUSEL_GREYHOUNDS_FLAT);
        elementMap.put("Greyhounds - Hurdles", CAROUSEL_GREYHOUNDS_HURDLES);
        elementMap.put("Motor racing", CAROUSEL_MOTOR_RACING);
        elementMap.put("Speedway", CAROUSEL_SPEEDWAY);
        elementMap.put("Cycling", CAROUSEL_CYCLING);
        virtualCarouselElements = Collections.unmodifiableMap(elementMap);
    }

    private static final Map<String, By> carouselElements;
    static {
        Map<String, By> elementMap = new LinkedMap();

        elementMap.put("Featured", CAROUSEL_FEATURED);
        elementMap.put("Horse Racing - Flat", CAROUSEL_HORSE_RACING_FLAT);
        elementMap.put("Horse Racing - National Hunt", CAROUSEL_HORSE_RACING_NATIONAL_HUNT);
        elementMap.put("Football", CAROUSEL_FOOTBALL);
        elementMap.put("Greyhounds - Flat", CAROUSEL_GREYHOUNDS_FLAT);
        elementMap.put("Greyhounds - Hurdles", CAROUSEL_GREYHOUNDS_HURDLES);
        elementMap.put("Motor racing", CAROUSEL_MOTOR_RACING);
        elementMap.put("Speedway", CAROUSEL_SPEEDWAY);
        elementMap.put("Cycling", CAROUSEL_CYCLING);
        elementMap.put("antepost",CAROUSEL_ANTEPOST);
        elementMap.put("top bets",CAROUSEL_TOP_BETS);
        elementMap.put("specials",CAROUSEL_SPECIAL);
        elementMap.put("coupons",CAROUSEL_COUPON);
        elementMap.put("competitions",CAROUSEL_COMPETITIONS);
        elementMap.put("matches",CAROUSEL_DAILY_LIST);
        elementMap.put("in-play",CAROUSEL_IN_PLAY);
        carouselElements = Collections.unmodifiableMap(elementMap);
    }


    @Autowired
    @Lazy
    private MeetingsNavigationComponentImpl meetingsNavigationComponent;

    @Autowired
    @Lazy
    private CouponsPage couponsPage;

    @Autowired
    @Lazy
    private TopBetsPage topBetsPage;

    @Autowired
    @Lazy
    private SpecialRacingPage specialRacingPage;

    @Autowired
    @Lazy
    private CompetitionsPage competitionsPage;

    @Autowired
    @Lazy
    private InPlaySectionImpl inPlaySection;

    @Autowired
    @Lazy
    private HighlightsSectionImpl highlightsSection;

    @Autowired
    @Lazy
    private DailyListPage dailyListPage;


    public boolean isDisplayed() {
        return isElementDisplayed(CAROUSEL);
    }

    public MeetingsNavigationComponentImpl clickOnMeetingsIcon(){
        find(MEETING_ICON).click();
        return meetingsNavigationComponent;
    }

    public MeetingsNavigationComponentImpl clickOnAntePostIcon(){
        clickAndWaitSportsbook(ANTEPOST_ICON);
        return meetingsNavigationComponent;
    }

    public TopBetsPage clickOnTopBetsIcon() {
        clickAndWaitSportsbook(TOPBETS_ICON);
        return topBetsPage;
    }

    public SpecialRacingPage clickOnSpecialIcon(){
        clickAndWaitSportsbook(SPECIALS_ICON);
        return specialRacingPage;
    }

    public CouponsPage clickOnCouponsIcon() {
        clickAndWaitSportsbook(COUPONS_ICON);
        return couponsPage;
    }

    public CompetitionsPage clickOnCompetitionsIcon() {
        clickAndWaitSportsbook(COMPETITIONS_ICON);
        return competitionsPage;
    }

    @Override
    public void clickOnNextOffIcon(){
        clickAndWaitSportsbook(NEXT_OFF_ICON);
    }

    @Override
    public boolean isTopBetIconDisplayed() {
        return isElementDisplayed(TOPBETS_ICON);
    }

    @Override
    public InPlaySectionImpl clickOnInPlaySection() {
        clickAndWaitSportsbook(IN_PLAY_ICON);
        return inPlaySection;
    }

    @Override
    public DailyListPage clickOnDailyListIcon() {
        clickAndWaitSportsbook(DAILY_LIST_ICON);
        return dailyListPage;
    }

    @Override
    public void clickOnGivenCarouselElement(String elementName){
        for (String name : carouselElements.keySet()) {
            if(name.equalsIgnoreCase(elementName)){
                click(carouselElements.get(elementName));
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clickOnGivenAllInPlayCarouselElement(String elementName){
        click(allInPlayCarouselElements.get(elementName));
    }

    @Override
    public boolean highlightedOptionInVirtualWorldCarousel(String option){
        return find(CAROUSEL_HIGHLIGHTED_ELEMENT_TEXT).getText().equals(option);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String highlightedOptionInAllInPlayCarousel(){
        return find(HIGHLIGHTED_ELEMENT_TEXT_IN_PLAY).getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int amountOfHighlightedOptionsInAllInPlayCarousel(){
        return findElements(HIGHLIGHTED_ELEMENT_TEXT_IN_PLAY).size();
    }

    @Override
    public List<String> getVirtualWorldCarouselSportNames(){
        List<WebElement> currentList = findElements(CAROUSEL_ELEMENTS_TEXT);
        List<String> sportNames = new ArrayList<>();
        int iterator = 0;
        for (WebElement element:currentList) {
            sportNames.add(element.getText());
        }
        return sportNames;
    }

    @Override
    public List<String> getExpectedVirtualSportNames() {
        List<String> expected = new ArrayList<>();
        expected.addAll(virtualCarouselElements.keySet());
        return expected;
    }

    @Override
    public boolean isMeetingsTabSelected() {
        return isElementDisplayed(MEETING_TAB_ACTIVE);
    }

    @Override
    public boolean isNextOffTabSelected() {
        waitElementToBeVisible(NEXT_OFF_TAB_ACTIVE, 5);
        return isElementDisplayed(NEXT_OFF_TAB_ACTIVE);
    }

    @Override
    public List<String> getIconsText() {
        List<WebElement> iconList = find(CAROUSEL).findElements(By.cssSelector(".contextual-nav__item"));
        List<String> resultList = new ArrayList<>();
        for (WebElement element:iconList) {
            if(element.isDisplayed()) {
                String text;
                if(!element.getAttribute("class").contains("contextual-nav__item-sprite")) {
                    text = element.findElement(By.tagName("p")).getText();
                } else{
                    text = element.findElement(By.tagName("span")).getText();
                }
                resultList.add(text);
            }
        }
        return resultList;
    }
}
