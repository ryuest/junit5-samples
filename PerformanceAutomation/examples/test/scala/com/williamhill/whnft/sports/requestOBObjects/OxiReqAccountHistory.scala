package com.williamhill.whnft.sports


import com.williamhill.whnft.sports.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by jboiko on 28/11/2017.
  */
object OxiReqAccountHistory {

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Cache-Control" -> "no-cache")


  def reqAccountValidate() = {

    exec(http("reqAccountValidate")
      .post("http://backoffice.williamhill.remote/oxixmlserver_gib")
      .headers(headers_0)
      .body(StringBody(
        """<!DOCTYPE oxip SYSTEM "reqAccountValidate.dtd">
                          <oxip version="6.0">
     <request>
     <reqClientAuth returnToken="N" >
     <user>Administrator</user>
     <password>1ncharge</password>
     </reqClientAuth>
     <reqAccountValidate1 returnBalance="N" returnRefererDetails="Y" returnPreferences="N" returnAlias="N" returnLastLogin="N" updateLastLogin="N">
     <userName mandatory="N">${Username}</userName>
     <password>${Password}</password>
     <channel>I</channel>
     <systemName>Sportsbook</systemName>
     </reqAccountValidate1>
     </request>
        </oxip>"""))
      .check(status.is(200))
      .check(regex("<token>(.*)</token>").saveAs("token"))

    )
      .pause(1, 5)
  }

  def reqAccountHistory() = {

    exec(http("reqAccountHistory")
      .post("http://backoffice.williamhill.remote/oxixmlserver_gib")
      .headers(headers_0)
      .body(StringBody(
        """<!DOCTYPE oxip SYSTEM "reqAccountHistory.dtd">
                          <oxip version="7.0">
                            <request>
                                  <reqClientAuth>
                                      <user>Administrator</user>
                                      <password>1ncharge</password>
                                  </reqClientAuth>
                                  <reqAccountHistory returnAvailableFilters="N">
                                      <token>${token}</token>
                                      <filter type="fromDate">2018-01-01 10:00:00</filter>
                                      <filter type="toDate">2018-01-28 18:59:59</filter>
                                      <group>BET</group>
                                      <paging blockSize="20"/>
                                      <filter type="date_field">settled_at</filter>
                                     <filter type="settled">Y</filter>
                                    <detailLevel>DETAILED</detailLevel>
                                  </reqAccountHistory>
                              </request>
                          </oxip>"""))
      .check(status.is(200))
    )
      .pause(1, 5)
  }

}
