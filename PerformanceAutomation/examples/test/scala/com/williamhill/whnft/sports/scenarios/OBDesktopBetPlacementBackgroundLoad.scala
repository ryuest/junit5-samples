package com.williamhill.whnft.sports.scenarios

import java.util.Calendar

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestOBObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  */

object OBDesktopBetPlacementBackgroundLoad extends Simulation {

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts1" + Common.environmentUnderTest.toUpperCase + ".csv")
  val events = csv("Events.csv")
  val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
  val month = Calendar.getInstance().get(Calendar.MONTH) + 1

  val isPTE : Boolean =
    Common.environmentUnderTest.toUpperCase == "PTE"

  val betURL : String =
    if (isPTE)
      "slp_gib"
    else
      "slp"

  val placeBetDesktopOBBackgroundLoad =
    scenario("OB Desktop bet placement")
        .forever {
          exec(session => session.reset)
              .exec(flushHttpCache)
              .exec(flushCookieJar)
              .exec(flushSessionCookies)
              .feed(ualistfeeder.random.circular)
              .feed(usersfeeder.random)
              .feed(events.random.circular)
              .doIfOrElse(isPTE) {
                exec(
                  SelectEventPartialOB.getEventIdFromCompetitionPage(Common.targetCompetitionPage),
                  SelectEventPartialOB.fetchSelectionsFromEventPartial,
                  HomePageOB.get(true),
                  LoginOB.login
                )
                    .doIf(session => session.contains("custLogin")) {
                      exec(
                        HomePageOB.get(),
                        BetSlipOB.placebet(betURL, Common.numberOfBetsPerUser)
                      )
                    }
              } {
                exec(
                  SelectEventPartialOB.fetchSelectionsFromEventPartial,
                  HomePageOB.get(true),
                  LoginOB.login
                )
                    .doIf(session => !session.contains("custLogin")) {
                      exec(
                        HomePageOB.get(),
                        BetSlipOB.placebet(betURL, Common.numberOfBetsPerUser)
                        //AccountStatementsOB.getAccHistory(day, month, Common.numberOfAccountStatements, true)
                      )
                    }
              }
        }

}