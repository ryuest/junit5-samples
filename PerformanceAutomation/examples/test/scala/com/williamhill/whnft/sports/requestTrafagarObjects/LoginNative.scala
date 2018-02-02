package com.williamhill.whnft.sports.requestTrafagarObjects

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.Common._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.asynchttpclient.uri.Uri
import io.gatling.http.cookie._

/**
  * Created by lorenzo on 21/04/2016.
  */
object LoginNative {

  val casLogin : String =Common.getConfigFromFile("environment.conf", "casLoginURL")
  val authURL : String =Common.getConfigFromFile("environment.conf", "authURL")
  val v1SessionTicketsURL : String =Common.getConfigFromFile("environment.conf", "v1SessionTicketsURL")
  val v1AccountsMeURL : String =Common.getConfigFromFile("environment.conf", "v1AccountsMeURL")
  val apiKey : String =Common.getConfigFromFile("environment.conf", "nativeapiKey")
  val secret : String =Common.getConfigFromFile("environment.conf", "nativesecret")
  val nativeAuthURL : String =Common.getConfigFromFile("environment.conf", "nativeAuthURL")

  val headersGwGi = Map(
    "who-apiKey" -> apiKey,
    "accept" -> "application/json",
    "who-secret" -> secret,
    "User-Agent" -> "${UAStrings}")

  val headersLogin = Map(
    "Accept-Encoding" -> "gzip, deflate",
    "Origin" -> "http://sports.williamhill.com",
     "User-Agent" -> "${UAStrings}")

  def login =
    exec(http("GET V1 Session InternalTicket - ${platform}")
    .post(v1SessionTicketsURL + "internalTickets")
    .headers(headersGwGi)
    .header(HttpHeaderNames.ContentType, HttpHeaderValues.ApplicationFormUrlEncoded)
    .formParam("username", "${Username}")
    .formParam("password", "${Password}")
    .check(status.is(201))
    .check(jsonPath("$.whoSessions.ticket").saveAs("tgtTicket"))
    .check(jsonPath("$.whoSessions.location").saveAs("location")))
    .pause(1, 5)
    .exec(http("GET V1 Session Tickets - ${platform}")
    .post("${location}")
    .formParam("target", nativeAuthURL)
    .headers(headersGwGi)
    .header(HttpHeaderNames.ContentType, HttpHeaderValues.ApplicationFormUrlEncoded)
    .check(status.is(201))
    .check(jsonPath("$.whoSessions.ticket").saveAs("stTicket"))
    .check(jsonPath("$.whoSessions.location").saveAs("nativelocation")))
    .exec(http("Cas share")
    .get(authURL + "/cas/share?target=" + nativeAuthURL + "&ticket=${stTicket}&cust_login=true")
    .header(HttpHeaderNames.UserAgent, "${UAStrings}")
    .header(HttpHeaderNames.Accept, HttpHeaderValues.ApplicationXhtml)
    .check(status.is(200)))
     .exec(session => {
       session("gatling.http.cookies").validate[CookieJar].map { cookieJar =>
         val custLoginCookie = cookieJar.get(Uri.create("https://sports.williamhill.com")).find(_.getName == "cust_login")
         val sslLoginCookie = cookieJar.get(Uri.create("https://sports.williamhill.com")).find(_.getName == "cust_ssl_login")
         val custAuthCookie = cookieJar.get(Uri.create("https://sports.williamhill.com")).find(_.getName == "cust_auth")

         session.setAll(Map(
           "custLogin" -> custLoginCookie.getOrElse(null).getValue,
           "sslLogin" -> sslLoginCookie.getOrElse(null).getValue,
           "custAuth" -> custAuthCookie.getOrElse(null).getValue)
         )
       }
     })
    .exec(getBalance)
    .doIf(session => !userLoggedIn(session)) {
      exec(session => {
        println("Username: " + session("Username").as[String] + " not logged in successfully.")
        session
      })
    }
  .pause(1, 5)

  def getBalance =
    exec(http("GET Account Me - ${platform}")
    .get(v1AccountsMeURL)
    .headers(headersGwGi)
    .header("who-ticket", "${tgtTicket}")
    .check(status.is(200)))
    .pause(1, 5)
    .exec(http("GET Account Me Balance - ${platform}")
    .get(v1AccountsMeURL + "/balance")
    .headers(headersGwGi)
    .header("who-ticket", "${tgtTicket}")
    .check(status.is(200)))
    .pause(1, 5)

}