package com.williamhill.whnft.sports.requestMobengaObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AccountHistoryMobenga {
  
  val headers = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
  "User-Agent" -> "${UAStrings}",
  "Accept-Encoding" -> "gzip, deflate, sdch, br",
  "_clientAppVersion" -> "5")

  val mobSecurebaseUrl: String = Common.getConfigFromFile("environment.conf", "mobSecurebaseURL")
  val deviceURL: String = Common.getConfigFromFile("environment.conf", "deviceURL")

  def betHistory (numberOfAccStatements: Int = 1) =
    repeat(numberOfAccStatements) {
      exec(session => session.set("ref", mobSecurebaseUrl + "/"))
      .exec(http("Mobenga: Go to bet history - ${platform}")
      .get(mobSecurebaseUrl + "/" + deviceURL + "/acc/en-gb/bet_history_tabs")
      .queryParam("i", "1")
      .headers(headers)
      .header(HttpHeaderNames.Referer, "${ref}")
      .check(status.in(200, 996)))
      .pause(1, 5)
    }

}