package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.Searches._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class Search extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      mixedSearchScenarios.inject(atOnceUsers(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      mixedSearchScenarios.inject(rampProfile(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}