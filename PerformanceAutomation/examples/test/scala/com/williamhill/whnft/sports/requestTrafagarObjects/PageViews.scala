package com.williamhill.whnft.sports.requestTrafagarObjects

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.williamhill.whnft.sports.helpers.Common

/**
 * Created by lorenzo on 21/04/2016.
 */
object PageViews {

  val headersAll = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, sdch",
    "User-Agent" -> "${UAStrings}")
  val headersFragment = Map(
    "Accept" -> "text/html, */*; q=0.01",
    "Accept-Encoding" -> "gzip, deflate, sdch",
    "User-Agent" -> "${UAStrings}")

  val baseUri: String = Common.getConfigFromFile("environment.conf", "baseURL")
  val eventUrlPartial : String = Common.getConfigFromFile("environment.conf", "eventPartialURL")
  val marketFragmentUrl : String = Common.getConfigFromFile("environment.conf", "marketFragmentURL")
  val eventUrl: String = Common.getConfigFromFile("environment.conf", "eventURL")
  val numBets: String = Common.getConfigFromFile("environment.conf", "NumTopBets")
  val sportIds: String = Common.getConfigFromFile("environment.conf", "SportIds")
  val categoryStreamingLogs: String = Common.getConfigFromFile("environment.conf", "categoryStreamingLogsURL")
  val connDetailsStreamingLogs: String = Common.getConfigFromFile("environment.conf", "connDetailsStreamingLogsURL")
//  val offerClubUrl: String = Common.getConfigFromFile("environment.conf", "offerClubURL")
  val intlFootball : Map[String, String] =
    Map(
      "ru-ru" -> "Футбол",
      "el-gr" -> "Ποδόσφαιρο",
      "de-de" -> "Fußball",
      "sv-se" -> "Fotboll",
      "ja-jp" -> "サッカー"
    )

  def getMarketFragment =
    exec(session => {
      val r1 = new scala.util.Random(session.hashCode())
      val marketId = session.get("markets").as[Vector[String]]
      val markUrl = marketFragmentUrl.replace("[sportName]", "football").replace("[eventID]", session.get("EventID").as[String]).replace("[marketID]", marketId(r1.nextInt(17)))
      session.set("fragmentUrl", markUrl)
    })
    .exec(session => {
      println(session.get("fragmentUrl").as[String])
      session
    })
    .exec(http("Open Market Fragment - ${platform}")
      .get("${fragmentUrl}")
      .headers(headersFragment)
      .check(status.is(200)) // check not cached
    )
    .pause(1,5)

  def getFootballHighlights =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Football Highlights - ${platform}")
    .get(baseUri + "/betting/en-gb/football")
    .headers(headersAll)
    .check(status.is(200))
    .check(regex("<title>Bet on Football at William Hill - Football Betting</title>").count.is(1)))
    .pause(1,5)

  def getFootballHighlightsPartial =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Football Highlights partial - ${platform}")
    .get(baseUri + "/betting/en-gb/football.partial")
    .headers(headersAll)
    .check(status.is(200))) //  .check(regex("<title>Online Betting from William Hill - The Home of Betting</title>").count.is(1))
    .pause(1,5)

  def getFootballMatches =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Football Matches - ${platform}")
    .get(baseUri + "/betting/en-gb/football/matches")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getFootballMatchesPartial =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Football Matches partial - ${platform}")
    .get(baseUri + "/betting/en-gb/football/matches.partial")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getFootballCompetitions =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Football Matches competitions - ${platform}")
    .get(baseUri + "/betting/en-gb/football/competitions")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getFootballCompetitionsPartial =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Football Matches competitions partial - ${platform}")
    .get(baseUri + "/betting/en-gb/football/competitions.partial")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getEvent =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Event Page - ${platform}")
    .get(eventUrl.replace("[sportName]", "${Sport}").replace("[eventID]", "${EventID}"))
    .headers(headersAll)
    .check(status.in(200,304)))
    .pause(1,5)

  def getEventPartial =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Football Event partial - ${platform}")
    .get(eventUrlPartial.replace("[sportName]", "${Sport}").replace("[eventID]", "${EventID}"))
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getInplay =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open in-play - ${platform}")
    .get(baseUri + "/betting/en-gb/in-play/all")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getInplayPartial =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open foot in-play partial - ${platform}")
    .get(baseUri + "/betting/en-gb/in-play/all.partial")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getHorseRacingAntePost =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Horse Racing ante-post - ${platform}")
    .get(baseUri + "/betting/en-gb/horse-racing/ante-post")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getHorseRacingAntePostPartial =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Horse Racing ante-post part - ${platform}")
    .get(baseUri + "/betting/en-gb/horse-racing/ante-post.partial")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getHorseRacingSpecials =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Horse Racing specials - ${platform}")
    .get(baseUri + "/betting/en-gb/horse-racing/specials")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getHorseRacingSpecialsPartial =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Horse Racing specials part - ${platform}")
    .get(baseUri + "/betting/en-gb/horse-racing/specials.partial")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getHorseRacingMeetings =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open horse in-play - ${platform}")
    .get(baseUri + "/betting/en-gb/horse-racing/meetings")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getHorseRacingMeetingsPartial =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open horse in-play part - ${platform}")
    .get(baseUri + "/betting/en-gb/horse-racing/meetings.partial")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getTopBetsApps =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Top Bets Apps - ${platform}")
    .get(baseUri + "/betting/en-gb/apps/top-bets")
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getTopBets =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Get Top Bets - ${platform}")
    .get(baseUri + "/betting/en-gb")
    .queryParam("action", "GetTopXSeln")
    .queryParam("Type", "json")
    .queryParam("NumBets", numBets) // "NumBets" : "10"
    .queryParam("SportIds", sportIds) // TODO: Update sportsIds "SportIds" : "1,5,6,8,9,11,42,43,24,19"
    .queryParam("Locale", "gb")
    .queryParam("Page", "0")
    .headers(headersAll)
    .check(status.in(200, 304)))
    .pause(1,5)

  def getStreamingLogs =
    //TODO: Include the login call in the request
    getEvent
    .exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Get Category streaming logs - ${platform}")
    .get(categoryStreamingLogs)
    .headers(headersAll)
    .check(status.is(204)))
    .pause(1,5)
    .exec(http("Get ConnDetails streaming logs - ${platform}")
    .get(connDetailsStreamingLogs)
    .headers(headersAll)
    .check(status.is(200)))
    .pause(1,5)

  def getIntlHome =
    foreach(intlFootball.toSeq, "intlFootball") {
      exec(session => {
        var kV = session("intlFootball").as[Tuple2[String, String]]
        session.setAll(Map(
          "language" -> kV._1,
          "intlSport" -> kV._2
        ))
      })
      .exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
      .exec(http("Open ${language} Home - ${platform}")
      .get(baseUri + "/betting/${language}")
      .headers(headersAll)
      .check(status.is(200))
      .check(regex("<span(.*)>${intlSport}</span>").exists))
    }
    .pause(1,5)

  def getIntlFootballHighlights =
    foreach(intlFootball.toSeq, "intlFootball") {
      exec(session => {
        var kV = session("intlFootball").as[Tuple2[String, String]]
        session.setAll(Map(
          "language" -> kV._1,
          "intlSport" -> kV._2
        ))
      })
      .exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
      .exec(http("Open ${language} Football Highlights - ${platform}")
      .get(baseUri + "/betting/${language}/${intlSport}")
      .headers(headersAll)
      .check(status.is(200))
      .check(regex("<span(.*)>${intlSport}</span>").exists))
    }
    .pause(1,5)

  def getVirtualWorld =
    exec(addCookie(Cookie("wh_traf_bet", "Yes").withMaxAge(86400).withDomain(baseUri).withPath("/")))
    .exec(http("Open Virtual World - ${platform}")
    .get(baseUri + "/betting/en-gb/apps/virtual/horse-racing/flat")
    .headers(headersAll)
    .check(status.is(200))
    )
    .pause(1,5)

