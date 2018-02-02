package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object MobileAccountRegistration extends Simulation{

  val ualistfeeder = csv("UAMobile.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")

  var productSource = "SM" // Android
    
  if (ualistfeeder.toString().contains(("iPhone"))) {
    productSource = "SM"
  } else if (ualistfeeder.toString().contains(("iPad"))) {
    productSource = "ST"
  } else if (ualistfeeder.toString().contains(("Nexus 7"))) {
    productSource = "ST"
  }

  val registerMobileAccount =
    scenario("Account registration - Mobile")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .exec(AccountRegistration.clickJoin(productSource, "mobile"), AccountRegistration.accountRegistration(productSource))
    }

}
