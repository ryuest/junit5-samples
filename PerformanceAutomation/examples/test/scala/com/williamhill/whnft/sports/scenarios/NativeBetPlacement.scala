package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.Common.userLoggedIn
/**
  * Created by lorenzo on 25/04/2016.
  */
object NativeBetPlacement extends Simulation{

  val ualistfeeder = csv("UANative.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")
  val selections = csv("Selections.csv")

  var betCode = ""
  if (ualistfeeder.toString().contains(("iPhone"))) {
    betCode = "800"
    // _iPhone_App
  } else if (ualistfeeder.toString().contains(("iPad"))) {
    betCode = "801"
    // _iPad_App"
  }

  val betURL = "mbt_slp"

  val placeBetNative =
    scenario("Native bet placement")
    .forever("loop") {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .feed(selections.random.circular)
      .exec(HomePage.getNative, LoginNative.login)
      .doIf(session => userLoggedIn(session)) {
        exec(
          BetSlip.placebet(betCode, betURL, Common.numberOfBetsPerUser),
          LoginNative.getBalance
        )
      }
    }

  }
