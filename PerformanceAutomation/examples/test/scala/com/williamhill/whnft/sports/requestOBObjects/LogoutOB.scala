package com.williamhill.whnft.sports.requestOBObjects

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.Common._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 18/05/2016.
  */
object LogoutOB {
  val securebaseURL : String =Common.getConfigFromFile("environment.conf", "securebaseURL")
  val baseUri : String = Common.getConfigFromFile("environment.conf", "baseURL")
  val authURL : String = Common.getConfigFromFile("environment.conf", "authURL")

  val headers = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "User-Agent" -> "${UAStrings}",
    "Accept-Encoding" -> "gzip, deflate, sdch")

  def logout =
    doIf(session => userLoggedIn(session)) {
      exec(http("Logout OB - ${platform}")
      .get(securebaseURL + "/bet/en-gb")
      .headers(headers)
      .disableFollowRedirect
      .queryParam("action", "DoLogout")
      .queryParam("target_page", securebaseURL + "/acc/en-gb")
      .check(status.is(301)))
      .pause(1, 5)
      .exec(http("CAS Logout - ${platform}")
      .post(authURL + "/cas/logout?service=https://sports.williamhill.com/bet/en-gb")
      .headers(headers)
      .disableFollowRedirect
      .check(status.is(302)))
      //    .check(headerRegex("Set-Cookie", "cust_ssl_login=([^;]+)").exists.saveAs("sslLogin"))
      //    .check(headerRegex("Set-Cookie", "cust_login=([^;]+)").exists.saveAs("custLogin")))
      .pause(1, 5)
      //TODO: Fix the logout  not removing expired cookies when a bet has been placed before the logout
      //    .exec(session => {
      //      println("login values: " + session("sslLogin").as[String] + " " + session("sslLogin").as[String].isEmpty )
      //      if(session("sslLogin").as[String] == "") {
      //        println("reached here!" )
      //        addCookie(Cookie("cust_login", ""))
      //        addCookie(Cookie("cust_ssl_login", ""))
      //      }
      //      session
      //    })
      .exec(http("Navigate to home OB - ${platform}")
      .get(baseUri + "/bet/en-gb")
      .headers(headers)
      .check(status.is(200))
      //TODO: Fix this regex!!!
      //    .check(regex("<span>Join Now</span></a>").findAll.exists))
      .check(regex("Join Now").findAll.exists))
      .pause(1, 5)
    }

}
