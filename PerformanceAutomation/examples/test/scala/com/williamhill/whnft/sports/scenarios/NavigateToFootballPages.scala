package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestTrafagarObjects._
import com.williamhill.whnft.sports.helpers.UserAgents._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object NavigateToFootballPages extends Simulation{

  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")
  val events = csv("Events.csv")

  def navigateToFootballPages(platform: Platform) =
    scenario("Football " + platform.name)
    .forever{
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .exec(setAgentFeed(platform))
      .feed(usersfeeder.random.circular)
      .feed(events.random.circular)
      .exec(
        HomePage.get,
        PageViews.getFootballHighlightsPartial,
        PageViews.getFootballMatches,
        PageViews.getFootballMatchesPartial,
        PageViews.getFootballHighlights,
        PageViews.getFootballCompetitions,
        PageViews.getFootballCompetitionsPartial,
//        PageViews.getEvent,
//        PageViews.getEventPartial,
        PageViews.getInplay,
        PageViews.getInplayPartial,
        PageViews.getTopBetsApps,
        PageViews.getTopBets
      )
    }

}




