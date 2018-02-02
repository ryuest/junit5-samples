package com.williamhill.whnft.sports.requestOBObjects

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.williamhill.whnft.sports.helpers.Common

/**
  * Created by lorenzo on 21/04/2016.
  */
object HomePageOB {

  val headersAll = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "User-Agent" -> "${UAStrings}",
    "Accept-Encoding" -> "gzip, deflate, sdch")

  val baseUri : String = Common.getConfigFromFile("environment.conf", "baseURL")

  val isPTE : Boolean =
    Common.environmentUnderTest.toUpperCase == "PTE"

  val basePath : String =
    if (isPTE)
      "/bet_gib"
    else
      "/bet"

  def get (grepLogUID: Boolean = false) =
    doIfOrElse(grepLogUID) {
      asLongAs(session => !session.contains("loginUID")) {
        exec(http("Navigate to home OB - ${platform}")
        .get(baseUri + basePath + "/en-gb")
        .headers(headersAll)
//          .check(status.is(200))
        .check(regex("""name="login_uid"\s+value="(.*)"\s+\/>""").saveAs("loginUID"))
        .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token")))
        .pause(1, 5)
      }
    } {
      exec(http("Navigate to home OB - ${platform}")
      .get(baseUri + basePath + "/en-gb")
      .headers(headersAll)
      .check(status.is(200))
      .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token")))
    }
    .pause(1, 5)


}