//  def getOfferClubFlow =
//    exec(addCookie(Cookie("cust_login", "${custLogin}")))
//    .exec(addCookie(Cookie("cust_auth", "${custAuth}")))
//    .exec(addCookie(Cookie("cust_ssl_login", "${sslLogin}")))
//    .exec(http("Open OfferClub page - ${platform}")
//    .get(offerClubUrl)
//    .check(status.is(200))
//    .check(regex("""<input type="hidden" name="ticket" value="(.*)">""").saveAs("unique_id")))
//    .exec(http("whauth")
//    .post("https://offerclub.williamhillbet.com/whauth")
//    .formParam("ticket", "${unique_id}")
//    .check(status.is(200)))
//    .exec(http("Click join - ${platform}")
//    .get(offerClubUrl + "Home/Join")
//    .check(status.is(200)))
//    .pause(1,5)
//    .exec(http("Click bet now - ${platform}")
//    .get("http://sports.williamhill.com/bet/en-gb/betting/y/5/cp/727/Football.html?intcid=offCB-uki-spo-OfferClub-oth-con-all-ptl-soc-040816")
//    .check(status.is(200)))
//    .pause(1,5)

  //TODO:Check if the following endpoints are hit:
  //http://w.sports.williamhill.com/fragments/racecard/en-gb/horse-racing/OB_EV9005157
  //http://sports.williamhill.com/mbt_ajax/en-gb
  //GET   https://myaccount.williamhill.com/callback
  //GET   https://myaccount.williamhill.com/api/statements/bet
  //GET   http://whdn.williamhill.com/cms/maintenance/sports/
  //GET   http://w.sports.williamhill.com/fragments/eventEntity/en-gb/horse-racing/OB_EV8960724/1.json
  //GET   http://w.sports.williamhill.com/fragments/eventEntity/en-gb/football/OB_EV8960724/1.json
  //GET   http://w.sports.williamhill.com/betting/en-gb/horse-racing/specials.partial
  //GET   http://w.sports.williamhill.com/betting/en-gb/horse-racing/OB_EV8960724.partial
  //GET   http://w.sports.williamhill.com/betting/en-gb/horse-racing/ante-post.partial
  //GET   http://w.sports.williamhill.com/betting/en-gb/football/OB_EV8960724.partial
  //GET   http://sports.williamhill.com/slp/en-gb/custom/initAPI.js
  //GET   http://sports.williamhill.com/betting/en-gb/football/matches/competition

}