package com.williamhill.whnft.sports.simulations

import com.typesafe.config.ConfigFactory
import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.core.controller.inject.InjectionStep
import io.gatling.http.Predef._

import scala.concurrent.duration._

/**
  * Created by lorenzo on 06/05/2016.
  */
trait SimulationSettingsTrait {

  val environmentUnderTest = Common.prop("sut", "pte")
  val vU  = Common.prop("vu", 15)
  val vU1 = Common.prop("vu1", 1)
  val vU2 = Common.prop("vu2", 1)
  val vU3 = Common.prop("vu3", 1)
  val vU4 = Common.prop("vu4", 1)
  val vU5 = Common.prop("vu5", 1)
  val vU6 = Common.prop("vu6", 1)
  val vU7 = Common.prop("vu7", 1)
  val vU8 = Common.prop("vu8", 1)
  val vU9 = Common.prop("vu9", 1)
  val vU10 = Common.prop("vu10", 1)
  val vU11 = Common.prop("vu11", 1)
  val vU12 = Common.prop("vu12", 1)
  val vU13 = Common.prop("vu13", 1)
  val vU14 = Common.prop("vu14", 1)

  val singleRun = Common.prop("single_run", true)
  val rampDurationMinutes = Common.prop("ramp_duration", 1)
  val paceDurationMinutes = Common.prop("pace_duration", 10)
  val runDurationMinutes = Common.prop("run_duration", 3)
  val warmUpTimeSeconds = Common.prop("warm_up_time", 4)

  val numberOfBetsPerUser = Common.prop("bets_per_user", 1)
  val numberOfAccountStatements = Common.prop("acc_statements_per_user", 10)
  val numberOfDepositsPerUser = Common.prop("deposits_per_user", 10)

  val targetCompetitionPage = Common.prop("target_comp_page", "25084")

  val conf = ConfigFactory.load("environment.conf")
  val baseUri : String = Common.getConfigFromFile("environment.conf", "baseURL")

  def rampProfile(scenarioVU: Int) : Iterable[InjectionStep] =
    List(
      nothingFor(warmUpTimeSeconds seconds),
      rampUsers(scenarioVU) over (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes),
      rampUsers(scenarioVU) over (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes),
      rampUsers(scenarioVU) over (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes),
      rampUsers(scenarioVU / 2) over (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes),
      rampUsers(scenarioVU / 2) over (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes),
      rampUsers(scenarioVU / 2) over (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes)
    )

  def httpProtocol = http
      .warmUp("http://www.google.com")
      .baseURL(baseUri)
    //  .extraInfoExtractor(extraInfo => List(extraInfo.request))
    //  .proxy(Proxy("localhost", 8889)
    //    .httpsPort(8889))
      .inferHtmlResources(BlackList(
          """.*metrics.williamhill.com.*""",
          """.*nexus.ensighten.com.*""",
        """.*\.(jpg|css|JPG|jpeg|gif|ico|png|map|PNG|svg|woff|woff2|((t|o)tf)|js).*"""
      ))
      .silentResources
//      .disableClientSharing

//        white = WhiteList(
//          """.*\\sports.williamhill.com/*""", """.*\\sports.williamhill.com/*""",""".*\\w.sports.williamhill.com/*""",""".*\\o.sports.williamhill.com/*""",
//        """.*\\auth.williamhill.com""",""".*\\myaccount.williamhill.com/*""",""".*\\mobileapps.williamhill.com/*"""  ,""".*\\gw.gi.whapi.com/*""",
//          """.*\\whdn.williamhill.com/*""",""".*\\sports.staticcache.org/*""",""".*\\trans.staticcache.org/*""",""".*\\sports2.staticcache.org/*""",
//          """.*\\cachescoreboards.staticcache.org/*""",""".*\\cachestreaming.staticcache.org/*""",""".*\\scoreboards-ssl.williamhill.com/*""",
//          """.*\\streaming.williamhill.com/*""", """.*\\itvseven-test1234.racecaller.com/*""", """.*\\auth.williamhill*""","""*sports.williamhill*""",
//          """*staticcache.org/*"""
//        )


}
