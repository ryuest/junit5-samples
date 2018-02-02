package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.DesktopAccountRegistration._
import com.williamhill.whnft.sports.scenarios.MobileAccountRegistration._
import com.williamhill.whnft.sports.scenarios.NativeAccountRegistration._
import com.williamhill.whnft.sports.scenarios.OBAccountRegistration._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class RegistrationsMix extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      registerDesktopAccount.inject(atOnceUsers(vU)),
      registerMobileAccount.inject(atOnceUsers(vU1)),
      registerNativeAccount.inject(atOnceUsers(vU2)),
      accountRegistrationOB.inject(atOnceUsers(vU3))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      registerDesktopAccount.inject(rampProfile(vU)),
      registerMobileAccount.inject(rampProfile(vU1)),
      registerNativeAccount.inject(rampProfile(vU2)),
      accountRegistrationOB.inject(rampProfile(vU3))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}