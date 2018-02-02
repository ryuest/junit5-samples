package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.MobengaAccountRegistration._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class MobengaAccountRegistration extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(registerMobengaAccount.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(registerMobengaAccount.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
