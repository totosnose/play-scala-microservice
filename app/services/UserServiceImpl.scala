package services

import javax.inject.{Inject, Singleton}
import models.User
import reactivemongo.bson.BSONObjectID
import repositories.UserRepository

import scala.concurrent.Future

@Singleton
class UserServiceImpl @Inject()(userRepo: UserRepository) extends UserService {

  override def getAll: Seq[User] = ???
  override def getOne(id: String): Future[Option[User]] = userRepo.findOne(id)
}
