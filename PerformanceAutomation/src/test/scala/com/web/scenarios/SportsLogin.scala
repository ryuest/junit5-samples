package com.web.scenarios

import com.web.helpers.Common
import com.web.simulations.SimulationSettingsTrait
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import com.web.requestObjects.HomePage
/**
  * Created by jboiko on 15/12/2017.
  */
object SportsLogin extends Simulation with SimulationSettingsTrait {

 // val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
 // val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + "OB50K.csv")

  val SportsLogin =
    scenario("SportsLogin")
      .forever {
        exec(session => session.reset)
          .exec(flushHttpCache)
          .exec(flushCookieJar)
          .exec(flushSessionCookies)
      //    .feed(ualistfeeder.random.circular)
      //    .feed(usersfeeder.random.circular)
          .exec(HomePage.getHomePage)
      }
}
