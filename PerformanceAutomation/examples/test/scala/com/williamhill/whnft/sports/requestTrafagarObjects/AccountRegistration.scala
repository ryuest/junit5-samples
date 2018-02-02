package com.williamhill.whnft.sports.requestTrafagarObjects

import java.util.{Random}

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.RandomGen
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AccountRegistration {

  val headersRegister = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "User-Agent" -> "${UAStrings}")

  val headersJoin = Map(
    "Accept" -> "*/*",
    "Accept-Encoding" -> "gzip, deflate",
    "Content-Type" -> "application/javascript",
    "User-Agent" -> "${UAStrings}")

  val headersRegistration = Map(
    "Accept" -> "*/*",
    "Accept-Encoding" -> "gzip, deflate",
    "Content-Type" -> "application/json;charset=UTF-8",
    "User-Agent" -> "${UAStrings}")

  val scriptPrefix = "NFTTEST"
  var randomGenerator = new Random()
  val authURL: String = Common.getConfigFromFile("environment.conf", "authURL")

  def clickJoin (productSource: String, layout: String) =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withDomain(authURL).withPath("/")))
    .exec(http("Join click - ${platform}")
      .get(authURL+"/register/")
      .headers(headersRegister)
      .check(status.is(200)))
      .pause(1,5)
    //TODO: Remove not required requests
    .exec(http("Configuration - ${platform}")
      .get(authURL+"/whsso/api/v2/init/configuration")
      .headers(headersRegister)
      .queryParam("layout", layout)
      .queryParam("locale", "en-gb")
      .queryParam("productSource", productSource)
      .check(status.is(200)))
      .pause(1,5)
    .exec(http("Translations labels - ${platform}")
      .get(authURL+"/whsso/api/v2/translations/labels")
      .headers(headersJoin)
      .queryParam("application", "registrations")
      .queryParam("locale", "null")
      .check(status.is(200))
      .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*?);").saveAs("token")))
      .pause(1,5)

  def accountRegistration(productSource: String) =
    exec(session => RandomGen.makeCustomer(productSource, session))
    .exec(http("Sign Up - ${platform}")
    .post(authURL+"/whsso/api/v2/accounts")
    .body(StringBody("${customerDataFormBody}")).asJSON
    .headers(headersRegistration)
    .header("X-XSRF-TOKEN", "${token}")
    .check(status.is(201))
    .check(regex(""""success":(.*?),""").find.is("true"))
    .check(regex(""""accountNo":"(.*?)"""").find.saveAs("accountNo")))
//    .exec(session =>
//    {
//      println("Username: "+ session("username").as[String])
////      println("Created account number: " + session("accountNo").as[String])
//      session.remove("customerDataFormBody")
//      session.remove("accountNo")
//    })
    .pause(5,10)

}