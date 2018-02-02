package com.williamhill.whnft.sports.simulations

import scala.concurrent.duration._
import com.williamhill.whnft.sports.scenarios.DesktopBetPlacement._
import io.gatling.core.Predef._
/**
  * Created by lorenzo on 21/04/2016.
  */
class DesktopBetPlacement extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      placeBetDesktop.inject(atOnceUsers(vU))
      .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      placeBetDesktop.inject(rampProfile(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
