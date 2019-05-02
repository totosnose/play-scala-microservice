package services

import javax.inject.{Inject, Singleton}
import models.User
import reactivemongo.api.commands.WriteResult
import repositories.UserRepository

import scala.concurrent.Future

@Singleton
class UserServiceImpl @Inject()(userRepo: UserRepository) extends UserService {

  override def getAll: Future[Seq[User]] = userRepo.findAll(100)

  override def getOne(id: String): Future[Option[User]] = userRepo.findOne(id)

  override def add(user: User): Future[WriteResult] = userRepo.insert(user)
}
