package com.williamhill.whnft.sports.scenarios

import java.util.Calendar

import com.williamhill.whnft.sports.requestOBObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import com.williamhill.whnft.sports.helpers.Common

/**
  * Created by lorenzo on 25/04/2016.
  */

object OBDesktopBetPlacement extends Simulation {

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv")
  val events = csv("Events.csv")
  val selections = csv("Selections.csv")
  val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
  val month = Calendar.getInstance().get(Calendar.MONTH) + 1

  val isPTE : Boolean =
    Common.environmentUnderTest.toUpperCase == "PTE"

  val betURL : String =
    if (isPTE)
      "slp_gib"
    else
      "slp"

  val obURL : String =
    if (isPTE)
      "bet_gib"
    else
      "bet"

  val placeBetDesktopOB =
    scenario("OB Desktop bet placement")
      .forever {
        exec(session => session.reset)
          .exec(flushHttpCache)
          .exec(flushCookieJar)
          .exec(flushSessionCookies)
          .feed(ualistfeeder.random.circular)
          .feed(usersfeeder.random.circular)
          .feed(selections.random.circular)
          .exec(HomePageOB.get(true), LoginOB.login, BetSlipOB.placebet(betURL, Common.numberOfBetsPerUser))
       //   .exec(HomePageOB.get(true), LoginOB.loginAccountNumber, LoginOB.freebetsCall, BetSlipOB.placeDoubleBet(betURL, Common.numberOfBetsPerUser))
      }
  /*
  val placeBetDesktopOB =
    scenario("OB Desktop bet placement")
    .forever {
      exec(session => session.reset)
        .exec(flushHttpCache)
        .exec(flushCookieJar)
        .exec(flushSessionCookies)
        .feed(ualistfeeder.random.circular)
        .feed(usersfeeder.random)
        .feed(events.random.circular)
        .feed(selections.random.circular)

        .doIfOrElse(isPTE) {
          exec(
            //            SelectEventPartialOB.getEventIdFromCompetitionPage(Common.targetCompetitionPage),
            //            SelectEventPartialOB.fetchSelectionsFromEventPartial,
            HomePageOB.get(true),
            LoginOB.login
          )
            .doIf(session => session.contains("custLogin")) {
              exec(
                HomePageOB.get(),
                BetSlipOB.placebet(betURL, Common.numberOfBetsPerUser)
              )
            }
        }
    }
  {
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
//        }

    }
*/
}