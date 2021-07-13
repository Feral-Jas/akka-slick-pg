package me.liuchenyu.persistence.components

import me.liuchenyu.persistence.{DbConfig, RepoDefinition, User}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

/**
  * @author liuchenyu
  * @date 2021/7/12
  * @description
  */
trait UserComponent extends RepoDefinition {
  this: DbConfig =>

  import driver.api._

  class UserTable(tag: Tag) extends BaseTable[User](tag, "user") {
    val name   = column[String]("name")
    val gender = column[String]("gender")
    val age    = column[Int]("age")

    override def * =
      (id, name, gender, age, created, modified.?) <> (User.tupled, User.unapply)
  }

  class UserRepository(implicit ex: ExecutionContext) extends BaseRepo[User, UserTable] {

    override val table = TableQuery[UserTable]

    def userByName(name: String): Future[Seq[User]] = {
      db.run {
        table filter (_.name === name) result
      }
    }

  }

}
