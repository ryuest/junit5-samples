package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.DesktopAccountRegistration._
import com.williamhill.whnft.sports.scenarios.MobileAccountRegistration._
import com.williamhill.whnft.sports.scenarios.NativeAccountRegistration._
import com.williamhill.whnft.sports.scenarios.OBAccountRegistration._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class OBRegistrations extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      accountRegistrationOB.inject(atOnceUsers(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      accountRegistrationOB.inject(rampProfile(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}