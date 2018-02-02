package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestMobengaObjects.{HomePageMobenga, LoginMobenga}
import com.williamhill.whnft.sports.requestTrafagarObjects.Deposit
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object MobengaDeposit extends Simulation {

  val ualistfeeder = csv("UAMobileAndroid.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")

  val mobengaDeposit =
    scenario("Mobenga: Deposit")
      .forever {
        exec(session => session.reset)
        .exec(flushHttpCache)
        .exec(flushCookieJar)
        .exec(flushSessionCookies)
        .feed(ualistfeeder.random.circular)
        .feed(usersfeeder.random.circular)
        .exec(HomePageMobenga.get, LoginMobenga.clickLogin, LoginMobenga.login,
          Deposit.deposit("mob", "DP", "mob_ext", Common.numberOfDepositsPerUser))
      }

}
