package com.williamhill.whnft.sports.requestMobengaObjects

import io.gatling.core.Predef._
import com.williamhill.whnft.sports.helpers.Common
import io.gatling.http.Predef._

object SelectEventPartialMobenga {
  
  val headers = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
  "User-Agent" -> "${UAStrings}")

  //https://mobet.williamhill.es/data/event/9323273
  val mobSecurebaseUrl: String = Common.getConfigFromFile("environment.conf", "mobSecurebaseURL")

  def fetchSelectionsFromEventPartial = {
    exec(session => {
      var tmp = session("EventID").as[String].replace("OB_EV", "")
      session.set("EventID", tmp)
    })
    .exec(http("Mobenga: Crawl selection IDs - ${platform}")
      .get(mobSecurebaseUrl + "/data/event/${EventID}")
      .headers(headers)
      .check(status.is(200))
      .check(jsonPath("$.id").findAll.transform(s => util.Random.shuffle(s).get(0)).saveAs("eventId")) //already have event id as above
      .check(jsonPath("$.collections[*].id").findAll.transform(s => util.Random.shuffle(s).get(0)).saveAs("collectionId"))
      .check(jsonPath("$.collections[?(@.id == ${collectionId})].markets[*].id").findAll.transform(s => util.Random.shuffle(s).get(0)).saveAs("marketId"))
      .check(jsonPath("$.collections[?(@.id == ${collectionId})].markets[?(@.id == ${marketId})].selections[*].id").findAll.transform(s => util.Random.shuffle(s).get(0)).saveAs("selectionId"))
      .check(jsonPath("$.collections[?(@.id == ${collectionId})].name").find.saveAs("eventName"))
      .check(jsonPath("$.dateTime").find.saveAs("eventStartDate"))
      .check(jsonPath("$.collections[?(@.id == ${collectionId})].markets[?(@.id == ${marketId})].name").find.saveAs("marketName"))
      .check(jsonPath("$.collections[?(@.id == ${collectionId})].markets[?(@.id == ${marketId})].selections[?(@.id == ${selectionId})].name").find.saveAs("selectionName"))
      .check(jsonPath("$.collections[?(@.id == ${collectionId})].markets[?(@.id == ${marketId})].selections[?(@.id == ${selectionId})].numerator").find.saveAs("selectionNumerator"))
      .check(jsonPath("$.collections[?(@.id == ${collectionId})].markets[?(@.id == ${marketId})].selections[?(@.id == ${selectionId})].denominator").find.saveAs("selectionDenominator"))
    )
   }
  
}