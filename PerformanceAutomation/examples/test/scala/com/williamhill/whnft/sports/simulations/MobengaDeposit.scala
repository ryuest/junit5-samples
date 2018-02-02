package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.MobengaDeposit._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class MobengaDeposit extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(mobengaDeposit.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(mobengaDeposit.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
