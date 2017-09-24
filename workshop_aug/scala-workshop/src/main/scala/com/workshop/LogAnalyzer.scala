package com.workshop

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

import scala.io.Source

// define a case class that represents a log line
case class Record(
                   ip: String,
//                   ph1: String,
//                   ph2: String,
                   time: DateTime,
//                   req: String,
                   statusCode: String,
                   size: String,
                   url: String
//                   userAgent: String
                 )

object LogAnalyzer extends App {
  //Read from the log file
  val log = Source.fromInputStream(getClass.getResourceAsStream("/access_log")).getLines()

  // map each line to a case class that represents it
  // Split? Regex?
  // How to parse the datetime? DateTime.parse(dateTimeString, DateTimeFormat.forPattern("dd/MMM/yyyy:HH:mm:ssZ"))
  val logLines = log.map(_.split(" ")).map(x =>
    {
      val formattedTime = x(3).drop(1) + x(4).dropRight(1)
      val time = DateTime.parse(formattedTime, DateTimeFormat.forPattern("dd/MMM/yyyy:HH:mm:ssZ"))

      Record(x(0), time,  x(8), x(9), x(10))
    }).toList

// 146.182.198.198 - - [17/Aug/2017:16:01:55 +0300] "DELETE /list HTTP/1.0" 200 4984 "http://lam.com/tag/tag/privacy.html" "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_7_6 rv:6.0; en-US) AppleWebKit/534.32.5 (KHTML, like Gecko) Version/5.1 Safari/534.32.5"





    // how many web requests?
  // how many of each status code?
  // how many distinct IPs? Wait, did you use String for IP? Maybe use a case class that will validate?
  //   Maybe some lines are bad? We want to throw them away. (When parsing return Try/Option, use flatMap instead of map on the log lines)
  // Largest response size? What was the request for it? Average response size? Sum of all responses?
  // which url have most hits? How many hits?

  println(s"Requests: ${logLines.size}")
  println(s"Statuses: ${logLines.groupBy(_.statusCode).mapValues(_.size)}")
  println(s"Unique IPs: ${logLines.map(_.ip).distinct.size}")
  println(s"Largest response: ${logLines.map(_.size).max}")
}