package me.liuchenyu.persistence.components

import me.liuchenyu.persistence.{Action, DbConfig, RepoDefinition, User}

import java.sql.Timestamp
import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

/**
 * @author liuchenyu
 * @date 2021/7/12
 * @description ${DESCRIPTION}
 */
trait ActionComponent extends RepoDefinition {
  this: DbConfig =>

  import driver.api._

  class ActionTable(tag: Tag) extends BaseTable[Action](tag, "action") {
    val name = column[String]("name")
    val description = column[String]("description")

    override def * =
      (id, name, description, created, modified.?) <> (Action.tupled, Action.unapply)
  }

  class ActionRepository(implicit ex: ExecutionContext) extends BaseRepo[Action, ActionTable] {

    override val table = TableQuery[ActionTable]

    def actionsInRange(from: Timestamp, to: Timestamp): Future[Seq[Action]] = {
      db.run {
        table filter (_.created between(from, to)) result
      }
    }

  }

}
