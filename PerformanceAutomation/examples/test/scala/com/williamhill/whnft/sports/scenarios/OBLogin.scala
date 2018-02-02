package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestOBObjects.{BetSlipOBBetBoost, HomePageOB, LoginOB}
import com.williamhill.whnft.sports.scenarios.OBDesktopBetBoostPlacement.betURL
import com.williamhill.whnft.sports.simulations.SimulationSettingsTrait
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  */
object OBLogin extends Simulation with SimulationSettingsTrait {

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + "OB50K.csv")

  val oBLogin =
    scenario("OB Login")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
    //  .exec(HomePageOB.get(true), LoginOB.login)
      .exec(HomePageOB.get(true), LoginOB.loginAccountNumber, LoginOB.freebetsCall)

    }

}
