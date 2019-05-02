package services

import models.User
import reactivemongo.api.commands.WriteResult

import scala.concurrent.Future

trait UserService {
  def getAll: Future[Seq[User]]
  def getOne(id: String): Future[Option[User]]
  def add(user: User): Future[WriteResult]
}
