package services

import models.User
import reactivemongo.api.commands.WriteResult

import scala.concurrent.Future

trait UserService {

  def getAll: Future[Seq[User]]

  def getOne(id: String): Future[Option[User]]

  def create(user: User): Future[WriteResult]

  def update(id: String, user: User): Future[Option[User]]

  def delete(id: String): Future[Option[User]]
}
