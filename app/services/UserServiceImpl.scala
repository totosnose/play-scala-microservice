package services

import javax.inject.{Inject, Singleton}
import models.User
import repositories.dao.UserDAO

@Singleton
class UserServiceImpl @Inject()(userDAO: UserDAO) extends UserService {
  def create(userId: Int, studioId: Int): User = {
    val user = User(userId, studioId)

    userDAO.add(user)

    user
  }

  override def find(userId: Int, studioId: Int): Option[User] = {
    val user = User(userId, studioId)

    Some(user).filter(userDAO.exists)
  }
}
