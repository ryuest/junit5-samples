package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.helpers.UserAgents.DESKTOP
import com.williamhill.whnft.sports.scenarios.GetStreamingLogs._
import com.williamhill.whnft.sports.scenarios.NavigateToFootballPages._
import com.williamhill.whnft.sports.scenarios.NavigateToFootballTransPages._
import com.williamhill.whnft.sports.scenarios.NavigateToHorseRacingPages._
import com.williamhill.whnft.sports.scenarios.NavigateToMobengaPages._
import com.williamhill.whnft.sports.scenarios.NavigateToOBFootballPages._
import com.williamhill.whnft.sports.scenarios.NavigateToOBHorsePages._
import com.williamhill.whnft.sports.scenarios.NavigateToOBTennisPages._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class NavigateToTransPages extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      navigateToFootballTransPages(DESKTOP).inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      navigateToFootballTransPages(DESKTOP).inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
