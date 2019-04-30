package repositories.dao

import javax.inject.{Inject, Singleton}
import models.User

@Singleton
class UserDAOImpl @Inject()() extends UserDAO{

//  val db = dbApi.database("default")

  def add(user: User): Nothing = ??? //{
//    db.withConnection { implicit c =>
//      SQL(
//        """
//          | INSERT IGNORE INTO `terminal` (`userId`, `studioId`)
//          | VALUES
//          |   ({userId}, {studioId});
//        """.stripMargin).on(
//          "userId" -> terminal.userId,
//          "studioId" -> terminal.studioId
//        ).executeInsert()
//    }
//  }

  def exists(user: User): Boolean = false
}



