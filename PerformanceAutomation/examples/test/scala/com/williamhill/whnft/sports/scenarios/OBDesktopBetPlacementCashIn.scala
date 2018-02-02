package com.williamhill.whnft.sports.scenarios

import java.util.Calendar

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestOBObjects._
import com.williamhill.whnft.sports.requestTrafagarObjects._
import com.williamhill.whnft.sports.scenarios.OBDesktopBetPlacement.betURL
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  */

object OBDesktopBetPlacementCashIn extends Simulation {

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + "OB.csv")
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

  val placeBetDesktopCashInOB =
    scenario("OB Desktop bet placement and Cash In")
      .forever {
        exec(session => session.reset)
          .exec(flushHttpCache)
          .exec(flushCookieJar)
          .exec(flushSessionCookies)
          .feed(ualistfeeder.random.circular)
          .feed(usersfeeder.random.circular)
          .feed(events.random.circular)
          .feed(selections.random.circular)
          .exec(HomePageOB.get(true), LoginOB.login, BetSlipOB.placebet(betURL, Common.numberOfBetsPerUser),
            BetSlipCashIn.cashInBet(Common.numberOfBetsPerUser))
      }
}