package com.williamhill.whnft.sports.helpers

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder

/**
  * Created by lorenzo on 08/03/2017.
  */
object UserAgents extends Simulation  {

  sealed trait Platform { def name: String }
  case object NATIVE extends Platform { def name: String = "native"}
  case object MOBILE extends Platform { def name: String = "mobile"}
  case object DESKTOP extends Platform { def name: String = "desktop"}

  val desktopUserAGents = csv("UADesktop.csv", escapeChar = '\\')
  val nativeUserAGents = csv("UANative.csv", escapeChar = '\\')
  val mobileUserAGents = csv("UAMobile.csv", escapeChar = '\\')

  def setAgentFeed (platform: Platform): ChainBuilder =
    platform match {
      case NATIVE => feed(nativeUserAGents.random.circular)
      case MOBILE => feed(mobileUserAGents.random.circular)
      case DESKTOP => feed(desktopUserAGents.random.circular)
    }

}
