package me.liuchenyu.persistence

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, JsNumber, JsString, JsValue, JsonFormat, RootJsonFormat}

import java.sql.Timestamp
import java.time.Instant
import java.util.UUID

/**
 * @author liuchenyu
 * @date 2021/7/12
 * @description ${DESCRIPTION}
 */
trait BaseJsonProtocol extends DefaultJsonProtocol {
  implicit val timestampFormat: JsonFormat[Timestamp] = new JsonFormat[Timestamp] {
    override def write(obj: Timestamp): JsValue = JsNumber(obj.getTime)

    override def read(json: JsValue): Timestamp = json match {
      case JsNumber(x) => Timestamp.from(Instant.ofEpochMilli(x.toLong))
      case _ =>
        throw new IllegalArgumentException(
          s"Can not parse json value [$json] to a timestamp object")
    }
  }

  implicit val uuidJsonFormat: JsonFormat[UUID] = new JsonFormat[UUID] {
    override def write(x: UUID): JsValue = JsString(x.toString)

    override def read(value: JsValue): UUID = value match {
      case JsString(x) => UUID.fromString(x)
      case x =>
        throw new IllegalArgumentException("Expected UUID as JsString, but got " + x.getClass)
    }
  }
}

trait JsonParser extends SprayJsonSupport with BaseJsonProtocol {
  implicit val userFormat: RootJsonFormat[User] = jsonFormat6(User)
  implicit val actionFormat:RootJsonFormat[Action] = jsonFormat5(Action)
}
