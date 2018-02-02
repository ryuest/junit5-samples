package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  */
object NewCasLogin extends Simulation{

  val ualistfeeder = csv("UAMobile.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")

  val newCasLogin =
    scenario("New Cas Login")
      .forever {
        exec(session => session.reset)
          .exec(flushHttpCache)
          .exec(flushCookieJar)
          .exec(flushSessionCookies)
          .feed(ualistfeeder.random.circular)
          .feed(usersfeeder.random.circular)
          .exec(HomePage.get, LoginNativeNewCas.login)
      }
}

