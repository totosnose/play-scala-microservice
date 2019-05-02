package services

import com.google.inject.ImplementedBy
import models.User
import reactivemongo.bson.BSONObjectID

import scala.concurrent.Future

@ImplementedBy(classOf[UserServiceImpl])
trait UserService {
  def getAll: Seq[User]
  def getOne(id: String): Future[Option[User]]
}
