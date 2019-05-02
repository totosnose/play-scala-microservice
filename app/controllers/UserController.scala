package controllers

import io.swagger.annotations._
import javax.inject.{Singleton, Inject}
import models.JsonFormats._
import models.User
import play.api.libs.json.Json
import play.api.mvc._
import reactivemongo.bson.BSONObjectID
import services.UserService

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
@Api(value = "/users")
class UserController @Inject()(userService: UserService, cc: ControllerComponents) extends AbstractController(cc) {

//  @ApiOperation(value = "Find all Users", response = classOf[User], responseContainer = "List")
//  def getAllUsers = Action.async {
//    userService.getAll.map { users =>
//      Ok(Json.toJson(users))
//    }
//  }

  @ApiOperation(value = "Get a User", response = classOf[User])
  @ApiResponses(Array(new ApiResponse(code = 404, message = "User not found")))
  def getUser(@ApiParam(value = "The id of the User to fetch") id: String) = Action.async {
    userService.getOne(id).map { maybeUser =>
      maybeUser.map { user =>
        Ok(Json.toJson(user))
      }.getOrElse(NotFound)
    }
  }
}
