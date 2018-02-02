package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestMobengaObjects.{AccountRegistrationMobenga, HomePageMobenga, LoginMobenga}
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object MobengaAccountRegistration extends Simulation{

  val ualistfeeder = csv("UAMobileAndroid.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")

  val registerMobengaAccount =
    scenario("Mobenga Account registration")
    .forever{
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .exec(HomePageMobenga.get, LoginMobenga.clickLogin, AccountRegistrationMobenga.register)
    }

}
