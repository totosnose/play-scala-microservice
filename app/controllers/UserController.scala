package controllers

import io.swagger.annotations._
import javax.inject.{Inject, Singleton}
import models.JsonFormats._
import models.User
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AnyContent, _}
import services.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
@Api(value = "/users")
class UserController @Inject()(userService: UserService, cc: ControllerComponents) extends AbstractController(cc) {

  @ApiOperation(value = "Find all Users", response = classOf[User], responseContainer = "List")
  def getAllUsers: Action[AnyContent] = Action.async {
    userService.getAll.map { users =>
      Ok(Json.toJson(users))
    }
  }

  @ApiOperation(value = "Get a User", response = classOf[User])
  @ApiResponses(Array(new ApiResponse(code = 404, message = "User not found")))
  def getUser(@ApiParam(value = "The id of the User to fetch") id: String): Action[AnyContent] = Action.async {
    userService.getOne(id).map { maybeUser =>
      maybeUser.map { user =>
        Ok(Json.toJson(user))
      }.getOrElse(NotFound)
    }
  }

  @ApiOperation(value = "Add a new User to the list", response = classOf[Void], code = 201)
  @ApiResponses(Array(new ApiResponse(code = 400, message = "Invalid User format")))
  @ApiImplicitParams(Array(new ApiImplicitParam(value = "The User to add, in Json Format",
    required = true, dataType = "models.User", paramType = "body")))
  def createTodo(): Action[JsValue] = Action.async(parse.json) {
    _.body.validate[User].map { user =>
      userService.add(user).map { _ =>
        Created
      }
    }.getOrElse(Future.successful(BadRequest("Invalid Todo format")))
  }
}
