package com.williamhill.whnft.sports.requestTrafagarObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef.{bodyString, _}

import scala.util.Random

/**
 * Created by lorenzo on 21/04/2016.
 */
object BetSlip {

  val baseURL: String = Common.getConfigFromFile("environment.conf", "baseURL")
  val securebaseURL: String = Common.getConfigFromFile("environment.conf", "securebaseURL")
  val myaccountURL: String = Common.getConfigFromFile("environment.conf", "myaccountURL")
  val betAmount = Common.getConfigFromFile("environment.conf", "betAmount")

  val headersEventspartial = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")

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

  def getUrl(url: String, eventID: String, sport: String) =
    url.replace("${sportName}", sport).replace("${eventID}", eventID)

  def gerRandomSelection(session: Session): Session = {
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

  def placebet(betCode: String, betURL: String, numberOfBets: Int) =
    repeat(numberOfBets) {
      exec(session => {
        if (!session.contains("csrf_token"))
          session.set("csrf_token", "empty")
        else session
      })
      .exec(flushSessionCookies)
      .exec(
        addToBetSlip(betCode, betURL),
        actualBetPlacement(betURL)
      )
    }

  def addToBetSlip(betCode: String, betURL: String): ChainBuilder = {
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseURL).withPath("/")))
    .exec(addCookie(Cookie("cust_login", "${custLogin}")))
    .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
    .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
    .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
    //          .exec(session => gerRandomSelection(session))
    .exec(http("Add to bet slip - ${platform} - ${Sport}")
    .post(baseURL + "/" + betURL + "/en-gb")
    .formParam("action", "GoAddLeg")
    .formParam("leg_sort", "--")
    .formParam("price_type", "L")
    .formParam("lp_num", "${Num}")
    .formParam("lp_den", "${Denom}")
    .formParam("hcap_value", "0")
    .formParam("bir_index", "")
    .formParam("ew_places", "")
    .formParam("ew_fac_num", "")
    .formParam("ew_fac_den", "")
    .formParam("ev_oc_id", "${SelectionID}")
    .formParam("combi_sel", "Y")
    .formParam("blockbuster_id", "-1")
    .formParam("switch_tab", "1")
    .formParam("aff_id", betCode)
    .formParam("csrf_token", "${csrf_token}")
    .headers(headersBetSlip)
    .check(status.is(200))
    .check(regex("\"bet_uid\": \"(.*?)\"").saveAs("betUid"))
    .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token"))
    .check(headerRegex("Set-Cookie", "IBSBET=(.*); path=/").find.transform(string => string.replace("0|0|SGL|1|W|0|", "0|0|SGL|1|W|" + betAmount + "|")).saveAs("ibsbet"))
    .check(headerRegex("Set-Cookie", "IBSLEG=(.*); path=/").saveAs("ibsleg"))
    .check(headerRegex("Set-Cookie", "IBSGRP=(.*); path=/").saveAs("ibsgrp"))
    .check(headerRegex("Set-Cookie", "IBSSLIP=(.*); path=/").saveAs("ibsslip")))
    .pause(1, 5)
  }

  def actualBetPlacement(betURL: String): ChainBuilder = {
    exec(flushSessionCookies)
    .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
    .exec(addCookie(Cookie("cust_login", "${custLogin}")))
    .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
    .exec(addCookie(Cookie("IBSSLIP", "${ibsslip}")))
    .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
    .exec(addCookie(Cookie("IBSLEG", "${ibsleg}")))
    .exec(addCookie(Cookie("IBSGRP", "${ibsgrp}")))
    .exec(addCookie(Cookie("IBSBET", "${ibsbet}")))
    .exec(http("Place bet - ${platform} - ${Sport}")
    .post(securebaseURL + "/" + betURL + "/en-gb")
    .formParam("action", "DoPlaceBet")
    .formParam("dont_show_again", "")
    .formParam("uid", "${betUid}")
    .formParam("csrf_token", "${csrf_token}")
    .formParam("blockbuster_id", "-1")
    .formParam("target_page", baseURL + "/" + betURL + "/en-gb?action=PlayIfrSlip")
    .disableFollowRedirect
    .headers(headersBetSlip)
    .check(status.is(301)))
    // TODO: Add assertion back
    //        .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token"))
    //        .check(regex("\"bet_placed\":(\\s*)\"Y\"").exists)
    //        .check(regex("\"receipt\":(\\s+)\"O\\\\\\/(\\d+)\\\\\\/(\\d+)\\\\\\/(\\w){1}\"").exists))
    // TODO: is bir requ required (post /slp) after this?
    //        .check(regex("\"bir_req_id\":(\\d+)").exists.saveAs("birRedId")))
    .pause(1, 5)
    //        .disableFollowRedirect
    .exec(session => {
    println("Execute bet against selection: " + session("SelectionID").as[String])
    session
    })
  }

}
