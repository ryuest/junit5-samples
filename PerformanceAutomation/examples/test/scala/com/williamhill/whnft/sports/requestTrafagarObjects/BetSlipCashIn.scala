package com.williamhill.whnft.sports.requestTrafagarObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef.{bodyString, _}


/**
  * Created by lorenzo on 21/04/2016.
  */
object BetSlipCashIn {

  val baseURL : String =Common.getConfigFromFile("environment.conf", "baseURL")
  val securebaseURL : String =Common.getConfigFromFile("environment.conf", "securebaseURL")

  val isPTE : Boolean =
    Common.environmentUnderTest.toUpperCase == "PTE"

  val betURL : String =
    if (isPTE)
      "_gib"
    else
      ""


  def cashInBet (numberOfBets: Int) =
    tryMax(3) {
   //   repeat(numberOfBets) {
        exec(flushSessionCookies)
          .exec(addCookie(Cookie("cust_login", "${custLogin}")))
          .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
        exec(http("Ajax Go bets - ${platform}")
          .post(baseURL + "/" + "ajax" + betURL + "/en-gb")
          .formParam("action", "GoBets")
          .formParam("blockbuster", "-1")
          .formParam("csrf_token", "${csrf_token}")
          .check(status.is(200))
          .check(headerRegex("Set-Cookie", "CSRF_COOKIE=([^;]+)").exists.saveAs("csrfToken")))
          .exec(http("Ajax Open bets - ${platform}")
            .post(baseURL + "/" + "ajax" + betURL + "/en-gb")
            .formParam("action", "GoOpenBets")
            .formParam("blockbuster", "-1")
            .formParam("csrf_token", "${csrf_token}")
            .check(status.is(200))
            .check(regex("\"bet_id\": \"(.*?)\"").saveAs("bet_id"))
            .check(headerRegex("Set-Cookie", "CSRF_COOKIE=([^;]+)").exists.saveAs("csrf_token"))
            .check(regex("\"cashout_value\": \"(.*?)\"").saveAs("cashoutValue")))
          .exec(addCookie(Cookie("cust_login", "${custLogin}")))
          .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
          .exec(http("Cashout Placed Bet - ${platform}")
            .post(securebaseURL + "/slp" + betURL + "/en-gb")
            .formParam("action", "DoCashOutBet")
            .formParam("bet_id", "${bet_id}")
            .formParam("cashout_price", "${cashoutValue}")
            .formParam("target_page", baseURL + "/slp" + betURL + "/en-gb?action=PlayIfrOpenBets")
            .formParam("csrf_token", "${csrf_token}")
            .formParam("partialCashout", "")
            .check(status.is(200))
            .check(headerRegex("Set-Cookie", "CSRF_COOKIE=([^;]+)").exists.saveAs("csrf_token")))
      }
  //  }
      .pause(1,5)

  def getAjaxOpenBets =
    tryMax(3) {
      exec(http("Ajax Open bets - ${platform}")
        .post(baseURL + "/" + "ajax" + betURL + "/en-gb")
        .formParam("action", "GoOpenBets")
        .formParam("blockbuster", "-1")
        .formParam("csrf_token", "${csrf_token}")
        .check(status.is(200))
        .check(regex("\"bet_id\": \"(.*?)\"").saveAs("bet_id"))
        .check(headerRegex("Set-Cookie", "CSRF_COOKIE=([^;]+)").exists.saveAs("csrf_token"))
        .check(regex("\"cashout_value\": \"(.*?)\"").saveAs("cashoutValue")))
    }
      .pause(1,5)

  def cashoutPlacedBet =
    tryMax(3) {
      exec(addCookie(Cookie("cust_login", "${custLogin}")))
        .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
        .exec(http("Cashout Placed Bet - ${platform}")
          .post(securebaseURL + "/slp" + betURL + "/en-gb")
          .formParam("action", "DoCashOutBet")
          .formParam("bet_id", "${bet_id}")
          .formParam("cashout_price", "${cashoutValue}")
          .formParam("target_page", baseURL+"/slp"+ betURL +"/en-gb?action=PlayIfrOpenBets")
          .formParam("csrf_token", "${csrf_token}")
          .formParam("partialCashout", "")
          .check(status.is(200))
          .check(headerRegex("Set-Cookie", "CSRF_COOKIE=([^;]+)").exists.saveAs("csrf_token"))
        )
    }
      .pause(1,5)
}