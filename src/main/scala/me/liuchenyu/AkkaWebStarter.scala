package me.liuchenyu

import me.liuchenyu.http.{HttpServer, HttpService}
import me.liuchenyu.persistence.PG

/**
  * @author liuchenyu
  * @date 2021/7/13
  * @description ${DESCRIPTION}
  */
object AkkaWebStarter extends App with HttpService with HttpServer with PG
