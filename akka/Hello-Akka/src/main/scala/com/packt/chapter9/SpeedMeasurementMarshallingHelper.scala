package com.packt.chapter9

import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}

trait SpeedMeasurementMarshallingHelper {
  val contentType =
    ContentType(MediaTypes.`text/tab-separated-values`, HttpCharsets.`UTF-8`)
  implicit val utf8TextSpaceMarshaller: ToEntityMarshaller[SpeedMeasurement] =
    Marshaller.withFixedContentType(contentType) { speedMeasurement =>
      HttpEntity(contentType, speedMeasurement.marshall)
    }
    implicit val utf8TextSpaceUnmarshaller: FromEntityUnmarshaller[SpeedMeasurement] =
      Unmarshaller.stringUnmarshaller.map(SpeedMeasurement.unmarshall)

}
