package org.crypto

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.forkjoin.ThreadLocalRandom

class CryptoSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://localhost:7779")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.8,uk;q=0.6,ru;q=0.4")
    .contentTypeHeader("application/json")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")

  val uri1 = "http://localhost:7779/crypto-service"

  object AddKey {
    val run =
      // set the random ID to be used during a single iteration
      exec(session => session.set("id", ThreadLocalRandom.current.nextInt(9999).toString))
      .exec(http("add key")
      .post("/crypto-service/account-key/${id}")
      .check(status.is(201)))
  }

  object EncryptSecKey {
    val run = exec(http("encrypt sec key")
      .post("/crypto-service/encrypt/${id}")
      .body(StringBody(
        """
					{"key1": "value1", "key2": "value2"}
				""")).asJSON
      .check(status.is(200))
      // save encrypted values - these will be used in the "/re-encrypt"  
      .check(jsonPath("$.key1").saveAs("value1"))
      .check(jsonPath("$.key2").saveAs("value2"))
    )
  }

  object UploadKey {
    val run = exec(http("upload public key")
      .post("/crypto-service/public-key/${id}/${id}")
      .body(
        // the key to be uploaded is taken from the "feed" csv file
        StringBody("${Key}")))
  }

  object EncryptPubKey {
    val run = exec(http("encrypt public key")
      .post("/crypto-service/encrypt/${id}/${id}")
      .body(StringBody(
        """
					{"key1": "value1", "key2": "value2"}
				""")).asJSON)
  }

  object Rencrypt {
    val run = exec(http("reencrypt sec->public")
      .post("/crypto-service/re-encrypt/${id}/${id}")
      .body(StringBody(
        // value1 and value2 were obtained from "/encrypt" response body
        """
          {"key1": "${value1}", "key2": "${value2}"}
        """)).asJSON)
  }

  // pre-defined keys are taken from the file, circularly
  val feeder = csv("keys.csv").circular

  val scn =
    scenario("RecordedSimulationCrypto")
      .feed(feeder)
      // operations are executed in the defined order
      .exec(AddKey.run, EncryptSecKey.run, UploadKey.run, EncryptPubKey.run, Rencrypt.run)

  // single user
  // setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  // raise the number of users during the period of time
  setUp(scn.inject(rampUsersPerSec(5) to (10) during (30 seconds))).protocols(httpProtocol)
}