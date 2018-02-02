package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.helpers.UserAgents._
import com.williamhill.whnft.sports.scenarios.NavigateToFootballPages._
import com.williamhill.whnft.sports.scenarios.NavigateToVirtualPages._
import com.williamhill.whnft.sports.scenarios.NavigateToHorseRacingPages._
import com.williamhill.whnft.sports.scenarios.NavigateToMobengaPages._
import com.williamhill.whnft.sports.scenarios.NavigateToOBFootballPages._
import com.williamhill.whnft.sports.scenarios.NavigateToOBHorsePages._
import com.williamhill.whnft.sports.scenarios.NavigateToOBTennisPages._
import com.williamhill.whnft.sports.scenarios.NavigateToFootballTransPages._
import com.williamhill.whnft.sports.scenarios.GetStreamingLogs._
import com.williamhill.whnft.sports.scenarios.NavigateToFragmentPages._
import com.williamhill.whnft.sports.requestTrafagarObjects._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class NavigateToPages extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      navigateToFootballPages(DESKTOP).inject(atOnceUsers(vU))
//      navigateToHorsePages.inject(atOnceUsers(vU1)),
//      navigateToMobengaPages.inject(atOnceUsers(vU2)),
//      navigateToOBFootballPages.inject(atOnceUsers(vU3)),
//      navigateToOBHorsePages.inject(atOnceUsers(vU4)),
//      navigateToOBTennisPages.inject(atOnceUsers(vU5)),
//      getStreamingLogs.inject(atOnceUsers(vU6)),
//      navigateToFootballTransPages.inject(atOnceUsers(vU7)),
//      navigateToVirtualPages.inject(atOnceUsers(vU8)),
//      navigateToFragmentPages.inject(atOnceUsers(vU9))
     .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      navigateToFootballPages(NATIVE).inject(rampProfile(vU)),
      navigateToFootballPages(MOBILE).inject(rampProfile(vU1)),
      navigateToHorsePages(NATIVE).inject(rampProfile(vU2)),
      navigateToHorsePages(MOBILE).inject(rampProfile(vU3)),
      navigateToOBFootballPages.inject(rampProfile(vU4)),
      navigateToOBHorsePages.inject(rampProfile(vU5)),
      navigateToOBTennisPages.inject(rampProfile(vU6)),
      getStreamingLogs(NATIVE).inject(rampProfile(vU7)),
      getStreamingLogs(MOBILE).inject(rampProfile(vU8)),
      navigateToFootballTransPages(NATIVE).inject(rampProfile(vU9)),
      navigateToFootballTransPages(MOBILE).inject(rampProfile(vU10)),
      navigateToVirtualPages(NATIVE).inject(rampProfile(vU11)),
      navigateToVirtualPages(MOBILE).inject(rampProfile(vU12)),
      navigateToFragmentPages(NATIVE).inject(rampProfile(vU13)),
      navigateToFragmentPages(MOBILE).inject(rampProfile(vU14))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
