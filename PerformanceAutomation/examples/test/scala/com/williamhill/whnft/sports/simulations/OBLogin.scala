package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.OBLogin._
import io.gatling.core.Predef._
import scala.concurrent.duration._

/**
  * Created by lorenzo on 17/05/2016.
  */
class OBLogin extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(oBLogin.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(oBLogin.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}




