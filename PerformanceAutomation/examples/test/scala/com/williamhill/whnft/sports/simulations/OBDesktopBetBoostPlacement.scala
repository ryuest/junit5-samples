package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.OBDesktopBetBoostPlacement._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 17/05/2016.
  */
class OBDesktopBetBoostPlacement extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(placeBetBoostDesktopOB.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(placeBetBoostDesktopOB.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}




