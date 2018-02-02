package com.williamhill.whnft.sports.requestOBObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 18/05/2016.
  */
object AccountStatementsOB {

  val headers = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "User-Agent" -> "${UAStrings}",
    "Accept-Encoding" -> "gzip, deflate, sdch")

  val oBAccURL : String =Common.getConfigFromFile("environment.conf", "OBAccURL")

  def clickMyAccount = {
    exec(http("Click my account OB - ${platform}")
      .get(oBAccURL)
      .headers(headers)
      .queryParam("action", "GoAcct")
      .check(status.is(200)))
  }

  def getAccHistory (day: Int = 1, month: Int = 1, numberOfAccStatements: Int = 1, checkBetOpen: Boolean = false) = {
    repeat(numberOfAccStatements) {
      doIfOrElse(checkBetOpen) {
        tryMax(3) {
          exec(http("Get account history OB - ${platform}")
          .get(oBAccURL)
          .headers(headers)
          .queryParam("action", "DoHistory")
          .queryParam("start_day", day)
          .queryParam("end_day", day)
          .queryParam("start_month", month)
          .queryParam("end_month", month)
          .queryParam("start_year", "2016")
          .queryParam("end_year", "2016")
          .queryParam("txn_type", "BET_OPEN")
          .queryParam("stateTime", "SELECTION")
          .queryParam("_start_day", day)
          .queryParam("_start_month", month)
          .queryParam("_start_year", "2016")
          .queryParam("resultsOnly", "1")
          .check(status.is(200))
          .check(regex("There were no transactions found on the chosen date").findAll.notExists))
          .pause(1,5)
        }
      } {
        exec(http("Get account history OB - ${platform}")
        .get(oBAccURL)
        .headers(headers)
        .queryParam("action", "DoHistory")
        .queryParam("start_day", day)
        .queryParam("end_day", day)
        .queryParam("start_month", month)
        .queryParam("end_month", month)
        .queryParam("start_year", "2016")
        .queryParam("end_year", "2016")
        .queryParam("txn_type", "BET_OPEN")
        .queryParam("stateTime", "SELECTION")
        .queryParam("_start_day", day)
        .queryParam("_start_month", month)
        .queryParam("_start_year", "2016")
        .queryParam("resultsOnly", "1")
        .check(status.is(200)))
        .pause(1,5)
      }
    }

  }

}
