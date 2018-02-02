package com.williamhill.whnft.sports.simulations

import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory
import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.scenarios.MobileBetPlacement._
import com.williamhill.whnft.sports.scenarios.OBAccountHistory._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 21/04/2016.
  */
class MobileBetPlacement extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      placeBetMobile.inject(atOnceUsers(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      placeBetMobile.inject(rampProfile(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
