package com.williamhill.whnft.sports.helpers

import com.typesafe.config.ConfigFactory
import com.williamhill.whnft.sports.simulations.SimulationSettingsTrait
import io.gatling.core.structure
import io.gatling.core.session.Session
import io.gatling.http.cookie.CookieJar
import org.asynchttpclient.uri.Uri

object Common extends SimulationSettingsTrait {

  def isEmpty(x: String) : Boolean = x != null && x.trim.nonEmpty

  def getConfigFromFile(configFileName: String, configName: String) : String =
  {
    val conf = ConfigFactory.load(configFileName)
    conf.getString("properties." + environmentUnderTest + "."+configName)
  }

  def getCookieValue(cookieName: String, cookieHost: String, session: Session) =
    session("gatling.http.cookies").as[CookieJar].get(Uri.create(cookieHost)).find(_.getName == cookieName).get.getValue

  def prop(key : String) : Option[String] = {
    if (sys.env.contains(key))
      Some(sys.env(key))
    else None
  }

  def prop[T](key : String, defaultValue:  T) : T = {
    if (sys.env.contains(key)) {
      defaultValue match {
        case defaultValue: Int => sys.env(key).toInt.asInstanceOf[T]
        case defaultValue: String => sys.env(key).toString.asInstanceOf[T]
        case defaultValue: Boolean => sys.env(key).toBoolean.asInstanceOf[T]
      }
    }
    else
      defaultValue
  }

  def userLoggedIn (session: Session) : Boolean =
    (session.contains("custLogin")  && !session("custLogin").as[String].isEmpty
      && session.contains("sslLogin") && !session("sslLogin").as[String].isEmpty
      && session.contains("custAuth") && !session("custAuth").as[String].isEmpty)

}