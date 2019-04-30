package repositories.dao

import models.User

trait UserDAO {
  def add(terminal: User): Nothing
  def exists(terminal: User): Boolean
}
