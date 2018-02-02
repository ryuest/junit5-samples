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

object MobileBetPlacement extends Simulation{

  val ualistfeeder = csv("UAMobile.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")
  val selections = csv("Selections.csv")
  
    var betCode = ""
    
    if (ualistfeeder.toString().contains(("iPhone")))
    {
      betCode = "852"
      //    prefix2 = "_iOS_Mobile";
    } else {
       if (ualistfeeder.toString().contains(("iPad"))) {
          betCode = "851"
          //      prefix2 = "_iOS_Tablet";
       } else {
          if (ualistfeeder.toString().contains(("Nexus 7"))) {
          betCode = "851"
          //      prefix2 = "_iOS_Tablet";
          } else {
              if (ualistfeeder.toString().contains(("Android"))) {
                betCode = "852"
          //      prefix2 = "_Android_Mobile";
              }
          }
       }
    }

  val betURL = "mbt_slp"

  val placeBetMobile =
    scenario("Mobile bet placement")
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
          BetSlip.placebet(betCode, betURL, Common.numberOfBetsPerUser),
          Login.getBalance
        )
      }
    }
}
