package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 22/02/2017.
  */
object DesktopHomepage extends Simulation{

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
//  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")

  val desktopHomePage =
    scenario("Desktop Homepage")
//    .forever {
      .exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
//      .feed(usersfeeder.random.circular)
      .exec(HomePage.get)
//    }
}
