package com.williamhill.whnft.sports.requestMobengaObjects

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.williamhill.whnft.sports.helpers.Common

import scala.util.matching.Regex

object HomePageMobenga {
  
  val headersAll = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "User-Agent" -> "${UAStrings}")
  
  val mobSecurebaseUrl : String = Common.getConfigFromFile("environment.conf", "mobSecurebaseURL")
  val authUrl : String = Common.getConfigFromFile("environment.conf", "authURL")
  val contXStack : String = Common.getConfigFromFile("environment.conf", "contXStack")
  
  def get =
    exec(addCookie(Cookie("WH_DEBUG", "Y").withMaxAge(86400).withDomain(".williamhill.com").withPath("/")))
    .exec(addCookie(Cookie("KC", "120").withMaxAge(86400).withDomain(".williamhill.com").withPath("/")))
    .exec(http("Mobenga: Home - ${platform}")
    .get(mobSecurebaseUrl)
    .headers(headersAll)
    .check(status.is(200)))
    .exec(http("Mobenga: Comet - ${platform}")
    .get(mobSecurebaseUrl + "/williamhill_cog_comet_iframe.t")
    .queryParam("i", "1")
    .headers(headersAll)
    .check(status.is(200)))
    .exec(http("Mobenga: Comet - ${platform}")
    .get(mobSecurebaseUrl + "/williamhill_comet_iframe.t")
    .queryParam("i", "1")
    .headers(headersAll)
    .check(status.is(200)))
    .exec(http("Mobenga: en_strings - ${platform}")
    .get(mobSecurebaseUrl + "/en/strings.json")
    .header(HttpHeaderNames.Accept, "application/json")
    .headers(headersAll)
    .check(status.is(200)))
    .exec(http("Mobenga: data_promo - ${platform}")
    .get(mobSecurebaseUrl +  "/data/promo/newFeatures.json")
    .header(HttpHeaderNames.Accept, "application/json")
    .headers(headersAll)
    .check(status.is(200)))
    .exec(http("Mobenga: data_home - ${platform}")
    .get(mobSecurebaseUrl +  "/data/homepage")
    .headers(headersAll)
    .check(status.is(200)))
    .exec(http("Mobenga: synch_trans - ${platform}")
    .get(mobSecurebaseUrl +  "/synch_transactional.t")
    .headers(headersAll)
    .queryParam("i", "1")
    .check(status.is(200)))
    .exec(http("Mobenga: home / - ${platform}")
    .get(mobSecurebaseUrl +  "/")
    .headers(headersAll)
    .check(status.is(200)))
    .exec(http("Mobenga: home / - ${platform}")
    .get(mobSecurebaseUrl +  "/data/topBets")
    .headers(headersAll)
    .check(status.is(200)))
    .exec(http("Mobenga: cas_login - ${platform}")
    .get(authUrl +  "/cas/login")
    .headers(headersAll)
    .queryParam("service", "https://mobet.williamhill.com/auth")
    .queryParam("gateway", "true")
    .header(HttpHeaderNames.Referer, mobSecurebaseUrl)
    .check(status.is(200)))
    .pause(1,5)

  def getHomeOnly  =
    exec(addCookie(Cookie("WH_DEBUG", "Y").withMaxAge(86400).withDomain(".williamhill.com").withPath("/")))
    .exec(addCookie(Cookie("KC", "120").withMaxAge(86400).withDomain(".williamhill.com").withPath("/")))
    .exec(http("Mobenga: Home - ${platform}")
    .get(mobSecurebaseUrl)
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getHiddenLive =
    exec(addCookie(Cookie("msb_stack_tag", contXStack)))
    .exec(http("Mobenga: Home - Hidden Live - ${platform}")
    .get(mobSecurebaseUrl)
    .headers(headersAll)
    .check(status.is(200)))
    .exec(http("Mobenga: Hidden live check - ${platform}")
    .get(mobSecurebaseUrl + "/data/homepage")
    .check(status.is(200))
    .check(substring(""""title":"HIDDEN LIVE"""").exists))
    .pause(1,5)
                    
}