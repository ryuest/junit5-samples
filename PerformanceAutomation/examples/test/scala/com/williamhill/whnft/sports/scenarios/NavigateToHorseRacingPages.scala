package com.williamhill.whnft.sports.scenarios

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.UserAgents.{Platform, setAgentFeed}
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

object NavigateToHorseRacingPages extends Simulation{

  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")
  val events = csv("Events.csv")

  def navigateToHorsePages(platform: Platform) =
    scenario("Horse Racing " + platform.name)
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
        PageViews.getHorseRacingAntePost,
        PageViews.getHorseRacingAntePostPartial,
        PageViews.getHorseRacingSpecials,
        PageViews.getHorseRacingSpecialsPartial,
        PageViews.getHorseRacingMeetings,
        PageViews.getHorseRacingMeetingsPartial
      )
    }

}




