package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.DesktopBetPlacement._
import com.williamhill.whnft.sports.scenarios.MobengaBetPlacement._
import com.williamhill.whnft.sports.scenarios.MobileBetPlacement._
import com.williamhill.whnft.sports.scenarios.NativeBetPlacement._
import com.williamhill.whnft.sports.scenarios.OBDesktopBetPlacement._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class BetPlacementMix extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
//      placeBetMobenga.inject(atOnceUsers(vU)),
      placeBetDesktopOB.inject(atOnceUsers(vU1))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
    }
  else {
    setUp(
//      placeBetMobenga.inject(rampProfile(vU)),
      placeBetDesktopOB.inject(rampProfile(vU1))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
