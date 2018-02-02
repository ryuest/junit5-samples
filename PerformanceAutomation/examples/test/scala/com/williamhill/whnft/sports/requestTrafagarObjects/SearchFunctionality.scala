package com.williamhill.whnft.sports.requestTrafagarObjects

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.RandomGen
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

/**
 * Created by lorenzo on 21/04/2016.
 */
object SearchFunctionality {

  val baseUri: String = Common.getConfigFromFile("environment.conf", "baseURL")
  val baseSportUri: String = baseUri + "/betting/en-gb"
  val headersAll = Map(
    "Accept" -> "*/*",
    "Accept-Encoding" -> "gzip, deflate, sdch",
    "Accept-Language" -> "en-GB,en-US;q=0.8,en;q=0.6,it;q=0.4",
    "Connection" -> "keep-alive",
    "Origin" -> baseUri,
    "Referer" -> baseSportUri,
    "User-Agent" -> "${UAStrings}"
  )

  val searchUrl: String = Common.getConfigFromFile("environment.conf", "searchURL")
  val top10Searches : Array[String] =
    Array(
        "None",
        "Big brother",
        "Ufc",
        "Olympics",
        "Premier league",
        "Big",
        "Irish",
        "Irish lottery",
        "premier",
        "Lottery"
    )

//  def top10QuerySearch = Random.shuffle(top10Searches.toList).head
  def top10QuerySearch = top10Searches(0)
  def randomQuerySearch = RandomGen.getRandomAlphaNumerics(10)

  def makeRealisticSearch =
    exec(session => {
      session.set("searchUrl", searchUrl.replace("[searchTerm]", top10QuerySearch))
    })
    .exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Top 10 Searches - ${platform}")
    .get("${searchUrl}")
    .headers(headersAll)
    .check(status.is(200)))

  def generateCacheMisses =
    exec(session => {
      session.set("searchUrl", searchUrl.replace("[searchTerm]", randomQuerySearch))
    })
    .exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Random cache miss search - ${platform}")
    .get("${searchUrl}")
    .headers(headersAll)
    .check(status.is(200)))
}