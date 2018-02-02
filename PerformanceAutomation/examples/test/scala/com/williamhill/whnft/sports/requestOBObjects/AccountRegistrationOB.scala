package com.williamhill.whnft.sports.requestOBObjects

import java.util.{Random}

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.RandomGen
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AccountRegistrationOB {
  val headersJoin = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
    "User-Agent" -> "${UAStrings}")

  val headersRegistration = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Content-Type" -> "application/x-www-form-urlencoded",
    "User-Agent" -> "${UAStrings}")

  val scriptPrefix = "NFT1"
  var randomGenerator = new Random()
  val baseURL: String = Common.getConfigFromFile("environment.conf", "baseURL")
  val accountRegisterUrl: String = Common.getConfigFromFile("environment.conf", "accountRegisterURL")
  val accountRegisterConfirmUrl: String = Common.getConfigFromFile("environment.conf", "accountRegisterConfirmURL")

  def clickJoin =
    exec(http("Join OB - ${platform}")
    .get(accountRegisterUrl)
    .check(status.is(200))
    .headers(headersJoin))
    .pause(2)

  def accountRegistration =
    exec(session => makeCustomer(session))
    .exec(http("Sign Up OB - ${platform}")
    .post(accountRegisterConfirmUrl)
    .formParam("modal_reg", "")
    .formParam("ioBlackBoxCopy", "")
    .formParam("source", "SB")
    .formParam("advertiser", "sys404")
    .formParam("bannerid", "")
    .formParam("profileid", "")
    .formParam("refererurl", "")
    .formParam("creferer", "")
    .formParam("is_redirect", "0")
    .formParam("title", "Mr")
    .formParam("fName", "${randomChar6}")
    .formParam("lName", "${randomChar6}")
    .formParam("dob_day", "${makeDay}")
    .formParam("dob_month", "${makeMonth}")
    .formParam("dob_year", "${makeYear}")
    .formParam("email", "${makeEmail}")
    .formParam("telephone", "${makePhoneNumber}")
    .formParam("country_code", "UK")
    .formParam("fhouse", "1")
    .formParam("addr_street_1", "${randomNum2}" + " " + "${randomChar6}" + " Road")
    .formParam("addr_street_2", "")
    .formParam("addr_city", "London")
    .formParam("addr_region", "")
    .formParam("addr_state_id", "-1")
    .formParam("postcode", "${makePostcode}")
    .formParam("reg_username", "${makeUsername}")
    .formParam("reg_password", "pa55word")
    .formParam("vfy_password", "pa55word")
    .formParam("challenge_1", "1")
    .formParam("response_1", "test")
    .formParam("ccy_code", "GBP")
    .formParam("max_deposit_day", "5000")
    .formParam("fb_promo_code", "")
    .formParam("acceptTerms", "on")
    .formParam("keepInformed", "on")
    .headers(headersRegistration)
    .check(status.is(200))
    .check(regex("Your account has been created successfully.").count.is(1)))
    .pause(10, 20)

  private def makeCustomer(session: Session): Session = {

    session.setAll(
      Map(
      "makeYear" -> RandomGen.makeYear,
      "makeMonth" -> RandomGen.makeMonth,
      "makeDay" -> RandomGen.makeDay,
      "randomChar3" -> RandomGen.getRandomCharacters(3),
      "randomNum2" -> RandomGen.getRandomNumerics(2),
      "randomChar6" -> RandomGen.getRandomAlphaCharacters(6),
      "makeEmail" -> RandomGen.makeEmail,
      "makeUsername" -> RandomGen.makeUsername,
      "makePhoneNumber" -> RandomGen.makePhoneNumber,
      "makePostcode" -> RandomGen.makePostcode)
    )
  }

}
