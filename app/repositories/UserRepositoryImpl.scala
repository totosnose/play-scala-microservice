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

  def findAll(limit: Int = 100): Future[Seq[User]] =
    usersCollection.flatMap(_.find(
      selector = Json.obj(/* Using Play JSON */),
      projection = Option.empty[JsObject])
      .cursor[User](ReadPreference.primary)
      .collect[Seq](limit, Cursor.FailOnError[Seq[User]]())
    )

  def findOne(id: String): Future[Option[User]] =
    usersCollection.flatMap(_.find(
      selector = BSONDocument("_id" -> id),
      projection = Option.empty[BSONDocument])
      .one[User])

  def insert(user: User): Future[WriteResult] =
    usersCollection.flatMap(_.insert(false).one(user))
}



