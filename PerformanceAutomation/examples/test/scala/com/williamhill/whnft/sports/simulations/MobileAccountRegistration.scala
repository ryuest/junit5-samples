package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.MobileAccountRegistration._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class MobileAccountRegistration extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(registerMobileAccount.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(registerMobileAccount.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}