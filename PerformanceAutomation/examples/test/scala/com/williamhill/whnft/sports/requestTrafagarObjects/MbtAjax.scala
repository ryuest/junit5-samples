package com.williamhill.whnft.sports.requestTrafagarObjects

import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by lorenzo on 25/04/2016.
  */
object MbtAjax {
  
   val headers = Map(
    "Accept-Encoding" -> "gzip, deflate",
    "Origin" -> "http://sports.williamhill.com",
     "User-Agent" -> "${UAStrings}")
  
  val eventUrl : String = Common.getConfigFromFile("environment.conf", "mbtAjaxURL")

  def request =
    exec(session =>
    {
      val csrfCookie = Common.getCookieValue("CSRF_COOKIE", "http://.williamhill.com", session)
      session.set("csrfCookie", csrfCookie)
    })
    .exec(http("Post Mbt Ajax - ${platform}")
        
      .post(eventUrl)
      .formParam("action", "GoBets")
      .formParam("blockbuster_id", "-1")
      .formParam("csrf_token", "${csrfCookie}")
      .headers(headers)
      .check(status.is(200))
      .check(bodyString.saveAs("body"))
      
    )
    .pause(1,5)

}
