package com.williamhill.whnft.sports.requestTrafagarObjects

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.Common._

/**
  * Created by lorenzo on 21/04/2016.
  */
object HomePage {

  val baseUri : String = Common.getConfigFromFile("environment.conf", "baseURL")
  val securebaseUri : String = Common.getConfigFromFile("environment.conf", "securebaseURL")
  val baseTFUri = baseUri + "/betting/en-gb"
  val wsportsUri : String = Common.getConfigFromFile("environment.conf", "wsportsURL")

  val headersNative = Map(
    HttpHeaderNames.Accept -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    HttpHeaderNames.UserAgent -> "${UAStrings}",
    "X-WH-BET-CODE" -> "800",
    "X-WH-WRAPPED" -> "YES")

  val headersAll = Map(
    HttpHeaderNames.Accept -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    HttpHeaderNames.UserAgent -> "${UAStrings}",
    HttpHeaderNames.AcceptEncoding -> "gzip, deflate, sdch",
    HttpHeaderNames.AcceptLanguage -> "en-GB,en;q=0.8,en-US;q=0.6,it;q=0.4,es;q=0.2",
    HttpHeaderNames.CacheControl -> "max-age=0"
  )

  val headersFragment = Map(
    HttpHeaderNames.Accept -> "text/html, */*; q=0.01",
    HttpHeaderNames.UserAgent -> "${UAStrings}",
    HttpHeaderNames.AcceptEncoding -> "gzip, deflate, sdch",
    HttpHeaderNames.AcceptLanguage -> "en-GB,en;q=0.8,en-US;q=0.6,it;q=0.4,es;q=0.2",
    HttpHeaderNames.CacheControl -> "max-age=0",
    HttpHeaderNames.Origin -> baseUri,
    HttpHeaderNames.Referer -> baseTFUri
  )

  def getNative =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain("${baseURL}").withPath("/")))
    .exec(addCookie(Cookie("wh_device", "{\"is_native\":true,\"device_os\":\"iOS\",\"os_version\":\"9.2.1\",\"is_tablet\":false};").withDomain("myaccount.williamhill.com")))
    .exec(http("Navigate to home - ${platform}")
    .get(baseUri + "/betting/en-gb")
    .headers(headersNative)
    .check(status.is(200))
    //TODO: Add check which identify is trafalgar and native
   // .check(regex("<title>Online Betting from William Hill - The Home of Betting</title>").count.is(1))
       )
    .pause(1,5)

  def get =
    tryMax(3) {
      exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
      .exec(http("Navigate to home - ${platform}")
      .get(baseUri + "/betting/en-gb")
      .headers(headersAll)
      .check(status.in(200, 304)))
      //TODO: Add cookie assertion here
//      .check(regex("<title>Online Betting from William Hill - The Home of Betting</title>").count.is(1)))
    }
//    TODO: Debug why this is failing now
//    .exec(getSslXLogin)
    .exec(http("Get slp js - ${platform}")
    .get(baseUri + "/slp/en-gb/custom/initAPI.js")
    .headers(headersFragment)
    .header(HttpHeaderNames.XRequestedWith, HttpHeaderValues.XmlHttpRequest)
    .check(status.in(302,200)))
    .exec(http("Get initApJs - ${platform}")
    .get(baseUri + "/bet/en-gb/custom/initAPI.js")
    .headers(headersFragment)
    .header(HttpHeaderNames.XRequestedWith, HttpHeaderValues.XmlHttpRequest)
    .check(status.in(200)))
    .exec(initPayment)
    .pause(1,5)

  def getPostLogin =
    doIf(session => userLoggedIn(session)) {
      exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
      .exec(http("Navigate to home - ${platform}")
      .get(baseUri + "/betting/en-gb")
      .headers(headersAll)
      .check(status.in(200, 304)))
//      .check(regex("<title>Online Betting from William Hill - The Home of Betting</title>").count.is(1)))
//      .exec(getSslXLogin)
      .exec(http("Get bal-GetBalance - ${platform}")
      .get(baseUri + "/bal/en-gb?action=GetBalance&external=false")
      .headers(headersFragment)
      .check(status.is(200)))
      .exec(http("Get slp js - ${platform}")
      .get(baseUri + "/slp/en-gb/custom/initAPI.js")
      .headers(headersFragment)
      .header(HttpHeaderNames.XRequestedWith, HttpHeaderValues.XmlHttpRequest)
      .check(status.in(302, 200)))
      .exec(http("Get initApJs - ${platform}")
      .get(baseUri + "/bet/en-gb/custom/initAPI.js")
      .headers(headersFragment)
      .header(HttpHeaderNames.XRequestedWith, HttpHeaderValues.XmlHttpRequest)
      .check(status.is(200)))
      .exec(initPayment)
      .pause(1, 5)
    }

  def getFragments = {
    exec(http("Get inplay fragment - ${platform}")
    .get(wsportsUri + "/fragments/home/inPlay/en-gb/all")
    .headers(headersFragment)
    .check(status.in(304,200)))
    .exec(http("Get highlights fragment - ${platform}")
    .get(wsportsUri + "/fragments/home/highlights/en-gb")
    .headers(headersFragment)
    .check(status.in(304,200)))
    .exec(http("Get enhanced fragment - ${platform}")
    .get(wsportsUri + "/fragments/home/enhanced/en-gb")
    .headers(headersFragment)
    .check(status.in(304,200)))
    .exec(http("Get nextOff fragment - ${platform}")
    .get(wsportsUri + "/fragments/home/nextOff/en-gb")
    .headers(headersFragment)
    .check(status.in(304,200)))
    .exec(http("Get topBets fragment - ${platform}")
    .get(wsportsUri + "/fragments/topBets/en-gb/homepage")
    .headers(headersFragment)
    .check(status.in(304, 200)))
  }

  def getSslXLogin  = {
    exec(http("Post GetSslXLoginForm - ${platform}")
    .post(securebaseUri + "/bet/en-gb?action=GetSslXLoginForm&LastLogin=1")
    .headers(headersFragment)
    .check(status.is(200)))
  }

  def initPayment = {
    exec(http("Get initPaymentApi - ${platform}")
    .get(securebaseUri + "/ext/en-gb?action=InitPaymentAPI&jsonApi=1")
    .headers(headersFragment)
    .check(status.is(200)))
  }

}