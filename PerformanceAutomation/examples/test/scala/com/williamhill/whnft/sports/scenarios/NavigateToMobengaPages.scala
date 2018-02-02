package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestMobengaObjects.PageViewsMobenga
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object NavigateToMobengaPages extends Simulation{

  val ualistfeeder = csv("UAMobileAndroid.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")
  val events = csv("Events.csv")

  val navigateToMobengaPages =
    scenario("Navigate to Mobenga pages")
    .forever{
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .feed(events.random.circular)
      .exec(
        PageViewsMobenga.navigateToHome,
        PageViewsMobenga.navigateToStringsJson,
        PageViewsMobenga.navigateToDataInPlayHome,
        PageViewsMobenga.navigateToDataInPlay,
        PageViewsMobenga.navigateToFootball,
        PageViewsMobenga.navigateToFootballByDay,
        PageViewsMobenga.getCometIframe,
        PageViewsMobenga.getCometCogIframe,
        PageViewsMobenga.getIndexContent,
        PageViewsMobenga.getSidemenu,
        PageViewsMobenga.navigateToWebkitGeneric,
        PageViewsMobenga.navigateToHorseRacing
      )
    }

}




