package repositories

import com.google.inject.ImplementedBy
import models.User
import reactivemongo.bson.BSONObjectID

import scala.concurrent.Future

@ImplementedBy(classOf[UserRepositoryImpl])
trait UserRepository {
  def findAll(limit: Int): Future[Seq[User]]
  def findOne(id: BSONObjectID): Future[Option[User]]
}
