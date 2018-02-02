package com.williamhill.whnft.sports.requestTrafagarObjects

import com.williamhill.whnft.sports.helpers.Common
import com.williamhill.whnft.sports.helpers.Common._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.matching.Regex

object Deposit {
  
   val headersDeposits = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
     "User-Agent" -> "${UAStrings}")
  
  val baseURL : String =Common.getConfigFromFile("environment.conf", "baseURL")
  val securebaseURL : String =Common.getConfigFromFile("environment.conf", "securebaseURL")
  val myaccountURL : String =Common.getConfigFromFile("environment.conf", "myaccountURL")
  var cpm_id = "";
  var userName = "";
  
  def deposit(clickDepositUrl:String, source:String, doDepositUrl:String, numberOfDeposits: Int) =
    doIf(session => userLoggedIn(session)) {
      repeat(numberOfDeposits) {
        exec(http("Click Deposit - ${platform}")
        .get(securebaseURL + "/" + clickDepositUrl + "/en-gb/payment/deposit/deposit.html")
        .queryParam("hide_header", "1")
        .queryParam("source", "DP")
        .headers(headersDeposits)
        // TODO: Add status code assertion
        .check(regex("""<input type="hidden" name="unique_id" value="([^"]*)" />""").saveAs("unique_id"))
        .check(regex("""card_methods_obj(.*?)""").saveAs("select_card_no"))
        .check(headerRegex("Set-Cookie", "CSRF_COOKIE=(.*); path=/").saveAs("csrf_token"))
        .check(regex("""cc_hldr_name(.*?);""").saveAs("hldr_name"))
        .check(bodyString.saveAs("responseBody"))
        )
        .pause(1, 5)
        .exec(session => {
          val pattern1 = new Regex("""(card_methods_obj)[ \[](.*])[ \[](.*)[ =](.*)""")
          val pattern2 = new Regex("""(cc_hldr_name)(.*)""")
          cpm_id = pattern1.findAllIn(session("responseBody").as[String]).matchData.next().group(2).replaceAll("\\D+", "")
          userName = pattern2.findAllIn(session("responseBody").as[String]).matchData.next().group(2).replaceAll("    = ", "!").replaceFirst(" ", "ZZZ").replaceAll("\\W", "").replaceAll("ZZZ", " ")
          session.remove("responseBody")
          session
        })
        .exec(addCookie(Cookie("CSRF_COOKIE", "${csrf_token}")))
        .exec(http("Confirm Deposit - ${platform}")
        .post(securebaseURL + "/" + doDepositUrl + "/en-gb/payment/deposit/confirm.html")
        .formParam("pay_mthd", "CC")
        .formParam("cpm_id", session => cpm_id)
        .formParam("unique_id", "${unique_id}")
        .formParam("csrf_token", "${csrf_token}")
        .formParam("txn_type", "DEP")
        .formParam("orig_type", "DEP")
        .formParam("pmt_min_amt", "")
        .formParam("pmt_max_amt", "")
        .formParam("popup_return_url", "")
        .formParam("game_code", "")
        .formParam("scheme", "MC")
        .formParam("prev_card_bin", "")
        .formParam("modal_reg", "1")
        .formParam("source", "DP")
        .formParam("pcv", "")
        .formParam("card_type", "CDT")
        .formParam("select_card_no", session => cpm_id)
        .formParam("card_no", "")
        .formParam("hldr_name", session => userName)
        .formParam("start_date_disp", "0")
        .formParam("issue_num", "")
        .formParam("issue_num_disp", "0")
        .formParam("cvv2", "123")
        .formParam("acceptTerms", "1")
        .formParam("txnAmount", "10.00")
        .formParam("expiry_month", "01")
        .formParam("expiry_year", "18")
        .formParam("start_month", "-1")
        .formParam("start_year", "-1")
        .headers(headersDeposits)
        //    .check(regex("""<input type="hidden" name="PaReq" value="(.*)" />""").saveAs("pareq"))
        //    .check(regex("""<input type="hidden" name="MD" value="(.*)" />""").saveAs("md"))
        .check(status.is(200)))
        //TODO: This assertion fail for mobile and native! Add this as conditional and assert in another way
        //     .check(regex("You have successfully added (.*) to your Main account balance.").count.is(1)))
        .pause(1, 5)
      }
    }
   
//  def depositAfterCall1 =
//    exec(http("Post deposit call1 - ${platform}")
//    .post(securebaseURL + "/bet/en-gb")
//    .queryParam("modal_reg", "1")
//    .queryParam("action", "DoPmtRedirectEnd")
//    .queryParam("body_only", "1")
//    .queryParam("card_type", "CDP")
//    .queryParam("pay_mthd", "CC")
//    .queryParam("redirect_type", "3DS")
//    .queryParam("scheme", "VC")
//    .queryParam("source", "DP")
//    .formParam("csrf", "null")
//    .formParam("PaRes", "${pareq}")
//    .formParam("MD", "${md}")
//    .headers(headersDeposits)
//    .check(regex("""<input type="hidden" name="PaRes" value="(.*)" />""").saveAs("pareq"))
//    .check(regex("""<input type="hidden" name="MD" value="(.*)" />""").saveAs("md")))
//    .pause(2)
//
//   def depositAfterCall2 =
//    exec(http("Post deposit Call2 - ${platform}")
//    .post(securebaseURL + "/bet/en-gb")
//    .formParam("action", "DoPmtRedirectEnd")
//    .formParam("pay_mthd", "CC")
//    .formParam("redirect_type", "3DS")
//    .formParam("card_type", "CDT")
//    .formParam("PaRes", "${pareq}")
//    .formParam("MD", "${md}")
//    .formParam("source", "DP")
//    .formParam("scheme", "VC")
//    .formParam("modal_reg", "1")
//    .formParam("blockbuster_id", "-1")
//    .check(bodyString.saveAs("responseBody"))
//    .headers(headersDeposits))
//    .pause(2)
//    .exec(session => {
//      val pattern1 = new Regex("""You have successfully added(.*)""")
//      val receipt = pattern1.findAllIn(session("responseBody").as[String]).matchData.next().group(1)
//      println("You have successfully added --->" + receipt)
//      session.remove("responseBody")
//      session
//    })
}