package services

import com.google.inject.ImplementedBy
import models.User
import reactivemongo.bson.BSONObjectID

@ImplementedBy(classOf[UserServiceImpl])
trait UserService {
  def getAll: Seq[User]
  def getOne(id: BSONObjectID): Option[User]
}
