package me.liuchenyu.persistence

import java.sql.Timestamp
import java.util.UUID

/**
 * @author liuchenyu
 * @date 2021/7/12
 * @description ${DESCRIPTION}
 */

trait Entity {
  def id: String

  def created: Timestamp

  def modified: Option[Timestamp]
}

case class User(id: String,
                name: String,
                gender: String,
                age: Int,
                created: Timestamp,
                modified: Option[Timestamp]
               ) extends Entity

case class Action(id: String,
                  name: String,
                  description: String,
                  created: Timestamp,
                  modified: Option[Timestamp]) extends Entity

case class Record(user: User,action: Action)

