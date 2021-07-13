package me.liuchenyu.http

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.stream.{ActorMaterializer, Materializer}
import me.liuchenyu.persistence.DbConfig
import me.liuchenyu.persistence.components.{ActionComponent, UserComponent}
import me.liuchenyu.router.AppRoutes

import scala.concurrent.ExecutionContext

/**
  * @author liuchenyu
  * @date 2021/7/13
  * @description ${DESCRIPTION}
  */
trait HttpService extends UserComponent with ActionComponent with DbConfig {
  implicit lazy val system: ActorSystem        = ActorSystem()
  implicit lazy val materializer: Materializer = ActorMaterializer()
  implicit lazy val ec: ExecutionContext       = system.dispatcher

  lazy val userRepo: UserRepository     = new UserRepository
  lazy val actionRepo: ActionRepository = new ActionRepository

  lazy val routes: Route = new AppRoutes(userRepo, actionRepo).routes
}
