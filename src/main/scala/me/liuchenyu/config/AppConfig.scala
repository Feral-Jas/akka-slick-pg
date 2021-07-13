package me.liuchenyu.config

import com.typesafe.config.ConfigFactory

/**
  * @author liuchenyu
  * @date 2021/7/12
  * @description ${DESCRIPTION}
  */
trait AppConfig {
  private lazy val config         = ConfigFactory.load()
  private lazy val httpConfig     = config.getConfig("http")
  private lazy val postgresConfig = config.getConfig("postgres")

  lazy val httpHost: String = httpConfig.getString("interface")
  lazy val httpPort: Int    = httpConfig.getInt("port")
}
