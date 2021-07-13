package me.liuchenyu.router

import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, pathEndOrSingleSlash, pathPrefix, post}
import akka.http.scaladsl.server.Route
import me.liuchenyu.persistence.components.{ActionComponent, UserComponent}
import me.liuchenyu.persistence.{Action, JsonParser, User}

import scala.concurrent.ExecutionContext


/**
 * @author liuchenyu
 * @date 2021/7/12
 * @description ${DESCRIPTION}
 */
class AppRoutes(userRepo: UserComponent#UserRepository,
                 actionRepo:ActionComponent#ActionRepository)
                (implicit ex: ExecutionContext)
  extends JsonParser {

  val routes: Route = {
    concat(
    pathPrefix("users") {
    pathEndOrSingleSlash{
      concat(
        get {
          complete(userRepo.all)
        },
        post {
          entity(as[User]) { u =>
            complete(userRepo.insert(u))
          }
        }
      )
    }},
      pathPrefix("actions"){
        pathEndOrSingleSlash{
          get{
            complete(actionRepo.all)
          }
          post{
            entity(as[Action]){a=>
              complete(actionRepo.insert(a))
            }
          }
        }
      }
    )
  }
}
