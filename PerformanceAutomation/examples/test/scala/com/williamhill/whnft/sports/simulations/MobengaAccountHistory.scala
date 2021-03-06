package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.MobengaAccountHistory._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class MobengaAccountHistory extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(mobengaAccountHistory.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(mobengaAccountHistory.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
