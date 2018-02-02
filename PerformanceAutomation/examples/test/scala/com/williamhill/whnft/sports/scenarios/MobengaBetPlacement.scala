package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestMobengaObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  */

object MobengaBetPlacement extends Simulation {

  // TODO: This should be a mobile UA
  val ualistfeeder = csv("UAMobileAndroid.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")
  val events = csv("Events.csv")

  var betCode = "850"
  val betURL = "slp"
  val fromDate = 0

  val placeBetMobenga =
    scenario("Mobenga: Bet placement")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .feed(events.random.circular)
      .exec(
        SelectEventPartialMobenga.fetchSelectionsFromEventPartial,
        HomePageMobenga.get,
        LoginMobenga.clickLogin,
        LoginMobenga.login,
        BetSlipMobenga.placeBet(Common.numberOfBetsPerUser)
      )
    }

}
