package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.MobengaAccountHistory._
import com.williamhill.whnft.sports.scenarios.NativeAccountRegistration._
import com.williamhill.whnft.sports.scenarios.MobileAccountRegistration._
import com.williamhill.whnft.sports.scenarios.DesktopAccountRegistration._
import com.williamhill.whnft.sports.scenarios.DesktopAccountStatements._
import com.williamhill.whnft.sports.scenarios.OBAccountHistory._
import com.williamhill.whnft.sports.scenarios.OBAccountRegistration._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 17/05/2016.
  */
class AccountMix extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      desktopAccountStatements.inject(atOnceUsers(vU)),
      registerMobileAccount.inject(atOnceUsers(vU1)),
      registerNativeAccount.inject(atOnceUsers(vU2)),
      registerDesktopAccount.inject(atOnceUsers(vU3)),
      accountRegistrationOB.inject(atOnceUsers(vU4)),
      obAccountStatement.inject(atOnceUsers(vU5))
//      mobengaAccountHistory.inject(rampProfile(vU6))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      desktopAccountStatements.inject(rampProfile(vU)),
      registerMobileAccount.inject(rampProfile(vU1)),
      registerNativeAccount.inject(rampProfile(vU2)),
      registerDesktopAccount.inject(rampProfile(vU3)),
      accountRegistrationOB.inject(rampProfile(vU4)),
      obAccountStatement.inject(rampProfile(vU5))
//      mobengaAccountHistory.inject(rampProfile(vU6))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}




