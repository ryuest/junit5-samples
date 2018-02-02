package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.GetBurstOfFragments._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 17/05/2016.
  */
class GetBurstOfFragments extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      getFragments.inject(atOnceUsers(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      getFragments.inject(
        nothingFor(warmUpTimeSeconds seconds),
        rampUsers(vU) over (rampDurationMinutes minutes)
      )
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}




