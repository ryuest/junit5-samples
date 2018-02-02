package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.DesktopDeposit._
import com.williamhill.whnft.sports.scenarios.MobileDeposit._
import com.williamhill.whnft.sports.scenarios.NativeDeposit._
import com.williamhill.whnft.sports.scenarios.OBDesktopDeposit._
import com.williamhill.whnft.sports.scenarios.MobengaDeposit._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class Deposit extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      desktopDeposit.inject(atOnceUsers(vU)),
      mobileDeposit.inject(atOnceUsers(vU1)),
      nativeDeposit.inject(atOnceUsers(vU2)),
      desktopDepositOB.inject(atOnceUsers(vU3))
//      mobengaDeposit.inject(atOnceUsers(vU4))
      .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      desktopDeposit.inject(rampProfile(vU)),
      mobileDeposit.inject(rampProfile(vU1)),
      nativeDeposit.inject(rampProfile(vU2)),
      desktopDepositOB.inject(rampProfile(vU3))
//      mobengaDeposit.inject(rampProfile(vU4))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
