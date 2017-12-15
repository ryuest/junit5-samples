package com.web.helpers

import com.typesafe.config.ConfigFactory
import com.web.simulations.SimulationSettingsTrait

/**
  * Created by jboiko on 15/12/2017.
  */
object Common extends SimulationSettingsTrait{

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

  def getConfigFromFile(configFileName: String, configName: String) : String =
  {
    val conf = ConfigFactory.load(configFileName)
    conf.getString("properties." + environmentUnderTest + "."+configName)
  }
}
