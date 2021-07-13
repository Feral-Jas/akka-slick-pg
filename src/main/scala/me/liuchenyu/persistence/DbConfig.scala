package me.liuchenyu.persistence

import slick.jdbc.{JdbcProfile, PostgresProfile}
import slick.lifted

import java.sql.Timestamp
import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag

/**
 * @author liuchenyu
 * @date 2021/7/12
 * @description ${DESCRIPTION}
 */
trait DbConfig {
  val driver: JdbcProfile

  import driver.api._

  lazy val db: Database = Database.forConfig("postgres")
}

trait PG extends DbConfig {
  override val driver: PostgresProfile = PostgresProfile
}

trait TableDefinition {
  this: DbConfig =>

  import driver.api._

  /**
   * The [[BaseTable]] describes the basic [[Entities]]
   */
  abstract class BaseTable[E <: Entity : ClassTag](tag: Tag,
                                                     tableName: String,
                                                     schemaName: Option[String] = None)
    extends Table[E](tag, schemaName, tableName) {

    val id = column[String]("id", O.PrimaryKey,O.Unique)
    val created = column[Timestamp]("created")
    val modified = column[Timestamp]("modified")
  }

}

sealed trait Repository[E <: Entity] {
  def all: Future[Seq[E]]

  def byId(id: String): Future[Option[E]]

  def insert(entity: E): Future[E]

  def update(entity: E): Future[Int]

  def delete(id: String): Future[Boolean]
}

trait RepoDefinition extends TableDefinition {
  this: DbConfig =>

  import driver.api._

  abstract class BaseRepo[E <: Entity, T <: BaseTable[E]](implicit ex: ExecutionContext)
    extends Repository[E] {

    val table: lifted.TableQuery[T]

    override def all: Future[Seq[E]] = db.run {
      table.to[Seq].result
    }

    override def byId(id: String): Future[Option[E]] = db.run {
      table.filter(_.id === id).result.headOption
    }

    override def insert(entity: E): Future[E] = db.run {
      table returning table += entity
    }

    override def update(entity: E): Future[Int] = db.run {
      table.insertOrUpdate(entity)
    }

    override def delete(id: String): Future[Boolean] = db.run {
      table.filter(_.id === id).delete.map(_ > 0)
    }
  }

}