package models

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Writes}

object User {
  implicit val favouriteStudioWrites: Writes[User] = (
    (JsPath \ "userId").write[Int] and
      (JsPath \ "studioId").write[Int]
    )(unlift(User.unapply))
}

case class User(userId: Int, studioId: Int)
