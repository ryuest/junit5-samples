package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import com.williamhill.whnft.sports.helpers.Common

object MobileDeposit extends Simulation{

  val ualistfeeder = csv("UAMobile.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")

  val mobileDeposit =
    scenario("Make Deposit - mobile")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .exec(HomePage.get, Login.login, Deposit.deposit("mob", "MO", "mob_ext", Common.numberOfDepositsPerUser))
      //TODO: Make these calls only for PP!, Deposit.depositAfterCall1, Deposit.depositAfterCall2)
    }

}
