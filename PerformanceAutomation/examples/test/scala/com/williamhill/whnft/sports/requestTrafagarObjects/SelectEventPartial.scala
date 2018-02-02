package com.williamhill.whnft.sports.requestTrafagarObjects

import io.gatling.core.Predef._
import com.williamhill.whnft.sports.helpers.Common
import io.gatling.http.Predef._
import io.gatling.http.check.HttpCheck
import io.gatling.http.request.builder.HttpRequestBuilder

/**
  * Created by lorenzo on 25/04/2016.
  *
  */
object SelectEventPartial {
  
  val headers = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "User-Agent" -> "${UAStrings}")
  
  val eventUrl = Common.getConfigFromFile("environment.conf", "eventPartialURL")

  def fetchSelectionsFromEventPartial = {
//    doIfEquals("${Sport}", "football") {
      exec(http("Crawl selection IDs - ${platform}")
      .get(eventUrl.replace("[sportName]", "${Sport}").replace("[eventID]", "${EventID}"))
      .headers(headers)
      .check(status.is(200))
      .check (css("div.btmarket__selection > button", "data-entityid").findAll.saveAs("selections"))
      .check(css("div.btmarket__selection > button", "data-num").findAll.saveAs("num"))
      .check(css("div.btmarket__selection > button", "data-denom").findAll.saveAs("denom"))
      )
//    }
//    .doIfEquals("${Sport}", "horse-racing") {
//     exec(http("Crawl selection IDs - ${platform}")
//      .get(eventUrl.replace("[sportName]", "${Sport}").replace("[eventID]", "${EventID}"))
//      .headers(headers)
//      .check(status.is(200))
//      .check(css("div.btmarket__selection button.betbutton", "data-entityid").findAll.saveAs("selections"))
//      .check(css("article.racecard-runner button.betbutton", "data-num").findAll.saveAs("num"))
//      .check(css("article.racecard-runner button.betbutton", "data-denom").findAll.saveAs("denom"))
//      )
//
//    }
    .exec(session=> {
      val numDen =  session("num").as[Vector[String]] zip session("denom").as[Vector[String]]
      val selectionOdd = session("selections").as[Vector[String]] zip numDen
      val selectionOdds = selectionOdd.map(d => Map(d._1 -> d._2))
      session.set("selections", selectionOdds)
    })

  }

  def fetchMarketsFromEventPartial = {
    exec(http("Crawl market IDs - ${platform}")
        .get(eventUrl.replace("[sportName]", "${Sport}").replace("[eventID]", "${EventID}"))
        .headers(headers)
        .check(status.is(200))
        .check(css("div#markets-container > div", "data-entityid").findAll.saveAs("markets"))
    )
  }

}
