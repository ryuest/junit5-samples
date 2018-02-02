package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.OBOxiReq._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 17/05/2016.
  */
class OBOxiReq extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(oBOxiReq.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(oBOxiReq.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}




