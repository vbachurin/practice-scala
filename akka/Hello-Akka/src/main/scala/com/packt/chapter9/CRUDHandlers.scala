package com.packt.chapter9

import scala.collection.mutable
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._

trait GetRequestHandler {
  def handleGet(cache: mutable.Map[String, TemperatureMeasurement]) =
    pathEndOrSingleSlash {
      complete {
        cache.map(keyValue => s"${keyValue._2.location}, " +
          s"${keyValue._2.measurement}")
          .mkString("n")
      }
    } ~
  path(Segment) {id =>
    complete {
      cache.get(id) match {
        case Some(TemperatureMeasurement(location, measurement)) => s"Temperature for $location is $measurement"
        case None => StatusCodes.NotFound -> s"No temperature measurement for $id"
      }
    }
  }
}

trait PostRequestsHandler {
  def handlePost(cache: mutable.Map[String, TemperatureMeasurement]) =
    entity(as[String]) { content =>
      complete {
        content.split(",") match {
          case Array(location, _) if cache.contains(location) =>
            StatusCodes.Conflict -> s"$location has a value already. To update it please use PUT method."
          case Array(location, measurement) =>
            cache.put(location, TemperatureMeasurement(location, measurement.toDouble))
            s"Measurement inserted for $location"
        }
      }
    }
}

trait PutRequestHandler {
  def handlePut(cache: mutable.Map[String, TemperatureMeasurement]) =
    path(Segment) {id =>
      entity(as[String]) { updatedMeasurement =>
        complete {
          cache.get(id) match {
            case Some(TemperatureMeasurement(location, measurement)) =>
              cache.put(id, TemperatureMeasurement(location, updatedMeasurement.toDouble))
              s"New temperature for $location is $updatedMeasurement"
            case None =>
              StatusCodes.NotFound -> s"No temperature measurement for $id"
          }
        }
      }
    }
}

trait DeleteRequestsHandler {
  def handleDelete(cache: mutable.Map[String, TemperatureMeasurement]) =
    path(Segment) { id =>
      complete {
        cache.get(id) match {
          case Some(TemperatureMeasurement(location, measurement)) =>
            cache.remove(id)
            s"Removed temperature for $location"
          case None =>
            StatusCodes.NotFound -> s"No temperature measurement for $id"
        }
      }
    }
}
