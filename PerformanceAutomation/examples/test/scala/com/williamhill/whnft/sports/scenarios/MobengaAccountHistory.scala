package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestMobengaObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  */

object MobengaAccountHistory extends Simulation {

  // TODO: This should be a mobile UA
  val ualistfeeder = csv("UAMobileAndroid.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")
  val events = csv("Events.csv")

  val mobengaAccountHistory =
    scenario("Mobenga account history")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .feed(events.random.circular)
      .exec(
        HomePageMobenga.get,
        LoginMobenga.clickLogin,
        LoginMobenga.login,
        AccountHistoryMobenga.betHistory(Common.numberOfAccountStatements)
      )
    }

}
