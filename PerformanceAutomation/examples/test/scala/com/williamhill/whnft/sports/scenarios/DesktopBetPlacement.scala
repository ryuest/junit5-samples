package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common._
import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  */

object DesktopBetPlacement extends Simulation {

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")
  val selections = csv("Selections.csv")

  var betCode = "850"
  val betURL = "slp"
  val fromDate = 0

  val placeBetDesktop =
    scenario("Desktop bet placement")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .feed(selections.random.circular)
      .exec(HomePage.get, Login.login)
      .doIf(session => userLoggedIn(session)) {
        exec(
          PageViews.getEvent,
          BetSlip.placebet(betCode, betURL, Common.numberOfBetsPerUser),
          Login.getBalance
        )
      }
    }

}
