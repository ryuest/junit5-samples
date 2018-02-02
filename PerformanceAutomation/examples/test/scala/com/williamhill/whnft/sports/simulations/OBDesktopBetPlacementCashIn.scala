package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.OBDesktopBetPlacementCashIn._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 17/05/2016.
  */
class OBDesktopBetPlacementCashIn extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(placeBetDesktopCashInOB.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(placeBetDesktopCashInOB.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}




