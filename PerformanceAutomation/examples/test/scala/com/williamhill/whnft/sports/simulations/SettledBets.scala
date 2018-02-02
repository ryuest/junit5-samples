package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.SettledBets._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 17/05/2016.
  */
class SettledBets extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      settledBets.inject(atOnceUsers(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      settledBets.inject(rampProfile(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}




