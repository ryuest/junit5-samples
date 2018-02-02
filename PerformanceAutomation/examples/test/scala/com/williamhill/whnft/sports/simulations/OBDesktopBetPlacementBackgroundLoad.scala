package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.OBDesktopBetPlacementBackgroundLoad._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 17/05/2016.
  */
class OBDesktopBetPlacementBackgroundLoad extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(placeBetDesktopOBBackgroundLoad.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(placeBetDesktopOBBackgroundLoad.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}




