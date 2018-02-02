package com.williamhill.whnft.sports.scenarios

import java.util.Calendar

import com.williamhill.whnft.sports.requestOBObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import com.williamhill.whnft.sports.helpers.Common

/**
  * Created by lorenzo on 25/04/2016.
  */

object OBDesktopBetBoostPlacement extends Simulation {

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + "OB50K.csv")
  val events = csv("Events.csv")
  val selections = csv("Selections.csv")
  val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
  val month = Calendar.getInstance().get(Calendar.MONTH) + 1

  val isPTE : Boolean =
    Common.environmentUnderTest.toUpperCase == "PTE"

  val betURL : String =
    if (isPTE)
      "slp_gib"
    else
      "slp"

  val obURL : String =
    if (isPTE)
      "bet_gib"
    else
      "bet"

  val placeBetBoostDesktopOB =
    scenario("OB Desktop bet placement")
      .forever {
        exec(session => session.reset)
          .exec(flushHttpCache)
          .exec(flushCookieJar)
          .exec(flushSessionCookies)
          .feed(ualistfeeder.random.circular)
          .feed(usersfeeder.random.circular)
        //  .feed(usersfeeder.circular)
          .feed(selections.random.circular)
          .exec(HomePageOB.get(true), LoginOB.login, BetSlipOBBetBoost.placebetWithBoost(betURL, Common.numberOfBetsPerUser), BetSlipOBBetBoost.doOpenBetsCall("Single To Win (Boosted)")) // BET_TYPE_SGL BET_SLIP_LEG_TYPE_W (BET_SLIP_BOOST_TITLE)

        //  .exec(HomePageOB.get(true), LoginOB.login, BetSlipOBBetBoost.placeDoubleBetBoost(betURL, Common.numberOfBetsPerUser), BetSlipOBBetBoost.doOpenBetsCall("Double To Win (Boosted)")) // Single To Win (Boosted)

        //  .exec(HomePageOB.get(true), LoginOB.login, BetSlipOBBetBoost.placeDoubleBetBoost(betURL, Common.numberOfBetsPerUser))

  //        .exec(HomePageOB.get(true), LoginOB.login, BetSlipOBBetBoost.addBoost(betURL, obURL, Common.numberOfBetsPerUser))
        //    .exec(HomePageOB.get(true), LoginOB.login, BetSlipOBBetBoost.addBoostDoEW(betURL, obURL, Common.numberOfBetsPerUser), BetSlipOBBetBoost.doOpenBetsCall("Single EW (Boosted)"))
      }
}