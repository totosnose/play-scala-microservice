package models

//todo used
trait BaseEntity {
  def id: Option[String]
}

case class User(id: Option[String], name: String, confirmed: Option[Boolean]) extends BaseEntity

object JsonFormats{
  import play.api.libs.json.{OFormat, Json}

  implicit val userFormat: OFormat[User] = Json.format[User]
}

/** OR you could do something like that: **/

//object User {
//
//  import play.api.libs.json._
//
//  implicit object UserWrites extends OWrites[User] {
//    def writes(user: User): JsObject = Json.obj(
//      "_id" -> user.id,
//      "title" -> user.name)
//  }
//
//  implicit object UserReads extends Reads[User] {
//    def reads(json: JsValue): JsResult[User] = json match {
//      case obj: JsObject => try {
//        val id = (obj \ "_id").asOpt[String]
//        val name = (obj \ "name").as[String]
//
//        JsSuccess(User(id, name))
//
//      } catch {
//        case cause: Throwable => JsError(cause.getMessage)
//      }
//
//      case _ => JsError("expected.jsobject")
//    }
//  }
//}

/** OR - that: **/

//object User {
//
//  import play.api.libs.functional.syntax._
//  import play.api.libs.json._
//
//  implicit val userWrites: Writes[User] = (
//    (JsPath \ "id").write[Long] and
//      (JsPath \ "title").write[String]
//    ) (unlift(User.unapply))
//}
