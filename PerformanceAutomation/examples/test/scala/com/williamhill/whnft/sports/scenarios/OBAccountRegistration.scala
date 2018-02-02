package com.williamhill.whnft.sports.scenarios

import java.util.Calendar

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestOBObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  */

object OBAccountRegistration extends Simulation {

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + "OB.csv")
  val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
  val month = Calendar.getInstance().get(Calendar.MONTH) + 1
  val betURL = "slp"

  val accountRegistrationOB =
    scenario("OB Account registration")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .exec(HomePageOB.get(), AccountRegistrationOB.clickJoin, AccountRegistrationOB.accountRegistration)
   }

}
