package com.williamhill.whnft.sports.helpers

import java.text.SimpleDateFormat
import java.util.{Calendar, Random}

import io.gatling.core.Predef._

/**
  * Created by lorenzo on 06/06/2016.
  */
object RandomGen {
  val alphaNumString = "abcdefghijklmnopqrstuvwxyz1234567890"
  val alphaString = "abcdefghijklmnopqrstuvwxyz"
  val numericString = "1234567890"
  val usernamePrefix = "NFTTEST"

  def makeCustomer(productSource: String, session: Session): Session = {
    val user = makeUsername
    var body = "{\"title\":\"Mr" + "\",\"countryCode\":\"UK" + "\",\"nationality\":\"UK" + "\",\"currencyCode\":\"GBP" + "\",\"productSource\":\"" + productSource + "\",\"depositLimit\":\"10000" + "\",\"depositLimitPeriod\":\"month" + "\",\"dob\":\"" + makeDob + "\",\"province\":\"1" + "\",\"fiscalRegion\":\"01" + "\",\"challenge\":\"Mother's maiden name" + "\",\"firstName\":\"" + RandomGen.getRandomAlphaCharacters(6) + "\",\"lastName\":\"" + RandomGen.getRandomAlphaCharacters(7) + "\",\"email\":\"" + makeEmail + "\",\"errorcode\":\"" + "\",\"mobile\":\"" + makePhoneNumber + "\",\"street1\":\"" + RandomGen.getRandomNumerics(3) + " " + RandomGen.getRandomCharacters(9) + "\",\"street2\":\"" + "" + "\",\"city\":\"" + RandomGen.getRandomCharacters(7) + "\",\"county\":\"" + "" + "\",\"postcode\":\"" + makePostcode + "\",\"username\":\"" + user + "\",\"password\":\"pa55word" + "\",\"response\":\"" + makeDob + "\",\"acceptTerms\":true" + ",\"blackbox\":\"blackbox" + "\",\"state\":\"" + "\",\"promoCode\":\"" + "\",\"contactable\":true, \"language\": \"en\"}"
    session.setAll(
      Map("customerDataFormBody" -> body, "username" -> user)
    )
  }

  def makeIpAddress : String = {
    var r = new Random()
    r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256)
  }

  def makeDob: String = {
    val c = Calendar.getInstance()
    c.add(Calendar.YEAR, -(18 + RandomGen.getRandomInt(50)))
    c.add(Calendar.MONTH, -(RandomGen.getRandomInt(12)))
    c.add(Calendar.DAY_OF_MONTH, -(RandomGen.getRandomInt(28)))
    new SimpleDateFormat("yyyy-MM-dd").format(c.getTime())
  }

  def makeYear: String = {
    var c = Calendar.getInstance()
    c.add(Calendar.YEAR, -(18 + RandomGen.getRandomInt(50)))
    new SimpleDateFormat("yyyy").format(c.getTime())
  }

  def makeMonth: String = {
    var c = Calendar.getInstance()
    c.add(Calendar.MONTH, -(RandomGen.getRandomInt(12)))
    new SimpleDateFormat("MM").format(c.getTime())
  }

  def makeDay: String = {
    var c = Calendar.getInstance()
    c.add(Calendar.DAY_OF_MONTH, -(RandomGen.getRandomInt(28)))
    new SimpleDateFormat("dd").format(c.getTime())
  }

  def makeEmail: String =
    RandomGen.getRandomCharacters(5) + System.currentTimeMillis + "@" + RandomGen.getRandomCharacters(5) + ".com"

  def makeUsername: String =
    usernamePrefix + RandomGen.getRandomAlphaCharacters(8).toUpperCase()

  def makePhoneNumber: String = {
    var number = "07"
    var a = 0
    for (a <- 0 until 9) {
      var thisNumber = RandomGen.getRandomInt(10);
      number = number + Integer.toString(thisNumber);
    }
    return number
  }

  def makePostcode: String = {
    var postcode: String = RandomGen.getRandomAlphaCharacters(2, true).toUpperCase()
    postcode += RandomGen.getRandomInt(100) + " ";
    postcode += RandomGen.getRandomInt(10);
    postcode += RandomGen.getRandomCharacters(2).toUpperCase();
    return postcode;
  }

  def getRandomInt(maxValue : Int) : Int =
    new Random().nextInt(maxValue)

  def getRandomAlphaCharacters(length: Int, postCode: Boolean = false): String = {
    if (postCode) {
      getRandomChars(length, alphaString.replace("u", ""))
    }
    else {
      getRandomChars(length, alphaString)
    }
  }

  def getRandomCharacters(length: Int): String =
    getRandomChars(length, alphaNumString)

  def getRandomNumerics(length: Int): String =
    getRandomChars(length, numericString)

  def getRandomAlphaNumerics(length: Int): String =
    getRandomChars(length, alphaNumString)

  def getRandomChars(length: Int, alphaString: String): String = {
    var builder = new StringBuilder()
    var a = 0
    for (a <- 0 until length) {
      var character = (Math.random() * alphaString.length())
      var zz = character.toInt
      builder.append(alphaString.charAt(zz))
    }
    return builder.toString()
  }
}
