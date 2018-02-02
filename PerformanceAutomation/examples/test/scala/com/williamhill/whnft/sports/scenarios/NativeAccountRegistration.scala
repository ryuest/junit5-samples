package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object NativeAccountRegistration extends Simulation{

  val ualistfeeder = csv("UANative.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")

  var productSource = "SM" //IPhone
    
  if (ualistfeeder.toString().contains(("iPad")))
    productSource = "ST"

  val registerNativeAccount =
    scenario("Account registration - Native")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .exec(AccountRegistration.clickJoin(productSource, "native"), AccountRegistration.accountRegistration(productSource))
    }

}
