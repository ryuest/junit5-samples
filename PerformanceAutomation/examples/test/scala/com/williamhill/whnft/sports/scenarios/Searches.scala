package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object Searches extends Simulation{

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')

  val mixedSearchScenarios =
    scenario("Various search scenarios - Desktop")
    .forever{
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .exec(
        SearchFunctionality.makeRealisticSearch,
        SearchFunctionality.generateCacheMisses
      )
    }

}




