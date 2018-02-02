package com.williamhill.whnft.sports.requestOBObjects

import java.util.Random

import io.gatling.core.Predef._
import com.williamhill.whnft.sports.helpers.Common
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  *
  */
object SelectEventPartialOB {

  val headers = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "User-Agent" -> "${UAStrings}")

  val eventUrl = Common.getConfigFromFile("environment.conf", "eventURLOB")
  val competitionUrl = Common.getConfigFromFile("environment.conf", "competitionURL")

  def fetchSelectionsFromEventPartial = {
    exec(session => {
      var tmp = session("EventID").as[String].replace("OB_EV", "")
      session.set("EventID", tmp)
    })
        .exec(http("Crawl selection IDs OB - ${platform}")
            .get(eventUrl.replace("[eventID]", "${EventID}"))
            .headers(headers)
            .check(status.is(200))
            .check(bodyString.saveAs("responseBody"))
        )
        .exec(session => {
          val pattern = """('L',')([0-9]+)(',')([0-9]+)(','','',')([0-9]+)""".r

          val numArray = pattern.findAllIn(session("responseBody").as[String]).matchData.map(_.group(2)).toArray
          val denomArray = pattern.findAllIn(session("responseBody").as[String]).matchData.map(_.group(4)).toArray
          val selectionArray = pattern.findAllIn(session("responseBody").as[String]).matchData.map(_.group(6)).toArray

          val rand = new Random(System.currentTimeMillis());
          val random_index = rand.nextInt(selectionArray.length);

          session.setAll(Map(
            "num" -> numArray(random_index).toString,
            "denom" -> denomArray(random_index).toString,
            "selectionID" -> selectionArray(random_index).toString)
          )

        })
        //TODO: Remove the save response body
        .exec(session => session.remove("responseBody"))
  }

  def getEventIdFromCompetitionPage(targetCompPage: String) = {
    exec(http("Crawl selection IDs OB - ${platform}")
        .get(competitionUrl.replaceAll("/t/\\d+$", "/t/" + targetCompPage))
        .headers(headers)
        .check(status.is(200))
        .check(bodyString.saveAs("responseBody")))
        .exec(session => {
          val pattern = "tup_row_(\\d+)\"".r
          val events = pattern.findAllMatchIn(session("responseBody").as[String]).toArray
          val rand = new Random(System.currentTimeMillis());
          val random_index = rand.nextInt(events.length);
          val randomEvent = events(random_index).group(1);
          session.set("EventID", randomEvent)
        })
  }
}

//object SelectEventPartialOB {
//
//  val headers = Map(
//    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
//    "User-Agent" -> "${UAStrings}")
//
//  val eventUrl = Common.getConfigFromFile("environment.conf", "eventURLOB")
//
//
//  import scala.util.Random
//
//  def pickRandomSelection (session:Session) = {
//    val num = Random.nextInt(session.get("selections").as[List[String]].size)
//    session
//        .set("selectionID", session.get("selections").as[List[String]].get(num).split(",").get(7).substring(1, session.get("selections").as[List[String]].get(num).split(",").get(7).size-2))
//        .set("num",   session.get("selections").as[List[String]].get(num).split(",").get(3).substring(1, session.get("selections").as[List[String]].get(num).split(",").get(3).size-1))
//        .set("denom", session.get("selections").as[List[String]].get(num).split(",").get(4).substring(1, session.get("selections").as[List[String]].get(num).split(",").get(4).size-1))
//  }
//
//  def fetchSelectionsFromEventPartial = {
//    exec(session => {
//      var tmp = session("EventID").as[String].replace("OB_EV", "")
//      session.set("EventID", tmp)
//    })
//        .exec(http("Crawl selection IDs OB - ${platform}")
//            .get(eventUrl.replace("[eventID]", "${EventID}"))
//            .headers(headers)
//            .check(status.is(200))
//            //      .check(bodyString.saveAs("responseBody"))
//            .check(regex("""onclick="document.betslip.add_leg((.*?))"""").findAll.saveAs("selections"))
//        )
//        //    .exec(session => {
//        //      //TODO: Is always betting on the same selection!!
//        //      val pattern = """('L',')([0-9]+)(',')([0-9]+)(','','',')([0-9]+)""".r
//        //      val num = pattern.findAllIn(session("responseBody").as[String]).matchData.next().group(2)
//        //      val denom = pattern.findAllIn(session("responseBody").as[String]).matchData.next().group(4).toString
//        //      val selection = pattern.findAllIn(session("responseBody").as[String]).matchData.next().group(6).toString
//        //
//        //      session.setAll(Map(
//        //        "num" -> num.toString,
//        //        "denom" -> denom.toString,
//        //        "selectionID" ->  selection.toString)
//        //      )
//        //    })
//        //    .exec(session => session.remove("responseBody"))
//        .exec(session => {pickRandomSelection(session)})
//        .exec(session => session.remove("selections"))
//  }
//
//}

