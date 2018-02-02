package com.williamhill.whnft.sports.requestTrafagarObjects

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.Common._

/**
  * Created by lorenzo on 21/04/2016.
  */
object Logout {
  
  val headers = Map(
    "Accept-Encoding" -> "gzip, deflate",
    "Origin" -> "http://sports.williamhill.com",
     "User-Agent" -> "${UAStrings}")
  
  val baseURL : String = Common.getConfigFromFile("environment.conf", "baseURL")
  val securebaseURL : String = Common.getConfigFromFile("environment.conf", "securebaseURL")
  val authURL : String = Common.getConfigFromFile("environment.conf", "authURL")

  def logout =
    doIf(session => userLoggedIn(session)) {
    exec(http("GET /bet?action=DoLogout - ${platform}")
    .get(securebaseURL + "/bet/en-gb")
    .queryParam("action", "DoLogout")
    .queryParam("target_page", baseURL + "/bet/en-gb")
    .headers(headers)
    .disableFollowRedirect
    .check(status.is(301)))
    .pause(1, 5)
    .exec(http("GET /cas/logout - ${platform}")
    .get(authURL + "/cas/logout")
    .queryParam("service", baseURL + "/bet/en-gb")
    .disableFollowRedirect
    .check(status.is(302)))
    .pause(1, 5)
    }
}    