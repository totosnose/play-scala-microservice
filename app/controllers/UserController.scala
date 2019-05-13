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
  def getUser(@ApiParam(value = "The id of the User to fetch") userId: String): Action[AnyContent] = Action.async {
    userService.getOne(userId).map { maybeUser =>
      maybeUser.map { user =>
        Ok(Json.toJson(user))
      }.getOrElse(NotFound)
    }
  }

  @ApiOperation(value = "Add a new User to the list", response = classOf[Void], code = 201)
  @ApiResponses(Array(new ApiResponse(code = 400, message = "Invalid User format")))
  @ApiImplicitParams(Array(new ApiImplicitParam(value = "The User to add, in Json Format", required = true, dataType = "models.User", paramType = "body")))
  def createUser(): Action[JsValue] = Action.async(parse.json) {
    _.body.validate[User].map { user =>
      userService.create(user).map { _ =>
        Created
      }
    }.getOrElse(Future.successful(BadRequest("Invalid User format")))
  }

  @ApiOperation(value = "Update a User", response = classOf[User])
  @ApiResponses(Array(new ApiResponse(code = 400, message = "Invalid User format")))
  @ApiImplicitParams(Array(new ApiImplicitParam(value = "The updated User, in Json Format", required = true, dataType = "models.User", paramType = "body")))
  def updateUser(@ApiParam(value = "The id of the User to update") userId: String): Action[JsValue] = Action.async(parse.json){ req =>
    req.body.validate[User].map { user =>
      userService.update(userId, user).map {
        case Some(`user`) => Ok(Json.toJson(user))
        case _ => NotFound
      }
    }.getOrElse(Future.successful(BadRequest("Invalid Json")))
  }

  @ApiOperation(value = "Delete a User",response = classOf[User]  )
  def deleteUser(@ApiParam(value = "The id of the User to delete") userId: String): Action[AnyContent] = Action.async { req =>
    userService.delete(userId).map {
      case Some(user) => Ok(Json.toJson(user))
      case _ => NotFound
    }
  }
}
