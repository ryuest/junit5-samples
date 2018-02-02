package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.OBAccountHistory._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 17/05/2016.
  */
class OBAccountStatement extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      obAccountStatement.inject(atOnceUsers(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      obAccountStatement.inject(rampProfile(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}




