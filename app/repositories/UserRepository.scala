package repositories

import models.User
import reactivemongo.api.commands.WriteResult

import scala.concurrent.Future

trait UserRepository {
  def findAll(limit: Int): Future[Seq[User]]
  def findOne(id: String): Future[Option[User]]
  def insert(user: User): Future[WriteResult]
}
