package com.packt.chapter9

import scala.collection.mutable
import akka.http.scaladsl.server.HttpApp
import akka.http.scaladsl.settings.ServerSettings
import com.typesafe.config.ConfigFactory

class TemperatureInMemoryStorageRestApi(
    cache: mutable.Map[String, TemperatureMeasurement])
    extends HttpApp
    with InMemoryStorageRestApi[String, TemperatureMeasurement]
    with GetRequestHandler
    with PostRequestsHandler
    with PutRequestHandler
    with DeleteRequestsHandler {

  override implicit val fixedPath = "temperature"
  override val route = composedRoute(cache)
}

object TemperatureInMemoryStorageRestApiApplication extends App {
  val cache = mutable.Map.empty[String, TemperatureMeasurement]
  new TemperatureInMemoryStorageRestApi(cache).startServer("0.0.0.0", 8088, ServerSettings(ConfigFactory.load))
}
