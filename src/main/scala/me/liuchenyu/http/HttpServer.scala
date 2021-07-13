package me.liuchenyu.http

/**
  * @author liuchenyu
  * @date 2021/7/12
  * @description ${DESCRIPTION}
  */
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import me.liuchenyu.config.AppConfig

import scala.concurrent.ExecutionContext

trait HttpServer extends AppConfig {
  implicit def system: ActorSystem

  implicit def materializer: Materializer

  implicit def ec: ExecutionContext

  def routes: Route

  Http().newServerAt(httpHost, httpPort).bind(routes)
}
