package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.NativeAccountRegistration._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class NativeAccountRegistration extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(registerNativeAccount.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(registerNativeAccount.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}