package com.williamhill.whnft.sports.requestTrafagarObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AccountStatements {
  
  val headersStatements = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
     "User-Agent" -> "${UAStrings}")

  val headersOpenBetStatement = Map(
    "Accept" -> "application/json, text/plain, */*",
    "Accept-Encoding" -> "gzip, deflate, br",
     "User-Agent" -> "${UAStrings}")

  val myaccountURL : String = Common.getConfigFromFile("environment.conf", "myaccountURL")
  
  def getOpenBets =
    exec(addCookie(Cookie("cust_login", "${custLogin}")))
    .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
    .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
    .exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain("myaccount.williamhill.com").withPath("/")))
    .exec(http("Account statements - ${platform}")
    .get(myaccountURL + "/statements?locale=&layout=desktop")
    .headers(headersStatements)
    .check(status.is(200)))
    .pause(1,5)
    .exec(http("Get open bet statements - ${platform}")
    .get(myaccountURL + "/api/statements/bet/open?fromDate=" + (System.currentTimeMillis - 30000) + "&pageSize=20&toDate=" + (System.currentTimeMillis + 10000))
    .disableFollowRedirect
    .headers(headersOpenBetStatement)
    .check(status.is(200))
    .check(regex("reference\":\"O\\/(\\d+)\\/(\\d+)\\/(\\w){1}\",").exists))
    .pause(1,5)

  def getAccountStatements (numberOfAccStatements : Int = 1)=
    repeat(numberOfAccStatements) {
      exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain("myaccount.williamhill.com").withPath("/")))
      .exec(http("Get account statements - ${platform}")
      .get(myaccountURL + "/statements?locale=&layout=desktop")
      .headers(headersStatements)
      .check(status.is(200)))
      .pause(1, 5)
      .exec(http("translations/labels - ${platform}")
      .get(myaccountURL + "/translations/labels?application=statements&locale=")
      .headers(headersStatements)
      .check(status.is(200)))
      .pause(1, 5)
      .exec(http("/api/account - ${platform}")
      .get(myaccountURL + "/api/account")
      .headers(headersStatements)
      .check(status.is(200)))
      .pause(1, 5)
      .exec(http("statements/bet/settled - ${platform}")
      .get(myaccountURL + "/api/statements/bet/settled?fromDate=" + (System.currentTimeMillis - 3000000) + "&pageSize=20&toDate=" + System.currentTimeMillis)
      .headers(headersStatements)
      .check(status.is(200)))
      .pause(1, 5)
      .exec(http("statements/bet/open - ${platform}")
      .get(myaccountURL + "/api/statements/bet/open?fromDate=" + (System.currentTimeMillis - 3000000) + "&pageSize=20&toDate=" + System.currentTimeMillis)
      .headers(headersStatements)
      .check(status.is(200)))
      .pause(1, 5)
      .exec(http("statements/bet/open - ${platform}")
      .get(myaccountURL + "/api/statements/payment?fromDate=" + (System.currentTimeMillis - 3000000) + "&pageSize=20&toDate=" + System.currentTimeMillis)
      .headers(headersStatements)
      .check(status.is(200)))
      .pause(1, 5)
    }

  def getSettledBets (fromDate : String, toDate : String) =
    doIf(session => session.contains("custLogin")) {
      exec(http("statements/bet/settled - ${platform}")
      .get(myaccountURL + "/api/statements/bet/settled?fromDate=" + fromDate + "&pageSize=20&toDate=" + toDate)
      .headers(headersStatements)
      .check(status.is(200))
      .check(jsonPath("$.statements").exists))
      .pause(1, 5)
    }

  def getPayments =
    exec(http("statements/bet/open - ${platform}")
    .get(myaccountURL + "/api/statements/payment?fromDate=" + (System.currentTimeMillis - 3000000) + "&pageSize=20&toDate=" + System.currentTimeMillis)
    .headers(headersStatements)
    .check(status.is(200)))
    .pause(1,5)

}