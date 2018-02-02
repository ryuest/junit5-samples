package com.williamhill.whnft.sports.requestOBObjects

import com.typesafe.config.ConfigFactory
import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure.ChainBuilder
import scala.util.Random
import scala.util.matching.Regex

/**
 * Created by lorenzo on 21/04/2016.
 */
object BetSlipOB {

  val baseURL : String =Common.getConfigFromFile("environment.conf", "baseURL")
  val securebaseURL : String =Common.getConfigFromFile("environment.conf", "securebaseURL")
  val myaccountURL : String =Common.getConfigFromFile("environment.conf", "myaccountURL")
  val eventUrl = Common.getConfigFromFile("environment.conf", "eventPartialURL")
  val betAmount = Common.getConfigFromFile("environment.conf", "betAmount")

  val headersEventspartial = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "User-Agent" -> "${UAStrings}")

  val headersBetSlip = Map(
    "Accept-Encoding" -> "gzip, deflate",
    "Origin" -> "http://sports.williamhill.com",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,q=0.8",
    "Access-Control-Allow-Headers" -> "X-WH-Wrapped,User-Agent",
    "Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
    "User-Agent" -> "${UAStrings}")

  val headersStatements = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
     "User-Agent" -> "${UAStrings}")

  val headersOpenBetStatement = Map(
    "Accept" -> "application/json, text/plain, */*",
    "Accept-Encoding" -> "gzip, deflate, br",
     "User-Agent" -> "${UAStrings}")

  def getUrl(url:String, eventID: String, sport: String) =
    url.replace("${sportName}", sport).replace("${eventID}", eventID)

  def gerRandomSelection (session : Session) : Session = {
    val selection = Random.shuffle(session("selections").as[Vector[Map[String, (String, String)]]].toList).head
    if (session.contains("selection")) session.remove("selection")
    if (session.contains("num")) session.remove("num")
    if (session.contains("denom")) session.remove("denom")

    session.setAll(Map(
      "selection" -> selection.head._1.replace("OB_OU", ""),
      "num" -> selection.head._2._1,
      "denom" -> selection.head._2._2)
    )
  }

  def placebet(betURL: String, numberOfBets: Int) =
    repeat(numberOfBets) {
      exec(flushSessionCookies)
      .exec(addCookie(Cookie("cust_login", "${custLogin}")))
      .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
      .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
      .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
      exec(http("Add to bet slip OB - ${platform} - ${Sport}")
      .post(baseURL + "/" + betURL + "/en-gb")
      .formParam("action", "GoAddLeg")
      .formParam("leg_sort", "--")
      .formParam("price_type", "L")
      .formParam("lp_num", "${Num}")
      .formParam("lp_den", "${Denom}")
      .formParam("hcap_value", "")
      .formParam("bir_index", "")
      .formParam("ew_fac_num", "")
      .formParam("ew_fac_den", "")
      .formParam("ev_oc_id", "${SelectionID}")
      .formParam("combi_sel", "Y")
      .formParam("blockbuster_id", "-1")
      .formParam("switch_tab", "1")
      .formParam("aff_id", "")
      .formParam("csrf_token", "${csrf_token}")
      .headers(headersBetSlip)
      .check(status.is(200))
      .check(regex("\"bet_uid\": \"(.*?)\"").saveAs("betUid"))
      .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token"))
      .check(headerRegex("Set-Cookie", "IBSBET=(.*); path=/").find.transform(string => string.replace("0|0|SGL|1|W|0|", "0|0|SGL|1|W|" + betAmount + "|")).saveAs("ibsbet"))
      .check(headerRegex("Set-Cookie", "IBSLEG=(.*); path=/").saveAs("ibsleg"))
      .check(headerRegex("Set-Cookie", "IBSGRP=(.*); path=/").saveAs("ibsgrp"))
      .check(headerRegex("Set-Cookie", "IBSSLIP=(.*); path=/").saveAs("ibsslip"))
        .check(headerRegex("Set-Cookie", "IBSBETBOOST=(.*); path=/").saveAs("ibsbetboost")))
      .pause(1, 5)
      .exec(flushSessionCookies)
      .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
      .exec(addCookie(Cookie("cust_login", "${custLogin}")))
      .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
      .exec(addCookie(Cookie("IBSSLIP", "${ibsslip}")))
      .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
      .exec(addCookie(Cookie("IBSLEG", "${ibsleg}")))
      .exec(addCookie(Cookie("IBSGRP", "${ibsgrp}")))
      .exec(addCookie(Cookie("IBSBET", "${ibsbet}")))
        .exec(addCookie(Cookie("IBSBETBOOST", "${ibsbetboost}")))
      .exec(http("Place bet OB - ${platform}")
      .post(securebaseURL + "/" + betURL + "/en-gb")
      .formParam("action", "DoPlaceBet")
      .formParam("uid", "${betUid}")
      .formParam("csrf_token", "${csrf_token}")
      .formParam("blockbuster_id", "-1")
      .formParam("dont_show_again", "")
      .formParam("target_page", baseURL + "/" + betURL + "/en-gb?action=PlayIfrSlip")
      .disableFollowRedirect
      .headers(headersBetSlip)
      .check(status.is(301))
      .check(headerRegex("Location", "(.*)").saveAs("location")))
      .exec(http("Place bet OB follow target - ${platform}")
      .get("${location}")
      .headers(headersBetSlip)
      .check(status.is(200))
        .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token"))
      )
      //TODO: Check why this is not returning back!
//        .check(regex("\"bet_placed\":(\\s*)\"Y\"").exists)
//        .check(regex("\"receipt\":(\\s+)\"O\\\\\\/(\\d+)\\\\\\/(\\d+)\\\\\\/(\\w){1}\"").exists))
      .pause(1, 5)
    }
    .exec(session => {
      println("Execute bet against selection: " + session("SelectionID").as[String])
      session
    })

  def placeMultipleBets( betURL: String, numberOfBets: Int, numOfMultipleBets: Int = 2) =
  //    doIf(session => userLoggedIn(session)) {
    repeat(numberOfBets) {
      exec(session => {
        if (!session.contains("csrf_token"))
          session.set("csrf_token", "empty")
        else session
      })
        .exec(flushSessionCookies)
      exec(
        //          addToBetSlip(betCode, betURL, modifyIbsetCookie = false),
        addToBetSlip(betURL, "2|2|DBL", isFirstSelection = false),
        placeBetNew(betURL)
      )
    }

  def placeBetNew(betURL: String) : ChainBuilder = {
    exec(flushSessionCookies)
      .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
      .exec(addCookie(Cookie("cust_login", "${custLogin}")))
      .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
      .exec(addCookie(Cookie("IBSSLIP", "${ibsslip}")))
      .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
      .exec(addCookie(Cookie("IBSLEG", "${ibsleg}")))
      .exec(addCookie(Cookie("IBSGRP", "${ibsgrp}")))
      .exec(addCookie(Cookie("IBSBET", "${ibsbet}")))
      .exec(addCookie(Cookie("IBSBETBOOST", "${ibsbetboost}")))
      .exec(addCookie(Cookie("IBSTOKEN", "${ibstoken}")))
      .exec(http("Place bet OB - ${platform}")
        .post(securebaseURL + "/" + betURL + "/en-gb")
        .formParam("action", "DoPlaceBet")
        .formParam("uid", "${betUid}")
        .formParam("csrf_token", "${csrf_token}")
        .formParam("blockbuster_id", "-1")
        .formParam("dont_show_again", "")
        .formParam("target_page", baseURL + "/" + betURL + "/en-gb?action=PlayIfrSlip")
        .disableFollowRedirect
        .headers(headersBetSlip)
        .check(status.is(301))
        .check(headerRegex("Location", "(.*)").saveAs("location")))
      .exec(http("Place bet OB follow target - ${platform}")
        .get("${location}")
        .headers(headersBetSlip)
        .check(status.is(200))
        .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token"))
      )
      //TODO: Check why this is not returning back!
      //        .check(regex("\"bet_placed\":(\\s*)\"Y\"").exists)
      //        .check(regex("\"receipt\":(\\s+)\"O\\\\\\/(\\d+)\\\\\\/(\\d+)\\\\\\/(\\w){1}\"").exists))
      .pause(1, 5)
  }

  def addToBetSlip( betURL: String, isBetSuffix: String = "0|0|SGL", modifyIbsetCookie: Boolean = true, isFirstSelection: Boolean = true) : ChainBuilder = {
    exec(flushSessionCookies)
      .exec(addCookie(Cookie("cust_login", "${custLogin}")))
      .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
      .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
      .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
    exec(http("Add to bet slip OB - ${platform} - ${Sport}")
      .post(baseURL + "/" + betURL + "/en-gb")
      .formParam("action", "GoAddLeg")
      .formParam("leg_sort", "--")
      .formParam("price_type", "L")
      .formParam("lp_num", "${Num}")
      .formParam("lp_den", "${Denom}")
      .formParam("hcap_value", "")
      .formParam("bir_index", "")
      .formParam("ew_fac_num", "")
      .formParam("ew_fac_den", "")
      .formParam("ev_oc_id", "${SelectionID}")
      .formParam("combi_sel", "Y")
      .formParam("blockbuster_id", "-1")
      .formParam("switch_tab", "1")
      .formParam("aff_id", "")
      .formParam("csrf_token", "${csrf_token}")
      .headers(headersBetSlip)
      .check(status.is(200))
      .check(regex("\"bet_uid\": \"(.*?)\"").saveAs("betUid"))
      .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token"))
      .check(headerRegex("Set-Cookie", "IBSBET=(.*); path=/").find.transform(string => string.replace("0|0|SGL|1|W|0|", "0|0|SGL|1|W|" + betAmount + "|")).saveAs("ibsbet"))
      .check(headerRegex("Set-Cookie", "IBSLEG=(.*); path=/").saveAs("ibsleg"))
      .check(headerRegex("Set-Cookie", "IBSGRP=(.*); path=/").saveAs("ibsgrp"))
      .check(headerRegex("Set-Cookie", "IBSSLIP=(.*); path=/").saveAs("ibsslip"))
      .check(headerRegex("Set-Cookie", "IBSBETBOOST=(.*); path=/").saveAs("ibsbetboost")))
      .doIf(modifyIbsetCookie){
        exec(session => {
          session.set("ibsbet", session("ibsbet").as[String].replace(isBetSuffix + "|1|W|0|", isBetSuffix + "|1|W|" + betAmount + "|"))
        })
      }
      .pause(1, 5)
  }

  def placeDoubleBet( betURL: String, numberOfBets: Int) =
    exec(addToBetSlip(betURL))
      .exec(addCookie(Cookie("cust_login", "${custLogin}")))
      .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
      .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
      .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
      .exec(http("Add to 2nd bet slip - ${platform} - ${Sport}")
        .post(baseURL + "/" + betURL + "/en-gb")
        .formParam("action", "GoAddLeg")
        .formParam("leg_sort", "--")
        .formParam("price_type", "L")
        .formParam("lp_num", "${Num2}")
        .formParam("lp_den", "${Denom2}")
        .formParam("hcap_value", "0")
        .formParam("bir_index", "")
        .formParam("ew_places", "")
        .formParam("ew_fac_num", "")
        .formParam("ew_fac_den", "")
        .formParam("ev_oc_id", "${SelectionID2}")
        .formParam("combi_sel", "Y")
        .formParam("blockbuster_id", "-1")
        .formParam("switch_tab", "1")
        .formParam("aff_id", "")
        .formParam("csrf_token", "${csrf_token}")
        .headers(headersBetSlip)
        .check(status.is(200))
        .check(bodyString.saveAs("responseError"))
        .check(regex("\"bet_uid\": \"(.*?)\"").saveAs("betUid"))
        .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token"))
        .check(headerRegex("Set-Cookie", "IBSBET=(.*); path=/").find.transform(string => string.replace("2|2|DBL|1|W|0|", "2|2|DBL|1|W|" + betAmount + "|")).saveAs("ibsbet"))
        .check(headerRegex("Set-Cookie", "IBSLEG=(.*); path=/").saveAs("ibsleg"))
        .check(headerRegex("Set-Cookie", "IBSGRP=(.*); path=/").saveAs("ibsgrp"))
        .check(headerRegex("Set-Cookie", "IBSSLIP=(.*); path=/").saveAs("ibsslip"))
        .check(headerRegex("Set-Cookie", "IBSTOKEN=(.*); path=/").find.transform(string => string.replace("|N|", "|Y|")).saveAs("ibstoken"))
      )
      .pause(1, 5)
      .exec(placeBetNew(betURL))
}
