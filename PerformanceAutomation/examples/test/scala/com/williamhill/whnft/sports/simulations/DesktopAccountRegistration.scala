package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.DesktopAccountRegistration._

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DesktopAccountRegistration extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(registerDesktopAccount.inject(atOnceUsers(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(registerDesktopAccount.inject(rampProfile(vU)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}