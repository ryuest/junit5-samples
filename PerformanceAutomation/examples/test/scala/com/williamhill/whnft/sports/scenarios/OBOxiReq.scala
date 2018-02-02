package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.OxiReqAccountHistory
import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestOBObjects.{HomePageOB, LoginOB}
import com.williamhill.whnft.sports.simulations.SimulationSettingsTrait
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  */
object OBOxiReq extends Simulation with SimulationSettingsTrait {

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")

  val oBOxiReq =
    scenario("OB Oxi Req")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .exec(OxiReqAccountHistory.reqAccountValidate(), OxiReqAccountHistory.reqAccountHistory())
    }

}
