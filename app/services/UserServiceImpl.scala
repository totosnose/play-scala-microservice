package services

import javax.inject.{Inject, Singleton}
import models.User
import reactivemongo.bson.BSONObjectID
import repositories.UserRepository

@Singleton
class UserServiceImpl @Inject()(userRepo: UserRepository) extends UserService {

  override def getAll: Seq[User] = ???
  override def getOne(id: BSONObjectID): Option[User] = ???
}
