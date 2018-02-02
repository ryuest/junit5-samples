package com.williamhill.whnft.sports.requestTrafagarObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.asynchttpclient.uri.Uri
import io.gatling.http.cookie._

/**
  * Created by lorenzo on 21/04/2016.
  */
object LoginNativeNewCas {

  val casLoginURL : String =Common.getConfigFromFile("environment.conf", "casLoginURL")
  val authURL : String =Common.getConfigFromFile("environment.conf", "authURL")
  val OBAccURL : String =Common.getConfigFromFile("environment.conf", "OBAccURL")
  val baseURL : String =Common.getConfigFromFile("environment.conf", "baseURL")

  val headersLogin = Map(
    "Accept-Encoding" -> "gzip, deflate",
    "Accept" -> "application/json",
    "Accept-Language" -> "ql",
    "Origin" -> baseURL,
    "User-Agent" -> "${UAStrings}")

  def login =
    exec(addCookie(Cookie("is_cas", "Y").withMaxAge(86400).withDomain(".williamhill-pp1.com").withPath("/")))
    .exec(http("GET to CAS")
      .get(casLoginURL)
      .queryParam("service", OBAccURL)
      .queryParam("action", "HandleServiceTicket")
      .headers(headersLogin)
      .header(HttpHeaderNames.ContentType, HttpHeaderValues.ApplicationFormUrlEncoded)
      .check(status.is(200))
      .check(jsonPath("$.form_defaults.lt").saveAs("lt")))
      .pause(1, 5)
      .exec(http("POST to CAS")
        .post(casLoginURL)
        .queryParam("service", OBAccURL)
        .queryParam("action", "HandleServiceTicket")
        .formParam("username", "${Username}")
        .formParam("password", "${Password}")
        .formParam("_eventId", "submit")
        .formParam("_rememberUsername", "off")
        .formParam("execution", "e1s1")
        .formParam("lt", "${lt}")
        .headers(headersLogin)
        .header(HttpHeaderNames.ContentType, HttpHeaderValues.ApplicationFormUrlEncoded)
        .check(status.is(200))
        .check(jsonPath("$.serviceTicket").saveAs("serviceTicket")))
       .exec(http("POST to Sportsbook")
          .get(OBAccURL)
          .header(HttpHeaderNames.UserAgent, "${UAStrings}")
          .header(HttpHeaderNames.Accept, HttpHeaderValues.ApplicationXhtml)
          .queryParam("action", "HandleServiceTicket")
          .queryParam("ticket", "${serviceTicket}")
          .queryParam("target_page", baseURL+"/acc/en-gb")
          .queryParam("cas_service", OBAccURL)
          .check(status.is(301))
        )
        .exec(session => {
          session("gatling.http.cookies").validate[CookieJar].map { cookieJar =>
            val casLoginCookie = cookieJar.get(Uri.create("https://sports.williamhill.com")).find(_.getName == "cas_login")
            val sslLoginCookie = cookieJar.get(Uri.create("https://sports.williamhill.com")).find(_.getName == "cas_ssl_login")

            session.setAll(Map(
              "cas_ssl_login" -> casLoginCookie.getOrElse(null).getValue,
              "cas_login" -> sslLoginCookie.getOrElse(null).getValue)
            )
          }
        })
        .pause(1, 5)

}