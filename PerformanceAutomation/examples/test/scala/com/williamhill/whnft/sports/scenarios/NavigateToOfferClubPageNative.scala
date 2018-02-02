package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object NavigateToOfferClubPageNative extends Simulation{

  val ualistfeeder = csv("UANative.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")

  val navigateToOfferClubPageNative =
    scenario("Navigate to offer-club native page")
    .forever{
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .exec(
        // HomePage.getNative,
        // LoginNative.login,
        // PageViews.getOfferClubFlow
      )
    }

}




