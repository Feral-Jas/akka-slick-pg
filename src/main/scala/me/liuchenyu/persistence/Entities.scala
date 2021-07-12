package me.liuchenyu.persistence

import java.sql.Timestamp
import java.util.UUID

/**
 * @author liuchenyu
 * @date 2021/7/12
 * @description ${DESCRIPTION}
 */

trait Entity {
  def id: UUID

  def created: Timestamp

  def modified: Option[Timestamp]
}

case class User(id: UUID,
                name: String,
                gender: String,
                age: Int,
                created: Timestamp,
                modified: Option[Timestamp]
               ) extends Entity

case class Action(id: UUID,
                  name: String,
                  description: String,
                  created: Timestamp,
                  modified: Option[Timestamp]) extends Entity

case class Record(user: User,action: Action)

