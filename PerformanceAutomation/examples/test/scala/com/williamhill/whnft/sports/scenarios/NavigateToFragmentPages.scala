package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.UserAgents._
import com.williamhill.whnft.sports.requestTrafagarObjects.HomePage._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object NavigateToFragmentPages extends Simulation{

  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")
  val events = csv("Events.csv")

  def navigateToFragmentPages(platform: Platform) =
    scenario("Fragment pages " + platform.name)
    .forever{
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .exec(setAgentFeed(platform))
      .feed(usersfeeder.random.circular)
      .feed(events.random.circular)
      .exec(
        getFragments
      )
    }

}




