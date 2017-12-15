package com.web.simulations


import com.web.scenarios.SportsLogin._
import scala.concurrent.duration._
import io.gatling.core.Predef._


/**
  * Created by jboiko on 15/12/2017.
  */
class SportsLogin  extends Simulation with SimulationSettingsTrait{
  if (singleRun) {
    setUp(SportsLogin.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(SportsLogin.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
}
