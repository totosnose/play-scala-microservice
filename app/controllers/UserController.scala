package controllers

import io.swagger.annotations._
import javax.inject._
import models.JsonFormats._
import models.User
import play.api.libs.json.Json
import play.api.mvc._
import reactivemongo.bson.BSONObjectID
import repositories.UserRepository
import services.UserService

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
@Api(value = "/users")
class UserController @Inject()(userService: UserService, userRepo: UserRepository, cc: ControllerComponents) extends AbstractController(cc) {

//  @ApiOperation(value = "Find all Users", response = classOf[User], responseContainer = "List")
//  def getAllUsers = Action.async {
//    userService.getAll.map { users =>
//      Ok(Json.toJson(users))
//    }
//  }

  @ApiOperation(value = "Get a Todo", response = classOf[User])
  @ApiResponses(Array(new ApiResponse(code = 404, message = "Todo not found")))
  def getUser(@ApiParam(value = "The id of the Todo to fetch") id: BSONObjectID) = Action.async {
    userRepo.findOne(id).map { maybeUser =>
      maybeUser.map { user =>
        Ok(Json.toJson(user))
      }.getOrElse(NotFound)
    }
  }
}
