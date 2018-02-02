package com.williamhill.whnft.sports.requestMobengaObjects

import com.williamhill.whnft.sports.helpers.{Common, RandomGen}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AccountRegistrationMobenga {

  val baseUrl: String = Common.getConfigFromFile("environment.conf", "baseURL")
  val authUrl: String = Common.getConfigFromFile("environment.conf", "authURL")
  val mobSecurebaseUrl: String = Common.getConfigFromFile("environment.conf", "mobSecurebaseURL")
  val mobileLanguage: String = Common.getConfigFromFile("environment.conf", "mobileLanguage")
  val deviceURL: String = Common.getConfigFromFile("environment.conf", "deviceURL")
  val registerUrl : String = "/android/acc/en-gb"

  def timestamp: Long = System.currentTimeMillis / 1000

  val headers = Map(
    "Accept" -> "*/*",
    "User-Agent" -> "${UAStrings}",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Content-Type" -> "application/x-www-form-urlencoded",
    "Origin" -> mobSecurebaseUrl,
    "_Clientappversion" -> "5")

  def register =
    exec(session => makeCustomer(session))
    .exec(session => session.set("ts", timestamp))
    .exec(http("Submit mobenga reg form pt.1 - ${platform}")
    .post(mobSecurebaseUrl + registerUrl)
    .queryParam("action", "GoRegister")
    .queryParam("ts", "${ts}")
    .queryParam("i", "1")
    .queryParam("page1", "")
    .headers(headers)
    .formParam("title",	"Mr")
    .formParam("firstName",	"${makeUsername}")
    .formParam("surname",	"${makeUsername}")
    .formParam("birthDate",	"${makeDay}")
    .formParam("birthMonth",	"${makeMonth}")
    .formParam("birthYear",	"${makeYear}")
    .formParam("email",	"${makeEmail}")
    .formParam("mobile",	"${makePhoneNumber}")
    .formParam("ipAddress",	"${makeIpAddress}")
    .formParam("nextButton",	"Next")
    .formParam("currentPage",	"page1")
    .formParam("alternativeRedirectTarget",	"/williamhill_index_content.t")
    .formParam("majorEvent",	"")
    .formParam("registrationBack", "registrationBack")
    .check(status.is(200)))
    .pause(1,5)
    .exec(session => session.set("ts", timestamp))
    .exec(http("Submit mobenga reg form pt.2 - ${platform}")
    .post(mobSecurebaseUrl + registerUrl)
    .headers(headers)
    .queryParam("action", "GoRegister")
    .queryParam("ts", "${ts}")
    .queryParam("i", "1")
    .formParam("countryCode",	"UK")
    .formParam("addrStreet1",	 "${randomNum2}" + " " + "${randomChar6}" + " Road")
    .formParam("addrStreet2",	"")
    .formParam("houseNo",	"")
    .formParam("addrCity", "LONDON")
    .formParam("addrPostcode",	"${makePostcode}")
    .formParam("addrPostcode2",	"")
    .formParam("addresses",	"")
    .formParam("nextButton",	"Find address")
    .formParam("currentPage",	"page2")
    .formParam("alternativeRedirectTarget",	"")
    .formParam("majorEvent",	"")
    .formParam("registrationBack", "registrationBack")
    .check(status.is(200)))
    .pause(1,5)
//    .check(regex("""\/wap\/acc\/en-gb\?action=GoRegister&amp;ts=(.*?)"\s+method="post"""").saveAs("ts")))

    .exec(session => session.set("ts", timestamp))
    .exec(http("Submit mobenga reg form pt.3 - ${platform}")
    .post(mobSecurebaseUrl + registerUrl)
    .queryParam("action", "GoRegister")
    .queryParam("ts", "${ts}")
    .queryParam("i", "1")
    .headers(headers)
      .header(HttpHeaderNames.Referer, mobSecurebaseUrl + registerUrl + "?action=GoRegister&i=1&ts=1458143002")
    .formParam("username", "${makeUsername}")
    .formParam("password", "pa55word")
    .formParam("securityQuestion", "My favourite player")
    .formParam("answer", "Test")
    .formParam("promoCode", "")
    .formParam("accept", "checked")
    .formParam("contactable", "checked")
    .formParam("nextButton", "Next")
    .formParam("currentPage", "	page3")
    .formParam("alternativeRedirectTarget",	"")
    .formParam("majorEvent",	"")
    .formParam("registrationBack", "registrationBack")
    .formParam("currency", "GBP")
    .formParam("limitType", "day")
    .formParam("limit", "5000")
    .check(status.is(200)))
    // TODO: Debug why is not registering users!!!
    // TODO: Add new user reg validation
        .pause(1,5)


  private def makeCustomer(session: Session): Session =
    session.setAll(Map(
      "makeYear" -> RandomGen.makeYear,
      "makeMonth" -> RandomGen.makeMonth,
      "makeDay" -> RandomGen.makeDay,
      "randomChar3" -> RandomGen.getRandomCharacters(3),
      "randomNum2" -> RandomGen.getRandomNumerics(2),
      "randomChar6" -> RandomGen.getRandomCharacters(6),
      "makeEmail" -> RandomGen.makeEmail,
      "makeUsername" -> RandomGen.makeUsername,
      "makePhoneNumber" -> RandomGen.makePhoneNumber,
      "makePostcode" -> RandomGen.makePostcode,
      "makeIpAddress" -> RandomGen.makeIpAddress)
    )


}