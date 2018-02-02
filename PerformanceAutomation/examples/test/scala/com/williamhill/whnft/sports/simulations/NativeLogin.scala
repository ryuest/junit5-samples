package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.NativeLogin._
import io.gatling.core.Predef._
import scala.concurrent.duration._
/**
  * Created by lorenzo on 21/04/2016.
  */
class NativeLogin extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(nativeLogin.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(nativeLogin.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
