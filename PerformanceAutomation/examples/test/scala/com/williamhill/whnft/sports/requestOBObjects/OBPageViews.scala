package com.williamhill.whnft.sports.requestOBObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
 * Created by lorenzo on 21/04/2016.
 */
object OBPageViews {

  val headers = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "User-Agent" -> "${UAStrings}",
    "Accept-Encoding" -> "gzip, deflate, sdch")

  val OBFootballUrl: String = Common.getConfigFromFile("environment.conf", "OBFootballURL")
  val OBTennisUrl: String = Common.getConfigFromFile("environment.conf", "OBTennisURL")
  val OBHorseUrl: String = Common.getConfigFromFile("environment.conf", "OBHorseURL")
  val eventUrl = Common.getConfigFromFile("environment.conf", "eventURLOB")

  def clickFootball =
    exec(http("OB - open Football Highlights - ${platform}")
    .get(OBFootballUrl)
    .headers(headers)
    .check(status.is(200)))
    .pause(1,5)

  def clickTennis =
    exec(http("OB - open Tennis Highlights - ${platform}")
    .get(OBTennisUrl)
    .headers(headers)
    .check(status.is(200)))
//TODO: debug why title assertion fails
//    .check(regex("(Tennis|tennis)").findAll.exists))
    .pause(1,5)

  def clickHorse =
    exec(http("OB - open Horse Highlights - ${platform}")
    .get(OBHorseUrl)
    .headers(headers)
    .check(status.is(200)))
//TODO: debug why title assertion fails
//    .check(regex("(Horse|horse)").findAll.exists))
    .pause(1,5)

  def getEventPage =
    exec(session => {
      session.set("EventID", session("EventID").as[String].replace("OB_EV", ""))
    })
    .exec(http("Open Event Page - ${platform}")
    .get(eventUrl.replace("[eventID]", "${EventID}"))
    .headers(headers)
    .check(status.in(200)))
    .pause(1,5)
    .exec(session => {
      session.set("EventID", "OB_EV" + session("EventID").as[String])
    })

}