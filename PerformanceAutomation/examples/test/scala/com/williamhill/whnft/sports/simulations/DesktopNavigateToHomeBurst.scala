package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.DesktopHomepage._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class DesktopNavigateToHomeBurst extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      desktopHomePage.inject(
        atOnceUsers(vU)
      )
      .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      desktopHomePage.inject(rampProfile(vU))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
