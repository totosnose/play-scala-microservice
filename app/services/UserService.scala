package services

import models.User

trait UserService {
  def create(userId: Int, studioId: Int): User
  def find(userId: Int, studioId: Int): Option[User]
}
