package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.NativeLogin.{nativeLogin, _}
import com.williamhill.whnft.sports.scenarios.OBLogin._
import com.williamhill.whnft.sports.scenarios.DesktopLogin._
import com.williamhill.whnft.sports.scenarios.MobileLogin._
import com.williamhill.whnft.sports.scenarios.MobengaLogin._
import com.williamhill.whnft.sports.scenarios.NewCasLogin._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class AllLogins extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      oBLogin.inject(atOnceUsers(vU))
 //     mobileLogin.inject(atOnceUsers(vU1))
//      desktopLogin.inject(atOnceUsers(vU2))
 //     nativeLogin.inject(atOnceUsers(vU3))
 //     mobengaLogin.inject(atOnceUsers(vU4))
 //     newCasLogin.inject(atOnceUsers(vU5))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      oBLogin.inject(rampProfile(vU)),
      mobileLogin.inject(rampProfile(vU1)),
      desktopLogin.inject(rampProfile(vU2)),
      nativeLogin.inject(rampProfile(vU3)),
      newCasLogin.inject(atOnceUsers(vU4))
//      mobengaLogin.inject(rampProfile(vU4))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
