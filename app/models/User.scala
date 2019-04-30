package models

import reactivemongo.bson.BSONObjectID

trait Entity {
  def id: Option[BSONObjectID]
}

object JsonFormats{
  import play.api.libs.json._

  implicit val todoFormat: OFormat[User] = Json.format[User]
}

object User {
  implicit val favouriteStudioWrites: Writes[User] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "title").write[String]
    )(unlift(User.unapply))
}

case class User(id: Option[BSONObjectID], title: String) extends Entity
