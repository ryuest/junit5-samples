package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.NavigateToOBFootballPages._
import com.williamhill.whnft.sports.scenarios.NavigateToOBHorsePages._
import com.williamhill.whnft.sports.scenarios.NavigateToOBTennisPages._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class OBNavigateToPages extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      navigateToOBFootballPages.inject(atOnceUsers(vU)),
      navigateToOBHorsePages.inject(atOnceUsers(vU1)),
      navigateToOBTennisPages.inject(atOnceUsers(vU2))
     .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      navigateToOBFootballPages.inject(rampProfile(vU)),
      navigateToOBHorsePages.inject(rampProfile(vU1)),
      navigateToOBTennisPages.inject(rampProfile(vU2))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
