package models

import play.api.libs.json.{JsObject, Json, OWrites}
import reactivemongo.bson.BSONObjectID
//import play.api.libs.functional.syntax._
//import play.api.libs.json.{JsPath, Writes}

trait BaseEntity {
  def id: Option[String]
}

case class User(id: Option[String], name: String) extends BaseEntity

object JsonFormats{
  import play.api.libs.json.{OFormat, Json}

  implicit val userFormat: OFormat[User] = Json.format[User]
}

//object User {
//
//  import play.api.libs.json._
//
////  implicit val favouriteStudioWrites: Writes[User] = (
////    (JsPath \ "id").write[Long] and
////      (JsPath \ "title").write[String]
////    )(unlift(User.unapply))
//
//  implicit object ArticleWrites extends OWrites[User] {
//    def writes(user: User): JsObject = Json.obj(
//      "_id" -> user.id,
//      "title" -> user.name)
//  }
//
//  implicit object ArticleReads extends Reads[User] {
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
