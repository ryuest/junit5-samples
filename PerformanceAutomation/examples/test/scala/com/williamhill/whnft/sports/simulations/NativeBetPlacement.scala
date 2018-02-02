package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.NativeBetPlacement._

import scala.concurrent.duration._
import io.gatling.core.Predef._

/**
  * Created by lorenzo on 21/04/2016.
  */
class NativeBetPlacement extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      placeBetNative.inject(atOnceUsers(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      placeBetNative.inject(rampProfile(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
 
}
