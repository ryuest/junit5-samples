package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.MobengaBetPlacement._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class MobengaBetPlacement extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(placeBetMobenga.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(placeBetMobenga.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
