package com.williamhill.whnft.sports.requestMobengaObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object BetSlipMobenga {
  val headersLoadBetslip = Map("_clientAppVersion" -> "5")

	val mobSecurebaseUrl: String = Common.getConfigFromFile("environment.conf", "mobSecurebaseURL")
  val baseUri: String = Common.getConfigFromFile("environment.conf", "baseURL")
  val mobileLanguage: String = Common.getConfigFromFile("environment.conf", "mobileLanguage")
  val deviceURL: String = Common.getConfigFromFile("environment.conf", "deviceURL")
  val betAmount: String = Common.getConfigFromFile("environment.conf", "betAmount")
  def timestamp: Long = System.currentTimeMillis / 1000

  val headersPlaceBet = Map(
    "Accept-Encoding" -> "gzip, deflate",
    "Origin" -> baseUri,
    "_clientAppVersion" -> "5")

  def placeBet(numberOfBets: Int) =
    repeat(numberOfBets) {
      // TODO: Make selection random for the same user not across multiple users
      exec(http("Mobenga: Navigate to betslip - ${platform}")
      .get(mobSecurebaseUrl + "/" + deviceURL + "/" + mobileLanguage + "/slip")
      .headers(headersLoadBetslip)
      .queryParam("i", "1")
      .check(status.is(200))
      .check(regex("name=\"slip-betslip-id\" value=\"(.*?)\"").saveAs("betSlipID")))
      .pause(1, 5)
      .exec(http("Mobenga: Add selection to betslip - ${platform}")
      .get(mobSecurebaseUrl + "/slip")
      .queryParam("action", "add")
      .queryParam("numerator", "${selectionNumerator}")
      .queryParam("denominator", "${selectionDenominator}")
      .queryParam("name", "${selectionName}")
      .queryParam("eventName", "${eventName}")
      .queryParam("marketName", "${marketName}")
      .queryParam("id", "${selectionId}")
      .queryParam("eventId", "${eventId}")
      .queryParam("marketId", "${marketId}")
      .queryParam("priceType", "Fixed")
      .queryParam("startTime", "${eventStartDate}")
      .queryParam("i", "1")
      .headers(headersLoadBetslip)
      .check(status.is(200))
      .check(regex("betSlipSelections=\"(.*?)\"").saveAs("betslipId")))
      .pause(1, 5)
      .exec(http("Mobenga: Navigate to betslip - ${platform}")
      .get(mobSecurebaseUrl + "/" + deviceURL + "/" + mobileLanguage + "/slip")
      .headers(headersLoadBetslip)
      .queryParam("i", "1")
      .check(status.is(200))
      .check(regex("name=\"slip-betslip-id\" value=\"(.*?)\"").saveAs("betSlipID")))
      .pause(1, 5)
      .exec(http("Mobenga: Place single bet - ${platform}")
      .post(mobSecurebaseUrl + "/" + deviceURL + "/android/en-gb/slip")
      .headers(headersPlaceBet)
      .queryParam("i", "1")
      .queryParam("ts", timestamp.toString())
      .formParam("slip-odds-stake-SGL_" + "${selectionId}", betAmount)
      .formParam("_slip-item-check-ew-SGL_" + "${selectionId}", "false")
      .formParam("slip-item-check-sp-indicator-SGL_" + "${selectionId}", "true")
      .formParam("_slip-item-check-sp-SGL_" + "${selectionId}", "false")
      .formParam("slip-item-check-include-multiple-" + "${selectionId}", "on")
      .formParam("slip-betslip-id", "${betSlipID}")
      .formParam("action", "place")
      .formParam("split-betId-list", "SGL_" + "${selectionId}")
      .formParam("removeId", "")
      .formParam("removeBetId", "")
      .formParam("removeComplexBetId", "")
      .formParam("forecastTricastOrders", "")
      .check(status.is(200)))
      .pause(1, 5)
      .exec(http("Mobenga: Get bet receipt")
      .get(mobSecurebaseUrl + "/" + deviceURL + "/en-gb/slip_receipt")
      .headers(headersLoadBetslip)
      .queryParam("i", "1")
      .check(css("#slip_receipt").exists)
      .check(css("#expandable-bet-receipt").exists))
      .pause(1, 5)
    }

}