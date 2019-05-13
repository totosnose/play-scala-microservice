package repositories

import javax.inject.{Inject, Singleton}
import models.User
import play.api.libs.json.{JsObject, Json}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.bson.BSONDocument
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class UserRepositoryImpl @Inject()(implicit ec: ExecutionContext, reactiveMongoApi: ReactiveMongoApi) extends UserRepository {

  import models.JsonFormats._

  def usersCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection("users"))

  override def findAll(limit: Int = 100): Future[Seq[User]] =
    usersCollection.flatMap(_.find(
      selector = Json.obj(/* Using Play JSON */),
      projection = Option.empty[JsObject])
      .cursor[User](ReadPreference.primary)
      .collect[Seq](limit, Cursor.FailOnError[Seq[User]]())
    )

  override def findOne(id: String): Future[Option[User]] =
    usersCollection.flatMap(_.find(
      selector = BSONDocument("_id" -> id),
      projection = Option.empty[BSONDocument])
      .one[User])

  override def insert(user: User): Future[WriteResult] =
    usersCollection.flatMap(_.insert(false).one(user))

  override def update(id: String, user: User): Future[Option[User]] = {
    val selector = BSONDocument("_id" -> id)
    val updateModifier = BSONDocument(
      f"$$set" -> BSONDocument(
        "name" -> user.name,
        "confirmed" -> user.confirmed)
    )

    usersCollection.flatMap(
      _.findAndUpdate(selector, updateModifier, fetchNewObject = true)
        .map(_.result[User])
    )
  }

  override def delete(id: String): Future[Option[User]] = {
    val selector = BSONDocument("_id" -> id)
    usersCollection.flatMap(_.findAndRemove(selector).map(_.result[User]))
  }
}



