package com.williamhill.whnft.sports.requestMobengaObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PageViewsMobenga {
  
  val headers = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
  "User-Agent" -> "${UAStrings}",
  "Accept-Encoding" -> "gzip, deflate, sdch, br")

  val baseUrl: String = Common.getConfigFromFile("environment.conf", "baseURL")
  val authUrl: String = Common.getConfigFromFile("environment.conf", "authURL")
  val mobSecurebaseUrl: String = Common.getConfigFromFile("environment.conf", "mobSecurebaseURL")
  val mobileLanguage: String = Common.getConfigFromFile("environment.conf", "mobileLanguage")
  val deviceURL: String = Common.getConfigFromFile("environment.conf", "deviceURL")

  def navigateToHome =
    exec(addCookie(Cookie("WH_DEBUG", "Y")))
    .exec(addCookie(Cookie("KC", "120")))
    .exec(http("Mobenga: Go to home - ${platform}")
    .get(mobSecurebaseUrl + "/")
    .headers(headers)
    .check(status.is(200)))
//    .check(regex("<title>William Hill Mobile Betting</title>").exists))
    .pause(1,5)

  def navigateToDataInPlayHome =
    exec(http("Mobenga: Go to data inPlayHome - ${platform}")
    .get(mobSecurebaseUrl + "/data/inplay/home/tab")
    .headers(headers)
    .check(status.is(200)))
//    .check(jsonPath("$.sports").exists))
    .pause(1,5)

  def navigateToDataInPlay =
    exec(http("Mobenga: Go to data inPlay - ${platform}")
    .get(mobSecurebaseUrl + "/data/inplay")
    .headers(headers)
    .check(status.is(200)))
//    .check(jsonPath("$.sports").exists))
    .pause(1,5)

  def navigateToAccLoginRedirect =
    exec(http("Mobenga: Go to acc loginRedirect - ${platform}")
    .get(mobSecurebaseUrl + "/acc/native_login_redirect")
    .queryParam("i", 1)
    .headers(headers)
    .check(status.is(200)))
//    .check(regex("<title>William Hill Mobile Betting</title>").exists))
    .pause(1,5)

  def navigateToStringsJson =
    exec(http("Mobenga: Go to strings json - ${platform}")
    .get(mobSecurebaseUrl + "/en/strings.json")
    .headers(headers)
    .check(status.is(200)))
//    .check(regex("<title>William Hill Mobile Betting</title>").exists))
    .pause(1,5)

  def navigateToFootball =
    exec(http("Mobenga: Go to football - ${platform}")
    .get(mobSecurebaseUrl + "/"+ deviceURL + "/bet/" + mobileLanguage + "/betting/y/9/Football.html?i=1")
    .headers(headers)
    .check(status.is(200)))
//    .check(regex("Football Betting | William Hill Mobile").exists))
    .pause(1,5)
			
  def navigateToFootballByDay =
    exec(http("Mobenga: Navigate to football by day - ${platform}")
    .get(mobSecurebaseUrl + "/!bet/" + mobileLanguage + "/by_day?category=FOOTBALL&date=TODAY")
    .headers(headers)
    .check(status.is(200)))
//    .check(regex("<title>William Hill Mobile Betting</title>").exists))
    .pause(1,5)

  def navigateToWebkitGeneric =
    exec(http("Mobenga: Navigate to webkit generic - ${platform}")
    .get(mobSecurebaseUrl + "/!/53.9/js/williamhill/webkit/generic/")
    .headers(headers)
    .check(status.is(200)))
//    .check(regex("<title>William Hill Mobile Betting</title>").exists))
    .pause(1,5)

  def navigateToHorseRacing =
    exec(http("Mobenga: Navigate to horse racing - ${platform}")
    .get(mobSecurebaseUrl + "/!android/bet/en-gb/betting/y/43/Horse-Racing.html")
    .queryParam("i", 1)
    .headers(headers)
    .check(status.is(200)))
//    .check(regex("Horse Racing Betting | William Hill Mobile").exists))
    .pause(1,5)

  def getSidemenu =
    exec(http("Mobenga: Get sidemenu - ${platform}")
    .get(mobSecurebaseUrl)
    .queryParam("native", "1")
    .queryParam("_json_data_", "sidemenu.t")
    .queryParam("appVersion", "4.0")
    .headers(headers)
    .check(status.is(200)))
//    .check(jsonPath("$.Sections").exists))
    .pause(1,5)

  def getCometIframe =
    exec(http("Mobenga: Get comet iframe - ${platform}")
    .get(mobSecurebaseUrl +  "/williamhill_comet_iframe.t")
    .queryParam("i", "1")
    .headers(headers)
    .check(status.is(200)))
    .pause(1,5)

  def getCometCogIframe =
    exec(http("Mobenga: Get comet cog iframe - ${platform}")
    .get(mobSecurebaseUrl + "/williamhill_cog_comet_iframe.t")
    .queryParam("i", "1")
    .headers(headers)
    .check(status.is(200)))
    .pause(1,5)

  def getIndexContent =
    exec(http("Mobenga: Get index content - ${platform}")
    .get(mobSecurebaseUrl + "/!williamhill_index_content.t")
    .headers(headers)
    .check(status.is(200)))
//    .check(regex("<title>William Hill Mobile Betting</title>").exists))
    .pause(1,5)

  def navigateToDataPromo =
    exec(http("Mobenga: Navigate to data promo - ${platform}")
    .get(mobSecurebaseUrl + "/data/promo/newFeatures.json")
    .headers(headers)
    .header(HttpHeaderNames.Accept, "application/json")
    .check(status.is(200)))
    .pause(1,5)

  def navigateToDataHome =
    exec(http("Mobenga: Navigate to data home - ${platform}")
    .get(mobSecurebaseUrl + "/data/homepage")
    .headers(headers)
    .check(status.is(200)))
    .pause(1,5)

  def navigateToSyncTrans =
    exec(http("Mobenga: Navigate to sync trans - ${platform}")
    .get(mobSecurebaseUrl + "/synch_transactional.t")
    .header(HttpHeaderNames.Referer, mobSecurebaseUrl)
    .queryParam("i", 1)
    .headers(headers)
    .check(status.is(200)))
    .pause(1,5)

  def navigateToTopBets =
    exec(http("Mobenga: Navigate to top bets - ${platform}")
    .get(mobSecurebaseUrl + "/data/topBets")
    .headers(headers)
    .check(status.is(200)))
    .pause(1,5)

  def navigateToCasLogin =
    exec(http("Mobenga: Navigate to  cas login - ${platform}")
    .get(authUrl + "/cas/login")
    .queryParam("service", "https://mobet.williamhill.com/auth")
    .queryParam("gateway", "true")
    .headers(headers)
    .header(HttpHeaderNames.Referer, mobSecurebaseUrl)
    .check(status.is(200)))
    .pause(1,5)

}