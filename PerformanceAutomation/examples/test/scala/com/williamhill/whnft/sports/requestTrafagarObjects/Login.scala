package com.williamhill.whnft.sports.requestTrafagarObjects

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.Common._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object Login {

  val baseURL: String = Common.getConfigFromFile("environment.conf", "baseURL")
  val baseTFURL: String = baseURL + "/betting/en-gb"
  val securebaseURL: String = Common.getConfigFromFile("environment.conf", "securebaseURL")
  val authURL: String = Common.getConfigFromFile("environment.conf", "authURL")

  def timestamp: Long = System.currentTimeMillis / 1000

  val headersLogin = Map(
    HttpHeaderNames.AcceptEncoding -> "gzip, deflate",
    HttpHeaderNames.Origin -> baseURL,
    HttpHeaderNames.UserAgent -> "${UAStrings}",
    HttpHeaderNames.Accept -> "*/*",
    HttpHeaderNames.ContentType -> "application/x-www-form-urlencoded; charset=UTF-8",
    HttpHeaderNames.Referer -> baseTFURL,
    HttpHeaderNames.AcceptLanguage -> "en-GB,en;q=0.8,en-US;q=0.6,it;q=0.4,es;q=0.2"
  )

  def login =
    tryMax(2) {
      pause(2, 8)
      .exec(http("Login - ${platform}")
      .post(securebaseURL + "/bet/en-gb")
      .formParam("username", "${Username}")
      .formParam("password", "${Password}")
      .formParam("action", "DoLogin")
      .formParam("login_uid", session => timestamp)
      .formParam("target_page", securebaseURL + "/bet/en-gb")
      .formParam("responseType", "json")
      .formParam("remember_me_value", "1")
      .headers(headersLogin)
      .check(status.is(200))
      .check(headerRegex("Set-Cookie", "cust_ssl_login=([^;]+)").exists.saveAs("sslLogin"))
      .check(headerRegex("Set-Cookie", "cust_auth=([^;]+)").exists.saveAs("custAuth"))
      .check(headerRegex("Set-Cookie", "cust_login=([^;]+)").exists.saveAs("custLogin"))
      .check(headerRegex("Set-Cookie", "CSRF_COOKIE=([^;]+)").exists.saveAs("csrfToken")))
      .doIf(session => !userLoggedIn(session)) {
        exec(session => {
          println("Username: " + session("Username").as[String] + " not logged in successfully.")
          session
        })
      }
    }
    .pause(1, 5)

  def getBalance =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain("${baseURL}").withPath("/")))
    .exec(http("Get user balance - ${platform}")
    .get(baseURL + "/bal/en-gb")
    .queryParam("action", "GetBalance")
    .queryParam("external", "false")
    .headers(headersLogin)
    .check(status.is(200))
    .check(jsonPath("$.amount")))
    .pause(1, 5)
//      .exec(session => {
//      println("Account amount balance: " + session("balanceAmount").as[String])
//      session.remove("balanceAmount")
//      session
//      })

  private def dynamicTry(numRetry: Int, minPause: Int, maxPause: Int, chain: ChainBuilder, pauseBetweenRetries: Boolean) = {
    tryMax(numRetry)  {
      doIfOrElse(pauseBetweenRetries) {
        pause(minPause, maxPause).exec(chain)
      } {
        exec(chain)
      }
    }
  }

}