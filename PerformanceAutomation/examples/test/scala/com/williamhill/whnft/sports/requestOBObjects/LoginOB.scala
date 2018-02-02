package com.williamhill.whnft.sports.requestOBObjects

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.Common._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object LoginOB {

  val oBbaseurl: String = Common.getConfigFromFile("environment.conf", "OBbaseURL")
  val securebaseURL: String = Common.getConfigFromFile("environment.conf", "securebaseURL")

  val headersLogin = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Content-Type" -> "application/x-www-form-urlencoded",
    "User-Agent" -> "${UAStrings}")

  val headerSSL = Map("login-authentication-ssl" -> "${sslLogin}",
    "Host" -> "sports.williamhill.com")


  def login =
    tryMax(2) {
      pause(2, 8)
        .exec(flushSessionCookies)
        .exec(http("Login OB - ${platform}")
          .post(oBbaseurl)
          .formParam("username", "${Username}")
          .formParam("password", "${Password}")
          .formParam("tmp_username", "Username")
          .formParam("action", "DoLogin")
          .formParam("login_uid", "${loginUID}")
          .formParam("target_page", oBbaseurl)
          //  .formParam("ioBlackBoxCopy", "")
          .formParam("remember_me_value", "-1")
          .disableFollowRedirect
          .headers(headersLogin)
          .check(status.is(301))
          .check(headerRegex("Set-Cookie", "cust_ssl_login=([^;]+)").exists.saveAs("sslLogin"))
          .check(headerRegex("Set-Cookie", "cust_auth=([^;]+)").exists.saveAs("custAuth"))
          .check(headerRegex("Set-Cookie", "cust_login=([^;]+)").exists.saveAs("custLogin"))
          .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token")))
        .doIf(session => !userLoggedIn(session)) {
          exec(session => {
            println("Username: " + session("Username").as[String] + " not logged in successfully.")
            session
          })
        }
    }
      .pause(1, 5)

  def loginAccountNumber =
    tryMax(2) {
      pause(1, 4)
        .exec(http("Login OB - ${platform}")
          .post(oBbaseurl)
          .formParam("username", "${Username}")
          .formParam("password", "${Password}")
          .formParam("tmp_username", "Username")
          .formParam("action", "DoLogin")
          .formParam("login_uid", "${loginUID}")
          .formParam("target_page", oBbaseurl)
          //  .formParam("ioBlackBoxCopy", "")
          .formParam("remember_me_value", "-1")
          .disableFollowRedirect
          .headers(headersLogin)
          .check(status.is(301))
          .check(headerRegex("Set-Cookie", "cust_ssl_login=([^;]+)").exists.saveAs("sslLogin"))
          .check(headerRegex("Set-Cookie", "cust_auth=([^;]+)").exists.saveAs("custAuth"))
          .check(headerRegex("Set-Cookie", "cust_login=([^;]+)").exists.saveAs("custLogin"))
          .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token")))
        .exec(http("Get Home")
          .get("http://sports.williamhill.com/bet_gib/en-gb")
          .check(regex("AccountID=\"(.*)\"").saveAs("Number"))
        )
        .doIf(session => !userLoggedIn(session)) {
          exec(session => {
            println("Username: " + session("Username").as[String] + " not logged in successfully.")
            session
          })
        }
    }
      .pause(1, 4)

  def freebetsCall =
    exec(flushSessionCookies)
      .exec(addCookie(Cookie("cust_login", "${custLogin}")))
      .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
      .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
      .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
      .exec(http("FreeBets bet boost API call")
        .post(securebaseURL + "/freebets/qualification/freebets/tokens")
        .formParam("triggerType", "ACTIVEUSER")
        .formParam("channel", "W")
        .formParam("accountNo", "${Number}")
        .headers(headerSSL)
        .check(status.is(202))
      //    .check(status.is(401))
        )
      .pause(1, 4)

  def freebetsCallAsyncMany =
    exec(flushSessionCookies)
      .exec(addCookie(Cookie("cust_login", "${custLogin}")))
      .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
      .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
      .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
      . exec(http("simple req").get("/")
        .resources(
          http("FreeBets bet boost API call1")
            .post(securebaseURL + "/freebets/qualification/freebets/tokens")
            .queryParam("triggerType", "ACTIVEUSER")
            .queryParam("channel", "W")
            .queryParam("accountNo", "${Number}")
            .headers(headerSSL)
            .check(status.is(202)),
          http("FreeBets bet boost API call2")
            .post(securebaseURL + "/freebets/qualification/freebets/tokens")
            .queryParam("triggerType", "ACTIVEUSER")
            .queryParam("channel", "W")
            .queryParam("accountNo", "${Number}")
            .headers(headerSSL)
            .check(status.is(202)),
          http("FreeBets bet boost API call3")
            .post(securebaseURL + "/freebets/qualification/freebets/tokens")
            .queryParam("triggerType", "ACTIVEUSER")
            .queryParam("channel", "W")
            .queryParam("accountNo", "${Number}")
            .headers(headerSSL)
            .check(status.is(202)),
          http("FreeBets bet boost API call4")
            .post(securebaseURL + "/freebets/qualification/freebets/tokens")
            .queryParam("triggerType", "ACTIVEUSER")
            .queryParam("channel", "W")
            .queryParam("accountNo", "${Number}")
            .headers(headerSSL)
            .check(status.is(202))
        )
      )
      .pause(4, 5)

}
