package com.web.requestObjects

/**
  * Created by jboiko on 15/12/2017.
  */

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.web.helpers.Common

object HomePage {

  val securebaseURL : String = Common.getConfigFromFile("environment.conf", "securebaseURL")

  val headersAll = Map(
    HttpHeaderNames.Accept -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
  //  HttpHeaderNames.UserAgent -> "${UAStrings}",
    HttpHeaderNames.AcceptEncoding -> "gzip, deflate, sdch",
    HttpHeaderNames.AcceptLanguage -> "en-GB,en;q=0.8,en-US;q=0.6,it;q=0.4,es;q=0.2",
    HttpHeaderNames.CacheControl -> "max-age=0"
  )

  def getHomePage =
  exec(http("Navigate to home")
        .get("https://ryuest.github.io/mine-portfolio-final/")
        //.headers(headersAll)
        .check(status.is(200))
        // .check(regex("<title>TBA</title>").count.is(1))
      )
      .pause(1,5)

}
