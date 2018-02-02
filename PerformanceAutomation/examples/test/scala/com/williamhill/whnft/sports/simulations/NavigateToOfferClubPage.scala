package com.williamhill.whnft.sports.simulations

import com.williamhill.whnft.sports.scenarios.NavigateToOfferClubPage._
import com.williamhill.whnft.sports.scenarios.NavigateToOfferClubPageNative._
import io.gatling.core.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 21/04/2016.
  */
class NavigateToOfferClubPage extends Simulation with SimulationSettingsTrait {

  if (singleRun) {
    setUp(
      navigateToOfferClubPage.inject(atOnceUsers(vU)),
      navigateToOfferClubPageNative.inject(atOnceUsers(vU1))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }
  else {
    setUp(
      navigateToOfferClubPage.inject(rampProfile(vU)),
      navigateToOfferClubPageNative.inject(rampProfile(vU1))
    .protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)
  }

}
