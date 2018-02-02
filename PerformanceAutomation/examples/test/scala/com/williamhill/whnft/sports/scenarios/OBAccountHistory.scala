package com.williamhill.whnft.sports.scenarios

import java.util.Calendar

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.requestOBObjects._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import java.text.SimpleDateFormat

/**
  * Created by lorenzo on 25/04/2016.
  */

object OBAccountHistory extends Simulation {

  val ualistfeeder = csv("UADesktop.csv", escapeChar = '\\')
  val usersfeeder = csv("UserAccounts" + Common.environmentUnderTest.toUpperCase + "OB.csv")

  val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
  val month = Calendar.getInstance().get(Calendar.MONTH) + 1

  val obAccountStatement =
    scenario("OB Account Statement")
    .forever {
      exec(session => session.reset)
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
      .feed(ualistfeeder.random.circular)
      .feed(usersfeeder.random.circular)
      .exec(
        HomePageOB.get(true),
        LoginOB.login
      )
      .doIf(session => session.contains("custLogin")) {
        exec(
          HomePageOB.get(),
          AccountStatementsOB.clickMyAccount,
          AccountStatementsOB.getAccHistory(day, month, Common.numberOfAccountStatements)
        )
      }
    }
}
