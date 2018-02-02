package com.williamhill.whnft.sports.requestMobengaObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoginMobenga {
  val authUrl : String = Common.getConfigFromFile("environment.conf", "authURL")
  val mobSecurebaseUrl: String = Common.getConfigFromFile("environment.conf", "mobSecurebaseURL")
  val deviceURL: String = Common.getConfigFromFile("environment.conf", "deviceURL")
  val mobileLanguage: String = Common.getConfigFromFile("environment.conf", "mobileLanguage")
  def timestamp: Long = System.currentTimeMillis / 1000

  val headersGoLogin = Map(
    "Accept-Encoding" -> "gzip, deflate, br",
    "User-Agent" -> "${UAStrings}",
    "_clientAppVersion" -> "5")

  val headersCasLogin = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
    "User-Agent" -> "${UAStrings}")

  val headersLogin = Map(
     "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",  
    "Accept-Encoding" -> "gzip, deflate, br",
    "Content-Type" -> "application/x-www-form-urlencoded",
     "User-Agent" -> "${UAStrings}",
    "Origin" -> authUrl)

  def clickLogin =
    tryMax(3) {
      exec(session => {
        session.set("ref", mobSecurebaseUrl + "/")
      })
      .exec(http("Mobenga: clickLogin - ${platform}")
      .get(authUrl + "/cas/login")
      .queryParam("service", "https://mobet.williamhill.com/auth")
      .queryParam("cust_login", "true")
      .headers(headersCasLogin)
      .header(HttpHeaderNames.Referer, "${ref}")
      .check(status.is(200))
      .check(headerRegex("Set-Cookie", "JSESSIONID=(.*); Path=/cas/").saveAs("jsessionID"))
      .check(regex("<input type=\"hidden\" name=\"lt\" value=\"(.*?)\"\\/>").saveAs("lt"))
      .check(regex("<input type=\"hidden\" name=\"execution\" value=\"(.*?)\"\\/>").saveAs("execution")))
    }
    .pause(1,5)

  def login =
    tryMax(3) {
      exec(session => {
        session.set("url", "/cas/login;jsessionid=" + session("jsessionID").as[String])
      })
      .exec(http("Mobenga: Login - ${platform}")
      .post(authUrl + "${url}")
      //    .disableFollowRedirect
      .queryParam("cust_login", "true")
      .queryParam("service", "https://mobet.williamhill.com/auth")
      .headers(headersLogin)
      .header(HttpHeaderNames.Origin, "https://auth.williamhill.com/cas/login?cust_login=true&service=https://mobet.williamhill.com/auth")
      .formParam("lt", "${lt}")
      .formParam("username", "${Username}")
      .formParam("password", "${Password}")
      .formParam("_rememberMe", "on")
      .formParam("_rememberUsername", "on")
      .formParam("_eventId", "submit")
      .formParam("execution", "${execution}")
      .check(status.is(200))
      .check(regex("Log Out</div>").exists))
    }
    .pause(1,5)

}