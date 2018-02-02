package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object NativeDeposit extends Simulation{

  val ualistfeeder = csv("UANative.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")

  val nativeDeposit = scenario("Make Deposit - native")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .exec(HomePage.getNative, LoginNative.login, Deposit.deposit("mob", "MO", "mob_ext", Common.numberOfDepositsPerUser))
    }// TODO: Make these calls only for PP!, Deposit.depositAfterCall1, Deposit.depositAfterCall2)

}
