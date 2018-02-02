package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.OBDesktopBetPlacement._
import io.gatling.core.Predef._
import scala.concurrent.duration._

/**
  * Created by lorenzo on 17/05/2016.
  */
class OBDesktopBetPlacement extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(placeBetDesktopOB.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(placeBetDesktopOB.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}




