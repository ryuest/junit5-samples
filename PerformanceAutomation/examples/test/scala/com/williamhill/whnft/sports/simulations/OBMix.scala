package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.OBDesktopBetPlacement._
import com.williamhill.whnft.sports.scenarios.OBLogin._
import com.williamhill.whnft.sports.scenarios.OBAccountRegistration._
import com.williamhill.whnft.sports.scenarios.OBAccountHistory._
import com.williamhill.whnft.sports.scenarios.NavigateToOBFootballPages._
import com.williamhill.whnft.sports.scenarios.NavigateToOBHorsePages._
import com.williamhill.whnft.sports.scenarios.NavigateToOBTennisPages._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class OBMix extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      navigateToOBFootballPages.inject(atOnceUsers(vU1)),
      navigateToOBHorsePages.inject(atOnceUsers(vU2)),
      navigateToOBTennisPages.inject(atOnceUsers(vU3)),
      oBLogin.inject(atOnceUsers(vU4)),
      obAccountStatement.inject(atOnceUsers(vU5)),
      accountRegistrationOB.inject(atOnceUsers(vU6))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
    }
  else {
    setUp(
      navigateToOBFootballPages.inject(rampProfile(vU1)),
      navigateToOBHorsePages.inject(rampProfile(vU2)),
      navigateToOBTennisPages.inject(rampProfile(vU3)),
      oBLogin.inject(rampProfile(vU4)),
      obAccountStatement.inject(rampProfile(vU5)),
      accountRegistrationOB.inject(rampProfile(vU6))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
